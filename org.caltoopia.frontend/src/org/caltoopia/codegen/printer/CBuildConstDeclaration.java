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
import org.caltoopia.ir.IntegerLiteral;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeBool;
import org.caltoopia.ir.TypeDeclaration;
import org.caltoopia.ir.TypeExternal;
import org.caltoopia.ir.TypeFloat;
import org.caltoopia.ir.TypeInt;
import org.caltoopia.ir.TypeList;
import org.caltoopia.ir.TypeString;
import org.caltoopia.ir.TypeUint;
import org.caltoopia.ir.TypeUndef;
import org.caltoopia.ir.TypeUser;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.util.IrSwitch;
import org.eclipse.emf.ecore.EObject;

/*
 * This class generates a string containing a constant declaration
 * of a normal variable or a declaration of a variable with initialization.
 * 
 * Quality: 5, should work
 */
public class CBuildConstDeclaration extends CBuildVarDeclaration {

    String initStr="";
    boolean header = false;

    /*
     * Constructor for building a long string containing the 
     * c-code of a variable declaration with an (constant) 
     * initialization. It prints the variable's type, name
     * and assigned expression.
     * 
     * variable: variable to be printed
     * cenv: input/output variable collecting information that is 
     *       needed in makefiles etc, same object used for all CBuilders
     * header: when constants are printed in a header it is extern declared
     *         it is then also printed in a c-file with the assignment.
     */
    public CBuildConstDeclaration(Variable variable, CEnvironment cenv, boolean header) {
        super(variable,cenv,false);
        this.header = header;
    }
    
    /*
     * Do the actual generation of the action string, use as:
     * new CBuildConstDeclaration(...).toStr()
     */
    @Override
    public String toStr() {
        Boolean res = doSwitch(variable);
        if(!res) {
            CodegenError.err("Const declaration builder", vtypeStr + " " + varStr);
        }
        return vtypeStr + " " + varStr + (header? "" : " = " + initStr);
    }
    
    private void enter(EObject obj) {}
    private void leave() {}

    //Do the actual string creation
    private void buildConstDeclaration(Variable variable, boolean constant) {
        //Type string
        vtypeStr = new CBuildTypeName(variable.getType(),new varCB(), true).toStr();
        //Variable name string including prefix of namespace
        String nsStr = TransUtil.getNamespaceAnnotation(variable) + (constant?"__":"");
        varStr = nsStr + variable.getName()+varStr;
        if(header) {
            //in header then only extern declaration
            vtypeStr = "extern " + vtypeStr;
        } else {
            //CBuildExpression affects our inherited dimStr and maxDim
            initStr = new CBuildExpression(variable.getInitValue(),cenv).toStr();
            if(!dimStr.equals("")) {
                /*
                 * Build the array and metadata struct
                 * {.p/.pp=malloc(...), flags = heap allocated and maybe temp var, dimensions, {dim1 size, dim2 size, ...}}
                 */
                initStr = "{" + initStr + ", "; 
                initStr += (TransUtil.getAnnotationArg(variable, "Variable", "VarLocalAccess").equals(VarLocalAccess.temp.name()))?"0xb":"0x3";
                initStr += ", ";
                initStr += maxDim + ", {" + dimStr + "}}";
            }
        }
    }

    @Override
    public Boolean caseVariable(Variable variable) {
        enter(variable);
        VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(variable, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
        switch(varType) {
        case actorConstVar:
            buildConstDeclaration(variable, true);
            vtypeStr = "static " + vtypeStr;
            break;
        case constVar:
        case blockConstVar:
            buildConstDeclaration(variable, true);
            break;
        case actorConstParamVar:
            buildConstDeclaration(variable, true);
            varStr = "__CalActorParam__" + varStr;
            vtypeStr = "static " + vtypeStr;
            break;
        //Actually declaration + initialization of non-const
        case funcVar:
        case procVar:
        case blockVar:
        case actionVar:
        case outPortVar:
        case outPortInitInDepVar:
            buildConstDeclaration(variable, false);
            break;
        default:
            varStr += ("/*TODO BCD "+variable.getName() + " of varType " + varType.name() + " */");
        }
        leave();
        return true;
    }
}
