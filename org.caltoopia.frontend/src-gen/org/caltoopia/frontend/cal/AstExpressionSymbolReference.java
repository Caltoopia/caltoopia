/**
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Expression Symbol Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstExpressionSymbolReference#getSymbol <em>Symbol</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstExpressionSymbolReference#getIndexes <em>Indexes</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstExpressionSymbolReference#getMember <em>Member</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstExpressionSymbolReference#getCtor <em>Ctor</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstExpressionSymbolReference#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionSymbolReference()
 * @model
 * @generated
 */
public interface AstExpressionSymbolReference extends AstExpression
{
  /**
   * Returns the value of the '<em><b>Symbol</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Symbol</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Symbol</em>' reference.
   * @see #setSymbol(AstVariable)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionSymbolReference_Symbol()
   * @model
   * @generated
   */
  AstVariable getSymbol();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstExpressionSymbolReference#getSymbol <em>Symbol</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Symbol</em>' reference.
   * @see #getSymbol()
   * @generated
   */
  void setSymbol(AstVariable value);

  /**
   * Returns the value of the '<em><b>Indexes</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstExpression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Indexes</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Indexes</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionSymbolReference_Indexes()
   * @model containment="true"
   * @generated
   */
  EList<AstExpression> getIndexes();

  /**
   * Returns the value of the '<em><b>Member</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstMemberAccess}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Member</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Member</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionSymbolReference_Member()
   * @model containment="true"
   * @generated
   */
  EList<AstMemberAccess> getMember();

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
   * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionSymbolReference_Ctor()
   * @model
   * @generated
   */
  String getCtor();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstExpressionSymbolReference#getCtor <em>Ctor</em>}' attribute.
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
   * @see org.caltoopia.frontend.cal.CalPackage#getAstExpressionSymbolReference_Parameters()
   * @model containment="true"
   * @generated
   */
  EList<AstExpression> getParameters();

} // AstExpressionSymbolReference
