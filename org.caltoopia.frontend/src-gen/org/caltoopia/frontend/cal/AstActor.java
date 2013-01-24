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
 * A representation of the model object '<em><b>Ast Actor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstActor#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstActor#getInputs <em>Inputs</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstActor#getOutputs <em>Outputs</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstActor#getFunctions <em>Functions</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstActor#getProcedures <em>Procedures</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstActor#getActions <em>Actions</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstActor#getInitializes <em>Initializes</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstActor#getStateVariables <em>State Variables</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstActor#getSchedules <em>Schedules</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstActor#getPriorities <em>Priorities</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstActor()
 * @model
 * @generated
 */
public interface AstActor extends EObject
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
   * @see org.caltoopia.frontend.cal.CalPackage#getAstActor_Parameters()
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
   * @see org.caltoopia.frontend.cal.CalPackage#getAstActor_Inputs()
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
   * @see org.caltoopia.frontend.cal.CalPackage#getAstActor_Outputs()
   * @model containment="true"
   * @generated
   */
  EList<AstPort> getOutputs();

  /**
   * Returns the value of the '<em><b>Functions</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstFunction}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Functions</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Functions</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstActor_Functions()
   * @model containment="true"
   * @generated
   */
  EList<AstFunction> getFunctions();

  /**
   * Returns the value of the '<em><b>Procedures</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstProcedure}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Procedures</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Procedures</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstActor_Procedures()
   * @model containment="true"
   * @generated
   */
  EList<AstProcedure> getProcedures();

  /**
   * Returns the value of the '<em><b>Actions</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstAction}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Actions</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Actions</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstActor_Actions()
   * @model containment="true"
   * @generated
   */
  EList<AstAction> getActions();

  /**
   * Returns the value of the '<em><b>Initializes</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstAction}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Initializes</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Initializes</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstActor_Initializes()
   * @model containment="true"
   * @generated
   */
  EList<AstAction> getInitializes();

  /**
   * Returns the value of the '<em><b>State Variables</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstVariable}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>State Variables</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>State Variables</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstActor_StateVariables()
   * @model containment="true"
   * @generated
   */
  EList<AstVariable> getStateVariables();

  /**
   * Returns the value of the '<em><b>Schedules</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstSchedule}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Schedules</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Schedules</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstActor_Schedules()
   * @model containment="true"
   * @generated
   */
  EList<AstSchedule> getSchedules();

  /**
   * Returns the value of the '<em><b>Priorities</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstPriority}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Priorities</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Priorities</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstActor_Priorities()
   * @model containment="true"
   * @generated
   */
  EList<AstPriority> getPriorities();

} // AstActor
