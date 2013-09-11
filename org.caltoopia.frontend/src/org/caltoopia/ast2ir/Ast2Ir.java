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
package org.caltoopia.ast2ir;

import java.util.ArrayList;  
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.eclipse.emf.ecore.EObject;
import org.caltoopia.frontend.cal.AstAbstractActor;
import org.caltoopia.frontend.cal.AstActor;
import org.caltoopia.frontend.cal.AstActorVariable;
import org.caltoopia.frontend.cal.AstAnnotation;
import org.caltoopia.frontend.cal.AstAssignParameter;
import org.caltoopia.frontend.cal.AstConnection;
import org.caltoopia.frontend.cal.AstConnectionAttribute;
import org.caltoopia.frontend.cal.AstExpressionSymbolReference;
import org.caltoopia.frontend.cal.AstExternalActor;
import org.caltoopia.frontend.cal.AstForeachGenerator;
import org.caltoopia.frontend.cal.AstMemberAccess;
import org.caltoopia.frontend.cal.AstNamespace;
import org.caltoopia.frontend.cal.AstNetwork;
import org.caltoopia.frontend.cal.AstPattern;
import org.caltoopia.frontend.cal.AstStatementAlternative;
import org.caltoopia.frontend.cal.AstStatementBlock;
import org.caltoopia.frontend.cal.AstStatementCase;
import org.caltoopia.frontend.cal.AstSubPattern;
import org.caltoopia.frontend.cal.AstTag;
import org.caltoopia.frontend.cal.AstTransition;
import org.caltoopia.frontend.cal.AstAction;
import org.caltoopia.frontend.cal.AstEntity;
import org.caltoopia.frontend.cal.AstExpression;
import org.caltoopia.frontend.cal.AstFunction;
import org.caltoopia.frontend.cal.AstInputPattern;
import org.caltoopia.frontend.cal.AstOutputPattern;
import org.caltoopia.frontend.cal.AstProcedure;
import org.caltoopia.frontend.cal.AstPort;
import org.caltoopia.frontend.cal.AstStatement;
import org.caltoopia.frontend.cal.AstStatementAssign;
import org.caltoopia.frontend.cal.AstStatementCall;
import org.caltoopia.frontend.cal.AstStatementForeach;
import org.caltoopia.frontend.cal.AstStatementIf;
import org.caltoopia.frontend.cal.AstStatementWhile;
import org.caltoopia.frontend.cal.AstType;
import org.caltoopia.frontend.cal.AstTypeUser;
import org.caltoopia.frontend.cal.AstVariable;
import org.caltoopia.frontend.cal.util.CalSwitch;
import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.types.TypeConverter;
import org.caltoopia.types.TypeSystem;
import org.caltoopia.frontend.util.ActionList;
import org.caltoopia.frontend.util.VoidSwitch;
import org.caltoopia.ir.AbstractActor;
import org.caltoopia.ir.Action;
import org.caltoopia.ir.Actor;
import org.caltoopia.ir.ActorInstance;
import org.caltoopia.ir.Annotation;
import org.caltoopia.ir.Block;
import org.caltoopia.ir.CaseStatement;
import org.caltoopia.ir.Connection;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.ExternalActor;
import org.caltoopia.ir.ForEach;
import org.caltoopia.ir.ForwardDeclaration;
import org.caltoopia.ir.FromSource;
import org.caltoopia.ir.LambdaExpression;
import org.caltoopia.ir.Generator;
import org.caltoopia.ir.Guard;
import org.caltoopia.ir.IfStatement;
import org.caltoopia.ir.IrFactory;
import org.caltoopia.ir.Member;
import org.caltoopia.ir.Namespace;
import org.caltoopia.ir.Network;
import org.caltoopia.ir.Node;
import org.caltoopia.ir.Pattern;
import org.caltoopia.ir.Point2PointConnection;
import org.caltoopia.ir.Port;
import org.caltoopia.ir.PortInstance;
import org.caltoopia.ir.PortRead;
import org.caltoopia.ir.PortPeek;
import org.caltoopia.ir.PortWrite;
import org.caltoopia.ir.ProcCall;
import org.caltoopia.ir.ProcExpression;
import org.caltoopia.ir.Scope;
import org.caltoopia.ir.Statement;
import org.caltoopia.ir.StmtAlternative;
import org.caltoopia.ir.SubPattern;
import org.caltoopia.ir.TaggedExpression;
import org.caltoopia.ir.TaggedTuple;
import org.caltoopia.ir.ToSink;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.TypeLambda;
import org.caltoopia.ir.TypeDeclaration;
import org.caltoopia.ir.TypeProc;
import org.caltoopia.ir.TypeTuple;
import org.caltoopia.ir.TypeUser;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.ir.VariableExternal;
import org.caltoopia.ir.VariableReference;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.WhileLoop;
import org.caltoopia.ir.Schedule;
import org.caltoopia.ir.State;

public class Ast2Ir extends CalSwitch<EObject> {
	
	private final int DEFAULT_BUFFER_SIZE = 4096;
	
	private final String BUFFER_SIZE_STRING = "BufferSize";
	
	private Stack<Scope> scopeStack = new Stack<Scope>();
	
	private int actionCounter = 0;
		
	private boolean verbose = true;
	
	private String filePath;
	
	private String projectPath;
		
	public void run(AstNamespace astNamespace, String filePath, String projectPath) {
		this.filePath = filePath;
		this.projectPath = projectPath;
//TODO		ActorDirectory.removeIndex(projectPath, filePath);
		caseAstNamespace(astNamespace);
	}
	
