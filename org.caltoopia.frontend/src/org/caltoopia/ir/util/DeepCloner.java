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
package org.caltoopia.ir.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.eclipse.emf.ecore.EObject;
import org.caltoopia.ast2ir.PriorityGraph;
import org.caltoopia.ast2ir.PriorityGraph.Edge;
import org.caltoopia.ast2ir.PriorityGraph.Vertex;
import org.caltoopia.ast2ir.Util;
import org.caltoopia.codegen.IrCompactPrinter;
import org.caltoopia.ir.AbstractActor;
import org.caltoopia.ir.Action;
import org.caltoopia.ir.Actor;
import org.caltoopia.ir.ActorInstance;
import org.caltoopia.ir.Annotation;
import org.caltoopia.ir.AnnotationArgument;
import org.caltoopia.ir.Assign;
import org.caltoopia.ir.BinaryExpression;
import org.caltoopia.ir.Block;
import org.caltoopia.ir.BooleanLiteral;
import org.caltoopia.ir.Connection;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.ExternalActor;
import org.caltoopia.ir.FloatLiteral;
import org.caltoopia.ir.ForwardDeclaration;
import org.caltoopia.ir.FromSource;
import org.caltoopia.ir.FunctionCall;
import org.caltoopia.ir.Generator;
import org.caltoopia.ir.Guard;
import org.caltoopia.ir.IfExpression;
import org.caltoopia.ir.IfStatement;
import org.caltoopia.ir.IntegerLiteral;
import org.caltoopia.ir.IrFactory;
import org.caltoopia.ir.LambdaExpression;
import org.caltoopia.ir.ListExpression;
import org.caltoopia.ir.Member;
import org.caltoopia.ir.Network;
import org.caltoopia.ir.Point2PointConnection;
import org.caltoopia.ir.Port;
import org.caltoopia.ir.PortAccess;
import org.caltoopia.ir.PortInstance;
import org.caltoopia.ir.PortPeek;
import org.caltoopia.ir.PortRead;
import org.caltoopia.ir.PortWrite;
import org.caltoopia.ir.ProcCall;
import org.caltoopia.ir.ProcExpression;
import org.caltoopia.ir.ReturnValue;
import org.caltoopia.ir.Schedule;
import org.caltoopia.ir.Scope;
import org.caltoopia.ir.State;
import org.caltoopia.ir.Statement;
import org.caltoopia.ir.StringLiteral;
import org.caltoopia.ir.TaggedExpression;
import org.caltoopia.ir.ToSink;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.TypeBool;
import org.caltoopia.ir.TypeConstructor;
import org.caltoopia.ir.TypeConstructorCall;
import org.caltoopia.ir.TypeDeclaration;
import org.caltoopia.ir.TypeDeclarationImport;
import org.caltoopia.ir.TypeFloat;
import org.caltoopia.ir.TypeInt;
import org.caltoopia.ir.TypeLambda;
import org.caltoopia.ir.TypeList;
import org.caltoopia.ir.TypeString;
import org.caltoopia.ir.TypeRecord;
import org.caltoopia.ir.TypeUint;
import org.caltoopia.ir.TypeUndef;
import org.caltoopia.ir.TypeUser;
import org.caltoopia.ir.UnaryExpression;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.ir.VariableExternal;
import org.caltoopia.ir.VariableImport;
import org.caltoopia.ir.VariableReference;
import org.caltoopia.ir.WhileLoop;

@Deprecated
public class DeepCloner extends IrSwitch<EObject> {

	static Map<EObject, EObject> mapOfClones = null;
	
	static public AbstractActor clone(AbstractActor actor) {
		if (actor instanceof Actor) {
			return clone((Actor) actor);
		} else if (actor instanceof Network){
			return clone((Network) actor);
		} else {
			assert(actor instanceof ExternalActor);
			// External actor has nothing to clone...
			return actor;
		}
	}
	
	static public Actor clone(Actor actor) {
		DeepCloner cloner = new DeepCloner();
		
		scopeStack = new Stack<Scope>();
		mapOfClones = new HashMap<EObject, EObject>();
		
		Actor clone = (Actor) cloner.doSwitch(actor);
		
		new IrReplaceSwitch() {
			Set<ForwardDeclaration> patched = new HashSet<ForwardDeclaration>();
			
			@Override
			public ForwardDeclaration caseForwardDeclaration(ForwardDeclaration decl) { 
				if (!patched.contains(decl)) {
					Declaration clonedDeclaration = findClonedDeclaration(decl.getDeclaration());
					decl.setDeclaration(clonedDeclaration);
					patched.add(decl);
				}
				return decl;
			}
		}.doSwitch(clone);
				
		return clone;
	}
	
