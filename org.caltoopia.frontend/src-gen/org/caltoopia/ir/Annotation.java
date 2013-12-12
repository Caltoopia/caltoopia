/**
 */
package org.caltoopia.ir;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Annotation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.Annotation#getName <em>Name</em>}</li>
 *   <li>{@link org.caltoopia.ir.Annotation#getArguments <em>Arguments</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getAnnotation()
 * @model
 * @generated
 */
public interface Annotation extends EObject {
	/**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.caltoopia.ir.IrPackage#getAnnotation_Name()
     * @model
     * @generated
     */
	String getName();

	/**
     * Sets the value of the '{@link org.caltoopia.ir.Annotation#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
	void setName(String value);

	/**
     * Returns the value of the '<em><b>Arguments</b></em>' reference list.
     * The list contents are of type {@link org.caltoopia.ir.AnnotationArgument}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Arguments</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Arguments</em>' reference list.
     * @see org.caltoopia.ir.IrPackage#getAnnotation_Arguments()
     * @model
     * @generated
     */
	EList<AnnotationArgument> getArguments();

} // Annotation
