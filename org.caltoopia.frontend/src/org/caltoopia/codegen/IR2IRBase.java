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

package org.caltoopia.codegen;

import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
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
import org.caltoopia.ir.ExpressionCall;
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
import org.caltoopia.ir.LiteralExpression;
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
import org.caltoopia.ir.ToSink;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.TypeBool;
import org.caltoopia.ir.TypeConstructor;
import org.caltoopia.ir.TypeConstructorCall;
import org.caltoopia.ir.TypeDeclaration;
import org.caltoopia.ir.TypeDeclarationImport;
import org.caltoopia.ir.TypeExternal;
import org.caltoopia.ir.TypeFloat;
import org.caltoopia.ir.TypeInt;
import org.caltoopia.ir.TypeLambda;
import org.caltoopia.ir.TypeList;
import org.caltoopia.ir.TypeProc;
import org.caltoopia.ir.TypeRecord;
import org.caltoopia.ir.TypeString;
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
import org.caltoopia.ir.util.IrSwitch;

public class IR2IRBase extends IrSwitch<EObject> {

	protected Set<Namespace> visitedNameSpaces = new HashSet<Namespace>();
	protected Set<Actor> visitedActor = new HashSet<Actor>();
	private Map<EObject,Integer> visitCounter = new HashMap<EObject,Integer>();
	protected Stack<EObject> hierarchy = new Stack<EObject>();
	protected Actor lastActor;
	protected Network lastNetwork;
	protected Action lastAction;
	protected Namespace currentNameSpace;
	
	public IR2IRBase() {
		visitedNameSpaces.clear();
		visitCounter.clear();
		lastActor = null;
		lastAction = null;
		lastNetwork = null;
		currentNameSpace = null;
		hierarchy.clear();
	}
	
	protected void cntpp(EObject o) {
		if(visitCounter.containsKey(o)) {
			int c=visitCounter.get(o)+1;
			visitCounter.remove(o);
			visitCounter.put(o,c);
		} else {
			visitCounter.put(o, 1);
		}
		hierarchy.push(o);
	}
	
	protected void leave() {
		hierarchy.pop();		
	}
	
	public int visitCnt(EObject o) {
		if(visitCounter.containsKey(o)) {
			return visitCounter.get(o);
		} else {
			return 0;
		}
	}
	
	public boolean visited(EObject o) {
		return visitCounter.containsKey(o);
	}
	
	protected Namespace getThisNamespace() {
		//First check if we have passed a namespace
		for(int i = hierarchy.size()-1; i>=0; i--) {
			if(hierarchy.get(i) instanceof Namespace) {
				return (Namespace) hierarchy.get(i);
			}
		}
		//When not then see if any of the scopes we have passed has an outer scope that is a namespace
		for(int i = hierarchy.size()-1; i>=0; i--) {
			if(hierarchy.get(i) instanceof Actor) {
				if(((Actor)hierarchy.get(i)).getOuter() instanceof Namespace) {
					return (Namespace) ((Actor)hierarchy.get(i)).getOuter();
				}
			}
			if(hierarchy.get(i) instanceof Network) {
				if(((Network)hierarchy.get(i)).getOuter() instanceof Namespace) {
					return (Namespace) ((Network)hierarchy.get(i)).getOuter();
				}
			}
			if(hierarchy.get(i) instanceof Action) {
				if(((Action)hierarchy.get(i)).getOuter() instanceof Namespace) {
					return (Namespace) ((Action)hierarchy.get(i)).getOuter();
				}
			}
		}
		return null;
	}
	
