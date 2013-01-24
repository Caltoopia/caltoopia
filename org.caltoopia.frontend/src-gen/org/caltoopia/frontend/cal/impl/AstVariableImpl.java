/**
 * <copyright>
 * </copyright>
 *
 */
package org.caltoopia.frontend.cal.impl;

import java.util.Collection;

import org.caltoopia.frontend.cal.AstAnnotation;
import org.caltoopia.frontend.cal.AstExpression;
import org.caltoopia.frontend.cal.AstType;
import org.caltoopia.frontend.cal.AstVariable;
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
 * An implementation of the model object '<em><b>Ast Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstVariableImpl#isConstant <em>Constant</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstVariableImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstVariableImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstVariableImpl#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstVariableImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstVariableImpl#getDimensions <em>Dimensions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AstVariableImpl extends MinimalEObjectImpl.Container implements AstVariable
{
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
   * The cached value of the '{@link #getValue() <em>Value</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected AstExpression value;

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
   * The cached value of the '{@link #getAnnotations() <em>Annotations</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnnotations()
   * @generated
   * @ordered
   */
  protected EList<AstAnnotation> annotations;

  /**
   * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected AstType type;

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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AstVariableImpl()
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
    return CalPackage.Literals.AST_VARIABLE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isConstant()
  {
    return constant;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConstant(boolean newConstant)
  {
    boolean oldConstant = constant;
    constant = newConstant;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_VARIABLE__CONSTANT, oldConstant, constant));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstExpression getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetValue(AstExpression newValue, NotificationChain msgs)
  {
    AstExpression oldValue = value;
    value = newValue;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CalPackage.AST_VARIABLE__VALUE, oldValue, newValue);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setValue(AstExpression newValue)
  {
    if (newValue != value)
    {
      NotificationChain msgs = null;
      if (value != null)
        msgs = ((InternalEObject)value).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_VARIABLE__VALUE, null, msgs);
      if (newValue != null)
        msgs = ((InternalEObject)newValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_VARIABLE__VALUE, null, msgs);
      msgs = basicSetValue(newValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_VARIABLE__VALUE, newValue, newValue));
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
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_VARIABLE__NAME, oldName, name));
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
      annotations = new EObjectContainmentEList<AstAnnotation>(AstAnnotation.class, this, CalPackage.AST_VARIABLE__ANNOTATIONS);
    }
    return annotations;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstType getType()
  {
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetType(AstType newType, NotificationChain msgs)
  {
    AstType oldType = type;
    type = newType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CalPackage.AST_VARIABLE__TYPE, oldType, newType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setType(AstType newType)
  {
    if (newType != type)
    {
      NotificationChain msgs = null;
      if (type != null)
        msgs = ((InternalEObject)type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_VARIABLE__TYPE, null, msgs);
      if (newType != null)
        msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_VARIABLE__TYPE, null, msgs);
      msgs = basicSetType(newType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_VARIABLE__TYPE, newType, newType));
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
      dimensions = new EObjectContainmentEList<AstExpression>(AstExpression.class, this, CalPackage.AST_VARIABLE__DIMENSIONS);
    }
    return dimensions;
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
      case CalPackage.AST_VARIABLE__VALUE:
        return basicSetValue(null, msgs);
      case CalPackage.AST_VARIABLE__ANNOTATIONS:
        return ((InternalEList<?>)getAnnotations()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_VARIABLE__TYPE:
        return basicSetType(null, msgs);
      case CalPackage.AST_VARIABLE__DIMENSIONS:
        return ((InternalEList<?>)getDimensions()).basicRemove(otherEnd, msgs);
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
      case CalPackage.AST_VARIABLE__CONSTANT:
        return isConstant();
      case CalPackage.AST_VARIABLE__VALUE:
        return getValue();
      case CalPackage.AST_VARIABLE__NAME:
        return getName();
      case CalPackage.AST_VARIABLE__ANNOTATIONS:
        return getAnnotations();
      case CalPackage.AST_VARIABLE__TYPE:
        return getType();
      case CalPackage.AST_VARIABLE__DIMENSIONS:
        return getDimensions();
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
      case CalPackage.AST_VARIABLE__CONSTANT:
        setConstant((Boolean)newValue);
        return;
      case CalPackage.AST_VARIABLE__VALUE:
        setValue((AstExpression)newValue);
        return;
      case CalPackage.AST_VARIABLE__NAME:
        setName((String)newValue);
        return;
      case CalPackage.AST_VARIABLE__ANNOTATIONS:
        getAnnotations().clear();
        getAnnotations().addAll((Collection<? extends AstAnnotation>)newValue);
        return;
      case CalPackage.AST_VARIABLE__TYPE:
        setType((AstType)newValue);
        return;
      case CalPackage.AST_VARIABLE__DIMENSIONS:
        getDimensions().clear();
        getDimensions().addAll((Collection<? extends AstExpression>)newValue);
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
      case CalPackage.AST_VARIABLE__CONSTANT:
        setConstant(CONSTANT_EDEFAULT);
        return;
      case CalPackage.AST_VARIABLE__VALUE:
        setValue((AstExpression)null);
        return;
      case CalPackage.AST_VARIABLE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case CalPackage.AST_VARIABLE__ANNOTATIONS:
        getAnnotations().clear();
        return;
      case CalPackage.AST_VARIABLE__TYPE:
        setType((AstType)null);
        return;
      case CalPackage.AST_VARIABLE__DIMENSIONS:
        getDimensions().clear();
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
      case CalPackage.AST_VARIABLE__CONSTANT:
        return constant != CONSTANT_EDEFAULT;
      case CalPackage.AST_VARIABLE__VALUE:
        return value != null;
      case CalPackage.AST_VARIABLE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case CalPackage.AST_VARIABLE__ANNOTATIONS:
        return annotations != null && !annotations.isEmpty();
      case CalPackage.AST_VARIABLE__TYPE:
        return type != null;
      case CalPackage.AST_VARIABLE__DIMENSIONS:
        return dimensions != null && !dimensions.isEmpty();
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
    result.append(" (constant: ");
    result.append(constant);
    result.append(", name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //AstVariableImpl
