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
 *   <li>{@link org.caltoopia.ir.StmtAlternative#getPattern <em>Pattern</em>}</li>
 *   <li>{@link org.caltoopia.ir.StmtAlternative#getGuards <em>Guards</em>}</li>
 *   <li>{@link org.caltoopia.ir.StmtAlternative#getStatement <em>Statement</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getStmtAlternative()
 * @model
 * @generated
 */
public interface StmtAlternative extends Scope {
	/**
	 * Returns the value of the '<em><b>Pattern</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pattern</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pattern</em>' reference.
	 * @see #setPattern(Pattern)
	 * @see org.caltoopia.ir.IrPackage#getStmtAlternative_Pattern()
	 * @model
	 * @generated
	 */
	Pattern getPattern();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.StmtAlternative#getPattern <em>Pattern</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pattern</em>' reference.
	 * @see #getPattern()
	 * @generated
	 */
	void setPattern(Pattern value);

	/**
	 * Returns the value of the '<em><b>Guards</b></em>' reference list.
	 * The list contents are of type {@link org.caltoopia.ir.Guard}.
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
	EList<Guard> getGuards();

	/**
	 * Returns the value of the '<em><b>Statement</b></em>' reference list.
	 * The list contents are of type {@link org.caltoopia.ir.Statement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Statement</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Statement</em>' reference list.
	 * @see org.caltoopia.ir.IrPackage#getStmtAlternative_Statement()
	 * @model
	 * @generated
	 */
	EList<Statement> getStatement();

} // StmtAlternative
