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
	
	public ArtTraceFileReader(ErrorConsole errConsole) {
		super(errConsole);
	}
	
	public List<ArtTraceEvent> readTraceFile(File file, int cpuIndex) {
		Document doc=readDocument(file);
		
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
		Long timestamp=getRequiredLongAttribute(element,"timestamp");
		
		if (timestamp!=null) {
			if (mLastEvent!=null) {
				// Fill in execution time of last event...
				String attr=element.getAttribute("attr");
				Integer value=getIntegerAttribute(element,"value");
				int execTime=(attr!=null && attr.equals("exectime") && value!=null)? value : // attr="exectime" value="1234" 
					         (int) (timestamp - mLastEvent.getTimeStamp());                  // or diff between consecutive timestamps 
				
				if (execTime>=0) {
					mLastEvent.setExecutionTime(execTime);
				}
				else {
					String msg=(attr!=null && attr.equals("exectime") && value!=null)? 
							("negative execution time: "+value) : 
							("decreasing sequence of timestamps: "+mLastEvent.getTimeStamp()+" followed by "+timestamp); 
					error(msg);
				}
				mLastEvent=null;
			}

			Integer step=getIntegerAttribute(element,"step");
			Integer action=getIntegerAttribute(element,"action");
			if (step!=null && action!=null) {
				int t=0;
				mLastEvent=new ArtTraceEvent(timestamp,step,t,action,mCpuIndex);
				return mLastEvent;
			}
		}
		
		return null;
	}
	
	protected ArtTraceEvent readOther(Element element) {
		return null;
	}
}
