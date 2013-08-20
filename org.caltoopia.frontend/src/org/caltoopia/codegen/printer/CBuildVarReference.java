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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.caltoopia.ast2ir.Stream;
import org.caltoopia.ast2ir.Util;
import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.cli.DirectoryException;
import org.caltoopia.codegen.CodegenError;
import org.caltoopia.codegen.UtilIR;
import org.caltoopia.codegen.transformer.IrTransformer;
import org.caltoopia.codegen.transformer.TransUtil;
import org.caltoopia.codegen.transformer.analysis.IrTypeStructureAnnotation.TypeMember;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarAccess;
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
import org.caltoopia.ir.TypeUndef;
import org.caltoopia.ir.UnaryExpression;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.ir.VariableExternal;
import org.caltoopia.ir.VariableImport;
import org.caltoopia.ir.VariableReference;
import org.caltoopia.ir.util.IrSwitch;
import org.eclipse.emf.ecore.EObject;

public class CBuildVarReference extends IrSwitch<Boolean> {
    String refStr="";
    VariableReference reference = null;
    boolean rangechk = false; //TODO be able to control this
    public CBuildVarReference(VariableReference reference) {
        refStr="";
        this.reference = reference;
    }
    
    public String toStr() {
        Boolean res = doSwitch(reference);
        return refStr;
    }

    private void enter(EObject obj) {}
    private void leave() {}
    
    //---------------------util------------------------------------
    //Prints indexStr, but also returns boolean true when the resulting type is not a list
    protected Boolean indexStr(List<Expression> index, Type varType) {
        if(index!=null && !index.isEmpty() && varType instanceof TypeList) {
            Type list = varType;
            for (Iterator<Expression> i = index.iterator(); i.hasNext();) {
                Expression e = i.next();
                refStr += ("[");
                if(rangechk) {
                    refStr += ("RANGECHK(");
                }
                refStr += new CBuildExpression(e).toStr();
                if(rangechk) {
                    refStr += (",");
                    refStr += new CBuildExpression(((TypeList)list).getSize()).toStr();
                    refStr += (")");
                }
                list = ((TypeList)list).getType();
                refStr += ("]");
            }
            return !(list instanceof TypeList);
        }
        return false;
    }
    
    
    private boolean directMember(Member member) {
        TypeMember typeMember = TypeMember.valueOf(TransUtil.getAnnotationArg(member, IrTransformer.TYPE_ANNOTATION, "TypeStructure"));
        boolean direct = true;
        switch(typeMember) {
        case unknown:
            CodegenError.err("Var ref builder", "unknown placement of member " + member.getName());
        case builtin:
        case byListSome: //Used when list of decided size and inlined  but have deeper members that are not
        case byListFull: //Used when list of decided size and inlined and all deeper members also (including lists of builtins)
        case inlineSome: //Used when user type that is inlined but have deeper members that are not
        case inlineFull: //Used when user type is inlined and all deeper members also
            direct = true;
            break;
        case byRef: //Used when either type (or list of non-decided size?)
            direct = false;
            break;
        default:
        }
        return direct;
    }
    
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
        case fifo:
        case code:
            direct = true;
            break;
        case ref: //used when passing as parameter as ref
        case fifoList:
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

        switch(varType) {
        case actorVar:
            refStr += ("thisActor->");
            break;
        case importConstVar:
        case constVar:
            refStr += TransUtil.getNamespaceAnnotation(var) + "__";
            break;
        default:
        }
        refStr += (CPrintUtil.validCName(var.getDeclaration().getName()));

        boolean hasIndex = indexStr(var.getIndex(),var.getDeclaration().getType());
        boolean direct = directVar(var);
        for (Member m : var.getMember()) {
            refStr += ((direct||hasIndex)?".":"->");
            hasIndex = caseMember(m);
            direct = directMember(m);
        }

        leave();
        return true;
    }
    
    @Override
    public Boolean caseMember(Member member) {
        enter(member);
        refStr += (CPrintUtil.validCName(member.getName()));
        boolean hasIndex = indexStr(member.getIndex(),member.getType());
        leave();
        return hasIndex;
    }
}
