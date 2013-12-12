/**
 */
package org.caltoopia.ir;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type User</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.TypeUser#getDeclaration <em>Declaration</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getTypeUser()
 * @model
 * @generated
 */
public interface TypeUser extends Type {
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
     * @see org.caltoopia.ir.IrPackage#getTypeUser_Declaration()
     * @model
     * @generated
     */
	Declaration getDeclaration();

	/**
     * Sets the value of the '{@link org.caltoopia.ir.TypeUser#getDeclaration <em>Declaration</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Declaration</em>' reference.
     * @see #getDeclaration()
     * @generated
     */
	void setDeclaration(Declaration value);

} // TypeUser
