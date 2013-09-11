/**
 */
package org.caltoopia.frontend.cal.impl;

import java.util.Collection;

import org.caltoopia.frontend.cal.AstExpression;
import org.caltoopia.frontend.cal.AstExpressionAlternative;
import org.caltoopia.frontend.cal.AstPattern;
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
 * An implementation of the model object '<em><b>Ast Expression Alternative</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstExpressionAlternativeImpl#getPattern <em>Pattern</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstExpressionAlternativeImpl#getGuards <em>Guards</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstExpressionAlternativeImpl#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AstExpressionAlternativeImpl extends MinimalEObjectImpl.Container implements AstExpressionAlternative
{
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
   * The cached value of the '{@link #getGuards() <em>Guards</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGuards()
   * @generated
   * @ordered
   */
  protected EList<AstExpression> guards;

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
  protected AstExpressionAlternativeImpl()
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
    return CalPackage.Literals.AST_EXPRESSION_ALTERNATIVE;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CalPackage.AST_EXPRESSION_ALTERNATIVE__PATTERN, oldPattern, newPattern);
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
        msgs = ((InternalEObject)pattern).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_EXPRESSION_ALTERNATIVE__PATTERN, null, msgs);
      if (newPattern != null)
        msgs = ((InternalEObject)newPattern).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_EXPRESSION_ALTERNATIVE__PATTERN, null, msgs);
      msgs = basicSetPattern(newPattern, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_EXPRESSION_ALTERNATIVE__PATTERN, newPattern, newPattern));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AstExpression> getGuards()
  {
    if (guards == null)
    {
      guards = new EObjectContainmentEList<AstExpression>(AstExpression.class, this, CalPackage.AST_EXPRESSION_ALTERNATIVE__GUARDS);
    }
    return guards;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CalPackage.AST_EXPRESSION_ALTERNATIVE__EXPRESSION, oldExpression, newExpression);
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
        msgs = ((InternalEObject)expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_EXPRESSION_ALTERNATIVE__EXPRESSION, null, msgs);
      if (newExpression != null)
        msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_EXPRESSION_ALTERNATIVE__EXPRESSION, null, msgs);
      msgs = basicSetExpression(newExpression, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_EXPRESSION_ALTERNATIVE__EXPRESSION, newExpression, newExpression));
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
      case CalPackage.AST_EXPRESSION_ALTERNATIVE__PATTERN:
        return basicSetPattern(null, msgs);
      case CalPackage.AST_EXPRESSION_ALTERNATIVE__GUARDS:
        return ((InternalEList<?>)getGuards()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_EXPRESSION_ALTERNATIVE__EXPRESSION:
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
      case CalPackage.AST_EXPRESSION_ALTERNATIVE__PATTERN:
        return getPattern();
      case CalPackage.AST_EXPRESSION_ALTERNATIVE__GUARDS:
        return getGuards();
      case CalPackage.AST_EXPRESSION_ALTERNATIVE__EXPRESSION:
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
      case CalPackage.AST_EXPRESSION_ALTERNATIVE__PATTERN:
        setPattern((AstPattern)newValue);
        return;
      case CalPackage.AST_EXPRESSION_ALTERNATIVE__GUARDS:
        getGuards().clear();
        getGuards().addAll((Collection<? extends AstExpression>)newValue);
        return;
      case CalPackage.AST_EXPRESSION_ALTERNATIVE__EXPRESSION:
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
      case CalPackage.AST_EXPRESSION_ALTERNATIVE__PATTERN:
        setPattern((AstPattern)null);
        return;
      case CalPackage.AST_EXPRESSION_ALTERNATIVE__GUARDS:
        getGuards().clear();
        return;
      case CalPackage.AST_EXPRESSION_ALTERNATIVE__EXPRESSION:
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
      case CalPackage.AST_EXPRESSION_ALTERNATIVE__PATTERN:
        return pattern != null;
      case CalPackage.AST_EXPRESSION_ALTERNATIVE__GUARDS:
        return guards != null && !guards.isEmpty();
      case CalPackage.AST_EXPRESSION_ALTERNATIVE__EXPRESSION:
        return expression != null;
    }
    return super.eIsSet(featureID);
  }

} //AstExpressionAlternativeImpl
