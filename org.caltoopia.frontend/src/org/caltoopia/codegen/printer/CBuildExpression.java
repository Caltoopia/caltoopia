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
import java.util.Map;

import org.caltoopia.ast2ir.Stream;
import org.caltoopia.ast2ir.Util;
import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.cli.DirectoryException;
import org.caltoopia.codegen.CodegenError;
import org.caltoopia.codegen.UtilIR;
import org.caltoopia.codegen.transformer.IrTransformer;
import org.caltoopia.codegen.transformer.TransUtil;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarAccess;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarType;
import org.caltoopia.ir.BinaryExpression;
import org.caltoopia.ir.BooleanLiteral;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.FloatLiteral;
import org.caltoopia.ir.ForwardDeclaration;
import org.caltoopia.ir.FunctionCall;
import org.caltoopia.ir.IfExpression;
import org.caltoopia.ir.IntegerLiteral;
import org.caltoopia.ir.ListExpression;
import org.caltoopia.ir.Member;
import org.caltoopia.ir.Namespace;
import org.caltoopia.ir.StringLiteral;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeConstructorCall;
import org.caltoopia.ir.TypeList;
import org.caltoopia.ir.TypeUndef;
import org.caltoopia.ir.UnaryExpression;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.ir.VariableExternal;
import org.caltoopia.ir.VariableImport;
import org.caltoopia.ir.VariableReference;
import org.caltoopia.ir.util.IrSwitch;
import org.eclipse.emf.ecore.EObject;

public class CBuildExpression extends IrSwitch<Boolean> {
    String exprStr="";
    Expression expression = null;
    boolean rangechk = false; //TODO be able to control this
    public CBuildExpression(Expression expression) {
        exprStr="";
        this.expression = expression;
    }
    
    public String toStr() {
        Boolean res = doSwitch(expression);
        if(!res) {
            CodegenError.err("Expression builder", exprStr);
        }
        return exprStr;
    }

    private void enter(EObject obj) {}
    private void leave() {}

    @Override
    public Boolean caseIntegerLiteral(IntegerLiteral lit) {
        enter(lit);
        exprStr += (Long.toString(lit.getValue()));
        leave();
        return true;
    }

    @Override
    public Boolean caseFloatLiteral(FloatLiteral lit) {
        enter(lit);
        exprStr += (Double.toString(lit.getValue()));
        leave();
        return true;
    }

    @Override
    public Boolean caseBooleanLiteral(BooleanLiteral lit) {
        enter(lit);
        exprStr += (lit.isValue()?"1":"0");
        leave();
        return true;
    }

    @Override
    public Boolean caseStringLiteral(StringLiteral lit) {
        enter(lit);
        exprStr += ("\"" + lit.getValue() + "\"");
        leave();
        return true;
    }

    @Override
    public Boolean caseListExpression(ListExpression lit) {
        enter(lit);
        exprStr += ("{");
        for (Iterator<Expression> i = lit.getExpressions().iterator(); i.hasNext();) {
            Expression l = i.next();            
            doSwitch(l);
            if (i.hasNext()) exprStr += ", ";
        }
        if(!lit.getGenerators().isEmpty()) {
            exprStr = "/* Don't know what to do with a generator yet "+ lit.toString() +"*/";
            leave();
            return false;
        }
        exprStr += ("}");
        leave();
        return true;
    }

    @Override
    public Boolean caseVariableExpression(VariableExpression var) {
        enter(var);
        VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(var, IrTransformer.VARIABLE_ANNOTATION, "VarType"));

        boolean constant = false;
        switch(varType) {
        case actorVar:
            exprStr += ("thisActor->");
            break;
        case importConstVar:
        case constVar:
            exprStr += TransUtil.getNamespaceAnnotation(var) + "__";
            constant = true;
            break;
        default:
        }
        exprStr += (CPrintUtil.validCName(var.getVariable().getName()));
        if (!var.getIndex().isEmpty()) {
            Type list = ((Variable)var.getVariable()).getType();
            for (Iterator<Expression> i = var.getIndex().iterator(); i.hasNext();) {
                Expression e = i.next();
                exprStr += ("[");
                if(rangechk) {
                    exprStr += ("RANGECHK(");
                }
                doSwitch(e);
                if(rangechk) {
                    exprStr += (",");
                    doSwitch(((TypeList)list).getSize());
                    exprStr += (")");
                    list = ((TypeList)list).getType();
                }
                exprStr += ("]");
            }
        }

        boolean hasIndex=var.getIndex().isEmpty()?false:true;
        for (Member m : var.getMember()) {
            exprStr += ((constant||hasIndex)?".":"->");
            doSwitch(m);
            hasIndex = m.getIndex().isEmpty()?false:true;
        }