	@Override
	public Namespace caseAstNamespace(AstNamespace astNamespace) {
		if (verbose) System.out.println("[Ast2Ir] compiling namespace '" + astNamespace.getName() + "'");
		// Make sure not referring Actor Directory to the elaborated temp directory
		ActorDirectory.resetTransformedNetwork();

		Util.clearDefs();
		
		Namespace ns = IrFactory.eINSTANCE.createNamespace();	
		ns.setId(Util.getDefinitionId());
		doAnnotations(astNamespace.getAnnotations(), ns);
		
		ns.getAnnotations().add(Util.createPathAnnotation(filePath));
		ns.getAnnotations().add(Util.createProjectAnnotation(projectPath));
		
		FindImportedAstSymbols.run(astNamespace, ns);		

		//Need to fill up with outer namespaces first
		List<String> namespaceName = new ArrayList<String>();	
		EObject outer = astNamespace.eContainer();
		while (outer != null) {
			if(outer instanceof AstNamespace) {
				namespaceName.add(0, ((AstNamespace) outer).getName());
			}
			outer = outer.eContainer();
		}
		
		String tmp[] = java.util.regex.Pattern.compile("\\.").split(astNamespace.getName());
		for (String s : tmp) {
			namespaceName.add(s);
		}		
		ns.getName().addAll(namespaceName);
		scopeStack.push(ns);		
			
		for (AstAnnotation astAnnotation : astNamespace.getAnnotations()) {
			Annotation annotation = Util.createAnnotation(astAnnotation);
			ns.getAnnotations().add(annotation);
		}

		// Do all the definitions, sort first
		List<VertexData> graphData = new ArrayList<VertexData>();

		for (EObject external : astNamespace.getExternals()) {
			VariableExternal ve = Util.createExternalDeclaration(ns, external, false);
			ns.getDeclarations().add(ve);
		}
		
		for (AstVariable constvar : astNamespace.getVariables()) {
			graphData.add(new AstDeclVertex(constvar));
		}
		
		for (AstVariable f : astNamespace.getFunctions()) {
			graphData.add(new AstDeclVertex(f));
		}
		
		for (AstTypeUser td : astNamespace.getTypedefs()) {
			graphData.add(new AstDeclVertex(td));
		}
		
		graphData = new Graph(graphData).sortByDependency();
		
		for (VertexData data : graphData) {
			if (data.getData() instanceof AstFunction){
				Declaration def = Util.createForwardFunctionDeclaration(scopeStack.peek(), (AstFunction) data.getData(), false);				                                                                                   
				ns.getDeclarations().add(def);
			} else if (data.getData() instanceof AstProcedure) {
				// NB Only external procedures are allow.
				Declaration def =  Util.createForwardProcedureDeclaration(scopeStack.peek(), (AstProcedure) data.getData());				                                                                                   
				ns.getDeclarations().add(def);
			} else if (data.getData() instanceof AstTypeUser) {
				TypeDeclaration typeDecl  = TypeConverter.createTypeDeclaration(scopeStack.peek(), (AstTypeUser) data.getData(), false);
				ns.getDeclarations().add(typeDecl);						
			} else if (data.getData() instanceof AstVariable) {
				AstVariable var = (AstVariable) data.getData();
				Util.createVariable(scopeStack.peek(), var, false);
			}
			
			else {
				throw new RuntimeException("Internal meltdown. Fire the developer.");
			}
		}
		
		for (AstVariable astFunction : astNamespace.getFunctions()) {
			Variable irFunction = (Variable) doSwitch(astFunction);
			ns.getDeclarations().add(irFunction);
		}
		
		ActorDirectory.addNamespace(ns, projectPath);
		
		for (AstEntity astEntity : astNamespace.getEntities()) {
			// Do all actors and networks
			Util.clearDefs();
			
			if (verbose) System.out.println("[Ast2Ir] compiling actor/network '" + astEntity.getActor().getName() + "'");
			AbstractActor actor = caseAstEntity(astEntity);
			TypeActor type = IrFactory.eINSTANCE.createTypeActor();
			type.setName(astEntity.getActor().getName());
			actor.setOuter(ns);
			for (String s : ns.getName()) {
				type.getNamespace().add(s);
			}
			actor.setType(type);

			actor.getAnnotations().add(Util.createPathAnnotation(filePath));
			actor.getAnnotations().add(Util.createProjectAnnotation(projectPath));
			ActorDirectory.addActor(actor, projectPath);
		}	
		
		for (AstNamespace astSubNamespace : astNamespace.getNamespaces()) {
			// Do all subnetworks 
			Util.clearDefs();
			
			Namespace subNamespace = caseAstNamespace(astSubNamespace);			
			subNamespace.setOuter(ns);
			
			ActorDirectory.addNamespace(subNamespace, projectPath);
		}
		
		return ns;
	}
	
	@Override
	public AbstractActor caseAstEntity(AstEntity astEntity) {
		AbstractActor result = null;
		AstAbstractActor astActor = astEntity.getActor();
		if (astActor instanceof AstActor) {
			 result = caseAstActor((AstActor) astActor);
		} else if (astActor instanceof AstNetwork){
			result =  caseAstNetwork((AstNetwork) astActor);
		} else if (astActor instanceof AstExternalActor){
			result =  caseAstExternalActor((AstExternalActor) astActor);
		}
		doAnnotations(astEntity.getAnnotations(), result);
		return result;
	}
	
