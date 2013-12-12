/**
 */
package org.caltoopia.ir.impl;

import java.util.Collection;

import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.IrPackage;
import org.caltoopia.ir.Member;
import org.caltoopia.ir.VariableExpression;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variable Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.ir.impl.VariableExpressionImpl#getVariable <em>Variable</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.VariableExpressionImpl#getIndex <em>Index</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.VariableExpressionImpl#getMember <em>Member</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VariableExpressionImpl extends ExpressionImpl implements VariableExpression {
	/**
     * The cached value of the '{@link #getVariable() <em>Variable</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getVariable()
     * @generated
     * @ordered
     */
	protected Declaration variable;

	/**
     * The cached value of the '{@link #getIndex() <em>Index</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getIndex()
     * @generated
     * @ordered
     */
	protected EList<Expression> index;

	/**
     * The cached value of the '{@link #getMember() <em>Member</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMember()
     * @generated
     * @ordered
     */
	protected EList<Member> member;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected VariableExpressionImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return IrPackage.Literals.VARIABLE_EXPRESSION;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Declaration getVariable() {
        if (variable != null && variable.eIsProxy()) {
            InternalEObject oldVariable = (InternalEObject)variable;
            variable = (Declaration)eResolveProxy(oldVariable);
            if (variable != oldVariable) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.VARIABLE_EXPRESSION__VARIABLE, oldVariable, variable));
            }
        }
        return variable;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Declaration basicGetVariable() {
        return variable;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setVariable(Declaration newVariable) {
        Declaration oldVariable = variable;
        variable = newVariable;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.VARIABLE_EXPRESSION__VARIABLE, oldVariable, variable));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<Expression> getIndex() {
        if (index == null) {
            index = new EObjectResolvingEList<Expression>(Expression.class, this, IrPackage.VARIABLE_EXPRESSION__INDEX);
        }
        return index;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<Member> getMember() {
        if (member == null) {
            member = new EObjectResolvingEList<Member>(Member.class, this, IrPackage.VARIABLE_EXPRESSION__MEMBER);
        }
        return member;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case IrPackage.VARIABLE_EXPRESSION__VARIABLE:
                if (resolve) return getVariable();
                return basicGetVariable();
            case IrPackage.VARIABLE_EXPRESSION__INDEX:
                return getIndex();
            case IrPackage.VARIABLE_EXPRESSION__MEMBER:
                return getMember();
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
            case IrPackage.VARIABLE_EXPRESSION__VARIABLE:
                setVariable((Declaration)newValue);
                return;
            case IrPackage.VARIABLE_EXPRESSION__INDEX:
                getIndex().clear();
                getIndex().addAll((Collection<? extends Expression>)newValue);
                return;
            case IrPackage.VARIABLE_EXPRESSION__MEMBER:
                getMember().clear();
                getMember().addAll((Collection<? extends Member>)newValue);
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
            case IrPackage.VARIABLE_EXPRESSION__VARIABLE:
                setVariable((Declaration)null);
                return;
            case IrPackage.VARIABLE_EXPRESSION__INDEX:
                getIndex().clear();
                return;
            case IrPackage.VARIABLE_EXPRESSION__MEMBER:
                getMember().clear();
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
            case IrPackage.VARIABLE_EXPRESSION__VARIABLE:
                return variable != null;
            case IrPackage.VARIABLE_EXPRESSION__INDEX:
                return index != null && !index.isEmpty();
            case IrPackage.VARIABLE_EXPRESSION__MEMBER:
                return member != null && !member.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //VariableExpressionImpl
