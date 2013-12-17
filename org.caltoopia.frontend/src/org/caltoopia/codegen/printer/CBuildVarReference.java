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
 * This class generates a string for a variable reference.
 * It has much resemblence with variable expressions in
 * CBuildExpression and could benefit from more common code.
 * 
 * Quality: 3, works but it is complicated and could have errors
 */
public class CBuildVarReference extends IrSwitch<Boolean> {
    String refStr="";
    String flagsStr = "";
    String ref2Str="";
    String lastVarStr = "";
    List<Expression> indexArray;
    List<Expression> sizeArray;
    VariableReference reference = null;
    boolean rangechk = false; //TODO be able to control this
    int level = 0;
    boolean asRef = false;
    boolean sepIndex = false;
    boolean lastMember = false;
    CEnvironment cenv = null;

    /*
     * Constructor for building a long string containing the 
     * c-code of an variable reference. The variable reference
     * is printed as a single line of c-code to be embedded 
     * into a statement.
     * 
     * reference: variable reference to be printed
     * cenv: input/output variable collecting information that is 
     *       needed in makefiles etc, same object used for all CBuilders
     * asRef: print the variable as a c-code pointer, i.e. if not already a pointer prefix with "&"
     * sepIndex: the expStr will not have the indices of the variable (or last user type member)
     *           instead these can be access by indexStr().
     */
    public CBuildVarReference(VariableReference reference, CEnvironment cenv, boolean asRef, boolean sepIndex) {
        refStr="";
        ref2Str="";
        this.flagsStr = "";
        indexArray = new ArrayList<Expression>();
        sizeArray = new ArrayList<Expression>();
        this.reference = reference;
        this.cenv = cenv;
        this.asRef = asRef;
        this.sepIndex = sepIndex;
    }
    
    //Use this constructor as much as possible
    public CBuildVarReference(VariableReference reference, CEnvironment cenv) {
        this(reference,cenv,false,false);
    }
    
    /*
     * Do the actual generation of the variable ref string, use as:
     * new CBuildVarReference(...).toStr()
     */
    public String toStr() {
        Boolean res = doSwitch(reference);
        return ref2Str + refStr;
    }