	@Override
	public Actor caseAstActor(AstActor astActor) {
		Actor actor = IrFactory.eINSTANCE.createActor();
		actor.setId(Util.getDefinitionId());
		
		actor.setOuter(scopeStack.peek());
		scopeStack.push(actor);
		
		FindImportedAstSymbols.run(astActor, actor);
		
		for (AstVariable p : astActor.getParameters()) {				
			Variable var = Util.createVariable(actor, p, false);
			var.setParameter(true);
			actor.getParameters().add(var);
		}
		
		List<VertexData> graphData = new ArrayList<VertexData>();
		for (AstVariable state : astActor.getStateVariables()) {
			graphData.add(new AstDeclVertex(state));
		}
		
		for (AstVariable f : astActor.getFunctions()) {
			graphData.add(new AstDeclVertex(f));
		}
		
		for (AstProcedure p : astActor.getProcedures()) {
			graphData.add(new AstDeclVertex(p));
		}
		
		//new Graph(graphData).print();
		graphData = new Graph(graphData).sortByDependency();
		
		for (VertexData data : graphData) {
			if (data.getData() instanceof AstFunction){
				// Create a forward declaration
				Declaration def = Util.createForwardFunctionDeclaration(scopeStack.peek(), (AstFunction) data.getData(), false);				                                                                                   
				actor.getDeclarations().add(def);
			} else if (data.getData() instanceof AstProcedure) {
				// Create a forward declaration
				Declaration def =  Util.createForwardProcedureDeclaration(scopeStack.peek(), (AstProcedure) data.getData());				                                                                                   
				actor.getDeclarations().add(def);
			} else if (data.getData() instanceof AstVariable) {
				AstVariable var = (AstVariable) data.getData();
				Util.createVariable(scopeStack.peek(), var, false);
			} 
		}
		
		for (AstVariable astFunction : astActor.getFunctions()) {
			Variable irFunction = (Variable) doSwitch(astFunction);
			actor.getDeclarations().add(irFunction);
		}
		
		for (AstProcedure astProcedure : astActor.getProcedures()) {
			Variable irProcedure = (Variable) doSwitch(astProcedure);
			actor.getDeclarations().add(irProcedure);
		}
		
		for (AstPort p : astActor.getInputs()) {
			Port port = Util.createPort(scopeStack.peek(), p, false);
			actor.getInputPorts().add(port);
		}
		
		for (AstPort p : astActor.getOutputs()) {
			Port port = Util.createPort(scopeStack.peek(), p, false);
			actor.getOutputPorts().add(port);
		}
		
		ActionList actionList = new ActionList();
		
		for (AstAction a : astActor.getInitializes()) {
			Action initAction = caseAstAction(a);
			actor.getInitializers().add(initAction);
		}		
		
		for (AstAction a : astActor.getActions()) {
			Action action = caseAstAction(a);
			actor.getActions().add(action);
			actionList.add(action);
		}

		Schedule schedule = createSchedule(astActor, actionList);
		actor.setSchedule(schedule);
		scopeStack.pop();
		
		return actor;
	}


	
	private Schedule createSchedule(AstActor astActor, ActionList actionList) {

		// List<Action> sorted; 		
		Schedule schedule = IrFactory.eINSTANCE.createSchedule();
	
		// First take care of the free runners, ie untagged actions
		for (Action a : actionList.getUntaggedActions()) {
			schedule.getFreeRunners().add(a);
		}
		
		// Create a priority graph containing all priority relations
		PriorityGraph graph = new PriorityGraph(actionList, astActor.getPriorities());
		
		// Save it to the schedule, in case it can be of use in 
		// any of the backends
		schedule.setPriorityGraph(graph);
				
		/*
		graph.print();		
		sorted = graph.getOneTopologicalOrder();
		
		for (Action a : sorted) {
			System.out.println(a.getId());
		}
		System.out.println();
		*/
	
		
		if (astActor.getSchedules().size() == 0 ) {
			// No FSM is specified. So we create a one state FSM 
			// Note that tagged actions that are not part of the prio 
			// list still are added to the list of valid actions.
			
			State state = IrFactory.eINSTANCE.createState();
			state.setName("defaultState");			
			
			HashMap<Action, String> action2target = new HashMap<Action, String>();
			for (Action a : actionList.getTaggedActions()) {
				action2target.put(a, "defaultState");
			}
			state.setAction2TargetMap(action2target);
			state.setPriorityGraph(graph);
			
			schedule.getStates().add(state);
			schedule.setInitialState(state);
		} else {
		
			Map<String, HashMap<Action, String>> state2transitions = new HashMap<String, HashMap<Action, String>>();
    
			// Add all states to the schedule
			for (AstTransition t : astActor.getSchedules().get(0).getTransitions()) {			
				
				// Try find the source state. If it does not exist, create it.
				boolean found = false;
				for (State s : schedule.getStates()) {
					if (s.getName().equals(t.getSource().getName())) {
						found = true;
					}
				}
		
				if (!found) {
					State s = IrFactory.eINSTANCE.createState();
					s.setName(t.getSource().getName());
					if (t.getSource().getName().equals(astActor.getSchedules().get(0).getInitialState().getName())) {
						schedule.setInitialState(s);
					}
					schedule.getStates().add(s);
					HashMap<Action, String> transitions = new HashMap<Action, String>();
			
					for (Iterator<AstTag> it = t.getTags().iterator(); it.hasNext(); ) {					
						List<String> tags = new ArrayList<String>(it.next().getIdentifiers());
						List<Action> as = actionList.getTaggedActions(tags);
						if (as != null) {
							for (Action a : as) {
								transitions.put(a, t.getTarget().getName());
							}
						}
					}
					state2transitions.put(t.getSource().getName(), transitions);
				} else {
					HashMap<Action, String> transitions = state2transitions.get(t.getSource().getName());
					for (Iterator<AstTag> it = t.getTags().iterator(); it.hasNext(); ) {					
						List<String> tags = new ArrayList<String>(it.next().getIdentifiers());
						List<Action> as = actionList.getTaggedActions(tags);
						for (Action a : as) {
							transitions.put(a, t.getTarget().getName());
						}					
					}
				}
		
				// Find the target state. If it does not exist, create it.
				found = false;
				for (State s : schedule.getStates()) {
					if (s.getName().equals(t.getTarget().getName())) {
						found = true;
					}
				}
				if (!found) {		
					State s = IrFactory.eINSTANCE.createState();
					s.setName(t.getTarget().getName());
					if (t.getTarget().getName().equals(astActor.getSchedules().get(0).getInitialState().getName())) {
						schedule.setInitialState(s);
					}
					schedule.getStates().add(s);
					HashMap<Action, String> transitions = new HashMap<Action, String>();
					state2transitions.put(t.getTarget().getName(), transitions);
				}
			}

			
			// System.out.println("----------------------------------------------------------------");			
			for (State state : schedule.getStates()) {
				HashMap<Action, String> action2target = state2transitions.get(state.getName());

				PriorityGraph subgraph = graph.getSubgraph(action2target.keySet());
			
				state.setPriorityGraph(subgraph);
				state.setAction2TargetMap(action2target);
				// subgraph.print();
				/*
				sorted = subgraph.getOneTopologicalOrder();
				System.out.println("State '" + state.getName() + "'");
				for (Action a0 : sorted) {
					System.out.println(a0.getId());
				}
				System.out.println("----------------------------------------------------------------");
				*/			
			}			
		}
		
		return schedule;
	}

