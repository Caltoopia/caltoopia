/**
 */
package org.caltoopia.ir.impl;

import java.util.Collection;

import org.caltoopia.ir.Expression;
import org.caltoopia.ir.IrPackage;
import org.caltoopia.ir.PortGuard;
import org.caltoopia.ir.Statement;
import org.caltoopia.ir.StmtAlternative;

import org.caltoopia.ir.TypeGuard;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stmt Alternative</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.ir.impl.StmtAlternativeImpl#getTypeGuard <em>Type Guard</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.StmtAlternativeImpl#getValueGuards <em>Value Guards</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.StmtAlternativeImpl#getStatements <em>Statements</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StmtAlternativeImpl extends ScopeImpl implements StmtAlternative {
	/**
	 * The cached value of the '{@link #getTypeGuard() <em>Type Guard</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeGuard()
	 * @generated
	 * @ordered
	 */
	protected TypeGuard typeGuard;

	/**
	 * The cached value of the '{@link #getValueGuards() <em>Value Guards</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValueGuards()
	 * @generated
	 * @ordered
	 */
	protected EList<Expression> valueGuards;

	/**
	 * The cached value of the '{@link #getStatements() <em>Statements</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatements()
	 * @generated
	 * @ordered
	 */
	protected EList<Statement> statements;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StmtAlternativeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IrPackage.Literals.STMT_ALTERNATIVE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeGuard getTypeGuard() {
		if (typeGuard != null && typeGuard.eIsProxy()) {
			InternalEObject oldTypeGuard = (InternalEObject)typeGuard;
			typeGuard = (TypeGuard)eResolveProxy(oldTypeGuard);
			if (typeGuard != oldTypeGuard) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, IrPackage.STMT_ALTERNATIVE__TYPE_GUARD, oldTypeGuard, typeGuard));
			}
		}
		return typeGuard;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeGuard basicGetTypeGuard() {
		return typeGuard;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeGuard(TypeGuard newTypeGuard) {
		TypeGuard oldTypeGuard = typeGuard;
		typeGuard = newTypeGuard;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IrPackage.STMT_ALTERNATIVE__TYPE_GUARD, oldTypeGuard, typeGuard));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Expression> getValueGuards() {
		if (valueGuards == null) {
			valueGuards = new EObjectResolvingEList<Expression>(Expression.class, this, IrPackage.STMT_ALTERNATIVE__VALUE_GUARDS);
		}
		return valueGuards;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Statement> getStatements() {
		if (statements == null) {
			statements = new EObjectResolvingEList<Statement>(Statement.class, this, IrPackage.STMT_ALTERNATIVE__STATEMENTS);
		}
		return statements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IrPackage.STMT_ALTERNATIVE__TYPE_GUARD:
				if (resolve) return getTypeGuard();
				return basicGetTypeGuard();
			case IrPackage.STMT_ALTERNATIVE__VALUE_GUARDS:
				return getValueGuards();
			case IrPackage.STMT_ALTERNATIVE__STATEMENTS:
				return getStatements();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case IrPackage.STMT_ALTERNATIVE__TYPE_GUARD:
				setTypeGuard((TypeGuard)newValue);
				return;
			case IrPackage.STMT_ALTERNATIVE__VALUE_GUARDS:
				getValueGuards().clear();
				getValueGuards().addAll((Collection<? extends Expression>)newValue);
				return;
			case IrPackage.STMT_ALTERNATIVE__STATEMENTS:
				getStatements().clear();
				getStatements().addAll((Collection<? extends Statement>)newValue);
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
			case IrPackage.STMT_ALTERNATIVE__TYPE_GUARD:
				setTypeGuard((TypeGuard)null);
				return;
			case IrPackage.STMT_ALTERNATIVE__VALUE_GUARDS:
				getValueGuards().clear();
				return;
			case IrPackage.STMT_ALTERNATIVE__STATEMENTS:
				getStatements().clear();
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
			case IrPackage.STMT_ALTERNATIVE__TYPE_GUARD:
				return typeGuard != null;
			case IrPackage.STMT_ALTERNATIVE__VALUE_GUARDS:
				return valueGuards != null && !valueGuards.isEmpty();
			case IrPackage.STMT_ALTERNATIVE__STATEMENTS:
				return statements != null && !statements.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //StmtAlternativeImpl
