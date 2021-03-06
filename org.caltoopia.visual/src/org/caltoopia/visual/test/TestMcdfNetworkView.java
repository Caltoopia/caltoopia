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

package org.caltoopia.visual.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import org.caltoopia.analysis.actor.McdfActorAnalysis;
import org.caltoopia.analysis.air.ActorInstance;
import org.caltoopia.analysis.air.Network;
import org.caltoopia.analysis.network.McdfNetworkAnalysis;
import org.caltoopia.analysis.network.SneakyNetworkAnalyzer;
import org.caltoopia.visual.network.NetworkController;
import org.caltoopia.visual.network.NetworkView;
import org.caltoopia.visual.util.ColorCodeLegend;
import org.caltoopia.visual.util.GuiAction;
import com.mxgraph.view.mxGraph;

public class TestMcdfNetworkView extends TestNetworkView {

	private Color mAmodalColor=new Color(0xC0,0xA0,0xC0);
	private Color mMcColor=new Color(0xFF,0xC0,0x40);
	private Color mDataDependentColor=new Color(0xF0,0xE0,0x40);
	private Color mModalColor=new Color(0x40,0xC0,0x40);
	private Color mNAColor=new Color(0xC3,0xD9,0xFF);
	private Color mOtherColor=new Color(0xFF,0x40,0x40);
		
	private String mAmodalStyle="#C0A0C0";
	private String mMcStyle="#FFC040";
	private String mDataDependentStyle="#F0E040";
	private String mModalStyle="#40C040";
	private String mNAStyle="#C3D9FF";
	private String mOtherStyle="#FF4040";
	
	protected void test(Network network) {
		mxGraph graph=new mxGraph();
		NetworkView view=new NetworkView(graph);
		NetworkController ctrl=new McdfNetworkController(view);
		
		view.setPreferredSize(new Dimension(800,600));
		ctrl.setNetwork(network);
		ctrl.createFrame();
		view.zoomToFit();
	}
	
	private class McdfNetworkController extends NetworkController {

		public McdfNetworkController(NetworkView view) {
			super(view);
		}
	
		public void classify() {
			Network network=getNetworkModel();
			NetworkView view=getNetworkView();
			SneakyNetworkAnalyzer mNetworkAnalyzer=new SneakyNetworkAnalyzer();
			McdfNetworkAnalysis mcdfAnalysis=
					new McdfNetworkAnalysis(network, mNetworkAnalyzer.analyze(network));
			
			mcdfAnalysis.getModeControlActors();
			
			for (ActorInstance actor: network.getActors()) {
				McdfActorAnalysis actorAnalysis=mcdfAnalysis.getMcdfActorAnalysis(actor);
				// Set style here depending on the classification of actor
				String style=mNAStyle;
				
				switch (actorAnalysis.getMcdfActorInstanceType()) {
				case MC:
					style=mMcStyle;
					break;
				case SWITCH:
				case SELECT:
				case TUNNEL:
					style=mDataDependentStyle;
					break;
				case AMODAL:
					style=mAmodalStyle;
					break;
				case MODAL:
					style=mModalStyle;
					break;
				case UNCLASSIFIED:
					style=mOtherStyle;
					break;
				}

				view.setActorFillColor(actor, style);
			}
		}

		@Override
		protected void createActions() {
			GuiAction action=new GuiAction("Classify") {
				@Override
				public void stateChanged() {
					setEnabled(McdfNetworkController.this.getNetworkView()!=null);
				}

				@Override
				public void actionPerformed(ActionEvent e) {
					McdfNetworkController.this.classify();
				}
			};
			action.stateChanged();
			getActions().add(action);
			
			super.createActions();
		}
		
		@Override
		protected JComponent createStatusLine() {
			ColorCodeLegend legend=new ColorCodeLegend();
			
			legend.add(mMcColor,"Mode Controller");
			legend.add(mAmodalColor,"Amodal Actors");
			legend.add(mDataDependentColor,"Data-dependent Actors");
			legend.add(mModalColor,"Modal Actors");
			legend.add(mNAColor,"Not Analyzed");
			legend.add(mOtherColor,"Other MoC");
			
			return legend;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TestMcdfNetworkView().test(args);
	}
}
