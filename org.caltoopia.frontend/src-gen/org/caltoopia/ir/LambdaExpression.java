/**
 */
package org.caltoopia.ir;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Lambda Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.LambdaExpression#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.caltoopia.ir.LambdaExpression#getBody <em>Body</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getLambdaExpression()
 * @model
 * @generated
 */
public interface LambdaExpression extends Scope, Expression {
	/**
     * Returns the value of the '<em><b>Parameters</b></em>' reference list.
     * The list contents are of type {@link org.caltoopia.ir.Variable}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameters</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Parameters</em>' reference list.
     * @see org.caltoopia.ir.IrPackage#getLambdaExpression_Parameters()
     * @model
     * @generated
     */
	EList<Variable> getParameters();

	/**
     * Returns the value of the '<em><b>Body</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Body</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Body</em>' reference.
     * @see #setBody(Expression)
     * @see org.caltoopia.ir.IrPackage#getLambdaExpression_Body()
     * @model
     * @generated
     */
	Expression getBody();

	/**
     * Sets the value of the '{@link org.caltoopia.ir.LambdaExpression#getBody <em>Body</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Body</em>' reference.
     * @see #getBody()
     * @generated
     */
	void setBody(Expression value);

} // LambdaExpression
