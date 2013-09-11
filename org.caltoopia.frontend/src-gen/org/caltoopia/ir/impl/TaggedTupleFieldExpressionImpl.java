/**
 */
package org.caltoopia.ir.impl;

import org.caltoopia.ir.IrPackage;
import org.caltoopia.ir.TaggedTupleFieldExpression;
import org.caltoopia.ir.TypeTuple;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tagged Tuple Field Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.ir.impl.TaggedTupleFieldExpressionImpl#getTupleType <em>Tuple Type</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.TaggedTupleFieldExpressionImpl#getTag <em>Tag</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.TaggedTupleFieldExpressionImpl#getLabel <em>Label</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TaggedTupleFieldExpressionImpl extends ExpressionImpl implements TaggedTupleFieldExpression {
	/**
	 * The cached value of the '{@link #getTupleType() <em>Tuple Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTupleType()
	 * @generated
	 * @ordered
	 */
	protected TypeTuple tupleType;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TaggedTupleFieldExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IrPackage.Literals.TAGGED_TUPLE_FIELD_EXPRESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeTuple getTupleType() {
		if (tupleType != null && tupleType.eIsProxy()) {
			InternalEObject oldTupleType = (InternalEObject)tupleType;
			tupleType = (TypeTuple)eResolveProxy(oldTupleType);
			if (tupleType != oldTupleType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.TAGGED_TUPLE_FIELD_EXPRESSION__TUPLE_TYPE, oldTupleType, tupleType));
			}
		}
		return tupleType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeTuple basicGetTupleType() {
		return tupleType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTupleType(TypeTuple newTupleType) {
		TypeTuple oldTupleType = tupleType;
		tupleType = newTupleType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.TAGGED_TUPLE_FIELD_EXPRESSION__TUPLE_TYPE, oldTupleType, tupleType));
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
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.TAGGED_TUPLE_FIELD_EXPRESSION__TAG, oldTag, tag));
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
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.TAGGED_TUPLE_FIELD_EXPRESSION__LABEL, oldLabel, label));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IrPackage.TAGGED_TUPLE_FIELD_EXPRESSION__TUPLE_TYPE:
				if (resolve) return getTupleType();
				return basicGetTupleType();
			case IrPackage.TAGGED_TUPLE_FIELD_EXPRESSION__TAG:
				return getTag();
			case IrPackage.TAGGED_TUPLE_FIELD_EXPRESSION__LABEL:
				return getLabel();
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
			case IrPackage.TAGGED_TUPLE_FIELD_EXPRESSION__TUPLE_TYPE:
				setTupleType((TypeTuple)newValue);
				return;
			case IrPackage.TAGGED_TUPLE_FIELD_EXPRESSION__TAG:
				setTag((String)newValue);
				return;
			case IrPackage.TAGGED_TUPLE_FIELD_EXPRESSION__LABEL:
				setLabel((String)newValue);
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
			case IrPackage.TAGGED_TUPLE_FIELD_EXPRESSION__TUPLE_TYPE:
				setTupleType((TypeTuple)null);
				return;
			case IrPackage.TAGGED_TUPLE_FIELD_EXPRESSION__TAG:
				setTag(TAG_EDEFAULT);
				return;
			case IrPackage.TAGGED_TUPLE_FIELD_EXPRESSION__LABEL:
				setLabel(LABEL_EDEFAULT);
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
			case IrPackage.TAGGED_TUPLE_FIELD_EXPRESSION__TUPLE_TYPE:
				return tupleType != null;
			case IrPackage.TAGGED_TUPLE_FIELD_EXPRESSION__TAG:
				return TAG_EDEFAULT == null ? tag != null : !TAG_EDEFAULT.equals(tag);
			case IrPackage.TAGGED_TUPLE_FIELD_EXPRESSION__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
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

} //TaggedTupleFieldExpressionImpl
