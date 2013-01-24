/**
 * <copyright>
 * </copyright>
 *
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Expression Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstExpressionVariable#getValue <em>Value</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstExpressionVariable#getIndexes <em>Indexes</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstExpressionVariable#getMember <em>Member</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionVariable()
 * @model
 * @generated
 */
public interface AstExpressionVariable extends AstExpression
{
  /**
   * Returns the value of the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' containment reference.
   * @see #setValue(AstVariableReference)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionVariable_Value()
   * @model containment="true"
   * @generated
   */
  AstVariableReference getValue();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstExpressionVariable#getValue <em>Value</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' containment reference.
   * @see #getValue()
   * @generated
   */
  void setValue(AstVariableReference value);

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
   * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionVariable_Indexes()
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
   * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionVariable_Member()
   * @model containment="true"
   * @generated
   */
  EList<AstMemberAccess> getMember();

} // AstExpressionVariable
