/**
 */
package org.caltoopia.ir.impl;

import java.util.Collection;

import org.caltoopia.ir.Expression;
import org.caltoopia.ir.Generator;
import org.caltoopia.ir.IrPackage;
import org.caltoopia.ir.ListExpression;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>List Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.ir.impl.ListExpressionImpl#getGenerators <em>Generators</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.ListExpressionImpl#getExpressions <em>Expressions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ListExpressionImpl extends ExpressionImpl implements ListExpression {
	/**
     * The cached value of the '{@link #getGenerators() <em>Generators</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getGenerators()
     * @generated
     * @ordered
     */
	protected EList<Generator> generators;

	/**
     * The cached value of the '{@link #getExpressions() <em>Expressions</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getExpressions()
     * @generated
     * @ordered
     */
	protected EList<Expression> expressions;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ListExpressionImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return IrPackage.Literals.LIST_EXPRESSION;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<Generator> getGenerators() {
        if (generators == null) {
            generators = new EObjectResolvingEList<Generator>(Generator.class, this, IrPackage.LIST_EXPRESSION__GENERATORS);
        }
        return generators;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<Expression> getExpressions() {
        if (expressions == null) {
            expressions = new EObjectResolvingEList<Expression>(Expression.class, this, IrPackage.LIST_EXPRESSION__EXPRESSIONS);
        }
        return expressions;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case IrPackage.LIST_EXPRESSION__GENERATORS:
                return getGenerators();
            case IrPackage.LIST_EXPRESSION__EXPRESSIONS:
                return getExpressions();
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
            case IrPackage.LIST_EXPRESSION__GENERATORS:
                getGenerators().clear();
                getGenerators().addAll((Collection<? extends Generator>)newValue);
                return;
            case IrPackage.LIST_EXPRESSION__EXPRESSIONS:
                getExpressions().clear();
                getExpressions().addAll((Collection<? extends Expression>)newValue);
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
            case IrPackage.LIST_EXPRESSION__GENERATORS:
                getGenerators().clear();
                return;
            case IrPackage.LIST_EXPRESSION__EXPRESSIONS:
                getExpressions().clear();
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
            case IrPackage.LIST_EXPRESSION__GENERATORS:
                return generators != null && !generators.isEmpty();
            case IrPackage.LIST_EXPRESSION__EXPRESSIONS:
                return expressions != null && !expressions.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //ListExpressionImpl
