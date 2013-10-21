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
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.caltoopia.ast2ir.Util;
import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.cli.CompilationSession;
import org.caltoopia.cli.DirectoryException;
import org.caltoopia.codegen.CodegenError;
import org.caltoopia.codegen.UtilIR;
import org.caltoopia.codegen.transformer.IrTransformer;
import org.caltoopia.codegen.transformer.IrTransformer.IrPassTypes;
import org.caltoopia.codegen.transformer.TransUtil;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarAssign;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarLocalAccess;
import org.caltoopia.codegen.transformer.FixMovedExpr;
import org.caltoopia.ir.AbstractActor;
import org.caltoopia.ir.Action;
import org.caltoopia.ir.ActorInstance;
import org.caltoopia.ir.Assign;
import org.caltoopia.ir.Block;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.ExternalActor;
import org.caltoopia.ir.IrFactory;
import org.caltoopia.ir.LambdaExpression;
import org.caltoopia.ir.ListExpression;
import org.caltoopia.ir.Network;
import org.caltoopia.ir.Node;
import org.caltoopia.ir.ProcExpression;
import org.caltoopia.ir.ReturnValue;
import org.caltoopia.ir.Scope;
import org.caltoopia.ir.Statement;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.TypeLambda;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.ir.util.IrReplaceSwitch;
import org.caltoopia.types.TypeSystem;
import org.eclipse.emf.ecore.EObject;

public class ExprToTempVar extends IrReplaceSwitch {
    
    private PrintStream serr = null; 
    private CompilationSession session;

