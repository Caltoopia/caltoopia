/**
 */
package org.caltoopia.ir.impl;

import java.util.Collection;

import org.caltoopia.ir.Action;
import org.caltoopia.ir.Guard;
import org.caltoopia.ir.IrPackage;
import org.caltoopia.ir.PortRead;
import org.caltoopia.ir.PortWrite;
import org.caltoopia.ir.Statement;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.caltoopia.ir.impl.ActionImpl#getGuards <em>Guards</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.ActionImpl#getOutputs <em>Outputs</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.ActionImpl#getInputs <em>Inputs</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.ActionImpl#getTag <em>Tag</em>}</li>
 *   <li>{@link org.caltoopia.ir.impl.ActionImpl#getStatements <em>Statements</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActionImpl extends ScopeImpl implements Action {
	/**
	 * The cached value of the '{@link #getGuards() <em>Guards</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGuards()
	 * @generated
	 * @ordered
	 */
	protected EList<Guard> guards;

	/**
	 * The cached value of the '{@link #getOutputs() <em>Outputs</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutputs()
	 * @generated
	 * @ordered
	 */
	protected EList<PortWrite> outputs;

	/**
	 * The cached value of the '{@link #getInputs() <em>Inputs</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputs()
	 * @generated
	 * @ordered
	 */
	protected EList<PortRead> inputs;

	/**
	 * The cached value of the '{@link #getTag() <em>Tag</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTag()
	 * @generated
	 * @ordered
	 */
	protected EList<String> tag;

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
	protected ActionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IrPackage.Literals.ACTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Guard> getGuards() {
		if (guards == null) {
			guards = new EObjectResolvingEList<Guard>(Guard.class, this, IrPackage.ACTION__GUARDS);
		}
		return guards;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PortWrite> getOutputs() {
		if (outputs == null) {
			outputs = new EObjectResolvingEList<PortWrite>(PortWrite.class, this, IrPackage.ACTION__OUTPUTS);
		}
		return outputs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PortRead> getInputs() {
		if (inputs == null) {
			inputs = new EObjectResolvingEList<PortRead>(PortRead.class, this, IrPackage.ACTION__INPUTS);
		}
		return inputs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getTag() {
		if (tag == null) {
			tag = new EDataTypeUniqueEList<String>(String.class, this, IrPackage.ACTION__TAG);
		}
		return tag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Statement> getStatements() {
		if (statements == null) {
			statements = new EObjectResolvingEList<Statement>(Statement.class, this, IrPackage.ACTION__STATEMENTS);
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
			case IrPackage.ACTION__GUARDS:
				return getGuards();
			case IrPackage.ACTION__OUTPUTS:
				return getOutputs();
			case IrPackage.ACTION__INPUTS:
				return getInputs();
			case IrPackage.ACTION__TAG:
				return getTag();
			case IrPackage.ACTION__STATEMENTS:
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
			case IrPackage.ACTION__GUARDS:
				getGuards().clear();
				getGuards().addAll((Collection<? extends Guard>)newValue);
				return;
			case IrPackage.ACTION__OUTPUTS:
				getOutputs().clear();
				getOutputs().addAll((Collection<? extends PortWrite>)newValue);
				return;
			case IrPackage.ACTION__INPUTS:
				getInputs().clear();
				getInputs().addAll((Collection<? extends PortRead>)newValue);
				return;
			case IrPackage.ACTION__TAG:
				getTag().clear();
				getTag().addAll((Collection<? extends String>)newValue);
				return;
			case IrPackage.ACTION__STATEMENTS:
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
			case IrPackage.ACTION__GUARDS:
				getGuards().clear();
				return;
			case IrPackage.ACTION__OUTPUTS:
				getOutputs().clear();
				return;
			case IrPackage.ACTION__INPUTS:
				getInputs().clear();
				return;
			case IrPackage.ACTION__TAG:
				getTag().clear();
				return;
			case IrPackage.ACTION__STATEMENTS:
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
			case IrPackage.ACTION__GUARDS:
				return guards != null && !guards.isEmpty();
			case IrPackage.ACTION__OUTPUTS:
				return outputs != null && !outputs.isEmpty();
			case IrPackage.ACTION__INPUTS:
				return inputs != null && !inputs.isEmpty();
			case IrPackage.ACTION__TAG:
				return tag != null && !tag.isEmpty();
			case IrPackage.ACTION__STATEMENTS:
				return statements != null && !statements.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (tag: ");
		result.append(tag);
		result.append(')');
		return result.toString();
	}

} //ActionImpl
