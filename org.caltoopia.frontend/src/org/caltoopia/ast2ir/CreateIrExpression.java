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

import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.caltoopia.frontend.cal.AstAction;
import org.caltoopia.frontend.cal.AstActor;
import org.caltoopia.frontend.cal.AstExpression;
import org.caltoopia.frontend.cal.AstExpressionAlternative;
import org.caltoopia.frontend.cal.AstExpressionBinary;
import org.caltoopia.frontend.cal.AstExpressionBoolean;
import org.caltoopia.frontend.cal.AstExpressionCase;
import org.caltoopia.frontend.cal.AstExpressionFloat;
import org.caltoopia.frontend.cal.AstExpressionIf;
import org.caltoopia.frontend.cal.AstExpressionInteger;
import org.caltoopia.frontend.cal.AstExpressionList;
import org.caltoopia.frontend.cal.AstExpressionString;
import org.caltoopia.frontend.cal.AstExpressionSymbolReference;
import org.caltoopia.frontend.cal.AstExpressionUnary;
import org.caltoopia.frontend.cal.AstFunction;
import org.caltoopia.frontend.cal.AstGenerator;
import org.caltoopia.frontend.cal.AstInputPattern;
import org.caltoopia.frontend.cal.AstMemberAccess;
import org.caltoopia.frontend.cal.AstPort;
import org.caltoopia.frontend.cal.AstType;
import org.caltoopia.frontend.cal.AstTypeUser;
import org.caltoopia.frontend.cal.AstVariable;
import org.caltoopia.frontend.cal.util.CalSwitch;
import org.caltoopia.ir.BinaryExpression;
import org.caltoopia.ir.CaseExpression;
import org.caltoopia.ir.ExprAlternative;
import org.caltoopia.ir.Guard;
import org.caltoopia.ir.IfExpression;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.FunctionCall;
import org.caltoopia.ir.Generator;
import org.caltoopia.ir.IrFactory;
import org.caltoopia.ir.ListExpression;
import org.caltoopia.ir.Member;
import org.caltoopia.ir.Scope;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeConstructorCall;
import org.caltoopia.ir.TypeLambda;
import org.caltoopia.ir.TypeUser;
import org.caltoopia.ir.UnaryExpression;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.types.TypeConverter;

public class CreateIrExpression extends CalSwitch<Expression> {
	
	public final static Expression NotEvaluatedExpression = IrFactory.eINSTANCE.createExpression();
	
	private Scope currentScope = null; 

	private static Expression value = null;
		
	public CreateIrExpression(Scope outer) {
		this.currentScope = outer;		
	}
	
	public void run(AstExpression e) {
		value = doSwitch(e);
	}
	
	public static Expression convert(Scope scope, AstExpression expr) {
		if (expr != null) {
			CreateIrExpression ce = new CreateIrExpression(scope);
			ce.run(expr);
			return value;
		} else {
			return null;
		}
	}
	
	@Override	
	public Expression caseAstExpressionInteger(AstExpressionInteger e) {
		return Util.createIntegerLiteral(e);
	}
	
	@Override 
	public Expression caseAstExpressionFloat(AstExpressionFloat e) {				
		return Util.createFloatLiteral(e);
	}
	
	@Override
	public Expression caseAstExpressionBoolean(AstExpressionBoolean e) {		
		return Util.createBooleanLiteral(e);
	}

	@Override
	public Expression caseAstExpressionString(AstExpressionString e) {				
		return Util.createStringLiteral(e);
	}
		
	@Override
	public Expression caseAstExpressionList(AstExpressionList e) {				
		ListExpression result = IrFactory.eINSTANCE.createListExpression();
		result.setId(Util.getDefinitionId());
		result.setContext(currentScope);
		
		Scope initialScope = currentScope;
		
		if (!e.getGenerators().isEmpty()) {	
			List<AstGenerator> gs = e.getGenerators();
			
			for (int i = gs.size() - 1; i >= 0; i--) {
				Generator generator = IrFactory.eINSTANCE.createGenerator();
				generator.setId(Util.getDefinitionId());
				
				result.getGenerators().add(generator);
				generator.setOuter(currentScope);

				currentScope = generator;
				
				AstGenerator astGenerator = gs.get(i);
				Util.createVariable(currentScope, astGenerator.getVariable(), false);
				generator.setSource(doSwitch(gs.get(i).getExpression()));
			}	
		} 

		for (AstExpression astExpr : e.getExpressions()) {
			Expression expr = convert(currentScope, astExpr);
			result.getExpressions().add(expr);
		}	
		result.setId(Util.getDefinitionId());

		currentScope = initialScope;
		return result;
							
	}		

	@Override
	public Expression caseAstExpressionBinary(AstExpressionBinary e) {		
		Expression e1 = doSwitch(e.getLeft());
		Expression e2 = doSwitch(e.getRight());		
		BinaryExpression result = IrFactory.eINSTANCE.createBinaryExpression();
		result.setId(Util.getDefinitionId());
		result.setContext(currentScope);
		
		result.setOperand1(e1);
		result.setOperand2(e2);
		result.setOperator(e.getOperator());
		
		return result;
	}

