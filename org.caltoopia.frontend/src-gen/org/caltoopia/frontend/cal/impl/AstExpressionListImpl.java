/**
 * <copyright>
 * </copyright>
 *
 */
package org.caltoopia.frontend.cal.impl;

import java.util.Collection;

import org.caltoopia.frontend.cal.AstExpression;
import org.caltoopia.frontend.cal.AstExpressionList;
import org.caltoopia.frontend.cal.AstGenerator;
import org.caltoopia.frontend.cal.CalPackage;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ast Expression List</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstExpressionListImpl#getExpressions <em>Expressions</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstExpressionListImpl#getGenerators <em>Generators</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AstExpressionListImpl extends AstExpressionImpl implements AstExpressionList
{
  /**
   * The cached value of the '{@link #getExpressions() <em>Expressions</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpressions()
   * @generated
   * @ordered
   */
  protected EList<AstExpression> expressions;

  /**
   * The cached value of the '{@link #getGenerators() <em>Generators</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGenerators()
   * @generated
   * @ordered
   */
  protected EList<AstGenerator> generators;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AstExpressionListImpl()
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
    return CalPackage.Literals.AST_EXPRESSION_LIST;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AstExpression> getExpressions()
  {
    if (expressions == null)
    {
      expressions = new EObjectContainmentEList<AstExpression>(AstExpression.class, this, CalPackage.AST_EXPRESSION_LIST__EXPRESSIONS);
    }
    return expressions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AstGenerator> getGenerators()
  {
    if (generators == null)
    {
      generators = new EObjectContainmentEList<AstGenerator>(AstGenerator.class, this, CalPackage.AST_EXPRESSION_LIST__GENERATORS);
    }
    return generators;
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
      case CalPackage.AST_EXPRESSION_LIST__EXPRESSIONS:
        return ((InternalEList<?>)getExpressions()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_EXPRESSION_LIST__GENERATORS:
        return ((InternalEList<?>)getGenerators()).basicRemove(otherEnd, msgs);
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
      case CalPackage.AST_EXPRESSION_LIST__EXPRESSIONS:
        return getExpressions();
      case CalPackage.AST_EXPRESSION_LIST__GENERATORS:
        return getGenerators();
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
      case CalPackage.AST_EXPRESSION_LIST__EXPRESSIONS:
        getExpressions().clear();
        getExpressions().addAll((Collection<? extends AstExpression>)newValue);
        return;
      case CalPackage.AST_EXPRESSION_LIST__GENERATORS:
        getGenerators().clear();
        getGenerators().addAll((Collection<? extends AstGenerator>)newValue);
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
      case CalPackage.AST_EXPRESSION_LIST__EXPRESSIONS:
        getExpressions().clear();
        return;
      case CalPackage.AST_EXPRESSION_LIST__GENERATORS:
        getGenerators().clear();
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
      case CalPackage.AST_EXPRESSION_LIST__EXPRESSIONS:
        return expressions != null && !expressions.isEmpty();
      case CalPackage.AST_EXPRESSION_LIST__GENERATORS:
        return generators != null && !generators.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //AstExpressionListImpl
