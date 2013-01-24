/**
 * <copyright>
 * </copyright>
 *
 */
package org.caltoopia.frontend.cal;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.caltoopia.frontend.cal.CalFactory
 * @model kind="package"
 * @generated
 */
public interface CalPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "cal";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.caltoopia.org/frontend/Cal";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "cal";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  CalPackage eINSTANCE = org.caltoopia.frontend.cal.impl.CalPackageImpl.init();

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstTopImpl <em>Ast Top</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstTopImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstTop()
   * @generated
   */
  int AST_TOP = 0;

  /**
   * The number of structural features of the '<em>Ast Top</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_TOP_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstPackageImpl <em>Ast Package</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstPackageImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstPackage()
   * @generated
   */
  int AST_PACKAGE = 1;

  /**
   * The number of structural features of the '<em>Ast Package</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_PACKAGE_FEATURE_COUNT = AST_TOP_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstUnitImpl <em>Ast Unit</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstUnitImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstUnit()
   * @generated
   */
  int AST_UNIT = 2;

  /**
   * The number of structural features of the '<em>Ast Unit</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_UNIT_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstNamespaceImpl <em>Ast Namespace</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstNamespaceImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstNamespace()
   * @generated
   */
  int AST_NAMESPACE = 3;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_NAMESPACE__NAME = AST_TOP_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Entities</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_NAMESPACE__ENTITIES = AST_TOP_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Imports</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_NAMESPACE__IMPORTS = AST_TOP_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Units</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_NAMESPACE__UNITS = AST_TOP_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Functions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_NAMESPACE__FUNCTIONS = AST_TOP_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Variables</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_NAMESPACE__VARIABLES = AST_TOP_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>Externals</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_NAMESPACE__EXTERNALS = AST_TOP_FEATURE_COUNT + 6;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_NAMESPACE__ANNOTATIONS = AST_TOP_FEATURE_COUNT + 7;

  /**
   * The feature id for the '<em><b>Typedefs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_NAMESPACE__TYPEDEFS = AST_TOP_FEATURE_COUNT + 8;

  /**
   * The feature id for the '<em><b>Namespaces</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_NAMESPACE__NAMESPACES = AST_TOP_FEATURE_COUNT + 9;

  /**
   * The number of structural features of the '<em>Ast Namespace</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_NAMESPACE_FEATURE_COUNT = AST_TOP_FEATURE_COUNT + 10;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstEntityImpl <em>Ast Entity</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstEntityImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstEntity()
   * @generated
   */
  int AST_ENTITY = 4;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ENTITY__ANNOTATIONS = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ENTITY__NAME = 1;

  /**
   * The feature id for the '<em><b>Actor</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ENTITY__ACTOR = 2;

  /**
   * The feature id for the '<em><b>Network</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ENTITY__NETWORK = 3;

  /**
   * The feature id for the '<em><b>External</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ENTITY__EXTERNAL = 4;

  /**
   * The number of structural features of the '<em>Ast Entity</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ENTITY_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.ImportImpl <em>Import</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.ImportImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getImport()
   * @generated
   */
  int IMPORT = 5;

  /**
   * The feature id for the '<em><b>Imported Namespace</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT__IMPORTED_NAMESPACE = 0;

  /**
   * The number of structural features of the '<em>Import</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstNetworkImpl <em>Ast Network</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstNetworkImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstNetwork()
   * @generated
   */
  int AST_NETWORK = 6;

  /**
   * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_NETWORK__PARAMETERS = 0;

  /**
   * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_NETWORK__INPUTS = 1;

  /**
   * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_NETWORK__OUTPUTS = 2;

  /**
   * The feature id for the '<em><b>Variables</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_NETWORK__VARIABLES = 3;

  /**
   * The feature id for the '<em><b>Instances</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_NETWORK__INSTANCES = 4;

  /**
   * The feature id for the '<em><b>Structure</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_NETWORK__STRUCTURE = 5;

  /**
   * The number of structural features of the '<em>Ast Network</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_NETWORK_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstActorVariableImpl <em>Ast Actor Variable</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstActorVariableImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstActorVariable()
   * @generated
   */
  int AST_ACTOR_VARIABLE = 7;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ACTOR_VARIABLE__NAME = 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ACTOR_VARIABLE__TYPE = 1;

  /**
   * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ACTOR_VARIABLE__PARAMETERS = 2;

  /**
   * The number of structural features of the '<em>Ast Actor Variable</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ACTOR_VARIABLE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstAssignParameterImpl <em>Ast Assign Parameter</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstAssignParameterImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstAssignParameter()
   * @generated
   */
  int AST_ASSIGN_PARAMETER = 8;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ASSIGN_PARAMETER__NAME = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ASSIGN_PARAMETER__VALUE = 1;

  /**
   * The number of structural features of the '<em>Ast Assign Parameter</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ASSIGN_PARAMETER_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstStructureImpl <em>Ast Structure</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstStructureImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstStructure()
   * @generated
   */
  int AST_STRUCTURE = 9;

  /**
   * The feature id for the '<em><b>Connections</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_STRUCTURE__CONNECTIONS = 0;

  /**
   * The number of structural features of the '<em>Ast Structure</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_STRUCTURE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstConnectionImpl <em>Ast Connection</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstConnectionImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstConnection()
   * @generated
   */
  int AST_CONNECTION = 10;

  /**
   * The feature id for the '<em><b>From</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_CONNECTION__FROM = 0;

  /**
   * The feature id for the '<em><b>Out Port</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_CONNECTION__OUT_PORT = 1;

  /**
   * The feature id for the '<em><b>To</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_CONNECTION__TO = 2;

  /**
   * The feature id for the '<em><b>In Port</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_CONNECTION__IN_PORT = 3;

  /**
   * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_CONNECTION__ATTRIBUTE = 4;

  /**
   * The number of structural features of the '<em>Ast Connection</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_CONNECTION_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstActorVariableReferenceImpl <em>Ast Actor Variable Reference</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstActorVariableReferenceImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstActorVariableReference()
   * @generated
   */
  int AST_ACTOR_VARIABLE_REFERENCE = 11;

  /**
   * The feature id for the '<em><b>Variable</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ACTOR_VARIABLE_REFERENCE__VARIABLE = 0;

  /**
   * The number of structural features of the '<em>Ast Actor Variable Reference</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ACTOR_VARIABLE_REFERENCE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstConnectionAttributeImpl <em>Ast Connection Attribute</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstConnectionAttributeImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstConnectionAttribute()
   * @generated
   */
  int AST_CONNECTION_ATTRIBUTE = 12;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_CONNECTION_ATTRIBUTE__NAME = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_CONNECTION_ATTRIBUTE__VALUE = 1;

  /**
   * The number of structural features of the '<em>Ast Connection Attribute</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_CONNECTION_ATTRIBUTE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstVariableImpl <em>Ast Variable</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstVariableImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstVariable()
   * @generated
   */
  int AST_VARIABLE = 13;

  /**
   * The feature id for the '<em><b>Constant</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_VARIABLE__CONSTANT = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_VARIABLE__VALUE = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_VARIABLE__NAME = 2;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_VARIABLE__ANNOTATIONS = 3;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_VARIABLE__TYPE = 4;

  /**
   * The feature id for the '<em><b>Dimensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_VARIABLE__DIMENSIONS = 5;

  /**
   * The number of structural features of the '<em>Ast Variable</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_VARIABLE_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstTypeNameImpl <em>Ast Type Name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstTypeNameImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstTypeName()
   * @generated
   */
  int AST_TYPE_NAME = 14;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_TYPE_NAME__NAME = 0;

  /**
   * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_TYPE_NAME__PARAMETERS = 1;

  /**
   * The feature id for the '<em><b>Constructor</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_TYPE_NAME__CONSTRUCTOR = 2;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_TYPE_NAME__TYPE = 3;

  /**
   * The number of structural features of the '<em>Ast Type Name</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_TYPE_NAME_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstTypeDefinitionParameterImpl <em>Ast Type Definition Parameter</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstTypeDefinitionParameterImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstTypeDefinitionParameter()
   * @generated
   */
  int AST_TYPE_DEFINITION_PARAMETER = 15;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_TYPE_DEFINITION_PARAMETER__VALUE = 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_TYPE_DEFINITION_PARAMETER__TYPE = 1;

  /**
   * The number of structural features of the '<em>Ast Type Definition Parameter</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_TYPE_DEFINITION_PARAMETER_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstExternalFunctionImpl <em>Ast External Function</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstExternalFunctionImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstExternalFunction()
   * @generated
   */
  int AST_EXTERNAL_FUNCTION = 19;

  /**
   * The number of structural features of the '<em>Ast External Function</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXTERNAL_FUNCTION_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstFunctionImpl <em>Ast Function</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstFunctionImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstFunction()
   * @generated
   */
  int AST_FUNCTION = 16;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_FUNCTION__NAME = AST_EXTERNAL_FUNCTION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Members</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_FUNCTION__MEMBERS = AST_EXTERNAL_FUNCTION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_FUNCTION__ANNOTATIONS = AST_EXTERNAL_FUNCTION_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_FUNCTION__PARAMETERS = AST_EXTERNAL_FUNCTION_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_FUNCTION__TYPE = AST_EXTERNAL_FUNCTION_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Variables</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_FUNCTION__VARIABLES = AST_EXTERNAL_FUNCTION_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_FUNCTION__EXPRESSION = AST_EXTERNAL_FUNCTION_FEATURE_COUNT + 6;

  /**
   * The number of structural features of the '<em>Ast Function</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_FUNCTION_FEATURE_COUNT = AST_EXTERNAL_FUNCTION_FEATURE_COUNT + 7;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstActorImpl <em>Ast Actor</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstActorImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstActor()
   * @generated
   */
  int AST_ACTOR = 17;

  /**
   * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ACTOR__PARAMETERS = 0;

  /**
   * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ACTOR__INPUTS = 1;

  /**
   * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ACTOR__OUTPUTS = 2;

  /**
   * The feature id for the '<em><b>Functions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ACTOR__FUNCTIONS = 3;

  /**
   * The feature id for the '<em><b>Procedures</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ACTOR__PROCEDURES = 4;

  /**
   * The feature id for the '<em><b>Actions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ACTOR__ACTIONS = 5;

  /**
   * The feature id for the '<em><b>Initializes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ACTOR__INITIALIZES = 6;

  /**
   * The feature id for the '<em><b>State Variables</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ACTOR__STATE_VARIABLES = 7;

  /**
   * The feature id for the '<em><b>Schedules</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ACTOR__SCHEDULES = 8;

  /**
   * The feature id for the '<em><b>Priorities</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ACTOR__PRIORITIES = 9;

  /**
   * The number of structural features of the '<em>Ast Actor</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ACTOR_FEATURE_COUNT = 10;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstPortImpl <em>Ast Port</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstPortImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstPort()
   * @generated
   */
  int AST_PORT = 18;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_PORT__ANNOTATIONS = 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_PORT__TYPE = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_PORT__NAME = 2;

  /**
   * The number of structural features of the '<em>Ast Port</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_PORT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstExternalProcedureImpl <em>Ast External Procedure</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstExternalProcedureImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstExternalProcedure()
   * @generated
   */
  int AST_EXTERNAL_PROCEDURE = 21;

  /**
   * The number of structural features of the '<em>Ast External Procedure</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXTERNAL_PROCEDURE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstProcedureImpl <em>Ast Procedure</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstProcedureImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstProcedure()
   * @generated
   */
  int AST_PROCEDURE = 20;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_PROCEDURE__ANNOTATIONS = AST_EXTERNAL_PROCEDURE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_PROCEDURE__NAME = AST_EXTERNAL_PROCEDURE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_PROCEDURE__PARAMETERS = AST_EXTERNAL_PROCEDURE_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Variables</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_PROCEDURE__VARIABLES = AST_EXTERNAL_PROCEDURE_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_PROCEDURE__STATEMENTS = AST_EXTERNAL_PROCEDURE_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the '<em>Ast Procedure</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_PROCEDURE_FEATURE_COUNT = AST_EXTERNAL_PROCEDURE_FEATURE_COUNT + 5;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstTagImpl <em>Ast Tag</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstTagImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstTag()
   * @generated
   */
  int AST_TAG = 22;

  /**
   * The feature id for the '<em><b>Identifiers</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_TAG__IDENTIFIERS = 0;

  /**
   * The number of structural features of the '<em>Ast Tag</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_TAG_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstExternalActorImpl <em>Ast External Actor</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstExternalActorImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstExternalActor()
   * @generated
   */
  int AST_EXTERNAL_ACTOR = 23;

  /**
   * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXTERNAL_ACTOR__PARAMETERS = 0;

  /**
   * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXTERNAL_ACTOR__INPUTS = 1;

  /**
   * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXTERNAL_ACTOR__OUTPUTS = 2;

  /**
   * The number of structural features of the '<em>Ast External Actor</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXTERNAL_ACTOR_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstInequalityImpl <em>Ast Inequality</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstInequalityImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstInequality()
   * @generated
   */
  int AST_INEQUALITY = 24;

  /**
   * The feature id for the '<em><b>Tags</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_INEQUALITY__TAGS = 0;

  /**
   * The number of structural features of the '<em>Ast Inequality</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_INEQUALITY_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstPriorityImpl <em>Ast Priority</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstPriorityImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstPriority()
   * @generated
   */
  int AST_PRIORITY = 25;

  /**
   * The feature id for the '<em><b>Inequalities</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_PRIORITY__INEQUALITIES = 0;

  /**
   * The number of structural features of the '<em>Ast Priority</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_PRIORITY_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstScheduleImpl <em>Ast Schedule</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstScheduleImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstSchedule()
   * @generated
   */
  int AST_SCHEDULE = 26;

  /**
   * The feature id for the '<em><b>Initial State</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_SCHEDULE__INITIAL_STATE = 0;

  /**
   * The feature id for the '<em><b>Transitions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_SCHEDULE__TRANSITIONS = 1;

  /**
   * The number of structural features of the '<em>Ast Schedule</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_SCHEDULE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstTransitionImpl <em>Ast Transition</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstTransitionImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstTransition()
   * @generated
   */
  int AST_TRANSITION = 27;

  /**
   * The feature id for the '<em><b>Source</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_TRANSITION__SOURCE = 0;

  /**
   * The feature id for the '<em><b>Tags</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_TRANSITION__TAGS = 1;

  /**
   * The feature id for the '<em><b>Target</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_TRANSITION__TARGET = 2;

  /**
   * The number of structural features of the '<em>Ast Transition</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_TRANSITION_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstStateImpl <em>Ast State</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstStateImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstState()
   * @generated
   */
  int AST_STATE = 28;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_STATE__NAME = 0;

  /**
   * The number of structural features of the '<em>Ast State</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_STATE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstActionImpl <em>Ast Action</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstActionImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstAction()
   * @generated
   */
  int AST_ACTION = 29;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ACTION__ANNOTATIONS = 0;

  /**
   * The feature id for the '<em><b>Tag</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ACTION__TAG = 1;

  /**
   * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ACTION__INPUTS = 2;

  /**
   * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ACTION__OUTPUTS = 3;

  /**
   * The feature id for the '<em><b>Guards</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ACTION__GUARDS = 4;

  /**
   * The feature id for the '<em><b>Variables</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ACTION__VARIABLES = 5;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ACTION__STATEMENTS = 6;

  /**
   * The number of structural features of the '<em>Ast Action</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ACTION_FEATURE_COUNT = 7;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstInputPatternImpl <em>Ast Input Pattern</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstInputPatternImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstInputPattern()
   * @generated
   */
  int AST_INPUT_PATTERN = 30;

  /**
   * The feature id for the '<em><b>Port</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_INPUT_PATTERN__PORT = 0;

  /**
   * The feature id for the '<em><b>Tokens</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_INPUT_PATTERN__TOKENS = 1;

  /**
   * The feature id for the '<em><b>Repeat</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_INPUT_PATTERN__REPEAT = 2;

  /**
   * The number of structural features of the '<em>Ast Input Pattern</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_INPUT_PATTERN_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstOutputPatternImpl <em>Ast Output Pattern</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstOutputPatternImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstOutputPattern()
   * @generated
   */
  int AST_OUTPUT_PATTERN = 31;

  /**
   * The feature id for the '<em><b>Port</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_OUTPUT_PATTERN__PORT = 0;

  /**
   * The feature id for the '<em><b>Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_OUTPUT_PATTERN__VALUES = 1;

  /**
   * The feature id for the '<em><b>Repeat</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_OUTPUT_PATTERN__REPEAT = 2;

  /**
   * The number of structural features of the '<em>Ast Output Pattern</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_OUTPUT_PATTERN_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstStatementImpl <em>Ast Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstStatementImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstStatement()
   * @generated
   */
  int AST_STATEMENT = 39;

  /**
   * The number of structural features of the '<em>Ast Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_STATEMENT_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstStatementAssignImpl <em>Ast Statement Assign</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstStatementAssignImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstStatementAssign()
   * @generated
   */
  int AST_STATEMENT_ASSIGN = 32;

  /**
   * The feature id for the '<em><b>Target</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_STATEMENT_ASSIGN__TARGET = AST_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Indexes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_STATEMENT_ASSIGN__INDEXES = AST_STATEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Member</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_STATEMENT_ASSIGN__MEMBER = AST_STATEMENT_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_STATEMENT_ASSIGN__VALUE = AST_STATEMENT_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Ast Statement Assign</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_STATEMENT_ASSIGN_FEATURE_COUNT = AST_STATEMENT_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstStatementCallImpl <em>Ast Statement Call</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstStatementCallImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstStatementCall()
   * @generated
   */
  int AST_STATEMENT_CALL = 33;

  /**
   * The feature id for the '<em><b>Procedure</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_STATEMENT_CALL__PROCEDURE = AST_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_STATEMENT_CALL__PARAMETERS = AST_STATEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Ast Statement Call</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_STATEMENT_CALL_FEATURE_COUNT = AST_STATEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstStatementForeachImpl <em>Ast Statement Foreach</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstStatementForeachImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstStatementForeach()
   * @generated
   */
  int AST_STATEMENT_FOREACH = 34;

  /**
   * The feature id for the '<em><b>Generators</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_STATEMENT_FOREACH__GENERATORS = AST_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Variables</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_STATEMENT_FOREACH__VARIABLES = AST_STATEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_STATEMENT_FOREACH__STATEMENTS = AST_STATEMENT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Ast Statement Foreach</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_STATEMENT_FOREACH_FEATURE_COUNT = AST_STATEMENT_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstForeachGeneratorImpl <em>Ast Foreach Generator</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstForeachGeneratorImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstForeachGenerator()
   * @generated
   */
  int AST_FOREACH_GENERATOR = 35;

  /**
   * The feature id for the '<em><b>Variable</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_FOREACH_GENERATOR__VARIABLE = 0;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_FOREACH_GENERATOR__EXPRESSION = 1;

  /**
   * The number of structural features of the '<em>Ast Foreach Generator</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_FOREACH_GENERATOR_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstStatementBlockImpl <em>Ast Statement Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstStatementBlockImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstStatementBlock()
   * @generated
   */
  int AST_STATEMENT_BLOCK = 36;

  /**
   * The feature id for the '<em><b>Variables</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_STATEMENT_BLOCK__VARIABLES = AST_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_STATEMENT_BLOCK__STATEMENTS = AST_STATEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Ast Statement Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_STATEMENT_BLOCK_FEATURE_COUNT = AST_STATEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstStatementIfImpl <em>Ast Statement If</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstStatementIfImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstStatementIf()
   * @generated
   */
  int AST_STATEMENT_IF = 37;

  /**
   * The feature id for the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_STATEMENT_IF__CONDITION = AST_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Then</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_STATEMENT_IF__THEN = AST_STATEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Else</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_STATEMENT_IF__ELSE = AST_STATEMENT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Ast Statement If</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_STATEMENT_IF_FEATURE_COUNT = AST_STATEMENT_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstStatementWhileImpl <em>Ast Statement While</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstStatementWhileImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstStatementWhile()
   * @generated
   */
  int AST_STATEMENT_WHILE = 38;

  /**
   * The feature id for the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_STATEMENT_WHILE__CONDITION = AST_STATEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_STATEMENT_WHILE__STATEMENTS = AST_STATEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Ast Statement While</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_STATEMENT_WHILE_FEATURE_COUNT = AST_STATEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstExpressionImpl <em>Ast Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstExpressionImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstExpression()
   * @generated
   */
  int AST_EXPRESSION = 40;

  /**
   * The number of structural features of the '<em>Ast Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXPRESSION_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstExpressionCallImpl <em>Ast Expression Call</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstExpressionCallImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstExpressionCall()
   * @generated
   */
  int AST_EXPRESSION_CALL = 41;

  /**
   * The feature id for the '<em><b>Function</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXPRESSION_CALL__FUNCTION = AST_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXPRESSION_CALL__PARAMETERS = AST_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Ast Expression Call</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXPRESSION_CALL_FEATURE_COUNT = AST_EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstExpressionIfImpl <em>Ast Expression If</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstExpressionIfImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstExpressionIf()
   * @generated
   */
  int AST_EXPRESSION_IF = 42;

  /**
   * The feature id for the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXPRESSION_IF__CONDITION = AST_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Then</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXPRESSION_IF__THEN = AST_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Else</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXPRESSION_IF__ELSE = AST_EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Ast Expression If</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXPRESSION_IF_FEATURE_COUNT = AST_EXPRESSION_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstExpressionListImpl <em>Ast Expression List</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstExpressionListImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstExpressionList()
   * @generated
   */
  int AST_EXPRESSION_LIST = 43;

  /**
   * The feature id for the '<em><b>Expressions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXPRESSION_LIST__EXPRESSIONS = AST_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Generators</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXPRESSION_LIST__GENERATORS = AST_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Ast Expression List</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXPRESSION_LIST_FEATURE_COUNT = AST_EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstGeneratorImpl <em>Ast Generator</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstGeneratorImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstGenerator()
   * @generated
   */
  int AST_GENERATOR = 44;

  /**
   * The feature id for the '<em><b>Variable</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_GENERATOR__VARIABLE = 0;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_GENERATOR__EXPRESSION = 1;

  /**
   * The number of structural features of the '<em>Ast Generator</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_GENERATOR_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstExpressionVariableImpl <em>Ast Expression Variable</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstExpressionVariableImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstExpressionVariable()
   * @generated
   */
  int AST_EXPRESSION_VARIABLE = 45;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXPRESSION_VARIABLE__VALUE = AST_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Indexes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXPRESSION_VARIABLE__INDEXES = AST_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Member</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXPRESSION_VARIABLE__MEMBER = AST_EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Ast Expression Variable</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXPRESSION_VARIABLE_FEATURE_COUNT = AST_EXPRESSION_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstExpressionLiteralImpl <em>Ast Expression Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstExpressionLiteralImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstExpressionLiteral()
   * @generated
   */
  int AST_EXPRESSION_LITERAL = 46;

  /**
   * The number of structural features of the '<em>Ast Expression Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXPRESSION_LITERAL_FEATURE_COUNT = AST_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstExpressionBooleanImpl <em>Ast Expression Boolean</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstExpressionBooleanImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstExpressionBoolean()
   * @generated
   */
  int AST_EXPRESSION_BOOLEAN = 47;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXPRESSION_BOOLEAN__VALUE = AST_EXPRESSION_LITERAL_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Ast Expression Boolean</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXPRESSION_BOOLEAN_FEATURE_COUNT = AST_EXPRESSION_LITERAL_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstExpressionFloatImpl <em>Ast Expression Float</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstExpressionFloatImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstExpressionFloat()
   * @generated
   */
  int AST_EXPRESSION_FLOAT = 48;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXPRESSION_FLOAT__VALUE = AST_EXPRESSION_LITERAL_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Ast Expression Float</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXPRESSION_FLOAT_FEATURE_COUNT = AST_EXPRESSION_LITERAL_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstExpressionIntegerImpl <em>Ast Expression Integer</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstExpressionIntegerImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstExpressionInteger()
   * @generated
   */
  int AST_EXPRESSION_INTEGER = 49;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXPRESSION_INTEGER__VALUE = AST_EXPRESSION_LITERAL_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Ast Expression Integer</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXPRESSION_INTEGER_FEATURE_COUNT = AST_EXPRESSION_LITERAL_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstExpressionStringImpl <em>Ast Expression String</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstExpressionStringImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstExpressionString()
   * @generated
   */
  int AST_EXPRESSION_STRING = 50;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXPRESSION_STRING__VALUE = AST_EXPRESSION_LITERAL_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Ast Expression String</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXPRESSION_STRING_FEATURE_COUNT = AST_EXPRESSION_LITERAL_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstTypeImpl <em>Ast Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstTypeImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstType()
   * @generated
   */
  int AST_TYPE = 51;

  /**
   * The feature id for the '<em><b>Builtin</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_TYPE__BUILTIN = 0;

  /**
   * The feature id for the '<em><b>Type Params</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_TYPE__TYPE_PARAMS = 1;

  /**
   * The feature id for the '<em><b>Dimensions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_TYPE__DIMENSIONS = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_TYPE__NAME = 3;

  /**
   * The feature id for the '<em><b>Domain</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_TYPE__DOMAIN = 4;

  /**
   * The feature id for the '<em><b>Codomain</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_TYPE__CODOMAIN = 5;

  /**
   * The feature id for the '<em><b>Members</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_TYPE__MEMBERS = 6;

  /**
   * The number of structural features of the '<em>Ast Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_TYPE_FEATURE_COUNT = 7;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstTypeParameterListImpl <em>Ast Type Parameter List</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstTypeParameterListImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstTypeParameterList()
   * @generated
   */
  int AST_TYPE_PARAMETER_LIST = 52;

  /**
   * The feature id for the '<em><b>Params</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_TYPE_PARAMETER_LIST__PARAMS = 0;

  /**
   * The number of structural features of the '<em>Ast Type Parameter List</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_TYPE_PARAMETER_LIST_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstTypeParamImpl <em>Ast Type Param</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstTypeParamImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstTypeParam()
   * @generated
   */
  int AST_TYPE_PARAM = 53;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_TYPE_PARAM__NAME = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_TYPE_PARAM__VALUE = 1;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_TYPE_PARAM__TYPE = 2;

  /**
   * The number of structural features of the '<em>Ast Type Param</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_TYPE_PARAM_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstVariableReferenceImpl <em>Ast Variable Reference</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstVariableReferenceImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstVariableReference()
   * @generated
   */
  int AST_VARIABLE_REFERENCE = 54;

  /**
   * The feature id for the '<em><b>Variable</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_VARIABLE_REFERENCE__VARIABLE = 0;

  /**
   * The number of structural features of the '<em>Ast Variable Reference</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_VARIABLE_REFERENCE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstMemberAccessImpl <em>Ast Member Access</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstMemberAccessImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstMemberAccess()
   * @generated
   */
  int AST_MEMBER_ACCESS = 55;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_MEMBER_ACCESS__NAME = 0;

  /**
   * The feature id for the '<em><b>Member Index</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_MEMBER_ACCESS__MEMBER_INDEX = 1;

  /**
   * The number of structural features of the '<em>Ast Member Access</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_MEMBER_ACCESS_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstAnnotationImpl <em>Ast Annotation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstAnnotationImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstAnnotation()
   * @generated
   */
  int AST_ANNOTATION = 56;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ANNOTATION__NAME = 0;

  /**
   * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ANNOTATION__ARGUMENTS = 1;

  /**
   * The number of structural features of the '<em>Ast Annotation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ANNOTATION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstAnnotationArgumentImpl <em>Ast Annotation Argument</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstAnnotationArgumentImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstAnnotationArgument()
   * @generated
   */
  int AST_ANNOTATION_ARGUMENT = 57;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ANNOTATION_ARGUMENT__NAME = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ANNOTATION_ARGUMENT__VALUE = 1;

  /**
   * The number of structural features of the '<em>Ast Annotation Argument</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_ANNOTATION_ARGUMENT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstInitializeImpl <em>Ast Initialize</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstInitializeImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstInitialize()
   * @generated
   */
  int AST_INITIALIZE = 58;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_INITIALIZE__ANNOTATIONS = AST_ACTION__ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Tag</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_INITIALIZE__TAG = AST_ACTION__TAG;

  /**
   * The feature id for the '<em><b>Inputs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_INITIALIZE__INPUTS = AST_ACTION__INPUTS;

  /**
   * The feature id for the '<em><b>Outputs</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_INITIALIZE__OUTPUTS = AST_ACTION__OUTPUTS;

  /**
   * The feature id for the '<em><b>Guards</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_INITIALIZE__GUARDS = AST_ACTION__GUARDS;

  /**
   * The feature id for the '<em><b>Variables</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_INITIALIZE__VARIABLES = AST_ACTION__VARIABLES;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_INITIALIZE__STATEMENTS = AST_ACTION__STATEMENTS;

  /**
   * The number of structural features of the '<em>Ast Initialize</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_INITIALIZE_FEATURE_COUNT = AST_ACTION_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstExpressionBinaryImpl <em>Ast Expression Binary</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstExpressionBinaryImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstExpressionBinary()
   * @generated
   */
  int AST_EXPRESSION_BINARY = 59;

  /**
   * The feature id for the '<em><b>Left</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXPRESSION_BINARY__LEFT = AST_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Operator</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXPRESSION_BINARY__OPERATOR = AST_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Right</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXPRESSION_BINARY__RIGHT = AST_EXPRESSION_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Ast Expression Binary</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXPRESSION_BINARY_FEATURE_COUNT = AST_EXPRESSION_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.caltoopia.frontend.cal.impl.AstExpressionUnaryImpl <em>Ast Expression Unary</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.caltoopia.frontend.cal.impl.AstExpressionUnaryImpl
   * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstExpressionUnary()
   * @generated
   */
  int AST_EXPRESSION_UNARY = 60;

  /**
   * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXPRESSION_UNARY__UNARY_OPERATOR = AST_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXPRESSION_UNARY__EXPRESSION = AST_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Ast Expression Unary</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AST_EXPRESSION_UNARY_FEATURE_COUNT = AST_EXPRESSION_FEATURE_COUNT + 2;


  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstTop <em>Ast Top</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Top</em>'.
   * @see org.caltoopia.frontend.cal.AstTop
   * @generated
   */
  EClass getAstTop();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstPackage <em>Ast Package</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Package</em>'.
   * @see org.caltoopia.frontend.cal.AstPackage
   * @generated
   */
  EClass getAstPackage();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstUnit <em>Ast Unit</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Unit</em>'.
   * @see org.caltoopia.frontend.cal.AstUnit
   * @generated
   */
  EClass getAstUnit();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstNamespace <em>Ast Namespace</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Namespace</em>'.
   * @see org.caltoopia.frontend.cal.AstNamespace
   * @generated
   */
  EClass getAstNamespace();

  /**
   * Returns the meta object for the attribute '{@link org.caltoopia.frontend.cal.AstNamespace#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.caltoopia.frontend.cal.AstNamespace#getName()
   * @see #getAstNamespace()
   * @generated
   */
  EAttribute getAstNamespace_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstNamespace#getEntities <em>Entities</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Entities</em>'.
   * @see org.caltoopia.frontend.cal.AstNamespace#getEntities()
   * @see #getAstNamespace()
   * @generated
   */
  EReference getAstNamespace_Entities();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstNamespace#getImports <em>Imports</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Imports</em>'.
   * @see org.caltoopia.frontend.cal.AstNamespace#getImports()
   * @see #getAstNamespace()
   * @generated
   */
  EReference getAstNamespace_Imports();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstNamespace#getUnits <em>Units</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Units</em>'.
   * @see org.caltoopia.frontend.cal.AstNamespace#getUnits()
   * @see #getAstNamespace()
   * @generated
   */
  EReference getAstNamespace_Units();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstNamespace#getFunctions <em>Functions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Functions</em>'.
   * @see org.caltoopia.frontend.cal.AstNamespace#getFunctions()
   * @see #getAstNamespace()
   * @generated
   */
  EReference getAstNamespace_Functions();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstNamespace#getVariables <em>Variables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Variables</em>'.
   * @see org.caltoopia.frontend.cal.AstNamespace#getVariables()
   * @see #getAstNamespace()
   * @generated
   */
  EReference getAstNamespace_Variables();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstNamespace#getExternals <em>Externals</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Externals</em>'.
   * @see org.caltoopia.frontend.cal.AstNamespace#getExternals()
   * @see #getAstNamespace()
   * @generated
   */
  EReference getAstNamespace_Externals();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstNamespace#getAnnotations <em>Annotations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Annotations</em>'.
   * @see org.caltoopia.frontend.cal.AstNamespace#getAnnotations()
   * @see #getAstNamespace()
   * @generated
   */
  EReference getAstNamespace_Annotations();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstNamespace#getTypedefs <em>Typedefs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Typedefs</em>'.
   * @see org.caltoopia.frontend.cal.AstNamespace#getTypedefs()
   * @see #getAstNamespace()
   * @generated
   */
  EReference getAstNamespace_Typedefs();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstNamespace#getNamespaces <em>Namespaces</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Namespaces</em>'.
   * @see org.caltoopia.frontend.cal.AstNamespace#getNamespaces()
   * @see #getAstNamespace()
   * @generated
   */
  EReference getAstNamespace_Namespaces();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstEntity <em>Ast Entity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Entity</em>'.
   * @see org.caltoopia.frontend.cal.AstEntity
   * @generated
   */
  EClass getAstEntity();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstEntity#getAnnotations <em>Annotations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Annotations</em>'.
   * @see org.caltoopia.frontend.cal.AstEntity#getAnnotations()
   * @see #getAstEntity()
   * @generated
   */
  EReference getAstEntity_Annotations();

  /**
   * Returns the meta object for the attribute '{@link org.caltoopia.frontend.cal.AstEntity#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.caltoopia.frontend.cal.AstEntity#getName()
   * @see #getAstEntity()
   * @generated
   */
  EAttribute getAstEntity_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstEntity#getActor <em>Actor</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Actor</em>'.
   * @see org.caltoopia.frontend.cal.AstEntity#getActor()
   * @see #getAstEntity()
   * @generated
   */
  EReference getAstEntity_Actor();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstEntity#getNetwork <em>Network</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Network</em>'.
   * @see org.caltoopia.frontend.cal.AstEntity#getNetwork()
   * @see #getAstEntity()
   * @generated
   */
  EReference getAstEntity_Network();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstEntity#getExternal <em>External</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>External</em>'.
   * @see org.caltoopia.frontend.cal.AstEntity#getExternal()
   * @see #getAstEntity()
   * @generated
   */
  EReference getAstEntity_External();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.Import <em>Import</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Import</em>'.
   * @see org.caltoopia.frontend.cal.Import
   * @generated
   */
  EClass getImport();

  /**
   * Returns the meta object for the attribute '{@link org.caltoopia.frontend.cal.Import#getImportedNamespace <em>Imported Namespace</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Imported Namespace</em>'.
   * @see org.caltoopia.frontend.cal.Import#getImportedNamespace()
   * @see #getImport()
   * @generated
   */
  EAttribute getImport_ImportedNamespace();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstNetwork <em>Ast Network</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Network</em>'.
   * @see org.caltoopia.frontend.cal.AstNetwork
   * @generated
   */
  EClass getAstNetwork();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstNetwork#getParameters <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parameters</em>'.
   * @see org.caltoopia.frontend.cal.AstNetwork#getParameters()
   * @see #getAstNetwork()
   * @generated
   */
  EReference getAstNetwork_Parameters();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstNetwork#getInputs <em>Inputs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Inputs</em>'.
   * @see org.caltoopia.frontend.cal.AstNetwork#getInputs()
   * @see #getAstNetwork()
   * @generated
   */
  EReference getAstNetwork_Inputs();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstNetwork#getOutputs <em>Outputs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Outputs</em>'.
   * @see org.caltoopia.frontend.cal.AstNetwork#getOutputs()
   * @see #getAstNetwork()
   * @generated
   */
  EReference getAstNetwork_Outputs();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstNetwork#getVariables <em>Variables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Variables</em>'.
   * @see org.caltoopia.frontend.cal.AstNetwork#getVariables()
   * @see #getAstNetwork()
   * @generated
   */
  EReference getAstNetwork_Variables();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstNetwork#getInstances <em>Instances</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Instances</em>'.
   * @see org.caltoopia.frontend.cal.AstNetwork#getInstances()
   * @see #getAstNetwork()
   * @generated
   */
  EReference getAstNetwork_Instances();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstNetwork#getStructure <em>Structure</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Structure</em>'.
   * @see org.caltoopia.frontend.cal.AstNetwork#getStructure()
   * @see #getAstNetwork()
   * @generated
   */
  EReference getAstNetwork_Structure();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstActorVariable <em>Ast Actor Variable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Actor Variable</em>'.
   * @see org.caltoopia.frontend.cal.AstActorVariable
   * @generated
   */
  EClass getAstActorVariable();

  /**
   * Returns the meta object for the attribute '{@link org.caltoopia.frontend.cal.AstActorVariable#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.caltoopia.frontend.cal.AstActorVariable#getName()
   * @see #getAstActorVariable()
   * @generated
   */
  EAttribute getAstActorVariable_Name();

  /**
   * Returns the meta object for the reference '{@link org.caltoopia.frontend.cal.AstActorVariable#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type</em>'.
   * @see org.caltoopia.frontend.cal.AstActorVariable#getType()
   * @see #getAstActorVariable()
   * @generated
   */
  EReference getAstActorVariable_Type();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstActorVariable#getParameters <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parameters</em>'.
   * @see org.caltoopia.frontend.cal.AstActorVariable#getParameters()
   * @see #getAstActorVariable()
   * @generated
   */
  EReference getAstActorVariable_Parameters();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstAssignParameter <em>Ast Assign Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Assign Parameter</em>'.
   * @see org.caltoopia.frontend.cal.AstAssignParameter
   * @generated
   */
  EClass getAstAssignParameter();

  /**
   * Returns the meta object for the attribute '{@link org.caltoopia.frontend.cal.AstAssignParameter#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.caltoopia.frontend.cal.AstAssignParameter#getName()
   * @see #getAstAssignParameter()
   * @generated
   */
  EAttribute getAstAssignParameter_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstAssignParameter#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see org.caltoopia.frontend.cal.AstAssignParameter#getValue()
   * @see #getAstAssignParameter()
   * @generated
   */
  EReference getAstAssignParameter_Value();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstStructure <em>Ast Structure</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Structure</em>'.
   * @see org.caltoopia.frontend.cal.AstStructure
   * @generated
   */
  EClass getAstStructure();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstStructure#getConnections <em>Connections</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Connections</em>'.
   * @see org.caltoopia.frontend.cal.AstStructure#getConnections()
   * @see #getAstStructure()
   * @generated
   */
  EReference getAstStructure_Connections();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstConnection <em>Ast Connection</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Connection</em>'.
   * @see org.caltoopia.frontend.cal.AstConnection
   * @generated
   */
  EClass getAstConnection();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstConnection#getFrom <em>From</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>From</em>'.
   * @see org.caltoopia.frontend.cal.AstConnection#getFrom()
   * @see #getAstConnection()
   * @generated
   */
  EReference getAstConnection_From();

  /**
   * Returns the meta object for the attribute '{@link org.caltoopia.frontend.cal.AstConnection#getOutPort <em>Out Port</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Out Port</em>'.
   * @see org.caltoopia.frontend.cal.AstConnection#getOutPort()
   * @see #getAstConnection()
   * @generated
   */
  EAttribute getAstConnection_OutPort();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstConnection#getTo <em>To</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>To</em>'.
   * @see org.caltoopia.frontend.cal.AstConnection#getTo()
   * @see #getAstConnection()
   * @generated
   */
  EReference getAstConnection_To();

  /**
   * Returns the meta object for the attribute '{@link org.caltoopia.frontend.cal.AstConnection#getInPort <em>In Port</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>In Port</em>'.
   * @see org.caltoopia.frontend.cal.AstConnection#getInPort()
   * @see #getAstConnection()
   * @generated
   */
  EAttribute getAstConnection_InPort();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstConnection#getAttribute <em>Attribute</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Attribute</em>'.
   * @see org.caltoopia.frontend.cal.AstConnection#getAttribute()
   * @see #getAstConnection()
   * @generated
   */
  EReference getAstConnection_Attribute();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstActorVariableReference <em>Ast Actor Variable Reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Actor Variable Reference</em>'.
   * @see org.caltoopia.frontend.cal.AstActorVariableReference
   * @generated
   */
  EClass getAstActorVariableReference();

  /**
   * Returns the meta object for the reference '{@link org.caltoopia.frontend.cal.AstActorVariableReference#getVariable <em>Variable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Variable</em>'.
   * @see org.caltoopia.frontend.cal.AstActorVariableReference#getVariable()
   * @see #getAstActorVariableReference()
   * @generated
   */
  EReference getAstActorVariableReference_Variable();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstConnectionAttribute <em>Ast Connection Attribute</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Connection Attribute</em>'.
   * @see org.caltoopia.frontend.cal.AstConnectionAttribute
   * @generated
   */
  EClass getAstConnectionAttribute();

  /**
   * Returns the meta object for the attribute '{@link org.caltoopia.frontend.cal.AstConnectionAttribute#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.caltoopia.frontend.cal.AstConnectionAttribute#getName()
   * @see #getAstConnectionAttribute()
   * @generated
   */
  EAttribute getAstConnectionAttribute_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstConnectionAttribute#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see org.caltoopia.frontend.cal.AstConnectionAttribute#getValue()
   * @see #getAstConnectionAttribute()
   * @generated
   */
  EReference getAstConnectionAttribute_Value();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstVariable <em>Ast Variable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Variable</em>'.
   * @see org.caltoopia.frontend.cal.AstVariable
   * @generated
   */
  EClass getAstVariable();

  /**
   * Returns the meta object for the attribute '{@link org.caltoopia.frontend.cal.AstVariable#isConstant <em>Constant</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Constant</em>'.
   * @see org.caltoopia.frontend.cal.AstVariable#isConstant()
   * @see #getAstVariable()
   * @generated
   */
  EAttribute getAstVariable_Constant();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstVariable#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see org.caltoopia.frontend.cal.AstVariable#getValue()
   * @see #getAstVariable()
   * @generated
   */
  EReference getAstVariable_Value();

  /**
   * Returns the meta object for the attribute '{@link org.caltoopia.frontend.cal.AstVariable#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.caltoopia.frontend.cal.AstVariable#getName()
   * @see #getAstVariable()
   * @generated
   */
  EAttribute getAstVariable_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstVariable#getAnnotations <em>Annotations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Annotations</em>'.
   * @see org.caltoopia.frontend.cal.AstVariable#getAnnotations()
   * @see #getAstVariable()
   * @generated
   */
  EReference getAstVariable_Annotations();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstVariable#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see org.caltoopia.frontend.cal.AstVariable#getType()
   * @see #getAstVariable()
   * @generated
   */
  EReference getAstVariable_Type();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstVariable#getDimensions <em>Dimensions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Dimensions</em>'.
   * @see org.caltoopia.frontend.cal.AstVariable#getDimensions()
   * @see #getAstVariable()
   * @generated
   */
  EReference getAstVariable_Dimensions();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstTypeName <em>Ast Type Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Type Name</em>'.
   * @see org.caltoopia.frontend.cal.AstTypeName
   * @generated
   */
  EClass getAstTypeName();

  /**
   * Returns the meta object for the attribute '{@link org.caltoopia.frontend.cal.AstTypeName#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.caltoopia.frontend.cal.AstTypeName#getName()
   * @see #getAstTypeName()
   * @generated
   */
  EAttribute getAstTypeName_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstTypeName#getParameters <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parameters</em>'.
   * @see org.caltoopia.frontend.cal.AstTypeName#getParameters()
   * @see #getAstTypeName()
   * @generated
   */
  EReference getAstTypeName_Parameters();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstTypeName#getConstructor <em>Constructor</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Constructor</em>'.
   * @see org.caltoopia.frontend.cal.AstTypeName#getConstructor()
   * @see #getAstTypeName()
   * @generated
   */
  EReference getAstTypeName_Constructor();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstTypeName#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see org.caltoopia.frontend.cal.AstTypeName#getType()
   * @see #getAstTypeName()
   * @generated
   */
  EReference getAstTypeName_Type();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstTypeDefinitionParameter <em>Ast Type Definition Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Type Definition Parameter</em>'.
   * @see org.caltoopia.frontend.cal.AstTypeDefinitionParameter
   * @generated
   */
  EClass getAstTypeDefinitionParameter();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstTypeDefinitionParameter#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see org.caltoopia.frontend.cal.AstTypeDefinitionParameter#getValue()
   * @see #getAstTypeDefinitionParameter()
   * @generated
   */
  EReference getAstTypeDefinitionParameter_Value();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstTypeDefinitionParameter#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see org.caltoopia.frontend.cal.AstTypeDefinitionParameter#getType()
   * @see #getAstTypeDefinitionParameter()
   * @generated
   */
  EReference getAstTypeDefinitionParameter_Type();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstFunction <em>Ast Function</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Function</em>'.
   * @see org.caltoopia.frontend.cal.AstFunction
   * @generated
   */
  EClass getAstFunction();

  /**
   * Returns the meta object for the attribute '{@link org.caltoopia.frontend.cal.AstFunction#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.caltoopia.frontend.cal.AstFunction#getName()
   * @see #getAstFunction()
   * @generated
   */
  EAttribute getAstFunction_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstFunction#getMembers <em>Members</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Members</em>'.
   * @see org.caltoopia.frontend.cal.AstFunction#getMembers()
   * @see #getAstFunction()
   * @generated
   */
  EReference getAstFunction_Members();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstFunction#getAnnotations <em>Annotations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Annotations</em>'.
   * @see org.caltoopia.frontend.cal.AstFunction#getAnnotations()
   * @see #getAstFunction()
   * @generated
   */
  EReference getAstFunction_Annotations();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstFunction#getParameters <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parameters</em>'.
   * @see org.caltoopia.frontend.cal.AstFunction#getParameters()
   * @see #getAstFunction()
   * @generated
   */
  EReference getAstFunction_Parameters();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstFunction#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see org.caltoopia.frontend.cal.AstFunction#getType()
   * @see #getAstFunction()
   * @generated
   */
  EReference getAstFunction_Type();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstFunction#getVariables <em>Variables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Variables</em>'.
   * @see org.caltoopia.frontend.cal.AstFunction#getVariables()
   * @see #getAstFunction()
   * @generated
   */
  EReference getAstFunction_Variables();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstFunction#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression</em>'.
   * @see org.caltoopia.frontend.cal.AstFunction#getExpression()
   * @see #getAstFunction()
   * @generated
   */
  EReference getAstFunction_Expression();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstActor <em>Ast Actor</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Actor</em>'.
   * @see org.caltoopia.frontend.cal.AstActor
   * @generated
   */
  EClass getAstActor();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstActor#getParameters <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parameters</em>'.
   * @see org.caltoopia.frontend.cal.AstActor#getParameters()
   * @see #getAstActor()
   * @generated
   */
  EReference getAstActor_Parameters();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstActor#getInputs <em>Inputs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Inputs</em>'.
   * @see org.caltoopia.frontend.cal.AstActor#getInputs()
   * @see #getAstActor()
   * @generated
   */
  EReference getAstActor_Inputs();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstActor#getOutputs <em>Outputs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Outputs</em>'.
   * @see org.caltoopia.frontend.cal.AstActor#getOutputs()
   * @see #getAstActor()
   * @generated
   */
  EReference getAstActor_Outputs();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstActor#getFunctions <em>Functions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Functions</em>'.
   * @see org.caltoopia.frontend.cal.AstActor#getFunctions()
   * @see #getAstActor()
   * @generated
   */
  EReference getAstActor_Functions();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstActor#getProcedures <em>Procedures</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Procedures</em>'.
   * @see org.caltoopia.frontend.cal.AstActor#getProcedures()
   * @see #getAstActor()
   * @generated
   */
  EReference getAstActor_Procedures();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstActor#getActions <em>Actions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Actions</em>'.
   * @see org.caltoopia.frontend.cal.AstActor#getActions()
   * @see #getAstActor()
   * @generated
   */
  EReference getAstActor_Actions();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstActor#getInitializes <em>Initializes</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Initializes</em>'.
   * @see org.caltoopia.frontend.cal.AstActor#getInitializes()
   * @see #getAstActor()
   * @generated
   */
  EReference getAstActor_Initializes();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstActor#getStateVariables <em>State Variables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>State Variables</em>'.
   * @see org.caltoopia.frontend.cal.AstActor#getStateVariables()
   * @see #getAstActor()
   * @generated
   */
  EReference getAstActor_StateVariables();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstActor#getSchedules <em>Schedules</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Schedules</em>'.
   * @see org.caltoopia.frontend.cal.AstActor#getSchedules()
   * @see #getAstActor()
   * @generated
   */
  EReference getAstActor_Schedules();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstActor#getPriorities <em>Priorities</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Priorities</em>'.
   * @see org.caltoopia.frontend.cal.AstActor#getPriorities()
   * @see #getAstActor()
   * @generated
   */
  EReference getAstActor_Priorities();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstPort <em>Ast Port</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Port</em>'.
   * @see org.caltoopia.frontend.cal.AstPort
   * @generated
   */
  EClass getAstPort();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstPort#getAnnotations <em>Annotations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Annotations</em>'.
   * @see org.caltoopia.frontend.cal.AstPort#getAnnotations()
   * @see #getAstPort()
   * @generated
   */
  EReference getAstPort_Annotations();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstPort#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see org.caltoopia.frontend.cal.AstPort#getType()
   * @see #getAstPort()
   * @generated
   */
  EReference getAstPort_Type();

  /**
   * Returns the meta object for the attribute '{@link org.caltoopia.frontend.cal.AstPort#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.caltoopia.frontend.cal.AstPort#getName()
   * @see #getAstPort()
   * @generated
   */
  EAttribute getAstPort_Name();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstExternalFunction <em>Ast External Function</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast External Function</em>'.
   * @see org.caltoopia.frontend.cal.AstExternalFunction
   * @generated
   */
  EClass getAstExternalFunction();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstProcedure <em>Ast Procedure</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Procedure</em>'.
   * @see org.caltoopia.frontend.cal.AstProcedure
   * @generated
   */
  EClass getAstProcedure();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstProcedure#getAnnotations <em>Annotations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Annotations</em>'.
   * @see org.caltoopia.frontend.cal.AstProcedure#getAnnotations()
   * @see #getAstProcedure()
   * @generated
   */
  EReference getAstProcedure_Annotations();

  /**
   * Returns the meta object for the attribute '{@link org.caltoopia.frontend.cal.AstProcedure#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.caltoopia.frontend.cal.AstProcedure#getName()
   * @see #getAstProcedure()
   * @generated
   */
  EAttribute getAstProcedure_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstProcedure#getParameters <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parameters</em>'.
   * @see org.caltoopia.frontend.cal.AstProcedure#getParameters()
   * @see #getAstProcedure()
   * @generated
   */
  EReference getAstProcedure_Parameters();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstProcedure#getVariables <em>Variables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Variables</em>'.
   * @see org.caltoopia.frontend.cal.AstProcedure#getVariables()
   * @see #getAstProcedure()
   * @generated
   */
  EReference getAstProcedure_Variables();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstProcedure#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Statements</em>'.
   * @see org.caltoopia.frontend.cal.AstProcedure#getStatements()
   * @see #getAstProcedure()
   * @generated
   */
  EReference getAstProcedure_Statements();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstExternalProcedure <em>Ast External Procedure</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast External Procedure</em>'.
   * @see org.caltoopia.frontend.cal.AstExternalProcedure
   * @generated
   */
  EClass getAstExternalProcedure();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstTag <em>Ast Tag</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Tag</em>'.
   * @see org.caltoopia.frontend.cal.AstTag
   * @generated
   */
  EClass getAstTag();

  /**
   * Returns the meta object for the attribute list '{@link org.caltoopia.frontend.cal.AstTag#getIdentifiers <em>Identifiers</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Identifiers</em>'.
   * @see org.caltoopia.frontend.cal.AstTag#getIdentifiers()
   * @see #getAstTag()
   * @generated
   */
  EAttribute getAstTag_Identifiers();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstExternalActor <em>Ast External Actor</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast External Actor</em>'.
   * @see org.caltoopia.frontend.cal.AstExternalActor
   * @generated
   */
  EClass getAstExternalActor();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstExternalActor#getParameters <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parameters</em>'.
   * @see org.caltoopia.frontend.cal.AstExternalActor#getParameters()
   * @see #getAstExternalActor()
   * @generated
   */
  EReference getAstExternalActor_Parameters();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstExternalActor#getInputs <em>Inputs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Inputs</em>'.
   * @see org.caltoopia.frontend.cal.AstExternalActor#getInputs()
   * @see #getAstExternalActor()
   * @generated
   */
  EReference getAstExternalActor_Inputs();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstExternalActor#getOutputs <em>Outputs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Outputs</em>'.
   * @see org.caltoopia.frontend.cal.AstExternalActor#getOutputs()
   * @see #getAstExternalActor()
   * @generated
   */
  EReference getAstExternalActor_Outputs();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstInequality <em>Ast Inequality</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Inequality</em>'.
   * @see org.caltoopia.frontend.cal.AstInequality
   * @generated
   */
  EClass getAstInequality();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstInequality#getTags <em>Tags</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Tags</em>'.
   * @see org.caltoopia.frontend.cal.AstInequality#getTags()
   * @see #getAstInequality()
   * @generated
   */
  EReference getAstInequality_Tags();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstPriority <em>Ast Priority</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Priority</em>'.
   * @see org.caltoopia.frontend.cal.AstPriority
   * @generated
   */
  EClass getAstPriority();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstPriority#getInequalities <em>Inequalities</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Inequalities</em>'.
   * @see org.caltoopia.frontend.cal.AstPriority#getInequalities()
   * @see #getAstPriority()
   * @generated
   */
  EReference getAstPriority_Inequalities();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstSchedule <em>Ast Schedule</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Schedule</em>'.
   * @see org.caltoopia.frontend.cal.AstSchedule
   * @generated
   */
  EClass getAstSchedule();

  /**
   * Returns the meta object for the reference '{@link org.caltoopia.frontend.cal.AstSchedule#getInitialState <em>Initial State</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Initial State</em>'.
   * @see org.caltoopia.frontend.cal.AstSchedule#getInitialState()
   * @see #getAstSchedule()
   * @generated
   */
  EReference getAstSchedule_InitialState();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstSchedule#getTransitions <em>Transitions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Transitions</em>'.
   * @see org.caltoopia.frontend.cal.AstSchedule#getTransitions()
   * @see #getAstSchedule()
   * @generated
   */
  EReference getAstSchedule_Transitions();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstTransition <em>Ast Transition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Transition</em>'.
   * @see org.caltoopia.frontend.cal.AstTransition
   * @generated
   */
  EClass getAstTransition();

  /**
   * Returns the meta object for the reference '{@link org.caltoopia.frontend.cal.AstTransition#getSource <em>Source</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Source</em>'.
   * @see org.caltoopia.frontend.cal.AstTransition#getSource()
   * @see #getAstTransition()
   * @generated
   */
  EReference getAstTransition_Source();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstTransition#getTags <em>Tags</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Tags</em>'.
   * @see org.caltoopia.frontend.cal.AstTransition#getTags()
   * @see #getAstTransition()
   * @generated
   */
  EReference getAstTransition_Tags();

  /**
   * Returns the meta object for the reference '{@link org.caltoopia.frontend.cal.AstTransition#getTarget <em>Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Target</em>'.
   * @see org.caltoopia.frontend.cal.AstTransition#getTarget()
   * @see #getAstTransition()
   * @generated
   */
  EReference getAstTransition_Target();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstState <em>Ast State</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast State</em>'.
   * @see org.caltoopia.frontend.cal.AstState
   * @generated
   */
  EClass getAstState();

  /**
   * Returns the meta object for the attribute '{@link org.caltoopia.frontend.cal.AstState#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.caltoopia.frontend.cal.AstState#getName()
   * @see #getAstState()
   * @generated
   */
  EAttribute getAstState_Name();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstAction <em>Ast Action</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Action</em>'.
   * @see org.caltoopia.frontend.cal.AstAction
   * @generated
   */
  EClass getAstAction();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstAction#getAnnotations <em>Annotations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Annotations</em>'.
   * @see org.caltoopia.frontend.cal.AstAction#getAnnotations()
   * @see #getAstAction()
   * @generated
   */
  EReference getAstAction_Annotations();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstAction#getTag <em>Tag</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Tag</em>'.
   * @see org.caltoopia.frontend.cal.AstAction#getTag()
   * @see #getAstAction()
   * @generated
   */
  EReference getAstAction_Tag();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstAction#getInputs <em>Inputs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Inputs</em>'.
   * @see org.caltoopia.frontend.cal.AstAction#getInputs()
   * @see #getAstAction()
   * @generated
   */
  EReference getAstAction_Inputs();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstAction#getOutputs <em>Outputs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Outputs</em>'.
   * @see org.caltoopia.frontend.cal.AstAction#getOutputs()
   * @see #getAstAction()
   * @generated
   */
  EReference getAstAction_Outputs();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstAction#getGuards <em>Guards</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Guards</em>'.
   * @see org.caltoopia.frontend.cal.AstAction#getGuards()
   * @see #getAstAction()
   * @generated
   */
  EReference getAstAction_Guards();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstAction#getVariables <em>Variables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Variables</em>'.
   * @see org.caltoopia.frontend.cal.AstAction#getVariables()
   * @see #getAstAction()
   * @generated
   */
  EReference getAstAction_Variables();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstAction#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Statements</em>'.
   * @see org.caltoopia.frontend.cal.AstAction#getStatements()
   * @see #getAstAction()
   * @generated
   */
  EReference getAstAction_Statements();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstInputPattern <em>Ast Input Pattern</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Input Pattern</em>'.
   * @see org.caltoopia.frontend.cal.AstInputPattern
   * @generated
   */
  EClass getAstInputPattern();

  /**
   * Returns the meta object for the reference '{@link org.caltoopia.frontend.cal.AstInputPattern#getPort <em>Port</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Port</em>'.
   * @see org.caltoopia.frontend.cal.AstInputPattern#getPort()
   * @see #getAstInputPattern()
   * @generated
   */
  EReference getAstInputPattern_Port();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstInputPattern#getTokens <em>Tokens</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Tokens</em>'.
   * @see org.caltoopia.frontend.cal.AstInputPattern#getTokens()
   * @see #getAstInputPattern()
   * @generated
   */
  EReference getAstInputPattern_Tokens();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstInputPattern#getRepeat <em>Repeat</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Repeat</em>'.
   * @see org.caltoopia.frontend.cal.AstInputPattern#getRepeat()
   * @see #getAstInputPattern()
   * @generated
   */
  EReference getAstInputPattern_Repeat();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstOutputPattern <em>Ast Output Pattern</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Output Pattern</em>'.
   * @see org.caltoopia.frontend.cal.AstOutputPattern
   * @generated
   */
  EClass getAstOutputPattern();

  /**
   * Returns the meta object for the reference '{@link org.caltoopia.frontend.cal.AstOutputPattern#getPort <em>Port</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Port</em>'.
   * @see org.caltoopia.frontend.cal.AstOutputPattern#getPort()
   * @see #getAstOutputPattern()
   * @generated
   */
  EReference getAstOutputPattern_Port();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstOutputPattern#getValues <em>Values</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Values</em>'.
   * @see org.caltoopia.frontend.cal.AstOutputPattern#getValues()
   * @see #getAstOutputPattern()
   * @generated
   */
  EReference getAstOutputPattern_Values();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstOutputPattern#getRepeat <em>Repeat</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Repeat</em>'.
   * @see org.caltoopia.frontend.cal.AstOutputPattern#getRepeat()
   * @see #getAstOutputPattern()
   * @generated
   */
  EReference getAstOutputPattern_Repeat();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstStatementAssign <em>Ast Statement Assign</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Statement Assign</em>'.
   * @see org.caltoopia.frontend.cal.AstStatementAssign
   * @generated
   */
  EClass getAstStatementAssign();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstStatementAssign#getTarget <em>Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Target</em>'.
   * @see org.caltoopia.frontend.cal.AstStatementAssign#getTarget()
   * @see #getAstStatementAssign()
   * @generated
   */
  EReference getAstStatementAssign_Target();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstStatementAssign#getIndexes <em>Indexes</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Indexes</em>'.
   * @see org.caltoopia.frontend.cal.AstStatementAssign#getIndexes()
   * @see #getAstStatementAssign()
   * @generated
   */
  EReference getAstStatementAssign_Indexes();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstStatementAssign#getMember <em>Member</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Member</em>'.
   * @see org.caltoopia.frontend.cal.AstStatementAssign#getMember()
   * @see #getAstStatementAssign()
   * @generated
   */
  EReference getAstStatementAssign_Member();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstStatementAssign#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see org.caltoopia.frontend.cal.AstStatementAssign#getValue()
   * @see #getAstStatementAssign()
   * @generated
   */
  EReference getAstStatementAssign_Value();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstStatementCall <em>Ast Statement Call</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Statement Call</em>'.
   * @see org.caltoopia.frontend.cal.AstStatementCall
   * @generated
   */
  EClass getAstStatementCall();

  /**
   * Returns the meta object for the reference '{@link org.caltoopia.frontend.cal.AstStatementCall#getProcedure <em>Procedure</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Procedure</em>'.
   * @see org.caltoopia.frontend.cal.AstStatementCall#getProcedure()
   * @see #getAstStatementCall()
   * @generated
   */
  EReference getAstStatementCall_Procedure();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstStatementCall#getParameters <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parameters</em>'.
   * @see org.caltoopia.frontend.cal.AstStatementCall#getParameters()
   * @see #getAstStatementCall()
   * @generated
   */
  EReference getAstStatementCall_Parameters();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstStatementForeach <em>Ast Statement Foreach</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Statement Foreach</em>'.
   * @see org.caltoopia.frontend.cal.AstStatementForeach
   * @generated
   */
  EClass getAstStatementForeach();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstStatementForeach#getGenerators <em>Generators</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Generators</em>'.
   * @see org.caltoopia.frontend.cal.AstStatementForeach#getGenerators()
   * @see #getAstStatementForeach()
   * @generated
   */
  EReference getAstStatementForeach_Generators();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstStatementForeach#getVariables <em>Variables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Variables</em>'.
   * @see org.caltoopia.frontend.cal.AstStatementForeach#getVariables()
   * @see #getAstStatementForeach()
   * @generated
   */
  EReference getAstStatementForeach_Variables();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstStatementForeach#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Statements</em>'.
   * @see org.caltoopia.frontend.cal.AstStatementForeach#getStatements()
   * @see #getAstStatementForeach()
   * @generated
   */
  EReference getAstStatementForeach_Statements();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstForeachGenerator <em>Ast Foreach Generator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Foreach Generator</em>'.
   * @see org.caltoopia.frontend.cal.AstForeachGenerator
   * @generated
   */
  EClass getAstForeachGenerator();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstForeachGenerator#getVariable <em>Variable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Variable</em>'.
   * @see org.caltoopia.frontend.cal.AstForeachGenerator#getVariable()
   * @see #getAstForeachGenerator()
   * @generated
   */
  EReference getAstForeachGenerator_Variable();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstForeachGenerator#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression</em>'.
   * @see org.caltoopia.frontend.cal.AstForeachGenerator#getExpression()
   * @see #getAstForeachGenerator()
   * @generated
   */
  EReference getAstForeachGenerator_Expression();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstStatementBlock <em>Ast Statement Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Statement Block</em>'.
   * @see org.caltoopia.frontend.cal.AstStatementBlock
   * @generated
   */
  EClass getAstStatementBlock();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstStatementBlock#getVariables <em>Variables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Variables</em>'.
   * @see org.caltoopia.frontend.cal.AstStatementBlock#getVariables()
   * @see #getAstStatementBlock()
   * @generated
   */
  EReference getAstStatementBlock_Variables();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstStatementBlock#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Statements</em>'.
   * @see org.caltoopia.frontend.cal.AstStatementBlock#getStatements()
   * @see #getAstStatementBlock()
   * @generated
   */
  EReference getAstStatementBlock_Statements();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstStatementIf <em>Ast Statement If</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Statement If</em>'.
   * @see org.caltoopia.frontend.cal.AstStatementIf
   * @generated
   */
  EClass getAstStatementIf();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstStatementIf#getCondition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Condition</em>'.
   * @see org.caltoopia.frontend.cal.AstStatementIf#getCondition()
   * @see #getAstStatementIf()
   * @generated
   */
  EReference getAstStatementIf_Condition();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstStatementIf#getThen <em>Then</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Then</em>'.
   * @see org.caltoopia.frontend.cal.AstStatementIf#getThen()
   * @see #getAstStatementIf()
   * @generated
   */
  EReference getAstStatementIf_Then();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstStatementIf#getElse <em>Else</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Else</em>'.
   * @see org.caltoopia.frontend.cal.AstStatementIf#getElse()
   * @see #getAstStatementIf()
   * @generated
   */
  EReference getAstStatementIf_Else();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstStatementWhile <em>Ast Statement While</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Statement While</em>'.
   * @see org.caltoopia.frontend.cal.AstStatementWhile
   * @generated
   */
  EClass getAstStatementWhile();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstStatementWhile#getCondition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Condition</em>'.
   * @see org.caltoopia.frontend.cal.AstStatementWhile#getCondition()
   * @see #getAstStatementWhile()
   * @generated
   */
  EReference getAstStatementWhile_Condition();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstStatementWhile#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Statements</em>'.
   * @see org.caltoopia.frontend.cal.AstStatementWhile#getStatements()
   * @see #getAstStatementWhile()
   * @generated
   */
  EReference getAstStatementWhile_Statements();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstStatement <em>Ast Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Statement</em>'.
   * @see org.caltoopia.frontend.cal.AstStatement
   * @generated
   */
  EClass getAstStatement();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstExpression <em>Ast Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Expression</em>'.
   * @see org.caltoopia.frontend.cal.AstExpression
   * @generated
   */
  EClass getAstExpression();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstExpressionCall <em>Ast Expression Call</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Expression Call</em>'.
   * @see org.caltoopia.frontend.cal.AstExpressionCall
   * @generated
   */
  EClass getAstExpressionCall();

  /**
   * Returns the meta object for the reference '{@link org.caltoopia.frontend.cal.AstExpressionCall#getFunction <em>Function</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Function</em>'.
   * @see org.caltoopia.frontend.cal.AstExpressionCall#getFunction()
   * @see #getAstExpressionCall()
   * @generated
   */
  EReference getAstExpressionCall_Function();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstExpressionCall#getParameters <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parameters</em>'.
   * @see org.caltoopia.frontend.cal.AstExpressionCall#getParameters()
   * @see #getAstExpressionCall()
   * @generated
   */
  EReference getAstExpressionCall_Parameters();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstExpressionIf <em>Ast Expression If</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Expression If</em>'.
   * @see org.caltoopia.frontend.cal.AstExpressionIf
   * @generated
   */
  EClass getAstExpressionIf();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstExpressionIf#getCondition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Condition</em>'.
   * @see org.caltoopia.frontend.cal.AstExpressionIf#getCondition()
   * @see #getAstExpressionIf()
   * @generated
   */
  EReference getAstExpressionIf_Condition();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstExpressionIf#getThen <em>Then</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Then</em>'.
   * @see org.caltoopia.frontend.cal.AstExpressionIf#getThen()
   * @see #getAstExpressionIf()
   * @generated
   */
  EReference getAstExpressionIf_Then();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstExpressionIf#getElse <em>Else</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Else</em>'.
   * @see org.caltoopia.frontend.cal.AstExpressionIf#getElse()
   * @see #getAstExpressionIf()
   * @generated
   */
  EReference getAstExpressionIf_Else();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstExpressionList <em>Ast Expression List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Expression List</em>'.
   * @see org.caltoopia.frontend.cal.AstExpressionList
   * @generated
   */
  EClass getAstExpressionList();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstExpressionList#getExpressions <em>Expressions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Expressions</em>'.
   * @see org.caltoopia.frontend.cal.AstExpressionList#getExpressions()
   * @see #getAstExpressionList()
   * @generated
   */
  EReference getAstExpressionList_Expressions();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstExpressionList#getGenerators <em>Generators</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Generators</em>'.
   * @see org.caltoopia.frontend.cal.AstExpressionList#getGenerators()
   * @see #getAstExpressionList()
   * @generated
   */
  EReference getAstExpressionList_Generators();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstGenerator <em>Ast Generator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Generator</em>'.
   * @see org.caltoopia.frontend.cal.AstGenerator
   * @generated
   */
  EClass getAstGenerator();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstGenerator#getVariable <em>Variable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Variable</em>'.
   * @see org.caltoopia.frontend.cal.AstGenerator#getVariable()
   * @see #getAstGenerator()
   * @generated
   */
  EReference getAstGenerator_Variable();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstGenerator#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression</em>'.
   * @see org.caltoopia.frontend.cal.AstGenerator#getExpression()
   * @see #getAstGenerator()
   * @generated
   */
  EReference getAstGenerator_Expression();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstExpressionVariable <em>Ast Expression Variable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Expression Variable</em>'.
   * @see org.caltoopia.frontend.cal.AstExpressionVariable
   * @generated
   */
  EClass getAstExpressionVariable();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstExpressionVariable#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see org.caltoopia.frontend.cal.AstExpressionVariable#getValue()
   * @see #getAstExpressionVariable()
   * @generated
   */
  EReference getAstExpressionVariable_Value();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstExpressionVariable#getIndexes <em>Indexes</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Indexes</em>'.
   * @see org.caltoopia.frontend.cal.AstExpressionVariable#getIndexes()
   * @see #getAstExpressionVariable()
   * @generated
   */
  EReference getAstExpressionVariable_Indexes();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstExpressionVariable#getMember <em>Member</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Member</em>'.
   * @see org.caltoopia.frontend.cal.AstExpressionVariable#getMember()
   * @see #getAstExpressionVariable()
   * @generated
   */
  EReference getAstExpressionVariable_Member();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstExpressionLiteral <em>Ast Expression Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Expression Literal</em>'.
   * @see org.caltoopia.frontend.cal.AstExpressionLiteral
   * @generated
   */
  EClass getAstExpressionLiteral();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstExpressionBoolean <em>Ast Expression Boolean</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Expression Boolean</em>'.
   * @see org.caltoopia.frontend.cal.AstExpressionBoolean
   * @generated
   */
  EClass getAstExpressionBoolean();

  /**
   * Returns the meta object for the attribute '{@link org.caltoopia.frontend.cal.AstExpressionBoolean#isValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.caltoopia.frontend.cal.AstExpressionBoolean#isValue()
   * @see #getAstExpressionBoolean()
   * @generated
   */
  EAttribute getAstExpressionBoolean_Value();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstExpressionFloat <em>Ast Expression Float</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Expression Float</em>'.
   * @see org.caltoopia.frontend.cal.AstExpressionFloat
   * @generated
   */
  EClass getAstExpressionFloat();

  /**
   * Returns the meta object for the attribute '{@link org.caltoopia.frontend.cal.AstExpressionFloat#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.caltoopia.frontend.cal.AstExpressionFloat#getValue()
   * @see #getAstExpressionFloat()
   * @generated
   */
  EAttribute getAstExpressionFloat_Value();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstExpressionInteger <em>Ast Expression Integer</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Expression Integer</em>'.
   * @see org.caltoopia.frontend.cal.AstExpressionInteger
   * @generated
   */
  EClass getAstExpressionInteger();

  /**
   * Returns the meta object for the attribute '{@link org.caltoopia.frontend.cal.AstExpressionInteger#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.caltoopia.frontend.cal.AstExpressionInteger#getValue()
   * @see #getAstExpressionInteger()
   * @generated
   */
  EAttribute getAstExpressionInteger_Value();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstExpressionString <em>Ast Expression String</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Expression String</em>'.
   * @see org.caltoopia.frontend.cal.AstExpressionString
   * @generated
   */
  EClass getAstExpressionString();

  /**
   * Returns the meta object for the attribute '{@link org.caltoopia.frontend.cal.AstExpressionString#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.caltoopia.frontend.cal.AstExpressionString#getValue()
   * @see #getAstExpressionString()
   * @generated
   */
  EAttribute getAstExpressionString_Value();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstType <em>Ast Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Type</em>'.
   * @see org.caltoopia.frontend.cal.AstType
   * @generated
   */
  EClass getAstType();

  /**
   * Returns the meta object for the attribute '{@link org.caltoopia.frontend.cal.AstType#getBuiltin <em>Builtin</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Builtin</em>'.
   * @see org.caltoopia.frontend.cal.AstType#getBuiltin()
   * @see #getAstType()
   * @generated
   */
  EAttribute getAstType_Builtin();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstType#getTypeParams <em>Type Params</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type Params</em>'.
   * @see org.caltoopia.frontend.cal.AstType#getTypeParams()
   * @see #getAstType()
   * @generated
   */
  EReference getAstType_TypeParams();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstType#getDimensions <em>Dimensions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Dimensions</em>'.
   * @see org.caltoopia.frontend.cal.AstType#getDimensions()
   * @see #getAstType()
   * @generated
   */
  EReference getAstType_Dimensions();

  /**
   * Returns the meta object for the reference '{@link org.caltoopia.frontend.cal.AstType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Name</em>'.
   * @see org.caltoopia.frontend.cal.AstType#getName()
   * @see #getAstType()
   * @generated
   */
  EReference getAstType_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstType#getDomain <em>Domain</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Domain</em>'.
   * @see org.caltoopia.frontend.cal.AstType#getDomain()
   * @see #getAstType()
   * @generated
   */
  EReference getAstType_Domain();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstType#getCodomain <em>Codomain</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Codomain</em>'.
   * @see org.caltoopia.frontend.cal.AstType#getCodomain()
   * @see #getAstType()
   * @generated
   */
  EReference getAstType_Codomain();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstType#getMembers <em>Members</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Members</em>'.
   * @see org.caltoopia.frontend.cal.AstType#getMembers()
   * @see #getAstType()
   * @generated
   */
  EReference getAstType_Members();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstTypeParameterList <em>Ast Type Parameter List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Type Parameter List</em>'.
   * @see org.caltoopia.frontend.cal.AstTypeParameterList
   * @generated
   */
  EClass getAstTypeParameterList();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstTypeParameterList#getParams <em>Params</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Params</em>'.
   * @see org.caltoopia.frontend.cal.AstTypeParameterList#getParams()
   * @see #getAstTypeParameterList()
   * @generated
   */
  EReference getAstTypeParameterList_Params();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstTypeParam <em>Ast Type Param</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Type Param</em>'.
   * @see org.caltoopia.frontend.cal.AstTypeParam
   * @generated
   */
  EClass getAstTypeParam();

  /**
   * Returns the meta object for the attribute '{@link org.caltoopia.frontend.cal.AstTypeParam#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.caltoopia.frontend.cal.AstTypeParam#getName()
   * @see #getAstTypeParam()
   * @generated
   */
  EAttribute getAstTypeParam_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstTypeParam#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see org.caltoopia.frontend.cal.AstTypeParam#getValue()
   * @see #getAstTypeParam()
   * @generated
   */
  EReference getAstTypeParam_Value();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstTypeParam#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see org.caltoopia.frontend.cal.AstTypeParam#getType()
   * @see #getAstTypeParam()
   * @generated
   */
  EReference getAstTypeParam_Type();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstVariableReference <em>Ast Variable Reference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Variable Reference</em>'.
   * @see org.caltoopia.frontend.cal.AstVariableReference
   * @generated
   */
  EClass getAstVariableReference();

  /**
   * Returns the meta object for the reference '{@link org.caltoopia.frontend.cal.AstVariableReference#getVariable <em>Variable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Variable</em>'.
   * @see org.caltoopia.frontend.cal.AstVariableReference#getVariable()
   * @see #getAstVariableReference()
   * @generated
   */
  EReference getAstVariableReference_Variable();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstMemberAccess <em>Ast Member Access</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Member Access</em>'.
   * @see org.caltoopia.frontend.cal.AstMemberAccess
   * @generated
   */
  EClass getAstMemberAccess();

  /**
   * Returns the meta object for the attribute '{@link org.caltoopia.frontend.cal.AstMemberAccess#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.caltoopia.frontend.cal.AstMemberAccess#getName()
   * @see #getAstMemberAccess()
   * @generated
   */
  EAttribute getAstMemberAccess_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstMemberAccess#getMemberIndex <em>Member Index</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Member Index</em>'.
   * @see org.caltoopia.frontend.cal.AstMemberAccess#getMemberIndex()
   * @see #getAstMemberAccess()
   * @generated
   */
  EReference getAstMemberAccess_MemberIndex();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstAnnotation <em>Ast Annotation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Annotation</em>'.
   * @see org.caltoopia.frontend.cal.AstAnnotation
   * @generated
   */
  EClass getAstAnnotation();

  /**
   * Returns the meta object for the attribute '{@link org.caltoopia.frontend.cal.AstAnnotation#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.caltoopia.frontend.cal.AstAnnotation#getName()
   * @see #getAstAnnotation()
   * @generated
   */
  EAttribute getAstAnnotation_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.caltoopia.frontend.cal.AstAnnotation#getArguments <em>Arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Arguments</em>'.
   * @see org.caltoopia.frontend.cal.AstAnnotation#getArguments()
   * @see #getAstAnnotation()
   * @generated
   */
  EReference getAstAnnotation_Arguments();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstAnnotationArgument <em>Ast Annotation Argument</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Annotation Argument</em>'.
   * @see org.caltoopia.frontend.cal.AstAnnotationArgument
   * @generated
   */
  EClass getAstAnnotationArgument();

  /**
   * Returns the meta object for the attribute '{@link org.caltoopia.frontend.cal.AstAnnotationArgument#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.caltoopia.frontend.cal.AstAnnotationArgument#getName()
   * @see #getAstAnnotationArgument()
   * @generated
   */
  EAttribute getAstAnnotationArgument_Name();

  /**
   * Returns the meta object for the attribute '{@link org.caltoopia.frontend.cal.AstAnnotationArgument#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.caltoopia.frontend.cal.AstAnnotationArgument#getValue()
   * @see #getAstAnnotationArgument()
   * @generated
   */
  EAttribute getAstAnnotationArgument_Value();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstInitialize <em>Ast Initialize</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Initialize</em>'.
   * @see org.caltoopia.frontend.cal.AstInitialize
   * @generated
   */
  EClass getAstInitialize();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstExpressionBinary <em>Ast Expression Binary</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Expression Binary</em>'.
   * @see org.caltoopia.frontend.cal.AstExpressionBinary
   * @generated
   */
  EClass getAstExpressionBinary();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstExpressionBinary#getLeft <em>Left</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Left</em>'.
   * @see org.caltoopia.frontend.cal.AstExpressionBinary#getLeft()
   * @see #getAstExpressionBinary()
   * @generated
   */
  EReference getAstExpressionBinary_Left();

  /**
   * Returns the meta object for the attribute '{@link org.caltoopia.frontend.cal.AstExpressionBinary#getOperator <em>Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Operator</em>'.
   * @see org.caltoopia.frontend.cal.AstExpressionBinary#getOperator()
   * @see #getAstExpressionBinary()
   * @generated
   */
  EAttribute getAstExpressionBinary_Operator();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstExpressionBinary#getRight <em>Right</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Right</em>'.
   * @see org.caltoopia.frontend.cal.AstExpressionBinary#getRight()
   * @see #getAstExpressionBinary()
   * @generated
   */
  EReference getAstExpressionBinary_Right();

  /**
   * Returns the meta object for class '{@link org.caltoopia.frontend.cal.AstExpressionUnary <em>Ast Expression Unary</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ast Expression Unary</em>'.
   * @see org.caltoopia.frontend.cal.AstExpressionUnary
   * @generated
   */
  EClass getAstExpressionUnary();

  /**
   * Returns the meta object for the attribute '{@link org.caltoopia.frontend.cal.AstExpressionUnary#getUnaryOperator <em>Unary Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Unary Operator</em>'.
   * @see org.caltoopia.frontend.cal.AstExpressionUnary#getUnaryOperator()
   * @see #getAstExpressionUnary()
   * @generated
   */
  EAttribute getAstExpressionUnary_UnaryOperator();

  /**
   * Returns the meta object for the containment reference '{@link org.caltoopia.frontend.cal.AstExpressionUnary#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression</em>'.
   * @see org.caltoopia.frontend.cal.AstExpressionUnary#getExpression()
   * @see #getAstExpressionUnary()
   * @generated
   */
  EReference getAstExpressionUnary_Expression();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  CalFactory getCalFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstTopImpl <em>Ast Top</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstTopImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstTop()
     * @generated
     */
    EClass AST_TOP = eINSTANCE.getAstTop();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstPackageImpl <em>Ast Package</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstPackageImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstPackage()
     * @generated
     */
    EClass AST_PACKAGE = eINSTANCE.getAstPackage();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstUnitImpl <em>Ast Unit</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstUnitImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstUnit()
     * @generated
     */
    EClass AST_UNIT = eINSTANCE.getAstUnit();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstNamespaceImpl <em>Ast Namespace</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstNamespaceImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstNamespace()
     * @generated
     */
    EClass AST_NAMESPACE = eINSTANCE.getAstNamespace();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AST_NAMESPACE__NAME = eINSTANCE.getAstNamespace_Name();

    /**
     * The meta object literal for the '<em><b>Entities</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_NAMESPACE__ENTITIES = eINSTANCE.getAstNamespace_Entities();

    /**
     * The meta object literal for the '<em><b>Imports</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_NAMESPACE__IMPORTS = eINSTANCE.getAstNamespace_Imports();

    /**
     * The meta object literal for the '<em><b>Units</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_NAMESPACE__UNITS = eINSTANCE.getAstNamespace_Units();

    /**
     * The meta object literal for the '<em><b>Functions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_NAMESPACE__FUNCTIONS = eINSTANCE.getAstNamespace_Functions();

    /**
     * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_NAMESPACE__VARIABLES = eINSTANCE.getAstNamespace_Variables();

    /**
     * The meta object literal for the '<em><b>Externals</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_NAMESPACE__EXTERNALS = eINSTANCE.getAstNamespace_Externals();

    /**
     * The meta object literal for the '<em><b>Annotations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_NAMESPACE__ANNOTATIONS = eINSTANCE.getAstNamespace_Annotations();

    /**
     * The meta object literal for the '<em><b>Typedefs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_NAMESPACE__TYPEDEFS = eINSTANCE.getAstNamespace_Typedefs();

    /**
     * The meta object literal for the '<em><b>Namespaces</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_NAMESPACE__NAMESPACES = eINSTANCE.getAstNamespace_Namespaces();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstEntityImpl <em>Ast Entity</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstEntityImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstEntity()
     * @generated
     */
    EClass AST_ENTITY = eINSTANCE.getAstEntity();

    /**
     * The meta object literal for the '<em><b>Annotations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_ENTITY__ANNOTATIONS = eINSTANCE.getAstEntity_Annotations();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AST_ENTITY__NAME = eINSTANCE.getAstEntity_Name();

    /**
     * The meta object literal for the '<em><b>Actor</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_ENTITY__ACTOR = eINSTANCE.getAstEntity_Actor();

    /**
     * The meta object literal for the '<em><b>Network</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_ENTITY__NETWORK = eINSTANCE.getAstEntity_Network();

    /**
     * The meta object literal for the '<em><b>External</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_ENTITY__EXTERNAL = eINSTANCE.getAstEntity_External();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.ImportImpl <em>Import</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.ImportImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getImport()
     * @generated
     */
    EClass IMPORT = eINSTANCE.getImport();

    /**
     * The meta object literal for the '<em><b>Imported Namespace</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IMPORT__IMPORTED_NAMESPACE = eINSTANCE.getImport_ImportedNamespace();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstNetworkImpl <em>Ast Network</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstNetworkImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstNetwork()
     * @generated
     */
    EClass AST_NETWORK = eINSTANCE.getAstNetwork();

    /**
     * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_NETWORK__PARAMETERS = eINSTANCE.getAstNetwork_Parameters();

    /**
     * The meta object literal for the '<em><b>Inputs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_NETWORK__INPUTS = eINSTANCE.getAstNetwork_Inputs();

    /**
     * The meta object literal for the '<em><b>Outputs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_NETWORK__OUTPUTS = eINSTANCE.getAstNetwork_Outputs();

    /**
     * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_NETWORK__VARIABLES = eINSTANCE.getAstNetwork_Variables();

    /**
     * The meta object literal for the '<em><b>Instances</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_NETWORK__INSTANCES = eINSTANCE.getAstNetwork_Instances();

    /**
     * The meta object literal for the '<em><b>Structure</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_NETWORK__STRUCTURE = eINSTANCE.getAstNetwork_Structure();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstActorVariableImpl <em>Ast Actor Variable</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstActorVariableImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstActorVariable()
     * @generated
     */
    EClass AST_ACTOR_VARIABLE = eINSTANCE.getAstActorVariable();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AST_ACTOR_VARIABLE__NAME = eINSTANCE.getAstActorVariable_Name();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_ACTOR_VARIABLE__TYPE = eINSTANCE.getAstActorVariable_Type();

    /**
     * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_ACTOR_VARIABLE__PARAMETERS = eINSTANCE.getAstActorVariable_Parameters();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstAssignParameterImpl <em>Ast Assign Parameter</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstAssignParameterImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstAssignParameter()
     * @generated
     */
    EClass AST_ASSIGN_PARAMETER = eINSTANCE.getAstAssignParameter();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AST_ASSIGN_PARAMETER__NAME = eINSTANCE.getAstAssignParameter_Name();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_ASSIGN_PARAMETER__VALUE = eINSTANCE.getAstAssignParameter_Value();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstStructureImpl <em>Ast Structure</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstStructureImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstStructure()
     * @generated
     */
    EClass AST_STRUCTURE = eINSTANCE.getAstStructure();

    /**
     * The meta object literal for the '<em><b>Connections</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_STRUCTURE__CONNECTIONS = eINSTANCE.getAstStructure_Connections();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstConnectionImpl <em>Ast Connection</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstConnectionImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstConnection()
     * @generated
     */
    EClass AST_CONNECTION = eINSTANCE.getAstConnection();

    /**
     * The meta object literal for the '<em><b>From</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_CONNECTION__FROM = eINSTANCE.getAstConnection_From();

    /**
     * The meta object literal for the '<em><b>Out Port</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AST_CONNECTION__OUT_PORT = eINSTANCE.getAstConnection_OutPort();

    /**
     * The meta object literal for the '<em><b>To</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_CONNECTION__TO = eINSTANCE.getAstConnection_To();

    /**
     * The meta object literal for the '<em><b>In Port</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AST_CONNECTION__IN_PORT = eINSTANCE.getAstConnection_InPort();

    /**
     * The meta object literal for the '<em><b>Attribute</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_CONNECTION__ATTRIBUTE = eINSTANCE.getAstConnection_Attribute();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstActorVariableReferenceImpl <em>Ast Actor Variable Reference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstActorVariableReferenceImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstActorVariableReference()
     * @generated
     */
    EClass AST_ACTOR_VARIABLE_REFERENCE = eINSTANCE.getAstActorVariableReference();

    /**
     * The meta object literal for the '<em><b>Variable</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_ACTOR_VARIABLE_REFERENCE__VARIABLE = eINSTANCE.getAstActorVariableReference_Variable();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstConnectionAttributeImpl <em>Ast Connection Attribute</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstConnectionAttributeImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstConnectionAttribute()
     * @generated
     */
    EClass AST_CONNECTION_ATTRIBUTE = eINSTANCE.getAstConnectionAttribute();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AST_CONNECTION_ATTRIBUTE__NAME = eINSTANCE.getAstConnectionAttribute_Name();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_CONNECTION_ATTRIBUTE__VALUE = eINSTANCE.getAstConnectionAttribute_Value();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstVariableImpl <em>Ast Variable</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstVariableImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstVariable()
     * @generated
     */
    EClass AST_VARIABLE = eINSTANCE.getAstVariable();

    /**
     * The meta object literal for the '<em><b>Constant</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AST_VARIABLE__CONSTANT = eINSTANCE.getAstVariable_Constant();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_VARIABLE__VALUE = eINSTANCE.getAstVariable_Value();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AST_VARIABLE__NAME = eINSTANCE.getAstVariable_Name();

    /**
     * The meta object literal for the '<em><b>Annotations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_VARIABLE__ANNOTATIONS = eINSTANCE.getAstVariable_Annotations();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_VARIABLE__TYPE = eINSTANCE.getAstVariable_Type();

    /**
     * The meta object literal for the '<em><b>Dimensions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_VARIABLE__DIMENSIONS = eINSTANCE.getAstVariable_Dimensions();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstTypeNameImpl <em>Ast Type Name</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstTypeNameImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstTypeName()
     * @generated
     */
    EClass AST_TYPE_NAME = eINSTANCE.getAstTypeName();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AST_TYPE_NAME__NAME = eINSTANCE.getAstTypeName_Name();

    /**
     * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_TYPE_NAME__PARAMETERS = eINSTANCE.getAstTypeName_Parameters();

    /**
     * The meta object literal for the '<em><b>Constructor</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_TYPE_NAME__CONSTRUCTOR = eINSTANCE.getAstTypeName_Constructor();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_TYPE_NAME__TYPE = eINSTANCE.getAstTypeName_Type();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstTypeDefinitionParameterImpl <em>Ast Type Definition Parameter</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstTypeDefinitionParameterImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstTypeDefinitionParameter()
     * @generated
     */
    EClass AST_TYPE_DEFINITION_PARAMETER = eINSTANCE.getAstTypeDefinitionParameter();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_TYPE_DEFINITION_PARAMETER__VALUE = eINSTANCE.getAstTypeDefinitionParameter_Value();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_TYPE_DEFINITION_PARAMETER__TYPE = eINSTANCE.getAstTypeDefinitionParameter_Type();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstFunctionImpl <em>Ast Function</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstFunctionImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstFunction()
     * @generated
     */
    EClass AST_FUNCTION = eINSTANCE.getAstFunction();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AST_FUNCTION__NAME = eINSTANCE.getAstFunction_Name();

    /**
     * The meta object literal for the '<em><b>Members</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_FUNCTION__MEMBERS = eINSTANCE.getAstFunction_Members();

    /**
     * The meta object literal for the '<em><b>Annotations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_FUNCTION__ANNOTATIONS = eINSTANCE.getAstFunction_Annotations();

    /**
     * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_FUNCTION__PARAMETERS = eINSTANCE.getAstFunction_Parameters();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_FUNCTION__TYPE = eINSTANCE.getAstFunction_Type();

    /**
     * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_FUNCTION__VARIABLES = eINSTANCE.getAstFunction_Variables();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_FUNCTION__EXPRESSION = eINSTANCE.getAstFunction_Expression();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstActorImpl <em>Ast Actor</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstActorImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstActor()
     * @generated
     */
    EClass AST_ACTOR = eINSTANCE.getAstActor();

    /**
     * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_ACTOR__PARAMETERS = eINSTANCE.getAstActor_Parameters();

    /**
     * The meta object literal for the '<em><b>Inputs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_ACTOR__INPUTS = eINSTANCE.getAstActor_Inputs();

    /**
     * The meta object literal for the '<em><b>Outputs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_ACTOR__OUTPUTS = eINSTANCE.getAstActor_Outputs();

    /**
     * The meta object literal for the '<em><b>Functions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_ACTOR__FUNCTIONS = eINSTANCE.getAstActor_Functions();

    /**
     * The meta object literal for the '<em><b>Procedures</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_ACTOR__PROCEDURES = eINSTANCE.getAstActor_Procedures();

    /**
     * The meta object literal for the '<em><b>Actions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_ACTOR__ACTIONS = eINSTANCE.getAstActor_Actions();

    /**
     * The meta object literal for the '<em><b>Initializes</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_ACTOR__INITIALIZES = eINSTANCE.getAstActor_Initializes();

    /**
     * The meta object literal for the '<em><b>State Variables</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_ACTOR__STATE_VARIABLES = eINSTANCE.getAstActor_StateVariables();

    /**
     * The meta object literal for the '<em><b>Schedules</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_ACTOR__SCHEDULES = eINSTANCE.getAstActor_Schedules();

    /**
     * The meta object literal for the '<em><b>Priorities</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_ACTOR__PRIORITIES = eINSTANCE.getAstActor_Priorities();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstPortImpl <em>Ast Port</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstPortImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstPort()
     * @generated
     */
    EClass AST_PORT = eINSTANCE.getAstPort();

    /**
     * The meta object literal for the '<em><b>Annotations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_PORT__ANNOTATIONS = eINSTANCE.getAstPort_Annotations();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_PORT__TYPE = eINSTANCE.getAstPort_Type();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AST_PORT__NAME = eINSTANCE.getAstPort_Name();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstExternalFunctionImpl <em>Ast External Function</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstExternalFunctionImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstExternalFunction()
     * @generated
     */
    EClass AST_EXTERNAL_FUNCTION = eINSTANCE.getAstExternalFunction();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstProcedureImpl <em>Ast Procedure</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstProcedureImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstProcedure()
     * @generated
     */
    EClass AST_PROCEDURE = eINSTANCE.getAstProcedure();

    /**
     * The meta object literal for the '<em><b>Annotations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_PROCEDURE__ANNOTATIONS = eINSTANCE.getAstProcedure_Annotations();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AST_PROCEDURE__NAME = eINSTANCE.getAstProcedure_Name();

    /**
     * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_PROCEDURE__PARAMETERS = eINSTANCE.getAstProcedure_Parameters();

    /**
     * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_PROCEDURE__VARIABLES = eINSTANCE.getAstProcedure_Variables();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_PROCEDURE__STATEMENTS = eINSTANCE.getAstProcedure_Statements();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstExternalProcedureImpl <em>Ast External Procedure</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstExternalProcedureImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstExternalProcedure()
     * @generated
     */
    EClass AST_EXTERNAL_PROCEDURE = eINSTANCE.getAstExternalProcedure();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstTagImpl <em>Ast Tag</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstTagImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstTag()
     * @generated
     */
    EClass AST_TAG = eINSTANCE.getAstTag();

    /**
     * The meta object literal for the '<em><b>Identifiers</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AST_TAG__IDENTIFIERS = eINSTANCE.getAstTag_Identifiers();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstExternalActorImpl <em>Ast External Actor</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstExternalActorImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstExternalActor()
     * @generated
     */
    EClass AST_EXTERNAL_ACTOR = eINSTANCE.getAstExternalActor();

    /**
     * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_EXTERNAL_ACTOR__PARAMETERS = eINSTANCE.getAstExternalActor_Parameters();

    /**
     * The meta object literal for the '<em><b>Inputs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_EXTERNAL_ACTOR__INPUTS = eINSTANCE.getAstExternalActor_Inputs();

    /**
     * The meta object literal for the '<em><b>Outputs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_EXTERNAL_ACTOR__OUTPUTS = eINSTANCE.getAstExternalActor_Outputs();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstInequalityImpl <em>Ast Inequality</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstInequalityImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstInequality()
     * @generated
     */
    EClass AST_INEQUALITY = eINSTANCE.getAstInequality();

    /**
     * The meta object literal for the '<em><b>Tags</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_INEQUALITY__TAGS = eINSTANCE.getAstInequality_Tags();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstPriorityImpl <em>Ast Priority</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstPriorityImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstPriority()
     * @generated
     */
    EClass AST_PRIORITY = eINSTANCE.getAstPriority();

    /**
     * The meta object literal for the '<em><b>Inequalities</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_PRIORITY__INEQUALITIES = eINSTANCE.getAstPriority_Inequalities();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstScheduleImpl <em>Ast Schedule</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstScheduleImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstSchedule()
     * @generated
     */
    EClass AST_SCHEDULE = eINSTANCE.getAstSchedule();

    /**
     * The meta object literal for the '<em><b>Initial State</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_SCHEDULE__INITIAL_STATE = eINSTANCE.getAstSchedule_InitialState();

    /**
     * The meta object literal for the '<em><b>Transitions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_SCHEDULE__TRANSITIONS = eINSTANCE.getAstSchedule_Transitions();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstTransitionImpl <em>Ast Transition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstTransitionImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstTransition()
     * @generated
     */
    EClass AST_TRANSITION = eINSTANCE.getAstTransition();

    /**
     * The meta object literal for the '<em><b>Source</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_TRANSITION__SOURCE = eINSTANCE.getAstTransition_Source();

    /**
     * The meta object literal for the '<em><b>Tags</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_TRANSITION__TAGS = eINSTANCE.getAstTransition_Tags();

    /**
     * The meta object literal for the '<em><b>Target</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_TRANSITION__TARGET = eINSTANCE.getAstTransition_Target();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstStateImpl <em>Ast State</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstStateImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstState()
     * @generated
     */
    EClass AST_STATE = eINSTANCE.getAstState();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AST_STATE__NAME = eINSTANCE.getAstState_Name();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstActionImpl <em>Ast Action</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstActionImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstAction()
     * @generated
     */
    EClass AST_ACTION = eINSTANCE.getAstAction();

    /**
     * The meta object literal for the '<em><b>Annotations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_ACTION__ANNOTATIONS = eINSTANCE.getAstAction_Annotations();

    /**
     * The meta object literal for the '<em><b>Tag</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_ACTION__TAG = eINSTANCE.getAstAction_Tag();

    /**
     * The meta object literal for the '<em><b>Inputs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_ACTION__INPUTS = eINSTANCE.getAstAction_Inputs();

    /**
     * The meta object literal for the '<em><b>Outputs</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_ACTION__OUTPUTS = eINSTANCE.getAstAction_Outputs();

    /**
     * The meta object literal for the '<em><b>Guards</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_ACTION__GUARDS = eINSTANCE.getAstAction_Guards();

    /**
     * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_ACTION__VARIABLES = eINSTANCE.getAstAction_Variables();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_ACTION__STATEMENTS = eINSTANCE.getAstAction_Statements();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstInputPatternImpl <em>Ast Input Pattern</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstInputPatternImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstInputPattern()
     * @generated
     */
    EClass AST_INPUT_PATTERN = eINSTANCE.getAstInputPattern();

    /**
     * The meta object literal for the '<em><b>Port</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_INPUT_PATTERN__PORT = eINSTANCE.getAstInputPattern_Port();

    /**
     * The meta object literal for the '<em><b>Tokens</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_INPUT_PATTERN__TOKENS = eINSTANCE.getAstInputPattern_Tokens();

    /**
     * The meta object literal for the '<em><b>Repeat</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_INPUT_PATTERN__REPEAT = eINSTANCE.getAstInputPattern_Repeat();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstOutputPatternImpl <em>Ast Output Pattern</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstOutputPatternImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstOutputPattern()
     * @generated
     */
    EClass AST_OUTPUT_PATTERN = eINSTANCE.getAstOutputPattern();

    /**
     * The meta object literal for the '<em><b>Port</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_OUTPUT_PATTERN__PORT = eINSTANCE.getAstOutputPattern_Port();

    /**
     * The meta object literal for the '<em><b>Values</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_OUTPUT_PATTERN__VALUES = eINSTANCE.getAstOutputPattern_Values();

    /**
     * The meta object literal for the '<em><b>Repeat</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_OUTPUT_PATTERN__REPEAT = eINSTANCE.getAstOutputPattern_Repeat();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstStatementAssignImpl <em>Ast Statement Assign</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstStatementAssignImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstStatementAssign()
     * @generated
     */
    EClass AST_STATEMENT_ASSIGN = eINSTANCE.getAstStatementAssign();

    /**
     * The meta object literal for the '<em><b>Target</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_STATEMENT_ASSIGN__TARGET = eINSTANCE.getAstStatementAssign_Target();

    /**
     * The meta object literal for the '<em><b>Indexes</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_STATEMENT_ASSIGN__INDEXES = eINSTANCE.getAstStatementAssign_Indexes();

    /**
     * The meta object literal for the '<em><b>Member</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_STATEMENT_ASSIGN__MEMBER = eINSTANCE.getAstStatementAssign_Member();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_STATEMENT_ASSIGN__VALUE = eINSTANCE.getAstStatementAssign_Value();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstStatementCallImpl <em>Ast Statement Call</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstStatementCallImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstStatementCall()
     * @generated
     */
    EClass AST_STATEMENT_CALL = eINSTANCE.getAstStatementCall();

    /**
     * The meta object literal for the '<em><b>Procedure</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_STATEMENT_CALL__PROCEDURE = eINSTANCE.getAstStatementCall_Procedure();

    /**
     * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_STATEMENT_CALL__PARAMETERS = eINSTANCE.getAstStatementCall_Parameters();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstStatementForeachImpl <em>Ast Statement Foreach</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstStatementForeachImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstStatementForeach()
     * @generated
     */
    EClass AST_STATEMENT_FOREACH = eINSTANCE.getAstStatementForeach();

    /**
     * The meta object literal for the '<em><b>Generators</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_STATEMENT_FOREACH__GENERATORS = eINSTANCE.getAstStatementForeach_Generators();

    /**
     * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_STATEMENT_FOREACH__VARIABLES = eINSTANCE.getAstStatementForeach_Variables();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_STATEMENT_FOREACH__STATEMENTS = eINSTANCE.getAstStatementForeach_Statements();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstForeachGeneratorImpl <em>Ast Foreach Generator</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstForeachGeneratorImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstForeachGenerator()
     * @generated
     */
    EClass AST_FOREACH_GENERATOR = eINSTANCE.getAstForeachGenerator();

    /**
     * The meta object literal for the '<em><b>Variable</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_FOREACH_GENERATOR__VARIABLE = eINSTANCE.getAstForeachGenerator_Variable();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_FOREACH_GENERATOR__EXPRESSION = eINSTANCE.getAstForeachGenerator_Expression();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstStatementBlockImpl <em>Ast Statement Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstStatementBlockImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstStatementBlock()
     * @generated
     */
    EClass AST_STATEMENT_BLOCK = eINSTANCE.getAstStatementBlock();

    /**
     * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_STATEMENT_BLOCK__VARIABLES = eINSTANCE.getAstStatementBlock_Variables();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_STATEMENT_BLOCK__STATEMENTS = eINSTANCE.getAstStatementBlock_Statements();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstStatementIfImpl <em>Ast Statement If</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstStatementIfImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstStatementIf()
     * @generated
     */
    EClass AST_STATEMENT_IF = eINSTANCE.getAstStatementIf();

    /**
     * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_STATEMENT_IF__CONDITION = eINSTANCE.getAstStatementIf_Condition();

    /**
     * The meta object literal for the '<em><b>Then</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_STATEMENT_IF__THEN = eINSTANCE.getAstStatementIf_Then();

    /**
     * The meta object literal for the '<em><b>Else</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_STATEMENT_IF__ELSE = eINSTANCE.getAstStatementIf_Else();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstStatementWhileImpl <em>Ast Statement While</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstStatementWhileImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstStatementWhile()
     * @generated
     */
    EClass AST_STATEMENT_WHILE = eINSTANCE.getAstStatementWhile();

    /**
     * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_STATEMENT_WHILE__CONDITION = eINSTANCE.getAstStatementWhile_Condition();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_STATEMENT_WHILE__STATEMENTS = eINSTANCE.getAstStatementWhile_Statements();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstStatementImpl <em>Ast Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstStatementImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstStatement()
     * @generated
     */
    EClass AST_STATEMENT = eINSTANCE.getAstStatement();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstExpressionImpl <em>Ast Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstExpressionImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstExpression()
     * @generated
     */
    EClass AST_EXPRESSION = eINSTANCE.getAstExpression();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstExpressionCallImpl <em>Ast Expression Call</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstExpressionCallImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstExpressionCall()
     * @generated
     */
    EClass AST_EXPRESSION_CALL = eINSTANCE.getAstExpressionCall();

    /**
     * The meta object literal for the '<em><b>Function</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_EXPRESSION_CALL__FUNCTION = eINSTANCE.getAstExpressionCall_Function();

    /**
     * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_EXPRESSION_CALL__PARAMETERS = eINSTANCE.getAstExpressionCall_Parameters();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstExpressionIfImpl <em>Ast Expression If</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstExpressionIfImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstExpressionIf()
     * @generated
     */
    EClass AST_EXPRESSION_IF = eINSTANCE.getAstExpressionIf();

    /**
     * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_EXPRESSION_IF__CONDITION = eINSTANCE.getAstExpressionIf_Condition();

    /**
     * The meta object literal for the '<em><b>Then</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_EXPRESSION_IF__THEN = eINSTANCE.getAstExpressionIf_Then();

    /**
     * The meta object literal for the '<em><b>Else</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_EXPRESSION_IF__ELSE = eINSTANCE.getAstExpressionIf_Else();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstExpressionListImpl <em>Ast Expression List</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstExpressionListImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstExpressionList()
     * @generated
     */
    EClass AST_EXPRESSION_LIST = eINSTANCE.getAstExpressionList();

    /**
     * The meta object literal for the '<em><b>Expressions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_EXPRESSION_LIST__EXPRESSIONS = eINSTANCE.getAstExpressionList_Expressions();

    /**
     * The meta object literal for the '<em><b>Generators</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_EXPRESSION_LIST__GENERATORS = eINSTANCE.getAstExpressionList_Generators();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstGeneratorImpl <em>Ast Generator</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstGeneratorImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstGenerator()
     * @generated
     */
    EClass AST_GENERATOR = eINSTANCE.getAstGenerator();

    /**
     * The meta object literal for the '<em><b>Variable</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_GENERATOR__VARIABLE = eINSTANCE.getAstGenerator_Variable();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_GENERATOR__EXPRESSION = eINSTANCE.getAstGenerator_Expression();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstExpressionVariableImpl <em>Ast Expression Variable</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstExpressionVariableImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstExpressionVariable()
     * @generated
     */
    EClass AST_EXPRESSION_VARIABLE = eINSTANCE.getAstExpressionVariable();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_EXPRESSION_VARIABLE__VALUE = eINSTANCE.getAstExpressionVariable_Value();

    /**
     * The meta object literal for the '<em><b>Indexes</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_EXPRESSION_VARIABLE__INDEXES = eINSTANCE.getAstExpressionVariable_Indexes();

    /**
     * The meta object literal for the '<em><b>Member</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_EXPRESSION_VARIABLE__MEMBER = eINSTANCE.getAstExpressionVariable_Member();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstExpressionLiteralImpl <em>Ast Expression Literal</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstExpressionLiteralImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstExpressionLiteral()
     * @generated
     */
    EClass AST_EXPRESSION_LITERAL = eINSTANCE.getAstExpressionLiteral();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstExpressionBooleanImpl <em>Ast Expression Boolean</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstExpressionBooleanImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstExpressionBoolean()
     * @generated
     */
    EClass AST_EXPRESSION_BOOLEAN = eINSTANCE.getAstExpressionBoolean();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AST_EXPRESSION_BOOLEAN__VALUE = eINSTANCE.getAstExpressionBoolean_Value();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstExpressionFloatImpl <em>Ast Expression Float</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstExpressionFloatImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstExpressionFloat()
     * @generated
     */
    EClass AST_EXPRESSION_FLOAT = eINSTANCE.getAstExpressionFloat();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AST_EXPRESSION_FLOAT__VALUE = eINSTANCE.getAstExpressionFloat_Value();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstExpressionIntegerImpl <em>Ast Expression Integer</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstExpressionIntegerImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstExpressionInteger()
     * @generated
     */
    EClass AST_EXPRESSION_INTEGER = eINSTANCE.getAstExpressionInteger();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AST_EXPRESSION_INTEGER__VALUE = eINSTANCE.getAstExpressionInteger_Value();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstExpressionStringImpl <em>Ast Expression String</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstExpressionStringImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstExpressionString()
     * @generated
     */
    EClass AST_EXPRESSION_STRING = eINSTANCE.getAstExpressionString();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AST_EXPRESSION_STRING__VALUE = eINSTANCE.getAstExpressionString_Value();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstTypeImpl <em>Ast Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstTypeImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstType()
     * @generated
     */
    EClass AST_TYPE = eINSTANCE.getAstType();

    /**
     * The meta object literal for the '<em><b>Builtin</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AST_TYPE__BUILTIN = eINSTANCE.getAstType_Builtin();

    /**
     * The meta object literal for the '<em><b>Type Params</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_TYPE__TYPE_PARAMS = eINSTANCE.getAstType_TypeParams();

    /**
     * The meta object literal for the '<em><b>Dimensions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_TYPE__DIMENSIONS = eINSTANCE.getAstType_Dimensions();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_TYPE__NAME = eINSTANCE.getAstType_Name();

    /**
     * The meta object literal for the '<em><b>Domain</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_TYPE__DOMAIN = eINSTANCE.getAstType_Domain();

    /**
     * The meta object literal for the '<em><b>Codomain</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_TYPE__CODOMAIN = eINSTANCE.getAstType_Codomain();

    /**
     * The meta object literal for the '<em><b>Members</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_TYPE__MEMBERS = eINSTANCE.getAstType_Members();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstTypeParameterListImpl <em>Ast Type Parameter List</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstTypeParameterListImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstTypeParameterList()
     * @generated
     */
    EClass AST_TYPE_PARAMETER_LIST = eINSTANCE.getAstTypeParameterList();

    /**
     * The meta object literal for the '<em><b>Params</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_TYPE_PARAMETER_LIST__PARAMS = eINSTANCE.getAstTypeParameterList_Params();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstTypeParamImpl <em>Ast Type Param</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstTypeParamImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstTypeParam()
     * @generated
     */
    EClass AST_TYPE_PARAM = eINSTANCE.getAstTypeParam();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AST_TYPE_PARAM__NAME = eINSTANCE.getAstTypeParam_Name();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_TYPE_PARAM__VALUE = eINSTANCE.getAstTypeParam_Value();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_TYPE_PARAM__TYPE = eINSTANCE.getAstTypeParam_Type();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstVariableReferenceImpl <em>Ast Variable Reference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstVariableReferenceImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstVariableReference()
     * @generated
     */
    EClass AST_VARIABLE_REFERENCE = eINSTANCE.getAstVariableReference();

    /**
     * The meta object literal for the '<em><b>Variable</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_VARIABLE_REFERENCE__VARIABLE = eINSTANCE.getAstVariableReference_Variable();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstMemberAccessImpl <em>Ast Member Access</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstMemberAccessImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstMemberAccess()
     * @generated
     */
    EClass AST_MEMBER_ACCESS = eINSTANCE.getAstMemberAccess();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AST_MEMBER_ACCESS__NAME = eINSTANCE.getAstMemberAccess_Name();

    /**
     * The meta object literal for the '<em><b>Member Index</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_MEMBER_ACCESS__MEMBER_INDEX = eINSTANCE.getAstMemberAccess_MemberIndex();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstAnnotationImpl <em>Ast Annotation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstAnnotationImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstAnnotation()
     * @generated
     */
    EClass AST_ANNOTATION = eINSTANCE.getAstAnnotation();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AST_ANNOTATION__NAME = eINSTANCE.getAstAnnotation_Name();

    /**
     * The meta object literal for the '<em><b>Arguments</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_ANNOTATION__ARGUMENTS = eINSTANCE.getAstAnnotation_Arguments();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstAnnotationArgumentImpl <em>Ast Annotation Argument</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstAnnotationArgumentImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstAnnotationArgument()
     * @generated
     */
    EClass AST_ANNOTATION_ARGUMENT = eINSTANCE.getAstAnnotationArgument();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AST_ANNOTATION_ARGUMENT__NAME = eINSTANCE.getAstAnnotationArgument_Name();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AST_ANNOTATION_ARGUMENT__VALUE = eINSTANCE.getAstAnnotationArgument_Value();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstInitializeImpl <em>Ast Initialize</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstInitializeImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstInitialize()
     * @generated
     */
    EClass AST_INITIALIZE = eINSTANCE.getAstInitialize();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstExpressionBinaryImpl <em>Ast Expression Binary</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstExpressionBinaryImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstExpressionBinary()
     * @generated
     */
    EClass AST_EXPRESSION_BINARY = eINSTANCE.getAstExpressionBinary();

    /**
     * The meta object literal for the '<em><b>Left</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_EXPRESSION_BINARY__LEFT = eINSTANCE.getAstExpressionBinary_Left();

    /**
     * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AST_EXPRESSION_BINARY__OPERATOR = eINSTANCE.getAstExpressionBinary_Operator();

    /**
     * The meta object literal for the '<em><b>Right</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_EXPRESSION_BINARY__RIGHT = eINSTANCE.getAstExpressionBinary_Right();

    /**
     * The meta object literal for the '{@link org.caltoopia.frontend.cal.impl.AstExpressionUnaryImpl <em>Ast Expression Unary</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.caltoopia.frontend.cal.impl.AstExpressionUnaryImpl
     * @see org.caltoopia.frontend.cal.impl.CalPackageImpl#getAstExpressionUnary()
     * @generated
     */
    EClass AST_EXPRESSION_UNARY = eINSTANCE.getAstExpressionUnary();

    /**
     * The meta object literal for the '<em><b>Unary Operator</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AST_EXPRESSION_UNARY__UNARY_OPERATOR = eINSTANCE.getAstExpressionUnary_UnaryOperator();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AST_EXPRESSION_UNARY__EXPRESSION = eINSTANCE.getAstExpressionUnary_Expression();

  }

} //CalPackage
