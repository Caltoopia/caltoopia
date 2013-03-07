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
package org.caltoopia.analysis.actor;

import java.io.PrintStream;
import java.util.Collection;

import org.caltoopia.analysis.air.ActorInstance;
import org.caltoopia.analysis.air.State;

public class ActorClassifierOutput {

	protected PrintStream mOut;
	
	public ActorClassifierOutput(PrintStream out) {
		mOut=out;
	}
	
	public void print(GenericActorAnalysis analysis) {
		mOut.println();
		ActorInstance actor = analysis.getActor();
		mOut.println("actor "+actor.getInstanceName());
		if (analysis!=null) {
			mOut.println("executes indefinitely:   "+(analysis.executesIndefinitely()? "yes" : "no/perhaps"));
			if (!analysis.executesIndefinitely()) {
				mOut.print("potentially terminal states:\n  ");
				println(analysis.getPotentiallyTerminalStates());
			}
			mOut.println("static schedule:         "+(analysis.hasStaticSchedule()?    "yes" : "no/perhaps"));
			if (!analysis.hasStaticSchedule()) {
				mOut.print("potentially input-dependent states:\n  ");
				println(analysis.getPotentiallyInputDependentStates());
			}
			else {
				print(analysis.getStaticSchedule());
			}
			mOut.println("timing independent:      "+(analysis.isTimingIndependent()?  "yes" : "no/perhaps"));
			if (!analysis.isTimingIndependent()) {
				mOut.print("potentially timing-dependent states:\n  ");
				println(analysis.getPotentiallyTimingDependentStates());
			}
			mOut.println("otherwise determinate:   "+(analysis.hasDeterminateRates()?  "yes" : "no/perhaps"));
			if (!analysis.hasDeterminateRates()) {
				mOut.print("potentially non-determinate states:\n  ");
				println(analysis.getPotentiallyNonDeterminateStates());
			}
			mOut.println("Actor Type: "+analysis.getActorInstanceTypeAsString());

		}
		else {
			if (!actor.hasImplementation()){
				mOut.print("extern/unknown implementation");
			}
			mOut.println("actor not analyzed");
		}
	}
	
	public void print(McdfActorAnalysis analysis) {
		mOut.println(analysis.getActor().getInstanceName()+" : "+analysis.getMcdfActorInstanceTypeAsString());
	}
	
	public void print(ScenarioAwareActorAnalysis analysis) {
			mOut.println(analysis.getActor().getInstanceName()+" : "+analysis.getScenarioAwareActorInstanceTypeAsString());
	}
	
	private void println(Collection<State> states) {
		if (!states.isEmpty()) {
			String delimiter="";

			for (State s: states) {
				mOut.print(delimiter+s.getOriginalStateTag());
				delimiter=", ";
			}
			mOut.println();
		}
	}
	
	private void print(StaticActorSchedule schedule) {
		System.out.println("Initial/Transient phase: "+(schedule.hasTransientPhase()?
				                                        schedule.getTransientPhase().toString()
				                                        : "<none>"));
		System.out.println("Repeated phase:          "+(schedule.hasRepeatedPhase()?
				                                        schedule.getRepeatedPhase().toString()
				                                        : "<none>"));
	}
}
