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

package org.caltoopia.cli;

import java.io.File;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import org.caltoopia.codegen.IrXmlPrinter;
import org.caltoopia.frontend.CalStandaloneSetup;
import org.caltoopia.ir.AbstractActor;
import org.caltoopia.ir.Actor;
import org.caltoopia.ir.ActorInstance;
import org.caltoopia.ir.Network;
import org.caltoopia.ir.TypeActor;
import org.eclipse.xtext.resource.XtextResourceSet;
import com.google.inject.Inject;
import com.google.inject.Injector;
import jargs.gnu.*;

public class Cal2Xml {
	
	@Inject
	private XtextResourceSet resourceSet;
	
	boolean debug = false;
	
	List<String> topNetworkNamespace = null;
	
	String topNetworkName = null;
	
	/**
	 * @param args
	 */	
	
	public static void main(String[] args) {
		CmdLineParser cmdLineParser = new CmdLineParser();

		CmdLineParser.Option top = cmdLineParser.addStringOption('t', "top");
		CmdLineParser.Option path = cmdLineParser.addStringOption('p', "path");
		CmdLineParser.Option output = cmdLineParser.addStringOption('o', "output");
		CmdLineParser.Option exclude = cmdLineParser.addStringOption('x', "exclude");
		
		try {
			cmdLineParser.parse(args);
            
            String topValue = (String) cmdLineParser.getOptionValue(top);
            
            String pathValue = (String) cmdLineParser.getOptionValue(path);
            String separator = Matcher.quoteReplacement(File.pathSeparator);
            List<String> paths = new ArrayList<String>();
            for (String p : java.util.regex.Pattern.compile(separator).split(pathValue)) {
            	paths.add(p);
            }
            
            String outputValue = (String) cmdLineParser.getOptionValue(output);

            String excludeValue = (String) cmdLineParser.getOptionValue(exclude);
            List<String> excludedFiles = new ArrayList<String>();
            if (excludeValue != null) {
            	for (String f :  java.util.regex.Pattern.compile(separator).split(excludeValue)) {
            		excludedFiles.add(f);
            	}
            }
            
            Injector injector = new CalStandaloneSetup().createInjectorAndDoEMFRegistration();
    		Cal2Xml cal2xml = injector.getInstance(Cal2Xml.class);
    		CompilationSession session = new CompilationSession(paths, excludedFiles, outputValue, System.out);    		    		
			try {
				if (session.validateAndCreateIR(cal2xml.resourceSet)) {
					session.elaborateNetwork(topValue);
					cal2xml.generate(topValue, session);
				} else {
					System.err.println("Validation failed.");
				}
			} catch (Exception x) {
				System.err.println("Not good. Not at all good. x = " + x);
			}
            
        } catch ( CmdLineParser.OptionException e ) {
            System.err.println(e.getMessage());
            usage();
            System.exit(2);
        }		
	}
	
		
	public static void usage() {
		System.err.println("usage: cmdline [{--top, -t}  <network.cal>] [{-t, -paths} <path>(:<path>)*] [{-o, -output} folder]");		
	}
			
	public void generate(String topNetwork, CompilationSession session) {	
		PrintStream out = session.getOutputStream();
		out.println("------------------------------------------------------");
		out.println("Compiling network: '" + topNetwork + "'");
		session.print();
		out.println("------------------------------------------------------");		
		
		try {
			out.print("Elaborating network...");
			session.elaborateNetwork(topNetwork);
			out.println("done");
			
			Network elaboratedNetwork = session.getElaboratedNetwork();

			out.println("Writing elaborate network");
			new IrXmlPrinter(session.getOutputFolder()).doSwitch(elaboratedNetwork);

			//	Print a c-file for each actor instance
			for (ActorInstance instance : elaboratedNetwork.getActors()) {
				try {
					AbstractActor actor = ActorDirectory.findActor((TypeActor) instance.getType());

					if (actor instanceof Actor) {
						out.println("Writing '" + instance.getName() + "'");
						new IrXmlPrinter(session.getOutputFolder()).doSwitch(instance);
					} 
				} catch (DirectoryException x) {
					out.println("error: actor '" + ((TypeActor) instance.getType()).getName() + "' not found");
					return;
				}
			}

			out.print("Finished compiling at ");
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			out.println(dateFormat.format(date));
		} catch (Exception x) {
			out.println("Compilation failed, no output written.");
		}
	}
}
	


