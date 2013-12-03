/**
 */
package org.caltoopia.ir.impl;

import org.caltoopia.ir.Expression;
import org.caltoopia.ir.IfExpression;
import org.caltoopia.ir.IrPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>If Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.ir.impl.IfExpressionImpl#getThenExpression <em>Then Expression</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.IfExpressionImpl#getElseExpression <em>Else Expression</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.IfExpressionImpl#getCondition <em>Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IfExpressionImpl extends ExpressionImpl implements IfExpression {
	/**
	 * The cached value of the '{@link #getThenExpression() <em>Then Expression</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThenExpression()
	 * @generated
	 * @ordered
	 */
	protected Expression thenExpression;

	/**
	 * The cached value of the '{@link #getElseExpression() <em>Else Expression</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElseExpression()
	 * @generated
	 * @ordered
	 */
	protected Expression elseExpression;

	/**
	 * The cached value of the '{@link #getCondition() <em>Condition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected Expression condition;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IfExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IrPackage.Literals.IF_EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getThenExpression() {
		if (thenExpression != null && thenExpression.eIsProxy()) {
			InternalEObject oldThenExpression = (InternalEObject)thenExpression;
			thenExpression = (Expression)eResolveProxy(oldThenExpression);
			if (thenExpression != oldThenExpression) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.IF_EXPRESSION__THEN_EXPRESSION, oldThenExpression, thenExpression));
			}
		}
		return thenExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression basicGetThenExpression() {
		return thenExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setThenExpression(Expression newThenExpression) {
		Expression oldThenExpression = thenExpression;
		thenExpression = newThenExpression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.IF_EXPRESSION__THEN_EXPRESSION, oldThenExpression, thenExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getElseExpression() {
		if (elseExpression != null && elseExpression.eIsProxy()) {
			InternalEObject oldElseExpression = (InternalEObject)elseExpression;
			elseExpression = (Expression)eResolveProxy(oldElseExpression);
			if (elseExpression != oldElseExpression) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.IF_EXPRESSION__ELSE_EXPRESSION, oldElseExpression, elseExpression));
			}
		}
		return elseExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression basicGetElseExpression() {
		return elseExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElseExpression(Expression newElseExpression) {
		Expression oldElseExpression = elseExpression;
		elseExpression = newElseExpression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.IF_EXPRESSION__ELSE_EXPRESSION, oldElseExpression, elseExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getCondition() {
		if (condition != null && condition.eIsProxy()) {
			InternalEObject oldCondition = (InternalEObject)condition;
			condition = (Expression)eResolveProxy(oldCondition);
			if (condition != oldCondition) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.IF_EXPRESSION__CONDITION, oldCondition, condition));
			}
		}
		return condition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression basicGetCondition() {
		return condition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCondition(Expression newCondition) {
		Expression oldCondition = condition;
		condition = newCondition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.IF_EXPRESSION__CONDITION, oldCondition, condition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IrPackage.IF_EXPRESSION__THEN_EXPRESSION:
				if (resolve) return getThenExpression();
				return basicGetThenExpression();
			case IrPackage.IF_EXPRESSION__ELSE_EXPRESSION:
				if (resolve) return getElseExpression();
				return basicGetElseExpression();
			case IrPackage.IF_EXPRESSION__CONDITION:
				if (resolve) return getCondition();
				return basicGetCondition();
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
			case IrPackage.IF_EXPRESSION__THEN_EXPRESSION:
				setThenExpression((Expression)newValue);
				return;
			case IrPackage.IF_EXPRESSION__ELSE_EXPRESSION:
				setElseExpression((Expression)newValue);
				return;
			case IrPackage.IF_EXPRESSION__CONDITION:
				setCondition((Expression)newValue);
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
			case IrPackage.IF_EXPRESSION__THEN_EXPRESSION:
				setThenExpression((Expression)null);
				return;
			case IrPackage.IF_EXPRESSION__ELSE_EXPRESSION:
				setElseExpression((Expression)null);
				return;
			case IrPackage.IF_EXPRESSION__CONDITION:
				setCondition((Expression)null);
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
			case IrPackage.IF_EXPRESSION__THEN_EXPRESSION:
				return thenExpression != null;
			case IrPackage.IF_EXPRESSION__ELSE_EXPRESSION:
				return elseExpression != null;
			case IrPackage.IF_EXPRESSION__CONDITION:
				return condition != null;
		}
		return super.eIsSet(featureID);
	}

} //IfExpressionImpl
