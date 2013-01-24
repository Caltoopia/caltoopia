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
 * A representation of the model object '<em><b>Type Lambda</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.TypeLambda#getInputTypes <em>Input Types</em>}</li>
 *   <li>{@link org.caltoopia.ir.TypeLambda#getOutputType <em>Output Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getTypeLambda()
 * @model
 * @generated
 */
public interface TypeLambda extends Type {
	/**
	 * Returns the value of the '<em><b>Input Types</b></em>' reference list.
	 * The list contents are of type {@link org.caltoopia.ir.Type}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input Types</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Input Types</em>' reference list.
	 * @see org.caltoopia.ir.IrPackage#getTypeLambda_InputTypes()
	 * @model
	 * @generated
	 */
	EList<Type> getInputTypes();

	/**
	 * Returns the value of the '<em><b>Output Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Output Type</em>' reference.
	 * @see #setOutputType(Type)
	 * @see org.caltoopia.ir.IrPackage#getTypeLambda_OutputType()
	 * @model
	 * @generated
	 */
	Type getOutputType();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.TypeLambda#getOutputType <em>Output Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Output Type</em>' reference.
	 * @see #getOutputType()
	 * @generated
	 */
	void setOutputType(Type value);

} // TypeLambda
