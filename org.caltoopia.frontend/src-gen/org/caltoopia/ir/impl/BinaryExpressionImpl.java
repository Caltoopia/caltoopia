/**
 */
package org.caltoopia.ir.impl;

import org.caltoopia.ir.BinaryExpression;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.IrPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Binary Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.ir.impl.BinaryExpressionImpl#getOperand1 <em>Operand1</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.BinaryExpressionImpl#getOperator <em>Operator</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.BinaryExpressionImpl#getOperand2 <em>Operand2</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BinaryExpressionImpl extends ExpressionImpl implements BinaryExpression {
	/**
     * The cached value of the '{@link #getOperand1() <em>Operand1</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getOperand1()
     * @generated
     * @ordered
     */
	protected Expression operand1;

	/**
     * The default value of the '{@link #getOperator() <em>Operator</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getOperator()
     * @generated
     * @ordered
     */
	protected static final String OPERATOR_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getOperator() <em>Operator</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getOperator()
     * @generated
     * @ordered
     */
	protected String operator = OPERATOR_EDEFAULT;

	/**
     * The cached value of the '{@link #getOperand2() <em>Operand2</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getOperand2()
     * @generated
     * @ordered
     */
	protected Expression operand2;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected BinaryExpressionImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return IrPackage.Literals.BINARY_EXPRESSION;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Expression getOperand1() {
        if (operand1 != null && operand1.eIsProxy()) {
            InternalEObject oldOperand1 = (InternalEObject)operand1;
            operand1 = (Expression)eResolveProxy(oldOperand1);
            if (operand1 != oldOperand1) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.BINARY_EXPRESSION__OPERAND1, oldOperand1, operand1));
            }
        }
        return operand1;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Expression basicGetOperand1() {
        return operand1;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setOperand1(Expression newOperand1) {
        Expression oldOperand1 = operand1;
        operand1 = newOperand1;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.BINARY_EXPRESSION__OPERAND1, oldOperand1, operand1));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getOperator() {
        return operator;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setOperator(String newOperator) {
        String oldOperator = operator;
        operator = newOperator;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.BINARY_EXPRESSION__OPERATOR, oldOperator, operator));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Expression getOperand2() {
        if (operand2 != null && operand2.eIsProxy()) {
            InternalEObject oldOperand2 = (InternalEObject)operand2;
            operand2 = (Expression)eResolveProxy(oldOperand2);
            if (operand2 != oldOperand2) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.BINARY_EXPRESSION__OPERAND2, oldOperand2, operand2));
            }
        }
        return operand2;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Expression basicGetOperand2() {
        return operand2;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setOperand2(Expression newOperand2) {
        Expression oldOperand2 = operand2;
        operand2 = newOperand2;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.BINARY_EXPRESSION__OPERAND2, oldOperand2, operand2));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case IrPackage.BINARY_EXPRESSION__OPERAND1:
                if (resolve) return getOperand1();
                return basicGetOperand1();
            case IrPackage.BINARY_EXPRESSION__OPERATOR:
                return getOperator();
            case IrPackage.BINARY_EXPRESSION__OPERAND2:
                if (resolve) return getOperand2();
                return basicGetOperand2();
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
            case IrPackage.BINARY_EXPRESSION__OPERAND1:
                setOperand1((Expression)newValue);
                return;
            case IrPackage.BINARY_EXPRESSION__OPERATOR:
                setOperator((String)newValue);
                return;
            case IrPackage.BINARY_EXPRESSION__OPERAND2:
                setOperand2((Expression)newValue);
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
            case IrPackage.BINARY_EXPRESSION__OPERAND1:
                setOperand1((Expression)null);
                return;
            case IrPackage.BINARY_EXPRESSION__OPERATOR:
                setOperator(OPERATOR_EDEFAULT);
                return;
            case IrPackage.BINARY_EXPRESSION__OPERAND2:
                setOperand2((Expression)null);
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
            case IrPackage.BINARY_EXPRESSION__OPERAND1:
                return operand1 != null;
            case IrPackage.BINARY_EXPRESSION__OPERATOR:
                return OPERATOR_EDEFAULT == null ? operator != null : !OPERATOR_EDEFAULT.equals(operator);
            case IrPackage.BINARY_EXPRESSION__OPERAND2:
                return operand2 != null;
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
        result.append(" (operator: ");
        result.append(operator);
        result.append(')');
        return result.toString();
    }

} //BinaryExpressionImpl
