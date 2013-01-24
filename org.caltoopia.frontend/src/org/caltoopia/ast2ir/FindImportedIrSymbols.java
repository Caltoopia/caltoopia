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

package org.caltoopia.ast2ir;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.cli.DirectoryException;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Namespace;
import org.caltoopia.ir.TypeDeclarationImport;
import org.caltoopia.ir.VariableImport;
import org.caltoopia.ir.util.IrReplaceSwitch;

public class FindImportedIrSymbols extends IrReplaceSwitch {
	
	private List<Declaration> imports = new ArrayList<Declaration>();
		
	public FindImportedIrSymbols(EObject unit) {
		doSwitch(unit);
	}
	
	public List<Declaration> getImports() {
		return imports;
	}
	
	public Set<Namespace> getImportedNamespaces() {
		Set<Namespace> namespaces = new HashSet<Namespace>();
		for (Declaration decl : imports) {
			try {
				namespaces.add(ActorDirectory.findNamespace(decl));
			} catch (DirectoryException x) {
				System.err.println("[FindImportedIrSymbols] Internal error #1 x = " + x );
				return null;
			}
		}
		
		return namespaces;
	}
	
	@Override
	public EObject caseVariableImport(VariableImport v) { 
		imports.add(v);
		return super.caseVariableImport(v);
	}	

	@Override
	public EObject caseTypeDeclarationImport(TypeDeclarationImport v) { 
		imports.add(v);
		return super.caseTypeDeclarationImport(v);
	}	
	
}	
