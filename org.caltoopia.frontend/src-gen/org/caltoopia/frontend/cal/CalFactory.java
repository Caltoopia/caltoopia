/**
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.caltoopia.frontend.cal.CalPackage
 * @generated
 */
public interface CalFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  CalFactory eINSTANCE = org.caltoopia.frontend.cal.impl.CalFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Ast Top</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Top</em>'.
   * @generated
   */
  AstTop createAstTop();

  /**
   * Returns a new object of class '<em>Ast Package</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Package</em>'.
   * @generated
   */
  AstPackage createAstPackage();

  /**
   * Returns a new object of class '<em>Ast Unit</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Unit</em>'.
   * @generated
   */
  AstUnit createAstUnit();

  /**
   * Returns a new object of class '<em>Ast Namespace</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Namespace</em>'.
   * @generated
   */
  AstNamespace createAstNamespace();

  /**
   * Returns a new object of class '<em>Ast Entity</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Entity</em>'.
   * @generated
   */
  AstEntity createAstEntity();

  /**
   * Returns a new object of class '<em>Ast Abstract Actor</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Abstract Actor</em>'.
   * @generated
   */
  AstAbstractActor createAstAbstractActor();

  /**
   * Returns a new object of class '<em>Import</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Import</em>'.
   * @generated
   */
  Import createImport();

  /**
   * Returns a new object of class '<em>Ast Network</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Network</em>'.
   * @generated
   */
  AstNetwork createAstNetwork();

  /**
   * Returns a new object of class '<em>Ast Actor Variable</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Actor Variable</em>'.
   * @generated
   */
  AstActorVariable createAstActorVariable();

  /**
   * Returns a new object of class '<em>Ast Assign Parameter</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Assign Parameter</em>'.
   * @generated
   */
  AstAssignParameter createAstAssignParameter();

  /**
   * Returns a new object of class '<em>Ast Structure</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Structure</em>'.
   * @generated
   */
  AstStructure createAstStructure();

  /**
   * Returns a new object of class '<em>Ast Connection</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Connection</em>'.
   * @generated
   */
  AstConnection createAstConnection();

  /**
   * Returns a new object of class '<em>Ast Actor Variable Reference</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Actor Variable Reference</em>'.
   * @generated
   */
  AstActorVariableReference createAstActorVariableReference();

  /**
   * Returns a new object of class '<em>Ast Connection Attribute</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Connection Attribute</em>'.
   * @generated
   */
  AstConnectionAttribute createAstConnectionAttribute();

  /**
   * Returns a new object of class '<em>Ast Variable</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Variable</em>'.
   * @generated
   */
  AstVariable createAstVariable();

  /**
   * Returns a new object of class '<em>Ast Type Name</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Type Name</em>'.
   * @generated
   */
  AstTypeName createAstTypeName();

  /**
   * Returns a new object of class '<em>Ast Type Definition Parameter</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Type Definition Parameter</em>'.
   * @generated
   */
  AstTypeDefinitionParameter createAstTypeDefinitionParameter();

  /**
   * Returns a new object of class '<em>Ast Constructor</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Constructor</em>'.
   * @generated
   */
  AstConstructor createAstConstructor();

  /**
   * Returns a new object of class '<em>Ast Actor</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Actor</em>'.
   * @generated
   */
  AstActor createAstActor();

  /**
   * Returns a new object of class '<em>Ast Port</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Port</em>'.
   * @generated
   */
  AstPort createAstPort();

  /**
   * Returns a new object of class '<em>Ast External Function</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast External Function</em>'.
   * @generated
   */
  AstExternalFunction createAstExternalFunction();

  /**
   * Returns a new object of class '<em>Ast Procedure</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Procedure</em>'.
   * @generated
   */
  AstProcedure createAstProcedure();

  /**
   * Returns a new object of class '<em>Ast External Procedure</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast External Procedure</em>'.
   * @generated
   */
  AstExternalProcedure createAstExternalProcedure();

  /**
   * Returns a new object of class '<em>Ast Tag</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Tag</em>'.
   * @generated
   */
  AstTag createAstTag();

  /**
   * Returns a new object of class '<em>Ast External Actor</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast External Actor</em>'.
   * @generated
   */
  AstExternalActor createAstExternalActor();

  /**
   * Returns a new object of class '<em>Ast Inequality</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Inequality</em>'.
   * @generated
   */
  AstInequality createAstInequality();

  /**
   * Returns a new object of class '<em>Ast Priority</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Priority</em>'.
   * @generated
   */
  AstPriority createAstPriority();

  /**
   * Returns a new object of class '<em>Ast Schedule</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Schedule</em>'.
   * @generated
   */
  AstSchedule createAstSchedule();

  /**
   * Returns a new object of class '<em>Ast Transition</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Transition</em>'.
   * @generated
   */
  AstTransition createAstTransition();

  /**
   * Returns a new object of class '<em>Ast State</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast State</em>'.
   * @generated
   */
  AstState createAstState();

  /**
   * Returns a new object of class '<em>Ast Action</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Action</em>'.
   * @generated
   */
  AstAction createAstAction();

  /**
   * Returns a new object of class '<em>Ast Input Pattern</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Input Pattern</em>'.
   * @generated
   */
  AstInputPattern createAstInputPattern();

  /**
   * Returns a new object of class '<em>Ast Output Pattern</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Output Pattern</em>'.
   * @generated
   */
  AstOutputPattern createAstOutputPattern();

  /**
   * Returns a new object of class '<em>Ast Statement Assign</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Statement Assign</em>'.
   * @generated
   */
  AstStatementAssign createAstStatementAssign();

  /**
   * Returns a new object of class '<em>Ast Statement Call</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Statement Call</em>'.
   * @generated
   */
  AstStatementCall createAstStatementCall();

  /**
   * Returns a new object of class '<em>Ast Statement Foreach</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Statement Foreach</em>'.
   * @generated
   */
  AstStatementForeach createAstStatementForeach();

  /**
   * Returns a new object of class '<em>Ast Foreach Generator</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Foreach Generator</em>'.
   * @generated
   */
  AstForeachGenerator createAstForeachGenerator();

  /**
   * Returns a new object of class '<em>Ast Statement Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Statement Block</em>'.
   * @generated
   */
  AstStatementBlock createAstStatementBlock();

  /**
   * Returns a new object of class '<em>Ast Statement If</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Statement If</em>'.
   * @generated
   */
  AstStatementIf createAstStatementIf();

  /**
   * Returns a new object of class '<em>Ast Statement While</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Statement While</em>'.
   * @generated
   */
  AstStatementWhile createAstStatementWhile();

  /**
   * Returns a new object of class '<em>Ast Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Statement</em>'.
   * @generated
   */
  AstStatement createAstStatement();

  /**
   * Returns a new object of class '<em>Ast Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Expression</em>'.
   * @generated
   */
  AstExpression createAstExpression();

  /**
   * Returns a new object of class '<em>Ast Expression Symbol Reference</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Expression Symbol Reference</em>'.
   * @generated
   */
  AstExpressionSymbolReference createAstExpressionSymbolReference();

  /**
   * Returns a new object of class '<em>Ast Expression If</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Expression If</em>'.
   * @generated
   */
  AstExpressionIf createAstExpressionIf();

  /**
   * Returns a new object of class '<em>Ast Expression List</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Expression List</em>'.
   * @generated
   */
  AstExpressionList createAstExpressionList();

  /**
   * Returns a new object of class '<em>Ast Generator</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Generator</em>'.
   * @generated
   */
  AstGenerator createAstGenerator();

  /**
   * Returns a new object of class '<em>Ast Expression Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Expression Literal</em>'.
   * @generated
   */
  AstExpressionLiteral createAstExpressionLiteral();

  /**
   * Returns a new object of class '<em>Ast Expression Boolean</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Expression Boolean</em>'.
   * @generated
   */
  AstExpressionBoolean createAstExpressionBoolean();

  /**
   * Returns a new object of class '<em>Ast Expression Float</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Expression Float</em>'.
   * @generated
   */
  AstExpressionFloat createAstExpressionFloat();

  /**
   * Returns a new object of class '<em>Ast Expression Integer</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Expression Integer</em>'.
   * @generated
   */
  AstExpressionInteger createAstExpressionInteger();

  /**
   * Returns a new object of class '<em>Ast Expression String</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Expression String</em>'.
   * @generated
   */
  AstExpressionString createAstExpressionString();

  /**
   * Returns a new object of class '<em>Ast Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Type</em>'.
   * @generated
   */
  AstType createAstType();

  /**
   * Returns a new object of class '<em>Ast Type Parameter List</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Type Parameter List</em>'.
   * @generated
   */
  AstTypeParameterList createAstTypeParameterList();

  /**
   * Returns a new object of class '<em>Ast Type Param</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Type Param</em>'.
   * @generated
   */
  AstTypeParam createAstTypeParam();

  /**
   * Returns a new object of class '<em>Ast Member Access</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Member Access</em>'.
   * @generated
   */
  AstMemberAccess createAstMemberAccess();

  /**
   * Returns a new object of class '<em>Ast Annotation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Annotation</em>'.
   * @generated
   */
  AstAnnotation createAstAnnotation();

  /**
   * Returns a new object of class '<em>Ast Annotation Argument</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Annotation Argument</em>'.
   * @generated
   */
  AstAnnotationArgument createAstAnnotationArgument();

  /**
   * Returns a new object of class '<em>Ast Anonymous Constructor</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Anonymous Constructor</em>'.
   * @generated
   */
  AstAnonymousConstructor createAstAnonymousConstructor();

  /**
   * Returns a new object of class '<em>Ast Function</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Function</em>'.
   * @generated
   */
  AstFunction createAstFunction();

  /**
   * Returns a new object of class '<em>Ast Initialize</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Initialize</em>'.
   * @generated
   */
  AstInitialize createAstInitialize();

  /**
   * Returns a new object of class '<em>Ast Expression Binary</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Expression Binary</em>'.
   * @generated
   */
  AstExpressionBinary createAstExpressionBinary();

  /**
   * Returns a new object of class '<em>Ast Expression Unary</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ast Expression Unary</em>'.
   * @generated
   */
  AstExpressionUnary createAstExpressionUnary();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  CalPackage getCalPackage();

} //CalFactory
