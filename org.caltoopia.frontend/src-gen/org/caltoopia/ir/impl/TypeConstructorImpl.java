/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.caltoopia.ir.impl;

import java.util.Collection;

import org.caltoopia.ir.IrPackage;
import org.caltoopia.ir.TypeConstructor;
import org.caltoopia.ir.TypeDeclaration;
import org.caltoopia.ir.Variable;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Constructor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.ir.impl.TypeConstructorImpl#getTypedef <em>Typedef</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.TypeConstructorImpl#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TypeConstructorImpl extends DeclarationImpl implements TypeConstructor {
	/**
	 * The cached value of the '{@link #getTypedef() <em>Typedef</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypedef()
	 * @generated
	 * @ordered
	 */
	protected TypeDeclaration typedef;

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
	protected TypeConstructorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IrPackage.Literals.TYPE_CONSTRUCTOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeDeclaration getTypedef() {
		if (typedef != null && typedef.eIsProxy()) {
			InternalEObject oldTypedef = (InternalEObject)typedef;
			typedef = (TypeDeclaration)eResolveProxy(oldTypedef);
			if (typedef != oldTypedef) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.TYPE_CONSTRUCTOR__TYPEDEF, oldTypedef, typedef));
			}
		}
		return typedef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeDeclaration basicGetTypedef() {
		return typedef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypedef(TypeDeclaration newTypedef) {
		TypeDeclaration oldTypedef = typedef;
		typedef = newTypedef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.TYPE_CONSTRUCTOR__TYPEDEF, oldTypedef, typedef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Variable> getParameters() {
		if (parameters == null) {
			parameters = new EObjectResolvingEList<Variable>(Variable.class, this, IrPackage.TYPE_CONSTRUCTOR__PARAMETERS);
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
			case IrPackage.TYPE_CONSTRUCTOR__TYPEDEF:
				if (resolve) return getTypedef();
				return basicGetTypedef();
			case IrPackage.TYPE_CONSTRUCTOR__PARAMETERS:
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
			case IrPackage.TYPE_CONSTRUCTOR__TYPEDEF:
				setTypedef((TypeDeclaration)newValue);
				return;
			case IrPackage.TYPE_CONSTRUCTOR__PARAMETERS:
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
			case IrPackage.TYPE_CONSTRUCTOR__TYPEDEF:
				setTypedef((TypeDeclaration)null);
				return;
			case IrPackage.TYPE_CONSTRUCTOR__PARAMETERS:
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
			case IrPackage.TYPE_CONSTRUCTOR__TYPEDEF:
				return typedef != null;
			case IrPackage.TYPE_CONSTRUCTOR__PARAMETERS:
				return parameters != null && !parameters.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TypeConstructorImpl
