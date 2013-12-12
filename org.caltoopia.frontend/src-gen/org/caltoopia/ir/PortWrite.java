/**
 */
package org.caltoopia.ir;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Port Write</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.PortWrite#getExpressions <em>Expressions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getPortWrite()
 * @model
 * @generated
 */
public interface PortWrite extends Block, PortAccess {
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
     * @see org.caltoopia.ir.IrPackage#getPortWrite_Expressions()
     * @model
     * @generated
     */
	EList<Expression> getExpressions();

} // PortWrite
