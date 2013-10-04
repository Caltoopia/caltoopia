/**
 */
package org.caltoopia.ir;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expr Alternative</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.ExprAlternative#getTypeGuard <em>Type Guard</em>}</li>
 *   <li>{@link org.caltoopia.ir.ExprAlternative#getValueGuards <em>Value Guards</em>}</li>
 *   <li>{@link org.caltoopia.ir.ExprAlternative#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getExprAlternative()
 * @model
 * @generated
 */
public interface ExprAlternative extends Scope {
	/**
	 * Returns the value of the '<em><b>Type Guard</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Guard</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Guard</em>' reference.
	 * @see #setTypeGuard(TypeGuard)
	 * @see org.caltoopia.ir.IrPackage#getExprAlternative_TypeGuard()
	 * @model
	 * @generated
	 */
	TypeGuard getTypeGuard();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.ExprAlternative#getTypeGuard <em>Type Guard</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Guard</em>' reference.
	 * @see #getTypeGuard()
	 * @generated
	 */
	void setTypeGuard(TypeGuard value);

	/**
	 * Returns the value of the '<em><b>Value Guards</b></em>' reference list.
	 * The list contents are of type {@link org.caltoopia.ir.Expression}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value Guards</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value Guards</em>' reference list.
	 * @see org.caltoopia.ir.IrPackage#getExprAlternative_ValueGuards()
	 * @model
	 * @generated
	 */
	EList<Expression> getValueGuards();

	/**
	 * Returns the value of the '<em><b>Expression</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' reference.
	 * @see #setExpression(Expression)
	 * @see org.caltoopia.ir.IrPackage#getExprAlternative_Expression()
	 * @model
	 * @generated
	 */
	Expression getExpression();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.ExprAlternative#getExpression <em>Expression</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(Expression value);

} // ExprAlternative
