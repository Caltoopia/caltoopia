/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.caltoopia.ir;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Actor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.Actor#getActions <em>Actions</em>}</li>
 *   <li>{@link org.caltoopia.ir.Actor#getInitializers <em>Initializers</em>}</li>
 *   <li>{@link org.caltoopia.ir.Actor#getSchedule <em>Schedule</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getActor()
 * @model
 * @generated
 */
public interface Actor extends AbstractActor {
	/**
	 * Returns the value of the '<em><b>Actions</b></em>' reference list.
	 * The list contents are of type {@link org.caltoopia.ir.Action}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actions</em>' reference list.
	 * @see org.caltoopia.ir.IrPackage#getActor_Actions()
	 * @model
	 * @generated
	 */
	EList<Action> getActions();

	/**
	 * Returns the value of the '<em><b>Initializers</b></em>' reference list.
	 * The list contents are of type {@link org.caltoopia.ir.Action}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initializers</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initializers</em>' reference list.
	 * @see org.caltoopia.ir.IrPackage#getActor_Initializers()
	 * @model
	 * @generated
	 */
	EList<Action> getInitializers();

	/**
	 * Returns the value of the '<em><b>Schedule</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Schedule</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Schedule</em>' reference.
	 * @see #setSchedule(Schedule)
	 * @see org.caltoopia.ir.IrPackage#getActor_Schedule()
	 * @model
	 * @generated
	 */
	Schedule getSchedule();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.Actor#getSchedule <em>Schedule</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Schedule</em>' reference.
	 * @see #getSchedule()
	 * @generated
	 */
	void setSchedule(Schedule value);

} // Actor