	@Override
	public Expression caseAstExpressionSymbolReference(AstExpressionSymbolReference e) {
		if(e.getSymbol() instanceof AstFunction) {
			Declaration funDecl = (Declaration) Util.findIrDeclaration(e.getSymbol());
			
			FunctionCall result = IrFactory.eINSTANCE.createFunctionCall();
			result.setId(Util.getDefinitionId());
			result.setContext(currentScope);
				
			VariableExpression funExpr = IrFactory.eINSTANCE.createVariableExpression();
			funExpr.setId(Util.getDefinitionId());
			funExpr.setContext(currentScope);
			
			funExpr.setVariable(funDecl);
			result.setFunction(funExpr);
			
			for (AstExpression p : e.getParameters()) {
				result.getParameters().add(doSwitch(p));
			}
			
			return result;
		} else if (e.getSymbol() instanceof AstTypeUser){
			TypeConstructorCall result = IrFactory.eINSTANCE.createTypeConstructorCall();
			result.setId(Util.getDefinitionId());
			result.setContext(currentScope);
			
			result.setName(e.getCtor());
			AstTypeUser astTypedef = (AstTypeUser) e.getSymbol();
			Declaration typedef = Util.findIrDeclaration(astTypedef);
			result.setTypedef(typedef);			
			
			for (AstExpression p : e.getParameters()) {
				result.getParameters().add(doSwitch(p));
			}

			return result;			
		} else {
			VariableExpression result = IrFactory.eINSTANCE.createVariableExpression();
			result.setId(Util.getDefinitionId());
			result.setContext(currentScope);
			
			Declaration decl = Util.findIrDeclaration(e.getSymbol());
			result.setVariable(decl);
				
			for (AstExpression i : e.getIndexes()) {
				Expression ve = convert(currentScope, i);
				result.getIndex().add(ve);
			}
			
			for (AstMemberAccess mv : e.getMember()) {
				Member m = Util.createFieldAccess(mv, currentScope);
				result.getMember().add(m);
			}
						
			return result;			
		}
	}
		
	@Override
	public Expression caseAstExpressionUnary(AstExpressionUnary e) {
		UnaryExpression result = IrFactory.eINSTANCE.createUnaryExpression();
		result.setId(Util.getDefinitionId());
		result.setContext(currentScope);
		
		result.setOperator(e.getUnaryOperator());
		result.setOperand(doSwitch(e.getExpression()));
		
		return result;
	}

	@Override 
	public Expression caseAstExpressionIf(AstExpressionIf e) {
		IfExpression result = IrFactory.eINSTANCE.createIfExpression();
		result.setId(Util.getDefinitionId());		
		result.setContext(currentScope);

		result.setCondition(CreateIrExpression.convert(currentScope, e.getCondition()));
		result.setThenExpression(CreateIrExpression.convert(currentScope, e.getThen()));
		result.setElseExpression(CreateIrExpression.convert(currentScope, e.getElse()));
				
		return result;
	}
	
	@Override
	public Expression caseAstExpressionCase(AstExpressionCase e) {
		CaseExpression result = IrFactory.eINSTANCE.createCaseExpression();
		result.setId(Util.getDefinitionId());
		Expression condition = CreateIrExpression.convert(currentScope, e.getExpression());
		result.setExpression(condition);
		result.setContext(currentScope);
		
		try {
			AstVariable v = e.getExpression().getSymbol();
			AstType astType = TypeConverter.getAstTypeOfAstVariable(v);
			
			for (AstExpressionAlternative a : e.getCases()) {
				ExprAlternative alt = IrFactory.eINSTANCE.createExprAlternative();
				alt.setId(Util.getDefinitionId());
				alt.setOuter(currentScope);
			
				Util.doPatternDeclarations(alt, astType, a.getPattern(), condition, null);				
                if (a.getPattern() != null)
                    alt.getGuards().add(Util.doPatternTypeGuards(currentScope, a.getPattern(), condition, astType));
				
				for (AstExpression guard : a.getGuards()) {
					alt.getGuards().add(CreateIrExpression.convert(alt, guard));
				}
				
				alt.setExpression(CreateIrExpression.convert(currentScope, a.getExpression()));
				
				result.getAlternatives().add(alt);
			}
			
			if (e.getDefault() != null) {
				result.setDefault(CreateIrExpression.convert(currentScope, e.getDefault()));
			}
		} catch (Exception x) {
			System.err.println("Internal error in Ast2Ir");
		}
				
		return result;
	}
	
	@Override
	public Expression doSwitch(EObject o) {
		if (o != null) {
			return super.doSwitch(o);
		} else {
			System.err.println("CreateExpression: null valued object");
			return null;
		}
	}
	
}