	protected Scope getThisScope() {
		//First check if we have passed a Scope
		for(int i = hierarchy.size()-1; i>=0; i--) {
			if(hierarchy.get(i) instanceof Scope) {
				return (Scope) hierarchy.get(i);
			}
		}
		//When not then see if any of the passed has an outer scope
		for(int i = hierarchy.size()-1; i>=0; i--) {
			if(hierarchy.get(i) instanceof Actor) {
				if(((Actor)hierarchy.get(i)).getOuter() !=null) {
					return ((Actor)hierarchy.get(i)).getOuter();
				}
			}
			if(hierarchy.get(i) instanceof Network) {
				if(((Network)hierarchy.get(i)).getOuter() !=null) {
					return ((Network)hierarchy.get(i)).getOuter();
				}
			}
			if(hierarchy.get(i) instanceof Action) {
				if(((Action)hierarchy.get(i)).getOuter() !=null) {
					return ((Action)hierarchy.get(i)).getOuter();
				}
			}
		}
		return null;
	}
	
	protected Actor getThisActor() {
		//First check if we have passed a Actor
		for(int i = hierarchy.size()-1; i>=0; i--) {
			if(hierarchy.get(i) instanceof Actor) {
				return (Actor) hierarchy.get(i);
			}
		}
		//When not then see if any of the passed has an outer scope
		for(int i = hierarchy.size()-1; i>=0; i--) {
			if(hierarchy.get(i) instanceof Action) {
				Scope scope = ((Action)hierarchy.get(i)).getOuter();
				if(scope !=null && scope instanceof Actor) {
					return (Actor) scope;
				}
			}
		}
		return null;
	}
	
	protected AbstractActor getThisAbstractActor() {
		//First check if we have passed a AbstarctActor
		for(int i = hierarchy.size()-1; i>=0; i--) {
			if(hierarchy.get(i) instanceof AbstractActor) {
				return (AbstractActor) hierarchy.get(i);
			}
		}
		//When not then see if any of the passed has an outer scope
		for(int i = hierarchy.size()-1; i>=0; i--) {
			if(hierarchy.get(i) instanceof Action) {
				Scope scope = ((Action)hierarchy.get(i)).getOuter();
				if(scope !=null && scope instanceof AbstractActor) {
					return (AbstractActor) scope;
				}
			}
		}
		return null;
	}
	
	@Override
	public EObject caseNamespace(Namespace ns) {
		if(!visitedNameSpaces.contains(ns)) {
			cntpp(ns);
			currentNameSpace = ns;
			visitedNameSpaces.add(ns);
			for (Declaration d : ns.getDeclarations()) {
				doSwitch(d);
			}
			for (AbstractActor a : ns.getActors()) {
				doSwitch(a);				
			}
			currentNameSpace = null;
			leave();
		}
		return ns;
 	}
 	
	@Override
	public EObject caseActor(Actor actor) {
		if(!visitedActor.contains(actor)) {
			visitedActor.add(actor);
			cntpp(actor);
			lastActor = actor;
			/* TODO verify not needed anymore
			//Which namespaces are used...
			Set<Namespace> usedNameSpaces = new HashSet<Namespace>();
			for (Import imp : ActorDirectory.findNamespace(actor.getNamespace()).getImports()) {
				if (imp.getDefinition() != null) {
					usedNameSpaces.add(ActorDirectory.findNamespace(imp.getDefinition()));
				} else {
					usedNameSpaces.add(ActorDirectory.findNamespace(imp.getTypedef()));
				}
			}
			//... visit them
			for (Namespace ns : usedNameSpaces) {
				doSwitch(ns);
			}
			//To also catch anything defined in the namespace surrounding an actor/network
			doSwitch(actor.getOuter());
			*/
			for (Action a : actor.getActions()) {
				doSwitch(a);				
			}
			//TODO verify what this is???
			if(!actor.getInitializers().isEmpty()) {
				for(Action a : actor.getInitializers()) {
					doSwitch(a);
				}
			}
			doSwitch(actor.getSchedule());
			if(actor.getType()!=null) doSwitch(actor.getType()); //FIXME why do I get a null type???
			for (Port p : actor.getInputPorts()) {
				doSwitch(p);
			}
			for (Port p : actor.getOutputPorts()) {
				doSwitch(p);
			}
			for (Declaration d : actor.getParameters()) {
				doSwitch(d);
			}
			for (Declaration d : actor.getDeclarations()) {
				doSwitch(d);
			}
			leave();
		}
		return actor;
	}

