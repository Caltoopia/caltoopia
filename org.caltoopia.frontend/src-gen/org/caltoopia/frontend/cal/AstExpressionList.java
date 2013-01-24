/**
 * <copyright>
 * </copyright>
 *
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Expression List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstExpressionList#getExpressions <em>Expressions</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstExpressionList#getGenerators <em>Generators</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionList()
 * @model
 * @generated
 */
public interface AstExpressionList extends AstExpression
{
  /**
   * Returns the value of the '<em><b>Expressions</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstExpression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expressions</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expressions</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionList_Expressions()
   * @model containment="true"
   * @generated
   */
  EList<AstExpression> getExpressions();

  /**
   * Returns the value of the '<em><b>Generators</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstGenerator}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Generators</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Generators</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionList_Generators()
   * @model containment="true"
   * @generated
   */
  EList<AstGenerator> getGenerators();

} // AstExpressionList
