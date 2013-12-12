/**
 */
package org.caltoopia.ir;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Port Read</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.PortRead#getVariables <em>Variables</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getPortRead()
 * @model
 * @generated
 */
public interface PortRead extends PortAccess {
	/**
     * Returns the value of the '<em><b>Variables</b></em>' reference list.
     * The list contents are of type {@link org.caltoopia.ir.VariableReference}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variables</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Variables</em>' reference list.
     * @see org.caltoopia.ir.IrPackage#getPortRead_Variables()
     * @model
     * @generated
     */
	EList<VariableReference> getVariables();

} // PortRead
