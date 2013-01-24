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

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import org.caltoopia.analysis.air.ActorInstance;
import org.caltoopia.analysis.air.Connection;
import org.caltoopia.analysis.air.Network;
import org.caltoopia.analysis.iradapter.CaltoopiaActorInstance;
import org.caltoopia.analysis.iradapter.CaltoopiaActorImplemenation;
import org.caltoopia.analysis.iradapter.CaltoopiaNetwork;
import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.cli.CompilationError;
import org.caltoopia.cli.CompilationSession;
import org.caltoopia.frontend.CalStandaloneSetup;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * Allows the analysis package to be tested from the command-line (without Eclipse):
 * (1) Creates a stand-alone instance of the Caltoopia front end, 
 * (2) Read command-line arguments 
 * (3) Processes a collection of source files
 * (4) Elaborates and instantiates a "top-level" network
 * (5) Converts the elaborated network to an AIR interface (CaltoopiaNetwork).
 * (6) Printing the actors and the connections to System.out -just to show some output.
 * 
 * The idea is to use this class (possibly skipping the last step) to drive tests of 
 */
public class TestFrontEnd {

	@Inject
	private XtextResourceSet mResourceSet;
	private List<String> mPaths=new ArrayList<String>();
	private List<String> mExcludedFiles=new ArrayList<String>();
	private String mOutputFolder="."+File.separator;
	private PrintStream mOutputStream=System.out;
	private String mQualifiedTopNetwork;
	private CompilationSession mSession;
	
	public static TestFrontEnd createStandAlone() {
		Injector injector = new CalStandaloneSetup().createInjectorAndDoEMFRegistration();
		return injector.getInstance(TestFrontEnd.class);
	}
	
	public void addAllPaths(String pathsWithSeparators) {
		String separator = Matcher.quoteReplacement(File.pathSeparator);
        for (String p : java.util.regex.Pattern.compile(separator).split(pathsWithSeparators)) {
        	mPaths.add(p);
        }		
	}
	
	public void addPath(String path) {
		mPaths.add(path);
	}
	
	public List<String> getPaths() {
		return mPaths;
	}
	
	public void excludeFile(String file) {
		mExcludedFiles.add(file);
	}
	
	public List<String> getExcludedFiled() {
		return mExcludedFiles;
	}
	
	public void setOutputFolder(String outputFolder) {
		mOutputFolder=outputFolder;
	}
	
	public String getOutputFolder() {
		return mOutputFolder;
	}
	
	public void setOutputStream(PrintStream outputStream) {
		mOutputStream=outputStream;
	}
	
	public PrintStream getOutputStream() {
		return mOutputStream;
	}
	
	public CompilationSession getCompilationSession() {
		return mSession;
	}
	
	public void setTopNetwork(String qualifiedTopNetwork) {
		mQualifiedTopNetwork=qualifiedTopNetwork;
	}

	public String getTopNetwork() {
		return mQualifiedTopNetwork;
	}
	
	public boolean parseCommandLine(String args[]) {
		String top=(args.length>0)? args[0] : null;
		String pathWithSeparators=(args.length>1)? args[1] : null;
		String outputFolder=(args.length>2)? args[2] : null;
		
		if (top!=null && pathWithSeparators!=null && outputFolder!=null) {
			setTopNetwork(top);
			addAllPaths(pathWithSeparators);
			setOutputFolder(outputFolder);
			return true;
		}
		else {
			showSynopsis();
			return false;
		}
	}
	
	
	/**
	 * Describes how to use the tool
	 */
	protected void showSynopsis() {
		System.err.println("Requires command-line arguments: <qualified-top-network> <paths> <output-folder>");
	}
	
	public CaltoopiaNetwork elaborate() {
		return elaborate(mQualifiedTopNetwork);
	}
	
	public CaltoopiaNetwork elaborate(String qualifiedTopNetwork) {
		if (mSession==null && !mPaths.isEmpty()) {
			mSession=new CompilationSession(mPaths,mExcludedFiles,mOutputFolder,mOutputStream);
			ActorDirectory.initCompilation(mSession);
		}
		
		if (mSession==null && qualifiedTopNetwork==null) {
			return null;
		}
		else {
			// Elaborate the network
			org.caltoopia.ir.Network network=null;

			try {
				if (mSession.validateAndCreateIR(mResourceSet)) {
					mSession.elaborateNetwork(qualifiedTopNetwork);
					network=mSession.getElaboratedNetwork();
				} else {
					System.err.println("Validation failed.");
				}
			} catch (CompilationError error) {
				System.err.println(error);
			}

			// Keep track of instance-to-actor-class mapping
			Map<org.caltoopia.ir.ActorInstance,org.caltoopia.analysis.air.ActorInstance> instanceMap=new LinkedHashMap<org.caltoopia.ir.ActorInstance,org.caltoopia.analysis.air.ActorInstance>();
			for (org.caltoopia.ir.ActorInstance caltoopiaInstance: network.getActors()) {
				org.caltoopia.ir.AbstractActor caltoopiaClass=mSession.applyActorParameters(caltoopiaInstance);
				instanceMap.put(caltoopiaInstance, createActor(caltoopiaInstance,caltoopiaClass));
			}

			return new CaltoopiaNetwork(network,instanceMap);
		}
	}
	
	private org.caltoopia.analysis.air.ActorInstance createActor(org.caltoopia.ir.ActorInstance caltoopiaInstance,
	                                                             org.caltoopia.ir.AbstractActor caltoopiaClass) {		
		return (caltoopiaClass instanceof org.caltoopia.ir.Actor)?
			new CaltoopiaActorImplemenation(caltoopiaInstance,(org.caltoopia.ir.Actor) caltoopiaClass)
			: new CaltoopiaActorInstance(caltoopiaInstance,caltoopiaClass);
	}
	
	public void printNetwork(Network network) {
		System.out.println("network "+network.getName());
		System.out.println("actors:");
		for (ActorInstance actor: network.getActors()) {
			System.out.println("  "+actor.getInstanceName());
		}
		System.out.println("connections:");
		for (Connection c: network.getConnections()) {
			System.out.println("  "+c);
		}
	}	
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestFrontEnd fe=createStandAlone();
		
		if (fe.parseCommandLine(args)) {
			ActorDirectory.initCompilation(fe.getCompilationSession());
			CaltoopiaNetwork network=fe.elaborate();
			if (network!=null) {
				fe.printNetwork(network);
			}
		}
	}
}
