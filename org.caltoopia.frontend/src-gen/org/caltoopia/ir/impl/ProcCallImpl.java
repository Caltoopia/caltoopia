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
import org.caltoopia.ir.ProcCall;
import org.caltoopia.ir.VariableReference;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Proc Call</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.ir.impl.ProcCallImpl#getInParameters <em>In Parameters</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.ProcCallImpl#getOutParameters <em>Out Parameters</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.ProcCallImpl#getProcedure <em>Procedure</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProcCallImpl extends StatementImpl implements ProcCall {
	/**
	 * The cached value of the '{@link #getInParameters() <em>In Parameters</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> inParameters;

	/**
	 * The cached value of the '{@link #getOutParameters() <em>Out Parameters</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<VariableReference> outParameters;

	/**
	 * The cached value of the '{@link #getProcedure() <em>Procedure</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcedure()
	 * @generated
	 * @ordered
	 */
	protected Declaration procedure;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProcCallImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IrPackage.Literals.PROC_CALL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Expression> getInParameters() {
		if (inParameters == null) {
			inParameters = new EObjectResolvingEList<Expression>(Expression.class, this, IrPackage.PROC_CALL__IN_PARAMETERS);
		}
		return inParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<VariableReference> getOutParameters() {
		if (outParameters == null) {
			outParameters = new EObjectResolvingEList<VariableReference>(VariableReference.class, this, IrPackage.PROC_CALL__OUT_PARAMETERS);
		}
		return outParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Declaration getProcedure() {
		if (procedure != null && procedure.eIsProxy()) {
			InternalEObject oldProcedure = (InternalEObject)procedure;
			procedure = (Declaration)eResolveProxy(oldProcedure);
			if (procedure != oldProcedure) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.PROC_CALL__PROCEDURE, oldProcedure, procedure));
			}
		}
		return procedure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Declaration basicGetProcedure() {
		return procedure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProcedure(Declaration newProcedure) {
		Declaration oldProcedure = procedure;
		procedure = newProcedure;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.PROC_CALL__PROCEDURE, oldProcedure, procedure));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IrPackage.PROC_CALL__IN_PARAMETERS:
				return getInParameters();
			case IrPackage.PROC_CALL__OUT_PARAMETERS:
				return getOutParameters();
			case IrPackage.PROC_CALL__PROCEDURE:
				if (resolve) return getProcedure();
				return basicGetProcedure();
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
			case IrPackage.PROC_CALL__IN_PARAMETERS:
				getInParameters().clear();
				getInParameters().addAll((Collection<? extends Expression>)newValue);
				return;
			case IrPackage.PROC_CALL__OUT_PARAMETERS:
				getOutParameters().clear();
				getOutParameters().addAll((Collection<? extends VariableReference>)newValue);
				return;
			case IrPackage.PROC_CALL__PROCEDURE:
				setProcedure((Declaration)newValue);
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
			case IrPackage.PROC_CALL__IN_PARAMETERS:
				getInParameters().clear();
				return;
			case IrPackage.PROC_CALL__OUT_PARAMETERS:
				getOutParameters().clear();
				return;
			case IrPackage.PROC_CALL__PROCEDURE:
				setProcedure((Declaration)null);
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
			case IrPackage.PROC_CALL__IN_PARAMETERS:
				return inParameters != null && !inParameters.isEmpty();
			case IrPackage.PROC_CALL__OUT_PARAMETERS:
				return outParameters != null && !outParameters.isEmpty();
			case IrPackage.PROC_CALL__PROCEDURE:
				return procedure != null;
		}
		return super.eIsSet(featureID);
	}

} //ProcCallImpl
