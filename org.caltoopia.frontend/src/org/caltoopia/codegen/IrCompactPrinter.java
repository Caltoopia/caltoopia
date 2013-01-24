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

import java.util.List;
import java.util.Map;

import org.caltoopia.ast2ir.Stream;
import org.caltoopia.ast2ir.Util;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.caltoopia.ast2ir.PriorityGraph;
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
import org.caltoopia.ir.ExternalActor;
import org.caltoopia.ir.ForEach;
import org.caltoopia.ir.ForwardDeclaration;
import org.caltoopia.ir.FromSource;
import org.caltoopia.ir.IfExpression;
import org.caltoopia.ir.LambdaExpression;
import org.caltoopia.ir.Connection;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.FloatLiteral;
import org.caltoopia.ir.FunctionCall;
import org.caltoopia.ir.Generator;
import org.caltoopia.ir.Guard;
import org.caltoopia.ir.IfStatement;
import org.caltoopia.ir.IntegerLiteral;
import org.caltoopia.ir.ListExpression;
import org.caltoopia.ir.Member;
import org.caltoopia.ir.Namespace;
import org.caltoopia.ir.Network;
import org.caltoopia.ir.Point2PointConnection;
import org.caltoopia.ir.Port;
import org.caltoopia.ir.PortInstance;
import org.caltoopia.ir.PortPeek;
import org.caltoopia.ir.PortRead;
import org.caltoopia.ir.PortWrite;
import org.caltoopia.ir.ProcCall;
import org.caltoopia.ir.ProcExpression;
import org.caltoopia.ir.ReturnValue;
import org.caltoopia.ir.Schedule;
import org.caltoopia.ir.State;
import org.caltoopia.ir.Statement;
import org.caltoopia.ir.StringLiteral;
import org.caltoopia.ir.TaggedExpression;
import org.caltoopia.ir.ToSink;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.TypeBool;
import org.caltoopia.ir.TypeLambda;
import org.caltoopia.ir.TypeConstructor;
import org.caltoopia.ir.TypeConstructorCall;
import org.caltoopia.ir.TypeDeclaration;
import org.caltoopia.ir.TypeFloat;
import org.caltoopia.ir.TypeInt;
import org.caltoopia.ir.TypeList;
import org.caltoopia.ir.TypeString;
import org.caltoopia.ir.TypeRecord;
import org.caltoopia.ir.TypeUint;
import org.caltoopia.ir.TypeUndef;
import org.caltoopia.ir.TypeUser;
import org.caltoopia.ir.TypeDeclarationImport;
import org.caltoopia.ir.UnaryExpression;
import org.caltoopia.ir.VariableExternal;
import org.caltoopia.ir.VariableReference;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.ir.VariableImport;
import org.caltoopia.ir.WhileLoop;
import org.caltoopia.ir.util.IrSwitch;
import java.sql.Timestamp;


public class IrCompactPrinter extends IrSwitch<Stream> {

	private static Stream s;
		
	static public void print(String output, EObject object) {
		setOutput(output);
		java.util.Date date= new java.util.Date();
		s.println();
		s.println("/* --- Compact CAL IR " + new Timestamp(date.getTime()) + " --- */");

		new IrCompactPrinter().doSwitch(object);
	}
	
	
	private static void setOutput(String outputFile) {
				
		if(outputFile!=null) {
			s = new Stream(outputFile);				
		} else {
			s = new Stream();
		}
	}

	public IrCompactPrinter() {
		
	}
	
	@Override
	public Stream caseNamespace(Namespace ns) {
		s.printlnInc("namespace " + Util.packQualifiedName(ns.getName()) + ":");
		printAnnotations(ns.getAnnotations());
		
		for (Declaration d : ns.getDeclarations()) {
			doSwitch(d);
			s.println(";");
		}
		
		s.println();
		for (AbstractActor a : ns.getActors()) {
			doSwitch(a);
			s.println();
		}
		
		s.dec();
		s.printlnDec("end");
		return s;
 	}

