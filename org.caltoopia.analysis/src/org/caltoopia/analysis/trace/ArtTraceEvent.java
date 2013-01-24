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

/**
 * A TraceEvent, as read from a trace file ("trace_[0-9].xml") that is produced by the ACTORS run-time.
 * Each event belongs to a CPU (separate trace file), has a time stamp (in clock cycles) and
 * an index (0,1,2,...) that is used for synchronization of the per-core traces.
 * In addition a trace event may be associated with an action, have an execution time (duration).
 * 
 * It is assumed that the "step" attribute is unique among all trace events, since it is used to establish
 * a total order among events.
 */
public class ArtTraceEvent implements Comparable<ArtTraceEvent>, JobInstance {
	private long mTimeStamp;
	private int mStep, mExecutionTime;
	private int mAction;
	private int mCpu;
	
	public ArtTraceEvent(long timeStamp, int step, int executionTime, int action, int cpu) {
		mTimeStamp=timeStamp;
		mStep=step;
		mExecutionTime=executionTime;
		mAction=action;
		mCpu=cpu;
	}
	
	
	@Override
	public int compareTo(ArtTraceEvent e) {
		return mStep - e.mStep;
	}


	/**
	 * @return time stamp in clock cycles
	 */
	@Override
	public long getTimeStamp() {
		return mTimeStamp;
	}
	
	/**
	 * @param timeStamp
	 * sets time stamp (in clock cycles)
	 */
	@Override
	public void setTimeStamp(long timeStamp) {
		mTimeStamp=timeStamp;
	}

	/**
	 * @return CPU index (0,1,2,...) of event (the CPU which logged the event)
	 */
	@Override
	public int getCPU() {
		return mCpu;
	}

	/**
	 * set the CPU index (0,1,2,...) of event
	 * @param cpu
	 */
	@Override
	public void setCpu(int cpu) {
		mCpu=cpu;
	}

	/**
	 * @return a sequence index, which is unique among the per-CPU traces (used to synchronize traces)
	 */
	public int getSequenceIndex() {
		return mStep;
	}

	/**
	 * @return the Action associated with this TraceEvent (NULL if none)
	 */
	public Integer getAction() {
		return mAction;
	}

	/**
	 * @return the execution time associated with this TraceEvent (NULL if none)
	 */
	@Override
	public int getExecutionTime() {
		return mExecutionTime;
	}

	/**
	 * sets the execution time associated with this TraceEvent
	 */
	@Override
	public void setExecutionTime(int execTime) {
		mExecutionTime=execTime;
	}
}
