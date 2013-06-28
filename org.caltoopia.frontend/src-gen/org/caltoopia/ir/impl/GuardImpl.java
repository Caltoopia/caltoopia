/**
 */
package org.caltoopia.ir.impl;

import java.util.Collection;

import org.caltoopia.ir.Guard;
import org.caltoopia.ir.IrPackage;
import org.caltoopia.ir.PortPeek;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Guard</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.ir.impl.GuardImpl#getPeeks <em>Peeks</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GuardImpl extends LambdaExpressionImpl implements Guard {
	/**
	 * The cached value of the '{@link #getPeeks() <em>Peeks</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPeeks()
	 * @generated
	 * @ordered
	 */
	protected EList<PortPeek> peeks;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GuardImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IrPackage.Literals.GUARD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PortPeek> getPeeks() {
		if (peeks == null) {
			peeks = new EObjectResolvingEList<PortPeek>(PortPeek.class, this, IrPackage.GUARD__PEEKS);
		}
		return peeks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IrPackage.GUARD__PEEKS:
				return getPeeks();
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
			case IrPackage.GUARD__PEEKS:
				getPeeks().clear();
				getPeeks().addAll((Collection<? extends PortPeek>)newValue);
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
			case IrPackage.GUARD__PEEKS:
				getPeeks().clear();
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
			case IrPackage.GUARD__PEEKS:
				return peeks != null && !peeks.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //GuardImpl
