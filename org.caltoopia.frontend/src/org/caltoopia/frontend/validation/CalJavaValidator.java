package org.caltoopia.frontend.validation;

/*
 * Copyright (c) 2010, IETR/INSA of Rennes
 * Copyright (c) 2011, Ericsson AB
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 *   * Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *   * Neither the name of the IETR/INSA of Rennes nor the names of its
 *     contributors may be used to endorse or promote products derived from this
 *     software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 */


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.caltoopia.frontend.cal.AstAbstractActor;
import org.caltoopia.frontend.cal.AstAction;
import org.caltoopia.frontend.cal.AstActor;
import org.caltoopia.frontend.cal.AstActorVariable;
import org.caltoopia.frontend.cal.AstAssignParameter;
import org.caltoopia.frontend.cal.AstExternalActor;
import org.caltoopia.frontend.cal.AstInequality;
import org.caltoopia.frontend.cal.AstMemberAccess;
import org.caltoopia.frontend.cal.AstNamespace;
import org.caltoopia.frontend.cal.AstNetwork;
import org.caltoopia.frontend.cal.AstEntity;
import org.caltoopia.frontend.cal.AstExpression;
import org.caltoopia.frontend.cal.AstExpressionCall;
import org.caltoopia.frontend.cal.AstExpressionVariable;
import org.caltoopia.frontend.cal.AstFunction;
import org.caltoopia.frontend.cal.AstGenerator;
import org.caltoopia.frontend.cal.AstInputPattern;
import org.caltoopia.frontend.cal.AstOutputPattern;
import org.caltoopia.frontend.cal.AstPort;
import org.caltoopia.frontend.cal.AstPriority;
import org.caltoopia.frontend.cal.AstProcedure;
import org.caltoopia.frontend.cal.AstSchedule;
import org.caltoopia.frontend.cal.AstStatementAssign;
import org.caltoopia.frontend.cal.AstStatementCall;
import org.caltoopia.frontend.cal.AstStatementForeach;
import org.caltoopia.frontend.cal.AstStructure;
import org.caltoopia.frontend.cal.AstTag;
import org.caltoopia.frontend.cal.AstTransition;
import org.caltoopia.frontend.cal.AstTypeName;
import org.caltoopia.frontend.cal.AstVariable;
import org.caltoopia.frontend.cal.AstVariableReference;
import org.caltoopia.frontend.cal.CalPackage;
import org.caltoopia.frontend.cal.AstConnection;
import org.caltoopia.frontend.cal.Import;
import org.caltoopia.frontend.util.BooleanSwitch;
import org.caltoopia.frontend.util.CalActionList;
import org.caltoopia.frontend.util.OrccUtil;
import org.caltoopia.frontend.util.Util;
import org.caltoopia.frontend.util.VoidSwitch;
import org.caltoopia.frontend.validation.AbstractCalJavaValidator;
import org.caltoopia.types.TypeConverter;
import org.caltoopia.types.TypeException;
import org.caltoopia.types.TypePrinter;
import org.caltoopia.types.TypeSystem;
import org.caltoopia.ir.Type;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;
import org.eclipse.xtext.util.SimpleAttributeResolver;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.CheckType;

import com.google.inject.Inject;

/**
 * This class describes the validation of a CAL actor or a CAL network. The checks tagged as
 * "expensive" are only performed when the file is saved and before code
 * generation.
 * 
 * @author Matthieu Wipliez
 * 		   Johan Eker
 * 
 */
public class CalJavaValidator extends AbstractCalJavaValidator {
	
	private SimpleAttributeResolver<EObject, String> resolver = SimpleAttributeResolver.newResolver(String.class, "name");

	private TypePrinter typePrinter = new TypePrinter();
	
	public CalJavaValidator() {
	}

	@Inject
	ResourceDescriptionsProvider resourceDescriptionsProvider;
	
