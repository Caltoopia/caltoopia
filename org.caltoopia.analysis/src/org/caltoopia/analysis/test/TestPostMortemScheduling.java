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

package org.caltoopia.analysis.test;

import java.util.ArrayList;
import java.util.List;

import org.caltoopia.analysis.trace.ArtTraceEvent;
import org.caltoopia.analysis.trace.CpuAffinity;
import org.caltoopia.analysis.trace.FifoScheduler;
import org.caltoopia.analysis.trace.Trace;
import org.caltoopia.analysis.util.graph.DiGraph;

public class TestPostMortemScheduling extends TestCausationTraceBuilder {
	
	protected FifoScheduler<ArtTraceEvent> mScheduler;
	protected int mNumGeneralCores;
	
	public TestPostMortemScheduling(FifoScheduler<ArtTraceEvent> scheduler) {
		mScheduler=scheduler;
	}
	
	public Trace<ArtTraceEvent> schedule(String[] args) {
		List<String> files=new ArrayList<String>();
		int i=0;
		while (i<args.length) {
			if (args[i].startsWith("-n")) {
				if (args[i].length()==2) {
					if (i+1<args.length) {
						mNumGeneralCores=Integer.valueOf(args[i+1]);
						++i;
					}
				}
				else {
					mNumGeneralCores=Integer.valueOf(args[i].substring(2));
				}
			}
			else {
				files.add(args[i]);
			}
			++i;
		}
		
		return (mNumGeneralCores>0)? schedule(files.toArray(new String[files.size()]), mNumGeneralCores) : null;
	}
	
	public Trace<ArtTraceEvent> schedule(String[] args, int numCores) {
		DiGraph<ArtTraceEvent> precedenceDAG=buildCausationTrace(args);
		if (precedenceDAG!=null && mStateDep!=null && mBundle!=null) {
			CpuAffinity<ArtTraceEvent,Object> affinity=mStateDep.getTraceEventAffinity(mBundle.getNetwork());
			return mScheduler.schedule(precedenceDAG, numCores, affinity);
		}
		else
			return null;
	}
	
	protected void printSynopsis() {
		System.err.println("Usage: TestPostMortemScheduling [-n num.cores] state_depend.xml net_trace.xml <trace-file>...");		
	}
	
	public static void main(String[] args) {
		TestPostMortemScheduling test=new TestPostMortemScheduling(new FifoScheduler<ArtTraceEvent>());
		Trace<ArtTraceEvent> trace=test.schedule(args);
			
		if (trace!=null) {
			test.printTrace(trace, test.mBundle.getNetwork());
		}
	}		
	
}