	@Override
	public EObject caseNetwork(Network network) {
		cntpp(network);
		lastNetwork = network;
		//Which namespaces are used...
		/* TODO verify not needed
		Set<Namespace> usedNameSpaces = new HashSet<Namespace>();
		for (Import imp : ActorDirectory.findNamespace(network.getNamespace()).getImports()) {
			doSwitch(imp);
			if (imp.getDefinition() != null) {
				usedNameSpaces.add(ActorDirectory.findNamespace(imp.getDefinition()));
			} else {
				usedNameSpaces.add(ActorDirectory.findNamespace(imp.getTypedef()));
			}
		}
		//... visit them
		for (Namespace ns : usedNameSpaces) {
			doSwitch(ns);
		}
		*/
		for (Declaration d : network.getDeclarations()) {
			doSwitch(d);
		}
		/* We are going to visit the constant evaluated version later in CPrinter instead
		for (ActorInstance actorInstance : network.getActors()) {
			System.out.println(actorInstance.getName());
			doSwitch(actorInstance);
			doSwitch(ActorDirectory.findActor((TypeActor) actorInstance.getType()));				
		}
		*/
		for (Connection c : network.getConnections()) {
			doSwitch(c);
		}
		leave();
		return network;		
	}
	
	@Override
	public EObject caseAction(Action action) {
		cntpp(action);
		lastAction = action;
		for (Declaration d : action.getDeclarations()) {
			doSwitch(d);
		}
		for (Guard g : action.getGuards()) {
			doSwitch(g);
		}
		for (PortWrite p : action.getOutputs()) {
			doSwitch(p);
		}
		for (PortRead p : action.getInputs()) {
			doSwitch(p);
		}
		for (Statement s : action.getStatements()) {
			Statement s2=(Statement) doSwitch(s);
			if(!s2.equals(s)) {
				int i = action.getStatements().indexOf(s);
				action.getStatements().set(i, s2);
			}
		}
		//doSwitch(action.getOuter());
		leave();
		return action;
	}
	
	@Override
	public EObject caseIntegerLiteral(IntegerLiteral lit) {
		cntpp(lit);
		leave();
		return lit;
	}

	@Override
	public EObject caseFloatLiteral(FloatLiteral lit) {
		cntpp(lit);
		leave();
		return lit;
	}

	@Override
	public EObject caseBooleanLiteral(BooleanLiteral lit) {
		cntpp(lit);
		leave();
		return lit;
	}

	@Override
	public EObject caseStringLiteral(StringLiteral lit) {
		cntpp(lit);
		leave();
		return lit;
	}

	@Override
	public EObject caseListExpression(ListExpression lit) {
		cntpp(lit);
		leave();
		return lit;
	}

	
	@Override
	public EObject caseVariableExpression(VariableExpression var) {
		cntpp(var);
		if(var.getType() != null) doSwitch(var.getType()); //TODO why do we get type less expression
		doSwitch(var.getVariable());
		for (Expression e : var.getIndex()) {
			doSwitch(e);
		}
		for (Member m : var.getMember()) {
			doSwitch(m);
		}
		//doSwitch(var.getContext());
		leave();
		return var;
	}
	
	@Override
	public EObject caseVariableReference(VariableReference var) {
		cntpp(var);
		if(var.getType()!=null) doSwitch(var.getType()); //FIXME why do I get a null type???
		doSwitch(var.getDeclaration());
		for (Expression e : var.getIndex()) {
			doSwitch(e);
		}
		for (Member m : var.getMember()) {
			doSwitch(m);
		}
		leave();
		return var;
	}

