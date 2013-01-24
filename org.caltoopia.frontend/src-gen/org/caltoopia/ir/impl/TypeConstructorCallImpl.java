/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.caltoopia.ir.impl;

import java.util.Collection;

import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.IrPackage;
import org.caltoopia.ir.TypeConstructorCall;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Constructor Call</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.ir.impl.TypeConstructorCallImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.TypeConstructorCallImpl#getTypedef <em>Typedef</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.TypeConstructorCallImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TypeConstructorCallImpl extends ExpressionCallImpl implements TypeConstructorCall {
	/**
	 * The cached value of the '{@link #getParameters() <em>Parameters</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> parameters;

	/**
	 * The cached value of the '{@link #getTypedef() <em>Typedef</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypedef()
	 * @generated
	 * @ordered
	 */
	protected Declaration typedef;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypeConstructorCallImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IrPackage.Literals.TYPE_CONSTRUCTOR_CALL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Expression> getParameters() {
		if (parameters == null) {
			parameters = new EObjectResolvingEList<Expression>(Expression.class, this, IrPackage.TYPE_CONSTRUCTOR_CALL__PARAMETERS);
		}
		return parameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Declaration getTypedef() {
		if (typedef != null && typedef.eIsProxy()) {
			InternalEObject oldTypedef = (InternalEObject)typedef;
			typedef = (Declaration)eResolveProxy(oldTypedef);
			if (typedef != oldTypedef) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.TYPE_CONSTRUCTOR_CALL__TYPEDEF, oldTypedef, typedef));
			}
		}
		return typedef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Declaration basicGetTypedef() {
		return typedef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypedef(Declaration newTypedef) {
		Declaration oldTypedef = typedef;
		typedef = newTypedef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.TYPE_CONSTRUCTOR_CALL__TYPEDEF, oldTypedef, typedef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.TYPE_CONSTRUCTOR_CALL__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IrPackage.TYPE_CONSTRUCTOR_CALL__PARAMETERS:
				return getParameters();
			case IrPackage.TYPE_CONSTRUCTOR_CALL__TYPEDEF:
				if (resolve) return getTypedef();
				return basicGetTypedef();
			case IrPackage.TYPE_CONSTRUCTOR_CALL__NAME:
				return getName();
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
			case IrPackage.TYPE_CONSTRUCTOR_CALL__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection<? extends Expression>)newValue);
				return;
			case IrPackage.TYPE_CONSTRUCTOR_CALL__TYPEDEF:
				setTypedef((Declaration)newValue);
				return;
			case IrPackage.TYPE_CONSTRUCTOR_CALL__NAME:
				setName((String)newValue);
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
			case IrPackage.TYPE_CONSTRUCTOR_CALL__PARAMETERS:
				getParameters().clear();
				return;
			case IrPackage.TYPE_CONSTRUCTOR_CALL__TYPEDEF:
				setTypedef((Declaration)null);
				return;
			case IrPackage.TYPE_CONSTRUCTOR_CALL__NAME:
				setName(NAME_EDEFAULT);
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
			case IrPackage.TYPE_CONSTRUCTOR_CALL__PARAMETERS:
				return parameters != null && !parameters.isEmpty();
			case IrPackage.TYPE_CONSTRUCTOR_CALL__TYPEDEF:
				return typedef != null;
			case IrPackage.TYPE_CONSTRUCTOR_CALL__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //TypeConstructorCallImpl
