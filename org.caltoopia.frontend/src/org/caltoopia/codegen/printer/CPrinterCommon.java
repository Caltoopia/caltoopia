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
 * This is the class for printing code common among
 * all actors from an annotated and transformed IR. It prints
 * the types, arrays definitions and helper functions into
 * header and c-code files.
 * 
 * Quality: 4, should work
 */
public class CPrinterCommon extends IrSwitch<Stream> {

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
     * Constructor for printing common header and c-files.
     * 
     * session: session info
     * cenv: empty input variable to collect information 
     *       needed in makefiles etc from all the CBuilders
     */
    public CPrinterCommon(Network network, CompilationSession session, CEnvironment cenv) {
        PrintStream out = session.getOutputStream();
        String nsName;
        String file;
        String baseName;
        this.session = session;
        this.out = session.getOutputStream();
        this.cenv = cenv;

        file = session.getOutputFolder() + File.separator + "commonSpec";
        s = new Stream(file +".h");
        out.println("Writing '" + file +".h" + "'");
        //Print header file
        header = true;
        doSwitch(network);
        s.close();
        
        s = new Stream(file + ".c");
        out.println("Writing '" + file + ".c'");
        //Print network c-code file
        header = false;
        doSwitch(network);
        cenv.sourceFiles.add("commonSpec.c");
        s.close();
    }

    //---------------------------------------------------------------------
    
