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

package org.caltoopia.frontend.ui.builder;

import org.caltoopia.ast2ir.Ast2Ir;
import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.frontend.cal.AstNamespace;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.xtext.builder.IXtextBuilderParticipant;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescription.Delta;

public class IrBuilder implements IXtextBuilderParticipant {

	public void build(IBuildContext context, IProgressMonitor monitor) throws CoreException {
		
		switch (context.getBuildType()) {		
			case CLEAN: 
				System.err.println("clean");
				ActorDirectory.clean();
			break;
			case FULL:
				System.err.println("full");					
			break;
			case RECOVERY:
				System.err.println("recovery");
			break;
			case INCREMENTAL:
				System.err.println("incremental");
			break;
		}
		
		ResourceSet resourceSet = context.getResourceSet();
		monitor.beginTask("Compiling Actors", context.getDeltas().size());
		for (Delta delta : context.getDeltas()) {
			if (delta.getNew() != null) {
				IResourceDescription resourceDescription = delta.getNew();
				monitor.subTask(resourceDescription.getURI().lastSegment());				
				Resource resource = resourceSet.getResource(resourceDescription.getURI(), true);
				if (resource.getContents().size() > 0 ) {
					AstNamespace namespace = (AstNamespace) resource.getContents().get(0);
					System.out.println("[Ast2Ir] compiling '" + namespace.getName() + "'");
					if (!hasErrors(resource)) {
						try {
							new Ast2Ir().run(namespace, resourceDescription.getURI().toPlatformString(false), context.getBuiltProject().getLocation().toString());
						} catch (Exception e) {
							System.err.println("Compilation failed: " + e.getMessage());				
						}
					}
				}				
			}

			if (monitor.isCanceled()) {
				break;
			}
			monitor.worked(1);
		}

		monitor.done();
	}
	
	private boolean hasErrors(Resource resource) {
		String fullPath = resource.getURI().toPlatformString(true);
		IPath path = new Path(fullPath);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IFile file = root.getFile(path);
		
		try {
			IMarker[] markers = file.findMarkers(EValidator.MARKER, true, IResource.DEPTH_INFINITE);
			for (IMarker marker : markers) {
				if (IMarker.SEVERITY_ERROR == marker.getAttribute(IMarker.SEVERITY,	IMarker.SEVERITY_INFO)) {
					// an error => no compilation
					return true;
				}
			}
		} catch (Exception x) {
			return true;
		}

		return false;
		
	}

}
