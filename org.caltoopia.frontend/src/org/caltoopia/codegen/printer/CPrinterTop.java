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

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.caltoopia.ast2ir.Instantiator;
import org.caltoopia.ast2ir.Stream;
import org.caltoopia.ast2ir.Util;
import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.cli.CompilationSession;
import org.caltoopia.cli.DirectoryException;
import org.caltoopia.codegen.CEnvironment;
import org.caltoopia.codegen.CPrinter;
import org.caltoopia.codegen.IrXmlPrinter;
import org.caltoopia.codegen.UtilIR;
import org.caltoopia.codegen.transformer.IrTransformer;
import org.caltoopia.codegen.transformer.TransUtil;
import org.caltoopia.codegen.transformer.IrTransformer.IrPassTypes;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarAccess;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarType;
import org.caltoopia.ir.AbstractActor;
import org.caltoopia.ir.Actor;
import org.caltoopia.ir.ActorInstance;
import org.caltoopia.ir.Annotation;
import org.caltoopia.ir.AnnotationArgument;
import org.caltoopia.ir.Connection;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.ExternalActor;
import org.caltoopia.ir.IntegerLiteral;
import org.caltoopia.ir.LambdaExpression;
import org.caltoopia.ir.Namespace;
import org.caltoopia.ir.Network;
import org.caltoopia.ir.Point2PointConnection;
import org.caltoopia.ir.Port;
import org.caltoopia.ir.PortInstance;
import org.caltoopia.ir.ProcExpression;
import org.caltoopia.ir.ReturnValue;
import org.caltoopia.ir.StringLiteral;
import org.caltoopia.ir.TaggedExpression;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.TypeBool;
import org.caltoopia.ir.TypeDeclaration;
import org.caltoopia.ir.TypeExternal;
import org.caltoopia.ir.TypeFloat;
import org.caltoopia.ir.TypeInt;
import org.caltoopia.ir.TypeList;
import org.caltoopia.ir.TypeRecord;
import org.caltoopia.ir.TypeString;
import org.caltoopia.ir.TypeUint;
import org.caltoopia.ir.TypeUndef;
import org.caltoopia.ir.TypeUser;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableExternal;
import org.caltoopia.ir.VariableImport;
import org.caltoopia.ir.util.IrSwitch;
import org.eclipse.emf.ecore.EObject;

public class CPrinterTop extends IrSwitch<Stream> {

    Stream s;
    CompilationSession session = null;
    PrintStream out = null;
    boolean debugPrint = false;
    boolean header = false;
    CEnvironment cenv;

    //-----------------------------------------------------------------------------
    
