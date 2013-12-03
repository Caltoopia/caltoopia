/**
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Statement Alternative</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstStatementAlternative#getPattern <em>Pattern</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstStatementAlternative#getGuards <em>Guards</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstStatementAlternative#getStatements <em>Statements</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstStatementAlternative()
 * @model
 * @generated
 */
public interface AstStatementAlternative extends EObject
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
   * @see org.caltoopia.frontend.cal.CalPackage#getAstStatementAlternative_Pattern()
   * @model containment="true"
   * @generated
   */
  AstPattern getPattern();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstStatementAlternative#getPattern <em>Pattern</em>}' containment reference.
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
   * @see org.caltoopia.frontend.cal.CalPackage#getAstStatementAlternative_Guards()
   * @model containment="true"
   * @generated
   */
  EList<AstExpression> getGuards();

  /**
   * Returns the value of the '<em><b>Statements</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstStatement}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Statements</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Statements</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstStatementAlternative_Statements()
   * @model containment="true"
   * @generated
   */
  EList<AstStatement> getStatements();

} // AstStatementAlternative
