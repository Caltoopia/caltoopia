/**
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Type User</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstTypeUser#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstTypeUser#getTuples <em>Tuples</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstTypeUser()
 * @model
 * @generated
 */
public interface AstTypeUser extends AstVariable
{
  /**
   * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstTypeDefinitionParameter}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameters</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstTypeUser_Parameters()
   * @model containment="true"
   * @generated
   */
  EList<AstTypeDefinitionParameter> getParameters();

  /**
   * Returns the value of the '<em><b>Tuples</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstTaggedTuple}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tuples</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tuples</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstTypeUser_Tuples()
   * @model containment="true"
   * @generated
   */
  EList<AstTaggedTuple> getTuples();

} // AstTypeUser
