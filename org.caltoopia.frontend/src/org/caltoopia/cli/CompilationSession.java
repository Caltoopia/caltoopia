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
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Collection;
import java.util.Set;
import org.caltoopia.ast2ir.Ast2Ir;
import org.caltoopia.ast2ir.ConstantExpressionEvaluator;
import org.caltoopia.ast2ir.Elaborator;
import org.caltoopia.ast2ir.ExpandIrSymbols;
import org.caltoopia.ast2ir.FindImportedIrSymbols;
import org.caltoopia.ast2ir.Instantiator;
import org.caltoopia.frontend.cal.AstNamespace;
import org.caltoopia.ir.AbstractActor;
import org.caltoopia.ir.ActorInstance;
import org.caltoopia.ir.IrFactory;
import org.caltoopia.ir.Namespace;
import org.caltoopia.ir.Network;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.types.TypeAnnotater;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.resource.containers.IAllContainersState;
import org.eclipse.xtext.resource.containers.DelegatingIAllContainerAdapter;
import org.eclipse.xtext.mwe.ContainersStateFactory;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Sets;

/**
 * Experiment with a split front end, which does not drive output generation (C/IR printing),
 * but instead provides an elaborated network
 */
public class CompilationSession {
	
	public class Result {
		Set<Namespace> usedNamespaces = null;		
		Network elaboratedNetwork = null;		
	}
	
	/* The result from executing the compilation session */
	private Result result;
	
	/* The paths that are scanned for source files */
	private List<String> paths;
	
	/* Files that may exist on the source paths, but that shall
	 * be execluded from being compiled. 
	 */
	private List<String> excludedFiles;
	
	/* Location of the generated C code */
	private String outputFolder;
	
	/* Location of the C-runtime library */
	private String runtimePath;
	
	/* Flag to increase print outs during compilation */
	private boolean verbose = false;
	
	/* True if the compilation is invoked from the Eclipse plugin */
	private boolean plugin = false;
	
	private String launchConfigName;
	
	private boolean generateCdtProject = false;
	
	private boolean generateDot = false;
	
	private boolean rangechk = false;

	/* Debug print 0: None, 1: User's dprint, 2: Action firings and User's dprint */
	private int debugPrint;
	public static final int DEBUG_TYPE_NONE = 0;
	public static final int DEBUG_TYPE_USER = 1;
	public static final int DEBUG_TYPE_ACTIONUSER = 2;
	public static final int DEBUG_TYPE_DEFAULT = 0;

	private IProject cproject = null;
	
	private IProgressMonitor monitor = null;
	
	private PrintStream out = System.out;

	private String target = null;

	/* The folder to execute the resulting binary */
	private String workingDirectory = null;
	
	/* Options to use when running the resulting binary */ 
	private String runOptions = null;
	
	public CompilationSession(List<String> paths, List<String> excludedFiles, String outputFolder, PrintStream ps, 
			                  String runtimePath, boolean isPlugin, String target, boolean generateCdtProject,
			                  String workingDirectory, String runOptions, boolean generateDot, int debug) {		
		this.paths = paths;
		this.excludedFiles = excludedFiles;
		this.outputFolder = outputFolder;
		this.out = ps;
		this.plugin = isPlugin;
		this.runtimePath = runtimePath;
		this.target = target;
		this.generateCdtProject = generateCdtProject;
		this.workingDirectory = workingDirectory;
		this.runOptions = runOptions;
		this.generateDot = generateDot;
		this.debugPrint = debug;
	}
	
	public CompilationSession(List<String> paths, List<String> excludedFiles, String outputFolder, PrintStream ps) {
		this(paths, excludedFiles, outputFolder, ps, "", false, System.getProperty("os.name").toLowerCase(), false, "", "");
	}

	public CompilationSession(List<String> paths, List<String> excludedFiles, String outputFolder, PrintStream ps, 
            String runtimePath, boolean isPlugin, String target, boolean generateCdtProject,
            String workingDirectory, String runOptions) {
		this(paths, excludedFiles, outputFolder, ps, 
             runtimePath, isPlugin, target, generateCdtProject,
             workingDirectory, runOptions, false, 2 /*DEBUG_TYPE_DEFAULT*/);
	}

	/**
	 * @param qualifiedTopNetwork  Qualified name of top-level network
	 * @param paths                Paths to the CAL files, separated by platform-specific delimiter (: or ;)
	 * @return elaborated network (null if it could not be successfully compiled)
	 * 
	 * The returned network is a "flat" network of actor instances (no sub-networks, 
	 * parameter values are propagated into actors). 
	 */
	public void elaborateNetwork(String qualifiedTopNetworkName) throws CompilationError {	
		out.print("Elaborating network...");

		result = new Result();
		try {							
			Network topNetwork = (Network) ActorDirectory.findActor(createActorType(qualifiedTopNetworkName));
            
			// Elaborate the network
			result.elaboratedNetwork = Elaborator.elaborate(topNetwork);
			
			// Resolve imports, evaluate constants and type expressions
			result.usedNamespaces = findUsedNameSpaces(result.elaboratedNetwork);
			resolveElaboratedNetwork(result.elaboratedNetwork, result.usedNamespaces);	
			out.println("done");
		} catch (Exception x) {
			throw new RuntimeException("Elaboration failed - " + x.getMessage());
		}
	}
	
