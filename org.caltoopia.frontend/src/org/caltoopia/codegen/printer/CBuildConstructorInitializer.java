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

import org.caltoopia.ast2ir.Stream;
import org.caltoopia.ast2ir.Util;
import org.caltoopia.ast2ir.Stream.Indent;
import org.caltoopia.codegen.CEnvironment;
import org.caltoopia.codegen.CodegenError;
import org.caltoopia.codegen.UtilIR;
import org.caltoopia.codegen.printer.CBuildVarDeclaration.varCB;
import org.caltoopia.codegen.transformer.IrTransformer;
import org.caltoopia.codegen.transformer.TransUtil;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarAccess;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarType;
import org.caltoopia.ir.Action;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Block;
import org.caltoopia.ir.LambdaExpression;
import org.caltoopia.ir.ProcExpression;
import org.caltoopia.ir.ReturnValue;
import org.caltoopia.ir.Statement;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.TypeLambda;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableReference;
import org.caltoopia.ir.util.IrSwitch;
import org.eclipse.emf.ecore.EObject;

public class CBuildConstructorInitializer extends IrSwitch<Boolean> {
    String bodyStr="";
    Action initializer;
    boolean header = false;
    CEnvironment cenv = null;
    private IndentStr ind = null;

    public CBuildConstructorInitializer(Action initializer, CEnvironment cenv, IndentStr ind) {
        bodyStr="";
        this.initializer = initializer;
        this.cenv = cenv;
        if(ind == null) {
            this.ind = new IndentStr();
        } else {
            this.ind = ind;
        }
    }
    
    public String toStr() {
        Boolean res = doSwitch(initializer);
        if(!res) {
            CodegenError.err("Body builder", bodyStr);
        }
        return bodyStr;
    }
    
    private void enter(EObject obj) {}
    private void leave() {}

    @Override
    public Boolean caseAction(Action block) {
        enter(block);
        bodyStr += ind.ind() + ("{") + ind.nl();
        ind.inc();
        bodyStr += ind.ind() + "void * __tempVoidPointer;" +ind.nl();
        bodyStr += ind.ind() + "__array4void __tempArray;" +ind.nl();
        for (Declaration d : block.getDeclarations()) {
            VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(d, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
            switch(varType) {
            case constVar:
                bodyStr += ind.ind() + (new CBuildConstDeclaration((Variable) d, cenv,false).toStr()) + ";" + ind.nl();
                break;
            case blockConstVar:
                bodyStr += ind.ind() + (new CBuildConstDeclaration((Variable) d,cenv, false).toStr()) + ";" + ind.nl();
                break;
            case actorVar:
            case blockVar:
            case procVar:
            case funcVar:
            case actionVar:
            case actionInitInDepVar:
                if(((Variable)d).getInitValue() != null) {
                    //TODO should have separate class for var declaration with initialization
                    bodyStr += ind.ind() + (new CBuildConstDeclaration((Variable) d, cenv, false).toStr()) + ";" + ind.nl();
                } else {
                    bodyStr += ind.ind() + (new CBuildVarDeclaration((Variable) d,cenv, false).toStr()) + ";" + ind.nl();
                }
                break;
            default:
                VarAccess varAccess = VarAccess.valueOf(TransUtil.getAnnotationArg(d, IrTransformer.VARIABLE_ANNOTATION, "VarAccess"));
                String typeUsage = TransUtil.getAnnotationArg(d, IrTransformer.TYPE_ANNOTATION, "TypeUsage");
                String varStr =(varType.name() +", " +
                        varAccess.name() +", " +
                        typeUsage);
                bodyStr += ind.ind() + ("/*TODO BD "+d.getName() + ", " + varStr + " */") +ind.nl();
            } 
        }

        for (Statement s : block.getStatements()) {
            bodyStr += new CBuildStatement(s, cenv, ind,true,block).toStr();
        }
        ind.dec();
        bodyStr += ind.ind() + ("}") + ind.nl();
        leave();
        return true;
    }

}
