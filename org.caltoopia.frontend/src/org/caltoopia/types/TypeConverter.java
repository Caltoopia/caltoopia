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

package org.caltoopia.types;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.caltoopia.frontend.cal.AstAction;
import org.caltoopia.frontend.cal.AstActor;
import org.caltoopia.frontend.cal.AstExpression;
import org.caltoopia.frontend.cal.AstExpressionBinary;
import org.caltoopia.frontend.cal.AstExpressionBoolean;
import org.caltoopia.frontend.cal.AstExpressionSymbolReference;
import org.caltoopia.frontend.cal.AstExpressionFloat;
import org.caltoopia.frontend.cal.AstExpressionIf;
import org.caltoopia.frontend.cal.AstExpressionInteger;
import org.caltoopia.frontend.cal.AstExpressionList;
import org.caltoopia.frontend.cal.AstExpressionString;
import org.caltoopia.frontend.cal.AstExpressionUnary;
import org.caltoopia.frontend.cal.AstFunction;
import org.caltoopia.frontend.cal.AstInputPattern;
import org.caltoopia.frontend.cal.AstMemberAccess;
import org.caltoopia.frontend.cal.AstPort;
import org.caltoopia.frontend.cal.AstStatementAssign;
import org.caltoopia.frontend.cal.AstTaggedTuple;
import org.caltoopia.frontend.cal.AstType;
import org.caltoopia.frontend.cal.AstTypeUser;
import org.caltoopia.frontend.cal.AstTypeParam;
import org.caltoopia.frontend.cal.AstTypeParameterList;
import org.caltoopia.frontend.cal.AstVariable;
import org.caltoopia.frontend.cal.CalPackage;
import org.caltoopia.frontend.cal.util.CalSwitch;
import org.caltoopia.frontend.validation.CalJavaValidator;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.IrFactory;
import org.caltoopia.ir.Scope;
import org.caltoopia.ir.TaggedTuple;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeBool;
import org.caltoopia.ir.TypeTuple;
import org.caltoopia.ir.TypeDeclaration;
import org.caltoopia.ir.TypeFloat;
import org.caltoopia.ir.TypeInt;
import org.caltoopia.ir.TypeList;
import org.caltoopia.ir.TypeString;
import org.caltoopia.ir.TypeUint;
import org.caltoopia.ir.TypeUndef;
import org.caltoopia.ir.TypeUser;
import org.caltoopia.ir.Variable;
import org.caltoopia.ast2ir.CreateIrExpression;
import org.caltoopia.ast2ir.Util;

public class TypeConverter extends CalSwitch<Type> {
	
	private static CalJavaValidator validator;
	
	private static TypePrinter typePrinter = new TypePrinter();
	
	private boolean approximate = false;
		
	private Scope context;
		
	public static String BUILTIN_TYPE_INT   	= "int";
	public static String BUILTIN_TYPE_UINT  	= "uint";
	public static String BUILTIN_TYPE_FLOAT 	= "float";
	public static String BUILTIN_TYPE_BOOL 		= "bool";
	public static String BUILTIN_TYPE_STRING 	= "string";
	public static String BUILTIN_TYPE_LIST 		= "List";
	
	public static Type typeOf(Scope context, AstExpression e, boolean approximate) {
		return new TypeConverter(context, approximate).doSwitch(e);
	}
	
	public static Type typeOf(Scope context, AstVariable v, List<AstExpression> index, List<AstMemberAccess> members, boolean approximate) {
		try {
			Type t = getTypeOfAstVariable(v, index, members, context, approximate);
			return t;
		} catch (TypeException x) {
			error("Failed to determine type for '" + v.getName() + "'",
					v, CalPackage.eINSTANCE.getAstVariable_Name(), -1);
			return TypeSystem.createTypeUndef();
		}
	}
	

