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
 * A representation of the model object '<em><b>Ast External Actor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstExternalActor#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstExternalActor#getInputs <em>Inputs</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstExternalActor#getOutputs <em>Outputs</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstExternalActor()
 * @model
 * @generated
 */
public interface AstExternalActor extends EObject
{
  /**
   * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstVariable}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameters</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstExternalActor_Parameters()
   * @model containment="true"
   * @generated
   */
  EList<AstVariable> getParameters();

  /**
   * Returns the value of the '<em><b>Inputs</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstPort}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Inputs</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Inputs</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstExternalActor_Inputs()
   * @model containment="true"
   * @generated
   */
  EList<AstPort> getInputs();

  /**
   * Returns the value of the '<em><b>Outputs</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstPort}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Outputs</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Outputs</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstExternalActor_Outputs()
   * @model containment="true"
   * @generated
   */
  EList<AstPort> getOutputs();

} // AstExternalActor
