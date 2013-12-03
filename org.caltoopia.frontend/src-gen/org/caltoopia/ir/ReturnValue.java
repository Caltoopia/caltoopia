/**
 */
package org.caltoopia.ir;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Return Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.ReturnValue#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getReturnValue()
 * @model
 * @generated
 */
public interface ReturnValue extends Statement {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' reference.
	 * @see #setValue(Expression)
	 * @see org.caltoopia.ir.IrPackage#getReturnValue_Value()
	 * @model
	 * @generated
	 */
	Expression getValue();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.ReturnValue#getValue <em>Value</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' reference.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(Expression value);

} // ReturnValue
