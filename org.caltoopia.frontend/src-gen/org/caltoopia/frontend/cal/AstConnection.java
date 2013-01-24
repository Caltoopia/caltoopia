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
 * A representation of the model object '<em><b>Ast Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstConnection#getFrom <em>From</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstConnection#getOutPort <em>Out Port</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstConnection#getTo <em>To</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstConnection#getInPort <em>In Port</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstConnection#getAttribute <em>Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstConnection()
 * @model
 * @generated
 */
public interface AstConnection extends EObject
{
  /**
   * Returns the value of the '<em><b>From</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>From</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>From</em>' containment reference.
   * @see #setFrom(AstActorVariableReference)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstConnection_From()
   * @model containment="true"
   * @generated
   */
  AstActorVariableReference getFrom();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstConnection#getFrom <em>From</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>From</em>' containment reference.
   * @see #getFrom()
   * @generated
   */
  void setFrom(AstActorVariableReference value);

  /**
   * Returns the value of the '<em><b>Out Port</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Out Port</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Out Port</em>' attribute.
   * @see #setOutPort(String)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstConnection_OutPort()
   * @model
   * @generated
   */
  String getOutPort();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstConnection#getOutPort <em>Out Port</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Out Port</em>' attribute.
   * @see #getOutPort()
   * @generated
   */
  void setOutPort(String value);

  /**
   * Returns the value of the '<em><b>To</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>To</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>To</em>' containment reference.
   * @see #setTo(AstActorVariableReference)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstConnection_To()
   * @model containment="true"
   * @generated
   */
  AstActorVariableReference getTo();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstConnection#getTo <em>To</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>To</em>' containment reference.
   * @see #getTo()
   * @generated
   */
  void setTo(AstActorVariableReference value);

  /**
   * Returns the value of the '<em><b>In Port</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>In Port</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>In Port</em>' attribute.
   * @see #setInPort(String)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstConnection_InPort()
   * @model
   * @generated
   */
  String getInPort();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstConnection#getInPort <em>In Port</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>In Port</em>' attribute.
   * @see #getInPort()
   * @generated
   */
  void setInPort(String value);

  /**
   * Returns the value of the '<em><b>Attribute</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstConnectionAttribute}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Attribute</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attribute</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstConnection_Attribute()
   * @model containment="true"
   * @generated
   */
  EList<AstConnectionAttribute> getAttribute();

} // AstConnection
