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

import org.caltoopia.analysis.actor.ActorAnalyzer;
import org.caltoopia.analysis.actor.ActorClassifierOutput;
import org.caltoopia.analysis.actor.McdfActorAnalysis;
import org.caltoopia.analysis.actor.GenericActorAnalysis;
import org.caltoopia.analysis.actor.SneakyActorAnalyzer;
import org.caltoopia.analysis.air.ActorInstance;
import org.caltoopia.analysis.air.Network;
import org.caltoopia.analysis.network.NetworkClassifierOutput;
import org.caltoopia.analysis.network.SneakyNetworkAnalyzer;
import org.caltoopia.analysis.network.GenericNetworkAnalysis;
import org.caltoopia.analysis.network.McdfNetworkAnalysis;
import org.caltoopia.analysis.network.ScenarioAwareNetworkAnalysis;
import org.caltoopia.cli.ActorDirectory;

public class TestSneakyClassifier {

	private TestFrontEnd mFrontEnd;
	private ActorAnalyzer mAnalyzer;
	private SneakyNetworkAnalyzer mNetworkAnalyzer;
	private ActorClassifierOutput mOutput;
	private NetworkClassifierOutput mNetworkOutput;
	
	public TestSneakyClassifier(ActorAnalyzer analyzer, SneakyNetworkAnalyzer nAnalyzer,
								ActorClassifierOutput output, NetworkClassifierOutput nOutput) {
		mFrontEnd=TestFrontEnd.createStandAlone();
		mAnalyzer=analyzer;
		mOutput=output;
		mNetworkAnalyzer=nAnalyzer;
		mNetworkOutput=nOutput;
	}
	
	public void testActor(String args[]) {		
		if (mFrontEnd.parseCommandLine(args)) {
			Network network=mFrontEnd.elaborate();			
			ActorDirectory.initCompilation(mFrontEnd.getCompilationSession());
			for (ActorInstance actor: network.getActors()) {
				GenericActorAnalysis genericAnalysis=null;	
				if (actor.hasImplementation()) {
					genericAnalysis=new GenericActorAnalysis(actor,mAnalyzer.analyze(actor.getImplementation()));
					mOutput.print(genericAnalysis);
				}
			}
		}
	}
	
	public void testNetwork(String args[]) {			
		if (mFrontEnd.parseCommandLine(args)) {
			ActorDirectory.initCompilation(mFrontEnd.getCompilationSession());
			Network network=mFrontEnd.elaborate();	
			GenericNetworkAnalysis analysis = 
					new GenericNetworkAnalysis(network, mNetworkAnalyzer.analyze(network));
			mNetworkOutput.setOutputFolder(mFrontEnd.getOutputFolder());
			mNetworkOutput.print(analysis);
			if(analysis.isSingleRateStaticDaflowGraph() 
					|| analysis.isMultiRateStaticDaflowGraph())
				mNetworkOutput.printSDFXML(network, analysis);
			else if(analysis.isCycloStaticDaflowGraph()){
				mNetworkOutput.printCSDFXML(network, analysis);
			}
			}
	}	
	
	public void testMcdfNetwork(String args[]) {			
		if (mFrontEnd.parseCommandLine(args)) {
			ActorDirectory.initCompilation(mFrontEnd.getCompilationSession());
			Network network=mFrontEnd.elaborate();	
				McdfNetworkAnalysis mcdfAnalysis=
						new McdfNetworkAnalysis(network, mNetworkAnalyzer.analyze(network));
				mNetworkOutput.setOutputFolder(mFrontEnd.getOutputFolder());
				//Print actor instance types
				for (ActorInstance actor: network.getActors()) {
						mOutput.print(mcdfAnalysis.getMcdfActorAnalysis(actor));
				}
				mNetworkOutput.print(mcdfAnalysis);
				if(mcdfAnalysis.isModeControlledDataflow()){
					mNetworkOutput.printMcdfXML(network,mcdfAnalysis);
				}
		}
	}
	
	public void testSANetwork(String args[]) {			
		if (mFrontEnd.parseCommandLine(args)) {
			ActorDirectory.initCompilation(mFrontEnd.getCompilationSession());
			Network network=mFrontEnd.elaborate();	
				ScenarioAwareNetworkAnalysis saAnalysis=
						new ScenarioAwareNetworkAnalysis(network, mNetworkAnalyzer.analyze(network), args[4]);
				mNetworkOutput.setOutputFolder(mFrontEnd.getOutputFolder());
				//Print actor instance types
				for (ActorInstance actor: network.getActors()) {
					if(actor.hasImplementation())
						mOutput.print(saAnalysis.getScenarioAwareActorAnalysis(actor));
				}
				mNetworkOutput.print(saAnalysis);
				if(saAnalysis.isScenarioAwareDataflowGraph()){
					mNetworkOutput.printSaXML(network,saAnalysis);
				}
		}
	}	
	
	public static void main(String[] args) {
		ActorAnalyzer actorAnalyzer=new SneakyActorAnalyzer();
		TestSneakyClassifier t=new TestSneakyClassifier(actorAnalyzer,
														new SneakyNetworkAnalyzer(),
				                                        new ActorClassifierOutput(System.out),
				                                        new NetworkClassifierOutput());
		//TODO: This is just temporary.
		String testType=(args.length>3)? args[3] : null;
		if (testType!=null) {
			if(testType.contains("actor")){
				t.testActor(args);
			}
			else if(testType.contentEquals("network")){
				t.testNetwork(args);
			}
			else if(testType.contentEquals("mcdf")){
				t.testMcdfNetwork(args);
			}
			else if(testType.contentEquals("sa")){
				t.testSANetwork(args);
			}
		}
	}
}
