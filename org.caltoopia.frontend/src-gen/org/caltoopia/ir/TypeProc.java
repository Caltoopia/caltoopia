/**
 */
package org.caltoopia.ir;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Proc</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.TypeProc#getInputTypes <em>Input Types</em>}</li>
 *   <li>{@link org.caltoopia.ir.TypeProc#getOutputTypes <em>Output Types</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getTypeProc()
 * @model
 * @generated
 */
public interface TypeProc extends Type {
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
	 * @see org.caltoopia.ir.IrPackage#getTypeProc_InputTypes()
	 * @model
	 * @generated
	 */
	EList<Type> getInputTypes();

	/**
	 * Returns the value of the '<em><b>Output Types</b></em>' reference list.
	 * The list contents are of type {@link org.caltoopia.ir.Type}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output Types</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Output Types</em>' reference list.
	 * @see org.caltoopia.ir.IrPackage#getTypeProc_OutputTypes()
	 * @model
	 * @generated
	 */
	EList<Type> getOutputTypes();

} // TypeProc
