/*
 * Copyright (c) 2010, IETR/INSA of Rennes
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
package org.caltoopia.frontend.util;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.caltoopia.frontend.cal.AstAbstractActor;
import org.caltoopia.frontend.cal.AstAction;
import org.caltoopia.frontend.cal.AstActor;
import org.caltoopia.frontend.cal.AstActorVariable;
import org.caltoopia.frontend.cal.AstActorVariableReference;
import org.caltoopia.frontend.cal.AstAssignParameter;
import org.caltoopia.frontend.cal.AstConnection;
import org.caltoopia.frontend.cal.AstConnectionAttribute;
import org.caltoopia.frontend.cal.AstExpressionAlternative;
import org.caltoopia.frontend.cal.AstExpressionCase;
import org.caltoopia.frontend.cal.AstPattern;
import org.caltoopia.frontend.cal.AstStatementAlternative;
import org.caltoopia.frontend.cal.AstStatementCase;
import org.caltoopia.frontend.cal.AstSubPattern;
import org.caltoopia.frontend.cal.AstTaggedTuple;
import org.caltoopia.frontend.cal.AstEntity;
import org.caltoopia.frontend.cal.AstExpression;
import org.caltoopia.frontend.cal.AstExpressionBinary;
import org.caltoopia.frontend.cal.AstExpressionBoolean;
import org.caltoopia.frontend.cal.AstExpressionFloat;
import org.caltoopia.frontend.cal.AstExpressionIf;
import org.caltoopia.frontend.cal.AstExpressionInteger;
import org.caltoopia.frontend.cal.AstExpressionList;
import org.caltoopia.frontend.cal.AstExpressionString;
import org.caltoopia.frontend.cal.AstExpressionUnary;
import org.caltoopia.frontend.cal.AstExpressionSymbolReference;
import org.caltoopia.frontend.cal.AstForeachGenerator;
import org.caltoopia.frontend.cal.AstFunction;
import org.caltoopia.frontend.cal.AstGenerator;
import org.caltoopia.frontend.cal.AstInputPattern;
import org.caltoopia.frontend.cal.AstMemberAccess;
import org.caltoopia.frontend.cal.AstNamespace;
import org.caltoopia.frontend.cal.AstNetwork;
import org.caltoopia.frontend.cal.AstOutputPattern;
import org.caltoopia.frontend.cal.AstPort;
import org.caltoopia.frontend.cal.AstProcedure;
import org.caltoopia.frontend.cal.AstStatement;
import org.caltoopia.frontend.cal.AstStatementAssign;
import org.caltoopia.frontend.cal.AstStatementCall;
import org.caltoopia.frontend.cal.AstStatementForeach;
import org.caltoopia.frontend.cal.AstStatementIf;
import org.caltoopia.frontend.cal.AstStatementWhile;
import org.caltoopia.frontend.cal.AstStatementBlock;
import org.caltoopia.frontend.cal.AstType;
import org.caltoopia.frontend.cal.AstTypeUser;
import org.caltoopia.frontend.cal.AstTypeParam;
import org.caltoopia.frontend.cal.AstVariable;
import org.caltoopia.frontend.cal.Import;
import org.caltoopia.frontend.cal.util.CalSwitch;

/**
 * This class defines a basic switch that visits everything until
 * {@link #doSwitch(org.eclipse.emf.ecore.EObject)} returns <code>true</code>.
 * Case methods should be overridden to implement predicates.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class BooleanSwitch extends CalSwitch<Boolean> {

	Set<EObject> visited = new HashSet<EObject>();
	
	@Override
	public Boolean caseAstNamespace(AstNamespace namespace) {
		for (AstEntity entity : namespace.getEntities()) {
			if (doSwitch(entity)) {
				return true;
			}
		}

		for (AstVariable fun : namespace.getFunctions()) {
			if (doSwitch(fun)) {
				return true;
			}
		}

		for (AstNamespace ns : namespace.getNamespaces()) {
			if (doSwitch(ns)) {
				return true;
			}
		}

		for (AstTypeUser typedef : namespace.getTypedefs()) {
			if (doSwitch(typedef)) {
				return true;
			}
		}

		for (AstVariable var : namespace.getVariables()) {
			if (doSwitch(var)) {
				return true;
			}
		}
				
		return false;
	}
	
	@Override
	public Boolean caseAstAction(AstAction action) {
		for (AstInputPattern input : action.getInputs()) {
			if (doSwitch(input)) {
				return true;
			}
		}

		for (AstExpression guard : action.getGuards()) {
			if (doSwitch(guard)) {
				return true;
			}
		}

		for (AstVariable variable : action.getVariables()) {
			if (doSwitch(variable)) {
				return true;
			}
		}

		for (AstStatement statement : action.getStatements()) {
			if (doSwitch(statement)) {
				return true;
			}
		}

		for (AstOutputPattern output : action.getOutputs()) {
			if (doSwitch(output)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public Boolean caseAstActor(AstActor actor) {
		for (AstAction action : actor.getActions()) {
			if (doSwitch(action)) {
				return true;
			}
		}

		for (AstVariable function : actor.getFunctions()) {
			if (doSwitch(function)) {
				return true;
			}
		}

		for (AstAction action : actor.getInitializes()) {
			if (doSwitch(action)) {
				return true;
			}
		}

		for (AstPort port : actor.getInputs()) {
			if (doSwitch(port)) {
				return true;
			}
		}

		for (AstPort port : actor.getOutputs()) {
			if (doSwitch(port)) {
				return true;
			}
		}

		for (AstVariable parameter : actor.getParameters()) {
			if (doSwitch(parameter)) {
				return true;
			}
		}

		for (AstProcedure procedure : actor.getProcedures()) {
			if (doSwitch(procedure)) {
				return true;
			}
		}

		for (AstVariable parameter : actor.getStateVariables()) {
			if (doSwitch(parameter)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public Boolean caseAstEntity(AstEntity entity) {
		AstAbstractActor actor = entity.getActor();
		if (actor instanceof AstActor || actor instanceof AstNetwork) {
			return doSwitch(actor);
		} 
		return true;
	}

	@Override
	public Boolean caseAstNetwork(AstNetwork network) {
		for (AstVariable p: network.getParameters()) {
			if (doSwitch(p)) {
				return true;
			}
		}

		for (AstPort i : network.getInputs()) {
			if (doSwitch(i)) {
				return true;
			}
		}

		for (AstPort o : network.getOutputs()) {
			if (doSwitch(o)) {
				return true;
			}
		}

		for (AstVariable v : network.getVariables()) {
			if (doSwitch(v)) {
				return true;
			}
		}

		for (AstActorVariable a : network.getInstances()) {
			if (doSwitch(a)) {
				return true;
			}
		}
		
		for (AstConnection c : network.getStructure().getConnections()) {
			if (doSwitch(c)) {
				return true;
			}
		}
				
		return false;
	}

	@Override 
	public Boolean caseAstActorVariable(AstActorVariable a) {
		for (AstAssignParameter p : a.getParameters()) {
			if (doSwitch(p)) {
				return true;
			}
		}
		return false;
	}

	@Override 
	public Boolean caseAstActorVariableReference(AstActorVariableReference a) {
		return false;
	}

	
	@Override
	public Boolean caseAstConnection(AstConnection c) {
		if (doSwitch(c.getFrom())) {
			return true;
		}
		
		if (doSwitch(c.getTo())) {
			return true;			
		}
		
		for (AstConnectionAttribute a : c.getAttribute()) {
			if (doSwitch(a)) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public Boolean caseAstConnectionAttribute(AstConnectionAttribute a) {
		if (doSwitch(a.getValue())) {
			return true;
		}
		return false;
	}
	
	@Override 
	public Boolean caseAstAssignParameter(AstAssignParameter p) {
		if (doSwitch(p.getValue())) {
			return true;
		}
		return false;
	}
	
	
	@Override
	public Boolean caseAstExpressionBinary(AstExpressionBinary expression) {
		if (doSwitch(expression.getLeft()) || doSwitch(expression.getRight())) {
			return true;
		}

		return false;
	}

	@Override
	public Boolean caseAstExpressionBoolean(AstExpressionBoolean expression) {
		return false;
	}

	@Override
	public Boolean caseAstExpressionSymbolReference(AstExpressionSymbolReference ref) {
		
		for (AstExpression parameter : ref.getParameters()) {
			if (doSwitch(parameter)) {
				return true;
			}
		}
		
		for (AstMemberAccess mv: ref.getMember()) {
			if (doSwitch(mv)) {
				return true;
			}
		}

		for (AstExpression index: ref.getIndexes()) {
			if (doSwitch(index)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public Boolean caseAstExpressionFloat(AstExpressionFloat expression) {
		return false;
	}

	@Override
	public Boolean caseAstExpressionIf(AstExpressionIf expression) {
		if (doSwitch(expression.getCondition())
			|| doSwitch(expression.getThen())
			|| doSwitch(expression.getElse())) {
			return true;
		}

		return false;
	}

	@Override
	public Boolean caseAstExpressionInteger(AstExpressionInteger expression) {
		return false;
	}

	@Override
	public Boolean caseAstExpressionList(AstExpressionList expression) {
		for (AstExpression subExpression : expression.getExpressions()) {
			if (doSwitch(subExpression)) {
				return true;
			}
		}

		for (AstGenerator generator : expression.getGenerators()) {
			if (doSwitch(generator)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public Boolean caseAstExpressionString(AstExpressionString expression) {
		return false;
	}

	@Override
	public Boolean caseAstExpressionUnary(AstExpressionUnary expression) {
		return doSwitch(expression.getExpression());
	}

	@Override 
	public Boolean caseAstMemberAccess(AstMemberAccess mv) {
		for (AstExpression index: mv.getMemberIndex()) {
			if (doSwitch(index)) {
				return true;
			}
		}		
		return false;
	}
	
	@Override
	public Boolean caseAstExpressionCase(AstExpressionCase exprCase) { 
		if (doSwitch(exprCase.getExpression())) {
			return true;
		}

		for (AstExpressionAlternative alt : exprCase.getCases()) {
			if (doSwitch(alt)) {
				return true;
			}
		}
		
		return false;	
	}
	
	@Override 
	public Boolean caseAstExpressionAlternative(AstExpressionAlternative exprAlt) {
		if (doSwitch(exprAlt.getPattern())) {
			return true;
		}

		for (AstExpression guard : exprAlt.getGuards()) {
			if (doSwitch(guard)) {
				return true;
			}
		}
		
		if (doSwitch(exprAlt.getExpression())) {
			return true;
		}
		
		return false;
	}	

	@Override
	public Boolean caseAstFunction(AstFunction function) {
		for (AstVariable parameter : function.getParameters()) {
			if (doSwitch(parameter)) {
				return true;
			}
		}

		for (AstVariable variable : function.getVariables()) {
			if (doSwitch(variable)) {
				return true;
			}
		}

		return doSwitch(function.getType())
				|| doSwitch(function.getExpression());
	}

	@Override
	public Boolean caseAstGenerator(AstGenerator generator) {
		return (doSwitch(generator.getVariable()) || doSwitch(generator.getExpression()));
	}
	
	@Override
	public Boolean caseAstInputPattern(AstInputPattern input) {
		// if (doSwitch(input.getPort())) {
		//	return true;
		// }

		for (AstVariable token : input.getTokens()) {
			if (doSwitch(token)) {
				return true;
			}
		}

		if (doSwitch(input.getRepeat())) {
			return true;
		}

		return false;
	}

	@Override
	public Boolean caseAstOutputPattern(AstOutputPattern output) {
		if (doSwitch(output.getPort())) {
			return true;
		}

		for (AstExpression value : output.getValues()) {
			if (doSwitch(value)) {
				return true;
			}
		}

		if (doSwitch(output.getRepeat())) {
			return true;
		}

		return false;
	}

	@Override
	public Boolean caseAstPort(AstPort port) {
		return doSwitch(port.getType());
	}

	@Override
	public Boolean caseAstProcedure(AstProcedure procedure) {
		for (AstVariable parameter : procedure.getParameters()) {
			if (doSwitch(parameter)) {
				return true;
			}
		}

		for (AstVariable variable : procedure.getVariables()) {
			if (doSwitch(variable)) {
				return true;
			}
		}

		for (AstStatement statement : procedure.getStatements()) {
			if (doSwitch(statement)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public Boolean caseAstStatementAssign(AstStatementAssign assign) {
		for (AstExpression index : assign.getIndexes()) {
			if (doSwitch(index)) {
				return true;
			}
		}

		for (AstMemberAccess member : assign.getMember()) {
			if (doSwitch(member)) {
				return true;
			}
		}

		return doSwitch(assign.getValue());
	}

	@Override
	public Boolean caseAstStatementCall(AstStatementCall call) {
		for (AstExpression parameter : call.getParameters()) {
			if (doSwitch(parameter)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public Boolean caseAstStatementForeach(AstStatementForeach foreach) {
		for (AstForeachGenerator g : foreach.getGenerators()) {
			if (doSwitch(g)) {
				return true;
			}
		}		
		
		if (foreach.getVariables() != null && !foreach.getVariables().isEmpty()) {
			for (AstVariable v : foreach.getVariables()) {
				if (doSwitch(v)) {
					return true;
				}
			}
		}
		
		for (AstStatement statement : foreach.getStatements()) {
			if (doSwitch(statement)) {
				return true;
			}
		}

		return false;
	}
	
	@Override 
	public Boolean caseAstForeachGenerator(AstForeachGenerator generator) {
		if (doSwitch(generator.getVariable())) {
			return true;
		}
		if (doSwitch(generator.getExpression())) {
			return true;
		}
		
		return false;
	}

	@Override
	public Boolean caseAstStatementIf(AstStatementIf stmtIf) {
		if (doSwitch(stmtIf.getCondition())) {
			return true;
		}

		for (AstStatement statement : stmtIf.getThen()) {
			if (doSwitch(statement)) {
				return true;
			}
		}

		for (AstStatement statement : stmtIf.getElse()) {
			if (doSwitch(statement)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public Boolean caseAstStatementWhile(AstStatementWhile stmtWhile) {
		if (doSwitch(stmtWhile.getCondition())) {
			return true;
		}

		for (AstStatement statement : stmtWhile.getStatements()) {
			if (doSwitch(statement)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public Boolean caseAstStatementBlock(AstStatementBlock stmtBlock) { 
		for (AstVariable v: stmtBlock.getVariables()) {
			if (doSwitch(v)) {
				return true;
			}
		}
		
		for (AstStatement s: stmtBlock.getStatements()) {
			if (doSwitch(s)) {
				return true;
			}
		}
		return false;	
	}
	
	@Override
	public Boolean caseAstStatementCase(AstStatementCase stmtCase) { 
		if (doSwitch(stmtCase.getExpression())) {
			return true;
		}

		for (AstStatementAlternative alt : stmtCase.getCases()) {
			if (doSwitch(alt)) {
				return true;
			}
		}
		
		return false;	
	}
	
	@Override 
	public Boolean caseAstStatementAlternative(AstStatementAlternative stmtAlt) {
		if (doSwitch(stmtAlt.getPattern())) {
			return true;
		}

		for (AstExpression guard : stmtAlt.getGuards()) {
			if (doSwitch(guard)) {
				return true;
			}
		}
		
		for (AstStatement stmt : stmtAlt.getStatements()) {
			if (doSwitch(stmt)) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override 
	public Boolean caseAstPattern(AstPattern pattern) {
		for (AstSubPattern subPattern : pattern.getSubpatterns()) {
			if (doSwitch(subPattern)) {
				return true;
			}
		}
		
		return false;		
	}
	
	@Override 
	public Boolean caseAstSubPattern(AstSubPattern pattern) {
		
		return false;		
	}
	
	@Override
	public Boolean caseAstType(AstType type) {
		if (visited.contains(type)) return true; else visited.add(type); 
		
		if (doSwitch(type.getName())) {
			return true;
		}			
		
		for (AstType t : type.getDomain()) {
			if (doSwitch(t))
				return true;
		}

		for (AstType t : type.getCodomain()) {
			if (doSwitch(t))
				return true;
		}
				
		if (type.getTypeParams() == null) 
			return false;

		for (AstTypeParam tp : type.getTypeParams().getParams()) {
			if (doSwitch(tp)) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public Boolean caseAstTypeUser(AstTypeUser typeUser) { 
		if (typeUser.getName() == null) 
			return true;
		
		if (typeUser.getTuples() != null) {
			for (AstTaggedTuple tt : typeUser.getTuples()) {			
				for (AstVariable tmd : tt.getFields()) {
					if (doSwitch(tmd.getType())) {
						return true;
					}
				}
			}
		} 
		
		return false;
	}
	
	@Override
	public Boolean caseAstTaggedTuple(AstTaggedTuple tuple) {
		for (AstVariable parameter : tuple.getFields()) {
			if (doSwitch(parameter)) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override 
	public Boolean caseAstTypeParam(AstTypeParam e) {
		if (e.getValue() != null) {
			if (doSwitch(e.getValue())) {
				return true;
			}
		} else if (e.getType() != null) {
			if (doSwitch(e.getType())) {
				return true;
			}				
		}
		return false;
	}

	@Override
	public Boolean caseAstVariable(AstVariable variable) {
		AstType type = variable.getType();
		if (doSwitch(type)) {
			return true;
		}

		return doSwitch(variable.getValue());
	}

	@Override
	public Boolean doSwitch(EObject theEObject) {
		if (theEObject == null) {
			return false;
		} else {
			return super.doSwitch(theEObject);
		}
	}

}
