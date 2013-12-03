/**
 */
package org.caltoopia.frontend.cal.impl;

import java.util.Collection;

import org.caltoopia.frontend.cal.AstExpression;
import org.caltoopia.frontend.cal.AstExpressionSymbolReference;
import org.caltoopia.frontend.cal.AstMemberAccess;
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
 * An implementation of the model object '<em><b>Ast Expression Symbol Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstExpressionSymbolReferenceImpl#getSymbol <em>Symbol</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstExpressionSymbolReferenceImpl#getIndexes <em>Indexes</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstExpressionSymbolReferenceImpl#getMember <em>Member</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstExpressionSymbolReferenceImpl#getCtor <em>Ctor</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstExpressionSymbolReferenceImpl#isCall <em>Call</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstExpressionSymbolReferenceImpl#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AstExpressionSymbolReferenceImpl extends AstExpressionImpl implements AstExpressionSymbolReference
{
  /**
   * The cached value of the '{@link #getSymbol() <em>Symbol</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSymbol()
   * @generated
   * @ordered
   */
  protected AstVariable symbol;

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
   * The default value of the '{@link #getCtor() <em>Ctor</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCtor()
   * @generated
   * @ordered
   */
  protected static final String CTOR_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getCtor() <em>Ctor</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCtor()
   * @generated
   * @ordered
   */
  protected String ctor = CTOR_EDEFAULT;

  /**
   * The default value of the '{@link #isCall() <em>Call</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isCall()
   * @generated
   * @ordered
   */
  protected static final boolean CALL_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isCall() <em>Call</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isCall()
   * @generated
   * @ordered
   */
  protected boolean call = CALL_EDEFAULT;

  /**
   * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParameters()
   * @generated
   * @ordered
   */
  protected EList<AstExpression> parameters;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AstExpressionSymbolReferenceImpl()
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
    return CalPackage.Literals.AST_EXPRESSION_SYMBOL_REFERENCE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstVariable getSymbol()
  {
    if (symbol != null && symbol.eIsProxy())
    {
      InternalEObject oldSymbol = (InternalEObject)symbol;
      symbol = (AstVariable)eResolveProxy(oldSymbol);
      if (symbol != oldSymbol)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__SYMBOL, oldSymbol, symbol));
      }
    }
    return symbol;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstVariable basicGetSymbol()
  {
    return symbol;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSymbol(AstVariable newSymbol)
  {
    AstVariable oldSymbol = symbol;
    symbol = newSymbol;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__SYMBOL, oldSymbol, symbol));
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
      indexes = new EObjectContainmentEList<AstExpression>(AstExpression.class, this, CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__INDEXES);
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
      member = new EObjectContainmentEList<AstMemberAccess>(AstMemberAccess.class, this, CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__MEMBER);
    }
    return member;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getCtor()
  {
    return ctor;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCtor(String newCtor)
  {
    String oldCtor = ctor;
    ctor = newCtor;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__CTOR, oldCtor, ctor));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isCall()
  {
    return call;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCall(boolean newCall)
  {
    boolean oldCall = call;
    call = newCall;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__CALL, oldCall, call));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AstExpression> getParameters()
  {
    if (parameters == null)
    {
      parameters = new EObjectContainmentEList<AstExpression>(AstExpression.class, this, CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__PARAMETERS);
    }
    return parameters;
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
      case CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__INDEXES:
        return ((InternalEList<?>)getIndexes()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__MEMBER:
        return ((InternalEList<?>)getMember()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__PARAMETERS:
        return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
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
      case CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__SYMBOL:
        if (resolve) return getSymbol();
        return basicGetSymbol();
      case CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__INDEXES:
        return getIndexes();
      case CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__MEMBER:
        return getMember();
      case CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__CTOR:
        return getCtor();
      case CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__CALL:
        return isCall();
      case CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__PARAMETERS:
        return getParameters();
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
      case CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__SYMBOL:
        setSymbol((AstVariable)newValue);
        return;
      case CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__INDEXES:
        getIndexes().clear();
        getIndexes().addAll((Collection<? extends AstExpression>)newValue);
        return;
      case CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__MEMBER:
        getMember().clear();
        getMember().addAll((Collection<? extends AstMemberAccess>)newValue);
        return;
      case CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__CTOR:
        setCtor((String)newValue);
        return;
      case CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__CALL:
        setCall((Boolean)newValue);
        return;
      case CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__PARAMETERS:
        getParameters().clear();
        getParameters().addAll((Collection<? extends AstExpression>)newValue);
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
      case CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__SYMBOL:
        setSymbol((AstVariable)null);
        return;
      case CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__INDEXES:
        getIndexes().clear();
        return;
      case CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__MEMBER:
        getMember().clear();
        return;
      case CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__CTOR:
        setCtor(CTOR_EDEFAULT);
        return;
      case CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__CALL:
        setCall(CALL_EDEFAULT);
        return;
      case CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__PARAMETERS:
        getParameters().clear();
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
      case CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__SYMBOL:
        return symbol != null;
      case CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__INDEXES:
        return indexes != null && !indexes.isEmpty();
      case CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__MEMBER:
        return member != null && !member.isEmpty();
      case CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__CTOR:
        return CTOR_EDEFAULT == null ? ctor != null : !CTOR_EDEFAULT.equals(ctor);
      case CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__CALL:
        return call != CALL_EDEFAULT;
      case CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE__PARAMETERS:
        return parameters != null && !parameters.isEmpty();
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
    result.append(" (ctor: ");
    result.append(ctor);
    result.append(", call: ");
    result.append(call);
    result.append(')');
    return result.toString();
  }

} //AstExpressionSymbolReferenceImpl
