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
 *   <li>{@link org.caltoopia.frontend.cal.AstTypeUser#isDefinition <em>Definition</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstTypeUser#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstTypeUser#getTuples <em>Tuples</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstTypeUser#isVariable <em>Variable</em>}</li>
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
   * Returns the value of the '<em><b>Definition</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Definition</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Definition</em>' attribute.
   * @see #setDefinition(boolean)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstTypeUser_Definition()
   * @model
   * @generated
   */
  boolean isDefinition();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstTypeUser#isDefinition <em>Definition</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Definition</em>' attribute.
   * @see #isDefinition()
   * @generated
   */
  void setDefinition(boolean value);

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

  /**
   * Returns the value of the '<em><b>Variable</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Variable</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Variable</em>' attribute.
   * @see #setVariable(boolean)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstTypeUser_Variable()
   * @model
   * @generated
   */
  boolean isVariable();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstTypeUser#isVariable <em>Variable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Variable</em>' attribute.
   * @see #isVariable()
   * @generated
   */
  void setVariable(boolean value);

} // AstTypeUser
