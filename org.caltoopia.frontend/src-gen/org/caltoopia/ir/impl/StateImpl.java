/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.caltoopia.ir.impl;

import org.caltoopia.ir.IrPackage;
import org.caltoopia.ir.State;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>State</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.ir.impl.StateImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.StateImpl#getPriorityGraph <em>Priority Graph</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.StateImpl#getAction2TargetMap <em>Action2 Target Map</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StateImpl extends EObjectImpl implements State {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getPriorityGraph() <em>Priority Graph</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriorityGraph()
	 * @generated
	 * @ordered
	 */
	protected static final Object PRIORITY_GRAPH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPriorityGraph() <em>Priority Graph</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriorityGraph()
	 * @generated
	 * @ordered
	 */
	protected Object priorityGraph = PRIORITY_GRAPH_EDEFAULT;

	/**
	 * The default value of the '{@link #getAction2TargetMap() <em>Action2 Target Map</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAction2TargetMap()
	 * @generated
	 * @ordered
	 */
	protected static final Object ACTION2_TARGET_MAP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAction2TargetMap() <em>Action2 Target Map</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAction2TargetMap()
	 * @generated
	 * @ordered
	 */
	protected Object action2TargetMap = ACTION2_TARGET_MAP_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IrPackage.Literals.STATE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.STATE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getPriorityGraph() {
		return priorityGraph;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPriorityGraph(Object newPriorityGraph) {
		Object oldPriorityGraph = priorityGraph;
		priorityGraph = newPriorityGraph;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.STATE__PRIORITY_GRAPH, oldPriorityGraph, priorityGraph));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getAction2TargetMap() {
		return action2TargetMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAction2TargetMap(Object newAction2TargetMap) {
		Object oldAction2TargetMap = action2TargetMap;
		action2TargetMap = newAction2TargetMap;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.STATE__ACTION2_TARGET_MAP, oldAction2TargetMap, action2TargetMap));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IrPackage.STATE__NAME:
				return getName();
			case IrPackage.STATE__PRIORITY_GRAPH:
				return getPriorityGraph();
			case IrPackage.STATE__ACTION2_TARGET_MAP:
				return getAction2TargetMap();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case IrPackage.STATE__NAME:
				setName((String)newValue);
				return;
			case IrPackage.STATE__PRIORITY_GRAPH:
				setPriorityGraph(newValue);
				return;
			case IrPackage.STATE__ACTION2_TARGET_MAP:
				setAction2TargetMap(newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case IrPackage.STATE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case IrPackage.STATE__PRIORITY_GRAPH:
				setPriorityGraph(PRIORITY_GRAPH_EDEFAULT);
				return;
			case IrPackage.STATE__ACTION2_TARGET_MAP:
				setAction2TargetMap(ACTION2_TARGET_MAP_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case IrPackage.STATE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case IrPackage.STATE__PRIORITY_GRAPH:
				return PRIORITY_GRAPH_EDEFAULT == null ? priorityGraph != null : !PRIORITY_GRAPH_EDEFAULT.equals(priorityGraph);
			case IrPackage.STATE__ACTION2_TARGET_MAP:
				return ACTION2_TARGET_MAP_EDEFAULT == null ? action2TargetMap != null : !ACTION2_TARGET_MAP_EDEFAULT.equals(action2TargetMap);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", PriorityGraph: ");
		result.append(priorityGraph);
		result.append(", Action2TargetMap: ");
		result.append(action2TargetMap);
		result.append(')');
		return result.toString();
	}

} //StateImpl
