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

package org.caltoopia.codegen.printer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.caltoopia.ast2ir.Stream;
import org.caltoopia.ast2ir.Util;
import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.cli.DirectoryException;
import org.caltoopia.codegen.CEnvironment;
import org.caltoopia.codegen.CodegenError;
import org.caltoopia.codegen.UtilIR;
import org.caltoopia.codegen.printer.CPrintUtil.dummyCB;
import org.caltoopia.codegen.transformer.IrTransformer;
import org.caltoopia.codegen.transformer.TransUtil;
import org.caltoopia.codegen.transformer.analysis.IrTypeStructureAnnotation.TypeMember;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarAccess;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarLocalAccess;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarType;
import org.caltoopia.codegen.transformer.analysis.IrVariablePlacementAnnotation.VarPlacement;
import org.caltoopia.ir.Assign;
import org.caltoopia.ir.BinaryExpression;
import org.caltoopia.ir.BooleanLiteral;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.FloatLiteral;
import org.caltoopia.ir.ForwardDeclaration;
import org.caltoopia.ir.FunctionCall;
import org.caltoopia.ir.IfExpression;
import org.caltoopia.ir.IntegerLiteral;
import org.caltoopia.ir.ListExpression;
import org.caltoopia.ir.Member;
import org.caltoopia.ir.Namespace;
import org.caltoopia.ir.StringLiteral;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeBool;
import org.caltoopia.ir.TypeConstructorCall;
import org.caltoopia.ir.TypeDeclaration;
import org.caltoopia.ir.TypeExternal;
import org.caltoopia.ir.TypeFloat;
import org.caltoopia.ir.TypeInt;
import org.caltoopia.ir.TypeList;
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
import org.caltoopia.ir.util.IrSwitch;
import org.eclipse.emf.ecore.EObject;

/*
 * This class generates a string giving the c-code sizeof(). 
 * Supports types, variables, expressions, and assignments.
 * 
 * Like the idea of this class but no real usage since
 * switch to runtime dynamic array handling. Maybe needed again
 * when doing optimized copying, user type ports etc.
 * 
 * Quality: 2, only one user (from code that should not be reached)
 *             needs shaping up before actual usage.
 */
public class CBuildSizeOf extends IrSwitch<Boolean> {
    String exprStr="";
    CEnvironment cenv = null;
    int dim = 0;
    int level = 0;
    EObject node = null;
    boolean constSize = false; //When true the sizeof is built only from type and hence must be constant size
    boolean topOnly = false;
    boolean excludeList = false;
    boolean justList;
    boolean noDynList = true;
    boolean exprEval = false;
    
    public CBuildSizeOf(Type type, CEnvironment cenv, boolean topOnly, boolean excludeList, int level, boolean justList) {
        exprStr="";
        this.constSize = true;
        this.topOnly = topOnly;
        this.excludeList = excludeList;
        this.justList = justList;
        this.level = level;
        this.node = type;
        this.cenv = cenv;
        dim = 0;
    }
    
    public CBuildSizeOf(Variable var, CEnvironment cenv, boolean topOnly, boolean excludeList, int level, boolean justList) {
        exprStr="";
        this.constSize = false;
        this.topOnly = topOnly;
        this.excludeList = excludeList;
        this.level = level;
        this.node = var;
        this.cenv = cenv;
        dim = 0;
    }

    public CBuildSizeOf(VariableReference var, CEnvironment cenv, boolean topOnly, boolean excludeList, int level, boolean justList) {
        exprStr="";
        this.constSize = false;
        this.topOnly = topOnly;
        this.excludeList = excludeList;
        this.justList = justList;
        this.level = level;
        this.node = var;
        this.cenv = cenv;
        dim = 0;
    }

