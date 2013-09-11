/**
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Pattern</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.frontend.cal.AstPattern#getTag <em>Tag</em>}</li>
 *   <li>{@link org.caltoopia.frontend.cal.AstPattern#getSubpatterns <em>Subpatterns</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.frontend.cal.CalPackage#getAstPattern()
 * @model
 * @generated
 */
public interface AstPattern extends EObject
{
  /**
   * Returns the value of the '<em><b>Tag</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tag</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tag</em>' attribute.
   * @see #setTag(String)
   * @see org.caltoopia.frontend.cal.CalPackage#getAstPattern_Tag()
   * @model
   * @generated
   */
  String getTag();

  /**
   * Sets the value of the '{@link org.caltoopia.frontend.cal.AstPattern#getTag <em>Tag</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tag</em>' attribute.
   * @see #getTag()
   * @generated
   */
  void setTag(String value);

  /**
   * Returns the value of the '<em><b>Subpatterns</b></em>' containment reference list.
   * The list contents are of type {@link org.caltoopia.frontend.cal.AstSubPattern}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Subpatterns</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Subpatterns</em>' containment reference list.
   * @see org.caltoopia.frontend.cal.CalPackage#getAstPattern_Subpatterns()
   * @model containment="true"
   * @generated
   */
  EList<AstSubPattern> getSubpatterns();

} // AstPattern
