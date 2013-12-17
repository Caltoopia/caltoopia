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
import org.caltoopia.codegen.transformer.IrTransformer;
import org.caltoopia.codegen.transformer.TransUtil;
import org.caltoopia.codegen.transformer.analysis.IrTypeStructureAnnotation.TypeMember;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarAccess;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarLocalAccess;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarType;
import org.caltoopia.codegen.transformer.analysis.IrVariablePlacementAnnotation.VarPlacement;
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
import org.caltoopia.ir.TagOf;
import org.caltoopia.ir.TaggedTupleFieldRead;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeConstructorCall;
import org.caltoopia.ir.TypeList;
import org.caltoopia.ir.TypeString;
import org.caltoopia.ir.TypeUndef;
import org.caltoopia.ir.UnaryExpression;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.ir.VariableExternal;
import org.caltoopia.ir.VariableImport;
import org.caltoopia.ir.VariableReference;
import org.caltoopia.ir.util.IrSwitch;
import org.eclipse.emf.ecore.EObject;

/*
 * This class generates a string for an expression.
 * It covers all expressions currently and should 
 * benefit from further being broken up.
 * 
 * Quality: 3, works but variable expression printing is complicated and could have errors
 *             also type constructor calls have limited testing.
 */
public class CBuildExpression extends IrSwitch<Boolean> {
    String exprStr="";
    String refStr ="";
    String castStr ="";
    String flagsStr = "";
    String lastVarStr="";
    List<Expression> indexArray;
    List<Expression> sizeArray;
    Expression expression = null;
    boolean sepIndex = false;
    CEnvironment cenv = null;
    boolean rangechk = false; //TODO be able to control this
    int dim = 0;
    boolean asRef = false;
    boolean asArrayPart = false;
    int level = 0;
    /*
     * Constructor for building a long string containing the 
     * c-code of an expression. The expression is printed as 
     * a single line of c-code to be embedded into a statement.
     * 
     * expression: expression to be printed
     * cenv: input/output variable collecting information that is 
     *       needed in makefiles etc, same object used for all CBuilders
     * asRef: print the variable as a c-code pointer, i.e. if not already a pointer prefix with "&"
     * sepIndex: the expStr will not have the indices of the variable (or last user type member)
     *           instead these can be access by indexStr(), hence only relevant to variable expressions.
     *  asArrayPart: build up the metadata structure of a sub-array around the multi-dim variable 
     *               expression, to avoid copying to temp array. Only used for input params 
     *               to functions/procedures.
     */
    public CBuildExpression(Expression expression, CEnvironment cenv, boolean asRef, boolean sepIndex, boolean asArrayPart) {
        exprStr="";
        refStr="";
        indexArray = new ArrayList<Expression>();
        sizeArray = new ArrayList<Expression>();
        this.expression = expression;
        this.cenv = cenv;
        this.asRef = asRef;
        this.sepIndex = sepIndex;
        this.asArrayPart = asArrayPart;
        level = 0;
        dim = 0;
    }
    
    //Use this constructor as much as possible
    public CBuildExpression(Expression expression, CEnvironment cenv) {
        this(expression, cenv,false,false,false);
    }

    /*
     * Do the actual generation of the expression string, use as:
     * new CBuildExpression(...).toStr()
     */
    public String toStr() {
        Boolean res = doSwitch(expression);
        return refStr + castStr + exprStr;
    }

    /*
     * Do the generation of the index string as a struct,
     * that can be used with the array copy functions.
     * Must have called toStr() first.
     */
    public String indexStr() {
        String indexStr = "(__arrayArg) {";
        indexStr += indexArray.size() + ",{";
        for(Iterator<Expression> i = indexArray.iterator();i.hasNext();) {
            Expression e = i.next();
            indexStr += new CBuildExpression(e, cenv).toStr();
            if(i.hasNext()) indexStr += ", ";
        }
        indexStr += "}}";
        return indexStr;
    }