	@Check(CheckType.NORMAL) 
	public void checkImport(Import imp) {
		String s = imp.getImportedNamespace();
		
		IResourceDescriptions resourceDescriptions = resourceDescriptionsProvider.getResourceDescriptions(imp.eResource());
		
		if (s.endsWith("*")) {
			String[] importSegments = s.substring(0, s.length() - 2).split("\\.");
			
			QualifiedName qn = QualifiedName.create(importSegments);
			
			for (IResourceDescription resourceDescription : resourceDescriptions.getAllResourceDescriptions()) {
				if (resourceDescription.getExportedObjects(CalPackage.eINSTANCE.getAstNamespace(), qn, true).iterator().hasNext()) {
					return;
				}
			}
		} else {		
			String[] importSegments = s.split("\\.");		
			QualifiedName qn = QualifiedName.create(importSegments);
			
			if (resourceDescriptions.getExportedObjects(CalPackage.eINSTANCE.getAstVariable(), qn, true).iterator().hasNext()  ||
				resourceDescriptions.getExportedObjects(CalPackage.eINSTANCE.getAstTypeName(), qn, true).iterator().hasNext()  ||
				resourceDescriptions.getExportedObjects(CalPackage.eINSTANCE.getAstFunction(), qn, true).iterator().hasNext()  || 
				resourceDescriptions.getExportedObjects(CalPackage.eINSTANCE.getAstProcedure(), qn, true).iterator().hasNext() || 
				resourceDescriptions.getExportedObjects(CalPackage.eINSTANCE.getAstEntity(), qn, true).iterator().hasNext() ) {
				return;
			}
		}
		error("Import '" + imp.getImportedNamespace() + "' not found", imp, CalPackage.eINSTANCE.getImport_ImportedNamespace(), -1);
	}
	
	
	@Check(CheckType.NORMAL)
	public void checkAction(AstAction action) {		
		checkActionVariables(action);
		checkActionInputs(action.getInputs());
		checkActionOutputs(action.getOutputs());
	}

	/**
	 * Checks the input pattern.
	 * 
	 * @param inputs
	 *            the input patterns of an action
	 */
	private void checkActionInputs(List<AstInputPattern> inputs) {
		Set<String> names = new HashSet<String>();

		for (AstInputPattern pattern : inputs) {
			// check duplicate ports in output pattern
			
			if (pattern.getPort() != null) {
				String name = pattern.getPort().getName();
				if (names.contains(name)) {
					error("Duplicate port " + name + " in input pattern", pattern,
							CalPackage.eINSTANCE.getAstInputPattern_Port(), -1);
				}
				names.add(name);
			}
		}
	}

	/**
	 * Checks the token expressions are correctly typed.
	 * 
	 * @param outputs
	 *            the output patterns of an action
	 */
	private void checkActionOutputs(List<AstOutputPattern> outputs) {

		Set<String> names = new HashSet<String>();

		/*
		for (AstOutputPattern pattern : outputs) {
			// check duplicate ports in output pattern
			if (pattern.getPort() != null) {
				String name = pattern.getPort().getName();
				if (names.contains(name)) {
					error("Duplicate port " + name + " in output pattern", pattern,
							CalPackage.AST_OUTPUT_PATTERN__PORT);
				}
				names.add(name);

				AstType portType = pattern.getPort().getType();
				AstExpression astRepeat = pattern.getRepeat();
				if (astRepeat == null) {
					List<AstExpression> values = pattern.getValues();
					for (AstExpression value : values) {				
						AstType type = value.getAssignedType();
						try {
							TypeSystem.isCompatible(type, portType); 							
						} catch (TypeException x) {
							error("This expression must be of type " + portType + ": " + x.getMessage(),
							       value, 
							       CalPackage.AST_EXPRESSION);								
						}
					}					
				}
			} else {
				// each value is supposed to be a list
				List<AstExpression> values = pattern.getValues();
				for (AstExpression value : values) {
				
					AstType type = value.getAssignedType();
					if (TypeSystem.isList(type)) {
						AstType elementType = TypeSystem.getElementType(type);							
						try {
							TypeSystem.isCompatible(type, elementType);
						} catch (TypeException x) {
							error("this expression must be of type List of " + elementType,
									value,
									CalPackage.AST_EXPRESSION);
					
						}
					}
				}
			}
		}
		*/
	}
	