	public Network getElaboratedNetwork() {		
		return result.elaboratedNetwork;
	}
	
	public Set<Namespace> getUsedNamespaces() {		
		return result.usedNamespaces;
	}
	
	public AbstractActor applyActorParameters(ActorInstance actorInstance) {
		return Instantiator.instantiate(actorInstance, null);
	}
	
	/**
	 * Adds .cal source files to the resource set
	 * 
	 * @param paths  paths to scan for .cal files, separated by platform-specific delimiter (: or ;)
	 */
	private void scanPathsCreateResources(List<String> paths, List<String> excludedFiles, XtextResourceSet resourceSet) {
		List<String> files = scanPathsForCalFiles(paths, excludedFiles); 
		
		Multimap<String, URI> uris = HashMultimap.create(); 

		for (int i = 0; i<files.size(); i++) {			
			uris.put(files.get(i), URI.createFileURI(files.get(i)));	
		}
						
		ContainersStateFactory containersStateFactory = new ContainersStateFactory();		
		IAllContainersState containersState = containersStateFactory.getContainersState(files, uris);		
		resourceSet.eAdapters().add(new DelegatingIAllContainerAdapter(containersState));		
			
		Collection<URI> values = Sets.newHashSet(uris.values());
		for (URI uri : values) {
			resourceSet.createResource(uri);							
		}
	}
	
	/**
	 * @param paths  paths to search, separated by platform-specific delimiter (: or ;)
	 * @return .cal files in trees rooted at the given paths
	 */
	private List<String> scanPathsForCalFiles(List<String> paths, List<String> excludedFiles ) {		
		// Scan the paths for actors & networks.
		List<String> files = new ArrayList<String>(); 
				
		for (String path : paths) {
			scanPathForCalFiles(new File(path), files, excludedFiles);			
		}
		
		return files;
	}
	
	private void scanPathForCalFiles(File path, List<String> result, List<String> excludedFiles) {	
		
		if (path.isDirectory()) {
			if (verbose) out.println("scanning for Cal files: " + path + " (directory)");
			for (File file : path.listFiles()) {
				scanPathForCalFiles(file, result, excludedFiles);
			}
		} else if (path.isFile()) {
			if (path.getName().endsWith(".cal") || path.getName().endsWith(".CAL")) {
				if (!excludedFiles.contains(path.getPath())) 
					result.add(path.getPath());
				if (verbose) out.println("scanning for Cal files: " + path + " (CAL file)");
			} else {
				if (verbose) out.println("scanning for Cal files: " + path + " (not a CAL file)");
			}
		} else {
			if (verbose) out.println("scanning for Cal files: " + path + " (does not exist?)");
		}
	}	
	
	/**
	 * @return true if the network was successfully validated (false otherwise) 
	 */
	public boolean validateAndCreateIR(XtextResourceSet resourceSet){
		
		scanPathsCreateResources(paths, excludedFiles, resourceSet);				
		
		if (resourceSet.getResources().isEmpty()) {
			throw new CompilationError("No source files found");
		}
		
		Resource resource = (Resource) resourceSet.getResources().get(0);		
		IResourceValidator v = ((XtextResource) resource).getResourceServiceProvider().getResourceValidator();
		List<Resource> resources = Lists.newArrayList(resourceSet.getResources());
		boolean validated = true;
		
		//
		// Check for diagnostics (print them if any)
		// Transform ASTs into IR and insert into ActorDirectory
		//
			
		for (Resource res : resources) {
			out.println("Reading file '" + res.getURI() + "'");
			boolean hasErrors = false;	
			List<Diagnostic> errors = res.getErrors();
			if (!errors.isEmpty()) {
				for (Diagnostic error : errors) {
					out.println("error: " + error);
				}
				hasErrors = true;
			}
			
			try {
				res.load(null);
				List<Issue> result = v.validate(res, CheckMode.ALL, null);
		        for (Issue issue : result) {
		        	switch (issue.getSeverity()) {
		        		case ERROR:
		        			out.println(res.getURI().toFileString() + ":" + issue.getLineNumber() + ": error: " + issue.getMessage());
		        			hasErrors = true;
		        		break;
		        		case WARNING:
		        			out.println(res.getURI().toFileString() + ":" + issue.getLineNumber() + ": warning: " + issue.getMessage());
		        		break;
		        		case INFO:
		        			out.println(res.getURI().toFileString() + ":" + issue.getLineNumber() + ": info: " + issue.getMessage());
		        		break;
		        	}
		        }
			} catch (IOException e) {
				out.println("error: Couldn't load resource (" + res.getURI() + ")" + e);
			}	
			if (hasErrors) {
				validated = false;
			} else {
				AstNamespace namespace = (AstNamespace) res.getContents().get(0);
				if (verbose) out.println("[Ast2Ir] compiling '" + namespace.getName() + "'");
				new Ast2Ir().run(namespace, res.getURI().toFileString(), findSourceRoot(res.getURI().toFileString()));													
			}			
		}
		
		return validated;
	}
	
