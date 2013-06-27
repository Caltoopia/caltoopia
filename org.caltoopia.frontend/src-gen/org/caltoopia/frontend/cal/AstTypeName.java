/**
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Type Name</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstTypeName#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstTypeName#getConstructor <em>Constructor</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstTypeName()
 * @model
 * @generated
 */
public interface AstTypeName extends AstVariable
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
   * @see org.caltoopia.frontend.cal.CalPackage#getAstTypeName_Parameters()
   * @model containment="true"
   * @generated
   */
  EList<AstTypeDefinitionParameter> getParameters();

  /**
   * Returns the value of the '<em><b>Constructor</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstConstructor}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Constructor</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Constructor</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstTypeName_Constructor()
   * @model containment="true"
   * @generated
   */
  EList<AstConstructor> getConstructor();

} // AstTypeName