    private void enter(EObject obj) {
    }
    private void leave(EObject obj) {
    }
    
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
                break;
            default:
            }
        }
        
        for(String str : cHeaders) {
            s.println("#include \""+str+"\"");
        }        
    }

    /*
     * Print the common parts.
     * This function can print either header, c-code
     * or type header file based on how the class
     * variables header and typeHeader are set.
     * 
     * network: top network
     * Quality: 4, should work.
     */
    @Override
    public Stream caseNetwork(Network network) {
        s.println("// " + Util.marshallQualifiedName(network.getType().getNamespace()) + network.getType().getName());
        if(header) {
            //Printing all list types
            String name = Util.marshallQualifiedName(network.getType().getNamespace()) + "__" + network.getType().getName();
            name = name.toUpperCase();
            name = "CAL_COMMON_" + name;
            s.println("#ifndef "+name);
            s.println("#define "+name);
            s.println("#include <stdint.h>");
            s.println("#include <stdlib.h>");
            s.println("#include <string.h>");
            s.println("#include \"actors-rts.h\"");
            s.println("#include \"natives.h\"");
            printCIncludes(network.getDeclarations());
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
            
            //The user type declarations should not have TYPE_DIRECT defined
            s.println("#undef TYPE_DIRECT");
            
            /*
             * Print typedef definition of user type struct for all user types,
             * since we need to be able to declare pointers to them in the arrays,
             * but later the types themselves needs the array definitions. Hence,
             * this breaks the circular definitions also needed later by types
             * that contain fields to themselves.
             */
            for(Declaration d : network.getDeclarations()) {
                VarType varType = VarType.valueOf(TransUtil.getAnnotationArg(d, IrTransformer.VARIABLE_ANNOTATION, "VarType"));
                switch(varType) {
                case declarationType:
                    s.println("typedef struct " + ((TypeDeclaration)d).getName() + "_s " + ((TypeDeclaration)d).getName() + "_t;");
                }
            }
            
            /*
             * Print *array* metadata struct and 
             * array methods (by including __arrayCopy.h" for all user types.
             * Also the single user type operations are defined here due to
             * used inside __arrayCopy.h.
             */
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
                    for(TaggedTuple tt: ((TypeTuple)UtilIR.getType(d)).getTaggedTuples()) {
                        boolean single = UtilIR.isSingleTagTuple(UtilIR.getType(d));
                        s.print("int construct" + (single?t:((TypeDeclaration)d).getName()+"___"+tt.getTag()) + "("+ t + " ** dst");
                        for (Iterator<Variable> i = tt.getFields().iterator(); i.hasNext();) {
                            Variable var = i.next();
                            s.print(", " + new CBuildVarDeclaration(var,cenv,false).toStr());
                        }
                        s.println(");");
                    }
                    s.println("#define TYPE " + t);
                    s.println("#include \"__arrayCopy.h\"");
                }
            }

            //Printing all declarations
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
            leave(network);
            return s;
        } else {
            //Printing the common parts into one c-code file
            s.println("#include \"commonSpec.h\"");
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
        }

        leave(network);
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
            boolean single = UtilIR.isSingleTagTuple(struct);
            if(!single) {
                s.println("switch(src->tag) {");
            }            
            for(TaggedTuple tt: ((TypeTuple)struct).getTaggedTuples()) {
                String tag = single?"":tt.getTag()+".";
                if(!single) {
                    s.printlnInc("case " + type.getName()+"___"+tt.getTag() + ":");
                }
                for (Iterator<Variable> i = tt.getFields().iterator(); i.hasNext();) {
                    Variable var = i.next();
                    if(UtilIR.isList(var.getType())) {
                        //Free any array members
                        s.print("free" + new CBuildTypeName(var.getType(), new CPrintUtil.dummyCB(), false).asNameStr() + "(&src->members." + tag + new CBuildVarDeclaration(var,cenv,true).toStr() + ", TRUE)");
                        s.println(";");
                    } else if(UtilIR.isTuple(var.getType())) {
                        //Go deep into a user typed member
                        s.print("freeStruct" + new CBuildTypeName(var.getType(), new CPrintUtil.dummyCB(), false).asNameStr() + "(src->members." + tag + new CBuildVarDeclaration(var,cenv,true).toStr() + ", TRUE)");
                        s.println(";");
                    }
                }
                if(!single) {
                    s.println("break;");
                    s.dec();
                }
            }
            if(!single) {
                s.printlnInc("default:");
                s.println("break;");
                s.dec();
                s.println("}");
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
            s.println(   "int flags;");
            s.printlnInc("if(*dst==NULL) {");
            s.println(      "*dst = malloc(sizeof(**dst));");
            s.println(      "flags = 0x1;");
            s.printlnDec("} else {");
            s.inc();
            s.println(      "flags = (*dst)->flags;");
            s.printlnDec("}");
            //Copy anything that is part of the struct and also falsely copy metadata for arrays
            s.println(   "memcpy(*dst,src,sizeof(**dst));");
            s.println(   "(*dst)->flags = flags;");
            if(!single) {
                s.println("switch(src->tag) {");
            }            
            for(TaggedTuple tt: ((TypeTuple)struct).getTaggedTuples()) {
                String tag = single?"":tt.getTag()+".";
                if(!single) {
                    s.printlnInc("case " + type.getName()+"___"+tt.getTag() + ":");
                }
                for (Iterator<Variable> i = tt.getFields().iterator(); i.hasNext();) {
                    Variable var = i.next();
                    if(UtilIR.isList(var.getType())) {
                        //Copy any array members - but first make sure that the metadata is reset
                        String member = new CBuildVarDeclaration(var,cenv,true).toStr();
                        s.println("(*dst)->members." + tag + member + ".flags = 0x0;");
                        s.println("copy" + new CBuildTypeName(var.getType(), new CPrintUtil.dummyCB(), false).asNameStr() + 
                                "(&(*dst)->members." + tag + member + 
                                ", &src->members." + tag + member + 
                                ", (__arrayArg){0,{}}" + 
                                ", (__arrayArg){0,{}}" + 
                                ", (__arrayArg){src->members." + tag + member +".dim,{" +
                                    "src->members." + tag + member + ".sz[0]," + 
                                    "src->members." + tag + member + ".sz[1]," + 
                                    "src->members." + tag + member + ".sz[2]," + 
                                    "src->members." + tag + member + ".sz[3]" + 
                                "}});");
                    } else if(UtilIR.isSingleTagTuple(var.getType())) {
                        //Go deep into a user typed member
                        //FIXME this is not correct
                        String member = new CBuildVarDeclaration(var,cenv,true).toStr();
                        s.println("copyStruct" + new CBuildTypeName(var.getType(), new CPrintUtil.dummyCB(), false).asNameStr() + 
                                "(&(*dst)->members." + tag + member + 
                                ",&src->members." + tag + member + 
                                ");");
                    }
                }
                if(!single) {
                    s.println("break;");
                    s.dec();
                }
            }
            if(!single) {
                s.printlnInc("default:");
                s.println("break;");
                s.dec();
                s.println("}");
            }
            s.println("return TRUE;");
            s.dec();
            s.println("}"); 
            s.println("");
            /*
             * Construct user type
             */
            /*
            int constructT1_t(T1_t ** dst, int a, int b, __array4int32_t c) {
                if(*dst==NULL) {
                  *dst = malloc(sizeof(**dst));
                }
                (*dst)->flags = 0x1;
                (*dst)->members.a = a;
                (*dst)->members.b = b;
                copyint32_t(&(*dst)->members.c, &c, (__arrayArg){0,{}}, (__arrayArg){0,{}}, (__arrayArg){c.dim,{c.sz[0],c.sz[1],c.sz[2],c.sz[3]}});
                return TRUE;
              }
            */
            for(TaggedTuple tt: ((TypeTuple)struct).getTaggedTuples()) {
                String tag = single?"":tt.getTag()+".";
                s.print("int construct" + type.getName() + "_"+(single?"t":"__"+tt.getTag())+"("+ type.getName() + "_t ** dst");
                List<Variable> members =  tt.getFields();
                for (Iterator<Variable> i = members.iterator(); i.hasNext();) {
                    Variable var = i.next();
                    s.print(", " + new CBuildVarDeclaration(var,cenv,false).toStr());
                }
                s.printlnInc(") {");
                s.printlnInc("if(*dst==NULL) {");
                s.println(      "*dst = malloc(sizeof(**dst));");
                s.printlnDec("}");
                s.println(   "(*dst)->flags = 0x1;");
                s.println(   "(*dst)->tag = " + (single?"0":type.getName() + "___" + tt.getTag()) + ";");
                for (Iterator<Variable> i = members.iterator(); i.hasNext();) {
                    Variable var = i.next();
                    String member = new CBuildVarDeclaration(var,cenv,true).toStr();
                    if(UtilIR.isList(var.getType())) {
                        //Copy array members
                        s.println("copy" + new CBuildTypeName(var.getType(), new CPrintUtil.dummyCB(), false).asNameStr() + 
                                "(&(*dst)->members." + tag+member + 
                                ", &" + member + 
                                ", (__arrayArg){0,{}}" + 
                                ", (__arrayArg){0,{}}" + 
                                ", (__arrayArg){" + member +".dim,{" +
                                    member + ".sz[0]," + 
                                    member + ".sz[1]," + 
                                    member + ".sz[2]," + 
                                    member + ".sz[3]" + 
                                "}});");
                    } else if(UtilIR.isTuple(var.getType())) {
                        //Go deep into a user typed member
                        s.println("copyStruct" + new CBuildTypeName(var.getType(), new CPrintUtil.dummyCB(), false).asNameStr() + 
                                "(&(*dst)->members." + tag + member + 
                                ", " + member + 
                                ");");
                    } else {
                        s.println("(*dst)->members." + tag + member + " = " + member + ";");
                    }
                }
                s.println("return TRUE;");
                s.dec();
                s.println("}"); 
                s.println("");
            }
        }
        leave(type);
        return s;
    }

}