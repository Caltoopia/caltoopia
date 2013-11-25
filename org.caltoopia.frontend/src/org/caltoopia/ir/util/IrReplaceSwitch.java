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

import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.caltoopia.ir.AbstractActor;
import org.caltoopia.ir.Action;
import org.caltoopia.ir.Actor;
import org.caltoopia.ir.ActorInstance;
import org.caltoopia.ir.Assign;
import org.caltoopia.ir.BinaryExpression;
import org.caltoopia.ir.Block;
import org.caltoopia.ir.BooleanLiteral;
import org.caltoopia.ir.Connection;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.FloatLiteral;
import org.caltoopia.ir.ForEach;
import org.caltoopia.ir.ForwardDeclaration;
import org.caltoopia.ir.FromSource;
import org.caltoopia.ir.FunctionCall;
import org.caltoopia.ir.Generator;
import org.caltoopia.ir.Guard;
import org.caltoopia.ir.IfExpression;
import org.caltoopia.ir.IfStatement;
import org.caltoopia.ir.IntegerLiteral;
import org.caltoopia.ir.LambdaExpression;
import org.caltoopia.ir.ListExpression;
import org.caltoopia.ir.Member;
import org.caltoopia.ir.Namespace;
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
import org.caltoopia.ir.TaggedTuple;
import org.caltoopia.ir.ToSink;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.TypeBool;
import org.caltoopia.ir.TypeConstructorCall;
import org.caltoopia.ir.TypeDeclaration;
import org.caltoopia.ir.TypeFloat;
import org.caltoopia.ir.TypeInt;
import org.caltoopia.ir.TypeLambda;
import org.caltoopia.ir.TypeList;
import org.caltoopia.ir.TypeString;
import org.caltoopia.ir.TypeTuple;
import org.caltoopia.ir.TypeUint;
import org.caltoopia.ir.TypeUndef;
import org.caltoopia.ir.TypeUser;
import org.caltoopia.ir.UnaryExpression;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.ir.VariableExternal;
import org.caltoopia.ir.VariableReference;
import org.caltoopia.ir.WhileLoop;
import org.caltoopia.ir.util.IrSwitch;

public class IrReplaceSwitch extends IrSwitch<EObject> {

	@Override 
	public Scope caseScope(Scope scope) {
		//Visit all declarations
		List<Declaration> decls = scope.getDeclarations();
		for (int i = 0; i < decls.size(); i++) {
			Declaration decl = (Declaration) doSwitch(decls.get(i));
			decls.set(i, decl);
		}
		
		return scope;
	}
	
	@Override
	public Namespace caseNamespace(Namespace namespace) {
		caseScope(namespace);
				
		//Visit all actors
		List<AbstractActor> actors =  namespace.getActors();
		for (int i = 0; i < actors.size(); i++) {
			AbstractActor a = (AbstractActor) doSwitch(actors.get(i));
			actors.set(i, a);
		}		
		
		return namespace;
	}
	
	@Override
	public AbstractActor caseAbstractActor(AbstractActor abstractActor) {
		caseScope(abstractActor);
		
		//Visit the actor type 
		doSwitch(abstractActor.getType());
		
		//Visit the input ports
		List<Port> inputs = abstractActor.getInputPorts();
		for (int i = 0; i < inputs.size(); i++) {
			Port p = casePort(inputs.get(i));
			inputs.set(i, p);
		}
				
		//Visit the output ports
		List<Port> outputs = abstractActor.getOutputPorts();
		for (int i = 0; i < outputs.size(); i++) {
			Port p = casePort(outputs.get(i));
			outputs.set(i, p);
		}
		
		//Visit the parameters
		List<Variable> params = abstractActor.getParameters();
		for (int i = 0; i < params.size(); i++) {
			Variable def = (Variable) doSwitch(params.get(i));
			params.set(i, def);
		} 
		
		return abstractActor;
	}

	
	@Override
	public AbstractActor caseNetwork(Network network) {
		caseAbstractActor(network);

		//Visit the actor instances
		List<ActorInstance> instances = network.getActors();
		for (int i = 0; i < instances.size(); i++) {
			ActorInstance instance = caseActorInstance(instances.get(i));
			instances.set(i, instance);
		}
		
		//Visit the connections
		List<Connection> connections = network.getConnections();
		for (int i = 0; i < connections.size(); i++) {
			Connection c = (Connection) doSwitch(connections.get(i));
			connections.set(i, c);
		}
		
		return network;
	}

