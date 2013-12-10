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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.caltoopia.analysis.util.io.ErrorConsole;
import org.caltoopia.analysis.util.io.XmlReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ArtTraceFileReader extends XmlReader {

	protected int mCpuIndex;
	protected List<ArtTraceEvent> mTrace;
	protected ArtTraceEvent mLastEvent;
	protected boolean mExecutionTimeSet;
	protected DecorationMap<ArtTraceEvent,String> mDecorationMap;
	
	public ArtTraceFileReader(ErrorConsole errConsole) {
		super(errConsole);
	}
	
	public List<ArtTraceEvent> readTraceFile(File file, int cpuIndex, DecorationMap<ArtTraceEvent,String> decorationMap) {
		Document doc=readDocument(file);
		
		mDecorationMap=decorationMap;
		for (Node node=doc.getFirstChild(); node!=null; node=node.getNextSibling()) {
			if (isTag(node,"execution-trace")) {
				mCpuIndex=cpuIndex;
				mTrace=createTrace();
				readExecutionTrace((Element) node);
				return mTrace;
			}
		}
		
		return null;
	}
	
	protected void readExecutionTrace(Element element) {
		for (Node node=element.getFirstChild(); node!=null; node=node.getNextSibling()) {
			ArtTraceEvent event=null;
			
			if (isTag(node,"trace")) {
				event=readTraceEvent((Element) node);
			}
			else if (node.getNodeType()==Node.ELEMENT_NODE) {
				event=readOther((Element) node);
			}
			
			if (event!=null)
				mTrace.add(event);
		}
	}
	
	protected List<ArtTraceEvent> createTrace() {
		return new ArrayList<ArtTraceEvent>();
	}
	
	protected ArtTraceEvent readTraceEvent(Element element) {
		Integer action=getIntegerAttribute(element,"action");
		
		if (action!=null) {
			// <trace action="id" timestamp="t" step="s" />
			Long timestamp=getRequiredLongAttribute(element,"timestamp");
			Integer step=getRequiredIntegerAttribute(element,"step");
			
			if (mLastEvent!=null && !mExecutionTimeSet) {
				// Fill in execution time of last event: difference of consecutive timestamps
				int execTime=(int) (timestamp - mLastEvent.getExecutionTime());
				if (execTime>=0) {
					mLastEvent.setExecutionTime(execTime);
				}
				else {
					error("decreasing sequence of timestamps: "+mLastEvent.getTimeStamp()+" followed by "+timestamp);
				}
			}
			
			int t=0;
			mLastEvent=new ArtTraceEvent(timestamp,step,t,action,mCpuIndex);
			mExecutionTimeSet=false;
			return mLastEvent;
		}
		else {
			String attr=element.getAttribute("attr");
			
			if (mLastEvent!=null && attr!=null) {
				// Decorate last event with a (key,value) pair
				String value=element.getAttribute("value");
			
				if (attr.equals("exectime")) {
					// <trace attr="exectime" value="1234"/>
					int execTime=Integer.valueOf(value);
					if (execTime>=0) {
						mLastEvent.setExecutionTime(execTime);
						mExecutionTimeSet=true;
					}
					else {
						error("negative execution time: "+value);
					}
				}
				else if (mDecorationMap!=null){
					// Put other attributes in the decoration map (if there is one)
					mDecorationMap.decorate(mLastEvent, attr, value);
				}
			}
		}
		
		return null; // Not a new trace event
	}
	
	protected ArtTraceEvent readOther(Element element) {
		return null;
	}
}
