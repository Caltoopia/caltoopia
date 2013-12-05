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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.caltoopia.codegen.CEnvironment;
import org.caltoopia.codegen.CodegenError;
import org.caltoopia.codegen.UtilIR;
import org.caltoopia.codegen.transformer.IrTransformer;
import org.caltoopia.codegen.transformer.TransUtil;
import org.caltoopia.codegen.transformer.analysis.IrTypeStructureAnnotation.TypeMember;
import org.caltoopia.ir.AbstractActor;
import org.caltoopia.ir.Annotation;
import org.caltoopia.ir.AnnotationArgument;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.FunctionCall;
import org.caltoopia.ir.Namespace;
import org.caltoopia.ir.Scope;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeList;
import org.caltoopia.ir.TypeUser;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableExternal;

public class CPrintUtil {
    //Annotation strings @extern(c_name,c_prefix,c_includepath="path1,path2",c_header="header1,header2",c_lib="lib1,lib2")
    private static final String EXTERN_ANNOTATION = "extern";
    private static final String EXTERN_ANNOTATION_CNAME = "c_name";
    private static final String EXTERN_ANNOTATION_CPREFIX = "c_prefix";
    private static final String EXTERN_ANNOTATION_CHEADER = "c_header";
    private static final String EXTERN_ANNOTATION_CSOURCES = "c_sources";
    private static final String EXTERN_ANNOTATION_CINCLUDEPATH = "c_includepath";
    private static final String EXTERN_ANNOTATION_CLIB = "c_lib";
    private static final String EXTERN_ANNOTATION_CLIBPATH = "c_libpath";
    private static final String EXTERN_ANNOTATION_CACTIVEMODE = "c_activemode";
    
    static Boolean isCActiveMode(AbstractActor actor) {
        Map<String,String> annotations = getExternAnnotations(actor.getAnnotations());
        if(annotations.containsKey(EXTERN_ANNOTATION_CACTIVEMODE)) {
            String value = annotations.get(EXTERN_ANNOTATION_CACTIVEMODE);
            if (value.equals("true"))
                return true;
        }
        return false;
    }

    private static final Set<String> keywords;
    static {
        Set<String> a = new HashSet<String>();
        a.add("asm");
        a.add("break");
        a.add("continue");
        a.add("enum");
        a.add("extern");
        a.add("struct");
        a.add("goto");
        a.add("inline");
        a.add("register");
        a.add("volatile");
        a.add("static");
        a.add("this");
        a.add("union");
        a.add("unsigned");
        a.add("signed");
        a.add("long");
        a.add("char");
        a.add("double");
        a.add("void");
        a.add("return");
        a.add("case");
        a.add("swith");
        keywords = Collections.unmodifiableSet(a);
    }
    
    static public String validCName(String name) {
        String ret = name;
        if(keywords.contains(name)) {
            ret=name+"__cal2c__";
        }
        ret = ret.replace("$", "__DOLLAR__");
        return ret;
    }

    
    static public String getNamespace(List<String> nsStrs) {
        String ns = "";
        for(String s:nsStrs) {
            ns += "__" + s;
        }
        return ns;
    }

    static public List<Annotation> collectAnnotations(VariableExternal var, Namespace ns) {
        List<Annotation> annotations = new ArrayList<Annotation>();
        annotations.addAll(var.getAnnotations());
        if(ns!=null) {
            annotations.addAll(ns.getAnnotations());
            while(ns.getOuter() instanceof Namespace) {
                ns = (Namespace) ns.getOuter();
                annotations.addAll(ns.getAnnotations());
            }
        }
        /*
        EObject obj = var;
        while(obj.eContainer()!=null) {
            if(obj instanceof AbstractActor) {
                annotations.addAll(((AbstractActor) obj).getAnnotations());
            }
            if(obj instanceof Namespace) {
                annotations.addAll(((Namespace) obj).getAnnotations());
            }
            obj=obj.eContainer();
        }
        */
        return annotations;
    }

    static public List<Annotation> collectActorAnnotations(AbstractActor var, Namespace ns) {
        List<Annotation> annotations = new ArrayList<Annotation>();
        annotations.addAll(var.getAnnotations());
        if(ns!=null) {
            annotations.addAll(ns.getAnnotations());
            while(ns.getOuter() instanceof Namespace) {
                ns = (Namespace) ns.getOuter();
                annotations.addAll(ns.getAnnotations());
            }
        }
        return annotations;
    }

    static public Map<String,String> getExternAnnotations(List<Annotation> annotations) {
        Map<String,String> args = new HashMap<String,String>();
        String path = null;
        for(Annotation a : annotations) {
            if(a.getName().equals("Project")) {
                for(AnnotationArgument aa : a.getArguments()) {
                    if(aa.getId().equals("name")) {
                        path = aa.getValue();
                        break;
                    }
                }
                if(path!=null)
                    break;
            }
        }
        if(path==null) {
            path="";
        }
        
        for(Annotation a : annotations) {
            if(a.getName().equals(EXTERN_ANNOTATION)) {
                for(AnnotationArgument aa : a.getArguments()) {
                    //Don't replace annotations closer to the function with namespace annotations
                    if(!args.containsKey(aa.getId())) {
                            String value = aa.getValue().replace("$PROJECTROOT", path);
                            args.put(aa.getId(), value);
                    //concat some annotation from different layers
                    } else if(aa.getId().equals(EXTERN_ANNOTATION_CINCLUDEPATH) || 
                              aa.getId().equals(EXTERN_ANNOTATION_CLIBPATH) || 
                              aa.getId().equals(EXTERN_ANNOTATION_CLIB) || 
                              aa.getId().equals(EXTERN_ANNOTATION_CSOURCES) ||
                              aa.getId().equals(EXTERN_ANNOTATION_CHEADER) ||
                              aa.getId().equals(EXTERN_ANNOTATION_CACTIVEMODE)) {
                        String value = aa.getValue().replace("$PROJECTROOT", path);
                        args.put(aa.getId(), args.get(aa.getId()) + "," + value);
                    }
                }
            }
        }
        return args;
    }
    
