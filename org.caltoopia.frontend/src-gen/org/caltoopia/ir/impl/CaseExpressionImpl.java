/**
 */
package org.caltoopia.ir.impl;

import java.util.Collection;

import org.caltoopia.ir.CaseExpression;
import org.caltoopia.ir.ExprAlternative;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.IrPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Case Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.ir.impl.CaseExpressionImpl#getAlternatives <em>Alternatives</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.CaseExpressionImpl#getDefault <em>Default</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.CaseExpressionImpl#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CaseExpressionImpl extends ExpressionImpl implements CaseExpression {
	/**
     * The cached value of the '{@link #getAlternatives() <em>Alternatives</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAlternatives()
     * @generated
     * @ordered
     */
	protected EList<ExprAlternative> alternatives;

	/**
     * The cached value of the '{@link #getDefault() <em>Default</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDefault()
     * @generated
     * @ordered
     */
	protected Expression default_;

	/**
     * The cached value of the '{@link #getExpression() <em>Expression</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getExpression()
     * @generated
     * @ordered
     */
	protected Expression expression;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected CaseExpressionImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return IrPackage.Literals.CASE_EXPRESSION;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<ExprAlternative> getAlternatives() {
        if (alternatives == null) {
            alternatives = new EObjectResolvingEList<ExprAlternative>(ExprAlternative.class, this, IrPackage.CASE_EXPRESSION__ALTERNATIVES);
        }
        return alternatives;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Expression getDefault() {
        if (default_ != null && default_.eIsProxy()) {
            InternalEObject oldDefault = (InternalEObject)default_;
            default_ = (Expression)eResolveProxy(oldDefault);
            if (default_ != oldDefault) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.CASE_EXPRESSION__DEFAULT, oldDefault, default_));
            }
        }
        return default_;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Expression basicGetDefault() {
        return default_;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setDefault(Expression newDefault) {
        Expression oldDefault = default_;
        default_ = newDefault;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.CASE_EXPRESSION__DEFAULT, oldDefault, default_));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Expression getExpression() {
        if (expression != null && expression.eIsProxy()) {
            InternalEObject oldExpression = (InternalEObject)expression;
            expression = (Expression)eResolveProxy(oldExpression);
            if (expression != oldExpression) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.CASE_EXPRESSION__EXPRESSION, oldExpression, expression));
            }
        }
        return expression;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Expression basicGetExpression() {
        return expression;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setExpression(Expression newExpression) {
        Expression oldExpression = expression;
        expression = newExpression;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.CASE_EXPRESSION__EXPRESSION, oldExpression, expression));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case IrPackage.CASE_EXPRESSION__ALTERNATIVES:
                return getAlternatives();
            case IrPackage.CASE_EXPRESSION__DEFAULT:
                if (resolve) return getDefault();
                return basicGetDefault();
            case IrPackage.CASE_EXPRESSION__EXPRESSION:
                if (resolve) return getExpression();
                return basicGetExpression();
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
            case IrPackage.CASE_EXPRESSION__ALTERNATIVES:
                getAlternatives().clear();
                getAlternatives().addAll((Collection<? extends ExprAlternative>)newValue);
                return;
            case IrPackage.CASE_EXPRESSION__DEFAULT:
                setDefault((Expression)newValue);
                return;
            case IrPackage.CASE_EXPRESSION__EXPRESSION:
                setExpression((Expression)newValue);
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
            case IrPackage.CASE_EXPRESSION__ALTERNATIVES:
                getAlternatives().clear();
                return;
            case IrPackage.CASE_EXPRESSION__DEFAULT:
                setDefault((Expression)null);
                return;
            case IrPackage.CASE_EXPRESSION__EXPRESSION:
                setExpression((Expression)null);
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
            case IrPackage.CASE_EXPRESSION__ALTERNATIVES:
                return alternatives != null && !alternatives.isEmpty();
            case IrPackage.CASE_EXPRESSION__DEFAULT:
                return default_ != null;
            case IrPackage.CASE_EXPRESSION__EXPRESSION:
                return expression != null;
        }
        return super.eIsSet(featureID);
    }

} //CaseExpressionImpl