	@Override
	public Network caseAstNetwork(AstNetwork astNetwork) {
		Network network = IrFactory.eINSTANCE.createNetwork();
		network.setId(Util.getDefinitionId());
		network.setOuter(scopeStack.peek());
		scopeStack.push(network);
				
		network.setType(TypeSystem.createTypeNetwork());
		
		FindImportedAstSymbols.run(astNetwork, network);
		
		for (AstVariable p : astNetwork.getParameters()) {				
			Variable var = Util.createVariable(network, p, false);
			var.setParameter(true);
			network.getParameters().add(var);
		}
		
		List<VertexData> graphData = new ArrayList<VertexData>();
		for (AstVariable state : astNetwork.getVariables()) {
			graphData.add(new AstDeclVertex(state));
		}
		
		graphData = new Graph(graphData).sortByDependency();
		
		for (VertexData data : graphData) {
			if (data.getData() instanceof AstFunction){
				Declaration decl = Util.createForwardFunctionDeclaration(scopeStack.peek(), (AstFunction) data.getData(), false);				                                                                                   
				network.getDeclarations().add(decl);
			} else if (data.getData() instanceof AstProcedure) {
				Declaration decl =  Util.createForwardProcedureDeclaration(scopeStack.peek(), (AstProcedure) data.getData());				                                                                                   
				network.getDeclarations().add(decl);
			} else if (data.getData() instanceof AstVariable) {
				AstVariable var = (AstVariable) data.getData();
				Util.createVariable(scopeStack.peek(), var, false);
			} 
		}				
		
		for (AstPort p : astNetwork.getInputs()) {
			Port port = Util.createPort(scopeStack.peek(), p, false);
			network.getInputPorts().add(port);
		}
		
		for (AstPort p : astNetwork.getOutputs()) {
			Port port = Util.createPort(scopeStack.peek(), p, false);
			network.getOutputPorts().add(port);
		}
			
		Map<AstActorVariable, ActorInstance> ast2irMap = new HashMap<AstActorVariable, ActorInstance>();
		
		for (AstActorVariable astActorVariable : astNetwork.getInstances()) {
			ActorInstance actorInstance = IrFactory.eINSTANCE.createActorInstance();
			AstEntity astActorType = astActorVariable.getType();
			actorInstance.setName(astActorVariable.getName());
			actorInstance.setId(Util.getDefinitionId());
			TypeActor typeActor = Util.createType(scopeStack.peek(), astActorType);
			actorInstance.setType(typeActor);
			actorInstance.setScope(scopeStack.peek());
			
			List<AstPort> inputs = astActorType.getActor().getInputs();			
			for (AstPort port : inputs) {
				PortInstance portInstance = IrFactory.eINSTANCE.createPortInstance();
				portInstance.setName(port.getName());			
				portInstance.setActor(actorInstance);
				actorInstance.getInputs().add(portInstance);
			}

			List<AstPort> outputs = astActorType.getActor().getOutputs();
			for (AstPort port : outputs) {
				PortInstance portInstance = IrFactory.eINSTANCE.createPortInstance();
				portInstance.setName(port.getName());		
				portInstance.setActor(actorInstance);
				actorInstance.getOutputs().add(portInstance);
			}
						
			for (AstAssignParameter ap : astActorVariable.getParameters()) {
				Expression e = CreateIrExpression.convert(network, ap.getValue());
				TaggedExpression te = IrFactory.eINSTANCE.createTaggedExpression();
				te.setExpression(e);
				
				te.getExpression().setType(TypeConverter.typeOf(scopeStack.peek(), ap.getValue(), false));
				te.setTag(ap.getName());
				actorInstance.getActualParameters().add(te);
			}			
						
			network.getActors().add(actorInstance);
			ast2irMap.put(astActorVariable, actorInstance);
		}		

		for (AstConnection astConnection : astNetwork.getStructure().getConnections()) {			
			String sourcePortName = astConnection.getOutPort();
			String targetPortName = astConnection.getInPort();
			if (astConnection.getFrom() == null) {
				FromSource connection = IrFactory.eINSTANCE.createFromSource();	
				connection.setId(Util.getDefinitionId());
				
				Port sourcePort = Util.getInputPort(network, sourcePortName);
				
				ActorInstance targetActor = ast2irMap.get(astConnection.getTo().getVariable());				
				PortInstance targetPort = Util.getInputPort(targetActor, targetPortName);			
				targetPort.getConnections().add(connection);
				
				connection.setSource(sourcePort);
				connection.setTarget(targetPort);
				doAttributes(astConnection, connection);
				network.getConnections().add(connection);						
			} else if (astConnection.getTo() == null) {
				ToSink connection = IrFactory.eINSTANCE.createToSink();
				connection.setId(Util.getDefinitionId());
				
				ActorInstance sourceActor = ast2irMap.get(astConnection.getFrom().getVariable());				
				PortInstance sourcePort = Util.getOutputPort(sourceActor, sourcePortName);
				sourcePort.getConnections().add(connection);
				
				Port targetPort = Util.getOutputPort(network, targetPortName);
				
				connection.setSource(sourcePort);
				connection.setSink(targetPort);
				doAttributes(astConnection, connection);
				network.getConnections().add(connection);	
			} else {
				Point2PointConnection connection = IrFactory.eINSTANCE.createPoint2PointConnection();
				connection.setId(Util.getDefinitionId());

				ActorInstance sourceActor = ast2irMap.get(astConnection.getFrom().getVariable());				
				PortInstance sourcePort = Util.getOutputPort(sourceActor, sourcePortName);
				sourcePort.getConnections().add(connection);
				
				ActorInstance targetActor = ast2irMap.get(astConnection.getTo().getVariable());				
				PortInstance targetPort = Util.getInputPort(targetActor, targetPortName);			
				targetPort.getConnections().add(connection);
				
				connection.setSource(sourcePort);
				connection.setTarget(targetPort);
				doAttributes(astConnection, connection);
				network.getConnections().add(connection);		
			}
		}
		scopeStack.pop();

		return network;
	}
	