	@Check(CheckType.NORMAL)	
	public void CheckTypeDef(AstTypeName typedef) {
		for (AstFunction tc : typedef.getConstructor()) {			
			for (AstVariable v : tc.getMembers()) {
				/*
				try {
					TypeSystem.validateAstType(v.getType());
				} catch (TypeException x) {
					error(x.getMessage(),
						  v.getType(),
						  CalPackage.AST_TYPE);
				}
				*/
			}
		}
	}
	
	/**
	 * Checks the tokens and variables declared in the action are unique.
	 * 
	 * @param action
	 *            the action to check
	 */
	private void checkActionVariables(AstAction action) {
		List<AstInputPattern> inputs = action.getInputs();
		List<AstVariable> variables = new ArrayList<AstVariable>();
		for (AstInputPattern pattern : inputs) {
			variables.addAll(pattern.getTokens());
		}

		variables.addAll(action.getVariables());
		checkUniqueNames(variables);
	}

	@Check(CheckType.NORMAL)
	public void checkActor(AstActor actor) {
		
		TypeConverter.setValidator(this);
		
		// check unique names
		checkUniqueNames(actor.getParameters());
		checkUniqueNames(actor.getInputs());
		checkUniqueNames(actor.getOutputs());
		checkUniqueNames(actor.getFunctions());
		checkUniqueNames(actor.getProcedures());
		checkUniqueNames(actor.getStateVariables());

		// build action list
		CalActionList actionList = new CalActionList();
		actionList.addActions(actor.getActions());

		// check FSM and priorities
		if (actor.getSchedules().size() == 1) {
			checkFsm(actionList, actor.getSchedules().get(0));
		} else if (actor.getSchedules().size() > 1) {
			for (AstSchedule schedule : actor.getSchedules()) {
				error("Only one schedule is allowed",
					CalPackage.Literals.AST_ACTOR__SCHEDULES);
			}
		}

		checkPriorities(actor, actionList);
	}

	@Check(CheckType.FAST)
	public void checkMemberVariable(AstMemberAccess var) {
		TypeConverter.typeOf(null, var, true);
	}
	
	
	@Check(CheckType.NORMAL)
	public void checkAssign(AstStatementAssign assign) {
		AstVariable variable = assign.getTarget().getVariable();
		if (variable.isConstant()) {
			error("The variable " + variable.getName() + " is not assignable",
					CalPackage.Literals.AST_STATEMENT_ASSIGN__TARGET);
		}
		Type LHS = TypeConverter.typeOf(null, assign.getTarget().getVariable(), assign.getIndexes(), assign.getMember(), true);
		Type RHS = TypeConverter.typeOf(null, assign.getValue(), true);
		try {
			TypeSystem.isCompatible(LHS, RHS); 	
			/*
			info("Type info: LHS:" + typePrinter.doSwitch(LHS) + " & RHS:" + typePrinter.doSwitch(RHS),
					assign,
					CalPackage.eINSTANCE.getAstStatementAssign_Target(), -1);
					*/
		} catch (Exception e) {
			String s1 = LHS != null ? typePrinter.doSwitch(LHS) : "<type fail>";
			String s2 = RHS != null ? typePrinter.doSwitch(RHS) : "<type fail>";
			error("Type error in assignment: LHS=" + s1 + " & RHS=" + s2 + " -- " + e.getMessage(),
					assign,
					CalPackage.eINSTANCE.getAstStatementAssign_Target(), -1);
			return;
		} 	
	}
	
	
	@Check(CheckType.FAST)
	public void checkActorDeclaration(AstActorVariable decl) {		
		List<String> params = new ArrayList<String>();
		if (decl.getType() != null) {
			AstAbstractActor actor = decl.getType().getActor();
			for (AstVariable v : actor.getParameters()) {
				params.add(v.getName());
			}
		
			if (params.size() != decl.getParameters().size()) {
				error("Incorrect number of parameters in actor declaration", 
						decl, 
						CalPackage.eINSTANCE.getAstActorVariable_Parameters(), -1);
			}
			for (AstAssignParameter p : decl.getParameters()) {
				boolean unknown = true;
				for (String s : params) {
					if (s.equals(p.getName()))
						unknown = false;
				}
				if (unknown) 
					error("unknown parameter '" + p.getName() + "'", 
							decl, 
							CalPackage.eINSTANCE.getAstActorVariable_Name(), -1);			
			}
		} else {
			error("unknown type for entity '" + decl.getName() + "'", 
					decl, 
					CalPackage.eINSTANCE.getAstActorVariable_Name(), -1);
		}
	}
	
