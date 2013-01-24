/**
 * <copyright>
 * </copyright>
 *
 */
package org.caltoopia.frontend.cal.impl;

import java.util.Collection;

import org.caltoopia.frontend.cal.AstExternalActor;
import org.caltoopia.frontend.cal.AstPort;
import org.caltoopia.frontend.cal.AstVariable;
import org.caltoopia.frontend.cal.CalPackage;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ast External Actor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstExternalActorImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstExternalActorImpl#getInputs <em>Inputs</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstExternalActorImpl#getOutputs <em>Outputs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AstExternalActorImpl extends MinimalEObjectImpl.Container implements AstExternalActor
{
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
   * The cached value of the '{@link #getInputs() <em>Inputs</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInputs()
   * @generated
   * @ordered
   */
  protected EList<AstPort> inputs;

  /**
   * The cached value of the '{@link #getOutputs() <em>Outputs</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOutputs()
   * @generated
   * @ordered
   */
  protected EList<AstPort> outputs;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AstExternalActorImpl()
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
    return CalPackage.Literals.AST_EXTERNAL_ACTOR;
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
      parameters = new EObjectContainmentEList<AstVariable>(AstVariable.class, this, CalPackage.AST_EXTERNAL_ACTOR__PARAMETERS);
    }
    return parameters;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AstPort> getInputs()
  {
    if (inputs == null)
    {
      inputs = new EObjectContainmentEList<AstPort>(AstPort.class, this, CalPackage.AST_EXTERNAL_ACTOR__INPUTS);
    }
    return inputs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AstPort> getOutputs()
  {
    if (outputs == null)
    {
      outputs = new EObjectContainmentEList<AstPort>(AstPort.class, this, CalPackage.AST_EXTERNAL_ACTOR__OUTPUTS);
    }
    return outputs;
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
      case CalPackage.AST_EXTERNAL_ACTOR__PARAMETERS:
        return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_EXTERNAL_ACTOR__INPUTS:
        return ((InternalEList<?>)getInputs()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_EXTERNAL_ACTOR__OUTPUTS:
        return ((InternalEList<?>)getOutputs()).basicRemove(otherEnd, msgs);
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
      case CalPackage.AST_EXTERNAL_ACTOR__PARAMETERS:
        return getParameters();
      case CalPackage.AST_EXTERNAL_ACTOR__INPUTS:
        return getInputs();
      case CalPackage.AST_EXTERNAL_ACTOR__OUTPUTS:
        return getOutputs();
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
      case CalPackage.AST_EXTERNAL_ACTOR__PARAMETERS:
        getParameters().clear();
        getParameters().addAll((Collection<? extends AstVariable>)newValue);
        return;
      case CalPackage.AST_EXTERNAL_ACTOR__INPUTS:
        getInputs().clear();
        getInputs().addAll((Collection<? extends AstPort>)newValue);
        return;
      case CalPackage.AST_EXTERNAL_ACTOR__OUTPUTS:
        getOutputs().clear();
        getOutputs().addAll((Collection<? extends AstPort>)newValue);
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
      case CalPackage.AST_EXTERNAL_ACTOR__PARAMETERS:
        getParameters().clear();
        return;
      case CalPackage.AST_EXTERNAL_ACTOR__INPUTS:
        getInputs().clear();
        return;
      case CalPackage.AST_EXTERNAL_ACTOR__OUTPUTS:
        getOutputs().clear();
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
      case CalPackage.AST_EXTERNAL_ACTOR__PARAMETERS:
        return parameters != null && !parameters.isEmpty();
      case CalPackage.AST_EXTERNAL_ACTOR__INPUTS:
        return inputs != null && !inputs.isEmpty();
      case CalPackage.AST_EXTERNAL_ACTOR__OUTPUTS:
        return outputs != null && !outputs.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //AstExternalActorImpl
