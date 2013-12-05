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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.caltoopia.ast2ir.Stream;
import org.caltoopia.ast2ir.Util;
import org.caltoopia.ast2ir.Stream.Indent;
import org.caltoopia.codegen.CEnvironment;
import org.caltoopia.codegen.CodegenError;
import org.caltoopia.codegen.UtilIR;
import org.caltoopia.codegen.printer.CBuildVarDeclaration.varCB;
import org.caltoopia.codegen.printer.CPrintUtil.dummyCB;
import org.caltoopia.codegen.transformer.IrTransformer;
import org.caltoopia.codegen.transformer.TransUtil;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarAccess;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarType;
import org.caltoopia.ir.Action;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Block;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.Guard;
import org.caltoopia.ir.LambdaExpression;
import org.caltoopia.ir.PortPeek;
import org.caltoopia.ir.PortRead;
import org.caltoopia.ir.PortWrite;
import org.caltoopia.ir.ProcExpression;
import org.caltoopia.ir.ReturnValue;
import org.caltoopia.ir.Statement;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.TypeConstructorCall;
import org.caltoopia.ir.TypeLambda;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableReference;
import org.caltoopia.ir.util.IrSwitch;
import org.eclipse.emf.ecore.EObject;

/*
 * This class generates a string containing a guard declaration
 * for an action.
 * 
 * Quality: 5, should work
 */
public class CBuildGuard extends IrSwitch<Boolean> {
    String bodyStr="";
    String thisStr="";
    int idNbr=0;
    Action action;
    boolean header = false;
    CEnvironment cenv = null;
    private IndentStr ind = null;

    /*
     * Constructor for building a long string containing the 
     * c-code of an action's guards. The guards are printed as
     * one c function with all the action's guards (comma separated).
     * Which is called from the action scheduler.
     * 
     * action: action's guards to be printed
     * cenv: input/output variable collecting information that is 
     *       needed in makefiles etc, same object used for all CBuilders
     * thisStr: actor prefix string for the guards
     * idNbr: index number in list of actions
     */
    public CBuildGuard(Action action, CEnvironment cenv, String thisStr, int idNbr) {
        bodyStr="";
        this.thisStr = thisStr;
        this.idNbr = idNbr;
        this.action = action;
        this.cenv = cenv;
        this.ind = new IndentStr();
    }
    
    /*
     * Do the actual generation of the guard string, use as:
     * new CBuildGuard(...).toStr()
     */
    public String toStr() {
        Boolean res = doSwitch(action);
        if(!res) {
            CodegenError.err("Guard builder", bodyStr);
        }
        return bodyStr;
    }
    
    private void enter(EObject obj) {}
    private void leave() {}

