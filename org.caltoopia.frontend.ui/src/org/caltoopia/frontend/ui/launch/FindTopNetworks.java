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

package org.caltoopia.frontend.ui.launch;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.caltoopia.frontend.cal.AstEntity;
import org.caltoopia.frontend.cal.AstNamespace;
import org.caltoopia.frontend.cal.AstNetwork;
import org.caltoopia.frontend.util.VoidSwitch;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.mwe.ContainersStateFactory;
import org.eclipse.xtext.resource.containers.DelegatingIAllContainerAdapter;
import org.eclipse.xtext.resource.containers.IAllContainersState;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

public class FindTopNetworks extends VoidSwitch {
	
	private List<String> topNetworks;
	
	static List<String> run(List<String> paths) {
		FindTopNetworks me = new FindTopNetworks();
		List<String> tops = me.find(paths);

		for (String name : tops) {
			System.out.println("Top network: " + name);
		}
		return tops;
	}
	
	public FindTopNetworks() {
		topNetworks = new ArrayList<String>();
	}
	
	public Void caseAstEntity(AstEntity entity) {

		if (entity.getNetwork() != null) {
			AstNetwork network = (AstNetwork) entity.getNetwork();
			if (network.getParameters().isEmpty() && network.getInputs().isEmpty() && network.getOutputs().isEmpty()) {
				String qualifiedName = entity.getName();
				EObject container = entity.eContainer();
				while (container != null) {
					if (container instanceof AstNamespace) {
						qualifiedName = ((AstNamespace) container).getName() + "." + qualifiedName;
					}
					container = container.eContainer();
				}
				topNetworks.add(qualifiedName);
			}
		}
		
		return null;
	}
	
	List<String> getTopNetworks() {
		return topNetworks;
	}
		
	public List<String> find(List<String> paths) {	
		ResourceSet resourceSet = new ResourceSetImpl();
		
		// Scan the paths for actors & networks.
		List<String> files = new ArrayList<String>(); 
		
		for (String path : paths) {
			getActors(files, new File(path));			
		}
		
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
						
		List<Resource> resources = Lists.newArrayList(resourceSet.getResources());
				
		for (Resource res : resources) {
			try {
				res.load(null);
				doSwitch(res.getContents().get(0));			
			} catch (Exception x) {
				System.err.println("[FindTopNetworks] error: " + x.getMessage());
			}
		}
		
		return topNetworks;
	}	
	
	private void getActors(List<String> files, File path) {	
		for (File file : path.listFiles()) {
			if (file.isDirectory()) {
				getActors(files, file);
			} else if (file.getName().endsWith("cal")) {
				files.add(file.getPath());
			}
		}
	}
	
	
}
