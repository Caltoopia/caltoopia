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

import java.util.Arrays;
import java.util.Iterator;

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
import org.caltoopia.ir.Actor;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Block;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.ForwardDeclaration;
import org.caltoopia.ir.IntegerLiteral;
import org.caltoopia.ir.LambdaExpression;
import org.caltoopia.ir.Port;
import org.caltoopia.ir.PortRead;
import org.caltoopia.ir.PortWrite;
import org.caltoopia.ir.ProcExpression;
import org.caltoopia.ir.ReturnValue;
import org.caltoopia.ir.Statement;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.TypeBool;
import org.caltoopia.ir.TypeDeclaration;
import org.caltoopia.ir.TypeDeclarationImport;
import org.caltoopia.ir.TypeExternal;
import org.caltoopia.ir.TypeFloat;
import org.caltoopia.ir.TypeInt;
import org.caltoopia.ir.TypeLambda;
import org.caltoopia.ir.TypeList;
import org.caltoopia.ir.TypeString;
import org.caltoopia.ir.TypeTuple;
import org.caltoopia.ir.TypeUint;
import org.caltoopia.ir.TypeUndef;
import org.caltoopia.ir.TypeUser;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableReference;
import org.caltoopia.ir.util.IrSwitch;
import org.eclipse.emf.ecore.EObject;
/*
 * This class generates a string for serialize or deserialize an
 * actor state.
 * 
 */
public class CBuildActorStateSerialize extends IrSwitch<Boolean> {
    String stateStr="";
    String thisStr="";
    CEnvironment cenv = null;
    private IndentStr ind = null;
    private Actor actor;
    private boolean serialize;
    private String actorId;
    String typeStr="";
    String pointerStr="";
    ITypeCallbacks cb=null;
    int dim=0;
    boolean array;
    boolean tag = false;

    /*
     * Constructor for building a string containing the 
     * serialization or deserialization of actor state.
     * 
     * actor: actor to be (de)serialized
     * serialize: true for serialize otherwise deserialize
     * cenv: input/output variable collecting information that is 
     *       needed in makefiles etc, same object used for all CBuilders
     * ind: indentation object, passed in so that sub-parts maintains overall indentation level
     * thisStr: actor prefix string for the action tag
     */
    public CBuildActorStateSerialize(Actor actor, boolean serialize, CEnvironment cenv, IndentStr ind, String thisStr) {
        stateStr="";
        this.thisStr = thisStr;
        this.actor = actor;
        this.serialize = serialize;
        this.cenv = cenv;
        if(ind == null) {
            this.ind = new IndentStr();
        } else {
            this.ind = ind;
        }
        this.actorId = "ActorInstance_" + thisStr;
        this.cb = new CPrintUtil.dummyCB();

    }
    
    /*
     * Do the actual generation of the serialization string, use as:
     * new CBuildActorStateSerialize(...).toStr()
     */
    public String toStr() {
        Boolean res = doSwitch(actor);
        if(!res) {
            CodegenError.err("Actor State Serialize builder", stateStr);
        }
        return stateStr;
    }
    
    private void enter(EObject obj) {}
    private void leave() {}

