/**
 */
package org.caltoopia.ir.impl;

import org.caltoopia.ir.ForwardTypeDeclaration;
import org.caltoopia.ir.IrPackage;

import org.caltoopia.ir.TypeDeclaration;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Forward Type Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.ir.impl.ForwardTypeDeclarationImpl#getDeclaration <em>Declaration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ForwardTypeDeclarationImpl extends DeclarationImpl implements ForwardTypeDeclaration {
	/**
	 * The cached value of the '{@link #getDeclaration() <em>Declaration</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeclaration()
	 * @generated
	 * @ordered
	 */
	protected TypeDeclaration declaration;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ForwardTypeDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IrPackage.Literals.FORWARD_TYPE_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeDeclaration getDeclaration() {
		if (declaration != null && declaration.eIsProxy()) {
			InternalEObject oldDeclaration = (InternalEObject)declaration;
			declaration = (TypeDeclaration)eResolveProxy(oldDeclaration);
			if (declaration != oldDeclaration) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.FORWARD_TYPE_DECLARATION__DECLARATION, oldDeclaration, declaration));
			}
		}
		return declaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeDeclaration basicGetDeclaration() {
		return declaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeclaration(TypeDeclaration newDeclaration) {
		TypeDeclaration oldDeclaration = declaration;
		declaration = newDeclaration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.FORWARD_TYPE_DECLARATION__DECLARATION, oldDeclaration, declaration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IrPackage.FORWARD_TYPE_DECLARATION__DECLARATION:
				if (resolve) return getDeclaration();
				return basicGetDeclaration();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case IrPackage.FORWARD_TYPE_DECLARATION__DECLARATION:
				setDeclaration((TypeDeclaration)newValue);
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
			case IrPackage.FORWARD_TYPE_DECLARATION__DECLARATION:
				setDeclaration((TypeDeclaration)null);
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
			case IrPackage.FORWARD_TYPE_DECLARATION__DECLARATION:
				return declaration != null;
		}
		return super.eIsSet(featureID);
	}

} //ForwardTypeDeclarationImpl
