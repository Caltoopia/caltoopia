/**
 */
package org.caltoopia.frontend.cal.impl;

import org.caltoopia.frontend.cal.AstExpression;
import org.caltoopia.frontend.cal.AstPattern;
import org.caltoopia.frontend.cal.AstSubPattern;
import org.caltoopia.frontend.cal.AstVariable;
import org.caltoopia.frontend.cal.CalPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ast Sub Pattern</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstSubPatternImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstSubPatternImpl#isDontcare <em>Dontcare</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstSubPatternImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstSubPatternImpl#getVariable <em>Variable</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstSubPatternImpl#getPattern <em>Pattern</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AstSubPatternImpl extends MinimalEObjectImpl.Container implements AstSubPattern
{
  /**
   * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLabel()
   * @generated
   * @ordered
   */
  protected static final String LABEL_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLabel()
   * @generated
   * @ordered
   */
  protected String label = LABEL_EDEFAULT;

  /**
   * The default value of the '{@link #isDontcare() <em>Dontcare</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isDontcare()
   * @generated
   * @ordered
   */
  protected static final boolean DONTCARE_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isDontcare() <em>Dontcare</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isDontcare()
   * @generated
   * @ordered
   */
  protected boolean dontcare = DONTCARE_EDEFAULT;

  /**
   * The cached value of the '{@link #getCondition() <em>Condition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCondition()
   * @generated
   * @ordered
   */
  protected AstExpression condition;

  /**
   * The cached value of the '{@link #getVariable() <em>Variable</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariable()
   * @generated
   * @ordered
   */
  protected AstVariable variable;

  /**
   * The cached value of the '{@link #getPattern() <em>Pattern</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPattern()
   * @generated
   * @ordered
   */
  protected AstPattern pattern;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AstSubPatternImpl()
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
    return CalPackage.Literals.AST_SUB_PATTERN;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLabel()
  {
    return label;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLabel(String newLabel)
  {
    String oldLabel = label;
    label = newLabel;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_SUB_PATTERN__LABEL, oldLabel, label));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isDontcare()
  {
    return dontcare;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDontcare(boolean newDontcare)
  {
    boolean oldDontcare = dontcare;
    dontcare = newDontcare;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_SUB_PATTERN__DONTCARE, oldDontcare, dontcare));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstExpression getCondition()
  {
    return condition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCondition(AstExpression newCondition, NotificationChain msgs)
  {
    AstExpression oldCondition = condition;
    condition = newCondition;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CalPackage.AST_SUB_PATTERN__CONDITION, oldCondition, newCondition);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCondition(AstExpression newCondition)
  {
    if (newCondition != condition)
    {
      NotificationChain msgs = null;
      if (condition != null)
        msgs = ((InternalEObject)condition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_SUB_PATTERN__CONDITION, null, msgs);
      if (newCondition != null)
        msgs = ((InternalEObject)newCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_SUB_PATTERN__CONDITION, null, msgs);
      msgs = basicSetCondition(newCondition, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_SUB_PATTERN__CONDITION, newCondition, newCondition));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstVariable getVariable()
  {
    return variable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetVariable(AstVariable newVariable, NotificationChain msgs)
  {
    AstVariable oldVariable = variable;
    variable = newVariable;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CalPackage.AST_SUB_PATTERN__VARIABLE, oldVariable, newVariable);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVariable(AstVariable newVariable)
  {
    if (newVariable != variable)
    {
      NotificationChain msgs = null;
      if (variable != null)
        msgs = ((InternalEObject)variable).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_SUB_PATTERN__VARIABLE, null, msgs);
      if (newVariable != null)
        msgs = ((InternalEObject)newVariable).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_SUB_PATTERN__VARIABLE, null, msgs);
      msgs = basicSetVariable(newVariable, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_SUB_PATTERN__VARIABLE, newVariable, newVariable));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstPattern getPattern()
  {
    return pattern;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPattern(AstPattern newPattern, NotificationChain msgs)
  {
    AstPattern oldPattern = pattern;
    pattern = newPattern;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CalPackage.AST_SUB_PATTERN__PATTERN, oldPattern, newPattern);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPattern(AstPattern newPattern)
  {
    if (newPattern != pattern)
    {
      NotificationChain msgs = null;
      if (pattern != null)
        msgs = ((InternalEObject)pattern).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_SUB_PATTERN__PATTERN, null, msgs);
      if (newPattern != null)
        msgs = ((InternalEObject)newPattern).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_SUB_PATTERN__PATTERN, null, msgs);
      msgs = basicSetPattern(newPattern, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_SUB_PATTERN__PATTERN, newPattern, newPattern));
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
      case CalPackage.AST_SUB_PATTERN__CONDITION:
        return basicSetCondition(null, msgs);
      case CalPackage.AST_SUB_PATTERN__VARIABLE:
        return basicSetVariable(null, msgs);
      case CalPackage.AST_SUB_PATTERN__PATTERN:
        return basicSetPattern(null, msgs);
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
      case CalPackage.AST_SUB_PATTERN__LABEL:
        return getLabel();
      case CalPackage.AST_SUB_PATTERN__DONTCARE:
        return isDontcare();
      case CalPackage.AST_SUB_PATTERN__CONDITION:
        return getCondition();
      case CalPackage.AST_SUB_PATTERN__VARIABLE:
        return getVariable();
      case CalPackage.AST_SUB_PATTERN__PATTERN:
        return getPattern();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case CalPackage.AST_SUB_PATTERN__LABEL:
        setLabel((String)newValue);
        return;
      case CalPackage.AST_SUB_PATTERN__DONTCARE:
        setDontcare((Boolean)newValue);
        return;
      case CalPackage.AST_SUB_PATTERN__CONDITION:
        setCondition((AstExpression)newValue);
        return;
      case CalPackage.AST_SUB_PATTERN__VARIABLE:
        setVariable((AstVariable)newValue);
        return;
      case CalPackage.AST_SUB_PATTERN__PATTERN:
        setPattern((AstPattern)newValue);
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
      case CalPackage.AST_SUB_PATTERN__LABEL:
        setLabel(LABEL_EDEFAULT);
        return;
      case CalPackage.AST_SUB_PATTERN__DONTCARE:
        setDontcare(DONTCARE_EDEFAULT);
        return;
      case CalPackage.AST_SUB_PATTERN__CONDITION:
        setCondition((AstExpression)null);
        return;
      case CalPackage.AST_SUB_PATTERN__VARIABLE:
        setVariable((AstVariable)null);
        return;
      case CalPackage.AST_SUB_PATTERN__PATTERN:
        setPattern((AstPattern)null);
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
      case CalPackage.AST_SUB_PATTERN__LABEL:
        return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
      case CalPackage.AST_SUB_PATTERN__DONTCARE:
        return dontcare != DONTCARE_EDEFAULT;
      case CalPackage.AST_SUB_PATTERN__CONDITION:
        return condition != null;
      case CalPackage.AST_SUB_PATTERN__VARIABLE:
        return variable != null;
      case CalPackage.AST_SUB_PATTERN__PATTERN:
        return pattern != null;
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
    result.append(" (label: ");
    result.append(label);
    result.append(", dontcare: ");
    result.append(dontcare);
    result.append(')');
    return result.toString();
  }

} //AstSubPatternImpl