	@Override
	public EObject caseMember(Member member) {
		cntpp(member);
		doSwitch(member.getType());
		for (Expression e : member.getIndex()) {
			doSwitch(e);
		}
		leave();
		return member;
	}

	@Override
	public EObject caseBinaryExpression(BinaryExpression expr) {
		cntpp(expr);
		if(expr.getType() !=null) doSwitch(expr.getType()); //TODO why do we get type less expressions
		doSwitch(expr.getOperand1());
		doSwitch(expr.getOperand2());
		//doSwitch(expr.getContext());
		leave();
		return expr;
	}

	@Override
	public EObject caseUnaryExpression(UnaryExpression expr) {
		cntpp(expr);
		if(expr.getType() != null) doSwitch(expr.getType()); //TODO typeless???
		doSwitch(expr.getOperand());
		//doSwitch(expr.getContext());
		leave();
		return expr;
	}

	@Override
	public EObject caseFunctionCall(FunctionCall expr) {
		cntpp(expr);
		if(expr.getType() != null) doSwitch(expr.getType()); //TODO type less ???
		doSwitch(expr.getFunction());
		for (Expression e : expr.getParameters()) {
			doSwitch(e);
		}
		//doSwitch(expr.getContext());
		leave();
		return expr;
	}

	@Override
	public EObject caseTypeConstructorCall(TypeConstructorCall expr) {
		cntpp(expr);
		if(expr.getType() != null) doSwitch(expr.getType()); //TODO type less
		for (Expression e : expr.getParameters()) {
			doSwitch(e);
		}
		//doSwitch(expr.getContext());
		leave();
		return expr;
	}

	@Override
	public EObject caseAssign(Assign assign) {
		cntpp(assign);
		doSwitch(assign.getTarget());
		doSwitch(assign.getExpression());
		leave();
		return assign;
	}

	@Override
	public EObject caseProcCall(ProcCall call) {
		cntpp(call);
		doSwitch(call.getProcedure());
		for (Expression p : call.getInParameters()) {
			doSwitch(p);
		}
		for (VariableReference p : call.getOutParameters()) {
			doSwitch(p);
		}
		leave();
		return call;
	}

	@Override
	public EObject caseWhileLoop(WhileLoop stmt) {
		cntpp(stmt);
		doSwitch(stmt.getCondition());		
		doSwitch(stmt.getBody());
		leave();
		return stmt;
	}

	@Override
	public EObject caseIfStatement(IfStatement stmt) {
		cntpp(stmt);
		doSwitch(stmt.getCondition());		
		doSwitch(stmt.getThenBlock());
		if (stmt.getElseBlock() != null) {
		  doSwitch(stmt.getElseBlock());
		}
		leave();
		return stmt;
	}

	@Override
	public EObject caseBlock(Block block) {
		cntpp(block);
		for (Declaration d : block.getDeclarations()) {
			doSwitch(d);
		}		
		for (Statement s : block.getStatements()) {
			Statement s2=(Statement) doSwitch(s);
			if(!s2.equals(s)) {
				int i = block.getStatements().indexOf(s);
				block.getStatements().set(i, s2);
			}
		}
		leave();
		return block;
	}

	@Override
	public EObject casePortRead(PortRead read) {
		cntpp(read);
		for (VariableReference v : read.getVariables()) {
			doSwitch(v);
		}
		doSwitch(read.getPort());
		if(read.getRepeat()!=null) doSwitch(read.getRepeat());
		leave();
		return read;
	}
	
	@Override
	public EObject casePortPeek(PortPeek peek) {
		cntpp(peek);
		doSwitch(peek.getVariable());
		doSwitch(peek.getPort());
		if(peek.getRepeat()!=null) doSwitch(peek.getRepeat());
		leave();
		return peek;
	}
	
