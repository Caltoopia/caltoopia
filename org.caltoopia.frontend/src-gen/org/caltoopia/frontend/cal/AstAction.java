/**
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstAction#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstAction#getTag <em>Tag</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstAction#getInputs <em>Inputs</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstAction#getOutputs <em>Outputs</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstAction#getGuards <em>Guards</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstAction#getVariables <em>Variables</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstAction#getStatements <em>Statements</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstAction()
 * @model
 * @generated
 */
public interface AstAction extends EObject
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
   * @see org.caltoopia.frontend.cal.CalPackage#getAstAction_Annotations()
   * @model containment="true"
   * @generated
   */
  EList<AstAnnotation> getAnnotations();

  /**
   * Returns the value of the '<em><b>Tag</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tag</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tag</em>' containment reference.
   * @see #setTag(AstTag)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstAction_Tag()
   * @model containment="true"
   * @generated
   */
  AstTag getTag();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstAction#getTag <em>Tag</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tag</em>' containment reference.
   * @see #getTag()
   * @generated
   */
  void setTag(AstTag value);

  /**
   * Returns the value of the '<em><b>Inputs</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstInputPattern}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Inputs</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Inputs</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstAction_Inputs()
   * @model containment="true"
   * @generated
   */
  EList<AstInputPattern> getInputs();

  /**
   * Returns the value of the '<em><b>Outputs</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstOutputPattern}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Outputs</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Outputs</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstAction_Outputs()
   * @model containment="true"
   * @generated
   */
  EList<AstOutputPattern> getOutputs();

  /**
   * Returns the value of the '<em><b>Guards</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstExpression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Guards</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Guards</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstAction_Guards()
   * @model containment="true"
   * @generated
   */
  EList<AstExpression> getGuards();

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
   * @see org.caltoopia.frontend.cal.CalPackage#getAstAction_Variables()
   * @model containment="true"
   * @generated
   */
  EList<AstVariable> getVariables();

  /**
   * Returns the value of the '<em><b>Statements</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstStatement}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Statements</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Statements</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstAction_Statements()
   * @model containment="true"
   * @generated
   */
  EList<AstStatement> getStatements();

} // AstAction
