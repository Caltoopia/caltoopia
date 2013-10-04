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

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.eclipse.emf.ecore.EObject;
import org.caltoopia.frontend.cal.AstAction;
import org.caltoopia.frontend.cal.AstActor;
import org.caltoopia.frontend.cal.AstAnnotation;
import org.caltoopia.frontend.cal.AstAnnotationArgument;
import org.caltoopia.frontend.cal.AstEntity;
import org.caltoopia.frontend.cal.AstExpression;
import org.caltoopia.frontend.cal.AstExpressionBoolean;
import org.caltoopia.frontend.cal.AstExpressionFloat;
import org.caltoopia.frontend.cal.AstExpressionInteger;
import org.caltoopia.frontend.cal.AstExpressionString;
import org.caltoopia.frontend.cal.AstInputPattern;
import org.caltoopia.frontend.cal.AstMemberAccess;
import org.caltoopia.frontend.cal.AstFunction;
import org.caltoopia.frontend.cal.AstNamespace;
import org.caltoopia.frontend.cal.AstOutputPattern;
import org.caltoopia.frontend.cal.AstPattern;
import org.caltoopia.frontend.cal.AstPort;
import org.caltoopia.frontend.cal.AstProcedure;
import org.caltoopia.frontend.cal.AstSubPattern;
import org.caltoopia.frontend.cal.AstTaggedTuple;
import org.caltoopia.frontend.cal.AstType;
import org.caltoopia.frontend.cal.AstTypeUser;
import org.caltoopia.frontend.cal.AstVariable;
import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.ir.Action;
import org.caltoopia.ir.Annotation;
import org.caltoopia.ir.AnnotationArgument;
import org.caltoopia.ir.ExprAlternative;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.ForwardDeclaration;
import org.caltoopia.ir.Guard;
import org.caltoopia.ir.IfExpression;
import org.caltoopia.ir.StmtAlternative;
import org.caltoopia.ir.TaggedTuple;
import org.caltoopia.ir.TaggedTupleFieldRead;
import org.caltoopia.ir.TypeDeclaration;
import org.caltoopia.ir.TypeDeclarationImport;
import org.caltoopia.ir.TypeGuard;
import org.caltoopia.ir.TypeLambda;
import org.caltoopia.ir.TypeProc;
import org.caltoopia.ir.TypeUser;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.AbstractActor;
import org.caltoopia.ir.Actor;
import org.caltoopia.ir.ActorInstance;
import org.caltoopia.ir.Assign;
import org.caltoopia.ir.BinaryExpression;
import org.caltoopia.ir.Block;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.IntegerLiteral;
import org.caltoopia.ir.Member;
import org.caltoopia.ir.Namespace;
import org.caltoopia.ir.PortInstance;
import org.caltoopia.ir.StringLiteral;
import org.caltoopia.ir.FloatLiteral;
import org.caltoopia.ir.BooleanLiteral;
import org.caltoopia.ir.IrFactory;
import org.caltoopia.ir.Port;
import org.caltoopia.ir.Scope;
import org.caltoopia.ir.Statement;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.TypeConstructorCall;
import org.caltoopia.ir.TypeList;
import org.caltoopia.ir.TypeTuple;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.VariableExternal;
import org.caltoopia.ir.VariableImport;
import org.caltoopia.ir.VariableReference;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.types.TypeException;
import org.caltoopia.types.TypeSystem;
import org.caltoopia.types.TypeConverter;

public class Util {

	static private int variableID = 0;
	
	// This is a map from AstVariable & function definitions to corresponding IR declaration
	static private Map<EObject, Declaration> defs = new HashMap<EObject, Declaration>();
	
	static private int definitionID = 0;	

	public static void clearDefs() {
		defs.clear();
	}

	public static void defsput(EObject astObject, Declaration irObject) {
		defs.put(astObject, irObject);
	}
	
	public static void defsprint() {
		System.out.println("------------------ D E F S ------------------");
		for (Declaration decl : defs.values()) {
			System.out.println(decl.getName());
		}
		System.out.println("---------------------------------------------");
	}
	
	public static VariableImport createImportedVariableDeclaration(List<String> NamespaceName, AstVariable p) {
		VariableImport var = IrFactory.eINSTANCE.createVariableImport();
		var.setName(p.getName());
		var.setId(getDefinitionId());
		var.getNamespace().addAll(NamespaceName);
		defsput(p, var);
		
		return var;
	}
	
