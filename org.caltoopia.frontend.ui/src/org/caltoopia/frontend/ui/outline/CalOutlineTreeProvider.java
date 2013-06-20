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

package org.caltoopia.frontend.ui.outline;

import org.caltoopia.frontend.cal.AstAbstractActor;
import org.caltoopia.frontend.cal.AstAction;
import org.caltoopia.frontend.cal.AstActor;
import org.caltoopia.frontend.cal.AstEntity;
import org.caltoopia.frontend.cal.AstExternalActor;
import org.caltoopia.frontend.cal.AstInequality;
import org.caltoopia.frontend.cal.AstNamespace;
import org.caltoopia.frontend.cal.AstNetwork;
import org.caltoopia.frontend.cal.AstPort;
import org.caltoopia.frontend.cal.AstPriority;
import org.caltoopia.frontend.cal.AstProcedure;
import org.caltoopia.frontend.cal.AstSchedule;
import org.caltoopia.frontend.cal.AstTransition;
import org.caltoopia.frontend.cal.CalPackage;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.ui.IImageHelper;
import org.eclipse.xtext.ui.codetemplates.templates.Variable;
import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtext.ui.editor.outline.impl.DefaultOutlineTreeProvider;

import com.google.common.base.Function;
import com.google.inject.Inject;

/**
 * customization of the default outline structure
 * 
 */
public class CalOutlineTreeProvider extends DefaultOutlineTreeProvider {

	@Inject
	private IImageHelper imageHelper;

	protected void _createNode(IOutlineNode parentNode, AstNamespace namespace) {
		
		if (!namespace.getImports().isEmpty()) {
			createEStructuralFeatureNode(parentNode, namespace,
					CalPackage.eINSTANCE.getAstNamespace_Imports(), null,
					"imports", false);
		}
		
		if (!namespace.getNamespaces().isEmpty()) {
			createEStructuralFeatureNode(parentNode, namespace,
					CalPackage.eINSTANCE.getAstNamespace_Namespaces(), null,
					"namespaces", false);
		}

		if (!namespace.getEntities().isEmpty()) {
			for (AstEntity entity : namespace.getEntities()) {
				createEStructuralFeatureNode(parentNode, entity,
						CalPackage.eINSTANCE.getAstEntity_Actor(), null,
						entity.getActor().getName(), false);
			}
		}
		
		if (!namespace.getFunctions().isEmpty()) {
			createEStructuralFeatureNode(parentNode, namespace,
					CalPackage.eINSTANCE.getAstNamespace_Functions(), null,
					"functions", false);
		}

		if (!namespace.getTypedefs().isEmpty()) {
			createEStructuralFeatureNode(parentNode, namespace,
					CalPackage.eINSTANCE.getAstNamespace_Typedefs(), null,
					"types", false);
		}
		
		if (!namespace.getVariables().isEmpty()) {
			createEStructuralFeatureNode(parentNode, namespace,
					CalPackage.eINSTANCE.getAstNamespace_Variables(), null,
					"constants", false);
		}

		if (!namespace.getExternals().isEmpty()) {
			createEStructuralFeatureNode(parentNode, namespace,
					CalPackage.eINSTANCE.getAstNamespace_Externals(), null,
					"external declarations", false);
		}		
	
	}
	
	protected void _createNode(IOutlineNode parentNode, AstActor actor) {
		if (!actor.getParameters().isEmpty()) {
			createEStructuralFeatureNode(parentNode, actor,
					CalPackage.eINSTANCE.getAstAbstractActor_Parameters(), null,
					"parameters", false);
		}

		if (!actor.getInputs().isEmpty()) {
			createEStructuralFeatureNode(parentNode, actor,
					CalPackage.eINSTANCE.getAstAbstractActor_Inputs(), null, "input ports",
					false);
		}

		if (!actor.getOutputs().isEmpty()) {
			createEStructuralFeatureNode(parentNode, actor,
					CalPackage.eINSTANCE.getAstAbstractActor_Outputs(), null,
					"output ports", false);
		}

		if (!actor.getStateVariables().isEmpty()) {
			createEStructuralFeatureNode(parentNode, actor,
					CalPackage.eINSTANCE.getAstActor_StateVariables(), null,
					"state variables", false);
		}

		if (!actor.getFunctions().isEmpty()) {
			createEStructuralFeatureNode(parentNode, actor,
					CalPackage.eINSTANCE.getAstActor_Functions(), null,
					"functions", false);
		}

		if (!actor.getProcedures().isEmpty()) {
			createEStructuralFeatureNode(parentNode, actor,
					CalPackage.eINSTANCE.getAstActor_Procedures(), null,
					"procedures", false);
		}

		if (!actor.getActions().isEmpty()) {
			createEStructuralFeatureNode(parentNode, actor,
					CalPackage.eINSTANCE.getAstActor_Actions(), null, "actions",
					false);
		}

		if (actor.getSchedules().isEmpty()) {
			createEStructuralFeatureNode(parentNode, actor,
					CalPackage.eINSTANCE.getAstActor_Schedules(), null,
					"schedule", false);
		}

		if (!actor.getPriorities().isEmpty()) {
			createEStructuralFeatureNode(parentNode, actor,
					CalPackage.eINSTANCE.getAstActor_Priorities(), null,
					"priorities", false);
		}
	}

	protected void _createNode(IOutlineNode parentNode, AstEntity entity) {	

		AstAbstractActor actor = entity.getActor();
		String kind = "";
		Image image = null;
		if (actor instanceof AstActor) {
			image = _image(actor);
			kind = " [actor]";
		} else if (actor instanceof AstNetwork) {
			kind = " [network]";
		} else if (actor instanceof AstExternalActor) {
			kind = " [external]";
		}
		createEStructuralFeatureNode(parentNode, entity,
				CalPackage.eINSTANCE.getAstEntity_Actor(),
				image, actor.getName() + kind, false);
	}

	protected void _createNode(IOutlineNode parentNode, AstNetwork network) {
		if (!network.getVariables().isEmpty()) {
			createEStructuralFeatureNode(parentNode, network,
					CalPackage.eINSTANCE.getAstNetwork_Variables(), null, "variables",
					false);
		}

		if (!network.getInstances().isEmpty()) {
			createEStructuralFeatureNode(parentNode, network,
					CalPackage.eINSTANCE.getAstNetwork_Instances(), null,
					"actor instances", false);
		}

		if (network.getStructure() != null && !network.getStructure().getConnections().isEmpty()) {
			createEStructuralFeatureNode(parentNode, network,
					CalPackage.eINSTANCE.getAstNetwork_Structure(), null,
					"connections", false);
		}
	}

	protected void _createNode(IOutlineNode parentNode, AstPriority priority) {
		for (AstInequality inequality : priority.getInequalities()) {
			createNode(parentNode, inequality);
		}
	}

	protected void _createNode(IOutlineNode parentNode, AstSchedule schedule) {
		for (AstTransition transition : schedule.getTransitions()) {
			createNode(parentNode, transition);
		}
	}

	protected boolean _isLeaf(AstAction action) {
		return true;
	}

	protected boolean _isLeaf(AstPort port) {
		return true;
	}

	protected boolean _isLeaf(AstProcedure procedure) {
		return true;
	}

	protected boolean _isLeaf(AstTransition transition) {
		return true;
	}

	protected boolean _isLeaf(Function function) {
		return true;
	}

	protected boolean _isLeaf(Variable variable) {
		return true;
	}

}
