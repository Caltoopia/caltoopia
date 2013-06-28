/**
 */
package org.caltoopia.ir;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Point2 Point Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.caltoopia.ir.Point2PointConnection#getSource <em>Source</em>}</li>
 *   <li>{@link org.caltoopia.ir.Point2PointConnection#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.caltoopia.ir.IrPackage#getPoint2PointConnection()
 * @model
 * @generated
 */
public interface Point2PointConnection extends Connection {
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
	 * @see org.caltoopia.ir.IrPackage#getPoint2PointConnection_Source()
	 * @model
	 * @generated
	 */
	PortInstance getSource();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.Point2PointConnection#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(PortInstance value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(PortInstance)
	 * @see org.caltoopia.ir.IrPackage#getPoint2PointConnection_Target()
	 * @model
	 * @generated
	 */
	PortInstance getTarget();

	/**
	 * Sets the value of the '{@link org.caltoopia.ir.Point2PointConnection#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(PortInstance value);

} // Point2PointConnection
