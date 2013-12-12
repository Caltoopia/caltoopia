/**
 */
package org.caltoopia.ir;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.Variable#getInitValue <em>Init Value</em>}</li>
 *   <li>{@link org.caltoopia.ir.Variable#isConstant <em>Constant</em>}</li>
 *   <li>{@link org.caltoopia.ir.Variable#getType <em>Type</em>}</li>
 *   <li>{@link org.caltoopia.ir.Variable#isParameter <em>Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getVariable()
 * @model
 * @generated
 */
public interface Variable extends Declaration {
	/**
     * Returns the value of the '<em><b>Init Value</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Init Value</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Init Value</em>' reference.
     * @see #setInitValue(Expression)
     * @see org.caltoopia.ir.IrPackage#getVariable_InitValue()
     * @model
     * @generated
     */
	Expression getInitValue();

	/**
     * Sets the value of the '{@link org.caltoopia.ir.Variable#getInitValue <em>Init Value</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Init Value</em>' reference.
     * @see #getInitValue()
     * @generated
     */
	void setInitValue(Expression value);

	/**
     * Returns the value of the '<em><b>Constant</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constant</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Constant</em>' attribute.
     * @see #setConstant(boolean)
     * @see org.caltoopia.ir.IrPackage#getVariable_Constant()
     * @model default="false"
     * @generated
     */
	boolean isConstant();

	/**
     * Sets the value of the '{@link org.caltoopia.ir.Variable#isConstant <em>Constant</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Constant</em>' attribute.
     * @see #isConstant()
     * @generated
     */
	void setConstant(boolean value);

	/**
     * Returns the value of the '<em><b>Type</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' reference.
     * @see #setType(Type)
     * @see org.caltoopia.ir.IrPackage#getVariable_Type()
     * @model
     * @generated
     */
	Type getType();

	/**
     * Sets the value of the '{@link org.caltoopia.ir.Variable#getType <em>Type</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' reference.
     * @see #getType()
     * @generated
     */
	void setType(Type value);

	/**
     * Returns the value of the '<em><b>Parameter</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Parameter</em>' attribute.
     * @see #setParameter(boolean)
     * @see org.caltoopia.ir.IrPackage#getVariable_Parameter()
     * @model default="false"
     * @generated
     */
	boolean isParameter();

	/**
     * Sets the value of the '{@link org.caltoopia.ir.Variable#isParameter <em>Parameter</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parameter</em>' attribute.
     * @see #isParameter()
     * @generated
     */
	void setParameter(boolean value);

} // Variable
