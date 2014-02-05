/**
 */
package org.caltoopia.frontend.cal.util;

import org.caltoopia.frontend.cal.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.caltoopia.frontend.cal.CalPackage
 * @generated
 */
public class CalSwitch<T> extends Switch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static CalPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CalSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = CalPackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @parameter ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(EPackage ePackage)
  {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @Override
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case CalPackage.AST_TOP:
      {
        AstTop astTop = (AstTop)theEObject;
        T result = caseAstTop(astTop);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_PACKAGE:
      {
        AstPackage astPackage = (AstPackage)theEObject;
        T result = caseAstPackage(astPackage);
        if (result == null) result = caseAstTop(astPackage);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_UNIT:
      {
        AstUnit astUnit = (AstUnit)theEObject;
        T result = caseAstUnit(astUnit);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_NAMESPACE:
      {
        AstNamespace astNamespace = (AstNamespace)theEObject;
        T result = caseAstNamespace(astNamespace);
        if (result == null) result = caseAstPackage(astNamespace);
        if (result == null) result = caseAstUnit(astNamespace);
        if (result == null) result = caseAstTop(astNamespace);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_ENTITY:
      {
        AstEntity astEntity = (AstEntity)theEObject;
        T result = caseAstEntity(astEntity);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_ABSTRACT_ACTOR:
      {
        AstAbstractActor astAbstractActor = (AstAbstractActor)theEObject;
        T result = caseAstAbstractActor(astAbstractActor);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.IMPORT:
      {
        Import import_ = (Import)theEObject;
        T result = caseImport(import_);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_NETWORK:
      {
        AstNetwork astNetwork = (AstNetwork)theEObject;
        T result = caseAstNetwork(astNetwork);
        if (result == null) result = caseAstAbstractActor(astNetwork);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_ACTOR_VARIABLE:
      {
        AstActorVariable astActorVariable = (AstActorVariable)theEObject;
        T result = caseAstActorVariable(astActorVariable);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_ASSIGN_PARAMETER:
      {
        AstAssignParameter astAssignParameter = (AstAssignParameter)theEObject;
        T result = caseAstAssignParameter(astAssignParameter);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_STRUCTURE:
      {
        AstStructure astStructure = (AstStructure)theEObject;
        T result = caseAstStructure(astStructure);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_CONNECTION:
      {
        AstConnection astConnection = (AstConnection)theEObject;
        T result = caseAstConnection(astConnection);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_ACTOR_VARIABLE_REFERENCE:
      {
        AstActorVariableReference astActorVariableReference = (AstActorVariableReference)theEObject;
        T result = caseAstActorVariableReference(astActorVariableReference);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_CONNECTION_ATTRIBUTE:
      {
        AstConnectionAttribute astConnectionAttribute = (AstConnectionAttribute)theEObject;
        T result = caseAstConnectionAttribute(astConnectionAttribute);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_VARIABLE:
      {
        AstVariable astVariable = (AstVariable)theEObject;
        T result = caseAstVariable(astVariable);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_TYPE_USER:
      {
        AstTypeUser astTypeUser = (AstTypeUser)theEObject;
        T result = caseAstTypeUser(astTypeUser);
        if (result == null) result = caseAstVariable(astTypeUser);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_TYPE_DEFINITION_PARAMETER:
      {
        AstTypeDefinitionParameter astTypeDefinitionParameter = (AstTypeDefinitionParameter)theEObject;
        T result = caseAstTypeDefinitionParameter(astTypeDefinitionParameter);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_TAGGED_TUPLE:
      {
        AstTaggedTuple astTaggedTuple = (AstTaggedTuple)theEObject;
        T result = caseAstTaggedTuple(astTaggedTuple);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_ACTOR:
      {
        AstActor astActor = (AstActor)theEObject;
        T result = caseAstActor(astActor);
        if (result == null) result = caseAstAbstractActor(astActor);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_PORT:
      {
        AstPort astPort = (AstPort)theEObject;
        T result = caseAstPort(astPort);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_EXTERNAL_FUNCTION:
      {
        AstExternalFunction astExternalFunction = (AstExternalFunction)theEObject;
        T result = caseAstExternalFunction(astExternalFunction);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_PROCEDURE:
      {
        AstProcedure astProcedure = (AstProcedure)theEObject;
        T result = caseAstProcedure(astProcedure);
        if (result == null) result = caseAstExternalProcedure(astProcedure);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_EXTERNAL_PROCEDURE:
      {
        AstExternalProcedure astExternalProcedure = (AstExternalProcedure)theEObject;
        T result = caseAstExternalProcedure(astExternalProcedure);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_TAG:
      {
        AstTag astTag = (AstTag)theEObject;
        T result = caseAstTag(astTag);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_EXTERNAL_ACTOR:
      {
        AstExternalActor astExternalActor = (AstExternalActor)theEObject;
        T result = caseAstExternalActor(astExternalActor);
        if (result == null) result = caseAstAbstractActor(astExternalActor);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_INEQUALITY:
      {
        AstInequality astInequality = (AstInequality)theEObject;
        T result = caseAstInequality(astInequality);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_PRIORITY:
      {
        AstPriority astPriority = (AstPriority)theEObject;
        T result = caseAstPriority(astPriority);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_SCHEDULE:
      {
        AstSchedule astSchedule = (AstSchedule)theEObject;
        T result = caseAstSchedule(astSchedule);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_TRANSITION:
      {
        AstTransition astTransition = (AstTransition)theEObject;
        T result = caseAstTransition(astTransition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_STATE:
      {
        AstState astState = (AstState)theEObject;
        T result = caseAstState(astState);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_ACTION:
      {
        AstAction astAction = (AstAction)theEObject;
        T result = caseAstAction(astAction);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_INPUT_PATTERN:
      {
        AstInputPattern astInputPattern = (AstInputPattern)theEObject;
        T result = caseAstInputPattern(astInputPattern);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_OUTPUT_PATTERN:
      {
        AstOutputPattern astOutputPattern = (AstOutputPattern)theEObject;
        T result = caseAstOutputPattern(astOutputPattern);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_STATEMENT_ASSIGN:
      {
        AstStatementAssign astStatementAssign = (AstStatementAssign)theEObject;
        T result = caseAstStatementAssign(astStatementAssign);
        if (result == null) result = caseAstStatement(astStatementAssign);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_STATEMENT_CALL:
      {
        AstStatementCall astStatementCall = (AstStatementCall)theEObject;
        T result = caseAstStatementCall(astStatementCall);
        if (result == null) result = caseAstStatement(astStatementCall);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_STATEMENT_FOREACH:
      {
        AstStatementForeach astStatementForeach = (AstStatementForeach)theEObject;
        T result = caseAstStatementForeach(astStatementForeach);
        if (result == null) result = caseAstStatement(astStatementForeach);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_FOREACH_GENERATOR:
      {
        AstForeachGenerator astForeachGenerator = (AstForeachGenerator)theEObject;
        T result = caseAstForeachGenerator(astForeachGenerator);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_STATEMENT_BLOCK:
      {
        AstStatementBlock astStatementBlock = (AstStatementBlock)theEObject;
        T result = caseAstStatementBlock(astStatementBlock);
        if (result == null) result = caseAstStatement(astStatementBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_STATEMENT_IF:
      {
        AstStatementIf astStatementIf = (AstStatementIf)theEObject;
        T result = caseAstStatementIf(astStatementIf);
        if (result == null) result = caseAstStatement(astStatementIf);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_STATEMENT_WHILE:
      {
        AstStatementWhile astStatementWhile = (AstStatementWhile)theEObject;
        T result = caseAstStatementWhile(astStatementWhile);
        if (result == null) result = caseAstStatement(astStatementWhile);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_STATEMENT_CASE:
      {
        AstStatementCase astStatementCase = (AstStatementCase)theEObject;
        T result = caseAstStatementCase(astStatementCase);
        if (result == null) result = caseAstStatement(astStatementCase);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_STATEMENT_ALTERNATIVE:
      {
        AstStatementAlternative astStatementAlternative = (AstStatementAlternative)theEObject;
        T result = caseAstStatementAlternative(astStatementAlternative);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_STATEMENT:
      {
        AstStatement astStatement = (AstStatement)theEObject;
        T result = caseAstStatement(astStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_EXPRESSION:
      {
        AstExpression astExpression = (AstExpression)theEObject;
        T result = caseAstExpression(astExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE:
      {
        AstExpressionSymbolReference astExpressionSymbolReference = (AstExpressionSymbolReference)theEObject;
        T result = caseAstExpressionSymbolReference(astExpressionSymbolReference);
        if (result == null) result = caseAstExpression(astExpressionSymbolReference);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_EXPRESSION_IF:
      {
        AstExpressionIf astExpressionIf = (AstExpressionIf)theEObject;
        T result = caseAstExpressionIf(astExpressionIf);
        if (result == null) result = caseAstExpression(astExpressionIf);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_EXPRESSION_LIST:
      {
        AstExpressionList astExpressionList = (AstExpressionList)theEObject;
        T result = caseAstExpressionList(astExpressionList);
        if (result == null) result = caseAstExpression(astExpressionList);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_GENERATOR:
      {
        AstGenerator astGenerator = (AstGenerator)theEObject;
        T result = caseAstGenerator(astGenerator);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_EXPRESSION_CASE:
      {
        AstExpressionCase astExpressionCase = (AstExpressionCase)theEObject;
        T result = caseAstExpressionCase(astExpressionCase);
        if (result == null) result = caseAstExpression(astExpressionCase);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_EXPRESSION_ALTERNATIVE:
      {
        AstExpressionAlternative astExpressionAlternative = (AstExpressionAlternative)theEObject;
        T result = caseAstExpressionAlternative(astExpressionAlternative);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_EXPRESSION_LITERAL:
      {
        AstExpressionLiteral astExpressionLiteral = (AstExpressionLiteral)theEObject;
        T result = caseAstExpressionLiteral(astExpressionLiteral);
        if (result == null) result = caseAstExpression(astExpressionLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_EXPRESSION_BOOLEAN:
      {
        AstExpressionBoolean astExpressionBoolean = (AstExpressionBoolean)theEObject;
        T result = caseAstExpressionBoolean(astExpressionBoolean);
        if (result == null) result = caseAstExpressionLiteral(astExpressionBoolean);
        if (result == null) result = caseAstExpression(astExpressionBoolean);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_EXPRESSION_FLOAT:
      {
        AstExpressionFloat astExpressionFloat = (AstExpressionFloat)theEObject;
        T result = caseAstExpressionFloat(astExpressionFloat);
        if (result == null) result = caseAstExpressionLiteral(astExpressionFloat);
        if (result == null) result = caseAstExpression(astExpressionFloat);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_EXPRESSION_INTEGER:
      {
        AstExpressionInteger astExpressionInteger = (AstExpressionInteger)theEObject;
        T result = caseAstExpressionInteger(astExpressionInteger);
        if (result == null) result = caseAstExpressionLiteral(astExpressionInteger);
        if (result == null) result = caseAstExpression(astExpressionInteger);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_EXPRESSION_STRING:
      {
        AstExpressionString astExpressionString = (AstExpressionString)theEObject;
        T result = caseAstExpressionString(astExpressionString);
        if (result == null) result = caseAstExpressionLiteral(astExpressionString);
        if (result == null) result = caseAstExpression(astExpressionString);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_PATTERN:
      {
        AstPattern astPattern = (AstPattern)theEObject;
        T result = caseAstPattern(astPattern);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_SUB_PATTERN:
      {
        AstSubPattern astSubPattern = (AstSubPattern)theEObject;
        T result = caseAstSubPattern(astSubPattern);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_TYPE:
      {
        AstType astType = (AstType)theEObject;
        T result = caseAstType(astType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_TYPE_PARAMETER_LIST:
      {
        AstTypeParameterList astTypeParameterList = (AstTypeParameterList)theEObject;
        T result = caseAstTypeParameterList(astTypeParameterList);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_TYPE_PARAM:
      {
        AstTypeParam astTypeParam = (AstTypeParam)theEObject;
        T result = caseAstTypeParam(astTypeParam);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_MEMBER_ACCESS:
      {
        AstMemberAccess astMemberAccess = (AstMemberAccess)theEObject;
        T result = caseAstMemberAccess(astMemberAccess);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_ANNOTATION:
      {
        AstAnnotation astAnnotation = (AstAnnotation)theEObject;
        T result = caseAstAnnotation(astAnnotation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_ANNOTATION_ARGUMENT:
      {
        AstAnnotationArgument astAnnotationArgument = (AstAnnotationArgument)theEObject;
        T result = caseAstAnnotationArgument(astAnnotationArgument);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_TUPLE:
      {
        AstTuple astTuple = (AstTuple)theEObject;
        T result = caseAstTuple(astTuple);
        if (result == null) result = caseAstTaggedTuple(astTuple);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_FUNCTION:
      {
        AstFunction astFunction = (AstFunction)theEObject;
        T result = caseAstFunction(astFunction);
        if (result == null) result = caseAstVariable(astFunction);
        if (result == null) result = caseAstExternalFunction(astFunction);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_INITIALIZE:
      {
        AstInitialize astInitialize = (AstInitialize)theEObject;
        T result = caseAstInitialize(astInitialize);
        if (result == null) result = caseAstAction(astInitialize);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_EXPRESSION_BINARY:
      {
        AstExpressionBinary astExpressionBinary = (AstExpressionBinary)theEObject;
        T result = caseAstExpressionBinary(astExpressionBinary);
        if (result == null) result = caseAstExpression(astExpressionBinary);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case CalPackage.AST_EXPRESSION_UNARY:
      {
        AstExpressionUnary astExpressionUnary = (AstExpressionUnary)theEObject;
        T result = caseAstExpressionUnary(astExpressionUnary);
        if (result == null) result = caseAstExpression(astExpressionUnary);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Top</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Top</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstTop(AstTop object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Package</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Package</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstPackage(AstPackage object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Unit</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Unit</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstUnit(AstUnit object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Namespace</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Namespace</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstNamespace(AstNamespace object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Entity</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Entity</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstEntity(AstEntity object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Abstract Actor</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Abstract Actor</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstAbstractActor(AstAbstractActor object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Import</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Import</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseImport(Import object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Network</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Network</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstNetwork(AstNetwork object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Actor Variable</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Actor Variable</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstActorVariable(AstActorVariable object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Assign Parameter</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Assign Parameter</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstAssignParameter(AstAssignParameter object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Structure</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Structure</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstStructure(AstStructure object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Connection</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Connection</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstConnection(AstConnection object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Actor Variable Reference</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Actor Variable Reference</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstActorVariableReference(AstActorVariableReference object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Connection Attribute</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Connection Attribute</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstConnectionAttribute(AstConnectionAttribute object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Variable</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Variable</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstVariable(AstVariable object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Type User</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Type User</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstTypeUser(AstTypeUser object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Type Definition Parameter</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Type Definition Parameter</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstTypeDefinitionParameter(AstTypeDefinitionParameter object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Tagged Tuple</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Tagged Tuple</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstTaggedTuple(AstTaggedTuple object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Actor</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Actor</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstActor(AstActor object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Port</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Port</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstPort(AstPort object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast External Function</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast External Function</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstExternalFunction(AstExternalFunction object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Procedure</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Procedure</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstProcedure(AstProcedure object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast External Procedure</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast External Procedure</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstExternalProcedure(AstExternalProcedure object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Tag</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Tag</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstTag(AstTag object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast External Actor</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast External Actor</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstExternalActor(AstExternalActor object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Inequality</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Inequality</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstInequality(AstInequality object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Priority</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Priority</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstPriority(AstPriority object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Schedule</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Schedule</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstSchedule(AstSchedule object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Transition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Transition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstTransition(AstTransition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast State</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast State</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstState(AstState object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Action</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Action</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstAction(AstAction object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Input Pattern</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Input Pattern</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstInputPattern(AstInputPattern object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Output Pattern</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Output Pattern</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstOutputPattern(AstOutputPattern object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Statement Assign</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Statement Assign</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstStatementAssign(AstStatementAssign object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Statement Call</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Statement Call</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstStatementCall(AstStatementCall object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Statement Foreach</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Statement Foreach</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstStatementForeach(AstStatementForeach object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Foreach Generator</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Foreach Generator</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstForeachGenerator(AstForeachGenerator object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Statement Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Statement Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstStatementBlock(AstStatementBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Statement If</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Statement If</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstStatementIf(AstStatementIf object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Statement While</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Statement While</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstStatementWhile(AstStatementWhile object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Statement Case</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Statement Case</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstStatementCase(AstStatementCase object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Statement Alternative</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Statement Alternative</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstStatementAlternative(AstStatementAlternative object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstStatement(AstStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstExpression(AstExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Expression Symbol Reference</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Expression Symbol Reference</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstExpressionSymbolReference(AstExpressionSymbolReference object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Expression If</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Expression If</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstExpressionIf(AstExpressionIf object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Expression List</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Expression List</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstExpressionList(AstExpressionList object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Generator</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Generator</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstGenerator(AstGenerator object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Expression Case</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Expression Case</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstExpressionCase(AstExpressionCase object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Expression Alternative</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Expression Alternative</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstExpressionAlternative(AstExpressionAlternative object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Expression Literal</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Expression Literal</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstExpressionLiteral(AstExpressionLiteral object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Expression Boolean</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Expression Boolean</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstExpressionBoolean(AstExpressionBoolean object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Expression Float</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Expression Float</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstExpressionFloat(AstExpressionFloat object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Expression Integer</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Expression Integer</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstExpressionInteger(AstExpressionInteger object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Expression String</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Expression String</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstExpressionString(AstExpressionString object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Pattern</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Pattern</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstPattern(AstPattern object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Sub Pattern</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Sub Pattern</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstSubPattern(AstSubPattern object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstType(AstType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Type Parameter List</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Type Parameter List</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstTypeParameterList(AstTypeParameterList object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Type Param</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Type Param</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstTypeParam(AstTypeParam object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Member Access</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Member Access</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstMemberAccess(AstMemberAccess object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Annotation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Annotation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstAnnotation(AstAnnotation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Annotation Argument</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Annotation Argument</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstAnnotationArgument(AstAnnotationArgument object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Tuple</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Tuple</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstTuple(AstTuple object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Function</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Function</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstFunction(AstFunction object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Initialize</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Initialize</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstInitialize(AstInitialize object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Expression Binary</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Expression Binary</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstExpressionBinary(AstExpressionBinary object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ast Expression Unary</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ast Expression Unary</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAstExpressionUnary(AstExpressionUnary object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  @Override
  public T defaultCase(EObject object)
  {
    return null;
  }

} //CalSwitch
