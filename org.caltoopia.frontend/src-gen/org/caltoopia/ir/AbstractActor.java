/**
 */
package org.caltoopia.ir;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Actor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.AbstractActor#getType <em>Type</em>}</li>
 *   <li>{@link org.caltoopia.ir.AbstractActor#getInputPorts <em>Input Ports</em>}</li>
 *   <li>{@link org.caltoopia.ir.AbstractActor#getOutputPorts <em>Output Ports</em>}</li>
 *   <li>{@link org.caltoopia.ir.AbstractActor#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getAbstractActor()
 * @model
 * @generated
 */
public interface AbstractActor extends Scope {
	/**
     * Returns the value of the '<em><b>Type</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' reference.
     * @see #setType(TypeActor)
     * @see org.caltoopia.ir.IrPackage#getAbstractActor_Type()
     * @model
     * @generated
     */
	TypeActor getType();

	/**
     * Sets the value of the '{@link org.caltoopia.ir.AbstractActor#getType <em>Type</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' reference.
     * @see #getType()
     * @generated
     */
	void setType(TypeActor value);

	/**
     * Returns the value of the '<em><b>Input Ports</b></em>' reference list.
     * The list contents are of type {@link org.caltoopia.ir.Port}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input Ports</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Input Ports</em>' reference list.
     * @see org.caltoopia.ir.IrPackage#getAbstractActor_InputPorts()
     * @model
     * @generated
     */
	EList<Port> getInputPorts();

	/**
     * Returns the value of the '<em><b>Output Ports</b></em>' reference list.
     * The list contents are of type {@link org.caltoopia.ir.Port}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output Ports</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Output Ports</em>' reference list.
     * @see org.caltoopia.ir.IrPackage#getAbstractActor_OutputPorts()
     * @model
     * @generated
     */
	EList<Port> getOutputPorts();

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
     * @see org.caltoopia.ir.IrPackage#getAbstractActor_Parameters()
     * @model
     * @generated
     */
	EList<Variable> getParameters();

} // AbstractActor