	public static TypeDeclarationImport createImportedTypeDeclaration(List<String> NamespaceName, AstTypeUser t) {
		TypeDeclarationImport typedef = IrFactory.eINSTANCE.createTypeDeclarationImport();
		typedef.setName(t.getName());
		typedef.setId(getDefinitionId());
		typedef.getNamespace().addAll(NamespaceName);
		defsput(t, typedef);

		return typedef;
	}
		
	public static Variable createTmpVariable(Scope scope, Type type, Expression value) {
		Variable var = IrFactory.eINSTANCE.createVariable();
		var.setName(Util.getUniqueName());
		var.setId(getDefinitionId());
		var.setType(type);
		var.setScope(scope);
		var.setInitValue(value);
		scope.getDeclarations().add(var);
		
		return var;
	}	
	
	public static Variable createTmpVariable(Scope scope, AstType t, AstExpression v, boolean approximate) {
		Type type = TypeConverter.convert(scope, t, approximate);
		Expression value = CreateIrExpression.convert(scope, v);
		return createTmpVariable(scope, type, value);
	}	
		
	public static Variable createVariable(Scope scope, AstVariable astVariable, boolean approximate) {
		try {
			Type type = TypeConverter.getTypeOfAstVariable(astVariable, null, null, scope, approximate);
			Expression value = CreateIrExpression.convert(scope, astVariable.getValue());
			return createVariable(scope, astVariable, type, value);
		} catch (TypeException x) {
			System.err.print("Internal error: " + x.getMessage());
			return null;
		}
	}
	
	public static Variable createVariable(Scope scope, AstVariable astVariable, Expression value, boolean approximate) {
		// Copy dimensions from the variable side to the type in 
		// case the ORCC C-style array declarations have been used
		astVariable.getType().getDimensions().addAll(astVariable.getDimensions());
		
		Type type = TypeConverter.convert(scope, astVariable.getType(), approximate);	
		return createVariable(scope, astVariable, type, value);
	}
	
	public static Variable createVariable(Scope scope, AstVariable astVariable, Type type, Expression value) {
		Variable var = IrFactory.eINSTANCE.createVariable();
		var.setName(astVariable.getName());
		var.setId(getDefinitionId());	
		var.setType(type);
		var.setConstant(astVariable.isConstant());
		var.setScope(scope);
		var.setInitValue(value);
		
		scope.getDeclarations().add(var);

		defsput(astVariable, var);

		return var;
	}		
	
	public static Declaration createForwardFunctionDeclaration(Scope scope, AstFunction f, boolean approximate) {
		ForwardDeclaration fun = IrFactory.eINSTANCE.createForwardDeclaration();
		fun.setName(f.getName());
		fun.setId(getDefinitionId());		
		Type type = TypeConverter.convert(scope, f.getType(), approximate);
		fun.setType(type);
		fun.setScope(scope);
		
		defsput(f, fun);
		
		return fun;
	}
	
	public static Declaration createImportedFunctionDeclaration(List<String> NamespaceName, AstFunction f) {
		VariableImport fun = IrFactory.eINSTANCE.createVariableImport();		
		fun.setName(f.getName());
		fun.setId(getDefinitionId());		
		fun.getNamespace().addAll(NamespaceName);
			
		defsput(f, fun);
		return fun;
	}
	
	// This method only declares the procedure, the definition comes later in order to allow recursion etc.
	public static Declaration createForwardProcedureDeclaration(Scope peek, AstProcedure p) {
		ForwardDeclaration proc = IrFactory.eINSTANCE.createForwardDeclaration();
		proc.setName(p.getName());
		proc.setId(getDefinitionId());		
		proc.setType(TypeSystem.createTypeUndef());//FIXME
		proc.setScope(peek); 
		
		defsput(p, proc);
		
		return proc;
	}

	public static VariableImport createImportedProcedureDeclaration(List<String> NameSpaceName, AstProcedure p) {
		VariableImport proc = IrFactory.eINSTANCE.createVariableImport();
		proc.setName(p.getName());
		proc.setId(getDefinitionId());			
		proc.getNamespace().addAll(NameSpaceName);
		defsput(p, proc);
		
		return proc;
	}
	
