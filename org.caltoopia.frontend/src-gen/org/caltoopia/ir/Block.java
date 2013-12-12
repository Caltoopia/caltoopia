/**
 */
package org.caltoopia.ir;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Block</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.Block#getStatements <em>Statements</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getBlock()
 * @model
 * @generated
 */
public interface Block extends Scope, Statement {
	/**
     * Returns the value of the '<em><b>Statements</b></em>' reference list.
     * The list contents are of type {@link org.caltoopia.ir.Statement}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Statements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Statements</em>' reference list.
     * @see org.caltoopia.ir.IrPackage#getBlock_Statements()
     * @model
     * @generated
     */
	EList<Statement> getStatements();

} // Block
