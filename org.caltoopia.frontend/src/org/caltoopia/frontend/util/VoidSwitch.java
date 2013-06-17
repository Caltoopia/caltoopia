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

import org.eclipse.emf.ecore.EObject;
import org.caltoopia.frontend.cal.AstAbstractActor;
import org.caltoopia.frontend.cal.AstAction;
import org.caltoopia.frontend.cal.AstActor;
import org.caltoopia.frontend.cal.AstActorVariable;
import org.caltoopia.frontend.cal.AstActorVariableReference;
import org.caltoopia.frontend.cal.AstAssignParameter;
import org.caltoopia.frontend.cal.AstConnection;
import org.caltoopia.frontend.cal.AstConnectionAttribute;
import org.caltoopia.frontend.cal.AstEntity;
import org.caltoopia.frontend.cal.AstExpression;
import org.caltoopia.frontend.cal.AstExpressionBinary;
import org.caltoopia.frontend.cal.AstExpressionBoolean;
import org.caltoopia.frontend.cal.AstExpressionCall;
import org.caltoopia.frontend.cal.AstExpressionFloat;
import org.caltoopia.frontend.cal.AstExpressionIf;
import org.caltoopia.frontend.cal.AstExpressionInteger;
import org.caltoopia.frontend.cal.AstExpressionList;
import org.caltoopia.frontend.cal.AstExpressionString;
import org.caltoopia.frontend.cal.AstExpressionUnary;
import org.caltoopia.frontend.cal.AstExpressionVariable;
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
import org.caltoopia.frontend.cal.AstStatementBlock;
import org.caltoopia.frontend.cal.AstStatementCall;
import org.caltoopia.frontend.cal.AstStatementForeach;
import org.caltoopia.frontend.cal.AstStatementIf;
import org.caltoopia.frontend.cal.AstStatementWhile;
import org.caltoopia.frontend.cal.AstType;
import org.caltoopia.frontend.cal.AstTypeName;
import org.caltoopia.frontend.cal.AstTypeParam;
import org.caltoopia.frontend.cal.AstVariable;
import org.caltoopia.frontend.cal.AstVariableReference;
import org.caltoopia.frontend.cal.Import;
import org.caltoopia.frontend.cal.util.CalSwitch;

/**
 * This class defines a basic switch that visits everything. Case methods should
 * be overridden to implement the desired behavior.
 * 
 * @author Matthieu Wipliez
 * 
 */
public class VoidSwitch extends CalSwitch<Void> {

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

		for (AstEntity entity : namespace.getEntities()) {
			doSwitch(entity);
		}

		for (AstNamespace ns : namespace.getNamespaces()) {
			doSwitch(ns);
		}

