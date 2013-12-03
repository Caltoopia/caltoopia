/**
 */
package org.caltoopia.ir;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.VariableReference#getDeclaration <em>Declaration</em>}</li>
 *   <li>{@link org.caltoopia.ir.VariableReference#getIndex <em>Index</em>}</li>
 *   <li>{@link org.caltoopia.ir.VariableReference#getMember <em>Member</em>}</li>
 *   <li>{@link org.caltoopia.ir.VariableReference#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getVariableReference()
 * @model
 * @generated
 */
public interface VariableReference extends Node {
	/**
	 * Returns the value of the '<em><b>Declaration</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Declaration</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Declaration</em>' reference.
	 * @see #setDeclaration(Variable)
	 * @see org.caltoopia.ir.IrPackage#getVariableReference_Declaration()
	 * @model
	 * @generated
	 */
	Variable getDeclaration();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.VariableReference#getDeclaration <em>Declaration</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Declaration</em>' reference.
	 * @see #getDeclaration()
	 * @generated
	 */
	void setDeclaration(Variable value);

	/**
	 * Returns the value of the '<em><b>Index</b></em>' reference list.
	 * The list contents are of type {@link org.caltoopia.ir.Expression}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Index</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Index</em>' reference list.
	 * @see org.caltoopia.ir.IrPackage#getVariableReference_Index()
	 * @model
	 * @generated
	 */
	EList<Expression> getIndex();

	/**
	 * Returns the value of the '<em><b>Member</b></em>' reference list.
	 * The list contents are of type {@link org.caltoopia.ir.Member}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Member</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Member</em>' reference list.
	 * @see org.caltoopia.ir.IrPackage#getVariableReference_Member()
	 * @model
	 * @generated
	 */
	EList<Member> getMember();

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
	 * @see org.caltoopia.ir.IrPackage#getVariableReference_Type()
	 * @model
	 * @generated
	 */
	Type getType();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.VariableReference#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(Type value);

} // VariableReference
