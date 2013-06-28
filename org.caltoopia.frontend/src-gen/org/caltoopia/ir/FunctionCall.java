/**
 */
package org.caltoopia.ir;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function Call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.FunctionCall#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.caltoopia.ir.FunctionCall#getFunction <em>Function</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getFunctionCall()
 * @model
 * @generated
 */
public interface FunctionCall extends ExpressionCall {
	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' reference list.
	 * The list contents are of type {@link org.caltoopia.ir.Expression}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameters</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameters</em>' reference list.
	 * @see org.caltoopia.ir.IrPackage#getFunctionCall_Parameters()
	 * @model
	 * @generated
	 */
	EList<Expression> getParameters();

	/**
	 * Returns the value of the '<em><b>Function</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Function</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Function</em>' reference.
	 * @see #setFunction(Expression)
	 * @see org.caltoopia.ir.IrPackage#getFunctionCall_Function()
	 * @model
	 * @generated
	 */
	Expression getFunction();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.FunctionCall#getFunction <em>Function</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Function</em>' reference.
	 * @see #getFunction()
	 * @generated
	 */
	void setFunction(Expression value);

} // FunctionCall
