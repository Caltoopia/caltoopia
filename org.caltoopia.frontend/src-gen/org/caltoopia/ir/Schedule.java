/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.caltoopia.ir;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Schedule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.Schedule#getStates <em>States</em>}</li>
 *   <li>{@link org.caltoopia.ir.Schedule#getFreeRunners <em>Free Runners</em>}</li>
 *   <li>{@link org.caltoopia.ir.Schedule#getPriorityGraph <em>Priority Graph</em>}</li>
 *   <li>{@link org.caltoopia.ir.Schedule#getInitialState <em>Initial State</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getSchedule()
 * @model
 * @generated
 */
public interface Schedule extends EObject {
	/**
	 * Returns the value of the '<em><b>States</b></em>' reference list.
	 * The list contents are of type {@link org.caltoopia.ir.State}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>States</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>States</em>' reference list.
	 * @see org.caltoopia.ir.IrPackage#getSchedule_States()
	 * @model
	 * @generated
	 */
	EList<State> getStates();

	/**
	 * Returns the value of the '<em><b>Free Runners</b></em>' reference list.
	 * The list contents are of type {@link org.caltoopia.ir.Action}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Free Runners</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Free Runners</em>' reference list.
	 * @see org.caltoopia.ir.IrPackage#getSchedule_FreeRunners()
	 * @model
	 * @generated
	 */
	EList<Action> getFreeRunners();

	/**
	 * Returns the value of the '<em><b>Priority Graph</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Priority Graph</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Priority Graph</em>' attribute.
	 * @see #setPriorityGraph(Object)
	 * @see org.caltoopia.ir.IrPackage#getSchedule_PriorityGraph()
	 * @model
	 * @generated
	 */
	Object getPriorityGraph();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.Schedule#getPriorityGraph <em>Priority Graph</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Priority Graph</em>' attribute.
	 * @see #getPriorityGraph()
	 * @generated
	 */
	void setPriorityGraph(Object value);

	/**
	 * Returns the value of the '<em><b>Initial State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initial State</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initial State</em>' reference.
	 * @see #setInitialState(State)
	 * @see org.caltoopia.ir.IrPackage#getSchedule_InitialState()
	 * @model
	 * @generated
	 */
	State getInitialState();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.Schedule#getInitialState <em>Initial State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initial State</em>' reference.
	 * @see #getInitialState()
	 * @generated
	 */
	void setInitialState(State value);

} // Schedule
