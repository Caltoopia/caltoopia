/**
 * <copyright>
 * </copyright>
 *
 */
package org.caltoopia.frontend.cal.impl;

import java.util.Collection;

import org.caltoopia.frontend.cal.AstActorVariableReference;
import org.caltoopia.frontend.cal.AstConnection;
import org.caltoopia.frontend.cal.AstConnectionAttribute;
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
 * An implementation of the model object '<em><b>Ast Connection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstConnectionImpl#getFrom <em>From</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstConnectionImpl#getOutPort <em>Out Port</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstConnectionImpl#getTo <em>To</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstConnectionImpl#getInPort <em>In Port</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstConnectionImpl#getAttribute <em>Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AstConnectionImpl extends MinimalEObjectImpl.Container implements AstConnection
{
  /**
   * The cached value of the '{@link #getFrom() <em>From</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFrom()
   * @generated
   * @ordered
   */
  protected AstActorVariableReference from;

  /**
   * The default value of the '{@link #getOutPort() <em>Out Port</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOutPort()
   * @generated
   * @ordered
   */
  protected static final String OUT_PORT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getOutPort() <em>Out Port</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOutPort()
   * @generated
   * @ordered
   */
  protected String outPort = OUT_PORT_EDEFAULT;

  /**
   * The cached value of the '{@link #getTo() <em>To</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTo()
   * @generated
   * @ordered
   */
  protected AstActorVariableReference to;

  /**
   * The default value of the '{@link #getInPort() <em>In Port</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInPort()
   * @generated
   * @ordered
   */
  protected static final String IN_PORT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getInPort() <em>In Port</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInPort()
   * @generated
   * @ordered
   */
  protected String inPort = IN_PORT_EDEFAULT;

  /**
   * The cached value of the '{@link #getAttribute() <em>Attribute</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAttribute()
   * @generated
   * @ordered
   */
  protected EList<AstConnectionAttribute> attribute;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AstConnectionImpl()
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
    return CalPackage.Literals.AST_CONNECTION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstActorVariableReference getFrom()
  {
    return from;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFrom(AstActorVariableReference newFrom, NotificationChain msgs)
  {
    AstActorVariableReference oldFrom = from;
    from = newFrom;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CalPackage.AST_CONNECTION__FROM, oldFrom, newFrom);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFrom(AstActorVariableReference newFrom)
  {
    if (newFrom != from)
    {
      NotificationChain msgs = null;
      if (from != null)
        msgs = ((InternalEObject)from).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_CONNECTION__FROM, null, msgs);
      if (newFrom != null)
        msgs = ((InternalEObject)newFrom).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_CONNECTION__FROM, null, msgs);
      msgs = basicSetFrom(newFrom, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_CONNECTION__FROM, newFrom, newFrom));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getOutPort()
  {
    return outPort;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOutPort(String newOutPort)
  {
    String oldOutPort = outPort;
    outPort = newOutPort;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_CONNECTION__OUT_PORT, oldOutPort, outPort));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstActorVariableReference getTo()
  {
    return to;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTo(AstActorVariableReference newTo, NotificationChain msgs)
  {
    AstActorVariableReference oldTo = to;
    to = newTo;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CalPackage.AST_CONNECTION__TO, oldTo, newTo);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTo(AstActorVariableReference newTo)
  {
    if (newTo != to)
    {
      NotificationChain msgs = null;
      if (to != null)
        msgs = ((InternalEObject)to).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_CONNECTION__TO, null, msgs);
      if (newTo != null)
        msgs = ((InternalEObject)newTo).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_CONNECTION__TO, null, msgs);
      msgs = basicSetTo(newTo, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_CONNECTION__TO, newTo, newTo));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getInPort()
  {
    return inPort;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInPort(String newInPort)
  {
    String oldInPort = inPort;
    inPort = newInPort;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_CONNECTION__IN_PORT, oldInPort, inPort));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AstConnectionAttribute> getAttribute()
  {
    if (attribute == null)
    {
      attribute = new EObjectContainmentEList<AstConnectionAttribute>(AstConnectionAttribute.class, this, CalPackage.AST_CONNECTION__ATTRIBUTE);
    }
    return attribute;
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
      case CalPackage.AST_CONNECTION__FROM:
        return basicSetFrom(null, msgs);
      case CalPackage.AST_CONNECTION__TO:
        return basicSetTo(null, msgs);
      case CalPackage.AST_CONNECTION__ATTRIBUTE:
        return ((InternalEList<?>)getAttribute()).basicRemove(otherEnd, msgs);
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
      case CalPackage.AST_CONNECTION__FROM:
        return getFrom();
      case CalPackage.AST_CONNECTION__OUT_PORT:
        return getOutPort();
      case CalPackage.AST_CONNECTION__TO:
        return getTo();
      case CalPackage.AST_CONNECTION__IN_PORT:
        return getInPort();
      case CalPackage.AST_CONNECTION__ATTRIBUTE:
        return getAttribute();
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
      case CalPackage.AST_CONNECTION__FROM:
        setFrom((AstActorVariableReference)newValue);
        return;
      case CalPackage.AST_CONNECTION__OUT_PORT:
        setOutPort((String)newValue);
        return;
      case CalPackage.AST_CONNECTION__TO:
        setTo((AstActorVariableReference)newValue);
        return;
      case CalPackage.AST_CONNECTION__IN_PORT:
        setInPort((String)newValue);
        return;
      case CalPackage.AST_CONNECTION__ATTRIBUTE:
        getAttribute().clear();
        getAttribute().addAll((Collection<? extends AstConnectionAttribute>)newValue);
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
      case CalPackage.AST_CONNECTION__FROM:
        setFrom((AstActorVariableReference)null);
        return;
      case CalPackage.AST_CONNECTION__OUT_PORT:
        setOutPort(OUT_PORT_EDEFAULT);
        return;
      case CalPackage.AST_CONNECTION__TO:
        setTo((AstActorVariableReference)null);
        return;
      case CalPackage.AST_CONNECTION__IN_PORT:
        setInPort(IN_PORT_EDEFAULT);
        return;
      case CalPackage.AST_CONNECTION__ATTRIBUTE:
        getAttribute().clear();
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
      case CalPackage.AST_CONNECTION__FROM:
        return from != null;
      case CalPackage.AST_CONNECTION__OUT_PORT:
        return OUT_PORT_EDEFAULT == null ? outPort != null : !OUT_PORT_EDEFAULT.equals(outPort);
      case CalPackage.AST_CONNECTION__TO:
        return to != null;
      case CalPackage.AST_CONNECTION__IN_PORT:
        return IN_PORT_EDEFAULT == null ? inPort != null : !IN_PORT_EDEFAULT.equals(inPort);
      case CalPackage.AST_CONNECTION__ATTRIBUTE:
        return attribute != null && !attribute.isEmpty();
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
    result.append(" (outPort: ");
    result.append(outPort);
    result.append(", inPort: ");
    result.append(inPort);
    result.append(')');
    return result.toString();
  }

} //AstConnectionImpl
