/**
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Network</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstNetwork#getVariables <em>Variables</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstNetwork#getInstances <em>Instances</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstNetwork#getStructure <em>Structure</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstNetwork()
 * @model
 * @generated
 */
public interface AstNetwork extends AstAbstractActor
{
  /**
   * Returns the value of the '<em><b>Variables</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstVariable}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Variables</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Variables</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstNetwork_Variables()
   * @model containment="true"
   * @generated
   */
  EList<AstVariable> getVariables();

  /**
   * Returns the value of the '<em><b>Instances</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstActorVariable}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Instances</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Instances</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstNetwork_Instances()
   * @model containment="true"
   * @generated
   */
  EList<AstActorVariable> getInstances();

  /**
   * Returns the value of the '<em><b>Structure</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Structure</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Structure</em>' containment reference.
   * @see #setStructure(AstStructure)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstNetwork_Structure()
   * @model containment="true"
   * @generated
   */
  AstStructure getStructure();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstNetwork#getStructure <em>Structure</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Structure</em>' containment reference.
   * @see #getStructure()
   * @generated
   */
  void setStructure(AstStructure value);

} // AstNetwork
