/**
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Pattern Expression If</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstPatternExpressionIf#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstPatternExpressionIf#getThen <em>Then</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstPatternExpressionIf#getElse <em>Else</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstPatternExpressionIf()
 * @model
 * @generated
 */
public interface AstPatternExpressionIf extends EObject
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
   * @see org.caltoopia.frontend.cal.CalPackage#getAstPatternExpressionIf_Condition()
   * @model containment="true"
   * @generated
   */
  AstExpression getCondition();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstPatternExpressionIf#getCondition <em>Condition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Condition</em>' containment reference.
   * @see #getCondition()
   * @generated
   */
  void setCondition(AstExpression value);

  /**
   * Returns the value of the '<em><b>Then</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Then</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Then</em>' containment reference.
   * @see #setThen(AstExpression)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstPatternExpressionIf_Then()
   * @model containment="true"
   * @generated
   */
  AstExpression getThen();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstPatternExpressionIf#getThen <em>Then</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Then</em>' containment reference.
   * @see #getThen()
   * @generated
   */
  void setThen(AstExpression value);

  /**
   * Returns the value of the '<em><b>Else</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Else</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Else</em>' containment reference.
   * @see #setElse(AstExpression)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstPatternExpressionIf_Else()
   * @model containment="true"
   * @generated
   */
  AstExpression getElse();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstPatternExpressionIf#getElse <em>Else</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Else</em>' containment reference.
   * @see #getElse()
   * @generated
   */
  void setElse(AstExpression value);

} // AstPatternExpressionIf