	@Override
	public Stream caseActor(Actor actor) {
		s.print("actor " +  actor.getType().getName() + "("); 

		for (int i = 0; i < actor.getParameters().size(); i++) {
			Variable var = actor.getParameters().get(i);
			doSwitch(var);
			// s.print(" " + var.getName());
			if (i < actor.getParameters().size() - 1) 
				s.print(", ");
		}
		s.print(") ");
		
		for (int i = 0; i < actor.getInputPorts().size(); i++) { 
			Port port = actor.getInputPorts().get(i);
			doSwitch(port.getType());
			s.print(" " + port.getName());
			if (i < actor.getInputPorts().size() - 1) 
				s.print(", ");
		}

		s.print(" ==> ");
		for (int i = 0; i < actor.getOutputPorts().size(); i++) { 
			Port port = actor.getOutputPorts().get(i);
			doSwitch(port.getType());
			s.print(" " + port.getName());
			if (i < actor.getOutputPorts().size() - 1) 
				s.print(", ");
		}

		s.printlnInc(":");
				
		for (Declaration def : actor.getDeclarations()) {
			doSwitch(def);
			s.println(";");
		}	
	
		for (Action a : actor.getActions()) {
			s.println();
			caseAction(a);
		}
		
		s.println();
		caseSchedule(actor.getSchedule());
		
		s.printlnDec("end");
		
		return s;
	}

	@Override
	public Stream caseNetwork(Network network) {
		s.print("network " +  network.getType().getName() + "("); 
		printAnnotations(network.getAnnotations());
		
		for (int i = 0; i < network.getParameters().size(); i++) {
			Variable var = network.getParameters().get(i);
			doSwitch(var.getType());
			s.print(" " + var.getName());
			if (i < network.getParameters().size() - 1) 
				s.print(", ");
		}
		s.print(") ");
		
		for (int i = 0; i < network.getInputPorts().size(); i++) { 
			Port port = network.getInputPorts().get(i);
			doSwitch(port.getType());
			s.print(" " + port.getName());
			if (i < network.getInputPorts().size() - 1) 
				s.print(", ");
		}

		s.print(" ==> ");
		for (int i = 0; i < network.getOutputPorts().size(); i++) { 
			Port port = network.getOutputPorts().get(i);
			doSwitch(port.getType());
			s.print(" " + port.getName());
			if (i < network.getOutputPorts().size() - 1) 
				s.print(", ");
		}

		s.printlnInc(":");
		s.printlnInc("var");
		
		for (Declaration def : network.getDeclarations()) {
			doSwitch(def);	
			s.println(";");
		}
		
		s.println();
		s.printlnDec("entities");
		s.inc();
		for (ActorInstance instance : network.getActors()) {
			caseActorInstance(instance);
			s.println(";");
		}				
				  	
		s.println();
		s.printlnDec("structure");
		s.inc();
		for (Connection c : network.getConnections()) {
			doSwitch(c);
		}

		s.printlnDec("end");
		s.println();
		return s;		
	}

	@Override
	public Stream caseExternalActor(ExternalActor actor) {
		s.print("external actor " +  actor.getType().getName() + "(");
		printAnnotations(actor.getAnnotations());
		for (int i = 0; i < actor.getParameters().size(); i++) {
			Variable var = actor.getParameters().get(i);
			doSwitch(var);
			// s.print(" " + var.getName());
			if (i < actor.getParameters().size() - 1) 
				s.print(", ");
		}
		s.print(") ");
		
		for (int i = 0; i < actor.getInputPorts().size(); i++) { 
			Port port = actor.getInputPorts().get(i);
			doSwitch(port.getType());
			s.print(" " + port.getName());
			if (i < actor.getInputPorts().size() - 1) 
				s.print(", ");
		}

		s.print(" ==> ");
		for (int i = 0; i < actor.getOutputPorts().size(); i++) { 
			Port port = actor.getOutputPorts().get(i);
			doSwitch(port.getType());
			s.print(" " + port.getName());
			if (i < actor.getOutputPorts().size() - 1) 
				s.print(", ");
		}
		s.println();
		s.println("end");
		
		return s;
	}
	
	@Override
	public Stream casePoint2PointConnection(Point2PointConnection connection) {
		PortInstance source = connection.getSource();
		PortInstance target = connection.getTarget();
		s.println(source.getActor().getName() + "{" + source.getActor().getId() + "}." + source.getName() 
				+ " --> " + target.getActor().getName() + "{" + target.getActor().getId() + "}." + target.getName() + ";");				
		return s;
	}