    public CPrinterTop(CompilationSession session) {
        List<String> sourceFiles = new ArrayList<String>();
        PrintStream out = session.getOutputStream();
        String nsName;
        String file;
        String baseName;
        this.session = session;
        this.out = session.getOutputStream();
        Network network = null;
        TypeActor elaboratedNetworkType = session.getElaboratedNetwork().getType();
        try {
            System.out.println("[CPrinter] Get top network from storage.");
            network=(Network) ActorDirectory.findTransformedActor(elaboratedNetworkType);
        } catch (DirectoryException e) {
            System.err.println("[CPrinter] Internal error could not get top network from storage.");
        }

        debugPrint = session.debugPrint() == CompilationSession.DEBUG_TYPE_ACTIONUSER;

        nsName = Util.marshallQualifiedName(elaboratedNetworkType.getNamespace());
        baseName = nsName + "__" + elaboratedNetworkType .getName();
        file = session.getOutputFolder() + File.separator + baseName;
        s = new Stream(file + ".h");
        out.println("Writing '" + file + ".h'");
        header = true;
        doSwitch(network);
        s.close();
        
        s = new Stream(file + ".c");
        out.println("Writing '" + file + ".c'");
        header = false;
        doSwitch(network);
        sourceFiles.add(baseName + ".c");
        s.close();
        
        String needSdl = "n";
        
        //    Print a c-file for each actor instance
        for(ActorInstance a : network.getActors()) {
            AbstractActor actor=null;
            try {
                System.out.println("[CPrinter] Read in actor instance '" + a.getName() + "' of class " + ((TypeActor) a.getType()).getName());
                actor = (AbstractActor) ActorDirectory.findTransformedActor(a);
            } catch (DirectoryException x) {
                //OK, since likely external
                try {
                    actor = ActorDirectory.findActor((TypeActor) a.getType());
                    //We only want it from the non-transformed actor directory if it is an external actor
                    if(!(actor instanceof ExternalActor)) {
                        actor = null;
                    }
                } catch (DirectoryException e) {
                    //We could not find the actor, then presumably we have earlier failed to instantiate
                    out.println("ERROR: actor '" + ((TypeActor) a.getType()).getName() + "' not found");
                    return;
                }
            }
            if (actor instanceof Actor) {
                nsName = Util.marshallQualifiedName(((TypeActor) a.getType()).getNamespace());
                baseName = nsName + "__" + a.getName() + ".c";
                file = session.getOutputFolder() + File.separator + baseName;
                s = new Stream(file);
                out.println("Writing '" + file + "'");
                doSwitch(actor);
                s.close();
                sourceFiles.add(baseName);
            } else if(actor instanceof ExternalActor) {
                if(nsName.equals("ART") && actor.getType().getName().equals("art_Display_yuv"))
                    needSdl="y";
                Namespace ns = null;
                try {
                    ns = ActorDirectory.findNamespace(((TypeActor) a.getType()).getNamespace());
                } catch (DirectoryException ee) {}
                /*
                List<Annotation> allAnnotations = CPrinter.collectActorAnnotations(actor, ns);
                Map<String,String> externAnnotations = CPrinter.getExternAnnotations(allAnnotations);
                CPrinter.toEnvEnv(externAnnotations,env);
                */
            }
        }
    }

    //---------------------------------------------------------------------
    
    private void enter(EObject obj) {}
    private void leave() {}
    
    //----------------------------------------------------------------------

    private void toEnv(Map<String,String> aargs) {
        CPrintUtil.toEnvEnv(aargs, cenv);
    }

