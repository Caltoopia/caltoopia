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

import org.caltoopia.analysis.air.ActorImplementation;
import org.caltoopia.analysis.air.ActorInstance;
import org.caltoopia.analysis.air.Network;
import org.caltoopia.analysis.air.State;
import org.caltoopia.analysis.air.Transition;
import org.caltoopia.analysis.iradapter.CaltoopiaNetwork;
import org.caltoopia.analysis.util.collections.UnionOfDisjointIntervals;

public class TestModeControlGuard  {

	private TestFrontEnd mFrontEnd;
	
	public TestModeControlGuard() {
		mFrontEnd=TestFrontEnd.createStandAlone();
	}
	
	public void test(String args[]) {
		if (mFrontEnd.parseCommandLine(args)) {
			CaltoopiaNetwork network=mFrontEnd.elaborate();
		
			if (network!=null) {
				printNetwork(network);
			}
		}
	}
	
	public void printNetwork(Network network) {
		for (ActorInstance actor: network.getActors()) {
			System.out.println("actor "+actor.getInstanceName());
			
			ActorImplementation actorImpl=actor.getImplementation();
			if (actorImpl!=null) {
				for (State state: actorImpl.getSchedule().getStates()) {
					System.out.println("  state "+state.getOriginalStateTag());
					for (Transition t: state.getTransitions()) {
						System.out.print("  ->"+t.getTargetState().getOriginalStateTag()+": ");
						
						if (t.hasGuard()) {
							UnionOfDisjointIntervals modes=t.getGuard().matchModeControlGuard();
							if (modes!=null)
								System.out.println("[mode="+modes+"]");
							else
								System.out.println("[other guard]");
						}
						else
							System.out.println("[else/unguarded]");
					}
				}
			}
		}
	}
	
	
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TestModeControlGuard().test(args);
	}
}
