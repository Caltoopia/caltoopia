/**
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Statement Case</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstStatementCase#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstStatementCase#getCases <em>Cases</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstStatementCase#getDefault <em>Default</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstStatementCase()
 * @model
 * @generated
 */
public interface AstStatementCase extends AstStatement
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
   * @see org.caltoopia.frontend.cal.CalPackage#getAstStatementCase_Expression()
   * @model containment="true"
   * @generated
   */
  AstExpressionSymbolReference getExpression();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstStatementCase#getExpression <em>Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expression</em>' containment reference.
   * @see #getExpression()
   * @generated
   */
  void setExpression(AstExpressionSymbolReference value);

  /**
   * Returns the value of the '<em><b>Cases</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstStatementAlternative}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Cases</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Cases</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstStatementCase_Cases()
   * @model containment="true"
   * @generated
   */
  EList<AstStatementAlternative> getCases();

  /**
   * Returns the value of the '<em><b>Default</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Default</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Default</em>' containment reference.
   * @see #setDefault(AstStatement)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstStatementCase_Default()
   * @model containment="true"
   * @generated
   */
  AstStatement getDefault();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstStatementCase#getDefault <em>Default</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Default</em>' containment reference.
   * @see #getDefault()
   * @generated
   */
  void setDefault(AstStatement value);

} // AstStatementCase
