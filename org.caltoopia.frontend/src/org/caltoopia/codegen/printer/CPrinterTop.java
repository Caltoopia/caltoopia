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
 * the types header file, the network header and c-code,
 * and the actor c-code.
 * 
 * Quality: 4, should work but would benefit from breaking out parts and better organization
 */
public class CPrinterTop extends IrSwitch<Stream> {

    Stream s;
    CompilationSession session = null;
    PrintStream out = null;
    boolean debugPrint = false;
    boolean header = false;
    boolean typesHeader = false;
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

        //Check if debug printing is set in the GUI
        debugPrint = session.debugPrint() == CompilationSession.DEBUG_TYPE_ACTIONUSER;

        nsName = Util.marshallQualifiedName(elaboratedNetworkType.getNamespace());
        baseName = nsName + "__" + elaboratedNetworkType .getName();
        topHeaderFilename = baseName + ".h";
        file = session.getOutputFolder() + File.separator + baseName;
        s = new Stream(file + "__types.h");
        out.println("Writing '" + file + "__types.h'");
        //Print user type header file
        typesHeader = true;
        doSwitch(network);
        s.close();
        typesHeader = false;

        s = new Stream(file + ".h");
        out.println("Writing '" + file + ".h'");
        //Print network header file
        header = true;
        doSwitch(network);
        s.close();
        
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
            build.MonoMakefile();
            build.MonoConfigFile(needSdl);
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
     * This function can print either header, c-code
     * or type header file based on how the class
     * variables header and typeHeader are set.
     * 
     * network: top network
     * Quality: 3, need to clean up and break up into pieces.
     */
    @Override
    public Stream caseNetwork(Network network) {
        s.println("// " + Util.marshallQualifiedName(network.getType().getNamespace()) + network.getType().getName());
        if(typesHeader) {
            //Printing all list types
            String name = Util.marshallQualifiedName(network.getType().getNamespace()) + "__" + network.getType().getName();
            name = name.toUpperCase();
            name = "CAL_TOP_NETWORK_" + name +"__TYPES";
            s.println("#ifndef "+name);
            s.println("#define "+name);
            s.println("#include <stdint.h>");
            s.println("#define TRUE 1");
            s.println("#define FALSE 0");
            s.println("#define TYPE_DIRECT");

            int dimensions[] = {4};//For now only max 4 dim and no optimization of struct size, {1,2,3,4,256}; //list all dimensions needed, if someone wants more than 4 dimensions they get 256 since they anyway don't care about memory usage
            /*
             * Print a type used to specify index or size
             * of an array. Used by the array copy methods.
             */
            s.println("typedef struct {");
            s.println("  int32_t len;");
            s.println("  int32_t sz[" + dimensions[dimensions.length-1]+ "];");
            s.println("} __arrayArg;");

            /*
             * Print a function used to calc a max of
             * each dimension.
             * targetSz: the assigned target's size
             * exprSz: the assigning expression's size
             * shift: how many dimensions (due to index) that is used from the targetSz.
             */
            s.println("static inline __arrayArg maxArraySz(__arrayArg* targetSz, __arrayArg* exprSz, int shift) {");
            s.println("    __arrayArg ret;");
            //Full array replace
            s.println("    if(shift==0) {");
            s.println("        return *exprSz;");
            s.println("    }");
            //Partial array replace shift = nbr of indices
            s.println("    ret.len = targetSz->len;");
            s.println("    memcpy(ret.sz,targetSz->sz,sizeof(int32_t)*4);");
            s.println("    for(int i=0;i<exprSz->len;i++) {");
            s.println("        if(targetSz->sz[i+shift]<exprSz->sz[i])");
            s.println("            ret.sz[i+shift] = exprSz->sz[i];");
            s.println("    }");
            s.println("    return ret;");
            s.println("}");

            /*
             * These are the array types 
             * typedef struct {
             *     union {
             *         T_t* p;           pointer to array of builtin typed elements of the flattened array
             *         T_t* (*pp);       pointer to array of pointers to user type elements of the flattened array 
             *     };
             *     union { struct {
             *         uint16_t flags;  flags see below
             *         uint16_t dim;    dimensions valid in size array below
             *     }; uint32_t flags_dim;};
             *     int32_t sz[4];       size of each dimension (currently maximum of 4 allowed)
             * } __array4T_t;           This metadata type is created for all the builtin types and all the user types
             *
             * Flag field:
             *   Flags (true/false):
             *   0:0x01 direct(p)/indirect(pp)  (FIXME not set correctly in all the code, remove it since anyway linked to user type?)
             *   1:0x02 currently allocated(sz & pointer correct)/not-allocated (Very important to be correct)
             *   2:0x04 on heap/on stack, (the data is currently mostly allocated on the heap and if the pointer is changed the old needs to be (deep) freed (we know non-other keeps pointers to lower levels due to copy semantics)
             *   3:0x08 codegen (temporary created variable due to transformations go ahead and steel the memory)/cal variable (must obey copy semantics)
             *   4:0x10 part of multi-dim (can't change the pointer or free etc, since pointing into the memory of a larger array)/the full array
             */

            //Print array metadata struct and array methods for all builtin types
            for(String t: Arrays.asList("char", "int8_t", "uint8_t", "int16_t", "uint16_t", "int32_t", "uint32_t", "double","void", "bool_t")) {
                for(int i:dimensions) {
                    s.println("typedef struct {");
                    s.println("  union {");
                    s.println("  " + t +"* p;");
                    s.println("  " + t +"* (*pp);");
                    s.println("  };");
                    s.println("  union { struct {");
                    s.println("    uint16_t flags;");
                    s.println("    uint16_t dim;");
                    s.println("  }; uint32_t flags_dim;};");
                    s.println("  int32_t sz[" + i + "];");
                    s.println("} __array"+i+t +";");
                }
                s.println("#define TYPE " + t);
                s.println("#include \"__arrayCopy.h\"");
            }
            s.println("#undef TYPE_DIRECT");
            
            
            //Print typedef definition of user type struct for all user types
            for(Declaration d : network.getDeclarations()) {
                VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(d, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
                switch(varType) {
                case declarationType:
                    s.println("typedef struct " + ((TypeDeclaration)d).getName() + "_s " + ((TypeDeclaration)d).getName() + "_t;");
                }
            }
            
            //Print array metadata struct and array methods for all user types
            for(Declaration d : network.getDeclarations()) {
                VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(d, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
                switch(varType) {
                case declarationType:
                    String t = ((TypeDeclaration)d).getName()+"_t";
                    for(int i:dimensions) {
                        s.println("typedef struct {");
                        s.println("  union {");
                        s.println("    " + t +"* p;");
                        s.println("    " + t +"* (*pp);");
                        s.println("  };");
                        s.println("  union { struct {");
                        s.println("    uint16_t flags;");
                        s.println("    uint16_t dim;");
                        s.println("  }; uint32_t flags_dim;};");
                        s.println("  int32_t sz[" + i + "];");
                        s.println("} __array"+i+t +";");
                    }
                    s.println("int freeStruct" + t + "(" + t + " * src, int top);");
                    s.println("int copyStruct" + t + "(" + t + " ** dst, " + t +" * src);");
                    s.println("#define TYPE " + t);
                    s.println("#include \"__arrayCopy.h\"");
                }
            }

            s.println("#endif");
            return s;
        }
        //All the includes
        enter(network);
        s.println("#include \"actors-rts.h\"");
        s.println("#include \"natives.h\"");
        String name = Util.marshallQualifiedName(network.getType().getNamespace()) + "__" + network.getType().getName();
        s.println("#include \"" + name + "__types.h\"");

        if(header) {
            //Printing all declarations as externs into one header file
            name = name.toUpperCase();
            name = "CAL_TOP_NETWORK_" + name;
            s.println("#ifndef "+name);
            s.println("#define "+name);

            //include all external annotated files
            printCIncludes(network.getDeclarations());

            for(Declaration d : network.getDeclarations()) {
                VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(d, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
                switch(varType) {
                case constVar:
                case actorConstVar:
                case importConstVar:
                case importFunc:
                case importProc:
                case func:
                    //TODO maybe this check should be moved deeper into the c printing
                    if(TransUtil.getAnnotationArg(d, IrTransformer.VARIABLE_ANNOTATION, "DeclUsed").equals("TRUE")) {
                        doSwitch(d);
                    } else {
                        s.println("/*NOT USED " + d.getName() + "*/");
                    }
                    break;
                case declarationType:
                    doSwitch(d);
                }
            }
            s.println("#endif");
        } else {
            //Printing the actual network into one c-code file
            s.println("#include \"" + Util.marshallQualifiedName(network.getType().getNamespace()) + "__" + network.getType().getName() + ".h\"");
            for(Declaration d : network.getDeclarations()) {
                //TODO maybe the declaration used check should be moved deeper into the c printing
                VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(d, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
                if(TransUtil.getAnnotationArg(d, IrTransformer.VARIABLE_ANNOTATION, "DeclUsed").equals("TRUE") ||
                   varType.equals(VarType.declarationType)) {
                    doSwitch(d);
                } else {
                    s.println("/*NOT USED " + d.getName() + "*/");
                }
            }

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
        }
    
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
        s.println("#include \"" + topHeaderFilename + "\"");

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
        for (Declaration d : actor.getDeclarations()) {
            VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(d, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
            switch(varType) {
            case actorVar:
            case actorParamVar:
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
            //First param indicate user type or builtin type
            s.print("{" + (UtilIR.isSingleTagTuple(type)?"1":"0") + ", \"" + p.getName() + "\", ");
            //Last param indicate port token size in bytes
            s.print(CPrintUtil.createDeepSizeof(null, type, cenv));

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
            //First param indicate user type or builtin type
            s.print("{" + (UtilIR.isSingleTagTuple(type)?"1":"0") + ", \"" + p.getName() + "\", ");
            //Last param indicate port token size in bytes
            s.print(CPrintUtil.createDeepSizeof(null, type, cenv));

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
        s.printlnInc("ActorClass ActorClass_" + thisStr + " = INIT_ActorClass(");
        s.println("\"" + thisStr + "\",");
        s.println(actorId + ",");
        s.println(actorId + "_constructor,");
        s.println("0, //setParam not needed anymore (we instantiate with params)");
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

    //--------------------------TYPES-------------------------------------
    @Override
    public Stream caseTypeDeclaration(TypeDeclaration type) {
        if(header) {
            enter(type);
            /*
             * Create enum of tags for a tagged tuple
             */
            Type t = UtilIR.getType(type);
            s.printlnInc("enum " + type.getName() + "_tags {");
            s.print(type.getName() + "___none=0");
            if(UtilIR.isMultiTagTuple(t)) {
                for(TaggedTuple tag: ((TypeTuple)t).getTaggedTuples()) {
                    s.println(",");
                    s.print(type.getName() + "___" + tag.getTag()); 
                }
            }
            s.println("");
            s.printlnDec("};");
            /* 
             * This is printed in the network header file
             * The user type is created as a c-struct:
             *   struct T_s {
             *     uint32_t flags;
             *     enum T_tags tag;
             *     Alternative Single tag:
             *     union {
             *       struct {
             *         T1 member1;
             *         T2 member2;
             *       }; //No name since no tag and hence access as members.member1
             *     } members;
             *
             *     Alternative Multi tag:
             *     union {
             *       struct {
             *         T1 member1;
             *         T2 member2;
             *       } firstTag;
             *       struct {
             *         T3 member3;
             *         T4 member4;
             *       } secondTag;
             *     } members;
             *     //each type tuple tagged type has a tag hence access as members.firstTag.member1
             *   };
             * First a flags element:
             *   Flags (true/false):
             *   0:0x01 on heap/on stack
             *   1:0x02 codegen (temporary created variable go ahead and steel the memory)/cal variable (must obey copy semantics)
             * Second is the tag as an enum type
             * Third is the CAL members inside a members struct element
             */
            s.println("");
            s.printlnInc("struct " + type.getName() + "_s {");
            s.println("uint32_t flags;");
            s.println("enum " + type.getName() + "_tags tag;");
            if(UtilIR.isSingleTagTuple(t)) {
                s.printlnInc("union {");
                s.printlnInc("struct {");
                Type struct = UtilIR.getType(type);
                for (Iterator<Variable> i = UtilIR.getMembers(struct).iterator(); i.hasNext();) {
                    Variable var = i.next();
                    s.print(new CBuildVarDeclaration(var,cenv,false).toStr());
                    s.println(";");
                }
                s.printlnDec("};"); 
                s.printlnDec("} members;"); 
            } else if(UtilIR.isMultiTagTuple(t)) {
                s.printlnInc("union {");
                for(TaggedTuple tag: ((TypeTuple)t).getTaggedTuples()) {
                    s.printlnInc("struct {");
                    for (Iterator<Variable> i = tag.getFields().iterator(); i.hasNext();) {
                        Variable var = i.next();
                        s.print(new CBuildVarDeclaration(var,cenv,false).toStr());
                        s.println(";");
                    }
                    s.printlnDec("} " + tag.getTag() + ";"); 
                }
                s.printlnDec("} members;"); 
            }
            s.printlnDec("};"); 
        } else {
            //Printed in network c-file
            if(UtilIR.isMultiTagTuple(UtilIR.getType(type))) {
                CodegenError.err("CPrintTop", "Not yet implemented tuple with multiple tags (3) ");
            }
            Type struct = UtilIR.getType(type);
            /*
             * Declaration of freeStruct function. 
             * This function is used to deep free any user type variable.
             * It is also used by the array handling methods when freeing
             * arrays.
             * src: variable to be freed
             * top: if also the top level should be freed or only deeper levels
             */
            s.printlnInc("int freeStruct" + type.getName() + "_t ("+ type.getName() + "_t * src, int top) {");
            for (Iterator<Variable> i = UtilIR.getMembers(struct).iterator(); i.hasNext();) {
                Variable var = i.next();
                if(UtilIR.isList(var.getType())) {
                    //Free any array members
                    s.print("free" + new CBuildTypeName(var.getType(), new CPrintUtil.dummyCB(), false).asNameStr() + "(&src->members." + new CBuildVarDeclaration(var,cenv,true).toStr() + ", TRUE)");
                    s.println(";");
                } else if(UtilIR.isSingleTagTuple(var.getType())) {
                    //Go deep into a user typed member
                    s.print("freeStruct" + new CBuildTypeName(var.getType(), new CPrintUtil.dummyCB(), false).asNameStr() + "(&src->members." + new CBuildVarDeclaration(var,cenv,true).toStr() + ", TRUE)");
                    s.println(";");
                } else if(UtilIR.isMultiTagTuple(var.getType())) {
                    CodegenError.err("CPrintTop", "Not yet implemented tuple with multiple tags (4) ");
                }

            }
            //If top and allocated on heap free it
            s.printlnInc("if(top && (src->flags&0x1)==0x1) {");
            s.println("free(src);");
            s.dec();
            s.println("}"); 
            s.println("return TRUE;");
            s.dec();
            s.println("}"); 
            s.println("");
            /*
             * Declaration of copyStruct function. 
             * This function is used to deep copy any user type variable.
             * It is also used by the array handling methods when copying
             * arrays.
             * src: pointer to variable to be copied
             * dst: pointer to pointer of destination (allocate if needed)
             */
            s.printlnInc("int copyStruct" + type.getName() + "_t ("+ type.getName() + "_t ** dst, "+ type.getName() + "_t * src) {");
            //if temp and on heap just steal the src
            s.printlnInc("if((src->flags&0x3)==0x3) {");
            //but make sure that we don't leak memory if dst already pointing to object
            s.printlnInc(  "if(*dst!=NULL) {");
            s.println(       "freeStruct" + type.getName() + "_t(*dst,TRUE);");
            s.printlnDec(  "}");
            s.println(     "*dst = src;");
            s.println(     "return TRUE;");
            s.printlnDec("}");
            //OK, we need to copy - make sure dst is allocated
            s.printlnInc("if(*dst==NULL) {");
            s.println(      "*dst = malloc(sizeof(**dst));");
            s.printlnDec("}");
            //Copy anything that is part of the struct and also falsely copy metadata for arrays
            s.println(   "memcpy(*dst,src,sizeof(**dst));");
            
            for (Iterator<Variable> i = UtilIR.getMembers(struct).iterator(); i.hasNext();) {
                Variable var = i.next();
                if(UtilIR.isList(var.getType())) {
                    //Copy any array members - but first make sure that the metadata is reset
                    String member = new CBuildVarDeclaration(var,cenv,true).toStr();
                    s.println("(*dst)->members." + member + ".flags = 0x0;");
                    s.println("copy" + new CBuildTypeName(var.getType(), new CPrintUtil.dummyCB(), false).asNameStr() + 
                            "(&(*dst)->members." + member + 
                            ", &src->members." + member + 
                            ", (__arrayArg){0,{}}" + 
                            ", (__arrayArg){0,{}}" + 
                            ", (__arrayArg){src->members." + member +".dim,{" +
                                "src->members." + member + ".sz[0]," + 
                                "src->members." + member + ".sz[1]," + 
                                "src->members." + member + ".sz[2]," + 
                                "src->members." + member + ".sz[3]" + 
                            "}});");
                } else if(UtilIR.isSingleTagTuple(var.getType())) {
                    //Go deep into a user typed member
                    //FIXME this is not correct
                    String member = new CBuildVarDeclaration(var,cenv,true).toStr();
                    s.println("copyStruct" + new CBuildTypeName(var.getType(), new CPrintUtil.dummyCB(), false).asNameStr() + 
                            "(&(*dst)->members." + member + 
                            ",&src->members." + member + 
                            ");");
                } else if(UtilIR.isMultiTagTuple(var.getType())) {
                    CodegenError.err("CPrintTop", "Not yet implemented tuple with multiple tags (4) ");
                }

            }
            s.println("return TRUE;");
            s.dec();
            s.println("}"); 
            s.println("");
        }
        leave(type);
        return s;
    }

}