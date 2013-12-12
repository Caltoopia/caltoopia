/**
 */
package org.caltoopia.ir.impl;

import java.util.Collection;

import org.caltoopia.ir.ActorInstance;
import org.caltoopia.ir.Connection;
import org.caltoopia.ir.IrPackage;
import org.caltoopia.ir.PortInstance;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Port Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.ir.impl.PortInstanceImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.PortInstanceImpl#getConnections <em>Connections</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.PortInstanceImpl#getActor <em>Actor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PortInstanceImpl extends NodeImpl implements PortInstance {
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
     * The cached value of the '{@link #getConnections() <em>Connections</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getConnections()
     * @generated
     * @ordered
     */
	protected EList<Connection> connections;

	/**
     * The cached value of the '{@link #getActor() <em>Actor</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getActor()
     * @generated
     * @ordered
     */
	protected ActorInstance actor;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected PortInstanceImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return IrPackage.Literals.PORT_INSTANCE;
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
            eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.PORT_INSTANCE__NAME, oldName, name));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<Connection> getConnections() {
        if (connections == null) {
            connections = new EObjectResolvingEList<Connection>(Connection.class, this, IrPackage.PORT_INSTANCE__CONNECTIONS);
        }
        return connections;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ActorInstance getActor() {
        if (actor != null && actor.eIsProxy()) {
            InternalEObject oldActor = (InternalEObject)actor;
            actor = (ActorInstance)eResolveProxy(oldActor);
            if (actor != oldActor) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.PORT_INSTANCE__ACTOR, oldActor, actor));
            }
        }
        return actor;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ActorInstance basicGetActor() {
        return actor;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setActor(ActorInstance newActor) {
        ActorInstance oldActor = actor;
        actor = newActor;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.PORT_INSTANCE__ACTOR, oldActor, actor));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case IrPackage.PORT_INSTANCE__NAME:
                return getName();
            case IrPackage.PORT_INSTANCE__CONNECTIONS:
                return getConnections();
            case IrPackage.PORT_INSTANCE__ACTOR:
                if (resolve) return getActor();
                return basicGetActor();
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
            case IrPackage.PORT_INSTANCE__NAME:
                setName((String)newValue);
                return;
            case IrPackage.PORT_INSTANCE__CONNECTIONS:
                getConnections().clear();
                getConnections().addAll((Collection<? extends Connection>)newValue);
                return;
            case IrPackage.PORT_INSTANCE__ACTOR:
                setActor((ActorInstance)newValue);
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
            case IrPackage.PORT_INSTANCE__NAME:
                setName(NAME_EDEFAULT);
                return;
            case IrPackage.PORT_INSTANCE__CONNECTIONS:
                getConnections().clear();
                return;
            case IrPackage.PORT_INSTANCE__ACTOR:
                setActor((ActorInstance)null);
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
            case IrPackage.PORT_INSTANCE__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case IrPackage.PORT_INSTANCE__CONNECTIONS:
                return connections != null && !connections.isEmpty();
            case IrPackage.PORT_INSTANCE__ACTOR:
                return actor != null;
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
        result.append(')');
        return result.toString();
    }

} //PortInstanceImpl