	@Override
	public EObject casePortWrite(PortWrite write) {
		cntpp(write);
		for (Expression v : write.getExpressions()) {
			doSwitch(v);
		}
		for (Statement s : write.getStatements()) {
			doSwitch(s);
		}
		doSwitch(write.getPort());
		if(write.getRepeat() != null) doSwitch(write.getRepeat());
		leave();
		return write;
	}

	@Override
	public EObject caseVariable(Variable variable) {
		cntpp(variable);
		//Only visit if CIR has not created it
		if(!UtilIR.containsTagBool(variable.getAttributes(),"CIR_func")) {
			if(variable.getType()!=null) doSwitch(variable.getType()); //FIXME why do I get a null type???
			if(variable.getInitValue()!=null) doSwitch(variable.getInitValue());
		}
		leave();
		return variable;
	}

	@Override
	public EObject caseGuard(Guard guard) {
		cntpp(guard);
		doSwitch(guard.getType());
		for (Declaration d : guard.getDeclarations()) {
			doSwitch(d);
		}		
		for (Variable d : guard.getParameters()) {
			doSwitch(d);
		}
		for (PortPeek p : guard.getPeeks()) {
			doSwitch(p);
		}
		doSwitch(guard.getBody());
		//doSwitch(guard.getScope());
		leave();
		return guard;
	}
	
	@Override
	public EObject caseTypeConstructor(TypeConstructor tc) {
		cntpp(tc);
		//Don't follow typedef because then the scan becomes circular
		//doSwitch(tc.getScope());
		leave();
		return tc;
	}

	@Override
	public EObject caseTypeDeclaration(TypeDeclaration typeDecl) {
		if(hierarchy.lastElement() instanceof Network) {
			cntpp(typeDecl);
			if(typeDecl.getType()!=null) doSwitch(typeDecl.getType()); //FIXME why do I get a null type???
			if(typeDecl.getConstructor()!=null) doSwitch(typeDecl.getConstructor()); //FIXME why do I get a null type???
			//doSwitch(typeDef.getScope());
			leave();
		} else {
			//TODO Since the AST does not generate IR that have local typedef we can't test if it does work
			//hence since likely is not correct better to say error until debugged
			System.err.println("We currently only support global typedefs i.e. in top network or directly in namespaces!");
		}
		return typeDecl;
	}

	@Override
	public EObject caseReturnValue(ReturnValue returnValue) {
		cntpp(returnValue);
		doSwitch(returnValue.getValue());
		leave();
		return returnValue;
	}
	
	@Override
	public EObject caseTypeBool(TypeBool type) {
		cntpp(type);
		leave();
		return type;
	}

	@Override
	public EObject caseTypeInt(TypeInt type) {
		cntpp(type);
		if(type.getSize()!=null) doSwitch(type.getSize());
		leave();
		return type;
	}

	@Override
	public EObject caseTypeFloat(TypeFloat type) {
		cntpp(type);
		leave();
		return type;
	}

	@Override
	public EObject caseTypeList(TypeList type) {
		cntpp(type);
		doSwitch(type.getType());
		if (type.getSize()!=null) {
			doSwitch(type.getSize());
		}
		leave();
		return type;
	}

	@Override
	public EObject caseTypeUint(TypeUint type) {
		cntpp(type);
		if(type.getSize()!=null) doSwitch(type.getSize());
		leave();
		return type;
	}

	@Override
	public EObject caseTypeString(TypeString type) {
		cntpp(type);
		leave();
		return type;
	}
	
	@Override
	public EObject caseTypeRecord(TypeRecord struct) {
		cntpp(struct);
		for (Declaration d : struct.getMembers()) {
			doSwitch(d);
		}
		leave();
		return struct;
	}

	@Override
	public EObject caseTypeUndef(TypeUndef type) {
		cntpp(type);
		leave();
		return type;
	}
	
	@Override
	public EObject caseTypeExternal(TypeExternal type) {
		cntpp(type);
		leave();
		return type;
	}
	
