/**
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Expression Construction</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstExpressionConstruction#getFunction <em>Function</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstExpressionConstruction#getCtor <em>Ctor</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstExpressionConstruction#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionConstruction()
 * @model
 * @generated
 */
public interface AstExpressionConstruction extends AstExpression
{
  /**
   * Returns the value of the '<em><b>Function</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Function</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Function</em>' reference.
   * @see #setFunction(AstTypeName)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionConstruction_Function()
   * @model
   * @generated
   */
  AstTypeName getFunction();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstExpressionConstruction#getFunction <em>Function</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Function</em>' reference.
   * @see #getFunction()
   * @generated
   */
  void setFunction(AstTypeName value);

  /**
   * Returns the value of the '<em><b>Ctor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ctor</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ctor</em>' attribute.
   * @see #setCtor(String)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionConstruction_Ctor()
   * @model
   * @generated
   */
  String getCtor();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstExpressionConstruction#getCtor <em>Ctor</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ctor</em>' attribute.
   * @see #getCtor()
   * @generated
   */
  void setCtor(String value);

  /**
   * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstExpression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameters</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionConstruction_Parameters()
   * @model containment="true"
   * @generated
   */
  EList<AstExpression> getParameters();

} // AstExpressionConstruction