	private void doAttributes(AstConnection astConnection, Connection connection) {
		boolean bufferSizeAnnotation = false;
		for (AstConnectionAttribute astAttribute : astConnection.getAttribute()) {
			TaggedExpression attribute = IrFactory.eINSTANCE.createTaggedExpression();
			attribute.setTag(astAttribute.getName());
			attribute.setExpression(CreateIrExpression.convert(scopeStack.peek(), astAttribute.getValue()));
			connection.getAttributes().add(attribute);
			if (attribute.getTag().compareToIgnoreCase(BUFFER_SIZE_STRING) == 0) {
				bufferSizeAnnotation = true;
			}
		}		
		
		if (!bufferSizeAnnotation) {
			TaggedExpression attribute = IrFactory.eINSTANCE.createTaggedExpression();
			attribute.setTag(BUFFER_SIZE_STRING);
			attribute.setExpression(Util.createIntegerLiteral(DEFAULT_BUFFER_SIZE));
			connection.getAttributes().add(attribute);
		}
	}

	@Override
	public AbstractActor caseAstExternalActor(AstExternalActor astActor) {
		ExternalActor actor = IrFactory.eINSTANCE.createExternalActor();
		actor.setId(Util.getDefinitionId());
		
		for (AstVariable p : astActor.getParameters()) {				
			Variable var = Util.createVariable(actor, p, false);
			var.setParameter(true);
			actor.getParameters().add(var);
		}
		
		for (AstPort p : astActor.getInputs()) {
			Port port = Util.createPort(scopeStack.peek(), p, false);
			actor.getInputPorts().add(port);
		}
		
		for (AstPort p : astActor.getOutputs()) {
			Port port = Util.createPort(scopeStack.peek(), p, false);
			actor.getOutputPorts().add(port);
		}
		
		return actor;
	}


	@Override
	public Action caseAstAction(AstAction astAction) {
		Action action = IrFactory.eINSTANCE.createAction();
		action.setOuter(scopeStack.peek());
		scopeStack.push(action);
		
		String tagString = "";
		if (astAction.getTag() != null) {
			for (String t : astAction.getTag().getIdentifiers()) {
				action.getTag().add(t);
				tagString += "__" + t;
			}
		}
		action.setId("action" + (actionCounter++) + tagString);
		
		doAnnotations(astAction.getAnnotations(), action);
		
		for (AstInputPattern inputs : astAction.getInputs()) {
			Port port = Util.createInputPort(scopeStack.peek(), inputs, false);
			PortRead portRead = IrFactory.eINSTANCE.createPortRead();
			portRead.setId(Util.getDefinitionId());
			portRead.setPort(port);		
			
			Type type = port.getType();
			if (inputs.getRepeat() != null) {
				Expression repeat = CreateIrExpression.convert(scopeStack.peek(), inputs.getRepeat());
				portRead.setRepeat(repeat);
				type = TypeSystem.createTypeList(repeat, type);
			}
			
			for (int j = 0; j < inputs.getTokens().size(); j++) {
				AstVariable v = inputs.getTokens().get(j);
				Variable varDecl = Util.createVariable(action, v, type, null);
				portRead.getVariables().add(Util.createVariableReference(varDecl));				
				action.getInputs().add(portRead);
			}
							
		}
		
		for (AstExpression e : astAction.getGuards()) {
			final Guard guard =  IrFactory.eINSTANCE.createGuard();
			guard.setId(Util.getDefinitionId());
			guard.setOuter(scopeStack.peek());
			guard.setContext(scopeStack.peek());
						
			final List<PortPeek> peeks = new ArrayList<PortPeek>();
							
			new VoidSwitch() {
				@Override
				public Void caseAstExpressionSymbolReference(AstExpressionSymbolReference e)  {
					if (e.getSymbol().eContainer() instanceof AstInputPattern)  {
						AstInputPattern inputs = (AstInputPattern) e.getSymbol().eContainer();
						Port port = Util.createInputPort(scopeStack.peek(), inputs, false);						
						AstVariable token = e.getSymbol();
						int position = inputs.getTokens().indexOf(token);
						
						PortPeek portPeek = IrFactory.eINSTANCE.createPortPeek();
						portPeek.setId(Util.getDefinitionId());		
						
						if (inputs.getRepeat() != null) {
							Expression repeat = CreateIrExpression.convert(scopeStack.peek(), inputs.getRepeat());
							portPeek.setRepeat(repeat);
						}
						
						Variable varDecl = (Variable) Util.findIrDeclaration(e.getSymbol());
						portPeek.setPort(port);
						portPeek.setPosition(position);
						VariableReference varRef = Util.createVariableReference(varDecl);
						for(AstExpression expr : e.getIndexes()) {
							Expression index = CreateIrExpression.convert(scopeStack.peek(), expr);
							varRef.getIndex().add(index);
						}
						portPeek.setVariable(varRef);
						peeks.add(portPeek);
						
					}
					return super.caseAstExpressionSymbolReference(e);
				}
			}.doSwitch(e);
			
			for (PortPeek pp : peeks) {
				guard.getPeeks().add(pp);
			}
			
			guard.setType(TypeSystem.createTypeBool());
			guard.setBody(CreateIrExpression.convert(guard, e));
			action.getGuards().add(guard);
		}		
				
		List<VertexData> graphData = new ArrayList<VertexData>();
		for (AstVariable local : astAction.getVariables()) {
			graphData.add(new AstDeclVertex(local));
		}
		
		graphData = new Graph(graphData).sortByDependency();
		
		for (VertexData data : graphData) {
			AstVariable local = (AstVariable) ((AstDeclVertex) data).getData();
			Variable var = Util.createVariable(scopeStack.peek(), local, false);
			action.getDeclarations().add(var);
		}

		for (AstStatement s : astAction.getStatements()) {
			Statement stmt = (Statement) doSwitch(s);
			action.getStatements().add(stmt);
		}
			
		for (AstOutputPattern outputs : astAction.getOutputs()) {
			Port port = Util.createOutputPort(scopeStack.peek(), outputs, false);
			PortWrite portWrite = IrFactory.eINSTANCE.createPortWrite();
			portWrite.setId(Util.getDefinitionId());
			portWrite.setOuter(scopeStack.peek());
			scopeStack.push(portWrite);
			portWrite.setPort(port);					

			Type type = port.getType();
			if (outputs.getRepeat() != null) {
				Expression repeat = CreateIrExpression.convert(scopeStack.peek(), outputs.getRepeat());
				portWrite.setRepeat(repeat);
				type = TypeSystem.createTypeList(repeat, type);
			}

			for (AstExpression e : outputs.getValues()) {
				if(e instanceof AstExpressionSymbolReference && !(((AstExpressionSymbolReference) e).getSymbol() instanceof AstFunction)) {
					portWrite.getExpressions().add(CreateIrExpression.convert(scopeStack.peek(), e));
					action.getOutputs().add(portWrite);					
				} else {
					Variable var = Util.createTmpVariable(portWrite, type, null);
	
					Statement assign = Util.createAssignment(portWrite, var, e);
					portWrite.getStatements().add(assign);
					portWrite.getExpressions().add(Util.createVariableExpression(scopeStack.peek(), var));
					action.getOutputs().add(portWrite);
				}
			}
						
			// if (outputs.getRepeat() != null) {
			// 	 Expression repeat = CreateIrExpression.convert(scopeStack.peek(), outputs.getRepeat());				
			// 	 portWrite.setRepeat(repeat);
			// }
			
			scopeStack.pop();
		}
		
		scopeStack.pop();
		return action;
	}

	
	@Override
	public Variable caseAstFunction(AstFunction f) {
		// Look up the function prototype and add a body
		
		ForwardDeclaration forwardDeclaration = (ForwardDeclaration) Util.findIrDeclaration(f);
		Variable function = IrFactory.eINSTANCE.createVariable();
		forwardDeclaration.setDeclaration(function);

		function.setName(f.getName());
		function.setId(Util.getDefinitionId());		
		function.setScope(forwardDeclaration.getScope());
				
		LambdaExpression lambda = IrFactory.eINSTANCE.createLambdaExpression(); 
		lambda.setId(Util.getDefinitionId());
		lambda.setContext(forwardDeclaration.getScope());
		lambda.setOuter(scopeStack.peek());
		TypeLambda type = IrFactory.eINSTANCE.createTypeLambda();
		lambda.setType(type);
		
		function.setInitValue(lambda);
		function.setType(type);
		
		for (AstVariable param : f.getParameters()) {
			Variable decl = Util.createVariable(lambda, param, false);
			decl.setParameter(true);
			decl.setConstant(true);
			lambda.getParameters().add(decl);
			
			Type t = TypeConverter.convert(scopeStack.peek(), param.getType(), false);
			type.getInputTypes().add(t);
		}
								
		for (AstVariable v : f.getVariables()) {
			Util.createVariable(lambda, v, false);			
		}
				
		type.setOutputType(TypeConverter.convert(scopeStack.peek(), f.getType(), false));
		lambda.setBody(CreateIrExpression.convert(lambda, f.getExpression()));
				
		return function;
	}
	
