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

package org.caltoopia.codegen.transformer.transforms;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.caltoopia.ast2ir.Util;
import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.cli.CompilationSession;
import org.caltoopia.cli.DirectoryException;
import org.caltoopia.codegen.UtilIR;
import org.caltoopia.codegen.transformer.FixMovedExpr;
import org.caltoopia.codegen.transformer.IrTransformer;
import org.caltoopia.codegen.transformer.IrTransformer.IrPassTypes;
import org.caltoopia.codegen.transformer.TransUtil;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarAssign;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarType;
import org.caltoopia.codegen.transformer.analysis.IrVariablePlacementAnnotation.VarPlacement;
import org.caltoopia.ir.AbstractActor;
import org.caltoopia.ir.Action;
import org.caltoopia.ir.Actor;
import org.caltoopia.ir.ActorInstance;
import org.caltoopia.ir.Annotation;
import org.caltoopia.ir.AnnotationArgument;
import org.caltoopia.ir.Assign;
import org.caltoopia.ir.Block;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.ExternalActor;
import org.caltoopia.ir.IrFactory;
import org.caltoopia.ir.LambdaExpression;
import org.caltoopia.ir.ListExpression;
import org.caltoopia.ir.Network;
import org.caltoopia.ir.Node;
import org.caltoopia.ir.PortRead;
import org.caltoopia.ir.PortWrite;
import org.caltoopia.ir.ProcExpression;
import org.caltoopia.ir.ReturnValue;
import org.caltoopia.ir.Scope;
import org.caltoopia.ir.Statement;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.TypeDeclaration;
import org.caltoopia.ir.TypeDeclarationImport;
import org.caltoopia.ir.TypeLambda;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.ir.VariableReference;
import org.caltoopia.ir.util.IrFindSwitch;
import org.caltoopia.ir.util.IrReplaceSwitch;
import org.caltoopia.types.TypeSystem;
import org.eclipse.emf.ecore.EObject;

public class PortTransformations extends IrReplaceSwitch {
    
    private PrintStream serr = null; 
    private CompilationSession session;

    public PortTransformations(Node node, CompilationSession session, boolean errPrint) {
        if(!errPrint) {
            serr = new PrintStream(new OutputStream(){
                public void write(int b) {
                    //NO-OP
                }
            });
        } else {
            serr = System.err;
        }
        this.session = session;
        this.doSwitch(node);
    }
    
    @Override
    public Action caseAction(Action action) {
        for(PortWrite write: action.getOutputs()) {
            for(Declaration d: write.getDeclarations()) {
                FixMovedExpr.moveScope(d, action, write, false);
                action.getDeclarations().add(d);
            }
            for(Statement s:write.getStatements()) {
                FixMovedExpr.moveScope(s, action, write, false);
                action.getStatements().add(s);
            }
        }
        return action;
    }
    
    @Override
    public AbstractActor caseNetwork(Network obj) {
        AbstractActor ret = super.caseNetwork(obj);
        String path = TransUtil.getPath(ret);
        TransUtil.AnnotatePass(ret, IrPassTypes.PortTransformations, "0");
        ActorDirectory.addTransformedActor(ret, null, path);

        for(ActorInstance a : obj.getActors()) {
            AbstractActor actor=null;
            try {
                System.out.println("[Port Transformations] Read in actor instance '" + a.getName() + "' of class " + ((TypeActor) a.getType()).getName());
                actor = (AbstractActor) ActorDirectory.findTransformedActor(a);
            } catch (DirectoryException x) {
                //OK, since likely external
            }
            if(actor!=null && !(actor instanceof ExternalActor)) {
                actor = (AbstractActor) doSwitch(actor);
                path = TransUtil.getPath(actor);
                TransUtil.AnnotatePass(actor, IrPassTypes.PortTransformations, "0");
                ActorDirectory.addTransformedActor(actor, a, path);
            }
        }
        return ret;
    }

}