	@Override
	public AbstractActor caseActor(Actor actor) {
		caseAbstractActor(actor);
		
		//Visit init actions
		List<Action> inits = actor.getInitializers();
		for (int i = 0; i < inits.size(); i++) {
			Action a = caseAction(inits.get(i));
			inits.set(i, a);
		}				
		
		//Visit all the actions
		List<Action> actions = actor.getActions();
		for (int i = 0; i < actions.size(); i++) {
			Action a = caseAction(actions.get(i));
			actions.set(i, a);
		}
		
		//Visit the schedule
		caseSchedule(actor.getSchedule());
		
		return actor;
	}


	@Override
	public Action caseAction(Action action) {	
		//Visit the input tokens
		List<PortRead> reads = action.getInputs();
		for (int i = 0; i < reads.size(); i++) {
			PortRead r = casePortRead(reads.get(i));
			reads.set(i, r);
		}
		
		caseScope(action);
		
		//Visit the guards
		List<Guard> guards = action.getGuards();
		for (int i = 0; i < guards.size(); i++) {
			Guard g = caseGuard(guards.get(i));
			guards.set(i, g);
		}
		
		//Visit the output expression
		List<PortWrite> writes = action.getOutputs();
		for (int i = 0; i < writes.size(); i++) {
			PortWrite w = casePortWrite(writes.get(i));
			writes.set(i, w);
		}
		
		//Visit the statements
		List<Statement> stmts = action.getStatements();
		for (int i = 0; i < stmts.size(); i++) {
			Statement s = (Statement) doSwitch(stmts.get(i));
			stmts.set(i, s);
		}
		
		return action;
	}

	@Override
	public Port casePort(Port port) {
		Type t = (Type) doSwitch(port.getType());
		port.setType(t);
		
		return port;
	}

	@Override
	public EObject casePortInstance(PortInstance portInstance) {
		return portInstance;
	}

	@Override
	public TaggedExpression caseTaggedExpression(TaggedExpression taggedExpression) {
		Expression e = (Expression) doSwitch(taggedExpression.getExpression());
		taggedExpression.setExpression(e);
		
		return taggedExpression;
	}
	
	@Override 
	public Expression caseExpression(Expression expression) {
		if (expression.getType() != null) {
			Type t = (Type) doSwitch(expression.getType());
			expression.setType(t);
		}

		return expression;
	}
	
	@Override
	public Expression caseIntegerLiteral(IntegerLiteral literal) {
		caseExpression(literal);
		return literal;
	}

	@Override
	public Expression caseFloatLiteral(FloatLiteral literal) {
		caseExpression(literal);
		return literal;
	}

	@Override
	public Expression caseBooleanLiteral(BooleanLiteral literal) {
		caseExpression(literal);
		return literal;
	}

	@Override
	public Expression caseStringLiteral(StringLiteral literal) {
		caseExpression(literal);
		return literal;
	}

