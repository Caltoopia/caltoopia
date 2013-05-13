/**
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Schedule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstSchedule#getInitialState <em>Initial State</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstSchedule#getTransitions <em>Transitions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstSchedule()
 * @model
 * @generated
 */
public interface AstSchedule extends EObject
{
  /**
   * Returns the value of the '<em><b>Initial State</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Initial State</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Initial State</em>' reference.
   * @see #setInitialState(AstState)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstSchedule_InitialState()
   * @model
   * @generated
   */
  AstState getInitialState();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstSchedule#getInitialState <em>Initial State</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Initial State</em>' reference.
   * @see #getInitialState()
   * @generated
   */
  void setInitialState(AstState value);

  /**
   * Returns the value of the '<em><b>Transitions</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstTransition}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Transitions</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Transitions</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstSchedule_Transitions()
   * @model containment="true"
   * @generated
   */
  EList<AstTransition> getTransitions();

} // AstSchedule
