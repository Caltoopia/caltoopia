/**
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Type Definition Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstTypeDefinitionParameter#getValue <em>Value</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstTypeDefinitionParameter#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstTypeDefinitionParameter()
 * @model
 * @generated
 */
public interface AstTypeDefinitionParameter extends EObject
{
  /**
   * Returns the value of the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' containment reference.
   * @see #setValue(AstVariable)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstTypeDefinitionParameter_Value()
   * @model containment="true"
   * @generated
   */
  AstVariable getValue();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstTypeDefinitionParameter#getValue <em>Value</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' containment reference.
   * @see #getValue()
   * @generated
   */
  void setValue(AstVariable value);

  /**
   * Returns the value of the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' containment reference.
   * @see #setType(AstTypeName)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstTypeDefinitionParameter_Type()
   * @model containment="true"
   * @generated
   */
  AstTypeName getType();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstTypeDefinitionParameter#getType <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' containment reference.
   * @see #getType()
   * @generated
   */
  void setType(AstTypeName value);

} // AstTypeDefinitionParameter
