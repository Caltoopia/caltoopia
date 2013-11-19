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
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.LambdaExpression;
import org.caltoopia.ir.ProcExpression;
import org.caltoopia.ir.Statement;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.TypeLambda;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.util.IrSwitch;
import org.eclipse.emf.ecore.EObject;

public class CBuildProcDeclaration extends IrSwitch<Boolean> {
    String procStr="";
    Variable variable;
    boolean header = false;
    CEnvironment cenv = null;
    public CBuildProcDeclaration(Variable variable, CEnvironment cenv, boolean header) {
        procStr="";
        this.header = header;
        this.variable = variable;
        this.cenv = cenv;
    }
    
    public String toStr() {
        Boolean res = doSwitch(variable);
        if(!res) {
            CodegenError.err("Proc declaration builder", procStr);
        }
        return procStr;
    }
    
    private void enter(EObject obj) {}
    private void leave() {}
    
    public Boolean caseVariable(Variable variable) {
        ProcExpression proc =  (ProcExpression) variable.getInitValue();
        procStr = "static void ";
        String thisStr = TransUtil.getNamespaceAnnotation(variable);
        if(thisStr.equals("")) {
            Actor actor = (Actor)proc.getOuter();
            thisStr = Util.marshallQualifiedName(actor.getType().getNamespace()) + "__" + TransUtil.getAnnotationArg(actor, "Instance", "name");
        }
        procStr += "__" + CPrintUtil.validCName(variable.getName()) + "(";

        VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(variable, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
        if(varType == VarType.proc) {
            procStr += ("ActorInstance_" + thisStr + "* thisActor");
            if(!proc.getOutputs().isEmpty() || !proc.getParameters().isEmpty())
                procStr += (", ");
        }
        for(Iterator<Variable> i = proc.getParameters().iterator();i.hasNext();) {
            Variable p = i.next();
            //FIXME must fix so that it can handle params
            procStr += new CBuildVarDeclaration(p,cenv, false).toStr();
            if (i.hasNext()) procStr += ", ";
        }
        if(!proc.getOutputs().isEmpty()) {
            procStr += (", ");
            for(Iterator<Variable> i = proc.getOutputs().iterator();i.hasNext();) {
                Variable p = i.next();
                //FIXME must fix so that it can handle params
                procStr += new CBuildVarDeclaration(p,cenv, false).toStr();
                if (i.hasNext()) procStr += ", ";
            }           
        }
        procStr += (")");
        if(header) {
            procStr += (";\n");
        } else {
            procStr += "\n";
            procStr += new CBuildBody(proc.getBody(),cenv, null).toStr();
        }
        return true;
    }

}