	public static VariableExternal createExternalDeclaration(Scope scope, EObject d, boolean approximate)  {
		VariableExternal decl = IrFactory.eINSTANCE.createVariableExternal();	
		decl.setId(getDefinitionId());			
		decl.setScope(scope);
		
		if (d instanceof AstVariable) {
			AstVariable v = (AstVariable) d;
			decl.setName(v.getName());
			try {
				Type type = TypeConverter.getTypeOfAstVariable(v, null, null, scope, approximate);
				decl.setType(type);	
				for (AstAnnotation astAnnotation : v.getAnnotations()) {
					Annotation annotation = createAnnotation(astAnnotation);
					decl.getAnnotations().add(annotation);
				}
			} catch (TypeException x) {
				System.err.print("Internal error: " + x.getMessage());
				return null;
			}
			
		} else if (d instanceof AstFunction) {
			AstFunction f = (AstFunction) d;
			decl.setName(f.getName());
			TypeLambda type = IrFactory.eINSTANCE.createTypeLambda();
			try {
				for (AstVariable param : f.getParameters()) {
					Type parameterType = TypeConverter.getTypeOfAstVariable(param, null, null, scope, approximate);
					type.getInputTypes().add(parameterType);
				}					
				type.setOutputType(TypeConverter.convert(scope, f.getType(), approximate));			
				decl.setType(type);
				for (AstAnnotation astAnnotation : f.getAnnotations()) {
					Annotation annotation = createAnnotation(astAnnotation);
					decl.getAnnotations().add(annotation);
				}
			} catch (TypeException x) {
				System.err.print("Internal error: " + x.getMessage());
				return null;
			}
		} else {
			AstProcedure p = (AstProcedure) d;
			decl.setName(p.getName());
			TypeProc type = IrFactory.eINSTANCE.createTypeProc();
			
			try {
				for (AstVariable param : p.getParameters()) {
					Type parameterType = TypeConverter.getTypeOfAstVariable(param, null, null, scope, approximate);
					type.getInputTypes().add(parameterType);
				}
				decl.setType(type);
				for (AstAnnotation astAnnotation : p.getAnnotations()) {
					Annotation annotation = createAnnotation(astAnnotation);
					decl.getAnnotations().add(annotation);
				}
			} catch (TypeException x) {
				System.err.print("Internal error: " + x.getMessage());
				return null;
			}
		}
		
		defsput(d, decl);

		return decl;
	}

	
	static Annotation createAnnotation(AstAnnotation astAnnotation) {
		Annotation annotation = IrFactory.eINSTANCE.createAnnotation();
		annotation.setName(astAnnotation.getName());
		for (AstAnnotationArgument astArg : astAnnotation.getArguments()) {
			AnnotationArgument arg = IrFactory.eINSTANCE.createAnnotationArgument();
			arg.setId(astArg.getName());
			arg.setValue(astArg.getValue());
			annotation.getArguments().add(arg);
		}
		
		return annotation;
	}

	public static VariableExpression createVariableExpression(Scope context, Variable var) {
		VariableExpression result = IrFactory.eINSTANCE.createVariableExpression();
		result.setId(Util.getDefinitionId());
		result.setVariable(var);
		result.setContext(context);
		return result;
	}

	public static VariableReference createVariableReference(Variable varDecl) {
		VariableReference result = IrFactory.eINSTANCE.createVariableReference();		
		result.setDeclaration(varDecl);
		return result;
	}
	
	public static Port createPort(Scope scope, AstPort p, boolean approximate) {
		Port port = IrFactory.eINSTANCE.createPort();
		port.setId(Util.getDefinitionId());
		port.setName(p.getName());
		Type type = TypeConverter.convert(scope, p.getType(), approximate);
		port.setType(type);
		
		return port;
	}
	
	public static Port createInputPort(Scope scope, AstInputPattern pattern, boolean approximate) {
		Port port = IrFactory.eINSTANCE.createPort();
		AstPort astPort = getAstPort(pattern);
		port.setName(astPort.getName());
		Type type = TypeConverter.convert(scope, astPort.getType(), approximate);
		port.setType(type);
		
		return port;
	}
	
	public static AstPort getAstPort(AstInputPattern pattern) {
		AstPort astPort = null;
		if (pattern.getPort() != null) {
			astPort = pattern.getPort();
		} else {
			AstAction action = (AstAction) pattern.eContainer();
			List<AstInputPattern> inputs = action.getInputs();
			AstActor actor = (AstActor) action.eContainer();
			List<AstPort> ports = actor.getInputs();
			for (int i = 0; i < inputs.size(); i++) {
				if (inputs.get(i) == pattern) {
					astPort = ports.get(i);
				}
			}
		}
		assert(astPort != null);
		return astPort;
	}
	