	/* This is never called since overlayed by specific implementation */
	@Override
	public EObject caseType(Type object) {
		cntpp(object);
		leave();
		return object;
	}
	
	/* This is never called since overlayed by specific implementation */
	@Override
	public EObject caseAbstractActor(AbstractActor actor) {
		cntpp(actor);
		leave();
		return actor;
 	}
 	
	@Override
	public EObject caseActorInstance(ActorInstance actor) {
		cntpp(actor);
		doSwitch(actor.getType());
		for (PortInstance p : actor.getInputs()) {
			doSwitch(p);
		}
		for (PortInstance p : actor.getOutputs()) {
			doSwitch(p);
		}
		//doSwitch(actor.getInitValue());
		//doSwitch(actor.getScope());
		leave();
		return actor;
 	}
 	
	@Override
	public EObject caseConnection(Connection obj) {
		cntpp(obj);
		//FIXME do we need to run over tagegd experssions???
		//for (TaggedExpression o : obj.getAttributes()) {
		//	doSwitch(o);
		//}
		leave();
		return obj;
 	}
 	
	/* This is never called since overlayed by specific implementation */
	@Override
	public EObject caseDeclaration(Declaration obj) {
		cntpp(obj);
		//doSwitch(obj.getScope());
		leave();
		return obj;
 	}
 	
	/* This is never called since overlayed by specific implementation */
	@Override
	public EObject caseExpression(Expression obj) {
		cntpp(obj);
		doSwitch(obj.getType());
		//doSwitch(obj.getContext());
		leave();
		return obj;
 	}
 	
	/* This is never called since overlayed by specific implementation */
	@Override
	public EObject caseExpressionCall(ExpressionCall obj) {
		cntpp(obj);
		doSwitch(obj.getType());
		//doSwitch(obj.getContext());
		leave();
		return obj;
 	}
 	
	@Override
	public EObject caseFromSource(FromSource obj) {
		cntpp(obj);
		doSwitch(obj.getSource());
		doSwitch(obj.getTarget());
		leave();
		return obj;
 	}
 	
	
	@Override
	public EObject caseVariableImport(VariableImport obj) {
		cntpp(obj);
		leave();
		return obj;		
	}
	
	@Override
	public EObject caseTypeDeclarationImport(TypeDeclarationImport obj) {
		cntpp(obj);
		leave();
		return obj;		
	}

	/* This is never called since overlayed by specific implementation */
	@Override
	public EObject caseLiteralExpression(LiteralExpression obj) {
		cntpp(obj);
		doSwitch(obj.getType());
		//doSwitch(obj.getContext());
		leave();
		return obj;
 	}
 	
	@Override
	public EObject casePoint2PointConnection(Point2PointConnection obj) {
		cntpp(obj);
		doSwitch(obj.getSource());
		doSwitch(obj.getTarget());
		leave();
		return obj;
 	}
 	
	@Override
	public EObject casePort(Port obj) {
		cntpp(obj);
		doSwitch(obj.getType());
		leave();
		return obj;
 	}
 	
	@Override
	public EObject casePortInstance(PortInstance obj) {
		cntpp(obj);
		//doSwitch(obj.getActor());
		//for (Connection c : obj.getConnections()) {
		//	doSwitch(c);
		//}	
		leave();
		return obj;
 	}
 	
	@Override
	public EObject caseSchedule(Schedule obj) {
		cntpp(obj);
		/*
		for (State s : obj.getStates()) {
			doSwitch(s);
		}
		for (Action a : obj.getFreeRunners()) {
			doSwitch(a);
		}
		leave();
		*/
		return obj;
 	}
 	
	/* This is never called since overlayed by specific implementation */
	@Override
	public EObject caseScope(Scope obj) {
		cntpp(obj);
		for (Declaration d : obj.getDeclarations()) {
			doSwitch(d);
		}
		leave();
		return obj;
 	}
 	