	@Override
	public Stream caseToSink(ToSink connection) {
		PortInstance source = connection.getSource();
		Port target = connection.getSink();
		s.println(source.getActor().getName() + "{" + source.getActor().getId() + "}." + source.getName() + " --> " + target.getName() + ";");				
		return s;
	}
	
	@Override
	public Stream caseFromSource(FromSource connection) {
		Port source = connection.getSource();
		PortInstance target = connection.getTarget();
		s.println(source.getName() + " --> " + target.getActor().getName() + "{" + target.getActor().getId() + "}." + target.getName() + ";");				
		return s;
	}
	
	@Override
	public Stream caseActorInstance(ActorInstance instance) {
		s.print(instance.getName() + "{" + instance.getId() + "} = ");
		doSwitch(instance.getType());
		s.print("(");
		for (int i = 0; i < instance.getActualParameters().size(); i++) {
			TaggedExpression te = instance.getActualParameters().get(i);
			s.print(te.getTag() + " = ");
			doSwitch(te.getExpression());
			if (i < instance.getActualParameters().size() - 1)
				s.print(", ");
		}
		s.print(")");
		return s;
	}
	
	@Override
	public Stream caseAction(Action action) {
		s.print("action");	
		if (!action.getTag().isEmpty()) {
			s.print(" ");
			for (int i = 0; i < action.getTag().size(); i++) {
				s.print(action.getTag().get(i));
				if (i < action.getTag().size() - 1)
					s.print(".");
			}
		}
		s.print("{" + action.getId() + "} ");

						
		s.printlnInc(":");
		
		for (Guard guard : action.getGuards()) {
			caseGuard(guard);
		}
		
		s.printlnInc("var");
		for (Declaration v : action.getDeclarations() ) {
			doSwitch(v);
			s.println(";");
		}

		s.printlnDec("input");
		s.inc();		
		for (PortRead r : action.getInputs()) {
			doSwitch(r);
			s.println(";");
		}

		
		s.printlnDec("do");
		s.inc();		
		
		for (Statement stmt : action.getStatements()) {
			doSwitch(stmt);
		}

		s.printlnDec("output");
		s.inc();		

		for (PortWrite w : action.getOutputs()) {
			doSwitch(w);
			s.println(";");
		}

		s.dec();
		s.printlnDec("end");
		return s;
	}
	
	@Override
	public Stream caseIntegerLiteral(IntegerLiteral lit) {
		s.print(Long.toString(lit.getValue()));

		return s;
	}

	@Override
	public Stream caseFloatLiteral(FloatLiteral lit) {
		s.print(Double.toString(lit.getValue()));

		return s;
	}

	@Override
	public Stream caseBooleanLiteral(BooleanLiteral lit) {
		s.print(lit.isValue() ? "true" : "false");

		return s;
	}

	@Override
	public Stream caseStringLiteral(StringLiteral lit) {
		s.print(lit.getValue());
		
		return s;
	}
	
	@Override
	public Stream caseVariableExpression(VariableExpression var) {
		s.print(var.getVariable().getName() + "{" + var.getVariable().getId() + "}");
		if (!var.getIndex().isEmpty()) {
			s.print("[");
			for (int i = 0; i < var.getIndex().size(); i++) {
				doSwitch(var.getIndex().get(i));
				if (i < var.getIndex().size() - 1)
					s.print(", ");
			}
			s.print("]");
		} 
	
		for (int i = 0; i < var.getMember().size(); i++) {
			s.print(".");
			doSwitch(var.getMember().get(i));
		}

		return s;
	}
	
	@Override
	public Stream caseVariableReference(VariableReference var) {
		s.print(var.getDeclaration().getName() + "{" + var.getDeclaration().getId() + "}");
		if (!var.getIndex().isEmpty()) {
			s.print("[");
			for (int i = 0; i < var.getIndex().size(); i++) {
				doSwitch(var.getIndex().get(i));
				if (i < var.getIndex().size() - 1)
					s.print(", ");
			}
			s.print("]");
		} 
	
		for (int i = 0; i < var.getMember().size(); i++) {
			s.print(".");
			doSwitch(var.getMember().get(i));
		}
		
		return s;
	}