	static public Network clone(Network network) {
		DeepCloner cloner = new DeepCloner();
		
		scopeStack = new Stack<Scope>();
		mapOfClones = new HashMap<EObject, EObject>();
				
		scopeStack.push(network.getOuter()); // Set initial scope
		
		Network clone = (Network) cloner.doSwitch(network);
		
		return clone;
	}
	
	
	static Stack<Scope> scopeStack = null;
	
	private void startScope(Scope clone, Scope scope) {
		clone.setOuter(currentScope());
		scopeStack.push(clone);

		// Clone all definitions 
		for (Declaration d : scope.getDeclarations()) {
			Declaration decl = (Declaration) doSwitch(d);
			clone.getDeclarations().add(decl);
		}

		// Clone all annotations 
		for (Annotation a : scope.getAnnotations()) {
			Annotation annotation = (Annotation) doSwitch(a);
			clone.getAnnotations().add(annotation);
		}
	}
	
	private void endScope() {
		scopeStack.pop();
	}
		
	private Scope currentScope() {
		if (scopeStack.isEmpty()) {
			return null;
		} else {
			return scopeStack.peek();
		}
	}
		
	private static void addClonedDeclaration(Declaration orignal, Declaration clone) {
		mapOfClones.put(orignal, clone);
	}
	
	private static Declaration findClonedDeclaration(Declaration original) {
		EObject clone = mapOfClones.get(original);
		if (clone == null || ! (clone instanceof Declaration) ) {
			throw new RuntimeException("Internal error in DeepClone");
		}
		return (Declaration) clone;
	}
	
	@Override
	public Actor caseActor(Actor actor) {
		Actor clone = IrFactory.eINSTANCE.createActor();		
		startScope(clone, actor);
						
		// Clone the actor type
		clone.setType((TypeActor) doSwitch(actor.getType()));
		
		// Clone the input ports
		for (Port p : actor.getInputPorts()) {
			Port port = casePort(p);
			clone.getInputPorts().add(port);
		}
				
		// Clone the output ports
		for (Port p : actor.getOutputPorts()) {
			Port port = casePort(p);
			clone.getOutputPorts().add(port);
		}
		
		// Clone the parameters
		for (Variable p : actor.getParameters()) {
			Variable param = (Variable) doSwitch(p);
			clone.getParameters().add(param);
		} 
		
		// Clone init actions
		for (Action a : actor.getInitializers()) {
			Action action = caseAction(a);
			clone.getInitializers().add(action);
		}				
		
		// Clone all the actions
		for (Action a : actor.getActions()) {
			Action action = caseAction(a);
			clone.getActions().add(action);
		}
		
		// Copy the schedule since it will never be affected by 
		// constant value propagation
		clone.setSchedule(caseSchedule(actor.getSchedule()));
		endScope();
		
		return clone;
	}


	@Override
	public Action caseAction(Action action) {	
		Action clone = IrFactory.eINSTANCE.createAction();
		mapOfClones.put(action, clone);
		startScope(clone, action);
		
		clone.setId(action.getId());
		
		for (String s : action.getTag())
			clone.getTag().add(s);
		
		//Visit the input tokens
		for (PortRead r : action.getInputs()) {
			PortRead read = casePortRead(r);
			clone.getInputs().add(read);
		}		
		
		//Visit the guards
		for (Guard g : action.getGuards()) {
			Guard guard = caseGuard(g);
			clone.getGuards().add(guard);
		}
		
		//Visit the output expression
		for (PortWrite w : action.getOutputs()) {
			PortWrite write = casePortWrite(w);
			clone.getOutputs().add(write);
		}
		
		//Visit the statements
		for (Statement s : action.getStatements()) {
			Statement stmt = (Statement) doSwitch(s);
			clone.getStatements().add(stmt);
		}
		
		endScope();

		return clone;
	}

	@Override
	public Port casePort(Port port) {
		Port clone = IrFactory.eINSTANCE.createPort();
		mapOfClones.put(port, clone);
		
		clone.setName(port.getName());
		
		Type t = (Type) doSwitch(port.getType());
		clone.setType(t);
		
		return clone;
	}

	@Override
	public TaggedExpression caseTaggedExpression(TaggedExpression taggedExpression) {
		TaggedExpression clone = IrFactory.eINSTANCE.createTaggedExpression();
		
		Expression e = (Expression) doSwitch(taggedExpression.getExpression());
		clone.setExpression(e);
		
		return clone;
	}
	