	public static Type typeOf(Scope context, AstMemberAccess var, boolean approximate) {
		AstVariable topVar;
		Type topType;
		List<AstMemberAccess> members;
		int pos;

	
		if (var.eContainer() instanceof AstStatementAssign) {
			// The member is being assigned
			AstStatementAssign stmt = (AstStatementAssign) var.eContainer();
			topVar = stmt.getTarget();
			pos = stmt.getMember().indexOf(var);
			members = stmt.getMember();
		} else {
			// The member is in a variable expression 
			AstExpressionSymbolReference exp = (AstExpressionSymbolReference) var.eContainer();
			topVar = exp.getSymbol();
			pos = exp.getMember().indexOf(var);
			members = exp.getMember();
		}
	
		topType = TypeConverter.typeOf(context, topVar, null, null, approximate);
	
		Type type = topType;
		for (int i = 0; i <= pos; i++) {			
			if (TypeSystem.isList(type)) {
				type = TypeSystem.getElementType(type);				
			}
		
			if (TypeSystem.isTypeTuple(type)) {
				type = TypeSystem.asTypeTuple(type);
				TaggedTuple tt = ((TypeTuple) type).getTaggedTuples().get(0); //Take first, since there can only be one when dot notation is supported.	

				boolean found = false;
				for (Variable n : tt.getFields()) {
					if (n.getName().equals(members.get(i).getName())) {
						found = true;
						type = n.getType();
						break;
					}
				}
				if (!found) {
					error("The variable '" + members.get(i).getName() + "' is not part of the type '" + typePrinter.doSwitch(topType) + "'",
							var,
							CalPackage.eINSTANCE.getAstMemberAccess_Name(), -1);
					break;
				}
			} else {
				error("The variable '" + members.get(i).getName() + "' is not part of the type '" + typePrinter.doSwitch(topType) + "'",
						var,
						CalPackage.eINSTANCE.getAstMemberAccess_Name(), -1);
				break;
			}
		}
		return type;
	}
		
	public static Type convert(Scope context, AstType t, boolean approximate) {
		Type type = new TypeConverter(context, approximate).doSwitch(t);
		return type;
	}

	public static void setValidator(CalJavaValidator calJavaValidator) {
		TypeConverter.validator = calJavaValidator;
	}
	
	private TypeConverter(Scope context, boolean approximate) {
		this.context = context;
		this.approximate = approximate;
	}
	
	@Override	
	public Type caseAstExpressionInteger(AstExpressionInteger e) {
		return TypeSystem.createTypeInt();
	}
	
	@Override 
	public Type caseAstExpressionFloat(AstExpressionFloat e) {		
		return TypeSystem.createTypeFloat();
	}
	
	@Override
	public Type caseAstExpressionBoolean(AstExpressionBoolean e) {		
		return TypeSystem.createTypeBool();
	}

	@Override
	public Type caseAstExpressionString(AstExpressionString e) {		
		return TypeSystem.createTypeString();
	}

	@Override
	public Type caseAstExpressionList(AstExpressionList e) {		
		// In this initial version we assume only one expression 
		// and one generator.
		if (e.getGenerators() != null && !e.getGenerators().isEmpty()) {
				
			if ((e.getExpressions().size() > 1) || (e.getGenerators().size() > 1)) {
				error("List expressions only with single generator and expression supported right now.",
								e, CalPackage.eINSTANCE.getAstExpressionList_Generators(), -1);
				return TypeSystem.createTypeUndef();
			}

			Type elementType   = doSwitch(e.getExpressions().get(0));
			Type generatorType = doSwitch(e.getGenerators().get(0).getExpression());		
			Expression sz = Util.createIntegerLiteral(e.getExpressions().size());
			return TypeSystem.createTypeList(sz, elementType); //FIXME
		} else {
			AstExpression e1 = e.getExpressions().get(0);
			Type t1 = doSwitch(e1);
			for (AstExpression e2 : e.getExpressions()) {
				Type t2 = doSwitch(e2);
				try {
					t1 = TypeSystem.LUB(t1, t2);
				} catch (TypeException x) {
					error("Type error:" + x.getMessage(),
							e, 
							CalPackage.eINSTANCE.getAstExpressionList_Expressions(), -1);	
					return TypeSystem.createTypeUndef();
				}
			}
			Expression sz = Util.createIntegerLiteral(e.getExpressions().size());
			return TypeSystem.createTypeList(sz, t1);
		}			
	}		
			
