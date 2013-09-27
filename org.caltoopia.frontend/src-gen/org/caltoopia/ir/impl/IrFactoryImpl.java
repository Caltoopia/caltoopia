/**
 */
package org.caltoopia.ir.impl;

import org.caltoopia.ir.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class IrFactoryImpl extends EFactoryImpl implements IrFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static IrFactory init() {
		try {
			IrFactory theIrFactory = (IrFactory)EPackage.Registry.INSTANCE.getEFactory("http:///org/caltoopia/ir.ecore"); 
			if (theIrFactory != null) {
				return theIrFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new IrFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IrFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case IrPackage.SCOPE: return createScope();
			case IrPackage.NODE: return createNode();
			case IrPackage.VARIABLE_IMPORT: return createVariableImport();
			case IrPackage.VARIABLE_EXTERNAL: return createVariableExternal();
			case IrPackage.TYPE_DECLARATION_IMPORT: return createTypeDeclarationImport();
			case IrPackage.NAMESPACE: return createNamespace();
			case IrPackage.ABSTRACT_ACTOR: return createAbstractActor();
			case IrPackage.EXTERNAL_ACTOR: return createExternalActor();
			case IrPackage.ACTOR: return createActor();
			case IrPackage.NETWORK: return createNetwork();
			case IrPackage.ACTION: return createAction();
			case IrPackage.PORT: return createPort();
			case IrPackage.ACTOR_INSTANCE: return createActorInstance();
			case IrPackage.PORT_INSTANCE: return createPortInstance();
			case IrPackage.EXPRESSION: return createExpression();
			case IrPackage.TAGGED_EXPRESSION: return createTaggedExpression();
			case IrPackage.LITERAL_EXPRESSION: return createLiteralExpression();
			case IrPackage.INTEGER_LITERAL: return createIntegerLiteral();
			case IrPackage.FLOAT_LITERAL: return createFloatLiteral();
			case IrPackage.BOOLEAN_LITERAL: return createBooleanLiteral();
			case IrPackage.STRING_LITERAL: return createStringLiteral();
			case IrPackage.VARIABLE_EXPRESSION: return createVariableExpression();
			case IrPackage.LIST_EXPRESSION: return createListExpression();
			case IrPackage.GENERATOR: return createGenerator();
			case IrPackage.MEMBER: return createMember();
			case IrPackage.BINARY_EXPRESSION: return createBinaryExpression();
			case IrPackage.UNARY_EXPRESSION: return createUnaryExpression();
			case IrPackage.EXPRESSION_CALL: return createExpressionCall();
			case IrPackage.FUNCTION_CALL: return createFunctionCall();
			case IrPackage.TYPE_CONSTRUCTOR_CALL: return createTypeConstructorCall();
			case IrPackage.CONNECTION: return createConnection();
			case IrPackage.POINT2_POINT_CONNECTION: return createPoint2PointConnection();
			case IrPackage.FROM_SOURCE: return createFromSource();
			case IrPackage.TO_SINK: return createToSink();
			case IrPackage.STATEMENT: return createStatement();
			case IrPackage.VARIABLE_REFERENCE: return createVariableReference();
			case IrPackage.ASSIGN: return createAssign();
			case IrPackage.RETURN_VALUE: return createReturnValue();
			case IrPackage.PROC_CALL: return createProcCall();
			case IrPackage.WHILE_LOOP: return createWhileLoop();
			case IrPackage.FOR_EACH: return createForEach();
			case IrPackage.IF_STATEMENT: return createIfStatement();
			case IrPackage.BLOCK: return createBlock();
			case IrPackage.CASE_STATEMENT: return createCaseStatement();
			case IrPackage.STMT_ALTERNATIVE: return createStmtAlternative();
			case IrPackage.PORT_ACCESS: return createPortAccess();
			case IrPackage.PORT_WRITE: return createPortWrite();
			case IrPackage.PORT_READ: return createPortRead();
			case IrPackage.PORT_PEEK: return createPortPeek();
			case IrPackage.DECLARATION: return createDeclaration();
			case IrPackage.FORWARD_DECLARATION: return createForwardDeclaration();
			case IrPackage.VARIABLE: return createVariable();
			case IrPackage.LAMBDA_EXPRESSION: return createLambdaExpression();
			case IrPackage.PROC_EXPRESSION: return createProcExpression();
			case IrPackage.IF_EXPRESSION: return createIfExpression();
			case IrPackage.CASE_EXPRESSION: return createCaseExpression();
			case IrPackage.EXPR_ALTERNATIVE: return createExprAlternative();
			case IrPackage.GUARD: return createGuard();
			case IrPackage.TYPE: return createType();
			case IrPackage.TYPE_BOOL: return createTypeBool();
			case IrPackage.TYPE_EXTERNAL: return createTypeExternal();
			case IrPackage.TYPE_INT: return createTypeInt();
			case IrPackage.TYPE_LIST: return createTypeList();
			case IrPackage.TYPE_FLOAT: return createTypeFloat();
			case IrPackage.TYPE_UINT: return createTypeUint();
			case IrPackage.TYPE_STRING: return createTypeString();
			case IrPackage.TYPE_TUPLE: return createTypeTuple();
			case IrPackage.TAGGED_TUPLE: return createTaggedTuple();
			case IrPackage.TAGGED_TUPLE_FIELD_EXPRESSION: return createTaggedTupleFieldExpression();
			case IrPackage.TAG_OF: return createTagOf();
			case IrPackage.TYPE_UNDEF: return createTypeUndef();
			case IrPackage.TYPE_ACTOR: return createTypeActor();
			case IrPackage.TYPE_USER: return createTypeUser();
			case IrPackage.TYPE_LAMBDA: return createTypeLambda();
			case IrPackage.TYPE_PROC: return createTypeProc();
			case IrPackage.TYPE_VARIABLE: return createTypeVariable();
			case IrPackage.TYPE_DECLARATION: return createTypeDeclaration();
			case IrPackage.TYPE_VARIABLE_DECLARATION: return createTypeVariableDeclaration();
			case IrPackage.SCHEDULE: return createSchedule();
			case IrPackage.STATE: return createState();
			case IrPackage.ANNOTATION: return createAnnotation();
			case IrPackage.ANNOTATION_ARGUMENT: return createAnnotationArgument();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Scope createScope() {
		ScopeImpl scope = new ScopeImpl();
		return scope;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node createNode() {
		NodeImpl node = new NodeImpl();
		return node;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableImport createVariableImport() {
		VariableImportImpl variableImport = new VariableImportImpl();
		return variableImport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableExternal createVariableExternal() {
		VariableExternalImpl variableExternal = new VariableExternalImpl();
		return variableExternal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeDeclarationImport createTypeDeclarationImport() {
		TypeDeclarationImportImpl typeDeclarationImport = new TypeDeclarationImportImpl();
		return typeDeclarationImport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Namespace createNamespace() {
		NamespaceImpl namespace = new NamespaceImpl();
		return namespace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractActor createAbstractActor() {
		AbstractActorImpl abstractActor = new AbstractActorImpl();
		return abstractActor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExternalActor createExternalActor() {
		ExternalActorImpl externalActor = new ExternalActorImpl();
		return externalActor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Actor createActor() {
		ActorImpl actor = new ActorImpl();
		return actor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Network createNetwork() {
		NetworkImpl network = new NetworkImpl();
		return network;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Action createAction() {
		ActionImpl action = new ActionImpl();
		return action;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Port createPort() {
		PortImpl port = new PortImpl();
		return port;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActorInstance createActorInstance() {
		ActorInstanceImpl actorInstance = new ActorInstanceImpl();
		return actorInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortInstance createPortInstance() {
		PortInstanceImpl portInstance = new PortInstanceImpl();
		return portInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression createExpression() {
		ExpressionImpl expression = new ExpressionImpl();
		return expression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TaggedExpression createTaggedExpression() {
		TaggedExpressionImpl taggedExpression = new TaggedExpressionImpl();
		return taggedExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LiteralExpression createLiteralExpression() {
		LiteralExpressionImpl literalExpression = new LiteralExpressionImpl();
		return literalExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerLiteral createIntegerLiteral() {
		IntegerLiteralImpl integerLiteral = new IntegerLiteralImpl();
		return integerLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FloatLiteral createFloatLiteral() {
		FloatLiteralImpl floatLiteral = new FloatLiteralImpl();
		return floatLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanLiteral createBooleanLiteral() {
		BooleanLiteralImpl booleanLiteral = new BooleanLiteralImpl();
		return booleanLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringLiteral createStringLiteral() {
		StringLiteralImpl stringLiteral = new StringLiteralImpl();
		return stringLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableExpression createVariableExpression() {
		VariableExpressionImpl variableExpression = new VariableExpressionImpl();
		return variableExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ListExpression createListExpression() {
		ListExpressionImpl listExpression = new ListExpressionImpl();
		return listExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Generator createGenerator() {
		GeneratorImpl generator = new GeneratorImpl();
		return generator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Member createMember() {
		MemberImpl member = new MemberImpl();
		return member;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BinaryExpression createBinaryExpression() {
		BinaryExpressionImpl binaryExpression = new BinaryExpressionImpl();
		return binaryExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnaryExpression createUnaryExpression() {
		UnaryExpressionImpl unaryExpression = new UnaryExpressionImpl();
		return unaryExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpressionCall createExpressionCall() {
		ExpressionCallImpl expressionCall = new ExpressionCallImpl();
		return expressionCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FunctionCall createFunctionCall() {
		FunctionCallImpl functionCall = new FunctionCallImpl();
		return functionCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeConstructorCall createTypeConstructorCall() {
		TypeConstructorCallImpl typeConstructorCall = new TypeConstructorCallImpl();
		return typeConstructorCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Connection createConnection() {
		ConnectionImpl connection = new ConnectionImpl();
		return connection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Point2PointConnection createPoint2PointConnection() {
		Point2PointConnectionImpl point2PointConnection = new Point2PointConnectionImpl();
		return point2PointConnection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FromSource createFromSource() {
		FromSourceImpl fromSource = new FromSourceImpl();
		return fromSource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ToSink createToSink() {
		ToSinkImpl toSink = new ToSinkImpl();
		return toSink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Statement createStatement() {
		StatementImpl statement = new StatementImpl();
		return statement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableReference createVariableReference() {
		VariableReferenceImpl variableReference = new VariableReferenceImpl();
		return variableReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Assign createAssign() {
		AssignImpl assign = new AssignImpl();
		return assign;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReturnValue createReturnValue() {
		ReturnValueImpl returnValue = new ReturnValueImpl();
		return returnValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcCall createProcCall() {
		ProcCallImpl procCall = new ProcCallImpl();
		return procCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WhileLoop createWhileLoop() {
		WhileLoopImpl whileLoop = new WhileLoopImpl();
		return whileLoop;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ForEach createForEach() {
		ForEachImpl forEach = new ForEachImpl();
		return forEach;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IfStatement createIfStatement() {
		IfStatementImpl ifStatement = new IfStatementImpl();
		return ifStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Block createBlock() {
		BlockImpl block = new BlockImpl();
		return block;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CaseStatement createCaseStatement() {
		CaseStatementImpl caseStatement = new CaseStatementImpl();
		return caseStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StmtAlternative createStmtAlternative() {
		StmtAlternativeImpl stmtAlternative = new StmtAlternativeImpl();
		return stmtAlternative;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortAccess createPortAccess() {
		PortAccessImpl portAccess = new PortAccessImpl();
		return portAccess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortWrite createPortWrite() {
		PortWriteImpl portWrite = new PortWriteImpl();
		return portWrite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortRead createPortRead() {
		PortReadImpl portRead = new PortReadImpl();
		return portRead;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortPeek createPortPeek() {
		PortPeekImpl portPeek = new PortPeekImpl();
		return portPeek;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Declaration createDeclaration() {
		DeclarationImpl declaration = new DeclarationImpl();
		return declaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ForwardDeclaration createForwardDeclaration() {
		ForwardDeclarationImpl forwardDeclaration = new ForwardDeclarationImpl();
		return forwardDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Variable createVariable() {
		VariableImpl variable = new VariableImpl();
		return variable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LambdaExpression createLambdaExpression() {
		LambdaExpressionImpl lambdaExpression = new LambdaExpressionImpl();
		return lambdaExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcExpression createProcExpression() {
		ProcExpressionImpl procExpression = new ProcExpressionImpl();
		return procExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IfExpression createIfExpression() {
		IfExpressionImpl ifExpression = new IfExpressionImpl();
		return ifExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CaseExpression createCaseExpression() {
		CaseExpressionImpl caseExpression = new CaseExpressionImpl();
		return caseExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExprAlternative createExprAlternative() {
		ExprAlternativeImpl exprAlternative = new ExprAlternativeImpl();
		return exprAlternative;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Guard createGuard() {
		GuardImpl guard = new GuardImpl();
		return guard;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type createType() {
		TypeImpl type = new TypeImpl();
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeBool createTypeBool() {
		TypeBoolImpl typeBool = new TypeBoolImpl();
		return typeBool;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeExternal createTypeExternal() {
		TypeExternalImpl typeExternal = new TypeExternalImpl();
		return typeExternal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeInt createTypeInt() {
		TypeIntImpl typeInt = new TypeIntImpl();
		return typeInt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeList createTypeList() {
		TypeListImpl typeList = new TypeListImpl();
		return typeList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeFloat createTypeFloat() {
		TypeFloatImpl typeFloat = new TypeFloatImpl();
		return typeFloat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeUint createTypeUint() {
		TypeUintImpl typeUint = new TypeUintImpl();
		return typeUint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeString createTypeString() {
		TypeStringImpl typeString = new TypeStringImpl();
		return typeString;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeTuple createTypeTuple() {
		TypeTupleImpl typeTuple = new TypeTupleImpl();
		return typeTuple;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TaggedTuple createTaggedTuple() {
		TaggedTupleImpl taggedTuple = new TaggedTupleImpl();
		return taggedTuple;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TaggedTupleFieldExpression createTaggedTupleFieldExpression() {
		TaggedTupleFieldExpressionImpl taggedTupleFieldExpression = new TaggedTupleFieldExpressionImpl();
		return taggedTupleFieldExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagOf createTagOf() {
		TagOfImpl tagOf = new TagOfImpl();
		return tagOf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeUndef createTypeUndef() {
		TypeUndefImpl typeUndef = new TypeUndefImpl();
		return typeUndef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeActor createTypeActor() {
		TypeActorImpl typeActor = new TypeActorImpl();
		return typeActor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeUser createTypeUser() {
		TypeUserImpl typeUser = new TypeUserImpl();
		return typeUser;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeLambda createTypeLambda() {
		TypeLambdaImpl typeLambda = new TypeLambdaImpl();
		return typeLambda;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeProc createTypeProc() {
		TypeProcImpl typeProc = new TypeProcImpl();
		return typeProc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeVariable createTypeVariable() {
		TypeVariableImpl typeVariable = new TypeVariableImpl();
		return typeVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeDeclaration createTypeDeclaration() {
		TypeDeclarationImpl typeDeclaration = new TypeDeclarationImpl();
		return typeDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeVariableDeclaration createTypeVariableDeclaration() {
		TypeVariableDeclarationImpl typeVariableDeclaration = new TypeVariableDeclarationImpl();
		return typeVariableDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Schedule createSchedule() {
		ScheduleImpl schedule = new ScheduleImpl();
		return schedule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public State createState() {
		StateImpl state = new StateImpl();
		return state;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Annotation createAnnotation() {
		AnnotationImpl annotation = new AnnotationImpl();
		return annotation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnnotationArgument createAnnotationArgument() {
		AnnotationArgumentImpl annotationArgument = new AnnotationArgumentImpl();
		return annotationArgument;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IrPackage getIrPackage() {
		return (IrPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static IrPackage getPackage() {
		return IrPackage.eINSTANCE;
	}

} //IrFactoryImpl
