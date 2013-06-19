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
import org.caltoopia.frontend.cal.AstActor;
import org.caltoopia.frontend.cal.AstExpression;
import org.caltoopia.frontend.cal.AstExpressionCall;
import org.caltoopia.frontend.cal.AstExpressionVariable;
import org.caltoopia.frontend.cal.AstFunction;
import org.caltoopia.frontend.cal.AstNamespace;
import org.caltoopia.frontend.cal.AstNetwork;
import org.caltoopia.frontend.cal.AstProcedure;
import org.caltoopia.frontend.cal.AstStatement;
import org.caltoopia.frontend.cal.AstStatementCall;
import org.caltoopia.frontend.cal.AstType;
import org.caltoopia.frontend.cal.AstTypeName;
import org.caltoopia.frontend.cal.AstVariable;
import org.caltoopia.types.TypeConverter;
import org.caltoopia.frontend.util.VoidSwitch;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Scope;
import org.caltoopia.ir.TypeDeclarationImport;
import org.caltoopia.ir.VariableImport;

class FindImportedAstSymbols extends VoidSwitch {
	
	private static Set<EObject> imported = null;
	
	private Set<EObject> visited = new HashSet<EObject>();
	
	private EObject topAstContainer = null;
	
	private Scope irScope = null;
	
	public static void run(EObject topAstContainer, Scope irScope) {
		imported = new HashSet<EObject>();
		new FindImportedAstSymbols(topAstContainer, irScope);		
	}
	
	public static void print() {
		System.out.println("---------------- IMPORTED ----------------");
		for (EObject decl : imported) {
			System.out.println(decl);
		}
		System.out.println("------------------------------------------");
	}
	
	private FindImportedAstSymbols(EObject topAstContainer, Scope irScope) {
		this.topAstContainer = topAstContainer;
		this.irScope = irScope;
		doSwitch(topAstContainer);
	}
	
	@Override
	public Void caseAstNamespace(AstNamespace namespace) {
		
		for (AstVariable fun : namespace.getFunctions()) {
			doSwitch(fun);
		}

		for (AstTypeName typedef : namespace.getTypedefs()) {
			doSwitch(typedef);
		}

		for (AstVariable var : namespace.getVariables()) {
			doSwitch(var);
		}
		
		return null;
	}
	
	@Override
	public Void caseAstExpressionCall(AstExpressionCall e) { 
		AstVariable v = e.getFunction();
		AstNamespace exportingNamespace;	
		
		for (AstExpression arg : e.getParameters()) {
			doSwitch(arg);
		}
		
		// If already imported, just leave
		if (imported.contains(v) || visited.contains(v)) return null;
		visited.add(v);	
		
		exportingNamespace = checkDefinitionContainer(v.eContainer(), topAstContainer); 		
		
		if (exportingNamespace != null) {
			List<String> exportingNamespaceName = createExportingNamespace(exportingNamespace);
			if (v instanceof AstFunction) {
				Declaration d = Util.createImportedFunctionDeclaration(exportingNamespaceName, (AstFunction) v);	
					
				irScope.getDeclarations().add(d);
			} else {
				AstTypeName astTypedef = (AstTypeName) v.eContainer();
					
				if (imported.contains(astTypedef)) return null;

				TypeDeclarationImport typeImport = Util.createImportedTypeDeclaration(exportingNamespaceName, astTypedef);	
					
				irScope.getDeclarations().add(typeImport);		
				imported.add(astTypedef);
			}
				
			imported.add(v);			
		} else {		
			if (v instanceof AstFunction)
				doSwitch(((AstFunction) v).getExpression());
		}
		
		return null;
	}
	
