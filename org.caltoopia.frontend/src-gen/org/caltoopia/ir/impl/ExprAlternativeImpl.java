/**
 */
package org.caltoopia.ir.impl;

import java.util.Collection;

import org.caltoopia.ir.ExprAlternative;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.IrPackage;
import org.caltoopia.ir.TypeGuard;
import org.caltoopia.ir.PortGuard;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expr Alternative</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.ir.impl.ExprAlternativeImpl#getTypeGuard <em>Type Guard</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.ExprAlternativeImpl#getValueGuards <em>Value Guards</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.ExprAlternativeImpl#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExprAlternativeImpl extends ScopeImpl implements ExprAlternative {
	/**
	 * The cached value of the '{@link #getTypeGuard() <em>Type Guard</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeGuard()
	 * @generated
	 * @ordered
	 */
	protected TypeGuard typeGuard;

	/**
	 * The cached value of the '{@link #getValueGuards() <em>Value Guards</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueGuards()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> valueGuards;

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
	protected ExprAlternativeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IrPackage.Literals.EXPR_ALTERNATIVE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeGuard getTypeGuard() {
		if (typeGuard != null && typeGuard.eIsProxy()) {
			InternalEObject oldTypeGuard = (InternalEObject)typeGuard;
			typeGuard = (TypeGuard)eResolveProxy(oldTypeGuard);
			if (typeGuard != oldTypeGuard) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.EXPR_ALTERNATIVE__TYPE_GUARD, oldTypeGuard, typeGuard));
			}
		}
		return typeGuard;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeGuard basicGetTypeGuard() {
		return typeGuard;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeGuard(TypeGuard newTypeGuard) {
		TypeGuard oldTypeGuard = typeGuard;
		typeGuard = newTypeGuard;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.EXPR_ALTERNATIVE__TYPE_GUARD, oldTypeGuard, typeGuard));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Expression> getValueGuards() {
		if (valueGuards == null) {
			valueGuards = new EObjectResolvingEList<Expression>(Expression.class, this, IrPackage.EXPR_ALTERNATIVE__VALUE_GUARDS);
		}
		return valueGuards;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.EXPR_ALTERNATIVE__EXPRESSION, oldExpression, expression));
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
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.EXPR_ALTERNATIVE__EXPRESSION, oldExpression, expression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IrPackage.EXPR_ALTERNATIVE__TYPE_GUARD:
				if (resolve) return getTypeGuard();
				return basicGetTypeGuard();
			case IrPackage.EXPR_ALTERNATIVE__VALUE_GUARDS:
				return getValueGuards();
			case IrPackage.EXPR_ALTERNATIVE__EXPRESSION:
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
			case IrPackage.EXPR_ALTERNATIVE__TYPE_GUARD:
				setTypeGuard((TypeGuard)newValue);
				return;
			case IrPackage.EXPR_ALTERNATIVE__VALUE_GUARDS:
				getValueGuards().clear();
				getValueGuards().addAll((Collection<? extends Expression>)newValue);
				return;
			case IrPackage.EXPR_ALTERNATIVE__EXPRESSION:
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
			case IrPackage.EXPR_ALTERNATIVE__TYPE_GUARD:
				setTypeGuard((TypeGuard)null);
				return;
			case IrPackage.EXPR_ALTERNATIVE__VALUE_GUARDS:
				getValueGuards().clear();
				return;
			case IrPackage.EXPR_ALTERNATIVE__EXPRESSION:
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
			case IrPackage.EXPR_ALTERNATIVE__TYPE_GUARD:
				return typeGuard != null;
			case IrPackage.EXPR_ALTERNATIVE__VALUE_GUARDS:
				return valueGuards != null && !valueGuards.isEmpty();
			case IrPackage.EXPR_ALTERNATIVE__EXPRESSION:
				return expression != null;
		}
		return super.eIsSet(featureID);
	}

} //ExprAlternativeImpl