    /*
     * Do the generation of the size string as a struct,
     * that can be used with the array copy functions.
     * Must have called toStr() first.
     * partial: skip the dimensions that are covered by the indices
     */
    public String sizeStr(boolean partial) {
        int n = partial?indexArray.size():0;
        String indexStr = "(__arrayArg) {";
        if(expression.getType() instanceof TypeString) {
            indexStr += "1,{";
            indexStr += lastVarStr +".sz[0]";
        } else {
            indexStr += sizeArray.size()-n + ",{";
            for(int i = n; i<sizeArray.size();i++) {
                Expression e = sizeArray.get(i);
                if(e!=null) {
                    indexStr += new CBuildExpression(e, cenv).toStr();
                } else {
                    indexStr += "/*dynamic*/";
                    indexStr += lastVarStr +".sz[" + i + "]";
                }
                if(i<(sizeArray.size()-1)) indexStr += ", ";
            }
        }
        indexStr += "}}";
        return indexStr;
    }

    public String flagsStr() {
        return flagsStr + ".flags";
    }
    
    public String tagStr() {
        return exprStr + "->tag";
    }

    public int indexLen() {
        return indexArray.size();
    }

    public int sizeLen(boolean partial) {
        return sizeArray.size()-(partial?indexArray.size():0);
    }

    //Used when doing nested list expressions and want to keep track of the nesting level
    public CBuildExpression dimension(int dim) {
        this.dim = dim;
        return this;
    }
    
    private void enter(EObject obj) {level++;}
    private void leave() {level--;}
    
    //---------------------util------------------------------------
    /*
     * Prints indexStr, but also returns boolean true when the resulting type is not a list
     * The result indexStr is only used when printing index with variable
     * otherwise when separate it is generated in indexStr(). 
     * NB! it is only the last member when user types that can have separate index,
     * previous indices are always printed with the variable.
     * Since the array is allocated as a single memory allocation we multiply
     * indices with dimension sizes to reach correct element. 
     * [((((i0)*sz1+i1)*sz2+i2)*sz3+i3)], when fewer indices the +iX is muted
     * When static dimension sizes use those otherwise pick it from metadata.
     * 
     * index: list of indices expressions
     * varType: variable's type
     * varStr: name of variable including all prefixing etc
     * sep: if the indices string is printed separate
     */
    protected Boolean indexPStr(List<Expression> index, Type varType, String varStr, boolean sep) {
        List<Expression> szExpr = new ArrayList<Expression>();
        List<Expression> indExpr = new ArrayList<Expression>();
        String indexStr ="";
        lastVarStr = varStr;
        if(index!=null && !index.isEmpty() && varType instanceof TypeList) {
            Type list = varType;
            while(list instanceof TypeList) {
                szExpr.add(((TypeList)list).getSize());
                list = ((TypeList)list).getType();
            }
            list = varType;
            indexStr += ("[");
            for (int i = 0; i<index.size();i++) {
                indexStr += ("(");
            }
            for (int i = 0; i<index.size();i++) {
                Expression e = index.get(i);
                indExpr.add(e);
                if(i>0) {
                    indexStr += ")*";
                    if(szExpr.get(i)==null) {
                        indexStr += varStr + ".sz["+ (i-1) +"]+";
                    } else {
                        indexStr += new CBuildExpression(szExpr.get(i),cenv).toStr() +"+";
                    }
                }
                if(rangechk) {
                    indexStr += ("RANGECHK(");
                }
                indexStr += new CBuildExpression(e, cenv).toStr();
                if(rangechk) {
                    indexStr += (",");
                    indexStr += varStr + ".sz["+ i +"]";
                    indexStr += (")");
                }
                list = ((TypeList)list).getType();
            }
            indexStr += ")";
            //Any more dimensions to multiply sizes?
            if(list instanceof TypeList) {
                int i = index.size();
                Type t = list;
                while(t instanceof TypeList) {
                    indexStr += "*";
                    if(szExpr.get(i)==null) {
                        indexStr += varStr + ".sz["+ (i) +"]";
                    } else {
                        indexStr += new CBuildExpression(szExpr.get(i),cenv).toStr();
                    }
                    i++;
                    t = ((TypeList)t).getType();
                }
            }
            indexStr += ("]");
            if(!sep) {
                exprStr += indexStr;
            }
            indexArray.addAll(indExpr);
            sizeArray.addAll(szExpr);
            return !(list instanceof TypeList);
        }
        Type list = varType;
        while(list instanceof TypeList) {
            sizeArray.add(((TypeList)list).getSize());
            list = ((TypeList)list).getType();
        }
        return false;
    }
    
