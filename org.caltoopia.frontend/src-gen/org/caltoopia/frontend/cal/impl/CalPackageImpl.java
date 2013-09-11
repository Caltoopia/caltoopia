/**
 */
package org.caltoopia.frontend.cal.impl;

import org.caltoopia.frontend.cal.AstAbstractActor;
import org.caltoopia.frontend.cal.AstAction;
import org.caltoopia.frontend.cal.AstActor;
import org.caltoopia.frontend.cal.AstActorVariable;
import org.caltoopia.frontend.cal.AstActorVariableReference;
import org.caltoopia.frontend.cal.AstAnnotation;
import org.caltoopia.frontend.cal.AstAnnotationArgument;
import org.caltoopia.frontend.cal.AstAssignParameter;
import org.caltoopia.frontend.cal.AstConnection;
import org.caltoopia.frontend.cal.AstConnectionAttribute;
import org.caltoopia.frontend.cal.AstEntity;
import org.caltoopia.frontend.cal.AstExpression;
import org.caltoopia.frontend.cal.AstExpressionAlternative;
import org.caltoopia.frontend.cal.AstExpressionBinary;
import org.caltoopia.frontend.cal.AstExpressionBoolean;
import org.caltoopia.frontend.cal.AstExpressionCase;
import org.caltoopia.frontend.cal.AstExpressionFloat;
import org.caltoopia.frontend.cal.AstExpressionIf;
import org.caltoopia.frontend.cal.AstExpressionInteger;
import org.caltoopia.frontend.cal.AstExpressionList;
import org.caltoopia.frontend.cal.AstExpressionLiteral;
import org.caltoopia.frontend.cal.AstExpressionString;
import org.caltoopia.frontend.cal.AstExpressionSymbolReference;
import org.caltoopia.frontend.cal.AstExpressionUnary;
import org.caltoopia.frontend.cal.AstExternalActor;
import org.caltoopia.frontend.cal.AstExternalFunction;
import org.caltoopia.frontend.cal.AstExternalProcedure;
import org.caltoopia.frontend.cal.AstForeachGenerator;
import org.caltoopia.frontend.cal.AstFunction;
import org.caltoopia.frontend.cal.AstGenerator;
import org.caltoopia.frontend.cal.AstInequality;
import org.caltoopia.frontend.cal.AstInitialize;
import org.caltoopia.frontend.cal.AstInputPattern;
import org.caltoopia.frontend.cal.AstMemberAccess;
import org.caltoopia.frontend.cal.AstNamespace;
import org.caltoopia.frontend.cal.AstNetwork;
import org.caltoopia.frontend.cal.AstOutputPattern;
import org.caltoopia.frontend.cal.AstPackage;
import org.caltoopia.frontend.cal.AstPattern;
import org.caltoopia.frontend.cal.AstPatternExpressionBinary;
import org.caltoopia.frontend.cal.AstPatternExpressionIf;
import org.caltoopia.frontend.cal.AstPatternExpressionLiteral;
import org.caltoopia.frontend.cal.AstPatternExpressionSymbolReference;
import org.caltoopia.frontend.cal.AstPatternExpressionUnary;
import org.caltoopia.frontend.cal.AstPort;
import org.caltoopia.frontend.cal.AstPriority;
import org.caltoopia.frontend.cal.AstProcedure;
import org.caltoopia.frontend.cal.AstSchedule;
import org.caltoopia.frontend.cal.AstState;
import org.caltoopia.frontend.cal.AstStatement;
import org.caltoopia.frontend.cal.AstStatementAlternative;
import org.caltoopia.frontend.cal.AstStatementAssign;
import org.caltoopia.frontend.cal.AstStatementBlock;
import org.caltoopia.frontend.cal.AstStatementCall;
import org.caltoopia.frontend.cal.AstStatementCase;
import org.caltoopia.frontend.cal.AstStatementForeach;
import org.caltoopia.frontend.cal.AstStatementIf;
import org.caltoopia.frontend.cal.AstStatementWhile;
import org.caltoopia.frontend.cal.AstStructure;
import org.caltoopia.frontend.cal.AstSubPattern;
import org.caltoopia.frontend.cal.AstTag;
import org.caltoopia.frontend.cal.AstTaggedTuple;
import org.caltoopia.frontend.cal.AstTop;
import org.caltoopia.frontend.cal.AstTransition;
import org.caltoopia.frontend.cal.AstTuple;
import org.caltoopia.frontend.cal.AstType;
import org.caltoopia.frontend.cal.AstTypeDefinitionParameter;
import org.caltoopia.frontend.cal.AstTypeParam;
import org.caltoopia.frontend.cal.AstTypeParameterList;
import org.caltoopia.frontend.cal.AstTypeUser;
import org.caltoopia.frontend.cal.AstUnit;
import org.caltoopia.frontend.cal.AstVariable;
import org.caltoopia.frontend.cal.CalFactory;
import org.caltoopia.frontend.cal.CalPackage;
import org.caltoopia.frontend.cal.Import;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CalPackageImpl extends EPackageImpl implements CalPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astTopEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astPackageEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astUnitEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astNamespaceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astEntityEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astAbstractActorEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass importEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astNetworkEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astActorVariableEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astAssignParameterEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astStructureEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astConnectionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astActorVariableReferenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astConnectionAttributeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astVariableEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astTypeUserEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astTypeDefinitionParameterEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astTaggedTupleEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astActorEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astPortEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astExternalFunctionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astProcedureEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astExternalProcedureEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astTagEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astExternalActorEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astInequalityEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astPriorityEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astScheduleEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astTransitionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astStateEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astActionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astInputPatternEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astOutputPatternEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astStatementAssignEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astStatementCallEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astStatementForeachEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astForeachGeneratorEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astStatementBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astStatementIfEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astStatementWhileEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astStatementCaseEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astStatementAlternativeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astExpressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astExpressionSymbolReferenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astExpressionIfEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astExpressionListEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astGeneratorEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astExpressionCaseEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astExpressionAlternativeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astExpressionLiteralEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astExpressionBooleanEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astExpressionFloatEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astExpressionIntegerEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astExpressionStringEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astPatternEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astSubPatternEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astPatternExpressionSymbolReferenceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astPatternExpressionIfEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astPatternExpressionLiteralEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astTypeParameterListEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astTypeParamEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astMemberAccessEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astAnnotationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astAnnotationArgumentEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astTupleEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astFunctionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astInitializeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astExpressionBinaryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astExpressionUnaryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astPatternExpressionBinaryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass astPatternExpressionUnaryEClass = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.caltoopia.frontend.cal.CalPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private CalPackageImpl()
  {
    super(eNS_URI, CalFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link CalPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static CalPackage init()
  {
    if (isInited) return (CalPackage)EPackage.Registry.INSTANCE.getEPackage(CalPackage.eNS_URI);

    // Obtain or create and register package
    CalPackageImpl theCalPackage = (CalPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof CalPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new CalPackageImpl());

    isInited = true;

    // Create package meta-data objects
    theCalPackage.createPackageContents();

    // Initialize created meta-data
    theCalPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theCalPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(CalPackage.eNS_URI, theCalPackage);
    return theCalPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstTop()
  {
    return astTopEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstPackage()
  {
    return astPackageEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstUnit()
  {
    return astUnitEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstNamespace()
  {
    return astNamespaceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstNamespace_Name()
  {
    return (EAttribute)astNamespaceEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstNamespace_Entities()
  {
    return (EReference)astNamespaceEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstNamespace_Imports()
  {
    return (EReference)astNamespaceEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstNamespace_Units()
  {
    return (EReference)astNamespaceEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstNamespace_Functions()
  {
    return (EReference)astNamespaceEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstNamespace_Variables()
  {
    return (EReference)astNamespaceEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstNamespace_Externals()
  {
    return (EReference)astNamespaceEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstNamespace_Annotations()
  {
    return (EReference)astNamespaceEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstNamespace_Typedefs()
  {
    return (EReference)astNamespaceEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstNamespace_Namespaces()
  {
    return (EReference)astNamespaceEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstEntity()
  {
    return astEntityEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstEntity_Annotations()
  {
    return (EReference)astEntityEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstEntity_Actor()
  {
    return (EReference)astEntityEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstAbstractActor()
  {
    return astAbstractActorEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstAbstractActor_Name()
  {
    return (EAttribute)astAbstractActorEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstAbstractActor_Parameters()
  {
    return (EReference)astAbstractActorEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstAbstractActor_Inputs()
  {
    return (EReference)astAbstractActorEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstAbstractActor_Outputs()
  {
    return (EReference)astAbstractActorEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getImport()
  {
    return importEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getImport_ImportedNamespace()
  {
    return (EAttribute)importEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstNetwork()
  {
    return astNetworkEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstNetwork_Variables()
  {
    return (EReference)astNetworkEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstNetwork_Instances()
  {
    return (EReference)astNetworkEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstNetwork_Structure()
  {
    return (EReference)astNetworkEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstActorVariable()
  {
    return astActorVariableEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstActorVariable_Name()
  {
    return (EAttribute)astActorVariableEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstActorVariable_Type()
  {
    return (EReference)astActorVariableEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstActorVariable_Parameters()
  {
    return (EReference)astActorVariableEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstAssignParameter()
  {
    return astAssignParameterEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstAssignParameter_Name()
  {
    return (EAttribute)astAssignParameterEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstAssignParameter_Value()
  {
    return (EReference)astAssignParameterEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstStructure()
  {
    return astStructureEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstStructure_Connections()
  {
    return (EReference)astStructureEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstConnection()
  {
    return astConnectionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstConnection_From()
  {
    return (EReference)astConnectionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstConnection_OutPort()
  {
    return (EAttribute)astConnectionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstConnection_To()
  {
    return (EReference)astConnectionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstConnection_InPort()
  {
    return (EAttribute)astConnectionEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstConnection_Attribute()
  {
    return (EReference)astConnectionEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstActorVariableReference()
  {
    return astActorVariableReferenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstActorVariableReference_Variable()
  {
    return (EReference)astActorVariableReferenceEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstConnectionAttribute()
  {
    return astConnectionAttributeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstConnectionAttribute_Name()
  {
    return (EAttribute)astConnectionAttributeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstConnectionAttribute_Value()
  {
    return (EReference)astConnectionAttributeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstVariable()
  {
    return astVariableEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstVariable_Constant()
  {
    return (EAttribute)astVariableEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstVariable_Value()
  {
    return (EReference)astVariableEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstVariable_Name()
  {
    return (EAttribute)astVariableEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstVariable_Annotations()
  {
    return (EReference)astVariableEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstVariable_Type()
  {
    return (EReference)astVariableEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstVariable_Dimensions()
  {
    return (EReference)astVariableEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstTypeUser()
  {
    return astTypeUserEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstTypeUser_Definition()
  {
    return (EAttribute)astTypeUserEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstTypeUser_Parameters()
  {
    return (EReference)astTypeUserEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstTypeUser_Tuples()
  {
    return (EReference)astTypeUserEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstTypeUser_Variable()
  {
    return (EAttribute)astTypeUserEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstTypeDefinitionParameter()
  {
    return astTypeDefinitionParameterEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstTypeDefinitionParameter_Value()
  {
    return (EReference)astTypeDefinitionParameterEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstTypeDefinitionParameter_Type()
  {
    return (EReference)astTypeDefinitionParameterEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstTaggedTuple()
  {
    return astTaggedTupleEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstTaggedTuple_Name()
  {
    return (EAttribute)astTaggedTupleEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstTaggedTuple_Fields()
  {
    return (EReference)astTaggedTupleEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstActor()
  {
    return astActorEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstActor_Functions()
  {
    return (EReference)astActorEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstActor_Procedures()
  {
    return (EReference)astActorEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstActor_Actions()
  {
    return (EReference)astActorEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstActor_Initializes()
  {
    return (EReference)astActorEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstActor_StateVariables()
  {
    return (EReference)astActorEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstActor_Schedules()
  {
    return (EReference)astActorEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstActor_Priorities()
  {
    return (EReference)astActorEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstPort()
  {
    return astPortEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstPort_Annotations()
  {
    return (EReference)astPortEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstPort_Type()
  {
    return (EReference)astPortEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstPort_Name()
  {
    return (EAttribute)astPortEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstExternalFunction()
  {
    return astExternalFunctionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstProcedure()
  {
    return astProcedureEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstProcedure_Annotations()
  {
    return (EReference)astProcedureEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstProcedure_Name()
  {
    return (EAttribute)astProcedureEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstProcedure_Parameters()
  {
    return (EReference)astProcedureEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstProcedure_Variables()
  {
    return (EReference)astProcedureEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstProcedure_Statements()
  {
    return (EReference)astProcedureEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstExternalProcedure()
  {
    return astExternalProcedureEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstTag()
  {
    return astTagEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstTag_Identifiers()
  {
    return (EAttribute)astTagEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstExternalActor()
  {
    return astExternalActorEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstInequality()
  {
    return astInequalityEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstInequality_Tags()
  {
    return (EReference)astInequalityEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstPriority()
  {
    return astPriorityEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstPriority_Inequalities()
  {
    return (EReference)astPriorityEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstSchedule()
  {
    return astScheduleEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstSchedule_InitialState()
  {
    return (EReference)astScheduleEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstSchedule_Transitions()
  {
    return (EReference)astScheduleEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstTransition()
  {
    return astTransitionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstTransition_Source()
  {
    return (EReference)astTransitionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstTransition_Tags()
  {
    return (EReference)astTransitionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstTransition_Target()
  {
    return (EReference)astTransitionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstState()
  {
    return astStateEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstState_Name()
  {
    return (EAttribute)astStateEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstAction()
  {
    return astActionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstAction_Annotations()
  {
    return (EReference)astActionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstAction_Tag()
  {
    return (EReference)astActionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstAction_Inputs()
  {
    return (EReference)astActionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstAction_Outputs()
  {
    return (EReference)astActionEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstAction_Guards()
  {
    return (EReference)astActionEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstAction_Variables()
  {
    return (EReference)astActionEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstAction_Statements()
  {
    return (EReference)astActionEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstInputPattern()
  {
    return astInputPatternEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstInputPattern_Port()
  {
    return (EReference)astInputPatternEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstInputPattern_Tokens()
  {
    return (EReference)astInputPatternEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstInputPattern_Repeat()
  {
    return (EReference)astInputPatternEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstOutputPattern()
  {
    return astOutputPatternEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstOutputPattern_Port()
  {
    return (EReference)astOutputPatternEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstOutputPattern_Values()
  {
    return (EReference)astOutputPatternEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstOutputPattern_Repeat()
  {
    return (EReference)astOutputPatternEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstStatementAssign()
  {
    return astStatementAssignEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstStatementAssign_Target()
  {
    return (EReference)astStatementAssignEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstStatementAssign_Indexes()
  {
    return (EReference)astStatementAssignEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstStatementAssign_Member()
  {
    return (EReference)astStatementAssignEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstStatementAssign_Value()
  {
    return (EReference)astStatementAssignEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstStatementCall()
  {
    return astStatementCallEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstStatementCall_Procedure()
  {
    return (EReference)astStatementCallEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstStatementCall_Parameters()
  {
    return (EReference)astStatementCallEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstStatementForeach()
  {
    return astStatementForeachEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstStatementForeach_Generators()
  {
    return (EReference)astStatementForeachEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstStatementForeach_Variables()
  {
    return (EReference)astStatementForeachEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstStatementForeach_Statements()
  {
    return (EReference)astStatementForeachEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstForeachGenerator()
  {
    return astForeachGeneratorEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstForeachGenerator_Variable()
  {
    return (EReference)astForeachGeneratorEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstForeachGenerator_Expression()
  {
    return (EReference)astForeachGeneratorEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstStatementBlock()
  {
    return astStatementBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstStatementBlock_Variables()
  {
    return (EReference)astStatementBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstStatementBlock_Statements()
  {
    return (EReference)astStatementBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstStatementIf()
  {
    return astStatementIfEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstStatementIf_Condition()
  {
    return (EReference)astStatementIfEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstStatementIf_Then()
  {
    return (EReference)astStatementIfEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstStatementIf_Else()
  {
    return (EReference)astStatementIfEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstStatementWhile()
  {
    return astStatementWhileEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstStatementWhile_Condition()
  {
    return (EReference)astStatementWhileEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstStatementWhile_Statements()
  {
    return (EReference)astStatementWhileEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstStatementCase()
  {
    return astStatementCaseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstStatementCase_Expression()
  {
    return (EReference)astStatementCaseEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstStatementCase_Cases()
  {
    return (EReference)astStatementCaseEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstStatementAlternative()
  {
    return astStatementAlternativeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstStatementAlternative_Pattern()
  {
    return (EReference)astStatementAlternativeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstStatementAlternative_Guards()
  {
    return (EReference)astStatementAlternativeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstStatementAlternative_Statements()
  {
    return (EReference)astStatementAlternativeEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstStatement()
  {
    return astStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstExpression()
  {
    return astExpressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstExpressionSymbolReference()
  {
    return astExpressionSymbolReferenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstExpressionSymbolReference_Symbol()
  {
    return (EReference)astExpressionSymbolReferenceEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstExpressionSymbolReference_Indexes()
  {
    return (EReference)astExpressionSymbolReferenceEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstExpressionSymbolReference_Member()
  {
    return (EReference)astExpressionSymbolReferenceEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstExpressionSymbolReference_Ctor()
  {
    return (EAttribute)astExpressionSymbolReferenceEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstExpressionSymbolReference_Call()
  {
    return (EAttribute)astExpressionSymbolReferenceEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstExpressionSymbolReference_Parameters()
  {
    return (EReference)astExpressionSymbolReferenceEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstExpressionIf()
  {
    return astExpressionIfEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstExpressionIf_Condition()
  {
    return (EReference)astExpressionIfEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstExpressionIf_Then()
  {
    return (EReference)astExpressionIfEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstExpressionIf_Else()
  {
    return (EReference)astExpressionIfEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstExpressionList()
  {
    return astExpressionListEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstExpressionList_Expressions()
  {
    return (EReference)astExpressionListEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstExpressionList_Generators()
  {
    return (EReference)astExpressionListEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstGenerator()
  {
    return astGeneratorEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstGenerator_Variable()
  {
    return (EReference)astGeneratorEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstGenerator_Expression()
  {
    return (EReference)astGeneratorEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstExpressionCase()
  {
    return astExpressionCaseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstExpressionCase_Variable()
  {
    return (EReference)astExpressionCaseEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstExpressionCase_Cases()
  {
    return (EReference)astExpressionCaseEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstExpressionCase_Default()
  {
    return (EReference)astExpressionCaseEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstExpressionAlternative()
  {
    return astExpressionAlternativeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstExpressionAlternative_Pattern()
  {
    return (EReference)astExpressionAlternativeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstExpressionAlternative_Guards()
  {
    return (EReference)astExpressionAlternativeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstExpressionAlternative_Expression()
  {
    return (EReference)astExpressionAlternativeEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstExpressionLiteral()
  {
    return astExpressionLiteralEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstExpressionBoolean()
  {
    return astExpressionBooleanEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstExpressionBoolean_Value()
  {
    return (EAttribute)astExpressionBooleanEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstExpressionFloat()
  {
    return astExpressionFloatEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstExpressionFloat_Value()
  {
    return (EAttribute)astExpressionFloatEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstExpressionInteger()
  {
    return astExpressionIntegerEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstExpressionInteger_Value()
  {
    return (EAttribute)astExpressionIntegerEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstExpressionString()
  {
    return astExpressionStringEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstExpressionString_Value()
  {
    return (EAttribute)astExpressionStringEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstPattern()
  {
    return astPatternEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstPattern_Tag()
  {
    return (EAttribute)astPatternEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstPattern_Subpatterns()
  {
    return (EReference)astPatternEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstSubPattern()
  {
    return astSubPatternEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstSubPattern_Label()
  {
    return (EAttribute)astSubPatternEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstSubPattern_Dontcare()
  {
    return (EAttribute)astSubPatternEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstSubPattern_Condition()
  {
    return (EReference)astSubPatternEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstSubPattern_Variable()
  {
    return (EReference)astSubPatternEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstSubPattern_Pattern()
  {
    return (EReference)astSubPatternEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstPatternExpressionSymbolReference()
  {
    return astPatternExpressionSymbolReferenceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstPatternExpressionSymbolReference_Symbol()
  {
    return (EReference)astPatternExpressionSymbolReferenceEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstPatternExpressionSymbolReference_Indexes()
  {
    return (EReference)astPatternExpressionSymbolReferenceEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstPatternExpressionSymbolReference_Member()
  {
    return (EReference)astPatternExpressionSymbolReferenceEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstPatternExpressionSymbolReference_Ctor()
  {
    return (EAttribute)astPatternExpressionSymbolReferenceEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstPatternExpressionSymbolReference_Call()
  {
    return (EAttribute)astPatternExpressionSymbolReferenceEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstPatternExpressionSymbolReference_Parameters()
  {
    return (EReference)astPatternExpressionSymbolReferenceEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstPatternExpressionIf()
  {
    return astPatternExpressionIfEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstPatternExpressionIf_Condition()
  {
    return (EReference)astPatternExpressionIfEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstPatternExpressionIf_Then()
  {
    return (EReference)astPatternExpressionIfEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstPatternExpressionIf_Else()
  {
    return (EReference)astPatternExpressionIfEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstPatternExpressionLiteral()
  {
    return astPatternExpressionLiteralEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstType()
  {
    return astTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstType_Builtin()
  {
    return (EAttribute)astTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstType_TypeParams()
  {
    return (EReference)astTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstType_Dimensions()
  {
    return (EReference)astTypeEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstType_Name()
  {
    return (EReference)astTypeEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstType_Domain()
  {
    return (EReference)astTypeEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstType_Codomain()
  {
    return (EReference)astTypeEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstTypeParameterList()
  {
    return astTypeParameterListEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstTypeParameterList_Params()
  {
    return (EReference)astTypeParameterListEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstTypeParam()
  {
    return astTypeParamEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstTypeParam_Name()
  {
    return (EAttribute)astTypeParamEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstTypeParam_Value()
  {
    return (EReference)astTypeParamEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstTypeParam_Type()
  {
    return (EReference)astTypeParamEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstMemberAccess()
  {
    return astMemberAccessEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstMemberAccess_Name()
  {
    return (EAttribute)astMemberAccessEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstMemberAccess_MemberIndex()
  {
    return (EReference)astMemberAccessEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstAnnotation()
  {
    return astAnnotationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstAnnotation_Name()
  {
    return (EAttribute)astAnnotationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstAnnotation_Arguments()
  {
    return (EReference)astAnnotationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstAnnotationArgument()
  {
    return astAnnotationArgumentEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstAnnotationArgument_Name()
  {
    return (EAttribute)astAnnotationArgumentEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstAnnotationArgument_Value()
  {
    return (EAttribute)astAnnotationArgumentEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstTuple()
  {
    return astTupleEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstFunction()
  {
    return astFunctionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstFunction_Parameters()
  {
    return (EReference)astFunctionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstFunction_Variables()
  {
    return (EReference)astFunctionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstFunction_Expression()
  {
    return (EReference)astFunctionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstInitialize()
  {
    return astInitializeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstExpressionBinary()
  {
    return astExpressionBinaryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstExpressionBinary_Left()
  {
    return (EReference)astExpressionBinaryEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstExpressionBinary_Operator()
  {
    return (EAttribute)astExpressionBinaryEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstExpressionBinary_Right()
  {
    return (EReference)astExpressionBinaryEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstExpressionUnary()
  {
    return astExpressionUnaryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstExpressionUnary_UnaryOperator()
  {
    return (EAttribute)astExpressionUnaryEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstExpressionUnary_Expression()
  {
    return (EReference)astExpressionUnaryEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstPatternExpressionBinary()
  {
    return astPatternExpressionBinaryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstPatternExpressionBinary_Left()
  {
    return (EReference)astPatternExpressionBinaryEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstPatternExpressionBinary_Operator()
  {
    return (EAttribute)astPatternExpressionBinaryEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstPatternExpressionBinary_Right()
  {
    return (EReference)astPatternExpressionBinaryEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAstPatternExpressionUnary()
  {
    return astPatternExpressionUnaryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAstPatternExpressionUnary_UnaryOperator()
  {
    return (EAttribute)astPatternExpressionUnaryEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAstPatternExpressionUnary_Expression()
  {
    return (EReference)astPatternExpressionUnaryEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CalFactory getCalFactory()
  {
    return (CalFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    astTopEClass = createEClass(AST_TOP);

    astPackageEClass = createEClass(AST_PACKAGE);

    astUnitEClass = createEClass(AST_UNIT);

    astNamespaceEClass = createEClass(AST_NAMESPACE);
    createEAttribute(astNamespaceEClass, AST_NAMESPACE__NAME);
    createEReference(astNamespaceEClass, AST_NAMESPACE__ENTITIES);
    createEReference(astNamespaceEClass, AST_NAMESPACE__IMPORTS);
    createEReference(astNamespaceEClass, AST_NAMESPACE__UNITS);
    createEReference(astNamespaceEClass, AST_NAMESPACE__FUNCTIONS);
    createEReference(astNamespaceEClass, AST_NAMESPACE__VARIABLES);
    createEReference(astNamespaceEClass, AST_NAMESPACE__EXTERNALS);
    createEReference(astNamespaceEClass, AST_NAMESPACE__ANNOTATIONS);
    createEReference(astNamespaceEClass, AST_NAMESPACE__TYPEDEFS);
    createEReference(astNamespaceEClass, AST_NAMESPACE__NAMESPACES);

    astEntityEClass = createEClass(AST_ENTITY);
    createEReference(astEntityEClass, AST_ENTITY__ANNOTATIONS);
    createEReference(astEntityEClass, AST_ENTITY__ACTOR);

    astAbstractActorEClass = createEClass(AST_ABSTRACT_ACTOR);
    createEAttribute(astAbstractActorEClass, AST_ABSTRACT_ACTOR__NAME);
    createEReference(astAbstractActorEClass, AST_ABSTRACT_ACTOR__PARAMETERS);
    createEReference(astAbstractActorEClass, AST_ABSTRACT_ACTOR__INPUTS);
    createEReference(astAbstractActorEClass, AST_ABSTRACT_ACTOR__OUTPUTS);

    importEClass = createEClass(IMPORT);
    createEAttribute(importEClass, IMPORT__IMPORTED_NAMESPACE);

    astNetworkEClass = createEClass(AST_NETWORK);
    createEReference(astNetworkEClass, AST_NETWORK__VARIABLES);
    createEReference(astNetworkEClass, AST_NETWORK__INSTANCES);
    createEReference(astNetworkEClass, AST_NETWORK__STRUCTURE);

    astActorVariableEClass = createEClass(AST_ACTOR_VARIABLE);
    createEAttribute(astActorVariableEClass, AST_ACTOR_VARIABLE__NAME);
    createEReference(astActorVariableEClass, AST_ACTOR_VARIABLE__TYPE);
    createEReference(astActorVariableEClass, AST_ACTOR_VARIABLE__PARAMETERS);

    astAssignParameterEClass = createEClass(AST_ASSIGN_PARAMETER);
    createEAttribute(astAssignParameterEClass, AST_ASSIGN_PARAMETER__NAME);
    createEReference(astAssignParameterEClass, AST_ASSIGN_PARAMETER__VALUE);

    astStructureEClass = createEClass(AST_STRUCTURE);
    createEReference(astStructureEClass, AST_STRUCTURE__CONNECTIONS);

    astConnectionEClass = createEClass(AST_CONNECTION);
    createEReference(astConnectionEClass, AST_CONNECTION__FROM);
    createEAttribute(astConnectionEClass, AST_CONNECTION__OUT_PORT);
    createEReference(astConnectionEClass, AST_CONNECTION__TO);
    createEAttribute(astConnectionEClass, AST_CONNECTION__IN_PORT);
    createEReference(astConnectionEClass, AST_CONNECTION__ATTRIBUTE);

    astActorVariableReferenceEClass = createEClass(AST_ACTOR_VARIABLE_REFERENCE);
    createEReference(astActorVariableReferenceEClass, AST_ACTOR_VARIABLE_REFERENCE__VARIABLE);

    astConnectionAttributeEClass = createEClass(AST_CONNECTION_ATTRIBUTE);
    createEAttribute(astConnectionAttributeEClass, AST_CONNECTION_ATTRIBUTE__NAME);
    createEReference(astConnectionAttributeEClass, AST_CONNECTION_ATTRIBUTE__VALUE);

    astVariableEClass = createEClass(AST_VARIABLE);
    createEAttribute(astVariableEClass, AST_VARIABLE__CONSTANT);
    createEReference(astVariableEClass, AST_VARIABLE__VALUE);
    createEAttribute(astVariableEClass, AST_VARIABLE__NAME);
    createEReference(astVariableEClass, AST_VARIABLE__ANNOTATIONS);
    createEReference(astVariableEClass, AST_VARIABLE__TYPE);
    createEReference(astVariableEClass, AST_VARIABLE__DIMENSIONS);

    astTypeUserEClass = createEClass(AST_TYPE_USER);
    createEAttribute(astTypeUserEClass, AST_TYPE_USER__DEFINITION);
    createEReference(astTypeUserEClass, AST_TYPE_USER__PARAMETERS);
    createEReference(astTypeUserEClass, AST_TYPE_USER__TUPLES);
    createEAttribute(astTypeUserEClass, AST_TYPE_USER__VARIABLE);

    astTypeDefinitionParameterEClass = createEClass(AST_TYPE_DEFINITION_PARAMETER);
    createEReference(astTypeDefinitionParameterEClass, AST_TYPE_DEFINITION_PARAMETER__VALUE);
    createEReference(astTypeDefinitionParameterEClass, AST_TYPE_DEFINITION_PARAMETER__TYPE);

    astTaggedTupleEClass = createEClass(AST_TAGGED_TUPLE);
    createEAttribute(astTaggedTupleEClass, AST_TAGGED_TUPLE__NAME);
    createEReference(astTaggedTupleEClass, AST_TAGGED_TUPLE__FIELDS);

    astActorEClass = createEClass(AST_ACTOR);
    createEReference(astActorEClass, AST_ACTOR__FUNCTIONS);
    createEReference(astActorEClass, AST_ACTOR__PROCEDURES);
    createEReference(astActorEClass, AST_ACTOR__ACTIONS);
    createEReference(astActorEClass, AST_ACTOR__INITIALIZES);
    createEReference(astActorEClass, AST_ACTOR__STATE_VARIABLES);
    createEReference(astActorEClass, AST_ACTOR__SCHEDULES);
    createEReference(astActorEClass, AST_ACTOR__PRIORITIES);

    astPortEClass = createEClass(AST_PORT);
    createEReference(astPortEClass, AST_PORT__ANNOTATIONS);
    createEReference(astPortEClass, AST_PORT__TYPE);
    createEAttribute(astPortEClass, AST_PORT__NAME);

    astExternalFunctionEClass = createEClass(AST_EXTERNAL_FUNCTION);

    astProcedureEClass = createEClass(AST_PROCEDURE);
    createEReference(astProcedureEClass, AST_PROCEDURE__ANNOTATIONS);
    createEAttribute(astProcedureEClass, AST_PROCEDURE__NAME);
    createEReference(astProcedureEClass, AST_PROCEDURE__PARAMETERS);
    createEReference(astProcedureEClass, AST_PROCEDURE__VARIABLES);
    createEReference(astProcedureEClass, AST_PROCEDURE__STATEMENTS);

    astExternalProcedureEClass = createEClass(AST_EXTERNAL_PROCEDURE);

    astTagEClass = createEClass(AST_TAG);
    createEAttribute(astTagEClass, AST_TAG__IDENTIFIERS);

    astExternalActorEClass = createEClass(AST_EXTERNAL_ACTOR);

    astInequalityEClass = createEClass(AST_INEQUALITY);
    createEReference(astInequalityEClass, AST_INEQUALITY__TAGS);

    astPriorityEClass = createEClass(AST_PRIORITY);
    createEReference(astPriorityEClass, AST_PRIORITY__INEQUALITIES);

    astScheduleEClass = createEClass(AST_SCHEDULE);
    createEReference(astScheduleEClass, AST_SCHEDULE__INITIAL_STATE);
    createEReference(astScheduleEClass, AST_SCHEDULE__TRANSITIONS);

    astTransitionEClass = createEClass(AST_TRANSITION);
    createEReference(astTransitionEClass, AST_TRANSITION__SOURCE);
    createEReference(astTransitionEClass, AST_TRANSITION__TAGS);
    createEReference(astTransitionEClass, AST_TRANSITION__TARGET);

    astStateEClass = createEClass(AST_STATE);
    createEAttribute(astStateEClass, AST_STATE__NAME);

    astActionEClass = createEClass(AST_ACTION);
    createEReference(astActionEClass, AST_ACTION__ANNOTATIONS);
    createEReference(astActionEClass, AST_ACTION__TAG);
    createEReference(astActionEClass, AST_ACTION__INPUTS);
    createEReference(astActionEClass, AST_ACTION__OUTPUTS);
    createEReference(astActionEClass, AST_ACTION__GUARDS);
    createEReference(astActionEClass, AST_ACTION__VARIABLES);
    createEReference(astActionEClass, AST_ACTION__STATEMENTS);

    astInputPatternEClass = createEClass(AST_INPUT_PATTERN);
    createEReference(astInputPatternEClass, AST_INPUT_PATTERN__PORT);
    createEReference(astInputPatternEClass, AST_INPUT_PATTERN__TOKENS);
    createEReference(astInputPatternEClass, AST_INPUT_PATTERN__REPEAT);

    astOutputPatternEClass = createEClass(AST_OUTPUT_PATTERN);
    createEReference(astOutputPatternEClass, AST_OUTPUT_PATTERN__PORT);
    createEReference(astOutputPatternEClass, AST_OUTPUT_PATTERN__VALUES);
    createEReference(astOutputPatternEClass, AST_OUTPUT_PATTERN__REPEAT);

    astStatementAssignEClass = createEClass(AST_STATEMENT_ASSIGN);
    createEReference(astStatementAssignEClass, AST_STATEMENT_ASSIGN__TARGET);
    createEReference(astStatementAssignEClass, AST_STATEMENT_ASSIGN__INDEXES);
    createEReference(astStatementAssignEClass, AST_STATEMENT_ASSIGN__MEMBER);
    createEReference(astStatementAssignEClass, AST_STATEMENT_ASSIGN__VALUE);

    astStatementCallEClass = createEClass(AST_STATEMENT_CALL);
    createEReference(astStatementCallEClass, AST_STATEMENT_CALL__PROCEDURE);
    createEReference(astStatementCallEClass, AST_STATEMENT_CALL__PARAMETERS);

    astStatementForeachEClass = createEClass(AST_STATEMENT_FOREACH);
    createEReference(astStatementForeachEClass, AST_STATEMENT_FOREACH__GENERATORS);
    createEReference(astStatementForeachEClass, AST_STATEMENT_FOREACH__VARIABLES);
    createEReference(astStatementForeachEClass, AST_STATEMENT_FOREACH__STATEMENTS);

    astForeachGeneratorEClass = createEClass(AST_FOREACH_GENERATOR);
    createEReference(astForeachGeneratorEClass, AST_FOREACH_GENERATOR__VARIABLE);
    createEReference(astForeachGeneratorEClass, AST_FOREACH_GENERATOR__EXPRESSION);

    astStatementBlockEClass = createEClass(AST_STATEMENT_BLOCK);
    createEReference(astStatementBlockEClass, AST_STATEMENT_BLOCK__VARIABLES);
    createEReference(astStatementBlockEClass, AST_STATEMENT_BLOCK__STATEMENTS);

    astStatementIfEClass = createEClass(AST_STATEMENT_IF);
    createEReference(astStatementIfEClass, AST_STATEMENT_IF__CONDITION);
    createEReference(astStatementIfEClass, AST_STATEMENT_IF__THEN);
    createEReference(astStatementIfEClass, AST_STATEMENT_IF__ELSE);

    astStatementWhileEClass = createEClass(AST_STATEMENT_WHILE);
    createEReference(astStatementWhileEClass, AST_STATEMENT_WHILE__CONDITION);
    createEReference(astStatementWhileEClass, AST_STATEMENT_WHILE__STATEMENTS);

    astStatementCaseEClass = createEClass(AST_STATEMENT_CASE);
    createEReference(astStatementCaseEClass, AST_STATEMENT_CASE__EXPRESSION);
    createEReference(astStatementCaseEClass, AST_STATEMENT_CASE__CASES);

    astStatementAlternativeEClass = createEClass(AST_STATEMENT_ALTERNATIVE);
    createEReference(astStatementAlternativeEClass, AST_STATEMENT_ALTERNATIVE__PATTERN);
    createEReference(astStatementAlternativeEClass, AST_STATEMENT_ALTERNATIVE__GUARDS);
    createEReference(astStatementAlternativeEClass, AST_STATEMENT_ALTERNATIVE__STATEMENTS);

    astStatementEClass = createEClass(AST_STATEMENT);

    astExpressionEClass = createEClass(AST_EXPRESSION);

    astExpressionSymbolReferenceEClass = createEClass(AST_EXPRESSION_SYMBOL_REFERENCE);
    createEReference(astExpressionSymbolReferenceEClass, AST_EXPRESSION_SYMBOL_REFERENCE__SYMBOL);
    createEReference(astExpressionSymbolReferenceEClass, AST_EXPRESSION_SYMBOL_REFERENCE__INDEXES);
    createEReference(astExpressionSymbolReferenceEClass, AST_EXPRESSION_SYMBOL_REFERENCE__MEMBER);
    createEAttribute(astExpressionSymbolReferenceEClass, AST_EXPRESSION_SYMBOL_REFERENCE__CTOR);
    createEAttribute(astExpressionSymbolReferenceEClass, AST_EXPRESSION_SYMBOL_REFERENCE__CALL);
    createEReference(astExpressionSymbolReferenceEClass, AST_EXPRESSION_SYMBOL_REFERENCE__PARAMETERS);

    astExpressionIfEClass = createEClass(AST_EXPRESSION_IF);
    createEReference(astExpressionIfEClass, AST_EXPRESSION_IF__CONDITION);
    createEReference(astExpressionIfEClass, AST_EXPRESSION_IF__THEN);
    createEReference(astExpressionIfEClass, AST_EXPRESSION_IF__ELSE);

    astExpressionListEClass = createEClass(AST_EXPRESSION_LIST);
    createEReference(astExpressionListEClass, AST_EXPRESSION_LIST__EXPRESSIONS);
    createEReference(astExpressionListEClass, AST_EXPRESSION_LIST__GENERATORS);

    astGeneratorEClass = createEClass(AST_GENERATOR);
    createEReference(astGeneratorEClass, AST_GENERATOR__VARIABLE);
    createEReference(astGeneratorEClass, AST_GENERATOR__EXPRESSION);

    astExpressionCaseEClass = createEClass(AST_EXPRESSION_CASE);
    createEReference(astExpressionCaseEClass, AST_EXPRESSION_CASE__VARIABLE);
    createEReference(astExpressionCaseEClass, AST_EXPRESSION_CASE__CASES);
    createEReference(astExpressionCaseEClass, AST_EXPRESSION_CASE__DEFAULT);

    astExpressionAlternativeEClass = createEClass(AST_EXPRESSION_ALTERNATIVE);
    createEReference(astExpressionAlternativeEClass, AST_EXPRESSION_ALTERNATIVE__PATTERN);
    createEReference(astExpressionAlternativeEClass, AST_EXPRESSION_ALTERNATIVE__GUARDS);
    createEReference(astExpressionAlternativeEClass, AST_EXPRESSION_ALTERNATIVE__EXPRESSION);

    astExpressionLiteralEClass = createEClass(AST_EXPRESSION_LITERAL);

    astExpressionBooleanEClass = createEClass(AST_EXPRESSION_BOOLEAN);
    createEAttribute(astExpressionBooleanEClass, AST_EXPRESSION_BOOLEAN__VALUE);

    astExpressionFloatEClass = createEClass(AST_EXPRESSION_FLOAT);
    createEAttribute(astExpressionFloatEClass, AST_EXPRESSION_FLOAT__VALUE);

    astExpressionIntegerEClass = createEClass(AST_EXPRESSION_INTEGER);
    createEAttribute(astExpressionIntegerEClass, AST_EXPRESSION_INTEGER__VALUE);

    astExpressionStringEClass = createEClass(AST_EXPRESSION_STRING);
    createEAttribute(astExpressionStringEClass, AST_EXPRESSION_STRING__VALUE);

    astPatternEClass = createEClass(AST_PATTERN);
    createEAttribute(astPatternEClass, AST_PATTERN__TAG);
    createEReference(astPatternEClass, AST_PATTERN__SUBPATTERNS);

    astSubPatternEClass = createEClass(AST_SUB_PATTERN);
    createEAttribute(astSubPatternEClass, AST_SUB_PATTERN__LABEL);
    createEAttribute(astSubPatternEClass, AST_SUB_PATTERN__DONTCARE);
    createEReference(astSubPatternEClass, AST_SUB_PATTERN__CONDITION);
    createEReference(astSubPatternEClass, AST_SUB_PATTERN__VARIABLE);
    createEReference(astSubPatternEClass, AST_SUB_PATTERN__PATTERN);

    astPatternExpressionSymbolReferenceEClass = createEClass(AST_PATTERN_EXPRESSION_SYMBOL_REFERENCE);
    createEReference(astPatternExpressionSymbolReferenceEClass, AST_PATTERN_EXPRESSION_SYMBOL_REFERENCE__SYMBOL);
    createEReference(astPatternExpressionSymbolReferenceEClass, AST_PATTERN_EXPRESSION_SYMBOL_REFERENCE__INDEXES);
    createEReference(astPatternExpressionSymbolReferenceEClass, AST_PATTERN_EXPRESSION_SYMBOL_REFERENCE__MEMBER);
    createEAttribute(astPatternExpressionSymbolReferenceEClass, AST_PATTERN_EXPRESSION_SYMBOL_REFERENCE__CTOR);
    createEAttribute(astPatternExpressionSymbolReferenceEClass, AST_PATTERN_EXPRESSION_SYMBOL_REFERENCE__CALL);
    createEReference(astPatternExpressionSymbolReferenceEClass, AST_PATTERN_EXPRESSION_SYMBOL_REFERENCE__PARAMETERS);

    astPatternExpressionIfEClass = createEClass(AST_PATTERN_EXPRESSION_IF);
    createEReference(astPatternExpressionIfEClass, AST_PATTERN_EXPRESSION_IF__CONDITION);
    createEReference(astPatternExpressionIfEClass, AST_PATTERN_EXPRESSION_IF__THEN);
    createEReference(astPatternExpressionIfEClass, AST_PATTERN_EXPRESSION_IF__ELSE);

    astPatternExpressionLiteralEClass = createEClass(AST_PATTERN_EXPRESSION_LITERAL);

    astTypeEClass = createEClass(AST_TYPE);
    createEAttribute(astTypeEClass, AST_TYPE__BUILTIN);
    createEReference(astTypeEClass, AST_TYPE__TYPE_PARAMS);
    createEReference(astTypeEClass, AST_TYPE__DIMENSIONS);
    createEReference(astTypeEClass, AST_TYPE__NAME);
    createEReference(astTypeEClass, AST_TYPE__DOMAIN);
    createEReference(astTypeEClass, AST_TYPE__CODOMAIN);

    astTypeParameterListEClass = createEClass(AST_TYPE_PARAMETER_LIST);
    createEReference(astTypeParameterListEClass, AST_TYPE_PARAMETER_LIST__PARAMS);

    astTypeParamEClass = createEClass(AST_TYPE_PARAM);
    createEAttribute(astTypeParamEClass, AST_TYPE_PARAM__NAME);
    createEReference(astTypeParamEClass, AST_TYPE_PARAM__VALUE);
    createEReference(astTypeParamEClass, AST_TYPE_PARAM__TYPE);

    astMemberAccessEClass = createEClass(AST_MEMBER_ACCESS);
    createEAttribute(astMemberAccessEClass, AST_MEMBER_ACCESS__NAME);
    createEReference(astMemberAccessEClass, AST_MEMBER_ACCESS__MEMBER_INDEX);

    astAnnotationEClass = createEClass(AST_ANNOTATION);
    createEAttribute(astAnnotationEClass, AST_ANNOTATION__NAME);
    createEReference(astAnnotationEClass, AST_ANNOTATION__ARGUMENTS);

    astAnnotationArgumentEClass = createEClass(AST_ANNOTATION_ARGUMENT);
    createEAttribute(astAnnotationArgumentEClass, AST_ANNOTATION_ARGUMENT__NAME);
    createEAttribute(astAnnotationArgumentEClass, AST_ANNOTATION_ARGUMENT__VALUE);

    astTupleEClass = createEClass(AST_TUPLE);

    astFunctionEClass = createEClass(AST_FUNCTION);
    createEReference(astFunctionEClass, AST_FUNCTION__PARAMETERS);
    createEReference(astFunctionEClass, AST_FUNCTION__VARIABLES);
    createEReference(astFunctionEClass, AST_FUNCTION__EXPRESSION);

    astInitializeEClass = createEClass(AST_INITIALIZE);

    astExpressionBinaryEClass = createEClass(AST_EXPRESSION_BINARY);
    createEReference(astExpressionBinaryEClass, AST_EXPRESSION_BINARY__LEFT);
    createEAttribute(astExpressionBinaryEClass, AST_EXPRESSION_BINARY__OPERATOR);
    createEReference(astExpressionBinaryEClass, AST_EXPRESSION_BINARY__RIGHT);

    astExpressionUnaryEClass = createEClass(AST_EXPRESSION_UNARY);
    createEAttribute(astExpressionUnaryEClass, AST_EXPRESSION_UNARY__UNARY_OPERATOR);
    createEReference(astExpressionUnaryEClass, AST_EXPRESSION_UNARY__EXPRESSION);

    astPatternExpressionBinaryEClass = createEClass(AST_PATTERN_EXPRESSION_BINARY);
    createEReference(astPatternExpressionBinaryEClass, AST_PATTERN_EXPRESSION_BINARY__LEFT);
    createEAttribute(astPatternExpressionBinaryEClass, AST_PATTERN_EXPRESSION_BINARY__OPERATOR);
    createEReference(astPatternExpressionBinaryEClass, AST_PATTERN_EXPRESSION_BINARY__RIGHT);

    astPatternExpressionUnaryEClass = createEClass(AST_PATTERN_EXPRESSION_UNARY);
    createEAttribute(astPatternExpressionUnaryEClass, AST_PATTERN_EXPRESSION_UNARY__UNARY_OPERATOR);
    createEReference(astPatternExpressionUnaryEClass, AST_PATTERN_EXPRESSION_UNARY__EXPRESSION);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    astPackageEClass.getESuperTypes().add(this.getAstTop());
    astNamespaceEClass.getESuperTypes().add(this.getAstTop());
    astNamespaceEClass.getESuperTypes().add(this.getAstPackage());
    astNamespaceEClass.getESuperTypes().add(this.getAstUnit());
    astNetworkEClass.getESuperTypes().add(this.getAstAbstractActor());
    astTypeUserEClass.getESuperTypes().add(this.getAstVariable());
    astActorEClass.getESuperTypes().add(this.getAstAbstractActor());
    astProcedureEClass.getESuperTypes().add(this.getAstExternalProcedure());
    astExternalActorEClass.getESuperTypes().add(this.getAstAbstractActor());
    astStatementAssignEClass.getESuperTypes().add(this.getAstStatement());
    astStatementCallEClass.getESuperTypes().add(this.getAstStatement());
    astStatementForeachEClass.getESuperTypes().add(this.getAstStatement());
    astStatementBlockEClass.getESuperTypes().add(this.getAstStatement());
    astStatementIfEClass.getESuperTypes().add(this.getAstStatement());
    astStatementWhileEClass.getESuperTypes().add(this.getAstStatement());
    astStatementCaseEClass.getESuperTypes().add(this.getAstStatement());
    astExpressionSymbolReferenceEClass.getESuperTypes().add(this.getAstExpression());
    astExpressionIfEClass.getESuperTypes().add(this.getAstExpression());
    astExpressionListEClass.getESuperTypes().add(this.getAstExpression());
    astExpressionCaseEClass.getESuperTypes().add(this.getAstExpression());
    astExpressionLiteralEClass.getESuperTypes().add(this.getAstExpression());
    astExpressionBooleanEClass.getESuperTypes().add(this.getAstExpressionLiteral());
    astExpressionBooleanEClass.getESuperTypes().add(this.getAstPatternExpressionLiteral());
    astExpressionFloatEClass.getESuperTypes().add(this.getAstExpressionLiteral());
    astExpressionIntegerEClass.getESuperTypes().add(this.getAstExpressionLiteral());
    astExpressionIntegerEClass.getESuperTypes().add(this.getAstPatternExpressionLiteral());
    astExpressionStringEClass.getESuperTypes().add(this.getAstExpressionLiteral());
    astExpressionStringEClass.getESuperTypes().add(this.getAstPatternExpressionLiteral());
    astPatternExpressionSymbolReferenceEClass.getESuperTypes().add(this.getAstExpression());
    astPatternExpressionIfEClass.getESuperTypes().add(this.getAstExpression());
    astPatternExpressionLiteralEClass.getESuperTypes().add(this.getAstExpression());
    astTupleEClass.getESuperTypes().add(this.getAstTaggedTuple());
    astFunctionEClass.getESuperTypes().add(this.getAstVariable());
    astFunctionEClass.getESuperTypes().add(this.getAstExternalFunction());
    astInitializeEClass.getESuperTypes().add(this.getAstAction());
    astExpressionBinaryEClass.getESuperTypes().add(this.getAstExpression());
    astExpressionUnaryEClass.getESuperTypes().add(this.getAstExpression());
    astPatternExpressionBinaryEClass.getESuperTypes().add(this.getAstExpression());
    astPatternExpressionUnaryEClass.getESuperTypes().add(this.getAstExpression());

    // Initialize classes and features; add operations and parameters
    initEClass(astTopEClass, AstTop.class, "AstTop", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(astPackageEClass, AstPackage.class, "AstPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(astUnitEClass, AstUnit.class, "AstUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(astNamespaceEClass, AstNamespace.class, "AstNamespace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAstNamespace_Name(), ecorePackage.getEString(), "name", null, 0, 1, AstNamespace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstNamespace_Entities(), this.getAstEntity(), null, "entities", null, 0, -1, AstNamespace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstNamespace_Imports(), this.getImport(), null, "imports", null, 0, -1, AstNamespace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstNamespace_Units(), this.getAstUnit(), null, "units", null, 0, -1, AstNamespace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstNamespace_Functions(), this.getAstVariable(), null, "functions", null, 0, -1, AstNamespace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstNamespace_Variables(), this.getAstVariable(), null, "variables", null, 0, -1, AstNamespace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstNamespace_Externals(), ecorePackage.getEObject(), null, "externals", null, 0, -1, AstNamespace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstNamespace_Annotations(), this.getAstAnnotation(), null, "annotations", null, 0, -1, AstNamespace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstNamespace_Typedefs(), this.getAstTypeUser(), null, "typedefs", null, 0, -1, AstNamespace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstNamespace_Namespaces(), this.getAstNamespace(), null, "namespaces", null, 0, -1, AstNamespace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astEntityEClass, AstEntity.class, "AstEntity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstEntity_Annotations(), this.getAstAnnotation(), null, "annotations", null, 0, -1, AstEntity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstEntity_Actor(), this.getAstAbstractActor(), null, "actor", null, 0, 1, AstEntity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astAbstractActorEClass, AstAbstractActor.class, "AstAbstractActor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAstAbstractActor_Name(), ecorePackage.getEString(), "name", null, 0, 1, AstAbstractActor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstAbstractActor_Parameters(), this.getAstVariable(), null, "parameters", null, 0, -1, AstAbstractActor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstAbstractActor_Inputs(), this.getAstPort(), null, "inputs", null, 0, -1, AstAbstractActor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstAbstractActor_Outputs(), this.getAstPort(), null, "outputs", null, 0, -1, AstAbstractActor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(importEClass, Import.class, "Import", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getImport_ImportedNamespace(), ecorePackage.getEString(), "importedNamespace", null, 0, 1, Import.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astNetworkEClass, AstNetwork.class, "AstNetwork", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstNetwork_Variables(), this.getAstVariable(), null, "variables", null, 0, -1, AstNetwork.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstNetwork_Instances(), this.getAstActorVariable(), null, "instances", null, 0, -1, AstNetwork.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstNetwork_Structure(), this.getAstStructure(), null, "structure", null, 0, 1, AstNetwork.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astActorVariableEClass, AstActorVariable.class, "AstActorVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAstActorVariable_Name(), ecorePackage.getEString(), "name", null, 0, 1, AstActorVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstActorVariable_Type(), this.getAstEntity(), null, "type", null, 0, 1, AstActorVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstActorVariable_Parameters(), this.getAstAssignParameter(), null, "parameters", null, 0, -1, AstActorVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astAssignParameterEClass, AstAssignParameter.class, "AstAssignParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAstAssignParameter_Name(), ecorePackage.getEString(), "name", null, 0, 1, AstAssignParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstAssignParameter_Value(), this.getAstExpression(), null, "value", null, 0, 1, AstAssignParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astStructureEClass, AstStructure.class, "AstStructure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstStructure_Connections(), this.getAstConnection(), null, "connections", null, 0, -1, AstStructure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astConnectionEClass, AstConnection.class, "AstConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstConnection_From(), this.getAstActorVariableReference(), null, "from", null, 0, 1, AstConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAstConnection_OutPort(), ecorePackage.getEString(), "outPort", null, 0, 1, AstConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstConnection_To(), this.getAstActorVariableReference(), null, "to", null, 0, 1, AstConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAstConnection_InPort(), ecorePackage.getEString(), "inPort", null, 0, 1, AstConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstConnection_Attribute(), this.getAstConnectionAttribute(), null, "attribute", null, 0, -1, AstConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astActorVariableReferenceEClass, AstActorVariableReference.class, "AstActorVariableReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstActorVariableReference_Variable(), this.getAstActorVariable(), null, "variable", null, 0, 1, AstActorVariableReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astConnectionAttributeEClass, AstConnectionAttribute.class, "AstConnectionAttribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAstConnectionAttribute_Name(), ecorePackage.getEString(), "name", null, 0, 1, AstConnectionAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstConnectionAttribute_Value(), this.getAstExpression(), null, "value", null, 0, 1, AstConnectionAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astVariableEClass, AstVariable.class, "AstVariable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAstVariable_Constant(), ecorePackage.getEBoolean(), "constant", null, 0, 1, AstVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstVariable_Value(), this.getAstExpression(), null, "value", null, 0, 1, AstVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAstVariable_Name(), ecorePackage.getEString(), "name", null, 0, 1, AstVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstVariable_Annotations(), this.getAstAnnotation(), null, "annotations", null, 0, -1, AstVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstVariable_Type(), this.getAstType(), null, "type", null, 0, 1, AstVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstVariable_Dimensions(), this.getAstExpression(), null, "dimensions", null, 0, -1, AstVariable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astTypeUserEClass, AstTypeUser.class, "AstTypeUser", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAstTypeUser_Definition(), ecorePackage.getEBoolean(), "definition", null, 0, 1, AstTypeUser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstTypeUser_Parameters(), this.getAstTypeDefinitionParameter(), null, "parameters", null, 0, -1, AstTypeUser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstTypeUser_Tuples(), this.getAstTaggedTuple(), null, "tuples", null, 0, -1, AstTypeUser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAstTypeUser_Variable(), ecorePackage.getEBoolean(), "variable", null, 0, 1, AstTypeUser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astTypeDefinitionParameterEClass, AstTypeDefinitionParameter.class, "AstTypeDefinitionParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstTypeDefinitionParameter_Value(), this.getAstVariable(), null, "value", null, 0, 1, AstTypeDefinitionParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstTypeDefinitionParameter_Type(), this.getAstTypeUser(), null, "type", null, 0, 1, AstTypeDefinitionParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astTaggedTupleEClass, AstTaggedTuple.class, "AstTaggedTuple", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAstTaggedTuple_Name(), ecorePackage.getEString(), "name", null, 0, 1, AstTaggedTuple.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstTaggedTuple_Fields(), this.getAstVariable(), null, "fields", null, 0, -1, AstTaggedTuple.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astActorEClass, AstActor.class, "AstActor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstActor_Functions(), this.getAstVariable(), null, "functions", null, 0, -1, AstActor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstActor_Procedures(), this.getAstProcedure(), null, "procedures", null, 0, -1, AstActor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstActor_Actions(), this.getAstAction(), null, "actions", null, 0, -1, AstActor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstActor_Initializes(), this.getAstAction(), null, "initializes", null, 0, -1, AstActor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstActor_StateVariables(), this.getAstVariable(), null, "stateVariables", null, 0, -1, AstActor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstActor_Schedules(), this.getAstSchedule(), null, "schedules", null, 0, -1, AstActor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstActor_Priorities(), this.getAstPriority(), null, "priorities", null, 0, -1, AstActor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astPortEClass, AstPort.class, "AstPort", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstPort_Annotations(), this.getAstAnnotation(), null, "annotations", null, 0, -1, AstPort.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstPort_Type(), this.getAstType(), null, "type", null, 0, 1, AstPort.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAstPort_Name(), ecorePackage.getEString(), "name", null, 0, 1, AstPort.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astExternalFunctionEClass, AstExternalFunction.class, "AstExternalFunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(astProcedureEClass, AstProcedure.class, "AstProcedure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstProcedure_Annotations(), this.getAstAnnotation(), null, "annotations", null, 0, -1, AstProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAstProcedure_Name(), ecorePackage.getEString(), "name", null, 0, 1, AstProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstProcedure_Parameters(), this.getAstVariable(), null, "parameters", null, 0, -1, AstProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstProcedure_Variables(), this.getAstVariable(), null, "variables", null, 0, -1, AstProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstProcedure_Statements(), this.getAstStatement(), null, "statements", null, 0, -1, AstProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astExternalProcedureEClass, AstExternalProcedure.class, "AstExternalProcedure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(astTagEClass, AstTag.class, "AstTag", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAstTag_Identifiers(), ecorePackage.getEString(), "identifiers", null, 0, -1, AstTag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astExternalActorEClass, AstExternalActor.class, "AstExternalActor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(astInequalityEClass, AstInequality.class, "AstInequality", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstInequality_Tags(), this.getAstTag(), null, "tags", null, 0, -1, AstInequality.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astPriorityEClass, AstPriority.class, "AstPriority", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstPriority_Inequalities(), this.getAstInequality(), null, "inequalities", null, 0, -1, AstPriority.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astScheduleEClass, AstSchedule.class, "AstSchedule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstSchedule_InitialState(), this.getAstState(), null, "initialState", null, 0, 1, AstSchedule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstSchedule_Transitions(), this.getAstTransition(), null, "transitions", null, 0, -1, AstSchedule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astTransitionEClass, AstTransition.class, "AstTransition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstTransition_Source(), this.getAstState(), null, "source", null, 0, 1, AstTransition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstTransition_Tags(), this.getAstTag(), null, "tags", null, 0, -1, AstTransition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstTransition_Target(), this.getAstState(), null, "target", null, 0, 1, AstTransition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astStateEClass, AstState.class, "AstState", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAstState_Name(), ecorePackage.getEString(), "name", null, 0, 1, AstState.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astActionEClass, AstAction.class, "AstAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstAction_Annotations(), this.getAstAnnotation(), null, "annotations", null, 0, -1, AstAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstAction_Tag(), this.getAstTag(), null, "tag", null, 0, 1, AstAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstAction_Inputs(), this.getAstInputPattern(), null, "inputs", null, 0, -1, AstAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstAction_Outputs(), this.getAstOutputPattern(), null, "outputs", null, 0, -1, AstAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstAction_Guards(), this.getAstExpression(), null, "guards", null, 0, -1, AstAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstAction_Variables(), this.getAstVariable(), null, "variables", null, 0, -1, AstAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstAction_Statements(), this.getAstStatement(), null, "statements", null, 0, -1, AstAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astInputPatternEClass, AstInputPattern.class, "AstInputPattern", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstInputPattern_Port(), this.getAstPort(), null, "port", null, 0, 1, AstInputPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstInputPattern_Tokens(), this.getAstVariable(), null, "tokens", null, 0, -1, AstInputPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstInputPattern_Repeat(), this.getAstExpression(), null, "repeat", null, 0, 1, AstInputPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astOutputPatternEClass, AstOutputPattern.class, "AstOutputPattern", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstOutputPattern_Port(), this.getAstPort(), null, "port", null, 0, 1, AstOutputPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstOutputPattern_Values(), this.getAstExpression(), null, "values", null, 0, -1, AstOutputPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstOutputPattern_Repeat(), this.getAstExpression(), null, "repeat", null, 0, 1, AstOutputPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astStatementAssignEClass, AstStatementAssign.class, "AstStatementAssign", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstStatementAssign_Target(), this.getAstVariable(), null, "target", null, 0, 1, AstStatementAssign.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstStatementAssign_Indexes(), this.getAstExpression(), null, "indexes", null, 0, -1, AstStatementAssign.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstStatementAssign_Member(), this.getAstMemberAccess(), null, "member", null, 0, -1, AstStatementAssign.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstStatementAssign_Value(), this.getAstExpression(), null, "value", null, 0, 1, AstStatementAssign.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astStatementCallEClass, AstStatementCall.class, "AstStatementCall", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstStatementCall_Procedure(), this.getAstProcedure(), null, "procedure", null, 0, 1, AstStatementCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstStatementCall_Parameters(), this.getAstExpression(), null, "parameters", null, 0, -1, AstStatementCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astStatementForeachEClass, AstStatementForeach.class, "AstStatementForeach", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstStatementForeach_Generators(), this.getAstForeachGenerator(), null, "generators", null, 0, -1, AstStatementForeach.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstStatementForeach_Variables(), this.getAstVariable(), null, "variables", null, 0, -1, AstStatementForeach.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstStatementForeach_Statements(), this.getAstStatement(), null, "statements", null, 0, -1, AstStatementForeach.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astForeachGeneratorEClass, AstForeachGenerator.class, "AstForeachGenerator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstForeachGenerator_Variable(), this.getAstVariable(), null, "variable", null, 0, 1, AstForeachGenerator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstForeachGenerator_Expression(), this.getAstExpression(), null, "expression", null, 0, 1, AstForeachGenerator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astStatementBlockEClass, AstStatementBlock.class, "AstStatementBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstStatementBlock_Variables(), this.getAstVariable(), null, "variables", null, 0, -1, AstStatementBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstStatementBlock_Statements(), this.getAstStatement(), null, "statements", null, 0, -1, AstStatementBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astStatementIfEClass, AstStatementIf.class, "AstStatementIf", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstStatementIf_Condition(), this.getAstExpression(), null, "condition", null, 0, 1, AstStatementIf.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstStatementIf_Then(), this.getAstStatement(), null, "then", null, 0, -1, AstStatementIf.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstStatementIf_Else(), this.getAstStatement(), null, "else", null, 0, -1, AstStatementIf.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astStatementWhileEClass, AstStatementWhile.class, "AstStatementWhile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstStatementWhile_Condition(), this.getAstExpression(), null, "condition", null, 0, 1, AstStatementWhile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstStatementWhile_Statements(), this.getAstStatement(), null, "statements", null, 0, -1, AstStatementWhile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astStatementCaseEClass, AstStatementCase.class, "AstStatementCase", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstStatementCase_Expression(), this.getAstExpressionSymbolReference(), null, "expression", null, 0, 1, AstStatementCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstStatementCase_Cases(), this.getAstStatementAlternative(), null, "cases", null, 0, -1, AstStatementCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astStatementAlternativeEClass, AstStatementAlternative.class, "AstStatementAlternative", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstStatementAlternative_Pattern(), this.getAstPattern(), null, "pattern", null, 0, 1, AstStatementAlternative.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstStatementAlternative_Guards(), this.getAstExpression(), null, "guards", null, 0, -1, AstStatementAlternative.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstStatementAlternative_Statements(), this.getAstStatement(), null, "statements", null, 0, -1, AstStatementAlternative.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astStatementEClass, AstStatement.class, "AstStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(astExpressionEClass, AstExpression.class, "AstExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(astExpressionSymbolReferenceEClass, AstExpressionSymbolReference.class, "AstExpressionSymbolReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstExpressionSymbolReference_Symbol(), this.getAstVariable(), null, "symbol", null, 0, 1, AstExpressionSymbolReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstExpressionSymbolReference_Indexes(), this.getAstExpression(), null, "indexes", null, 0, -1, AstExpressionSymbolReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstExpressionSymbolReference_Member(), this.getAstMemberAccess(), null, "member", null, 0, -1, AstExpressionSymbolReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAstExpressionSymbolReference_Ctor(), ecorePackage.getEString(), "ctor", null, 0, 1, AstExpressionSymbolReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAstExpressionSymbolReference_Call(), ecorePackage.getEBoolean(), "call", null, 0, 1, AstExpressionSymbolReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstExpressionSymbolReference_Parameters(), this.getAstExpression(), null, "parameters", null, 0, -1, AstExpressionSymbolReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astExpressionIfEClass, AstExpressionIf.class, "AstExpressionIf", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstExpressionIf_Condition(), this.getAstExpression(), null, "condition", null, 0, 1, AstExpressionIf.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstExpressionIf_Then(), this.getAstExpression(), null, "then", null, 0, 1, AstExpressionIf.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstExpressionIf_Else(), this.getAstExpression(), null, "else", null, 0, 1, AstExpressionIf.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astExpressionListEClass, AstExpressionList.class, "AstExpressionList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstExpressionList_Expressions(), this.getAstExpression(), null, "expressions", null, 0, -1, AstExpressionList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstExpressionList_Generators(), this.getAstGenerator(), null, "generators", null, 0, -1, AstExpressionList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astGeneratorEClass, AstGenerator.class, "AstGenerator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstGenerator_Variable(), this.getAstVariable(), null, "variable", null, 0, 1, AstGenerator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstGenerator_Expression(), this.getAstExpression(), null, "expression", null, 0, 1, AstGenerator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astExpressionCaseEClass, AstExpressionCase.class, "AstExpressionCase", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstExpressionCase_Variable(), this.getAstExpressionSymbolReference(), null, "variable", null, 0, 1, AstExpressionCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstExpressionCase_Cases(), this.getAstExpressionAlternative(), null, "cases", null, 0, -1, AstExpressionCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstExpressionCase_Default(), this.getAstExpression(), null, "default", null, 0, 1, AstExpressionCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astExpressionAlternativeEClass, AstExpressionAlternative.class, "AstExpressionAlternative", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstExpressionAlternative_Pattern(), this.getAstPattern(), null, "pattern", null, 0, 1, AstExpressionAlternative.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstExpressionAlternative_Guards(), this.getAstExpression(), null, "guards", null, 0, -1, AstExpressionAlternative.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstExpressionAlternative_Expression(), this.getAstExpression(), null, "expression", null, 0, 1, AstExpressionAlternative.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astExpressionLiteralEClass, AstExpressionLiteral.class, "AstExpressionLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(astExpressionBooleanEClass, AstExpressionBoolean.class, "AstExpressionBoolean", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAstExpressionBoolean_Value(), ecorePackage.getEBoolean(), "value", null, 0, 1, AstExpressionBoolean.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astExpressionFloatEClass, AstExpressionFloat.class, "AstExpressionFloat", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAstExpressionFloat_Value(), ecorePackage.getEFloat(), "value", null, 0, 1, AstExpressionFloat.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astExpressionIntegerEClass, AstExpressionInteger.class, "AstExpressionInteger", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAstExpressionInteger_Value(), ecorePackage.getELong(), "value", null, 0, 1, AstExpressionInteger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astExpressionStringEClass, AstExpressionString.class, "AstExpressionString", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAstExpressionString_Value(), ecorePackage.getEString(), "value", null, 0, 1, AstExpressionString.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astPatternEClass, AstPattern.class, "AstPattern", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAstPattern_Tag(), ecorePackage.getEString(), "tag", null, 0, 1, AstPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstPattern_Subpatterns(), this.getAstSubPattern(), null, "subpatterns", null, 0, -1, AstPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astSubPatternEClass, AstSubPattern.class, "AstSubPattern", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAstSubPattern_Label(), ecorePackage.getEString(), "label", null, 0, 1, AstSubPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAstSubPattern_Dontcare(), ecorePackage.getEBoolean(), "dontcare", null, 0, 1, AstSubPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstSubPattern_Condition(), this.getAstExpression(), null, "condition", null, 0, 1, AstSubPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstSubPattern_Variable(), this.getAstVariable(), null, "variable", null, 0, 1, AstSubPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstSubPattern_Pattern(), this.getAstPattern(), null, "pattern", null, 0, 1, AstSubPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astPatternExpressionSymbolReferenceEClass, AstPatternExpressionSymbolReference.class, "AstPatternExpressionSymbolReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstPatternExpressionSymbolReference_Symbol(), this.getAstVariable(), null, "symbol", null, 0, 1, AstPatternExpressionSymbolReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstPatternExpressionSymbolReference_Indexes(), this.getAstExpression(), null, "indexes", null, 0, -1, AstPatternExpressionSymbolReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstPatternExpressionSymbolReference_Member(), this.getAstMemberAccess(), null, "member", null, 0, -1, AstPatternExpressionSymbolReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAstPatternExpressionSymbolReference_Ctor(), ecorePackage.getEString(), "ctor", null, 0, 1, AstPatternExpressionSymbolReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAstPatternExpressionSymbolReference_Call(), ecorePackage.getEBoolean(), "call", null, 0, 1, AstPatternExpressionSymbolReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstPatternExpressionSymbolReference_Parameters(), this.getAstExpression(), null, "parameters", null, 0, -1, AstPatternExpressionSymbolReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astPatternExpressionIfEClass, AstPatternExpressionIf.class, "AstPatternExpressionIf", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstPatternExpressionIf_Condition(), this.getAstExpression(), null, "condition", null, 0, 1, AstPatternExpressionIf.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstPatternExpressionIf_Then(), this.getAstExpression(), null, "then", null, 0, 1, AstPatternExpressionIf.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstPatternExpressionIf_Else(), this.getAstExpression(), null, "else", null, 0, 1, AstPatternExpressionIf.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astPatternExpressionLiteralEClass, AstPatternExpressionLiteral.class, "AstPatternExpressionLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(astTypeEClass, AstType.class, "AstType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAstType_Builtin(), ecorePackage.getEString(), "builtin", null, 0, 1, AstType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstType_TypeParams(), this.getAstTypeParameterList(), null, "typeParams", null, 0, 1, AstType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstType_Dimensions(), this.getAstExpression(), null, "dimensions", null, 0, -1, AstType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstType_Name(), this.getAstTypeUser(), null, "name", null, 0, 1, AstType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstType_Domain(), this.getAstType(), null, "domain", null, 0, -1, AstType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstType_Codomain(), this.getAstType(), null, "codomain", null, 0, -1, AstType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astTypeParameterListEClass, AstTypeParameterList.class, "AstTypeParameterList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstTypeParameterList_Params(), this.getAstTypeParam(), null, "params", null, 0, -1, AstTypeParameterList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astTypeParamEClass, AstTypeParam.class, "AstTypeParam", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAstTypeParam_Name(), ecorePackage.getEString(), "name", null, 0, 1, AstTypeParam.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstTypeParam_Value(), this.getAstExpression(), null, "value", null, 0, 1, AstTypeParam.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstTypeParam_Type(), this.getAstType(), null, "type", null, 0, 1, AstTypeParam.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astMemberAccessEClass, AstMemberAccess.class, "AstMemberAccess", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAstMemberAccess_Name(), ecorePackage.getEString(), "name", null, 0, 1, AstMemberAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstMemberAccess_MemberIndex(), this.getAstExpression(), null, "memberIndex", null, 0, -1, AstMemberAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astAnnotationEClass, AstAnnotation.class, "AstAnnotation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAstAnnotation_Name(), ecorePackage.getEString(), "name", null, 0, 1, AstAnnotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstAnnotation_Arguments(), this.getAstAnnotationArgument(), null, "arguments", null, 0, -1, AstAnnotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astAnnotationArgumentEClass, AstAnnotationArgument.class, "AstAnnotationArgument", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAstAnnotationArgument_Name(), ecorePackage.getEString(), "name", null, 0, 1, AstAnnotationArgument.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAstAnnotationArgument_Value(), ecorePackage.getEString(), "value", null, 0, 1, AstAnnotationArgument.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astTupleEClass, AstTuple.class, "AstTuple", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(astFunctionEClass, AstFunction.class, "AstFunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstFunction_Parameters(), this.getAstVariable(), null, "parameters", null, 0, -1, AstFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstFunction_Variables(), this.getAstVariable(), null, "variables", null, 0, -1, AstFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstFunction_Expression(), this.getAstExpression(), null, "expression", null, 0, 1, AstFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astInitializeEClass, AstInitialize.class, "AstInitialize", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(astExpressionBinaryEClass, AstExpressionBinary.class, "AstExpressionBinary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstExpressionBinary_Left(), this.getAstExpression(), null, "left", null, 0, 1, AstExpressionBinary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAstExpressionBinary_Operator(), ecorePackage.getEString(), "operator", null, 0, 1, AstExpressionBinary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstExpressionBinary_Right(), this.getAstExpression(), null, "right", null, 0, 1, AstExpressionBinary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astExpressionUnaryEClass, AstExpressionUnary.class, "AstExpressionUnary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAstExpressionUnary_UnaryOperator(), ecorePackage.getEString(), "unaryOperator", null, 0, 1, AstExpressionUnary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstExpressionUnary_Expression(), this.getAstExpression(), null, "expression", null, 0, 1, AstExpressionUnary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astPatternExpressionBinaryEClass, AstPatternExpressionBinary.class, "AstPatternExpressionBinary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAstPatternExpressionBinary_Left(), this.getAstExpression(), null, "left", null, 0, 1, AstPatternExpressionBinary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAstPatternExpressionBinary_Operator(), ecorePackage.getEString(), "operator", null, 0, 1, AstPatternExpressionBinary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstPatternExpressionBinary_Right(), this.getAstExpression(), null, "right", null, 0, 1, AstPatternExpressionBinary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(astPatternExpressionUnaryEClass, AstPatternExpressionUnary.class, "AstPatternExpressionUnary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAstPatternExpressionUnary_UnaryOperator(), ecorePackage.getEString(), "unaryOperator", null, 0, 1, AstPatternExpressionUnary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAstPatternExpressionUnary_Expression(), this.getAstExpression(), null, "expression", null, 0, 1, AstPatternExpressionUnary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //CalPackageImpl
