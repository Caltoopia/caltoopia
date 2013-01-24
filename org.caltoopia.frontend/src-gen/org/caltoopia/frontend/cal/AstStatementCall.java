/**
 * <copyright>
 * </copyright>
 *
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Statement Call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstStatementCall#getProcedure <em>Procedure</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstStatementCall#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstStatementCall()
 * @model
 * @generated
 */
public interface AstStatementCall extends AstStatement
{
  /**
   * Returns the value of the '<em><b>Procedure</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Procedure</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Procedure</em>' reference.
   * @see #setProcedure(AstProcedure)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstStatementCall_Procedure()
   * @model
   * @generated
   */
  AstProcedure getProcedure();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstStatementCall#getProcedure <em>Procedure</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Procedure</em>' reference.
   * @see #getProcedure()
   * @generated
   */
  void setProcedure(AstProcedure value);

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
   * @see org.caltoopia.frontend.cal.CalPackage#getAstStatementCall_Parameters()
   * @model containment="true"
   * @generated
   */
  EList<AstExpression> getParameters();

} // AstStatementCall
