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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.caltoopia.ast2ir.Util;
import org.caltoopia.codegen.IrXmlReader;
import org.caltoopia.codegen.IrXmlPrinter;
import org.caltoopia.codegen.UtilIR;
import org.caltoopia.ir.AbstractActor;
import org.caltoopia.ir.ActorInstance;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Namespace;
import org.caltoopia.ir.Network;
import org.caltoopia.ir.Scope;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.TypeDeclaration;
import org.caltoopia.ir.TypeDeclarationImport;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableExternal;
import org.caltoopia.ir.VariableImport;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;

/*
 * The ActorDirectory is managing the persistent actor IR code on disk. It can be accessed either from the
 * command line or from the Eclipse plug-in to added and retrieve actors, networks, types and constants.
 * To retrieve an element the qualified name must be provided and also a list of the projects where to look.
 * When called from the Eclipse plug-in the project names are used and when called from the command line 
 * the provided source paths are used to define implicit projects (i.e. each unique path is regarded as
 * a project) 
 * 
 * The code on disk is organized in a file structure where folder corresponds to namespace. A namespace can
 * be defined in several projects and may thus span over several source files. Actors and networks are stored 
 * in files named after the actor/network. Constants & types are written to files named after the sourcefile
 * with file delimiter replaced by '$'.
 * In addition there is an index-file created and updated each time the source file is compiled. This index file
 * is intended to provide a link between the source file and the actors that are generated. For example, when 
 * actors are deleted in the source file, the data in the index-file is used to remove the corresponding 
 * IR XML file from disk. The index-file can also be used to determine which CAL source files that need 
 * to be recompiled by inspection of the respective time stamps. The index file will also contain some meta data,
 * for example, information about potential top level networks.
 * 
 * .ActorDirectory/projectA/namespace1/namespace2/actorA.xml
 *                                               /actorX.xml
 *                                               /$path$to$sourcefile1.xml
 *                                               /$path$to$sourcefile2.xml
 *                         /$path$to$sourcefile1.index                       
 *                         /$path$to$sourcefile2.index                       
 *                /projectB/namespace3/actorB.xml
 *                                    /$path$to$sourcefile3.xml
 *                         /$path$to$sourcefile3.index
 *                /projectC/namespace1/actorC.xml 
 *                                    /$path$to$sourcefile4.xml
 *                                    /namespace2/ActorA.xml
 *                         /$path$to$sourcefile4.index 
 *                        
 *                                   
 *  The *.index file serves as a map from the source file to the resulting IR *.xml-files. The purpose of this
 *  map is to be used in incremental compilation, i.e. regeneration of IR is only needed if the time stamp of the 
 *  index file is older that the time stamp of the *.cal-file.
 *  The format of the index file is simply a list of the generated files:
 *  
 *  qualified.name.of.actor.a   
 *  qualified.name.of.another.actor.a
 *  qualified.name.of.actor.b   
 *  
 *  Files that point to networks that are potential top level networks are marked with a star (*).
 *                           
 */ 

public class ActorDirectory {
	
//	private static AstNamespace builtInAstNamespace = null;
	
	public static String DEFAULTNAMESPACE = "$DEFAULT_NAMESPACE";
	
	public static String FILEEXTENSION = ".xml";
		
	private static Map<String, Namespace> namespaceCache = new HashMap<String, Namespace>(); 
	
	private static CompilationSession session;
		
	public static void initCompilation(CompilationSession session) {
		ActorDirectory.session = session;
	}
	
	private static String currentIndexFile = null;
	
	private static PrintStream indexStream = null;
	
	private static Network transformedTopNetwork = null;
	
