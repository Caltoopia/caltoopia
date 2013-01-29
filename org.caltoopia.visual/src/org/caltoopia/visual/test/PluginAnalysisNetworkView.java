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
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.caltoopia.analysis.actor.GenericActorAnalysis;
import org.caltoopia.analysis.actor.McdfActorAnalysis;
import org.caltoopia.analysis.actor.ScenarioAwareActorAnalysis;
import org.caltoopia.analysis.air.ActorInstance;
import org.caltoopia.analysis.air.Network;
import org.caltoopia.analysis.iradapter.CaltoopiaActorImplemenation;
import org.caltoopia.analysis.iradapter.CaltoopiaActorInstance;
import org.caltoopia.analysis.iradapter.CaltoopiaNetwork;
import org.caltoopia.analysis.network.GenericNetworkAnalysis;
import org.caltoopia.analysis.network.McdfNetworkAnalysis;
import org.caltoopia.analysis.network.ScenarioAwareNetworkAnalysis;
import org.caltoopia.analysis.network.SneakyNetworkAnalyzer;
import org.caltoopia.cli.CompilationSession;
import org.caltoopia.visual.network.NetworkController;
import org.caltoopia.visual.network.NetworkView;
import org.caltoopia.visual.util.ColorCodeLegend;
import org.caltoopia.visual.util.GuiAction;
import com.mxgraph.view.mxGraph;

public class PluginAnalysisNetworkView {
	
	private static JFrame frame=null;
	
	private CompilationSession session;
	
    private int analysisType;
     
    private Color mSingleRateStaticColor=new Color(0xC0,0xA0,0xC0);
	private Color mMultiRateStaticColor=new Color(0xFF,0xC0,0x40);
	private Color mCycloStaticColor=new Color(0xF0,0xE0,0x40);
	private Color mQuasiStaticColor=new Color(0x40,0xC0,0x40);
	private Color mDynamicColor=new Color(0xC3,0xD9,0xFF);
	private Color mUnclassifiedColor=new Color(0xFF,0x40,0x40);
		
	private String mSingleRateStaticStyle="#C0A0C0";
	private String mMultiRateStaticStyle="#FFC040";
	private String mCycloStaticStyle="#F0E040";
	private String mQuasiStaticStyle="#40C040";
	private String mDynamicStyle="#C3D9FF";
	private String mUnclassifiedStyle="#FF4040";
	
	
	private Color mAmodalColor=new Color(0xC0,0xA0,0xC0);
	private Color mMcColor=new Color(0xFF,0xC0,0x40);
	private Color mDataDependentColor=new Color(0xF0,0xE0,0x40);
	private Color mModalColor=new Color(0x40,0xC0,0x40);
	private Color mNAColor=new Color(0xC3,0xD9,0xFF);
		
	private String mAmodalStyle="#C0A0C0";
	private String mMcStyle="#FFC040";
	private String mDataDependentStyle="#F0E040";
	private String mModalStyle="#40C040";
	private String mNAStyle="#C3D9FF";
	
	private Color mSADynamicColor=new Color(0x40,0xC0,0x40);
	private Color mDetectorColor=new Color(0xC3,0xD9,0x00);
	private Color mNoImplementationColor=new Color(0xFF,0xFF,0xFF);
		
	private String mSADynamicStyle="#40C040";
	private String mDetectorStyle="#C3D900";
	private String mNoImplementationStyle="#FFFFFF";
	
	public PluginAnalysisNetworkView(int type) {
		analysisType = type;
	}
		
	public void view(CompilationSession session) {
		org.caltoopia.ir.Network irNetwork=session.getElaboratedNetwork();
		this.session = session;
		// Keep track of instance-to-actor-class mapping
		Map<org.caltoopia.ir.ActorInstance,org.caltoopia.analysis.air.ActorInstance> instanceMap=new LinkedHashMap<org.caltoopia.ir.ActorInstance,org.caltoopia.analysis.air.ActorInstance>();
		for (org.caltoopia.ir.ActorInstance caltoopiaInstance: irNetwork.getActors()) {
			org.caltoopia.ir.AbstractActor caltoopiaClass=session.applyActorParameters(caltoopiaInstance);
			instanceMap.put(caltoopiaInstance, createActor(caltoopiaInstance,caltoopiaClass));
		}

		final Network airNetwork=new CaltoopiaNetwork(irNetwork,instanceMap);
			
		try {	
			SwingUtilities.invokeAndWait(new Runnable(){
				@Override
				public void run() {
					display(airNetwork);
				}
			}
			);
		} 
		catch (Exception e) {
			System.err.println("Error launching viewer! Exception = " + e.getMessage());
		}
	}
	
	
	private org.caltoopia.analysis.air.ActorInstance createActor(org.caltoopia.ir.ActorInstance caltoopiaInstance,
                                                                 org.caltoopia.ir.AbstractActor caltoopiaClass) {		
		return (caltoopiaClass instanceof org.caltoopia.ir.Actor)?
				new CaltoopiaActorImplemenation(caltoopiaInstance,(org.caltoopia.ir.Actor) caltoopiaClass)
			  : new CaltoopiaActorInstance(caltoopiaInstance,caltoopiaClass);
	}
	