    public CBuildSizeOf(Expression expr, CEnvironment cenv, boolean topOnly, boolean excludeList, int level, boolean justList) {
        exprStr="";
        this.constSize = false;
        this.topOnly = topOnly;
        this.excludeList = excludeList;
        this.justList = justList;
        this.level = level;
        this.node = expr;
        this.cenv = cenv;
        dim = 0;
    }

    public CBuildSizeOf(Assign assign, CEnvironment cenv, boolean topOnly, boolean excludeList, int level, boolean justList) {
        exprStr="";
        this.constSize = false;
        this.topOnly = topOnly;
        this.excludeList = excludeList;
        this.justList = justList;
        this.level = level;
        this.node = assign;
        this.cenv = cenv;
        dim = 0;
    }

    public String toStr() {
        if(node != null) {
            Boolean res = doSwitch(node);
        } else {
            CodegenError.err("sizeof", "Got null!");
        }
        return exprStr;
    }

    private void enter(EObject obj) {}
    private void leave() {}
    
    //---------------------caseX-----------------------------------
    @Override
    public Boolean caseTypeUser(TypeUser type) {
        enter(type);
        exprStr += "(";
        exprStr += "sizeof(" + new CBuildTypeName(type, new CPrintUtil.dummyCB(), false) +")";
        doSwitch(UtilIR.getType(type));
        exprStr += ")";
        leave();
        return true;
    }
    
    @Override
    public Boolean caseTypeBool(TypeBool type) {
        enter(type);
        exprStr += "sizeof(" + new CBuildTypeName(type, new CPrintUtil.dummyCB(), false).toStr() +")";
        leave();
        return true;
    }

    @Override
    public Boolean caseTypeInt(TypeInt type) {
        enter(type);
        exprStr += "sizeof(" + new CBuildTypeName(type, new CPrintUtil.dummyCB(), false).toStr() +")";
        leave();
        return true;
    }

    @Override
    public Boolean caseTypeUint(TypeUint type) {
        enter(type);
        exprStr += "sizeof(" + new CBuildTypeName(type, new CPrintUtil.dummyCB(), false).toStr() +")";
        leave();
        return true;
    }

    @Override
    public Boolean caseTypeFloat(TypeFloat type) {
        enter(type);
        exprStr += "sizeof(" + new CBuildTypeName(type, new CPrintUtil.dummyCB(), false).toStr() +")";
        leave();
        return true;
    }

    @Override
    public Boolean caseTypeList(TypeList type) {
        enter(type);
        exprStr += "(";
        Type t = type;
        String dimStr = "";
        while(t instanceof TypeList) {
            if(((TypeList)t).getSize()!=null) {
                dimStr += " * " + new CBuildExpression(((TypeList)t).getSize(), cenv).toStr();
            } else {
                noDynList = false;
                dimStr += " * __" + Util.getDefinitionId() + "__";
                //CodegenError.err("Build sizeof", "List of undefined length");
            }
            t = ((TypeList)t).getType();
        }
        if(!justList) {
            exprStr += "sizeof(" + new CBuildTypeName(t, new CPrintUtil.dummyCB(), true).toStr() +")";
        } else {
            exprStr += "0";
        }
        if(!excludeList) {
            CBuildSizeOf sz = new CBuildSizeOf(t, cenv, topOnly, excludeList,level,justList);
            exprStr += " + (" + sz.toStr() + ")";
            exprStr += dimStr;
            if(!sz.noDynList) noDynList = false;
        }
        exprStr += ")";
        leave();
        return true;
    }


    @Override
    public Boolean caseTypeString(TypeString type) {
        enter(type);
        exprStr += "/*strlen()*/";
        leave();
        return true;
    }

    
    @Override
    public Boolean caseTypeRecord(TypeRecord struct) {
        enter(struct);
        if(!(topOnly && level>0)) {
            exprStr += "(";
            for(Variable v: struct.getMembers()) {
                CBuildSizeOf sz = new CBuildSizeOf(v.getType(), cenv, topOnly, excludeList,level+1,justList);
                exprStr += " + " + sz.toStr();
                if(!sz.noDynList) noDynList = false;
            }
            exprStr += ")";
        }
        leave();
        return true;
    }

