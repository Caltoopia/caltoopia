/**
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Statement Assign</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstStatementAssign#getTarget <em>Target</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstStatementAssign#getIndexes <em>Indexes</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstStatementAssign#getMember <em>Member</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstStatementAssign#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstStatementAssign()
 * @model
 * @generated
 */
public interface AstStatementAssign extends AstStatement
{
  /**
   * Returns the value of the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target</em>' containment reference.
   * @see #setTarget(AstVariableReference)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstStatementAssign_Target()
   * @model containment="true"
   * @generated
   */
  AstVariableReference getTarget();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstStatementAssign#getTarget <em>Target</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target</em>' containment reference.
   * @see #getTarget()
   * @generated
   */
  void setTarget(AstVariableReference value);

  /**
   * Returns the value of the '<em><b>Indexes</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstExpression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Indexes</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Indexes</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstStatementAssign_Indexes()
   * @model containment="true"
   * @generated
   */
  EList<AstExpression> getIndexes();

  /**
   * Returns the value of the '<em><b>Member</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstMemberAccess}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Member</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Member</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstStatementAssign_Member()
   * @model containment="true"
   * @generated
   */
  EList<AstMemberAccess> getMember();

  /**
   * Returns the value of the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' containment reference.
   * @see #setValue(AstExpression)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstStatementAssign_Value()
   * @model containment="true"
   * @generated
   */
  AstExpression getValue();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstStatementAssign#getValue <em>Value</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' containment reference.
   * @see #getValue()
   * @generated
   */
  void setValue(AstExpression value);

} // AstStatementAssign