	@Override
	public Stream caseMember(Member member) {
		s.print(member.getName());
		if (!member.getIndex().isEmpty()) {
			s.print("[");
			if (!member.getIndex().isEmpty()) {
				for (int i = 0; i < member.getIndex().size(); i++) {
					doSwitch( member.getIndex().get(i));					
					if (i < member.getIndex().size() - 1) 
						s.print(", ");
				}				
			}
			s.print("]");
		}
		
		return s;
	}
	
	@Override
	public Stream caseIfExpression(IfExpression expr) {
		s.print("if ("); 
		doSwitch(expr.getCondition());
		s.printlnInc(") then"); 	
		doSwitch(expr.getThenExpression());
		s.printlnDec(" else");
		s.inc();
		doSwitch(expr.getElseExpression());
		s.printlnDec("end"); 
		
		return s;
	}

	@Override
	public Stream caseBinaryExpression(BinaryExpression expr) {
		s.print("(");
		doSwitch(expr.getOperand1());
		s.print(" " + expr.getOperator() + " ");
		doSwitch(expr.getOperand2());
		s.print(")");
		
		return s;
	}		

	@Override
	public Stream caseUnaryExpression(UnaryExpression expr) {
		s.print("(");
		s.print(expr.getOperator());
		doSwitch(expr.getOperand());		
		s.print(")");
		
		return s;
	}

	@Override
	public Stream caseListExpression(ListExpression var) {		
		if (!var.getGenerators().isEmpty()) {
			for (Generator g : var.getGenerators()) {
				s.print("[");
				for (int i = 0; i < g.getDeclarations().size(); i++) {
					doSwitch(g.getDeclarations().get(i));
					if (i < g.getDeclarations().size() - 1)
						s.print(", ");
				}
			}
			
			s.print(" : ");
			for (int i = 0; i < var.getExpressions().size(); i++) {
				doSwitch(var.getExpressions().get(i));
				if (i < var.getExpressions().size() - 1)
					s.print(", ");
			}

			for (Generator g : var.getGenerators()) {
				s.print("]");				
			}
		} else {
			s.print("[");					
			for (int i = 0; i < var.getExpressions().size(); i++) {
				doSwitch(var.getExpressions().get(i));
				if (i < var.getExpressions().size() - 1)
					s.print(", ");
			}
			s.print("]");					
		}

		return s;
	}
	
	@Override
	public Stream caseFunctionCall(FunctionCall expr) {		
		if (expr.getFunction() instanceof VariableExpression) {
			VariableExpression ve = (VariableExpression) expr.getFunction(); 
			s.print(ve.getVariable().getName() + "{" + ve.getVariable().getId() + "} (");
		} else {
			doSwitch(expr.getFunction());
			s.print("(");			
		}

		for (int i = 0; i < expr.getParameters().size(); i++) {
			doSwitch(expr.getParameters().get(i));
			if (i < expr.getParameters().size() - 1)
				s.print(", ");
		}
		s.print(")");
		
		return s;
	}

	@Override
	public Stream caseTypeConstructorCall(TypeConstructorCall expr) {
		
		s.print(expr.getTypedef().getName() + "{" + expr.getTypedef().getId() + "}:" + expr.getName() + "(");
		for (int i = 0; i < expr.getParameters().size(); i++) {
			doSwitch(expr.getParameters().get(i));
			if (i < expr.getParameters().size() - 1)
				s.print(", ");
		}
		s.print(")");
		
		return s;
	}

	@Override
	public Stream caseAssign(Assign assign) {
		doSwitch(assign.getTarget());
		s.print(" := "); 		
		doSwitch(assign.getExpression());
		s.println(";"); 	
		return s;
	}

	@Override
	public Stream caseProcCall(ProcCall call) {
		s.print(call.getProcedure().getName() + "{" + call.getProcedure().getId() + "}(");
		for (int i = 0; i < call.getInParameters().size(); i++) {
			doSwitch(call.getInParameters().get(i));
			if (i < call.getInParameters().size())
				s.print(", ");
		}
		
		if (!call.getOutParameters().isEmpty())
			s.print(", ");
		
		for (int i = 0; i < call.getOutParameters().size(); i++) {
			doSwitch(call.getOutParameters().get(i));
			if (i < call.getOutParameters().size())
				s.print(", ");
		}

		
		s.println(";");
		
		return s;
	}

