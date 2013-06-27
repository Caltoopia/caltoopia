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

package org.caltoopia.frontend.ui.contentassist;

import java.util.List;
import org.caltoopia.frontend.cal.AstActorVariable;
import org.caltoopia.frontend.cal.AstConnection;
import org.caltoopia.frontend.cal.AstEntity;
import org.caltoopia.frontend.cal.AstExpressionSymbolReference;
import org.caltoopia.frontend.cal.AstPort;
import org.caltoopia.frontend.cal.AstStatementAssign;
import org.caltoopia.frontend.cal.AstVariable;
import org.caltoopia.frontend.cal.CalPackage;
import org.caltoopia.frontend.ui.contentassist.AbstractCalProposalProvider;
import org.caltoopia.types.TypeConverter;
import org.caltoopia.types.TypeSystem;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeDeclaration;
import org.caltoopia.ir.TypeRecord;
import org.caltoopia.ir.TypeUser;
import org.caltoopia.ir.Variable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;
import com.google.inject.Inject;

/**
 * see http://www.eclipse.org/Xtext/documentation/latest/xtext.html#contentAssist on how to customize content assistant
 */
public class CalProposalProvider extends AbstractCalProposalProvider {
	
	@Inject
	ResourceDescriptionsProvider rdp;
	
	@Override
	public void completeImport_ImportedNamespace(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		IResourceDescriptions rd = rdp.getResourceDescriptions(model.eResource());
		for (IEObjectDescription d : rd.getExportedObjectsByType(CalPackage.eINSTANCE.getAstNamespace())) {
			acceptor.accept(createCompletionProposal(d.getQualifiedName().toString(), context));
		}
	}
	
	@Override
	public void completeAstConnection_From(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	
	@Override
	public void completeAstConnection_OutPort(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		if (model instanceof AstConnection) {
			AstConnection connection = (AstConnection) model;
			AstActorVariable actor = connection.getFrom().getVariable();
			AstEntity entity = actor.getType();
			List<AstPort> ports = entity.getActor().getOutputs();
			for (AstPort port : ports) {
				acceptor.accept(createCompletionProposal(port.getName(), context));
			}
		}
	}
	
	@Override
	public void completeAstConnection_To(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		completeRuleCall(((RuleCall)assignment.getTerminal()), context, acceptor);
	}
	
	@Override
	public void completeAstConnection_InPort(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		if (model instanceof AstConnection) {
			AstConnection connection = (AstConnection) model;
			AstActorVariable actor = connection.getTo().getVariable();
			AstEntity entity = actor.getType();
			List<AstPort> ports = entity.getActor().getInputs();			
			for (AstPort port : ports) {
				acceptor.accept(createCompletionProposal(port.getName(), context));
			}
		}
	}
	
	@Override
	public void completeAstExpressionSymbolReference_Member(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		if (model instanceof AstExpressionSymbolReference) {			
			AstVariable var = ((AstExpressionSymbolReference) model).getSymbol();
			Type type = TypeConverter.convert(null, var.getType(), true);		
			if (TypeSystem.isUser(type)) {
				TypeDeclaration typeDecl = (TypeDeclaration) ((TypeUser) type).getDeclaration();
				if (TypeSystem.isRecord(typeDecl.getType())) {
					TypeRecord typeRecord = (TypeRecord) typeDecl.getType();
					for (Variable m : typeRecord.getMembers()) {
						acceptor.accept(createCompletionProposal("." + m.getName(), context));
					}
				}
			}
		}		
	}	
	
	public void complete_AstMemberAccess(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		if (model instanceof AstStatementAssign) {			
			AstVariable var = ((AstStatementAssign) model).getTarget();
			Type type = TypeConverter.convert(null, var.getType(), true);		
			if (TypeSystem.isUser(type)) {
				TypeDeclaration typeDecl = (TypeDeclaration) ((TypeUser) type).getDeclaration();
				if (TypeSystem.isRecord(typeDecl.getType())) {
					TypeRecord typeRecord = (TypeRecord) typeDecl.getType();
					for (Variable m : typeRecord.getMembers()) {
						acceptor.accept(createCompletionProposal("." + m.getName(), context));
					}
				}
			}
		}
	}
	
}
