/**
 * <copyright>
 * </copyright>
 *
 */
package org.caltoopia.frontend.cal.impl;

import java.util.Collection;

import org.caltoopia.frontend.cal.AstExpression;
import org.caltoopia.frontend.cal.AstOutputPattern;
import org.caltoopia.frontend.cal.AstPort;
import org.caltoopia.frontend.cal.CalPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ast Output Pattern</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstOutputPatternImpl#getPort <em>Port</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstOutputPatternImpl#getValues <em>Values</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstOutputPatternImpl#getRepeat <em>Repeat</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AstOutputPatternImpl extends MinimalEObjectImpl.Container implements AstOutputPattern
{
  /**
   * The cached value of the '{@link #getPort() <em>Port</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPort()
   * @generated
   * @ordered
   */
  protected AstPort port;

  /**
   * The cached value of the '{@link #getValues() <em>Values</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValues()
   * @generated
   * @ordered
   */
  protected EList<AstExpression> values;

  /**
   * The cached value of the '{@link #getRepeat() <em>Repeat</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRepeat()
   * @generated
   * @ordered
   */
  protected AstExpression repeat;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AstOutputPatternImpl()
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
    return CalPackage.Literals.AST_OUTPUT_PATTERN;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstPort getPort()
  {
    if (port != null && port.eIsProxy())
    {
      InternalEObject oldPort = (InternalEObject)port;
      port = (AstPort)eResolveProxy(oldPort);
      if (port != oldPort)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CalPackage.AST_OUTPUT_PATTERN__PORT, oldPort, port));
      }
    }
    return port;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstPort basicGetPort()
  {
    return port;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPort(AstPort newPort)
  {
    AstPort oldPort = port;
    port = newPort;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_OUTPUT_PATTERN__PORT, oldPort, port));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AstExpression> getValues()
  {
    if (values == null)
    {
      values = new EObjectContainmentEList<AstExpression>(AstExpression.class, this, CalPackage.AST_OUTPUT_PATTERN__VALUES);
    }
    return values;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstExpression getRepeat()
  {
    return repeat;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetRepeat(AstExpression newRepeat, NotificationChain msgs)
  {
    AstExpression oldRepeat = repeat;
    repeat = newRepeat;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CalPackage.AST_OUTPUT_PATTERN__REPEAT, oldRepeat, newRepeat);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRepeat(AstExpression newRepeat)
  {
    if (newRepeat != repeat)
    {
      NotificationChain msgs = null;
      if (repeat != null)
        msgs = ((InternalEObject)repeat).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_OUTPUT_PATTERN__REPEAT, null, msgs);
      if (newRepeat != null)
        msgs = ((InternalEObject)newRepeat).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_OUTPUT_PATTERN__REPEAT, null, msgs);
      msgs = basicSetRepeat(newRepeat, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_OUTPUT_PATTERN__REPEAT, newRepeat, newRepeat));
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
      case CalPackage.AST_OUTPUT_PATTERN__VALUES:
        return ((InternalEList<?>)getValues()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_OUTPUT_PATTERN__REPEAT:
        return basicSetRepeat(null, msgs);
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
      case CalPackage.AST_OUTPUT_PATTERN__PORT:
        if (resolve) return getPort();
        return basicGetPort();
      case CalPackage.AST_OUTPUT_PATTERN__VALUES:
        return getValues();
      case CalPackage.AST_OUTPUT_PATTERN__REPEAT:
        return getRepeat();
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
      case CalPackage.AST_OUTPUT_PATTERN__PORT:
        setPort((AstPort)newValue);
        return;
      case CalPackage.AST_OUTPUT_PATTERN__VALUES:
        getValues().clear();
        getValues().addAll((Collection<? extends AstExpression>)newValue);
        return;
      case CalPackage.AST_OUTPUT_PATTERN__REPEAT:
        setRepeat((AstExpression)newValue);
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
      case CalPackage.AST_OUTPUT_PATTERN__PORT:
        setPort((AstPort)null);
        return;
      case CalPackage.AST_OUTPUT_PATTERN__VALUES:
        getValues().clear();
        return;
      case CalPackage.AST_OUTPUT_PATTERN__REPEAT:
        setRepeat((AstExpression)null);
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
      case CalPackage.AST_OUTPUT_PATTERN__PORT:
        return port != null;
      case CalPackage.AST_OUTPUT_PATTERN__VALUES:
        return values != null && !values.isEmpty();
      case CalPackage.AST_OUTPUT_PATTERN__REPEAT:
        return repeat != null;
    }
    return super.eIsSet(featureID);
  }

} //AstOutputPatternImpl
