/**
 */
package org.caltoopia.ir.impl;

import java.util.Collection;

import org.caltoopia.ir.Expression;
import org.caltoopia.ir.IrPackage;
import org.caltoopia.ir.LambdaExpression;
import org.caltoopia.ir.Scope;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.Variable;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Lambda Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.ir.impl.LambdaExpressionImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.LambdaExpressionImpl#getContext <em>Context</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.LambdaExpressionImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.LambdaExpressionImpl#getBody <em>Body</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LambdaExpressionImpl extends ScopeImpl implements LambdaExpression {
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
     * The cached value of the '{@link #getContext() <em>Context</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getContext()
     * @generated
     * @ordered
     */
	protected Scope context;

	/**
     * The cached value of the '{@link #getParameters() <em>Parameters</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getParameters()
     * @generated
     * @ordered
     */
	protected EList<Variable> parameters;

	/**
     * The cached value of the '{@link #getBody() <em>Body</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getBody()
     * @generated
     * @ordered
     */
	protected Expression body;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected LambdaExpressionImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return IrPackage.Literals.LAMBDA_EXPRESSION;
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.LAMBDA_EXPRESSION__TYPE, oldType, type));
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
            eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.LAMBDA_EXPRESSION__TYPE, oldType, type));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Scope getContext() {
        if (context != null && context.eIsProxy()) {
            InternalEObject oldContext = (InternalEObject)context;
            context = (Scope)eResolveProxy(oldContext);
            if (context != oldContext) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.LAMBDA_EXPRESSION__CONTEXT, oldContext, context));
            }
        }
        return context;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Scope basicGetContext() {
        return context;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setContext(Scope newContext) {
        Scope oldContext = context;
        context = newContext;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.LAMBDA_EXPRESSION__CONTEXT, oldContext, context));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<Variable> getParameters() {
        if (parameters == null) {
            parameters = new EObjectResolvingEList<Variable>(Variable.class, this, IrPackage.LAMBDA_EXPRESSION__PARAMETERS);
        }
        return parameters;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Expression getBody() {
        if (body != null && body.eIsProxy()) {
            InternalEObject oldBody = (InternalEObject)body;
            body = (Expression)eResolveProxy(oldBody);
            if (body != oldBody) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.LAMBDA_EXPRESSION__BODY, oldBody, body));
            }
        }
        return body;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Expression basicGetBody() {
        return body;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setBody(Expression newBody) {
        Expression oldBody = body;
        body = newBody;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.LAMBDA_EXPRESSION__BODY, oldBody, body));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case IrPackage.LAMBDA_EXPRESSION__TYPE:
                if (resolve) return getType();
                return basicGetType();
            case IrPackage.LAMBDA_EXPRESSION__CONTEXT:
                if (resolve) return getContext();
                return basicGetContext();
            case IrPackage.LAMBDA_EXPRESSION__PARAMETERS:
                return getParameters();
            case IrPackage.LAMBDA_EXPRESSION__BODY:
                if (resolve) return getBody();
                return basicGetBody();
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
            case IrPackage.LAMBDA_EXPRESSION__TYPE:
                setType((Type)newValue);
                return;
            case IrPackage.LAMBDA_EXPRESSION__CONTEXT:
                setContext((Scope)newValue);
                return;
            case IrPackage.LAMBDA_EXPRESSION__PARAMETERS:
                getParameters().clear();
                getParameters().addAll((Collection<? extends Variable>)newValue);
                return;
            case IrPackage.LAMBDA_EXPRESSION__BODY:
                setBody((Expression)newValue);
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
            case IrPackage.LAMBDA_EXPRESSION__TYPE:
                setType((Type)null);
                return;
            case IrPackage.LAMBDA_EXPRESSION__CONTEXT:
                setContext((Scope)null);
                return;
            case IrPackage.LAMBDA_EXPRESSION__PARAMETERS:
                getParameters().clear();
                return;
            case IrPackage.LAMBDA_EXPRESSION__BODY:
                setBody((Expression)null);
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
            case IrPackage.LAMBDA_EXPRESSION__TYPE:
                return type != null;
            case IrPackage.LAMBDA_EXPRESSION__CONTEXT:
                return context != null;
            case IrPackage.LAMBDA_EXPRESSION__PARAMETERS:
                return parameters != null && !parameters.isEmpty();
            case IrPackage.LAMBDA_EXPRESSION__BODY:
                return body != null;
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
        if (baseClass == Expression.class) {
            switch (derivedFeatureID) {
                case IrPackage.LAMBDA_EXPRESSION__TYPE: return IrPackage.EXPRESSION__TYPE;
                case IrPackage.LAMBDA_EXPRESSION__CONTEXT: return IrPackage.EXPRESSION__CONTEXT;
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
        if (baseClass == Expression.class) {
            switch (baseFeatureID) {
                case IrPackage.EXPRESSION__TYPE: return IrPackage.LAMBDA_EXPRESSION__TYPE;
                case IrPackage.EXPRESSION__CONTEXT: return IrPackage.LAMBDA_EXPRESSION__CONTEXT;
                default: return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

} //LambdaExpressionImpl
