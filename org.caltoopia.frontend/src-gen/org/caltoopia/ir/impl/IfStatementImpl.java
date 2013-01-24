/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.caltoopia.ir.impl;

import org.caltoopia.ir.Block;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.IfStatement;
import org.caltoopia.ir.IrPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>If Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.ir.impl.IfStatementImpl#getThenBlock <em>Then Block</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.IfStatementImpl#getElseBlock <em>Else Block</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.IfStatementImpl#getCondition <em>Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IfStatementImpl extends StatementImpl implements IfStatement {
	/**
	 * The cached value of the '{@link #getThenBlock() <em>Then Block</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThenBlock()
	 * @generated
	 * @ordered
	 */
	protected Block thenBlock;

	/**
	 * The cached value of the '{@link #getElseBlock() <em>Else Block</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElseBlock()
	 * @generated
	 * @ordered
	 */
	protected Block elseBlock;

	/**
	 * The cached value of the '{@link #getCondition() <em>Condition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected Expression condition;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IfStatementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IrPackage.Literals.IF_STATEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Block getThenBlock() {
		if (thenBlock != null && thenBlock.eIsProxy()) {
			InternalEObject oldThenBlock = (InternalEObject)thenBlock;
			thenBlock = (Block)eResolveProxy(oldThenBlock);
			if (thenBlock != oldThenBlock) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.IF_STATEMENT__THEN_BLOCK, oldThenBlock, thenBlock));
			}
		}
		return thenBlock;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Block basicGetThenBlock() {
		return thenBlock;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setThenBlock(Block newThenBlock) {
		Block oldThenBlock = thenBlock;
		thenBlock = newThenBlock;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.IF_STATEMENT__THEN_BLOCK, oldThenBlock, thenBlock));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Block getElseBlock() {
		if (elseBlock != null && elseBlock.eIsProxy()) {
			InternalEObject oldElseBlock = (InternalEObject)elseBlock;
			elseBlock = (Block)eResolveProxy(oldElseBlock);
			if (elseBlock != oldElseBlock) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.IF_STATEMENT__ELSE_BLOCK, oldElseBlock, elseBlock));
			}
		}
		return elseBlock;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Block basicGetElseBlock() {
		return elseBlock;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElseBlock(Block newElseBlock) {
		Block oldElseBlock = elseBlock;
		elseBlock = newElseBlock;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.IF_STATEMENT__ELSE_BLOCK, oldElseBlock, elseBlock));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getCondition() {
		if (condition != null && condition.eIsProxy()) {
			InternalEObject oldCondition = (InternalEObject)condition;
			condition = (Expression)eResolveProxy(oldCondition);
			if (condition != oldCondition) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.IF_STATEMENT__CONDITION, oldCondition, condition));
			}
		}
		return condition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression basicGetCondition() {
		return condition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCondition(Expression newCondition) {
		Expression oldCondition = condition;
		condition = newCondition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.IF_STATEMENT__CONDITION, oldCondition, condition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IrPackage.IF_STATEMENT__THEN_BLOCK:
				if (resolve) return getThenBlock();
				return basicGetThenBlock();
			case IrPackage.IF_STATEMENT__ELSE_BLOCK:
				if (resolve) return getElseBlock();
				return basicGetElseBlock();
			case IrPackage.IF_STATEMENT__CONDITION:
				if (resolve) return getCondition();
				return basicGetCondition();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case IrPackage.IF_STATEMENT__THEN_BLOCK:
				setThenBlock((Block)newValue);
				return;
			case IrPackage.IF_STATEMENT__ELSE_BLOCK:
				setElseBlock((Block)newValue);
				return;
			case IrPackage.IF_STATEMENT__CONDITION:
				setCondition((Expression)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case IrPackage.IF_STATEMENT__THEN_BLOCK:
				setThenBlock((Block)null);
				return;
			case IrPackage.IF_STATEMENT__ELSE_BLOCK:
				setElseBlock((Block)null);
				return;
			case IrPackage.IF_STATEMENT__CONDITION:
				setCondition((Expression)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case IrPackage.IF_STATEMENT__THEN_BLOCK:
				return thenBlock != null;
			case IrPackage.IF_STATEMENT__ELSE_BLOCK:
				return elseBlock != null;
			case IrPackage.IF_STATEMENT__CONDITION:
				return condition != null;
		}
		return super.eIsSet(featureID);
	}

} //IfStatementImpl
