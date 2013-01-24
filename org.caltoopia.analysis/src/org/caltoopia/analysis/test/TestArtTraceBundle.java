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

import java.io.File;

import org.caltoopia.analysis.trace.ArtNetworkDescription;
import org.caltoopia.analysis.trace.ArtTraceBundle;
import org.caltoopia.analysis.trace.ArtTraceBundleReader;
import org.caltoopia.analysis.trace.ArtTraceEvent;

public class TestArtTraceBundle {

	public ArtTraceBundle readTraceBundle(String[] args) {
		if (args.length<2) {
			printSynopsis();
			return null;
		}
		
		return readTraceBundle(args,0);
	}

	protected ArtTraceBundle readTraceBundle(String[] args, int first) {
		ArtTraceBundleReader reader=new ArtTraceBundleReader();
		ArtTraceBundle bundle=reader.readNetworkDescription(new File(args[first]));
		System.out.println("read "+bundle.getNetwork().getActors().size()+" actors from "+args[first]);
		
		for (int i=first+1; i<args.length; ++i) {
			reader.addTraceFile(bundle, new File(args[i]));
			System.out.println("read trace events from "+args[i]);
		}
		
		return bundle;		
	}
	
	protected void printSynopsis() {
		System.err.println("Usage: TestArtTraceReader net_trace.xml <trace-file>...");
	}
	
	protected void printTrace(Iterable<ArtTraceEvent> events, ArtNetworkDescription network) {
		System.out.println("timestamp step cpu exec.time action");
		for (ArtTraceEvent e: events) {
			int a=e.getAction();
			System.out.printf("%9d %4d %3d %9d %3d (%s)\n", 
					          e.getTimeStamp(), 
					          e.getSequenceIndex(),
					          e.getCPU(),
					          e.getExecutionTime(),
					          a,
					          network.getAction(a).getName());
		}		
	}
	
	public static void main(String[] args) {
		TestArtTraceBundle test=new TestArtTraceBundle();
		ArtTraceBundle bundle=test.readTraceBundle(args);
		
		if (bundle!=null) {
			test.printTrace(bundle,bundle.getNetwork());
		}
	}

}
