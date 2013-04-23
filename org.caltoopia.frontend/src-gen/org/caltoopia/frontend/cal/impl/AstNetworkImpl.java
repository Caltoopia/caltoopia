/**
 */
package org.caltoopia.frontend.cal.impl;

import java.util.Collection;

import org.caltoopia.frontend.cal.AstActorVariable;
import org.caltoopia.frontend.cal.AstNetwork;
import org.caltoopia.frontend.cal.AstStructure;
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
 * An implementation of the model object '<em><b>Ast Network</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstNetworkImpl#getVariables <em>Variables</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstNetworkImpl#getInstances <em>Instances</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstNetworkImpl#getStructure <em>Structure</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AstNetworkImpl extends AstAbstractActorImpl implements AstNetwork
{
  /**
   * The cached value of the '{@link #getVariables() <em>Variables</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariables()
   * @generated
   * @ordered
   */
  protected EList<AstVariable> variables;

  /**
   * The cached value of the '{@link #getInstances() <em>Instances</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInstances()
   * @generated
   * @ordered
   */
  protected EList<AstActorVariable> instances;

  /**
   * The cached value of the '{@link #getStructure() <em>Structure</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStructure()
   * @generated
   * @ordered
   */
  protected AstStructure structure;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AstNetworkImpl()
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
    return CalPackage.Literals.AST_NETWORK;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AstVariable> getVariables()
  {
    if (variables == null)
    {
      variables = new EObjectContainmentEList<AstVariable>(AstVariable.class, this, CalPackage.AST_NETWORK__VARIABLES);
    }
    return variables;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AstActorVariable> getInstances()
  {
    if (instances == null)
    {
      instances = new EObjectContainmentEList<AstActorVariable>(AstActorVariable.class, this, CalPackage.AST_NETWORK__INSTANCES);
    }
    return instances;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstStructure getStructure()
  {
    return structure;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetStructure(AstStructure newStructure, NotificationChain msgs)
  {
    AstStructure oldStructure = structure;
    structure = newStructure;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CalPackage.AST_NETWORK__STRUCTURE, oldStructure, newStructure);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStructure(AstStructure newStructure)
  {
    if (newStructure != structure)
    {
      NotificationChain msgs = null;
      if (structure != null)
        msgs = ((InternalEObject)structure).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_NETWORK__STRUCTURE, null, msgs);
      if (newStructure != null)
        msgs = ((InternalEObject)newStructure).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_NETWORK__STRUCTURE, null, msgs);
      msgs = basicSetStructure(newStructure, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_NETWORK__STRUCTURE, newStructure, newStructure));
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
      case CalPackage.AST_NETWORK__VARIABLES:
        return ((InternalEList<?>)getVariables()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_NETWORK__INSTANCES:
        return ((InternalEList<?>)getInstances()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_NETWORK__STRUCTURE:
        return basicSetStructure(null, msgs);
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
      case CalPackage.AST_NETWORK__VARIABLES:
        return getVariables();
      case CalPackage.AST_NETWORK__INSTANCES:
        return getInstances();
      case CalPackage.AST_NETWORK__STRUCTURE:
        return getStructure();
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
      case CalPackage.AST_NETWORK__VARIABLES:
        getVariables().clear();
        getVariables().addAll((Collection<? extends AstVariable>)newValue);
        return;
      case CalPackage.AST_NETWORK__INSTANCES:
        getInstances().clear();
        getInstances().addAll((Collection<? extends AstActorVariable>)newValue);
        return;
      case CalPackage.AST_NETWORK__STRUCTURE:
        setStructure((AstStructure)newValue);
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
      case CalPackage.AST_NETWORK__VARIABLES:
        getVariables().clear();
        return;
      case CalPackage.AST_NETWORK__INSTANCES:
        getInstances().clear();
        return;
      case CalPackage.AST_NETWORK__STRUCTURE:
        setStructure((AstStructure)null);
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
      case CalPackage.AST_NETWORK__VARIABLES:
        return variables != null && !variables.isEmpty();
      case CalPackage.AST_NETWORK__INSTANCES:
        return instances != null && !instances.isEmpty();
      case CalPackage.AST_NETWORK__STRUCTURE:
        return structure != null;
    }
    return super.eIsSet(featureID);
  }

} //AstNetworkImpl