	@Override
	public Variable caseAstProcedure(AstProcedure p) {		
		ForwardDeclaration forwardDeclaration = (ForwardDeclaration) Util.findIrDeclaration(p);
		
		Variable procedure = IrFactory.eINSTANCE.createVariable();
		forwardDeclaration.setDeclaration(procedure);
		
		procedure.setName(p.getName());
		procedure.setId(Util.getDefinitionId());		
		procedure.setScope(forwardDeclaration.getScope());
		
		ProcExpression proc = IrFactory.eINSTANCE.createProcExpression();
		proc.setId(Util.getDefinitionId());
		proc.setContext(forwardDeclaration.getScope());
		proc.setOuter(scopeStack.peek());
		TypeProc type = IrFactory.eINSTANCE.createTypeProc();
		proc.setType(type);
		
		procedure.setInitValue(proc);
		procedure.setType(type);
		
		for (AstVariable param : p.getParameters()) {
			Variable decl = Util.createVariable(proc, param, false);
			assert(decl != null);
			decl.setParameter(true);
			decl.setConstant(true);
			proc.getParameters().add(decl);
			
			Type t = TypeConverter.convert(scopeStack.peek(), param.getType(), false);
			type.getInputTypes().add(t);
		}
		
		scopeStack.push(proc);
		Block body = Util.createBlock(proc);
		body.setOuter(scopeStack.peek());
		proc.setBody(body);		
		scopeStack.push(body);

		for (AstVariable v : p.getVariables()) {
			Variable def = Util.createVariable(body, v, false);
			body.getDeclarations().add(def);
		}
		
		for (AstStatement s : p.getStatements()) {
			Statement stmt = (Statement) doSwitch(s);
			body.getStatements().add(stmt);
		}
		
		scopeStack.pop();
		scopeStack.pop();
		return procedure;
	}	
		
	@Override
	public EObject caseAstStatementAssign(AstStatementAssign astAssign) {
		Variable varDecl = (Variable) Util.findIrDeclaration(astAssign.getTarget()); 
		VariableReference varRef = Util.createVariableReference(varDecl);
		for (AstExpression e : astAssign.getIndexes()) {
			Expression expr = CreateIrExpression.convert(scopeStack.peek(), e);
			varRef.getIndex().add(expr);
		}

		for (AstMemberAccess mv : astAssign.getMember()) {
			Member member = IrFactory.eINSTANCE.createMember();
			member.setName(mv.getName());
			// member.setType(TypeConverter.typeOf(scopeStack.peek(), mv));
			for(AstExpression index : mv.getMemberIndex()) {
				Expression expr = CreateIrExpression.convert(scopeStack.peek(), index);
				member.getIndex().add(expr);
			}
			varRef.getMember().add(member);
		}

		Statement result = Util.createAssignment(scopeStack.peek(), varRef, astAssign.getValue());
		return result;
	}
		
