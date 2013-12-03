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
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;

import org.caltoopia.ast2ir.IrDottyPrinter;
import org.caltoopia.codegen.CEnvironment;
import org.caltoopia.codegen.printer.CPrinterTop;
import org.caltoopia.codegen.transformer.IrTransformer;
import org.caltoopia.frontend.CalStandaloneSetup;
import org.caltoopia.types.TypeMatchDeclaration;
import org.eclipse.xtext.resource.XtextResourceSet;
import com.google.inject.Inject;
import com.google.inject.Injector;
import jargs.gnu.*;
import org.eclipse.cdt.core.*;
import org.eclipse.cdt.core.model.CoreModel;
import org.eclipse.cdt.core.settings.model.ICConfigurationDescription;
import org.eclipse.cdt.core.settings.model.ICProjectDescription;
import org.eclipse.cdt.core.settings.model.ICProjectDescriptionManager;
import org.eclipse.cdt.core.settings.model.extension.CConfigurationData;
import org.eclipse.cdt.managedbuilder.core.IBuilder;
import org.eclipse.cdt.managedbuilder.core.ManagedBuildManager;
import org.eclipse.cdt.managedbuilder.internal.core.Configuration;
import org.eclipse.cdt.managedbuilder.internal.core.ManagedProject;
import org.eclipse.cdt.utils.envvar.StorableEnvironment;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;

public class Cal2C {
	
	private static final int ArrayList = 0;

	@Inject
	private XtextResourceSet resourceSet;
		
	boolean debug = false;
	
	List<String> topNetworkNamespace = null;
	
	String topNetworkName = null;		
	
	/**
	 * @param args
	 */	
	
	public static void main(String[] args) {
		try {
			compile(args);
		} catch (Exception e) {
			System.err.println("Compilation failed: " + e.getMessage());
			System.exit(2);			
		}		
	}
	
