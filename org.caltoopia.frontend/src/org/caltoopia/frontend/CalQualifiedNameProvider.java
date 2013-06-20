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

import org.caltoopia.frontend.cal.AstConstructor;
import org.caltoopia.frontend.cal.AstEntity;
import org.caltoopia.frontend.cal.AstFunction;
import org.caltoopia.frontend.cal.AstNamespace;
import org.caltoopia.frontend.cal.AstTypeName;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;

public class CalQualifiedNameProvider extends DefaultDeclarativeQualifiedNameProvider  {

	public QualifiedName getFullyQualifiedName(EObject obj) {
		if (obj instanceof AstConstructor) {
			QualifiedName tmp = super.getFullyQualifiedName(obj.eContainer().eContainer());
			tmp = tmp.append(((AstConstructor) obj).getName());
			return tmp;
		} else if (obj instanceof AstEntity && obj.eContainer() instanceof AstNamespace) {
			// obj = obj.eContainer();
			QualifiedName tmp = super.getFullyQualifiedName(obj.eContainer());	
			String name = ((AstEntity) obj).getActor().getName();
			if (name != null) { 
				tmp = tmp.append(name);
				return tmp;
			} else {
				return null;
			}
		} else if (obj instanceof AstNamespace || obj.eContainer() instanceof AstNamespace) {
			return super.getFullyQualifiedName(obj);
		} else {
			return null;
		}
	}

}
