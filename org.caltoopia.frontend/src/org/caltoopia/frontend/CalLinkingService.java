/*
 * Copyright (c) 2010, IETR/INSA of Rennes
 * Copyright (c) 2011 ERICSSON AB
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 *   * Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *   * Neither the name of the IETR/INSA of Rennes nor the names of its
 *     contributors may be used to endorse or promote products derived from this
 *     software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 */
package org.caltoopia.frontend;

import java.util.Collections;
import java.util.List;
import org.caltoopia.frontend.cal.AstState;
import org.caltoopia.frontend.cal.AstType;
import org.caltoopia.frontend.cal.AstTypeName;
import org.caltoopia.frontend.cal.CalFactory;
import org.caltoopia.frontend.cal.CalPackage;
import org.caltoopia.frontend.util.Util;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.linking.impl.DefaultLinkingService;
import org.eclipse.xtext.nodemodel.INode;

/**
 * This class defines a linking service for FSM states.
 * 
 * @author Matthieu Wipliez
 * 	       Johan Eker
 * 
 */
public class CalLinkingService extends DefaultLinkingService {
		
	public static String strUndef = "undef";	
	
	private Resource stubsResource = null;	
	
	
	@Override
	public List<EObject> getLinkedObjects(EObject context, EReference ref, INode node) {	
		
		List<EObject> result = null;
		try {
			result = super.getLinkedObjects(context, ref, node);
		} catch (Exception x) {
			System.out.println(x.getMessage());
		}
		if (result != null && !result.isEmpty()) {
			return result;
		}

		final EClass requiredType = ref.getEReferenceType();
		final String s = getCrossRefNodeAsString(node);		
		if (requiredType != null && s != null) {
			if (CalPackage.Literals.AST_STATE.isSuperTypeOf(requiredType)) {
				return getState(context, ref, s);
			} 
		}

		return Collections.emptyList();
	}

	private List<EObject> getState(EObject context, EReference reference, String name) {
		EObject cter = Util.getTopLevelContainer(context);

		// Create the model element instance using the factory
		AstState state = CalFactory.eINSTANCE.createAstState();
		state.setName(name);

		// Attach the stub to the resource that's being parsed
		Resource res = makeResource(cter.eResource());
		res.getContents().add(state);

		return Collections.singletonList((EObject) state);
	}

	/**
	 * Use a temporary 'child' resource to hold created stubs. The real resource
	 * URI is used to generate a 'temporary' resource to be the container for
	 * stub EObjects.
	 * 
	 * @param source
	 *            the real resource that is being parsed
	 * @return the cached reference to a resource named by the real resource
	 *         with the added extension 'xmi'
	 */
	private Resource makeResource(Resource source) {
		if (null != stubsResource)
			return stubsResource;
		URI stubURI = source.getURI().appendFileExtension("xmi");

		stubsResource = source.getResourceSet().getResource(stubURI, false);
		if (null == stubsResource) {
			// TODO find out if this should be cleaned up so as not to clutter
			// the project.
			stubsResource = source.getResourceSet().createResource(stubURI);
		}

		return stubsResource;
	}
	

	public static AstType createAstTypeUndef() {
		AstType type = CalFactory.eINSTANCE.createAstType();
		AstTypeName name = CalFactory.eINSTANCE.createAstTypeName();
		name.setName(strUndef);
		type.setName(name);
		return type;
	}

	
}
