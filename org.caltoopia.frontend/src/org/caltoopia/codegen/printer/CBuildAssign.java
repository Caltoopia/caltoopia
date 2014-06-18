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
import org.caltoopia.ir.IfExpression;
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

/*
 * This class generates a string with an assignment statement.
 * Some statements like assign and body are broken out of the
 * general CBuildStatement. This printer also handles allocation
 * of memory for dynamic size arrays, since it can't be determined
 * until runtime in each assignment if the elements fit in the array. 
 * 
 * Quality: 3, seems to work for all tests but could have problems 
 *             due to the complex nature of memory allocation.
 */
public class CBuildAssign extends IrSwitch<Boolean> {
    String statStr="";
    Statement statement;
    boolean semicolon = true;
    CEnvironment cenv = null;
    private IndentStr ind = null;
    Scope scope = null;

    /*
     * Constructor for building a long string containing the 
     * c-code of an assignment and potentially memory allocation. 
     * 
     * statement: assignment to be printed
     * cenv: input/output variable collecting information that is 
     *       needed in makefiles etc, same object used for all CBuilders
     * ind: indentation object, passed in so that sub-parts maintains overall indentation level
     * semicolon: should always be true, was introduced to support the CBuildInlineBody which is not used. FIXME remove parameter
     * scope: the surrounding scope (Action or Block) of the statement
     */
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
    
    /*
     * Do the actual generation of the assignment string, use as:
     * new CBuildAssign(...).toStr()
     */
    public String toStr() {
        Boolean res = doSwitch(statement);
        if(!res) {
            CodegenError.err("Statement builder", statStr);
        }
        return statStr;
    }
    
    //Calc dimension of array type
    private int getDim(Type type) {
        Type t = type;
        int dim = 0;
        while(t instanceof TypeList) {
            dim++;
            t = ((TypeList)t).getType();
        }
        return dim;
    }

