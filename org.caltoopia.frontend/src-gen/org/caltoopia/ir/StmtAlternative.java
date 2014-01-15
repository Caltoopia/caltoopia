/**
 */
package org.caltoopia.ir;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stmt Alternative</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.StmtAlternative#getGuards <em>Guards</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getStmtAlternative()
 * @model
 * @generated
 */
public interface StmtAlternative extends Block {
	/**
	 * Returns the value of the '<em><b>Guards</b></em>' reference list.
	 * The list contents are of type {@link org.caltoopia.ir.Expression}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Guards</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Guards</em>' reference list.
	 * @see org.caltoopia.ir.IrPackage#getStmtAlternative_Guards()
	 * @model
	 * @generated
	 */
	EList<Expression> getGuards();

} // StmtAlternative
