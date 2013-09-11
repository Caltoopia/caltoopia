/**
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Expression Alternative</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstExpressionAlternative#getPattern <em>Pattern</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstExpressionAlternative#getGuards <em>Guards</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstExpressionAlternative#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionAlternative()
 * @model
 * @generated
 */
public interface AstExpressionAlternative extends EObject
{
  /**
   * Returns the value of the '<em><b>Pattern</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Pattern</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Pattern</em>' containment reference.
   * @see #setPattern(AstPattern)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionAlternative_Pattern()
   * @model containment="true"
   * @generated
   */
  AstPattern getPattern();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstExpressionAlternative#getPattern <em>Pattern</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Pattern</em>' containment reference.
   * @see #getPattern()
   * @generated
   */
  void setPattern(AstPattern value);

  /**
   * Returns the value of the '<em><b>Guards</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstExpression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Guards</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Guards</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionAlternative_Guards()
   * @model containment="true"
   * @generated
   */
  EList<AstExpression> getGuards();

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
   * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionAlternative_Expression()
   * @model containment="true"
   * @generated
   */
  AstExpression getExpression();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstExpressionAlternative#getExpression <em>Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expression</em>' containment reference.
   * @see #getExpression()
   * @generated
   */
  void setExpression(AstExpression value);

} // AstExpressionAlternative
