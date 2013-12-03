/**
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Tagged Tuple</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstTaggedTuple#getName <em>Name</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstTaggedTuple#getFields <em>Fields</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstTaggedTuple()
 * @model
 * @generated
 */
public interface AstTaggedTuple extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstTaggedTuple_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstTaggedTuple#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Fields</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstVariable}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fields</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fields</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstTaggedTuple_Fields()
   * @model containment="true"
   * @generated
   */
  EList<AstVariable> getFields();

} // AstTaggedTuple