	@Check(CheckType.NORMAL)
	public void checkAstStatementCall(AstStatementCall astCall) {
		AstProcedure procedure = astCall.getProcedure();
		String name = procedure.getName();
		List<AstExpression> parameters = astCall.getParameters();
		if (procedure.getParameters().size() != parameters.size()) {
			error("procedure '" + name + "' takes "	+ procedure.getParameters().size() + " arguments.",
					astCall, 
					CalPackage.eINSTANCE.getAstStatementCall_Procedure(), -1);
			return;
		}

		Iterator<AstVariable> itFormal = procedure.getParameters().iterator();
		Iterator<AstExpression> itActual = parameters.iterator();
		int index = 0;
		while (itFormal.hasNext() && itActual.hasNext()) {
			Type formalType = TypeConverter.convert(null, itFormal.next().getType(), true);
			AstExpression expression = itActual.next();
			Type actualType = TypeConverter.typeOf(null, expression, true);
			try {
				TypeSystem.isCompatible(formalType, actualType);
			} catch (Exception x) {
				error("Parameter type mismatch:" + x.getMessage(), 
						astCall,
						CalPackage.eINSTANCE.getAstStatementCall_Parameters(), index);
			}	
			index++;
		}
	}
		
	@Check(CheckType.NORMAL)
	public void checkAstExpressionCall(AstExpressionCall astCall) {
		AstFunction function = astCall.getFunction();
		String name = function.getName();
		List<AstVariable> formalParameters;
		
		if (function.eContainer() instanceof AstTypeName) {
			// This is a call to a type constructor
			formalParameters = function.getMembers();
		} else {
			formalParameters = function.getParameters();
		}
		List<AstExpression> parameters = astCall.getParameters();
		if (formalParameters.size() != parameters.size()) {
			error("function or type constructor '" + name + "' takes " + formalParameters.size() + " arguments.",
					astCall, 
					CalPackage.eINSTANCE.getAstExpressionCall_Function(), -1);
			return;
		}
		
		Iterator<AstVariable> itFormal = function.getParameters().iterator();
		Iterator<AstExpression> itActual = parameters.iterator();
		int index = 0;
		while (itFormal.hasNext() && itActual.hasNext()) {
			Type formalType = TypeConverter.convert(null, itFormal.next().getType(), true);
			AstExpression expression = itActual.next();
			Type actualType = TypeConverter.typeOf(null, expression, true);
			try {
				TypeSystem.isCompatible(formalType, actualType);
			} catch (Exception x) {
				TypeConverter.typeOf(null, expression, true);
				error("Parameter type mismatch:" + x.getMessage(), 
						astCall,
						CalPackage.eINSTANCE.getAstExpressionCall_Parameters(), index);
				
				return; 
			}	
			index++;
		}
	}
	
	@Check(CheckType.NORMAL)
	public void checkEntity(AstEntity entity) {
		System.out.println("Processing actor/network '" + entity.getActor().getName() + "'");
 	}

	/**
	 * Checks the given FSM using the given action list. This check is not
	 * annotated because we need to build the action list, which is also useful
	 * for checking the priorities, and we do not want to build that twice.
	 * 
	 * @param actionList
	 *            the action list of the actor
	 * @param schedule
	 *            the FSM of the actor
	 */
	private void checkFsm(CalActionList actionList, AstSchedule schedule) {
		for (AstTransition transition : schedule.getTransitions()) {
			List<AstTag> tags = transition.getTags();
			if (tags != null) {
				for (AstTag tag : tags) {
					List<AstAction> actions = actionList.getTaggedActions(tag.getIdentifiers());
					if (actions == null || actions.isEmpty()) {
						error("tag " + getName(tag) + " does not refer to any action", 
								transition,
								CalPackage.eINSTANCE.getAstTransition_Tags(), -1);
					}
				}
			}
		}
	}

