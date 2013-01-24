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
import org.caltoopia.ast2ir.Util;
import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.cli.DirectoryException;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.IrFactory;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.TypeBool;
import org.caltoopia.ir.TypeDeclaration;
import org.caltoopia.ir.TypeDeclarationImport;
import org.caltoopia.ir.TypeExternal;
import org.caltoopia.ir.TypeFloat;
import org.caltoopia.ir.TypeInt;
import org.caltoopia.ir.TypeList;
import org.caltoopia.ir.TypeString;
import org.caltoopia.ir.TypeRecord;
import org.caltoopia.ir.TypeUint;
import org.caltoopia.ir.TypeUndef;
import org.caltoopia.ir.TypeUser;

public class TypeSystem {		
		
	public static final String strInt    = "int"; 
	public static final String strUint   = "uint"; 
	public static final String strFloat  = "float";
	public static final String strBool   = "bool";
	public static final String strString = "string";
	public static final String strList   = "List";
	public static final String strStruct = "struct";
	public static final String strUndef  = "undef";	
			
	
	// private static IntExpressionEvaluator evaluator = new IntExpressionEvaluator();
	
	private static TypePrinter typePrinter = new TypePrinter();
	
	// private static ExpressionPrinter expressionPrinter = new ExpressionPrinter();
			
	public static TypeInt createTypeInt(int sz) {
		TypeInt type = IrFactory.eINSTANCE.createTypeInt();
		type.setSize(Util.createIntegerLiteral(sz));			
		return type;
	}

	public static TypeInt createTypeInt() {
		TypeInt type = IrFactory.eINSTANCE.createTypeInt();
		type.setSize(null); 	
		return type;
	}

	public static TypeUint createTypeUInt(int sz) {
		TypeUint type = IrFactory.eINSTANCE.createTypeUint();
		type.setSize(Util.createIntegerLiteral(sz));			
		return type;
	}
	
	public static TypeUint createTypeUint() {
		TypeUint type = IrFactory.eINSTANCE.createTypeUint();
		type.setSize(null);			
		return type;
	}
	
	public static TypeFloat createTypeFloat() {
		TypeFloat type = IrFactory.eINSTANCE.createTypeFloat();
		return type;
	}

	public static Type createTypeBool() {
		TypeBool type = IrFactory.eINSTANCE.createTypeBool();
		return type;
	}
	
	public static Type createTypeList(Expression size, Type elementType) {
		TypeList type = IrFactory.eINSTANCE.createTypeList();
		type.setType(elementType);
		type.setSize(size);			
		return type;
	}	
	
	public static Type createTypeString() {
		TypeString type = IrFactory.eINSTANCE.createTypeString();
		return type;
	}
		
	
	public static Type createTypeExternal(String name, List<String> ns) {
		TypeExternal type = IrFactory.eINSTANCE.createTypeExternal();
		type.setName(name);
		if(ns==null) {
			type.getScopeName().add("__BUILT_IN__");
		} else {
			type.getScopeName().addAll(ns);
		}
		return type;
	}

	public static Type createTypeUndef() {
		TypeUndef type = IrFactory.eINSTANCE.createTypeUndef(); 
		return type;
	}

	public static TypeActor createTypeNetwork() {
		TypeActor type = IrFactory.eINSTANCE.createTypeActor();
		type.setName("Network");
		return type;
	}

	public static Type createTypeUser(Declaration decl) {
		TypeUser type = IrFactory.eINSTANCE.createTypeUser(); 
		type.setDeclaration(decl);
		return type;
	}
	
	public static Type createTypeActor(String name, List<String> namespace) {
		TypeActor type = IrFactory.eINSTANCE.createTypeActor(); 
		type.setName(name);
		for (String s : namespace) {
			type.getNamespace().add(s);
		}
		
		return type;
	}
	
	public static boolean isUser(Type t){		
		return (t instanceof TypeUser);
	}