	@Override
	public Stream caseWhileLoop(WhileLoop stmt) {
		s.print("while ("); 
		doSwitch(stmt.getCondition());
		s.printlnInc(")");	
		doSwitch(stmt.getBody());
		s.printlnDec("end"); 
		
		return s;
	}

	@Override
	public Stream caseIfStatement(IfStatement stmt) {
		s.print("if ("); 
		doSwitch(stmt.getCondition());
		s.printlnInc(") then"); 	
		doSwitch(stmt.getThenBlock());
		if (!stmt.getElseBlock().getStatements().isEmpty()) {
			s.printlnDec("else");
			s.inc();
			doSwitch(stmt.getElseBlock());
		}
		s.printlnDec("end"); 
		
		return s;
	}

	@Override 
	public Stream caseForEach(ForEach stmt) {
		s.printlnInc("Foreach ");
		for (Generator g : stmt.getGenerators()) {
			doSwitch(g);
			s.println();
		}

		doSwitch(stmt.getBody());
		s.printlnDec("end");
		return s;
	}
	
	@Override 
	public Stream caseGenerator(Generator generator) {
		s.print("Generator: ");
		for (Declaration decl : generator.getDeclarations()) {
			doSwitch(decl);
		}
		s.print(" in ");
		doSwitch(generator.getSource());
		return s;
	}
	
	@Override
	public Stream caseBlock(Block block) {
		if (block.getDeclarations().size() > 0 ) {
			s.printlnInc("var"); 
		
			for (int i = 0; i < block.getDeclarations().size(); i++) {
				doSwitch(block.getDeclarations().get(i));
				s.println(";");
			}
			s.printlnDec("do"); 
			s.inc();
		} else {
			s.printlnInc("do");
		}
		for (int i = 0; i < block.getStatements().size(); i++) {
			doSwitch(block.getStatements().get(i));
		}
				
		s.printlnDec("end");
		
		return s;
	}

	@Override
	public Stream casePortRead(PortRead read) {
		s.print(read.getPort().getName() + ":[");
		for (int i = 0; i < read.getVariables().size(); i++) {			
			doSwitch(read.getVariables().get(i));
			if (i < read.getVariables().size() - 1) 
				s.print(", ");
		}
		s.print("]");
		if (read.getRepeat() != null) {
			s.print(" repeat ");
			doSwitch(read.getRepeat());
		}
		return s;
	}
	
	@Override
	public Stream casePortPeek(PortPeek peek) {
		doSwitch(peek.getVariable());
		s.print(" := " + peek.getPort().getName() + "@" + peek.getPosition());
		
		return s;
	}
	
	@Override
	public Stream casePortWrite(PortWrite write) {
		for (Statement stmt : write.getStatements()) {
			doSwitch(stmt);		
		}
		s.print("@" + write.getPort().getName() + ": [");
		
		for (int i = 0; i < write.getExpressions().size(); i++) {
			doSwitch( write.getExpressions().get(i));
			if (i < write.getExpressions().size() - 1)
				s.print(", ");
		}
		s.print("]");
		
		return s;
	}

	@Override
	public Stream caseForwardDeclaration(ForwardDeclaration declaration) {
		s.print("forward ");
		
		doSwitch(declaration.getType());
		s.print(" " + declaration.getName() + "{" + declaration.getId() + "}");
		
		return s;
	}
	
	@Override
	public Stream caseVariable(Variable variable) {
		if (variable.isConstant()) 
			s.print("const ");
		
		doSwitch(variable.getType());
		s.print(" " + variable.getName() + "{" + variable.getId() + "}");
		
		if (variable.getInitValue() != null) {
			s.print(" := ");
			doSwitch(variable.getInitValue());
		}
		
		return s;
	}
	
	@Override
	public Stream caseVariableImport(VariableImport variable) {		
		s.print("variableImport " + variable.getName() +  "{" + variable.getId() + "} from " + Util.packQualifiedName(variable.getNamespace()));
		return s;
	}
	
	@Override
	public Stream caseVariableExternal(VariableExternal variable) {		
		s.print("variableExternal ");
		doSwitch(variable.getType());
		s.print(variable.getName() +  "{" + variable.getId() + "} ");
		printAnnotations(variable.getAnnotations());
		return s;
	}
	
