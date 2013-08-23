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

import org.caltoopia.ast2ir.Stream;
import org.caltoopia.ast2ir.Util;
import org.caltoopia.ast2ir.Stream.Indent;
import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.cli.DirectoryException;
import org.caltoopia.codegen.CodegenError;
import org.caltoopia.codegen.UtilIR;
import org.caltoopia.codegen.printer.CBuildVarDeclaration.varCB;
import org.caltoopia.codegen.transformer.IrTransformer;
import org.caltoopia.codegen.transformer.TransUtil;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarAccess;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarType;
import org.caltoopia.ir.Assign;
import org.caltoopia.ir.BinaryExpression;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Block;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.ForEach;
import org.caltoopia.ir.Generator;
import org.caltoopia.ir.IfStatement;
import org.caltoopia.ir.LambdaExpression;
import org.caltoopia.ir.Namespace;
import org.caltoopia.ir.ProcCall;
import org.caltoopia.ir.ProcExpression;
import org.caltoopia.ir.ReturnValue;
import org.caltoopia.ir.Statement;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.TypeConstructorCall;
import org.caltoopia.ir.TypeExternal;
import org.caltoopia.ir.TypeLambda;
import org.caltoopia.ir.TypeList;
import org.caltoopia.ir.TypeProc;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableExternal;
import org.caltoopia.ir.VariableImport;
import org.caltoopia.ir.VariableReference;
import org.caltoopia.ir.WhileLoop;
import org.caltoopia.ir.util.IrSwitch;
import org.eclipse.emf.ecore.EObject;

public class CBuildStatement extends IrSwitch<Boolean> {
    String statStr="";
    Statement statement;
    boolean semicolon = true;
    
    private IndentStr ind = null;