	@Override
	public Type caseAstExpressionBinary(AstExpressionBinary e) {		
		Type t1 = doSwitch(e.getLeft());
		Type t2 = doSwitch(e.getRight());
		String op = e.getOperator();
		Type result = null;
		
		if (t1 == null || t2 == null) return null;
		try {
			if (op.equals("+") || op.equals("-") || op.equals("*")	|| op.equals("/") || op.equals("mod") ||
					op.equals("|") || op.equals("^") || op.equals("&")  || op.equals("<<")	|| op.equals(">>")	) {			
				result = TypeSystem.LUB(t1, t2);				
			} else if (op.equals("and") || op.equals("or")) {
				result = TypeSystem.LUB(t1, t2);
			} else if (op.equals("=") || op.equals("!=") || 
					   op.equals("<") || op.equals("<=") ||
					   op.equals(">") || op.equals(">=")) {				
				if (TypeSystem.isCompatible(t1, t2)) { //FIXME This should be replace my something slightly smarter				
					result = TypeSystem.createTypeBool();
				} else {
					result = TypeSystem.createTypeUndef();
				}				
			} else if (op.equals("..")) {
				Expression size = Util.createIntegerLiteral(-1); //FIXME We give it size -1 to indicate that we have no clue
				result = TypeSystem.createTypeList(size, TypeSystem.createTypeInt());
			} else {
				result = TypeSystem.createTypeUndef();
			}
		} catch (TypeException x) {
			error("Type error:" + x.getMessage(),
			      e, 
				  CalPackage.eINSTANCE.getAstExpressionBinary_Operator(), -1);	
			return TypeSystem.createTypeUndef();
		}
			
		return result;
	}	
	
	@Override
	public Type caseAstExpressionSymbolReference(AstExpressionSymbolReference e) { 
		AstVariable v = e.getSymbol();
		if (v instanceof AstFunction) {	
			return doSwitch(((AstFunction) e.getSymbol()).getType());
		} else if (v instanceof AstTypeUser) {
			//This is not a function, but a type constructor			
			Type t = createTypeUser((AstTypeUser) v, approximate);
			return t; 
		} else {
			try {
				Type t = getTypeOfAstVariable(v, e.getIndexes(), e.getMember(), context, approximate);
				return t;
			} catch (Exception x) {
				error("Type error:" + x.getMessage(), e, null, -1);			
				return TypeSystem.createTypeUndef();
			}
		}
	}

	@Override
	public Type caseAstExpressionUnary(AstExpressionUnary e) {
		if (e.getUnaryOperator().equals("#")) {
			return TypeSystem.createTypeInt();
		} else {
			return doSwitch(e.getExpression()); //FIXME - This is only almost true
		}
	}
	
	@Override 
	public Type caseAstExpressionIf(AstExpressionIf e) {
		Type c  = doSwitch(e.getCondition());
		Type t1 = doSwitch(e.getThen());
		Type t2 = doSwitch(e.getElse());
		if (TypeSystem.isBool(c)) {
			try {
				return TypeSystem.LUB(t1, t2);
			} catch (TypeException x) {
				error("Type error:" + x.getMessage(), e, CalPackage.eINSTANCE.getAstExpressionIf_Condition(), -1);				
				return TypeSystem.createTypeUndef();
			}
		} 
		return TypeSystem.createTypeUndef();
	}
	
