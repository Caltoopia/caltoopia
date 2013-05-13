/**
 */
package org.caltoopia.frontend.cal;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Expression If</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstExpressionIf#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstExpressionIf#getThen <em>Then</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstExpressionIf#getElse <em>Else</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionIf()
 * @model
 * @generated
 */
public interface AstExpressionIf extends AstExpression
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
   * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionIf_Condition()
   * @model containment="true"
   * @generated
   */
  AstExpression getCondition();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstExpressionIf#getCondition <em>Condition</em>}' containment reference.
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
   * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionIf_Then()
   * @model containment="true"
   * @generated
   */
  AstExpression getThen();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstExpressionIf#getThen <em>Then</em>}' containment reference.
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
   * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionIf_Else()
   * @model containment="true"
   * @generated
   */
  AstExpression getElse();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstExpressionIf#getElse <em>Else</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Else</em>' containment reference.
   * @see #getElse()
   * @generated
   */
  void setElse(AstExpression value);

} // AstExpressionIf
