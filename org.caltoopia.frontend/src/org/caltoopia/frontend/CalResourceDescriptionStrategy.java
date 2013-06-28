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

package org.caltoopia.frontend;

import org.caltoopia.frontend.cal.AstEntity;
import org.caltoopia.frontend.cal.AstFunction;
import org.caltoopia.frontend.cal.AstNamespace;
import org.caltoopia.frontend.cal.AstTypeUser;
import org.caltoopia.frontend.cal.AstVariable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionStrategy;
import org.eclipse.xtext.util.IAcceptor;

public class CalResourceDescriptionStrategy extends DefaultResourceDescriptionStrategy {

	public boolean createEObjectDescriptions(EObject eObject, IAcceptor<IEObjectDescription> acceptor) {

		if (eObject instanceof AstNamespace) {
			AstNamespace topNamespace = (AstNamespace) eObject;
			computeExportedObjects(topNamespace, acceptor);		
			return true;
		} else { 
			return false;
		}
	}

	private void computeExportedObjects(AstNamespace namespace, IAcceptor<IEObjectDescription> acceptor) {
		
		tryAddWithQualifiedName(namespace, acceptor);

		// create object descriptions for actor classes
		for (AstEntity entity : namespace.getEntities()) {
			tryAddWithQualifiedName(entity, acceptor);
		}

		// create the object description for the entity (with qualified name)		
		for (AstVariable decl : namespace.getFunctions()) {
			tryAddWithQualifiedName(decl, acceptor);
		}
		
		for (EObject decl : namespace.getExternals()) {
			tryAddWithQualifiedName(decl, acceptor);
		}
		
		for (AstTypeUser decl : namespace.getTypedefs()) {
			// Export the type
			tryAddWithQualifiedName(decl, acceptor);
		}	
		
		for (AstVariable decl : namespace.getVariables()) {
			tryAddWithQualifiedName(decl, acceptor);
		}
	}
	
	void tryAddWithQualifiedName(EObject obj, IAcceptor<IEObjectDescription> acceptor) {
		try {
			QualifiedName qualifiedName = getQualifiedNameProvider().getFullyQualifiedName(obj);
			acceptor.accept(EObjectDescription.create(qualifiedName, obj));
		} catch (NullPointerException x) {
			// Do nothing
		}
	}
	
}



