/**
 */
package org.caltoopia.frontend.cal;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Expression Binary</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstExpressionBinary#getLeft <em>Left</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstExpressionBinary#getOperator <em>Operator</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstExpressionBinary#getRight <em>Right</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionBinary()
 * @model
 * @generated
 */
public interface AstExpressionBinary extends AstExpression
{
  /**
   * Returns the value of the '<em><b>Left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Left</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Left</em>' containment reference.
   * @see #setLeft(AstExpression)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionBinary_Left()
   * @model containment="true"
   * @generated
   */
  AstExpression getLeft();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstExpressionBinary#getLeft <em>Left</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Left</em>' containment reference.
   * @see #getLeft()
   * @generated
   */
  void setLeft(AstExpression value);

  /**
   * Returns the value of the '<em><b>Operator</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Operator</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Operator</em>' attribute.
   * @see #setOperator(String)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionBinary_Operator()
   * @model
   * @generated
   */
  String getOperator();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstExpressionBinary#getOperator <em>Operator</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Operator</em>' attribute.
   * @see #getOperator()
   * @generated
   */
  void setOperator(String value);

  /**
   * Returns the value of the '<em><b>Right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Right</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Right</em>' containment reference.
   * @see #setRight(AstExpression)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionBinary_Right()
   * @model containment="true"
   * @generated
   */
  AstExpression getRight();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstExpressionBinary#getRight <em>Right</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Right</em>' containment reference.
   * @see #getRight()
   * @generated
   */
  void setRight(AstExpression value);

} // AstExpressionBinary