	public void copyExpression(Expression target, Expression source) {
		if (source.getType() != null) {
			Type t = (Type) doSwitch(source.getType());
			target.setType(t);
		}
		// target.setContext((Scope) findClonedScope(source.getContext())); FIXME
		target.setId(source.getId());
	}
	
	@Override
	public Expression caseIntegerLiteral(IntegerLiteral literal) {
		IntegerLiteral clone = IrFactory.eINSTANCE.createIntegerLiteral();
		copyExpression(clone, literal);

		clone.setValue(literal.getValue());
		
		return clone;
	}

	@Override
	public Expression caseFloatLiteral(FloatLiteral literal) {
		FloatLiteral clone = IrFactory.eINSTANCE.createFloatLiteral();
		copyExpression(clone, literal);
	
		clone.setValue(literal.getValue());

		return clone;
	}

	@Override
	public Expression caseBooleanLiteral(BooleanLiteral literal) {
		BooleanLiteral clone = IrFactory.eINSTANCE.createBooleanLiteral();
		copyExpression(clone, literal);

		clone.setValue(literal.isValue());

		return clone;
	}

	@Override
	public Expression caseStringLiteral(StringLiteral literal) {
		StringLiteral clone = IrFactory.eINSTANCE.createStringLiteral();
		copyExpression(clone, literal);

		clone.setValue(literal.getValue());

		return clone;
	}

	@Override
	public Expression caseVariableExpression(VariableExpression var) {
		VariableExpression clone = IrFactory.eINSTANCE.createVariableExpression();		
		copyExpression(clone, var);
		
		// Find the cloned variable definition
		Declaration decl = findClonedDeclaration(var.getVariable());
		clone.setVariable(decl);
		
		// Clone the index expressions
		for (Expression e : var.getIndex()) {
			Expression index = (Expression) doSwitch(e);
			clone.getIndex().add(index);
		}
		
		// Clone the members
		for (Member m : var.getMember()) {
			Member member = (Member) doSwitch(m);
			clone.getMember().add(member);
		}	
		
		return clone;
	}

	@Override
	public EObject caseLambdaExpression(LambdaExpression lambda) {		
		LambdaExpression clone = IrFactory.eINSTANCE.createLambdaExpression();	
		startScope(clone, lambda);		
		copyExpression(clone, lambda);
		
		for (Variable p : lambda.getParameters()) {
			Variable param = (Variable) caseVariable(p);
			clone.getParameters().add(param);
		}
		
		Expression body = (Expression) doSwitch(lambda.getBody());
		clone.setBody(body);

		endScope();
		return clone;
	}
	
	@Override
	public EObject caseProcExpression(ProcExpression proc) {		
		ProcExpression clone = IrFactory.eINSTANCE.createProcExpression();		
		startScope(clone, proc);		
		copyExpression(clone, proc);

		
		for (Variable p : proc.getParameters()) {
			Variable param = (Variable) caseVariable(p);
			clone.getParameters().add(param);
		}
		
		Block body = (Block) doSwitch(proc.getBody());
		clone.setBody(body);
		
		endScope();
		return clone;
	}
	
	@Override
	public EObject caseIfExpression(IfExpression expr) {
		IfExpression clone = IrFactory.eINSTANCE.createIfExpression();
		copyExpression(clone, expr);
		
		Expression conditionExpr = (Expression) doSwitch(expr.getCondition());
		clone.setCondition(conditionExpr);

		Expression thenExpr = (Expression) doSwitch(expr.getThenExpression());
		clone.setThenExpression(thenExpr);

		Expression elseExpr = (Expression) doSwitch(expr.getElseExpression());
		clone.setElseExpression(elseExpr);
		
		return clone;
	}
	
	@Override
	public EObject caseListExpression(ListExpression listExpr) {
		ListExpression clone = IrFactory.eINSTANCE.createListExpression();
		copyExpression(clone, listExpr);
		
		for (Generator g : listExpr.getGenerators()) {
			Generator gen = (Generator) doSwitch(g);
			clone.getGenerators().add(gen);
		}
		
		for (Expression e : listExpr.getExpressions()) {
			Expression expr =  (Expression) doSwitch(e);
			clone.getExpressions().add(expr);
		}

		return clone;
	}
	
	@Override
	public EObject caseGenerator(Generator generator) {
		Generator clone = IrFactory.eINSTANCE.createGenerator();
		
		startScope(clone, generator);		
		
		Expression expr = (Expression) doSwitch(generator.getSource());		
		clone.setSource(expr);
		
		return clone;
	}
	
