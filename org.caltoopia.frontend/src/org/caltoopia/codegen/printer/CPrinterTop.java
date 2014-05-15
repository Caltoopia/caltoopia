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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import org.caltoopia.codegen.CodegenError;
import org.caltoopia.ast2ir.IrXmlPrinter;
import org.caltoopia.codegen.UtilIR;
import org.caltoopia.codegen.transformer.IrTransformer;
import org.caltoopia.codegen.transformer.TransUtil;
import org.caltoopia.codegen.transformer.IrTransformer.IrPassTypes;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarAccess;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarType;
import org.caltoopia.ir.AbstractActor;
import org.caltoopia.ir.Action;
import org.caltoopia.ir.Actor;
import org.caltoopia.ir.ActorInstance;
import org.caltoopia.ir.Annotation;
import org.caltoopia.ir.AnnotationArgument;
import org.caltoopia.ir.Connection;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.ExternalActor;
import org.caltoopia.ir.ForwardDeclaration;
import org.caltoopia.ir.Guard;
import org.caltoopia.ir.IntegerLiteral;
import org.caltoopia.ir.LambdaExpression;
import org.caltoopia.ir.Namespace;
import org.caltoopia.ir.Network;
import org.caltoopia.ir.Point2PointConnection;
import org.caltoopia.ir.Port;
import org.caltoopia.ir.PortInstance;
import org.caltoopia.ir.PortRead;
import org.caltoopia.ir.PortWrite;
import org.caltoopia.ir.ProcExpression;
import org.caltoopia.ir.ReturnValue;
import org.caltoopia.ir.State;
import org.caltoopia.ir.Statement;
import org.caltoopia.ir.StringLiteral;
import org.caltoopia.ir.TaggedExpression;
import org.caltoopia.ir.TaggedTuple;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.TypeBool;
import org.caltoopia.ir.TypeDeclaration;
import org.caltoopia.ir.TypeDeclarationImport;
import org.caltoopia.ir.TypeExternal;
import org.caltoopia.ir.TypeFloat;
import org.caltoopia.ir.TypeInt;
import org.caltoopia.ir.TypeList;
import org.caltoopia.ir.TypeString;
import org.caltoopia.ir.TypeTuple;
import org.caltoopia.ir.TypeUint;
import org.caltoopia.ir.TypeUndef;
import org.caltoopia.ir.TypeUser;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableExternal;
import org.caltoopia.ir.VariableImport;
import org.caltoopia.ir.util.IrSwitch;
import org.eclipse.emf.ecore.EObject;

/*
 * This is the top class for printing an
 * annotated and transformed IR. It prints
 * the network c-code, and the actor c-code.
 * It also calls CPrintCommon to print
 * the consts, function declarations and 
 * types (with their operations)
 * 
 * Quality: 4, should work
 */
public class CPrinterTop extends IrSwitch<Stream> {

    Stream s;
    CompilationSession session = null;
    PrintStream out = null;
    boolean debugPrint = false;
    boolean header = false;
    boolean typesHeader = false;
    boolean inConstructor = false;
    CEnvironment cenv = null;
    String topHeaderFilename = null;
    Actor currentActor = null;
    