	@Override
	public EObject caseAstStatementCall(AstStatementCall astCall) {
		ProcCall result = IrFactory.eINSTANCE.createProcCall();
		Declaration fun = (Declaration) Util.findIrDeclaration(astCall.getProcedure());
		result.setProcedure(fun);
		
		for (AstExpression p : astCall.getParameters()) {
			Expression ve = CreateIrExpression.convert(scopeStack.peek(), p);
			result.getInParameters().add(ve);
		}
		
		return result;
	}

	@Override
	public EObject caseAstStatementForeach(AstStatementForeach astForeach) {
		ForEach foreach = IrFactory.eINSTANCE.createForEach();

		for (AstForeachGenerator g : astForeach.getGenerators()) {			
			Generator generator = IrFactory.eINSTANCE.createGenerator();
			generator.setId(Util.getDefinitionId());
			foreach.getGenerators().add(generator);			
			generator.setOuter(scopeStack.peek());
			scopeStack.push(generator);			

			Util.createVariable(generator, g.getVariable(), false);
			generator.setSource(CreateIrExpression.convert(scopeStack.peek(), g.getExpression()));	
		}

		Block block = Util.createBlock(scopeStack.peek());
		scopeStack.push(block);

		for (AstVariable astVar : astForeach.getVariables()) {
			Variable var = Util.createVariable(scopeStack.peek(), astVar, false);
			block.getDeclarations().add(var);
		}
		
		for (AstStatement astStmt : astForeach.getStatements()) {
			Statement stmt = (Statement) doSwitch(astStmt);
			block.getStatements().add(stmt);
		}
		
		foreach.setBody(block);
		
		scopeStack.pop();
		for (AstForeachGenerator g : astForeach.getGenerators()) 
			scopeStack.pop();

		return foreach;
		
		/*
		Block block = Util.createBlock(scopeStack.peek());
		scopeStack.push(block);
		
		for (AstForeachGenerator g : astForeach.getGenerators()) {			
			Variable loopCounter = Util.createTmpVariable(scopeStack.peek(), TypeSystem.createTypeInt(), Util.createIntegerLiteral(0));
			Variable listExpr    = Util.createTmpVariable(scopeStack.peek(), TypeSystem.createTypeInt(), CreateIrExpression.convert(scopeStack.peek(), g.getExpression()));						
			Expression loopCondition = Util.createBinaryExpression(scopeStack.peek(), Util.createVariableExpression(scopeStack.peek(), loopCounter), "<", 
																   TypeSystem.sizeOfList(TypeEvaluator.typeOf(null, scopeStack.peek() ,g.getExpression())),
																   TypeSystem.createTypeInt());
			
			Expression value = Util.createIndexedVariableReference(scopeStack.peek(), listExpr, loopCounter);
			Util.createVariable(scopeStack.peek(), g.getVariable(), value);
			
			Block body = Util.createBlock(scopeStack.peek());
			Statement increment = Util.createIncrementStatement(scopeStack.peek(), loopCounter);
			scopeStack.push(block);
			
			WhileLoop whileLoop = IrFactory.eINSTANCE.createWhileLoop();	
			whileLoop.setCondition(loopCondition);
			whileLoop.setBody(body);
		}
		
		List<GraphData> graphData = new ArrayList<GraphData>();
		for (AstVariable local : astForeach.getVariables()) {
			graphData.add(new DefinitionGraphData(local));
		}
		
		graphData = new Graph(graphData).sortByDependency();
		
		for (GraphData data : graphData) {
			AstVariable local = (AstVariable) ((DefinitionGraphData) data).getData();
			Variable var = Util.createVariable(scopeStack.peek(), local);
			scopeStack.peek().getDeclarations().add(var);
		}
		
		for (AstStatement s : astForeach.getStatements()) {
			Statement stmt = (Statement) doSwitch(s);
			((Block) scopeStack.peek()).getStatements().add(stmt);
		}
		
		for (AstForeachGenerator g : astForeach.getGenerators()) {			
			scopeStack.pop();
		}

		scopeStack.pop();
		
		return block;
		*/
	}
	
	
	@Override
	public EObject caseAstStatementIf(AstStatementIf stmtIf) {
		IfStatement result = IrFactory.eINSTANCE.createIfStatement();
		
		Expression condition = CreateIrExpression.convert(scopeStack.peek(), stmtIf.getCondition());
		result.setCondition(condition);

		
		Block thenBlock = Util.createBlock(scopeStack.peek());
		Block elseBlock = Util.createBlock(scopeStack.peek());
		result.setThenBlock(thenBlock);		
		result.setElseBlock(elseBlock);		

		scopeStack.push(thenBlock);
		for (AstStatement s : stmtIf.getThen()) {
			Statement stmt = (Statement) doSwitch(s);
			result.getThenBlock().getStatements().add(stmt);
		}
		scopeStack.pop();
		
		scopeStack.push(elseBlock);
		for (AstStatement s : stmtIf.getElse()) {
			Statement stmt = (Statement) doSwitch(s);
			result.getElseBlock().getStatements().add(stmt);
		}
		scopeStack.pop();
		
		return result;
	}

	@Override
	public EObject caseAstStatementWhile(AstStatementWhile stmtWhile) {
		WhileLoop whileLoop = IrFactory.eINSTANCE.createWhileLoop();
		
		Expression condition = CreateIrExpression.convert(scopeStack.peek(), stmtWhile.getCondition());
		
		whileLoop.setCondition(condition);
		
		Block body = Util.createBlock(scopeStack.peek());
		whileLoop.setBody(body);
		scopeStack.push(body);
		for (AstStatement s : stmtWhile.getStatements()) {
			Statement stmt = (Statement) doSwitch(s);
			body.getStatements().add(stmt);
		}
		scopeStack.pop();
		
		return whileLoop;
	}