	@Override
	public EObject caseMember(Member member) {
		Member clone = IrFactory.eINSTANCE.createMember();
				
		clone.setName(member.getName());
		
		//Clone the type
		if (member.getType() != null) {
			Type t = (Type) doSwitch(member.getType());
			clone.setType(t);
		}
		
		//Visit the index expressions
		for (Expression e : member.getIndex()) {
			Expression index = (Expression) doSwitch(e);
			clone.getIndex().add(index);
		}
		
		return clone;
	}
	
	@Override
	public Expression caseBinaryExpression(BinaryExpression binaryExpression) {
		BinaryExpression clone = IrFactory.eINSTANCE.createBinaryExpression();		
		copyExpression(clone, binaryExpression);
		
		clone.setOperator(binaryExpression.getOperator());
		
		Expression e1 = (Expression) doSwitch(binaryExpression.getOperand1());
		clone.setOperand1(e1);
		
		Expression e2 = (Expression) doSwitch(binaryExpression.getOperand2());
		clone.setOperand2(e2);

		return clone;
	}

	@Override
	public Expression caseUnaryExpression(UnaryExpression unaryExpression) {
		UnaryExpression clone = IrFactory.eINSTANCE.createUnaryExpression();
		copyExpression(clone, unaryExpression);
			
		clone.setOperator(unaryExpression.getOperator());
		
		Expression e = (Expression) doSwitch(unaryExpression.getOperand());
		clone.setOperand(e);
		
		return clone;
	}

	@Override
	public Expression caseFunctionCall(FunctionCall call) {
		FunctionCall clone = IrFactory.eINSTANCE.createFunctionCall();
		copyExpression(clone, call);
		
		//Clone the function def
		Expression expr = (Expression) doSwitch(call.getFunction());
		clone.setFunction(expr);		
		
		//Clone the parameters
		for (Expression p : call.getParameters()) {
			Expression param = (Expression) doSwitch(p);
			clone.getParameters().add(param);
		}
		
		return clone;
	}

	@Override
	public Expression caseTypeConstructorCall(TypeConstructorCall call) {
		TypeConstructorCall clone = IrFactory.eINSTANCE.createTypeConstructorCall();
		copyExpression(clone, call);
		
		Declaration typedef = findClonedDeclaration(call.getTypedef());
		clone.setTypedef(typedef);	
		
		clone.setName(call.getName());
		
		for (Expression p : call.getParameters()) {
			Expression param = (Expression) doSwitch(p);
			clone.getParameters().add(param);
		}
		
		return clone;
	}
	

	@Override
	public VariableReference caseVariableReference(VariableReference var) {
		VariableReference clone = IrFactory.eINSTANCE.createVariableReference();
		
		// Find the variable definition
		Variable decl = (Variable) findClonedDeclaration(var.getDeclaration());
		clone.setDeclaration(decl);
		
		// Clone the index expressions
		for (Expression i : var.getIndex()) {
			Expression index = (Expression) doSwitch(i);
			clone.getIndex().add(index);
		}
		
		// Clone the members
		for (Member m : var.getMember()) {
			Member member = (Member) doSwitch(m);
			clone.getMember().add(member);
		}
				
		return clone;
	}

	@Override
	public Statement caseAssign(Assign assign) {
		Assign clone = IrFactory.eINSTANCE.createAssign();
		
		VariableReference target = caseVariableReference(assign.getTarget());
		clone.setTarget(target);

		Expression value = (Expression) doSwitch(assign.getExpression());
		clone.setExpression(value);
		
		return clone;
	}

	@Override
	public Statement caseReturnValue(ReturnValue returnValue) {
		assert(false);
		return returnValue;
	}

	@Override
	public Statement caseProcCall(ProcCall call) {	
		ProcCall clone = IrFactory.eINSTANCE.createProcCall();
		
		// Clone the proc. def
		Declaration proc = findClonedDeclaration(call.getProcedure());
		clone.setProcedure(proc);
		
		// Clone the parameter expressions
		for (Expression p : call.getInParameters()) {
			Expression param = (Expression) doSwitch(p);
			clone.getInParameters().add(param);
		}

		for (VariableReference p : call.getOutParameters()) {
			VariableReference param = (VariableReference) doSwitch(p);
			clone.getOutParameters().add(param);
		}

		return clone;
	}

