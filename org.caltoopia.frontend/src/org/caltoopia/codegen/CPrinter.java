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

package org.caltoopia.codegen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.caltoopia.ast2ir.Instantiator;
import org.caltoopia.ast2ir.Stream;
import org.caltoopia.ast2ir.Util;
import org.eclipse.emf.ecore.EObject;
import org.caltoopia.ast2ir.PriorityGraph;
import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.cli.DirectoryException;
import org.caltoopia.ir.AbstractActor;
import org.caltoopia.ir.Action;
import org.caltoopia.ir.Actor;
import org.caltoopia.ir.ActorInstance;
import org.caltoopia.ir.Annotation;
import org.caltoopia.ir.AnnotationArgument;
import org.caltoopia.ir.Assign;
import org.caltoopia.ir.BinaryExpression;
import org.caltoopia.ir.Block;
import org.caltoopia.ir.BooleanLiteral;
import org.caltoopia.ir.Connection;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.FloatLiteral;
import org.caltoopia.ir.ForEach;
import org.caltoopia.ir.ForwardDeclaration;
import org.caltoopia.ir.FunctionCall;
import org.caltoopia.ir.Generator;
import org.caltoopia.ir.IfExpression;
import org.caltoopia.ir.IrFactory;
import org.caltoopia.ir.LambdaExpression;
import org.caltoopia.ir.PortPeek;
import org.caltoopia.ir.ProcExpression;
import org.caltoopia.ir.TypeLambda;
import org.caltoopia.ir.TypeProc;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.Guard;
import org.caltoopia.ir.IfStatement;
import org.caltoopia.ir.IntegerLiteral;
import org.caltoopia.ir.ListExpression;
import org.caltoopia.ir.Member;
import org.caltoopia.ir.Namespace;
import org.caltoopia.ir.Network;
import org.caltoopia.ir.Point2PointConnection;
import org.caltoopia.ir.Port;
import org.caltoopia.ir.PortInstance;
import org.caltoopia.ir.PortRead;
import org.caltoopia.ir.PortWrite;
import org.caltoopia.ir.ProcCall;
import org.caltoopia.ir.ReturnValue;
import org.caltoopia.ir.State;
import org.caltoopia.ir.Statement;
import org.caltoopia.ir.StringLiteral;
import org.caltoopia.ir.TaggedExpression;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.TypeBool;
import org.caltoopia.ir.TypeConstructor;
import org.caltoopia.ir.TypeConstructorCall;
import org.caltoopia.ir.TypeDeclaration;
import org.caltoopia.ir.TypeExternal;
import org.caltoopia.ir.TypeFloat;
import org.caltoopia.ir.TypeInt;
import org.caltoopia.ir.TypeList;
import org.caltoopia.ir.TypeString;
import org.caltoopia.ir.TypeRecord;
import org.caltoopia.ir.TypeUser;
import org.caltoopia.ir.TypeUint;
import org.caltoopia.ir.TypeUndef;
import org.caltoopia.ir.UnaryExpression;
import org.caltoopia.ir.VariableExternal;
import org.caltoopia.ir.VariableImport;
import org.caltoopia.ir.VariableReference;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.ir.WhileLoop;
import org.caltoopia.ir.util.IrSwitch;

public class CPrinter extends IrSwitch<Stream> {

	Stream s;
	String outputFile;
	
	String actorId = "";
	Actor actor = null;
	
	String thisStr="";
	
	boolean systemc;
	CEnvironment cenv;
	
	Set<String> actorStructDecl = new HashSet<String>();
	Set<String> actorFuncDecl = new HashSet<String>();
	Set<String> networkConstDecl = new HashSet<String>();
	Set<String> actorConstDecl = new HashSet<String>();
	
	private Network topNetwork = null;
	private IR2CIR cir = null;
	private boolean topHeader = false;
	
	boolean initialToken=false;
	
	boolean debugPrint=true;
	
	boolean rangechk=false;
	
	Map<String, String> inputPortMap = new HashMap<String, String>();
	
	Map<String, String> outputPortMap = new HashMap<String, String>();
	
	private Stack<EObject> hierarchy = new Stack<EObject>();
	
	Set<String> includes = new HashSet<String>();
	
	List<TaggedExpression> actualParameters;
	
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
	
	private static String validCName(String name) {
		String ret = name;
		if(keywords.contains(name)) {
			ret=name+"__cal2c__";
		}
		ret = ret.replace("$", "__DOLLAR__");
		return ret;
	}

	private void enter(EObject o) {
		hierarchy.push(o);
	}
	
	private void leave() {
		hierarchy.pop();		
	}

	private EObject parent() {
		return hierarchy.elementAt(hierarchy.size()-2);		
	}
	
	private boolean isConstDecl(Declaration v) {
		return networkConstDecl.contains(v.getId());
	}
	private boolean isActorConstDecl(Declaration v) {
		return actorConstDecl.contains(v.getId());
	}
	
	private boolean withinDeclaration() {
		for(int i = hierarchy.size()-1; i>=0; i--) {
			if(hierarchy.get(i) instanceof Variable && UtilIR.isNormalVariable((Variable) hierarchy.get(i)) && ((Variable) hierarchy.get(i)).getInitValue()!=null) {
				return true;
			}
		}
		return false;
	}
	
	public CPrinter(String outputPath, List<TaggedExpression> actualParameters, Network top, IR2CIR inCir, boolean systemC, CEnvironment env, boolean debugPrint, boolean rangechk) {
		this.outputFile = outputPath;
		this.actualParameters = actualParameters;
		this.topNetwork = top;
		this.cir = inCir;
		this.systemc = systemC;
		this.cenv=env;
		this.debugPrint=debugPrint;
		this.rangechk = rangechk;
		hierarchy.clear();

		if(outputFile!=null) {
			s = new Stream(outputFile);				
		} else {
			s = new Stream();
		}
		
		if (actualParameters == null) {
			// Create an empty one so that we can avoid testing
			actualParameters = new ArrayList<TaggedExpression>();
		}
	}

	public CPrinter(String outputPath, List<TaggedExpression> actualParameters, Network top, IR2CIR inCir) {
		this(outputPath, actualParameters, top, inCir, false);
	}
	
	public CPrinter(String outputPath, List<TaggedExpression> actualParameters, Network top, IR2CIR inCir, boolean systemC) {
		this(outputPath, actualParameters, top, inCir, systemC, new CEnvironment(),true,false);
	}
	
	public CPrinter(String outputPath, List<TaggedExpression> actualParameters, Network top, IR2CIR inCir, CEnvironment env) {
		this(outputPath, actualParameters, top, inCir, false, env,true,false);
	}

	private void listsSize(Type type) {
		if (UtilIR.isList(type)) {
			s.print(" * ");		
			doSwitch(((TypeList) type).getSize());
			listsSize(((TypeList) type).getType());
		}		
	}
	@Override
	public Stream caseActorInstance(ActorInstance actor) {
		enter(actor);
		thisStr = Util.marshallQualifiedName(((TypeActor) actor.getType()).getNamespace()) +"_" + actor.getName();
		//Instantiate and const evaluate actor
 		Actor ia = (Actor) Instantiator.instantiate(actor, topNetwork);
		//Do IR2CIR conversion followed by C-printing the actor
		if(cir!=null)
			caseActor((Actor) cir.doSwitch(ia));
		else
			System.err.println("CPrinting actor " + thisStr +" failed due to that IR2CIR object was not valid!");
		leave();
		return s;
	}
	