    private void printCIncludes(List<Declaration> declarations) {
        Set<String> cHeaders = new HashSet<String>();
        
        for(Declaration d : declarations) {
            VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(d, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
            switch(varType) {
            case externFunc:
            case externOtherTypeVar:
            case externProc:
                Namespace ns = null;
                VariableExternal e = null;
                try {
                    e = (VariableExternal) ActorDirectory.findVariable((VariableImport) d);
                    ns = ActorDirectory.findNamespace(((VariableImport)d).getNamespace());
                } catch (DirectoryException ee) {
                    out.println();
                }
                Map<String,String> annotations = CPrintUtil.getExternAnnotations(CPrintUtil.collectAnnotations(e,ns));
                cHeaders.addAll(CPrintUtil.externalCInclude(annotations,e));
                toEnv(annotations);
                break;
            default:
            }            
        }
        
        for(String str : cHeaders) {
            s.println("#include \""+str+"\"");
        }        
    }

    //--------------------------------------------------------------------------------
    
    @Override
    public Stream caseNetwork(Network network) {
        s.println("// " + Util.marshallQualifiedName(network.getType().getNamespace()) + network.getType().getName());
        enter(network);
        s.println("#include \"actors-rts.h\"");
        s.println("#include \"natives.h\"");

        if(header) {
            //Printing all declarations as externs into one header file
            String name = Util.marshallQualifiedName(network.getType().getNamespace()) + "__" + network.getType().getName();
            name = name.toUpperCase();
            name = "CAL_TOP_NETWORK_" + name;
            s.println("#ifndef "+name);
            s.println("#define "+name);

            printCIncludes(network.getDeclarations());

            for(Declaration d : network.getDeclarations()) {
                VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(d, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
                switch(varType) {
                case constVar:
                case importConstVar:
                case importFunc:
                case importProc:
                case func:
                case declarationType:
                    doSwitch(d);
                }
            }
            s.println("#endif");
        } else {
            //Printing the actual network into one c-code file
            s.println("#include \"" + Util.marshallQualifiedName(network.getType().getNamespace()) + "__" + network.getType().getName() + ".h\"");
            for(Declaration d : network.getDeclarations()) {
                doSwitch(d);
            }

            s.printlnInc("static void initNetwork(AbstractActorInstance ***pInstances, int *pNumberOfInstances) {");
            s.println("int numberOfInstances = " + network.getActors().size() + ";"); 
            s.println("AbstractActorInstance **actorInstances = (AbstractActorInstance **) malloc(numberOfInstances * sizeof(AbstractActorInstance *));");
            s.println("*pInstances=actorInstances;");
            s.println("*pNumberOfInstances=numberOfInstances;");
            s.println();
            
            // Declare the actors
            Set<String> artClasses = new HashSet<String>();
            artClasses.clear();
            for (ActorInstance instance : network.getActors()) {
                TypeActor type = ((TypeActor) instance.getType());
                String actorInstanceName = Util.marshallQualifiedName(type.getNamespace()) + "_" + instance.getName();
                String actorClassName = null;
                if(type.getName().startsWith("art_")) {
                    actorClassName = type.getName();
                } else {
                    actorClassName = actorInstanceName; 
                }
                if(!artClasses.contains(actorClassName)) {
                    s.println("extern ActorClass ActorClass_" + actorClassName + ";");
                    artClasses.add(actorClassName);
                }

                s.println("AbstractActorInstance *" + actorInstanceName + ";");
                
                AbstractActor actor;
                try {
                    actor = ActorDirectory.findActor(type);
                } catch (DirectoryException x) {
                    System.err.println("[CPrinter] Internal error #1");
                    return s;
                }
                for (Port port : actor.getInputPorts()) {
                    s.println("InputPort *" + actorInstanceName + "_" + port.getName() + ";");    
                }
    
                for (Port port : actor.getOutputPorts()) {
                    s.println("OutputPort *" + actorInstanceName + "_" + port.getName() + ";");    
                }
                s.println();
            }                
            
            // Instantiate the actors
            int actorId = 0;
            for (ActorInstance actor : network.getActors()) {
                TypeActor type = ((TypeActor) actor.getType());
                String actorInstanceName =  Util.marshallQualifiedName(type.getNamespace()) + "_" + actor.getName();
                String actorClassName = null;
                if(type.getName().startsWith("art_")) {
                    actorClassName = type.getName(); 
                } else {
                    actorClassName = actorInstanceName; 
                }
                s.println(actorInstanceName + " = createActorInstance(&ActorClass_" + actorClassName + ");");
                AbstractActor aactor = null;
                try {
                    aactor = ActorDirectory.findActor((TypeActor) actor.getType());
                } catch (DirectoryException x) {
                }
                if(type.getName().startsWith("art_") || aactor instanceof ExternalActor) {
                    for(TaggedExpression param : actor.getActualParameters()) {
                        s.print("setParameter(" + actorInstanceName + ", \"" + param.getTag() + "\", ");
                        if(param.getExpression().getType() instanceof TypeString || param.getExpression() instanceof StringLiteral) {
                            doSwitch(param.getExpression());
                        } else {
                            s.print("\"");
                            doSwitch(param.getExpression());                                
                            s.print("\"");
                        }
                        s.println(");");
                    }
                }
                
                for (PortInstance port : actor.getInputs()) {
                    String portName = port.getName();    
                    for (Connection c : network.getConnections()) {
                        long capacity = 0;
                        Point2PointConnection connection = (Point2PointConnection) c;
                        if (connection.getTarget() == port) {
                            for (TaggedExpression attribute : connection.getAttributes()) {
                                if (attribute.getTag().compareToIgnoreCase("BufferSize") == 0) {
                                    assert(attribute.getExpression() instanceof IntegerLiteral);
                                    long bufferSize = ((IntegerLiteral) attribute.getExpression()).getValue();
                                    capacity = bufferSize > capacity ? bufferSize : capacity;
                                }
                            }                                    
                            s.println(actorInstanceName + "_" + portName + " = createInputPort(" + actorInstanceName + ", \"" + portName +"\", " + capacity + ");");                
                            break;
                        }
                    }                            
                }
                
                for (PortInstance port : actor.getOutputs()) {
                    String portName = port.getName();    
                    int fanOut = port.getConnections().size();
                    s.println(actorInstanceName + "_" + portName + " = createOutputPort(" + actorInstanceName + ", \"" + portName +"\", " + fanOut + ");");                
                }
                                        
                s.println("actorInstances[" + actorId++ + "] = " + actorInstanceName + ";");
                s.println();
                          
                for (Connection c : network.getConnections()) {
                    if(c instanceof Point2PointConnection) {
                        Point2PointConnection p2p = (Point2PointConnection) c;
                        s.print("connectPorts(" + Util.marshallQualifiedName(((TypeActor) p2p.getSource().getActor().getType()).getNamespace()) + 
                                "_" + p2p.getSource().getActor().getName() + 
                                "_" + p2p.getSource().getName());
                        s.println(", " + Util.marshallQualifiedName(((TypeActor) p2p.getTarget().getActor().getType()).getNamespace()) + 
                                "_" + p2p.getTarget().getActor().getName() + 
                                "_" + p2p.getTarget().getName() + ");");
                    } else {
                        System.err.println("CONNECTION not P2P:" + c.toString());
                    }
                }
                s.printlnDec("");
                s.println("}");
                
                s.printlnInc("int main(int argc, char *argv[]) {");
                s.println("int numberOfInstances;");
                s.println("AbstractActorInstance **instances;");
                s.println("initNetwork(&instances, &numberOfInstances);");
                s.println("return executeNetwork(argc, argv, instances, numberOfInstances);");
                s.printlnDec("");
                s.println("}"); 
            }
        }
    
        leave();
        return s;        
    }

    @Override
    public Stream caseActor(Actor actor) {
        enter(actor);
        s.println("// " + Util.marshallQualifiedName(actor.getType().getNamespace()) + ", "+
                    actor.getType().getName() +", "+
                    TransUtil.getAnnotationArg(actor, "Instance", "name") + ", " +
                    TransUtil.getAnnotationArg(actor, "Instance", "id"));
        leave();
        return s;
    }
    
    //------------------------VARIABLES, FUNC, PROC, ETC DECLARATIONS---------------------------------------
    //------------------caseX
    @Override
    public Stream caseVariable(Variable variable) {
        enter(variable);
        VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(variable, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
        switch(varType) {
        case constVar:
            s.print(new CBuildConstDeclaration(variable,header).toStr());
            s.println(";");
            break;
        case func:
        case actorFunc:
            s.print(new CBuildFuncDeclaration(variable,header).toStr());
            break;
        default:
            VarAccess varAccess = VarAccess.valueOf(TransUtil.getAnnotationArg(variable, IrTransformer.VARIABLE_ANNOTATION, "VarAccess"));
            String typeUsage = TransUtil.getAnnotationArg(variable, IrTransformer.TYPE_ANNOTATION, "TypeUsage");
            String varStr =(varType.name() +", " +
                    varAccess.name() +", " +
                    typeUsage);
            s.println("/*TODO T "+variable.getName() + ", " + varStr + " */");
        } 
        leave();
        return s;
    }

    //--------------------------TYPES-------------------------------------
    
    //----------------- caseX-----
    @Override
    public Stream caseTypeDeclaration(TypeDeclaration type) {
        if(header) {
            enter(type);
            s.println("");
            s.printlnInc("typedef struct {");
            TypeRecord struct = (TypeRecord) (type.getType() instanceof TypeUser ? ((TypeDeclaration)((TypeUser)type.getType()).getDeclaration()).getType(): type.getType());
            for (Iterator<Variable> i = struct.getMembers().iterator(); i.hasNext();) {
                Variable var = i.next();
                s.print(new CBuildVarDeclaration(var).toStr());
                s.println(";");
                if(!i.hasNext()) {
                    s.dec(); //After next println
                }
            }
            s.print("} ");
            s.println(type.getName() + "_t;");
            leave();
            s.println("");
        }
        return s;
    }

}