/**
 */
package org.caltoopia.ir;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Namespace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.Namespace#getName <em>Name</em>}</li>
 *   <li>{@link org.caltoopia.ir.Namespace#getActors <em>Actors</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getNamespace()
 * @model
 * @generated
 */
public interface Namespace extends Scope {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute list.
	 * @see org.caltoopia.ir.IrPackage#getNamespace_Name()
	 * @model
	 * @generated
	 */
	EList<String> getName();

	/**
	 * Returns the value of the '<em><b>Actors</b></em>' reference list.
	 * The list contents are of type {@link org.caltoopia.ir.AbstractActor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actors</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actors</em>' reference list.
	 * @see org.caltoopia.ir.IrPackage#getNamespace_Actors()
	 * @model
	 * @generated
	 */
	EList<AbstractActor> getActors();

} // Namespace
