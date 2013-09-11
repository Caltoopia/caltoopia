/**
 */
package org.caltoopia.ir;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sub Pattern</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.SubPattern#getLabel <em>Label</em>}</li>
 *   <li>{@link org.caltoopia.ir.SubPattern#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.caltoopia.ir.SubPattern#getBinding <em>Binding</em>}</li>
 *   <li>{@link org.caltoopia.ir.SubPattern#getPattern <em>Pattern</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getSubPattern()
 * @model
 * @generated
 */
public interface SubPattern extends EObject {
	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see #setLabel(String)
	 * @see org.caltoopia.ir.IrPackage#getSubPattern_Label()
	 * @model
	 * @generated
	 */
	String getLabel();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.SubPattern#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' attribute.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(String value);

	/**
	 * Returns the value of the '<em><b>Condition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' reference.
	 * @see #setCondition(Expression)
	 * @see org.caltoopia.ir.IrPackage#getSubPattern_Condition()
	 * @model
	 * @generated
	 */
	Expression getCondition();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.SubPattern#getCondition <em>Condition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' reference.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(Expression value);

	/**
	 * Returns the value of the '<em><b>Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Binding</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Binding</em>' reference.
	 * @see #setBinding(Variable)
	 * @see org.caltoopia.ir.IrPackage#getSubPattern_Binding()
	 * @model
	 * @generated
	 */
	Variable getBinding();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.SubPattern#getBinding <em>Binding</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding</em>' reference.
	 * @see #getBinding()
	 * @generated
	 */
	void setBinding(Variable value);

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
	 * @see org.caltoopia.ir.IrPackage#getSubPattern_Pattern()
	 * @model
	 * @generated
	 */
	Pattern getPattern();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.SubPattern#getPattern <em>Pattern</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pattern</em>' reference.
	 * @see #getPattern()
	 * @generated
	 */
	void setPattern(Pattern value);

} // SubPattern
