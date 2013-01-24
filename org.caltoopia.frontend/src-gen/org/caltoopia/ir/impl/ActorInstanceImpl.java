/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.caltoopia.ir.impl;

import java.util.Collection;

import org.caltoopia.ir.ActorInstance;
import org.caltoopia.ir.IrPackage;
import org.caltoopia.ir.PortInstance;
import org.caltoopia.ir.TaggedExpression;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Actor Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.ir.impl.ActorInstanceImpl#getInputs <em>Inputs</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.ActorInstanceImpl#getOutputs <em>Outputs</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.ActorInstanceImpl#getActualParameters <em>Actual Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActorInstanceImpl extends VariableImpl implements ActorInstance {
	/**
	 * The cached value of the '{@link #getInputs() <em>Inputs</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputs()
	 * @generated
	 * @ordered
	 */
	protected EList<PortInstance> inputs;

	/**
	 * The cached value of the '{@link #getOutputs() <em>Outputs</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputs()
	 * @generated
	 * @ordered
	 */
	protected EList<PortInstance> outputs;

	/**
	 * The cached value of the '{@link #getActualParameters() <em>Actual Parameters</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<TaggedExpression> actualParameters;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActorInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IrPackage.Literals.ACTOR_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PortInstance> getInputs() {
		if (inputs == null) {
			inputs = new EObjectResolvingEList<PortInstance>(PortInstance.class, this, IrPackage.ACTOR_INSTANCE__INPUTS);
		}
		return inputs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PortInstance> getOutputs() {
		if (outputs == null) {
			outputs = new EObjectResolvingEList<PortInstance>(PortInstance.class, this, IrPackage.ACTOR_INSTANCE__OUTPUTS);
		}
		return outputs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TaggedExpression> getActualParameters() {
		if (actualParameters == null) {
			actualParameters = new EObjectResolvingEList<TaggedExpression>(TaggedExpression.class, this, IrPackage.ACTOR_INSTANCE__ACTUAL_PARAMETERS);
		}
		return actualParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IrPackage.ACTOR_INSTANCE__INPUTS:
				return getInputs();
			case IrPackage.ACTOR_INSTANCE__OUTPUTS:
				return getOutputs();
			case IrPackage.ACTOR_INSTANCE__ACTUAL_PARAMETERS:
				return getActualParameters();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case IrPackage.ACTOR_INSTANCE__INPUTS:
				getInputs().clear();
				getInputs().addAll((Collection<? extends PortInstance>)newValue);
				return;
			case IrPackage.ACTOR_INSTANCE__OUTPUTS:
				getOutputs().clear();
				getOutputs().addAll((Collection<? extends PortInstance>)newValue);
				return;
			case IrPackage.ACTOR_INSTANCE__ACTUAL_PARAMETERS:
				getActualParameters().clear();
				getActualParameters().addAll((Collection<? extends TaggedExpression>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case IrPackage.ACTOR_INSTANCE__INPUTS:
				getInputs().clear();
				return;
			case IrPackage.ACTOR_INSTANCE__OUTPUTS:
				getOutputs().clear();
				return;
			case IrPackage.ACTOR_INSTANCE__ACTUAL_PARAMETERS:
				getActualParameters().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case IrPackage.ACTOR_INSTANCE__INPUTS:
				return inputs != null && !inputs.isEmpty();
			case IrPackage.ACTOR_INSTANCE__OUTPUTS:
				return outputs != null && !outputs.isEmpty();
			case IrPackage.ACTOR_INSTANCE__ACTUAL_PARAMETERS:
				return actualParameters != null && !actualParameters.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ActorInstanceImpl
