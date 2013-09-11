/**
 */
package org.caltoopia.ir;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.TypeVariable#getDeclaration <em>Declaration</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getTypeVariable()
 * @model
 * @generated
 */
public interface TypeVariable extends Type {
	/**
	 * Returns the value of the '<em><b>Declaration</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Declaration</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Declaration</em>' reference.
	 * @see #setDeclaration(TypeVariableDeclaration)
	 * @see org.caltoopia.ir.IrPackage#getTypeVariable_Declaration()
	 * @model
	 * @generated
	 */
	TypeVariableDeclaration getDeclaration();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.TypeVariable#getDeclaration <em>Declaration</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Declaration</em>' reference.
	 * @see #getDeclaration()
	 * @generated
	 */
	void setDeclaration(TypeVariableDeclaration value);

} // TypeVariable
