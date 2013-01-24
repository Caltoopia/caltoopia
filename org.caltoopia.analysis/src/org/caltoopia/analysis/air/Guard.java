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

package org.caltoopia.analysis.air;

import java.util.Collection;
import java.util.Map;

import org.caltoopia.analysis.util.collections.UnionOfDisjointIntervals;

/**
 * Represents the guard of an action
 */
public interface Guard {

	/**
	 * @return true iff this is an input-dependent guard (=it looks ahead on some port)
	 */
	boolean dependsOnInput();
	
	/**
	 * @return the collection of input look-aheads, which this guard depends on
	 *         (thus empty unless input-dependent)
	 */
	Collection<? extends InputLookAhead> getInputLookAheads();
	
	/**
	 * @return true iff this is a state-dependent guard (=it depends on some state variable)
	 */
	boolean dependsOnState();
	
	/**
	 * @return the collection of StateVariables, which this guard depends on
	 *         (thus empty unless state-dependent)
	 */
	Collection<? extends StateVariable> getStateVariables();
	
	/**
	 * @return the set of "modes" (constants) accepted by this guard (null if this guard fails to match).
	 * 
	 * What we require:
	 * 1) That the guard depends on exactly one input,
	 * 2) The "index" of the corresponding look-ahead is zero (the next token to be consumed),
	 * 3) The guard can be expressed as testing whether the input belongs to a set of constants
	 *    formed by conjunctions/disjunctions of simpler tests of the form x relop CONSTANT,
	 *    where x is the variable that is bound to the input, relop is a relational operator
	 *    (=,!=,< etc) and CONSTANT is a compile-time constant.
	 *    
	 * If (1) through (3) are satisfied, the constant is returned (null otherwise). 
	 */
	UnionOfDisjointIntervals matchModeControlGuard();
	
	Map<InputLookAhead, UnionOfDisjointIntervals> matchScenarioAwareGuard();
	
	UnionOfDisjointIntervals getScenarioAwareGuardIntervals(InputLookAhead ila);
}
