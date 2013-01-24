/**
 * <copyright>
 * </copyright>
 *
 */
package org.caltoopia.frontend.cal.impl;

import java.util.Collection;

import org.caltoopia.frontend.cal.AstAction;
import org.caltoopia.frontend.cal.AstAnnotation;
import org.caltoopia.frontend.cal.AstExpression;
import org.caltoopia.frontend.cal.AstInputPattern;
import org.caltoopia.frontend.cal.AstOutputPattern;
import org.caltoopia.frontend.cal.AstStatement;
import org.caltoopia.frontend.cal.AstTag;
import org.caltoopia.frontend.cal.AstVariable;
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
 * An implementation of the model object '<em><b>Ast Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstActionImpl#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstActionImpl#getTag <em>Tag</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstActionImpl#getInputs <em>Inputs</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstActionImpl#getOutputs <em>Outputs</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstActionImpl#getGuards <em>Guards</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstActionImpl#getVariables <em>Variables</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstActionImpl#getStatements <em>Statements</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AstActionImpl extends MinimalEObjectImpl.Container implements AstAction
{
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
   * The cached value of the '{@link #getTag() <em>Tag</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTag()
   * @generated
   * @ordered
   */
  protected AstTag tag;

  /**
   * The cached value of the '{@link #getInputs() <em>Inputs</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInputs()
   * @generated
   * @ordered
   */
  protected EList<AstInputPattern> inputs;

  /**
   * The cached value of the '{@link #getOutputs() <em>Outputs</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOutputs()
   * @generated
   * @ordered
   */
  protected EList<AstOutputPattern> outputs;

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
  protected AstActionImpl()
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
    return CalPackage.Literals.AST_ACTION;
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
      annotations = new EObjectContainmentEList<AstAnnotation>(AstAnnotation.class, this, CalPackage.AST_ACTION__ANNOTATIONS);
    }
    return annotations;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstTag getTag()
  {
    return tag;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTag(AstTag newTag, NotificationChain msgs)
  {
    AstTag oldTag = tag;
    tag = newTag;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CalPackage.AST_ACTION__TAG, oldTag, newTag);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTag(AstTag newTag)
  {
    if (newTag != tag)
    {
      NotificationChain msgs = null;
      if (tag != null)
        msgs = ((InternalEObject)tag).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_ACTION__TAG, null, msgs);
      if (newTag != null)
        msgs = ((InternalEObject)newTag).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CalPackage.AST_ACTION__TAG, null, msgs);
      msgs = basicSetTag(newTag, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_ACTION__TAG, newTag, newTag));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AstInputPattern> getInputs()
  {
    if (inputs == null)
    {
      inputs = new EObjectContainmentEList<AstInputPattern>(AstInputPattern.class, this, CalPackage.AST_ACTION__INPUTS);
    }
    return inputs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AstOutputPattern> getOutputs()
  {
    if (outputs == null)
    {
      outputs = new EObjectContainmentEList<AstOutputPattern>(AstOutputPattern.class, this, CalPackage.AST_ACTION__OUTPUTS);
    }
    return outputs;
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
      guards = new EObjectContainmentEList<AstExpression>(AstExpression.class, this, CalPackage.AST_ACTION__GUARDS);
    }
    return guards;
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
      variables = new EObjectContainmentEList<AstVariable>(AstVariable.class, this, CalPackage.AST_ACTION__VARIABLES);
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
      statements = new EObjectContainmentEList<AstStatement>(AstStatement.class, this, CalPackage.AST_ACTION__STATEMENTS);
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
      case CalPackage.AST_ACTION__ANNOTATIONS:
        return ((InternalEList<?>)getAnnotations()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_ACTION__TAG:
        return basicSetTag(null, msgs);
      case CalPackage.AST_ACTION__INPUTS:
        return ((InternalEList<?>)getInputs()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_ACTION__OUTPUTS:
        return ((InternalEList<?>)getOutputs()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_ACTION__GUARDS:
        return ((InternalEList<?>)getGuards()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_ACTION__VARIABLES:
        return ((InternalEList<?>)getVariables()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_ACTION__STATEMENTS:
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
      case CalPackage.AST_ACTION__ANNOTATIONS:
        return getAnnotations();
      case CalPackage.AST_ACTION__TAG:
        return getTag();
      case CalPackage.AST_ACTION__INPUTS:
        return getInputs();
      case CalPackage.AST_ACTION__OUTPUTS:
        return getOutputs();
      case CalPackage.AST_ACTION__GUARDS:
        return getGuards();
      case CalPackage.AST_ACTION__VARIABLES:
        return getVariables();
      case CalPackage.AST_ACTION__STATEMENTS:
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
      case CalPackage.AST_ACTION__ANNOTATIONS:
        getAnnotations().clear();
        getAnnotations().addAll((Collection<? extends AstAnnotation>)newValue);
        return;
      case CalPackage.AST_ACTION__TAG:
        setTag((AstTag)newValue);
        return;
      case CalPackage.AST_ACTION__INPUTS:
        getInputs().clear();
        getInputs().addAll((Collection<? extends AstInputPattern>)newValue);
        return;
      case CalPackage.AST_ACTION__OUTPUTS:
        getOutputs().clear();
        getOutputs().addAll((Collection<? extends AstOutputPattern>)newValue);
        return;
      case CalPackage.AST_ACTION__GUARDS:
        getGuards().clear();
        getGuards().addAll((Collection<? extends AstExpression>)newValue);
        return;
      case CalPackage.AST_ACTION__VARIABLES:
        getVariables().clear();
        getVariables().addAll((Collection<? extends AstVariable>)newValue);
        return;
      case CalPackage.AST_ACTION__STATEMENTS:
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
      case CalPackage.AST_ACTION__ANNOTATIONS:
        getAnnotations().clear();
        return;
      case CalPackage.AST_ACTION__TAG:
        setTag((AstTag)null);
        return;
      case CalPackage.AST_ACTION__INPUTS:
        getInputs().clear();
        return;
      case CalPackage.AST_ACTION__OUTPUTS:
        getOutputs().clear();
        return;
      case CalPackage.AST_ACTION__GUARDS:
        getGuards().clear();
        return;
      case CalPackage.AST_ACTION__VARIABLES:
        getVariables().clear();
        return;
      case CalPackage.AST_ACTION__STATEMENTS:
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
      case CalPackage.AST_ACTION__ANNOTATIONS:
        return annotations != null && !annotations.isEmpty();
      case CalPackage.AST_ACTION__TAG:
        return tag != null;
      case CalPackage.AST_ACTION__INPUTS:
        return inputs != null && !inputs.isEmpty();
      case CalPackage.AST_ACTION__OUTPUTS:
        return outputs != null && !outputs.isEmpty();
      case CalPackage.AST_ACTION__GUARDS:
        return guards != null && !guards.isEmpty();
      case CalPackage.AST_ACTION__VARIABLES:
        return variables != null && !variables.isEmpty();
      case CalPackage.AST_ACTION__STATEMENTS:
        return statements != null && !statements.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //AstActionImpl