	public static Port createOutputPort(Scope scope, AstOutputPattern pattern, boolean approximate) {
		Port port = IrFactory.eINSTANCE.createPort();
		AstPort astPort = null;
		if (pattern.getPort() != null) {
			astPort = pattern.getPort();
		} else {
			AstAction action = (AstAction) pattern.eContainer();
			List<AstOutputPattern> outputs = action.getOutputs();
			AstActor actor = (AstActor) action.eContainer();
			List<AstPort> ports = actor.getOutputs();
			for (int i = 0; i < outputs.size(); i++) {
				if (outputs.get(i) == pattern) {
					astPort = ports.get(i);
				}
			}
		}
		assert(astPort != null);
		port.setName(astPort.getName());
		Type type = TypeConverter.convert(scope, astPort.getType(), approximate);
		port.setType(type);
		
		return port;
	}

	public static String getUniqueName() {			
		return "U__" + variableID++;
	}
	
	public static String getDefinitionId() {			
		return "$d" + definitionID++;
	}
	
	public static Statement  createAssignment(Scope scope, Variable var, AstExpression expr) {
		VariableReference target = createVariableReference(var);
		Statement stmt = createAssignment(scope, target, expr);
		return stmt;
	}	
	
	public static Statement createAssignment(Scope scope, VariableReference target, AstExpression expr) {
		Assign assign = IrFactory.eINSTANCE.createAssign();
		
		Expression value = CreateIrExpression.convert(scope, expr);
		value.setContext(scope); 
		assign.setExpression(value);	
		assign.setTarget(target);	
		return assign;
	}

	public static IntegerLiteral createIntegerLiteral(AstExpressionInteger e) {
		IntegerLiteral lit = IrFactory.eINSTANCE.createIntegerLiteral();
		lit.setId(Util.getDefinitionId());
		lit.setValue(e.getValue());
		lit.setType(TypeSystem.createTypeInt());
		return lit;
	}

	public static IntegerLiteral createIntegerLiteral(int i) {
		IntegerLiteral lit = IrFactory.eINSTANCE.createIntegerLiteral();
		lit.setId(Util.getDefinitionId());
		lit.setType(TypeSystem.createTypeInt());
		lit.setValue(i);	
		return lit;
	}
	
	public static FloatLiteral createFloatLiteral(AstExpressionFloat e) {
		FloatLiteral lit = IrFactory.eINSTANCE.createFloatLiteral();
		lit.setId(Util.getDefinitionId());
		lit.setValue(e.getValue());
		lit.setType(TypeSystem.createTypeFloat());
		return lit;
	}

	public static BooleanLiteral createBooleanLiteral(AstExpressionBoolean e) {
		BooleanLiteral lit = IrFactory.eINSTANCE.createBooleanLiteral();
		lit.setId(Util.getDefinitionId());
		lit.setValue(e.isValue());
		lit.setType(TypeSystem.createTypeBool());
		return lit;
	}
	
	private static Expression createFalseLiteral() {
		BooleanLiteral lit = IrFactory.eINSTANCE.createBooleanLiteral();
		lit.setId(Util.getDefinitionId());
		lit.setValue(false);
		lit.setType(TypeSystem.createTypeBool());

		return lit;
	}

	public static StringLiteral createStringLiteral(AstExpressionString e) {
		StringLiteral lit = IrFactory.eINSTANCE.createStringLiteral();
		lit.setId(Util.getDefinitionId());
		lit.setValue(e.getValue());
		lit.setType(TypeSystem.createTypeString());
		return lit;
	}
	
	public static Expression createBinaryExpression(Scope scope, Expression expr1, String operator, Expression expr2, Type resultType) {
		BinaryExpression binExpr = IrFactory.eINSTANCE.createBinaryExpression();
		binExpr.setId(Util.getDefinitionId());
		binExpr.setOperand1(expr1);
		binExpr.setOperator(operator);
		binExpr.setOperand2(expr2);

		binExpr.setContext(scope);
		binExpr.setType(resultType);
		return binExpr;
	}
	
	public static Block createBlock(Scope currentBlock) {
		Block block = IrFactory.eINSTANCE.createBlock();
		block.setId(Util.getDefinitionId());
		block.setOuter(currentBlock);

		return block;
	}

