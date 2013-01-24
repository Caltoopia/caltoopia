/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.caltoopia.ir;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>To Sink</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.ToSink#getSource <em>Source</em>}</li>
 *   <li>{@link org.caltoopia.ir.ToSink#getSink <em>Sink</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getToSink()
 * @model
 * @generated
 */
public interface ToSink extends Connection {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(PortInstance)
	 * @see org.caltoopia.ir.IrPackage#getToSink_Source()
	 * @model
	 * @generated
	 */
	PortInstance getSource();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.ToSink#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(PortInstance value);

	/**
	 * Returns the value of the '<em><b>Sink</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sink</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sink</em>' reference.
	 * @see #setSink(Port)
	 * @see org.caltoopia.ir.IrPackage#getToSink_Sink()
	 * @model
	 * @generated
	 */
	Port getSink();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.ToSink#getSink <em>Sink</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sink</em>' reference.
	 * @see #getSink()
	 * @generated
	 */
	void setSink(Port value);

} // ToSink
