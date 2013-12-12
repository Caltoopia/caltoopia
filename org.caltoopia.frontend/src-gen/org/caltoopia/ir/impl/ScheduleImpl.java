/**
 */
package org.caltoopia.ir.impl;

import java.util.Collection;

import org.caltoopia.ir.Action;
import org.caltoopia.ir.IrPackage;
import org.caltoopia.ir.Schedule;
import org.caltoopia.ir.State;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Schedule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.ir.impl.ScheduleImpl#getStates <em>States</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.ScheduleImpl#getFreeRunners <em>Free Runners</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.ScheduleImpl#getPriorityGraph <em>Priority Graph</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.ScheduleImpl#getInitialState <em>Initial State</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScheduleImpl extends EObjectImpl implements Schedule {
	/**
     * The cached value of the '{@link #getStates() <em>States</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getStates()
     * @generated
     * @ordered
     */
	protected EList<State> states;

	/**
     * The cached value of the '{@link #getFreeRunners() <em>Free Runners</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getFreeRunners()
     * @generated
     * @ordered
     */
	protected EList<Action> freeRunners;

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
     * The cached value of the '{@link #getInitialState() <em>Initial State</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getInitialState()
     * @generated
     * @ordered
     */
	protected State initialState;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ScheduleImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return IrPackage.Literals.SCHEDULE;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<State> getStates() {
        if (states == null) {
            states = new EObjectResolvingEList<State>(State.class, this, IrPackage.SCHEDULE__STATES);
        }
        return states;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<Action> getFreeRunners() {
        if (freeRunners == null) {
            freeRunners = new EObjectResolvingEList<Action>(Action.class, this, IrPackage.SCHEDULE__FREE_RUNNERS);
        }
        return freeRunners;
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
            eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.SCHEDULE__PRIORITY_GRAPH, oldPriorityGraph, priorityGraph));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public State getInitialState() {
        if (initialState != null && initialState.eIsProxy()) {
            InternalEObject oldInitialState = (InternalEObject)initialState;
            initialState = (State)eResolveProxy(oldInitialState);
            if (initialState != oldInitialState) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.SCHEDULE__INITIAL_STATE, oldInitialState, initialState));
            }
        }
        return initialState;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public State basicGetInitialState() {
        return initialState;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setInitialState(State newInitialState) {
        State oldInitialState = initialState;
        initialState = newInitialState;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.SCHEDULE__INITIAL_STATE, oldInitialState, initialState));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case IrPackage.SCHEDULE__STATES:
                return getStates();
            case IrPackage.SCHEDULE__FREE_RUNNERS:
                return getFreeRunners();
            case IrPackage.SCHEDULE__PRIORITY_GRAPH:
                return getPriorityGraph();
            case IrPackage.SCHEDULE__INITIAL_STATE:
                if (resolve) return getInitialState();
                return basicGetInitialState();
        }
        return super.eGet(featureID, resolve, coreType);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case IrPackage.SCHEDULE__STATES:
                getStates().clear();
                getStates().addAll((Collection<? extends State>)newValue);
                return;
            case IrPackage.SCHEDULE__FREE_RUNNERS:
                getFreeRunners().clear();
                getFreeRunners().addAll((Collection<? extends Action>)newValue);
                return;
            case IrPackage.SCHEDULE__PRIORITY_GRAPH:
                setPriorityGraph(newValue);
                return;
            case IrPackage.SCHEDULE__INITIAL_STATE:
                setInitialState((State)newValue);
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
            case IrPackage.SCHEDULE__STATES:
                getStates().clear();
                return;
            case IrPackage.SCHEDULE__FREE_RUNNERS:
                getFreeRunners().clear();
                return;
            case IrPackage.SCHEDULE__PRIORITY_GRAPH:
                setPriorityGraph(PRIORITY_GRAPH_EDEFAULT);
                return;
            case IrPackage.SCHEDULE__INITIAL_STATE:
                setInitialState((State)null);
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
            case IrPackage.SCHEDULE__STATES:
                return states != null && !states.isEmpty();
            case IrPackage.SCHEDULE__FREE_RUNNERS:
                return freeRunners != null && !freeRunners.isEmpty();
            case IrPackage.SCHEDULE__PRIORITY_GRAPH:
                return PRIORITY_GRAPH_EDEFAULT == null ? priorityGraph != null : !PRIORITY_GRAPH_EDEFAULT.equals(priorityGraph);
            case IrPackage.SCHEDULE__INITIAL_STATE:
                return initialState != null;
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
        result.append(" (PriorityGraph: ");
        result.append(priorityGraph);
        result.append(')');
        return result.toString();
    }

} //ScheduleImpl