	public static Expression createIndexedVariableReference(Scope scope, Variable var, Variable indexVar) {
		VariableExpression varRef = IrFactory.eINSTANCE.createVariableExpression();
		varRef.setId(Util.getDefinitionId());
		varRef.setContext(scope);
		TypeList t=(TypeList) var.getType();
		varRef.setType(t.getType());
		varRef.setVariable(var);
		
		VariableExpression indexExpr = IrFactory.eINSTANCE.createVariableExpression();
		indexExpr.setId(Util.getDefinitionId());
		indexExpr.setContext(scope);
		indexExpr.setVariable(indexVar);
		indexExpr.setType(indexVar.getType());
		
		varRef.getIndex().add(indexExpr);

		return varRef;	
	}

	public static VariableReference createIndexedVariableReference(Variable var, Expression index) {
		VariableReference varRef = IrFactory.eINSTANCE.createVariableReference();
		varRef.setDeclaration(var);
		if(var.getType() instanceof TypeList) {
			varRef.setType(((TypeList)var.getType()).getType());
		} else {
			varRef.setType(var.getType());			
		}
		varRef.getIndex().add(index);

		return varRef;	
	}
	
	
	static TypeActor createType(Scope scope, AstEntity entity) {
		TypeActor type = IrFactory.eINSTANCE.createTypeActor();
		
		type.setName(entity.getActor().getName());

		if (entity.eContainer() != null) {			
			AstNamespace namespace = (AstNamespace) entity.eContainer();
			
			type.getNamespace().add(namespace.getName()); 
			while (namespace.eContainer() != null) {
				namespace = (AstNamespace) namespace.eContainer();
				type.getNamespace().add(0, namespace.getName());
			}						
		} else {
			type.getNamespace().add(ActorDirectory.DEFAULTNAMESPACE);
		}				
		
		return type;
	}
	
	public static boolean withinActor(Expression expr) {
		Scope scope = expr.getContext();
		while(scope !=null) {
			if(scope instanceof Actor) {
				return true;
			}
			Scope outer = scope.getOuter();
			if(scope == outer) {
				break;
			}
			scope = outer;
		}
		return false;
	}
    
	public static Declaration findIrDeclaration(EObject astObject) {
		Declaration irObject = defs.get(astObject);
				
		if (irObject == null) {			
			String name = "unknown";
			if (astObject instanceof AstVariable) {
				name = ((AstVariable) astObject).getName() + " (variable)";
			} else if (astObject instanceof AstTypeUser) {
				name = ((AstTypeUser) astObject).getName() + " (typedef)";
			} else if (astObject instanceof AstFunction) {
				name = ((AstFunction) astObject).getName() + " (function)";
			} else if (astObject instanceof AstProcedure) {
				name = ((AstProcedure) astObject).getName() + " (procedure)";
			}
			// defsprint();
			throw new RuntimeException("Internal error in findIrDeclaration for '" + name + "'");
		}

		return irObject;
	}
		
	public static Member createFieldAccess(AstMemberAccess field, Scope scope) {
		Member member = IrFactory.eINSTANCE.createMember();
		member.setName(field.getName());
		// Type type = TypeConverter.typeOf(scope, field);
		// member.setType(type);
		for (AstExpression i : field.getMemberIndex()) {
			Expression ve = CreateIrExpression.convert(scope, i);
			member.getIndex().add(ve);
		}
		return member;
	}
	
	public static Statement createIncrementStatement(Scope scope, Variable var) {
		Assign assign = IrFactory.eINSTANCE.createAssign();
		VariableReference varUse = createVariableReference(var);
		Expression varRef = createVariableExpression(scope, var);
		Expression value = createBinaryExpression(scope, varRef, "+", createIntegerLiteral(1), TypeSystem.createTypeInt());
		value.setId(Util.getDefinitionId());
		assign.setTarget(varUse);
		assign.setExpression(value);
		return assign;
	}
	