	/*
	 * This methods determines from which of the given paths that the source was read from. The return value
	 * will server as project name in the ActorDirectory.
	 * 
	 */
	
	private String findSourceRoot(String filepath) {
		for (String folderpath : paths) {
			if (filepath.startsWith(folderpath)) {
				return folderpath;
			}
		}
		assert(false);
		return null;
	}

	/**
	 * @param elaboratedNetwork 
	 * @return the set of Namespaces used in the elaborated network
	 */
	private Set<Namespace> findUsedNameSpaces(Network elaboratedNetwork) {
		// Collect all the imports, starting with the top network		
		Set<Namespace> usedNameSpaces = new HashSet<Namespace>();
		usedNameSpaces.addAll(new FindImportedIrSymbols(elaboratedNetwork).getImportedNamespaces());
					
		// Collect all the namespaces that are imported to each used actor type
		for (ActorInstance instance : elaboratedNetwork.getActors()) {
			try {
				AbstractActor actor = ActorDirectory.findActor((TypeActor) instance.getType());				
				usedNameSpaces.addAll(new FindImportedIrSymbols(actor).getImportedNamespaces());
			} catch (DirectoryException x) {
				throw new RuntimeException("Actor not found: " + x.getMessage());
			}
		}
				
		// Calculate the transitive hull over all used namespaces
		Set<Namespace> tmp;
		do {
			tmp = new HashSet<Namespace>();
			for (Namespace ns : usedNameSpaces) {
				tmp.add(ns);
				tmp.addAll(new FindImportedIrSymbols(ns).getImportedNamespaces());
			}
			if (usedNameSpaces.equals(tmp)) break;
			usedNameSpaces = tmp;
		} while (true);
		
		return usedNameSpaces;
	}
	

	/**
	 * @param elaboratedNetwork  an elaborated network
	 * @param usedNameSpaces     name spaces, used in network
	 * 
	 * Resolves imports, evaluates constants and and type expressions
	 */
	private void resolveElaboratedNetwork(Network elaboratedNetwork, Set<Namespace> usedNameSpaces) {
		// Add all the imports from namespaces to the elaboratedNetwork,
		// since it basically constitutes the main program

		// Create one main program with all global symbols (ordered)
		ExpandIrSymbols.addAndResolveDeclarations(elaboratedNetwork, usedNameSpaces);

		// Evaluate all global symbols
		ConstantExpressionEvaluator.evaluate(elaboratedNetwork, null);
		new TypeAnnotater().doSwitch(elaboratedNetwork);
	}
	
	private TypeActor createActorType(String qualifiedName) {
		TypeActor type = IrFactory.eINSTANCE.createTypeActor();

		if (qualifiedName.contains(".")) {
			String tmp[] = java.util.regex.Pattern.compile("\\.").split(qualifiedName.substring(0, qualifiedName.lastIndexOf(".")));
			for (String s : tmp) {
				type.getNamespace().add(s);
			}
			type.setName(qualifiedName.substring(qualifiedName.lastIndexOf(".") + 1, qualifiedName.length()));
		} else {
			type.setName(qualifiedName);
		};
		
		return type;
	}
		
	public void print() {
		out.println("output folder: '" + outputFolder + "'");
		// Scan the paths for actors & networks.
		
		out.println(" paths:");
		for (String path : paths) {
			out.println("   " + path);						
		}
		
		out.println(" excluding:");
		for (String exclude : excludedFiles) {
			out.println("   " + exclude);
		}
	}

	public List<String> getPaths() {
		return paths;		
	}
	
	public void setRuntimePath(String runtimePath) {
		this.runtimePath = runtimePath;		
	}
	
	public String getRuntimePath() {
		return runtimePath;		
	}

	public String getOutputFolder() {
		return outputFolder;
	}
	
	public PrintStream getOutputStream() {
		return out;
	}
	
	public IProgressMonitor getMonitor() {
		return monitor;
	}
	
	public void setMonitor(IProgressMonitor monitor) {
		this.monitor =monitor;
	}
	
	public boolean isPlugin() {
		return plugin;
	}
	
	public boolean isRangeChk() {
		return rangechk;
	}

	public void setRangeChk(boolean v) {
		rangechk=v;
	}

	public boolean generateCdtProject() {
		return generateCdtProject;
	}

	public boolean generateDot() {
		return generateDot;
	}
	
	public int debugPrint() {
		return debugPrint;
	}
	
	public String getWorkingDirectory() {
		return workingDirectory;
	}
	
	public void setWorkingDirectory(String dir) {
		workingDirectory = dir;		
	}
	
	public String getRunOptions() {
		return runOptions;
	}
	
	public void setCProject(IProject cproject) {
		this.cproject = cproject;
	}
	
	public IProject getCProject() {
		return cproject;
	}
	
	public String getTarget() {
		return target;
	}

	public void setLaunchConfigName(String launchConfigName) {
		this.launchConfigName = launchConfigName;
	}
	
	public String getLaunchConfigName() {
		return launchConfigName;
	}

}
