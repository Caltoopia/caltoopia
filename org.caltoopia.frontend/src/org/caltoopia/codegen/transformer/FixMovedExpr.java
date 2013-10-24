package org.caltoopia.codegen.transformer;

import org.caltoopia.ir.BinaryExpression;
import org.caltoopia.ir.Block;
import org.caltoopia.ir.BooleanLiteral;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.FloatLiteral;
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
import org.caltoopia.ir.TypeConstructorCall;
import org.caltoopia.ir.UnaryExpression;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.ir.util.IrReplaceSwitch;
import org.eclipse.emf.ecore.EObject;


public class FixMovedExpr extends IrReplaceSwitch {
    Scope newScope = null;
    Node oldScope = null;
    boolean skipTop = true;
    
    public FixMovedExpr(Scope newScope, Node oldScope, boolean skipTop) {
        this.newScope = newScope;
        this.oldScope = oldScope;
        this.skipTop = skipTop;
    }

    public static void moveScope(Node node, Scope newScope, Node oldScope, boolean skipTop) {
        new FixMovedExpr(newScope, oldScope, skipTop).doSwitch(node);
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
}