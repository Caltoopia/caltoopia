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
import java.util.Stack;

import org.caltoopia.ast2ir.Stream;
import org.caltoopia.ast2ir.Util;
import org.caltoopia.ast2ir.Stream.Indent;
import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.cli.DirectoryException;
import org.caltoopia.codegen.CEnvironment;
import org.caltoopia.codegen.CodegenError;
import org.caltoopia.codegen.UtilIR;
import org.caltoopia.codegen.printer.CBuildVarDeclaration.varCB;
import org.caltoopia.codegen.transformer.IrTransformer;
import org.caltoopia.codegen.transformer.TransUtil;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarAccess;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarLocalAccess;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarType;
import org.caltoopia.ir.Actor;
import org.caltoopia.ir.Assign;
import org.caltoopia.ir.BinaryExpression;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Block;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.ForEach;
import org.caltoopia.ir.ForwardDeclaration;
import org.caltoopia.ir.FunctionCall;
import org.caltoopia.ir.Generator;
import org.caltoopia.ir.IfStatement;
import org.caltoopia.ir.LambdaExpression;
import org.caltoopia.ir.Namespace;
import org.caltoopia.ir.ProcCall;
import org.caltoopia.ir.ProcExpression;
import org.caltoopia.ir.ReturnValue;
import org.caltoopia.ir.Scope;
import org.caltoopia.ir.Statement;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.TypeConstructorCall;
import org.caltoopia.ir.TypeExternal;
import org.caltoopia.ir.TypeLambda;
import org.caltoopia.ir.TypeList;
import org.caltoopia.ir.TypeProc;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.ir.VariableExternal;
import org.caltoopia.ir.VariableImport;
import org.caltoopia.ir.VariableReference;
import org.caltoopia.ir.WhileLoop;
import org.caltoopia.ir.util.IrSwitch;
import org.eclipse.emf.ecore.EObject;

/*
 * This class generates a string for a statement. 
 * This is the general statement printer for most
 * statements.
 * Assignment and block (body) is broken out into 
 * CBuildAssign and CBuildBody.
 * 
 * Quality: 5, should work
 */
public class CBuildStatement extends IrSwitch<Boolean> {
    String statStr="";
    Statement statement;
    boolean semicolon = true;
    CEnvironment cenv = null;
    private IndentStr ind = null;
    Scope scope = null;

    /*
     * Constructor for building a long string containing the 
     * c-code of a statement. The statement is printed as a 
     * line or more lines of c-code and should be embedded in
     * Block/Action bodies. 
     * 
     * statement: statement to be printed
     * cenv: input/output variable collecting information that is 
     *       needed in makefiles etc, same object used for all CBuilders
     * ind: indentation object, passed in so that sub-parts maintains overall indentation level
     * semicolon: a leftover from supporting inline body should always be true
     * scope: the scope of the statement (not currently used)
     */
    public CBuildStatement(Statement statement, CEnvironment cenv, IndentStr ind, boolean semicolon, Scope scope) {
        statStr="";
        this.statement = statement;
        this.semicolon = semicolon;
        this.cenv = cenv;
        this.scope = scope;
        if(ind == null) {
            this.ind = new IndentStr();
        } else {
            this.ind = ind;
        }
    }
    
    /*
     * Do the actual generation of the statement string, use as:
     * new CBuildStatement(...).toStr()
     */
    public String toStr() {
        Boolean res = doSwitch(statement);
        if(!res) {
            CodegenError.err("Statement builder", statStr);
        }
        return statStr;
    }
    
    private void enter(EObject obj) {}
    private void leave() {}

    @Override
    public Boolean caseBlock(Block block) {
        enter(block);
        if(TransUtil.getAnnotationArg(block, IrTransformer.C_ANNOTATION, "inlineBlock").equals("c")) {
            statStr += new CBuildInlineBody(block, cenv, ind).toStr();
        } else {
            statStr += new CBuildBody(block, cenv, ind).toStr();
        }
        leave();
        return true;
    }

    @Override
    public Boolean caseAssign(Assign assign) {
        enter(assign);
        statStr += new CBuildAssign(assign, cenv, ind, semicolon, scope).toStr();
        leave();
        return true;
    }

