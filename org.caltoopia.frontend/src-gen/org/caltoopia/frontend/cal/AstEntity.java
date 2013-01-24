/**
 * <copyright>
 * </copyright>
 *
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Entity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstEntity#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstEntity#getName <em>Name</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstEntity#getActor <em>Actor</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstEntity#getNetwork <em>Network</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstEntity#getExternal <em>External</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstEntity()
 * @model
 * @generated
 */
public interface AstEntity extends EObject
{
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
   * @see org.caltoopia.frontend.cal.CalPackage#getAstEntity_Annotations()
   * @model containment="true"
   * @generated
   */
  EList<AstAnnotation> getAnnotations();

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
   * @see org.caltoopia.frontend.cal.CalPackage#getAstEntity_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstEntity#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Actor</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Actor</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Actor</em>' containment reference.
   * @see #setActor(AstActor)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstEntity_Actor()
   * @model containment="true"
   * @generated
   */
  AstActor getActor();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstEntity#getActor <em>Actor</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Actor</em>' containment reference.
   * @see #getActor()
   * @generated
   */
  void setActor(AstActor value);

  /**
   * Returns the value of the '<em><b>Network</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Network</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Network</em>' containment reference.
   * @see #setNetwork(AstNetwork)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstEntity_Network()
   * @model containment="true"
   * @generated
   */
  AstNetwork getNetwork();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstEntity#getNetwork <em>Network</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Network</em>' containment reference.
   * @see #getNetwork()
   * @generated
   */
  void setNetwork(AstNetwork value);

  /**
   * Returns the value of the '<em><b>External</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>External</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>External</em>' containment reference.
   * @see #setExternal(AstExternalActor)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstEntity_External()
   * @model containment="true"
   * @generated
   */
  AstExternalActor getExternal();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstEntity#getExternal <em>External</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>External</em>' containment reference.
   * @see #getExternal()
   * @generated
   */
  void setExternal(AstExternalActor value);

} // AstEntity