	@Override
	public Stream caseTypeDeclarationImport(TypeDeclarationImport typedecl) {
		s.print("typeImport " + typedecl.getName() + "{" + typedecl.getId()+ "} from " + Util.packQualifiedName(typedecl.getNamespace()));
		return s;
	}
	
	@Override
	public Stream caseLambdaExpression(LambdaExpression lambda) {
		s.print("lambda{" + lambda.getId() + "}(");
		for (int i = 0; i < lambda.getParameters().size(); i++) {
			doSwitch(lambda.getParameters().get(i));
			if (i < lambda.getParameters().size() - 1)
				s.print(", ");
		}
		s.print(") :: ");
		doSwitch(lambda.getType());
		s.printlnInc(""); 
		if (lambda.getDeclarations().size() > 0 ) {
			s.printlnInc("var"); 
		
			for (int i = 0; i < lambda.getDeclarations().size(); i++) {
				doSwitch(lambda.getDeclarations().get(i));
				s.println(";");
			}
			s.printlnDec("do"); 
			s.inc();
		} else {
			s.printlnInc("do");
		}
		
		doSwitch(lambda.getBody()); 		
		s.dec();
		s.dec();
		
		return s;
	}
	
	
	@Override
	public Stream caseProcExpression(ProcExpression proc) {
		s.print("proc{" + proc.getId() + "}(");

		for (int i = 0; i < proc.getParameters().size(); i++) {
			doSwitch(proc.getParameters().get(i));
			if (i < proc.getParameters().size() - 1)
				s.print(", ");
		}

		/*
		for (int i = 0; i < proc.getOutputs().size(); i++) {
			doSwitch(proc.getParameters().get(i));
			if (i < proc.getParameters().size() - 1)
				s.print(", ");
		}
		*/

		s.print(") :: ");

		doSwitch(proc.getType());

		s.printlnInc(":");
			
		doSwitch(proc.getBody()); 		
		s.dec();
		return s;
	}

	@Override
	public Stream caseGuard(Guard guard) {
		s.printlnInc("guard:");
		
		for (Declaration d : guard.getDeclarations()) {
			doSwitch(d);
			s.println(";");
		}
		
		for (PortPeek peek : guard.getPeeks()) {
			doSwitch(peek);
			s.println(";");
		}
		
		doSwitch(guard.getBody());
		s.println();
		s.dec();

		return s;
	}
	
	@Override
	public Stream caseTypeConstructor(TypeConstructor tc) {
		s.print(tc.getName() + "(");
		for (int i = 0; i < tc.getParameters().size(); i++) {
			doSwitch(tc.getParameters().get(i).getType());
			s.print(" " + tc.getParameters().get(i).getName());
			if (i < tc.getParameters().size() - 1)
				s.print(", ");
		}
		s.print(")");
		
		return s;
	}

	@Override
	public Stream caseTypeDeclaration(TypeDeclaration typeDecl) {
		s.printlnInc("type " + typeDecl.getName() + "{" + typeDecl.getId() + "} : ");
		doSwitch(typeDecl.getType());
		s.println();
		doSwitch(typeDecl.getConstructor());
		s.println();
		s.printlnDec("end");

		return s;
	}

	@Override
	public Stream caseReturnValue(ReturnValue returnValue) {
		s.print("return ");
		doSwitch(returnValue.getValue());

		return s;
	}

	@Override
	public Stream caseTypeActor(TypeActor type) {
		s.print(type.getName());
		
		return s;
	}
	
	@Override
	public Stream caseTypeBool(TypeBool type) {
		s.print("bool");
		
		return s;
	}

	@Override
	public Stream caseTypeInt(TypeInt type) {
		s.print("int");
		if (type.getSize() != null) {
			s.print("(size=");
			doSwitch(type.getSize());
			s.print(")");
		} 
		
		return s;
	}

	@Override
	public Stream caseTypeFloat(TypeFloat type) {
		s.print("float");

		return s;
	}

	@Override
	public Stream caseTypeList(TypeList type) {
		s.print("List(type:");
		doSwitch(type.getType());
		s.print(", size=");
		doSwitch(type.getSize());
		s.print(")");
		
		return s;
	}

	@Override
	public Stream caseTypeUint(TypeUint type) {
		s.print("uint");
		if (type.getSize() != null) {
			s.print("(size=");
			doSwitch(type.getSize());
			s.print(")");
		} 
		
		return s;
	}	

