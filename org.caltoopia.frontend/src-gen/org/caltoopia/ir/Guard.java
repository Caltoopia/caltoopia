/**
 */
package org.caltoopia.ir;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Guard</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.Guard#getPeeks <em>Peeks</em>}</li>
 *   <li>{@link org.caltoopia.ir.Guard#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getGuard()
 * @model
 * @generated
 */
public interface Guard extends Scope {
	/**
     * Returns the value of the '<em><b>Peeks</b></em>' reference list.
     * The list contents are of type {@link org.caltoopia.ir.PortPeek}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Peeks</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Peeks</em>' reference list.
     * @see org.caltoopia.ir.IrPackage#getGuard_Peeks()
     * @model
     * @generated
     */
	EList<PortPeek> getPeeks();

	/**
     * Returns the value of the '<em><b>Expression</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Expression</em>' reference.
     * @see #setExpression(Expression)
     * @see org.caltoopia.ir.IrPackage#getGuard_Expression()
     * @model
     * @generated
     */
	Expression getExpression();

	/**
     * Sets the value of the '{@link org.caltoopia.ir.Guard#getExpression <em>Expression</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Expression</em>' reference.
     * @see #getExpression()
     * @generated
     */
	void setExpression(Expression value);

} // Guard