	public static void addNamespace(Namespace ns, String sourceRootPath) {		
		String path = Util.getPathAnnotation(ns.getAnnotations());
		String filename = path.replace(File.separator, "$");
		filename = Util.removeWindowsDriveLetter(filename.replace(".cal", ".index"));
		String folder = getActorDirectoryLocation() + File.separator + path2Project(sourceRootPath);
		Util.checkFolder(folder);
		try {
			filename = folder + File.separator + filename;
			if (filename.equals(currentIndexFile)) {			 
			  indexStream.println(Util.packQualifiedName(ns.getName()));
			} else {
			  if (indexStream != null)  {
				  indexStream.close();
			  }
			  File f = new File(filename);
			  if (f.exists()) {
				  f.delete();
			  }
			  indexStream = new PrintStream(filename);
			  currentIndexFile = filename;
			  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			  Date date = new Date();
			  indexStream.println(dateFormat.format(date));
			}

		} catch (Exception x) {
			System.err.println("Failed writing index file x = " + x);
		}

		  new IrXmlPrinter(folder).run(ns);
	}

	public static void addActor(AbstractActor actor, String sourceRootPath) {
		if (actor instanceof Network && actor.getParameters().isEmpty() && actor.getInputPorts().isEmpty() && actor.getOutputPorts().isEmpty()) {
			indexStream.print("*");
		}
		String qid  = Util.packQualifiedName(actor.getType().getNamespace(), actor.getType().getName());		
		indexStream.println(qid);
		new IrXmlPrinter(getActorDirectoryLocation() + File.separator + path2Project(sourceRootPath)).run(actor);
	}

	public static List<String> getTopNetworks(String sourceRootPath)  {	
		List<String> topLevelNetworks = new ArrayList<String>();
		File folder = new File(getActorDirectoryLocation() + File.separatorChar + path2Project(sourceRootPath));
		if (folder.listFiles() != null) {
			for (File file : folder.listFiles()) {
				if (file.getName().endsWith(".index")) {
					try {					
						String line;
						System.out.println(file.getAbsolutePath());
						InputStream fis = new FileInputStream(file.getAbsolutePath());
						BufferedReader br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
						while ((line = br.readLine()) != null) {
							if (line.startsWith("*")) {
								topLevelNetworks.add(line.substring(1));
							}
						}
					} catch (Exception x) {
						// No nothing.
					}
				}
			}
		}
		
		return topLevelNetworks;
	}	
	
	public static Collection<List<String>> getNamespaces() {
		return scanForNamespaces(new ArrayList<String>());
	}
	
	private static Collection<List<String>> scanForNamespaces(List<String> namespace) {
		String folderPath = getActorDirectoryLocation();
		for (String s : namespace) {
			folderPath = folderPath + File.separator + s;
		}
		
		File folder = new File(folderPath);

		Collection<List<String>> result = new ArrayList<List<String>>();
		
		for (File f : folder.listFiles()) {
			if (f.isDirectory()) {
				List<String> ns = new ArrayList<String>(namespace);
				ns.add(f.getName());
				result.add(ns);
				result.addAll(scanForNamespaces(ns));
			}
		}
		
		return result;
	}

	public static AbstractActor findActor(TypeActor type) throws DirectoryException {		
		
		for (String root : getSession().getPaths()) {
			String path = getActorDirectoryLocation() + File.separator + path2Project(root);			
			for (String s : type.getNamespace()) {
				path = path + File.separator + s ;
			}
			path += File.separator + type.getName() + FILEEXTENSION;
		
			if (new File(path).exists()) {
				AbstractActor actor = new IrXmlReader().readActor(path);

				return actor;
			}
		}
		
		throw new DirectoryException("[ActorDirectory] Actor '" + Util.packQualifiedName(type.getNamespace()) + "." + type.getName() + "' not found.");
	}
	
