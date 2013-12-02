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

import org.caltoopia.ast2ir.Util;
import org.caltoopia.codegen.CEnvironment;
import org.caltoopia.codegen.CodegenError;
import org.caltoopia.codegen.UtilIR;
import org.caltoopia.codegen.printer.CBuildVarDeclaration.varCB;
import org.caltoopia.codegen.transformer.IrTransformer;
import org.caltoopia.codegen.transformer.TransUtil;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarType;
import org.caltoopia.ir.Actor;
import org.caltoopia.ir.Block;
import org.caltoopia.ir.LambdaExpression;
import org.caltoopia.ir.ProcExpression;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.TypeLambda;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.util.IrSwitch;
import org.eclipse.emf.ecore.EObject;

/*
 * This class generates a string containing a function declaration.
 * 
 * Quality: 5, should work
 */
public class CBuildFuncDeclaration extends IrSwitch<Boolean> {
    String funcStr="";
    Variable variable;
    boolean header = false;
    CEnvironment cenv = null;

    /*
     * Constructor for building a long string containing the 
     * c-code of a function declaration. It prints the 
     * functions's type, name, parameters and body.
     * NB! CAL makes a difference between functions and procedures
     * but we have likely already transformed any functions to use
     * a procedure body.
     * 
     * variable: function to be printed
     * cenv: input/output variable collecting information that is 
     *       needed in makefiles etc, same object used for all CBuilders
     * header: when functions are printed in a header it is only defined
     *         i.e. no body. It should then also be printed in a c-file
     *         with the body.
     */
    public CBuildFuncDeclaration(Variable variable, CEnvironment cenv, boolean header) {
        funcStr="";
        this.header = header;
        this.variable = variable;
        this.cenv = cenv;
    }
    
    /*
     * Do the actual generation of the function declaration string, use as:
     * new CBuildFuncDeclaration(...).toStr()
     */
    public String toStr() {
        Boolean res = doSwitch(variable);
        if(!res) {
            CodegenError.err("Func declaration builder", funcStr);
        }
        return funcStr;
    }
    
    private void enter(EObject obj) {}
    private void leave() {}
    
    

    public Boolean caseVariable(Variable variable) {
        VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(variable, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
        //Actor functions are printed in the actor's file and hence can be made static
        if(varType == VarType.actorFunc) {
            funcStr = "static ";
        } else {
            funcStr = "";
        }
        LambdaExpression lambda = (LambdaExpression) variable.getInitValue();
        Type type = ((TypeLambda)lambda.getType()).getOutputType();
        funcStr += new CBuildTypeName(type, new CPrintUtil.dummyCB(),true).toStr() + " ";

        String thisStr = TransUtil.getNamespaceAnnotation(variable);
        
        funcStr += thisStr + "__";
        funcStr += CPrintUtil.validCName(variable.getName()) + "(";

        if(varType == VarType.actorFunc) {
            //All actor functions have as first parameter the actor instance pointer to allow access to actor state variables
            if(thisStr.equals("")) {
                Actor actor = (Actor)lambda.getOuter();
                thisStr = Util.marshallQualifiedName(actor.getType().getNamespace()) + "__" + TransUtil.getAnnotationArg(actor, "Instance", "name");
            }
            funcStr += ("ActorInstance_" + thisStr + "* thisActor");
            if(!lambda.getParameters().isEmpty())
                funcStr += (", ");
        }
        //All the parameters
        for(Iterator<Variable> i = lambda.getParameters().iterator();i.hasNext();) {
            Variable p = i.next();
            funcStr += new CBuildVarDeclaration(p,cenv,false).toStr();
            if (i.hasNext()) funcStr += ", ";
        }
        funcStr += (")");
        if(header) {
            //Only declaration
            funcStr += (";\n");
        } else {
            if(lambda.getBody() instanceof ProcExpression) {
                //Expression have been expanded to nameless proc to get a block
                funcStr += new CBuildBody(((ProcExpression)lambda.getBody()).getBody(), cenv, null).toStr();
            } else {
                /*
                 * Both the MoveInitValueExpr and ExprToTempVar always transforms a 
                 * lambda's expression into a proc expression. But if those transform
                 * passes have not executed we might end up here.
                 * 
                 * Create a block out of the expression to not need to repeat
                 * memory handling etc.
                 */
                Block b = UtilIR.createBlock(lambda);
                UtilIR.createReturn(b, lambda.getBody());
                b.getDeclarations().addAll(lambda.getDeclarations());
                funcStr += new CBuildBody(b, cenv, null).toStr();
            }
        }
        return true;
    }

}
