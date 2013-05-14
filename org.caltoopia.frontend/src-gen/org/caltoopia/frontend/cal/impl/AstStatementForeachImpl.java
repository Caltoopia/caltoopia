/**
 */
package org.caltoopia.frontend.cal.impl;

import java.util.Collection;

import org.caltoopia.frontend.cal.AstForeachGenerator;
import org.caltoopia.frontend.cal.AstStatement;
import org.caltoopia.frontend.cal.AstStatementForeach;
import org.caltoopia.frontend.cal.AstVariable;
import org.caltoopia.frontend.cal.CalPackage;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ast Statement Foreach</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstStatementForeachImpl#getGenerators <em>Generators</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstStatementForeachImpl#getVariables <em>Variables</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstStatementForeachImpl#getStatements <em>Statements</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AstStatementForeachImpl extends AstStatementImpl implements AstStatementForeach
{
  /**
   * The cached value of the '{@link #getGenerators() <em>Generators</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGenerators()
   * @generated
   * @ordered
   */
  protected EList<AstForeachGenerator> generators;

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
   * The cached value of the '{@link #getStatements() <em>Statements</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStatements()
   * @generated
   * @ordered
   */
  protected EList<AstStatement> statements;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AstStatementForeachImpl()
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
    return CalPackage.Literals.AST_STATEMENT_FOREACH;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AstForeachGenerator> getGenerators()
  {
    if (generators == null)
    {
      generators = new EObjectContainmentEList<AstForeachGenerator>(AstForeachGenerator.class, this, CalPackage.AST_STATEMENT_FOREACH__GENERATORS);
    }
    return generators;
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
      variables = new EObjectContainmentEList<AstVariable>(AstVariable.class, this, CalPackage.AST_STATEMENT_FOREACH__VARIABLES);
    }
    return variables;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AstStatement> getStatements()
  {
    if (statements == null)
    {
      statements = new EObjectContainmentEList<AstStatement>(AstStatement.class, this, CalPackage.AST_STATEMENT_FOREACH__STATEMENTS);
    }
    return statements;
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
      case CalPackage.AST_STATEMENT_FOREACH__GENERATORS:
        return ((InternalEList<?>)getGenerators()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_STATEMENT_FOREACH__VARIABLES:
        return ((InternalEList<?>)getVariables()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_STATEMENT_FOREACH__STATEMENTS:
        return ((InternalEList<?>)getStatements()).basicRemove(otherEnd, msgs);
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
      case CalPackage.AST_STATEMENT_FOREACH__GENERATORS:
        return getGenerators();
      case CalPackage.AST_STATEMENT_FOREACH__VARIABLES:
        return getVariables();
      case CalPackage.AST_STATEMENT_FOREACH__STATEMENTS:
        return getStatements();
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
      case CalPackage.AST_STATEMENT_FOREACH__GENERATORS:
        getGenerators().clear();
        getGenerators().addAll((Collection<? extends AstForeachGenerator>)newValue);
        return;
      case CalPackage.AST_STATEMENT_FOREACH__VARIABLES:
        getVariables().clear();
        getVariables().addAll((Collection<? extends AstVariable>)newValue);
        return;
      case CalPackage.AST_STATEMENT_FOREACH__STATEMENTS:
        getStatements().clear();
        getStatements().addAll((Collection<? extends AstStatement>)newValue);
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
      case CalPackage.AST_STATEMENT_FOREACH__GENERATORS:
        getGenerators().clear();
        return;
      case CalPackage.AST_STATEMENT_FOREACH__VARIABLES:
        getVariables().clear();
        return;
      case CalPackage.AST_STATEMENT_FOREACH__STATEMENTS:
        getStatements().clear();
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
      case CalPackage.AST_STATEMENT_FOREACH__GENERATORS:
        return generators != null && !generators.isEmpty();
      case CalPackage.AST_STATEMENT_FOREACH__VARIABLES:
        return variables != null && !variables.isEmpty();
      case CalPackage.AST_STATEMENT_FOREACH__STATEMENTS:
        return statements != null && !statements.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //AstStatementForeachImpl
