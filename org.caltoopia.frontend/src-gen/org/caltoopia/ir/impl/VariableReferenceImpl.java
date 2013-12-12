/**
 */
package org.caltoopia.ir.impl;

import java.util.Collection;

import org.caltoopia.ir.Expression;
import org.caltoopia.ir.IrPackage;
import org.caltoopia.ir.Member;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableReference;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variable Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.ir.impl.VariableReferenceImpl#getDeclaration <em>Declaration</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.VariableReferenceImpl#getIndex <em>Index</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.VariableReferenceImpl#getMember <em>Member</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.VariableReferenceImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VariableReferenceImpl extends NodeImpl implements VariableReference {
	/**
     * The cached value of the '{@link #getDeclaration() <em>Declaration</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDeclaration()
     * @generated
     * @ordered
     */
	protected Variable declaration;

	/**
     * The cached value of the '{@link #getIndex() <em>Index</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getIndex()
     * @generated
     * @ordered
     */
	protected EList<Expression> index;

	/**
     * The cached value of the '{@link #getMember() <em>Member</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getMember()
     * @generated
     * @ordered
     */
	protected EList<Member> member;

	/**
     * The cached value of the '{@link #getType() <em>Type</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
	protected Type type;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected VariableReferenceImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return IrPackage.Literals.VARIABLE_REFERENCE;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Variable getDeclaration() {
        if (declaration != null && declaration.eIsProxy()) {
            InternalEObject oldDeclaration = (InternalEObject)declaration;
            declaration = (Variable)eResolveProxy(oldDeclaration);
            if (declaration != oldDeclaration) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.VARIABLE_REFERENCE__DECLARATION, oldDeclaration, declaration));
            }
        }
        return declaration;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Variable basicGetDeclaration() {
        return declaration;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setDeclaration(Variable newDeclaration) {
        Variable oldDeclaration = declaration;
        declaration = newDeclaration;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.VARIABLE_REFERENCE__DECLARATION, oldDeclaration, declaration));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<Expression> getIndex() {
        if (index == null) {
            index = new EObjectResolvingEList<Expression>(Expression.class, this, IrPackage.VARIABLE_REFERENCE__INDEX);
        }
        return index;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<Member> getMember() {
        if (member == null) {
            member = new EObjectResolvingEList<Member>(Member.class, this, IrPackage.VARIABLE_REFERENCE__MEMBER);
        }
        return member;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Type getType() {
        if (type != null && type.eIsProxy()) {
            InternalEObject oldType = (InternalEObject)type;
            type = (Type)eResolveProxy(oldType);
            if (type != oldType) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.VARIABLE_REFERENCE__TYPE, oldType, type));
            }
        }
        return type;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Type basicGetType() {
        return type;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setType(Type newType) {
        Type oldType = type;
        type = newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.VARIABLE_REFERENCE__TYPE, oldType, type));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case IrPackage.VARIABLE_REFERENCE__DECLARATION:
                if (resolve) return getDeclaration();
                return basicGetDeclaration();
            case IrPackage.VARIABLE_REFERENCE__INDEX:
                return getIndex();
            case IrPackage.VARIABLE_REFERENCE__MEMBER:
                return getMember();
            case IrPackage.VARIABLE_REFERENCE__TYPE:
                if (resolve) return getType();
                return basicGetType();
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
            case IrPackage.VARIABLE_REFERENCE__DECLARATION:
                setDeclaration((Variable)newValue);
                return;
            case IrPackage.VARIABLE_REFERENCE__INDEX:
                getIndex().clear();
                getIndex().addAll((Collection<? extends Expression>)newValue);
                return;
            case IrPackage.VARIABLE_REFERENCE__MEMBER:
                getMember().clear();
                getMember().addAll((Collection<? extends Member>)newValue);
                return;
            case IrPackage.VARIABLE_REFERENCE__TYPE:
                setType((Type)newValue);
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
            case IrPackage.VARIABLE_REFERENCE__DECLARATION:
                setDeclaration((Variable)null);
                return;
            case IrPackage.VARIABLE_REFERENCE__INDEX:
                getIndex().clear();
                return;
            case IrPackage.VARIABLE_REFERENCE__MEMBER:
                getMember().clear();
                return;
            case IrPackage.VARIABLE_REFERENCE__TYPE:
                setType((Type)null);
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
            case IrPackage.VARIABLE_REFERENCE__DECLARATION:
                return declaration != null;
            case IrPackage.VARIABLE_REFERENCE__INDEX:
                return index != null && !index.isEmpty();
            case IrPackage.VARIABLE_REFERENCE__MEMBER:
                return member != null && !member.isEmpty();
            case IrPackage.VARIABLE_REFERENCE__TYPE:
                return type != null;
        }
        return super.eIsSet(featureID);
    }

} //VariableReferenceImpl