    static public String externalCName(Map<String,String> aargs, VariableExternal var) {
        String ret = "";
        if(aargs.containsKey(EXTERN_ANNOTATION_CPREFIX)) {
            ret += aargs.get(EXTERN_ANNOTATION_CPREFIX);
        }
        if(aargs.containsKey(EXTERN_ANNOTATION_CNAME)) {
            ret += aargs.get(EXTERN_ANNOTATION_CNAME);
        } else {
            ret += var.getName();            
        }
        return ret;
    }

    static public Set<String> externalCInclude(Map<String,String> aargs, VariableExternal var) {
        Set<String> ret = new HashSet<String>();
        if(aargs.containsKey(EXTERN_ANNOTATION_CHEADER)) {
            String headerstr = aargs.get(EXTERN_ANNOTATION_CHEADER);
            String[] headers = headerstr.split(" *, *");
            for(String str : headers) {
                ret.add(str);
            }
        }
        return ret;
    }

    static public void toEnvEnv(Map<String,String> aargs, CEnvironment env) {
        if(aargs.containsKey(EXTERN_ANNOTATION_CSOURCES)) {
            env.sources += aargs.get(EXTERN_ANNOTATION_CSOURCES) + ",";
        }
        if(aargs.containsKey(EXTERN_ANNOTATION_CINCLUDEPATH)) {
            env.includePaths += aargs.get(EXTERN_ANNOTATION_CINCLUDEPATH) + ",";
        }
        if(aargs.containsKey(EXTERN_ANNOTATION_CLIB)) {
            env.libraries += aargs.get(EXTERN_ANNOTATION_CLIB) + ",";
        }
        if(aargs.containsKey(EXTERN_ANNOTATION_CLIBPATH)) {
            env.libraryPaths += aargs.get(EXTERN_ANNOTATION_CLIBPATH) + ",";
        }
    }
    
    public static class dummyCB implements ITypeCallbacks {
        public String preTypeFn(Type type) {
            return "";
        }

        public String postTypeFn(Type type) {
            return "";
        }

        public String listTypeFn(TypeList type, int dim) {
            return "";
        }

        public String userTypeFn(TypeUser type) {
            return "";
        }
    }
    
    static public String createDeepSizeof(Scope body, Type type, CEnvironment cenv) {
        return createDeepSizeofInner(null,body, type, false, cenv);
    }

    static private String createDeepSizeofInner(String expr, Scope body, Type type, final boolean typeByRef, CEnvironment cenv) {
        if(expr==null)
            expr="";
        expr += "(";
        if(UtilIR.isSingleTagTuple(type)) {
            List<Variable> members = UtilIR.getMembers(UtilIR.getTypeDeclaration(type).getType());
            //each member
            for(int i=0;i<members.size();i++) {
                Variable member=members.get(i);
                TypeMember typeMember = TypeMember.valueOf(TransUtil.getAnnotationArg(member, IrTransformer.TYPE_ANNOTATION, "TypeStructure"));
                switch(typeMember) {
                case unknown:
                    CodegenError.err("Deep sizeof", "unknown placement of member " + member.getName());
                case scalarBuiltin:
                case scalarUserTypeFull:
                    break;
                case scalarUserType:
                case scalarUserTypeByRef:
                case listBuiltin:
                case listUserType:
                case listUserTypeFull:
                    expr += "(";
                    expr += createDeepSizeofInner(null,body,member.getType(),true, cenv);
                    expr += ")";
                    break;
                case dynListBuiltin:
                case dynListUserType:
                case dynListUserTypeFull:
                    expr += " + " + createDeepSizeofInner(null,body,member.getType(),false, cenv);
                    break;
                default:
                }
            }
        } else if(UtilIR.isTuple(type)) {
            CodegenError.err("CPrintUtil", "Not yet implemented tuple with multiple tags (1) ");
        }
        final CEnvironment fcenv = cenv;
        expr += new CBuildTypeName(type, new dummyCB(){
            @Override
            public String preTypeFn(Type type) {
                return "sizeof(";
            }

            @Override
            public String postTypeFn(Type type) {
                if(type instanceof TypeList && typeByRef) {
                    return "*))";
                }
                return "))";
            }

            @Override
            public String listTypeFn(TypeList type, int dim) {
                if(!typeByRef) {
                    return " * " + new CBuildExpression(type.getSize(), fcenv).toStr();
                }
                return "";
            }
        },true).toStr();
        return expr;
    }

}