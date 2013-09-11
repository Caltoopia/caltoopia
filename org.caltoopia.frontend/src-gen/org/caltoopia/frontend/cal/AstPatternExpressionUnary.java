/**
 */
package org.caltoopia.frontend.cal;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Pattern Expression Unary</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstPatternExpressionUnary#getUnaryOperator <em>Unary Operator</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstPatternExpressionUnary#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstPatternExpressionUnary()
 * @model
 * @generated
 */
public interface AstPatternExpressionUnary extends AstExpression
{
  /**
   * Returns the value of the '<em><b>Unary Operator</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Unary Operator</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Unary Operator</em>' attribute.
   * @see #setUnaryOperator(String)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstPatternExpressionUnary_UnaryOperator()
   * @model
   * @generated
   */
  String getUnaryOperator();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstPatternExpressionUnary#getUnaryOperator <em>Unary Operator</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Unary Operator</em>' attribute.
   * @see #getUnaryOperator()
   * @generated
   */
  void setUnaryOperator(String value);

  /**
   * Returns the value of the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expression</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expression</em>' containment reference.
   * @see #setExpression(AstExpression)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstPatternExpressionUnary_Expression()
   * @model containment="true"
   * @generated
   */
  AstExpression getExpression();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstPatternExpressionUnary#getExpression <em>Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expression</em>' containment reference.
   * @see #getExpression()
   * @generated
   */
  void setExpression(AstExpression value);

} // AstPatternExpressionUnary