	@Check(CheckType.NORMAL)
	public void checkFunction(final AstFunction function) {
		checkUniqueNames(function.getParameters());
		checkUniqueNames(function.getVariables());
		checkReturnType(function);

		checkParameters(function.getParameters());
		
		// Finally, check if a function is not used and give a
		// warning if not. We only do this for functions in the 
		// actor scope, i.e. functions that can be considered local.

		// Skip global functions
		if (function.eContainer() instanceof AstNamespace) {
			return;
		}
		
		// // Skip global functions
		if (function.eContainer() instanceof AstTypeName) {
			return;
		}	
	
		boolean used = new BooleanSwitch() {

			@Override
			public Boolean caseAstExpressionCall(AstExpressionCall expression) {
				if (expression.getFunction().equals(function)) {
					return true;
				}

				return super.caseAstExpressionCall(expression);
			}

		}.doSwitch(Util.getTopLevelContainer(function));

		if (!used) {
			warning("The function " + function.getName() + " is never called", 
					CalPackage.Literals.AST_FUNCTION__NAME);
		}
	}

	private void checkParameters(final EList<AstVariable> parameters) {
		//Check parameters for circular dependencies; type expressions (like "size")
		//in one parameter may not depend on any other parameter 
				
		for (AstVariable parameter : parameters) {
			new BooleanSwitch() {

				@Override
				public Boolean caseAstVariableReference(AstVariableReference varRef) {
					for (AstVariable parameter : parameters) {
						if (varRef.getVariable() == parameter) {
							error("Circular dependency in parameters not allowed.", 
									parameter,
									CalPackage.eINSTANCE.getAstVariable_Name(), -1);
							return true;
						}
					}

					return false;
				}

			}.doSwitch(parameter);

		}					
	}

	@Check(CheckType.NORMAL)
	public void checkGenerator(AstGenerator generator) {
		//TypeEvaluator.TypeOf(this, null, generator);
	}

	/**
	 * Checks that the given variable is used. If it is not, issue a warning.
	 * 
	 * @param variable
	 *            a variable
	 */
	private void checkIsVariableUsed(final AstVariable variable) {
		// do not take variables declared by input patterns and
		// generator/foreach
		EObject container = variable.eContainer();
		if (container instanceof AstInputPattern
			|| container instanceof AstGenerator
			|| container instanceof AstStatementForeach
			|| container instanceof AstNamespace
			|| container instanceof AstFunction) {
			return;
		}

		try {
		boolean used = new BooleanSwitch() {
		
			@Override
			public Boolean caseAstExpressionVariable(AstExpressionVariable expression) {
				return expression.getValue().getVariable().equals(variable);
			}

			@Override
			public Boolean caseAstStatementAssign(AstStatementAssign assign) {
				if (assign.getTarget().getVariable().equals(variable)) {
					return true;
				}

				return super.caseAstStatementAssign(assign);
			}

		}.doSwitch(Util.getTopLevelContainer(variable));
		
		
		// do not warn about unused actor parameters
		// used for system actors
		EReference reference = variable.eContainmentFeature();
		if (!used && reference != CalPackage.eINSTANCE.getAstAbstractActor_Parameters()) {
			warning("The variable " + variable.getName() + " is never read",
					CalPackage.Literals.AST_VARIABLE__NAME);
		}
		
		} catch (Exception x) {
			System.out.println(x.getMessage());
		}
	}