    //FIXME remove function, it's a leftover from when we did static placement
    private boolean directMember(Member member) {
        boolean direct = true;
        return direct;
    }
    
    //FIXME should be removed, have the return value based on other annotations instead
    private boolean directVar(VariableExpression var) {
        VarPlacement varPlacement = VarPlacement.valueOf(TransUtil.getAnnotationArg(var, IrTransformer.VARIABLE_ANNOTATION, "VarPlacement"));
        boolean direct = true;
        switch(varPlacement) {
        case unknown:
            CodegenError.err("Expression builder", "unknown placement of var expr " + var.getVariable().getName());
        case constant:
        case actor: //Placed in constructor
        case auto:
        case heap:
        case code:
        case autoDeepHeap:
        case autoListDeepHeap:
        case autoListHeap:
            direct = true;
            break;
        case ref: //used when passing as parameter as ref
        case fifo:
        case fifoDeepFifo:
        case autoListDeepFifo:
        case autoListFifo:
            direct = false;
            break;
        default:
        }
        return direct;
    }
    
    //Translation between CAL and c for arithmetic operations
    private void printOperator(String operator) {
        if (operator.equals("=")) {
            exprStr += ("==");
        } else if (operator.equals(":=")) {
            exprStr += ("=");
        } else if (operator.equals("and")) {
            exprStr += ("&&");
        } else if (operator.equals("or")) {
            exprStr += ("||");
        } else if (operator.equals("bitand")) {
            exprStr += ("&");
        } else if (operator.equals("bitor")) {
            exprStr += ("|");
        } else if (operator.equals("not")) {
            exprStr += ("!");
        } else if (operator.equals("mod")) {
            exprStr += ("%");
        } else if (operator.equals(">>>")) {
            exprStr += (">>");
        } else {
            exprStr += (operator);
        }           
        
    }

    //---------------------caseX-----------------------------------

    @Override
    public Boolean caseIntegerLiteral(IntegerLiteral lit) {
        enter(lit);
        exprStr += (Long.toString(lit.getValue()));
        leave();
        return true;
    }

    @Override
    public Boolean caseFloatLiteral(FloatLiteral lit) {
        enter(lit);
        exprStr += (Double.toString(lit.getValue()));
        leave();
        return true;
    }

    @Override
    public Boolean caseBooleanLiteral(BooleanLiteral lit) {
        enter(lit);
        exprStr += (lit.isValue()?"1":"0");
        leave();
        return true;
    }

    @Override
    public Boolean caseStringLiteral(StringLiteral lit) {
        enter(lit);
        exprStr += ("\"" + lit.getValue() + "\"");
        leave();
        return true;
    }

