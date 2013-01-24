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

import java.util.Collection;

import org.caltoopia.analysis.air.ActorSchedule;
import org.caltoopia.analysis.air.State;

/**
 * Analysis of an actor with respect to termination, timing dependence, determinism and action schedule.
 * Results are approximative and conservative in the following sense:
 * 1) The possibility of termination is assumed unless the actor has been proven to execute indefinitely
 *    (given sufficient input).
 * 2) The possibility of dependence on the arrival time of inputs is assumed unless it has been proven that
 *    the actor can use blocking reads to retrieve inputs.
 * 3) The possibility of incomplete/non-deterministic specification of the actor's consumption and production
 *    rates are assumed unless it has been proven that the actor cannot reach a state, in which the choice of
 *    rates is arbitrary.
 * 4) The schedule is assumed to be dynamic (depend on run-time tests) unless it has been proven that there
 *    is a static schedule that can be determined beforehand.
 *    
 * If the outcome of (1)-(4) is negative, the candidate states that might be the cause are identified using
 * getXXXStates(), see below.
 */
public interface ActorAnalysis {
	/**
	 * @return true if the actor is known to execute indefinitely (never terminate when given sufficient input)
	 *         false otherwise (including the case when the actor may terminate, but is not known to do so)
	 */
	boolean executesIndefinitely();

	/**
	 * @return the collection of states, in which the actor may terminate
	 */
	Collection<State> getPotentiallyTerminalStates();
	
	/**
	 * @return true if the actor is known to be independent of timing (its selection of actions to fire
	 *         does not depend on the arrival time of inputs).
	 *         false otherwise (including the case when arrival times may matter, but is not known to do so)
	 *         
	 * Timing independence implies that it is not necessary to check for the absence of input in order to
	 * schedule the firings of the actor, which in turn means that blocking read operations can be used.
	 */
	boolean isTimingIndependent();

	/**
	 * @return the collection of states, in which the actor may make a timing-dependent transition
	 */
	Collection<State> getPotentiallyTimingDependentStates();
	
	/**
	 * @return true if the consumption and production rates of the actor are known to be completely specified
	 *         (in all states the choice of rates is determinate/not subject to a particular implementation,
	 *         given the actors state, availabilty and values of inputs).
	 *         false otherwise (including the case when there may be an arbitrary choice of actions, but it is 
	 *         not known that this state is reachable).
	 *         
	 * This test relates to the complete and determinate specification of an actor. However, timing dependence
	 * is factored out (we assume that the availability of inputs is given) and we are only concerned with the
	 * consumption and production rates (not the values of outputs produced).
	 */
	boolean hasDeterminateRates();
	
	/**
	 * @return the collection of states, in which the actor may make an arbitrary choice of actions ("be under-specified")
	 */	
	Collection<State> getPotentiallyNonDeterminateStates();
	
	/**
	 * @return true if a static schedule of the actor firings has been identified
	 *         false if no such schedule was found (including the case when the actor is not known to have
	 *         dynamic/input dependent schedules)
	 */
	boolean hasStaticSchedule();
		
	/**
	 * @return the static schedule of actor firings (null if none was found)
	 */
	StaticActorSchedule getStaticSchedule();
	
	/**
	 * @return the collection of states, in which the actor may 
	 */
	Collection<State> getPotentiallyInputDependentStates();

	
	/**
	 * @return the schedule (FSM) of the actor
	 */
	ActorSchedule getSchedule();
}
