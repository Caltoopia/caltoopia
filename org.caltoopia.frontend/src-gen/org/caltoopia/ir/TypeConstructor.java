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
 * A representation of the model object '<em><b>Type Constructor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.TypeConstructor#getTypedef <em>Typedef</em>}</li>
 *   <li>{@link org.caltoopia.ir.TypeConstructor#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getTypeConstructor()
 * @model
 * @generated
 */
public interface TypeConstructor extends Declaration {
	/**
	 * Returns the value of the '<em><b>Typedef</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Typedef</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Typedef</em>' reference.
	 * @see #setTypedef(TypeDeclaration)
	 * @see org.caltoopia.ir.IrPackage#getTypeConstructor_Typedef()
	 * @model
	 * @generated
	 */
	TypeDeclaration getTypedef();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.TypeConstructor#getTypedef <em>Typedef</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Typedef</em>' reference.
	 * @see #getTypedef()
	 * @generated
	 */
	void setTypedef(TypeDeclaration value);

	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' reference list.
	 * The list contents are of type {@link org.caltoopia.ir.Variable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameters</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameters</em>' reference list.
	 * @see org.caltoopia.ir.IrPackage#getTypeConstructor_Parameters()
	 * @model
	 * @generated
	 */
	EList<Variable> getParameters();

} // TypeConstructor
