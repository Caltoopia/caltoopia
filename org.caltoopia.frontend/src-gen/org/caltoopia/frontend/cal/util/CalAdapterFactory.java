/**
 */
package org.caltoopia.frontend.cal.util;

import org.caltoopia.frontend.cal.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.caltoopia.frontend.cal.CalPackage
 * @generated
 */
public class CalAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static CalPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CalAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = CalPackage.eINSTANCE;
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
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
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
  protected CalSwitch<Adapter> modelSwitch =
    new CalSwitch<Adapter>()
    {
      @Override
      public Adapter caseAstTop(AstTop object)
      {
        return createAstTopAdapter();
      }
      @Override
      public Adapter caseAstPackage(AstPackage object)
      {
        return createAstPackageAdapter();
      }
      @Override
      public Adapter caseAstUnit(AstUnit object)
      {
        return createAstUnitAdapter();
      }
      @Override
      public Adapter caseAstNamespace(AstNamespace object)
      {
        return createAstNamespaceAdapter();
      }
      @Override
      public Adapter caseAstEntity(AstEntity object)
      {
        return createAstEntityAdapter();
      }
      @Override
      public Adapter caseAstAbstractActor(AstAbstractActor object)
      {
        return createAstAbstractActorAdapter();
      }
      @Override
      public Adapter caseImport(Import object)
      {
        return createImportAdapter();
      }
      @Override
      public Adapter caseAstNetwork(AstNetwork object)
      {
        return createAstNetworkAdapter();
      }
      @Override
      public Adapter caseAstActorVariable(AstActorVariable object)
      {
        return createAstActorVariableAdapter();
      }
      @Override
      public Adapter caseAstAssignParameter(AstAssignParameter object)
      {
        return createAstAssignParameterAdapter();
      }
      @Override
      public Adapter caseAstStructure(AstStructure object)
      {
        return createAstStructureAdapter();
      }
      @Override
      public Adapter caseAstConnection(AstConnection object)
      {
        return createAstConnectionAdapter();
      }
      @Override
      public Adapter caseAstActorVariableReference(AstActorVariableReference object)
      {
        return createAstActorVariableReferenceAdapter();
      }
      @Override
      public Adapter caseAstConnectionAttribute(AstConnectionAttribute object)
      {
        return createAstConnectionAttributeAdapter();
      }
      @Override
      public Adapter caseAstVariable(AstVariable object)
      {
        return createAstVariableAdapter();
      }
      @Override
      public Adapter caseAstTypeUser(AstTypeUser object)
      {
        return createAstTypeUserAdapter();
      }
      @Override
      public Adapter caseAstTypeDefinitionParameter(AstTypeDefinitionParameter object)
      {
        return createAstTypeDefinitionParameterAdapter();
      }
      @Override
      public Adapter caseAstTaggedTuple(AstTaggedTuple object)
      {
        return createAstTaggedTupleAdapter();
      }
      @Override
      public Adapter caseAstActor(AstActor object)
      {
        return createAstActorAdapter();
      }
      @Override
      public Adapter caseAstPort(AstPort object)
      {
        return createAstPortAdapter();
      }
      @Override
      public Adapter caseAstExternalFunction(AstExternalFunction object)
      {
        return createAstExternalFunctionAdapter();
      }
      @Override
      public Adapter caseAstProcedure(AstProcedure object)
      {
        return createAstProcedureAdapter();
      }
      @Override
      public Adapter caseAstExternalProcedure(AstExternalProcedure object)
      {
        return createAstExternalProcedureAdapter();
      }
      @Override
      public Adapter caseAstTag(AstTag object)
      {
        return createAstTagAdapter();
      }
      @Override
      public Adapter caseAstExternalActor(AstExternalActor object)
      {
        return createAstExternalActorAdapter();
      }
      @Override
      public Adapter caseAstInequality(AstInequality object)
      {
        return createAstInequalityAdapter();
      }
      @Override
      public Adapter caseAstPriority(AstPriority object)
      {
        return createAstPriorityAdapter();
      }
      @Override
      public Adapter caseAstSchedule(AstSchedule object)
      {
        return createAstScheduleAdapter();
      }
      @Override
      public Adapter caseAstTransition(AstTransition object)
      {
        return createAstTransitionAdapter();
      }
      @Override
      public Adapter caseAstState(AstState object)
      {
        return createAstStateAdapter();
      }
      @Override
      public Adapter caseAstAction(AstAction object)
      {
        return createAstActionAdapter();
      }
      @Override
      public Adapter caseAstInputPattern(AstInputPattern object)
      {
        return createAstInputPatternAdapter();
      }
      @Override
      public Adapter caseAstOutputPattern(AstOutputPattern object)
      {
        return createAstOutputPatternAdapter();
      }
      @Override
      public Adapter caseAstStatementAssign(AstStatementAssign object)
      {
        return createAstStatementAssignAdapter();
      }
      @Override
      public Adapter caseAstStatementCall(AstStatementCall object)
      {
        return createAstStatementCallAdapter();
      }
      @Override
      public Adapter caseAstStatementForeach(AstStatementForeach object)
      {
        return createAstStatementForeachAdapter();
      }
      @Override
      public Adapter caseAstForeachGenerator(AstForeachGenerator object)
      {
        return createAstForeachGeneratorAdapter();
      }
      @Override
      public Adapter caseAstStatementBlock(AstStatementBlock object)
      {
        return createAstStatementBlockAdapter();
      }
      @Override
      public Adapter caseAstStatementIf(AstStatementIf object)
      {
        return createAstStatementIfAdapter();
      }
      @Override
      public Adapter caseAstStatementWhile(AstStatementWhile object)
      {
        return createAstStatementWhileAdapter();
      }
      @Override
      public Adapter caseAstStatementCase(AstStatementCase object)
      {
        return createAstStatementCaseAdapter();
      }
      @Override
      public Adapter caseAstStatementAlternative(AstStatementAlternative object)
      {
        return createAstStatementAlternativeAdapter();
      }
      @Override
      public Adapter caseAstStatement(AstStatement object)
      {
        return createAstStatementAdapter();
      }
      @Override
      public Adapter caseAstExpression(AstExpression object)
      {
        return createAstExpressionAdapter();
      }
      @Override
      public Adapter caseAstExpressionSymbolReference(AstExpressionSymbolReference object)
      {
        return createAstExpressionSymbolReferenceAdapter();
      }
      @Override
      public Adapter caseAstExpressionIf(AstExpressionIf object)
      {
        return createAstExpressionIfAdapter();
      }
      @Override
      public Adapter caseAstExpressionList(AstExpressionList object)
      {
        return createAstExpressionListAdapter();
      }
      @Override
      public Adapter caseAstGenerator(AstGenerator object)
      {
        return createAstGeneratorAdapter();
      }
      @Override
      public Adapter caseAstExpressionCase(AstExpressionCase object)
      {
        return createAstExpressionCaseAdapter();
      }
      @Override
      public Adapter caseAstExpressionAlternative(AstExpressionAlternative object)
      {
        return createAstExpressionAlternativeAdapter();
      }
      @Override
      public Adapter caseAstExpressionLiteral(AstExpressionLiteral object)
      {
        return createAstExpressionLiteralAdapter();
      }
      @Override
      public Adapter caseAstExpressionBoolean(AstExpressionBoolean object)
      {
        return createAstExpressionBooleanAdapter();
      }
      @Override
      public Adapter caseAstExpressionFloat(AstExpressionFloat object)
      {
        return createAstExpressionFloatAdapter();
      }
      @Override
      public Adapter caseAstExpressionInteger(AstExpressionInteger object)
      {
        return createAstExpressionIntegerAdapter();
      }
      @Override
      public Adapter caseAstExpressionString(AstExpressionString object)
      {
        return createAstExpressionStringAdapter();
      }
      @Override
      public Adapter caseAstPattern(AstPattern object)
      {
        return createAstPatternAdapter();
      }
      @Override
      public Adapter caseAstSubPattern(AstSubPattern object)
      {
        return createAstSubPatternAdapter();
      }
      @Override
      public Adapter caseAstPatternExpressionSymbolReference(AstPatternExpressionSymbolReference object)
      {
        return createAstPatternExpressionSymbolReferenceAdapter();
      }
      @Override
      public Adapter caseAstPatternExpressionIf(AstPatternExpressionIf object)
      {
        return createAstPatternExpressionIfAdapter();
      }
      @Override
      public Adapter caseAstPatternExpressionLiteral(AstPatternExpressionLiteral object)
      {
        return createAstPatternExpressionLiteralAdapter();
      }
      @Override
      public Adapter caseAstType(AstType object)
      {
        return createAstTypeAdapter();
      }
      @Override
      public Adapter caseAstTypeParameterList(AstTypeParameterList object)
      {
        return createAstTypeParameterListAdapter();
      }
      @Override
      public Adapter caseAstTypeParam(AstTypeParam object)
      {
        return createAstTypeParamAdapter();
      }
      @Override
      public Adapter caseAstMemberAccess(AstMemberAccess object)
      {
        return createAstMemberAccessAdapter();
      }
      @Override
      public Adapter caseAstAnnotation(AstAnnotation object)
      {
        return createAstAnnotationAdapter();
      }
      @Override
      public Adapter caseAstAnnotationArgument(AstAnnotationArgument object)
      {
        return createAstAnnotationArgumentAdapter();
      }
      @Override
      public Adapter caseAstTuple(AstTuple object)
      {
        return createAstTupleAdapter();
      }
      @Override
      public Adapter caseAstFunction(AstFunction object)
      {
        return createAstFunctionAdapter();
      }
      @Override
      public Adapter caseAstInitialize(AstInitialize object)
      {
        return createAstInitializeAdapter();
      }
      @Override
      public Adapter caseAstExpressionBinary(AstExpressionBinary object)
      {
        return createAstExpressionBinaryAdapter();
      }
      @Override
      public Adapter caseAstExpressionUnary(AstExpressionUnary object)
      {
        return createAstExpressionUnaryAdapter();
      }
      @Override
      public Adapter caseAstPatternExpressionBinary(AstPatternExpressionBinary object)
      {
        return createAstPatternExpressionBinaryAdapter();
      }
      @Override
      public Adapter caseAstPatternExpressionUnary(AstPatternExpressionUnary object)
      {
        return createAstPatternExpressionUnaryAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
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
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstTop <em>Ast Top</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstTop
   * @generated
   */
  public Adapter createAstTopAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstPackage <em>Ast Package</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstPackage
   * @generated
   */
  public Adapter createAstPackageAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstUnit <em>Ast Unit</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstUnit
   * @generated
   */
  public Adapter createAstUnitAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstNamespace <em>Ast Namespace</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstNamespace
   * @generated
   */
  public Adapter createAstNamespaceAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstEntity <em>Ast Entity</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstEntity
   * @generated
   */
  public Adapter createAstEntityAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstAbstractActor <em>Ast Abstract Actor</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstAbstractActor
   * @generated
   */
  public Adapter createAstAbstractActorAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.Import <em>Import</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.Import
   * @generated
   */
  public Adapter createImportAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstNetwork <em>Ast Network</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstNetwork
   * @generated
   */
  public Adapter createAstNetworkAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstActorVariable <em>Ast Actor Variable</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstActorVariable
   * @generated
   */
  public Adapter createAstActorVariableAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstAssignParameter <em>Ast Assign Parameter</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstAssignParameter
   * @generated
   */
  public Adapter createAstAssignParameterAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstStructure <em>Ast Structure</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstStructure
   * @generated
   */
  public Adapter createAstStructureAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstConnection <em>Ast Connection</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstConnection
   * @generated
   */
  public Adapter createAstConnectionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstActorVariableReference <em>Ast Actor Variable Reference</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstActorVariableReference
   * @generated
   */
  public Adapter createAstActorVariableReferenceAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstConnectionAttribute <em>Ast Connection Attribute</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstConnectionAttribute
   * @generated
   */
  public Adapter createAstConnectionAttributeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstVariable <em>Ast Variable</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstVariable
   * @generated
   */
  public Adapter createAstVariableAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstTypeUser <em>Ast Type User</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstTypeUser
   * @generated
   */
  public Adapter createAstTypeUserAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstTypeDefinitionParameter <em>Ast Type Definition Parameter</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstTypeDefinitionParameter
   * @generated
   */
  public Adapter createAstTypeDefinitionParameterAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstTaggedTuple <em>Ast Tagged Tuple</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstTaggedTuple
   * @generated
   */
  public Adapter createAstTaggedTupleAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstActor <em>Ast Actor</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstActor
   * @generated
   */
  public Adapter createAstActorAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstPort <em>Ast Port</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstPort
   * @generated
   */
  public Adapter createAstPortAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstExternalFunction <em>Ast External Function</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstExternalFunction
   * @generated
   */
  public Adapter createAstExternalFunctionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstProcedure <em>Ast Procedure</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstProcedure
   * @generated
   */
  public Adapter createAstProcedureAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstExternalProcedure <em>Ast External Procedure</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstExternalProcedure
   * @generated
   */
  public Adapter createAstExternalProcedureAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstTag <em>Ast Tag</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstTag
   * @generated
   */
  public Adapter createAstTagAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstExternalActor <em>Ast External Actor</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstExternalActor
   * @generated
   */
  public Adapter createAstExternalActorAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstInequality <em>Ast Inequality</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstInequality
   * @generated
   */
  public Adapter createAstInequalityAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstPriority <em>Ast Priority</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstPriority
   * @generated
   */
  public Adapter createAstPriorityAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstSchedule <em>Ast Schedule</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstSchedule
   * @generated
   */
  public Adapter createAstScheduleAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstTransition <em>Ast Transition</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstTransition
   * @generated
   */
  public Adapter createAstTransitionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstState <em>Ast State</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstState
   * @generated
   */
  public Adapter createAstStateAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstAction <em>Ast Action</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstAction
   * @generated
   */
  public Adapter createAstActionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstInputPattern <em>Ast Input Pattern</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstInputPattern
   * @generated
   */
  public Adapter createAstInputPatternAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstOutputPattern <em>Ast Output Pattern</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstOutputPattern
   * @generated
   */
  public Adapter createAstOutputPatternAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstStatementAssign <em>Ast Statement Assign</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstStatementAssign
   * @generated
   */
  public Adapter createAstStatementAssignAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstStatementCall <em>Ast Statement Call</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstStatementCall
   * @generated
   */
  public Adapter createAstStatementCallAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstStatementForeach <em>Ast Statement Foreach</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstStatementForeach
   * @generated
   */
  public Adapter createAstStatementForeachAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstForeachGenerator <em>Ast Foreach Generator</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstForeachGenerator
   * @generated
   */
  public Adapter createAstForeachGeneratorAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstStatementBlock <em>Ast Statement Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstStatementBlock
   * @generated
   */
  public Adapter createAstStatementBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstStatementIf <em>Ast Statement If</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstStatementIf
   * @generated
   */
  public Adapter createAstStatementIfAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstStatementWhile <em>Ast Statement While</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstStatementWhile
   * @generated
   */
  public Adapter createAstStatementWhileAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstStatementCase <em>Ast Statement Case</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstStatementCase
   * @generated
   */
  public Adapter createAstStatementCaseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstStatementAlternative <em>Ast Statement Alternative</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstStatementAlternative
   * @generated
   */
  public Adapter createAstStatementAlternativeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstStatement <em>Ast Statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstStatement
   * @generated
   */
  public Adapter createAstStatementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstExpression <em>Ast Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstExpression
   * @generated
   */
  public Adapter createAstExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstExpressionSymbolReference <em>Ast Expression Symbol Reference</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstExpressionSymbolReference
   * @generated
   */
  public Adapter createAstExpressionSymbolReferenceAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstExpressionIf <em>Ast Expression If</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstExpressionIf
   * @generated
   */
  public Adapter createAstExpressionIfAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstExpressionList <em>Ast Expression List</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstExpressionList
   * @generated
   */
  public Adapter createAstExpressionListAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstGenerator <em>Ast Generator</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstGenerator
   * @generated
   */
  public Adapter createAstGeneratorAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstExpressionCase <em>Ast Expression Case</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstExpressionCase
   * @generated
   */
  public Adapter createAstExpressionCaseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstExpressionAlternative <em>Ast Expression Alternative</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstExpressionAlternative
   * @generated
   */
  public Adapter createAstExpressionAlternativeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstExpressionLiteral <em>Ast Expression Literal</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstExpressionLiteral
   * @generated
   */
  public Adapter createAstExpressionLiteralAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstExpressionBoolean <em>Ast Expression Boolean</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstExpressionBoolean
   * @generated
   */
  public Adapter createAstExpressionBooleanAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstExpressionFloat <em>Ast Expression Float</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstExpressionFloat
   * @generated
   */
  public Adapter createAstExpressionFloatAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstExpressionInteger <em>Ast Expression Integer</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstExpressionInteger
   * @generated
   */
  public Adapter createAstExpressionIntegerAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstExpressionString <em>Ast Expression String</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstExpressionString
   * @generated
   */
  public Adapter createAstExpressionStringAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstPattern <em>Ast Pattern</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstPattern
   * @generated
   */
  public Adapter createAstPatternAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstSubPattern <em>Ast Sub Pattern</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstSubPattern
   * @generated
   */
  public Adapter createAstSubPatternAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstPatternExpressionSymbolReference <em>Ast Pattern Expression Symbol Reference</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstPatternExpressionSymbolReference
   * @generated
   */
  public Adapter createAstPatternExpressionSymbolReferenceAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstPatternExpressionIf <em>Ast Pattern Expression If</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstPatternExpressionIf
   * @generated
   */
  public Adapter createAstPatternExpressionIfAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstPatternExpressionLiteral <em>Ast Pattern Expression Literal</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstPatternExpressionLiteral
   * @generated
   */
  public Adapter createAstPatternExpressionLiteralAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstType <em>Ast Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstType
   * @generated
   */
  public Adapter createAstTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstTypeParameterList <em>Ast Type Parameter List</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstTypeParameterList
   * @generated
   */
  public Adapter createAstTypeParameterListAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstTypeParam <em>Ast Type Param</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstTypeParam
   * @generated
   */
  public Adapter createAstTypeParamAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstMemberAccess <em>Ast Member Access</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstMemberAccess
   * @generated
   */
  public Adapter createAstMemberAccessAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstAnnotation <em>Ast Annotation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstAnnotation
   * @generated
   */
  public Adapter createAstAnnotationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstAnnotationArgument <em>Ast Annotation Argument</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstAnnotationArgument
   * @generated
   */
  public Adapter createAstAnnotationArgumentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstTuple <em>Ast Tuple</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstTuple
   * @generated
   */
  public Adapter createAstTupleAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstFunction <em>Ast Function</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstFunction
   * @generated
   */
  public Adapter createAstFunctionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstInitialize <em>Ast Initialize</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstInitialize
   * @generated
   */
  public Adapter createAstInitializeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstExpressionBinary <em>Ast Expression Binary</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstExpressionBinary
   * @generated
   */
  public Adapter createAstExpressionBinaryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstExpressionUnary <em>Ast Expression Unary</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstExpressionUnary
   * @generated
   */
  public Adapter createAstExpressionUnaryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstPatternExpressionBinary <em>Ast Pattern Expression Binary</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstPatternExpressionBinary
   * @generated
   */
  public Adapter createAstPatternExpressionBinaryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.caltoopia.frontend.cal.AstPatternExpressionUnary <em>Ast Pattern Expression Unary</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.caltoopia.frontend.cal.AstPatternExpressionUnary
   * @generated
   */
  public Adapter createAstPatternExpressionUnaryAdapter()
  {
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
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //CalAdapterFactory
