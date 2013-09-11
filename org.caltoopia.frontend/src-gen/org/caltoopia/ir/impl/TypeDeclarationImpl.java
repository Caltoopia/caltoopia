/**
 */
package org.caltoopia.ir.impl;

import java.util.Collection;

import org.caltoopia.ir.IrPackage;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeDeclaration;
import org.caltoopia.ir.TypeVariable;
import org.caltoopia.ir.Variable;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.ir.impl.TypeDeclarationImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.TypeDeclarationImpl#getValueParameters <em>Value Parameters</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.TypeDeclarationImpl#getTypeParameters <em>Type Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TypeDeclarationImpl extends DeclarationImpl implements TypeDeclaration {
	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected Type type;

	/**
	 * The cached value of the '{@link #getValueParameters() <em>Value Parameters</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<Variable> valueParameters;

	/**
	 * The cached value of the '{@link #getTypeParameters() <em>Type Parameters</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeParameters()
	 * @generated
	 * @ordered
	 */
	protected TypeVariable typeParameters;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypeDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IrPackage.Literals.TYPE_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type getType() {
		if (type != null && type.eIsProxy()) {
			InternalEObject oldType = (InternalEObject)type;
			type = (Type)eResolveProxy(oldType);
			if (type != oldType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.TYPE_DECLARATION__TYPE, oldType, type));
			}
		}
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(Type newType) {
		Type oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.TYPE_DECLARATION__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Variable> getValueParameters() {
		if (valueParameters == null) {
			valueParameters = new EObjectResolvingEList<Variable>(Variable.class, this, IrPackage.TYPE_DECLARATION__VALUE_PARAMETERS);
		}
		return valueParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeVariable getTypeParameters() {
		if (typeParameters != null && typeParameters.eIsProxy()) {
			InternalEObject oldTypeParameters = (InternalEObject)typeParameters;
			typeParameters = (TypeVariable)eResolveProxy(oldTypeParameters);
			if (typeParameters != oldTypeParameters) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.TYPE_DECLARATION__TYPE_PARAMETERS, oldTypeParameters, typeParameters));
			}
		}
		return typeParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeVariable basicGetTypeParameters() {
		return typeParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeParameters(TypeVariable newTypeParameters) {
		TypeVariable oldTypeParameters = typeParameters;
		typeParameters = newTypeParameters;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.TYPE_DECLARATION__TYPE_PARAMETERS, oldTypeParameters, typeParameters));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IrPackage.TYPE_DECLARATION__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case IrPackage.TYPE_DECLARATION__VALUE_PARAMETERS:
				return getValueParameters();
			case IrPackage.TYPE_DECLARATION__TYPE_PARAMETERS:
				if (resolve) return getTypeParameters();
				return basicGetTypeParameters();
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
			case IrPackage.TYPE_DECLARATION__TYPE:
				setType((Type)newValue);
				return;
			case IrPackage.TYPE_DECLARATION__VALUE_PARAMETERS:
				getValueParameters().clear();
				getValueParameters().addAll((Collection<? extends Variable>)newValue);
				return;
			case IrPackage.TYPE_DECLARATION__TYPE_PARAMETERS:
				setTypeParameters((TypeVariable)newValue);
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
			case IrPackage.TYPE_DECLARATION__TYPE:
				setType((Type)null);
				return;
			case IrPackage.TYPE_DECLARATION__VALUE_PARAMETERS:
				getValueParameters().clear();
				return;
			case IrPackage.TYPE_DECLARATION__TYPE_PARAMETERS:
				setTypeParameters((TypeVariable)null);
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
			case IrPackage.TYPE_DECLARATION__TYPE:
				return type != null;
			case IrPackage.TYPE_DECLARATION__VALUE_PARAMETERS:
				return valueParameters != null && !valueParameters.isEmpty();
			case IrPackage.TYPE_DECLARATION__TYPE_PARAMETERS:
				return typeParameters != null;
		}
		return super.eIsSet(featureID);
	}

} //TypeDeclarationImpl
