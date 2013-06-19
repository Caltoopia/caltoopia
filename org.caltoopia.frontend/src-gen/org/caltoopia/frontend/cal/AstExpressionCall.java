/**
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Expression Call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstExpressionCall#getFunction <em>Function</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstExpressionCall#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionCall()
 * @model
 * @generated
 */
public interface AstExpressionCall extends AstExpression
{
  /**
   * Returns the value of the '<em><b>Function</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Function</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Function</em>' reference.
   * @see #setFunction(AstVariable)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionCall_Function()
   * @model
   * @generated
   */
  AstVariable getFunction();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstExpressionCall#getFunction <em>Function</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Function</em>' reference.
   * @see #getFunction()
   * @generated
   */
  void setFunction(AstVariable value);

  /**
   * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstExpression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameters</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionCall_Parameters()
   * @model containment="true"
   * @generated
   */
  EList<AstExpression> getParameters();

} // AstExpressionCall
