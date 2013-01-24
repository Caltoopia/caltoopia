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
import org.caltoopia.ir.TypeProc;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Proc</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.ir.impl.TypeProcImpl#getInputTypes <em>Input Types</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.TypeProcImpl#getOutputTypes <em>Output Types</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TypeProcImpl extends TypeImpl implements TypeProc {
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
	 * The cached value of the '{@link #getOutputTypes() <em>Output Types</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<Type> outputTypes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypeProcImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IrPackage.Literals.TYPE_PROC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Type> getInputTypes() {
		if (inputTypes == null) {
			inputTypes = new EObjectResolvingEList<Type>(Type.class, this, IrPackage.TYPE_PROC__INPUT_TYPES);
		}
		return inputTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Type> getOutputTypes() {
		if (outputTypes == null) {
			outputTypes = new EObjectResolvingEList<Type>(Type.class, this, IrPackage.TYPE_PROC__OUTPUT_TYPES);
		}
		return outputTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IrPackage.TYPE_PROC__INPUT_TYPES:
				return getInputTypes();
			case IrPackage.TYPE_PROC__OUTPUT_TYPES:
				return getOutputTypes();
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
			case IrPackage.TYPE_PROC__INPUT_TYPES:
				getInputTypes().clear();
				getInputTypes().addAll((Collection<? extends Type>)newValue);
				return;
			case IrPackage.TYPE_PROC__OUTPUT_TYPES:
				getOutputTypes().clear();
				getOutputTypes().addAll((Collection<? extends Type>)newValue);
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
			case IrPackage.TYPE_PROC__INPUT_TYPES:
				getInputTypes().clear();
				return;
			case IrPackage.TYPE_PROC__OUTPUT_TYPES:
				getOutputTypes().clear();
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
			case IrPackage.TYPE_PROC__INPUT_TYPES:
				return inputTypes != null && !inputTypes.isEmpty();
			case IrPackage.TYPE_PROC__OUTPUT_TYPES:
				return outputTypes != null && !outputTypes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TypeProcImpl
