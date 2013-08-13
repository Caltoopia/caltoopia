/**
 */
package org.caltoopia.frontend.cal.impl;

import java.util.Collection;

import org.caltoopia.frontend.cal.AstTaggedTuple;
import org.caltoopia.frontend.cal.AstTypeDefinitionParameter;
import org.caltoopia.frontend.cal.AstTypeUser;
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
 * An implementation of the model object '<em><b>Ast Type User</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstTypeUserImpl#isDefinition <em>Definition</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstTypeUserImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstTypeUserImpl#getTuples <em>Tuples</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstTypeUserImpl#isVariable <em>Variable</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AstTypeUserImpl extends AstVariableImpl implements AstTypeUser
{
  /**
   * The default value of the '{@link #isDefinition() <em>Definition</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isDefinition()
   * @generated
   * @ordered
   */
  protected static final boolean DEFINITION_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isDefinition() <em>Definition</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isDefinition()
   * @generated
   * @ordered
   */
  protected boolean definition = DEFINITION_EDEFAULT;

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
   * The default value of the '{@link #isVariable() <em>Variable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isVariable()
   * @generated
   * @ordered
   */
  protected static final boolean VARIABLE_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isVariable() <em>Variable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isVariable()
   * @generated
   * @ordered
   */
  protected boolean variable = VARIABLE_EDEFAULT;

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
  public boolean isDefinition()
  {
    return definition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDefinition(boolean newDefinition)
  {
    boolean oldDefinition = definition;
    definition = newDefinition;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_TYPE_USER__DEFINITION, oldDefinition, definition));
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
  public boolean isVariable()
  {
    return variable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVariable(boolean newVariable)
  {
    boolean oldVariable = variable;
    variable = newVariable;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_TYPE_USER__VARIABLE, oldVariable, variable));
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
      case CalPackage.AST_TYPE_USER__DEFINITION:
        return isDefinition();
      case CalPackage.AST_TYPE_USER__PARAMETERS:
        return getParameters();
      case CalPackage.AST_TYPE_USER__TUPLES:
        return getTuples();
      case CalPackage.AST_TYPE_USER__VARIABLE:
        return isVariable();
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
      case CalPackage.AST_TYPE_USER__DEFINITION:
        setDefinition((Boolean)newValue);
        return;
      case CalPackage.AST_TYPE_USER__PARAMETERS:
        getParameters().clear();
        getParameters().addAll((Collection<? extends AstTypeDefinitionParameter>)newValue);
        return;
      case CalPackage.AST_TYPE_USER__TUPLES:
        getTuples().clear();
        getTuples().addAll((Collection<? extends AstTaggedTuple>)newValue);
        return;
      case CalPackage.AST_TYPE_USER__VARIABLE:
        setVariable((Boolean)newValue);
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
      case CalPackage.AST_TYPE_USER__DEFINITION:
        setDefinition(DEFINITION_EDEFAULT);
        return;
      case CalPackage.AST_TYPE_USER__PARAMETERS:
        getParameters().clear();
        return;
      case CalPackage.AST_TYPE_USER__TUPLES:
        getTuples().clear();
        return;
      case CalPackage.AST_TYPE_USER__VARIABLE:
        setVariable(VARIABLE_EDEFAULT);
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
      case CalPackage.AST_TYPE_USER__DEFINITION:
        return definition != DEFINITION_EDEFAULT;
      case CalPackage.AST_TYPE_USER__PARAMETERS:
        return parameters != null && !parameters.isEmpty();
      case CalPackage.AST_TYPE_USER__TUPLES:
        return tuples != null && !tuples.isEmpty();
      case CalPackage.AST_TYPE_USER__VARIABLE:
        return variable != VARIABLE_EDEFAULT;
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
    result.append(" (definition: ");
    result.append(definition);
    result.append(", variable: ");
    result.append(variable);
    result.append(')');
    return result.toString();
  }

} //AstTypeUserImpl
