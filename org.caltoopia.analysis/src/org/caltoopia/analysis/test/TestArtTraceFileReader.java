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
import java.util.Collection;

import org.caltoopia.analysis.trace.ArtTraceEvent;
import org.caltoopia.analysis.trace.ArtTraceFileReader;
import org.caltoopia.analysis.util.io.ErrorConsole;
import org.caltoopia.analysis.util.io.StdErrConsole;

public class TestArtTraceFileReader {

	public static void main(String[] args) {
		ErrorConsole errorConsole=new StdErrConsole();
		ArtTraceFileReader reader=new ArtTraceFileReader(errorConsole);
		
		if (args.length==0) {
			System.err.println("Usage: TestArtTraceFileReader <input-file>...");
			System.exit(1);
		}


		for (int cpuIndex=0; cpuIndex<args.length; ++cpuIndex) {
			File input=new File(args[cpuIndex]);
			Collection<ArtTraceEvent> trace=reader.readTraceFile(input, cpuIndex);

			System.out.println("read "+trace.size()+" trace events from "+args[cpuIndex]);
			System.out.println("timestamp step action exec.time");
			for (ArtTraceEvent e: trace) {
				Integer action=e.getAction();
				Integer execTime=e.getExecutionTime();
				System.out.printf("%9d %4d", e.getTimeStamp(), e.getSequenceIndex());
				if (action!=null) {
					System.out.printf(" %6d", action);
				}
				else
					System.out.print("       ");
				if (execTime!=null) {
					System.out.printf(" %9d", execTime);
				}
				System.out.println();
			}
		}
	}
}
