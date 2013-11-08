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
import java.util.Stack;

import org.caltoopia.ast2ir.Stream;
import org.caltoopia.ast2ir.Util;
import org.caltoopia.ast2ir.Stream.Indent;
import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.cli.DirectoryException;
import org.caltoopia.codegen.CEnvironment;
import org.caltoopia.codegen.CodegenError;
import org.caltoopia.codegen.UtilIR;
import org.caltoopia.codegen.printer.CBuildVarDeclaration.varCB;
import org.caltoopia.codegen.printer.CPrintUtil.dummyCB;
import org.caltoopia.codegen.transformer.IrTransformer;
import org.caltoopia.codegen.transformer.TransUtil;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarAccess;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarLocalAccess;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarType;
import org.caltoopia.ir.Actor;
import org.caltoopia.ir.Assign;
import org.caltoopia.ir.BinaryExpression;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Block;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.ForEach;
import org.caltoopia.ir.ForwardDeclaration;
import org.caltoopia.ir.FunctionCall;
import org.caltoopia.ir.Generator;
import org.caltoopia.ir.IfStatement;
import org.caltoopia.ir.LambdaExpression;
import org.caltoopia.ir.Namespace;
import org.caltoopia.ir.ProcCall;
import org.caltoopia.ir.ProcExpression;
import org.caltoopia.ir.ReturnValue;
import org.caltoopia.ir.Scope;
import org.caltoopia.ir.Statement;
import org.caltoopia.ir.StringLiteral;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.TypeConstructorCall;
import org.caltoopia.ir.TypeExternal;
import org.caltoopia.ir.TypeLambda;
import org.caltoopia.ir.TypeList;
import org.caltoopia.ir.TypeProc;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.ir.VariableExternal;
import org.caltoopia.ir.VariableImport;
import org.caltoopia.ir.VariableReference;
import org.caltoopia.ir.WhileLoop;
import org.caltoopia.ir.util.IrSwitch;
import org.eclipse.emf.ecore.EObject;

public class CBuildAssign extends IrSwitch<Boolean> {
    String statStr="";
    Statement statement;
    boolean semicolon = true;
    CEnvironment cenv = null;
    private IndentStr ind = null;
    Scope scope = null;

    public CBuildAssign(Assign statement, CEnvironment cenv, IndentStr ind, boolean semicolon, Scope scope) {
        statStr="";
        this.statement = statement;
        this.semicolon = semicolon;
        this.cenv = cenv;
        this.scope = scope;
        if(ind == null) {
            this.ind = new IndentStr();
        } else {
            this.ind = ind;
        }
    }
    
    public String toStr() {
        Boolean res = doSwitch(statement);
        if(!res) {
            CodegenError.err("Statement builder", statStr);
        }
        return statStr;
    }
    
    private int getDim(Type type) {
        Type t = type;
        int dim = 0;
        while(t instanceof TypeList) {
            dim++;
            t = ((TypeList)t).getType();
        }
        return dim;
    }

    private String sizeofStr(String name, Type type) {
        Type t = type;
        int dim = 0;
        String ret = "(sizeof(";
        while(t instanceof TypeList) {
            dim++;
            t = ((TypeList)t).getType();
        }
        ret += new CBuildTypeName(t, new CPrintUtil.dummyCB(), false).toStr()+")";
        for(int i=0;i<dim;i++) {
            ret += " * " + name + ".sz[" + i +"]";
        }
        ret += ")";
        return ret;
    }
    
    private void enter(EObject obj) {}
    private void leave() {}

