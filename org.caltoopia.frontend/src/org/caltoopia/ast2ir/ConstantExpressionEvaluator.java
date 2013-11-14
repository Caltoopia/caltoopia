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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.cli.DirectoryException;
import org.caltoopia.codegen.UtilIR;
import org.caltoopia.ir.AbstractActor;
import org.caltoopia.ir.Action;
import org.caltoopia.ir.Assign;
import org.caltoopia.ir.Block;
import org.caltoopia.ir.BooleanLiteral;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.BinaryExpression;
import org.caltoopia.ir.FloatLiteral;
import org.caltoopia.ir.ForEach;
import org.caltoopia.ir.ForwardDeclaration;
import org.caltoopia.ir.FunctionCall;
import org.caltoopia.ir.Generator;
import org.caltoopia.ir.IfExpression;
import org.caltoopia.ir.IntegerLiteral;
import org.caltoopia.ir.LambdaExpression;
import org.caltoopia.ir.ListExpression;
import org.caltoopia.ir.LiteralExpression;
import org.caltoopia.ir.Member;
import org.caltoopia.ir.Namespace;
import org.caltoopia.ir.Network;
import org.caltoopia.ir.PortWrite;
import org.caltoopia.ir.ProcExpression;
import org.caltoopia.ir.Schedule;
import org.caltoopia.ir.Scope;
import org.caltoopia.ir.Statement;
import org.caltoopia.ir.StringLiteral;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeConstructorCall;
import org.caltoopia.ir.TypeDeclaration;
import org.caltoopia.ir.TypeDeclarationImport;
import org.caltoopia.ir.TypeRecord;
import org.caltoopia.ir.UnaryExpression;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.ir.VariableExternal;
import org.caltoopia.ir.VariableImport;
import org.caltoopia.ir.VariableReference;
import org.caltoopia.ir.util.IrReplaceSwitch;
import org.caltoopia.ir.IrFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class ConstantExpressionEvaluator extends IrReplaceSwitch {
	
	private Expression returnExpression;
	
	class Stack {
		
		private Stack outer;
				
		private List<Declaration> variables = new ArrayList<Declaration>();

		List<TypeDeclaration> types = new ArrayList<TypeDeclaration>();
		
		public Stack(Stack outer) {
			this.outer = outer;			
		}
		
		public Declaration find(String id) {
			for (Declaration var : variables) {
				if (var.getId().equals(id))
					return var;				
			}
			
			if (outer != null) {
				return outer.find(id);
			} else if (environment != null) {
				for (Declaration decl : environment.getDeclarations()) {
					if (decl.getId().equals(id)) {
						return decl;
					}
				}
			}
			print();	
			throw new RuntimeException("[ConstantExpressionEvaluator] Missing variable '" + id + "'");
		}
		
		String indent = "";
		
		public void print() {			
			if (outer == null) {
				indent = "";
				System.out.println("== Stack Top ==");
				for (Declaration v : variables) {
					System.out.println(v.getName() + " [id=" + v.getId() + "]");
				}
				
			} else {
				outer.print();
				indent = indent + " ";
				System.out.println("-------------------------");
				for (Declaration v : variables) {
					System.out.println(indent + v.getName() + " [id=" + v.getId() + "]");
				}	
				System.out.println("=========================");
			}
			
		}
		
	}
	
	private Stack stack = null;
	
	private static Scope environment;
					
	static public AbstractActor evaluate(AbstractActor abstractActor, Scope env) {
		ConstantExpressionEvaluator evaluator = new ConstantExpressionEvaluator(abstractActor);
		
		environment = env;
		
		final Set<String> modified = new HashSet<String>();
		new IrReplaceSwitch() {
			@Override 
			public Statement caseAssign(Assign assign) {
				modified.add(assign.getTarget().getDeclaration().getId());
				return assign;
			}
		}.doSwitch(abstractActor);
				
		new IrReplaceSwitch() {
			@Override 
			public Variable caseVariable(Variable variable) {
				super.caseVariable(variable);
				if (!modified.contains(variable.getId()))
					variable.setConstant(true);
				
				return variable;
			}
		}.doSwitch(abstractActor);
		
		evaluator.doSwitch(abstractActor);
		
		return abstractActor;
	}
	
	private ConstantExpressionEvaluator(Scope scope) {
		stack = new Stack(null);
	}
		
	@Override
	public Namespace caseNamespace(Namespace namespace) {
		stack = new Stack(stack);
		Namespace result = super.caseNamespace(namespace);
		stack = stack.outer;
		return result;
	}		

	@Override
	public Network caseNetwork(Network network) {
		stack = new Stack(stack);
		Network result = (Network) super.caseNetwork(network);
		stack = stack.outer;
		return result;
	}
	
	@Override
	public Action caseAction(Action action) {
		stack = new Stack(stack);
		caseScope(action);
		Action result = super.caseAction(action);		
		stack = stack.outer;
		return result;
	}	
	
	@Override
	public Block caseBlock(Block block) {
		stack = new Stack(stack);
		caseScope(block);
		
		//Visit all the statements
		List<Statement> stmts = block.getStatements();
		for (int i = 0; i < stmts.size(); i++) {
			doSwitch(stmts.get(i));
			// stmts.set(i, stmt);
		}
		
		stack = stack.outer;
		return block;
	}	
	
	@Override 
	public ForEach caseForEach(ForEach forEach) {
		Stack top = stack;
		
		List<Generator> generators = forEach.getGenerators();
		for (int i = 0; i < generators.size(); i++) {
			Generator generator = generators.get(i);
			stack = new Stack(stack);
			caseScope(generator);
			
			Generator g = IrFactory.eINSTANCE.createGenerator();
			g.setOuter(generator.getOuter());
			g.setId(generator.getId());
			g.getDeclarations().addAll(generator.getDeclarations());
			
			Expression source = (Expression) doSwitch(generator.getSource());
			g.setSource(source);
			
			generators.set(i, g);
		}
		
		doSwitch(forEach.getBody());

		stack = top;
		return forEach;
	}

	@Override
	public PortWrite casePortWrite(PortWrite portWrite) {
		stack = new Stack(stack);
		caseScope(portWrite);
		casePortAccess(portWrite);
		
		//Visit all the statements
		List<Statement> stmts = portWrite.getStatements();
		for (int i = 0; i < stmts.size(); i++) {
			doSwitch(stmts.get(i));
			// stmts.set(i, stmt);
		}
		
		//Visit all output expression
		List<Expression> expressions = portWrite.getExpressions();
		for (int i = 0; i < expressions.size(); i++) {
	       Expression e = (Expression) doSwitch(expressions.get(i));
		   expressions.set(i, e);
		}
				
		stack = stack.outer;
		return portWrite;
	}
	
	@Override
	public Declaration caseVariable(Variable var) {
		Variable result;
		if (var.getScope() instanceof Namespace || UtilIR.fromNamespace(var)) {
			result = var; 
		} else {
		    result = IrFactory.eINSTANCE.createVariable();	
			result.setId(var.getId());
			result.setName(var.getName());
			result.setConstant(var.isConstant());
			result.setParameter(var.isParameter());
			result.setScope(var.getScope());
			result.getAnnotations().addAll(var.getAnnotations());
		}
		Type type = (Type) doSwitch(var.getType());
		result.setType(type);
		if (var.getInitValue() != null) {
			Expression value = (Expression) doSwitch(var.getInitValue());
			result.setInitValue(value);
		}
		stack.variables.add(result);
		
		return result;		
	}
	
	@Override
	public Declaration caseVariableImport(VariableImport var) {
		stack.variables.add(var);
		
		return var;		
	}
	
	@Override
	public Declaration caseVariableExternal(VariableExternal var) {
		stack.variables.add(var);
		
		return var;		
	}
	
	@Override 
	public Expression caseIfExpression(IfExpression expression) {
		Expression condition = (Expression) doSwitch(expression.getCondition());
		if (condition instanceof BooleanLiteral) {
			if (( (BooleanLiteral) condition).isValue()) {
				return (Expression) doSwitch(expression.getThenExpression());
			} else {
				return (Expression) doSwitch(expression.getElseExpression());
			}
		} else {
			return expression;
		}
	}
	
	@Override
	public Expression caseBinaryExpression(BinaryExpression expression) {
		String op = expression.getOperator();
		Expression val1 = (Expression) doSwitch(expression.getOperand1());
		Expression val2 = (Expression) doSwitch(expression.getOperand2());
				
		if (val1 instanceof IntegerLiteral && val2 instanceof IntegerLiteral) {
			long v1 = ((IntegerLiteral) val1).getValue();
			long v2 = ((IntegerLiteral) val2).getValue();				
			
			if (op.equals("+")) {
				IntegerLiteral result = IrFactory.eINSTANCE.createIntegerLiteral();
				result.setValue(v1 + v2);
				return result;	
			} else if (op.equals("-")) {
				IntegerLiteral result = IrFactory.eINSTANCE.createIntegerLiteral();
				result.setValue(v1 - v2);
				return result;	
			} else if (op.equals("*")) {
				IntegerLiteral result = IrFactory.eINSTANCE.createIntegerLiteral();
				result.setValue(v1 * v2);
				return result;	
			} else if (op.equals("/")) {
				IntegerLiteral result = IrFactory.eINSTANCE.createIntegerLiteral();
				result.setValue(v1 / v2);
				return result;	
			} else if (op.equals("div")) {
				IntegerLiteral result = IrFactory.eINSTANCE.createIntegerLiteral();
				result.setValue((int) v1 / v2);
				return result;	
			} else if (op.equals("mod")) {
				IntegerLiteral result = IrFactory.eINSTANCE.createIntegerLiteral();
				result.setValue(v1 % v2);
				return result;	
			} else if (op.equals("&")) {
				BigInteger bint1 = BigInteger.valueOf(v1);
				BigInteger bint2 = BigInteger.valueOf(v2);
				IntegerLiteral result = IrFactory.eINSTANCE.createIntegerLiteral();
				result.setValue(bint1.and(bint2).longValue());
				return result;
			}  else if (op.equals("|")) {
				BigInteger bint1 = BigInteger.valueOf(v1);
				BigInteger bint2 = BigInteger.valueOf(v2);
				IntegerLiteral result = IrFactory.eINSTANCE.createIntegerLiteral();
				result.setValue(bint1.or(bint2).longValue());					
				return result;
			} else if (op.equals("^")) {
				BigInteger bint1 = BigInteger.valueOf(v1);
				BigInteger bint2 = BigInteger.valueOf(v2);
				IntegerLiteral result = IrFactory.eINSTANCE.createIntegerLiteral();
				result.setValue(bint1.xor(bint2).longValue());					
				return result;
			} else if (op.equals("<<")) {
				BigInteger bint1 = BigInteger.valueOf(v1);
				BigInteger bint2 = BigInteger.valueOf(v2);
				IntegerLiteral result = IrFactory.eINSTANCE.createIntegerLiteral();
				result.setValue(bint1.shiftLeft(bint2.intValue()).longValue());					
				return result;					
			} else if (op.equals(">>")) {
				BigInteger bint1 = BigInteger.valueOf(v1);
				BigInteger bint2 = BigInteger.valueOf(v2);	
				IntegerLiteral result = IrFactory.eINSTANCE.createIntegerLiteral();
				result.setValue(bint1.shiftRight(bint2.intValue()).longValue());					
				return result;					
			} else if (op.equals(">>>")) {
				assert(false); //FIXME what the semantics of urshift
				
				BigInteger bint1 = BigInteger.valueOf(v1);
				BigInteger bint2 = BigInteger.valueOf(v2);	
				IntegerLiteral result = IrFactory.eINSTANCE.createIntegerLiteral();
				result.setValue(bint1.shiftRight(bint2.intValue()).longValue());					
				return result;					
			} else if (op.equals("<")) {
				BooleanLiteral result = IrFactory.eINSTANCE.createBooleanLiteral();
				result.setValue(v1 < v2);
				return result;
			} else if (op.equals("<=")) {
				BooleanLiteral result = IrFactory.eINSTANCE.createBooleanLiteral();
				result.setValue(v1 <= v2);
				return result;
			} else if (op.equals(">=")) {
				BooleanLiteral result = IrFactory.eINSTANCE.createBooleanLiteral();
				result.setValue(v1 >= v2);
				return result;
			} else if (op.equals(">")) {
				BooleanLiteral result = IrFactory.eINSTANCE.createBooleanLiteral();
				result.setValue(v1 > v2);
				return result;
			} else if (op.equals("=")) {
				BooleanLiteral result = IrFactory.eINSTANCE.createBooleanLiteral();
				result.setValue(v1 == v2);
				return result;
			} 
				
			
		} else if (val1 instanceof FloatLiteral && val2 instanceof FloatLiteral) {
			double v1 = ((FloatLiteral) val1).getValue();
			double v2 = ((FloatLiteral) val2).getValue();				

			if (op.equals("+")) {
				FloatLiteral result = IrFactory.eINSTANCE.createFloatLiteral();
				result.setValue(v1 + v2);
				return result;
			} else if (op.equals("-")) {
				FloatLiteral result = IrFactory.eINSTANCE.createFloatLiteral();
				result.setValue(v1 - v2);
				return result;
			} else if (op.equals("*")) {
				FloatLiteral result = IrFactory.eINSTANCE.createFloatLiteral();
				result.setValue(v1 * v2);
				return result;
			} else if (op.equals("/")) {
				FloatLiteral result = IrFactory.eINSTANCE.createFloatLiteral();
				result.setValue(v1 / v2);
				return result;
			}		
					
		} else if (val1 instanceof FloatLiteral && val2 instanceof IntegerLiteral) {
			double v1 = ((FloatLiteral) val1).getValue();
			double v2 = ((IntegerLiteral) val2).getValue();				
			
			if (op.equals("+")) {
				FloatLiteral result = IrFactory.eINSTANCE.createFloatLiteral();
				result.setValue(v1 + v2);
				return result;
			} if (op.equals("-")) {
				FloatLiteral result = IrFactory.eINSTANCE.createFloatLiteral();
				result.setValue(v1 + v2);
				return result;
			} else if (op.equals("*")) {
				FloatLiteral result = IrFactory.eINSTANCE.createFloatLiteral();
				result.setValue(v1 * v2);
				return result;
			} else if (op.equals("/")) {
				FloatLiteral result = IrFactory.eINSTANCE.createFloatLiteral();
				result.setValue(v1 / v2);
				return result;
			} 		
			
		} else if (val1 instanceof IntegerLiteral && val2 instanceof FloatLiteral) {
			double v1 = ((IntegerLiteral) val1).getValue();
			double v2 = ((FloatLiteral) val2).getValue();				

			if (op.equals("+")) {
				FloatLiteral result = IrFactory.eINSTANCE.createFloatLiteral();
				result.setValue(v1 + v2);
				return result;
			} else if (op.equals("-")) {
				FloatLiteral result = IrFactory.eINSTANCE.createFloatLiteral();
				result.setValue(v1 - v2);
				return result;
			} else if (op.equals("*")) {
				FloatLiteral result = IrFactory.eINSTANCE.createFloatLiteral();
				result.setValue(v1 * v2);
				return result;
			} else if (op.equals("/")) {
				FloatLiteral result = IrFactory.eINSTANCE.createFloatLiteral();
				result.setValue(v1 / v2);
				return result;
			} 

		} else if (val1 instanceof BooleanLiteral && val2 instanceof BooleanLiteral) {
			boolean v1 = ((BooleanLiteral) val1).isValue();
			boolean v2 = ((BooleanLiteral) val2).isValue();	
			
			if (op.equals("and")) {
				BooleanLiteral result = IrFactory.eINSTANCE.createBooleanLiteral();
				result.setValue(v1 && v2);
				return result;
			} else if (op.equals("or")) {
				BooleanLiteral result = IrFactory.eINSTANCE.createBooleanLiteral();
				result.setValue(v1 || v2);
				return result;
			} 
		} else if (val1 instanceof StringLiteral && val2 instanceof StringLiteral) {
			String v1 = ((StringLiteral) val1).getValue();
			String v2 = ((StringLiteral) val2).getValue();	
			
			if (op.equals("+")) {
				StringLiteral result = IrFactory.eINSTANCE.createStringLiteral();
				result.setValue(v1 + v2);
				return result;
			} 
		}
		
		BinaryExpression result = IrFactory.eINSTANCE.createBinaryExpression();
		result.setId(Util.getDefinitionId());
		result.setContext(expression.getContext());
		result.setOperand1(val1);
		result.setOperator(expression.getOperator());
		result.setOperand2(val2);

		return result;
	}

	@Override
	public EObject caseLambdaExpression(LambdaExpression lambda) {
		Type t = (Type) doSwitch(lambda.getType());
		lambda.setType(t);
		
		stack = new Stack(stack);
		
		for (int i = 0; i < lambda.getParameters().size(); i++) {
			Variable param = (Variable) caseVariable(lambda.getParameters().get(i));
			lambda.getParameters().set(i, param);
		}
		
		for (Declaration decl : lambda.getDeclarations()) {
			doSwitch(decl);
		}
		
		Expression body = (Expression) doSwitch(lambda.getBody());
		lambda.setBody(body);
		
		stack = stack.outer;
		
		return lambda;
	}
	
	@Override
	public EObject caseProcExpression(ProcExpression proc) {
		stack = new Stack(stack);
		
		for (int i = 0; i < proc.getParameters().size(); i++) {
			Variable param = (Variable) caseVariable(proc.getParameters().get(i));
			proc.getParameters().set(i, param);
		}
		
		for (Declaration decl : proc.getDeclarations()) {
			doSwitch(decl);
		}
		
		Block body = (Block) doSwitch(proc.getBody());
		proc.setBody(body);
		
		stack = stack.outer;
		
		return proc;
	}
	
	@Override
	public Expression caseFunctionCall(FunctionCall call) {
		Expression result;
		LambdaExpression lambda;
		List<Expression> parameters = new ArrayList<Expression>();	
						
		for (int i = 0; i < call.getParameters().size(); i++) {
			Expression param = (Expression) doSwitch(call.getParameters().get(i));
			if (param instanceof LiteralExpression) {
				parameters.add(param);
			} else {
				// If not all parameters are constants we give up
				// and just returns the expression
				return call; 
			}
		} 
		
		if (call.getFunction() instanceof LambdaExpression) {
			lambda = (LambdaExpression) call.getFunction();
		} else {
			Declaration decl = ((VariableExpression) call.getFunction()).getVariable();
			if (decl instanceof ForwardDeclaration) { 
				Variable var = (Variable) ((ForwardDeclaration) decl).getDeclaration();
				lambda = (LambdaExpression) var.getInitValue();				
			} else if (decl instanceof VariableImport){
				try {
					Declaration d = ActorDirectory.findVariable((VariableImport) decl, true);
					if(d instanceof VariableExternal) {
						return call;
					}
					Variable var = (Variable) d;
					lambda = (LambdaExpression) var.getInitValue();
				} catch (DirectoryException x) {
					return call;
				}				
			} else if (decl instanceof VariableExternal)  {
				return call;	
			} else {
				Variable var = (Variable) decl;
				lambda = (LambdaExpression) var.getInitValue();
			}
		}
		
		stack = new Stack(stack);
		
		for (int i = 0; i < lambda.getParameters().size(); i++) {
			Variable param = IrFactory.eINSTANCE.createVariable();	
			param.setId(lambda.getParameters().get(i).getId());
			param.setName(lambda.getParameters().get(i).getName());
			param.setConstant(true);
	
			param.setInitValue(parameters.get(i));	
			stack.variables.add(param);
		}
		
		for (Declaration decl : lambda.getDeclarations()) {
			doSwitch(decl);
		}
		
		/*
		 * Need to set the calling scope when lifting out the expression in lambda.
		 * But lambda is a shared object for the declaration so make sure to
		 * restore it in the declaration. Can't just set the scope after due to
		 * it need to propagate to inner expressions, e.g. nested function calls
		 * that are eliminated.
		 * FIXME this is a race condition when used concurrently
		 */
		Scope storeScope = lambda.getBody().getContext();
		lambda.getBody().setContext(call.getContext());
		Expression returnExpression = (Expression) doSwitch(lambda.getBody());
		lambda.getBody().setContext(storeScope);
		
		result = returnExpression;
		
	    stack = stack.outer;
	    
	    return result;	    
	}
	
	
//	@Override
//	public ProcCall caseProcCall(ProcCall call) {
//		ProcExpression proc;
//		List<Expression> parameters = new ArrayList<Expression>();	
//						
//		for (int i = 0; i < call.getInParameters().size(); i++) {
//			Expression param = (Expression) doSwitch(call.getInParameters().get(i));
//			if (param instanceof LiteralExpression) {
//				parameters.add(param);
//			} else {
//				// If not all parameters are constants we give up
//				// and just returns the expression
//				return call; 
//			}
//		} 
//		
//		Declaration decl = call.getProcedure();
//		if (decl instanceof ForwardDeclaration) { 
//			Variable var = (Variable) ((ForwardDeclaration) decl).getDeclaration();
//			proc = (ProcExpression) var.getInitValue();				
//		} else if (decl instanceof VariableImport){
//			try {
//				Declaration d = ActorDirectory.findVariable((VariableImport) decl);
//				if(d instanceof VariableExternal) {
//					return call;
//				}
//				Variable var = (Variable) d;
//				proc = (ProcExpression) var.getInitValue();
//			} catch (DirectoryException x) {
//				return call;
//			}				
//		} else if (decl instanceof VariableExternal)  {
//			return call;	
//		} else {
//			Variable var = (Variable) decl;
//			proc = (ProcExpression) var.getInitValue();
//		}
//		
//		context = new Context(context);
//		
//		for (int i = 0; i < proc.getParameters().size(); i++) {
//			Variable param = IrFactory.eINSTANCE.createVariable();	
//			param.setId(proc.getParameters().get(i).getId());
//			param.setName(proc.getParameters().get(i).getName());
//			param.setConstant(true);
//	
//			param.setInitValue(parameters.get(i));	
//			context.variables.add(param);
//		}
//		
//		for (Declaration declaration : proc.getDeclarations()) {
//			doSwitch(declaration);
//		}
//		
//		doSwitch(proc.getBody());
//				
//	    context = context.outer;
//	    
//	    return call;	    
//	}
	
	@Override
	public Expression caseListExpression(ListExpression expression) {
		ListExpression result = IrFactory.eINSTANCE.createListExpression();
		
		if (expression.getGenerators().isEmpty()) {
			for (Expression element : expression.getExpressions()) {
				result.getExpressions().add((Expression) doSwitch(element));
			}
		} else {
			Stack top = stack;
			for (Generator generator : expression.getGenerators()) {

				stack = new Stack(stack);
				caseScope(generator);
				
				Generator g = IrFactory.eINSTANCE.createGenerator();
				g.setOuter(generator.getOuter());
				g.setId(generator.getId());
				g.getDeclarations().addAll(generator.getDeclarations());
				
				Expression source = (Expression) doSwitch(generator.getSource());
				g.setSource(source);

				result.getGenerators().add(g);						
			}
			
			for (Expression e : expression.getExpressions()) {
				result.getExpressions().add((Expression) doSwitch(e));
			}
			
			stack = top;
		}
	
		//a unique ID since might be different
		result.setId(Util.getDefinitionId());
		result.setContext(expression.getContext());

		return result;
	}
				
	@Override
	public Expression caseUnaryExpression(UnaryExpression expression) {
		String op = expression.getOperator();		
		if (op.equals("~")) {
			Expression value = (Expression) doSwitch(expression.getOperand());
			if (value instanceof IntegerLiteral) {
				BigInteger i = new BigInteger(((IntegerLiteral) value).toString());
				IntegerLiteral result = IrFactory.eINSTANCE.createIntegerLiteral();
				result.setValue(i.not().longValue());
				return result;
			}

			// error("bitnot expects an integer expression", expression, CalPackage.AST_EXPRESSION_UNARY__UNARY_OPERATOR);
			// return null;
		} else if (op.equals("!")) {
			Expression value = (Expression) doSwitch(expression.getOperand());
			if (value instanceof BooleanLiteral) {
				BooleanLiteral result = IrFactory.eINSTANCE.createBooleanLiteral();
				result.setValue(!((BooleanLiteral) value).isValue());
				return result;
			}

			// error("not expects a boolean expression", expression, CalPackage.AST_EXPRESSION_UNARY__UNARY_OPERATOR);
			// return null;
		} else if (op.equals("-")) {
			Expression value = (Expression) doSwitch(expression.getOperand());
			if (value instanceof IntegerLiteral) {				
				BigInteger i = BigInteger.valueOf(((IntegerLiteral) value).getValue());
				IntegerLiteral result = IrFactory.eINSTANCE.createIntegerLiteral();
				result.setValue(i.negate().longValue());
				return result;
			} else if (value instanceof FloatLiteral) {
				BigDecimal d = BigDecimal.valueOf(((FloatLiteral) value).getValue());
				FloatLiteral result = IrFactory.eINSTANCE.createFloatLiteral();
				result.setValue(d.negate().doubleValue());
				return result;
			}

			// error("minus expects an number expression", expression, CalPackage.AST_EXPRESSION_UNARY__UNARY_OPERATOR);
			// return null;
		} else if (op.equals("#")) {
			/*
			TypeChecker typeChecker = new TypeChecker(validator);
			Type type = typeChecker.getType(expression.getExpression());
			if (type != null && type.isList()) {
				return new IntExpr(((TypeList) type).getSize());
			}
             */
			error("operator # expects a list expression");
			return null;
		}

		return expression;
	}	
	
	@Override
	public Expression caseVariableExpression(VariableExpression expression) {
		Declaration decl = stack.find(expression.getVariable().getId());
		Variable variable;
		
		if (decl instanceof VariableExternal) {
			return expression;
		} else if (decl instanceof VariableImport) {
			try { 
				Declaration d = ActorDirectory.findVariable((VariableImport) expression.getVariable(), true);
				if (d instanceof Variable) {
					Variable var = (Variable) d;
					if (var.getInitValue() instanceof LiteralExpression) {
						return EcoreUtil.copy(var.getInitValue());
					}
				}
			} catch (DirectoryException x) {
				error("[ConstantExpressionEvaluator] Internal Error#1 in caseVariableExpression");
			}
			return expression;
		} 
		
		variable = (Variable) decl;
		
		List<Expression> indices = new ArrayList<Expression>();
		boolean constantIndices = true;
		
		for (Expression index : expression.getIndex()) {
			Expression i = (Expression) doSwitch(index);
			indices.add(i);
			constantIndices = constantIndices && (i instanceof IntegerLiteral);
		}		
		
		if (hasConstantValue(variable) && constantIndices ) {
			Expression result = (Expression) doSwitch(variable.getInitValue());;

			for (Expression index : indices) {
				assert(result instanceof ListExpression); //FIXME: Do some proper error handling godammit					
				result = (Expression) doSwitch( ((ListExpression) result).getExpressions().get((int) ((IntegerLiteral) index).getValue() ));
			}
			
			if (expression.getMember().size() > 0) {
				for (Member member : expression.getMember()) {	
					assert(result instanceof TypeConstructorCall);
					TypeDeclaration typedecl;
					
					if (((TypeConstructorCall) result).getTypedef() instanceof TypeDeclaration ) {
						typedecl = (TypeDeclaration) ((TypeConstructorCall) result).getTypedef();
					} else {
						try {
							typedecl = ActorDirectory.findTypeDeclaration((TypeDeclarationImport) ((TypeConstructorCall) result).getTypedef(),true);
						} catch (DirectoryException x) {
							error("[ConstantExpressionEvaluator] Internal Error#2 in caseVariableExpression");
							return expression;
						}
					}	
					TypeRecord typeRecord = (TypeRecord) typedecl.getType();
					
					Expression value = null;
					for (Variable v : typeRecord.getMembers()) {
						if (v.getName().equals(member.getName())) {
							value = ((TypeConstructorCall) result).getParameters().get(typeRecord.getMembers().indexOf(v));
						}
					}
					assert(value != null);
					result = (Expression) doSwitch(value);
						
					indices = new ArrayList<Expression>();
					constantIndices = true;
						
					for (Expression index : member.getIndex()) {
						Expression i = (Expression) doSwitch(index);
						indices.add(i);
						constantIndices = constantIndices && (i instanceof IntegerLiteral);
					}
					if (constantIndices) {
						for (Expression index : indices) {
							assert(result instanceof ListExpression); //FIXME: Do some proper error handling godammit					
							result = (Expression) doSwitch( ((ListExpression) result).getExpressions().get((int) ((IntegerLiteral) index).getValue() ));
						}
					}
					assert(value != null);
					result = (Expression) doSwitch(value);
				}
			}
			if (result instanceof LiteralExpression) {
				return EcoreUtil.copy(result);
			} 
		} 
		
		return expression;
	}
		
	@Override
	public VariableReference caseVariableReference(VariableReference var) {
		
		//Visit the index expressions
		List<Expression> indexes = var.getIndex();
		for (int i = 0; i < indexes.size(); i++) {
			Expression index = (Expression) doSwitch(indexes.get(i));
			indexes.set(i, index);
		}
		
		//Visit the members
		List<Member> members = var.getMember();
		for (int i = 0; i < members.size(); i++) {
			Member member = (Member) doSwitch(members.get(i));
			members.set(i, member);
		}
				
		return var;
	}
	
	public Schedule caseSchedule(Schedule schedule) {
		Schedule result = IrFactory.eINSTANCE.createSchedule();
		
		return result;
	}
		
	private boolean hasConstantValue(Variable variable) {
		if (variable.isConstant() && variable.getInitValue() != null) {
			Expression value = variable.getInitValue();
			if (value instanceof LiteralExpression) 
				return true;
		}
		
		return false;
	}

	private void error(String string) {		
		System.err.println("[ConstantExpressionEvaluator] Error: " + string);
	}

}
