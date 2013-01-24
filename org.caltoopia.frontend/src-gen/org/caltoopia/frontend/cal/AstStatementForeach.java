/**
 * <copyright>
 * </copyright>
 *
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Statement Foreach</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstStatementForeach#getGenerators <em>Generators</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstStatementForeach#getVariables <em>Variables</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstStatementForeach#getStatements <em>Statements</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstStatementForeach()
 * @model
 * @generated
 */
public interface AstStatementForeach extends AstStatement
{
  /**
   * Returns the value of the '<em><b>Generators</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstForeachGenerator}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Generators</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Generators</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstStatementForeach_Generators()
   * @model containment="true"
   * @generated
   */
  EList<AstForeachGenerator> getGenerators();

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
   * @see org.caltoopia.frontend.cal.CalPackage#getAstStatementForeach_Variables()
   * @model containment="true"
   * @generated
   */
  EList<AstVariable> getVariables();

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
   * @see org.caltoopia.frontend.cal.CalPackage#getAstStatementForeach_Statements()
   * @model containment="true"
   * @generated
   */
  EList<AstStatement> getStatements();

} // AstStatementForeach