	private void printCIncludes(List<Declaration> declarations) {
		Set<String> cHeaders = new HashSet<String>();
		
		for(Declaration d : declarations) {
			if(d instanceof VariableImport) {
				VariableImport dimport = (VariableImport) d;
				Declaration decl = null;
				if(!dimport.getName().startsWith("dprint")) {
					try {
						decl = ActorDirectory.findVariable(dimport);
					} catch (DirectoryException ee) {
						System.err.println("[CPrinter] Could not find imported declaration " + dimport.getName());
					}
					if(decl instanceof VariableExternal) {
						VariableExternal e = (VariableExternal) decl;
						Namespace ns = null;
						try {
							ns = ActorDirectory.findNamespace(dimport.getNamespace());
						} catch (DirectoryException ee) {}
						Map<String,String> annotations = getExternAnnotations(collectAnnotations(e,ns));
						cHeaders.addAll(externalCInclude(annotations,e));
						toEnv(annotations);
					}
				}
			}
		}
		
		for(String str : cHeaders) {
			s.println("#include \""+str+"\"");
		}		
	}
	
	private Boolean isCActiveMode(AbstractActor actor) {
		Map<String,String> annotations = getExternAnnotations(actor.getAnnotations());
		if(annotations.containsKey(EXTERN_ANNOTATION_CACTIVEMODE)) {
			String value = annotations.get(EXTERN_ANNOTATION_CACTIVEMODE);
			if (value.equals("true"))
				return true;
		}
		return false;
	}
	
