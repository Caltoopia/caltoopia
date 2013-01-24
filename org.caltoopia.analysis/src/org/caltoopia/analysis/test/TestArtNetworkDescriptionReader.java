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

import org.caltoopia.analysis.air.Action;
import org.caltoopia.analysis.air.ActorInstance;
import org.caltoopia.analysis.air.Connection;
import org.caltoopia.analysis.trace.ArtNetworkDescription;
import org.caltoopia.analysis.trace.ArtNetworkDescriptionReader;
import org.caltoopia.analysis.util.io.ErrorConsole;
import org.caltoopia.analysis.util.io.StdErrConsole;

public class TestArtNetworkDescriptionReader {

	public static void main(String[] args) {
		ErrorConsole errorConsole=new StdErrConsole();
		ArtNetworkDescriptionReader reader=new ArtNetworkDescriptionReader(errorConsole);
		
		if (args.length==0) {
			System.err.println("Usage: TestArtNetworkDescriptorReader <input-file>...");
			System.exit(1);
		}
		
		
		for (String fileName: args) {
			File input=new File(fileName);
			ArtNetworkDescription network=reader.readNetworkDescription(input);
			
			System.out.println("\nNetwork: "+network.getName());
			for (ActorInstance a: network.getActors()) {
				System.out.println("  actor "+a.getInstanceName());
			}
			System.out.println("Connections:");
			for (Connection c: network.getConnections()) {
				System.out.println("  "+c.getProducerPort()+" --> "+c.getConsumerPort());
			}
			int i=0;
			Action action=network.getAction(i);
			while (action!=null) {
				System.out.println("  action "+i+": "+action.getName());
				System.out.println("    "+action.getPortSignature());
				action=network.getAction(++i);
			}
		}
		
		
	}

}
