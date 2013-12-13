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
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarAssign;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarLocalAccess;
import org.caltoopia.codegen.transformer.FixMovedExpr;
import org.caltoopia.ir.AbstractActor;
import org.caltoopia.ir.Action;
import org.caltoopia.ir.ActorInstance;
import org.caltoopia.ir.Assign;
import org.caltoopia.ir.BinaryExpression;
import org.caltoopia.ir.Block;
import org.caltoopia.ir.CaseStatement;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.ExprAlternative;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.ExternalActor;
import org.caltoopia.ir.ForEach;
import org.caltoopia.ir.FunctionCall;
import org.caltoopia.ir.Guard;
import org.caltoopia.ir.IfExpression;
import org.caltoopia.ir.CaseExpression;
import org.caltoopia.ir.IfStatement;
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
import org.caltoopia.ir.StmtAlternative;
import org.caltoopia.ir.StringLiteral;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.TypeLambda;
import org.caltoopia.ir.TypeList;
import org.caltoopia.ir.TypeString;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.ir.util.IrReplaceSwitch;
import org.caltoopia.types.TypeSystem;
import org.eclipse.emf.ecore.EObject;

public class ExprToTempVar extends IrReplaceSwitch {
    
    private PrintStream serr = null; 
    private CompilationSession session;

