/**
 */
package org.caltoopia.ir;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>If Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.IfExpression#getThenExpression <em>Then Expression</em>}</li>
 *   <li>{@link org.caltoopia.ir.IfExpression#getElseExpression <em>Else Expression</em>}</li>
 *   <li>{@link org.caltoopia.ir.IfExpression#getCondition <em>Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getIfExpression()
 * @model
 * @generated
 */
public interface IfExpression extends Expression {
	/**
	 * Returns the value of the '<em><b>Then Expression</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Then Expression</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Then Expression</em>' reference.
	 * @see #setThenExpression(Expression)
	 * @see org.caltoopia.ir.IrPackage#getIfExpression_ThenExpression()
	 * @model
	 * @generated
	 */
	Expression getThenExpression();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.IfExpression#getThenExpression <em>Then Expression</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Then Expression</em>' reference.
	 * @see #getThenExpression()
	 * @generated
	 */
	void setThenExpression(Expression value);

	/**
	 * Returns the value of the '<em><b>Else Expression</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Else Expression</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Else Expression</em>' reference.
	 * @see #setElseExpression(Expression)
	 * @see org.caltoopia.ir.IrPackage#getIfExpression_ElseExpression()
	 * @model
	 * @generated
	 */
	Expression getElseExpression();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.IfExpression#getElseExpression <em>Else Expression</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Else Expression</em>' reference.
	 * @see #getElseExpression()
	 * @generated
	 */
	void setElseExpression(Expression value);

	/**
	 * Returns the value of the '<em><b>Condition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' reference.
	 * @see #setCondition(Expression)
	 * @see org.caltoopia.ir.IrPackage#getIfExpression_Condition()
	 * @model
	 * @generated
	 */
	Expression getCondition();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.IfExpression#getCondition <em>Condition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' reference.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(Expression value);

} // IfExpression
