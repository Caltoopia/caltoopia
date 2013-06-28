/**
 */
package org.caltoopia.frontend.cal.impl;

import java.util.Collection;

import org.caltoopia.frontend.cal.AstTaggedTuple;
import org.caltoopia.frontend.cal.AstTypeDefinitionParameter;
import org.caltoopia.frontend.cal.AstTypeUser;
import org.caltoopia.frontend.cal.CalPackage;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ast Type User</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstTypeUserImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstTypeUserImpl#getTuples <em>Tuples</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AstTypeUserImpl extends AstVariableImpl implements AstTypeUser
{
  /**
   * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParameters()
   * @generated
   * @ordered
   */
  protected EList<AstTypeDefinitionParameter> parameters;

  /**
   * The cached value of the '{@link #getTuples() <em>Tuples</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTuples()
   * @generated
   * @ordered
   */
  protected EList<AstTaggedTuple> tuples;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AstTypeUserImpl()
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
    return CalPackage.Literals.AST_TYPE_USER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AstTypeDefinitionParameter> getParameters()
  {
    if (parameters == null)
    {
      parameters = new EObjectContainmentEList<AstTypeDefinitionParameter>(AstTypeDefinitionParameter.class, this, CalPackage.AST_TYPE_USER__PARAMETERS);
    }
    return parameters;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AstTaggedTuple> getTuples()
  {
    if (tuples == null)
    {
      tuples = new EObjectContainmentEList<AstTaggedTuple>(AstTaggedTuple.class, this, CalPackage.AST_TYPE_USER__TUPLES);
    }
    return tuples;
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
      case CalPackage.AST_TYPE_USER__PARAMETERS:
        return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_TYPE_USER__TUPLES:
        return ((InternalEList<?>)getTuples()).basicRemove(otherEnd, msgs);
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
      case CalPackage.AST_TYPE_USER__PARAMETERS:
        return getParameters();
      case CalPackage.AST_TYPE_USER__TUPLES:
        return getTuples();
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
      case CalPackage.AST_TYPE_USER__PARAMETERS:
        getParameters().clear();
        getParameters().addAll((Collection<? extends AstTypeDefinitionParameter>)newValue);
        return;
      case CalPackage.AST_TYPE_USER__TUPLES:
        getTuples().clear();
        getTuples().addAll((Collection<? extends AstTaggedTuple>)newValue);
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
      case CalPackage.AST_TYPE_USER__PARAMETERS:
        getParameters().clear();
        return;
      case CalPackage.AST_TYPE_USER__TUPLES:
        getTuples().clear();
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
      case CalPackage.AST_TYPE_USER__PARAMETERS:
        return parameters != null && !parameters.isEmpty();
      case CalPackage.AST_TYPE_USER__TUPLES:
        return tuples != null && !tuples.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //AstTypeUserImpl
