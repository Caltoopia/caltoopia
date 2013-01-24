/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.caltoopia.ir.impl;

import java.util.Collection;

import org.caltoopia.ir.IrPackage;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeLambda;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Lambda</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.ir.impl.TypeLambdaImpl#getInputTypes <em>Input Types</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.TypeLambdaImpl#getOutputType <em>Output Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TypeLambdaImpl extends TypeImpl implements TypeLambda {
	/**
	 * The cached value of the '{@link #getInputTypes() <em>Input Types</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<Type> inputTypes;

	/**
	 * The cached value of the '{@link #getOutputType() <em>Output Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputType()
	 * @generated
	 * @ordered
	 */
	protected Type outputType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypeLambdaImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IrPackage.Literals.TYPE_LAMBDA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Type> getInputTypes() {
		if (inputTypes == null) {
			inputTypes = new EObjectResolvingEList<Type>(Type.class, this, IrPackage.TYPE_LAMBDA__INPUT_TYPES);
		}
		return inputTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getOutputType() {
		if (outputType != null && outputType.eIsProxy()) {
			InternalEObject oldOutputType = (InternalEObject)outputType;
			outputType = (Type)eResolveProxy(oldOutputType);
			if (outputType != oldOutputType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.TYPE_LAMBDA__OUTPUT_TYPE, oldOutputType, outputType));
			}
		}
		return outputType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetOutputType() {
		return outputType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutputType(Type newOutputType) {
		Type oldOutputType = outputType;
		outputType = newOutputType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.TYPE_LAMBDA__OUTPUT_TYPE, oldOutputType, outputType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IrPackage.TYPE_LAMBDA__INPUT_TYPES:
				return getInputTypes();
			case IrPackage.TYPE_LAMBDA__OUTPUT_TYPE:
				if (resolve) return getOutputType();
				return basicGetOutputType();
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
			case IrPackage.TYPE_LAMBDA__INPUT_TYPES:
				getInputTypes().clear();
				getInputTypes().addAll((Collection<? extends Type>)newValue);
				return;
			case IrPackage.TYPE_LAMBDA__OUTPUT_TYPE:
				setOutputType((Type)newValue);
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
			case IrPackage.TYPE_LAMBDA__INPUT_TYPES:
				getInputTypes().clear();
				return;
			case IrPackage.TYPE_LAMBDA__OUTPUT_TYPE:
				setOutputType((Type)null);
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
			case IrPackage.TYPE_LAMBDA__INPUT_TYPES:
				return inputTypes != null && !inputTypes.isEmpty();
			case IrPackage.TYPE_LAMBDA__OUTPUT_TYPE:
				return outputType != null;
		}
		return super.eIsSet(featureID);
	}

} //TypeLambdaImpl
