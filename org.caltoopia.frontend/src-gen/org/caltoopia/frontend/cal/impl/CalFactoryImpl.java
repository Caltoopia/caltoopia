/**
 */
package org.caltoopia.frontend.cal.impl;

import org.caltoopia.frontend.cal.*;

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
public class CalFactoryImpl extends EFactoryImpl implements CalFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static CalFactory init()
  {
    try
    {
      CalFactory theCalFactory = (CalFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.caltoopia.org/frontend/Cal"); 
      if (theCalFactory != null)
      {
        return theCalFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new CalFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CalFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case CalPackage.AST_TOP: return createAstTop();
      case CalPackage.AST_PACKAGE: return createAstPackage();
      case CalPackage.AST_UNIT: return createAstUnit();
      case CalPackage.AST_NAMESPACE: return createAstNamespace();
      case CalPackage.AST_ENTITY: return createAstEntity();
      case CalPackage.AST_ABSTRACT_ACTOR: return createAstAbstractActor();
      case CalPackage.IMPORT: return createImport();
      case CalPackage.AST_NETWORK: return createAstNetwork();
      case CalPackage.AST_ACTOR_VARIABLE: return createAstActorVariable();
      case CalPackage.AST_ASSIGN_PARAMETER: return createAstAssignParameter();
      case CalPackage.AST_STRUCTURE: return createAstStructure();
      case CalPackage.AST_CONNECTION: return createAstConnection();
      case CalPackage.AST_ACTOR_VARIABLE_REFERENCE: return createAstActorVariableReference();
      case CalPackage.AST_CONNECTION_ATTRIBUTE: return createAstConnectionAttribute();
      case CalPackage.AST_VARIABLE: return createAstVariable();
      case CalPackage.AST_TYPE_USER: return createAstTypeUser();
      case CalPackage.AST_TYPE_DEFINITION_PARAMETER: return createAstTypeDefinitionParameter();
      case CalPackage.AST_TAGGED_TUPLE: return createAstTaggedTuple();
      case CalPackage.AST_ACTOR: return createAstActor();
      case CalPackage.AST_PORT: return createAstPort();
      case CalPackage.AST_EXTERNAL_FUNCTION: return createAstExternalFunction();
      case CalPackage.AST_PROCEDURE: return createAstProcedure();
      case CalPackage.AST_EXTERNAL_PROCEDURE: return createAstExternalProcedure();
      case CalPackage.AST_TAG: return createAstTag();
      case CalPackage.AST_EXTERNAL_ACTOR: return createAstExternalActor();
      case CalPackage.AST_INEQUALITY: return createAstInequality();
      case CalPackage.AST_PRIORITY: return createAstPriority();
      case CalPackage.AST_SCHEDULE: return createAstSchedule();
      case CalPackage.AST_TRANSITION: return createAstTransition();
      case CalPackage.AST_STATE: return createAstState();
      case CalPackage.AST_ACTION: return createAstAction();
      case CalPackage.AST_INPUT_PATTERN: return createAstInputPattern();
      case CalPackage.AST_OUTPUT_PATTERN: return createAstOutputPattern();
      case CalPackage.AST_STATEMENT_ASSIGN: return createAstStatementAssign();
      case CalPackage.AST_STATEMENT_CALL: return createAstStatementCall();
      case CalPackage.AST_STATEMENT_FOREACH: return createAstStatementForeach();
      case CalPackage.AST_FOREACH_GENERATOR: return createAstForeachGenerator();
      case CalPackage.AST_STATEMENT_BLOCK: return createAstStatementBlock();
      case CalPackage.AST_STATEMENT_IF: return createAstStatementIf();
      case CalPackage.AST_STATEMENT_WHILE: return createAstStatementWhile();
      case CalPackage.AST_STATEMENT_CASE: return createAstStatementCase();
      case CalPackage.AST_STATEMENT_ALTERNATIVE: return createAstStatementAlternative();
      case CalPackage.AST_STATEMENT: return createAstStatement();
      case CalPackage.AST_EXPRESSION: return createAstExpression();
      case CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE: return createAstExpressionSymbolReference();
      case CalPackage.AST_EXPRESSION_IF: return createAstExpressionIf();
      case CalPackage.AST_EXPRESSION_LIST: return createAstExpressionList();
      case CalPackage.AST_GENERATOR: return createAstGenerator();
      case CalPackage.AST_EXPRESSION_CASE: return createAstExpressionCase();
      case CalPackage.AST_EXPRESSION_ALTERNATIVE: return createAstExpressionAlternative();
      case CalPackage.AST_EXPRESSION_LITERAL: return createAstExpressionLiteral();
      case CalPackage.AST_EXPRESSION_BOOLEAN: return createAstExpressionBoolean();
      case CalPackage.AST_EXPRESSION_FLOAT: return createAstExpressionFloat();
      case CalPackage.AST_EXPRESSION_INTEGER: return createAstExpressionInteger();
      case CalPackage.AST_EXPRESSION_STRING: return createAstExpressionString();
      case CalPackage.AST_PATTERN: return createAstPattern();
      case CalPackage.AST_SUB_PATTERN: return createAstSubPattern();
      case CalPackage.AST_PATTERN_EXPRESSION_IF: return createAstPatternExpressionIf();
      case CalPackage.AST_TYPE: return createAstType();
      case CalPackage.AST_TYPE_PARAMETER_LIST: return createAstTypeParameterList();
      case CalPackage.AST_TYPE_PARAM: return createAstTypeParam();
      case CalPackage.AST_MEMBER_ACCESS: return createAstMemberAccess();
      case CalPackage.AST_ANNOTATION: return createAstAnnotation();
      case CalPackage.AST_ANNOTATION_ARGUMENT: return createAstAnnotationArgument();
      case CalPackage.AST_TUPLE: return createAstTuple();
      case CalPackage.AST_FUNCTION: return createAstFunction();
      case CalPackage.AST_INITIALIZE: return createAstInitialize();
      case CalPackage.AST_EXPRESSION_BINARY: return createAstExpressionBinary();
      case CalPackage.AST_EXPRESSION_UNARY: return createAstExpressionUnary();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstTop createAstTop()
  {
    AstTopImpl astTop = new AstTopImpl();
    return astTop;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstPackage createAstPackage()
  {
    AstPackageImpl astPackage = new AstPackageImpl();
    return astPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstUnit createAstUnit()
  {
    AstUnitImpl astUnit = new AstUnitImpl();
    return astUnit;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstNamespace createAstNamespace()
  {
    AstNamespaceImpl astNamespace = new AstNamespaceImpl();
    return astNamespace;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstEntity createAstEntity()
  {
    AstEntityImpl astEntity = new AstEntityImpl();
    return astEntity;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstAbstractActor createAstAbstractActor()
  {
    AstAbstractActorImpl astAbstractActor = new AstAbstractActorImpl();
    return astAbstractActor;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Import createImport()
  {
    ImportImpl import_ = new ImportImpl();
    return import_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstNetwork createAstNetwork()
  {
    AstNetworkImpl astNetwork = new AstNetworkImpl();
    return astNetwork;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstActorVariable createAstActorVariable()
  {
    AstActorVariableImpl astActorVariable = new AstActorVariableImpl();
    return astActorVariable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstAssignParameter createAstAssignParameter()
  {
    AstAssignParameterImpl astAssignParameter = new AstAssignParameterImpl();
    return astAssignParameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstStructure createAstStructure()
  {
    AstStructureImpl astStructure = new AstStructureImpl();
    return astStructure;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstConnection createAstConnection()
  {
    AstConnectionImpl astConnection = new AstConnectionImpl();
    return astConnection;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstActorVariableReference createAstActorVariableReference()
  {
    AstActorVariableReferenceImpl astActorVariableReference = new AstActorVariableReferenceImpl();
    return astActorVariableReference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstConnectionAttribute createAstConnectionAttribute()
  {
    AstConnectionAttributeImpl astConnectionAttribute = new AstConnectionAttributeImpl();
    return astConnectionAttribute;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstVariable createAstVariable()
  {
    AstVariableImpl astVariable = new AstVariableImpl();
    return astVariable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstTypeUser createAstTypeUser()
  {
    AstTypeUserImpl astTypeUser = new AstTypeUserImpl();
    return astTypeUser;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstTypeDefinitionParameter createAstTypeDefinitionParameter()
  {
    AstTypeDefinitionParameterImpl astTypeDefinitionParameter = new AstTypeDefinitionParameterImpl();
    return astTypeDefinitionParameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstTaggedTuple createAstTaggedTuple()
  {
    AstTaggedTupleImpl astTaggedTuple = new AstTaggedTupleImpl();
    return astTaggedTuple;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstActor createAstActor()
  {
    AstActorImpl astActor = new AstActorImpl();
    return astActor;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstPort createAstPort()
  {
    AstPortImpl astPort = new AstPortImpl();
    return astPort;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstExternalFunction createAstExternalFunction()
  {
    AstExternalFunctionImpl astExternalFunction = new AstExternalFunctionImpl();
    return astExternalFunction;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstProcedure createAstProcedure()
  {
    AstProcedureImpl astProcedure = new AstProcedureImpl();
    return astProcedure;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstExternalProcedure createAstExternalProcedure()
  {
    AstExternalProcedureImpl astExternalProcedure = new AstExternalProcedureImpl();
    return astExternalProcedure;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstTag createAstTag()
  {
    AstTagImpl astTag = new AstTagImpl();
    return astTag;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstExternalActor createAstExternalActor()
  {
    AstExternalActorImpl astExternalActor = new AstExternalActorImpl();
    return astExternalActor;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstInequality createAstInequality()
  {
    AstInequalityImpl astInequality = new AstInequalityImpl();
    return astInequality;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstPriority createAstPriority()
  {
    AstPriorityImpl astPriority = new AstPriorityImpl();
    return astPriority;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstSchedule createAstSchedule()
  {
    AstScheduleImpl astSchedule = new AstScheduleImpl();
    return astSchedule;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstTransition createAstTransition()
  {
    AstTransitionImpl astTransition = new AstTransitionImpl();
    return astTransition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstState createAstState()
  {
    AstStateImpl astState = new AstStateImpl();
    return astState;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstAction createAstAction()
  {
    AstActionImpl astAction = new AstActionImpl();
    return astAction;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstInputPattern createAstInputPattern()
  {
    AstInputPatternImpl astInputPattern = new AstInputPatternImpl();
    return astInputPattern;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstOutputPattern createAstOutputPattern()
  {
    AstOutputPatternImpl astOutputPattern = new AstOutputPatternImpl();
    return astOutputPattern;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstStatementAssign createAstStatementAssign()
  {
    AstStatementAssignImpl astStatementAssign = new AstStatementAssignImpl();
    return astStatementAssign;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstStatementCall createAstStatementCall()
  {
    AstStatementCallImpl astStatementCall = new AstStatementCallImpl();
    return astStatementCall;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstStatementForeach createAstStatementForeach()
  {
    AstStatementForeachImpl astStatementForeach = new AstStatementForeachImpl();
    return astStatementForeach;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstForeachGenerator createAstForeachGenerator()
  {
    AstForeachGeneratorImpl astForeachGenerator = new AstForeachGeneratorImpl();
    return astForeachGenerator;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstStatementBlock createAstStatementBlock()
  {
    AstStatementBlockImpl astStatementBlock = new AstStatementBlockImpl();
    return astStatementBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstStatementIf createAstStatementIf()
  {
    AstStatementIfImpl astStatementIf = new AstStatementIfImpl();
    return astStatementIf;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstStatementWhile createAstStatementWhile()
  {
    AstStatementWhileImpl astStatementWhile = new AstStatementWhileImpl();
    return astStatementWhile;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstStatementCase createAstStatementCase()
  {
    AstStatementCaseImpl astStatementCase = new AstStatementCaseImpl();
    return astStatementCase;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstStatementAlternative createAstStatementAlternative()
  {
    AstStatementAlternativeImpl astStatementAlternative = new AstStatementAlternativeImpl();
    return astStatementAlternative;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstStatement createAstStatement()
  {
    AstStatementImpl astStatement = new AstStatementImpl();
    return astStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstExpression createAstExpression()
  {
    AstExpressionImpl astExpression = new AstExpressionImpl();
    return astExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstExpressionSymbolReference createAstExpressionSymbolReference()
  {
    AstExpressionSymbolReferenceImpl astExpressionSymbolReference = new AstExpressionSymbolReferenceImpl();
    return astExpressionSymbolReference;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstExpressionIf createAstExpressionIf()
  {
    AstExpressionIfImpl astExpressionIf = new AstExpressionIfImpl();
    return astExpressionIf;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstExpressionList createAstExpressionList()
  {
    AstExpressionListImpl astExpressionList = new AstExpressionListImpl();
    return astExpressionList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstGenerator createAstGenerator()
  {
    AstGeneratorImpl astGenerator = new AstGeneratorImpl();
    return astGenerator;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstExpressionCase createAstExpressionCase()
  {
    AstExpressionCaseImpl astExpressionCase = new AstExpressionCaseImpl();
    return astExpressionCase;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstExpressionAlternative createAstExpressionAlternative()
  {
    AstExpressionAlternativeImpl astExpressionAlternative = new AstExpressionAlternativeImpl();
    return astExpressionAlternative;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstExpressionLiteral createAstExpressionLiteral()
  {
    AstExpressionLiteralImpl astExpressionLiteral = new AstExpressionLiteralImpl();
    return astExpressionLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstExpressionBoolean createAstExpressionBoolean()
  {
    AstExpressionBooleanImpl astExpressionBoolean = new AstExpressionBooleanImpl();
    return astExpressionBoolean;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstExpressionFloat createAstExpressionFloat()
  {
    AstExpressionFloatImpl astExpressionFloat = new AstExpressionFloatImpl();
    return astExpressionFloat;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstExpressionInteger createAstExpressionInteger()
  {
    AstExpressionIntegerImpl astExpressionInteger = new AstExpressionIntegerImpl();
    return astExpressionInteger;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstExpressionString createAstExpressionString()
  {
    AstExpressionStringImpl astExpressionString = new AstExpressionStringImpl();
    return astExpressionString;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstPattern createAstPattern()
  {
    AstPatternImpl astPattern = new AstPatternImpl();
    return astPattern;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstSubPattern createAstSubPattern()
  {
    AstSubPatternImpl astSubPattern = new AstSubPatternImpl();
    return astSubPattern;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstPatternExpressionIf createAstPatternExpressionIf()
  {
    AstPatternExpressionIfImpl astPatternExpressionIf = new AstPatternExpressionIfImpl();
    return astPatternExpressionIf;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstType createAstType()
  {
    AstTypeImpl astType = new AstTypeImpl();
    return astType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstTypeParameterList createAstTypeParameterList()
  {
    AstTypeParameterListImpl astTypeParameterList = new AstTypeParameterListImpl();
    return astTypeParameterList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstTypeParam createAstTypeParam()
  {
    AstTypeParamImpl astTypeParam = new AstTypeParamImpl();
    return astTypeParam;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstMemberAccess createAstMemberAccess()
  {
    AstMemberAccessImpl astMemberAccess = new AstMemberAccessImpl();
    return astMemberAccess;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstAnnotation createAstAnnotation()
  {
    AstAnnotationImpl astAnnotation = new AstAnnotationImpl();
    return astAnnotation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstAnnotationArgument createAstAnnotationArgument()
  {
    AstAnnotationArgumentImpl astAnnotationArgument = new AstAnnotationArgumentImpl();
    return astAnnotationArgument;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstTuple createAstTuple()
  {
    AstTupleImpl astTuple = new AstTupleImpl();
    return astTuple;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstFunction createAstFunction()
  {
    AstFunctionImpl astFunction = new AstFunctionImpl();
    return astFunction;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstInitialize createAstInitialize()
  {
    AstInitializeImpl astInitialize = new AstInitializeImpl();
    return astInitialize;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstExpressionBinary createAstExpressionBinary()
  {
    AstExpressionBinaryImpl astExpressionBinary = new AstExpressionBinaryImpl();
    return astExpressionBinary;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AstExpressionUnary createAstExpressionUnary()
  {
    AstExpressionUnaryImpl astExpressionUnary = new AstExpressionUnaryImpl();
    return astExpressionUnary;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CalPackage getCalPackage()
  {
    return (CalPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static CalPackage getPackage()
  {
    return CalPackage.eINSTANCE;
  }

} //CalFactoryImpl
