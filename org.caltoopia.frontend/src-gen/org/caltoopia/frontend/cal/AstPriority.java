/**
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Priority</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstPriority#getInequalities <em>Inequalities</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstPriority()
 * @model
 * @generated
 */
public interface AstPriority extends EObject
{
  /**
   * Returns the value of the '<em><b>Inequalities</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstInequality}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Inequalities</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Inequalities</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstPriority_Inequalities()
   * @model containment="true"
   * @generated
   */
  EList<AstInequality> getInequalities();

} // AstPriority