	@Override
	public Expression caseVariableExpression(VariableExpression var) {
		caseExpression(var);
		
		//Visit the variable definition
		Declaration decl = (Declaration) doSwitch(var.getVariable());
		var.setVariable(decl);
		
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

	@Override
	public EObject caseLambdaExpression(LambdaExpression lambda) {
		caseScope(lambda);
		
		//Visit the type
		Type t = (Type) doSwitch(lambda.getType());
		lambda.setType(t);
		
		for (int i = 0; i < lambda.getParameters().size(); i++) {
			Variable param = (Variable) caseVariable(lambda.getParameters().get(i));
			lambda.getParameters().set(i, param);
		}
		
		
		
		Expression body = (Expression) doSwitch(lambda.getBody());
		lambda.setBody(body);
		
		return lambda;
	}
	
	@Override
	public EObject caseProcExpression(ProcExpression proc) {
		caseScope(proc);
		
		//Visit the type
		Type t = (Type) doSwitch(proc.getType());
		proc.setType(t);
		
		for (int i = 0; i < proc.getParameters().size(); i++) {
			Variable param = (Variable) caseVariable(proc.getParameters().get(i));
			proc.getParameters().set(i, param);
		}
		
		
		
		Block body = (Block) doSwitch(proc.getBody());
		proc.setBody(body);
		
		return proc;
	}
	
	@Override
	public EObject caseIfExpression(IfExpression expr) {
		Expression conditionExpr = (Expression) doSwitch(expr.getCondition());
		expr.setCondition(conditionExpr);

		Expression thenExpr = (Expression) doSwitch(expr.getThenExpression());
		expr.setThenExpression(thenExpr);

		Expression elseExpr = (Expression) doSwitch(expr.getElseExpression());
		expr.setElseExpression(elseExpr);
		
		return expr;
	}
	
	@Override
	public EObject caseListExpression(ListExpression expr) {
		
		for (int i = 0; i < expr.getGenerators().size(); i++) {
			Generator g = (Generator) doSwitch(expr.getGenerators().get(i));
			expr.getGenerators().set(i, g);
		}
		
		for (int i = 0; i < expr.getExpressions().size(); i++) {
			Expression e =  (Expression) doSwitch(expr.getExpressions().get(i));
			expr.getExpressions().set(i, e);
		}

		return expr;
	}
	
	@Override
	public EObject caseMember(Member member) {
		//Visit the type
		if (member.getType() != null) {
			Type t = (Type) doSwitch(member.getType());
			member.setType(t);
		}
		
		//Visit the index expressions
		List<Expression> indexes = member.getIndex();
		for (int i = 0; i < indexes.size(); i++) {
			Expression index = (Expression) doSwitch(indexes.get(i));
			indexes.set(i, index);
		}
		
		return member;
	}
	
	@Override
	public Expression caseBinaryExpression(BinaryExpression binaryExpression) {
		caseExpression(binaryExpression);
		
		Expression e1 = (Expression) doSwitch(binaryExpression.getOperand1());
		binaryExpression.setOperand1(e1);
		
		Expression e2 = (Expression) doSwitch(binaryExpression.getOperand2());
		binaryExpression.setOperand2(e2);

		return binaryExpression;
	}

	@Override
	public Expression caseUnaryExpression(UnaryExpression unaryExpression) {
		caseExpression(unaryExpression);
		
		Expression e = (Expression) doSwitch(unaryExpression.getOperand());
		unaryExpression.setOperand(e);
		
		return unaryExpression;
	}

	@Override
	public Expression caseFunctionCall(FunctionCall call) {
		caseExpression(call);
		
		//Visit the function def
		Expression expr = (Expression) doSwitch(call.getFunction());
		call.setFunction(expr);		
		
		//Visit the parameters
		List<Expression> actualParameters = call.getParameters();
		for (int i = 0; i < actualParameters.size(); i++) {
			Expression e = (Expression) doSwitch(actualParameters.get(i));
			actualParameters.set(i, e);
		}
		
		return call;
	}

	@Override
	public Expression caseTypeConstructorCall(TypeConstructorCall call) {
		caseExpression(call);
		
		//Visit the function def
		Declaration typedef = (Declaration) doSwitch(call.getTypedef());
		call.setTypedef(typedef);	
		
		List<Expression> initValues = call.getParameters();
		for (int i = 0; i < initValues.size(); i++) {
			Expression e = (Expression) doSwitch(initValues.get(i));
			initValues.set(i, e);
		}
		
		return call;
	}
	
	@Override
	public Connection caseConnection(Connection connection) {
		List<TaggedExpression> attributes = connection.getAttributes();
		for (int i = 0; i < attributes.size(); i++) {
			TaggedExpression te = caseTaggedExpression(attributes.get(i));
			attributes.set(i, te);
		}
		
		return connection;
	}

	@Override
	public Connection casePoint2PointConnection(Point2PointConnection connection) {
		caseConnection(connection);
		
		PortInstance source = (PortInstance) casePortInstance(connection.getSource());
		connection.setSource(source);
		
		PortInstance target = (PortInstance) casePortInstance(connection.getTarget());
		connection.setTarget(target);
		
		return connection;
	}

	@Override
	public Connection caseFromSource(FromSource connection) {
		caseConnection(connection);
		
		PortInstance target = (PortInstance) casePortInstance(connection.getTarget());
		connection.setTarget(target);
		
		return connection;
	}

	@Override
	public Connection caseToSink(ToSink connection) {
		caseConnection(connection);
		
		PortInstance source = (PortInstance) casePortInstance(connection.getSource());
		connection.setSource(source);

		return connection;
	}

	@Override
	public VariableReference caseVariableReference(VariableReference var) {
		
		//Visit the variable definition
		Variable def = (Variable) doSwitch(var.getDeclaration());
		var.setDeclaration(def);
		
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

	@Override
	public Statement caseAssign(Assign assign) {
		VariableReference target = caseVariableReference(assign.getTarget());
		assign.setTarget(target);

		Expression value = (Expression) doSwitch(assign.getExpression());
		assign.setExpression(value);
		
		return assign;
	}

	@Override
	public Statement caseReturnValue(ReturnValue returnValue) {
		
		Expression value = (Expression) doSwitch(returnValue.getValue());
		returnValue.setValue(value);
		
		return returnValue;
	}

	@Override
	public Statement caseProcCall(ProcCall call) {	
		//Visit the proc def
		doSwitch(call.getProcedure());
		
		//Visit the parameter expressions
		List<Expression> actualInParameters = call.getInParameters();
		for (int i = 0; i < actualInParameters.size(); i++) {
			Expression param = (Expression) doSwitch(actualInParameters.get(i));
			actualInParameters.set(i, param);
		}

		List<VariableReference> actualOutParameters = call.getOutParameters();
		for (int i = 0; i < actualOutParameters.size(); i++) {
			VariableReference param = (VariableReference) doSwitch(actualOutParameters.get(i));
			actualOutParameters.set(i, param);
		}

		return call;
	}

	@Override
	public Statement caseWhileLoop(WhileLoop whileLoop) {
		Expression condition = (Expression) doSwitch(whileLoop.getCondition());
		whileLoop.setCondition(condition);
		
		Block body = (Block) doSwitch(whileLoop.getBody());
		whileLoop.setBody(body);
		
		return whileLoop;
	}

	@Override
	public Statement caseForEach(ForEach forEach) {
		
		List<Generator> generators = forEach.getGenerators();
		for (int i = 0; i < generators.size(); i++) {
			Generator gen = (Generator) doSwitch(generators.get(i));
			generators.set(i, gen);
		}		
		
		Block body = (Block) doSwitch(forEach.getBody());
		forEach.setBody(body);
		
		return forEach;
	}
	
	@Override
	public Generator caseGenerator(Generator generator) {
		caseScope(generator);
		generator.setSource((Expression) doSwitch(generator.getSource())); 			
		return generator;
	}
	
	@Override
	public Statement caseIfStatement(IfStatement ifStatement) {
		Expression condition = (Expression) doSwitch(ifStatement.getCondition());
		ifStatement.setCondition(condition);
		
		Block thenBody = (Block) doSwitch(ifStatement.getThenBlock());
		ifStatement.setThenBlock(thenBody);
		
		if (ifStatement.getElseBlock() != null) {
			Block elseBody = (Block) doSwitch(ifStatement.getElseBlock());
			ifStatement.setElseBlock(elseBody);
		}

		return ifStatement;
	}

	@Override
	public Statement caseBlock(Block block) {
		caseScope(block);
		
		//Visit all the statements
		List<Statement> stmts = block.getStatements();
		for (int i = 0; i < stmts.size(); i++) {
			Statement stmt = (Statement) doSwitch(stmts.get(i));
			stmts.set(i, stmt);
		}
		
		return block;
	}

	@Override 
	public PortAccess casePortAccess(PortAccess portAccess) {
		//Visit the port
		Port port = casePort(portAccess.getPort());
		portAccess.setPort(port);
		
		//Visit the rate expression
		if (portAccess.getRepeat() != null) {
			Expression repeat = (Expression) doSwitch(portAccess.getRepeat());
			portAccess.setRepeat(repeat);
		}
		
		return portAccess;
	}
	
	@Override
	public PortWrite casePortWrite(PortWrite portWrite) {
		caseBlock(portWrite);
		casePortAccess(portWrite);
				
		//Visit all output expression
		List<Expression> expressions = portWrite.getExpressions();
		for (int i = 0; i < expressions.size(); i++) {
	        Expression e = (Expression) doSwitch(expressions.get(i));
			expressions.set(i, e);
		}
				
		return portWrite;
	}

	@Override
	public PortRead casePortRead(PortRead portRead) {
		casePortAccess(portRead);
		
		List<VariableReference> vars = portRead.getVariables();
		for (int i = 0; i < vars.size(); i++) {
			VariableReference var = caseVariableReference(vars.get(i));
			vars.set(i, var);
		}
		
		return portRead;
	}

	@Override
	public PortPeek casePortPeek(PortPeek portPeek) {
		casePortAccess(portPeek);
		
		VariableReference var = caseVariableReference(portPeek.getVariable());
		portPeek.setVariable(var);
		
		return portPeek;
	}

	@Override
	public Declaration caseForwardDeclaration(ForwardDeclaration decl) {
		//TODO anyone every need to go deep in this?
		return decl;
	}

	@Override
	public Declaration caseVariableExternal(VariableExternal decl) {
		//TODO anyone every need to go deep in this?
		return decl;
	}

	@Override
	public Declaration caseVariable(Variable variable) {
		if (variable.getType() != null) {
			Type t = (Type) doSwitch(variable.getType());
			variable.setType(t);
		}
		
		if (variable.getInitValue() != null) {
			Expression initValue = (Expression) doSwitch(variable.getInitValue());
			variable.setInitValue(initValue);
		}
		
		return variable;
	}


	@Override
	public ActorInstance caseActorInstance(ActorInstance instance) {
		for (int i = 0; i < instance.getActualParameters().size(); i++) {
			TaggedExpression te = instance.getActualParameters().get(i);
			Expression e = (Expression) doSwitch(te.getExpression());
			te.setExpression(e);
		}
		
		return instance;
	}

	@Override
	public Guard caseGuard(Guard guard) {
		Expression body = (Expression) doSwitch(guard.getExpression());
		guard.setExpression(body);
//		Type type = (Type) doSwitch(guard.getType());
//		guard.setType(type);

		//Visit all peeks
		List<PortPeek> port = guard.getPeeks();
		for (int i = 0; i < port.size(); i++) {
	        PortPeek p = (PortPeek) doSwitch(port.get(i));
			port.set(i, p);
		}		
		return guard;
	}

	@Override
	public Type caseTypeBool(TypeBool type) {
		//FIXME
		return type;
	}

	@Override
	public Type caseTypeInt(TypeInt type) {
		if (type.getSize() != null) {
			Expression size = (Expression) doSwitch(type.getSize());
			type.setSize(size);
		}
		
		return type;
	}

	@Override
	public Type caseTypeList(TypeList type) {
		if (type.getSize() != null) {
			Expression size = (Expression) doSwitch(type.getSize());
			type.setSize(size);
		}
		
		Type t = (Type) doSwitch(type.getType());
		type.setType(t);
		
		return type;
	}

	@Override
	public Type caseTypeFloat(TypeFloat type) {
		return type;
	}

	@Override
	public Type caseTypeUint(TypeUint type) {
		if (type.getSize() != null) {
			Expression size = (Expression) doSwitch(type.getSize());
			type.setSize(size);
		}

		return type;
	}

	@Override
	public Type caseTypeString(TypeString type) {
		return type;
	}

	@Override
	public TypeTuple caseTypeTuple(TypeTuple type) {
		for (int i = 0; i < type.getTaggedTuples().size(); i++) {
			TaggedTuple tt = (TaggedTuple) doSwitch(type.getTaggedTuples().get(i));
			type.getTaggedTuples().set(i, tt);
		}
		return type;
	}
	
	@Override
	public TaggedTuple caseTaggedTuple(TaggedTuple tt) {
		for (Variable member : tt.getFields()) {	
			Type t = (Type) doSwitch(member.getType());			
			member.setType(t);
		}
		return tt;
	}

	@Override
	public Type caseTypeLambda(TypeLambda type) {
		for (int i = 0; i < type.getInputTypes().size(); i++) {	
			Type t = (Type) doSwitch(type.getInputTypes().get(i));			
			type.getInputTypes().set(i, t);
		}
		
		if (type.getOutputType() != null) {
			Type t = (Type) doSwitch(type.getOutputType());			
			type.setOutputType(t);
		}
		
		return type;
	}
	
	@Override
	public Type caseTypeUndef(TypeUndef type) {
		//FIXME
		return type;
	}
	
	@Override
	public Type caseTypeUser(TypeUser type) {
		//FIXME
		return type;
	}

	@Override
	public Type caseTypeActor(TypeActor type) {
		//FIXME
		return type;
	}

	@Override
	public Declaration caseTypeDeclaration(TypeDeclaration typeDecl) {
		Type type = (Type) doSwitch(typeDecl.getType());
		typeDecl.setType(type);
		
		return typeDecl;
	}

	@Override
	public Schedule caseSchedule(Schedule schedule) {
		//FIXME
		return schedule;
	}

	@Override
	public State caseState(State state) {
		//FIXME
		return state;
	}

	@Override
	public EObject defaultCase(EObject object) {
		return object;
	}

}
