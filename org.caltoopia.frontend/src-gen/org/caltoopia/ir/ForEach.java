/**
 */
package org.caltoopia.ir;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>For Each</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.ForEach#getBody <em>Body</em>}</li>
 *   <li>{@link org.caltoopia.ir.ForEach#getGenerators <em>Generators</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getForEach()
 * @model
 * @generated
 */
public interface ForEach extends Statement {
	/**
     * Returns the value of the '<em><b>Body</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Body</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Body</em>' reference.
     * @see #setBody(Block)
     * @see org.caltoopia.ir.IrPackage#getForEach_Body()
     * @model
     * @generated
     */
	Block getBody();

	/**
     * Sets the value of the '{@link org.caltoopia.ir.ForEach#getBody <em>Body</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Body</em>' reference.
     * @see #getBody()
     * @generated
     */
	void setBody(Block value);

	/**
     * Returns the value of the '<em><b>Generators</b></em>' reference list.
     * The list contents are of type {@link org.caltoopia.ir.Generator}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generators</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Generators</em>' reference list.
     * @see org.caltoopia.ir.IrPackage#getForEach_Generators()
     * @model
     * @generated
     */
	EList<Generator> getGenerators();

} // ForEach