    /*
     * Build a string giving the size of an array (in bytes)
     * name: string prefix to reach array metadata (which include the dimension sizes)
     * type: variable type 
     */
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
        //print all annotations for assignment to help in debugging
        statStr += ind.ind() + "/*\n";
        if(!assignAnn.isEmpty()) statStr += ind.ind() + "A:" + assignAnn.toString() +"\n";
        if(!targetAnn.isEmpty()) statStr += ind.ind() + "T:" + targetAnn.toString() +"\n";
        if(!exprAnn.isEmpty()) statStr += ind.ind() + "E:" + exprAnn.toString() +"\n";
        statStr += ind.ind() + "*/\n";
        String initAnn = assignAnn == null?null:assignAnn.get("Variable_Allocate");
        boolean initAssign = (initAnn == null)?false:initAnn.equals("true");
        if(initAssign) {
            //This is just an assignment to signal that the target var should be initialized.
            statStr += ind.ind() + new CBuildVarDeclaration(assign.getTarget().getDeclaration(), cenv, false).initializeToStr() +";" + ind.nl();
            leave();
            return true;
        }
        /*
         * The transformation passes have altered the IR so only a smaller set of assignment exist. 
         * Which assignment exist:
         * 1) scalar builtin type assignment of any expression - identical to c, no memory mgmt
         * 2) scalar user type assignment of variable expression - must handle struct with structs or arrays
         * 3) Arrays fully indexed to scalar builtin type assigned any expression - identical to c, no memory mgmt
         * 4) Arrays fully indexed to scalar user type assigned variable expression - must handle struct with structs or arrays
         * 5) Full array assigned to full array
         * 6) Multi-dim array assigned lower dimension array
         * 7) Function returning list (allocated inside function) replace target var allocation (since always to same dimension (temp) var).
         */
        List<Expression> indices = null; //Hold the list of index for target variable
        String selfAnn = assignAnn == null?null:assignAnn.get("Variable_VarLocalAccess");
        boolean selfAssign = (selfAnn == null)?false:VarLocalAccess.valueOf(selfAnn).equals(VarLocalAccess.self);
        switch (VarLocalAccess.valueOf(targetAnn.get("Variable_VarLocalAccess"))) {
        case scalar:
        case listSingle:
        case listMultiSingle:
            indices = assign.getTarget().getIndex();
        case listMemberListMultiSingle:
        case listMemberListSingle:
        case listMemberScalar:
        case memberListMultiSingle:
        case memberListSingle:
        case memberScalar:
            //Anything that is a normal scalar assignment CAL and c looks similar
            if((indices==null || (indices!=null && indices.isEmpty())) &&
                    (assign.getTarget().getMember()!=null && !assign.getTarget().getMember().isEmpty())) {
                indices = assign.getTarget().getMember().get(assign.getTarget().getMember().size()-1).getIndex();
            }
            //Create string of target var without indices and as a ref
            CBuildVarReference cVarRefS = new CBuildVarReference(assign.getTarget(), cenv, true, true);
            String varStrS = cVarRefS.toStr();
            /*
             * get annotation of array size (only one dimension) 
             *  0=already static allocated
             *  var string or >0 insert allocation of array with the size in annotation before assignment 
             *  -1=insert allocation of array corresponding to indices before assignment
             */
            String sz = assignAnn == null?"0":(assignAnn.get("Variable_ListSize")==null?"0":assignAnn.get("Variable_ListSize"));
            if(!sz.equals("0")) {
                //reallocMoveArray takes a dst and src array and a arrayArg specifying wanted minimum size, 
                //it never shrinks an array.
                statStr += ind.ind() + "reallocMoveArray" + new CBuildTypeName(assign.getTarget().getType(), new CPrintUtil.dummyCB(), false).asNameStr()+ "(";
                int len = 1;
                //We allow literal integer or variable name
                try {
                    Integer.parseInt(sz);
                } catch(NumberFormatException e) {
                    sz=CPrintUtil.validCName(sz);
                }
                if(sz.equals("-1")) {
                    //Use indices as sizes, this expand the array to cover this element
                    len=0;
                    sz="";
                    //FIXME should rather do transformations to temp variables to guarantee to be side effect free
                    //but CAL expressions should be side effect free, even if a function is called twice instead of once
                    for (Iterator<Expression> i = indices.iterator(); i.hasNext();) {
                        len++;
                        Expression e = i.next();
                        sz += new CBuildExpression(e, cenv).toStr();
                        if (i.hasNext()) sz += ",";
                    }
                }
                //src==NULL since no elements to copy in
                statStr += varStrS + ", NULL, (__arrayArg) {" + len + ",{" + sz + "}});" + ind.nl();
            }
            //Create string of target var with indices
            statStr += ind.ind() + new CBuildVarReference(assign.getTarget(), cenv).toStr() + " = ";
            statStr += new CBuildExpression(assign.getExpression(), cenv).toStr();
            if(semicolon) {
                statStr += ";" + ind.nl();
            }
            break;
        case scalarUserType:
        case listUserTypeSingle:
        case listMultiUserTypeSingle:
            indices = assign.getTarget().getIndex();
        case listMemberListMultiUserTypeSingle:
        case listMemberListUserTypeSingle:
        case listMemberScalarUserType:
        case memberListMultiUserTypeSingle:
        case memberListUserTypeSingle:
        case memberScalarUserType:
            //Scalar assignment of user type variable use copyStruct
            if((indices==null || (indices!=null && indices.isEmpty())) &&
                    (assign.getTarget().getMember()!=null && !assign.getTarget().getMember().isEmpty())) {
                indices = assign.getTarget().getMember().get(assign.getTarget().getMember().size()-1).getIndex();
            }
            //Create string of target var without indices and as a ref
            cVarRefS = new CBuildVarReference(assign.getTarget(), cenv, true, true);
            varStrS = cVarRefS.toStr();
            /*
             * get annotation of array size (only one dimension) 
             *  0=already static allocated
             *  var string or >0 insert allocation of array with the size in annotation before assignment 
             *  -1=insert allocation of array corresponding to indices before assignment
             */
            sz = assignAnn == null?"0":(assignAnn.get("Variable_ListSize")==null?"0":assignAnn.get("Variable_ListSize"));
            if(!sz.equals("0")) {
                //reallocMoveArray takes a dst and src array and a arrayArg specifying wanted minimum size, 
                //it never shrinks an array.
                statStr += ind.ind() + "reallocMoveArray" + new CBuildTypeName(assign.getTarget().getType(), new CPrintUtil.dummyCB(), false).asNameStr()+ "(";
                int len = 1;
                //We allow literal integer or variable name
                try {
                    Integer.parseInt(sz);
                } catch(NumberFormatException e) {
                    sz=CPrintUtil.validCName(sz);
                }
                if(sz.equals("-1")) {
                    //Use indices as sizes, this expand the array to cover this element
                    len=0;
                    sz="";
                    //FIXME should rather do transformations to temp variables to guarantee to be side effect free
                    //but CAL expressions should be side effect free, even if a function is called twice instead of once
                    for (Iterator<Expression> i = indices.iterator(); i.hasNext();) {
                        len++;
                        Expression e = i.next();
                        sz += new CBuildExpression(e, cenv).toStr();
                        if (i.hasNext()) sz += ",";
                    }
                }
                //src==NULL since no elements to copy in
                statStr += varStrS + ", NULL, (__arrayArg) {" + len + ",{" + sz + "}});" + ind.nl();
            }
            /*
             * Can be type constructor, variable expression,
             * if-expression (since only pointers), or
             * function expression (returning pointer)
             * all other expressions should have been converted
             * to temp var. (e.g. case expression)
             */
            if(assign.getExpression() instanceof VariableExpression || assign.getExpression() instanceof IfExpression) {
                //Create copyStructT_t() call
                statStr += ind.ind() + "copyStruct" + new CBuildTypeName(assign.getTarget().getType(), new CPrintUtil.dummyCB(), false).asNameStr()+ "(";
                statStr += "&"+new CBuildVarReference(assign.getTarget(), cenv).toStr() + ", ";
                statStr += new CBuildExpression(assign.getExpression(), cenv).toStr()+")";
            } else if(assign.getExpression() instanceof FunctionCall) {
                statStr += ind.ind() + new CBuildVarReference(assign.getTarget(), cenv).toStr() + " = ";
                statStr += new CBuildExpression(assign.getExpression(), cenv).toStr();
            } else if(assign.getExpression() instanceof TypeConstructorCall) {
                String tag = ((TypeConstructorCall)assign.getExpression()).getName();
                statStr += ind.ind() + "construct" + new CBuildTypeName(assign.getTarget().getType(), new CPrintUtil.dummyCB(), false).asTagNameStr(tag)+ "(";
                statStr += "&" + new CBuildVarReference(assign.getTarget(), cenv).toStr();
                String tempStr = "";
                for(Expression e: ((TypeConstructorCall)assign.getExpression()).getParameters()) {
                    statStr += ", ";
                    CBuildExpression tcparam = new CBuildExpression(e, cenv,false,true,true);
                    statStr += tcparam.toStr();
                    if(e instanceof VariableExpression && UtilIR.isList(e.getType())) {
                        /*
                         * When using an (temp) array make sure to flag it as unallocated
                         * to prevent freeing the actual array memory now belonging to
                         * the user type object.
                         */
                        tempStr +=";" +ind.nl() +  ind.ind() + tcparam.flagsStr() + " = 0x0";
                    }
                }
                statStr += ")";
                statStr += tempStr;
            } else {
                statStr += ind.ind() + "/*NOT YET IMPLEMENTED "+ assign.getExpression().getClass().toString() + "*/" + new CBuildVarReference(assign.getTarget(), cenv).toStr() + " = ";
                statStr += new CBuildExpression(assign.getExpression(), cenv).toStr();
            }
            if(semicolon) {
                statStr += ";" + ind.nl();
            }
            break;
        case list:
        case listMemberList:
        case memberList:
        case listMultiList:
        case memberListMultiList:
        case listMemberListMultiList:
            //Assignment of a list to a list (single dimension) built-in type
            if(assign.getExpression() instanceof VariableExpression) {
                /*
                 * Assignment of variable to variable
                 * Utilize the copy array function which takes the parameters
                 * dst and src array, dst and src index, and the calculated maximum dimension sizes between the two arrays
                 * TODO maxArraySz is separate to later allow optimization and calculating such array size for several assignments
                 * and avoid resizing.
                 */
                CBuildVarReference cVarRef = new CBuildVarReference(assign.getTarget(), cenv, true, true);
                String varStr = cVarRef.toStr();
                statStr += ind.ind() + "copy" + new CBuildTypeName(assign.getTarget().getType(), new CPrintUtil.dummyCB(), false).asNameStr()+ "(";
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
                    //When temporary target var set that flag to allow stealing the memory
                    statStr += ind.ind() + cVarRef.flagsStr() + "|=0x8;" + ind.nl();
                }
            } else {
                if(assign.getExpression() instanceof FunctionCall) {
                    /*
                     * Assignment of result of function call to variable
                     * The array is allocated inside the function,
                     * hence free any memory already allocated for target variable
                     * before overwriting with the new array.
                     */
                    CBuildVarReference cVarRefF = new CBuildVarReference(assign.getTarget(), cenv, true, true);
                    String varStrF = cVarRefF.toStr();
                    statStr += ind.ind() + "free" + new CBuildTypeName(assign.getTarget().getType(), new CPrintUtil.dummyCB(), false).asNameStr() + "(" + varStrF + ", TRUE);" + ind.nl();
                    CBuildVarReference cVarRef = new CBuildVarReference(assign.getTarget(), cenv, false, true);
                    String varStr = cVarRef.toStr();
                    statStr += ind.ind() + varStr + " = ";
                    CBuildExpression cExpr = new CBuildExpression(assign.getExpression(), cenv, false, false,false);
                    statStr += cExpr.toStr() + ";" + ind.nl();
                    String tempAnn = assignAnn == null?null:assignAnn.get("Variable_VarLocalAccess");
                    boolean tempAssign = (tempAnn == null)?false:VarLocalAccess.valueOf(tempAnn).equals(VarLocalAccess.temp);
                    if(tempAssign) {
                        //When temporary target var set that flag to allow stealing the memory
                        statStr += ind.ind() + cVarRef.flagsStr() + "|=0x8;" + ind.nl();
                    }
                } else {
                    /*
                     * This should never be reached transformation should have removed all options.
                     * Anyway try a best effort standard memcpy, but watch out for copying to unallocated memory
                     */
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
            //Do the string type assignments
            if(assign.getExpression() instanceof VariableExpression) {
                /*
                 * Assignment of variable to variable
                 * Utilize the copy array function which takes the parameters
                 * dst and src char array, dst and src index, and the calculated maximum dimension sizes between the two strings
                 * FIXME since strings always single dimension enough to use the right hand side string size
                 */
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
                    //When temporary target var set that flag to allow stealing the memory
                    statStr += ind.ind() + cVarRef.flagsStr() + "|=0x8;" + ind.nl();
                }
            } else if(assign.getExpression() instanceof FunctionCall) {
                /*
                 * Assignment of result of function call to variable
                 * The char array is allocated inside the function,
                 * hence free any memory already allocated for target variable
                 * before overwriting with the new array.
                 */
                CBuildVarReference cVarRefF = new CBuildVarReference(assign.getTarget(), cenv, true, true);
                String varStrF = cVarRefF.toStr();
                statStr += ind.ind() + "free" + new CBuildTypeName(assign.getTarget().getType(), new CPrintUtil.dummyCB(), false).asNameStr() + "(" + varStrF + ", TRUE);" + ind.nl();
                CBuildVarReference cVarRef = new CBuildVarReference(assign.getTarget(), cenv, false, true);
                String varStr = cVarRef.toStr();
                statStr += ind.ind() + varStr + " = ";
                CBuildExpression cExpr = new CBuildExpression(assign.getExpression(), cenv, false, false,false);
                statStr += cExpr.toStr() + ";" + ind.nl();
                String tempAnn = assignAnn == null?null:assignAnn.get("Variable_VarLocalAccess");
                boolean tempAssign = (tempAnn == null)?false:VarLocalAccess.valueOf(tempAnn).equals(VarLocalAccess.temp);
                if(tempAssign) {
                    //When temporary target var set that flag to allow stealing the memory
                    statStr += ind.ind() + cVarRef.flagsStr() + "|=0x8;" + ind.nl();
                }
            } else if(assign.getExpression() instanceof BinaryExpression &&
                    ((BinaryExpression)assign.getExpression()).getOperator().equals("+")) {
                /*
                 * For strings we also support assignment of a variable with the concatenation of 2 strings
                 * Either l=literal or v=variable expression
                 */
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
                    //When temporary target var set that flag to allow stealing the memory
                    statStr += ind.ind() + cVarRef.flagsStr() + "|=0x8;" + ind.nl();
                }
            } else if(assign.getExpression() instanceof StringLiteral) {
                /*
                 * String variable could also be assigned a string literal
                 */
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
                    //When temporary target var set that flag to allow stealing the memory
                    statStr += ind.ind() + cVarRef.flagsStr() + "|=0x8;" + ind.nl();
                }
            } else {
                /*
                 * Should never be reached all other options should have been removed by transformations
                 * Anyway try to print as a simple assignment.
                 */
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
            /*
             * Handle assignments from variable expression of multi-dim array type.
             * All other right hand side expressions should have been transformed.
             */
            if(assign.getExpression() instanceof VariableExpression) {
                /*
                 * Assignment of variable to variable
                 * Utilize the copy array function which takes the parameters
                 * dst and src array, dst and src index, and the calculated maximum dimension sizes between the two arrays
                 * TODO maxArraySz is separate to later allow optimization and calculating such array size for several assignments
                 * and avoid resizing.
                 */
                CBuildVarReference cVarRef = new CBuildVarReference(assign.getTarget(), cenv, true, true);
                String varStr = cVarRef.toStr();
                statStr += ind.ind() + "copy" + new CBuildTypeName(assign.getTarget().getType(), new CPrintUtil.dummyCB(), false).asNameStr()+ "(";
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
                    //When temporary target var set that flag to allow stealing the memory
                    statStr += ind.ind() + cVarRef.flagsStr() + "|=0x8;" + ind.nl();
                }
                break;
            }
        case refMember:
            
        default:
            /*
             * Should never reach here all other options should have been transformed
             * Anyway try to print as a simple assignment
             */
            statStr += ind.ind() +"/*NOT IMPLEMENTED YET (1)*/";
            statStr += ind.ind() + new CBuildVarReference(assign.getTarget(), cenv).toStr() + " = ";
            statStr += new CBuildExpression(assign.getExpression(), cenv).toStr();
            if(semicolon) {
                statStr += ";" + ind.nl();
            }
        }
        leave();
        return true;
    }

}