	public static Namespace findNamespace(List<String> namespace) throws DirectoryException {
		String key = Util.packQualifiedName(namespace);
		if (namespaceCache.containsKey(key)) {
			return namespaceCache.get(key);
		}
		
		Namespace result = null;
		
		// Not found in cache, so let's fill the cache then.
		for (String root : getSession().getPaths()) {
			String path = getActorDirectoryLocation() + File.separator + path2Project(root);
			for (String s : namespace) {
				path = path + File.separator + s ;
			}
					
			File directory = new File(path);
			if (directory.exists()) {				
				File files[] = directory.listFiles();
				if (files != null) {
					for (File f : directory.listFiles()) {
						if (f.getName().startsWith("$")) {
							Namespace ns = new IrXmlReader().readNamespace(f.getAbsolutePath());
							if (result != null) {
								for (Declaration def : ns.getDeclarations()) {
									def.setScope(result);
									result.getDeclarations().add(def);
								}
								result.getAnnotations().addAll(ns.getAnnotations());
							} else {
								result = ns;
							}
						}
					}	
				}
			
				
			}
		}
		if (result != null) {	
			namespaceCache.put(key, result);
			return result;			
		} else {		
			throw new DirectoryException("[ActorDirectory] Namespace '" + Util.packQualifiedName(namespace) + "' not found.");
		}
	}
	
	public static List<TypeDeclaration> findAllTypeDeclarations() {
		List<TypeDeclaration> ret = new ArrayList<TypeDeclaration>();
		for(List<String> nsName : getNamespaces()) {
			try {			
				Namespace ns = findNamespace(nsName);
				for (Declaration decl : ns.getDeclarations()) {
					if (decl instanceof TypeDeclaration) {
						ret.add((TypeDeclaration) decl);
					}
				}
			} catch (DirectoryException x) {
				System.err.println("[ActorDirector - Internal Error x = " + x);
			}
		}
		return ret;
	}
	
	public static TypeDeclaration findTypeDeclaration(TypeDeclarationImport typedeclImport) throws DirectoryException {
		if(transformedTopNetwork!=null) {
			return findTransformedTypeDeclaration(typedeclImport);
		}
		Namespace ns = findNamespace(typedeclImport.getNamespace());
		
		for (Declaration decl : ns.getDeclarations()) {
			if (typedeclImport.getName().equals(decl.getName()) && decl instanceof TypeDeclaration) {
				return (TypeDeclaration) decl;
			}
		}
		
		throw new DirectoryException("[ActorDirectory] Typedef '" + typedeclImport.getName() + "' not found.");
	}

	public static Declaration findVariable(VariableImport variableImport) throws DirectoryException {		
		Namespace ns = findNamespace(variableImport.getNamespace());
		
		for (Declaration decl : ns.getDeclarations()) {
			if (variableImport.getName().equals(decl.getName())) {
				if ((decl instanceof Variable) || (decl instanceof VariableExternal)) {
					return  decl;
				}
			}
		}
		
		throw new DirectoryException("[ActorDirectory] Variable '" + variableImport.getName() + "' not found.");
	}
	
	public static Namespace findNamespace(Declaration decl) throws DirectoryException {
		if (decl instanceof VariableImport) {
			return findNamespace(((VariableImport) decl).getNamespace());
		} else if (decl instanceof TypeDeclarationImport) {
			return findNamespace(((TypeDeclarationImport) decl).getNamespace());
		} else {
			Scope result = decl.getScope();

			while (!(result instanceof Namespace)) {				
				result = result.getOuter();
			}
			assert (result != null);
		
			return (Namespace) result;
		}
	}
	
	public static Namespace findNamespace(TypeDeclaration decl) {
		Scope result = decl.getScope();
		
		while (!(result instanceof Namespace)) {
			result = result.getOuter();
		}
		assert (result != null);
		
		return (Namespace) result;
	}
	
	//Store transformed/annotated actors between compilation steps 
	public static void addTransformedActor(AbstractActor actor, ActorInstance actorInstance, String sourceRootPath) {
		new IrXmlPrinter(getActorDirectoryLocation() + File.separator + path2Project(sourceRootPath) + File.separator + "$Transformed",
				actorInstance!=null?actorInstance.getName():null).run(actor);
	}

