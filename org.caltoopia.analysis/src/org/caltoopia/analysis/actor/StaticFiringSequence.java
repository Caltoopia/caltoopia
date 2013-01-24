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

import java.util.List;

import org.caltoopia.analysis.air.Action;
import org.caltoopia.analysis.air.PortSignature;

/**
 * Represents a static sequence of actor firings (represented as actions) 
 * in the form of a looped schedule:
 * 
 *  L*(s1 ... sn), where L is the "looping factor" and s1 through sn are the 
 *  "sub-sequences" (also StaticFiringSequences). The looped schedule represents
 *  L repetitions of the schedule, s1 ... sn.
 *  
 *  The idea is that a repeated, static, sequence of firings be represented
 *  in a compact manner -even for very large looping factors.
 *  
 *  A StaticFiringSequence is thus defined recursively as an aggregation. At the bottom
 *  is a "trivial" firing sequence, whose flattened form consists of a single action. 
 */
public interface StaticFiringSequence {

	/**
	 * @return true for "trivial" StaticFiringSequences (whose flattened form is a single firing)
	 */
	boolean isTrivial();
	
	/**
	 * @return the looping factor, L, the number of times that the sub-sequences are repeated.
	 */
	int getLoopingFactor();
	
	/**
	 * @return the subsequences s1, s2, ..., sn (null for "trivial" sequences)
	 */
	List<? extends StaticFiringSequence> getSubSequences();
	
	/**
	 * @return the flat sequence of actor firings (represented as actions):
	 *         each subsequence s1, s2, ..., sn flattened and everything repeated L times (the looping factor).
	 */
	List<? extends Action> getFlatSequence();

	/**
	 * @return the total consumption/production rates of the entire sequence
	 *         (adding rates of the subsequences and taking everything times L, the looping factor).
	 */
	PortSignature getPortSignature();
}
