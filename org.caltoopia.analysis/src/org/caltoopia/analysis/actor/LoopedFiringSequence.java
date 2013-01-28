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

import java.util.AbstractList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.caltoopia.analysis.air.Action;
import org.caltoopia.analysis.air.PortInstance;
import org.caltoopia.analysis.air.PortSignature;
import org.caltoopia.analysis.iradapter.VanillaPortSignature;


/**
 * Implementation of StaticFiringSequence with looping factor, flattening of the sequence etc.
 */

public class LoopedFiringSequence implements StaticFiringSequence {

	private int mLoopingFactor;
	private List<? extends StaticFiringSequence> mSubSequences;
	private List<Action> mFlatSequence;
	private PortSignature mPortSignature;
	
	public LoopedFiringSequence(int loopingFactor, List<? extends StaticFiringSequence> subSequences) {
		mLoopingFactor=loopingFactor;
		mSubSequences=subSequences;
		mFlatSequence=new FlatFiringSequence();
		mPortSignature=computePortSignature();
	}
		
	@Override
	public boolean isTrivial() {
		return false;
	}

	@Override
	public int getLoopingFactor() {
		return mLoopingFactor;
	}

	@Override
	public List<? extends StaticFiringSequence> getSubSequences() {
		return mSubSequences;
	}

	@Override
	public List<? extends Action> getFlatSequence() {
		return mFlatSequence;
	}

	@Override
	public PortSignature getPortSignature() {
		return mPortSignature;
	}

	
	@Override
	public String toString() {
		String result="";
		
		if (mLoopingFactor!=1)
			result += mLoopingFactor;
		
		if (mSubSequences.size()==1 && mSubSequences.get(0).isTrivial()) {
			result += mSubSequences.get(0).toString();
		}
		else {
			String delimiter="";

			if (mLoopingFactor!=1)
				result += "(";
			for (StaticFiringSequence s: mSubSequences) {
				result += delimiter + s.toString();
				delimiter=" ";
			}
			if (mLoopingFactor!=1)
				result += ")";
		}
		
		return result;
	}

	private PortSignature computePortSignature() {
		Map<PortInstance,Integer> portmap=new LinkedHashMap<PortInstance,Integer>();
		PortSignature result=new VanillaPortSignature(portmap);
		
		/*
		 * Add mLoopingFactor times the rate of the PortSignatures in the subsequence
		 */
		for (StaticFiringSequence s: mSubSequences) {
			PortSignature subSig=s.getPortSignature();
			for (PortInstance port: subSig.getPorts()) {
				int rate=result.getPortRate(port) + mLoopingFactor*subSig.getPortRate(port);
				portmap.put(port, rate);
			}
		}
		
		return result;
	}
	
	private class FlatFiringSequence extends AbstractList<Action> {

		private int mLengthOfOneIteration;

		public FlatFiringSequence() {
			for (StaticFiringSequence s: LoopedFiringSequence.this.mSubSequences)
				mLengthOfOneIteration += s.getFlatSequence().size();
		}
		
		@Override
		public int size() {
			return LoopedFiringSequence.this.mLoopingFactor*mLengthOfOneIteration;
		}

		@Override
		public Action get(int index) {
			// Map index onto the sub sequences
			index %= mLengthOfOneIteration;
			
			// Then find the sub-sequence to which 'index' belongs
			for (StaticFiringSequence s: LoopedFiringSequence.this.mSubSequences) {
				List<? extends Action> flat=s.getFlatSequence();
				int N=flat.size();
				if (index < N) {
					return flat.get(index);
				}
				else {
					index -= N;
				}
			}
			
			// We should never get here...
			assert(false);
			return null;
		}
	}
}
