/**
 */
package org.caltoopia.ir;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Case Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.CaseStatement#getAlternatives <em>Alternatives</em>}</li>
 *   <li>{@link org.caltoopia.ir.CaseStatement#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.caltoopia.ir.CaseStatement#getDefault <em>Default</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getCaseStatement()
 * @model
 * @generated
 */
public interface CaseStatement extends Statement {
	/**
	 * Returns the value of the '<em><b>Alternatives</b></em>' reference list.
	 * The list contents are of type {@link org.caltoopia.ir.StmtAlternative}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Alternatives</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Alternatives</em>' reference list.
	 * @see org.caltoopia.ir.IrPackage#getCaseStatement_Alternatives()
	 * @model
	 * @generated
	 */
	EList<StmtAlternative> getAlternatives();

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
	 * @see org.caltoopia.ir.IrPackage#getCaseStatement_Expression()
	 * @model
	 * @generated
	 */
	Expression getExpression();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.CaseStatement#getExpression <em>Expression</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(Expression value);

	/**
	 * Returns the value of the '<em><b>Default</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default</em>' reference.
	 * @see #setDefault(Statement)
	 * @see org.caltoopia.ir.IrPackage#getCaseStatement_Default()
	 * @model
	 * @generated
	 */
	Statement getDefault();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.CaseStatement#getDefault <em>Default</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default</em>' reference.
	 * @see #getDefault()
	 * @generated
	 */
	void setDefault(Statement value);

} // CaseStatement
