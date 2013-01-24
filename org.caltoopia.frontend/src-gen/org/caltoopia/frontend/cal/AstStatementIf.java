/**
 * <copyright>
 * </copyright>
 *
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Statement If</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstStatementIf#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstStatementIf#getThen <em>Then</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstStatementIf#getElse <em>Else</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstStatementIf()
 * @model
 * @generated
 */
public interface AstStatementIf extends AstStatement
{
  /**
   * Returns the value of the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Condition</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Condition</em>' containment reference.
   * @see #setCondition(AstExpression)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstStatementIf_Condition()
   * @model containment="true"
   * @generated
   */
  AstExpression getCondition();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstStatementIf#getCondition <em>Condition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Condition</em>' containment reference.
   * @see #getCondition()
   * @generated
   */
  void setCondition(AstExpression value);

  /**
   * Returns the value of the '<em><b>Then</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstStatement}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Then</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Then</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstStatementIf_Then()
   * @model containment="true"
   * @generated
   */
  EList<AstStatement> getThen();

  /**
   * Returns the value of the '<em><b>Else</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstStatement}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Else</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Else</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstStatementIf_Else()
   * @model containment="true"
   * @generated
   */
  EList<AstStatement> getElse();

} // AstStatementIf