    @Override
    public Boolean caseListExpression(ListExpression lit) {
        enter(lit);
        //Build the list expression as a long list of elements between {}, even when multi-dim
        if(dim==0) 
            exprStr += ("{");
        dim++;
        for (Iterator<Expression> i = lit.getExpressions().iterator(); i.hasNext();) {
            Expression l = i.next();
            exprStr += new CBuildExpression(l, cenv, asRef, sepIndex, asArrayPart).dimension(dim).toStr();
            //doSwitch(l);
            if (i.hasNext()) exprStr += ", ";
        }
        if(!lit.getGenerators().isEmpty()) {
            //Transformations passes should have removed all of them
            exprStr += "/* Don't know what to do with a generator yet "+ lit.toString() +"*/";
            CodegenError.err("Expression builder", "Don't know what to do with a generator yet "+ lit.toString() + ", check output!");
        }
        dim--;
        if(dim==0) {
            exprStr += ("}");
            //Need a type cast string to help the c-compiler understand the expression between {}
            if(lit.getExpressions().get(0).getType() != null)
                castStr = "(" + new CBuildTypeName(lit.getExpressions().get(0).getType(), new CPrintUtil.dummyCB(),false).toStr() +"[])";
        }
        leave();
        return true;
    }

    @Override
    public Boolean caseVariableExpression(VariableExpression var) {
        enter(var);
        VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(var, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
        //create prefix string to variable dependent on classification
        switch(varType) {
        case actorVar:
            exprStr += ("thisActor->");
            break;
        case importConstVar:
        case constVar:
        case blockConstVar:
        case exprAltConstVar:
        case actorConstVar:
            exprStr += TransUtil.getNamespaceAnnotation(var) + "__";
            break;
        case actorConstParamVar:
            exprStr += "__CalActorParam____";
            break;
        default:
        }
        String varStr = (CPrintUtil.validCName(var.getVariable().getName()));
        exprStr += varStr;
        //create string that access the actual array 
        VarLocalAccess vla = VarLocalAccess.valueOf(TransUtil.getAnnotationArg(var, IrTransformer.VARIABLE_ANNOTATION, "VarLocalAccess"));
        boolean sep = false;
        boolean asArray = asArrayPart;
        boolean pointerArray = false;
        switch(vla) {
        case listMemberScalarUserType:
        case listMemberListUserTypeSingle:
        case listMemberListMultiUserTypeSingle:
        case listMemberScalar:
        case listMemberListSingle:
        case listMemberListMultiSingle:
            asArrayPart = false; //making sure scalar is not made into arrays (since caller might not have checked)
            asArray = false; //making sure scalar is not made into arrays (since caller might not have checked)
        case listMemberListUserType:
        case listMemberListMultiUserType:
        case listMemberListMultiUserTypeList:
        case listMemberList: //Since this is a list of user types even if that member have a list of builtin type
        case listMemberListMulti:
        case listMemberListMultiList:
        case listMemberString:
            //User types arrays are stored as an array of pointers and use the .pp notation
            pointerArray = true;
            exprStr += ".pp";
            //We want the flags of the member of array type this is why flags string contains the actual array of the first array
            flagsStr = exprStr;
            break;
        case listUserTypeSingle:
        case listMultiUserTypeSingle:
            pointerArray = true;
        case listSingle:
        case listMultiSingle:
            asArrayPart = false; //making sure scalar is not made into arrays (since caller might not have checked)
            asArray = false; //making sure scalar is not made into arrays (since caller might not have checked)
            asArrayPart = var.getIndex().isEmpty()?false:asArrayPart; //When no index, can't be sub-array
            sep = asArrayPart?true:sepIndex;//we also want to have the index separate when part of array
            flagsStr = exprStr;
            if(!sepIndex && !asArrayPart && !asArray) {
                exprStr += ".p" + (pointerArray?"p":"");
            }
            break;
        case listUserType:
        case listMultiUserType:
        case listMultiUserTypeList:
            pointerArray = true;
        case list:
        case listMulti:
        case listMultiList:
        case string:
            asArrayPart = var.getIndex().isEmpty()?false:asArrayPart; //When no index, can't be sub-array
            sep = asArrayPart?true:sepIndex;//we also want to have the index separate when part of array
            flagsStr = exprStr;
            if(!sepIndex && !asArrayPart && !asArray) {
                exprStr += ".p" + (pointerArray?"p":"");
            }
            break;
        case memberListUserTypeSingle:
        case memberListMultiUserTypeSingle:
        case memberListSingle:
        case memberListMultiSingle:
            asArrayPart = false; //making sure scalar is not made into arrays (since caller might not have checked)
            flagsStr = exprStr;
            break;
        case memberListUserType:
        case memberListMultiUserTypeList:
        case memberListMultiUserType:
        case memberList:
        case memberListMultiList:
        case memberListMulti:
        case memberString:
            flagsStr = exprStr;
            break;
        default:
            asArrayPart = false; //making sure scalar is not made into arrays (since caller might not have checked)
        }
        //Print any index
        boolean hasIndex = indexPStr(var.getIndex(),((Variable)UtilIR.getDeclarationTransformed(var.getVariable())).getType(),varStr,sep);
        boolean direct = directVar(var);
        int nbrMembers = var.getMember().size();
        boolean inSep = sepIndex;
        sepIndex = false;
        //Print each user type member and their index
        for (Member m : var.getMember()) {
            nbrMembers--;
            //Only for last member in chain any sepIndex applies
            if(nbrMembers==0){
                sepIndex = asArrayPart?true:inSep; //When asking for asArray part, implies want an array output (if not scalar) hence no index or .p
                asArrayPart = m.getIndex().isEmpty()?false:asArrayPart; //When no index, no need to build sub-array already is array
            }
            //Do we have a pointer?
            //exprStr += ((direct||hasIndex)&&!pointerArray?".":"->");
            exprStr += "->";
            hasIndex = caseMember(m);
            direct = directMember(m);
            //Check if list of user type which affect if we need to print "*" to get the actual structure
            pointerArray = TransUtil.getAnnotationArg(m, IrTransformer.TYPE_ANNOTATION, "TypeStructure").equals("listUserType");
        }
        //Build a sub-array from the expression
        if(asArrayPart) {
            //This is only used for building the var to the flags field and other metadata of array
            String eStr = exprStr;
            castStr = "(" + new CBuildTypeName(var.getType(), new CPrintUtil.dummyCB(),true).toStr() +")";
            //Calling build expression again to get a pointer to a part of the array
            exprStr = "{" + new CBuildExpression(var, cenv, true, false, false).toStr() + ", ";
            //flag this as a sub-array and hence can't be freed etc.
            exprStr += eStr + ".flags|0x10, ";
            int n = indexArray.size();
            exprStr += sizeArray.size()-n + ", {";
            for(int i = n; i<sizeArray.size();i++) {
                Expression e = sizeArray.get(i);
                if(e!=null) {
                    exprStr += new CBuildExpression(e, cenv).toStr();
                } else {
                    exprStr += "/*dynamic*/";
                    exprStr += eStr +".sz[" + i + "]";
                }
                if(i<(sizeArray.size()-1)) exprStr += ", ";
            }
            exprStr += "}}";
        }
        
        //If needing a pointer prefix with & if no members but have index or separate index requested and 
        //this is the highest level of the expression (hence not deeper down into sub-expressions reach by doSwitch)
        if(asRef && ((var.getMember().isEmpty()  && !var.getIndex().isEmpty()) || sep || sepIndex) && level == 1) {
            refStr = "&";
        }
        //user type array need extra *
        /*Not anymore since all user type handling code use pointer to struct
        if(pointerArray && !inSep) {
            refStr += "*";
        }
        */
        leave();
        return true;
    }
    
    @Override
    public Boolean caseMember(Member member) {
        enter(member);
        String varStr = (CPrintUtil.validCName(member.getName()));
        exprStr += "members." + varStr;
        boolean userTypeList = false;
        if(UtilIR.isList(member.getType()) && !sepIndex) {
            flagsStr = exprStr;
            userTypeList = TransUtil.getAnnotationArg(member, IrTransformer.TYPE_ANNOTATION, "TypeStructure").equals("listUserType");
            exprStr += ".p" + (userTypeList?"p":"");
        }
        if(asRef && !member.getIndex().isEmpty()) {
            refStr = "&";
        } else {
            //When deeper members overwrite any &
            refStr = "";
        }
        boolean hasIndex = indexPStr(member.getIndex(),member.getType(), varStr, sepIndex);
        leave();
        return hasIndex;
    }

    @Override
    public Boolean caseBinaryExpression(BinaryExpression expr) {
        enter(expr);
        exprStr += "(";
        //Handle the string comparisons by utilizing helper string methods
        //Handles l=literal or v=variable expressions
        if(expr.getOperand1().getType() instanceof TypeString) {
            boolean op1IsLit = expr.getOperand1() instanceof StringLiteral;
            boolean op2IsLit = expr.getOperand2() instanceof StringLiteral;
            exprStr += "string";
            String operator = expr.getOperator();
            if (operator.equals("=")) {
                exprStr += ("eq");
            } else if (operator.equals("!=")) {
                exprStr += ("ne");
            } else if (operator.equals("<")) {
                exprStr += ("lt");
            } else if (operator.equals(">")) {
                exprStr += ("gt");
            } else if (operator.equals("<=")) {
                exprStr += ("le");
            } else if (operator.equals(">=")) {
                exprStr += ("ge");
            } else {
                exprStr += "/* NOT YET IMPLEMENTED (3) string operator " + operator + "*/";
            }           
            exprStr += (op1IsLit?"l":"v") + (op2IsLit?"l":"v") + "(";
            if(op1IsLit) {
                StringLiteral strLit = (StringLiteral) expr.getOperand1();
                exprStr += "\"" + strLit.getValue() +"\"";
            } else {
                CBuildExpression cVarExpr = new CBuildExpression(expr.getOperand1(), cenv, true, true,false);
                exprStr += cVarExpr.toStr();
            }
            exprStr += ", ";
            if(op2IsLit) {
                StringLiteral strLit = (StringLiteral) expr.getOperand2();
                exprStr += "\"" + strLit.getValue() +"\"";
            } else {
                CBuildExpression cVarExpr = new CBuildExpression(expr.getOperand2(), cenv, true, true,false);
                exprStr += cVarExpr.toStr();
            }
            exprStr += ")";
        } else {
            //Handle the arithmetic comparisons
            doSwitch(expr.getOperand1());
            exprStr += " ";
            printOperator(expr.getOperator());
            exprStr += " ";
            doSwitch(expr.getOperand2());
        }
        exprStr += ")";
        leave();
        return true;
    }

    @Override
    public Boolean caseUnaryExpression(UnaryExpression expr) {
        enter(expr);
        exprStr += "(";
        String operator = expr.getOperator();
        //FIXME instead of chasing type here should make sure that the type annotator do its job
        if((expr.getOperand().getType() instanceof TypeString ||
            (expr.getOperand() instanceof VariableExpression && 
                    ((VariableExpression)expr.getOperand()).getVariable() instanceof Variable &&
                    ((Variable)((VariableExpression)expr.getOperand()).getVariable()).getType() instanceof TypeString))
            && operator.equals("#")) {
            //Get the length of a string with #-operator
            exprStr += "(int32_t)strlen(";
            doSwitch(expr.getOperand());
            exprStr += ")";
        } else {
            if (operator.equals("not")) {
                exprStr += ("!");
            } else if (operator.equals("#")) {
                    //FIXME need to calculate array lengths instead or int bit-width instead
                    exprStr += ("sizeof");
            } else if (operator.equals("old")) {
                exprStr += ("/* Usage of unary operator old is not supported! */");
                CodegenError.err("Expression builder", "Usage of unary operator old is not supported, check output!");
            } else {
                exprStr += (operator);
            }           
            exprStr += "(";
            doSwitch(expr.getOperand());
            exprStr += ")";
        }
        exprStr += ")";
        leave();
        return true;
    }

    @Override
    public Boolean caseFunctionCall(FunctionCall expr) {
        enter(expr);
        VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(expr.getFunction(), IrTransformer.VARIABLE_ANNOTATION, "VarType"));
        VarAccess varAccess = VarAccess.valueOf(TransUtil.getAnnotationArg(expr.getFunction(), IrTransformer.VARIABLE_ANNOTATION, "VarAccess"));
        String typeUsage = TransUtil.getAnnotationArg(expr.getFunction(), IrTransformer.TYPE_ANNOTATION, "TypeUsage");
        String thisStr = "";
        String extraParamStr = "";
        String nameStr="";
        Declaration f = null;
        boolean print = false;
        //Get the function prefix and function name
        switch(varType) {
        case func: //functions declared inside same namespace-level and likely called from a function or an initValue
            thisStr = TransUtil.getNamespaceAnnotation(expr.getFunction()) + "__";
            f = ((VariableExpression) expr.getFunction()).getVariable();
            Variable funcFunc = (Variable) ((f instanceof ForwardDeclaration)?((ForwardDeclaration)f).getDeclaration():f);
            nameStr = CPrintUtil.validCName(funcFunc.getName());
            print = true;
            break;
        case importFunc: //calling an imported function from any namespace including ours from inside an actor
            thisStr = CPrintUtil.getNamespace(((VariableImport)(((VariableExpression)expr.getFunction()).getVariable())).getNamespace()) + "__";
            VariableImport funcImport = (VariableImport) ((VariableExpression) expr.getFunction()).getVariable();
            nameStr = CPrintUtil.validCName(funcImport.getName());
            print = true;
            break;
        case actorFunc: //calling a function declared in the actor scope
            extraParamStr = ("thisActor");
            if(!expr.getParameters().isEmpty())
                extraParamStr += (", ");
            f = ((VariableExpression) expr.getFunction()).getVariable();
            Variable funcActor = (Variable) ((f instanceof ForwardDeclaration)?((ForwardDeclaration)f).getDeclaration():f);
            thisStr ="__";
            nameStr = CPrintUtil.validCName(funcActor.getName());
            print = true;
            break;
        case externFunc: //calling a c-function
        case externBuiltInTypeVar:
        case externBuiltInListTypeVar:
        case externOtherTypeVar:
            Declaration decl = ((VariableExpression) expr.getFunction()).getVariable();
            VariableExternal e=null;
            List<String> namespaceStr = null;
            if(decl instanceof VariableImport) {
                VariableImport funcExtern = (VariableImport) decl;
                f = null;
                try {
                    f = ActorDirectory.findVariable(funcExtern, false);
                } catch (DirectoryException ee) {
                    System.err.println("[CBuildExpression] Could not find imported extern function " + funcExtern.getName());
                }
                e = (VariableExternal) f;
                namespaceStr = funcExtern.getNamespace();
            } else if(decl instanceof VariableExternal) {
                e = (VariableExternal) decl;
                namespaceStr = TransUtil.getNamespaceAnnotationList(e);
            }
            Namespace ns = null;
            if(e!=null) {
                try {
                    ns = ActorDirectory.findNamespace(namespaceStr);
                } catch (DirectoryException ee) {}
                Map<String,String> annotations = CPrintUtil.getExternAnnotations(CPrintUtil.collectAnnotations(e,ns));
                nameStr = (CPrintUtil.externalCName(annotations,e));
                CPrintUtil.toEnvEnv(annotations,cenv);
                print = true;
            }
            break;
        default:
            exprStr +=("/* TODO FC " +
                    varType.name() +", " +
                    varAccess.name() +", " +
                    typeUsage + " */");
        }
        if(print) {
            //If successfully found function name print it with the param expressions.
            exprStr += thisStr + nameStr + "(" + extraParamStr;
            for (int i = 0; i<expr.getParameters().size(); i++) {
                Expression p = expr.getParameters().get(i);
                exprStr += new CBuildExpression(p, cenv,false,false,true).toStr();
                if (i<expr.getParameters().size()-1) exprStr += ", ";
            }
            exprStr += ")";
        }
        leave();
        return true;
    }

