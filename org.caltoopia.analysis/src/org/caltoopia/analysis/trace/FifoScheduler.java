/* 
 * Copyright (c) Ericsson AB, 2013
 * All rights reserved.
 *
 * License terms:
 *
 * Redistribution and use in source and binary forms, 
 * with or without modification, are permitted provided 
 * that the following conditions are met:
 *     * Redistributions of source code must retain the above 
 *       copyright notice, this list of conditions and the 
 *       following disclaimer.
 *     * Redistributions in binary form must reproduce the 
 *       above copyright notice, this list of conditions and 
 *       the following disclaimer in the documentation and/or 
 *       other materials provided with the distribution.
 *     * Neither the name of the copyright holder nor the names 
 *       of its contributors may be used to endorse or promote 
 *       products derived from this software without specific 
 *       prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND 
 * CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, 
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; 
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) 
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR 
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS 
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.caltoopia.analysis.trace;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import org.caltoopia.analysis.util.graph.DiGraph;

public class FifoScheduler<J extends JobInstance> {
	
	public Trace<J> schedule(DiGraph<J> precedenceDAG, int numGeneralCores, CpuAffinity<J,? extends Object> affinity) {
		Collection<? extends Object> specialCores=affinity.getCpus();
		long mEarliestStartingTime[]=new long[numGeneralCores+specialCores.size()];
		List<J> mSchedule=new ArrayList<J>();
		Map<J,Integer> mIndegree=new HashMap<J,Integer>();
		int initialCapacity=31;
		Queue<J> readyQ=new PriorityQueue<J>(initialCapacity, new JobComparator());
		
		/*
		 * First insert the sources of the precedence DAG into readyQ
		 */
		for (J job: precedenceDAG.getVertices()) {
			if (precedenceDAG.getPredecessors(job).isEmpty()) {
				job.setTimeStamp(0); // Earliest starting time
				readyQ.add(job);
			}
		}
		
		/*
		 * Repeatedly:
		 * 1) Remove the first, ready job
		 * 2) Schedule it as early as possible
		 * 3) Update the "indegree" of waiting jobs and insert ready jobs into readyQ 
		 */
		while (!readyQ.isEmpty()) {
			J job=readyQ.remove();
			
			// Remove the first ready job
			mSchedule.add(job);			

			// Find cpu and earliest starting time (next free slot on cpu)
			Object aCpu=affinity.getAffinity(job);
			int cpu=(aCpu==null)? 0 : numGeneralCores+indexOf(aCpu,specialCores);
			long timestamp=job.getTimeStamp(); // Not possible to start earlier than this (due to precedence)
                                               // Also: no point in looking for a cpu with an earlier free timeslot
			long earliest=mEarliestStartingTime[0];
			
			if (cpu==0) {
				// Dynamic processor allocation, can we find an earlier dispatch? 
				for (int i=1; i<numGeneralCores && earliest>timestamp; ++i) {
					long t=mEarliestStartingTime[i];
					if (t<earliest) {
						earliest=t;
						cpu=i;
					}
				}
			}
			else {
				// Fixed affinity
				earliest=mEarliestStartingTime[cpu];
			}
			
			earliest=Math.max(earliest, timestamp); // earliest starting time (due to precedence)
			job.setCpu(cpu);
			job.setTimeStamp(earliest);
			long completionTime=earliest+job.getExecutionTime();
			mEarliestStartingTime[cpu]=completionTime;
			
			// Update the jobs that are directly depending on this job
			for (J succ: precedenceDAG.getSuccessors(job)) {
				Integer inDegree=mIndegree.get(succ);
				if (inDegree==null) {
					// First time a predecessor is completed, compute inDegree
					inDegree=precedenceDAG.getPredecessors(succ).size()-1;
					succ.setTimeStamp(completionTime);
				}
				else {
					inDegree--;
					if (completionTime>succ.getTimeStamp()) {
						succ.setTimeStamp(completionTime);
					}
				}
				
				if (inDegree==0) {
					// Job is ready
					mIndegree.remove(succ);
					readyQ.add(succ);
				}
				else {
					// Job waiting, update map
					mIndegree.put(succ, inDegree);
				}
			}
		}
		
		return new SimpleTrace<J>(mSchedule);
	}
	
	private int indexOf(Object key, Collection<? extends Object> objects) {
		int i=0;
		
		for (Object o: objects) {
			if (key.equals(o))
				return i;
			else
				++i;
		}
		return -1;
	}
	
	protected class JobComparator implements Comparator<J> {

		@Override
		public int compare(J first, J second) {
			return Long.signum(first.getTimeStamp() - second.getTimeStamp());
		}
		
	}
}
