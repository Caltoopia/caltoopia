/**
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Output Pattern</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstOutputPattern#getPort <em>Port</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstOutputPattern#getValues <em>Values</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstOutputPattern#getRepeat <em>Repeat</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstOutputPattern()
 * @model
 * @generated
 */
public interface AstOutputPattern extends EObject
{
  /**
   * Returns the value of the '<em><b>Port</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Port</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Port</em>' reference.
   * @see #setPort(AstPort)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstOutputPattern_Port()
   * @model
   * @generated
   */
  AstPort getPort();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstOutputPattern#getPort <em>Port</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Port</em>' reference.
   * @see #getPort()
   * @generated
   */
  void setPort(AstPort value);

  /**
   * Returns the value of the '<em><b>Values</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstExpression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Values</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Values</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstOutputPattern_Values()
   * @model containment="true"
   * @generated
   */
  EList<AstExpression> getValues();

  /**
   * Returns the value of the '<em><b>Repeat</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Repeat</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Repeat</em>' containment reference.
   * @see #setRepeat(AstExpression)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstOutputPattern_Repeat()
   * @model containment="true"
   * @generated
   */
  AstExpression getRepeat();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstOutputPattern#getRepeat <em>Repeat</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Repeat</em>' containment reference.
   * @see #getRepeat()
   * @generated
   */
  void setRepeat(AstExpression value);

} // AstOutputPattern
