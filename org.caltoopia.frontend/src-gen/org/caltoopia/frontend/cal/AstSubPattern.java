/**
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Sub Pattern</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstSubPattern#getLabel <em>Label</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstSubPattern#isDontcare <em>Dontcare</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstSubPattern#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstSubPattern#getVariable <em>Variable</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstSubPattern#getPattern <em>Pattern</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstSubPattern()
 * @model
 * @generated
 */
public interface AstSubPattern extends EObject
{
  /**
   * Returns the value of the '<em><b>Label</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Label</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Label</em>' attribute.
   * @see #setLabel(String)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstSubPattern_Label()
   * @model
   * @generated
   */
  String getLabel();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstSubPattern#getLabel <em>Label</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Label</em>' attribute.
   * @see #getLabel()
   * @generated
   */
  void setLabel(String value);

  /**
   * Returns the value of the '<em><b>Dontcare</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Dontcare</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Dontcare</em>' attribute.
   * @see #setDontcare(boolean)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstSubPattern_Dontcare()
   * @model
   * @generated
   */
  boolean isDontcare();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstSubPattern#isDontcare <em>Dontcare</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Dontcare</em>' attribute.
   * @see #isDontcare()
   * @generated
   */
  void setDontcare(boolean value);

  /**
   * Returns the value of the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Condition</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Condition</em>' containment reference.
   * @see #setCondition(AstExpression)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstSubPattern_Condition()
   * @model containment="true"
   * @generated
   */
  AstExpression getCondition();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstSubPattern#getCondition <em>Condition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Condition</em>' containment reference.
   * @see #getCondition()
   * @generated
   */
  void setCondition(AstExpression value);

  /**
   * Returns the value of the '<em><b>Variable</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Variable</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Variable</em>' containment reference.
   * @see #setVariable(AstVariable)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstSubPattern_Variable()
   * @model containment="true"
   * @generated
   */
  AstVariable getVariable();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstSubPattern#getVariable <em>Variable</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Variable</em>' containment reference.
   * @see #getVariable()
   * @generated
   */
  void setVariable(AstVariable value);

  /**
   * Returns the value of the '<em><b>Pattern</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Pattern</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Pattern</em>' containment reference.
   * @see #setPattern(AstPattern)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstSubPattern_Pattern()
   * @model containment="true"
   * @generated
   */
  AstPattern getPattern();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstSubPattern#getPattern <em>Pattern</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Pattern</em>' containment reference.
   * @see #getPattern()
   * @generated
   */
  void setPattern(AstPattern value);

} // AstSubPattern