	protected void display(Network network) {
		if (frame!=null) {
			frame.dispose();
		}
		
		mxGraph graph=new mxGraph();
		NetworkView view=new NetworkView(graph);
		NetworkController ctrl = null;
		if(analysisType==1 || analysisType == 4){
			ctrl =new GenericNetworkController(view);
		}
		else if(analysisType==2 || analysisType == 5){
			ctrl =new McdfNetworkController(view);			
		}
		else if(analysisType==3 || analysisType == 6){
			ctrl = new ScenarioAwareNetworkController(view);
		}
		
		view.setPreferredSize(new Dimension(800,300));
		
		ctrl.setNetwork(network);
		frame = ctrl.createFrame();
		ctrl.classify();
		view.zoomToFit();
	}
	
	
	public class RunAction extends GuiAction {
		private static final long serialVersionUID=1L;

		public RunAction() {
			super("Run");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {				
			    List<String> command=new ArrayList<String>();
			    String executable=session.getOutputFolder()+File.separator+session.getElaboratedNetwork().getType().getName();
			    command.add(executable);
			    ProcessBuilder builder=new ProcessBuilder(command);		
			    builder.redirectErrorStream(true);
			    Map<String,String> env = builder.environment();
			    env.put("LD_LIBRARY_PATH", System.getenv("LD_LIBRARY_PATH")+
			    		":"+System.getenv("SYSTEMC_HOME")+"/lib-linux");
			    
			    builder.directory(new File(session.getOutputFolder()));		
			    
			    if (!session.getRunOptions().isEmpty()) {
			    	for(String s: session.getRunOptions().split(" "))
			    		command.add(s);
			    }
			    
			    final Process process=builder.start();
			    
			    InputStream is=process.getInputStream();
			    InputStreamReader isr=new InputStreamReader(is);
			    BufferedReader br=new BufferedReader(isr);
			    String line;
			    while ((line=br.readLine())!=null) {
			      session.getOutputStream().println(line);
			    }
			    session.getOutputStream().println("Program terminated!");								
			} catch(Exception x){
				session.getOutputStream().print("Failed to execute binary! ("+x.getMessage()+")");
			}
		}		
	}
	
	private class GenericNetworkController extends NetworkController {

		public GenericNetworkController(NetworkView view) {
			super(view);
		}
	
		public void classify() {
			Network network=getNetworkModel();
			NetworkView view=getNetworkView();
			SneakyNetworkAnalyzer mNetworkAnalyzer=new SneakyNetworkAnalyzer();
			GenericNetworkAnalysis genericAnalysis=
					new GenericNetworkAnalysis(network, mNetworkAnalyzer.analyze(network));
			
			for (ActorInstance actor: network.getActors()) {
				GenericActorAnalysis actorAnalysis=genericAnalysis.getGenericActorAnalysis(actor);
				// Set style here depending on the classification of actor
				String style=mUnclassifiedStyle;
				
				switch (actorAnalysis.getActorInstanceType()) {
				case SINGLE_RATE_STATIC:
					style=mSingleRateStaticStyle;
					break;
				case MULTI_RATE_STATIC:
					style=mMultiRateStaticStyle;
					break;
				case CYCLO_STATIC:
					style=mCycloStaticStyle;
					break;
				case QUASI_STATIC:
					style=mQuasiStaticStyle;
					break;
				case DYNAMIC:
					style=mDynamicStyle;
					break;
				case UNCLASSIFIED:
					style=mUnclassifiedStyle;
					break;
				}

				view.setActorFillColor(actor, style);
			}
		}

		protected void createActions() {
			super.createActions();
			
			GuiAction action=new RunAction(){
				private static final long serialVersionUID=1L;

				@Override
				public void stateChanged() {}
			};
			action.stateChanged();
			mActions.add(action);
		};
		
		@Override
		protected JComponent createStatusLine() {
			ColorCodeLegend legend=new ColorCodeLegend();
			
			legend.add(mSingleRateStaticColor,"Single-rate-static");
			legend.add(mMultiRateStaticColor,"Multi-rate-static");
			legend.add(mCycloStaticColor,"Cyclo-static");
			legend.add(mQuasiStaticColor,"Quasi-static");
			legend.add(mDynamicColor,"Dynamic");
			legend.add(mUnclassifiedColor,"Unclassified");
			
			return legend;
		}
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
					style=mUnclassifiedStyle;
					break;
				}

				view.setActorFillColor(actor, style);
			}
		}

		protected void createActions() {
			super.createActions();
			
			GuiAction action=new RunAction(){
				private static final long serialVersionUID=1L;

				@Override
				public void stateChanged() {}
			};
			action.stateChanged();
			mActions.add(action);
		};
		
		@Override
		protected JComponent createStatusLine() {
			ColorCodeLegend legend=new ColorCodeLegend();
			
			legend.add(mMcColor,"Mode Controller");
			legend.add(mAmodalColor,"Amodal Actors");
			legend.add(mDataDependentColor,"Data-dependent Actors");
			legend.add(mModalColor,"Modal Actors");
			legend.add(mNAColor,"Not Analyzed");
			legend.add(mUnclassifiedColor,"Unclassified");
			
			return legend;
		}
	}
	
	private class ScenarioAwareNetworkController extends NetworkController {

		public ScenarioAwareNetworkController(NetworkView view) {
			super(view);
		}
	
		public void classify() {
			Network network=getNetworkModel();
			NetworkView view=getNetworkView();
			SneakyNetworkAnalyzer mNetworkAnalyzer=new SneakyNetworkAnalyzer();
			ScenarioAwareNetworkAnalysis scenarioAwareAnalysis=
					new ScenarioAwareNetworkAnalysis(network, mNetworkAnalyzer.analyze(network), session.getWorkingDirectory());
			
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

		protected void createActions() {
			super.createActions();
			
			GuiAction action=new RunAction(){
				private static final long serialVersionUID=1L;

				@Override
				public void stateChanged() {}
			};
			action.stateChanged();
			mActions.add(action);
		};
		
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

		
}