	public static CompilationSession compile(String[] args) {
		CmdLineParser cmdLineParser = new CmdLineParser();

		CmdLineParser.Option top = cmdLineParser.addStringOption('t', "top");
		CmdLineParser.Option path = cmdLineParser.addStringOption('p', "path");
		CmdLineParser.Option output = cmdLineParser.addStringOption('o', "output");
		CmdLineParser.Option exclude = cmdLineParser.addStringOption('x', "exclude");
		CmdLineParser.Option runtime = cmdLineParser.addStringOption('r', "runtime");
		CmdLineParser.Option target = cmdLineParser.addStringOption('a', "arch");
		CmdLineParser.Option debug = cmdLineParser.addIntegerOption('d', "debug");
		CmdLineParser.Option calsim = cmdLineParser.addStringOption("calsim");	
		CmdLineParser.Option systemc = cmdLineParser.addStringOption("systemc");	
		CmdLineParser.Option clean = cmdLineParser.addBooleanOption("clean");
		CmdLineParser.Option rangechk = cmdLineParser.addBooleanOption("rangechk");
		
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
    		File outFile = new File(outputValue);
    		if(!outFile.exists())
    			outFile.mkdir();

            String excludeValue = (String) cmdLineParser.getOptionValue(exclude);
            List<String> excludedFiles = new ArrayList<String>();
            if (excludeValue != null) {
            	for (String f :  java.util.regex.Pattern.compile(separator).split(excludeValue)) {
            		excludedFiles.add(f);
            	}
            }
            int debugInt = CompilationSession.DEBUG_TYPE_DEFAULT;
            Integer debugInteger = (Integer) cmdLineParser.getOptionValue(debug);
            if(debugInteger!=null) {
            	debugInt  = debugInteger;
            }
            String targetStr = (String) cmdLineParser.getOptionValue(target);
            if(targetStr == null) {
            	targetStr = System.getProperty("os.name").toLowerCase();
            }
            Injector injector = new CalStandaloneSetup().createInjectorAndDoEMFRegistration();
    		Cal2C cal2c = injector.getInstance(Cal2C.class);
    		CompilationSession session = new CompilationSession(paths, excludedFiles, outputValue, System.out, 
    				                                            (String) cmdLineParser.getOptionValue(runtime), false, targetStr, false, "", "", true, debugInt);
    		ActorDirectory.initCompilation(session);
    		
            String calsimPath = (String) cmdLineParser.getOptionValue(calsim);
            String systemcPath = (String) cmdLineParser.getOptionValue(systemc);
            SimulationSession simulationSession = null;
            
            if (calsimPath != null || systemcPath != null) {
            	simulationSession = new SimulationSession(calsimPath, systemcPath);
            }
            
    		Object doClean = cmdLineParser.getOptionValue(clean);
    		if ((doClean != null) && ((Boolean) doClean)) {
    			ActorDirectory.clean();
    		}

    		Object doRangeChk = cmdLineParser.getOptionValue(rangechk);
    		if ((doRangeChk != null) && ((Boolean) doRangeChk)) {
    			session.setRangeChk(true);
    		}
    		    		
			try {
				if (session.validateAndCreateIR(cal2c.resourceSet)) {
					session.elaborateNetwork(topValue);
					cal2c.generate(topValue, session, simulationSession);
					return session;
				} else {
					throw new CompilationError("Validation failed");
				}
			} catch (Exception x) {
				Cal2C.generateErrorMakefile(session);
				throw new CompilationError("[Cal2C] Internal compilation error: " + x.getMessage());
			}
        } catch (CmdLineParser.OptionException e) {
            usage();
            throw new CompilationError("[Cal2C] Invalid Commandline:" + e.getMessage());
        } catch (Exception x) {
			throw new CompilationError("[Cal2C] Internal error: " + x.getMessage());
        }		
	}
			
	public static void usage() {
		System.err.println("usage: cmdline {--top, -t}  <namespace.network> {-p, -paths} <path>(:<path>)* {-o, -output} folder");		
		System.err.println("       [{-x, --exclude} <file.cal>(:<file.cal>)*] [{-r, --runtime} path-to-runtime-root]");		
		System.err.println("       [{-c, --calsim} path-to-calsim-root] [{-a, --arch} <linux,mac,win>]");	
		System.err.println("       {--clean true|false}");
	}
	
	public static IProject createCProject(String projectName, URI locationURI, CompilationSession session) {
		try {
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IWorkspaceRoot root = workspace.getRoot();
			IProject project = root.getProject(projectName);
			//Just return current project if it exist
			if(project.exists())
				return project;
	
			/*
			IToolChain[] tcs = ManagedBuildManager.getRealToolChains();
			@SuppressWarnings("restriction")
			ToolChain tc=null;
			for (int i=0; i<tcs.length; i++) {
				System.err.println(tcs[i].getName() + " - " + tcs[i].getId());
			}
			//Configuration cfg = new Configuration(mProj, tc, 
			//tc.getId(), tc.getName());
			*/
			
			IProjectDescription description = workspace.newProjectDescription(project.getName());
			description.setLocationURI(locationURI);
	
			IProject projectC = CCorePlugin.getDefault().createCDTProject(description, project, session.getMonitor());
			
			// Set up build information
			ICProjectDescriptionManager pdMgr = CoreModel.getDefault().getProjectDescriptionManager();
			ICProjectDescription projDesc = pdMgr.createProjectDescription(projectC, false);
			
			@SuppressWarnings("restriction")
			ManagedProject mProj = new ManagedProject(projDesc);
			@SuppressWarnings("restriction")
			Configuration config = new Configuration(mProj, null, "cdt.managedbuild.toolchain.gnu.base", "Linux GCC");
			@SuppressWarnings("restriction")
			IBuilder builder = config.getEditableBuilder();
			builder.setManagedBuildOn(false);
			builder.setCleanBuildEnable(false);
			builder.setFullBuildEnable(false);
			builder.setAutoBuildEnable(false);
			builder.setIncrementalBuildEnable(false);
			@SuppressWarnings("restriction")
			CConfigurationData data = config.getConfigurationData();
			@SuppressWarnings("unused")
			ICConfigurationDescription cfgDes = projDesc.createConfiguration(ManagedBuildManager.CFG_DATA_PROVIDER_ID, data);
			pdMgr.setProjectDescription(project, projDesc);
			if(projectC != null) {
				projectC.open(null);
			}

			return project;
		} catch (Throwable e) {
			throw new CompilationError("[Cal2C] Internal error: Failed to generate c-eclipse project, have you installed C-support in Eclipse?");
		}
	}

	public void generate(String topNetwork, CompilationSession session, SimulationSession simulationSession) {
		PrintStream out = session.getOutputStream();
		out.println("------------------------------------------------------");
		out.println("Compiling network: '" + topNetwork + "'");
		session.print();
		out.println("------------------------------------------------------");		
		try {
			// Start the printing 
			
			CEnvironment env = new CEnvironment();
		
			//Print the elaborated network
			out.println("#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*##*#*#*#");
			out.println("#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*##*#*#*#");
			out.println("#*#*#    Using refactored code generation    #*#*#");
			out.println("#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*##*#*#*#");
			out.println("#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*#*##*#*#*#");

			out.println("#*#*#*#*#*#*#*#*#*#*# Init  #*#*#*#*#*#*#*##*#*#*#");
			IrTransformer.IrTransformNetworkInit(session.getElaboratedNetwork());
            out.println("#*#*#*#*#*#*#*#*#*# Transform #*#*#*#*#*#*##*#*#*#");
            new IrTransformer(session.getElaboratedNetwork().getType(), session, 
                    Arrays.asList(  IrTransformer.IrPassTypes.UsedDeclaration,
                                    IrTransformer.IrPassTypes.Variable,
                                    IrTransformer.IrPassTypes.PortTransformations,
                                    IrTransformer.IrPassTypes.TypeUsage,
                                    IrTransformer.IrPassTypes.Port,
                                    IrTransformer.IrPassTypes.TypeStructure,
                                    IrTransformer.IrPassTypes.VariablePlacement,
                                    IrTransformer.IrPassTypes.MoveInitValueExpr,
                                    IrTransformer.IrPassTypes.CreateForLoop,
                                    IrTransformer.IrPassTypes.Variable, //Repeat
                                    IrTransformer.IrPassTypes.ExprToTempVar,
                                    //Repeat some analysis passes due to that we have moved things around
                                    IrTransformer.IrPassTypes.Variable,
                                    IrTransformer.IrPassTypes.VariablePlacement)
                                    );
			//For now run this to make sure the top network type declarations don't have type decl imports
            out.println("#*#*#*#*#*#*#*#*#*# Type decl #*#*#*#*#*#*##*#*#*#");
			new TypeMatchDeclaration().doSwitch(session.getElaboratedNetwork());
            out.println("#*#*#*#*#*#*#*#*#*#   Print   #*#*#*#*#*#*##*#*#*#");
			new CPrinterTop(session,env);

			
			if(session.generateDot()) {
				out.println("Generate top network dot-file.");
				new IrDottyPrinter(session.getOutputFolder()).caseNetwork(session.getElaboratedNetwork());
			}
						
			out.print("Finished compiling at ");
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			out.println(dateFormat.format(date));
		} catch (Exception x) {
			out.println("Compilation failed, no output written.");
			out.println(x.getMessage() + " :: " + x.toString());
			x.printStackTrace(out);
			generateErrorMakefile(session);
		}
	}
	
	static public void generateErrorMakefile(CompilationSession session) {
		//Print make config file including annotation information
		String fileStr = session.getOutputFolder() + File.separator + "config.mk";			
		session.getOutputStream().println("Overwriting '" + fileStr + "' so c-code compilation fails.");
		PrintStream config;
		try {
			config = new PrintStream(fileStr);
			config.println("$(error Failed to generate c-code, please check log)");
			config.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void turnOffAutomaticBuild(IProject project) {
		try {
			// Set up build information
			ICProjectDescriptionManager pdMgr = CoreModel.getDefault().getProjectDescriptionManager();
			ICProjectDescription projDesc = pdMgr.createProjectDescription(project, false);
			
			@SuppressWarnings("restriction")
			ManagedProject mProj = new ManagedProject(projDesc);
			@SuppressWarnings("restriction")
			Configuration config = new Configuration(mProj, null, "cdt.managedbuild.toolchain.gnu.base", "Linux GCC");
			@SuppressWarnings("restriction")
			IBuilder builder = config.getEditableBuilder();
			builder.setManagedBuildOn(false);
			builder.setCleanBuildEnable(false);
			builder.setFullBuildEnable(false);
			builder.setAutoBuildEnable(false);
			builder.setIncrementalBuildEnable(false);
			@SuppressWarnings("restriction")
			CConfigurationData data = config.getConfigurationData();
			@SuppressWarnings("unused")
			ICConfigurationDescription cfgDes = projDesc.createConfiguration(ManagedBuildManager.CFG_DATA_PROVIDER_ID, data);
			pdMgr.setProjectDescription(project, projDesc);
		} catch (Exception e) {
			
		}
	}
	
	private void turnOnAutomaticBuild(IProject project) {
		try {
			// Set up build information
			ICProjectDescriptionManager pdMgr = CoreModel.getDefault().getProjectDescriptionManager();
			ICProjectDescription projDesc = pdMgr.createProjectDescription(project, false);
			
			@SuppressWarnings("restriction")
			ManagedProject mProj = new ManagedProject(projDesc);
			@SuppressWarnings("restriction")
			Configuration config = new Configuration(mProj, null, "cdt.managedbuild.toolchain.gnu.base", "Linux GCC");
			@SuppressWarnings("restriction")
			IBuilder builder = config.getEditableBuilder();
			builder.setManagedBuildOn(true);
			builder.setCleanBuildEnable(true);
			builder.setFullBuildEnable(true);
			builder.setAutoBuildEnable(true);
			builder.setIncrementalBuildEnable(true);
			@SuppressWarnings("restriction")
			CConfigurationData data = config.getConfigurationData();
			@SuppressWarnings("unused")
			ICConfigurationDescription cfgDes = projDesc.createConfiguration(ManagedBuildManager.CFG_DATA_PROVIDER_ID, data);
			pdMgr.setProjectDescription(project, projDesc);

		} catch (Exception e) {
			
		}
	}
	
}
	


