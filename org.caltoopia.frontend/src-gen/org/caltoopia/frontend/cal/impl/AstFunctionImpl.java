/**
 * <copyright>
 * </copyright>
 *
 */
package org.caltoopia.frontend.cal.impl;

import java.util.Collection;

import org.caltoopia.frontend.cal.AstAnnotation;
import org.caltoopia.frontend.cal.AstExpression;
import org.caltoopia.frontend.cal.AstFunction;
import org.caltoopia.frontend.cal.AstType;
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
 * An implementation of the model object '<em><b>Ast Function</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstFunctionImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstFunctionImpl#getMembers <em>Members</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstFunctionImpl#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstFunctionImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstFunctionImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstFunctionImpl#getVariables <em>Variables</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstFunctionImpl#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AstFunctionImpl extends AstExternalFunctionImpl implements AstFunction
{
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
   * The cached value of the '{@link #getMembers() <em>Members</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMembers()
   * @generated
   * @ordered
   */
  protected EList<AstVariable> members;

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
   * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParameters()
   * @generated
   * @ordered
   */
  protected EList<AstVariable> parameters;

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
   * The cached value of the '{@link #getVariables() <em>Variables</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariables()
   * @generated
   * @ordered
   */
  protected EList<AstVariable> variables;

  /**
   * The cached value of the '{@link #getExpression() <em>Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpression()
   * @generated
   * @ordered
   */
  protected AstExpression expression;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AstFunctionImpl()
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
    return CalPackage.Literals.AST_FUNCTION;
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
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_FUNCTION__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AstVariable> getMembers()
  {
    if (members == null)
    {
      members = new EObjectContainmentEList<AstVariable>(AstVariable.class, this, CalPackage.AST_FUNCTION__MEMBERS);
    }
    return members;
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
      annotations = new EObjectContainmentEList<AstAnnotation>(AstAnnotation.class, this, CalPackage.AST_FUNCTION__ANNOTATIONS);
    }
    return annotations;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AstVariable> getParameters()
  {
    if (parameters == null)
    {
      parameters = new EObjectContainmentEList<AstVariable>(AstVariable.class, this, CalPackage.AST_FUNCTION__PARAMETERS);
    }
    return parameters;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CalPackage.AST_FUNCTION__TYPE, oldType, newType);
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
        msgs = ((InternalEObject)type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_FUNCTION__TYPE, null, msgs);
      if (newType != null)
        msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_FUNCTION__TYPE, null, msgs);
      msgs = basicSetType(newType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_FUNCTION__TYPE, newType, newType));
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
      variables = new EObjectContainmentEList<AstVariable>(AstVariable.class, this, CalPackage.AST_FUNCTION__VARIABLES);
    }
    return variables;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstExpression getExpression()
  {
    return expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetExpression(AstExpression newExpression, NotificationChain msgs)
  {
    AstExpression oldExpression = expression;
    expression = newExpression;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CalPackage.AST_FUNCTION__EXPRESSION, oldExpression, newExpression);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExpression(AstExpression newExpression)
  {
    if (newExpression != expression)
    {
      NotificationChain msgs = null;
      if (expression != null)
        msgs = ((InternalEObject)expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_FUNCTION__EXPRESSION, null, msgs);
      if (newExpression != null)
        msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_FUNCTION__EXPRESSION, null, msgs);
      msgs = basicSetExpression(newExpression, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_FUNCTION__EXPRESSION, newExpression, newExpression));
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
      case CalPackage.AST_FUNCTION__MEMBERS:
        return ((InternalEList<?>)getMembers()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_FUNCTION__ANNOTATIONS:
        return ((InternalEList<?>)getAnnotations()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_FUNCTION__PARAMETERS:
        return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_FUNCTION__TYPE:
        return basicSetType(null, msgs);
      case CalPackage.AST_FUNCTION__VARIABLES:
        return ((InternalEList<?>)getVariables()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_FUNCTION__EXPRESSION:
        return basicSetExpression(null, msgs);
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
      case CalPackage.AST_FUNCTION__NAME:
        return getName();
      case CalPackage.AST_FUNCTION__MEMBERS:
        return getMembers();
      case CalPackage.AST_FUNCTION__ANNOTATIONS:
        return getAnnotations();
      case CalPackage.AST_FUNCTION__PARAMETERS:
        return getParameters();
      case CalPackage.AST_FUNCTION__TYPE:
        return getType();
      case CalPackage.AST_FUNCTION__VARIABLES:
        return getVariables();
      case CalPackage.AST_FUNCTION__EXPRESSION:
        return getExpression();
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
      case CalPackage.AST_FUNCTION__NAME:
        setName((String)newValue);
        return;
      case CalPackage.AST_FUNCTION__MEMBERS:
        getMembers().clear();
        getMembers().addAll((Collection<? extends AstVariable>)newValue);
        return;
      case CalPackage.AST_FUNCTION__ANNOTATIONS:
        getAnnotations().clear();
        getAnnotations().addAll((Collection<? extends AstAnnotation>)newValue);
        return;
      case CalPackage.AST_FUNCTION__PARAMETERS:
        getParameters().clear();
        getParameters().addAll((Collection<? extends AstVariable>)newValue);
        return;
      case CalPackage.AST_FUNCTION__TYPE:
        setType((AstType)newValue);
        return;
      case CalPackage.AST_FUNCTION__VARIABLES:
        getVariables().clear();
        getVariables().addAll((Collection<? extends AstVariable>)newValue);
        return;
      case CalPackage.AST_FUNCTION__EXPRESSION:
        setExpression((AstExpression)newValue);
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
      case CalPackage.AST_FUNCTION__NAME:
        setName(NAME_EDEFAULT);
        return;
      case CalPackage.AST_FUNCTION__MEMBERS:
        getMembers().clear();
        return;
      case CalPackage.AST_FUNCTION__ANNOTATIONS:
        getAnnotations().clear();
        return;
      case CalPackage.AST_FUNCTION__PARAMETERS:
        getParameters().clear();
        return;
      case CalPackage.AST_FUNCTION__TYPE:
        setType((AstType)null);
        return;
      case CalPackage.AST_FUNCTION__VARIABLES:
        getVariables().clear();
        return;
      case CalPackage.AST_FUNCTION__EXPRESSION:
        setExpression((AstExpression)null);
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
      case CalPackage.AST_FUNCTION__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case CalPackage.AST_FUNCTION__MEMBERS:
        return members != null && !members.isEmpty();
      case CalPackage.AST_FUNCTION__ANNOTATIONS:
        return annotations != null && !annotations.isEmpty();
      case CalPackage.AST_FUNCTION__PARAMETERS:
        return parameters != null && !parameters.isEmpty();
      case CalPackage.AST_FUNCTION__TYPE:
        return type != null;
      case CalPackage.AST_FUNCTION__VARIABLES:
        return variables != null && !variables.isEmpty();
      case CalPackage.AST_FUNCTION__EXPRESSION:
        return expression != null;
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

} //AstFunctionImpl