	/**
	 * Checks the priorities of the given actor using the given action list.
	 * This check is not annotated because we need to build the action list,
	 * which is also useful for checking the FSM, and we do not want to build
	 * that twice.
	 * 
	 * @param actor
	 *            the actor
	 * @param actionList
	 *            the action list of the actor
	 */
	private void checkPriorities(AstActor actor, CalActionList actionList) {
		for (AstPriority prio : actor.getPriorities()) {
			List<AstInequality> inEqs = prio.getInequalities();
			if (inEqs != null) {
				for (AstInequality inEq : inEqs) {
					List<AstTag> tags = inEq.getTags();
					if (tags != null) {
						int cnt = 0;
						for (AstTag tag : tags) {
							List<AstAction> actions = actionList.getTaggedActions(tag.getIdentifiers());
							if (actions == null || actions.isEmpty()) {
								error("tag " + getName(tag) + " does not refer to any action", 
										inEq,
										CalPackage.eINSTANCE.getAstInequality_Tags(), cnt);
							}
							cnt++;
						}
					}
				}
			}
		}
	}

	@Check(CheckType.NORMAL)
	public void checkProcedure(final AstProcedure procedure) {
		checkUniqueNames(procedure.getParameters());
		checkUniqueNames(procedure.getVariables());

		checkParameters(procedure.getParameters());
		
		boolean used = new BooleanSwitch() {

			@Override
			public Boolean caseAstStatementCall(AstStatementCall call) {
				if (call.getProcedure().equals(procedure)) {
					return true;
				}

				return false;
			}

		}.doSwitch(Util.getTopLevelContainer(procedure));

		if (!used) {
			warning("The procedure " + procedure.getName() + " is never called",
					CalPackage.Literals.AST_PROCEDURE__NAME);
		}
	}

	private void checkReturnType(AstFunction function) {
/*
		TypeChecker checker = new TypeChecker(this);
		Type returnType = function.getIrType();
		Type expressionType = function.getExpression().getIrType();
		if (!checker.isConvertibleTo(expressionType, returnType)) {
			error("Type mismatch: cannot convert from " + expressionType
					+ " to " + returnType, function.getExpression(),
					CalPackage.AST_EXPRESSION);
		}
*/		
	}

	private void checkUniqueNames(List<? extends EObject> variables) {
		Set<String> names = new HashSet<String>();
		for (EObject variable : variables) {
			String name = getName(variable);
			if (names.contains(name)) {
				error("Duplicate variable " + name, 
						variable,
						CalPackage.eINSTANCE.getAstVariable_Name(), -1);
			}
			names.add(name);
		}
	}

	@Check(CheckType.NORMAL)
	public void checkNamespace(AstNamespace namespace) {
		System.out.println("Processing namespace '" + namespace.getName() + "'");
		// check unique names
		checkUniqueNames(namespace.getFunctions());
		checkUniqueNames(namespace.getEntities());
		checkUniqueNames(namespace.getTypedefs());
		checkUniqueNames(namespace.getVariables());
		
		for (AstTypeName typedef : namespace.getTypedefs()) {
			List<String> breadcrumb  = new ArrayList<String>();		
			checkTypeDeclaration(typedef, breadcrumb);
		}
	}

	public void checkTypeDeclaration(final AstTypeName typedef, final List<String> breadcrumb) {
		if (breadcrumb.contains(typedef.getName())) {
			String s = "";			
			for (String t : breadcrumb) {
				s += s + ":" + t;
			}
			error("Recursive type definitions are not supported. (" + s + ")",
					typedef,
					CalPackage.eINSTANCE.getAstTypeName_Name(), -1);
					return;
		} else {
			breadcrumb.add(typedef.getName());			
			if (typedef.getType() != null) {
				new VoidSwitch() {
					
					@Override
					public Void caseAstTypeName(AstTypeName td) {
						checkTypeDeclaration(td, new ArrayList<String>(breadcrumb));
						return null;
					}
				}.doSwitch(typedef.getType());
			} else {
				for (AstFunction tc : typedef.getConstructor()) {			
					for (AstVariable v : tc.getMembers()) {
						new VoidSwitch() {
							
							@Override
							public Void caseAstTypeName(AstTypeName td) {
								checkTypeDeclaration(td, new ArrayList<String>(breadcrumb));
								return null;
							}
						}.doSwitch(v.getType());
					}
				}
			}
		}		
	}
	