    /*
     * Constructor for printing all the header and c-files.
     * Main entry point for printing the annotated and
     * transformed IR.
     * 
     * session: session info
     * cenv: empty input variable to collect information 
     *       needed in makefiles etc from all the CBuilders
     */
    public CPrinterTop(CompilationSession session, CEnvironment cenv) {
        PrintStream out = session.getOutputStream();
        String nsName;
        String file;
        String baseName;
        this.session = session;
        this.out = session.getOutputStream();
        this.cenv = cenv;
        
        /* 
         * Copy the array methods header file, declaring all the functions
         * used for (de)allocating and copying arrays. It has inlined declared methods.
         */
        
        try {
            File dst = new File(session.getOutputFolder() + File.separator + "__arrayCopy.h");
            System.out.println("Copying '" + dst + "'");
            BufferedReader reader = null;
            InputStream src = this.getClass().getResourceAsStream("__arrayCopy.h");
            reader = new BufferedReader(new InputStreamReader(src));
            
            if(!dst.exists()) {
                    dst.createNewFile();
            }
            
            PrintStream writer = new PrintStream(new FileOutputStream(dst));
                 
            String line = null;
            while((line = reader.readLine()) != null) {
                writer.println(line);
            }
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Get the transformed elaborated top network from actor directory
        Network network = null;
        TypeActor elaboratedNetworkType = session.getElaboratedNetwork().getType();
        try {
            System.out.println("[CPrinter] Get top network from storage.");
            network=(Network) ActorDirectory.findTransformedActor(elaboratedNetworkType);
        } catch (DirectoryException e) {
            System.err.println("[CPrinter] Internal error could not get top network from storage.");
        }

        new CPrinterCommon(network,session,cenv);
        try {
            CalvinPrintTopFiles calvin = new CalvinPrintTopFiles(session, cenv, network);
            calvin.CalvinScriptFile();
            calvin.CalvinPythonFile();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        
        //Check if debug printing is set in the GUI
        debugPrint = session.debugPrint() == CompilationSession.DEBUG_TYPE_ACTIONUSER;

        nsName = Util.marshallQualifiedName(elaboratedNetworkType.getNamespace());
        baseName = nsName + "__" + elaboratedNetworkType .getName();
        topHeaderFilename = baseName + ".h";
        file = session.getOutputFolder() + File.separator + baseName;
        
        s = new Stream(file + ".c");
        out.println("Writing '" + file + ".c'");
        //Print network c-code file
        header = false;
        doSwitch(network);
        cenv.sourceFiles.add(baseName + ".c");
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
                //Print actor instance
                doSwitch(actor);
                s.close();
                cenv.sourceFiles.add(baseName);
                cenv.actorFiles.add(baseName);
            } else if(actor instanceof ExternalActor) {
                if(((TypeActor) a.getType()).getNamespace().get(0).equals("ART") && actor.getType().getName().equals("art_Display_yuv"))
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
        //Print build files
        CPrintBuildFiles build = new CPrintBuildFiles(session,cenv);
        try {
            build.CommonMakefile();
            build.CommonConfigFile(needSdl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //---------------------------------------------------------------------
    
    private void enter(EObject obj) {
        if(obj instanceof Actor) {
            currentActor = (Actor) obj;
        }
    }
    private void leave(EObject obj) {
        if(obj instanceof Actor) {
            currentActor = null;
        }
    }
    
    //----------------------------------------------------------------------

    private void toEnv(Map<String,String> aargs) {
        CPrintUtil.toEnvEnv(aargs, cenv);
    }

    /*
     * Find out which files to include in an actor c-code file,
     * based on the external declarations, i.e. extra external 
     * declarations.
     */
    private void printCIncludes(List<Declaration> declarations) {
        Set<String> cHeaders = new HashSet<String>();
        
        for(Declaration d : declarations) {
            VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(d, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
            switch(varType) {
            case externFunc:
            case externBuiltInTypeVar:
            case externBuiltInListTypeVar:
            case externOtherTypeVar:
            case externProc:
                Namespace ns = null;
                VariableExternal e = null;
                d = (d instanceof ForwardDeclaration)?((ForwardDeclaration)d).getDeclaration():d;
                if(d instanceof VariableImport) {
                    try {
                        e = (VariableExternal) ActorDirectory.findVariable((VariableImport) d, false);
                        ns = ActorDirectory.findNamespace(((VariableImport)d).getNamespace());
                    } catch (DirectoryException ee) {
                        ee.printStackTrace();
                    }
                } else if(d instanceof VariableExternal) {
                    e = (VariableExternal) d;
                    try {
                        ns = ActorDirectory.findNamespace(UtilIR.getAnnotatedNamespace(e));
                    } catch (DirectoryException e1) {
                        e1.printStackTrace();
                    }
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

    /*
     * Print the transformed elaborated top network.
     * This function prints the c-code that describes
     * the setup of the top network.
     * 
     * network: top network
     * Quality: 4, should work.
     */
    @Override
    public Stream caseNetwork(Network network) {
        s.println("// " + Util.marshallQualifiedName(network.getType().getNamespace()) + network.getType().getName());
        //All the includes
        enter(network);
        s.println("#include \"actors-rts.h\"");
        s.println("#include \"natives.h\"");
        s.println("#include \"commonSpec.h\"");

        //Printing the actual network into one c-code file
        s.printlnInc("static void initNetwork(AbstractActorInstance ***pInstances, int *pNumberOfInstances) {");
        s.println("int numberOfInstances = " + network.getActors().size() + ";"); 
        s.println("AbstractActorInstance **actorInstances = (AbstractActorInstance **) malloc(numberOfInstances * sizeof(AbstractActorInstance *));");
        s.println("*pInstances=actorInstances;");
        s.println("*pNumberOfInstances=numberOfInstances;");
        s.println();
        
        /*
         *  Declare the actors
         */
        Set<String> artClasses = new HashSet<String>();
        artClasses.clear();
        for (ActorInstance instance : network.getActors()) {
            TypeActor type = ((TypeActor) instance.getType());
            String actorInstanceName = Util.marshallQualifiedName(type.getNamespace()) + "__" + instance.getName();
            String actorClassName = null;
            //The ART actors are implemented in the runtime and we use the class name directly instead of instance name 
            if(type.getName().startsWith("art_")) {
                actorClassName = type.getName();
            } else {
                actorClassName = actorInstanceName; 
            }
            //Only new once are defined
            if(!artClasses.contains(actorClassName)) {
                s.println("extern ActorClass ActorClass_" + actorClassName + ";");
                artClasses.add(actorClassName);
            }

            //Declare the actor instance ...
            s.println("AbstractActorInstance *" + actorInstanceName + ";");

            //... with its ports
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
        
        /*
         *  Instantiate the actors
         */
        int actorId = 0;
        for (ActorInstance actor : network.getActors()) {
            TypeActor type = ((TypeActor) actor.getType());
            String actorInstanceName =  Util.marshallQualifiedName(type.getNamespace()) + "__" + actor.getName();
            String actorClassName = null;
            //The ART actors are implemented in the runtime and we use the class name directly instead of instance name 
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
            //For any ART actors set the parameters, all other actors have their parameters as part of the actor instance
            if(type.getName().startsWith("art_") || aactor instanceof ExternalActor) {
                for(TaggedExpression param : actor.getActualParameters()) {
                    s.print("setParameter(" + actorInstanceName + ", \"" + param.getTag() + "\", ");
                    //FIXME now we only support strings as parameter input and hence must be literal, so this goes wrong if it is a function call or variable
                    //Usually the constant expression evaluator have reduced it to a literal, but it is not possible for external declared functions etc.
                    if(param.getExpression().getType() instanceof TypeString || param.getExpression() instanceof StringLiteral) {
                        s.print(new CBuildExpression(param.getExpression(), cenv).toStr());
                    } else {
                        s.print("\"");
                        s.print(new CBuildExpression(param.getExpression(), cenv).toStr());
                        s.print("\"");
                    }
                    s.println(");");
                }
            }
            
            //Create all the ports
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
                                    
            //Finally put the actor instance into the array of instances
            s.println("actorInstances[" + actorId++ + "] = " + actorInstanceName + ";");
            s.println();
        }

        //Connect all the ports
        for (Connection c : network.getConnections()) {
            if(c instanceof Point2PointConnection) {
                Point2PointConnection p2p = (Point2PointConnection) c;
                s.print("connectPorts(" + Util.marshallQualifiedName(((TypeActor) p2p.getSource().getActor().getType()).getNamespace()) + 
                        "__" + p2p.getSource().getActor().getName() + 
                        "_" + p2p.getSource().getName());
                s.println(", " + Util.marshallQualifiedName(((TypeActor) p2p.getTarget().getActor().getType()).getNamespace()) + 
                        "__" + p2p.getTarget().getActor().getName() + 
                        "_" + p2p.getTarget().getName() + ");");
            } else {
                System.err.println("CONNECTION not P2P:" + c.toString());
            }
        }
        s.printlnDec("");
        s.println("}");
        
        //The main function setting up and executing the network
        s.printlnInc("int main(int argc, char *argv[]) {");
        s.println("int numberOfInstances;");
        s.println("AbstractActorInstance **instances;");
        s.println("initNetwork(&instances, &numberOfInstances);");
        s.println("return executeNetwork(argc, argv, instances, numberOfInstances);");
        s.printlnDec("");
        s.println("}"); 

        leave(network);
        return s;        
    }

    /*
     * Print an actor instance
     */
    @Override
    public Stream caseActor(Actor actor) {
        Map<String, String> inputPortMap = new HashMap<String, String>();
        Map<String, String> outputPortMap = new HashMap<String, String>();

        enter(actor);
        s.println("// " + Util.marshallQualifiedName(actor.getType().getNamespace()) + ", "+
                    actor.getType().getName() +", "+
                    TransUtil.getAnnotationArg(actor, "Instance", "name") + ", " +
                    TransUtil.getAnnotationArg(actor, "Instance", "id"));
        
        s.println("#include \"actors-rts.h\"");
        s.println("#include \"natives.h\"");
        s.println("#include \"commonSpec.h\"");

        //We print an actor instance from an elaborated transformed actor hence the instance name is only available in an annotation
        String thisStr = Util.marshallQualifiedName(actor.getType().getNamespace()) + "__" + TransUtil.getAnnotationArg(actor, "Instance", "name");
        String actorId = "ActorInstance_" + thisStr;
        
        //Print any external c includes from annotations
        printCIncludes(actor.getDeclarations());
        
        //Define all the ports
        for (int i = 0; i < actor.getInputPorts().size(); i++) {
            Port p = actor.getInputPorts().get(i);          
            s.println("#define  " + "IN" + i + "_" + p.getName() + " ART_INPUT(" + i + ")");
            inputPortMap.put(p.getName(), "IN" + i + "_" + p.getName());
        }
        
        for (int i = 0; i < actor.getOutputPorts().size(); i++) {
            Port p = actor.getOutputPorts().get(i);         
            s.println("#define  " + "OUT" + i + "_" + p.getName() + " ART_OUTPUT(" + i + ")");
            outputPortMap.put(p.getName(), "OUT" + i + "_" + p.getName());
        }
        s.println();

        /*
         * Actor local constants
         */
        s.println("//Actor constants");
        for (Declaration d : actor.getDeclarations()) {
            VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(d, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
            switch(varType) {
            case actorConstParamVar:
            case constVar:
            case actorConstVar:
                doSwitch(d);
                break;
            default:
                //s.println("/*TODO D " + d.getName() + "of varType " + varType.name() +"*/");
            }
        }   
    
        s.println();

        /*
         * Print actor instance state struct
         * Includes information on context, ports
         * state variables and FSM state.
         */
        s.println("//Actor state");
        s.printlnInc("typedef struct {");
        s.println("AbstractActorInstance base;");
        
        s.println("int _fsmState;"); 
        /***** Make sure struct definition, serialize and deserialize is kept in sync ******/
        for (Declaration d : actor.getDeclarations()) {
            VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(d, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
            switch(varType) {
            case actorVar:
            case actorParamVar:
            case actorNonLitConstVar:
                doSwitch(d);
                break;
            //These are known to not be printed here, makes sure that the default is only called for things that miss code (besides the type import or forward)
            case actorConstParamVar:
            case constVar:
            case actorConstVar:
            case proc:
            case func:
            case actorFunc:
                break;
            default:
                s.println("/*TODO DD " + d.getName() + " of varType " + varType.name() + " and " + ((d instanceof ForwardDeclaration)?"forward declaration":(d instanceof TypeDeclarationImport)?"type import":"NOT anticipated") + " */");
            }
        }

        s.printlnDec("} " + actorId + ";");
        s.println();
        
        /* 
         * Print serialize and deserialize of actor state functions.
         */
        s.println("#ifdef CAL_RT_CALVIN");
        s.println(new CBuildActorStateSerialize(actor, true, cenv, null, thisStr).toStr());
        s.println(new CBuildActorStateSerialize(actor, false, cenv, null, thisStr).toStr());
        s.println("#endif");
        
        /*
         * Print actor local function declarations
         */
        s.println("//Actor functions");
        for (Declaration d : actor.getDeclarations()) {
            VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(d, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
            switch(varType) {
            case actorFunc:
            case proc:
                doSwitch(d);
                break;
            //These are known to not be printed here, makes sure that the default is only called for things that miss code (besides the type import or forward)
            case actorVar:
            case actorParamVar:
            case actorConstParamVar:
            case constVar:
            case actorConstVar:
            case func:
                break;
            default:
                s.println("/*TODO DF " + d.getName() + " of varType " + varType.name() + " and " + ((d instanceof ForwardDeclaration)?"forward declaration":(d instanceof TypeDeclarationImport)?"type import":"NOT anticipated") +"*/");
            }
        }   

        //Allocate the actor's action context
        s.println("ART_ACTION_CONTEXT(" + actor.getInputPorts().size() + ", " + actor.getOutputPorts().size() + ")");
        s.println();

        //Define the actions
        for (Action a : actor.getActions()) {
            s.println("ART_ACTION(" + a.getId() + ", " + actorId + ");");
        }
        //Define the action scheduler
        s.println("ART_ACTION_SCHEDULER(" + thisStr + "_action_scheduler);");
        //Define the actor constructor
        s.println("static void " + actorId + "_constructor(AbstractActorInstance *);");
        s.println();
        
        //Declare the input port descriptions
        s.printlnInc("static const PortDescription inputPortDescriptions[]={");
        for (int i = 0; i < actor.getInputPorts().size(); i++) {
            Port p = actor.getInputPorts().get(i);
            Type type=p.getType();
            if(UtilIR.isMultiTagTuple(type)) {
                CodegenError.err("CPrintTop", "Not yet implemented tuple with multiple tags (1) ");
            }
            //User types is now references and hence also have a zero in first param
            s.print("{" + "0" + ", \"" + p.getName() + "\", ");
            if(UtilIR.isTuple(type)) {
                //third param indicate port token size in bytes
                //Tuples token always as pointer
                s.print("sizeof(void*)"); 
                //Calvin have a fourth param with function pointers
                s.println("");
                s.println("#ifdef CAL_RT_CALVIN");
                String typeStr = new CBuildTypeName(type, new CPrintUtil.dummyCB(), false).asNameStr();
                s.println(", &(tokenFn){(char * (*)(void *, char*))serializeStruct"+ typeStr +
                        ", (char * (*)(void **, char*))deserializeStruct"+ typeStr +
                        ", (long (*)(void *))sizeStruct"+ typeStr +
                        ", (int (*)(void *, int))freeStruct"+ typeStr+"}");
                s.println("#endif");
            } else {
                //third param indicate port token size in bytes
                s.print(CPrintUtil.createDeepSizeof(null, type, cenv));
                //For Calvin no functions pointers are needed for builtin types
                s.println("");
                s.println("#ifdef CAL_RT_CALVIN");
                s.println(", NULL");
                s.println("#endif");
            }
            
            if (i < actor.getInputPorts().size()) {
                s.println("},");            
            } else {
                s.println("}");                         
            }
        }
        s.printlnDec("};");

        //Declare the output port descriptions
        s.printlnInc("static const PortDescription outputPortDescriptions[]={");
        for (int i = 0; i < actor.getOutputPorts().size(); i++) {
            Port p = actor.getOutputPorts().get(i);         
            Type type=p.getType();
            if(UtilIR.isMultiTagTuple(type)) {
                CodegenError.err("CPrintTop", "Not yet implemented tuple with multiple tags (2) ");
            }
            //User types is now references and hence also have a zero in first param
            s.print("{" + "0" + ", \"" + p.getName() + "\", ");
            if(UtilIR.isTuple(type)) {
                //third param indicate port token size in bytes
                //Tuples token always as pointer
                s.print("sizeof(void*)");
                //Calvin have a fourth param with function pointers
                s.println("");
                s.println("#ifdef CAL_RT_CALVIN");
                String typeStr = new CBuildTypeName(type, new CPrintUtil.dummyCB(), false).asNameStr();
                s.println(", &(tokenFn){(char * (*)(void *, char*))serializeStruct"+ typeStr +
                        ", (char * (*)(void **, char*))deserializeStruct"+ typeStr +
                        ", (long (*)(void *))sizeStruct"+ typeStr +
                        ", (int (*)(void *, int))freeStruct"+ typeStr+"}");
                s.println("#endif");
            } else {
                //third param indicate port token size in bytes
                s.print(CPrintUtil.createDeepSizeof(null, type, cenv));
                //Calvin have a fourth param with function pointers
                s.println("");
                s.println("#ifdef CAL_RT_CALVIN");
                s.println(", NULL");
                s.println("#endif");
            }

            if (i < actor.getOutputPorts().size()) {
                s.println("},");            
            } else {
                s.println("}");                         
            }
        }
        s.printlnDec("};");
        s.println();

        //Declare the port rates
        for (Action action : actor.getActions()) {
            s.print("static const int portRate_in_" + action.getId() + "[] = {");
            for (Iterator<Port> i = actor.getInputPorts().iterator(); i.hasNext();) {
                Port p = i.next();
                Expression rate = Util.createIntegerLiteral(0);
                for (PortRead r : action.getInputs()) {
                    if(r.getPort().getName().equals(p.getName())) {
                        if (r.getRepeat()!=null) {
                            rate = UtilIR.createExpression(r.getRepeat(), "*", Util.createIntegerLiteral(r.getVariables().size()));
                        }
                        else {
                            rate = Util.createIntegerLiteral(r.getVariables().size());
                        }
                        
                    }
                }
                s.print(new CBuildExpression(rate,cenv).toStr());
                if (i.hasNext()) s.print(", ");
            }
            s.println("};");
            s.println();            
            
            s.print("static const int portRate_out_" + action.getId() + "[] = {");
            for (Iterator<Port> i = actor.getOutputPorts().iterator(); i.hasNext();) {
                Port p = i.next(); 
                Expression rate = Util.createIntegerLiteral(0);
                for (PortWrite w : action.getOutputs()) {
                    if(w.getPort().getName().equals(p.getName())) {
                        if (w.getRepeat()!=null) {
                            rate = UtilIR.createExpression(w.getRepeat(), "*", Util.createIntegerLiteral(w.getExpressions().size()));
                        }
                        else {
                            rate = Util.createIntegerLiteral(w.getExpressions().size());
                        }
                    }
                }
                s.print(new CBuildExpression(rate,cenv).toStr());
                if (i.hasNext()) s.print(", ");

            }
            s.println("};");
            s.println();    
        }

        //Declare the action descriptions with their port rates
        s.printlnInc("static const ActionDescription actionDescriptions[] = {");
        for (Action action : actor.getActions()) {
            s.println("{\"" + action.getId() + "\", portRate_in_" + action.getId() + ", portRate_out_" + action.getId() + "},");
        }
        s.printlnDec("};");
        s.println();    

        /*
         * Declare the actor instance struct
         */
        s.println("#ifdef CAL_RT_CALVIN");
        s.println("ActorClass klass");
        s.println("#else");
        s.println("ActorClass ActorClass_" + thisStr);
        s.printlnInc("#endif");
        s.println(" = INIT_ActorClass(");
        s.println("\"" + thisStr + "\",");
        s.println(actorId + ",");
        s.println(actorId + "_constructor,");
        s.println("0, //setParam not needed anymore (we instantiate with params)");
        s.println("#ifdef CAL_RT_CALVIN");
        s.println(actorId + "_serialize,");
        s.println(actorId + "_deserialize,");
        s.println("#endif");
        s.println(thisStr + "_action_scheduler,");
        s.println("0, // no destructor");
        s.println(actor.getInputPorts().size() + ", inputPortDescriptions,");
        s.println(actor.getOutputPorts().size() + ", outputPortDescriptions,");
        s.println(actor.getActions().size() + ", actionDescriptions");
        s.printlnDec(");");
        s.println();
        
        /*
         * Print each action as a function
         */
        for (int i=0;i < actor.getActions().size();i++) {
            Action a = actor.getActions().get(i);
            s.println(new CBuildAction(a,cenv,thisStr,i,debugPrint).toStr());
        }
        
        /*
         * Print the initializer actions which have output ports
         * as other actions.
         */
        if(!actor.getInitializers().isEmpty()) {
            for (int i=0;i < actor.getInitializers().size();i++) {
                Action a = actor.getInitializers().get(i);
                if(!a.getOutputs().isEmpty()) {
                    s.println(new CBuildAction(a,cenv,thisStr,i,debugPrint).toStr());
                }
            }
        }

        /*
         * Print all the actions guard functions
         */
        for (int i=0;i < actor.getActions().size();i++) {
            Action a = actor.getActions().get(i);
            s.println(new CBuildGuard(a,cenv,thisStr,i).toStr());
        }

        //Define the intial FSM state
        int i = 0;
        for (State state : actor.getSchedule().getStates()) {
            s.println("#define " + actorId + "__" + state.getName() + "_ID " + i++);
        }

        /*
         * Declare the actor constructor
         */
        s.printlnInc("static void " + actorId + "_constructor(AbstractActorInstance *pBase) {");
        s.println(actorId + " *thisActor=(" + actorId + "*) pBase;");

        //Set FSM inital state
        if(actor.getSchedule().getInitialState()!=null) {
            s.println("thisActor->_fsmState = " + actorId + "__" + actor.getSchedule().getInitialState().getName() + "_ID;//Initial state"); 
        } else if(!actor.getSchedule().getStates().isEmpty()){
            s.println("thisActor->_fsmState = " + actorId + "__" + actor.getSchedule().getStates().get(0).getName() + "_ID;//First state"); 
        }

        //Initialize all the constants that are not c compiler constant
        inConstructor = true;
        for (Declaration d : actor.getDeclarations()) {
            VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(d, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
            switch(varType) {
            case actorNonLitConstVar:
                s.print("thisActor->");
                doSwitch(d);
                break;
            default:
            }
        }
        inConstructor = false;
        
        //Print the bodies of all initializer actions without out ports
        if(!actor.getInitializers().isEmpty()) {
            IndentStr ind = new IndentStr();
            ind.inc();
            for(Action a : actor.getInitializers()) {
                if(a.getOutputs().isEmpty()) {
                    s.println(new CBuildConstructorInitializer(a, cenv, ind).toStr());
                }
            }
            ind.dec();
        }

        //Actors that have CAL annotation of needing active mode set that for the runtime
        if(CPrintUtil.isCActiveMode(actor)) 
        {
            s.println("{");
            s.inc();
            s.println("ActorClass *actorClass = (ActorClass *)thisActor->base.actor;");
            s.println("actorClass->actorExecMode = 1;");
            s.dec();
            s.println("}");             
        }
        
        s.dec();
        s.println();
        s.println("}");
        
        IndentStr ind = new IndentStr();
        s.println(new CBuildActorActionScheduler(actor,cenv,ind, debugPrint).toStr());
        
        leave(actor);
        return s;
    }
    
    //------------------------VARIABLES, FUNC, PROC, ETC DECLARATIONS---------------------------------------
    @Override
    public Stream caseVariable(Variable variable) {
        enter(variable);
        VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(variable, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
        switch(varType) {
        case actorConstParamVar:
        case constVar:
        case actorConstVar:
            s.print("/*CONST " + (UtilIR.isDeepLiteralExpression(variable.getInitValue())?"LITERAL":"NON-LITERAL")+"*/");
            if(!UtilIR.isDeepLiteralExpression(variable.getInitValue())) {
                CodegenError.err("CPrinterTop", "Not yet implemented handling of const declaration ("+variable.getName()+") that can't be reduced to literal constants at compile time.");
            }
            s.print(new CBuildConstDeclaration(variable,cenv,header).toStr());
            s.println(";");
            break;
        case actorVar:
            s.print(new CBuildVarDeclaration(variable,cenv,false).toStr());
            s.println(";");
            break;
        case actorNonLitConstVar:
            if(inConstructor) {
                s.print(new CBuildConstDeclaration(variable,cenv,false).skipTypeStr());
                s.println(";");
            } else {
                s.print(new CBuildVarDeclaration(variable,cenv,false).toStr());
                s.println(";");
            }
            break;
        case func:
        case actorFunc:
            s.print(new CBuildFuncDeclaration(variable,cenv,header).toStr());
            break;
        case proc:
            s.print(new CBuildProcDeclaration(variable,cenv,header).toStr());
            break;
        default:
            VarAccess varAccess = VarAccess.valueOf(TransUtil.getAnnotationArg(variable, IrTransformer.VARIABLE_ANNOTATION, "VarAccess"));
            String typeUsage = TransUtil.getAnnotationArg(variable, IrTransformer.TYPE_ANNOTATION, "TypeUsage");
            String varStr =(varType.name() +", " +
                    varAccess.name() +", " +
                    typeUsage);
            s.println("/*TODO V "+variable.getName() + ", " + varStr + " */");
        } 
        leave(variable);
        return s;
    }

}