    /*
     * Do the generation of the flags metadata string,
     * that can be used to change the flags (typically temp array).
     * Must have called toStr() first.
     */
    public String flagsStr() {
        return flagsStr + ".flags";
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
     */
    public String sizeStr() {
        String indexStr = "(__arrayArg) {";
        if(reference.getType() instanceof TypeString) {
            indexStr += "1,{";
            indexStr += lastVarStr +".sz[0]";
        } else {
            indexStr += sizeArray.size() + ",{";
            for(int i = 0; i<sizeArray.size();i++) {
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
    
    public int indexLen() {
        return indexArray.size();
    }

    public int sizeLen() {
        return sizeArray.size();
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
        lastVarStr = varStr;
        String indexStr ="";
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
            indexStr += (")");
            //Any more dimensions size to multiply with?
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
            if(sep) {
                indexArray.addAll(indExpr);
                sizeArray.addAll(szExpr);
            } else {
                refStr += indexStr;
            }
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
        TypeMember typeMember = TypeMember.valueOf(TransUtil.getAnnotationArg(member, IrTransformer.TYPE_ANNOTATION, "TypeStructure"));
        boolean direct = true;
        return direct;
    }
    
    //FIXME should be removed, have the return value based on other annotations instead
    private boolean directVar(VariableReference var) {
        VarPlacement varPlacement = VarPlacement.valueOf(TransUtil.getAnnotationArg(var, IrTransformer.VARIABLE_ANNOTATION, "VarPlacement"));
        boolean direct = true;
        switch(varPlacement) {
        case unknown:
            CodegenError.err("var ref builder", "unknown placement of var expr " + var.getDeclaration().getName());
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

    //---------------------caseX-----------------------------------

    @Override
    public Boolean caseVariableReference(VariableReference var) {
        enter(var);
        VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(var, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
        //create prefix string to variable dependent on classification
        switch(varType) {
        case actorVar:
            refStr += ("thisActor->");
            break;
        case importConstVar:
        case constVar:
        case blockConstVar:
        case exprAltConstVar:
        case actorConstVar:
            refStr += TransUtil.getNamespaceAnnotation(var) + "__";
            break;
        case actorConstParamVar:
            refStr += "_CalActorParam__";
            break;
        default:
        }
        String varStr = (CPrintUtil.validCName(var.getDeclaration().getName()));
        refStr += varStr;
        //create string that access the actual array 
        VarLocalAccess vla = VarLocalAccess.valueOf(TransUtil.getAnnotationArg(var, IrTransformer.VARIABLE_ANNOTATION, "VarLocalAccess"));
        boolean sep = false;
        boolean pointerArray = false; //list of pointers (for user types)
        switch(vla) {
        case listMemberScalar:
        case listMemberList:
        case listMemberListMulti:
        case listMemberScalarUserType:
        case listMemberListUserType:
        case listMemberListMultiUserType:
        case listMemberListSingle:
        case listMemberListMultiSingle:
        case listMemberListUserTypeSingle:
        case listMemberListMultiUserTypeSingle:
        case listMemberListMultiList:
        case listMemberListMultiUserTypeList:
        case listMemberString:
            pointerArray = true;
            refStr += ".pp";
            //We want the flags of the member of array type this is why flags string contains the actual array of the first array
            flagsStr = refStr;
            break;
        case listUserType:
        case listMultiUserType:
        case listUserTypeSingle:
        case listMultiUserTypeSingle:
        case listMultiUserTypeList:
            pointerArray = true;
        case list:
        case listMulti:
        case listSingle:
        case listMultiSingle:
        case listMultiList:
        case string:
            sep = sepIndex;
            flagsStr = refStr;
            if(!sepIndex) {
                refStr += ".p" + (pointerArray?"p":"");
            }
        default:
        }

        //Print any index
        boolean hasIndex = indexPStr(var.getIndex(),var.getDeclaration().getType(), varStr, sep);
        boolean direct = directVar(var);
        int nbrMembers = var.getMember().size();
        boolean inSep = sepIndex;
        sepIndex = false;
        //Print each user type member and their index
        for (Member m : var.getMember()) {
            nbrMembers--;
            //Only for last member in chain any sepIndex applies
            if(nbrMembers==0){
                sepIndex = inSep;
                lastMember = true;
            }
            //Do we have a pointer?
            //refStr += ((direct||hasIndex)&&!pointerArray?".":"->");
            refStr += "->";
            hasIndex = caseMember(m);
            direct = directMember(m);
            //Check if list of user type which affect if we need to print "*" to get the actual structure
            pointerArray = TransUtil.getAnnotationArg(m, IrTransformer.TYPE_ANNOTATION, "TypeStructure").equals("listUserType");
        }

        //If needing a pointer prefix with & if no members but have index or separate index requested and 
        //this is the highest level of the expression (hence not deeper down into sub-expressions reach by doSwitch)
        if(asRef && ((var.getMember().isEmpty()  && !var.getIndex().isEmpty()) || sep || sepIndex) && level == 1) {
            ref2Str = "&";
        }
        //user type array need extra *
        /*Not anymore since all user type handling code use pointer to struct
        if(pointerArray && !inSep) {
            ref2Str += "*";
        }
        */
        leave();
        return true;
    }
    
    @Override
    public Boolean caseMember(Member member) {
        enter(member);
        String varStr = (CPrintUtil.validCName(member.getName()));
        refStr += "members." + varStr;
        boolean userTypeList = false;
        if(UtilIR.isList(member.getType()) && !sepIndex) {
            if(lastMember) {
                //When last member in chain copy the string to flags
                flagsStr = refStr;
            }
            userTypeList = TransUtil.getAnnotationArg(member, IrTransformer.TYPE_ANNOTATION, "TypeStructure").equals("listUserType");
            refStr += ".p" + (userTypeList?"p":"");
        }
        if(asRef && !member.getIndex().isEmpty()) {
            ref2Str = "&";
        } else {
            //When deeper members overwrite any &
            ref2Str = "";
        }

        boolean hasIndex = indexPStr(member.getIndex(),member.getType(), varStr, sepIndex);
        leave();
        return hasIndex;
    }
}
