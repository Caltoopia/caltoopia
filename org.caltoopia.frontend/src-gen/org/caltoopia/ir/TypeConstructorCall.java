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
 * A representation of the model object '<em><b>Type Constructor Call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.TypeConstructorCall#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.caltoopia.ir.TypeConstructorCall#getTypedef <em>Typedef</em>}</li>
 *   <li>{@link org.caltoopia.ir.TypeConstructorCall#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getTypeConstructorCall()
 * @model
 * @generated
 */
public interface TypeConstructorCall extends ExpressionCall {
	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' reference list.
	 * The list contents are of type {@link org.caltoopia.ir.Expression}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameters</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameters</em>' reference list.
	 * @see org.caltoopia.ir.IrPackage#getTypeConstructorCall_Parameters()
	 * @model
	 * @generated
	 */
	EList<Expression> getParameters();

	/**
	 * Returns the value of the '<em><b>Typedef</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Typedef</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Typedef</em>' reference.
	 * @see #setTypedef(Declaration)
	 * @see org.caltoopia.ir.IrPackage#getTypeConstructorCall_Typedef()
	 * @model
	 * @generated
	 */
	Declaration getTypedef();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.TypeConstructorCall#getTypedef <em>Typedef</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Typedef</em>' reference.
	 * @see #getTypedef()
	 * @generated
	 */
	void setTypedef(Declaration value);

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
	 * @see org.caltoopia.ir.IrPackage#getTypeConstructorCall_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.TypeConstructorCall#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // TypeConstructorCall
