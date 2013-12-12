/**
 */
package org.caltoopia.ir.impl;

import java.util.Collection;

import org.caltoopia.ir.Annotation;
import org.caltoopia.ir.IrPackage;
import org.caltoopia.ir.Node;
import org.caltoopia.ir.TaggedTuple;
import org.caltoopia.ir.TypeTuple;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Tuple</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.ir.impl.TypeTupleImpl#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.TypeTupleImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.TypeTupleImpl#getTaggedTuples <em>Tagged Tuples</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TypeTupleImpl extends TypeImpl implements TypeTuple {
	/**
     * The cached value of the '{@link #getAnnotations() <em>Annotations</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAnnotations()
     * @generated
     * @ordered
     */
	protected EList<Annotation> annotations;

	/**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
	protected static final String ID_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
	protected String id = ID_EDEFAULT;

	/**
     * The cached value of the '{@link #getTaggedTuples() <em>Tagged Tuples</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTaggedTuples()
     * @generated
     * @ordered
     */
	protected EList<TaggedTuple> taggedTuples;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected TypeTupleImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return IrPackage.Literals.TYPE_TUPLE;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<Annotation> getAnnotations() {
        if (annotations == null) {
            annotations = new EObjectResolvingEList<Annotation>(Annotation.class, this, IrPackage.TYPE_TUPLE__ANNOTATIONS);
        }
        return annotations;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getId() {
        return id;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setId(String newId) {
        String oldId = id;
        id = newId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.TYPE_TUPLE__ID, oldId, id));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<TaggedTuple> getTaggedTuples() {
        if (taggedTuples == null) {
            taggedTuples = new EObjectResolvingEList<TaggedTuple>(TaggedTuple.class, this, IrPackage.TYPE_TUPLE__TAGGED_TUPLES);
        }
        return taggedTuples;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case IrPackage.TYPE_TUPLE__ANNOTATIONS:
                return getAnnotations();
            case IrPackage.TYPE_TUPLE__ID:
                return getId();
            case IrPackage.TYPE_TUPLE__TAGGED_TUPLES:
                return getTaggedTuples();
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
            case IrPackage.TYPE_TUPLE__ANNOTATIONS:
                getAnnotations().clear();
                getAnnotations().addAll((Collection<? extends Annotation>)newValue);
                return;
            case IrPackage.TYPE_TUPLE__ID:
                setId((String)newValue);
                return;
            case IrPackage.TYPE_TUPLE__TAGGED_TUPLES:
                getTaggedTuples().clear();
                getTaggedTuples().addAll((Collection<? extends TaggedTuple>)newValue);
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
            case IrPackage.TYPE_TUPLE__ANNOTATIONS:
                getAnnotations().clear();
                return;
            case IrPackage.TYPE_TUPLE__ID:
                setId(ID_EDEFAULT);
                return;
            case IrPackage.TYPE_TUPLE__TAGGED_TUPLES:
                getTaggedTuples().clear();
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
            case IrPackage.TYPE_TUPLE__ANNOTATIONS:
                return annotations != null && !annotations.isEmpty();
            case IrPackage.TYPE_TUPLE__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case IrPackage.TYPE_TUPLE__TAGGED_TUPLES:
                return taggedTuples != null && !taggedTuples.isEmpty();
        }
        return super.eIsSet(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == Node.class) {
            switch (derivedFeatureID) {
                case IrPackage.TYPE_TUPLE__ANNOTATIONS: return IrPackage.NODE__ANNOTATIONS;
                case IrPackage.TYPE_TUPLE__ID: return IrPackage.NODE__ID;
                default: return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == Node.class) {
            switch (baseFeatureID) {
                case IrPackage.NODE__ANNOTATIONS: return IrPackage.TYPE_TUPLE__ANNOTATIONS;
                case IrPackage.NODE__ID: return IrPackage.TYPE_TUPLE__ID;
                default: return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
        result.append(" (id: ");
        result.append(id);
        result.append(')');
        return result.toString();
    }

} //TypeTupleImpl