	@Override
	public Statement caseWhileLoop(WhileLoop whileLoop) {
		WhileLoop clone = IrFactory.eINSTANCE.createWhileLoop();
				
		Expression condition = (Expression) doSwitch(whileLoop.getCondition());
		clone.setCondition(condition);
		
		Block body = (Block) doSwitch(whileLoop.getBody());
		clone.setBody(body);
		
		return clone;
	}

	@Override
	public Statement caseIfStatement(IfStatement ifStatement) {
		IfStatement clone = IrFactory.eINSTANCE.createIfStatement();
		
		Expression condition = (Expression) doSwitch(ifStatement.getCondition());
		clone.setCondition(condition);
		
		Block thenBody = (Block) doSwitch(ifStatement.getThenBlock());
		clone.setThenBlock(thenBody);

		if (ifStatement.getElseBlock() != null) {
			Block elseBody = (Block) doSwitch(ifStatement.getElseBlock());
			clone.setElseBlock(elseBody);
		}

		return clone;
	}

	@Override
	public Statement caseBlock(Block block) {
		Block clone = IrFactory.eINSTANCE.createBlock();
		startScope(clone, block);
		
		// Clone all the statements
		for (Statement s : block.getStatements()) {
			Statement stmt = (Statement) doSwitch(s);
			clone.getStatements().add(stmt);
		}
		endScope();
		
		return clone;
	}

	public void doPortAccess(PortAccess clone, PortAccess portAccess) {
		// Clone the port
		Port port = casePort(portAccess.getPort());		
		clone.setPort(port);
		
		// Clone the rate expression
		if (portAccess.getRepeat() != null) {
			Expression repeat = (Expression) doSwitch(portAccess.getRepeat());
			clone.setRepeat(repeat);
		}
		
	}
	
	@Override
	public PortWrite casePortWrite(PortWrite portWrite) {
		PortWrite clone = IrFactory.eINSTANCE.createPortWrite();
		startScope(clone, portWrite);
		doPortAccess(clone, portWrite);
				
		// Clone the statements
		for (Statement s : portWrite.getStatements()) {
	        Statement stmt = (Statement) doSwitch(s);
			clone.getStatements().add(stmt);
		}		
		
		// Clone output expression
		for (Expression e : portWrite.getExpressions()) {
	        Expression expr = (Expression) doSwitch(e);
			clone.getExpressions().add(expr);
		}
		
		endScope();		
		return clone;
	}

	@Override
	public PortRead casePortRead(PortRead portRead) {
		PortRead clone = IrFactory.eINSTANCE.createPortRead();
		doPortAccess(clone, portRead);
		
		for (VariableReference v : portRead.getVariables()) {
			VariableReference var = caseVariableReference(v);
			clone.getVariables().add(var);
		}
		
		return clone;
	}

	@Override
	public PortPeek casePortPeek(PortPeek portPeek) {
		PortPeek clone = IrFactory.eINSTANCE.createPortPeek();
		doPortAccess(clone, portPeek);
		
		VariableReference var = caseVariableReference(portPeek.getVariable());
		clone.setVariable(var);
		clone.setPosition(portPeek.getPosition());
		
		return clone;
	}

	@Override
	public Declaration caseVariable(Variable variable) {
		Variable clone = IrFactory.eINSTANCE.createVariable();
		clone.setScope(currentScope());
	
		clone.setId(variable.getId());
		clone.setName(variable.getName());
		
		clone.setConstant(variable.isConstant());
		clone.setParameter(variable.isParameter());
		
		if (variable.getType() != null) {
			Type t = (Type) doSwitch(variable.getType());
			clone.setType(t);
		}
		
		if (variable.getInitValue() != null) {
			Expression initValue = (Expression) doSwitch(variable.getInitValue());
			clone.setInitValue(initValue);
		}
		
		addClonedDeclaration(variable, clone);
		
		return clone;
	}

	@Override 
	public VariableImport caseVariableImport(VariableImport imp) {
		VariableImport clone = IrFactory.eINSTANCE.createVariableImport();
		clone.setScope(currentScope());
		
		clone.setId(imp.getId());
		clone.setName(imp.getName());
		
		for (String s : imp.getNamespace())
			clone.getNamespace().add(s);
		
		addClonedDeclaration(imp, clone);
		
		return clone;
	}
	
	@Override 
	public VariableExternal caseVariableExternal(VariableExternal ext) {
		VariableExternal clone = IrFactory.eINSTANCE.createVariableExternal();
		clone.setScope(currentScope());
		
		clone.setId(ext.getId());
		clone.setName(ext.getName());
		Type t = (Type) doSwitch(ext.getType());
		clone.setType(t);
		
		addClonedDeclaration(ext, clone);
		
		return clone;
	}
	
