/**
 */
package org.caltoopia.ir.impl;

import org.caltoopia.ir.IrPackage;
import org.caltoopia.ir.Port;
import org.caltoopia.ir.PortInstance;
import org.caltoopia.ir.ToSink;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>To Sink</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.ir.impl.ToSinkImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.ToSinkImpl#getSink <em>Sink</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ToSinkImpl extends ConnectionImpl implements ToSink {
	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected PortInstance source;

	/**
	 * The cached value of the '{@link #getSink() <em>Sink</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSink()
	 * @generated
	 * @ordered
	 */
	protected Port sink;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ToSinkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IrPackage.Literals.TO_SINK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortInstance getSource() {
		if (source != null && source.eIsProxy()) {
			InternalEObject oldSource = (InternalEObject)source;
			source = (PortInstance)eResolveProxy(oldSource);
			if (source != oldSource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.TO_SINK__SOURCE, oldSource, source));
			}
		}
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortInstance basicGetSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(PortInstance newSource) {
		PortInstance oldSource = source;
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.TO_SINK__SOURCE, oldSource, source));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Port getSink() {
		if (sink != null && sink.eIsProxy()) {
			InternalEObject oldSink = (InternalEObject)sink;
			sink = (Port)eResolveProxy(oldSink);
			if (sink != oldSink) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.TO_SINK__SINK, oldSink, sink));
			}
		}
		return sink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Port basicGetSink() {
		return sink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSink(Port newSink) {
		Port oldSink = sink;
		sink = newSink;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.TO_SINK__SINK, oldSink, sink));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IrPackage.TO_SINK__SOURCE:
				if (resolve) return getSource();
				return basicGetSource();
			case IrPackage.TO_SINK__SINK:
				if (resolve) return getSink();
				return basicGetSink();
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
			case IrPackage.TO_SINK__SOURCE:
				setSource((PortInstance)newValue);
				return;
			case IrPackage.TO_SINK__SINK:
				setSink((Port)newValue);
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
			case IrPackage.TO_SINK__SOURCE:
				setSource((PortInstance)null);
				return;
			case IrPackage.TO_SINK__SINK:
				setSink((Port)null);
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
			case IrPackage.TO_SINK__SOURCE:
				return source != null;
			case IrPackage.TO_SINK__SINK:
				return sink != null;
		}
		return super.eIsSet(featureID);
	}

} //ToSinkImpl
