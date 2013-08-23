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

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.caltoopia.codegen.CodegenError;
import org.caltoopia.codegen.UtilIR;
import org.caltoopia.codegen.printer.CPrintUtil.listStarCB;
import org.caltoopia.codegen.transformer.IrTransformer;
import org.caltoopia.codegen.transformer.TransUtil;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarAccess;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarType;
import org.caltoopia.ir.IntegerLiteral;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeBool;
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
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.util.IrSwitch;
import org.eclipse.emf.ecore.EObject;

public class CBuildVarDeclaration extends IrSwitch<Boolean> {
    String vtypeStr="";
    String varStr="";
    Variable variable;
    boolean onlyVar = false;

    public CBuildVarDeclaration(Variable variable, boolean onlyVar) {
        vtypeStr="";
        varStr="";
        this.variable = variable;
        this.onlyVar = onlyVar;
    }
    
    public String toStr() {
        Boolean res = doSwitch(variable);
        if(!res) {
            CodegenError.err("Var declaration builder", vtypeStr + " " + varStr);
        }
        return (onlyVar?"":vtypeStr + " ") + varStr;
    }
    
    //-----------Util--------------------------------------------------------
    
    private void enter(EObject obj) {}
    private void leave() {}
    
    //---------------Type callbacks -----------------------------------------
    protected class varCB implements ITypeCallbacks {
        public String preTypeFn(Type type) {
            // TODO Auto-generated method stub
            return "";
        }

        public String postTypeFn(Type type) {
            /*VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(type, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
            VarAccess varAccess = VarAccess.valueOf(TransUtil.getAnnotationArg(type, IrTransformer.VARIABLE_ANNOTATION, "VarAccess"));
            String typeUsage = TransUtil.getAnnotationArg(type, IrTransformer.TYPE_ANNOTATION, "TypeUsage");*/
//            varStr +=("/* " +
//                    varType.name() +", " +
//                    varAccess.name() +", " +
//                    typeUsage +" */");
            return "";
        }

        public String listTypeFn(TypeList type) {
            if(!onlyVar) {
                varStr += ("[");
                varStr += (new CBuildExpression(type.getSize()).toStr());
                varStr += ("]");
            }
            return "";
        }

        public String userTypeFn(TypeUser type) {
            // TODO Auto-generated method stub
            return "";
        }
    }

    //------------------------VARIABLES, FUNC, PROC, ETC DECLARATIONS---------------------------------------
    //------------------util------
    private void buildVarDeclaration(Variable variable) {
        vtypeStr = new CBuildTypeName(variable.getType(),new varCB()).toStr();
        varStr = variable.getName()+varStr;
    }
    
    private void buildParamDeclaration(Variable variable) {
        vtypeStr = new CBuildTypeName(variable.getType(),new CPrintUtil.listStarCB()).toStr();
        varStr = variable.getName()+varStr;
    }

    @Override
    public Boolean caseVariable(Variable variable) {
        enter(variable);
        VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(variable, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
        switch(varType) {
        case memberDeclType:
        case actorVar:
        case procVar:
        case generatorVar:
        case blockVar:
            buildVarDeclaration(variable);
            break;
        case funcInParamVar:
        case procInParamVar:
            buildParamDeclaration(variable);
            break;
        default:
            varStr += ("/*TODO BVD "+variable.getName() + " of varType " + varType.name() + " */");
        }
        leave();
        return true;
    }

}
