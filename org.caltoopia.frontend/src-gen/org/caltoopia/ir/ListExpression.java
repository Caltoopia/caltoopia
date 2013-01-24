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
 * A representation of the model object '<em><b>List Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.ListExpression#getGenerators <em>Generators</em>}</li>
 *   <li>{@link org.caltoopia.ir.ListExpression#getExpressions <em>Expressions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getListExpression()
 * @model
 * @generated
 */
public interface ListExpression extends Expression {
	/**
	 * Returns the value of the '<em><b>Generators</b></em>' reference list.
	 * The list contents are of type {@link org.caltoopia.ir.Generator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generators</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generators</em>' reference list.
	 * @see org.caltoopia.ir.IrPackage#getListExpression_Generators()
	 * @model
	 * @generated
	 */
	EList<Generator> getGenerators();

	/**
	 * Returns the value of the '<em><b>Expressions</b></em>' reference list.
	 * The list contents are of type {@link org.caltoopia.ir.Expression}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expressions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expressions</em>' reference list.
	 * @see org.caltoopia.ir.IrPackage#getListExpression_Expressions()
	 * @model
	 * @generated
	 */
	EList<Expression> getExpressions();

} // ListExpression
