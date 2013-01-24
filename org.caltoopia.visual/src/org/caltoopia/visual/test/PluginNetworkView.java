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
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.caltoopia.analysis.actor.ActorAnalysis;
import org.caltoopia.analysis.actor.ActorAnalyzer;
import org.caltoopia.analysis.actor.SneakyActorAnalyzer;
import org.caltoopia.analysis.air.ActorInstance;
import org.caltoopia.analysis.air.Network;
import org.caltoopia.analysis.iradapter.CaltoopiaActorImplemenation;
import org.caltoopia.analysis.iradapter.CaltoopiaActorInstance;
import org.caltoopia.analysis.iradapter.CaltoopiaNetwork;
import org.caltoopia.cli.CompilationSession;
import org.caltoopia.visual.network.NetworkController;
import org.caltoopia.visual.network.NetworkView;
import org.caltoopia.visual.util.GuiAction;
import com.mxgraph.view.mxGraph;

public class PluginNetworkView {
	
	private static JFrame frame=null;
	
	private CompilationSession session;
	
	 private static String redish="#FF4040";
     private static String orange="#FFC40";
     private static String yellowish="#F0E040";
     private static String greenish="#40C040";
     private static String bluish="#C3D9FF";
     private static String purple="#C0AC0";
	
	
	public PluginNetworkView() {}
		
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
			});
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
		NetworkController ctrl=new NetworkController(view) {

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
		}; 
			
		view.setPreferredSize(new Dimension(800,300));
		
		ctrl.setNetwork(network);
		frame = ctrl.createFrame();
		classify(network, view);
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
			    if (!session.getWorkingDirectory().isEmpty()) { 
			    	builder.directory(new File(session.getWorkingDirectory()));
			    } else {
			    	builder.directory(new File(session.getOutputFolder()));
			    }
			    if (!session.getRunOptions().isEmpty()) {
			    	command.add(session.getRunOptions());
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
	
	public void classify(Network network, NetworkView view) {
		ActorAnalyzer actorAnalyzer=new SneakyActorAnalyzer();
		
		for (ActorInstance actor: network.getActors()) {
			// Set style here depending on the classification of actor
			String style;

			if (actor.hasImplementation()) {
				ActorAnalysis analysis = actorAnalyzer.analyze(actor.getImplementation());
				if (analysis.hasStaticSchedule()) {
					style=greenish;
				} else if (analysis.isTimingIndependent()){
					style=yellowish;
				} else {
					style=redish;					
				}
			} else {
			  style=bluish;
			}
			view.setActorFillColor(actor, style);
		}
	}
		
}