    @Override
    public Boolean caseProcCall(ProcCall call) {
        enter(call);
        Declaration p = call.getProcedure() instanceof ForwardDeclaration?((ForwardDeclaration)call.getProcedure()).getDeclaration():call.getProcedure();
        VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(p, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
        String thisStr = "";
        String extraParamStr = "";
        String nameStr="";
        boolean print = false;
        switch(varType) {
        case proc:
            thisStr = "__";
            nameStr = (CPrintUtil.validCName(p.getName()));
            //First parameter is pointer to actor instance to access actor state variables
            extraParamStr = ("thisActor");
            if(!(call.getInParameters().isEmpty() && call.getOutParameters().isEmpty()))
                extraParamStr += (", ");
            print = true;
            break;
        case externProc:
            Declaration pp = null;
            try {
                pp = ActorDirectory.findVariable((VariableImport)p, false);
            } catch (DirectoryException e) {
                System.err.println("[CBuildStatement] Could not find imported extern procedure " + p.getName());
            }
            VariableExternal e = (VariableExternal) pp;
            Namespace ns = null;
            try {
                ns = ActorDirectory.findNamespace(((VariableImport)p).getNamespace());
            } catch (DirectoryException ee) {}
            //Get the extern annotations that may affect the name of the procedure
            Map<String,String> annotations = CPrintUtil.getExternAnnotations(CPrintUtil.collectAnnotations(e,ns));
            nameStr = (CPrintUtil.externalCName(annotations,e));
            //also merge in the annotations to the overall c environment used for makefiles etc 
            CPrintUtil.toEnvEnv(annotations,cenv);
            print = true;
            break;
        default:
            VarAccess varAccess = VarAccess.valueOf(TransUtil.getAnnotationArg(p, IrTransformer.VARIABLE_ANNOTATION, "VarAccess"));
            String typeUsage = TransUtil.getAnnotationArg(p, IrTransformer.TYPE_ANNOTATION, "TypeUsage");
            statStr +=("/* PC " + p.getName() + ", " +
                    varType.name() +", " +
                    varAccess.name() +", " +
                    typeUsage +" */");
        }
        //Print the common part, i.e. parameter expressions
        if(print) {
            statStr += ind.ind() + thisStr + nameStr + "(" + extraParamStr;
            
            for (int i = 0; i<call.getInParameters().size(); i++) {
                Expression ep = call.getInParameters().get(i);
                statStr += new CBuildExpression(ep, cenv,false,false,true).toStr();
                if (i<call.getInParameters().size()-1) statStr += ", ";
            }
            //Currently the frontend syntax don't allow output parameters
            if(!call.getOutParameters().isEmpty()) {
                statStr += ", ";
                for (int i = 0; i<call.getOutParameters().size(); i++) {
                    VariableReference rp = call.getOutParameters().get(i);
                    statStr += new CBuildVarReference(rp, cenv).toStr();
                    if (i<call.getOutParameters().size()-1) statStr += ", ";
                }
            }
            statStr += ")";
            if(semicolon) {
                statStr += ";" +ind.nl();
            }
        }
        leave();
        return true;
    }

    @Override
    public Boolean caseWhileLoop(WhileLoop stmt) {
        enter(stmt);
        statStr += ind.ind() + ("while (");
        statStr += new CBuildExpression(stmt.getCondition(), cenv).toStr();
        statStr += (")") + ind.nl();
        statStr += new CBuildBody(stmt.getBody(),cenv, ind).toStr();
        leave();
        return true;
    }

    @Override
    public Boolean caseForEach(ForEach stmt) {
        enter(stmt);
        //The Create for loop pass has mangled the generators into the body
        doSwitch(stmt.getBody());
        leave();
        return true;
    }

    @Override
    public Boolean caseIfStatement(IfStatement stmt) {
        enter(stmt);
        statStr += ind.ind() + ("if (");
        statStr += new CBuildExpression(stmt.getCondition(), cenv).toStr();
        statStr += (")") + ind.nl();
        statStr += new CBuildBody(stmt.getThenBlock(), cenv, ind).toStr();
        if (stmt.getElseBlock()!=null && !stmt.getElseBlock().getStatements().isEmpty()) {
            statStr += ind.ind() + ("else") + ind.nl();
            statStr += new CBuildBody(stmt.getElseBlock(), cenv, ind).toStr();
        }
        leave();
        return true;
    }

    @Override
    public Boolean caseReturnValue(ReturnValue returnValue) {
        enter(returnValue);
        statStr += ind.ind() + ("return ");
        statStr += new CBuildExpression(returnValue.getValue(), cenv,false,true,false).toStr();
        if(semicolon) {
            statStr += ";" + ind.nl();
        }
        leave();
        return true;
    }

}
