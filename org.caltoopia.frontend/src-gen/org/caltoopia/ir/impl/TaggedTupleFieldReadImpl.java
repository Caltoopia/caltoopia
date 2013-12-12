/**
 */
package org.caltoopia.ir.impl;

import org.caltoopia.ir.Expression;
import org.caltoopia.ir.IrPackage;
import org.caltoopia.ir.TaggedTupleFieldRead;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tagged Tuple Field Read</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.ir.impl.TaggedTupleFieldReadImpl#getTag <em>Tag</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.TaggedTupleFieldReadImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.TaggedTupleFieldReadImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TaggedTupleFieldReadImpl extends ExpressionImpl implements TaggedTupleFieldRead {
	/**
     * The default value of the '{@link #getTag() <em>Tag</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTag()
     * @generated
     * @ordered
     */
	protected static final String TAG_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getTag() <em>Tag</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTag()
     * @generated
     * @ordered
     */
	protected String tag = TAG_EDEFAULT;

	/**
     * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getLabel()
     * @generated
     * @ordered
     */
	protected static final String LABEL_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getLabel()
     * @generated
     * @ordered
     */
	protected String label = LABEL_EDEFAULT;

	/**
     * The cached value of the '{@link #getValue() <em>Value</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getValue()
     * @generated
     * @ordered
     */
	protected Expression value;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected TaggedTupleFieldReadImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return IrPackage.Literals.TAGGED_TUPLE_FIELD_READ;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getTag() {
        return tag;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setTag(String newTag) {
        String oldTag = tag;
        tag = newTag;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.TAGGED_TUPLE_FIELD_READ__TAG, oldTag, tag));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getLabel() {
        return label;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setLabel(String newLabel) {
        String oldLabel = label;
        label = newLabel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.TAGGED_TUPLE_FIELD_READ__LABEL, oldLabel, label));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Expression getValue() {
        if (value != null && value.eIsProxy()) {
            InternalEObject oldValue = (InternalEObject)value;
            value = (Expression)eResolveProxy(oldValue);
            if (value != oldValue) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.TAGGED_TUPLE_FIELD_READ__VALUE, oldValue, value));
            }
        }
        return value;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Expression basicGetValue() {
        return value;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setValue(Expression newValue) {
        Expression oldValue = value;
        value = newValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.TAGGED_TUPLE_FIELD_READ__VALUE, oldValue, value));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case IrPackage.TAGGED_TUPLE_FIELD_READ__TAG:
                return getTag();
            case IrPackage.TAGGED_TUPLE_FIELD_READ__LABEL:
                return getLabel();
            case IrPackage.TAGGED_TUPLE_FIELD_READ__VALUE:
                if (resolve) return getValue();
                return basicGetValue();
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
            case IrPackage.TAGGED_TUPLE_FIELD_READ__TAG:
                setTag((String)newValue);
                return;
            case IrPackage.TAGGED_TUPLE_FIELD_READ__LABEL:
                setLabel((String)newValue);
                return;
            case IrPackage.TAGGED_TUPLE_FIELD_READ__VALUE:
                setValue((Expression)newValue);
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
            case IrPackage.TAGGED_TUPLE_FIELD_READ__TAG:
                setTag(TAG_EDEFAULT);
                return;
            case IrPackage.TAGGED_TUPLE_FIELD_READ__LABEL:
                setLabel(LABEL_EDEFAULT);
                return;
            case IrPackage.TAGGED_TUPLE_FIELD_READ__VALUE:
                setValue((Expression)null);
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
            case IrPackage.TAGGED_TUPLE_FIELD_READ__TAG:
                return TAG_EDEFAULT == null ? tag != null : !TAG_EDEFAULT.equals(tag);
            case IrPackage.TAGGED_TUPLE_FIELD_READ__LABEL:
                return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
            case IrPackage.TAGGED_TUPLE_FIELD_READ__VALUE:
                return value != null;
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
        result.append(" (tag: ");
        result.append(tag);
        result.append(", label: ");
        result.append(label);
        result.append(')');
        return result.toString();
    }

} //TaggedTupleFieldReadImpl
