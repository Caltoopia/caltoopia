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

import java.util.Arrays;
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

/*
 * This class generates a string for a block of 
 * statements and declarations.
 * 
 * Quality: 4, works
 */
public class CBuildBody extends IrSwitch<Boolean> {
    String bodyStr="";
    Block body;
    boolean header = false;
    CEnvironment cenv = null;
    private IndentStr ind = null;

    /*
     * Constructor for building a long string containing the 
     * c-code of a block. The block is printed between {} and is assumed
     * to be embedded into another c-function.
     * 
     * body: block to be printed
     * cenv: input/output variable collecting information that is 
     *       needed in makefiles etc, same object used for all CBuilders
     * ind: indentation object, passed in so that sub-parts maintains overall indentation level
     */
    public CBuildBody(Block body, CEnvironment cenv, IndentStr ind) {
        bodyStr="";
        this.body = body;
        this.cenv = cenv;
        if(ind == null) {
            this.ind = new IndentStr();
        } else {
            this.ind = ind;
        }
    }
    
    /*
     * Do the actual generation of the block string, use as:
     * new CBuildBody(...).toStr()
     */
    public String toStr() {
        Boolean res = doSwitch(body);
        if(!res) {
            CodegenError.err("Body builder", bodyStr);
        }
        return bodyStr;
    }
    
    private void enter(EObject obj) {}
    private void leave() {}

    @Override
    public Boolean caseBlock(Block block) {
        enter(block);
        bodyStr += ind.ind() + ("{") + ind.nl();
        ind.inc();
        //TODO remove these when the code potentially utilizing them in CBuildAssign is removed
        bodyStr += ind.ind() + "void * __tempVoidPointer;" +ind.nl();
        bodyStr += ind.ind() + "__array4void __tempArray;" +ind.nl();
        //Print all declarations
        for (Declaration d : block.getDeclarations()) {
            VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(d, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
            switch(varType) {
            case constVar:
            case actorConstVar:
                bodyStr += ind.ind() + (new CBuildConstDeclaration((Variable) d, cenv,false).toStr()) + ";" + ind.nl();
                break;
            case blockConstVar:
            case exprAltConstVar:
                bodyStr += ind.ind() + (new CBuildConstDeclaration((Variable) d,cenv, false).toStr()) + ";" + ind.nl();
                break;
            case actorVar:
            case blockVar:
            case procVar:
            case funcVar:
            case actionVar:
            case actionInitInDepVar:
                if(((Variable)d).getInitValue() != null) {
                    //If the variable needs initialization it uses the CBuildConstDeclaration
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

        //Print all statements except any return statement
        Statement ret = null;
        for (Statement s : block.getStatements()) {
            if(!(s instanceof ReturnValue)) {
                bodyStr += new CBuildStatement(s, cenv, ind,true,block).toStr();
            } else {
                //Assuming only one return at the end
                ret = s;
            }
        }
        /*
         * Allocation of arrays or user types are done at initialization of the variable (when static sizes)
         * or when the variable is assigned. Here we need to make sure to free the allocated memory before
         * leaving the block. Both arrays and user types contains metadata that keeps track of if the data
         * is allocated on heap or stack. Hence we call our free function on all of them which checks such
         * metadata before attempting to free the memory.
         * 
         * CBuildAction have similar code for freeing at end of Action. Make sure they evolve in sync.
         */
        for(Declaration d:block.getDeclarations()) {
            /*
             * Make sure to skip freeing any return variable it is the responsibility of the function caller.
             * Likewise don't free any of the function/procedure parameters since they also belong to the caller.
             */
            boolean retValue = TransUtil.getAnnotationArg(d, IrTransformer.VARIABLE_ANNOTATION, "VarAssign").equals(IrVariableAnnotation.VarAssign.movedRetAssigned.name());
            VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(d, IrTransformer.VARIABLE_ANNOTATION, "VarType"));            
            if(!retValue && !Arrays.asList(VarType.funcInParamVar, VarType.procInParamVar, VarType.procOutParamVar).contains(varType)) {
                if((d instanceof Variable) && UtilIR.isList(((Variable)d).getType())) {
                    VariableReference varRef = UtilIR.createVarRef((Variable) d);
                    TransUtil.copySelectedAnnotations(varRef, d, new TransUtil.AnnotationsFilter(IrTransformer.VARIABLE_ANNOTATION, new String[]{"VarPlacement","VarType"}));
                    CBuildVarReference cVarRefF = new CBuildVarReference(varRef , cenv, false, true);
                    String varStrF = cVarRefF.toStr();
                    bodyStr += ind.ind() + "free" + new CBuildTypeName(((Variable)d).getType(), new CPrintUtil.dummyCB(), false).asNameStr() + "(&" + varStrF + ", TRUE);" + ind.nl();
                } else if((d instanceof Variable) && UtilIR.isSingleTagTuple(((Variable)d).getType())) {
                    VariableReference varRef = UtilIR.createVarRef((Variable) d);
                    TransUtil.copySelectedAnnotations(varRef, d, new TransUtil.AnnotationsFilter(IrTransformer.VARIABLE_ANNOTATION, new String[]{"VarPlacement","VarType"}));
                    CBuildVarReference cVarRefF = new CBuildVarReference(varRef , cenv, false, true);
                    String varStrF = cVarRefF.toStr();
                    bodyStr += ind.ind() + "freeStruct" + new CBuildTypeName(((Variable)d).getType(), new CPrintUtil.dummyCB(), false).asNameStr() + "(" + varStrF + ", TRUE);" + ind.nl();
                } else if((d instanceof Variable) && UtilIR.isTuple(((Variable)d).getType())) {
                    CodegenError.err("Body builder", "Not yet implemented tuple with multiple tags");
                }
            }
        }
        //And final print any return statement
        if(ret!=null) {
            bodyStr += new CBuildStatement(ret, cenv, ind,true,block).toStr();
        }
        if(TransUtil.getAnnotationArg(block, "caseAlternative", "finished").equals("true")) {
            bodyStr += ind.ind() + ("break;") + ind.nl();
        }
        ind.dec();
        bodyStr += ind.ind() + ("}") + ind.nl();
        leave();
        return true;
    }

}
