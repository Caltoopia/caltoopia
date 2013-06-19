/**
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Constructor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstConstructor#getMembers <em>Members</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstConstructor()
 * @model
 * @generated
 */
public interface AstConstructor extends AstVariable
{
  /**
   * Returns the value of the '<em><b>Members</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstVariable}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Members</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Members</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstConstructor_Members()
   * @model containment="true"
   * @generated
   */
  EList<AstVariable> getMembers();

} // AstConstructor