	@Override
	public EObject caseState(State obj) {
		cntpp(obj);
		leave();
		return obj;
 	}
 	
	/* This is never called since overlayed by specific implementation */
	@Override
	public EObject caseStatement(Statement obj) {
		cntpp(obj);		
		leave();
		return obj;
 	}
 	
	/* TODO why is this never called */
	@Override
	public EObject caseTaggedExpression(TaggedExpression obj) {
		cntpp(obj);
		leave();
		return obj;
 	}
 	
	@Override
	public EObject caseToSink(ToSink obj) {
		cntpp(obj);
		doSwitch(obj.getSource());
		doSwitch(obj.getSink());
		leave();
		return obj;
 	}
 	
	@Override
	public EObject caseTypeActor(TypeActor obj) {
		cntpp(obj);
		leave();
		return obj;
 	}
	
	@Override
	public EObject caseForEach(ForEach obj) {
		cntpp(obj);
		doSwitch(obj.getBody());
		for(Generator g : obj.getGenerators())
			doSwitch(g);
		leave();
		return obj;
 	}

	@Override
	public EObject caseForwardDeclaration(ForwardDeclaration obj) {
		cntpp(obj);
		doSwitch(obj.getDeclaration());
		doSwitch(obj.getType());
		leave();
		return obj;
 	}

	@Override
	public EObject caseGenerator(Generator obj) {
		cntpp(obj);
		doSwitch(obj.getSource());
		for(Declaration d : obj.getDeclarations())
			doSwitch(d);
		leave();
		return obj;
 	}

	@Override
	public EObject caseIfExpression(IfExpression obj) {
		cntpp(obj);
		doSwitch(obj.getCondition());
		doSwitch(obj.getElseExpression());
		doSwitch(obj.getThenExpression());
		if(obj.getType() != null) doSwitch(obj.getType()); //TODO type less???
		leave();
		return obj;
 	}
	
	@Override
	public EObject caseLambdaExpression(LambdaExpression obj) {
		cntpp(obj);
		doSwitch(obj.getBody());
		doSwitch(obj.getType());
		for(Declaration d : obj.getDeclarations())
			doSwitch(d);
		for(Variable v : obj.getParameters())
			doSwitch(v);
		leave();
		return obj;
 	}
	
	@Override
	public EObject casePortAccess(PortAccess obj) {
		cntpp(obj);
		leave();
		return obj;
 	}
	
	@Override
	public EObject caseTypeLambda(TypeLambda obj) {
		cntpp(obj);
		for(Type t : obj.getInputTypes())
			doSwitch(t);
		//FIXME Get null output types due to that Johan give Procedures a Lambda type!!!! 
		if(obj.getOutputType()!=null) doSwitch(obj.getOutputType());
		leave();
		return obj;
 	}
	
	@Override
	public EObject caseTypeProc(TypeProc obj) {
		cntpp(obj);
		for(Type t : obj.getInputTypes())
			doSwitch(t);
		for(Type t : obj.getOutputTypes())
			doSwitch(t);
		leave();
		return obj;
 	}
	
	@Override
	public EObject caseTypeUser(TypeUser obj) {
		cntpp(obj);
		//We already have visited the TypeDeclaration this is just a reference 
		//doSwitch(obj.getDeclaration());
		leave();
		return obj;
 	}
	
	@Override
	public EObject caseVariableExternal(VariableExternal obj) {
		cntpp(obj);
		doSwitch(obj.getType());
		leave();
		return obj;
 	}
	
	@Override
	public EObject caseProcExpression(ProcExpression obj) {
		cntpp(obj);
		doSwitch(obj.getType());
		doSwitch(obj.getBody());
		for(Declaration d : obj.getDeclarations())
			doSwitch(d);
		for(Variable v : obj.getOutputs())
			doSwitch(v);
		for(Variable v : obj.getParameters())
			doSwitch(v);
		leave();
		return obj;
 	}
	
}