    @Override
    public Boolean caseTypeExternal(TypeExternal type) {
        enter(type);
        exprStr += "/*sizeof(external type)*/";
        leave();
        return true;
    }

    @Override
    public Boolean caseTypeUndef(TypeUndef type) {
        enter(type);
        exprStr += "/*sizeof(undef type)*/";
        leave();
        return true;
    }
    
    @Override
    public Boolean caseType(Type type) {
        assert(false);
        return false;
    }

    @Override
    public Boolean caseAssign(Assign assign) {
        enter(assign);
        //Sizeof type of assignment, first try target type, if that has dynamic list then need to evaluate expression.
        level = 0;
        if(TransUtil.allFixedLength(assign.getTarget().getType())) {
            doSwitch(assign.getTarget().getType());
        } else {
            exprEval = true;
            doSwitch(assign.getExpression());
        }
        leave();
        return true;
    }
    
    //---------------------------------------------------------------------------------------
    @Override
    public Boolean caseIntegerLiteral(IntegerLiteral lit) {
        enter(lit);
        leave();
        return true;
    }

    @Override
    public Boolean caseFloatLiteral(FloatLiteral lit) {
        enter(lit);
        leave();
        return true;
    }

    @Override
    public Boolean caseBooleanLiteral(BooleanLiteral lit) {
        enter(lit);
        leave();
        return true;
    }

    @Override
    public Boolean caseStringLiteral(StringLiteral lit) {
        enter(lit);
        leave();
        return true;
    }

    @Override
    public Boolean caseListExpression(ListExpression lit) {
        enter(lit);
        if(lit.getType() != null && ((TypeList)lit.getType()).getSize() !=null) {
            doSwitch(lit.getType());
            leave();
            return true;
        }
/*
        for (Iterator<Expression> i = lit.getExpressions().iterator(); i.hasNext();) {
            Expression l = i.next();            
            doSwitch(l);
            if (i.hasNext()) exprStr += ", ";
        }*/
        if(!lit.getGenerators().isEmpty()) {
            exprStr += "/* Don't know what to do with a generator yet "+ lit.toString() +"*/";
            CodegenError.err("Sizeof builder", "Don't know what to do with a generator yet "+ lit.toString() + ", check output!");
        }
        leave();
        return true;
    }

    @Override
    public Boolean caseVariableExpression(VariableExpression var) {
        enter(var);
        if(var.getType() != null && ((TypeList)var.getType()).getSize() !=null) {
            doSwitch(var.getType());
            leave();
            return true;
        }
        leave();
        return true;
    }
    
    @Override
    public Boolean caseMember(Member member) {
        enter(member);
        leave();
        return true;
    }

    @Override
    public Boolean caseBinaryExpression(BinaryExpression expr) {
        enter(expr);
        leave();
        return true;
    }

    @Override
    public Boolean caseUnaryExpression(UnaryExpression expr) {
        enter(expr);
        leave();
        return true;
    }

    @Override
    public Boolean caseFunctionCall(FunctionCall expr) {
        enter(expr);
        if(expr.getType() != null && TransUtil.allFixedLength(expr.getType())) {
            doSwitch(expr.getType());
        }
        leave();
        return true;
    }

    @Override
    public Boolean caseTypeConstructorCall(TypeConstructorCall expr) {
        enter(expr);
        leave();
        return true;
    }

    @Override
    public Boolean caseIfExpression(IfExpression expr) {
        enter(expr);
        doSwitch(expr.getCondition());
        doSwitch(expr.getThenExpression());
        doSwitch(expr.getElseExpression());
        leave();
        return true;
    }

    @Override
    public Boolean caseExpression(Expression object) {
        enter(object);
        leave();
        return true;
    }

}
