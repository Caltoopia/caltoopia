/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.caltoopia.ir;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scope</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.Scope#getDeclarations <em>Declarations</em>}</li>
 *   <li>{@link org.caltoopia.ir.Scope#getOuter <em>Outer</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getScope()
 * @model
 * @generated
 */
public interface Scope extends Node {
	/**
	 * Returns the value of the '<em><b>Declarations</b></em>' reference list.
	 * The list contents are of type {@link org.caltoopia.ir.Declaration}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Declarations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Declarations</em>' reference list.
	 * @see org.caltoopia.ir.IrPackage#getScope_Declarations()
	 * @model
	 * @generated
	 */
	EList<Declaration> getDeclarations();

	/**
	 * Returns the value of the '<em><b>Outer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outer</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outer</em>' reference.
	 * @see #setOuter(Scope)
	 * @see org.caltoopia.ir.IrPackage#getScope_Outer()
	 * @model
	 * @generated
	 */
	Scope getOuter();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.Scope#getOuter <em>Outer</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Outer</em>' reference.
	 * @see #getOuter()
	 * @generated
	 */
	void setOuter(Scope value);

} // Scope
