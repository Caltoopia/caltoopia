/**
 */
package org.caltoopia.ir;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Port Guard</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.PortGuard#getPeeks <em>Peeks</em>}</li>
 *   <li>{@link org.caltoopia.ir.PortGuard#getFieldReads <em>Field Reads</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getPortGuard()
 * @model
 * @generated
 */
public interface PortGuard extends Guard {
	/**
	 * Returns the value of the '<em><b>Peeks</b></em>' reference list.
	 * The list contents are of type {@link org.caltoopia.ir.PortPeek}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Peeks</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Peeks</em>' reference list.
	 * @see org.caltoopia.ir.IrPackage#getPortGuard_Peeks()
	 * @model
	 * @generated
	 */
	EList<PortPeek> getPeeks();

	/**
	 * Returns the value of the '<em><b>Field Reads</b></em>' reference list.
	 * The list contents are of type {@link org.caltoopia.ir.TaggedTupleFieldRead}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Field Reads</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Field Reads</em>' reference list.
	 * @see org.caltoopia.ir.IrPackage#getPortGuard_FieldReads()
	 * @model
	 * @generated
	 */
	EList<TaggedTupleFieldRead> getFieldReads();

} // PortGuard