	@Check(CheckType.NORMAL)
	public void checkVariable(AstVariable variable) {
		checkIsVariableUsed(variable);		
		AstExpression value =  variable.getValue();
		if (value != null) {
			Type LHS = TypeConverter.convert(null, variable.getType(), true);
			Type RHS = TypeConverter.typeOf(null, value, true);
			try {
				TypeSystem.isCompatible(LHS, RHS);
			} catch (TypeException e) {
				error("Type mismatch: " + e.getMessage(),
						variable,
						CalPackage.eINSTANCE.getAstVariable_Name(), -1);
						return;
			}
		}				
	}
	
	@Check(CheckType.FAST)
	public void checkAstConnection(AstConnection connection) {
		List<AstPort> outputs, inputs;
		AstPort sourcePort  = null;
		AstPort sinkPort = null;
		
		// First check that the end points actually exists
		if (connection.getOutPort() != null) {
			if (connection.getFrom() == null)  {
				// The source is a internal network port			
				AstStructure structure = (AstStructure) connection.eContainer();
				AstNetwork network = (AstNetwork) structure.eContainer();
				outputs = network.getInputs();
			} else {
				AstActorVariable decl = ((AstActorVariable) connection.getFrom().getVariable());
				if (decl != null) {
					outputs = decl.getType().getActor().getOutputs();
				} else {
					error("Output missing", 
							connection, 
							CalPackage.eINSTANCE.getAstConnection_OutPort(), -1);	
					return;	
				}
			}
		
			
			for (AstPort port : outputs) {
				if (connection.getOutPort().equals(port.getName())) {
					sourcePort = port;
				}
			}
			if (sourcePort == null)
				error("Output port '" + connection.getOutPort() + "' not found", 
						connection, 
						CalPackage.eINSTANCE.getAstConnection_From(), -1);
		} else {
			error("Output port missing", 
					connection, 
					CalPackage.eINSTANCE.getAstConnection_OutPort(), -1);
		}
		
		if (connection.getInPort() != null) {
			if (connection.getTo() == null)  {
				AstStructure structure = (AstStructure) connection.eContainer();
				AstNetwork network = (AstNetwork) structure.eContainer();
				inputs = network.getOutputs();
			} else {				
				AstActorVariable decl = ((AstActorVariable) connection.getTo().getVariable());
				if (decl != null) {
					inputs = decl.getType().getActor().getInputs();
				} else {
					error("Input missing", 
							connection, 
							CalPackage.eINSTANCE.getAstConnection_InPort(), -1);	
					return;					
				}
			}
						
			for (AstPort port : inputs) {
				if (connection.getInPort().equals(port.getName())) {
					sinkPort = port;
				}
			}
		
			if (sinkPort == null)
				error("Input port '" + connection.getInPort() + "' not found", 
						connection, 
						CalPackage.eINSTANCE.getAstConnection_InPort(), -1);	
		} else {
			error("Input port missing", 
					connection, 
					CalPackage.eINSTANCE.getAstConnection_InPort(), -1);	
		}
		// Secondly, we need to make sure that the typing works
		Type sourceType = sourcePort != null ? TypeConverter.convert(null, sourcePort.getType(), true) : TypeSystem.createTypeUndef();
		Type sinkType   = sinkPort   != null ? TypeConverter.convert(null, sinkPort.getType(), true)   : TypeSystem.createTypeUndef();
		
		try {
			TypeSystem.isCompatible(sourceType, sinkType);
		} catch (TypeException e) {
				error("Type mismatch: cannot connect from '" + typePrinter.doSwitch(sourceType) + "' to '" 
						+ typePrinter.doSwitch(sinkType) + "'" + e.getMessage(),
						connection,
						CalPackage.eINSTANCE.getAstConnection_From(), -1);
		}
	}
	
	public void error(String message, EObject source, EStructuralFeature feature, int index) {
		super.error(message, source, feature, index);
	}
	
	private String getName(AstTag tag) {
		return OrccUtil.toString(tag.getIdentifiers(), ".");
	}

	private String getName(EObject object) {
		if (object instanceof AstEntity) {
			return ((AstEntity) object).getActor().getName();
		}
		return resolver.getValue(object);
	}

}
