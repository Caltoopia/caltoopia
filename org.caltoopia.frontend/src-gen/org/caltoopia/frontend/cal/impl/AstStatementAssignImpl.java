/**
 */
package org.caltoopia.frontend.cal.impl;

import java.util.Collection;

import org.caltoopia.frontend.cal.AstExpression;
import org.caltoopia.frontend.cal.AstMemberAccess;
import org.caltoopia.frontend.cal.AstStatementAssign;
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
 * An implementation of the model object '<em><b>Ast Statement Assign</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstStatementAssignImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstStatementAssignImpl#getIndexes <em>Indexes</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstStatementAssignImpl#getMember <em>Member</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstStatementAssignImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AstStatementAssignImpl extends AstStatementImpl implements AstStatementAssign
{
  /**
   * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTarget()
   * @generated
   * @ordered
   */
  protected AstVariable target;

  /**
   * The cached value of the '{@link #getIndexes() <em>Indexes</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIndexes()
   * @generated
   * @ordered
   */
  protected EList<AstExpression> indexes;

  /**
   * The cached value of the '{@link #getMember() <em>Member</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMember()
   * @generated
   * @ordered
   */
  protected EList<AstMemberAccess> member;

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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AstStatementAssignImpl()
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
    return CalPackage.Literals.AST_STATEMENT_ASSIGN;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstVariable getTarget()
  {
    if (target != null && target.eIsProxy())
    {
      InternalEObject oldTarget = (InternalEObject)target;
      target = (AstVariable)eResolveProxy(oldTarget);
      if (target != oldTarget)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CalPackage.AST_STATEMENT_ASSIGN__TARGET, oldTarget, target));
      }
    }
    return target;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstVariable basicGetTarget()
  {
    return target;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTarget(AstVariable newTarget)
  {
    AstVariable oldTarget = target;
    target = newTarget;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_STATEMENT_ASSIGN__TARGET, oldTarget, target));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AstExpression> getIndexes()
  {
    if (indexes == null)
    {
      indexes = new EObjectContainmentEList<AstExpression>(AstExpression.class, this, CalPackage.AST_STATEMENT_ASSIGN__INDEXES);
    }
    return indexes;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AstMemberAccess> getMember()
  {
    if (member == null)
    {
      member = new EObjectContainmentEList<AstMemberAccess>(AstMemberAccess.class, this, CalPackage.AST_STATEMENT_ASSIGN__MEMBER);
    }
    return member;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CalPackage.AST_STATEMENT_ASSIGN__VALUE, oldValue, newValue);
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
        msgs = ((InternalEObject)value).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_STATEMENT_ASSIGN__VALUE, null, msgs);
      if (newValue != null)
        msgs = ((InternalEObject)newValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_STATEMENT_ASSIGN__VALUE, null, msgs);
      msgs = basicSetValue(newValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_STATEMENT_ASSIGN__VALUE, newValue, newValue));
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
      case CalPackage.AST_STATEMENT_ASSIGN__INDEXES:
        return ((InternalEList<?>)getIndexes()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_STATEMENT_ASSIGN__MEMBER:
        return ((InternalEList<?>)getMember()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_STATEMENT_ASSIGN__VALUE:
        return basicSetValue(null, msgs);
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
      case CalPackage.AST_STATEMENT_ASSIGN__TARGET:
        if (resolve) return getTarget();
        return basicGetTarget();
      case CalPackage.AST_STATEMENT_ASSIGN__INDEXES:
        return getIndexes();
      case CalPackage.AST_STATEMENT_ASSIGN__MEMBER:
        return getMember();
      case CalPackage.AST_STATEMENT_ASSIGN__VALUE:
        return getValue();
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
      case CalPackage.AST_STATEMENT_ASSIGN__TARGET:
        setTarget((AstVariable)newValue);
        return;
      case CalPackage.AST_STATEMENT_ASSIGN__INDEXES:
        getIndexes().clear();
        getIndexes().addAll((Collection<? extends AstExpression>)newValue);
        return;
      case CalPackage.AST_STATEMENT_ASSIGN__MEMBER:
        getMember().clear();
        getMember().addAll((Collection<? extends AstMemberAccess>)newValue);
        return;
      case CalPackage.AST_STATEMENT_ASSIGN__VALUE:
        setValue((AstExpression)newValue);
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
      case CalPackage.AST_STATEMENT_ASSIGN__TARGET:
        setTarget((AstVariable)null);
        return;
      case CalPackage.AST_STATEMENT_ASSIGN__INDEXES:
        getIndexes().clear();
        return;
      case CalPackage.AST_STATEMENT_ASSIGN__MEMBER:
        getMember().clear();
        return;
      case CalPackage.AST_STATEMENT_ASSIGN__VALUE:
        setValue((AstExpression)null);
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
      case CalPackage.AST_STATEMENT_ASSIGN__TARGET:
        return target != null;
      case CalPackage.AST_STATEMENT_ASSIGN__INDEXES:
        return indexes != null && !indexes.isEmpty();
      case CalPackage.AST_STATEMENT_ASSIGN__MEMBER:
        return member != null && !member.isEmpty();
      case CalPackage.AST_STATEMENT_ASSIGN__VALUE:
        return value != null;
    }
    return super.eIsSet(featureID);
  }

} //AstStatementAssignImpl
