/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.caltoopia.ir;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>If Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.IfStatement#getThenBlock <em>Then Block</em>}</li>
 *   <li>{@link org.caltoopia.ir.IfStatement#getElseBlock <em>Else Block</em>}</li>
 *   <li>{@link org.caltoopia.ir.IfStatement#getCondition <em>Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getIfStatement()
 * @model
 * @generated
 */
public interface IfStatement extends Statement {
	/**
	 * Returns the value of the '<em><b>Then Block</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Then Block</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Then Block</em>' reference.
	 * @see #setThenBlock(Block)
	 * @see org.caltoopia.ir.IrPackage#getIfStatement_ThenBlock()
	 * @model
	 * @generated
	 */
	Block getThenBlock();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.IfStatement#getThenBlock <em>Then Block</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Then Block</em>' reference.
	 * @see #getThenBlock()
	 * @generated
	 */
	void setThenBlock(Block value);

	/**
	 * Returns the value of the '<em><b>Else Block</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Else Block</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Else Block</em>' reference.
	 * @see #setElseBlock(Block)
	 * @see org.caltoopia.ir.IrPackage#getIfStatement_ElseBlock()
	 * @model
	 * @generated
	 */
	Block getElseBlock();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.IfStatement#getElseBlock <em>Else Block</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Else Block</em>' reference.
	 * @see #getElseBlock()
	 * @generated
	 */
	void setElseBlock(Block value);

	/**
	 * Returns the value of the '<em><b>Condition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' reference.
	 * @see #setCondition(Expression)
	 * @see org.caltoopia.ir.IrPackage#getIfStatement_Condition()
	 * @model
	 * @generated
	 */
	Expression getCondition();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.IfStatement#getCondition <em>Condition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' reference.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(Expression value);

} // IfStatement
