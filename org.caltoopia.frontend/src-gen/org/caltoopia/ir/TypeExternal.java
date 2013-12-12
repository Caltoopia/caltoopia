/**
 */
package org.caltoopia.ir;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type External</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.TypeExternal#getName <em>Name</em>}</li>
 *   <li>{@link org.caltoopia.ir.TypeExternal#getScopeName <em>Scope Name</em>}</li>
 *   <li>{@link org.caltoopia.ir.TypeExternal#getAttributes <em>Attributes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getTypeExternal()
 * @model
 * @generated
 */
public interface TypeExternal extends Type {
	/**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.caltoopia.ir.IrPackage#getTypeExternal_Name()
     * @model
     * @generated
     */
	String getName();

	/**
     * Sets the value of the '{@link org.caltoopia.ir.TypeExternal#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
	void setName(String value);

	/**
     * Returns the value of the '<em><b>Scope Name</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scope Name</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Scope Name</em>' attribute list.
     * @see org.caltoopia.ir.IrPackage#getTypeExternal_ScopeName()
     * @model
     * @generated
     */
	EList<String> getScopeName();

	/**
     * Returns the value of the '<em><b>Attributes</b></em>' reference list.
     * The list contents are of type {@link org.caltoopia.ir.TaggedExpression}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Attributes</em>' reference list.
     * @see org.caltoopia.ir.IrPackage#getTypeExternal_Attributes()
     * @model
     * @generated
     */
	EList<TaggedExpression> getAttributes();

} // TypeExternal
