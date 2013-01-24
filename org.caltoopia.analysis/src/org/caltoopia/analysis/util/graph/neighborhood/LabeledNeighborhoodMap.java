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

package org.caltoopia.analysis.util.graph.neighborhood;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.caltoopia.analysis.util.collections.SetWrapper;


public class LabeledNeighborhoodMap<V,VL,EL> extends AbstractNeighborhoodMap<V,VL,EL> {

	@Override
	protected Neighborhood<V,VL,EL> createNeighborhood() {
		return new LabeledNeighborhood<V,VL,EL>();
	}

	static private class LabeledNeighborhood<V,VL,EL> extends AbstractNeighborhood<V,VL,EL> {

		private Map<V,EL> mForwardLinkLabels=new LinkedHashMap<V,EL>();
		private VL mVertexLabel;

		@Override
		public VL getVertexLabel() {
			return mVertexLabel;
		}

		@Override
		public void setVertexLabel(VL vertexLabel) {
			mVertexLabel=vertexLabel;
		}

		@Override
		public EL getForwardLinkLabel(Object neighbor) {
			return mForwardLinkLabels.get(neighbor);
		}


		@Override
		public boolean setForwardLinkLabel(V neighbor, EL edgeLabel) {
			if (mForwardLinkLabels.containsKey(neighbor)) {
				mForwardLinkLabels.put(neighbor, edgeLabel);
				return true;
			}
			else
				return false;
		}

		@Override
		public Set<V> getForwardLinks() {
			return new ForwardLinkSet();
		}
		
		/**
		 * Unlike the keySet of the Map, this set supports add()
		 */
		class ForwardLinkSet extends SetWrapper<V> {

			ForwardLinkSet() {
				super(mForwardLinkLabels.keySet());
			}

			@Override
			public boolean add(V vertex) {
				if (mForwardLinkLabels.containsKey(vertex)) {
					return false;
				}
				else {
					mForwardLinkLabels.put(vertex, null);
					return true;
				}
			}
		}
	}
}

