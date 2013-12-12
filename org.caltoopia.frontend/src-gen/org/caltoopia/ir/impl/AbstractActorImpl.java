/**
 */
package org.caltoopia.ir.impl;

import java.util.Collection;

import org.caltoopia.ir.AbstractActor;
import org.caltoopia.ir.IrPackage;
import org.caltoopia.ir.Port;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.Variable;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Actor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.ir.impl.AbstractActorImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.AbstractActorImpl#getInputPorts <em>Input Ports</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.AbstractActorImpl#getOutputPorts <em>Output Ports</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.AbstractActorImpl#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AbstractActorImpl extends ScopeImpl implements AbstractActor {
	/**
     * The cached value of the '{@link #getType() <em>Type</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
	protected TypeActor type;

	/**
     * The cached value of the '{@link #getInputPorts() <em>Input Ports</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getInputPorts()
     * @generated
     * @ordered
     */
	protected EList<Port> inputPorts;

	/**
     * The cached value of the '{@link #getOutputPorts() <em>Output Ports</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getOutputPorts()
     * @generated
     * @ordered
     */
	protected EList<Port> outputPorts;

	/**
     * The cached value of the '{@link #getParameters() <em>Parameters</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getParameters()
     * @generated
     * @ordered
     */
	protected EList<Variable> parameters;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected AbstractActorImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return IrPackage.Literals.ABSTRACT_ACTOR;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public TypeActor getType() {
        if (type != null && type.eIsProxy()) {
            InternalEObject oldType = (InternalEObject)type;
            type = (TypeActor)eResolveProxy(oldType);
            if (type != oldType) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.ABSTRACT_ACTOR__TYPE, oldType, type));
            }
        }
        return type;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public TypeActor basicGetType() {
        return type;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setType(TypeActor newType) {
        TypeActor oldType = type;
        type = newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.ABSTRACT_ACTOR__TYPE, oldType, type));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<Port> getInputPorts() {
        if (inputPorts == null) {
            inputPorts = new EObjectResolvingEList<Port>(Port.class, this, IrPackage.ABSTRACT_ACTOR__INPUT_PORTS);
        }
        return inputPorts;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<Port> getOutputPorts() {
        if (outputPorts == null) {
            outputPorts = new EObjectResolvingEList<Port>(Port.class, this, IrPackage.ABSTRACT_ACTOR__OUTPUT_PORTS);
        }
        return outputPorts;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<Variable> getParameters() {
        if (parameters == null) {
            parameters = new EObjectResolvingEList<Variable>(Variable.class, this, IrPackage.ABSTRACT_ACTOR__PARAMETERS);
        }
        return parameters;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case IrPackage.ABSTRACT_ACTOR__TYPE:
                if (resolve) return getType();
                return basicGetType();
            case IrPackage.ABSTRACT_ACTOR__INPUT_PORTS:
                return getInputPorts();
            case IrPackage.ABSTRACT_ACTOR__OUTPUT_PORTS:
                return getOutputPorts();
            case IrPackage.ABSTRACT_ACTOR__PARAMETERS:
                return getParameters();
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
            case IrPackage.ABSTRACT_ACTOR__TYPE:
                setType((TypeActor)newValue);
                return;
            case IrPackage.ABSTRACT_ACTOR__INPUT_PORTS:
                getInputPorts().clear();
                getInputPorts().addAll((Collection<? extends Port>)newValue);
                return;
            case IrPackage.ABSTRACT_ACTOR__OUTPUT_PORTS:
                getOutputPorts().clear();
                getOutputPorts().addAll((Collection<? extends Port>)newValue);
                return;
            case IrPackage.ABSTRACT_ACTOR__PARAMETERS:
                getParameters().clear();
                getParameters().addAll((Collection<? extends Variable>)newValue);
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
            case IrPackage.ABSTRACT_ACTOR__TYPE:
                setType((TypeActor)null);
                return;
            case IrPackage.ABSTRACT_ACTOR__INPUT_PORTS:
                getInputPorts().clear();
                return;
            case IrPackage.ABSTRACT_ACTOR__OUTPUT_PORTS:
                getOutputPorts().clear();
                return;
            case IrPackage.ABSTRACT_ACTOR__PARAMETERS:
                getParameters().clear();
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
            case IrPackage.ABSTRACT_ACTOR__TYPE:
                return type != null;
            case IrPackage.ABSTRACT_ACTOR__INPUT_PORTS:
                return inputPorts != null && !inputPorts.isEmpty();
            case IrPackage.ABSTRACT_ACTOR__OUTPUT_PORTS:
                return outputPorts != null && !outputPorts.isEmpty();
            case IrPackage.ABSTRACT_ACTOR__PARAMETERS:
                return parameters != null && !parameters.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //AbstractActorImpl
