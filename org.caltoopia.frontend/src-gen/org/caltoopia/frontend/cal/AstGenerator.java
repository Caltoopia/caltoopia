/**
 * <copyright>
 * </copyright>
 *
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Generator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstGenerator#getVariable <em>Variable</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstGenerator#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstGenerator()
 * @model
 * @generated
 */
public interface AstGenerator extends EObject
{
  /**
   * Returns the value of the '<em><b>Variable</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Variable</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Variable</em>' containment reference.
   * @see #setVariable(AstVariable)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstGenerator_Variable()
   * @model containment="true"
   * @generated
   */
  AstVariable getVariable();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstGenerator#getVariable <em>Variable</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Variable</em>' containment reference.
   * @see #getVariable()
   * @generated
   */
  void setVariable(AstVariable value);

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
   * @see org.caltoopia.frontend.cal.CalPackage#getAstGenerator_Expression()
   * @model containment="true"
   * @generated
   */
  AstExpression getExpression();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstGenerator#getExpression <em>Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expression</em>' containment reference.
   * @see #getExpression()
   * @generated
   */
  void setExpression(AstExpression value);

} // AstGenerator