	public static boolean isRecord(Type t){		
		if (t instanceof TypeUser) {
			Declaration typeDecl = ((TypeUser) t).getDeclaration();
			if (typeDecl instanceof TypeDeclarationImport) {
				try {
					typeDecl = ActorDirectory.findTypeDeclaration((TypeDeclarationImport) typeDecl);
				} catch (DirectoryException x) {
					throw new RuntimeException("[TypeSystem] internal error#2a");
				}
			}
			Type t2 = ((TypeDeclaration) typeDecl).getType();
			return isRecord(t2);
		} else {
			return (t instanceof TypeRecord);
		}
	}

	public static TypeRecord asRecord(Type t) {
		if (t instanceof TypeRecord) {
			return (TypeRecord) t;
		} else if (t instanceof TypeUser) {
			Declaration typeDecl = ((TypeUser) t).getDeclaration();
			if (typeDecl instanceof TypeDeclarationImport) {
				try {
					typeDecl = ActorDirectory.findTypeDeclaration((TypeDeclarationImport) typeDecl);
				} catch (DirectoryException x) {
					throw new RuntimeException("[TypeSystem] internal error#2a");
				}
			}
			return (TypeRecord) ((TypeDeclaration) typeDecl).getType();
		} else {
			throw new RuntimeException("[TypeSystem] internal error#2b");
		}
		
	}

	public static boolean isUndef(Type t) {
		return (t instanceof TypeUndef);
	}

	public static boolean isInt(Type t) {
		return (t instanceof TypeInt);
	}

	public static boolean isUint(Type t) {
		return (t instanceof TypeUint);
	}
	
	public static boolean isFloat(Type t) {
		return (t instanceof TypeFloat);
	}

	public static boolean isBool(Type t) {
		return (t instanceof TypeBool);
	}

	public static boolean isList(Type t) {
		return (t instanceof TypeList);
	}
	
	public static boolean isString(Type t) {
		return (t instanceof TypeString);
	}

	//FIXME the functions "isCompatible" and "LUB" are just placeholders
	public static boolean isCompatible(Type t1, Type t2) throws TypeException {
		return !isUndef(LUB(t1, t2));
	}
	
	public static Type LUB(Type t1, Type t2) throws TypeException {
		
		if (isInt(t1)  && isInt(t2)  || 
			isInt(t1)  && isUint(t2) ||
			isUint(t1) && isInt(t2)) {
			return t1;
		} else if (isInt(t1) && isFloat(t2)  || 
				   isFloat(t1) && isInt(t2)  ||
				   isUint(t1) && isFloat(t2) || 
				   isFloat(t1)  && isUint(t2)) {
			return createTypeFloat();
		} else if (isList(t1) && isList(t2)) {
			Type elementType = LUB( ((TypeList) t1).getType(), ((TypeList) t2).getType() );			
			return TypeSystem.createTypeList(((TypeList)t1).getSize(), elementType); //FIXME Here we just take the first branch and hopes for the best:( Just to get the first tests out...
		} else if (isIdenticalTypes(t1, t2)) {
			return t1;
		} else {
			throw new TypeException("Incompatible types: '" + typePrinter.doSwitch(t1) + "' and '" + typePrinter.doSwitch(t2) + "'");
		}
	}		
		
	public static Type getElementType(Type type) {
		if (isList(type)) {
			return ((TypeList) type).getType();
		} else {
			throw new RuntimeException("Illegal indexing");
		}
	}

	public static Expression sizeOfList(Type type) {
		if (isList(type)) {
			return ((TypeList) type).getSize();
		} else {
			throw new RuntimeException("Type error - size of list not valid");
		}
	}	
	
	public static boolean isIdenticalTypes(Type t1, Type t2) throws TypeException {	
		if (t1.getClass().equals(t2.getClass())) {
			if (isList(t1)) {
				Type et1 = ((TypeList) t1).getType(); 
				//Expression sz1 = ((TypeList) t1).getSize();
				
				Type et2 = ((TypeList) t2).getType(); 
				// Expression sz2 = ((TypeList) t2).getSize();
				
				// return isIdenticalExpressions(sz1, sz2) && isIdenticalTypes(et1, et2);
				return isIdenticalTypes(et1, et2);
			} else {
				return true;
			}			
		}
		return false;
	}
}