    public CBuildStatement(Statement statement, IndentStr ind, boolean semicolon) {
        statStr="";
        this.statement = statement;
        this.semicolon = semicolon;
        if(ind == null) {
            this.ind = new IndentStr();
        } else {
            this.ind = ind;
        }
    }
    
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
        if(TransUtil.getAnnotationArg(block, IrTransformer.C_ANNOTATION, "forLoop").equals("c")) {
            statStr += new CBuildInlineBody(block,ind).toStr();
        } else {
            statStr += new CBuildBody(block,ind).toStr();
        }
        leave();
        return true;
    }

    @Override
    public Boolean caseAssign(Assign assign) {
        enter(assign);
        //TODO fix more complicated assignments
        statStr += ind.ind() + new CBuildVarReference(assign.getTarget()).toStr() + " = ";
        statStr += new CBuildExpression(assign.getExpression()).toStr();
        if(semicolon) {
            statStr += ";" + ind.nl();
        }
        /*
        if(assign.getExpression() instanceof TypeConstructorCall && !withinDeclaration()) {
            TypeConstructorCall expr = (TypeConstructorCall) assign.getExpression();
            statStr += (validCName(expr.getName()));
            statStr += ("(");
            if(hasIndex(assign.getTarget())) statStr += ("&");
            doSwitch(assign.getTarget());
            statStr += (", ");
            for (Iterator<Expression> i = expr.getParameters().iterator(); i.hasNext();) {
                Expression p = i.next();
                doSwitch(p);
                if (i.hasNext()) statStr += Comma();
            }
            statStr += Right();
            statStr += ln(";");
            leave();
            return true;
        }
        
        if(isIndir(assign.getTarget().getDeclaration())) statStr += ("*");
        doSwitch(assign.getTarget());
        statStr += (" = ");
        doSwitch(assign.getExpression());
        statStr += ln(";");
        */
        leave();
        return true;
    }

    @Override
    public Boolean caseProcCall(ProcCall call) {
        enter(call);
        VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(call, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
        switch(varType) {
        case proc:
            statStr += ind.ind() + (CPrintUtil.validCName(call.getProcedure().getName()));
            break;
        case importProc:
            statStr += ind.ind() + (call.getProcedure().getName().startsWith("dprint")?"":TransUtil.getNamespaceAnnotation(call) + "__");
            statStr += (CPrintUtil.validCName(call.getProcedure().getName()));
            break;
        case externProc:
            //TODO fix extern declared procedures
            //Map<String,String> annotations = getExternAnnotations(collectAnnotations(call,ns));
            //statStr += (externalCName(annotations,e));
            //toEnv(annotations);
            statStr += ind.ind() + (CPrintUtil.validCName(call.getProcedure().getName()));
            //break;
        default:
            VarAccess varAccess = VarAccess.valueOf(TransUtil.getAnnotationArg(call, IrTransformer.VARIABLE_ANNOTATION, "VarAccess"));
            String typeUsage = TransUtil.getAnnotationArg(call, IrTransformer.TYPE_ANNOTATION, "TypeUsage");
            statStr +=("/* PC " + call.getProcedure().getName() + ", " +
                    varType.name() +", " +
                    varAccess.name() +", " +
                    typeUsage +" */");
        }

        statStr += "(";
        statStr += ("thisActor");
        if(!call.getOutParameters().isEmpty() || !call.getInParameters().isEmpty())
            statStr += (", ");
        
        for (int i = 0; i<call.getInParameters().size(); i++) {
            Expression p = call.getInParameters().get(i);
            statStr += new CBuildExpression(p).toStr();
            if (i<call.getInParameters().size()-1) statStr += ", ";
        }

        if(!call.getOutParameters().isEmpty()) {
            statStr += ", ";
            for (int i = 0; i<call.getOutParameters().size(); i++) {
                VariableReference p = call.getOutParameters().get(i);
                statStr += new CBuildVarReference(p).toStr();
                if (i<call.getOutParameters().size()-1) statStr += ", ";
            }
        }
        statStr += ")";
        if(semicolon) {
            statStr += ";" +ind.nl();
        }

        leave();
        return true;
    }

    @Override
    public Boolean caseWhileLoop(WhileLoop stmt) {
        enter(stmt);
        statStr += ind.ind() + ("while (");
        statStr += new CBuildExpression(stmt.getCondition()).toStr();
        statStr += (")") + ind.nl();
        statStr += new CBuildBody(stmt.getBody(),ind).toStr();
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
        if(TransUtil.getAnnotationArg(stmt, IrTransformer.C_ANNOTATION, "forLoop").equals("c")) {
            //This is a c-style for loop
            statStr += ind.ind() + ("for (");
            if (stmt.getThenBlock()!=null && !stmt.getThenBlock().getStatements().isEmpty()) {
                statStr += new CBuildInlineBody(stmt.getThenBlock(),ind).toStr();
            }
            statStr += "; ";
            statStr += new CBuildExpression(stmt.getCondition()).toStr() + "; ";
            if (stmt.getElseBlock()!=null && !stmt.getElseBlock().getStatements().isEmpty()) {
                statStr += new CBuildInlineBody(stmt.getElseBlock(),ind).toStr();
            }
            statStr += (")") + ind.nl();
        } else {
            statStr += ind.ind() + ("if (");
            statStr += new CBuildExpression(stmt.getCondition()).toStr();
            statStr += (")") + ind.nl();
            statStr += new CBuildBody(stmt.getThenBlock(),ind).toStr();
            if (stmt.getElseBlock()!=null && !stmt.getElseBlock().getStatements().isEmpty()) {
                statStr += ind.ind() + ("else") + ind.nl();
                statStr += new CBuildBody(stmt.getElseBlock(),ind).toStr();
            }
        }
        leave();
        return true;
    }

    @Override
    public Boolean caseReturnValue(ReturnValue returnValue) {
        enter(returnValue);
        statStr += ind.ind() + ("return ");
        statStr += new CBuildExpression(returnValue.getValue()).toStr();
        if(semicolon) {
            statStr += ";" + ind.nl();
        }
        leave();
        return true;
    }

}
