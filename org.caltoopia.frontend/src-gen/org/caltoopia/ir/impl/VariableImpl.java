/**
 */
package org.caltoopia.ir.impl;

import org.caltoopia.ir.Expression;
import org.caltoopia.ir.IrPackage;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.Variable;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.ir.impl.VariableImpl#getInitValue <em>Init Value</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.VariableImpl#isConstant <em>Constant</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.VariableImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.VariableImpl#isParameter <em>Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VariableImpl extends DeclarationImpl implements Variable {
	/**
     * The cached value of the '{@link #getInitValue() <em>Init Value</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getInitValue()
     * @generated
     * @ordered
     */
	protected Expression initValue;

	/**
     * The default value of the '{@link #isConstant() <em>Constant</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isConstant()
     * @generated
     * @ordered
     */
	protected static final boolean CONSTANT_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isConstant() <em>Constant</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isConstant()
     * @generated
     * @ordered
     */
	protected boolean constant = CONSTANT_EDEFAULT;

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
     * The default value of the '{@link #isParameter() <em>Parameter</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isParameter()
     * @generated
     * @ordered
     */
	protected static final boolean PARAMETER_EDEFAULT = false;

	/**
     * The cached value of the '{@link #isParameter() <em>Parameter</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #isParameter()
     * @generated
     * @ordered
     */
	protected boolean parameter = PARAMETER_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected VariableImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return IrPackage.Literals.VARIABLE;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Expression getInitValue() {
        if (initValue != null && initValue.eIsProxy()) {
            InternalEObject oldInitValue = (InternalEObject)initValue;
            initValue = (Expression)eResolveProxy(oldInitValue);
            if (initValue != oldInitValue) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.VARIABLE__INIT_VALUE, oldInitValue, initValue));
            }
        }
        return initValue;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Expression basicGetInitValue() {
        return initValue;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setInitValue(Expression newInitValue) {
        Expression oldInitValue = initValue;
        initValue = newInitValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.VARIABLE__INIT_VALUE, oldInitValue, initValue));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean isConstant() {
        return constant;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setConstant(boolean newConstant) {
        boolean oldConstant = constant;
        constant = newConstant;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.VARIABLE__CONSTANT, oldConstant, constant));
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.VARIABLE__TYPE, oldType, type));
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
            eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.VARIABLE__TYPE, oldType, type));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean isParameter() {
        return parameter;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setParameter(boolean newParameter) {
        boolean oldParameter = parameter;
        parameter = newParameter;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.VARIABLE__PARAMETER, oldParameter, parameter));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case IrPackage.VARIABLE__INIT_VALUE:
                if (resolve) return getInitValue();
                return basicGetInitValue();
            case IrPackage.VARIABLE__CONSTANT:
                return isConstant();
            case IrPackage.VARIABLE__TYPE:
                if (resolve) return getType();
                return basicGetType();
            case IrPackage.VARIABLE__PARAMETER:
                return isParameter();
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
            case IrPackage.VARIABLE__INIT_VALUE:
                setInitValue((Expression)newValue);
                return;
            case IrPackage.VARIABLE__CONSTANT:
                setConstant((Boolean)newValue);
                return;
            case IrPackage.VARIABLE__TYPE:
                setType((Type)newValue);
                return;
            case IrPackage.VARIABLE__PARAMETER:
                setParameter((Boolean)newValue);
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
            case IrPackage.VARIABLE__INIT_VALUE:
                setInitValue((Expression)null);
                return;
            case IrPackage.VARIABLE__CONSTANT:
                setConstant(CONSTANT_EDEFAULT);
                return;
            case IrPackage.VARIABLE__TYPE:
                setType((Type)null);
                return;
            case IrPackage.VARIABLE__PARAMETER:
                setParameter(PARAMETER_EDEFAULT);
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
            case IrPackage.VARIABLE__INIT_VALUE:
                return initValue != null;
            case IrPackage.VARIABLE__CONSTANT:
                return constant != CONSTANT_EDEFAULT;
            case IrPackage.VARIABLE__TYPE:
                return type != null;
            case IrPackage.VARIABLE__PARAMETER:
                return parameter != PARAMETER_EDEFAULT;
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
        result.append(" (constant: ");
        result.append(constant);
        result.append(", parameter: ");
        result.append(parameter);
        result.append(')');
        return result.toString();
    }

} //VariableImpl
