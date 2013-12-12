/**
 */
package org.caltoopia.ir;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>While Loop</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.WhileLoop#getBody <em>Body</em>}</li>
 *   <li>{@link org.caltoopia.ir.WhileLoop#getCondition <em>Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getWhileLoop()
 * @model
 * @generated
 */
public interface WhileLoop extends Statement {
	/**
     * Returns the value of the '<em><b>Body</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Body</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Body</em>' reference.
     * @see #setBody(Block)
     * @see org.caltoopia.ir.IrPackage#getWhileLoop_Body()
     * @model
     * @generated
     */
	Block getBody();

	/**
     * Sets the value of the '{@link org.caltoopia.ir.WhileLoop#getBody <em>Body</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Body</em>' reference.
     * @see #getBody()
     * @generated
     */
	void setBody(Block value);

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
     * @see org.caltoopia.ir.IrPackage#getWhileLoop_Condition()
     * @model
     * @generated
     */
	Expression getCondition();

	/**
     * Sets the value of the '{@link org.caltoopia.ir.WhileLoop#getCondition <em>Condition</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Condition</em>' reference.
     * @see #getCondition()
     * @generated
     */
	void setCondition(Expression value);

} // WhileLoop