	@Override 
	public Declaration caseForwardDeclaration(ForwardDeclaration decl) {
		ForwardDeclaration clone = IrFactory.eINSTANCE.createForwardDeclaration();
		clone.setScope(currentScope());
		
		clone.setId(decl.getId());
		clone.setName(decl.getName());
		
		Type t = (Type) doSwitch(decl.getType());
		clone.setType(t);

		// NB: The clone declaration referenced must be set later
		// and now we only set the original to be used as a 'key'
		// in a later stage
		clone.setDeclaration(decl.getDeclaration());
		
		addClonedDeclaration(decl, clone);
		
		return clone;		
	}
	
	@Override
	public Declaration caseTypeDeclaration(TypeDeclaration typeDecl) {
		TypeDeclaration clone = IrFactory.eINSTANCE.createTypeDeclaration();
		clone.setScope(currentScope());
		
		Type type = (Type) doSwitch(typeDecl.getType());
		clone.setType(type);
		
		TypeConstructor ctor = (TypeConstructor) doSwitch(typeDecl.getConstructor());
		clone.setConstructor(ctor);
		
		addClonedDeclaration(typeDecl, clone);
		
		return clone;
	}

	@Override 
	public Declaration caseTypeDeclarationImport(TypeDeclarationImport imp) {
		TypeDeclarationImport clone = IrFactory.eINSTANCE.createTypeDeclarationImport();
		clone.setScope(currentScope());
		
		clone.setId(imp.getId());
		clone.setName(imp.getName());
		
		for (String s : imp.getNamespace())
			clone.getNamespace().add(s);

		
		addClonedDeclaration(imp, clone);
		
		return clone;
	}
	
	@Override
	public Guard caseGuard(Guard guard) {
		Guard clone = IrFactory.eINSTANCE.createGuard();
		copyExpression(clone, guard);
		startScope(clone, guard);

		// Clone output expression
		for (PortPeek p : guard.getPeeks()) {
	        PortPeek peek = (PortPeek) casePortPeek(p);
			clone.getPeeks().add(peek);
		}
		
		Expression body = (Expression) doSwitch(guard.getBody());
		clone.setBody(body);

		endScope();

		return clone;
	}

	@Override
	public Type caseTypeBool(TypeBool type) {
		TypeBool clone = IrFactory.eINSTANCE.createTypeBool();
		return clone;
	}

	@Override
	public Type caseTypeInt(TypeInt type) {
		TypeInt clone = IrFactory.eINSTANCE.createTypeInt();
		if (type.getSize() != null) {
			Expression size = (Expression) doSwitch(type.getSize());
			clone.setSize(size);
		}
		
		return clone;
	}

	@Override
	public Type caseTypeList(TypeList type) {
		TypeList clone = IrFactory.eINSTANCE.createTypeList();		
		
		Expression size = (Expression) doSwitch(type.getSize());
		clone.setSize(size);
		
		Type t = (Type) doSwitch(type.getType());
		clone.setType(t);
		
		return clone;
	}

	@Override
	public Type caseTypeFloat(TypeFloat type) {
		TypeFloat clone = IrFactory.eINSTANCE.createTypeFloat();
		
		return clone;
	}

	@Override
	public Type caseTypeUint(TypeUint type) {
		TypeUint clone = IrFactory.eINSTANCE.createTypeUint();
		
		if (type.getSize() != null) {
			Expression size = (Expression) doSwitch(type.getSize());
			clone.setSize(size);
		}

		return clone;
	}

	@Override
	public Type caseTypeString(TypeString type) {
		TypeString clone = IrFactory.eINSTANCE.createTypeString();
		
		return clone;
	}

	@Override
	public Type caseTypeRecord(TypeRecord type) {
		TypeRecord clone = IrFactory.eINSTANCE.createTypeRecord();
		
		for (Variable member : type.getMembers()) {	
			Variable m = IrFactory.eINSTANCE.createVariable();
			m.setName(member.getName());
			Type t = (Type) doSwitch(member.getType());			
			m.setType(t);
			
			clone.getMembers().add(m);
		}
		
		return clone;
	}

	@Override
	public Type caseTypeLambda(TypeLambda type) {
		TypeLambda clone = IrFactory.eINSTANCE.createTypeLambda();
		
		for (Type t : type.getInputTypes()) {	
			Type inputType = (Type) doSwitch(t);			
			clone.getInputTypes().add(inputType);
		}
		
		if (type.getOutputType() != null) {
			Type t = (Type) doSwitch(type.getOutputType());			
			clone.setOutputType(t);
		}
		
		return clone;
	}
	