	@Override
	public Stream caseTypeString(TypeString object) {
		s.println("string");

		return s;
	}
	
	@Override
	public Stream caseTypeRecord(TypeRecord type){		
		s.print("tuple(");
		for (int i = 0; i < type.getMembers().size(); i++) {			
			doSwitch(type.getMembers().get(i).getType());
			s.print(" " + type.getMembers().get(i).getName());
			if (i < type.getMembers().size() - 1)
				s.print(", ");
		}
		s.print(")");
		return s;
	}
	
	@Override
	public Stream caseTypeUser(TypeUser user) {
		s.print("userType{" + user.getDeclaration().getId() + "}");	
		return s;
	}
	
	@Override
	public Stream caseTypeLambda(TypeLambda lambda) {
		s.print("[");
		for (int i = 0; i < lambda.getInputTypes().size(); i++) {
			doSwitch(lambda.getInputTypes().get(i));
			if (i < lambda.getInputTypes().size() - 1)
				s.print(", ");
		}
		s.print(" --> ");
		if (lambda.getOutputType() != null) {
			doSwitch(lambda.getOutputType());
		}
		s.print("]");
		return s;
	}

	@Override
	public Stream caseTypeUndef(TypeUndef object) {
		s.print("typeUndef");
		
		return s;
	}
	
	public Stream defaultCase(EObject object) {
		return null;
	}

	@Override
 	public Stream caseSchedule(Schedule schedule) {
 		s.printlnInc("schedule:");
 		s.print("Initial state: ");
 		s.println(schedule.getInitialState().getName());
 		s.printlnInc("states:");
 		for (State state : schedule.getStates()) {
 			Map<Action, String> action2TargetMap = (Map<Action, String>) state.getAction2TargetMap();
 			PriorityGraph graph = (PriorityGraph) state.getPriorityGraph();
 			
 			s.printlnInc("state: " + state.getName());
 			List<Action> sorted = graph.getOneTopologicalOrder();
 			for (Action action : sorted) {
 				s.println("{" + action.getId() + "} --> " + action2TargetMap.get(action));
 			}
 			
 			for (PriorityGraph.Vertex vertex : graph.getVertices()) {
 				for (PriorityGraph.Edge edge : vertex.getOutgoingEdges()) { 		 		
 					s.println(edge.getSource().getAction().getId() + " > " +  edge.getTarget().getAction().getId());
 				}
 	 		}
// 			for (PriorityGraph.Edge edge : graph.getEdges()) {
// 				s.println(edge.getSource().getAction().getId() + " > " + edge.getTarget().getAction().getId() );
// 			}
 			s.printlnDec("end");
 		}
		s.printlnDec("end");
 		s.print("freerunners: ");
 		for (int i = 0; i < schedule.getFreeRunners().size(); i++) {
 			s.print(schedule.getFreeRunners().get(i).getId());
 			if (i < schedule.getFreeRunners().size() - 1)
 				s.print(", ");
 		}
 		s.println();
 		
 		s.printlnInc("complete priority graph: ");
 		PriorityGraph graph = (PriorityGraph) schedule.getPriorityGraph();
		s.printlnInc("actions: ");
 		for (PriorityGraph.Vertex vertex : graph.getVertices()) {
 			s.println(vertex.getAction().getId());
 		}
 		s.printlnDec("edges:");
 		s.inc();
 		for (PriorityGraph.Vertex vertex : graph.getVertices()) {
			for (PriorityGraph.Edge edge : vertex.getOutgoingEdges()) { 		 		
				s.println(edge.getSource().getAction().getId() + " > " +  edge.getTarget().getAction().getId());
			}
 		}
 		s.printlnDec("end");
 		s.printlnDec("end");
 		s.printlnDec("end");
 		
 		return s;
 	}

	private void printAnnotations(EList<Annotation> annotations) {
		for (Annotation a : annotations) {
			s.print("Annotation: " + a.getName() + "(");
			for (int i = 0; i < a.getArguments().size(); i++) {
				AnnotationArgument ap = a.getArguments().get(i);
				s.print(ap.getId() + "='" + ap.getValue());
				if (i < a.getArguments().size() - 1)
					s.print(", ");
			}
			s.print("')");
		}
	}
	
}
