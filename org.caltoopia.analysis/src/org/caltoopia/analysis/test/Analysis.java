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

package org.caltoopia.analysis.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.caltoopia.analysis.air.Network;
import org.caltoopia.analysis.iradapter.CaltoopiaActorImplemenation;
import org.caltoopia.analysis.iradapter.CaltoopiaActorInstance;
import org.caltoopia.analysis.iradapter.CaltoopiaNetwork;
import org.caltoopia.analysis.network.NetworkClassifierOutput;
import org.caltoopia.analysis.network.SneakyNetworkAnalyzer;
import org.caltoopia.analysis.network.GenericNetworkAnalysis;
import org.caltoopia.analysis.network.McdfNetworkAnalysis;
import org.caltoopia.analysis.network.ScenarioAwareNetworkAnalysis;
import org.caltoopia.cli.CompilationError;
import org.caltoopia.cli.CompilationSession;

public class Analysis {

	private SneakyNetworkAnalyzer mNetworkAnalyzer = new SneakyNetworkAnalyzer();
	private NetworkClassifierOutput mNetworkOutput =  new NetworkClassifierOutput();
	private int type;
	private CompilationSession session;
	private String sdf3Path;
	private String topNetwork;
	private Network network; 
	
	public enum AnalysisType {
		SDF("SDF"),
		CSDF("CSDF"),
		MCDF("MCDF"),
		FSMSADF("FSMSADF");
		
		private final String name;
		AnalysisType(String n){
			name=n;
		}
		
		public String getName() {
	        return name;
	    }
	}
	
	public Analysis(CompilationSession s, String top, int t, String sdf3) {
		type = t;
		session = s;
		topNetwork = top;
		sdf3Path = sdf3;
		org.caltoopia.ir.Network irNetwork=null;
		try {
			session.elaborateNetwork(topNetwork);
			irNetwork=session.getElaboratedNetwork();
		} catch (CompilationError error) {
			System.err.println(error);
		}
		

		// Keep track of instance-to-actor-class mapping
		Map<org.caltoopia.ir.ActorInstance,org.caltoopia.analysis.air.ActorInstance> instanceMap=new LinkedHashMap<org.caltoopia.ir.ActorInstance,org.caltoopia.analysis.air.ActorInstance>();
		for (org.caltoopia.ir.ActorInstance caltoopiaInstance: irNetwork.getActors()) {
			org.caltoopia.ir.AbstractActor caltoopiaClass=session.applyActorParameters(caltoopiaInstance);
			instanceMap.put(caltoopiaInstance, createActor(caltoopiaInstance,caltoopiaClass));
		}
		
		network = new CaltoopiaNetwork(irNetwork,instanceMap);
	}
	
	private org.caltoopia.analysis.air.ActorInstance createActor(org.caltoopia.ir.ActorInstance caltoopiaInstance,
            org.caltoopia.ir.AbstractActor caltoopiaClass) {		
	return (caltoopiaClass instanceof org.caltoopia.ir.Actor)?
	new CaltoopiaActorImplemenation(caltoopiaInstance,(org.caltoopia.ir.Actor) caltoopiaClass)
	: new CaltoopiaActorInstance(caltoopiaInstance,caltoopiaClass);
	}
	
	public void analyze(){
		switch(type){
		//Static analysis (SDF/CSDF)
		case 1:
		case 4:
			GenericNetworkAnalysis analysis = 
				new GenericNetworkAnalysis(network, mNetworkAnalyzer.analyze(network));
			mNetworkOutput.setOutputFolder(session.getOutputFolder());
			if(analysis.isSingleRateStaticDaflowGraph() 
					|| analysis.isMultiRateStaticDaflowGraph()){
				mNetworkOutput.printSDFXML(network, analysis);
				if(type == 4)
					 sdf3Analysis(AnalysisType.SDF);
			}
			else if(analysis.isCycloStaticDaflowGraph()){
				mNetworkOutput.printCSDFXML(network, analysis);	
				if(type == 4)
					 sdf3Analysis(AnalysisType.CSDF);
			}			
			break;
		//Dynamic analysis (MCDF)
		case 2:
		case 5:
			McdfNetworkAnalysis mcdfAnalysis=
				new McdfNetworkAnalysis(network, mNetworkAnalyzer.analyze(network));
			mNetworkOutput.setOutputFolder(session.getOutputFolder());
			if(mcdfAnalysis.isModeControlledDataflow()){
				mNetworkOutput.printMcdfXML(network,mcdfAnalysis);
			}
			if(type == 5)
				 sdf3Analysis(AnalysisType.MCDF);
			break;
		//Dynamic analysis (FSMSADF)
		case 3:
		case 6:
			ScenarioAwareNetworkAnalysis saAnalysis=
				new ScenarioAwareNetworkAnalysis(network, mNetworkAnalyzer.analyze(network), session.getWorkingDirectory());
			mNetworkOutput.setOutputFolder(session.getOutputFolder());
			if(saAnalysis.isScenarioAwareDataflowGraph()){
				mNetworkOutput.printSaXML(network,saAnalysis);
			}
			if(type == 6)
				 sdf3Analysis(AnalysisType.FSMSADF);
			break;
		default:
			break;
		}
	}
	
	public void sdf3Analysis(AnalysisType type){
		try {					
		    List<String> command=new ArrayList<String>();
		    String graphName=session.getOutputFolder()+File.separator+session.getElaboratedNetwork().getType().getName()+"_"
		    				+type.getName().toLowerCase()+".xml";
		    command.add("/bin/bash");
		    command.add(sdf3Path+"/sdf3analysis.sh");
		    command.add(graphName);
		    command.add(type.getName());
		    ProcessBuilder builder=new ProcessBuilder(command);		
		    builder.redirectErrorStream(true);
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
