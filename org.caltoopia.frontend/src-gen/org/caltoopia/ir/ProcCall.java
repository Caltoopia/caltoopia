/**
 */
package org.caltoopia.ir;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Proc Call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.ProcCall#getInParameters <em>In Parameters</em>}</li>
 *   <li>{@link org.caltoopia.ir.ProcCall#getOutParameters <em>Out Parameters</em>}</li>
 *   <li>{@link org.caltoopia.ir.ProcCall#getProcedure <em>Procedure</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getProcCall()
 * @model
 * @generated
 */
public interface ProcCall extends Statement {
	/**
	 * Returns the value of the '<em><b>In Parameters</b></em>' reference list.
	 * The list contents are of type {@link org.caltoopia.ir.Expression}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Parameters</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Parameters</em>' reference list.
	 * @see org.caltoopia.ir.IrPackage#getProcCall_InParameters()
	 * @model
	 * @generated
	 */
	EList<Expression> getInParameters();

	/**
	 * Returns the value of the '<em><b>Out Parameters</b></em>' reference list.
	 * The list contents are of type {@link org.caltoopia.ir.VariableReference}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out Parameters</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out Parameters</em>' reference list.
	 * @see org.caltoopia.ir.IrPackage#getProcCall_OutParameters()
	 * @model
	 * @generated
	 */
	EList<VariableReference> getOutParameters();

	/**
	 * Returns the value of the '<em><b>Procedure</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Procedure</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Procedure</em>' reference.
	 * @see #setProcedure(Declaration)
	 * @see org.caltoopia.ir.IrPackage#getProcCall_Procedure()
	 * @model
	 * @generated
	 */
	Declaration getProcedure();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.ProcCall#getProcedure <em>Procedure</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Procedure</em>' reference.
	 * @see #getProcedure()
	 * @generated
	 */
	void setProcedure(Declaration value);

} // ProcCall
