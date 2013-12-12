/**
 */
package org.caltoopia.ir;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Unary Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.UnaryExpression#getOperand <em>Operand</em>}</li>
 *   <li>{@link org.caltoopia.ir.UnaryExpression#getOperator <em>Operator</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getUnaryExpression()
 * @model
 * @generated
 */
public interface UnaryExpression extends Expression {
	/**
     * Returns the value of the '<em><b>Operand</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operand</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Operand</em>' reference.
     * @see #setOperand(Expression)
     * @see org.caltoopia.ir.IrPackage#getUnaryExpression_Operand()
     * @model
     * @generated
     */
	Expression getOperand();

	/**
     * Sets the value of the '{@link org.caltoopia.ir.UnaryExpression#getOperand <em>Operand</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Operand</em>' reference.
     * @see #getOperand()
     * @generated
     */
	void setOperand(Expression value);

	/**
     * Returns the value of the '<em><b>Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Operator</em>' attribute.
     * @see #setOperator(String)
     * @see org.caltoopia.ir.IrPackage#getUnaryExpression_Operator()
     * @model
     * @generated
     */
	String getOperator();

	/**
     * Sets the value of the '{@link org.caltoopia.ir.UnaryExpression#getOperator <em>Operator</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Operator</em>' attribute.
     * @see #getOperator()
     * @generated
     */
	void setOperator(String value);

} // UnaryExpression
