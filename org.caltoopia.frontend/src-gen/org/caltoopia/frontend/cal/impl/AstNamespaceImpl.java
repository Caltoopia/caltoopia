/**
 */
package org.caltoopia.frontend.cal.impl;

import java.util.Collection;

import org.caltoopia.frontend.cal.AstAnnotation;
import org.caltoopia.frontend.cal.AstEntity;
import org.caltoopia.frontend.cal.AstFunction;
import org.caltoopia.frontend.cal.AstNamespace;
import org.caltoopia.frontend.cal.AstTypeName;
import org.caltoopia.frontend.cal.AstUnit;
import org.caltoopia.frontend.cal.AstVariable;
import org.caltoopia.frontend.cal.CalPackage;
import org.caltoopia.frontend.cal.Import;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ast Namespace</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstNamespaceImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstNamespaceImpl#getEntities <em>Entities</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstNamespaceImpl#getImports <em>Imports</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstNamespaceImpl#getUnits <em>Units</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstNamespaceImpl#getFunctions <em>Functions</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstNamespaceImpl#getVariables <em>Variables</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstNamespaceImpl#getExternals <em>Externals</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstNamespaceImpl#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstNamespaceImpl#getTypedefs <em>Typedefs</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.impl.AstNamespaceImpl#getNamespaces <em>Namespaces</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AstNamespaceImpl extends AstTopImpl implements AstNamespace
{
  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getEntities() <em>Entities</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEntities()
   * @generated
   * @ordered
   */
  protected EList<AstEntity> entities;

  /**
   * The cached value of the '{@link #getImports() <em>Imports</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getImports()
   * @generated
   * @ordered
   */
  protected EList<Import> imports;

  /**
   * The cached value of the '{@link #getUnits() <em>Units</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUnits()
   * @generated
   * @ordered
   */
  protected EList<AstUnit> units;

  /**
   * The cached value of the '{@link #getFunctions() <em>Functions</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFunctions()
   * @generated
   * @ordered
   */
  protected EList<AstFunction> functions;

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
   * The cached value of the '{@link #getExternals() <em>Externals</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExternals()
   * @generated
   * @ordered
   */
  protected EList<EObject> externals;

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
   * The cached value of the '{@link #getTypedefs() <em>Typedefs</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypedefs()
   * @generated
   * @ordered
   */
  protected EList<AstTypeName> typedefs;

  /**
   * The cached value of the '{@link #getNamespaces() <em>Namespaces</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNamespaces()
   * @generated
   * @ordered
   */
  protected EList<AstNamespace> namespaces;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AstNamespaceImpl()
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
    return CalPackage.Literals.AST_NAMESPACE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CalPackage.AST_NAMESPACE__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AstEntity> getEntities()
  {
    if (entities == null)
    {
      entities = new EObjectContainmentEList<AstEntity>(AstEntity.class, this, CalPackage.AST_NAMESPACE__ENTITIES);
    }
    return entities;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Import> getImports()
  {
    if (imports == null)
    {
      imports = new EObjectContainmentEList<Import>(Import.class, this, CalPackage.AST_NAMESPACE__IMPORTS);
    }
    return imports;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AstUnit> getUnits()
  {
    if (units == null)
    {
      units = new EObjectContainmentEList<AstUnit>(AstUnit.class, this, CalPackage.AST_NAMESPACE__UNITS);
    }
    return units;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AstFunction> getFunctions()
  {
    if (functions == null)
    {
      functions = new EObjectContainmentEList<AstFunction>(AstFunction.class, this, CalPackage.AST_NAMESPACE__FUNCTIONS);
    }
    return functions;
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
      variables = new EObjectContainmentEList<AstVariable>(AstVariable.class, this, CalPackage.AST_NAMESPACE__VARIABLES);
    }
    return variables;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EObject> getExternals()
  {
    if (externals == null)
    {
      externals = new EObjectContainmentEList<EObject>(EObject.class, this, CalPackage.AST_NAMESPACE__EXTERNALS);
    }
    return externals;
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
      annotations = new EObjectContainmentEList<AstAnnotation>(AstAnnotation.class, this, CalPackage.AST_NAMESPACE__ANNOTATIONS);
    }
    return annotations;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AstTypeName> getTypedefs()
  {
    if (typedefs == null)
    {
      typedefs = new EObjectContainmentEList<AstTypeName>(AstTypeName.class, this, CalPackage.AST_NAMESPACE__TYPEDEFS);
    }
    return typedefs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AstNamespace> getNamespaces()
  {
    if (namespaces == null)
    {
      namespaces = new EObjectContainmentEList<AstNamespace>(AstNamespace.class, this, CalPackage.AST_NAMESPACE__NAMESPACES);
    }
    return namespaces;
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
      case CalPackage.AST_NAMESPACE__ENTITIES:
        return ((InternalEList<?>)getEntities()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_NAMESPACE__IMPORTS:
        return ((InternalEList<?>)getImports()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_NAMESPACE__UNITS:
        return ((InternalEList<?>)getUnits()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_NAMESPACE__FUNCTIONS:
        return ((InternalEList<?>)getFunctions()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_NAMESPACE__VARIABLES:
        return ((InternalEList<?>)getVariables()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_NAMESPACE__EXTERNALS:
        return ((InternalEList<?>)getExternals()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_NAMESPACE__ANNOTATIONS:
        return ((InternalEList<?>)getAnnotations()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_NAMESPACE__TYPEDEFS:
        return ((InternalEList<?>)getTypedefs()).basicRemove(otherEnd, msgs);
      case CalPackage.AST_NAMESPACE__NAMESPACES:
        return ((InternalEList<?>)getNamespaces()).basicRemove(otherEnd, msgs);
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
      case CalPackage.AST_NAMESPACE__NAME:
        return getName();
      case CalPackage.AST_NAMESPACE__ENTITIES:
        return getEntities();
      case CalPackage.AST_NAMESPACE__IMPORTS:
        return getImports();
      case CalPackage.AST_NAMESPACE__UNITS:
        return getUnits();
      case CalPackage.AST_NAMESPACE__FUNCTIONS:
        return getFunctions();
      case CalPackage.AST_NAMESPACE__VARIABLES:
        return getVariables();
      case CalPackage.AST_NAMESPACE__EXTERNALS:
        return getExternals();
      case CalPackage.AST_NAMESPACE__ANNOTATIONS:
        return getAnnotations();
      case CalPackage.AST_NAMESPACE__TYPEDEFS:
        return getTypedefs();
      case CalPackage.AST_NAMESPACE__NAMESPACES:
        return getNamespaces();
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
      case CalPackage.AST_NAMESPACE__NAME:
        setName((String)newValue);
        return;
      case CalPackage.AST_NAMESPACE__ENTITIES:
        getEntities().clear();
        getEntities().addAll((Collection<? extends AstEntity>)newValue);
        return;
      case CalPackage.AST_NAMESPACE__IMPORTS:
        getImports().clear();
        getImports().addAll((Collection<? extends Import>)newValue);
        return;
      case CalPackage.AST_NAMESPACE__UNITS:
        getUnits().clear();
        getUnits().addAll((Collection<? extends AstUnit>)newValue);
        return;
      case CalPackage.AST_NAMESPACE__FUNCTIONS:
        getFunctions().clear();
        getFunctions().addAll((Collection<? extends AstFunction>)newValue);
        return;
      case CalPackage.AST_NAMESPACE__VARIABLES:
        getVariables().clear();
        getVariables().addAll((Collection<? extends AstVariable>)newValue);
        return;
      case CalPackage.AST_NAMESPACE__EXTERNALS:
        getExternals().clear();
        getExternals().addAll((Collection<? extends EObject>)newValue);
        return;
      case CalPackage.AST_NAMESPACE__ANNOTATIONS:
        getAnnotations().clear();
        getAnnotations().addAll((Collection<? extends AstAnnotation>)newValue);
        return;
      case CalPackage.AST_NAMESPACE__TYPEDEFS:
        getTypedefs().clear();
        getTypedefs().addAll((Collection<? extends AstTypeName>)newValue);
        return;
      case CalPackage.AST_NAMESPACE__NAMESPACES:
        getNamespaces().clear();
        getNamespaces().addAll((Collection<? extends AstNamespace>)newValue);
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
      case CalPackage.AST_NAMESPACE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case CalPackage.AST_NAMESPACE__ENTITIES:
        getEntities().clear();
        return;
      case CalPackage.AST_NAMESPACE__IMPORTS:
        getImports().clear();
        return;
      case CalPackage.AST_NAMESPACE__UNITS:
        getUnits().clear();
        return;
      case CalPackage.AST_NAMESPACE__FUNCTIONS:
        getFunctions().clear();
        return;
      case CalPackage.AST_NAMESPACE__VARIABLES:
        getVariables().clear();
        return;
      case CalPackage.AST_NAMESPACE__EXTERNALS:
        getExternals().clear();
        return;
      case CalPackage.AST_NAMESPACE__ANNOTATIONS:
        getAnnotations().clear();
        return;
      case CalPackage.AST_NAMESPACE__TYPEDEFS:
        getTypedefs().clear();
        return;
      case CalPackage.AST_NAMESPACE__NAMESPACES:
        getNamespaces().clear();
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
      case CalPackage.AST_NAMESPACE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case CalPackage.AST_NAMESPACE__ENTITIES:
        return entities != null && !entities.isEmpty();
      case CalPackage.AST_NAMESPACE__IMPORTS:
        return imports != null && !imports.isEmpty();
      case CalPackage.AST_NAMESPACE__UNITS:
        return units != null && !units.isEmpty();
      case CalPackage.AST_NAMESPACE__FUNCTIONS:
        return functions != null && !functions.isEmpty();
      case CalPackage.AST_NAMESPACE__VARIABLES:
        return variables != null && !variables.isEmpty();
      case CalPackage.AST_NAMESPACE__EXTERNALS:
        return externals != null && !externals.isEmpty();
      case CalPackage.AST_NAMESPACE__ANNOTATIONS:
        return annotations != null && !annotations.isEmpty();
      case CalPackage.AST_NAMESPACE__TYPEDEFS:
        return typedefs != null && !typedefs.isEmpty();
      case CalPackage.AST_NAMESPACE__NAMESPACES:
        return namespaces != null && !namespaces.isEmpty();
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
    result.append(" (name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //AstNamespaceImpl