	public static boolean isTypeList(Type type) {
		if (type instanceof TypeList) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isTypeTuple(Type type) {
		if (type instanceof TypeTuple) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isTypeActor(Type type) {
		if (type instanceof AbstractActor) {
			return true;
		} else {
			return false;
		}
	}
	
	public static TypeConstructorCall createTypeConstructorCall(Scope scope, AstType astType, boolean approximate) {
		TypeConstructorCall ctor = IrFactory.eINSTANCE.createTypeConstructorCall();	
		ctor.setId(Util.getDefinitionId());
		Type type = TypeConverter.convert(scope, astType, approximate);
		ctor.setType(type);
		
		return ctor;
	}

	public static PortInstance getInputPort(ActorInstance actor, String portName) {
		for (PortInstance port : actor.getInputs()) {
			if (portName.equals(port.getName())) {
				return port;
			}
		}
		
		return null;
	}

	public static PortInstance getOutputPort(ActorInstance actor, String portName) {
		for (PortInstance port : actor.getOutputs()) {
			if (portName.equals(port.getName())) {
				return port;
			}
		}
		
		return null;
	}

	public static Port getInputPort(AbstractActor actor, String portName) {
		for (Port port : actor.getInputPorts()) {
			if (portName.equals(port.getName())) {
				return port;
			}
		}

		return null;
	}
	
	public static Port getOutputPort(AbstractActor actor, String portName) {
		for (Port port : actor.getOutputPorts()) {
			if (portName.equals(port.getName())) {
				return port;
			}
		}

		return null;
	}
	
//	public static void doPattern(Scope scope, Type type, AstPattern astPattern, Expression expr) { 
//			
//		if (astPattern.getVariable() != null) {					
//			Util.createVariable(scope, astPattern.getVariable(), type, expr);
//		} else {		
//			TypeUser tu = (TypeUser) type;
//
//			TaggedTuple tt = null;
//			TypeDeclaration typedef = (TypeDeclaration) tu.getDeclaration();            
//	 
//			for (TaggedTuple tmp :  ((TypeTuple) typedef.getType()).getTaggedTuples()) {
//				if (tmp.getTag().equals(astPattern.getTag())) {
//					tt = tmp;
//				}
//			}
//			assert(tt != null);
//			
//			for (AstSubPattern astSubPattern : astPattern.getSubpatterns()) {
//				int pos = astPattern.getSubpatterns().indexOf(astSubPattern);
//				String label;
//				if (astSubPattern.getLabel() != null) {
//					label = astSubPattern.getLabel();
//				} else {
//					label = tt.getFields().get(pos).getName();
//				}
//				
//				if (astSubPattern.getCondition() != null) {
//					Type t1 = tt.getFields().get(pos).getType();
//					Expression memberValue = Util.createTaggedTupleFieldExpression(scope, expr, tu, astPattern.getTag(), label);
//					Variable tmp = Util.createTmpVariable(scope, t1, memberValue);
//					
//					Expression e = CreateIrExpression.convert(scope, astSubPattern.getCondition());
//					VariableExpression varExpr = Util.createVariableExpression(scope, tmp);
//					Expression condition = Util.createBinaryExpression(scope, varExpr, "=", e, TypeSystem.createTypeBool());
//					
//					final Guard guard =  IrFactory.eINSTANCE.createGuard();
//					guard.setId(Util.getDefinitionId());
//					guard.setOuter(scope);
//					guard.setContext(scope);
//					guard.setBody(condition);
//					if (scope instanceof StmtAlternative) 
//						((StmtAlternative) scope).getGuards().add(guard);
//					else 
//						((ExprAlternative) scope).getGuards().add(guard);
//				} else if (astSubPattern.getPattern() != null) {
//					Type t1 = tt.getFields().get(pos).getType();
//					Expression value = Util.createTaggedTupleFieldExpression(scope, expr, tu, astPattern.getTag(), label);
//					Variable tmp = Util.createTmpVariable(scope, t1, value);
//					VariableExpression varExpr = Util.createVariableExpression(scope, tmp);
//					doPattern(scope, t1, astSubPattern.getPattern(), varExpr);					
//				}		
//			}
//		}		
//	}		

	public static TypeGuard doPattern(Scope scope, AstType astType, AstPattern astPattern, Expression expr, Expression repeat) { 
				
		if (astPattern.getVariable() != null) {
			Type type = TypeConverter.convert(scope, astType, false);
			if (repeat != null) 
				type = TypeSystem.createTypeList(repeat, type);
			Util.createVariable(scope, astPattern.getVariable(), type, expr);
			return null;
		} else {		
			TypeGuard typeGuard = IrFactory.eINSTANCE.createTypeGuard();
			typeGuard.setTag(astPattern.getTag());
			typeGuard.setId(Util.getDefinitionId());
			typeGuard.setContext(scope);
			typeGuard.setExpression(expr);
			typeGuard.setType(TypeSystem.createTypeBool());
			typeGuard.setOuter(scope);
			
			AstTypeUser astTypeUser = astType.getName();
			AstTaggedTuple tt = null;
			
			for (AstTaggedTuple tmp :  astTypeUser.getTuples()) {
				if (tmp.getName().equals(astPattern.getTag())) {
					tt = tmp;
				}
			}
			assert(tt != null);
			
			List<Expression> guards = new ArrayList<Expression>();
			
			for (AstSubPattern astSubPattern : astPattern.getSubpatterns()) {
				int pos = astPattern.getSubpatterns().indexOf(astSubPattern);
				String label;
				if (astSubPattern.getLabel() != null) {
					label = astSubPattern.getLabel();
				} else {
					label = tt.getFields().get(pos).getName();
				}
				
				if (astSubPattern.getCondition() != null) {
					AstType astFieldType = tt.getFields().get(pos).getType();
					Type fieldType = TypeConverter.convert(scope, astFieldType, false);
					if (repeat != null) 
						fieldType = TypeSystem.createTypeList(repeat, fieldType);

					// Declare a variable and place it in the surrounding scope	
					Variable var = Util.createTmpVariable(scope, fieldType, null);
					
					// Create a read that fills the variable declare above with the content of that field
					TypeUser tu = (TypeUser) TypeConverter.convert(scope, astType, true);
					TypeTuple type = (TypeTuple) ((TypeDeclaration) tu.getDeclaration()).getType();
					typeGuard.getReads().add(Util.createTaggedTupleFieldRead(scope, expr, type, astPattern.getTag(), label, var));
					
					// Add a guard expression
					Expression e = CreateIrExpression.convert(scope, astSubPattern.getCondition());
					VariableExpression varExpr = Util.createVariableExpression(scope, var);
					Expression condition = Util.createBinaryExpression(scope, varExpr, "=", e, TypeSystem.createTypeBool());
					
					guards.add(condition);					
				} else if (astSubPattern.getPattern() != null) {
					AstType astFieldType = tt.getFields().get(pos).getType();
					Type fieldType = TypeConverter.convert(scope, astFieldType, false);
					if (repeat != null) 
						fieldType = TypeSystem.createTypeList(repeat, fieldType);
					
					// Declare a variable and place it in the surrounding scope	
					Variable var;
					if (astSubPattern.getPattern().getVariable() != null) {
						var = Util.createVariable(scope, astSubPattern.getPattern().getVariable(), fieldType, null);
					} else { 
						var = Util.createTmpVariable(scope, fieldType, null);
					}
					// Create a read that fills the variable declare above with the content of that field
					// This variable will *only* be used by the subsequent pattern matchings
					TypeUser tu = (TypeUser) TypeConverter.convert(scope, astType, true);
					TypeTuple type = (TypeTuple) ((TypeDeclaration) tu.getDeclaration()).getType();
					typeGuard.getReads().add(Util.createTaggedTupleFieldRead(scope, expr, type, astPattern.getTag(), label, var));

					VariableExpression varExpr = Util.createVariableExpression(scope, var);			
				
					if (astSubPattern.getPattern().getVariable() == null) {
						Expression e = doPattern(scope, tt.getFields().get(pos).getType(), astSubPattern.getPattern(), varExpr, repeat);
						if (e != null) {
							guards.add(e);
						}
					}					
				}	
				
			}
			
			if (guards.size() > 0) {
				typeGuard.setBody(Util.createAndExpression(scope, guards));
			}
			
			return typeGuard;
		}
	}			

	private static Expression createAndExpression(Scope scope, List<Expression> expressions) {
		if (expressions.size() == 1) {
			return expressions.get(0);
		} else {
			return createBinaryExpression(scope, expressions.get(0), "and", 
					createAndExpression(scope, expressions.subList(1, expressions.size() - 1)), 
					TypeSystem.createTypeBool());
		}			
	}

	public static String packQualifiedName(List<String> ns, String... s) {
		String ret = packQualifiedName(ns);
		
		if (ns.size() > 0) {
			ret += ".";
		}
		for (int i = 0; i < s.length; i++) {			
			ret += s[i];
			if (i < s.length - 1) {
				ret += ".";
			}
		}
		
		return ret;
	}
	
	public static String packQualifiedName(List<String> ns) {
		String ret = "";
		if(ns != null) {
			for(Iterator<String> i=ns.iterator();i.hasNext();) {
				ret += i.next();
				if(i.hasNext()) ret += ".";
			}
		}
		
		return ret;
	}
	
	public static String marshallQualifiedName(List<String> ns) {
		String ret = "";
		if(ns != null) {
			for(Iterator<String> i=ns.iterator();i.hasNext();) {
				ret += i.next();
				if(i.hasNext()) ret += "_";
			}
		}
		
		return ret;
	}
	
	public static List<String> unpackQualifiedName(String ns) {
		List<String> ret = new ArrayList<String>();
		String tmp[] = java.util.regex.Pattern.compile("\\.").split(ns);
		for (String s : tmp) {
			ret.add(s);
		}	

		return ret;
	}
	
	public static boolean isGlobal(Variable def) {
		if (def.getScope() instanceof Actor) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean inSameNamespace(Scope s1, Scope s2) {
		while (! (s1 instanceof Namespace)) {
			s1 = s1.getOuter();
		}
		Namespace ns1 = (Namespace) s1;

		while (! (s2 instanceof Namespace)) {
			s2 = s2.getOuter();
		}
		Namespace ns2 = (Namespace) s2;

		return (packQualifiedName(ns1.getName()).equals(packQualifiedName(ns2.getName())));
	}

	public static boolean isGlobal(VariableExpression var) {
		return (var.getVariable().getScope() instanceof Actor);
	}

	public static Annotation createPathAnnotation(String path) {
		Annotation annotation = IrFactory.eINSTANCE.createAnnotation();
		annotation.setName("Location");
		AnnotationArgument annotationArgument = IrFactory.eINSTANCE.createAnnotationArgument();
		annotationArgument.setId("path");
		annotationArgument.setValue(path);
		annotation.getArguments().add(annotationArgument);

		return annotation;
	}

	public static String getPathAnnotation(List<Annotation> annotations) {
		for (Annotation annotation : annotations) {
			if (annotation.getName().equals("Location")) {
				return annotation.getArguments().get(0).getValue();		
			}
		}
		
		return null;
	}
	
	public static Annotation createProjectAnnotation(String project) {
		Annotation annotation = IrFactory.eINSTANCE.createAnnotation();
		annotation.setName("Project");
		AnnotationArgument annotationArgument = IrFactory.eINSTANCE.createAnnotationArgument();
		annotationArgument.setId("name");
		if (project != null) {
			annotationArgument.setValue(project);
		} else {
			annotationArgument.setValue("undef");
		}
		annotation.getArguments().add(annotationArgument);
		
		return annotation;
	}

	public static String getProjectAnnotation(List<Annotation> annotations) {
		for (Annotation annotation : annotations) {
			if (annotation.getName().equals("Project")) {
				return annotation.getArguments().get(0).getValue();		
			}
		}
		
		return null;
	}
	
	public static void checkFolder(String folder) {
		File f = new File(folder);
		if (!f.exists()) {
			checkFolder(folder.substring(0, folder.lastIndexOf(File.separator)));	
			f.mkdir();
		}		
	}
	
	public static String removeWindowsDriveLetter(String filename) {
		if (filename.length()>=2 && filename.charAt(1)==':') {
			return filename.substring(2);
		} else {
			return filename;
		}
	}
	
	public static String namespace2Path(List<String> ns) {
		String ret="";
		if(ns != null) {
			for(Iterator<String> i=ns.iterator();i.hasNext();) {
				String s=i.next();
				ret+=s;
				if(i.hasNext()) ret+=File.separator;
			}
		}
		return ret;
	}
	
	public static String marshall(String s) {
		s = s.replace("<=", "LTE");
		s = s.replace("<", "LT");
		s = s.replace(">=", "GTE");
		s = s.replace("<", "GT");
		s = s.replace("&&", "AND");
		s = s.replace("&", "BAND");
		s = s.replace("/", "DIV");
		
		return s;
	}

	public static String unmarshall(String s) {
		s = s.replace("LTE", "<=");
		s = s.replace("LT", "<");
		s = s.replace("GTE", ">=");
		s = s.replace("GT", ">");
		s = s.replace("BAND", "&");
		s = s.replace("AND", "&&");
		s = s.replace("DIV", "/");
		
		return s;
	}

	public static TaggedTupleFieldRead createTaggedTupleFieldRead(Scope context, Expression value, TypeTuple type, String tag, String label, Variable var) {
		TaggedTupleFieldRead field = IrFactory.eINSTANCE.createTaggedTupleFieldRead();
		field.setValue(value);
		field.setTag(tag);
		field.setLabel(label);		
		field.setTarget(Util.createVariableReference(var));
		
		return field;
	}
}
