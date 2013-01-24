/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.caltoopia.ir;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Declaration Import</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.TypeDeclarationImport#getNamespace <em>Namespace</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getTypeDeclarationImport()
 * @model
 * @generated
 */
public interface TypeDeclarationImport extends Declaration {
	/**
	 * Returns the value of the '<em><b>Namespace</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Namespace</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Namespace</em>' attribute list.
	 * @see org.caltoopia.ir.IrPackage#getTypeDeclarationImport_Namespace()
	 * @model
	 * @generated
	 */
	EList<String> getNamespace();

} // TypeDeclarationImport
