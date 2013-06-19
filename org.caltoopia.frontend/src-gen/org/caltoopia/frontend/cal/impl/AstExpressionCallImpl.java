/**
 */
package org.caltoopia.frontend.cal.impl;

import java.util.Collection;

import org.caltoopia.frontend.cal.AstExpression;
import org.caltoopia.frontend.cal.AstExpressionCall;
import org.caltoopia.frontend.cal.AstVariable;
import org.caltoopia.frontend.cal.CalPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ast Expression Call</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstExpressionCallImpl#getFunction <em>Function</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstExpressionCallImpl#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AstExpressionCallImpl extends AstExpressionImpl implements AstExpressionCall
{
  /**
   * The cached value of the '{@link #getFunction() <em>Function</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFunction()
   * @generated
   * @ordered
   */
  protected AstVariable function;

  /**
   * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParameters()
   * @generated
   * @ordered
   */
  protected EList<AstExpression> parameters;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AstExpressionCallImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return CalPackage.Literals.AST_EXPRESSION_CALL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstVariable getFunction()
  {
    if (function != null && function.eIsProxy())
    {
      InternalEObject oldFunction = (InternalEObject)function;
      function = (AstVariable)eResolveProxy(oldFunction);
      if (function != oldFunction)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CalPackage.AST_EXPRESSION_CALL__FUNCTION, oldFunction, function));
      }
    }
    return function;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstVariable basicGetFunction()
  {
    return function;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFunction(AstVariable newFunction)
  {
    AstVariable oldFunction = function;
    function = newFunction;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_EXPRESSION_CALL__FUNCTION, oldFunction, function));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AstExpression> getParameters()
  {
    if (parameters == null)
    {
      parameters = new EObjectContainmentEList<AstExpression>(AstExpression.class, this, CalPackage.AST_EXPRESSION_CALL__PARAMETERS);
    }
    return parameters;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case CalPackage.AST_EXPRESSION_CALL__PARAMETERS:
        return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case CalPackage.AST_EXPRESSION_CALL__FUNCTION:
        if (resolve) return getFunction();
        return basicGetFunction();
      case CalPackage.AST_EXPRESSION_CALL__PARAMETERS:
        return getParameters();
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
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case CalPackage.AST_EXPRESSION_CALL__FUNCTION:
        setFunction((AstVariable)newValue);
        return;
      case CalPackage.AST_EXPRESSION_CALL__PARAMETERS:
        getParameters().clear();
        getParameters().addAll((Collection<? extends AstExpression>)newValue);
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
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case CalPackage.AST_EXPRESSION_CALL__FUNCTION:
        setFunction((AstVariable)null);
        return;
      case CalPackage.AST_EXPRESSION_CALL__PARAMETERS:
        getParameters().clear();
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
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case CalPackage.AST_EXPRESSION_CALL__FUNCTION:
        return function != null;
      case CalPackage.AST_EXPRESSION_CALL__PARAMETERS:
        return parameters != null && !parameters.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //AstExpressionCallImpl