	//Retrieve transformed/annotated actors between compilation steps 
	public static AbstractActor findTransformedActor(ActorInstance actorInstance) throws DirectoryException {		
		TypeActor type = (TypeActor) actorInstance.getType();
		for (String root : getSession().getPaths()) {
			String path = getActorDirectoryLocation() + File.separator + path2Project(root) + File.separator + "$Transformed";
			for (String s : type.getNamespace()) {
				path = path + File.separator + s ;
			}
			path += File.separator + type.getName() + "_$" + actorInstance.getName() + FILEEXTENSION;
		
			if (new File(path).exists()) {
				AbstractActor actor = new IrXmlReader().readActor(path);

				return actor;
			}
		}
		
		throw new DirectoryException("[ActorDirectory] Annotated Actor '" + Util.packQualifiedName(type.getNamespace()) + "." + type.getName() + "' of instance '" + actorInstance.getName() + "' not found.");
	}

	//Retrieve transformed/annotated actors between compilation steps 
	public static AbstractActor findTransformedActor(TypeActor type) throws DirectoryException {		
		for (String root : getSession().getPaths()) {
			String path = getActorDirectoryLocation() + File.separator + path2Project(root) + File.separator + "$Transformed";
			for (String s : type.getNamespace()) {
				path = path + File.separator + s ;
			}
			path += File.separator + type.getName() + FILEEXTENSION;
		
			if (new File(path).exists()) {
				AbstractActor actor = new IrXmlReader().readActor(path);
				if(actor instanceof Network) {
					transformedTopNetwork = (Network) actor;
				}
				return actor;
			}
		}
		
		throw new DirectoryException("[ActorDirectory] Annotated Actor '" + Util.packQualifiedName(type.getNamespace()) + "." + type.getName() + "' not found.");
	}

	/*
	 * During period of working on elaborated network direct all request for
	 * TypeDeclarations from imports to the ones found in the top network.
	 */
	public static void resetTransformedNetwork() {
		transformedTopNetwork=null;
	}

	public static TypeDeclaration findTransformedTypeDeclaration(TypeDeclarationImport typedeclImport) throws DirectoryException {
		if(transformedTopNetwork!=null) {
			List<String> ns = typedeclImport.getNamespace();
			for (Declaration decl : transformedTopNetwork.getDeclarations()) {
				if (decl instanceof TypeDeclaration && typedeclImport.getName().equals(decl.getName()) &&
						UtilIR.getAnnotatedNamespace(decl).containsAll(ns)) {
					return (TypeDeclaration) decl;
				}
			}
		}		
		throw new DirectoryException("[ActorDirectory] Typedef '" + typedeclImport.getName() + "' not found.");
	}

	public static void clean() {
		namespaceCache = new HashMap<String, Namespace>(); 
		
		String actorDirectoryFolder = getActorDirectoryLocation();
		
		File file = new File(actorDirectoryFolder);
		if (file.exists())
			delete(file);
	}
	
	private static void delete(File f) {
		if (f.isDirectory()) {
			for (File c : f.listFiles())
		      delete(c);
		}
		
		if (!f.delete())
		    System.err.println("Failed to delete file: " + f);
	}
		
	private static String getActorDirectoryLocation() {
		String folder; 
		if (isPlugin()) {		
			IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();  				
			folder =  myWorkspaceRoot.getLocation() + File.separator + ".ActorDirectory";
	    } else {
			folder = getSession().getOutputFolder() + File.separator + ".ActorDirectory";
		} 
		Util.checkFolder(folder);
		return folder;
	}
	
	private static CompilationSession getSession() {
		if (session != null) {
			return session;
		} else {
			System.err.println("[ActorDirectory] Compilation session must be set!");
			return null;
		}
	}
	
	private static boolean isPlugin() {
		if (session == null) {
			return true;
		} else {
			return session.isPlugin();
		}
	}
	
	private static String path2Project(String path) {
		String result = path.replace(File.separator, "$");
		return result;
	}

}