    @Override
    public Boolean caseActor(Actor actor) {
        /* 
         * Print serialize and deserialize of actor state functions.
         */
        if(serialize) {
            stateStr += ind.ind() + "//Actor state serialie and deserialize" + ind.nl();
            stateStr += ind.ind() +"static void " + actorId + "_serialize(AbstractActorInstance *actor, ActorCoder *coder)" + ind.nl();
            stateStr += ind.ind() +"{" + ind.nl();
            ind.inc();
            stateStr += ind.ind() + actorId + " *this = (" + actorId + " *)actor;" + ind.nl();
            stateStr += ind.ind() + "const ActorClass *actorClass = actor->actorClass;" + ind.nl();
            stateStr += ind.ind() + "CoderState *state = coder->init(coder);" + ind.nl();
            stateStr += ind.ind() + "coder->encode(coder, state, \"class\", (void *)&actorClass->name, \"s\");" + ind.nl();
            stateStr += ind.ind() + "coder->encode(coder, state, \"_fsmState\", &this->_fsmState, \"i\");" + ind.nl();
            stateStr += ind.ind() + "coder->encode(coder, state, \"_initializers_have_run\", &this->_initializers_have_run, \"i\");" + ind.nl();
            /***** Make sure struct definition, serialize and deserialize is kept in sync ******/
            for (Declaration d : actor.getDeclarations()) {
                VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(d, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
                switch(varType) {
                case actorVar:
                case actorParamVar:
                    String name = CPrintUtil.validCName(d.getName());
                    stateStr += ind.ind() + "coder->encode(coder, state, \"" + name + "\", &this->"+ name + ", \"";
                    doSwitch(((Variable)d).getType());
                    stateStr += "\");" + ind.nl();
                    break;
                //These are known to not be printed here, makes sure that the default is only called for things that miss code (besides the type import or forward)
                case actorNonLitConstVar: //already handled in constructor
                case actorConstParamVar:
                case constVar:
                case actorConstVar:
                case proc:
                case func:
                case actorFunc:
                    break;
                default:
                    stateStr += ind.ind() +("/*TODO SD " + d.getName() + " of varType " + varType.name() + " and " + ((d instanceof ForwardDeclaration)?"forward declaration":(d instanceof TypeDeclarationImport)?"type import":"NOT anticipated") + " */");
                }
            }
            if(!actor.getOutputPorts().isEmpty()) {
                stateStr += ind.ind() + "CoderState *ports = coder->encode_struct(coder, state, \"outports\");" + ind.nl();
                stateStr += ind.ind() + "CoderState *port;" + ind.nl();
                stateStr += ind.ind() + "CoderState *buffer;" + ind.nl();
                stateStr += ind.ind() + "OutputPort *output;" + ind.nl();
                stateStr += ind.ind() + "InputPort *consumer;" + ind.nl();
                stateStr += ind.ind() + "int count;" + ind.nl();
                int i=0;
                for(Port port: actor.getOutputPorts()) {
                    stateStr += ind.ind() + "port = coder->encode_struct(coder, ports, \""+port.getName() + "\");" + ind.nl();
                    stateStr += ind.ind() + "buffer = coder->encode_array(coder, port, \"buffer\");" + ind.nl();
                    stateStr += ind.ind() + "output = output_port_array_get(actor->outputPort, "+i+");" + ind.nl();
                    stateStr += ind.ind() + "consumer = output_port_first_consumer(output);" + ind.nl();
                    stateStr += ind.ind() + "if (!consumer) m_critical(\"No consumer connected\");" + ind.nl();
                    String typeStr = new CBuildTypeName(port.getType(),new CPrintUtil.dummyCB(),false).toStr();
                    stateStr += ind.ind() + typeStr + "* " + port.getName() + "_end = (" + typeStr + "*)output_port_buffer_end(output);" + ind.nl();
                    stateStr += ind.ind() + typeStr + "* " + port.getName() + "_write = (" + typeStr + "*)output_port_write_ptr(output);" + ind.nl();
                    stateStr += ind.ind() + typeStr + "* " + port.getName() + "_read = (" + typeStr + "*)input_port_read_ptr(consumer);" + ind.nl();
                    stateStr += ind.ind() + "count = 0;" + ind.nl();
                    stateStr += ind.ind() + "while(" +  port.getName() + "_read != " + port.getName() +"_write) {" + ind.nl();
                    ind.inc();
                    stateStr += ind.ind() + "coder->encode(coder, buffer, NULL, " + port.getName() + "_read, \"";
                    doSwitch(port.getType());
                    stateStr += "\");" + ind.nl();
                    stateStr += ind.ind() + "count++;" + ind.nl();
                    stateStr += ind.ind() + port.getName() + "_read++;" + ind.nl();
                    stateStr += ind.ind() + "if (" + port.getName() + "_read >= " + port.getName() + "_end) {" + ind.nl();
                    ind.inc();
                    stateStr += ind.ind() + port.getName() + "_read = (" + typeStr + " *)output_port_buffer_start(output);" + ind.nl();
                    ind.dec();
                    stateStr += ind.ind() + "}" + ind.nl();
                    ind.dec();
                    stateStr += ind.ind() + "}" + ind.nl();
                    stateStr += ind.ind() + "coder->encode(coder, port, \"length\", &count, \"i\");" + ind.nl();
                    i++;
                }
            }
            ind.dec();
            stateStr += ind.ind() + "}" + ind.nl();
        } else {
            stateStr += ind.ind() +"static void " + actorId + "_deserialize(AbstractActorInstance *actor, ActorCoder *coder)" + ind.nl();
            stateStr += ind.ind() +"{" + ind.nl();
            ind.inc();
            stateStr += ind.ind() + actorId + " *this = (" + actorId + " *)actor;" + ind.nl();
            //stateStr += ind.ind() + "const ActorClass *actorClass = actor->actorClass;" + ind.nl();
            stateStr += ind.ind() + "CoderState *state = coder->init(coder);" + ind.nl();
            stateStr += ind.ind() + "coder->decode(coder, state, \"_fsmState\", (void*)&this->_fsmState, \"i\");" + ind.nl();
            stateStr += ind.ind() + "coder->decode(coder, state, \"_initializers_have_run\", (void*)&this->_initializers_have_run, \"i\");" + ind.nl();
            /***** Make sure struct definition, serialize and deserialize is kept in sync ******/
            for (Declaration d : actor.getDeclarations()) {
                VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(d, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
                switch(varType) {
                case actorVar:
                case actorParamVar:
                    String name = CPrintUtil.validCName(d.getName());
                    stateStr += ind.ind() + "coder->decode(coder, state, \"" + name + "\", &this->"+ name + ", \"";
                    doSwitch(((Variable)d).getType());
                    stateStr += "\");" + ind.nl();
                    break;
                //These are known to not be printed here, makes sure that the default is only called for things that miss code (besides the type import or forward)
                case actorNonLitConstVar: //already handled in constructor
                case actorConstParamVar:
                case constVar:
                case actorConstVar:
                case proc:
                case func:
                case actorFunc:
                    break;
                default:
                    stateStr += ind.ind() +("/*TODO SD " + d.getName() + " of varType " + varType.name() + " and " + ((d instanceof ForwardDeclaration)?"forward declaration":(d instanceof TypeDeclarationImport)?"type import":"NOT anticipated") + " */");
                }
            }
            if(!actor.getOutputPorts().isEmpty()) {
                stateStr += ind.ind() + "CoderState *ports = coder->decode_struct(coder, state, \"outports\");" + ind.nl();
                stateStr += ind.ind() + "CoderState *port;" + ind.nl();
                stateStr += ind.ind() + "CoderState *buffer;" + ind.nl();
                stateStr += ind.ind() + "OutputPort *output;" + ind.nl();
                stateStr += ind.ind() + "int count,len;" + ind.nl();
                int i=0;
                for(Port port: actor.getOutputPorts()) {
                    stateStr += ind.ind() + "port = coder->decode_struct(coder, ports, \""+port.getName() + "\");" + ind.nl();
                    stateStr += ind.ind() + "buffer = coder->decode_array(coder, port, \"buffer\");" + ind.nl();
                    stateStr += ind.ind() + "coder->decode(coder, port, \"length\", &len, \"i\");" + ind.nl();
                    stateStr += ind.ind() + "output = output_port_array_get(actor->outputPort, "+i+");" + ind.nl();
                    String typeStr = new CBuildTypeName(port.getType(),new CPrintUtil.dummyCB(),false).toStr();
                    stateStr += ind.ind() + typeStr + "* " + port.getName() + "_end = (" + typeStr + "*)output_port_buffer_end(output);" + ind.nl();
                    stateStr += ind.ind() + typeStr + "* " + port.getName() + "_write = (" + typeStr + "*)output_port_write_ptr(output);" + ind.nl();
                    stateStr += ind.ind() + "for(count=0;count<len;count++) {" + ind.nl();
                    ind.inc();
                    stateStr += ind.ind() + "coder->decode(coder, buffer, NULL, " + port.getName() + "_write, \"";
                    doSwitch(port.getType());
                    stateStr += "\");" + ind.nl();
                    stateStr += ind.ind() + port.getName() + "_write++;" + ind.nl();
                    stateStr += ind.ind() + "if (" + port.getName() + "_write >= " + port.getName() + "_end) {" + ind.nl();
                    ind.inc();
                    stateStr += ind.ind() + port.getName() + "_write = (" + typeStr + " *)output_port_buffer_start(output);" + ind.nl();
                    ind.dec();
                    stateStr += ind.ind() + "}" + ind.nl();
                    ind.dec();
                    stateStr += ind.ind() + "}" + ind.nl();
                    stateStr += ind.ind() + "output_port_set_write_ptr(output, "+ port.getName() + "_write);" + ind.nl();
                    i++;
                }
            }
            ind.dec();
            stateStr += ind.ind() + "}" + ind.nl();
        }
        return true;
    }

    @Override
    public Boolean caseTypeUser(TypeUser type) {
        enter(type);
        stateStr += "?FIXME";
        leave();
        return true;
    }
    
    @Override
    public Boolean caseTypeBool(TypeBool type) {
        enter(type);
        stateStr += "b";
        leave();
        return true;
    }

    @Override
    public Boolean caseTypeInt(TypeInt type) {
        enter(type);
        int sz = (int) (type.getSize() instanceof IntegerLiteral ? ((IntegerLiteral)type.getSize()).getValue() : (type.getSize()==null ? -1:-2));
        switch(sz) {
        case 32:
        case -1:
            //When no size specified use 32 bit
            stateStr += "i";
            break;
        case 16:
            stateStr += "h";
            break;
        case 8:
            stateStr += "c";
            break;
        default:
            //Also for sizes that don't is even 8, 16 or 32 use 32 bit, 
            //this is new behavior since previous codegen have all used 32 bit.
            stateStr += "i";
        }
        leave();
        return true;
    }

    @Override
    public Boolean caseTypeUint(TypeUint type) {
        enter(type);
        int sz = (int) (type.getSize() instanceof IntegerLiteral ? ((IntegerLiteral)type.getSize()).getValue() : (type.getSize()==null ? -1:-2));
        switch(sz) {
        case 32:
        case -1:
            //When no size specified use 32 bit
            stateStr += "I";
            break;
        case 16:
            stateStr += "H";
            break;
        case 8:
            stateStr += "C";
            break;
        default:
            //Also for sizes that don't is even 8, 16 or 32 use 32 bit, 
            //this is new behavior since previous codegen have all used 32 bit.
            stateStr += "I";
        }
        leave();
        return true;
    }

    @Override
    public Boolean caseTypeFloat(TypeFloat type) {
        enter(type);
        typeStr += ("d");
        leave();
        return true;
    }

    @Override
    public Boolean caseTypeList(TypeList type) {
        enter(type);
        stateStr +="?FIXME";
        leave();
        return true;
    }


    @Override
    public Boolean caseTypeString(TypeString type) {
        enter(type);
        stateStr += "s";
        leave();
        return true;
    }

    @Override
    public Boolean caseTypeTuple(TypeTuple type) {
        enter(type);
        //We should never need to print a tuple type directly, instead we print a string that will generate compile errors
        stateStr += ("SOMEONE PRINTING A TUPLE DIRECTLY????");
        leave();
        return true;
    }

    @Override
    public Boolean caseTypeExternal(TypeExternal type) {
        enter(type);
        stateStr += "?FIXME";
        leave();
        return true;
    }

    @Override
    public Boolean caseTypeUndef(TypeUndef type) {
        enter(type);
        stateStr += "?FIXME";
        leave();
        return true;
    }
    
    @Override
    public Boolean caseType(Type type) {
        assert(false);
        return null;
    }

}
