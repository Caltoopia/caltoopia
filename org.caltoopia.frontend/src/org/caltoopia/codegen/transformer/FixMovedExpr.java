package org.caltoopia.codegen.transformer;

import org.caltoopia.ir.BinaryExpression;
import org.caltoopia.ir.Block;
import org.caltoopia.ir.BooleanLiteral;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.FloatLiteral;
import org.caltoopia.ir.ForwardDeclaration;
import org.caltoopia.ir.FunctionCall;
import org.caltoopia.ir.Generator;
import org.caltoopia.ir.IfExpression;
import org.caltoopia.ir.IntegerLiteral;
import org.caltoopia.ir.LambdaExpression;
import org.caltoopia.ir.ListExpression;
import org.caltoopia.ir.Member;
import org.caltoopia.ir.Node;
import org.caltoopia.ir.ProcExpression;
import org.caltoopia.ir.Scope;
import org.caltoopia.ir.StringLiteral;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeConstructorCall;
import org.caltoopia.ir.UnaryExpression;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.ir.VariableExternal;
import org.caltoopia.ir.VariableImport;
import org.caltoopia.ir.util.IrReplaceSwitch;
import org.eclipse.emf.ecore.EObject;

/*
 * This class is used when moving an expression or a declaration.
 * Expressions and Declarations refer to their containing context 
 * or scope. When moved such reference needs to be updated to refer
 * to the new context or scope. Expressions or Declarations are
 * commonly nested and a scope reference could be buried deep down.
 * 
 * Quality: 4, works for all current uses and tests
 */
public class FixMovedExpr extends IrReplaceSwitch {
    Scope newScope = null;
    Node oldScope = null;
    boolean skipTop = true;
    boolean skipDecl = true;
    
    /*
     * Constructor of class, use new FixMovedExpr(...).doSwitch(node)
     * newScope: the new context or scope to replace with
     * oldScope: the previous context or scope to search for
     * skipTop: skip blocks and scope useful when converting a whole Block but don't want to effect the Block itself (i.e. created a new Block inside the old then we should refer to the old block as context)
     * skipDecl: when using this on expressions these may contain variable declarations and if their scope should not be replaced (which is typical) set this to true
     */
    public FixMovedExpr(Scope newScope, Node oldScope, boolean skipTop, boolean skipDecl) {
        this.newScope = newScope;
        this.oldScope = oldScope;
        this.skipTop = skipTop;
        this.skipDecl = skipDecl;
    }