	@Override 
	public Type caseAstType(AstType astType) {
	
		if (astType.getDimensions().size() > 0) {
			TypeList type = null;
			
			//FIXME: This is a hack to squeeze in support for the new "int[x][y] v" syntax
			// We need to redesign this whole type converter stuff... 
			AstType xxx = EcoreUtil.copy(astType);
			int sz = astType.getDimensions().size();
			for (int i = 0; i < sz; i++) {
				xxx.getDimensions().remove(0);
			}
			Type elementType = doSwitch(xxx);
			//Hack ends. (well, well)
			for (AstExpression dim : astType.getDimensions()) {
				type = IrFactory.eINSTANCE.createTypeList();
				type.setType(elementType);
				if (approximate) {			
					type.setSize(CreateIrExpression.NotEvaluatedExpression);				
				} else {
					type.setSize(CreateIrExpression.convert(context, dim));
				} 			
				elementType = type;
			}
						
			return type;
		}  else if (isUserDefined(astType)) {
			return createTypeUser(astType.getName(), approximate);
		} else if (astType.getBuiltin().equals(BUILTIN_TYPE_INT)) {
			TypeInt type = IrFactory.eINSTANCE.createTypeInt();
			AstExpression sz = getSizeParameter(astType);	
			if (sz != null) {
				if (sz != null) {
					if (approximate) {
						type.setSize(CreateIrExpression.NotEvaluatedExpression);
					} else {
						type.setSize(CreateIrExpression.convert(context, sz));
					}
				}
			}
			return type;
		} else if (astType.getBuiltin().equals(BUILTIN_TYPE_UINT)) {
			TypeUint type = IrFactory.eINSTANCE.createTypeUint();
			AstExpression sz = getSizeParameter(astType);	
			if (sz != null) {
				if (approximate) {
					type.setSize(CreateIrExpression.NotEvaluatedExpression);
				} else {
					type.setSize(CreateIrExpression.convert(context, sz));
				}
			}
			return type;
		} else if (astType.getBuiltin().equals(BUILTIN_TYPE_FLOAT)) {
			TypeFloat type = IrFactory.eINSTANCE.createTypeFloat();
			return type;
		} else if (astType.getBuiltin().equals(BUILTIN_TYPE_BOOL)) {
			TypeBool type = IrFactory.eINSTANCE.createTypeBool();
			return type;
		} else if (astType.getBuiltin().equals(BUILTIN_TYPE_STRING)) {
			TypeString type = IrFactory.eINSTANCE.createTypeString();
			return type;
		} else if (astType.getBuiltin().equals(BUILTIN_TYPE_LIST)) {
			TypeList type = IrFactory.eINSTANCE.createTypeList();
			if (approximate) {
				type.setSize(CreateIrExpression.NotEvaluatedExpression);				
			} else {
				try {
					type.setSize(CreateIrExpression.convert(context, getSizeParameter(astType)));
				} catch (Exception x) {					
					System.err.println("[TypeConverter] Fatal error:" + x.getMessage());
				}
			} 
			Type elementType = doSwitch(getTypeParameter(astType));
			type.setType(elementType);
			
			return type;
	    } else {
			TypeUndef type = IrFactory.eINSTANCE.createTypeUndef();
			return type;
		}
	}

	public AstExpression getSizeParameter(AstType t) {
		AstTypeParameterList params = t.getTypeParams();
		if (params != null) {
			for (AstTypeParam p : params.getParams()) {
				if (p.getName().equals("size")) {
					return p.getValue();
				}
			}
		}
		return null;
	}
	
	static public AstType getTypeParameter(AstType t) {
		AstTypeParameterList params = t.getTypeParams();
		if (params != null) {
			for (AstTypeParam p : params.getParams()) {
				if (p.getName().equals("type")) {
					return p.getType();
				}
			}
		}
		return null;
	}
	
	protected static void error(String message, EObject source, EStructuralFeature feature, int index) {
		if (validator != null) {
			validator.error(message, source, feature, index);
		} else {
			System.err.println(message);
		}
	 }
	
	
	@Override
	public Type doSwitch(EObject o) {
		if (o != null) {
			return super.doSwitch(o);
		} else {
			if (this.approximate) {
				return TypeSystem.createTypeUndef();
			} else {
				throw new RuntimeException("TypeConverter: Error - null valued object");
			}
		}
	}
	
	public static Type getTypeOfAstVariable(AstVariable v, List<AstExpression> indexes, List<AstMemberAccess> member, Scope context, boolean approximate) throws TypeException {
		Type type = null;
		
		try {
			if (v.eContainer() instanceof AstInputPattern) {
				AstInputPattern pattern = (AstInputPattern) v.eContainer();
				AstPort port = null;
				if (pattern.getPort() != null) {
					port = pattern.getPort();
				} else {
					AstAction action = (AstAction) pattern.eContainer();
					List<AstInputPattern> inputs = action.getInputs();
					AstActor actor = (AstActor) action.eContainer();
					List<AstPort> ports = actor.getInputs();
					for (int i = 0; i < inputs.size(); i++) {
						if (inputs.get(i) == pattern) {
							port = ports.get(i);
						}
					}
				}
				if (port == null || port.getType() == null) 
					throw new TypeException("Unknown type of port");
								
				if (pattern.getRepeat() != null) {
					Expression sz = null; 
					try {
						sz = CreateIrExpression.convert(context, pattern.getRepeat());
					} catch (Exception x) {
						sz = CreateIrExpression.NotEvaluatedExpression;
					}
					Type elementType = TypeConverter.convert(context, port.getType(), approximate);
					type = TypeSystem.createTypeList(sz, elementType); 
				} else {
					type = TypeConverter.convert(context, port.getType(), approximate);
				}
			} else {
				type = TypeConverter.convert(context, v.getType(), approximate);
			}
		
			if (type == null) 
				throw new TypeException("Type not found");
			
			if (indexes != null && !indexes.isEmpty()) {
				for (int i = 0; i < indexes.size(); i++) {
					if (TypeSystem.isList(type)) {
						type = ((TypeList) type).getType(); 
					} else {
						throw new TypeException("Illegal indexing for type.");
					}
				}
			}
			
			if (member != null && !member.isEmpty()) {
				type =  getTypeOfMember(member, type);
			}
		} catch (Exception e) {
			throw new TypeException("Type not found:" + e.getMessage() + "\n");
		}
		
		if (type != null) {
			return type;
		} else {
			throw new TypeException("Type not found");
		}
	}

