/**
 */
package org.caltoopia.frontend.cal;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Expression Boolean</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstExpressionBoolean#isValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionBoolean()
 * @model
 * @generated
 */
public interface AstExpressionBoolean extends AstExpressionLiteral, AstPatternExpressionLiteral
{
  /**
   * Returns the value of the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' attribute.
   * @see #setValue(boolean)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionBoolean_Value()
   * @model
   * @generated
   */
  boolean isValue();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstExpressionBoolean#isValue <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' attribute.
   * @see #isValue()
   * @generated
   */
  void setValue(boolean value);

} // AstExpressionBoolean
