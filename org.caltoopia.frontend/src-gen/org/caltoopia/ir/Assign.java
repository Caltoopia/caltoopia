/**
 */
package org.caltoopia.ir;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Assign</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.Assign#getTarget <em>Target</em>}</li>
 *   <li>{@link org.caltoopia.ir.Assign#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getAssign()
 * @model
 * @generated
 */
public interface Assign extends Statement {
	/**
     * Returns the value of the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Target</em>' reference.
     * @see #setTarget(VariableReference)
     * @see org.caltoopia.ir.IrPackage#getAssign_Target()
     * @model
     * @generated
     */
	VariableReference getTarget();

	/**
     * Sets the value of the '{@link org.caltoopia.ir.Assign#getTarget <em>Target</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target</em>' reference.
     * @see #getTarget()
     * @generated
     */
	void setTarget(VariableReference value);

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
     * @see org.caltoopia.ir.IrPackage#getAssign_Expression()
     * @model
     * @generated
     */
	Expression getExpression();

	/**
     * Sets the value of the '{@link org.caltoopia.ir.Assign#getExpression <em>Expression</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Expression</em>' reference.
     * @see #getExpression()
     * @generated
     */
	void setExpression(Expression value);

} // Assign
