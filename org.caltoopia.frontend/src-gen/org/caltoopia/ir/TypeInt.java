/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.caltoopia.ir;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Int</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.TypeInt#getSize <em>Size</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getTypeInt()
 * @model
 * @generated
 */
public interface TypeInt extends Type {
	/**
	 * Returns the value of the '<em><b>Size</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Size</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Size</em>' reference.
	 * @see #setSize(Expression)
	 * @see org.caltoopia.ir.IrPackage#getTypeInt_Size()
	 * @model
	 * @generated
	 */
	Expression getSize();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.TypeInt#getSize <em>Size</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Size</em>' reference.
	 * @see #getSize()
	 * @generated
	 */
	void setSize(Expression value);

} // TypeInt
