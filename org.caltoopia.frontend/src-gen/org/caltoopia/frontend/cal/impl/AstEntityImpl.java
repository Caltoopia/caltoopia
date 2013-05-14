/**
 */
package org.caltoopia.frontend.cal.impl;

import java.util.Collection;

import org.caltoopia.frontend.cal.AstAbstractActor;
import org.caltoopia.frontend.cal.AstAnnotation;
import org.caltoopia.frontend.cal.AstEntity;
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
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstEntityImpl#getActor <em>Actor</em>}</li>
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
   * The cached value of the '{@link #getActor() <em>Actor</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getActor()
   * @generated
   * @ordered
   */
  protected AstAbstractActor actor;

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
  public AstAbstractActor getActor()
  {
    return actor;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetActor(AstAbstractActor newActor, NotificationChain msgs)
  {
    AstAbstractActor oldActor = actor;
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
  public void setActor(AstAbstractActor newActor)
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
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case CalPackage.AST_ENTITY__ANNOTATIONS:
        return ((InternalEList<?>)getAnnotations()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_ENTITY__ACTOR:
        return basicSetActor(null, msgs);
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
      case CalPackage.AST_ENTITY__ACTOR:
        return getActor();
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
      case CalPackage.AST_ENTITY__ACTOR:
        setActor((AstAbstractActor)newValue);
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
      case CalPackage.AST_ENTITY__ACTOR:
        setActor((AstAbstractActor)null);
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
      case CalPackage.AST_ENTITY__ACTOR:
        return actor != null;
    }
    return super.eIsSet(featureID);
  }

} //AstEntityImpl