        leave();
        return true;
    }
    
    @Override
    public Boolean caseVariableReference(VariableReference var) {
        enter(var);
        VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(var, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
        VarAccess varAccess = VarAccess.valueOf(TransUtil.getAnnotationArg(var, IrTransformer.VARIABLE_ANNOTATION, "VarAccess"));
        String typeUsage = TransUtil.getAnnotationArg(var, IrTransformer.TYPE_ANNOTATION, "TypeUsage");
        exprStr +=("/* VR " +
                varType.name() +", " +
                varAccess.name() +", " +
                typeUsage +" */");
        /*
        boolean constant = false;
        if(actorStructDecl.contains(var.getDeclaration().getId())) {
            exprStr += ("thisActor->");
        } else if(var.getDeclaration() instanceof VariableImport) {
            exprStr += (Util.marshallQualifiedName(((VariableImport)var.getDeclaration()).getNamespace())+"_");
            constant=true;
        }
        exprStr += (validCName(var.getDeclaration().getName()));
        if (!var.getIndex().isEmpty()) {
            Type list = var.getDeclaration().getType();
            for (Iterator<Expression> i = var.getIndex().iterator(); i.hasNext();) {
                Expression e = i.next();
                exprStr += ("[");
                if(rangechk) {
                    exprStr += ("RANGECHK(");
                }
                doSwitch(e);
                if(rangechk) {
                    exprStr += (",");
                    doSwitch(((TypeList)list).getSize());
                    exprStr += (")");
                    list = ((TypeList)list).getType();
                }
                exprStr += ("]");
            }
        }
        boolean hasIndex=var.getIndex().isEmpty()?false:true;
        for (Member m : var.getMember()) {
            //s.print(hasIndex?".":"->");
            exprStr += ((constant||hasIndex)?".":"->");
            doSwitch(m);
            hasIndex = m.getIndex().isEmpty()?false:true;
        }
        */
        leave();
        return true;
    }

    @Override
    public Boolean caseMember(Member member) {
        enter(member);
        exprStr += (CPrintUtil.validCName(member.getName()));
        if (!member.getIndex().isEmpty()) {
            Type list = member.getType();
            for (Iterator<Expression> i = member.getIndex().iterator(); i.hasNext();) {
                Expression e = i.next();
                exprStr += ("[");
                if(rangechk) {
                    exprStr += ("RANGECHK(");
                }
                doSwitch(e);
                if(rangechk) {
                    exprStr += (",");
                    doSwitch(((TypeList)list).getSize());
                    exprStr += (")");
                    list = ((TypeList)list).getType();
                }
                exprStr += ("]");
            }
        }
        leave();
        return true;
    }

    @Override
    public Boolean caseBinaryExpression(BinaryExpression expr) {
        // Ugly hack starts
        // This is a solution JUST to get the test examples compiling
        // The '..' operator shall be not be around at this stage, 
        // but for that we need some dead code removal to clean it up.
        // So for, now we just print 'null'
        /*
        if (expr.getOperator().equals("..")) {
            exprStr += ("0");
            return true;
        }
        */
        // Ugly ends
        
        enter(expr);
        exprStr += "(";
        doSwitch(expr.getOperand1());
        exprStr += " ";
        printOperator(expr.getOperator());
        exprStr += " ";
        doSwitch(expr.getOperand2());
        exprStr += ")";
        leave();
        return true;
    }

    private void printOperator(String operator) {
        if (operator.equals("=")) {
            exprStr += ("==");
        } else if (operator.equals(":=")) {
            exprStr += ("=");
        } else if (operator.equals("and")) {
            exprStr += ("&&");
        } else if (operator.equals("or")) {
            exprStr += ("||");
        } else if (operator.equals("bitand")) {
            exprStr += ("&");
        } else if (operator.equals("bitor")) {
            exprStr += ("|");
        } else if (operator.equals("not")) {
            exprStr += ("!");
        } else if (operator.equals("mod")) {
            exprStr += ("%");
        } else if (operator.equals(">>>")) {
            exprStr += (">>");
        } else {
            exprStr += (operator);
        }           
        
    }

    @Override
    public Boolean caseUnaryExpression(UnaryExpression expr) {
        enter(expr);
        exprStr += "(";
        String operator = expr.getOperator();
        if (operator.equals("not")) {
            exprStr += ("!");
        } else if (operator.equals("#")) {
            exprStr += ("sizeof");
        } else if (operator.equals("old")) {
            exprStr += ("/* Usage of unary operator old is not supported! */");
            leave();
            return false;
        } else {
            exprStr += (operator);
        }           
        exprStr += "(";
        doSwitch(expr.getOperand());
        exprStr += "))";
        
        leave();
        return true;
    }

    @Override
    public Boolean caseFunctionCall(FunctionCall expr) {
        enter(expr);
        VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(expr, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
        VarAccess varAccess = VarAccess.valueOf(TransUtil.getAnnotationArg(expr, IrTransformer.VARIABLE_ANNOTATION, "VarAccess"));
        String typeUsage = TransUtil.getAnnotationArg(expr, IrTransformer.TYPE_ANNOTATION, "TypeUsage");
        exprStr +=("/* FC " +
                varType.name() +", " +
                varAccess.name() +", " +
                typeUsage +" */");
        /*
        String id="";
        if(expr.getFunction() instanceof VariableExpression && ((VariableExpression)expr.getFunction()).getVariable() instanceof Variable) {
            Variable func = (Variable) ((VariableExpression) expr.getFunction()).getVariable();
            id = func.getId();
            exprStr += (thisStr + validCName(func.getName()));
        } else if(expr.getFunction() instanceof VariableExpression && ((VariableExpression)expr.getFunction()).getVariable() instanceof VariableExternal) {
            VariableExternal func = (VariableExternal) ((VariableExpression) expr.getFunction()).getVariable();
            id = func.getId();
            exprStr += (validCName(func.getName()));
        } else if(expr.getFunction() instanceof VariableExpression && ((VariableExpression)expr.getFunction()).getVariable() instanceof VariableImport) {           
            VariableImport func = (VariableImport) ((VariableExpression) expr.getFunction()).getVariable();
            Declaration f = null;
            try {
                f = ActorDirectory.findVariable(func);
            } catch (DirectoryException e) {
                System.err.println("[CPrinter] Could not find imported function " + func.getName());
            }
            if(f instanceof VariableExternal) {
                VariableExternal e = (VariableExternal) f;
                id = e.getId();
                Namespace ns = null;
                try {
                    ns = ActorDirectory.findNamespace(func.getNamespace());
                } catch (DirectoryException ee) {}
                Map<String,String> annotations = getExternAnnotations(collectAnnotations(e,ns));
                exprStr += (externalCName(annotations,e));
                toEnv(annotations);
            } else {
                id = func.getId();
                exprStr += (validCName(func.getName()));
            }
        } else if(expr.getFunction() instanceof VariableExpression && ((VariableExpression)expr.getFunction()).getVariable() instanceof ForwardDeclaration) {
            ForwardDeclaration func = (ForwardDeclaration) ((VariableExpression) expr.getFunction()).getVariable();
            id = func.getId();
            exprStr += (thisStr + validCName(func.getName()));
        } else {
            exprStr += ("WHAT_IS_MY_NAME!??");
            throw new RuntimeException("Expected function decl in call to be of Variable type");
        }
        s.printLeft();
        if(actorFuncDecl.contains(id)) {
            exprStr += ("thisActor");
            if(!expr.getParameters().isEmpty())
                exprStr += (", ");
        }
        for (int i = 0; i<expr.getParameters().size(); i++) {
            Expression p = expr.getParameters().get(i);
            doSwitch(p);
            if (i<expr.getParameters().size()-1) s.printComma();
        }
        s.printRight();
        */
        leave();
        return true;
    }

    @Override
    public Boolean caseTypeConstructorCall(TypeConstructorCall expr) {
        enter(expr);
        VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(expr, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
        VarAccess varAccess = VarAccess.valueOf(TransUtil.getAnnotationArg(expr, IrTransformer.VARIABLE_ANNOTATION, "VarAccess"));
        String typeUsage = TransUtil.getAnnotationArg(expr, IrTransformer.TYPE_ANNOTATION, "TypeUsage");
        exprStr +=("/* TC " +
                varType.name() +", " +
                varAccess.name() +", " +
                typeUsage +" */");

        if(UtilIR.isLiteralExpression(expr)) {
            exprStr += ("{");
            for(Iterator<Expression> i= ((TypeConstructorCall) expr).getParameters().iterator();i.hasNext();) {
                Expression e = i.next();
                doSwitch(e);
                if(i.hasNext()) exprStr += ", ";
            }
            exprStr += ("}");               
        } else {
            exprStr += (CPrintUtil.validCName(expr.getName()));
            exprStr += ("(NULL, ");
            for (Iterator<Expression> i = expr.getParameters().iterator(); i.hasNext();) {
                Expression p = i.next();
                doSwitch(p);
                if (i.hasNext()) exprStr += ", ";
            }
            exprStr += ")";
        }
        leave();
        return true;
    }

    @Override
    public Boolean caseIfExpression(IfExpression expr) {
        enter(expr);
        exprStr += ("((");
        doSwitch(expr.getCondition());
        exprStr += (") ? (");
        doSwitch(expr.getThenExpression());
        exprStr += ("):(");
        doSwitch(expr.getElseExpression());
        exprStr += ("))");
        leave();
        return true;
    }

    @Override
    public Boolean caseExpression(Expression object) {
        enter(object);
        //Generic Expression is used as a special case to print the type of the expression or NULL
        //This is used for example when using sizeof() or any c-function that takes a pointer but we have nothing to supply
        if(object.getType() instanceof TypeUndef)
            exprStr += ("NULL");
        else {
            int i=0;
            Type type = object.getType();
            while(type instanceof TypeList) {
                type = ((TypeList) type).getType();
                i++;
            }
            exprStr += new CBuildTypeName(type,null).toStr();
            for(;i>0;i--)
                exprStr += ("*");
        }
        leave();
        return true;
    }

}
