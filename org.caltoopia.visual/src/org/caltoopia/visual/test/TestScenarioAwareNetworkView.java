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
import org.caltoopia.analysis.actor.ScenarioAwareActorAnalysis;
import org.caltoopia.analysis.air.ActorInstance;
import org.caltoopia.analysis.air.Network;
import org.caltoopia.analysis.network.ScenarioAwareNetworkAnalysis;
import org.caltoopia.analysis.network.SneakyNetworkAnalyzer;
import org.caltoopia.visual.network.NetworkController;
import org.caltoopia.visual.network.NetworkView;
import org.caltoopia.visual.util.ColorCodeLegend;
import org.caltoopia.visual.util.GuiAction;
import com.mxgraph.view.mxGraph;

public class TestScenarioAwareNetworkView extends TestNetworkView {

	private Color mSingleRateStaticColor=new Color(0xC0,0xA0,0xC0);
	private Color mMultiRateStaticColor=new Color(0xFF,0xC0,0x40);
	private Color mCycloStaticColor=new Color(0xF0,0xE0,0x40);
	private Color mSADynamicColor=new Color(0x40,0xC0,0x40);
	private Color mDetectorColor=new Color(0xC3,0xD9,0x00);
	private Color mUnclassifiedColor=new Color(0xFF,0x40,0x40);
	private Color mNoImplementationColor=new Color(0xFF,0xFF,0xFF);
		
	private String mSingleRateStaticStyle="#C0A0C0";
	private String mMultiRateStaticStyle="#FFC040";
	private String mCycloStaticStyle="#F0E040";
	private String mSADynamicStyle="#40C040";
	private String mDetectorStyle="#C3D900";
	private String mUnclassifiedStyle="#FF4040";
	private String mNoImplementationStyle="#FFFFFF";
	
	protected void test(Network network) {
		mxGraph graph=new mxGraph();
		NetworkView view=new NetworkView(graph);
		NetworkController ctrl=new ScenarioAwareNetworkController(view, this.pathToDetectorFSMs);
		
		view.setPreferredSize(new Dimension(800,600));
		ctrl.setNetwork(network);
		ctrl.createFrame();
		view.zoomToFit();
	}
	
	private class ScenarioAwareNetworkController extends NetworkController {

		String detectorFSMPath;
		public ScenarioAwareNetworkController(NetworkView view, String p) {
			super(view);
			detectorFSMPath = p;
		}
	
		public void classify() {
			Network network=getNetworkModel();
			NetworkView view=getNetworkView();
			SneakyNetworkAnalyzer mNetworkAnalyzer=new SneakyNetworkAnalyzer();
			ScenarioAwareNetworkAnalysis scenarioAwareAnalysis=
					new ScenarioAwareNetworkAnalysis(network, mNetworkAnalyzer.analyze(network), detectorFSMPath);
			
			for (ActorInstance actor: network.getActors()) {
				ScenarioAwareActorAnalysis actorAnalysis=scenarioAwareAnalysis.getScenarioAwareActorAnalysis(actor);
				// Set style here depending on the classification of actor
				String style=mNoImplementationStyle;
				if(actor.hasImplementation()){
					switch (actorAnalysis.getScenarioAwareActorInstanceType()) {
					case SA_STATIC:
						switch (actorAnalysis.getActorInstanceType()){
						case SINGLE_RATE_STATIC:
							style = mSingleRateStaticStyle;
							break;
						case MULTI_RATE_STATIC:
							style = mMultiRateStaticStyle;
							break;
						case CYCLO_STATIC:
							style = mCycloStaticStyle;
							break;
						}
						break;
					case SA_DYNAMIC:
						style=mSADynamicStyle;
						break;
					case SA_DETECTOR:
						style=mDetectorStyle;
						break;
					case UNCLASSIFIED:
						style=mUnclassifiedStyle;
						break;
					}
				}
				view.setActorFillColor(actor, style);
			}
		}

		@Override
		protected void createActions() {
			GuiAction action=new GuiAction("Classify") {
				private static final long serialVersionUID = 1L;

				@Override
				public void stateChanged() {
					setEnabled(ScenarioAwareNetworkController.this.getNetworkView()!=null);
				}

				@Override
				public void actionPerformed(ActionEvent e) {
					ScenarioAwareNetworkController.this.classify();
				}
			};
			action.stateChanged();
			getActions().add(action);
			
			super.createActions();
		}
		
		@Override
		protected JComponent createStatusLine() {
			ColorCodeLegend legend=new ColorCodeLegend();
			
			legend.add(mSingleRateStaticColor,"Single-rate-static");
			legend.add(mMultiRateStaticColor,"Multi-rate-static");
			legend.add(mCycloStaticColor,"Cyclo-static");
			legend.add(mSADynamicColor,"Scenario-aware-dynamic");
			legend.add(mDetectorColor,"Detector");
			legend.add(mUnclassifiedColor,"Unclassified");
			legend.add(mNoImplementationColor,"No Implementation");
			
			return legend;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TestScenarioAwareNetworkView().test(args);
	}
}
