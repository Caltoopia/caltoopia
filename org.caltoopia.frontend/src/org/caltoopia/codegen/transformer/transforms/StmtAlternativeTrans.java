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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.cli.CompilationSession;
import org.caltoopia.cli.DirectoryException;
import org.caltoopia.codegen.UtilIR;
import org.caltoopia.codegen.transformer.FixMovedExpr;
import org.caltoopia.codegen.transformer.IrTransformer;
import org.caltoopia.codegen.transformer.IrTransformer.IrPassTypes;
import org.caltoopia.codegen.transformer.TransUtil;
import org.caltoopia.ir.AbstractActor;
import org.caltoopia.ir.ActorInstance;
import org.caltoopia.ir.Block;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.ExternalActor;
import org.caltoopia.ir.IfStatement;
import org.caltoopia.ir.Network;
import org.caltoopia.ir.Node;
import org.caltoopia.ir.StmtAlternative;
import org.caltoopia.ir.TagOf;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.util.IrReplaceSwitch;

public class StmtAlternativeTrans extends IrReplaceSwitch {
    
    private PrintStream serr = null; 
    private CompilationSession session;

    /*
     * Transforms the guard expressions into an if statement surrounding the
     * old Block.
     * 
     * node: top network
     * session: contains metadata about the build like directory paths etc
     * errPrint: if error printout should be printed
     */

    public StmtAlternativeTrans(Node node, CompilationSession session, boolean errPrint) {
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
        if(node!=null) {
            this.doSwitch(node);
        }
    }
    
    @Override
    public StmtAlternative caseStmtAlternative(StmtAlternative alt) {
        /*
         * TagOf guards will be checked in switch-case statement outside of
         * the alternative. So only all the rest of the guards will
         * be transformed into the if statement.
         */
        Expression condition = null;
        List<Expression> rmGuards = new ArrayList<Expression>();
        for(Expression g: alt.getGuards()) {
            if(!(g instanceof TagOf)) {
                rmGuards.add(g);
                if(condition==null) {
                    condition = g;
                } else {
                    condition = UtilIR.createExpression(condition,"and",g);
                }
            }
        }
        //Remove all guards that moved into if statement
        for(Expression g: rmGuards) {
            alt.getGuards().remove(g);
        }
        if(condition!=null) {
            Block block = UtilIR.createBlock(alt);
            block.getStatements().addAll(alt.getStatements());
            TransUtil.setAnnotation(block, "caseAlternative", "finished", "true");
            alt.getStatements().clear();
            UtilIR.createIf(alt, condition, block, null);
        }
        
        return super.caseStmtAlternative(alt);
    }
        
    @Override
    public AbstractActor caseNetwork(Network obj) {
        AbstractActor ret = super.caseNetwork(obj);
        String path = TransUtil.getPath(ret);
        TransUtil.AnnotatePass(ret, IrPassTypes.StmtAlternative, "0");
        ActorDirectory.addTransformedActor(ret, null, path);

        for(ActorInstance a : obj.getActors()) {
            AbstractActor actor=null;
            try {
                System.out.println("[CreateForEachDeclarations] Read in actor instance '" + a.getName() + "' of class " + ((TypeActor) a.getType()).getName());
                actor = (AbstractActor) ActorDirectory.findTransformedActor(a);
            } catch (DirectoryException x) {
                //OK, since likely external
            }
            if(actor!=null && !(actor instanceof ExternalActor)) {
                actor = (AbstractActor) doSwitch(actor);
                path = TransUtil.getPath(actor);
                TransUtil.AnnotatePass(actor, IrPassTypes.StmtAlternative, "0");
                ActorDirectory.addTransformedActor(actor, a, path);
            }
        }
        return ret;
    }

}