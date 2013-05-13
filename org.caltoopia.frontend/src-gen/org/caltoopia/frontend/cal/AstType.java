/**
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstType#getBuiltin <em>Builtin</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstType#getTypeParams <em>Type Params</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstType#getDimensions <em>Dimensions</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstType#getName <em>Name</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstType#getDomain <em>Domain</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstType#getCodomain <em>Codomain</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstType#getMembers <em>Members</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstType()
 * @model
 * @generated
 */
public interface AstType extends EObject
{
  /**
   * Returns the value of the '<em><b>Builtin</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Builtin</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Builtin</em>' attribute.
   * @see #setBuiltin(String)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstType_Builtin()
   * @model
   * @generated
   */
  String getBuiltin();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstType#getBuiltin <em>Builtin</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Builtin</em>' attribute.
   * @see #getBuiltin()
   * @generated
   */
  void setBuiltin(String value);

  /**
   * Returns the value of the '<em><b>Type Params</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type Params</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type Params</em>' containment reference.
   * @see #setTypeParams(AstTypeParameterList)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstType_TypeParams()
   * @model containment="true"
   * @generated
   */
  AstTypeParameterList getTypeParams();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstType#getTypeParams <em>Type Params</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type Params</em>' containment reference.
   * @see #getTypeParams()
   * @generated
   */
  void setTypeParams(AstTypeParameterList value);

  /**
   * Returns the value of the '<em><b>Dimensions</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstExpression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Dimensions</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Dimensions</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstType_Dimensions()
   * @model containment="true"
   * @generated
   */
  EList<AstExpression> getDimensions();

  /**
   * Returns the value of the '<em><b>Name</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' reference.
   * @see #setName(AstTypeName)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstType_Name()
   * @model
   * @generated
   */
  AstTypeName getName();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstType#getName <em>Name</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' reference.
   * @see #getName()
   * @generated
   */
  void setName(AstTypeName value);

  /**
   * Returns the value of the '<em><b>Domain</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Domain</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Domain</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstType_Domain()
   * @model containment="true"
   * @generated
   */
  EList<AstType> getDomain();

  /**
   * Returns the value of the '<em><b>Codomain</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Codomain</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Codomain</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstType_Codomain()
   * @model containment="true"
   * @generated
   */
  EList<AstType> getCodomain();

  /**
   * Returns the value of the '<em><b>Members</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstVariable}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Members</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Members</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstType_Members()
   * @model containment="true"
   * @generated
   */
  EList<AstVariable> getMembers();

} // AstType