    /*
     * Transforms the IR so that non-scalar expressions and assignments
     * (including strings) are broken down to scalar assignments when needed.
     * First an outer Switch is used to find lists of statements 
     * (in Block and Action). For each such list of statements 3 transformations
     * are made:
     * 1) MoveExpr - used to find expressions that can't easily be c-printed and
     * convert that to statements, typically assignments to temporary variable
     * or if expression to if statement when lists involved.
     * 2) moveAssign - used to break down any multi-dim list assignments or
     * assignments from list of elements into separate assign statements.
     * 3) moveString - since we treat CAL type string as list of char need to handle
     * any expressions doing string operations.
     * 
     * Quality: 3, no known issues but likely cases not yet supported
     * 
     * node: top network
     * session: contains metadata about the build like directory paths etc
     * errPrint: if error printout should be printed
     */

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
        //Continue at the caseNetwork at the end of the file
        this.doSwitch(node);
    }
    

    /*
     ***************************************************************
     * Split assignments of (multi-dim) lists since no matching 
     * C statement.
     * 
     * Inserts annotation of list size to allocate for dynamic
     * sized arrays. Make sure that self assignments introduce a
     * temporary variable assignment as a middle step. Assignments
     * of a list of elements is broken up into separate assignments.
     ***************************************************************
     */
    private class moveListAssign extends IrReplaceSwitch {
        Stack<Node> stack = new Stack<Node>();
        
        Statement s = null;
        List<Declaration> declarations = null;
        List<Statement> statements = null;
        Scope scope = null;
        int pos;
        int inserts = 0;
        boolean multi = false;
        
        moveListAssign(Statement s, int pos, List<Declaration> declarations, List<Statement> statements, Scope scope) {
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
            //Also handle the allocation when assign scalar into dynamic list
            if(assign.getTarget().getDeclaration().getType() instanceof TypeList && 
                    !TransUtil.allFixedLength(assign.getTarget().getDeclaration().getType()) &&
                    assignAnn.get("Variable_ListSize")==null &&
                    Arrays.asList(VarLocalAccess.listSingle.name(),
                            VarLocalAccess.listMultiSingle.name(),
                            VarLocalAccess.listUserTypeSingle.name(),
                            VarLocalAccess.listMultiUserTypeSingle.name(),
                            VarLocalAccess.listMemberListMultiSingle.name(),
                            VarLocalAccess.listMemberListMultiUserTypeSingle.name(),
                            VarLocalAccess.listMemberListSingle.name(),
                            VarLocalAccess.listMemberListUserTypeSingle.name(),
                            VarLocalAccess.memberListMultiSingle.name(),
                            VarLocalAccess.memberListMultiUserTypeSingle.name(),
                            VarLocalAccess.memberListSingle.name(),
                            VarLocalAccess.memberListUserTypeSingle.name()).contains(targetAnn.get("Variable_VarLocalAccess"))) {
                TransUtil.setAnnotation(assign, "Variable", "ListSize", String.valueOf(-1));
            }
            String selfAnn = assignAnn == null?null:assignAnn.get("Variable_VarLocalAccess");
            boolean selfAssign = (selfAnn == null)?false:VarLocalAccess.valueOf(selfAnn).equals(VarLocalAccess.self);
            //Only introduce temp variable if the expressions isn't only the same variable without further operation since moving of (some dimension of) lists can be handled
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
                case string:
                case memberString:
                case listMemberString:
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
                    //If list to list check if the expression list should be broken up
                    Expression e = (Expression) doSwitch(assign.getExpression());
                    if(e != null) {
                        assign.setExpression(e);
                    }
                    break;
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
                    FixMovedExpr.moveScope(assign.getExpression(), scope, assign.getExpression().getContext(), false);
                    newAssign = UtilIR.createAssignN(scope, target, assign.getExpression());
                    lAssign = newAssign;
                    TransUtil.setAnnotation(newAssign, "Variable", "VarLocalAccess", VarLocalAccess.temp.name());
                    statements.add(pos, newAssign);
                    assign.setExpression(UtilIR.createExpression(scope, target));
                    TransUtil.rmAnnotation(assign, "Variable", "VarLocalAccess");
                    inserts++;
                    stack.pop();
                    stack.push(lAssign);
                    e = (Expression) doSwitch(lAssign.getExpression());
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
                //Generator expressions are handled in moveExpr
            } else {
                //The stack is used to go deep into the assignment, e.g. a many dimension array might need to be broken up several times
                //Also the stack makes sure to only go into list expressions that are part of an assignment
                if(stack.empty()) {
                    Variable target = UtilIR.createVarDef(null, "__temp_" + expr.getId(), expr.getType());
                    target.setScope(scope);
                    TransUtil.setAnnotation(target, "Variable", "VarLocalAccess", VarLocalAccess.temp.name());
                    declarations.add(target);
                    FixMovedExpr.moveScope(expr, scope, expr.getContext(), false);
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
                //We replace an assignment with another hence we remove the old
                statements.remove(lpos);
                inserts--;
                for (int i = 0; i < expr.getExpressions().size(); i++) {
                    Expression e =  expr.getExpressions().get(i);
                    /*if(UtilIR.isList(e.getType())) {
                        doSwitch(e);
                    }*/
                    //Don't introduce temp variable for expressions that already is a variable or is a scalar literal (it just looks stupid)
                    if(!(e instanceof VariableExpression) && !UtilIR.isScalarLiteralExpression(e)) {
                        String id = e.getId()==null?Util.getDefinitionId():e.getId();
                        if(e.getType() == null) {
                            CodegenError.err("Expression to temp var", "An element " + e.getId() + "in list expression is typeless");
                        }
                        Variable target = UtilIR.createVarDef(null, "__temp_" + id, e.getType());
                        target.setScope(scope);
                        TransUtil.setAnnotation(target, "Variable", "VarLocalAccess", VarLocalAccess.temp.name());
                        declarations.add(target);
                        FixMovedExpr.moveScope(e,scope,expr.getContext(),false);
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
                    //Expand the index with one for the specified element (might be multi-dim array with other indices that needs to be kept)
                    List<Expression> index = new ArrayList<Expression>();
                    index.addAll(assign.getTarget().getIndex());
                    index.add(UtilIR.lit(i));
                    Assign newIndAssign = UtilIR.createAssignN(scope, 
                            UtilIR.createVarRef(assign.getTarget().getDeclaration(), index),
                            e);
                    //For the first element we add an annotation to make the c printer insert an allocation of the array if needed.
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
    
    //Helper function for moving assignments of lists
    private boolean moveAssign(List<Declaration> declarations, List<Statement> statements, Scope scope) {
        if(declarations == null || scope == null || statements == null)
            return false;
                
        for(int i=0;i<statements.size();i++) {
            Statement s = statements.get(i);
            if(!(s instanceof Block) && !TransUtil.getAnnotationArg(s, "Variable", "Allocate").equals("true")) {
                i += new moveListAssign(s,i,declarations,statements,scope).inserts;
            }
        }
        return false;
    }
    
    /*
     ***************************************************************
     * Move expressions involving lists without C-matching into 
     * statements
     * 
     * Printer can't handle if-expressions of list so these are 
     * converted to if-statements. Also list expression can be of
     * the syntax [expr(x):for int x in List], called generator.
     * This is converted to a foreach statement which also have 
     * generator and the foreach to while statement transform is 
     * applied.
     ***************************************************************
     */
    private class moveExpr extends IrReplaceSwitch {
        Stack<Node> stack = new Stack<Node>();
        
        Statement s = null;
        List<Declaration> declarations = null;
        List<Statement> statements = null;
        Scope scope = null;
        int pos;
        int inserts = 0;
        boolean multi = false;
        
        moveExpr(Statement s, int pos, List<Declaration> declarations, List<Statement> statements, Scope scope) {
            this.s=s;
            this.pos = pos;
            this.declarations = declarations;
            this.statements = statements;
            this.scope = scope;
            doSwitch(s);
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
        public Statement caseAssign(Assign assign) {
            /*
             * Only go into assignments when not directly assign 
             * a list expression without generators, since they
             * are broken up into element assignments by moveAssign()
             */
            if(assign.getExpression() instanceof ListExpression &&
               ((ListExpression)assign.getExpression()).getGenerators().isEmpty()) {
                return assign;
            } else {
                return super.caseAssign(assign);
            }
        }

        @Override
        public Declaration caseVariable(Variable var) {
            /*
             * Only go into variables when not initialized with 
             * a list expression without generators.
             */
            if(var.getInitValue() instanceof ListExpression &&
               ((ListExpression)var.getInitValue()).getGenerators().isEmpty()) {
                return var;
            } else {
                return super.caseVariable(var);
            }
        }

        @Override
        public EObject caseIfExpression(IfExpression expr) {
            if(UtilIR.isList(expr.getType())) {
                Variable target = UtilIR.createVarDef(null, "__temp_" + expr.getId(), expr.getType());
                target.setScope(scope);
                TransUtil.setAnnotation(target, "Variable", "VarLocalAccess", VarLocalAccess.temp.name());
                declarations.add(target);
                Assign thenAssign = UtilIR.createAssignN(scope, target, expr.getThenExpression());
                TransUtil.setAnnotation(thenAssign, "Variable", "VarLocalAccess", VarLocalAccess.temp.name());
                Assign elseAssign = UtilIR.createAssignN(scope, target, expr.getElseExpression());
                TransUtil.setAnnotation(elseAssign, "Variable", "VarLocalAccess", VarLocalAccess.temp.name());
                IfStatement ifStatement = UtilIR.createIfN(scope, expr.getCondition(), (Statement)thenAssign, (Statement)elseAssign);
                FixMovedExpr.moveScope(ifStatement.getCondition(), scope, expr.getContext(), false);
                FixMovedExpr.moveScope(ifStatement.getThenBlock(), ifStatement.getThenBlock(), expr.getContext(), true);
                FixMovedExpr.moveScope(ifStatement.getElseBlock(), ifStatement.getElseBlock(), expr.getContext(), true);
                
                statements.add(pos+inserts, ifStatement);
                inserts++;
                
                if(s instanceof Assign) {
                    //Since we have inserted a temp var remove any self assign
                    TransUtil.rmAnnotation(s, "Variable", "VarLocalAccess","self");
                }
                return UtilIR.createExpression(scope, target);
            }
            return expr;
        }

        @Override
        public Expression caseCaseExpression(CaseExpression expr) {
            //Transform a case expression into a case statement
            //Temp variable to be assign the alternative expressions
            Variable target = UtilIR.createVarDef(null, "__temp_" + expr.getId(), expr.getType());
            target.setScope(scope);
            TransUtil.setAnnotation(target, "Variable", "VarLocalAccess", VarLocalAccess.temp.name());
            declarations.add(target);
            //First assign the default expression before case, 
            //since in CAL all expressions should be side effect free
            Assign assign = UtilIR.createAssignN(scope, target, expr.getDefault());
            TransUtil.setAnnotation(assign, "Variable", "VarLocalAccess", VarLocalAccess.temp.name());
            statements.add(pos+inserts, assign);
            inserts++;
            CaseStatement caze = IrFactory.eINSTANCE.createCaseStatement();
            caze.setId(Util.getDefinitionId());
            caze.setExpression(expr.getExpression());
            for(ExprAlternative e:expr.getAlternatives()) {
                StmtAlternative alt = IrFactory.eINSTANCE.createStmtAlternative();
                alt.setId(Util.getDefinitionId());
                alt.setOuter(scope);
                alt.getDeclarations().addAll(e.getDeclarations());
                FixMovedExpr.moveDeclScope(alt, alt, e, true);
                assign = UtilIR.createAssign(alt, target, e.getExpression());
                TransUtil.setAnnotation(assign, "Variable", "VarLocalAccess", VarLocalAccess.temp.name());
                alt.getGuards().addAll(e.getGuards());
                FixMovedExpr.moveScope(alt, alt, e, true);
                caze.getAlternatives().add(alt);
            }
            //Apply the transformation of guards
            caze = new StmtAlternativeTrans(null, session, false).caseCaseStatement(caze);
            statements.add(pos+inserts, caze);
            inserts++;
            
            if(s instanceof Assign) {
                //Since we have inserted a temp var remove any self assign
                TransUtil.rmAnnotation(s, "Variable", "VarLocalAccess","self");
            }
            return UtilIR.createExpression(scope, target);
        }

        @Override
        public EObject caseListExpression(ListExpression expr) {
            if(expr.getGenerators().isEmpty()) {
                /*
                 * If finds a list expression (not directly in assignment,
                 * see caseAssign) move it to an assignment. 
                 * moveAssign will divide it into element assignments
                 * if needed.
                 */
                Expression e = (Expression) super.caseListExpression(expr);
                if(e instanceof ListExpression) {
                    expr = (ListExpression) e;
                    Variable target = UtilIR.createVarDef(null, "__temp_" + expr.getId(), expr.getType());
                    target.setScope(scope);
                    declarations.add(target);
                    Assign assign = UtilIR.createAssignN(scope, target, expr);
                    TransUtil.setAnnotation(assign, "Variable", "VarLocalAccess", VarLocalAccess.temp.name());
                    IrVariableAnnotation.setLocalAccess(assign.getTarget());
                    FixMovedExpr.moveScope(assign.getExpression(), scope, expr.getContext(), false);
                    statements.add(pos+inserts, assign);
                    inserts++;
                    return UtilIR.createExpression(scope, target);
                } else {
                    return expr;
                }
            } else {
                /*
                 * Convert generator list expressions into foreach statement 
                 * and then into while statements, to reuse the same foreach
                 * transformation
                 */
                if(expr.getGenerators().size()==1) {
                    Variable target = UtilIR.createVarDef(null, "__temp_" + expr.getId(), expr.getType());
                    target.setScope(scope);
                    TransUtil.setAnnotation(target, "Variable", "VarLocalAccess", VarLocalAccess.temp.name());
                    declarations.add(target);
                    
                    ForEach fe = IrFactory.eINSTANCE.createForEach();
                    fe.setId(Util.getDefinitionId());
                    fe.getGenerators().addAll(expr.getGenerators());
                    Block body = UtilIR.createBlock(scope);
                    fe.setBody(body);
                    Variable index = UtilIR.createVarDef(null, "__temp_index_" + expr.getId(), TypeSystem.createTypeInt());
                    index.setScope(scope);
                    TransUtil.setAnnotation(index, "Variable", "VarLocalAccess", VarLocalAccess.temp.name());
                    declarations.add(index);
                    
                    //FIXME currently only support first generator as the syntax frontend
                    Assign assign = UtilIR.createAssign(body, 
                            UtilIR.createVarRef(target, Arrays.asList(UtilIR.createExpression(body, index))), 
                            expr.getExpressions().get(0));
                    TransUtil.setAnnotation(assign, "Variable", "VarLocalAccess", VarLocalAccess.temp.name());
                    //TODO This code expand the list one element at a time when not fixed size but should be made more efficient or try harder to calc size
                    if(!TransUtil.allFixedLength(assign.getTarget().getDeclaration().getType())) {
                        TransUtil.setAnnotation(assign, "Variable", "ListSize", "__temp_index_" + expr.getId());
                    } else {
                        TransUtil.setAnnotation(assign, "Variable", "ListSize", "0");
                    }
                    //FIXME currently only support first generator as the syntax frontend
                    FixMovedExpr.moveScope(body, body, expr.getGenerators().get(0), true);
                    
                    Assign incAssign = UtilIR.createAssign(body, 
                            index, 
                            UtilIR.createExpression(UtilIR.createExpression(body, index), "+", UtilIR.lit(1)));
                    
                    //Roll out the for each statement into while-statements etc
                    fe = new CreateForLoop(null, session, false).caseForEach(fe);
    
                    Assign initAssign = UtilIR.createAssignN(body, index, UtilIR.lit(0));
                    statements.add(pos+inserts, initAssign);
                    inserts++;
                    statements.add(pos+inserts, fe);
                    inserts++;
                    
                    if(s instanceof Assign) {
                        //Since we have inserted a temp var remove any self assign
                        TransUtil.rmAnnotation(s, "Variable", "VarLocalAccess","self");
                    }
                    return UtilIR.createExpression(scope, target);
                } else {
                    throw new RuntimeException("[Expr to Temp Var] Not yet implemented foreach list with multiple generators.");
                }
            }
        }
    }
    //Helper function to move expression into statement
    private boolean moveExprToStatement(List<Declaration> declarations, List<Statement> statements, Scope scope) {
        if(declarations == null || scope == null || statements == null)
            return false;

        for(int i=0;i<statements.size();i++) {
            Statement s = statements.get(i);
            if(!(s instanceof Block) && !TransUtil.getAnnotationArg(s, "Variable", "Allocate").equals("true")) {
                i += new moveExpr(s,i,declarations,statements,scope).inserts;
            }
        }
        return false;
    }
    
    /*
     ***************************************************************
     * Move expressions involving strings without C-matching into
     * statements.
     * 
     * Printer only handles concat of two strings, hence make
     * all binary expressions store result in temp variable. Also
     * if expressions are converted to if statement, to simplify
     * printing.
     ***************************************************************
     */
    private class moveStringExpr extends IrReplaceSwitch {
        Stack<Node> stack = new Stack<Node>();
        
        Statement s = null;
        List<Declaration> declarations = null;
        List<Statement> statements = null;
        Scope scope = null;
        int pos;
        int inserts = 0;
        boolean multi = false;
        
        moveStringExpr(Statement s, int pos, List<Declaration> declarations, List<Statement> statements, Scope scope) {
            this.s=s;
            this.pos = pos;
            this.declarations = declarations;
            this.statements = statements;
            this.scope = scope;
            doSwitch(s);
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
        public Expression caseStringLiteral(StringLiteral literal) {
            //Don't go into string literals
            return literal;
        }

        @Override
        public Expression caseBinaryExpression(BinaryExpression expr) {
            if(expr.getType() instanceof TypeString) {
                expr.setOperand1((Expression) doSwitch(expr.getOperand1()));
                expr.setOperand2((Expression) doSwitch(expr.getOperand2()));
                Variable target = UtilIR.createVarDef(null, "__temp_" + expr.getId(), expr.getType());
                target.setScope(scope);
                TransUtil.setAnnotation(target, "Variable", "VarLocalAccess", VarLocalAccess.temp.name());
                declarations.add(target);
                Assign assign = UtilIR.createAssignN(scope, target, expr);
                TransUtil.setAnnotation(assign, "Variable", "VarLocalAccess", VarLocalAccess.temp.name());
                statements.add(pos+inserts, assign);
                inserts++;
                TransUtil.setAnnotation(assign, "DEBUG", "StringExprToTemp", "BinaryExpression");
                
                if(s instanceof Assign) {
                    //Since we have inserted a temp var remove any self assign
                    TransUtil.rmAnnotation(s, "Variable", "VarLocalAccess","self");
                }
                return UtilIR.createExpression(scope, target);
            }
            return expr;
        }

        @Override
        public EObject caseIfExpression(IfExpression expr) {
            if(expr.getType() instanceof TypeString) {
                Variable target = UtilIR.createVarDef(null, "__temp_" + expr.getId(), expr.getType());
                target.setScope(scope);
                TransUtil.setAnnotation(target, "Variable", "VarLocalAccess", VarLocalAccess.temp.name());
                declarations.add(target);
                Assign thenAssign = UtilIR.createAssignN(scope, target, expr.getThenExpression());
                TransUtil.setAnnotation(thenAssign, "Variable", "VarLocalAccess", VarLocalAccess.temp.name());
                Assign elseAssign = UtilIR.createAssignN(scope, target, expr.getElseExpression());
                TransUtil.setAnnotation(elseAssign, "Variable", "VarLocalAccess", VarLocalAccess.temp.name());
                IfStatement ifStatement = UtilIR.createIfN(scope, expr.getCondition(), (Statement)thenAssign, (Statement)elseAssign);
                FixMovedExpr.moveScope(ifStatement.getCondition(), scope, expr.getContext(), false);
                FixMovedExpr.moveScope(ifStatement.getThenBlock(), ifStatement.getThenBlock(), expr.getContext(), true);
                FixMovedExpr.moveScope(ifStatement.getElseBlock(), ifStatement.getElseBlock(), expr.getContext(), true);
                
                statements.add(pos+inserts, ifStatement);
                inserts++;
                
                if(s instanceof Assign) {
                    //Since we have inserted a temp var remove any self assign
                    TransUtil.rmAnnotation(s, "Variable", "VarLocalAccess","self");
                }
                return UtilIR.createExpression(scope, target);
            }
            return expr;
        }

        @Override
        public Expression caseFunctionCall(FunctionCall expr) {
            super.caseFunctionCall(expr);
            if(expr.getType() instanceof TypeString) {
                Variable target = UtilIR.createVarDef(null, "__temp_" + expr.getId(), expr.getType());
                target.setScope(scope);
                TransUtil.setAnnotation(target, "Variable", "VarLocalAccess", VarLocalAccess.temp.name());
                declarations.add(target);
                Assign assign = UtilIR.createAssignN(scope, target, expr);
                TransUtil.setAnnotation(assign, "Variable", "VarLocalAccess", VarLocalAccess.temp.name());
                statements.add(pos+inserts, assign);
                inserts++;

                TransUtil.setAnnotation(assign, "DEBUG", "StringExprToTemp", "FunctionCall");
                
                if(s instanceof Assign) {
                    //Since we have inserted a temp var remove any self assign
                    TransUtil.rmAnnotation(s, "Variable", "VarLocalAccess","self");
                }
                return UtilIR.createExpression(scope, target);
            }
            return expr;
        }
    }
    
    //Helper function for the moveString class
    private boolean moveStringExprToStatement(List<Declaration> declarations, List<Statement> statements, Scope scope) {
        if(declarations == null || scope == null || statements == null)
            return false;

        for(int i=0;i<statements.size();i++) {
            Statement s = statements.get(i);
            if(!(s instanceof Block) && !TransUtil.getAnnotationArg(s, "Variable", "Allocate").equals("true")) {
                i += new moveStringExpr(s,i,declarations,statements,scope).inserts;
            }
        }
        return false;
    }

    /*
     ***************************************************************
     * Outer Switch find all blocks of statements in
     * Actions, Blocks (in procedures, etc), and also converted 
     * LambdaExpressions (functions) body to Block.
     * 
     * Call the moveExpr, moveAssign and moveStringExpr on those blocks
     ***************************************************************
     */
    @Override
    public Action caseAction(Action action) {
        moveExprToStatement(action.getDeclarations(), action.getStatements(), action);
        moveAssign(action.getDeclarations(), action.getStatements(), action);
        moveStringExprToStatement(action.getDeclarations(), action.getStatements(), action);
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
        
        moveExprToStatement(body.getDeclarations(), body.getStatements(), body);
        moveAssign(body.getDeclarations(), body.getStatements(), body);
        moveStringExprToStatement(body.getDeclarations(), body.getStatements(), body);

        for (int i = 0; i < obj.getParameters().size(); i++) {
            Variable param = (Variable) caseVariable(obj.getParameters().get(i));
            obj.getParameters().set(i, param);
        }
        return obj;
    }
    
    @Override
    public Statement caseBlock(Block block) {
        moveExprToStatement(block.getDeclarations(), block.getStatements(), block);
        moveAssign(block.getDeclarations(), block.getStatements(), block);
        moveStringExprToStatement(block.getDeclarations(), block.getStatements(), block);
        return super.caseBlock(block);
    }

    @Override
    public StmtAlternative caseStmtAlternative(StmtAlternative block) {
        moveExprToStatement(block.getDeclarations(), block.getStatements(), block);
        moveAssign(block.getDeclarations(), block.getStatements(), block);
        moveStringExprToStatement(block.getDeclarations(), block.getStatements(), block);
        return super.caseStmtAlternative(block);
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