	@Override
	public Type caseTypeUndef(TypeUndef type) {
		return type;
	}
	
	@Override
	public Type caseTypeUser(TypeUser type) {
		TypeUser clone = IrFactory.eINSTANCE.createTypeUser();

		Declaration decl = findClonedDeclaration(type.getDeclaration());
		clone.setDeclaration(decl);
		
		return clone;
	}
	
	@Override
	public Type caseTypeActor(TypeActor type) {
		TypeActor clone = IrFactory.eINSTANCE.createTypeActor();

		clone.setName(type.getName());
		clone.getNamespace().addAll(type.getNamespace());
		
		return clone;
	}

	@Override
	public TypeConstructor caseTypeConstructor(TypeConstructor ctor) {
		TypeConstructor clone = IrFactory.eINSTANCE.createTypeConstructor();
		clone.setName(ctor.getName());
		clone.setId(ctor.getId());
	    
		Declaration decl = findClonedDeclaration(ctor.getTypedef());
		clone.setTypedef((TypeDeclaration) decl);
		
		for (Variable arg : ctor.getParameters()) {
			clone.getParameters().add((Variable) doSwitch(arg));
		}
				
		return clone;
	}
	
	@Override 
	public Network caseNetwork(Network network) {
		Network clone = IrFactory.eINSTANCE.createNetwork();
		startScope(clone, network);
		
		// Clone the network type
		clone.setType((TypeActor) doSwitch(network.getType()));
		
		// Clone the parameters
		for (Variable p : network.getParameters()) {
			Variable param = (Variable) doSwitch(p);
			clone.getParameters().add(param);
		} 
		
		// Clone the input ports
		for (Port p : network.getInputPorts()) {
			Port port = casePort(p);
			clone.getInputPorts().add(port);
		}
				
		// Clone the output ports
		for (Port p : network.getOutputPorts()) {
			Port port = casePort(p);
			clone.getOutputPorts().add(port);
		}		
		
		// Clone the actor instances
		for (ActorInstance i : network.getActors()) {
			ActorInstance instance = caseActorInstance(i);
			clone.getActors().add(instance);
			instance.setScope(clone);
		}
		
		// Clone the connections
		for (Connection c : network.getConnections()) {
			Connection connection = (Connection) doSwitch(c);
			clone.getConnections().add(connection); 
		}
		
		endScope();
		return clone;		
	}
	
	@Override
	public ActorInstance caseActorInstance(ActorInstance instance) {
		ActorInstance clone = IrFactory.eINSTANCE.createActorInstance();		
		mapOfClones.put(instance, clone);
		
		clone.setType((TypeActor) doSwitch(instance.getType()));
		clone.setId(Util.getDefinitionId());
		clone.setName(instance.getName());
		
		for (TaggedExpression te : instance.getActualParameters()) {
			TaggedExpression taggedExpression = IrFactory.eINSTANCE.createTaggedExpression();
			taggedExpression.setTag(te.getTag());
			taggedExpression.setExpression((Expression) doSwitch(te.getExpression()));
			clone.getActualParameters().add(taggedExpression);
		}
		
		// Clone the input ports
		for (PortInstance pi : instance.getInputs()) {
			PortInstance portInstance = casePortInstance(pi);
			portInstance.setActor(clone);
			clone.getInputs().add(portInstance);
		}
				
		// Clone the output ports
		for (PortInstance pi : instance.getOutputs()) {
			PortInstance portInstance = casePortInstance(pi);
			portInstance.setActor(clone);
			clone.getOutputs().add(portInstance);
		}		
		 
		return clone;
	}

	@Override 
	public PortInstance casePortInstance(PortInstance portInstance) {
		PortInstance clone = IrFactory.eINSTANCE.createPortInstance();
		mapOfClones.put(portInstance, clone);

		clone.setActor((ActorInstance) mapOfClones.get(portInstance.getActor()));
		clone.setName(portInstance.getName());
		
		return clone;		
	}
	
	@Override 
	public Connection caseFromSource(FromSource fromSource) {
		FromSource clone = IrFactory.eINSTANCE.createFromSource();
		Port source = (Port) mapOfClones.get(fromSource.getSource());
		assert(source != null);
		clone.setSource(source);
		
		PortInstance target = (PortInstance) mapOfClones.get(fromSource.getTarget());
		assert(target != null);
		clone.setTarget(target);
		target.getConnections().add(clone);
		
		return clone;
	}	
	
