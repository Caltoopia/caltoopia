/**
 */
package org.caltoopia.frontend.cal.impl;

import java.util.Collection;

import org.caltoopia.frontend.cal.AstExpression;
import org.caltoopia.frontend.cal.AstExpressionConstruction;
import org.caltoopia.frontend.cal.AstTypeName;
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
 * An implementation of the model object '<em><b>Ast Expression Construction</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstExpressionConstructionImpl#getFunction <em>Function</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstExpressionConstructionImpl#getCtor <em>Ctor</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstExpressionConstructionImpl#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AstExpressionConstructionImpl extends AstExpressionImpl implements AstExpressionConstruction
{
  /**
   * The cached value of the '{@link #getFunction() <em>Function</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFunction()
   * @generated
   * @ordered
   */
  protected AstTypeName function;

  /**
   * The default value of the '{@link #getCtor() <em>Ctor</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCtor()
   * @generated
   * @ordered
   */
  protected static final String CTOR_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getCtor() <em>Ctor</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCtor()
   * @generated
   * @ordered
   */
  protected String ctor = CTOR_EDEFAULT;

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
  protected AstExpressionConstructionImpl()
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
    return CalPackage.Literals.AST_EXPRESSION_CONSTRUCTION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstTypeName getFunction()
  {
    if (function != null && function.eIsProxy())
    {
      InternalEObject oldFunction = (InternalEObject)function;
      function = (AstTypeName)eResolveProxy(oldFunction);
      if (function != oldFunction)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CalPackage.AST_EXPRESSION_CONSTRUCTION__FUNCTION, oldFunction, function));
      }
    }
    return function;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstTypeName basicGetFunction()
  {
    return function;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFunction(AstTypeName newFunction)
  {
    AstTypeName oldFunction = function;
    function = newFunction;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_EXPRESSION_CONSTRUCTION__FUNCTION, oldFunction, function));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getCtor()
  {
    return ctor;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCtor(String newCtor)
  {
    String oldCtor = ctor;
    ctor = newCtor;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_EXPRESSION_CONSTRUCTION__CTOR, oldCtor, ctor));
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
      parameters = new EObjectContainmentEList<AstExpression>(AstExpression.class, this, CalPackage.AST_EXPRESSION_CONSTRUCTION__PARAMETERS);
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
      case CalPackage.AST_EXPRESSION_CONSTRUCTION__PARAMETERS:
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
      case CalPackage.AST_EXPRESSION_CONSTRUCTION__FUNCTION:
        if (resolve) return getFunction();
        return basicGetFunction();
      case CalPackage.AST_EXPRESSION_CONSTRUCTION__CTOR:
        return getCtor();
      case CalPackage.AST_EXPRESSION_CONSTRUCTION__PARAMETERS:
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
      case CalPackage.AST_EXPRESSION_CONSTRUCTION__FUNCTION:
        setFunction((AstTypeName)newValue);
        return;
      case CalPackage.AST_EXPRESSION_CONSTRUCTION__CTOR:
        setCtor((String)newValue);
        return;
      case CalPackage.AST_EXPRESSION_CONSTRUCTION__PARAMETERS:
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
      case CalPackage.AST_EXPRESSION_CONSTRUCTION__FUNCTION:
        setFunction((AstTypeName)null);
        return;
      case CalPackage.AST_EXPRESSION_CONSTRUCTION__CTOR:
        setCtor(CTOR_EDEFAULT);
        return;
      case CalPackage.AST_EXPRESSION_CONSTRUCTION__PARAMETERS:
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
      case CalPackage.AST_EXPRESSION_CONSTRUCTION__FUNCTION:
        return function != null;
      case CalPackage.AST_EXPRESSION_CONSTRUCTION__CTOR:
        return CTOR_EDEFAULT == null ? ctor != null : !CTOR_EDEFAULT.equals(ctor);
      case CalPackage.AST_EXPRESSION_CONSTRUCTION__PARAMETERS:
        return parameters != null && !parameters.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (ctor: ");
    result.append(ctor);
    result.append(')');
    return result.toString();
  }

} //AstExpressionConstructionImpl
