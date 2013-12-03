/**
 */
package org.caltoopia.frontend.cal.impl;

import java.util.Collection;

import org.caltoopia.frontend.cal.AstExpression;
import org.caltoopia.frontend.cal.AstType;
import org.caltoopia.frontend.cal.AstTypeParameterList;
import org.caltoopia.frontend.cal.AstTypeUser;
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
 * An implementation of the model object '<em><b>Ast Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstTypeImpl#getBuiltin <em>Builtin</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstTypeImpl#getTypeParams <em>Type Params</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstTypeImpl#getDimensions <em>Dimensions</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstTypeImpl#getDomain <em>Domain</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstTypeImpl#getCodomain <em>Codomain</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AstTypeImpl extends MinimalEObjectImpl.Container implements AstType
{
  /**
   * The default value of the '{@link #getBuiltin() <em>Builtin</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBuiltin()
   * @generated
   * @ordered
   */
  protected static final String BUILTIN_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getBuiltin() <em>Builtin</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBuiltin()
   * @generated
   * @ordered
   */
  protected String builtin = BUILTIN_EDEFAULT;

  /**
   * The cached value of the '{@link #getTypeParams() <em>Type Params</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypeParams()
   * @generated
   * @ordered
   */
  protected AstTypeParameterList typeParams;

  /**
   * The cached value of the '{@link #getDimensions() <em>Dimensions</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDimensions()
   * @generated
   * @ordered
   */
  protected EList<AstExpression> dimensions;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected AstTypeUser name;

  /**
   * The cached value of the '{@link #getDomain() <em>Domain</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDomain()
   * @generated
   * @ordered
   */
  protected EList<AstType> domain;

  /**
   * The cached value of the '{@link #getCodomain() <em>Codomain</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCodomain()
   * @generated
   * @ordered
   */
  protected AstType codomain;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AstTypeImpl()
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
    return CalPackage.Literals.AST_TYPE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getBuiltin()
  {
    return builtin;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBuiltin(String newBuiltin)
  {
    String oldBuiltin = builtin;
    builtin = newBuiltin;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_TYPE__BUILTIN, oldBuiltin, builtin));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstTypeParameterList getTypeParams()
  {
    return typeParams;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTypeParams(AstTypeParameterList newTypeParams, NotificationChain msgs)
  {
    AstTypeParameterList oldTypeParams = typeParams;
    typeParams = newTypeParams;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CalPackage.AST_TYPE__TYPE_PARAMS, oldTypeParams, newTypeParams);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTypeParams(AstTypeParameterList newTypeParams)
  {
    if (newTypeParams != typeParams)
    {
      NotificationChain msgs = null;
      if (typeParams != null)
        msgs = ((InternalEObject)typeParams).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_TYPE__TYPE_PARAMS, null, msgs);
      if (newTypeParams != null)
        msgs = ((InternalEObject)newTypeParams).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_TYPE__TYPE_PARAMS, null, msgs);
      msgs = basicSetTypeParams(newTypeParams, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_TYPE__TYPE_PARAMS, newTypeParams, newTypeParams));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AstExpression> getDimensions()
  {
    if (dimensions == null)
    {
      dimensions = new EObjectContainmentEList<AstExpression>(AstExpression.class, this, CalPackage.AST_TYPE__DIMENSIONS);
    }
    return dimensions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstTypeUser getName()
  {
    if (name != null && name.eIsProxy())
    {
      InternalEObject oldName = (InternalEObject)name;
      name = (AstTypeUser)eResolveProxy(oldName);
      if (name != oldName)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CalPackage.AST_TYPE__NAME, oldName, name));
      }
    }
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstTypeUser basicGetName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(AstTypeUser newName)
  {
    AstTypeUser oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_TYPE__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AstType> getDomain()
  {
    if (domain == null)
    {
      domain = new EObjectContainmentEList<AstType>(AstType.class, this, CalPackage.AST_TYPE__DOMAIN);
    }
    return domain;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstType getCodomain()
  {
    return codomain;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCodomain(AstType newCodomain, NotificationChain msgs)
  {
    AstType oldCodomain = codomain;
    codomain = newCodomain;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CalPackage.AST_TYPE__CODOMAIN, oldCodomain, newCodomain);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCodomain(AstType newCodomain)
  {
    if (newCodomain != codomain)
    {
      NotificationChain msgs = null;
      if (codomain != null)
        msgs = ((InternalEObject)codomain).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_TYPE__CODOMAIN, null, msgs);
      if (newCodomain != null)
        msgs = ((InternalEObject)newCodomain).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_TYPE__CODOMAIN, null, msgs);
      msgs = basicSetCodomain(newCodomain, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_TYPE__CODOMAIN, newCodomain, newCodomain));
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
      case CalPackage.AST_TYPE__TYPE_PARAMS:
        return basicSetTypeParams(null, msgs);
      case CalPackage.AST_TYPE__DIMENSIONS:
        return ((InternalEList<?>)getDimensions()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_TYPE__DOMAIN:
        return ((InternalEList<?>)getDomain()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_TYPE__CODOMAIN:
        return basicSetCodomain(null, msgs);
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
      case CalPackage.AST_TYPE__BUILTIN:
        return getBuiltin();
      case CalPackage.AST_TYPE__TYPE_PARAMS:
        return getTypeParams();
      case CalPackage.AST_TYPE__DIMENSIONS:
        return getDimensions();
      case CalPackage.AST_TYPE__NAME:
        if (resolve) return getName();
        return basicGetName();
      case CalPackage.AST_TYPE__DOMAIN:
        return getDomain();
      case CalPackage.AST_TYPE__CODOMAIN:
        return getCodomain();
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
      case CalPackage.AST_TYPE__BUILTIN:
        setBuiltin((String)newValue);
        return;
      case CalPackage.AST_TYPE__TYPE_PARAMS:
        setTypeParams((AstTypeParameterList)newValue);
        return;
      case CalPackage.AST_TYPE__DIMENSIONS:
        getDimensions().clear();
        getDimensions().addAll((Collection<? extends AstExpression>)newValue);
        return;
      case CalPackage.AST_TYPE__NAME:
        setName((AstTypeUser)newValue);
        return;
      case CalPackage.AST_TYPE__DOMAIN:
        getDomain().clear();
        getDomain().addAll((Collection<? extends AstType>)newValue);
        return;
      case CalPackage.AST_TYPE__CODOMAIN:
        setCodomain((AstType)newValue);
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
      case CalPackage.AST_TYPE__BUILTIN:
        setBuiltin(BUILTIN_EDEFAULT);
        return;
      case CalPackage.AST_TYPE__TYPE_PARAMS:
        setTypeParams((AstTypeParameterList)null);
        return;
      case CalPackage.AST_TYPE__DIMENSIONS:
        getDimensions().clear();
        return;
      case CalPackage.AST_TYPE__NAME:
        setName((AstTypeUser)null);
        return;
      case CalPackage.AST_TYPE__DOMAIN:
        getDomain().clear();
        return;
      case CalPackage.AST_TYPE__CODOMAIN:
        setCodomain((AstType)null);
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
      case CalPackage.AST_TYPE__BUILTIN:
        return BUILTIN_EDEFAULT == null ? builtin != null : !BUILTIN_EDEFAULT.equals(builtin);
      case CalPackage.AST_TYPE__TYPE_PARAMS:
        return typeParams != null;
      case CalPackage.AST_TYPE__DIMENSIONS:
        return dimensions != null && !dimensions.isEmpty();
      case CalPackage.AST_TYPE__NAME:
        return name != null;
      case CalPackage.AST_TYPE__DOMAIN:
        return domain != null && !domain.isEmpty();
      case CalPackage.AST_TYPE__CODOMAIN:
        return codomain != null;
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
    result.append(" (builtin: ");
    result.append(builtin);
    result.append(')');
    return result.toString();
  }

} //AstTypeImpl