	@Override
	public Void caseAstExpressionVariable(AstExpressionVariable e)  {
		AstVariable v = e.getValue().getVariable();			
		AstNamespace exportingNamespace;
		
		// If already imported, just leave
		if (imported.contains(v)) return null;
		
		exportingNamespace = checkDefinitionContainer(v.eContainer(), topAstContainer); 		

		if (exportingNamespace != null) {
			List<String> exportingNamespaceName = createExportingNamespace(exportingNamespace);
			VariableImport d = Util.createImportedVariableDeclaration(exportingNamespaceName, v);				
			irScope.getDeclarations().add(d);

			imported.add(v);
		} 
		
		super.caseAstExpressionVariable(e);

		return null;
	}	
		
	@Override
	public Void caseAstStatementCall(AstStatementCall call) {
		AstProcedure p = call.getProcedure();
		AstNamespace exportingNamespace;
						
		for (AstExpression arg : call.getParameters()) {
			doSwitch(arg);
		}
		
		// If already visited, just leave
		if (imported.contains(p) || visited.contains(p)) return null;
		visited.add(p);
		
		exportingNamespace = checkDefinitionContainer(p.eContainer(), topAstContainer); 
				
		if (exportingNamespace != null) {
			List<String> exportingNamespaceName = createExportingNamespace(exportingNamespace);
			VariableImport d = Util.createImportedProcedureDeclaration(exportingNamespaceName, p);				
			irScope.getDeclarations().add(d);				
			
			imported.add(p);			
		} else {
			for (AstStatement stmt : p.getStatements()) {
				doSwitch(stmt);
			}
		}

		return null;
	}
	
	@Override 
	public Void caseAstType(AstType astType) {
		if (TypeConverter.isUserDefined(astType)) {
			AstNamespace exportingNamespace;
			
			// If already imported, just leave
			if (imported.contains(astType.getName())) return null;

			exportingNamespace = checkDefinitionContainer(astType.getName().eContainer(), topAstContainer); 
				
			if (exportingNamespace != null) {
				super.caseAstType(astType);
				List<String> exportingNamespaceName = createExportingNamespace(exportingNamespace);
				AstTypeName astTypedef = astType.getName();
				TypeDeclarationImport typeImport = Util.createImportedTypeDeclaration(exportingNamespaceName, astTypedef);				
				irScope.getDeclarations().add(typeImport);					
				imported.add(astTypedef);

				return null;
			}
		}

		return super.caseAstType(astType);
	}
	
	private List<String> createExportingNamespace(AstNamespace astExportingNamespace) {
		List<String> exportingNamespaceName = new ArrayList<String>();
		
		//Need to fill up with outer namespaces first
		EObject outer = astExportingNamespace.eContainer();
		while(outer instanceof AstNamespace) {
			exportingNamespaceName.add(0,((AstNamespace) outer).getName());
			outer = ((AstNamespace) outer).eContainer();
		}
		
		String[] tmp = java.util.regex.Pattern.compile("\\.").split(astExportingNamespace.getName());
		for (String s : tmp) {
			exportingNamespaceName.add(s);
		}
		return exportingNamespaceName;
	}
	
	/* 
	 * 	The method checkDefinitionContainer calculates if a variable is declared
	 *  in the scope (or subscope) of a given block. The parameter 'definitionContainer'
	 * 	is the declaration scope of the variable and the parameter 'topContainer' is 
	 *  highest level scope of the file being processed. 
	 *  In case that the 'definitionContainer' is not identical to the 'topContainer' then 
	 *  the variable must have been imported and the definition scope of the variable 
	 *  is returned.
	 * 
	 */
	
	private AstNamespace checkDefinitionContainer(EObject definitionContainer, EObject topContainer) {
		while (!(definitionContainer instanceof AstNamespace) &&  
			   !(definitionContainer instanceof AstActor)     &&
			   !(definitionContainer instanceof AstNetwork)   &&
			   !(definitionContainer instanceof AstFunction)  && 
			   !(definitionContainer instanceof AstProcedure)) {
				  definitionContainer = definitionContainer.eContainer();
		}
		if (definitionContainer != topContainer && (definitionContainer instanceof AstNamespace)) {
			return (AstNamespace) definitionContainer;
		} else {
			return null;
		}
	}
}	
