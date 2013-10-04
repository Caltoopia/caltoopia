/**
 */
package org.caltoopia.ir;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Guard</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.TypeGuard#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.caltoopia.ir.TypeGuard#getTag <em>Tag</em>}</li>
 *   <li>{@link org.caltoopia.ir.TypeGuard#getReads <em>Reads</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getTypeGuard()
 * @model
 * @generated
 */
public interface TypeGuard extends Guard {
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
	 * @see org.caltoopia.ir.IrPackage#getTypeGuard_Expression()
	 * @model
	 * @generated
	 */
	Expression getExpression();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.TypeGuard#getExpression <em>Expression</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(Expression value);

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
	 * @see org.caltoopia.ir.IrPackage#getTypeGuard_Tag()
	 * @model
	 * @generated
	 */
	String getTag();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.TypeGuard#getTag <em>Tag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tag</em>' attribute.
	 * @see #getTag()
	 * @generated
	 */
	void setTag(String value);

	/**
	 * Returns the value of the '<em><b>Reads</b></em>' reference list.
	 * The list contents are of type {@link org.caltoopia.ir.TaggedTupleFieldRead}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reads</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reads</em>' reference list.
	 * @see org.caltoopia.ir.IrPackage#getTypeGuard_Reads()
	 * @model
	 * @generated
	 */
	EList<TaggedTupleFieldRead> getReads();

} // TypeGuard
