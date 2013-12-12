/**
 */
package org.caltoopia.ir;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.Action#getGuards <em>Guards</em>}</li>
 *   <li>{@link org.caltoopia.ir.Action#getOutputs <em>Outputs</em>}</li>
 *   <li>{@link org.caltoopia.ir.Action#getInputs <em>Inputs</em>}</li>
 *   <li>{@link org.caltoopia.ir.Action#getTag <em>Tag</em>}</li>
 *   <li>{@link org.caltoopia.ir.Action#getStatements <em>Statements</em>}</li>
 *   <li>{@link org.caltoopia.ir.Action#getTypeGuards <em>Type Guards</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getAction()
 * @model
 * @generated
 */
public interface Action extends Scope {
	/**
     * Returns the value of the '<em><b>Guards</b></em>' reference list.
     * The list contents are of type {@link org.caltoopia.ir.Guard}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Guards</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Guards</em>' reference list.
     * @see org.caltoopia.ir.IrPackage#getAction_Guards()
     * @model
     * @generated
     */
	EList<Guard> getGuards();

	/**
     * Returns the value of the '<em><b>Outputs</b></em>' reference list.
     * The list contents are of type {@link org.caltoopia.ir.PortWrite}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outputs</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Outputs</em>' reference list.
     * @see org.caltoopia.ir.IrPackage#getAction_Outputs()
     * @model
     * @generated
     */
	EList<PortWrite> getOutputs();

	/**
     * Returns the value of the '<em><b>Inputs</b></em>' reference list.
     * The list contents are of type {@link org.caltoopia.ir.PortRead}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inputs</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Inputs</em>' reference list.
     * @see org.caltoopia.ir.IrPackage#getAction_Inputs()
     * @model
     * @generated
     */
	EList<PortRead> getInputs();

	/**
     * Returns the value of the '<em><b>Tag</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tag</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Tag</em>' attribute list.
     * @see org.caltoopia.ir.IrPackage#getAction_Tag()
     * @model
     * @generated
     */
	EList<String> getTag();

	/**
     * Returns the value of the '<em><b>Statements</b></em>' reference list.
     * The list contents are of type {@link org.caltoopia.ir.Statement}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Statements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Statements</em>' reference list.
     * @see org.caltoopia.ir.IrPackage#getAction_Statements()
     * @model
     * @generated
     */
	EList<Statement> getStatements();

	/**
     * Returns the value of the '<em><b>Type Guards</b></em>' reference list.
     * The list contents are of type {@link org.caltoopia.ir.Guard}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Guards</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Type Guards</em>' reference list.
     * @see org.caltoopia.ir.IrPackage#getAction_TypeGuards()
     * @model
     * @generated
     */
	EList<Guard> getTypeGuards();

} // Action