		return null;
	}
	
	@Override
	public Void caseAstAction(AstAction action) {
		for (AstInputPattern input : action.getInputs()) {
			doSwitch(input);
		}

		for (AstExpression guard : action.getGuards()) {
			doSwitch(guard);
		}

		for (AstVariable variable : action.getVariables()) {
			doSwitch(variable);
		}

		for (AstStatement statement : action.getStatements()) {
			doSwitch(statement);
		}

		for (AstOutputPattern output : action.getOutputs()) {
			doSwitch(output);
		}

		return null;
	}

	@Override
	public Void caseAstActor(AstActor actor) {
		for (AstVariable parameter : actor.getParameters()) {
			doSwitch(parameter);
		}

		for (AstVariable stateVariable : actor.getStateVariables()) {
			doSwitch(stateVariable);
		}

		for (AstVariable function : actor.getFunctions()) {
			doSwitch(function);
		}

		for (AstProcedure procedure : actor.getProcedures()) {
			doSwitch(procedure);
		}

		for (AstPort port : actor.getInputs()) {
			doSwitch(port);
		}

		for (AstPort port : actor.getOutputs()) {
			doSwitch(port);
		}

		for (AstAction action : actor.getActions()) {
			doSwitch(action);
		}

		for (AstAction action : actor.getInitializes()) {
			doSwitch(action);
		}

		return null;
	}

	@Override
	public Void caseAstEntity(AstEntity entity) {
		AstAbstractActor actor = entity.getActor();
		if (actor instanceof AstActor || actor instanceof AstNetwork) {
			doSwitch(actor);
		} 
		
		return null;
	}
	
	@Override
	public Void caseAstNetwork(AstNetwork network) {
		for (AstVariable p: network.getParameters()) {
			doSwitch(p);
		}

		for (AstPort i : network.getInputs()) {
			doSwitch(i);
		}

		for (AstPort o : network.getOutputs()) {
			doSwitch(o);
		}

		for (AstVariable v : network.getVariables()) {
			doSwitch(v);
		}

		for (AstActorVariable a : network.getInstances()) {
			doSwitch(a);
		}
		
		for (AstConnection c : network.getStructure().getConnections()) {
			doSwitch(c);
		}
				
		return null;
	}

	@Override 
	public Void caseAstActorVariable(AstActorVariable a) {
		
		for (AstAssignParameter p : a.getParameters()) {
		 	doSwitch(p);
		}
		
		return null;
	}

	@Override 
	public Void caseAstActorVariableReference(AstActorVariableReference a) {
		return null;
	}
	
	
	@Override
	public Void caseAstConnection(AstConnection c) {
		doSwitch(c.getFrom());
		doSwitch(c.getTo());
		for (AstConnectionAttribute a : c.getAttribute()) {
			doSwitch(a);
		}
		
		return null;
	}
	
	@Override
	public Void caseAstConnectionAttribute(AstConnectionAttribute a) {
		doSwitch(a.getValue());
		return null;
	}
	
	@Override 
	public Void caseAstAssignParameter(AstAssignParameter p) {
		doSwitch(p.getValue());	
		return null;
	}
	
	@Override
	public Void caseAstExpressionBinary(AstExpressionBinary expression) {
		doSwitch(expression.getLeft());
		doSwitch(expression.getRight());

		return null;
	}

	@Override
	public Void caseAstExpressionBoolean(AstExpressionBoolean expression) {
		return null;
	}

	@Override
	public Void caseAstExpressionCall(AstExpressionCall call) {
		for (AstExpression parameter : call.getParameters()) {
			doSwitch(parameter);
		}

		return null;
	}

	@Override
	public Void caseAstExpressionFloat(AstExpressionFloat expression) {
		return null;
	}

	@Override
	public Void caseAstExpressionIf(AstExpressionIf expression) {
		doSwitch(expression.getCondition());
		doSwitch(expression.getThen());
		doSwitch(expression.getElse());

		return null;
	}

	@Override
	public Void caseAstExpressionInteger(AstExpressionInteger expression) {
		return null;
	}

	@Override
	public Void caseAstExpressionList(AstExpressionList expression) {
		for (AstGenerator generator : expression.getGenerators()) {
			doSwitch(generator);
		}

		for (AstExpression subExpression : expression.getExpressions()) {
			doSwitch(subExpression);
		}

		return null;
	}

	@Override
	public Void caseAstExpressionString(AstExpressionString expression) {
		return null;
	}

	@Override
	public Void caseAstExpressionUnary(AstExpressionUnary expression) {
		return doSwitch(expression.getExpression());
	}

	@Override
	public Void caseAstExpressionVariable(AstExpressionVariable expression) {
		for (AstMemberAccess mv: expression.getMember()) {
			doSwitch(mv);
		}

		for (AstExpression index: expression.getIndexes()) {
			doSwitch(index);
		}
		
		return doSwitch(expression.getValue());
	}

	@Override 
	public Void caseAstMemberAccess(AstMemberAccess mv) {
		for (AstExpression index: mv.getMemberIndex()) {
			doSwitch(index);
		}		
		return null;
	}
	
	@Override
	public Void caseAstFunction(AstFunction function) {
		doSwitch(function.getType());
		for (AstVariable parameter : function.getParameters()) {
			doSwitch(parameter);
		}
		
		for (AstVariable variable : function.getVariables()) {
			doSwitch(variable);
		}
		
		doSwitch(function.getExpression());

		for (AstVariable member : function.getMembers()) {
			doSwitch(member);
		}
		
		return null;
	}

	@Override
	public Void caseAstGenerator(AstGenerator generator) {
		doSwitch(generator.getVariable());
		doSwitch(generator.getExpression());
		return null;
	}

	@Override
	public Void caseAstInputPattern(AstInputPattern input) {
		doSwitch(input.getPort());

		for (AstVariable token : input.getTokens()) {
			doSwitch(token);
		}

		doSwitch(input.getRepeat());

		return null;
	}

	@Override
	public Void caseAstOutputPattern(AstOutputPattern output) {
		doSwitch(output.getPort());

		for (AstExpression value : output.getValues()) {
			doSwitch(value);
		}

		doSwitch(output.getRepeat());

		return null;
	}

	@Override
	public Void caseAstPort(AstPort port) {
		return doSwitch(port.getType());
	}

	@Override
	public Void caseAstProcedure(AstProcedure procedure) {
		for (AstVariable parameter : procedure.getParameters()) {
			doSwitch(parameter);
		}

		for (AstVariable variable : procedure.getVariables()) {
			doSwitch(variable);
		}

		for (AstStatement statement : procedure.getStatements()) {
			doSwitch(statement);
		}

		return null;
	}

	@Override
	public Void caseAstStatementAssign(AstStatementAssign assign) {
		for (AstExpression index : assign.getIndexes()) {
			doSwitch(index);
		}
		
		for (AstMemberAccess member : assign.getMember()) {
			doSwitch(member);
		}

		doSwitch(assign.getValue());

		return null;
	}

	@Override
	public Void caseAstStatementCall(AstStatementCall call) {
		for (AstExpression parameter : call.getParameters()) {
			doSwitch(parameter);
		}

		return null;
	}

	@Override
	public Void caseAstStatementForeach(AstStatementForeach foreach) {
		for (AstForeachGenerator g : foreach.getGenerators()) {
			doSwitch(g);
		}		
		
		if (foreach.getVariables() != null && !foreach.getVariables().isEmpty()) {
			for (AstVariable v : foreach.getVariables()) {
				doSwitch(v);
			}
		}
		
		for (AstStatement statement : foreach.getStatements()) {
			doSwitch(statement);
		}
		return null;
	}
	
	@Override 
	public Void caseAstForeachGenerator(AstForeachGenerator generator) {
		doSwitch(generator.getVariable());
		
		doSwitch(generator.getExpression());
		
		return null;
	}

	@Override
	public Void caseAstStatementIf(AstStatementIf stmtIf) {
		doSwitch(stmtIf.getCondition());

		for (AstStatement statement : stmtIf.getThen()) {
			doSwitch(statement);
		}

		for (AstStatement statement : stmtIf.getElse()) {
			doSwitch(statement);
		}

		return null;
	}

	@Override
	public Void caseAstStatementWhile(AstStatementWhile stmtWhile) {
		doSwitch(stmtWhile.getCondition());

		for (AstStatement statement : stmtWhile.getStatements()) {
			doSwitch(statement);
		}

		return null;
	}

	@Override
	public Void caseAstStatementBlock(AstStatementBlock stmtBlock) { 
		for (AstVariable v: stmtBlock.getVariables()) {
			doSwitch(v);
		}
		
		for (AstStatement s: stmtBlock.getStatements()) {
			doSwitch(s);
		}
		return null;	
	}
	
	@Override
	public Void caseAstType(AstType type) {
		doSwitch(type.getName());

		for(AstExpression e : type.getDimensions()) {
			doSwitch(e);
		}		
		
		for(AstType t : type.getDomain()) {
			doSwitch(t);
		}	
		
		for(AstType t : type.getCodomain()) {
			doSwitch(t);
		}		
		
		if (type.getTypeParams() != null) {
			for (AstTypeParam p: type.getTypeParams().getParams()) {
				doSwitch(p);
			}
		}
		return null;
	}

	@Override
	public Void caseAstTypeName(AstTypeName typeName) { 
		if (typeName.getConstructor() != null) {
			for (AstFunction tc : typeName.getConstructor()) {			
				for (AstVariable tmd : tc.getMembers()) {
					doSwitch(tmd.getType());
				}
			}
		} 
		
		return null;
	}
	
	@Override 
	public Void caseAstTypeParam(AstTypeParam e) {
		if (e.getValue() != null) {
			doSwitch(e.getValue());
		} else if (e.getType() != null) {
			doSwitch(e.getType());
		}
		
		return null;
	}

	@Override
	public Void caseAstVariable(AstVariable variable) {
		doSwitch(variable.getType());
		return doSwitch(variable.getValue());
	}

	@Override
	public Void caseAstVariableReference(AstVariableReference reference) {
		return null;
	}

	@Override
	public Void doSwitch(EObject theEObject) {
		if (theEObject == null) {
			return null;
		} else {
			return super.doSwitch(theEObject);
		}
	}

}
