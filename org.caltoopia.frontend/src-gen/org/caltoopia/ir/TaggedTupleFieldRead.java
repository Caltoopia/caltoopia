/**
 */
package org.caltoopia.ir;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tagged Tuple Field Read</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.TaggedTupleFieldRead#getTag <em>Tag</em>}</li>
 *   <li>{@link org.caltoopia.ir.TaggedTupleFieldRead#getLabel <em>Label</em>}</li>
 *   <li>{@link org.caltoopia.ir.TaggedTupleFieldRead#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getTaggedTupleFieldRead()
 * @model
 * @generated
 */
public interface TaggedTupleFieldRead extends Expression {
	/**
	 * Returns the value of the '<em><b>Tag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tag</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tag</em>' attribute.
	 * @see #setTag(String)
	 * @see org.caltoopia.ir.IrPackage#getTaggedTupleFieldRead_Tag()
	 * @model
	 * @generated
	 */
	String getTag();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.TaggedTupleFieldRead#getTag <em>Tag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tag</em>' attribute.
	 * @see #getTag()
	 * @generated
	 */
	void setTag(String value);

	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see #setLabel(String)
	 * @see org.caltoopia.ir.IrPackage#getTaggedTupleFieldRead_Label()
	 * @model
	 * @generated
	 */
	String getLabel();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.TaggedTupleFieldRead#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' attribute.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(String value);

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
	 * @see org.caltoopia.ir.IrPackage#getTaggedTupleFieldRead_Value()
	 * @model
	 * @generated
	 */
	Expression getValue();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.TaggedTupleFieldRead#getValue <em>Value</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' reference.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(Expression value);

} // TaggedTupleFieldRead
