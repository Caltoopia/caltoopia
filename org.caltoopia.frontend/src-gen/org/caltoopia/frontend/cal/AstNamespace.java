/**
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Namespace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstNamespace#getName <em>Name</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstNamespace#getEntities <em>Entities</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstNamespace#getImports <em>Imports</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstNamespace#getUnits <em>Units</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstNamespace#getFunctions <em>Functions</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstNamespace#getVariables <em>Variables</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstNamespace#getExternals <em>Externals</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstNamespace#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstNamespace#getTypedefs <em>Typedefs</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstNamespace#getNamespaces <em>Namespaces</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstNamespace()
 * @model
 * @generated
 */
public interface AstNamespace extends AstTop, AstPackage, AstUnit
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstNamespace_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstNamespace#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Entities</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstEntity}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Entities</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Entities</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstNamespace_Entities()
   * @model containment="true"
   * @generated
   */
  EList<AstEntity> getEntities();

  /**
   * Returns the value of the '<em><b>Imports</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.Import}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Imports</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Imports</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstNamespace_Imports()
   * @model containment="true"
   * @generated
   */
  EList<Import> getImports();

  /**
   * Returns the value of the '<em><b>Units</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstUnit}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Units</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Units</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstNamespace_Units()
   * @model containment="true"
   * @generated
   */
  EList<AstUnit> getUnits();

  /**
   * Returns the value of the '<em><b>Functions</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstFunction}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Functions</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Functions</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstNamespace_Functions()
   * @model containment="true"
   * @generated
   */
  EList<AstFunction> getFunctions();

  /**
   * Returns the value of the '<em><b>Variables</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstVariable}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Variables</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Variables</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstNamespace_Variables()
   * @model containment="true"
   * @generated
   */
  EList<AstVariable> getVariables();

  /**
   * Returns the value of the '<em><b>Externals</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Externals</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Externals</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstNamespace_Externals()
   * @model containment="true"
   * @generated
   */
  EList<EObject> getExternals();

  /**
   * Returns the value of the '<em><b>Annotations</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstAnnotation}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Annotations</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotations</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstNamespace_Annotations()
   * @model containment="true"
   * @generated
   */
  EList<AstAnnotation> getAnnotations();

  /**
   * Returns the value of the '<em><b>Typedefs</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstTypeName}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Typedefs</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Typedefs</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstNamespace_Typedefs()
   * @model containment="true"
   * @generated
   */
  EList<AstTypeName> getTypedefs();

  /**
   * Returns the value of the '<em><b>Namespaces</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstNamespace}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Namespaces</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Namespaces</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstNamespace_Namespaces()
   * @model containment="true"
   * @generated
   */
  EList<AstNamespace> getNamespaces();

} // AstNamespace