	@Override 
	public Connection caseToSink(ToSink toSink) {
		ToSink clone = IrFactory.eINSTANCE.createToSink();

		PortInstance source = (PortInstance) mapOfClones.get(toSink.getSource());
		assert(source != null);
		clone.setSource(source);
		source.getConnections().add(clone);
		
		Port sink = (Port) mapOfClones.get(toSink.getSink());
		assert(sink != null);
		clone.setSink(sink);
		
		return clone;
	}	

	@Override 
	public Connection casePoint2PointConnection(Point2PointConnection connection) {
		Point2PointConnection clone = IrFactory.eINSTANCE.createPoint2PointConnection();
		
		PortInstance source = (PortInstance) mapOfClones.get(connection.getSource());
		assert(source != null);
		clone.setSource(source);
		source.getConnections().add(clone);
		
		PortInstance target = (PortInstance) mapOfClones.get(connection.getTarget());
		assert(target != null);
		clone.setTarget(target);
		target.getConnections().add(clone);
		
		return clone;
	}	

	@Override 
	public Schedule caseSchedule(Schedule schedule) {
		Schedule result = IrFactory.eINSTANCE.createSchedule();

		// First clone all the states
		for (State state : schedule.getStates()) {
			State clone = IrFactory.eINSTANCE.createState();
			clone.setName(state.getName());
			mapOfClones.put(state, clone);
			result.getStates().add(clone);
		}

		// Then clone the actual schedule
		result.setInitialState((State) mapOfClones.get(schedule.getInitialState())); 
		
		for (Action action : schedule.getFreeRunners()) {
			Action clone = (Action) mapOfClones.get(action);
			assert(clone != null);
			result.getFreeRunners().add(clone);
		}
		
		for (State state : schedule.getStates()) {
			State clone = (State) mapOfClones.get(state);
			Map<Action, String> map = (Map<Action, String>) state.getAction2TargetMap();
			Map<Action, String> clonedMap = new HashMap<Action, String>();
					
			for (Action action : map.keySet()) {
				Action clonedAction = (Action) mapOfClones.get(action);
				assert(clonedAction != null);
				String target = map.get(action);
				clonedMap.put(clonedAction, target);
			}
			
			clone.setAction2TargetMap(clonedMap);
			
			PriorityGraph graph = (PriorityGraph) state.getPriorityGraph();		
			PriorityGraph clonedGraph = new PriorityGraph();
			
			for (Vertex vertex : graph.getVertices()) {
				Action action = (Action) mapOfClones.get(vertex.getAction());
				clonedGraph.addVertex(action);
			}
			
			for (Vertex vertex : graph.getVertices()) {
				for (Edge edge : vertex.getOutgoingEdges()) {
					Action source = (Action) mapOfClones.get(edge.getSource().getAction());
					Action target = (Action) mapOfClones.get(edge.getTarget().getAction());
					clonedGraph.addEdge(source, target);				
				}
			}
			
			clone.setPriorityGraph(clonedGraph);
		}		
		
		PriorityGraph graph = (PriorityGraph) schedule.getPriorityGraph();		
		PriorityGraph clonedGraph = new PriorityGraph();
		
		for (Vertex vertex : graph.getVertices()) {
			Action action = (Action) mapOfClones.get(vertex.getAction());
			clonedGraph.addVertex(action);
		}
		
		for (Vertex vertex : graph.getVertices()) {
			for (Edge edge : vertex.getOutgoingEdges()) {
				Action source = (Action) mapOfClones.get(edge.getSource().getAction());
				Action target = (Action) mapOfClones.get(edge.getTarget().getAction());
				clonedGraph.addEdge(source, target);				
			}
		}

		result.setPriorityGraph(clonedGraph);
		
		return result;
	}			
	
	@Override
	public Annotation caseAnnotation(Annotation annotation) {
		Annotation clone = IrFactory.eINSTANCE.createAnnotation();
		
		clone.setName(annotation.getName());
		
		for (AnnotationArgument arg : annotation.getArguments()) {
			AnnotationArgument clonedArg = IrFactory.eINSTANCE.createAnnotationArgument();
			clonedArg.setId(arg.getId());
			clonedArg.setValue(arg.getValue());
			clone.getArguments().add(clonedArg);			
		}
		
		return clone;
	}
	
	@Override
	public EObject defaultCase(EObject object) {
		return object;
	}

}
