/**
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Actor Variable Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstActorVariableReference#getVariable <em>Variable</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstActorVariableReference()
 * @model
 * @generated
 */
public interface AstActorVariableReference extends EObject
{
  /**
   * Returns the value of the '<em><b>Variable</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Variable</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Variable</em>' reference.
   * @see #setVariable(AstActorVariable)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstActorVariableReference_Variable()
   * @model
   * @generated
   */
  AstActorVariable getVariable();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstActorVariableReference#getVariable <em>Variable</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Variable</em>' reference.
   * @see #getVariable()
   * @generated
   */
  void setVariable(AstActorVariable value);

} // AstActorVariableReference
