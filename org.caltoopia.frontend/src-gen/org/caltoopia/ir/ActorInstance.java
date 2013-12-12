/**
 */
package org.caltoopia.ir;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Actor Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.ActorInstance#getInputs <em>Inputs</em>}</li>
 *   <li>{@link org.caltoopia.ir.ActorInstance#getOutputs <em>Outputs</em>}</li>
 *   <li>{@link org.caltoopia.ir.ActorInstance#getActualParameters <em>Actual Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getActorInstance()
 * @model
 * @generated
 */
public interface ActorInstance extends Variable {
	/**
     * Returns the value of the '<em><b>Inputs</b></em>' reference list.
     * The list contents are of type {@link org.caltoopia.ir.PortInstance}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inputs</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Inputs</em>' reference list.
     * @see org.caltoopia.ir.IrPackage#getActorInstance_Inputs()
     * @model
     * @generated
     */
	EList<PortInstance> getInputs();

	/**
     * Returns the value of the '<em><b>Outputs</b></em>' reference list.
     * The list contents are of type {@link org.caltoopia.ir.PortInstance}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outputs</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Outputs</em>' reference list.
     * @see org.caltoopia.ir.IrPackage#getActorInstance_Outputs()
     * @model
     * @generated
     */
	EList<PortInstance> getOutputs();

	/**
     * Returns the value of the '<em><b>Actual Parameters</b></em>' reference list.
     * The list contents are of type {@link org.caltoopia.ir.TaggedExpression}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actual Parameters</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Actual Parameters</em>' reference list.
     * @see org.caltoopia.ir.IrPackage#getActorInstance_ActualParameters()
     * @model
     * @generated
     */
	EList<TaggedExpression> getActualParameters();

} // ActorInstance