    public static void moveScope(Node node, Scope newScope, Node oldScope, boolean skipTop) {
        new FixMovedExpr(newScope, oldScope, skipTop, true).doSwitch(node);
    }
    public static void moveDeclScope(Node node, Scope newScope, Node oldScope, boolean skipTop) {
        new FixMovedExpr(newScope, oldScope, skipTop, false).doSwitch(node);
    }
    @Override
    public Scope caseScope(Scope scope) {
        super.caseScope(scope);
        if(!(skipTop && scope.getId().equals(newScope.getId()))) {
            if(scope.getOuter().getId().equals(oldScope.getId())) {
                scope.setOuter(newScope);
            }
        }
        return scope;
    }
    @Override
    public Block caseBlock(Block scope) {
        super.caseBlock(scope);
        if(!(skipTop && scope.getId().equals(newScope.getId()))) {
            if(scope.getOuter().getId().equals(oldScope.getId())) {
                scope.setOuter(newScope);
            }
        }
        return scope;
    }
    @Override
    public Expression caseExpression(Expression expr) {
        super.caseExpression(expr);
        if(expr.getContext().getId().equals(oldScope.getId())) {
            expr.setContext(newScope);
        }
        return expr;
    }
    @Override
    public Expression caseTypeConstructorCall(TypeConstructorCall expr) {
        super.caseTypeConstructorCall(expr);
        if(expr.getContext().getId().equals(oldScope.getId())) {
            expr.setContext(newScope);
        }
        return expr;
    }
    @Override
    public Expression caseIntegerLiteral(IntegerLiteral literal) {
        if(literal.getContext()!=null && literal.getContext().getId().equals(oldScope.getId())) {
            literal.setContext(newScope);
        }
        return literal;
    }
    @Override
    public Expression caseFloatLiteral(FloatLiteral literal) {
        if(literal.getContext()!=null && literal.getContext().getId().equals(oldScope.getId())) {
            literal.setContext(newScope);
        }
        return literal;
    }
    @Override
    public Expression caseBooleanLiteral(BooleanLiteral literal) {
        if(literal.getContext()!=null && literal.getContext().getId().equals(oldScope.getId())) {
            literal.setContext(newScope);
        }
        return literal;
    }
    @Override
    public Expression caseStringLiteral(StringLiteral literal) {
        if(literal.getContext()!=null && literal.getContext().getId().equals(oldScope.getId())) {
            literal.setContext(newScope);
        }
        return literal;
    }
    @Override
    public Expression caseVariableExpression(VariableExpression var) {
        super.caseVariableExpression(var);
        if(var.getContext().getId().equals(oldScope.getId())) {
            var.setContext(newScope);
        }
        return var;
    }
    @Override
    public EObject caseLambdaExpression(LambdaExpression lambda) {
        super.caseLambdaExpression(lambda);
        if(lambda.getContext().getId().equals(oldScope.getId())) {
            lambda.setContext(newScope);
        }
        return lambda;
    }
    @Override
    public EObject caseProcExpression(ProcExpression proc) {
        super.caseProcExpression(proc);
        if(proc.getContext().getId().equals(oldScope.getId())) {
            proc.setContext(newScope);
        }
        return proc;
    }
    @Override
    public EObject caseIfExpression(IfExpression expr) {
        super.caseIfExpression(expr);
        if(expr.getContext().getId().equals(oldScope.getId())) {
            expr.setContext(newScope);
        }
        return expr;
    }
    @Override
    public EObject caseListExpression(ListExpression expr) {
        super.caseListExpression(expr);
        if(expr == null || (expr!=null && expr.getContext()==null))
            return expr;
        if(expr.getType()!=null) {
            expr.setType((Type)doSwitch(expr.getType()));
        }
        if(expr.getContext().getId().equals(oldScope.getId())) {
            expr.setContext(newScope);
        }
        return expr;
    }
    @Override
    public EObject caseMember(Member member) {
        super.caseMember(member);
        return member;
    }
    @Override
    public Expression caseBinaryExpression(BinaryExpression expr) {
        super.caseBinaryExpression(expr);
        if(expr.getContext().getId().equals(oldScope.getId())) {
            expr.setContext(newScope);
        }
        return expr;
    }
    @Override
    public Expression caseUnaryExpression(UnaryExpression expr) {
        super.caseUnaryExpression(expr);
        if(expr.getContext().getId().equals(oldScope.getId())) {
            expr.setContext(newScope);
        }
        return expr;
    }
    @Override
    public Expression caseFunctionCall(FunctionCall call) {
        super.caseFunctionCall(call);
        if(call.getContext().getId().equals(oldScope.getId())) {
            call.setContext(newScope);
        }
        return call;
    }
    //---- Also declarations
    @Override
    public Declaration caseVariable(Variable variable) {
        super.caseVariable(variable);
        if(!skipDecl && variable.getScope().getId().equals(oldScope.getId())) {
            variable.setScope(newScope);
        }
        return variable;
    }
    @Override
    public Declaration caseDeclaration(Declaration variable) {
        super.caseDeclaration(variable);
        if(!skipDecl && variable.getScope().getId().equals(oldScope.getId())) {
            variable.setScope(newScope);
        }
        return variable;
    }
    @Override
    public Declaration caseForwardDeclaration(ForwardDeclaration variable) {
        super.caseForwardDeclaration(variable);
        if(!skipDecl && variable.getScope().getId().equals(oldScope.getId())) {
            variable.setScope(newScope);
        }
        return variable;
    }
    @Override
    public Declaration caseVariableExternal(VariableExternal variable) {
        super.caseVariableExternal(variable);
        if(!skipDecl && variable.getScope().getId().equals(oldScope.getId())) {
            variable.setScope(newScope);
        }
        return variable;
    }
    @Override
    public Declaration caseVariableImport(VariableImport variable) {
        super.caseVariableImport(variable);
        if(variable == null || (variable!=null && variable.getScope()==null))
            return variable;
        if(!skipDecl && variable.getScope().getId().equals(oldScope.getId())) {
            variable.setScope(newScope);
        }
        return variable;
    }
}