	@Override
	public Stream caseActor(Actor actor) {
		enter(actor);
		actorStructDecl.clear();
		actorFuncDecl.clear();
		actorConstDecl.clear();
		this.actor = actor;
		if(!systemc){
			s.println("#include \"actors-rts.h\"");
			s.println("#include \"natives.h\"");
		}
		s.println("#include \"" + Util.marshallQualifiedName(topNetwork.getType().getNamespace()) + "__" + topNetwork.getType().getName() + ".h\"");

		actorId = "ActorInstance_" + thisStr;
		
		printCIncludes(actor.getDeclarations());
		
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


		s.println("//Actor constants");
		for (Declaration decl : actor.getDeclarations()) {
			if (UtilIR.isNormalConstant(decl)) {
				UtilIR.tag(decl,"const", true);
				actorConstDecl.add(decl.getId());
				s.print("static const ");
				doSwitch(decl);
			}
		}	
	
		s.println();
		
		if(systemc){
			s.println("static void " + actorId + "_constructor(ActorState *);");
			s.println();
		}
		
		s.println("//Actor state");
		if(!systemc){
			s.printlnInc("typedef struct {");
			s.println("AbstractActorInstance base;");
		}else{
			s.printlnInc("ACTOR_CLASS_HEADER(" + actorId + ")");
			s.println("int executionTime;");
		}
		
		s.println("int _fsmState;"); 
		for (Declaration decl : actor.getDeclarations()) {
			if (UtilIR.isNormalDef(decl) || UtilIR.isNormalInit(decl)) {
				UtilIR.tag(decl,"const", false);
				actorStructDecl.add(decl.getId());
				doSwitch(((Variable)decl).getType());
				if(UtilIR.isList(UtilIR.getType(((Variable)decl).getType()))) {
					Type type = ((Variable)decl).getType();
					while(type instanceof TypeList) {
						type = ((TypeList) type).getType();
						s.print("*");
					}
				} else if(UtilIR.isRecord(UtilIR.getType(((Variable)decl).getType()))) {
						s.print("*");
				}
				s.println(" " + validCName(((Variable)decl).getName()) + ";");
			}
		}

		if(!systemc){
			s.printlnDec("} " + actorId + ";");
			s.println();
		}else{
			s.printlnDec("ACTOR_INDEXING(" + actorId + ")");
			s.println();
			s.println("ACTOR_REGISTER(" + actorId + ")");
			s.println();
		}
		
		s.println("//Actor functions");
		for (Declaration decl : actor.getDeclarations()) {
			if (UtilIR.isLocalFunc(decl)) {
				actorFuncDecl.add(decl.getId());
				doSwitch(decl);
			}
		}	

		if(!systemc){
			s.println("ART_ACTION_CONTEXT(" + actor.getInputPorts().size() + ", " + actor.getOutputPorts().size() + ")");
			s.println();
		}

		for (Action a : actor.getActions()) {
			s.println("ART_ACTION(" + a.getId() + ", " + actorId + ");");
		}
		s.println("ART_ACTION_SCHEDULER(" + thisStr + "_action_scheduler);");
		if(!systemc){
			s.println("static void " + actorId + "_constructor(AbstractActorInstance *);");
		}
		s.println();
		

		s.printlnInc("static const PortDescription inputPortDescriptions[]={");
		for (int i = 0; i < actor.getInputPorts().size(); i++) {
			Port p = actor.getInputPorts().get(i);
			Type type=p.getType();
			if(UtilIR.isRecord(type)) {
				s.print("{1, \"" + p.getName() + "\", ");
				doSwitch(cir.createDeepSizeof(null, type));
			} else {
				s.print("{0, \"" + p.getName() + "\", sizeof(");
				doSwitch(type);
				s.print(")");
			}
			listsSize(type);
			if (i < actor.getInputPorts().size()) {
				s.println("},");			
			} else {
				s.println("}");							
			}
		}
		s.printlnDec("};");

		s.printlnInc("static const PortDescription outputPortDescriptions[]={");
		for (int i = 0; i < actor.getOutputPorts().size(); i++) {
			Port p = actor.getOutputPorts().get(i);			
			Type type=p.getType();
			
			if(UtilIR.isRecord(type)) {
				s.print("{1, \"" + p.getName() + "\", ");
				doSwitch(cir.createDeepSizeof(null, type));
			} else {
				s.print("{0, \"" + p.getName() + "\", sizeof(");
				doSwitch(type);
				s.print(")");
			}
			listsSize(type);
			if (i < actor.getOutputPorts().size()) {
				s.println("},");			
			} else {
				s.println("}");							
			}
		}
		s.printlnDec("};");
		s.println();
		
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
				doSwitch(rate);

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
				doSwitch(rate);
				
				if (i.hasNext()) s.print(", ");

			}
			s.println("};");
			s.println();	
		}

		s.printlnInc("static const ActionDescription actionDescriptions[] = {");
		for (Action action : actor.getActions()) {
			s.println("{\"" + action.getId() + "\", portRate_in_" + action.getId() + ", portRate_out_" + action.getId() + "},");
		}
		s.printlnDec("};");
		s.println();	
		
		if(!systemc){
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
		}
		
		for (Action a : actor.getActions()) {
			doSwitch(a);
		}
		
		if(!actor.getInitializers().isEmpty()) {
            for(Action a : actor.getInitializers()) {
            	if(!a.getOutputs().isEmpty()) {
            		initialToken=true;
            		doSwitch(a);
            	}
            }
        }

		int i = 0;
 		for (State state : actor.getSchedule().getStates()) {
 			s.println("#define " + actorId + "__" + state.getName() + "_ID " + i++);
 		}

 		if(!systemc){
 			s.printlnInc("static void " + actorId + "_constructor(AbstractActorInstance *pBase) {");
 			s.println(actorId + " *thisActor=(" + actorId + "*) pBase;");
 		}
 		else{
 			s.printlnInc("static void " + actorId + "_constructor(ActorState *pBase) {");
 			s.println(actorId + " *thisActor=(" + actorId + "*) pBase;");
 			int executionTime=1;
 			 for (org.caltoopia.ir.Annotation a : actor.getAnnotations()) {
 				if (a.getName().equals("CALSim")) {
 					for (org.caltoopia.ir.AnnotationArgument arg : a.getArguments()) {
 						if (arg.getId().equals("WCET")) {
 							executionTime=(Integer.parseInt(arg.getValue()));
 						}
 					}
 				}
 			}
 			s.println("thisActor->executionTime =" + executionTime + ";");
 		}
		if(actor.getSchedule().getInitialState()!=null) {
			s.println("thisActor->_fsmState = " + actorId + "__" + actor.getSchedule().getInitialState().getName() + "_ID;//Initial state"); 
		} else if(!actor.getSchedule().getStates().isEmpty()){
			s.println("thisActor->_fsmState = " + actorId + "__" + actor.getSchedule().getStates().get(0).getName() + "_ID;//First state"); 
		}
		doSwitch(cir.actorConstructor.get(actor));

		if(!actor.getInitializers().isEmpty()) {
            for(Action a : actor.getInitializers()) {
            	if(a.getOutputs().isEmpty()) {
	            	s.println("{");
	            	s.inc();
	            	for(Declaration d : a.getDeclarations()) {
	            		doSwitch(d);
	            	}
	            	for(Statement d : a.getStatements()) {
	            		doSwitch(d);
	            	}
	            	s.dec();
	            	s.println("}");
            	}
            }
        }
		
		if(isCActiveMode(actor)) 
		{
           	s.println("{");
           	s.inc();
           	s.println("ActorClass *actorClass = (ActorClass *)thisActor->base.actorClass;");
           	s.println("actorClass->actorExecMode = 1;");
        	s.dec();
        	s.println("}");           	
		}

		s.dec();
		s.println();
		s.println("}");
		
		doActionScheduler(actor);

		if(systemc){
			for(Action a : actor.getActions()){
	 			s.printlnInc("bool "+a.getId()+"GuardCondition(ActorContext *context, "+actorId+" *thisActor) {");
	 			if (!a.getGuards().isEmpty()) {
					s.print("return ");
					for (Iterator<Guard> ii = a.getGuards().iterator(); ii.hasNext();) {
						Guard f = ii.next();
						s.print(thisStr + validCName(f.getId()) + "(context, thisActor)");
						if (ii.hasNext()) s.print(" && ");
					}			
					s.println(";");
				}
	 			else
					s.println("return true;");
				s.printlnDec("}");
				s.println();
	 		}

		 	s.printlnInc("int "+ actorId + "::getExecutionTime(){");
		 	s.printlnDec("return executionTime;");
		 	s.printlnDec("}");
		 
			s.printlnInc("bool "+ actorId + "::checkActionGuardCondition(std::string a){");
			for (Action a : actor.getActions()) {
				s.printlnInc("if(a==\""+ a.getId()+"\"){");
				s.print("return ");
				s.println(a.getId()+"GuardCondition(context, this);");
				
				s.printlnDec("}");
			}
			s.println("cout<<\"GuardCondition: Action not known!\"<<endl;");
			s.println("return false;");
			s.printlnDec("}");
			s.println();
			
			s.printlnInc("void "+ actorId + "::runAction(std::string a){");
			for (Action a : actor.getActions()) {
				s.printlnInc("if(a==\""+ a.getId()+"\"){");
				s.println(a.getId()+"(context, this);");
				s.println("return;");
				s.printlnDec("}");
			}
			s.println("cout<<\"RunAction: Action not known!\"<<endl;");
			s.printlnDec("}");
			s.println();
			
			s.println("ACTOR_INDEXING_DEFINITION(" + actorId + ")");
			s.println();
		}
		leave();
		return s;
	}

	@Override
	public Stream caseNetwork(Network network) {
		enter(network);
		networkConstDecl.clear();
		// Here we assume that the network has been elaborated, and thus is flat.
		if(!systemc){
			s.println("#include \"actors-rts.h\"");
			s.println("#include \"natives.h\"");
		}
		else
			s.println("#include \"include/basic.h\"");

		if(UtilIR.containsTagBool(actualParameters, "header")) {
			//Printing all declarations as externs into one header file
			topHeader=true;
			String name = Util.marshallQualifiedName(topNetwork.getType().getNamespace()) + "__" + topNetwork.getType().getName();
			name = name.toUpperCase();
			name = "CAL_TOP_NETWORK_" + name;
			s.println("#ifndef "+name);
			s.println("#define "+name);

			printCIncludes(network.getDeclarations());

			for(Declaration d : network.getDeclarations()) {
				doSwitch(d);
				networkConstDecl.add(d.getId());
			}
			s.println("#endif");
		} else {
			//Printing the actual network into one c-code file
			s.println("#include \"" + Util.marshallQualifiedName(topNetwork.getType().getNamespace()) + "__" + topNetwork.getType().getName() + ".h\"");
			if(systemc){
				s.println("DEFINE_ACTORS_MAP");
			}
			for(Declaration d : network.getDeclarations()) {
				if(!(d instanceof TypeDeclaration) && !networkConstDecl.contains(d.getId()))
					doSwitch(d);
				networkConstDecl.add(d.getId());
			}
			if(!systemc){
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
						if(type.getName().startsWith("art_")) {
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
								/* TODO if want to use other than string param don't know how to detect this
		                        if(param.getExpression().getType() instanceof TypeString) {
		                                s.print("char");
		                        } else {
		                                doSwitch(param.getExpression().getType());
		                        }
		                        s.print(" tmp__" + actorInstanceName + "_" + param.getTag());
		                        if(param.getExpression().getType() instanceof TypeString) {
		                                s.print("[]");
		                        }
		                        s.print("=");
		                        doSwitch(param.getExpression());
		                        s.println(";");
		                        s.print("setParameterBytes(" + actorInstanceName + ", \"" + param.getTag() + "\", (void *) &tmp__" + actorInstanceName + "_" + param.getTag());
		                        if(param.getExpression().getType() instanceof TypeString) {
		                                s.print(", strlen(tmp__" + actorInstanceName + "_" + param.getTag()+")");
		                        } else {
		                                s.print(", sizeof(");
		                                doSwitch(param.getExpression().getType());
		                                s.print(")");
		                        }
		                        s.println(");");
		                        */
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
				}
				  		
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
	public Stream caseAction(Action action) {
		enter(action);
		
		for (Guard guard : action.getGuards()) {
			caseGuard(guard);
		}
		s.println();
		
		// The c-action a name, use tag + other id. 
		String actionId = "";
		for (Iterator<String> i = action.getTag().iterator(); i.hasNext();) {
			String s = i.next();
			actionId += s;
			if (i.hasNext()) actionId += ".";			
		}
				
		s.println("// " + actionId);
		s.printlnInc("ART_ACTION(" + action.getId() + ", " + actorId + ") {");
		
		for (Declaration v : action.getDeclarations() ) {
			doSwitch(v);
		}
		
		if(debugPrint)
			s.println("dprint(\"" + actorId + " " + action.getId() +"\\n\");");
		
		int idNbr = actor.getActions().indexOf(action);
		s.println("ART_ACTION_ENTER(" + action.getId() + ", " + idNbr + ");");
		
		for (Statement s : action.getStatements()) {
			doSwitch(s);
		}

		s.println("ART_ACTION_EXIT(" + action.getId() + ", " + idNbr + ");" );  

		s.printlnDec("}");
		s.println();
		leave();
		return s;
	}

	@Override
	public Stream caseIntegerLiteral(IntegerLiteral lit) {
		enter(lit);
		s.print(Long.toString(lit.getValue()));
		leave();
		return s;
	}

	@Override
	public Stream caseFloatLiteral(FloatLiteral lit) {
		enter(lit);
		s.print(Double.toString(lit.getValue()));
		leave();
		return s;
	}

	@Override
	public Stream caseBooleanLiteral(BooleanLiteral lit) {
		enter(lit);
		s.print(lit.isValue()?"1":"0");
		leave();
		return s;
	}

	@Override
	public Stream caseStringLiteral(StringLiteral lit) {
		enter(lit);
		s.print("\"" + lit.getValue() + "\"");
		leave();
		return s;
	}

	@Override
	public Stream caseListExpression(ListExpression lit) {
		enter(lit);
		s.print("{");
		for (Iterator<Expression> i = lit.getExpressions().iterator(); i.hasNext();) {
			Expression l = i.next();			
			doSwitch(l);
			if (i.hasNext()) s.printComma();
		}
		if(!lit.getGenerators().isEmpty())
			System.err.println("WHAT DO I DO WITH A GENERATOR???");
		s.print("}");
		leave();
		return s;
	}

	
	@Override
	public Stream caseVariableExpression(VariableExpression var) {
		enter(var);
		boolean constant = false;
		if(actorStructDecl.contains(var.getVariable().getId())) {
			s.print("thisActor->");
		} else if(var.getVariable() instanceof VariableImport) {
			s.print(Util.marshallQualifiedName(((VariableImport)var.getVariable()).getNamespace())+"_");
			constant=true;
		} else if(isConstDecl(var.getVariable())){
			s.print(Util.marshallQualifiedName(UtilIR.getAnnotatedNamespace(var.getVariable()))+"_");
			constant=true;
		} else if(isActorConstDecl(var.getVariable())) {
			constant=true;
		}
		s.print(validCName(var.getVariable().getName()));
		if (!var.getIndex().isEmpty()) {
			Type list = ((Variable)var.getVariable()).getType();
			for (Iterator<Expression> i = var.getIndex().iterator(); i.hasNext();) {
				Expression e = i.next();
				s.print("[");
				if(rangechk) {
					s.print("RANGECHK(");
				}
				doSwitch(e);
				if(rangechk) {
					s.print(",");
					doSwitch(((TypeList)list).getSize());
					s.print(")");
					list = ((TypeList)list).getType();
				}
				s.print("]");
			}
		}

		boolean hasIndex=var.getIndex().isEmpty()?false:true;
		for (Member m : var.getMember()) {
			s.print((constant||hasIndex)?".":"->");
			doSwitch(m);
			hasIndex = m.getIndex().isEmpty()?false:true;
		}
		leave();
		return s;
	}
	
	@Override
	public Stream caseVariableReference(VariableReference var) {
		enter(var);
		boolean constant = false;
		if(actorStructDecl.contains(var.getDeclaration().getId())) {
			s.print("thisActor->");
		} else if(var.getDeclaration() instanceof VariableImport) {
			s.print(Util.marshallQualifiedName(((VariableImport)var.getDeclaration()).getNamespace())+"_");
			constant=true;
		}
		s.print(validCName(var.getDeclaration().getName()));
		if (!var.getIndex().isEmpty()) {
			Type list = var.getDeclaration().getType();
			for (Iterator<Expression> i = var.getIndex().iterator(); i.hasNext();) {
				Expression e = i.next();
				s.print("[");
				if(rangechk) {
					s.print("RANGECHK(");
				}
				doSwitch(e);
				if(rangechk) {
					s.print(",");
					doSwitch(((TypeList)list).getSize());
					s.print(")");
					list = ((TypeList)list).getType();
				}
				s.print("]");
			}
		}
		boolean hasIndex=var.getIndex().isEmpty()?false:true;
		for (Member m : var.getMember()) {
			//s.print(hasIndex?".":"->");
			s.print((constant||hasIndex)?".":"->");
			doSwitch(m);
			hasIndex = m.getIndex().isEmpty()?false:true;
		}
		leave();
		return s;
	}

	@Override
	public Stream caseMember(Member member) {
		enter(member);
		s.print(validCName(member.getName()));
		if (!member.getIndex().isEmpty()) {
			Type list = member.getType();
			for (Iterator<Expression> i = member.getIndex().iterator(); i.hasNext();) {
				Expression e = i.next();
				s.print("[");
				if(rangechk) {
					s.print("RANGECHK(");
				}
				doSwitch(e);
				if(rangechk) {
					s.print(",");
					doSwitch(((TypeList)list).getSize());
					s.print(")");
					list = ((TypeList)list).getType();
				}
				s.print("]");
			}
		}
		
		leave();
		return s;
	}

	@Override
	public Stream caseBinaryExpression(BinaryExpression expr) {
		// Ugly hack starts
		// This is a solution JUST to get the test examples compiling
		// The '..' operator shall be not be around at this stage, 
		// but for that we need some dead code removal to clean it up.
		// So for, now we just print 'null'
		if (expr.getOperator().equals("..")) {
			s.print("0");
			return s;
		}
		// Ugly ends
		
		enter(expr);
		s.printLeft();
		doSwitch(expr.getOperand1());
		s.printSpace();
		printOperator(expr.getOperator());
		s.printSpace();
		doSwitch(expr.getOperand2());
		s.printRight();
		leave();
		return s;
	}

	private void printOperator(String operator) {
		if (operator.equals("=")) {
			s.print("==");
		} else if (operator.equals(":=")) {
			s.print("=");
		} else if (operator.equals("and")) {
			s.print("&&");
		} else if (operator.equals("or")) {
			s.print("||");
		} else if (operator.equals("bitand")) {
			s.print("&");
		} else if (operator.equals("bitor")) {
			s.print("|");
		} else if (operator.equals("not")) {
			s.print("!");
		} else if (operator.equals("mod")) {
			s.print("%");
		} else if (operator.equals(">>>")) {
			s.print(">>");
		} else {
			s.print(operator);
		} 			
		
	}

	@Override
	public Stream caseUnaryExpression(UnaryExpression expr) {
		enter(expr);
		s.printLeft();
		String operator = expr.getOperator();
		if (operator.equals("not")) {
			s.print("!");
		} else if (operator.equals("#")) {
			s.print("sizeof");
		} else if (operator.equals("old")) {
			s.print("");
			System.err.println("Usage of unary operator old is not supported!");
		} else {
			s.print(operator);
		} 			
		s.printLeft();
		doSwitch(expr.getOperand());
		s.printRight();
		s.printRight();
		
		leave();
		return s;
	}

	private List<Annotation> collectAnnotations(VariableExternal var, Namespace ns) {
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

	private Map<String,String> getExternAnnotations(List<Annotation> annotations) {
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
	
	private String externalCName(Map<String,String> aargs, VariableExternal var) {
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

	private Set<String> externalCInclude(Map<String,String> aargs, VariableExternal var) {
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
	
	private void toEnv(Map<String,String> aargs) {
		if(aargs.containsKey(EXTERN_ANNOTATION_CSOURCES)) {
			cenv.sources += aargs.get(EXTERN_ANNOTATION_CSOURCES) + ",";
		}
		if(aargs.containsKey(EXTERN_ANNOTATION_CINCLUDEPATH)) {
			cenv.includePaths += aargs.get(EXTERN_ANNOTATION_CINCLUDEPATH) + ",";
		}
		if(aargs.containsKey(EXTERN_ANNOTATION_CLIB)) {
			cenv.libraries += aargs.get(EXTERN_ANNOTATION_CLIB) + ",";
		}
		if(aargs.containsKey(EXTERN_ANNOTATION_CLIBPATH)) {
			cenv.libraryPaths += aargs.get(EXTERN_ANNOTATION_CLIBPATH) + ",";
		}
	}

	@Override
	public Stream caseFunctionCall(FunctionCall expr) {
		enter(expr);
		String id="";
		if(expr.getFunction() instanceof VariableExpression && ((VariableExpression)expr.getFunction()).getVariable() instanceof Variable) {
			Variable func = (Variable) ((VariableExpression) expr.getFunction()).getVariable();
			id = func.getId();
			s.print(thisStr + validCName(func.getName()));
		} else if(expr.getFunction() instanceof VariableExpression && ((VariableExpression)expr.getFunction()).getVariable() instanceof VariableExternal) {
			VariableExternal func = (VariableExternal) ((VariableExpression) expr.getFunction()).getVariable();
			id = func.getId();
			s.print(validCName(func.getName()));
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
				s.print(externalCName(annotations,e));
				toEnv(annotations);
			} else {
				id = func.getId();
				s.print(validCName(func.getName()));
			}
		} else if(expr.getFunction() instanceof VariableExpression && ((VariableExpression)expr.getFunction()).getVariable() instanceof ForwardDeclaration) {
			ForwardDeclaration func = (ForwardDeclaration) ((VariableExpression) expr.getFunction()).getVariable();
			id = func.getId();
			s.print(thisStr + validCName(func.getName()));
		} else {
			s.print("WHAT_IS_MY_NAME!??");
			throw new RuntimeException("Expected function decl in call to be of Variable type");
		}
		s.printLeft();
		if(actorFuncDecl.contains(id)) {
			s.print("thisActor");
			if(!expr.getParameters().isEmpty())
				s.print(", ");
		}
		for (int i = 0; i<expr.getParameters().size(); i++) {
			Expression p = expr.getParameters().get(i);
			doSwitch(p);
			if (i<expr.getParameters().size()-1) s.printComma();
		}
		s.printRight();
		leave();
		return s;
	}

	@Override
	public Stream caseTypeConstructorCall(TypeConstructorCall expr) {
		enter(expr);
		if(UtilIR.isLiteralExpression(expr)) {
			s.print("{");
			for(Iterator<Expression> i= ((TypeConstructorCall) expr).getParameters().iterator();i.hasNext();) {
				Expression e = i.next();
				doSwitch(e);
				if(i.hasNext()) s.printComma();
			}
			s.print("}");				
		} else {
			s.print(validCName(expr.getName()));
			s.print("(NULL, ");
			for (Iterator<Expression> i = expr.getParameters().iterator(); i.hasNext();) {
				Expression p = i.next();
				doSwitch(p);
				if (i.hasNext()) s.printComma();
			}
			s.printRight();
		}
		leave();
		return s;
	}

	@Override
	public Stream caseAssign(Assign assign) {
		enter(assign);

		if(assign.getExpression() instanceof TypeConstructorCall && !withinDeclaration()) {
			TypeConstructorCall expr = (TypeConstructorCall) assign.getExpression();
			s.print(validCName(expr.getName()));
			s.print("(");
			if(hasIndex(assign.getTarget())) s.print("&");
			doSwitch(assign.getTarget());
			s.print(", ");
			for (Iterator<Expression> i = expr.getParameters().iterator(); i.hasNext();) {
				Expression p = i.next();
				doSwitch(p);
				if (i.hasNext()) s.printComma();
			}
			s.printRight();
			s.println(";");
			leave();
			return s;
		}
		
		if(isIndir(assign.getTarget().getDeclaration())) s.print("*");
		doSwitch(assign.getTarget());
		s.print(" = ");
		doSwitch(assign.getExpression());
		s.println(";");
		leave();
		return s;
	}

	private boolean hasIndex(VariableReference v) {
		if(v.getMember().isEmpty()) {
			return !v.getIndex().isEmpty();
		} else {
			return !v.getMember().get(v.getMember().size()-1).getIndex().isEmpty();
		}
	}
	
	private boolean hasIndex(Expression v) {
		if(!(v instanceof VariableExpression))
			return false;
		if(((VariableExpression)v).getMember().isEmpty()) {
			return !((VariableExpression)v).getIndex().isEmpty();
		} else {
			return !((VariableExpression)v).getMember().get(((VariableExpression)v).getMember().size()-1).getIndex().isEmpty();
		}
	}
	
	@Override
	public Stream caseProcCall(ProcCall call) {
		enter(call);
		if(call.getProcedure() instanceof VariableImport &&	!call.getProcedure().getName().startsWith("dprint")) {
			VariableImport func = (VariableImport) call.getProcedure();
			Declaration f = null;
			try {
				f = ActorDirectory.findVariable(func);
			} catch (DirectoryException e) {
				System.err.println("[CPrinter] Could not find imported procedure " + func.getName());
			}
			if(f instanceof VariableExternal) {
				VariableExternal e = (VariableExternal) f;
				Namespace ns = null;
				try {
					ns = ActorDirectory.findNamespace(func.getNamespace());
				} catch (DirectoryException ee) {}
				Map<String,String> annotations = getExternAnnotations(collectAnnotations(e,ns));
				s.print(externalCName(annotations,e));
				toEnv(annotations);
			} else {
				if(!UtilIR.containsTagBool(call.getProcedure().getAttributes(), "CIR_func") && !(call.getProcedure() instanceof VariableExternal) &&
						!call.getProcedure().getName().startsWith("dprint")) {
					s.print(thisStr);
				}
				s.print(validCName(call.getProcedure().getName()));
			}
		} else {
			if(!UtilIR.containsTagBool(call.getProcedure().getAttributes(), "CIR_func") && !(call.getProcedure() instanceof VariableExternal) &&
					!call.getProcedure().getName().startsWith("dprint")) {
				s.print(thisStr);
			}
			s.print(validCName(call.getProcedure().getName()));			
		}
		s.printLeft();
		if(actorFuncDecl.contains(call.getProcedure().getId())) {
			s.print("thisActor");
			if(!call.getOutParameters().isEmpty() || !call.getInParameters().isEmpty())
				s.print(", ");
		}
		for (int i = 0; i<call.getInParameters().size(); i++) {
			Expression p = call.getInParameters().get(i);
			if(call.getProcedure() instanceof VariableExternal) {
				//Check if void type then we cast to get rid of all the warnings
				Type lambda=((VariableExternal)call.getProcedure()).getType();
				if(lambda instanceof TypeLambda) {
					Type param = ((TypeLambda) lambda).getInputTypes().get(i);
					int ind=0;
					while(param instanceof TypeList) {
						ind++;
						param = ((TypeList)param).getType();
					}
					if(param instanceof TypeExternal && ((TypeExternal)param).getName().equals("void")) {
						s.print("(void");
						while(ind>0) {
							s.print("*");
							ind--;
						}
						s.print(") ");
					}
				}
			}
			Type ftype = UtilIR.getFinalType(p);
			if(isIndir(UtilIR.getProcInputType(call.getProcedure(),i),true) || (hasIndex(p) && UtilIR.isListOrRecord(UtilIR.getProcInputType(call.getProcedure(),i)))|| 
					(UtilIR.refLiteralExpression(p) && UtilIR.isListOrRecord(ftype)) ) {
				s.print("&");
			}
			doSwitch(p);
			if (i<call.getInParameters().size()-1) s.printComma();
		}
		if(!call.getOutParameters().isEmpty()) {
			s.printComma();
			for (int i = 0; i<call.getOutParameters().size(); i++) {
				VariableReference p = call.getOutParameters().get(i);
				//Check if void type then we cast to get rid of all the warnings
				Type param = p.getType();
				int ind=0;
				while(param instanceof TypeList) {
					ind++;
					param = ((TypeList)param).getType();
				}
				if(param instanceof TypeExternal && ((TypeExternal)param).getName().equals("void") && 
						call.getProcedure() instanceof Variable &&
						((Variable)call.getProcedure()).getType() instanceof TypeProc ) {
					s.print("(");
					Type type = ((TypeProc)((Variable)call.getProcedure()).getType()).getOutputTypes().get(i);
					doSwitch(type);
					while(ind>0) {
						s.print("*");
						ind--;
					}
					s.print("*)");					
				}
				if(isIndir(UtilIR.getProcOutputType(call.getProcedure(),i),false) || hasIndex(p)) 
					s.print("&");
				doSwitch(p);
				if (i<call.getOutParameters().size()-1) s.printComma();
			}
		}
		s.printRight();
		s.println(";");
		
		leave();
		return s;
	}

	@Override
	public Stream caseWhileLoop(WhileLoop stmt) {
		enter(stmt);
		s.print("while (");
		doSwitch(stmt.getCondition());
		s.printlnInc(") {");
		for (Declaration d : stmt.getBody().getDeclarations()) {
			doSwitch(d);
			s.println(";");
		}		
		for (Statement s1 : stmt.getBody().getStatements()) {
			doSwitch(s1);			
		}
		s.printlnDec("}");
		
		leave();
		return s;
	}

	@Override
	public Stream caseForEach(ForEach stmt) {
		enter(stmt);
		for(int i = stmt.getGenerators().size()-1;i>=0;i--) {
			Generator g = stmt.getGenerators().get(i);
			s.print("for(");
			if(g.getSource() instanceof BinaryExpression && ((BinaryExpression) g.getSource()).getOperator().equals("..")) {
				s.print(validCName(g.getDeclarations().get(0).getName()) + " = ");
				doSwitch(((BinaryExpression)g.getSource()).getOperand1());
				s.print("; " +validCName(g.getDeclarations().get(0).getName())+" <= ");
				doSwitch(((BinaryExpression)g.getSource()).getOperand2());
				s.println("; "+validCName(g.getDeclarations().get(0).getName())+"++)");
			} else {
				throw new RuntimeException("[CPrinter] Expecting foreach expressions to have A .. B syntax");
			}
		}
		doSwitch(stmt.getBody());
		leave();
		return s;
	}

	@Override
	public Stream caseIfStatement(IfStatement stmt) {
		enter(stmt);
		s.print("if (");
		doSwitch(stmt.getCondition());
		s.printlnInc(") {");
		for (Declaration d : stmt.getThenBlock().getDeclarations()) {
			doSwitch(d);
			s.println(";");
		}		
		for (Statement s1 : stmt.getThenBlock().getStatements()) {
			doSwitch(s1);
		}
		if (stmt.getElseBlock()!=null && !stmt.getElseBlock().getStatements().isEmpty()) {
			s.dec();
			s.println();
			s.printlnInc("} else {");
			for (Declaration d : stmt.getElseBlock().getDeclarations()) {
				doSwitch(d);
				s.println(";");
			}		
			for (Statement s1 : stmt.getElseBlock().getStatements()) {
				doSwitch(s1);			
			}
		}
		s.printlnDec("}");
		
		leave();
		return s;
	}

	@Override
	public Stream caseIfExpression(IfExpression expr) {
		enter(expr);
		s.print("((");
		doSwitch(expr.getCondition());
		s.print(") ? (");
		doSwitch(expr.getThenExpression());
		s.print("):(");
		doSwitch(expr.getElseExpression());
		s.print("))");
		leave();
		return s;
	}

	@Override
	public Stream caseBlock(Block block) {
		enter(block);
		s.printlnInc("{");
		
		for (Declaration d : block.getDeclarations()) {
			doSwitch(d);
		}
		
		for (Statement s : block.getStatements()) {
			doSwitch(s);
		}
				
		s.printlnDec("}");
		leave();
		return null;
	}

	@Override
	public Stream casePortRead(PortRead read) {
		enter(read);
		//Moved logic to IR2CIR
		leave();
		return s;
	}
	
	private void printFuncDecl(Variable variable) {
		LambdaExpression lambda = (LambdaExpression) variable.getInitValue();
		Type type = ((TypeLambda)lambda.getType()).getOutputType();
		if(!systemc)
			s.print("inline ");
		doSwitch(type);
		if(UtilIR.isListOrRecord(type))
			s.print("*");
		s.print(" " + thisStr + variable.getName() + "(");
		if(actorFuncDecl.contains(variable.getId())) {
			s.print("ActorInstance_" + thisStr + "* thisActor");
			if(!lambda.getParameters().isEmpty())
				s.print(", ");
		}
		for(Iterator<Variable> i = lambda.getParameters().iterator();i.hasNext();) {
			Variable p = i.next();
			doSwitch(p.getType());
			if(UtilIR.isListOrRecord(p.getType())) s.print("*");
			s.print(" " + validCName(p.getName()));
			if (i.hasNext()) s.printComma();
		}
		s.print(")");
		if(topHeader) {
			s.println(";");
		} else {
			if(lambda.getBody() instanceof ProcExpression) {
				//Expression have been expanded to nameless proc to get a block
				doSwitch(((ProcExpression)lambda.getBody()).getBody());
			} else {
				s.println("{");
				s.inc();
				s.print("return ");
				doSwitch(lambda.getBody());
				s.println(";");
				s.dec();
				s.println("}");
			}
		}
	}
	
	private boolean isIndir(Type type, boolean isOutputOnInput) {
		Type tt = type;
		int i=0;
		while(tt instanceof TypeList && ((TypeList)tt).getSize() instanceof IntegerLiteral && ((IntegerLiteral) ((TypeList)tt).getSize()).getValue()==0) {
			tt = ((TypeList)tt).getType();
			i++;
		}
		if(i>(isOutputOnInput?1:0)) 
			return true;
		return false;
	}

	private boolean isIndir(Variable var) {
		if(UtilIR.isList(var.getType())) {
			TypeList type = (TypeList) var.getType();
			if(type.getSize() instanceof IntegerLiteral && ((IntegerLiteral) type.getSize()).getValue()==0) {
				return true;
			}
		}
		return false;
	}

	private void printProcDecl(Variable variable) {
		s.print("void " + thisStr + variable.getName() + "(");
		ProcExpression proc =  (ProcExpression) variable.getInitValue();
		if(actorFuncDecl.contains(variable.getId())) {
			s.print("ActorInstance_" + thisStr + "* thisActor");
			if(!proc.getOutputs().isEmpty() || !proc.getParameters().isEmpty())
				s.print(", ");
		}
		for(Iterator<Variable> i = proc.getParameters().iterator();i.hasNext();) {
			Variable p = i.next();
			doSwitch(p.getType());
			if(UtilIR.isListOrRecord(p.getType())) s.print("*");
			s.print(" " + validCName(p.getName()));
			if (i.hasNext()) s.printComma();
		}
		if(!proc.getOutputs().isEmpty()) {
			s.printComma();
			for(Iterator<Variable> i = proc.getOutputs().iterator();i.hasNext();) {
				Variable p = i.next();
				doSwitch(p.getType());
				if(isIndir(p)) s.print("*");
				s.print("* " + validCName(p.getName()));
				if (i.hasNext()) s.printComma();
			}			
		}
		s.print(")");
		if(topHeader) {
			s.println(";");
		} else {
			s.println(" {");
			s.inc();
			for (Declaration d : proc.getBody().getDeclarations()) {
				doSwitch(d);
			}	
			for (Statement statement : proc.getBody().getStatements()) {
				doSwitch(statement);
			}
			s.dec();
			s.println("}");
		}
	}
	
	private void printVarDecl(Variable variable) {
		String namespace = "";
		if(variable.getScope() instanceof Network && !(parent() instanceof TypeDeclaration)) {
			List<String> ns = UtilIR.getAnnotatedNamespace(variable);
			if(ns.size()>0) {
				namespace = Util.marshallQualifiedName(ns)+"_";
			}
		}
		if(topHeader && !(parent() instanceof TypeDeclaration)) s.print("extern ");
		doSwitch(variable.getType());
		if(variable.getInitValue() != null) {
			s.print(" " + namespace + validCName(variable.getName()));
			if(UtilIR.isList(variable.getType())) {
				Type type = variable.getType();
				while(type instanceof TypeList) {
					s.print("[");
					doSwitch(((TypeList)type).getSize());
					s.print("]");
					type = ((TypeList)type).getType();
				}
			}
			if(!topHeader) {
				s.print(" = ");
				doSwitch(variable.getInitValue());
			}
		} else {
			if(UtilIR.isList(variable.getType())) {
				Type type = variable.getType();
				while(type instanceof TypeList) {
					type = ((TypeList) type).getType();
					s.print("*");
				}
			} else if(UtilIR.isRecord(variable.getType())) {
				s.print("*");
			}
			s.print(" " + namespace + validCName(variable.getName()));
		}
		s.println(";");
	}
	
	@Override
	public Stream caseVariable(Variable variable) {
		enter(variable);
		if(variable.getInitValue() instanceof LambdaExpression) {
			printFuncDecl(variable);
		} else if(variable.getInitValue() instanceof ProcExpression) {
			printProcDecl(variable);
		} else {
			printVarDecl(variable);
		} 
		leave();
		return s;
	}
	
	@Override
	public Stream caseGuard(Guard guard) {
		enter(guard);
		doSwitch(guard.getType());
		s.printSpace();
		s.print(thisStr + validCName(guard.getId()) + "(");
		if(!systemc){
			s.print("art_action_context_t *context, ActorInstance_" + thisStr + " *thisActor");
		}
		else
			s.print("ActorContext *context, ActorInstance_" + thisStr + " *thisActor");
		s.printlnInc(") {");

		for (Declaration d : guard.getDeclarations()) {
			doSwitch(d);
		}
		//The in port variables are declared in action scope hence need to print the ones this guard use (but only once) for peek
		Set<Declaration> peekDecl = new HashSet<Declaration>();
		for(PortPeek p: guard.getPeeks()) {
			boolean found=false;
			for(Declaration d: peekDecl) {
				if(d.getId().equals(p.getVariable().getDeclaration().getId())) {
					found = true;
					break;
				}
			}
			if(!found) {
				peekDecl.add(p.getVariable().getDeclaration());
				doSwitch(p.getVariable().getDeclaration());
			}
		}

		if(guard.getBody() instanceof ProcExpression) {
			doSwitch(((ProcExpression)guard.getBody()).getBody());
		} else {
			s.print("return ");
			doSwitch(guard.getBody());
			s.println(";");	
		}

		s.printlnDec("}");

		leave();
		return s;
	}

	@Override
	public Stream caseTypeConstructor(TypeConstructor tc) {
		enter(tc);
		Type type = tc.getTypedef().getType();
		s.print(tc.getTypedef().getName() + "_t *");
		s.print(validCName(tc.getName()) + "(");
		s.print(tc.getTypedef().getName() + "_t *" + "input, ");
		TypeRecord struct = (TypeRecord) UtilIR.getType(type);
		for (Iterator<Variable> i = struct.getMembers().iterator(); i.hasNext();) {
			Variable p = i.next();
			doSwitch(p.getType());
			if(UtilIR.isListOrRecord(p.getType())) s.print("*");
			s.print(" " + p.getName());
			if (i.hasNext()) s.printComma();
		}
		s.print(")");
		if(topHeader) {
			s.println(";");
		} else {
			s.println(" {");
			s.inc();
			s.println(tc.getTypedef().getName() + "_t *ret;");
			s.println("if(input==NULL)");
			s.inc();
			s.println("alloc__" + tc.getTypedef().getName() + "(NULL, 1, &ret);");
			s.dec();
			s.println("else");
			s.inc();
			s.println("ret=input;");
			s.dec();
			for (Iterator<Variable> i = struct.getMembers().iterator(); i.hasNext();) {
				Variable p = i.next();
				if(UtilIR.isRecord(p.getType())) {
					s.print("copy__" + ((TypeDeclaration)((TypeUser)p.getType()).getDeclaration()).getName());
					s.print("(" + p.getName() + ", 1, ret->"+p.getName());
					s.println(");");
				} else if(UtilIR.isList(p.getType())) {
					TypeList typeL = (TypeList) UtilIR.getType(p.getType());
					Type typeE = typeL.getType();
					if(UtilIR.isRecord(typeE)) {
						s.print("copy__" + UtilIR.getTypeDeclaration(typeE).getName());
						s.print("(" + p.getName() + ", ");
						doSwitch(typeL.getSize());
						s.println(", ret->"+p.getName() + ");");
					} else {
						s.print("memcpy");
						s.print("(ret->"+p.getName() + ", " + p.getName() + ", sizeof(");
						doSwitch(typeE);
						s.print(")*");
						doSwitch(typeL.getSize());
						s.println(");");
					}
				} else {
					s.println("ret->"+p.getName()+" = " + p.getName() + ";");
				}
			}
			s.println("return ret;");
			s.dec();
			s.println("}");
		}
		leave();
		return s;
	}

	@Override
	public Stream caseTypeDeclaration(TypeDeclaration type) {
		enter(type);
		s.println("");
		s.printlnInc("typedef struct {");
		TypeRecord struct = (TypeRecord) (type.getType() instanceof TypeUser ? ((TypeDeclaration)((TypeUser)type.getType()).getDeclaration()).getType(): type.getType());
		for (Iterator<Variable> i = struct.getMembers().iterator(); i.hasNext();) {
			Variable var = i.next();
			doSwitch(var);
			if(!i.hasNext()) {
				s.dec(); //After next println
			}
		}
		s.print("} ");
		s.println(type.getName() + "_t;");
		leave();
		s.println("");
		return s;
	}

	@Override
	public Stream caseTypeUser(TypeUser type) {
		TypeDeclaration decl = UtilIR.getTypeDeclaration(type);
		s.print(decl.getName() + "_t");
		return s;
	}
	
	@Override
	public Stream caseReturnValue(ReturnValue returnValue) {
		enter(returnValue);
		s.print("return");
		s.printSpace();
		doSwitch(returnValue.getValue());
		s.println(";");

		leave();
		return s;
	}
	
	@Override
	public Stream caseTypeBool(TypeBool type) {
		enter(type);
		s.print("bool_t");
		
		leave();
		return s;
	}

	@Override
	public Stream caseTypeInt(TypeInt type) {
		enter(type);
		s.print("int32_t");
		
		leave();
		return s;
	}

	@Override
	public Stream caseTypeFloat(TypeFloat type) {
		enter(type);
		s.print("double");
		
		leave();
		return s;
	}

	@Override
	public Stream caseTypeList(TypeList type) {
		enter(type);
		doSwitch(type.getType());
		leave();
		return s;
	}

	@Override
	public Stream caseTypeUint(TypeUint object) {
		enter(object);
		s.print("uint32_t");
		
		leave();
		return s;
	}


	@Override
	public Stream caseTypeString(TypeString object) {
		enter(object);
		s.print("char *");
		
		leave();
		return s;
	}

	
	@Override
	public Stream caseTypeRecord(TypeRecord struct) {
		enter(struct);
		//We should never need to print a record directly, instead we print a string that will generate compile errors
		s.print("SOMEONE PRINTING A RECORD DIRECTLY????");
		leave();
		return s;
	}

	@Override
	public Stream caseTypeExternal(TypeExternal object) {
		enter(object);
		s.print(object.getName());
		leave();
		return s;
	}

	@Override
	public Stream caseTypeUndef(TypeUndef object) {
		enter(object);
		s.print("void");
		leave();
		return s;
	}
	
	@Override
	public Stream caseType(Type object) {
		assert(false);
		return null;
	}

	@Override
	public Stream caseExpression(Expression object) {
		enter(object);
		//Generic Expression is used as a special case to print the type of the expression or NULL
		//This is used for example when using sizeof() or any c-function that takes a pointer but we have nothing to supply
		if(object.getType() instanceof TypeUndef)
			s.print("NULL");
		else {
			int i=0;
			Type type = object.getType();
			while(type instanceof TypeList) {
				type = ((TypeList) type).getType();
				i++;
			}
			doSwitch(type);
			for(;i>0;i--)
				s.print("*");
		}
		leave();
		return s;
	}
	
	public Stream defaultCase(EObject object) {
		return null;
	}
	
	
 	public void doActionScheduler(Actor actor) {
 		//TODO This should be one per port and we should point to the correct one when blocking instead of this Any
 		if(initialToken)
 			s.println("static int initializers_have_run=0;");
 		s.println("static const int exitcode_block_Any[3]={1,0,1};");
 		s.printlnInc("ART_ACTION_SCHEDULER(" + thisStr + "_action_scheduler) {");
 		s.println("const int *result = EXIT_CODE_YIELD;");
 		s.println("ActorInstance_" + thisStr + " *thisActor=(ActorInstance_" + thisStr + "*) pBase;"); 		
 		s.println("ART_ACTION_SCHEDULER_ENTER(" + actor.getInputPorts().size() + ", " + actor.getOutputPorts().size() + ")");
 		if(initialToken) {
	 		s.println("if(!initializers_have_run) {");
	 		s.inc();
 	 		s.println("initializers_have_run=1;");
	 		for(Action a : actor.getInitializers()) {
            	if(!a.getOutputs().isEmpty())
            		s.println("ART_FIRE_ACTION(" + a.getId() + ");");
	 		}
	 		s.dec();
	 		s.println("}");
 		}
 		s.printlnInc("while(1) {");
 		if (!actor.getSchedule().getFreeRunners().isEmpty()) {
 		  printFiringTests(actor.getSchedule().getFreeRunners(), null);
 		}
 		s.printlnInc("switch(thisActor->_fsmState) {");
 		for (State state : actor.getSchedule().getStates()) {
 			s.printlnInc("case " + actorId + "__" + state.getName() + "_ID:");
 			PriorityGraph priorityGraph = (PriorityGraph) state.getPriorityGraph();
 			Map<Action, String> action2TargetMap = (Map<Action, String>) state.getAction2TargetMap();
 			printFiringTests(priorityGraph.getOneTopologicalOrder(), action2TargetMap); 	
 			s.println("result = exitcode_block_Any;");
 			s.println("goto out;");
 			s.println("break;");
 			s.dec();
 			s.println("");
 		} 		
 		s.printlnDec("}");

 		s.printlnDec("}");
 		s.println("out:");
 		s.println("ART_ACTION_SCHEDULER_EXIT(" + actor.getInputPorts().size() + ", " + actor.getOutputPorts().size() + ")");
		s.println("return result;");
 		s.printlnDec("}");
 	}
 	
	private void printFiringTests(List<Action> actions, Map<Action, String> action2TargetMap) {
		
		for(Iterator<Action> a = actions.iterator();a.hasNext();) {
			Action action = a.next();
			// First check for availability of tokens
			if (!action.getInputs().isEmpty()) {
				s.print("if (");
				for (Iterator<PortRead> i = action.getInputs().iterator(); i.hasNext();) {
					PortRead read = i.next();
					Type t = read.getPort().getType();
					if (UtilIR.isList(t)) {
						t = ((TypeList) t).getType();
					}
					if(UtilIR.isRecord(t)) {
						s.print("(pinAvailIn_" + UtilIR.getTypeDeclaration(t).getName());
					} else {
						s.print("(pinAvailIn_");
						doSwitch(t);
					}
					s.print("(" + inputPortMap.get(read.getPort().getName()) + ") >= ");
					if (UtilIR.isList(read.getPort().getType())) {
						doSwitch(((TypeList) t).getSize());
						s.print("*");
					} 
					if(read.getRepeat()!=null)
						doSwitch(read.getRepeat());
					else
						s.print("1");

					//When many variables each variable need to exist in fifo
					if(read.getVariables().size()>1)
						s.print("*"+read.getVariables().size());

					s.print(")");
					if (i.hasNext()) s.print(" && ");
				}
				s.printlnInc(") {");
			}
			
			if (!action.getGuards().isEmpty()) {
				s.print("if (");
				for (Iterator<Guard> i = action.getGuards().iterator(); i.hasNext();) {
					Guard f = i.next();
					s.print(thisStr + validCName(f.getId()) + "(context, thisActor)");
					if (i.hasNext()) s.print(" && ");
				}			
				s.printlnInc(") {");
			}
	
			if (!action.getOutputs().isEmpty()) {
				s.print("if (");
				for (Iterator<PortWrite> i = action.getOutputs().iterator(); i.hasNext();) {
					PortWrite write = i.next();
					Type t = write.getPort().getType();
					if (UtilIR.isList(t)) {
						t = ((TypeList) t).getType();
					} 
					if(UtilIR.isRecord(t)) {
						s.print("(pinAvailOut_" + UtilIR.getTypeDeclaration(t).getName());
					} else {
						s.print("(pinAvailOut_");
						doSwitch(t);
					}
					s.print("(" + outputPortMap.get(write.getPort().getName()) + ") >= ");	
					if (UtilIR.isList(write.getPort().getType())) {
						doSwitch(((TypeList) t).getSize());
						s.print("*");
					} 
					if(write.getRepeat()!=null)
						doSwitch(write.getRepeat());
					else
						s.print("1");
					
					//When many expressions each expression need to have room
					if(write.getExpressions().size()>1)
						s.print("*"+write.getExpressions().size());
						
					s.print(")");
					if (i.hasNext()) s.print(" && ");
				}
				s.printlnInc(") {");		
			}
			s.println("ART_FIRE_ACTION(" + action.getId() + ");");
			if (action2TargetMap != null) {
				if(debugPrint) {
					s.println("if(thisActor->_fsmState != " + actorId + "__" + action2TargetMap.get(action) + "_ID) {");
					s.inc();
					s.println("dprint2(\"%i --> %i " + actorId + "__" + action2TargetMap.get(action) +
							"\\n\", thisActor->_fsmState, " + actorId + "__" + action2TargetMap.get(action) + "_ID" + ");");
					s.dec();
					s.println("}");
				}
				s.print("thisActor->_fsmState = ");
				s.print(actorId + "__" + action2TargetMap.get(action) + "_ID");
				s.println(";");
			}
			s.println("continue;");
	 		if (!action.getOutputs().isEmpty()) {
				s.dec();
				s.println();
				s.printlnInc("} else {");
				s.println("result = exitcode_block_Any;");
				s.println("goto out;");
				s.dec();
				s.println("}");
			}
			if (!action.getGuards().isEmpty()) {
					s.printlnDec("}");
			}
			
			if (!action.getInputs().isEmpty()) {
				s.printlnDec("}");		
			}
		}
	}
}
