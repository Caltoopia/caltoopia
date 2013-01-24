/**
 * <copyright>
 * </copyright>
 *
 */
package org.caltoopia.frontend.cal.impl;

import java.util.Collection;

import org.caltoopia.frontend.cal.AstActor;
import org.caltoopia.frontend.cal.AstAnnotation;
import org.caltoopia.frontend.cal.AstEntity;
import org.caltoopia.frontend.cal.AstExternalActor;
import org.caltoopia.frontend.cal.AstNetwork;
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
 * An implementation of the model object '<em><b>Ast Entity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstEntityImpl#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstEntityImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstEntityImpl#getActor <em>Actor</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstEntityImpl#getNetwork <em>Network</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstEntityImpl#getExternal <em>External</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AstEntityImpl extends MinimalEObjectImpl.Container implements AstEntity
{
  /**
   * The cached value of the '{@link #getAnnotations() <em>Annotations</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnnotations()
   * @generated
   * @ordered
   */
  protected EList<AstAnnotation> annotations;

  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getActor() <em>Actor</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getActor()
   * @generated
   * @ordered
   */
  protected AstActor actor;

  /**
   * The cached value of the '{@link #getNetwork() <em>Network</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNetwork()
   * @generated
   * @ordered
   */
  protected AstNetwork network;

  /**
   * The cached value of the '{@link #getExternal() <em>External</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExternal()
   * @generated
   * @ordered
   */
  protected AstExternalActor external;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AstEntityImpl()
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
    return CalPackage.Literals.AST_ENTITY;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AstAnnotation> getAnnotations()
  {
    if (annotations == null)
    {
      annotations = new EObjectContainmentEList<AstAnnotation>(AstAnnotation.class, this, CalPackage.AST_ENTITY__ANNOTATIONS);
    }
    return annotations;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_ENTITY__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstActor getActor()
  {
    return actor;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetActor(AstActor newActor, NotificationChain msgs)
  {
    AstActor oldActor = actor;
    actor = newActor;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CalPackage.AST_ENTITY__ACTOR, oldActor, newActor);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setActor(AstActor newActor)
  {
    if (newActor != actor)
    {
      NotificationChain msgs = null;
      if (actor != null)
        msgs = ((InternalEObject)actor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_ENTITY__ACTOR, null, msgs);
      if (newActor != null)
        msgs = ((InternalEObject)newActor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_ENTITY__ACTOR, null, msgs);
      msgs = basicSetActor(newActor, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_ENTITY__ACTOR, newActor, newActor));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstNetwork getNetwork()
  {
    return network;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetNetwork(AstNetwork newNetwork, NotificationChain msgs)
  {
    AstNetwork oldNetwork = network;
    network = newNetwork;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CalPackage.AST_ENTITY__NETWORK, oldNetwork, newNetwork);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNetwork(AstNetwork newNetwork)
  {
    if (newNetwork != network)
    {
      NotificationChain msgs = null;
      if (network != null)
        msgs = ((InternalEObject)network).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_ENTITY__NETWORK, null, msgs);
      if (newNetwork != null)
        msgs = ((InternalEObject)newNetwork).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_ENTITY__NETWORK, null, msgs);
      msgs = basicSetNetwork(newNetwork, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_ENTITY__NETWORK, newNetwork, newNetwork));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstExternalActor getExternal()
  {
    return external;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetExternal(AstExternalActor newExternal, NotificationChain msgs)
  {
    AstExternalActor oldExternal = external;
    external = newExternal;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CalPackage.AST_ENTITY__EXTERNAL, oldExternal, newExternal);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExternal(AstExternalActor newExternal)
  {
    if (newExternal != external)
    {
      NotificationChain msgs = null;
      if (external != null)
        msgs = ((InternalEObject)external).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_ENTITY__EXTERNAL, null, msgs);
      if (newExternal != null)
        msgs = ((InternalEObject)newExternal).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_ENTITY__EXTERNAL, null, msgs);
      msgs = basicSetExternal(newExternal, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_ENTITY__EXTERNAL, newExternal, newExternal));
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
      case CalPackage.AST_ENTITY__ANNOTATIONS:
        return ((InternalEList<?>)getAnnotations()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_ENTITY__ACTOR:
        return basicSetActor(null, msgs);
      case CalPackage.AST_ENTITY__NETWORK:
        return basicSetNetwork(null, msgs);
      case CalPackage.AST_ENTITY__EXTERNAL:
        return basicSetExternal(null, msgs);
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
      case CalPackage.AST_ENTITY__ANNOTATIONS:
        return getAnnotations();
      case CalPackage.AST_ENTITY__NAME:
        return getName();
      case CalPackage.AST_ENTITY__ACTOR:
        return getActor();
      case CalPackage.AST_ENTITY__NETWORK:
        return getNetwork();
      case CalPackage.AST_ENTITY__EXTERNAL:
        return getExternal();
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
      case CalPackage.AST_ENTITY__ANNOTATIONS:
        getAnnotations().clear();
        getAnnotations().addAll((Collection<? extends AstAnnotation>)newValue);
        return;
      case CalPackage.AST_ENTITY__NAME:
        setName((String)newValue);
        return;
      case CalPackage.AST_ENTITY__ACTOR:
        setActor((AstActor)newValue);
        return;
      case CalPackage.AST_ENTITY__NETWORK:
        setNetwork((AstNetwork)newValue);
        return;
      case CalPackage.AST_ENTITY__EXTERNAL:
        setExternal((AstExternalActor)newValue);
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
      case CalPackage.AST_ENTITY__ANNOTATIONS:
        getAnnotations().clear();
        return;
      case CalPackage.AST_ENTITY__NAME:
        setName(NAME_EDEFAULT);
        return;
      case CalPackage.AST_ENTITY__ACTOR:
        setActor((AstActor)null);
        return;
      case CalPackage.AST_ENTITY__NETWORK:
        setNetwork((AstNetwork)null);
        return;
      case CalPackage.AST_ENTITY__EXTERNAL:
        setExternal((AstExternalActor)null);
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
      case CalPackage.AST_ENTITY__ANNOTATIONS:
        return annotations != null && !annotations.isEmpty();
      case CalPackage.AST_ENTITY__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case CalPackage.AST_ENTITY__ACTOR:
        return actor != null;
      case CalPackage.AST_ENTITY__NETWORK:
        return network != null;
      case CalPackage.AST_ENTITY__EXTERNAL:
        return external != null;
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
    result.append(" (name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //AstEntityImpl