	@Override
	public EObject caseAstStatementBlock(AstStatementBlock stmtBlock) {
		Block block = Util.createBlock(scopeStack.peek());
		for (AstVariable var : stmtBlock.getVariables()) {
			Variable def = Util.createVariable(block, var, false);
			block.getDeclarations().add(def);			
		}
		
		for (AstStatement s : stmtBlock.getStatements()) {
			Statement stmt = (Statement) doSwitch(s);
			block.getStatements().add(stmt);
		}
		
		return block;
	}
	
	@Override
	public EObject caseAstStatementCase(AstStatementCase stmtCase) {
		CaseStatement caseStatement = IrFactory.eINSTANCE.createCaseStatement();
		caseStatement.setId(Util.getDefinitionId());
		Expression condition = CreateIrExpression.convert(scopeStack.peek(), stmtCase.getExpression());
		caseStatement.setExpression(condition);
		
		try {
			AstVariable v = stmtCase.getExpression().getSymbol();
			AstType astType = null;;
			if (v.eContainer() instanceof AstInputPattern) {
				AstInputPattern pattern = (AstInputPattern) v.eContainer();
				AstPort port = null;
				if (pattern.getPort() != null) {
					port = pattern.getPort();
				} else {
					AstAction action = (AstAction) pattern.eContainer();
					List<AstInputPattern> inputs = action.getInputs();
					AstActor actor = (AstActor) action.eContainer();
					List<AstPort> ports = actor.getInputs();
					for (int i = 0; i < inputs.size(); i++) {
						if (inputs.get(i) == pattern) {
							port = ports.get(i);
						}
					}
				}
				astType = port.getType();
			} else {
				astType = v.getType();
			}
			Type t = TypeConverter.convert(null, astType, true);
			if (t instanceof TypeLambda) {
				t = ((TypeLambda) t).getOutputType();
			}
			assert(t instanceof TypeUser);
			TypeUser tu = (TypeUser) t;
			for (AstStatementAlternative a : stmtCase.getCases()) {
				StmtAlternative alt = IrFactory.eINSTANCE.createStmtAlternative();
				alt.setId(Util.getDefinitionId());
				alt.setOuter(scopeStack.peek());
				scopeStack.push(alt);
				doPattern(tu, a.getPattern());

				for (AstExpression g : a.getGuards()) {
					final Guard guard =  IrFactory.eINSTANCE.createGuard();
					guard.setId(Util.getDefinitionId());
					guard.setOuter(scopeStack.peek());
					guard.setContext(scopeStack.peek());
					guard.setBody(CreateIrExpression.convert(scopeStack.peek(), g));
				}
				
				//alt.setPattern(pattern);
				caseStatement.getAlternatives().add(alt);
				scopeStack.pop();
			}
		} catch (Exception e) {
			System.err.println("Internal error in Ast2Ir");
		}
				
		return caseStatement;
	}
		
	private Pattern doPattern(TypeUser tu, AstPattern astPattern) { 
		Pattern pattern = IrFactory.eINSTANCE.createPattern();
		pattern.setTag(astPattern.getTag());
		TaggedTuple tt = null;
		TypeDeclaration typedef = (TypeDeclaration) tu.getDeclaration();            
 
		for (TaggedTuple tmp :  ((TypeTuple) typedef.getType()).getTaggedTuples()) {
			if (tmp.getTag().equals(astPattern.getTag())) {
				tt = tmp;
			}
		}
		assert(tt != null);
				
		for (AstSubPattern astSubPattern : astPattern.getSubpatterns()) {
			SubPattern sp = IrFactory.eINSTANCE.createSubPattern();
			int pos = astPattern.getSubpatterns().indexOf(astSubPattern);
			String label;
			if (astSubPattern.getLabel() != null) {
				label = astSubPattern.getLabel();
			} else {
				label = tt.getFields().get(pos).getName();
			}
			sp.setLabel(label);
			
			if (astSubPattern.getCondition() != null) {
				Type t1 = tt.getFields().get(pos).getType();
				Expression memberValue = Util.createTaggedTupleFieldExpression(scopeStack.peek(), tu, astPattern.getTag(), label);
				Variable tmp = Util.createTmpVariable(scopeStack.peek(), t1, memberValue);
				
				Expression e = CreateIrExpression.convert(null, astSubPattern.getCondition());
				VariableExpression varExpr = Util.createVariableExpression(scopeStack.peek(), tmp);
				Expression condition = Util.createBinaryExpression(scopeStack.peek(), varExpr, "=", e, TypeSystem.createTypeBool());
				
				final Guard guard =  IrFactory.eINSTANCE.createGuard();
				guard.setId(Util.getDefinitionId());
				guard.setOuter(scopeStack.peek());
				guard.setContext(scopeStack.peek());
				guard.setBody(condition);
				((StmtAlternative) scopeStack.peek()).getGuards().add(guard);
			} else if (astSubPattern.getVariable() != null) {
				Expression value = Util.createTaggedTupleFieldExpression(scopeStack.peek(), tu, astPattern.getTag(), label);
				Variable var = Util.createVariable(scopeStack.peek(), astSubPattern.getVariable(), tt.getFields().get(pos).getType(), value);
				sp.setBinding(var);
			} else if (astSubPattern.getPattern() != null) {
				Type t1 = tt.getFields().get(pos).getType();
				sp.setPattern(doPattern((TypeUser) t1, astSubPattern.getPattern()));					
			}		
			pattern.getSubPatterns().add(sp);			
		}
		
		return pattern;		
	}
	
	private void doAnnotations(List<AstAnnotation> annotations, Node irNode) {
		
		for (AstAnnotation astAnnotation : annotations) {
			Annotation annotation = Util.createAnnotation(astAnnotation);
			irNode.getAnnotations().add(annotation);
		}
	}

}
