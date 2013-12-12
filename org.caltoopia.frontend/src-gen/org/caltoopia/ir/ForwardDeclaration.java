/**
 */
package org.caltoopia.ir;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Forward Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.ForwardDeclaration#getDeclaration <em>Declaration</em>}</li>
 *   <li>{@link org.caltoopia.ir.ForwardDeclaration#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getForwardDeclaration()
 * @model
 * @generated
 */
public interface ForwardDeclaration extends Declaration {
	/**
     * Returns the value of the '<em><b>Declaration</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Declaration</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Declaration</em>' reference.
     * @see #setDeclaration(Declaration)
     * @see org.caltoopia.ir.IrPackage#getForwardDeclaration_Declaration()
     * @model
     * @generated
     */
	Declaration getDeclaration();

	/**
     * Sets the value of the '{@link org.caltoopia.ir.ForwardDeclaration#getDeclaration <em>Declaration</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Declaration</em>' reference.
     * @see #getDeclaration()
     * @generated
     */
	void setDeclaration(Declaration value);

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
     * @see org.caltoopia.ir.IrPackage#getForwardDeclaration_Type()
     * @model
     * @generated
     */
	Type getType();

	/**
     * Sets the value of the '{@link org.caltoopia.ir.ForwardDeclaration#getType <em>Type</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' reference.
     * @see #getType()
     * @generated
     */
	void setType(Type value);

} // ForwardDeclaration