    @Override
    public Boolean caseAssign(Assign assign) {
        enter(assign);
        Map<String,String> assignAnn = TransUtil.getAnnotationsMap(assign);
        Map<String,String> targetAnn = TransUtil.getAnnotationsMap(assign.getTarget());
        Map<String,String> exprAnn = TransUtil.getAnnotationsMap(assign.getExpression());
        statStr += ind.ind() + "/*\n";
        if(!assignAnn.isEmpty()) statStr += ind.ind() + "A:" + assignAnn.toString() +"\n";
        if(!targetAnn.isEmpty()) statStr += ind.ind() + "T:" + targetAnn.toString() +"\n";
        if(!exprAnn.isEmpty()) statStr += ind.ind() + "E:" + exprAnn.toString() +"\n";
        statStr += ind.ind() + "*/\n";
        //TODO fix more complicated assignments
        /*
         * Which assignment exist:
         * 1) scalar and user type assignment of any expression - identical to c, no memory mgmt
         * 2) Lists fully indexed to scalar type or user type assigned any expression - identical to c, no memory mgmt
         * 3) List assignment of literal list - allocate target or replace content? Need temp variable?
         * 3a) When self assignment must use temp target, when target full variable free old and change pointer to temp, otherwise copy into place
         * 3b) When multi dim list and several list expressions loop over inner lists temp variable with pointer into larger list
         * 4) When function returning list (allocated inside function) copy or replace target var pointer.
         * 5) When list expression inside other list expression use temp variables
         */
        List<Expression> indices = null;
        String selfAnn = assignAnn == null?null:assignAnn.get("Variable_VarLocalAccess");
        boolean selfAssign = (selfAnn == null)?false:VarLocalAccess.valueOf(selfAnn).equals(VarLocalAccess.self);
        switch (VarLocalAccess.valueOf(targetAnn.get("Variable_VarLocalAccess"))) {
        //Anything that is a normal scalar assignment CAL and c looks similar
        case scalar:
        case scalarUserType:
        case listSingle:
        case listMultiSingle:
        case listUserTypeSingle:
        case listMultiUserTypeSingle:
            indices = assign.getTarget().getIndex();
        case listMemberListMultiSingle:
        case listMemberListMultiUserTypeSingle:
        case listMemberListSingle:
        case listMemberListUserTypeSingle:
        case listMemberScalar:
        case listMemberScalarUserType:
        case memberListMultiSingle:
        case memberListMultiUserTypeSingle:
        case memberListSingle:
        case memberListUserTypeSingle:
        case memberScalar:
        case memberScalarUserType:
            if((indices==null || (indices!=null && indices.isEmpty())) &&
                    (assign.getTarget().getMember()!=null && !assign.getTarget().getMember().isEmpty())) {
                indices = assign.getTarget().getMember().get(assign.getTarget().getMember().size()-1).getIndex();
            }
            CBuildVarReference cVarRefS = new CBuildVarReference(assign.getTarget(), cenv, true, true);
            String varStrS = cVarRefS.toStr();
            String sz = assignAnn == null?"0":(assignAnn.get("Variable_ListSize")==null?"0":assignAnn.get("Variable_ListSize"));
            if(!sz.equals("0")) {
                statStr += ind.ind() + "reallocMoveArray" + new CBuildTypeName(assign.getTarget().getType(), new CPrintUtil.dummyCB(), false).toStr()+ "(";
                int len = 1;
                //We allow literal integer or variable name
                try {
                    Integer.parseInt(sz);
                } catch(NumberFormatException e) {
                    sz=CPrintUtil.validCName(sz);
                }
                if(sz.equals("-1")) {
                    //Should use indices as sizes
                    len=0;
                    sz="";
                    //FIXME should rather do temp variables to guarantee to be side effect free but CAL expressions should be side effect free
                    for (Iterator<Expression> i = indices.iterator(); i.hasNext();) {
                        len++;
                        Expression e = i.next();
                        sz += new CBuildExpression(e, cenv).toStr();
                        if (i.hasNext()) sz += ",";
                    }
                }
                statStr += varStrS + ", NULL, (__arrayArg) {" + len + ",{" + sz + "}});" + ind.nl();
            }
            statStr += ind.ind() + new CBuildVarReference(assign.getTarget(), cenv).toStr() + " = ";
            statStr += new CBuildExpression(assign.getExpression(), cenv).toStr();
            if(semicolon) {
                statStr += ";" + ind.nl();
            }
            break;
        //Assignment of a list to a list (single dimension) built-in type
        case list:
        case listMemberList:
        case memberList:
        case listMultiList:
        case memberListMultiList:
        case listMemberListMultiList:
            if(assign.getExpression() instanceof VariableExpression) {
                CBuildVarReference cVarRef = new CBuildVarReference(assign.getTarget(), cenv, true, true);
                String varStr = cVarRef.toStr();
                statStr += ind.ind() + "copy" + new CBuildTypeName(assign.getTarget().getType(), new CPrintUtil.dummyCB(), false).toStr()+ "(";
                statStr += varStr + ", ";
                CBuildExpression cVarExpr = new CBuildExpression(assign.getExpression(), cenv, true, true,false);
                statStr += cVarExpr.toStr() + ", ";
                statStr += cVarRef.indexStr() + ", ";
                statStr += cVarExpr.indexStr() + ", ";
                statStr += "maxArraySz(&" + cVarRef.sizeStr() + ", &" + cVarExpr.sizeStr(true) + ", " + cVarRef.indexLen() + ")";
                statStr += ")";
                statStr += ";" + ind.nl();
                String tempAnn = assignAnn == null?null:assignAnn.get("Variable_VarLocalAccess");
                boolean tempAssign = (tempAnn == null)?false:VarLocalAccess.valueOf(tempAnn).equals(VarLocalAccess.temp);
                if(tempAssign) {
                    statStr += ind.ind() + cVarRef.flagsStr() + "|=0x8;" + ind.nl();
                }
            } else {
                if(assign.getExpression() instanceof FunctionCall) {
                    CBuildVarReference cVarRefF = new CBuildVarReference(assign.getTarget(), cenv, true, true);
                    String varStrF = cVarRefF.toStr();
                    statStr += ind.ind() + "free" + new CBuildTypeName(assign.getTarget().getType(), new CPrintUtil.dummyCB(), false).toStr() + "(" + varStrF + ", TRUE);" + ind.nl();
                    CBuildVarReference cVarRef = new CBuildVarReference(assign.getTarget(), cenv, false, true);
                    String varStr = cVarRef.toStr();
                    statStr += ind.ind() + varStr + " = ";
                    CBuildExpression cExpr = new CBuildExpression(assign.getExpression(), cenv, false, false,false);
                    statStr += cExpr.toStr() + ";" + ind.nl();
                    String tempAnn = assignAnn == null?null:assignAnn.get("Variable_VarLocalAccess");
                    boolean tempAssign = (tempAnn == null)?false:VarLocalAccess.valueOf(tempAnn).equals(VarLocalAccess.temp);
                    if(tempAssign) {
                        statStr += ind.ind() + cVarRef.flagsStr() + "|=0x8;" + ind.nl();
                    }

                    /*
                    statStr += ind.ind() + "__tempExpr_" + assign.getExpression().getId() + " = " + new CBuildExpression(assign.getExpression(), cenv,true,false).toStr() + ";" + ind.nl();
                    statStr += ind.ind() + "memcpy(" + new CBuildVarReference(assign.getTarget(), cenv, true,false).toStr() + ", ";
                    statStr += "__tempExpr_" + assign.getExpression().getId() + ".p, ";
                    statStr += sizeofStr("__tempExpr_" + assign.getExpression().getId(),assign.getTarget().getType()) + ")";
                    */
                } else {
                    statStr += ind.ind() + "__tempVoidPointer = " + new CBuildExpression(assign.getExpression(), cenv,true,false,false).toStr() + ";" + ind.nl();
                    statStr += ind.ind() + "mem" + (selfAssign?"move":"cpy") + "(" + new CBuildVarReference(assign.getTarget(), cenv, true,false).toStr() + ", ";
                    statStr += "__tempVoidPointer, ";
                    statStr += new CBuildSizeOf(assign, cenv, false, false, 0, true).toStr() + ")";
                    if(semicolon) {
                        statStr += ";" + ind.nl();
                    }
                }
            }
            break;
            
        case string:
        case memberString:
        case listMemberString:
            if(assign.getExpression() instanceof VariableExpression) {
                CBuildVarReference cVarRef = new CBuildVarReference(assign.getTarget(), cenv, true, true);
                String varStr = cVarRef.toStr();
                statStr += ind.ind() + "copychar(";
                statStr += varStr + ", ";
                CBuildExpression cVarExpr = new CBuildExpression(assign.getExpression(), cenv, true, true,false);
                statStr += cVarExpr.toStr() + ", ";
                statStr += cVarRef.indexStr() + ", ";
                statStr += cVarExpr.indexStr() + ", ";
                statStr += "maxArraySz(&" + cVarRef.sizeStr() + ", &" + cVarExpr.sizeStr(true) + ", " + cVarRef.indexLen() + ")";
                statStr += ")";
                statStr += ";" + ind.nl();
                String tempAnn = assignAnn == null?null:assignAnn.get("Variable_VarLocalAccess");
                boolean tempAssign = (tempAnn == null)?false:VarLocalAccess.valueOf(tempAnn).equals(VarLocalAccess.temp);
                if(tempAssign) {
                    statStr += ind.ind() + cVarRef.flagsStr() + "|=0x8;" + ind.nl();
                }
            } else if(assign.getExpression() instanceof FunctionCall) {
                CBuildVarReference cVarRefF = new CBuildVarReference(assign.getTarget(), cenv, true, true);
                String varStrF = cVarRefF.toStr();
                statStr += ind.ind() + "free" + new CBuildTypeName(assign.getTarget().getType(), new CPrintUtil.dummyCB(), false).toStr() + "(" + varStrF + ", TRUE);" + ind.nl();
                CBuildVarReference cVarRef = new CBuildVarReference(assign.getTarget(), cenv, false, true);
                String varStr = cVarRef.toStr();
                statStr += ind.ind() + varStr + " = ";
                CBuildExpression cExpr = new CBuildExpression(assign.getExpression(), cenv, false, false,false);
                statStr += cExpr.toStr() + ";" + ind.nl();
                String tempAnn = assignAnn == null?null:assignAnn.get("Variable_VarLocalAccess");
                boolean tempAssign = (tempAnn == null)?false:VarLocalAccess.valueOf(tempAnn).equals(VarLocalAccess.temp);
                if(tempAssign) {
                    statStr += ind.ind() + cVarRef.flagsStr() + "|=0x8;" + ind.nl();
                }
            } else if(assign.getExpression() instanceof BinaryExpression &&
                    ((BinaryExpression)assign.getExpression()).getOperator().equals("+")) {
                boolean op1IsLit = ((BinaryExpression)assign.getExpression()).getOperand1() instanceof StringLiteral;
                boolean op2IsLit = ((BinaryExpression)assign.getExpression()).getOperand2() instanceof StringLiteral;
                statStr += ind.ind() + "stringadd" + (op1IsLit?"l":"v") + (op2IsLit?"l":"v") + "(";
                CBuildVarReference cVarRef = new CBuildVarReference(assign.getTarget(), cenv, true, true);
                String varStr = cVarRef.toStr();
                statStr += varStr + ", ";
                if(op1IsLit) {
                    StringLiteral strLit = (StringLiteral) ((BinaryExpression)assign.getExpression()).getOperand1();
                    statStr += "\"" + strLit.getValue() +"\"";
                } else {
                    CBuildExpression cVarExpr = new CBuildExpression(((BinaryExpression)assign.getExpression()).getOperand1(), cenv, true, true,false);
                    statStr += cVarExpr.toStr();
                }
                statStr += ", ";
                if(op2IsLit) {
                    StringLiteral strLit = (StringLiteral) ((BinaryExpression)assign.getExpression()).getOperand2();
                    statStr += "\"" + strLit.getValue() +"\"";
                } else {
                    CBuildExpression cVarExpr = new CBuildExpression(((BinaryExpression)assign.getExpression()).getOperand2(), cenv, true, true,false);
                    statStr += cVarExpr.toStr();
                }
                statStr += ");" + ind.nl();
                String tempAnn = assignAnn == null?null:assignAnn.get("Variable_VarLocalAccess");
                boolean tempAssign = (tempAnn == null)?false:VarLocalAccess.valueOf(tempAnn).equals(VarLocalAccess.temp);
                if(tempAssign) {
                    statStr += ind.ind() + cVarRef.flagsStr() + "|=0x8;" + ind.nl();
                }
            } else if(assign.getExpression() instanceof StringLiteral) {
                statStr += ind.ind() + "stringassignl(";
                CBuildVarReference cVarRef = new CBuildVarReference(assign.getTarget(), cenv, true, true);
                String varStr = cVarRef.toStr();
                statStr += varStr + ", ";
                StringLiteral strLit = (StringLiteral) assign.getExpression();
                statStr += "\"" + strLit.getValue() +"\"";
                statStr += ");" + ind.nl();
                String tempAnn = assignAnn == null?null:assignAnn.get("Variable_VarLocalAccess");
                boolean tempAssign = (tempAnn == null)?false:VarLocalAccess.valueOf(tempAnn).equals(VarLocalAccess.temp);
                if(tempAssign) {
                    statStr += ind.ind() + cVarRef.flagsStr() + "|=0x8;" + ind.nl();
                }
            } else {
                statStr += "/*NOT YET IMPLEMENTED (2)*/";
                statStr += ind.ind() + new CBuildVarReference(assign.getTarget(), cenv).toStr() + " = ";
                statStr += new CBuildExpression(assign.getExpression(), cenv).toStr();
                if(semicolon) {
                    statStr += ";" + ind.nl();
                }
            }
            
            break;
            
        case listMulti:
        case listMemberListMulti:
        case memberListMulti:

        case listMemberListUserType:
        case listUserType:
        case inlinedMember:
        case listMemberListMultiUserType:
        case listMultiUserType:
        case memberListMultiUserType:
        case memberListUserType:
            //Handle assignments from var in the same way for multi-dim lists, others should have been broken up already
            if(assign.getExpression() instanceof VariableExpression) {
                CBuildVarReference cVarRef = new CBuildVarReference(assign.getTarget(), cenv, true, true);
                String varStr = cVarRef.toStr();
                statStr += ind.ind() + "copy" + new CBuildTypeName(assign.getTarget().getType(), new CPrintUtil.dummyCB(), false).toStr()+ "(";
                statStr += varStr + ", ";
                CBuildExpression cVarExpr = new CBuildExpression(assign.getExpression(), cenv, true, true,false);
                statStr += cVarExpr.toStr() + ", ";
                statStr += cVarRef.indexStr() + ", ";
                statStr += cVarExpr.indexStr() + ", ";
                statStr += "maxArraySz(&" + cVarRef.sizeStr() + ", &" + cVarExpr.sizeStr(true) + ", " + cVarRef.indexLen() + ")";
                statStr += ")";
                statStr += ";" + ind.nl();
                String tempAnn = assignAnn == null?null:assignAnn.get("Variable_VarLocalAccess");
                boolean tempAssign = (tempAnn == null)?false:VarLocalAccess.valueOf(tempAnn).equals(VarLocalAccess.temp);
                if(tempAssign) {
                    statStr += ind.ind() + cVarRef.flagsStr() + "|=0x8;" + ind.nl();
                }
                break;
            }
        case refMember:
            
        default:
            statStr += ind.ind() +"/*NOT IMPLEMENTED YET (1)*/";
            statStr += ind.ind() + new CBuildVarReference(assign.getTarget(), cenv).toStr() + " = ";
            statStr += new CBuildExpression(assign.getExpression(), cenv).toStr();
            if(semicolon) {
                statStr += ";" + ind.nl();
            }
        }
        /*
        if(assign.getExpression() instanceof TypeConstructorCall && !withinDeclaration()) {
            TypeConstructorCall expr = (TypeConstructorCall) assign.getExpression();
            statStr += (validCName(expr.getName()));
            statStr += ("(");
            if(hasIndex(assign.getTarget())) statStr += ("&");
            doSwitch(assign.getTarget());
            statStr += (", ");
            for (Iterator<Expression> i = expr.getParameters().iterator(); i.hasNext();) {
                Expression p = i.next();
                doSwitch(p);
                if (i.hasNext()) statStr += Comma();
            }
            statStr += Right();
            statStr += ln(";");
            leave();
            return true;
        }
        
        if(isIndir(assign.getTarget().getDeclaration())) statStr += ("*");
        doSwitch(assign.getTarget());
        statStr += (" = ");
        doSwitch(assign.getExpression());
        statStr += ln(";");
        */
        leave();
        return true;
    }

}
