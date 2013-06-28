/**
 */
package org.caltoopia.ir;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.VariableExpression#getVariable <em>Variable</em>}</li>
 *   <li>{@link org.caltoopia.ir.VariableExpression#getIndex <em>Index</em>}</li>
 *   <li>{@link org.caltoopia.ir.VariableExpression#getMember <em>Member</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getVariableExpression()
 * @model
 * @generated
 */
public interface VariableExpression extends Expression {
	/**
	 * Returns the value of the '<em><b>Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variable</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variable</em>' reference.
	 * @see #setVariable(Declaration)
	 * @see org.caltoopia.ir.IrPackage#getVariableExpression_Variable()
	 * @model
	 * @generated
	 */
	Declaration getVariable();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.VariableExpression#getVariable <em>Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variable</em>' reference.
	 * @see #getVariable()
	 * @generated
	 */
	void setVariable(Declaration value);

	/**
	 * Returns the value of the '<em><b>Index</b></em>' reference list.
	 * The list contents are of type {@link org.caltoopia.ir.Expression}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Index</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Index</em>' reference list.
	 * @see org.caltoopia.ir.IrPackage#getVariableExpression_Index()
	 * @model
	 * @generated
	 */
	EList<Expression> getIndex();

	/**
	 * Returns the value of the '<em><b>Member</b></em>' reference list.
	 * The list contents are of type {@link org.caltoopia.ir.Member}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Member</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Member</em>' reference list.
	 * @see org.caltoopia.ir.IrPackage#getVariableExpression_Member()
	 * @model
	 * @generated
	 */
	EList<Member> getMember();

} // VariableExpression
