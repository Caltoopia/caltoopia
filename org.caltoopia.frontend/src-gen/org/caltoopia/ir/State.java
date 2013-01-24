/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.caltoopia.ir;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.State#getName <em>Name</em>}</li>
 *   <li>{@link org.caltoopia.ir.State#getPriorityGraph <em>Priority Graph</em>}</li>
 *   <li>{@link org.caltoopia.ir.State#getAction2TargetMap <em>Action2 Target Map</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getState()
 * @model
 * @generated
 */
public interface State extends EObject {
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
	 * @see org.caltoopia.ir.IrPackage#getState_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.State#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

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
	 * @see org.caltoopia.ir.IrPackage#getState_PriorityGraph()
	 * @model
	 * @generated
	 */
	Object getPriorityGraph();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.State#getPriorityGraph <em>Priority Graph</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Priority Graph</em>' attribute.
	 * @see #getPriorityGraph()
	 * @generated
	 */
	void setPriorityGraph(Object value);

	/**
	 * Returns the value of the '<em><b>Action2 Target Map</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action2 Target Map</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action2 Target Map</em>' attribute.
	 * @see #setAction2TargetMap(Object)
	 * @see org.caltoopia.ir.IrPackage#getState_Action2TargetMap()
	 * @model
	 * @generated
	 */
	Object getAction2TargetMap();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.State#getAction2TargetMap <em>Action2 Target Map</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action2 Target Map</em>' attribute.
	 * @see #getAction2TargetMap()
	 * @generated
	 */
	void setAction2TargetMap(Object value);

} // State