    @Override
    public Boolean caseAction(Action action) {
        enter(action);
        if(!action.getGuards().isEmpty()) {
            //Name the guards as Guard_ actor prefix __ action tag
            //context contains the port info
            String actionId = thisStr + CPrintUtil.getNamespace(action.getTag());
            bodyStr += ind.ind() + "static int Guard_" + actionId + "__" + action.getId()+ "(art_action_context_t *context, ActorInstance_"+thisStr+" *thisActor)" + ind.nl();
            bodyStr += ind.ind() + ("{") + ind.nl();
            ind.inc();
            bodyStr += ind.ind() + "void * __tempVoidPointer;" +ind.nl();
            bodyStr += ind.ind() + "__array4void __tempArray;" +ind.nl();
            Set<String> printed = new HashSet<String>();
            for (Guard g: action.getGuards()) {
                for (Declaration d : g.getDeclarations()) {
                    //Only print the constants and peek variable declarations
                    VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(d, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
                    switch(varType) {
                    //FIXME Are these consts needed?
                    case constVar:
                    case actorConstVar:
                        bodyStr += ind.ind() + (new CBuildConstDeclaration((Variable) d, cenv,false).toStr()) + ";" + ind.nl();
                        break;
                    case peekVar:
                    case inPortPeekVar:
                    case inOutPortPeekVar:
                        if(!printed.contains(d.getName())) {
                            printed.add(d.getName());
                            bodyStr += ind.ind() + (new CBuildVarDeclaration((Variable) d,cenv, false).toStr()) + ";" + ind.nl();
                        }
                        break;
                    default:
                        VarAccess varAccess = VarAccess.valueOf(TransUtil.getAnnotationArg(d, IrTransformer.VARIABLE_ANNOTATION, "VarAccess"));
                        String typeUsage = TransUtil.getAnnotationArg(d, IrTransformer.TYPE_ANNOTATION, "TypeUsage");
                        String varStr =(varType.name() +", " +
                                varAccess.name() +", " +
                                typeUsage);
                        bodyStr += ind.ind() + ("/*TODO BGD "+d.getName() + ", " + varStr + " */") +ind.nl();
                    } 
                }
            }
            Set<String> peeked = new HashSet<String>();
            for (Guard g: action.getGuards()) {
                for (PortPeek peek: g.getPeeks()){
                    String portNbr = TransUtil.getAnnotationArg(peek, "Port", "index");
                    String portStr = "IN" + portNbr+ "_" + TransUtil.getAnnotationArg(peek, "Port", "name");
                    VariableReference peekVar = peek.getVariable();
                    if(!peeked.contains(peekVar.getDeclaration().getName())) {
                        //Only peek into FIFO once for each variable/port
                        peeked.add(peekVar.getDeclaration().getName());
                        if(peek.getRepeat()==null) {
                            bodyStr += ind.ind() + new CBuildVarReference(peekVar, cenv).toStr() + " = ";
                            bodyStr += "pinPeek_" + new CBuildTypeName(peek.getPort().getType(), new CPrintUtil.dummyCB(), false).asNameStr();
                            bodyStr += "(" + portStr + ", " + peek.getPosition() + ");"+ind.nl();
                        } else {
                            //TODO optimize by only reading in the indices that are used
                            int sz = g.getPeeks().size();
                            bodyStr += ind.ind() + "{" + ind.nl();
                            ind.inc();
                            /*
                             * When peeking and have repeat and potentially several variable references
                             * we must make sure to interleave the peeking correctly. 
                             * 
                             * action In:[a,b,c] repeat 5
                             * guard a[0]>c[2]>b[4]
                             * 
                             * 
                             * {int __tempIN0_InCount, __tempIN0_In = 5;
                             * for(__tempIN0_InCount=0;__tempIN0_InCount<__tempIN0_In;__tempIN0_InCount++) {
                             *   a[__tempIN0_InCount]= pinPeek_T(IN0_In, 3 * __tempIN0_InCount + 0);
                             * }}
                             * {int __tempIN0_InCount, __tempIN0_In = 5;
                             * for(__tempIN0_InCount=0;__tempIN0_InCount<__tempIN0_In;__tempIN0_InCount++) {
                             *   c[__tempIN0_InCount]= pinPeek_T(IN0_In, 3 * __tempIN0_InCount + 2);
                             * }}
                             * {int __tempIN0_InCount, __tempIN0_In = 5;
                             * for(__tempIN0_InCount=0;__tempIN0_InCount<__tempIN0_In;__tempIN0_InCount++) {
                             *   b[__tempIN0_InCount]= pinPeek_T(IN0_In, 3 * __tempIN0_InCount + 1);
                             * }}
                             * The number 3 comes from the 3 variables, the + 1, 2, 0 comes from the position of a, c and b
                             */
                            String repStr = "__temp" + CPrintUtil.validCName(portStr);
                            bodyStr += ind.ind() + "int " + repStr + "Count, " + repStr + " = " + 
                                    new CBuildExpression(peek.getRepeat(),cenv).toStr() + ";" + ind.nl();
                            bodyStr += ind.ind() + "for(" + repStr + "Count = 0;" + repStr + "Count<" + repStr + "; "+repStr+"Count++) {" + ind.nl(); 
                            ind.inc();
                            bodyStr += ind.ind() + new CBuildVarReference(peekVar, cenv, false, true).toStr() + ".p["+ repStr +"Count] = ";
                            bodyStr += "pinPeek_" + new CBuildTypeName(peek.getPort().getType(), new CPrintUtil.dummyCB(), false).asNameStr();
                            bodyStr += "(" + portStr + ", " + sz +" * "+ repStr + "Count + " + peek.getPosition() + ");" + ind.nl();
                            ind.dec();
                            bodyStr += ind.ind() + "}" + ind.nl();
                            ind.dec();
                            bodyStr += ind.ind() + "}" + ind.nl();
                        }
                    }
                }
            }
            bodyStr += ind.ind() + "int ret = " +ind.nl();
            ind.inc();
            //And together the individual boolean guard expressions
            for (Iterator<Guard> i = action.getGuards().iterator();i.hasNext();) {
                Expression e = i.next().getExpression();
                bodyStr += ind.ind() + new CBuildExpression(e, cenv).toStr();
                if(i.hasNext()) {
                    bodyStr += " &&"+ ind.nl();
                } else {
                    bodyStr += ";"+ ind.nl();
                }
            }
            ind.dec();
            bodyStr += ind.ind() + "return ret;" + ind.nl();
            ind.dec();
            bodyStr += ind.ind() + "}" + ind.nl();
        }
        leave();
        return true;
    }

}
