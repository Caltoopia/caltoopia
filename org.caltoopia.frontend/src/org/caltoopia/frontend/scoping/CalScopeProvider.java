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

package org.caltoopia.frontend.scoping;

import java.util.ArrayList;
import java.util.List;
import org.caltoopia.frontend.cal.AstAction;
import org.caltoopia.frontend.cal.AstActor;
import org.caltoopia.frontend.cal.AstExpressionList;
import org.caltoopia.frontend.cal.AstForeachGenerator;
import org.caltoopia.frontend.cal.AstFunction;
import org.caltoopia.frontend.cal.AstGenerator;
import org.caltoopia.frontend.cal.AstInputPattern;
import org.caltoopia.frontend.cal.AstNamespace;
import org.caltoopia.frontend.cal.AstProcedure;
import org.caltoopia.frontend.cal.AstStatementBlock;
import org.caltoopia.frontend.cal.AstStatementForeach;
import org.caltoopia.frontend.cal.AstStructure;
import org.caltoopia.frontend.cal.AstNetwork;
import org.caltoopia.frontend.cal.AstActorVariable;
import org.caltoopia.frontend.cal.AstTypeName;
import org.caltoopia.frontend.cal.AstVariable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.Scopes;
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider;

public class CalScopeProvider extends AbstractDeclarativeScopeProvider {
	
	/* 
	 * Compute the CAL actor instances that can be reached 
	 * from a the structure section of a network
	 * The method Scopes.scopeFor() creates a scope using SimpleAttributeResolver.NAME_RESOLVER 
	 * to compute the names and the second argument is the outer scope.
	 */
	
	public IScope scope_AstActorVariableReference_variable(AstStructure structure,	EReference reference) {		
		AstNetwork network = (AstNetwork) structure.eContainer();
		List<AstActorVariable> instances = new ArrayList<AstActorVariable>();
		for (AstActorVariable decl : network.getInstances()) {
			instances.add(decl);
		}
		return Scopes.scopeFor(instances, getScope(network, reference));
	}

	/* 
	 * Compute the set of functions that can are visible from within an actor 
	 * (and subsequently from all actions and their sub scopes)
	 */
	
	public IScope scope_AstVariable(AstActor actor, EReference reference) {
		return Scopes.scopeFor(actor.getFunctions(), delegateGetScope(actor, reference));
	}
	
	public IScope scope_AstVariable(AstNamespace namespace, EReference reference) {
		List<AstVariable> elements = new ArrayList<AstVariable>();
		elements.addAll(namespace.getFunctions());
		for (AstTypeName typedef : namespace.getTypedefs()) {
			for (AstVariable ctor : typedef.getConstructor()) {
				elements.add(ctor);
			}
		}
		return Scopes.scopeFor(elements, delegateGetScope(namespace, reference));
	}

	public IScope scope_AstInputPattern_port(AstAction action, EReference reference) {
		AstActor actor = (AstActor) action.eContainer();
		return Scopes.scopeFor(actor.getInputs(), getScope(actor, reference));
	}

	public IScope scope_AstOutputPattern_port(AstAction action, EReference reference) {
		AstActor actor = (AstActor) action.eContainer();
		return Scopes.scopeFor(actor.getOutputs(), getScope(actor, reference));
	}

	public IScope scope_AstProcedure(AstActor actor, EReference reference) {
		return Scopes.scopeFor(actor.getProcedures(),
				delegateGetScope(actor, reference));
	}

	public IScope scope_AstVariableReference_variable(AstAction action,	EReference reference) {		
		List<AstVariable> elements = new ArrayList<AstVariable>();
		for (AstInputPattern pattern : action.getInputs()) {
			elements.addAll(pattern.getTokens());
		}
		elements.addAll(action.getVariables());
		
		AstActor actor = (AstActor) action.eContainer();
		return Scopes.scopeFor(elements, getScope(actor, reference));
	}

	public IScope scope_AstVariableReference_variable(AstActor actor, EReference reference) {
		List<AstVariable> elements = new ArrayList<AstVariable>();
		elements.addAll(actor.getParameters());
		elements.addAll(actor.getStateVariables());	
		elements.addAll(actor.getFunctions());	
			
		return Scopes.scopeFor(elements, delegateGetScope(actor, reference));
	}
	
	public IScope scope_AstVariableReference_variable(AstActorVariable decl, EReference reference) {
		AstNetwork network = (AstNetwork) decl.eContainer();
		List<AstVariable> elements = new ArrayList<AstVariable>();
		elements.addAll(network.getVariables());
		elements.addAll(network.getParameters());
		return Scopes.scopeFor(elements, getScope(network, reference));
	}

	public IScope scope_AstVariableReference_variable(AstNetwork network, EReference reference) {
		List<AstVariable> elements = new ArrayList<AstVariable>();
		elements.addAll(network.getParameters());

		return Scopes.scopeFor(elements, delegateGetScope(network, reference));
	}
	
	public IScope scope_AstVariableReference_variable(AstExpressionList list, EReference reference) {
		List<AstVariable> elements = new ArrayList<AstVariable>();
		for (AstGenerator generator : list.getGenerators()) {
			elements.add(generator.getVariable());
		}
		EObject container = list.eContainer();
		while (container instanceof AstGenerator || container instanceof AstExpressionList || 
			  container instanceof AstForeachGenerator || container instanceof AstStatementForeach) 
			container = container.eContainer();
		return Scopes.scopeFor(elements, getScope(container, reference));
	}
	
	public IScope scope_AstVariableReference_variable(AstFunction function,	EReference reference) {
		List<AstVariable> elements = new ArrayList<AstVariable>();
		elements.addAll(function.getParameters());
		elements.addAll(function.getVariables());

		EObject cter = function.eContainer();		
		// return Scopes.scopeFor(elements, getScope(cter, reference));
		IScope s = getScope(cter, reference);
		IScope s2 = Scopes.scopeFor(elements, s);
		return s2;
	}

	public IScope scope_AstVariableReference_variable(AstProcedure procedure, EReference reference) {
		List<AstVariable> elements = new ArrayList<AstVariable>();
		elements.addAll(procedure.getParameters());
		elements.addAll(procedure.getVariables());

		AstActor actor = (AstActor) procedure.eContainer();
		return Scopes.scopeFor(elements, getScope(actor, reference));
	}

	public IScope scope_AstVariableReference_variable(AstStatementForeach foreach, EReference reference) {
		List<AstVariable> variables = new ArrayList<AstVariable>();
		for (AstForeachGenerator g : foreach.getGenerators()) {
			variables.add(g.getVariable());
		}
		variables.addAll(foreach.getVariables());
		return Scopes.scopeFor(variables,
				getScope(foreach.eContainer(), reference));
	}
		
    public IScope scope_AstVariableReference_variable(AstStatementBlock block, EReference reference) {
		List<AstVariable> variables = new ArrayList<AstVariable>();		
		variables.addAll(block.getVariables());
		
		return Scopes.scopeFor(variables, getScope(block.eContainer(), reference));
	}
        
}

