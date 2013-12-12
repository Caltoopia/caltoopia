/**
 */
package org.caltoopia.ir;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Case Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.CaseExpression#getAlternatives <em>Alternatives</em>}</li>
 *   <li>{@link org.caltoopia.ir.CaseExpression#getDefault <em>Default</em>}</li>
 *   <li>{@link org.caltoopia.ir.CaseExpression#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getCaseExpression()
 * @model
 * @generated
 */
public interface CaseExpression extends Expression {
	/**
     * Returns the value of the '<em><b>Alternatives</b></em>' reference list.
     * The list contents are of type {@link org.caltoopia.ir.ExprAlternative}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Alternatives</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Alternatives</em>' reference list.
     * @see org.caltoopia.ir.IrPackage#getCaseExpression_Alternatives()
     * @model
     * @generated
     */
	EList<ExprAlternative> getAlternatives();

	/**
     * Returns the value of the '<em><b>Default</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Default</em>' reference.
     * @see #setDefault(Expression)
     * @see org.caltoopia.ir.IrPackage#getCaseExpression_Default()
     * @model
     * @generated
     */
	Expression getDefault();

	/**
     * Sets the value of the '{@link org.caltoopia.ir.CaseExpression#getDefault <em>Default</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default</em>' reference.
     * @see #getDefault()
     * @generated
     */
	void setDefault(Expression value);

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
     * @see org.caltoopia.ir.IrPackage#getCaseExpression_Expression()
     * @model
     * @generated
     */
	Expression getExpression();

	/**
     * Sets the value of the '{@link org.caltoopia.ir.CaseExpression#getExpression <em>Expression</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Expression</em>' reference.
     * @see #getExpression()
     * @generated
     */
	void setExpression(Expression value);

} // CaseExpression