    @Override
    public Boolean caseTypeConstructorCall(TypeConstructorCall expr) {
        enter(expr);
        if(UtilIR.isMultiTagTuple(expr.getType())) {
            CodegenError.err("CBuildExpression", "Not yet implemented tuple with multiple tags in type constructor ");
        }
        VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(expr, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
        VarAccess varAccess = VarAccess.valueOf(TransUtil.getAnnotationArg(expr, IrTransformer.VARIABLE_ANNOTATION, "VarAccess"));
        String typeUsage = TransUtil.getAnnotationArg(expr, IrTransformer.TYPE_ANNOTATION, "TypeUsage");
        //A type constructor call is printed as &(type){flags,tag,member1,member2,...}, the c-compiler figures out the implicit {} to reach the members part of the structure
        exprStr += "&((" + CPrintUtil.validCName(expr.getTypedef().getName()) + "_t) ";
        exprStr += ("{");
        exprStr += "0x0, "; //Flag allocated on the stack
        exprStr += "0, "; //FIXME Tuple tag
        for(Iterator<Expression> i= ((TypeConstructorCall) expr).getParameters().iterator();i.hasNext();) {
            Expression e = i.next();
            exprStr += new CBuildExpression(e,cenv,false,false,true).toStr();
            if(i.hasNext()) exprStr += ", ";
        }
        exprStr += ("})");               
        leave();
        return true;
    }

    @Override
    public Boolean caseIfExpression(IfExpression expr) {
        enter(expr);
        exprStr += ("((");
        doSwitch(expr.getCondition());
        exprStr += (") ? (");
        exprStr += new CBuildExpression(expr.getThenExpression(), cenv, asRef,false,false).toStr();
        exprStr += ("):(");
        exprStr += new CBuildExpression(expr.getElseExpression(), cenv, asRef,false,false).toStr();
        exprStr += ("))");
        leave();
        return true;
    }

    @Override
    public Boolean caseTagOf(TagOf tag) {
        /*
         * Should produce a boolean expression checking if the var has the correct tag.
         * Used in action guards.
         * When building case alternatives this should not be used.
         */
        String tagid = tag.getTag();
        CBuildExpression caseVar = new CBuildExpression(tag.getExpression(),cenv,false,true,true);
        caseVar.toStr();
        exprStr += "(" +caseVar.tagStr() +" == ";
        exprStr += new CBuildTypeName(tag.getExpression().getType(), new CPrintUtil.dummyCB(), false).asTagNameStr(tagid) + ")";
        return true;
    }
    
    @Override
    public Boolean caseTaggedTupleFieldRead(TaggedTupleFieldRead read) {
        exprStr += new CBuildExpression(read.getValue(), cenv, asRef, sepIndex, asArrayPart).toStr();
        exprStr += "->members." + read.getTag() + "." + read.getLabel();
        return true;
    }

    @Override
    public Boolean caseExpression(Expression object) {
        enter(object);
        //Generic Expression is used as a special case to print the type of the expression or NULL
        //This is used for example when using sizeof() or any c-function that takes a pointer but we have nothing to supply
        if(object.getType() instanceof TypeUndef)
            exprStr += ("NULL");
        else {
            int i=0;
            Type type = object.getType();
            while(type instanceof TypeList) {
                type = ((TypeList) type).getType();
                i++;
            }
            exprStr += new CBuildTypeName(type,null, true).toStr();
            for(;i>0;i--)
                exprStr += ("*");
        }
        leave();
        return true;
    }

}
