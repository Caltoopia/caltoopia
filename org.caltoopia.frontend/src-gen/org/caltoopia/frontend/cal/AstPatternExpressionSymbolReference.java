/**
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Pattern Expression Symbol Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstPatternExpressionSymbolReference#getSymbol <em>Symbol</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstPatternExpressionSymbolReference#getIndexes <em>Indexes</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstPatternExpressionSymbolReference#getMember <em>Member</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstPatternExpressionSymbolReference#getCtor <em>Ctor</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstPatternExpressionSymbolReference#isCall <em>Call</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstPatternExpressionSymbolReference#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstPatternExpressionSymbolReference()
 * @model
 * @generated
 */
public interface AstPatternExpressionSymbolReference extends AstExpression
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
   * @see org.caltoopia.frontend.cal.CalPackage#getAstPatternExpressionSymbolReference_Symbol()
   * @model
   * @generated
   */
  AstVariable getSymbol();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstPatternExpressionSymbolReference#getSymbol <em>Symbol</em>}' reference.
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
   * @see org.caltoopia.frontend.cal.CalPackage#getAstPatternExpressionSymbolReference_Indexes()
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
   * @see org.caltoopia.frontend.cal.CalPackage#getAstPatternExpressionSymbolReference_Member()
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
   * @see org.caltoopia.frontend.cal.CalPackage#getAstPatternExpressionSymbolReference_Ctor()
   * @model
   * @generated
   */
  String getCtor();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstPatternExpressionSymbolReference#getCtor <em>Ctor</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ctor</em>' attribute.
   * @see #getCtor()
   * @generated
   */
  void setCtor(String value);

  /**
   * Returns the value of the '<em><b>Call</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Call</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Call</em>' attribute.
   * @see #setCall(boolean)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstPatternExpressionSymbolReference_Call()
   * @model
   * @generated
   */
  boolean isCall();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstPatternExpressionSymbolReference#isCall <em>Call</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Call</em>' attribute.
   * @see #isCall()
   * @generated
   */
  void setCall(boolean value);

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
   * @see org.caltoopia.frontend.cal.CalPackage#getAstPatternExpressionSymbolReference_Parameters()
   * @model containment="true"
   * @generated
   */
  EList<AstExpression> getParameters();

} // AstPatternExpressionSymbolReference
