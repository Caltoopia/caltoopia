/**
 */
package org.caltoopia.ir.util;

import org.caltoopia.ir.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.caltoopia.ir.IrPackage
 * @generated
 */
public class IrAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static IrPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IrAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = IrPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IrSwitch<Adapter> modelSwitch =
		new IrSwitch<Adapter>() {
			@Override
			public Adapter caseScope(Scope object) {
				return createScopeAdapter();
			}
			@Override
			public Adapter caseNode(Node object) {
				return createNodeAdapter();
			}
			@Override
			public Adapter caseVariableImport(VariableImport object) {
				return createVariableImportAdapter();
			}
			@Override
			public Adapter caseVariableExternal(VariableExternal object) {
				return createVariableExternalAdapter();
			}
			@Override
			public Adapter caseTypeDeclarationImport(TypeDeclarationImport object) {
				return createTypeDeclarationImportAdapter();
			}
			@Override
			public Adapter caseNamespace(Namespace object) {
				return createNamespaceAdapter();
			}
			@Override
			public Adapter caseAbstractActor(AbstractActor object) {
				return createAbstractActorAdapter();
			}
			@Override
			public Adapter caseExternalActor(ExternalActor object) {
				return createExternalActorAdapter();
			}
			@Override
			public Adapter caseActor(Actor object) {
				return createActorAdapter();
			}
			@Override
			public Adapter caseNetwork(Network object) {
				return createNetworkAdapter();
			}
			@Override
			public Adapter caseAction(Action object) {
				return createActionAdapter();
			}
			@Override
			public Adapter casePort(Port object) {
				return createPortAdapter();
			}
			@Override
			public Adapter caseActorInstance(ActorInstance object) {
				return createActorInstanceAdapter();
			}
			@Override
			public Adapter casePortInstance(PortInstance object) {
				return createPortInstanceAdapter();
			}
			@Override
			public Adapter caseExpression(Expression object) {
				return createExpressionAdapter();
			}
			@Override
			public Adapter caseTaggedExpression(TaggedExpression object) {
				return createTaggedExpressionAdapter();
			}
			@Override
			public Adapter caseLiteralExpression(LiteralExpression object) {
				return createLiteralExpressionAdapter();
			}
			@Override
			public Adapter caseIntegerLiteral(IntegerLiteral object) {
				return createIntegerLiteralAdapter();
			}
			@Override
			public Adapter caseFloatLiteral(FloatLiteral object) {
				return createFloatLiteralAdapter();
			}
			@Override
			public Adapter caseBooleanLiteral(BooleanLiteral object) {
				return createBooleanLiteralAdapter();
			}
			@Override
			public Adapter caseStringLiteral(StringLiteral object) {
				return createStringLiteralAdapter();
			}
			@Override
			public Adapter caseVariableExpression(VariableExpression object) {
				return createVariableExpressionAdapter();
			}
			@Override
			public Adapter caseListExpression(ListExpression object) {
				return createListExpressionAdapter();
			}
			@Override
			public Adapter caseGenerator(Generator object) {
				return createGeneratorAdapter();
			}
			@Override
			public Adapter caseMember(Member object) {
				return createMemberAdapter();
			}
			@Override
			public Adapter caseBinaryExpression(BinaryExpression object) {
				return createBinaryExpressionAdapter();
			}
			@Override
			public Adapter caseUnaryExpression(UnaryExpression object) {
				return createUnaryExpressionAdapter();
			}
			@Override
			public Adapter caseExpressionCall(ExpressionCall object) {
				return createExpressionCallAdapter();
			}
			@Override
			public Adapter caseFunctionCall(FunctionCall object) {
				return createFunctionCallAdapter();
			}
			@Override
			public Adapter caseTypeConstructorCall(TypeConstructorCall object) {
				return createTypeConstructorCallAdapter();
			}
			@Override
			public Adapter caseConnection(Connection object) {
				return createConnectionAdapter();
			}
			@Override
			public Adapter casePoint2PointConnection(Point2PointConnection object) {
				return createPoint2PointConnectionAdapter();
			}
			@Override
			public Adapter caseFromSource(FromSource object) {
				return createFromSourceAdapter();
			}
			@Override
			public Adapter caseToSink(ToSink object) {
				return createToSinkAdapter();
			}
			@Override
			public Adapter caseStatement(Statement object) {
				return createStatementAdapter();
			}
			@Override
			public Adapter caseVariableReference(VariableReference object) {
				return createVariableReferenceAdapter();
			}
			@Override
			public Adapter caseAssign(Assign object) {
				return createAssignAdapter();
			}
			@Override
			public Adapter caseReturnValue(ReturnValue object) {
				return createReturnValueAdapter();
			}
			@Override
			public Adapter caseProcCall(ProcCall object) {
				return createProcCallAdapter();
			}
			@Override
			public Adapter caseWhileLoop(WhileLoop object) {
				return createWhileLoopAdapter();
			}
			@Override
			public Adapter caseForEach(ForEach object) {
				return createForEachAdapter();
			}
			@Override
			public Adapter caseIfStatement(IfStatement object) {
				return createIfStatementAdapter();
			}
			@Override
			public Adapter caseBlock(Block object) {
				return createBlockAdapter();
			}
			@Override
			public Adapter caseCaseStatement(CaseStatement object) {
				return createCaseStatementAdapter();
			}
			@Override
			public Adapter caseStmtAlternative(StmtAlternative object) {
				return createStmtAlternativeAdapter();
			}
			@Override
			public Adapter casePortAccess(PortAccess object) {
				return createPortAccessAdapter();
			}
			@Override
			public Adapter casePortWrite(PortWrite object) {
				return createPortWriteAdapter();
			}
			@Override
			public Adapter casePortRead(PortRead object) {
				return createPortReadAdapter();
			}
			@Override
			public Adapter casePortPeek(PortPeek object) {
				return createPortPeekAdapter();
			}
			@Override
			public Adapter caseDeclaration(Declaration object) {
				return createDeclarationAdapter();
			}
			@Override
			public Adapter caseForwardDeclaration(ForwardDeclaration object) {
				return createForwardDeclarationAdapter();
			}
			@Override
			public Adapter caseVariable(Variable object) {
				return createVariableAdapter();
			}
			@Override
			public Adapter caseLambdaExpression(LambdaExpression object) {
				return createLambdaExpressionAdapter();
			}
			@Override
			public Adapter caseProcExpression(ProcExpression object) {
				return createProcExpressionAdapter();
			}
			@Override
			public Adapter caseIfExpression(IfExpression object) {
				return createIfExpressionAdapter();
			}
			@Override
			public Adapter caseCaseExpression(CaseExpression object) {
				return createCaseExpressionAdapter();
			}
			@Override
			public Adapter caseExprAlternative(ExprAlternative object) {
				return createExprAlternativeAdapter();
			}
			@Override
			public Adapter casePortGuard(PortGuard object) {
				return createPortGuardAdapter();
			}
			@Override
			public Adapter caseGuard(Guard object) {
				return createGuardAdapter();
			}
			@Override
			public Adapter caseType(Type object) {
				return createTypeAdapter();
			}
			@Override
			public Adapter caseTypeBool(TypeBool object) {
				return createTypeBoolAdapter();
			}
			@Override
			public Adapter caseTypeExternal(TypeExternal object) {
				return createTypeExternalAdapter();
			}
			@Override
			public Adapter caseTypeInt(TypeInt object) {
				return createTypeIntAdapter();
			}
			@Override
			public Adapter caseTypeList(TypeList object) {
				return createTypeListAdapter();
			}
			@Override
			public Adapter caseTypeFloat(TypeFloat object) {
				return createTypeFloatAdapter();
			}
			@Override
			public Adapter caseTypeUint(TypeUint object) {
				return createTypeUintAdapter();
			}
			@Override
			public Adapter caseTypeString(TypeString object) {
				return createTypeStringAdapter();
			}
			@Override
			public Adapter caseTypeTuple(TypeTuple object) {
				return createTypeTupleAdapter();
			}
			@Override
			public Adapter caseTaggedTuple(TaggedTuple object) {
				return createTaggedTupleAdapter();
			}
			@Override
			public Adapter caseTaggedTupleFieldRead(TaggedTupleFieldRead object) {
				return createTaggedTupleFieldReadAdapter();
			}
			@Override
			public Adapter caseTypeGuard(TypeGuard object) {
				return createTypeGuardAdapter();
			}
			@Override
			public Adapter caseTypeUndef(TypeUndef object) {
				return createTypeUndefAdapter();
			}
			@Override
			public Adapter caseTypeActor(TypeActor object) {
				return createTypeActorAdapter();
			}
			@Override
			public Adapter caseTypeUser(TypeUser object) {
				return createTypeUserAdapter();
			}
			@Override
			public Adapter caseTypeLambda(TypeLambda object) {
				return createTypeLambdaAdapter();
			}
			@Override
			public Adapter caseTypeProc(TypeProc object) {
				return createTypeProcAdapter();
			}
			@Override
			public Adapter caseTypeVariable(TypeVariable object) {
				return createTypeVariableAdapter();
			}
			@Override
			public Adapter caseTypeDeclaration(TypeDeclaration object) {
				return createTypeDeclarationAdapter();
			}
			@Override
			public Adapter caseTypeVariableDeclaration(TypeVariableDeclaration object) {
				return createTypeVariableDeclarationAdapter();
			}
			@Override
			public Adapter caseSchedule(Schedule object) {
				return createScheduleAdapter();
			}
			@Override
			public Adapter caseState(State object) {
				return createStateAdapter();
			}
			@Override
			public Adapter caseAnnotation(Annotation object) {
				return createAnnotationAdapter();
			}
			@Override
			public Adapter caseAnnotationArgument(AnnotationArgument object) {
				return createAnnotationArgumentAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.Scope <em>Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.Scope
	 * @generated
	 */
	public Adapter createScopeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.Node
	 * @generated
	 */
	public Adapter createNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.VariableImport <em>Variable Import</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.VariableImport
	 * @generated
	 */
	public Adapter createVariableImportAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.VariableExternal <em>Variable External</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.VariableExternal
	 * @generated
	 */
	public Adapter createVariableExternalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.TypeDeclarationImport <em>Type Declaration Import</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.TypeDeclarationImport
	 * @generated
	 */
	public Adapter createTypeDeclarationImportAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.Namespace <em>Namespace</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.Namespace
	 * @generated
	 */
	public Adapter createNamespaceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.AbstractActor <em>Abstract Actor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.AbstractActor
	 * @generated
	 */
	public Adapter createAbstractActorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.ExternalActor <em>External Actor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.ExternalActor
	 * @generated
	 */
	public Adapter createExternalActorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.Actor <em>Actor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.Actor
	 * @generated
	 */
	public Adapter createActorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.Network <em>Network</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.Network
	 * @generated
	 */
	public Adapter createNetworkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.Action <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.Action
	 * @generated
	 */
	public Adapter createActionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.Port <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.Port
	 * @generated
	 */
	public Adapter createPortAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.ActorInstance <em>Actor Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.ActorInstance
	 * @generated
	 */
	public Adapter createActorInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.PortInstance <em>Port Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.PortInstance
	 * @generated
	 */
	public Adapter createPortInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.Expression
	 * @generated
	 */
	public Adapter createExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.TaggedExpression <em>Tagged Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.TaggedExpression
	 * @generated
	 */
	public Adapter createTaggedExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.LiteralExpression <em>Literal Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.LiteralExpression
	 * @generated
	 */
	public Adapter createLiteralExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.IntegerLiteral <em>Integer Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.IntegerLiteral
	 * @generated
	 */
	public Adapter createIntegerLiteralAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.FloatLiteral <em>Float Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.FloatLiteral
	 * @generated
	 */
	public Adapter createFloatLiteralAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.BooleanLiteral <em>Boolean Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.BooleanLiteral
	 * @generated
	 */
	public Adapter createBooleanLiteralAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.StringLiteral <em>String Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.StringLiteral
	 * @generated
	 */
	public Adapter createStringLiteralAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.VariableExpression <em>Variable Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.VariableExpression
	 * @generated
	 */
	public Adapter createVariableExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.ListExpression <em>List Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.ListExpression
	 * @generated
	 */
	public Adapter createListExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.Generator <em>Generator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.Generator
	 * @generated
	 */
	public Adapter createGeneratorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.Member <em>Member</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.Member
	 * @generated
	 */
	public Adapter createMemberAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.BinaryExpression <em>Binary Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.BinaryExpression
	 * @generated
	 */
	public Adapter createBinaryExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.UnaryExpression <em>Unary Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.UnaryExpression
	 * @generated
	 */
	public Adapter createUnaryExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.ExpressionCall <em>Expression Call</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.ExpressionCall
	 * @generated
	 */
	public Adapter createExpressionCallAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.FunctionCall <em>Function Call</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.FunctionCall
	 * @generated
	 */
	public Adapter createFunctionCallAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.TypeConstructorCall <em>Type Constructor Call</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.TypeConstructorCall
	 * @generated
	 */
	public Adapter createTypeConstructorCallAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.Connection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.Connection
	 * @generated
	 */
	public Adapter createConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.Point2PointConnection <em>Point2 Point Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.Point2PointConnection
	 * @generated
	 */
	public Adapter createPoint2PointConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.FromSource <em>From Source</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.FromSource
	 * @generated
	 */
	public Adapter createFromSourceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.ToSink <em>To Sink</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.ToSink
	 * @generated
	 */
	public Adapter createToSinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.Statement <em>Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.Statement
	 * @generated
	 */
	public Adapter createStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.VariableReference <em>Variable Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.VariableReference
	 * @generated
	 */
	public Adapter createVariableReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.Assign <em>Assign</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.Assign
	 * @generated
	 */
	public Adapter createAssignAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.ReturnValue <em>Return Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.ReturnValue
	 * @generated
	 */
	public Adapter createReturnValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.ProcCall <em>Proc Call</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.ProcCall
	 * @generated
	 */
	public Adapter createProcCallAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.WhileLoop <em>While Loop</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.WhileLoop
	 * @generated
	 */
	public Adapter createWhileLoopAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.ForEach <em>For Each</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.ForEach
	 * @generated
	 */
	public Adapter createForEachAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.IfStatement <em>If Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.IfStatement
	 * @generated
	 */
	public Adapter createIfStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.Block <em>Block</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.Block
	 * @generated
	 */
	public Adapter createBlockAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.CaseStatement <em>Case Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.CaseStatement
	 * @generated
	 */
	public Adapter createCaseStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.StmtAlternative <em>Stmt Alternative</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.StmtAlternative
	 * @generated
	 */
	public Adapter createStmtAlternativeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.PortAccess <em>Port Access</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.PortAccess
	 * @generated
	 */
	public Adapter createPortAccessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.PortWrite <em>Port Write</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.PortWrite
	 * @generated
	 */
	public Adapter createPortWriteAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.PortRead <em>Port Read</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.PortRead
	 * @generated
	 */
	public Adapter createPortReadAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.PortPeek <em>Port Peek</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.PortPeek
	 * @generated
	 */
	public Adapter createPortPeekAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.Declaration <em>Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.Declaration
	 * @generated
	 */
	public Adapter createDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.ForwardDeclaration <em>Forward Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.ForwardDeclaration
	 * @generated
	 */
	public Adapter createForwardDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.Variable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.Variable
	 * @generated
	 */
	public Adapter createVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.LambdaExpression <em>Lambda Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.LambdaExpression
	 * @generated
	 */
	public Adapter createLambdaExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.ProcExpression <em>Proc Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.ProcExpression
	 * @generated
	 */
	public Adapter createProcExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.IfExpression <em>If Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.IfExpression
	 * @generated
	 */
	public Adapter createIfExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.CaseExpression <em>Case Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.CaseExpression
	 * @generated
	 */
	public Adapter createCaseExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.ExprAlternative <em>Expr Alternative</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.ExprAlternative
	 * @generated
	 */
	public Adapter createExprAlternativeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.PortGuard <em>Port Guard</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.PortGuard
	 * @generated
	 */
	public Adapter createPortGuardAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.Guard <em>Guard</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.Guard
	 * @generated
	 */
	public Adapter createGuardAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.Type <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.Type
	 * @generated
	 */
	public Adapter createTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.TypeBool <em>Type Bool</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.TypeBool
	 * @generated
	 */
	public Adapter createTypeBoolAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.TypeExternal <em>Type External</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.TypeExternal
	 * @generated
	 */
	public Adapter createTypeExternalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.TypeInt <em>Type Int</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.TypeInt
	 * @generated
	 */
	public Adapter createTypeIntAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.TypeList <em>Type List</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.TypeList
	 * @generated
	 */
	public Adapter createTypeListAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.TypeFloat <em>Type Float</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.TypeFloat
	 * @generated
	 */
	public Adapter createTypeFloatAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.TypeUint <em>Type Uint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.TypeUint
	 * @generated
	 */
	public Adapter createTypeUintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.TypeString <em>Type String</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.TypeString
	 * @generated
	 */
	public Adapter createTypeStringAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.TypeTuple <em>Type Tuple</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.TypeTuple
	 * @generated
	 */
	public Adapter createTypeTupleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.TaggedTuple <em>Tagged Tuple</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.TaggedTuple
	 * @generated
	 */
	public Adapter createTaggedTupleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.TaggedTupleFieldRead <em>Tagged Tuple Field Read</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.TaggedTupleFieldRead
	 * @generated
	 */
	public Adapter createTaggedTupleFieldReadAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.TypeGuard <em>Type Guard</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.TypeGuard
	 * @generated
	 */
	public Adapter createTypeGuardAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.TypeUndef <em>Type Undef</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.TypeUndef
	 * @generated
	 */
	public Adapter createTypeUndefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.TypeActor <em>Type Actor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.TypeActor
	 * @generated
	 */
	public Adapter createTypeActorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.TypeUser <em>Type User</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.TypeUser
	 * @generated
	 */
	public Adapter createTypeUserAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.TypeLambda <em>Type Lambda</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.TypeLambda
	 * @generated
	 */
	public Adapter createTypeLambdaAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.TypeProc <em>Type Proc</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.TypeProc
	 * @generated
	 */
	public Adapter createTypeProcAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.TypeVariable <em>Type Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.TypeVariable
	 * @generated
	 */
	public Adapter createTypeVariableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.TypeDeclaration <em>Type Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.TypeDeclaration
	 * @generated
	 */
	public Adapter createTypeDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.TypeVariableDeclaration <em>Type Variable Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.TypeVariableDeclaration
	 * @generated
	 */
	public Adapter createTypeVariableDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.Schedule <em>Schedule</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.Schedule
	 * @generated
	 */
	public Adapter createScheduleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.State <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.State
	 * @generated
	 */
	public Adapter createStateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.Annotation <em>Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.Annotation
	 * @generated
	 */
	public Adapter createAnnotationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.caltoopia.ir.AnnotationArgument <em>Annotation Argument</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.caltoopia.ir.AnnotationArgument
	 * @generated
	 */
	public Adapter createAnnotationArgumentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //IrAdapterFactory
