/**
 */
package org.caltoopia.ir.impl;

import java.util.Collection;

import org.caltoopia.ir.Action;
import org.caltoopia.ir.Actor;
import org.caltoopia.ir.IrPackage;
import org.caltoopia.ir.Schedule;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Actor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.ir.impl.ActorImpl#getActions <em>Actions</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.ActorImpl#getInitializers <em>Initializers</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.ActorImpl#getSchedule <em>Schedule</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActorImpl extends AbstractActorImpl implements Actor {
	/**
     * The cached value of the '{@link #getActions() <em>Actions</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getActions()
     * @generated
     * @ordered
     */
	protected EList<Action> actions;

	/**
     * The cached value of the '{@link #getInitializers() <em>Initializers</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getInitializers()
     * @generated
     * @ordered
     */
	protected EList<Action> initializers;

	/**
     * The cached value of the '{@link #getSchedule() <em>Schedule</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSchedule()
     * @generated
     * @ordered
     */
	protected Schedule schedule;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ActorImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return IrPackage.Literals.ACTOR;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<Action> getActions() {
        if (actions == null) {
            actions = new EObjectResolvingEList<Action>(Action.class, this, IrPackage.ACTOR__ACTIONS);
        }
        return actions;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<Action> getInitializers() {
        if (initializers == null) {
            initializers = new EObjectResolvingEList<Action>(Action.class, this, IrPackage.ACTOR__INITIALIZERS);
        }
        return initializers;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Schedule getSchedule() {
        if (schedule != null && schedule.eIsProxy()) {
            InternalEObject oldSchedule = (InternalEObject)schedule;
            schedule = (Schedule)eResolveProxy(oldSchedule);
            if (schedule != oldSchedule) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.ACTOR__SCHEDULE, oldSchedule, schedule));
            }
        }
        return schedule;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Schedule basicGetSchedule() {
        return schedule;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setSchedule(Schedule newSchedule) {
        Schedule oldSchedule = schedule;
        schedule = newSchedule;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.ACTOR__SCHEDULE, oldSchedule, schedule));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case IrPackage.ACTOR__ACTIONS:
                return getActions();
            case IrPackage.ACTOR__INITIALIZERS:
                return getInitializers();
            case IrPackage.ACTOR__SCHEDULE:
                if (resolve) return getSchedule();
                return basicGetSchedule();
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
            case IrPackage.ACTOR__ACTIONS:
                getActions().clear();
                getActions().addAll((Collection<? extends Action>)newValue);
                return;
            case IrPackage.ACTOR__INITIALIZERS:
                getInitializers().clear();
                getInitializers().addAll((Collection<? extends Action>)newValue);
                return;
            case IrPackage.ACTOR__SCHEDULE:
                setSchedule((Schedule)newValue);
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
            case IrPackage.ACTOR__ACTIONS:
                getActions().clear();
                return;
            case IrPackage.ACTOR__INITIALIZERS:
                getInitializers().clear();
                return;
            case IrPackage.ACTOR__SCHEDULE:
                setSchedule((Schedule)null);
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
            case IrPackage.ACTOR__ACTIONS:
                return actions != null && !actions.isEmpty();
            case IrPackage.ACTOR__INITIALIZERS:
                return initializers != null && !initializers.isEmpty();
            case IrPackage.ACTOR__SCHEDULE:
                return schedule != null;
        }
        return super.eIsSet(featureID);
    }

} //ActorImpl