    public ExprToTempVar(Node node, CompilationSession session, boolean errPrint) {
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
    
    private class moveIt extends IrReplaceSwitch {
        Stack<Node> stack = new Stack<Node>();
        
        Statement s = null;
        List<Declaration> declarations = null;
        List<Statement> statements = null;
        Scope scope = null;
        int pos;
        int inserts = 0;
        boolean multi = false;
        
        moveIt(Statement s, int pos, List<Declaration> declarations, List<Statement> statements, Scope scope) {
            this.s=s;
            this.pos = pos;
            this.declarations = declarations;
            this.statements = statements;
            this.scope = scope;
            doSwitch(s);
        }
        
        @Override
        public Statement caseAssign(Assign assign) {
            stack.push(assign);
            Assign lAssign = assign;
            Map<String,String> assignAnn = TransUtil.getAnnotationsMap(assign);
            Map<String,String> targetAnn = TransUtil.getAnnotationsMap(assign.getTarget());
            Map<String,String> exprAnn = TransUtil.getAnnotationsMap(assign.getExpression());
            String selfAnn = assignAnn == null?null:assignAnn.get("Variable_VarLocalAccess");
            boolean selfAssign = (selfAnn == null)?false:VarLocalAccess.valueOf(selfAnn).equals(VarLocalAccess.self);
            if (selfAssign || !(assign.getExpression() instanceof VariableExpression)) {
                String vla = targetAnn==null?null:targetAnn.get("Variable_VarLocalAccess");
                if(vla == null) {
                    vla = exprAnn==null?null:exprAnn.get("Variable_VarLocalAccess");
                }
                if(vla == null) {
                    vla = VarLocalAccess.unknown.name();
                }
                Variable target;
                Assign newAssign;
                switch (VarLocalAccess.valueOf(vla)) {
                //Anything that is a normal scalar assignment CAL and c looks similar
                case scalar:
                case scalarUserType:
                case listSingle:
                case listMultiSingle:
                case listUserTypeSingle:
                case listMultiUserTypeSingle:
                case listMemberListMultiSingle:
                case listMemberListMultiUserTypeSingle:
                case listMemberListSingle:
                case listMemberListUserTypeSingle:
                case listMemberScalar:
                case listMemberScalarUserType:
                case memberListMultiSingle:
                case memberListMultiUserTypeSingle:
                case memberListSingle:
                case memberListUserTypeSingle:
                case memberScalar:
                case memberScalarUserType:
                case inlinedMember:
                    //CodegenError.err("Expr to temp var", "Did not do anything for scalar " + assign.getTarget().getDeclaration().getName());
                    break;
                //Assignment of a list to a list, break up into temp var
                case list:
                case listMultiList:
                case listMemberList:
                case listMemberListMultiList:
                case memberList:
                case memberListMultiList:
                case listUserType:
                case listMemberListUserType:
                case memberListUserType:
                    /*
                    target = UtilIR.createVarDef(null, "__temp_" + assign.getExpression().getId(), assign.getTarget().getType());
                    target.setScope(scope);
                    declarations.add(target);
                    newAssign = UtilIR.createAssignN(scope, target, assign.getExpression());
                    lAssign = newAssign;
                    TransUtil.setAnnotation(newAssign, "Variable", "VarLocalAccess", VarLocalAccess.temp.name());
                    statements.add(pos, newAssign);
                    assign.setExpression(UtilIR.createExpression(scope, target));
                    TransUtil.rmAnnotation(assign, "Variable", "VarLocalAccess");
                    inserts++;
                    break;*/
                //Assignment of a multi-dim list to a multi-dim list, break up into temp vars
                case listMulti:
                case listMemberListMulti:
                case memberListMulti:
                case listMultiUserType:
                case listMemberListMultiUserType:
                case memberListMultiUserType:
                case refMember:
                    target = UtilIR.createVarDef(null, "__temp_" + assign.getExpression().getId(), assign.getTarget().getType());
                    target.setScope(scope);
                    TransUtil.setAnnotation(target, "Variable", "VarLocalAccess", VarLocalAccess.temp.name());
                    declarations.add(target);
                    FixMovedExpr.moveScope(assign.getExpression(), scope, assign.getExpression().getContext());
                    newAssign = UtilIR.createAssignN(scope, target, assign.getExpression());
                    lAssign = newAssign;
                    TransUtil.setAnnotation(newAssign, "Variable", "VarLocalAccess", VarLocalAccess.temp.name());
                    statements.add(pos, newAssign);
                    assign.setExpression(UtilIR.createExpression(scope, target));
                    TransUtil.rmAnnotation(assign, "Variable", "VarLocalAccess");
                    inserts++;
                    stack.pop();
                    stack.push(lAssign);
                    Expression e = (Expression) doSwitch(lAssign.getExpression());
                    if(e != null) {
                        lAssign.setExpression(e);
                    }
                    break;
                default:
                    //CodegenError.err("Expr to temp var", "Did not expect a " + vla +" for " + assign.getTarget().getDeclaration().getName());
                }
            } else {
                //CodegenError.err("Expr to temp var", "Did not do anything for " + assign.getTarget().getDeclaration().getName());
            }
            stack.pop();
            return assign;
        }
        
        @Override
        public Expression caseVariableExpression(VariableExpression var) {
            //Don't go into variable expressions
            return var;
        }
        @Override
        public Statement caseBlock(Block block) {
            //Don't go into blocks, that is handled by ExprToTempVar class
            return block;
        }

        @Override
        public EObject caseListExpression(ListExpression expr) {
            if(!expr.getGenerators().isEmpty()) {
                //TODO
            } else {
                if(stack.empty()) {
                    Variable target = UtilIR.createVarDef(null, "__temp_" + expr.getId(), expr.getType());
                    target.setScope(scope);
                    TransUtil.setAnnotation(target, "Variable", "VarLocalAccess", VarLocalAccess.temp.name());
                    declarations.add(target);
                    FixMovedExpr.moveScope(expr, scope, expr.getContext());
                    Assign newAssign = UtilIR.createAssignN(scope, target, expr);
                    TransUtil.setAnnotation(newAssign, "Variable", "VarLocalAccess", VarLocalAccess.temp.name());
                    statements.add(pos+inserts, newAssign);
                    inserts++;
                    stack.push(newAssign);
                    doSwitch(newAssign.getExpression());
                    stack.pop();
                    return UtilIR.createExpression(scope, target);
                }
                Assign assign = (Assign) stack.peek();
                //CodegenError.info("Expr to temp var","Peek(" + stack.size() + ") at " + assign.getTarget().getDeclaration().getName() + (stack.size()>1?", " + ((Assign) stack.get(1)).getExpression().getContext().getId():"") +" in " + assign.getExpression().getContext().getId() + " of class " + assign.getExpression().getContext().getClass().getName());
                int lpos = statements.indexOf(assign);
                int linserts = 0;
                statements.remove(lpos);
                inserts--;
                for (int i = 0; i < expr.getExpressions().size(); i++) {
                    Expression e =  expr.getExpressions().get(i);
                    /*if(UtilIR.isList(e.getType())) {
                        doSwitch(e);
                    }*/
                    if(!(e instanceof VariableExpression) && !UtilIR.isScalarLiteralExpression(e)) {
                        String id = e.getId()==null?Util.getDefinitionId():e.getId();
                        if(e.getType() == null) {
                            CodegenError.err("Expression to temp var", "An element " + e.getId() + "in list expression is typeless");
                        }
                        Variable target = UtilIR.createVarDef(null, "__temp_" + id, e.getType());
                        target.setScope(scope);
                        TransUtil.setAnnotation(target, "Variable", "VarLocalAccess", VarLocalAccess.temp.name());
                        declarations.add(target);
                        FixMovedExpr.moveScope(e,scope,expr.getContext());
                        Assign newAssign = UtilIR.createAssignN(scope, target, e);
                        TransUtil.setAnnotation(newAssign, "Variable", "VarLocalAccess", VarLocalAccess.temp.name());
                        statements.add(lpos+linserts, newAssign);
                        linserts++;
                        inserts++;
                        int insertsOld = inserts;
                        stack.push(newAssign);
                        doSwitch(e);
                        stack.pop();
                        linserts += inserts-insertsOld;
                        e = UtilIR.createExpression(scope, target);
                    }
                    List<Expression> index = new ArrayList<Expression>();
                    index.addAll(assign.getTarget().getIndex());
                    index.add(UtilIR.lit(i));
                    Assign newIndAssign = UtilIR.createAssignN(scope, 
                            UtilIR.createVarRef(assign.getTarget().getDeclaration(), index),
                            e);
                    if(i==0) {
                        if(!TransUtil.allFixedLength(newIndAssign.getTarget().getDeclaration().getType())) {
                            TransUtil.setAnnotation(newIndAssign, "Variable", "ListSize", String.valueOf(expr.getExpressions().size()));
                        } else {
                            TransUtil.setAnnotation(newIndAssign, "Variable", "ListSize", "0");
                        }
                    }
                    statements.add(lpos+linserts, newIndAssign);
                    linserts++;
                    inserts++;
                }
                return null;
            }
            return expr;
        }

    }
    
    private boolean moveExprToTempVar(List<Declaration> declarations, List<Statement> statements, Scope scope) {
        if(declarations == null || scope == null || statements == null)
            return false;
                
        for(int i=0;i<statements.size();i++) {
            Statement s = statements.get(i);
            if(!(s instanceof Block)) {
                i += new moveIt(s,i,declarations,statements,scope).inserts;
            }
        }
        return false;
    }
    
    @Override
    public Action caseAction(Action action) {
        moveExprToTempVar(action.getDeclarations(), action.getStatements(), action);
        return super.caseAction(action);
    }
    
    @Override
    public EObject caseLambdaExpression(LambdaExpression obj) {
        /*
         * Lambda only have an expression and no block.
         * We might need to insert statements surrounding the expression.
         * Hence we set the expression to an anonymous proc expression
         * to obtain a Block were to place statements. 
         * (With a return of the original expression,
         * which is OK in C but not in CAL.)
         */
        ProcExpression pe = null;
        Block body = null;
        if(obj.getBody() instanceof ProcExpression) {
            pe = (ProcExpression) obj.getBody();
            body = pe.getBody();
        } else {
            body = IrFactory.eINSTANCE.createBlock();
            pe = IrFactory.eINSTANCE.createProcExpression();
            pe.setBody(body);
            pe.setContext(obj);
            pe.setOuter(obj.getOuter());
            pe.setId(Util.getDefinitionId());
            pe.setType(TypeSystem.createTypeUndef()); //Normally a TypeProc, but this is a nameless parameterless ProcExpression
            body.setId(Util.getDefinitionId());
            body.setOuter(pe);
            body.getDeclarations().addAll(obj.getDeclarations());
            //The original expr as return stmt in temp variable (to allow for statements after the expression)
            Variable ret = UtilIR.createVarDef(body, "_cal__ret__", ((TypeLambda)obj.getType()).getOutputType());
            Assign assign = UtilIR.createAssign(body, ret, obj.getBody());
            TransUtil.setAnnotation(assign.getTarget().getDeclaration(),IrTransformer.VARIABLE_ANNOTATION, 
                    "VarAssign",VarAssign.movedRetAssigned.name());
            TransUtil.copyAnnotations(assign.getTarget(), obj.getBody());
            body.getStatements().add(assign);
            ReturnValue retstmt = IrFactory.eINSTANCE.createReturnValue();
            retstmt.setValue(UtilIR.createExpression(body,ret));
            retstmt.setId(Util.getDefinitionId());
            body.getStatements().add(retstmt);
            obj.setBody(pe);
        }
        
        moveExprToTempVar(body.getDeclarations(), body.getStatements(), body);
        return super.caseLambdaExpression(obj);
    }
    
    @Override
    public Statement caseBlock(Block block) {
        moveExprToTempVar(block.getDeclarations(), block.getStatements(), block);
        return super.caseBlock(block);
    }

    @Override
    public AbstractActor caseNetwork(Network obj) {
        AbstractActor ret = super.caseNetwork(obj);
        String path = TransUtil.getPath(ret);
        TransUtil.AnnotatePass(ret, IrPassTypes.ExprToTempVar, "0");
        ActorDirectory.addTransformedActor(ret, null, path);

        for(ActorInstance a : obj.getActors()) {
            AbstractActor actor=null;
            try {
                System.out.println("[ExprToTempVar] Read in actor instance '" + a.getName() + "' of class " + ((TypeActor) a.getType()).getName());
                actor = (AbstractActor) ActorDirectory.findTransformedActor(a);
            } catch (DirectoryException x) {
                //OK, since likely external
            }
            if(actor!=null && !(actor instanceof ExternalActor)) {
                actor = (AbstractActor) doSwitch(actor);
                path = TransUtil.getPath(actor);
                TransUtil.AnnotatePass(actor, IrPassTypes.ExprToTempVar, "0");
                ActorDirectory.addTransformedActor(actor, a, path);
            }
        }
        return ret;
    }

}