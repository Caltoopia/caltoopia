/**
 */
package org.caltoopia.ir;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.TypeDeclaration#getType <em>Type</em>}</li>
 *   <li>{@link org.caltoopia.ir.TypeDeclaration#getValueParameters <em>Value Parameters</em>}</li>
 *   <li>{@link org.caltoopia.ir.TypeDeclaration#getTypeParameters <em>Type Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getTypeDeclaration()
 * @model
 * @generated
 */
public interface TypeDeclaration extends Declaration {
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
	 * @see org.caltoopia.ir.IrPackage#getTypeDeclaration_Type()
	 * @model
	 * @generated
	 */
	Type getType();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.TypeDeclaration#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(Type value);

	/**
	 * Returns the value of the '<em><b>Value Parameters</b></em>' reference list.
	 * The list contents are of type {@link org.caltoopia.ir.Variable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value Parameters</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value Parameters</em>' reference list.
	 * @see org.caltoopia.ir.IrPackage#getTypeDeclaration_ValueParameters()
	 * @model
	 * @generated
	 */
	EList<Variable> getValueParameters();

	/**
	 * Returns the value of the '<em><b>Type Parameters</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Parameters</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Parameters</em>' reference.
	 * @see #setTypeParameters(TypeVariable)
	 * @see org.caltoopia.ir.IrPackage#getTypeDeclaration_TypeParameters()
	 * @model
	 * @generated
	 */
	TypeVariable getTypeParameters();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.TypeDeclaration#getTypeParameters <em>Type Parameters</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Parameters</em>' reference.
	 * @see #getTypeParameters()
	 * @generated
	 */
	void setTypeParameters(TypeVariable value);

} // TypeDeclaration
