/**
 * <copyright>
 * </copyright>
 *
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Transition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstTransition#getSource <em>Source</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstTransition#getTags <em>Tags</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstTransition#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstTransition()
 * @model
 * @generated
 */
public interface AstTransition extends EObject
{
  /**
   * Returns the value of the '<em><b>Source</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Source</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Source</em>' reference.
   * @see #setSource(AstState)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstTransition_Source()
   * @model
   * @generated
   */
  AstState getSource();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstTransition#getSource <em>Source</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source</em>' reference.
   * @see #getSource()
   * @generated
   */
  void setSource(AstState value);

  /**
   * Returns the value of the '<em><b>Tags</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstTag}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tags</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tags</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstTransition_Tags()
   * @model containment="true"
   * @generated
   */
  EList<AstTag> getTags();

  /**
   * Returns the value of the '<em><b>Target</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target</em>' reference.
   * @see #setTarget(AstState)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstTransition_Target()
   * @model
   * @generated
   */
  AstState getTarget();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstTransition#getTarget <em>Target</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target</em>' reference.
   * @see #getTarget()
   * @generated
   */
  void setTarget(AstState value);

} // AstTransition
