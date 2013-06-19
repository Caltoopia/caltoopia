/**
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstFunction#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstFunction#getVariables <em>Variables</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstFunction#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstFunction()
 * @model
 * @generated
 */
public interface AstFunction extends AstVariable, AstExternalFunction
{
  /**
   * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstVariable}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameters</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstFunction_Parameters()
   * @model containment="true"
   * @generated
   */
  EList<AstVariable> getParameters();

  /**
   * Returns the value of the '<em><b>Variables</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstVariable}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Variables</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Variables</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstFunction_Variables()
   * @model containment="true"
   * @generated
   */
  EList<AstVariable> getVariables();

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
   * @see org.caltoopia.frontend.cal.CalPackage#getAstFunction_Expression()
   * @model containment="true"
   * @generated
   */
  AstExpression getExpression();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstFunction#getExpression <em>Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expression</em>' containment reference.
   * @see #getExpression()
   * @generated
   */
  void setExpression(AstExpression value);

} // AstFunction