	public static Type getTypeOfMember(List<AstMemberAccess> member, Type type) throws TypeException { 

		if (type instanceof TypeUser) {
			Declaration decl = ((TypeUser) type).getDeclaration();			
			type = ((TypeDeclaration) decl).getType();
		}
		
		if (TypeSystem.isTypeTuple(type)) {
			type = TypeSystem.asTypeTuple(type);
			TaggedTuple tt = ((TypeTuple) type).getTaggedTuples().get(0); //Take first, since there can only be one when dot notation is supported.

			AstMemberAccess v = member.get(0);
			String name = v.getName();
			if (member.size() == 1) {
				for (Variable d : tt.getFields()) {
					if (d.getName().equals(name)) {	
						if (v.getMemberIndex().isEmpty())
							return d.getType();
						else 
							return TypeSystem.getElementType(d.getType());
					}
				}
			} else {
				for (Variable d : tt.getFields()) {
					if (d.getName().equals(name)) {			
						if (v.getMemberIndex().isEmpty()) {
							return getTypeOfMember(member.subList(1,member.size()), d.getType());
						} else  {
							if (TypeSystem.isList(d.getType())) {
								Type e = ((TypeList) d.getType()).getType();								
								return getTypeOfMember(member.subList(1,member.size()), e);
							} else {
								throw new TypeException("Illegal indexing for member of type." + typePrinter.doSwitch(d.getType()));
							}
						} 
					}
				}
			}			
		}
		String name = "";
		for (AstMemberAccess v : member)
			name += v.getName() + ".";
		throw new TypeException("Failed to determine valid type for member reference '" + name +  "' for top type" + typePrinter.doSwitch(type) );		
	}

	
	public static Type createTypeUser(AstTypeUser astTypeUser, boolean approximate) {
		TypeUser type = IrFactory.eINSTANCE.createTypeUser();
		
		if (approximate) {
			TypeDeclaration typedef = createTypeDeclaration(null, astTypeUser, approximate);
			type.setDeclaration(typedef);
		} else {
		  try {	
			  Declaration typedef = (Declaration) Util.findIrDeclaration(astTypeUser);
			  type.setDeclaration(typedef);
		  } catch (Exception x) {
			  System.err.println("[TypeConverter] Fatal internal error. Failed to create user type: " + x.getMessage());
		  }
		} 	
		
		return type;
	}
	
	public static TypeDeclaration createTypeDeclaration(Scope scope, AstTypeUser astTypeUser, boolean approximate) {
		TypeDeclaration typeDecl = IrFactory.eINSTANCE.createTypeDeclaration();			
		typeDecl.setId(Util.getDefinitionId());
		typeDecl.setScope(scope);
		
		TypeTuple tupleType = IrFactory.eINSTANCE.createTypeTuple();
		tupleType.setId(Util.getDefinitionId());
		
		for (AstTaggedTuple tt : astTypeUser.getTuples()) {		

			TaggedTuple taggedTuple = IrFactory.eINSTANCE.createTaggedTuple();
			taggedTuple.setTag(tt.getName());
			
			for (AstVariable astField : tt.getFields()) {
				Variable field = IrFactory.eINSTANCE.createVariable();
				field.setId(Util.getDefinitionId());
				field.setScope(scope);
				
				Type t = TypeConverter.convert(scope, astField.getType(), approximate);
				field.setType(t);
				field.setName(astField.getName());
				taggedTuple.getFields().add(field);
			}
		
			tupleType.getTaggedTuples().add(taggedTuple);			
		}
		
		typeDecl.setType(tupleType);
		typeDecl.setName(astTypeUser.getName());								

		Util.defsput(astTypeUser, typeDecl);
		
		return typeDecl;
	}
	
	public static boolean isUserDefined(AstType t){		
		if (t.getName() != null) {
			return true;
		} else {
			return false;
		}
	}	
	
}