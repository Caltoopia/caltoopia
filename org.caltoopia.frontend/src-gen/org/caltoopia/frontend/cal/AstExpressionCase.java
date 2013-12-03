/**
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Expression Case</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstExpressionCase#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstExpressionCase#getCases <em>Cases</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstExpressionCase#getDefault <em>Default</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionCase()
 * @model
 * @generated
 */
public interface AstExpressionCase extends AstExpression
{
  /**
   * Returns the value of the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expression</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expression</em>' containment reference.
   * @see #setExpression(AstExpressionSymbolReference)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionCase_Expression()
   * @model containment="true"
   * @generated
   */
  AstExpressionSymbolReference getExpression();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstExpressionCase#getExpression <em>Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expression</em>' containment reference.
   * @see #getExpression()
   * @generated
   */
  void setExpression(AstExpressionSymbolReference value);

  /**
   * Returns the value of the '<em><b>Cases</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstExpressionAlternative}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Cases</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Cases</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionCase_Cases()
   * @model containment="true"
   * @generated
   */
  EList<AstExpressionAlternative> getCases();

  /**
   * Returns the value of the '<em><b>Default</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Default</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Default</em>' containment reference.
   * @see #setDefault(AstExpression)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionCase_Default()
   * @model containment="true"
   * @generated
   */
  AstExpression getDefault();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstExpressionCase#getDefault <em>Default</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Default</em>' containment reference.
   * @see #getDefault()
   * @generated
   */
  void setDefault(AstExpression value);

} // AstExpressionCase
