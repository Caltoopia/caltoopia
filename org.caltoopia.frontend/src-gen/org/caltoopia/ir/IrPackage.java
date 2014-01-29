/**
 */
package org.caltoopia.ir;

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
 * @see org.caltoopia.ir.IrFactory
 * @model kind="package"
 * @generated
 */
public interface IrPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ir";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///org/caltoopia/ir.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.caltoopia.ir";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	IrPackage eINSTANCE = org.caltoopia.ir.impl.IrPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.NodeImpl <em>Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.NodeImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getNode()
	 * @generated
	 */
	int NODE = 1;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__ANNOTATIONS = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__ID = 1;

	/**
	 * The number of structural features of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.ScopeImpl <em>Scope</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.ScopeImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getScope()
	 * @generated
	 */
	int SCOPE = 0;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE__ANNOTATIONS = NODE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE__ID = NODE__ID;

	/**
	 * The feature id for the '<em><b>Declarations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE__DECLARATIONS = NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Outer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE__OUTER = NODE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Scope</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_FEATURE_COUNT = NODE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.DeclarationImpl <em>Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.DeclarationImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getDeclaration()
	 * @generated
	 */
	int DECLARATION = 49;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARATION__ANNOTATIONS = SCOPE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARATION__ID = SCOPE__ID;

	/**
	 * The feature id for the '<em><b>Declarations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARATION__DECLARATIONS = SCOPE__DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Outer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARATION__OUTER = SCOPE__OUTER;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARATION__NAME = SCOPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Scope</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARATION__SCOPE = SCOPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARATION__ATTRIBUTES = SCOPE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARATION_FEATURE_COUNT = SCOPE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.VariableImportImpl <em>Variable Import</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.VariableImportImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getVariableImport()
	 * @generated
	 */
	int VARIABLE_IMPORT = 2;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_IMPORT__ANNOTATIONS = DECLARATION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_IMPORT__ID = DECLARATION__ID;

	/**
	 * The feature id for the '<em><b>Declarations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_IMPORT__DECLARATIONS = DECLARATION__DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Outer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_IMPORT__OUTER = DECLARATION__OUTER;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_IMPORT__NAME = DECLARATION__NAME;

	/**
	 * The feature id for the '<em><b>Scope</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_IMPORT__SCOPE = DECLARATION__SCOPE;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_IMPORT__ATTRIBUTES = DECLARATION__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_IMPORT__NAMESPACE = DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Variable Import</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_IMPORT_FEATURE_COUNT = DECLARATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.VariableExternalImpl <em>Variable External</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.VariableExternalImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getVariableExternal()
	 * @generated
	 */
	int VARIABLE_EXTERNAL = 3;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXTERNAL__ANNOTATIONS = DECLARATION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXTERNAL__ID = DECLARATION__ID;

	/**
	 * The feature id for the '<em><b>Declarations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXTERNAL__DECLARATIONS = DECLARATION__DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Outer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXTERNAL__OUTER = DECLARATION__OUTER;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXTERNAL__NAME = DECLARATION__NAME;

	/**
	 * The feature id for the '<em><b>Scope</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXTERNAL__SCOPE = DECLARATION__SCOPE;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXTERNAL__ATTRIBUTES = DECLARATION__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXTERNAL__TYPE = DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Variable External</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXTERNAL_FEATURE_COUNT = DECLARATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.TypeDeclarationImportImpl <em>Type Declaration Import</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.TypeDeclarationImportImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeDeclarationImport()
	 * @generated
	 */
	int TYPE_DECLARATION_IMPORT = 4;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION_IMPORT__ANNOTATIONS = DECLARATION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION_IMPORT__ID = DECLARATION__ID;

	/**
	 * The feature id for the '<em><b>Declarations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION_IMPORT__DECLARATIONS = DECLARATION__DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Outer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION_IMPORT__OUTER = DECLARATION__OUTER;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION_IMPORT__NAME = DECLARATION__NAME;

	/**
	 * The feature id for the '<em><b>Scope</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION_IMPORT__SCOPE = DECLARATION__SCOPE;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION_IMPORT__ATTRIBUTES = DECLARATION__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION_IMPORT__NAMESPACE = DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Type Declaration Import</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION_IMPORT_FEATURE_COUNT = DECLARATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.NamespaceImpl <em>Namespace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.NamespaceImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getNamespace()
	 * @generated
	 */
	int NAMESPACE = 5;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMESPACE__ANNOTATIONS = SCOPE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMESPACE__ID = SCOPE__ID;

	/**
	 * The feature id for the '<em><b>Declarations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMESPACE__DECLARATIONS = SCOPE__DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Outer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMESPACE__OUTER = SCOPE__OUTER;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMESPACE__NAME = SCOPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Actors</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMESPACE__ACTORS = SCOPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Namespace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMESPACE_FEATURE_COUNT = SCOPE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.AbstractActorImpl <em>Abstract Actor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.AbstractActorImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getAbstractActor()
	 * @generated
	 */
	int ABSTRACT_ACTOR = 6;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ACTOR__ANNOTATIONS = SCOPE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ACTOR__ID = SCOPE__ID;

	/**
	 * The feature id for the '<em><b>Declarations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ACTOR__DECLARATIONS = SCOPE__DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Outer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ACTOR__OUTER = SCOPE__OUTER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ACTOR__TYPE = SCOPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Input Ports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ACTOR__INPUT_PORTS = SCOPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Output Ports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ACTOR__OUTPUT_PORTS = SCOPE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ACTOR__PARAMETERS = SCOPE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Abstract Actor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ACTOR_FEATURE_COUNT = SCOPE_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.ExternalActorImpl <em>External Actor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.ExternalActorImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getExternalActor()
	 * @generated
	 */
	int EXTERNAL_ACTOR = 7;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_ACTOR__ANNOTATIONS = ABSTRACT_ACTOR__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_ACTOR__ID = ABSTRACT_ACTOR__ID;

	/**
	 * The feature id for the '<em><b>Declarations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_ACTOR__DECLARATIONS = ABSTRACT_ACTOR__DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Outer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_ACTOR__OUTER = ABSTRACT_ACTOR__OUTER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_ACTOR__TYPE = ABSTRACT_ACTOR__TYPE;

	/**
	 * The feature id for the '<em><b>Input Ports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_ACTOR__INPUT_PORTS = ABSTRACT_ACTOR__INPUT_PORTS;

	/**
	 * The feature id for the '<em><b>Output Ports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_ACTOR__OUTPUT_PORTS = ABSTRACT_ACTOR__OUTPUT_PORTS;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_ACTOR__PARAMETERS = ABSTRACT_ACTOR__PARAMETERS;

	/**
	 * The number of structural features of the '<em>External Actor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_ACTOR_FEATURE_COUNT = ABSTRACT_ACTOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.ActorImpl <em>Actor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.ActorImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getActor()
	 * @generated
	 */
	int ACTOR = 8;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__ANNOTATIONS = ABSTRACT_ACTOR__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__ID = ABSTRACT_ACTOR__ID;

	/**
	 * The feature id for the '<em><b>Declarations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__DECLARATIONS = ABSTRACT_ACTOR__DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Outer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__OUTER = ABSTRACT_ACTOR__OUTER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__TYPE = ABSTRACT_ACTOR__TYPE;

	/**
	 * The feature id for the '<em><b>Input Ports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__INPUT_PORTS = ABSTRACT_ACTOR__INPUT_PORTS;

	/**
	 * The feature id for the '<em><b>Output Ports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__OUTPUT_PORTS = ABSTRACT_ACTOR__OUTPUT_PORTS;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__PARAMETERS = ABSTRACT_ACTOR__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Actions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__ACTIONS = ABSTRACT_ACTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Initializers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__INITIALIZERS = ABSTRACT_ACTOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Schedule</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__SCHEDULE = ABSTRACT_ACTOR_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Actor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_FEATURE_COUNT = ABSTRACT_ACTOR_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.NetworkImpl <em>Network</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.NetworkImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getNetwork()
	 * @generated
	 */
	int NETWORK = 9;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK__ANNOTATIONS = ABSTRACT_ACTOR__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK__ID = ABSTRACT_ACTOR__ID;

	/**
	 * The feature id for the '<em><b>Declarations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK__DECLARATIONS = ABSTRACT_ACTOR__DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Outer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK__OUTER = ABSTRACT_ACTOR__OUTER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK__TYPE = ABSTRACT_ACTOR__TYPE;

	/**
	 * The feature id for the '<em><b>Input Ports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK__INPUT_PORTS = ABSTRACT_ACTOR__INPUT_PORTS;

	/**
	 * The feature id for the '<em><b>Output Ports</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK__OUTPUT_PORTS = ABSTRACT_ACTOR__OUTPUT_PORTS;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK__PARAMETERS = ABSTRACT_ACTOR__PARAMETERS;

	/**
	 * The feature id for the '<em><b>Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK__CONNECTIONS = ABSTRACT_ACTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Actors</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK__ACTORS = ABSTRACT_ACTOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Network</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK_FEATURE_COUNT = ABSTRACT_ACTOR_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.ActionImpl <em>Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.ActionImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getAction()
	 * @generated
	 */
	int ACTION = 10;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__ANNOTATIONS = SCOPE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__ID = SCOPE__ID;

	/**
	 * The feature id for the '<em><b>Declarations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__DECLARATIONS = SCOPE__DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Outer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__OUTER = SCOPE__OUTER;

	/**
	 * The feature id for the '<em><b>Guards</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__GUARDS = SCOPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__OUTPUTS = SCOPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__INPUTS = SCOPE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Tag</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__TAG = SCOPE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Statements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__STATEMENTS = SCOPE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FEATURE_COUNT = SCOPE_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.PortImpl <em>Port</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.PortImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getPort()
	 * @generated
	 */
	int PORT = 11;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__ANNOTATIONS = NODE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__ID = NODE__ID;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__TYPE = NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__NAME = NODE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Port</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_FEATURE_COUNT = NODE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.VariableImpl <em>Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.VariableImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getVariable()
	 * @generated
	 */
	int VARIABLE = 51;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__ANNOTATIONS = DECLARATION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__ID = DECLARATION__ID;

	/**
	 * The feature id for the '<em><b>Declarations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__DECLARATIONS = DECLARATION__DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Outer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__OUTER = DECLARATION__OUTER;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__NAME = DECLARATION__NAME;

	/**
	 * The feature id for the '<em><b>Scope</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__SCOPE = DECLARATION__SCOPE;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__ATTRIBUTES = DECLARATION__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Init Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__INIT_VALUE = DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Constant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__CONSTANT = DECLARATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__TYPE = DECLARATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__PARAMETER = DECLARATION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_FEATURE_COUNT = DECLARATION_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.ActorInstanceImpl <em>Actor Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.ActorInstanceImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getActorInstance()
	 * @generated
	 */
	int ACTOR_INSTANCE = 12;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_INSTANCE__ANNOTATIONS = VARIABLE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_INSTANCE__ID = VARIABLE__ID;

	/**
	 * The feature id for the '<em><b>Declarations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_INSTANCE__DECLARATIONS = VARIABLE__DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Outer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_INSTANCE__OUTER = VARIABLE__OUTER;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_INSTANCE__NAME = VARIABLE__NAME;

	/**
	 * The feature id for the '<em><b>Scope</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_INSTANCE__SCOPE = VARIABLE__SCOPE;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_INSTANCE__ATTRIBUTES = VARIABLE__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Init Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_INSTANCE__INIT_VALUE = VARIABLE__INIT_VALUE;

	/**
	 * The feature id for the '<em><b>Constant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_INSTANCE__CONSTANT = VARIABLE__CONSTANT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_INSTANCE__TYPE = VARIABLE__TYPE;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_INSTANCE__PARAMETER = VARIABLE__PARAMETER;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_INSTANCE__INPUTS = VARIABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_INSTANCE__OUTPUTS = VARIABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Actual Parameters</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_INSTANCE__ACTUAL_PARAMETERS = VARIABLE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Actor Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_INSTANCE_FEATURE_COUNT = VARIABLE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.PortInstanceImpl <em>Port Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.PortInstanceImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getPortInstance()
	 * @generated
	 */
	int PORT_INSTANCE = 13;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_INSTANCE__ANNOTATIONS = NODE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_INSTANCE__ID = NODE__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_INSTANCE__NAME = NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_INSTANCE__CONNECTIONS = NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Actor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_INSTANCE__ACTOR = NODE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Port Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_INSTANCE_FEATURE_COUNT = NODE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.ExpressionImpl <em>Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.ExpressionImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getExpression()
	 * @generated
	 */
	int EXPRESSION = 14;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION__ANNOTATIONS = NODE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION__ID = NODE__ID;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION__TYPE = NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION__CONTEXT = NODE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_FEATURE_COUNT = NODE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.TaggedExpressionImpl <em>Tagged Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.TaggedExpressionImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getTaggedExpression()
	 * @generated
	 */
	int TAGGED_EXPRESSION = 15;

	/**
	 * The feature id for the '<em><b>Tag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAGGED_EXPRESSION__TAG = 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAGGED_EXPRESSION__EXPRESSION = 1;

	/**
	 * The number of structural features of the '<em>Tagged Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAGGED_EXPRESSION_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.LiteralExpressionImpl <em>Literal Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.LiteralExpressionImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getLiteralExpression()
	 * @generated
	 */
	int LITERAL_EXPRESSION = 16;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL_EXPRESSION__ANNOTATIONS = EXPRESSION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL_EXPRESSION__ID = EXPRESSION__ID;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL_EXPRESSION__TYPE = EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL_EXPRESSION__CONTEXT = EXPRESSION__CONTEXT;

	/**
	 * The number of structural features of the '<em>Literal Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.IntegerLiteralImpl <em>Integer Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.IntegerLiteralImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getIntegerLiteral()
	 * @generated
	 */
	int INTEGER_LITERAL = 17;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_LITERAL__ANNOTATIONS = LITERAL_EXPRESSION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_LITERAL__ID = LITERAL_EXPRESSION__ID;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_LITERAL__TYPE = LITERAL_EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_LITERAL__CONTEXT = LITERAL_EXPRESSION__CONTEXT;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_LITERAL__VALUE = LITERAL_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Integer Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_LITERAL_FEATURE_COUNT = LITERAL_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.FloatLiteralImpl <em>Float Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.FloatLiteralImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getFloatLiteral()
	 * @generated
	 */
	int FLOAT_LITERAL = 18;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_LITERAL__ANNOTATIONS = LITERAL_EXPRESSION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_LITERAL__ID = LITERAL_EXPRESSION__ID;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_LITERAL__TYPE = LITERAL_EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_LITERAL__CONTEXT = LITERAL_EXPRESSION__CONTEXT;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_LITERAL__VALUE = LITERAL_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Float Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_LITERAL_FEATURE_COUNT = LITERAL_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.BooleanLiteralImpl <em>Boolean Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.BooleanLiteralImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getBooleanLiteral()
	 * @generated
	 */
	int BOOLEAN_LITERAL = 19;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LITERAL__ANNOTATIONS = LITERAL_EXPRESSION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LITERAL__ID = LITERAL_EXPRESSION__ID;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LITERAL__TYPE = LITERAL_EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LITERAL__CONTEXT = LITERAL_EXPRESSION__CONTEXT;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LITERAL__VALUE = LITERAL_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Boolean Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LITERAL_FEATURE_COUNT = LITERAL_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.StringLiteralImpl <em>String Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.StringLiteralImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getStringLiteral()
	 * @generated
	 */
	int STRING_LITERAL = 20;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL__ANNOTATIONS = LITERAL_EXPRESSION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL__ID = LITERAL_EXPRESSION__ID;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL__TYPE = LITERAL_EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL__CONTEXT = LITERAL_EXPRESSION__CONTEXT;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL__VALUE = LITERAL_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>String Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL_FEATURE_COUNT = LITERAL_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.VariableExpressionImpl <em>Variable Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.VariableExpressionImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getVariableExpression()
	 * @generated
	 */
	int VARIABLE_EXPRESSION = 21;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXPRESSION__ANNOTATIONS = EXPRESSION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXPRESSION__ID = EXPRESSION__ID;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXPRESSION__TYPE = EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXPRESSION__CONTEXT = EXPRESSION__CONTEXT;

	/**
	 * The feature id for the '<em><b>Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXPRESSION__VARIABLE = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXPRESSION__INDEX = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Member</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXPRESSION__MEMBER = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Variable Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.ListExpressionImpl <em>List Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.ListExpressionImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getListExpression()
	 * @generated
	 */
	int LIST_EXPRESSION = 22;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_EXPRESSION__ANNOTATIONS = EXPRESSION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_EXPRESSION__ID = EXPRESSION__ID;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_EXPRESSION__TYPE = EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_EXPRESSION__CONTEXT = EXPRESSION__CONTEXT;

	/**
	 * The feature id for the '<em><b>Generators</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_EXPRESSION__GENERATORS = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Expressions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_EXPRESSION__EXPRESSIONS = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>List Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.GeneratorImpl <em>Generator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.GeneratorImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getGenerator()
	 * @generated
	 */
	int GENERATOR = 23;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__ANNOTATIONS = SCOPE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__ID = SCOPE__ID;

	/**
	 * The feature id for the '<em><b>Declarations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__DECLARATIONS = SCOPE__DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Outer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__OUTER = SCOPE__OUTER;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR__SOURCE = SCOPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Generator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATOR_FEATURE_COUNT = SCOPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.MemberImpl <em>Member</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.MemberImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getMember()
	 * @generated
	 */
	int MEMBER = 24;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER__ANNOTATIONS = NODE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER__ID = NODE__ID;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER__INDEX = NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER__NAME = NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER__TYPE = NODE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Member</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_FEATURE_COUNT = NODE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.BinaryExpressionImpl <em>Binary Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.BinaryExpressionImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getBinaryExpression()
	 * @generated
	 */
	int BINARY_EXPRESSION = 25;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_EXPRESSION__ANNOTATIONS = EXPRESSION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_EXPRESSION__ID = EXPRESSION__ID;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_EXPRESSION__TYPE = EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_EXPRESSION__CONTEXT = EXPRESSION__CONTEXT;

	/**
	 * The feature id for the '<em><b>Operand1</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_EXPRESSION__OPERAND1 = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_EXPRESSION__OPERATOR = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Operand2</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_EXPRESSION__OPERAND2 = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Binary Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BINARY_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.UnaryExpressionImpl <em>Unary Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.UnaryExpressionImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getUnaryExpression()
	 * @generated
	 */
	int UNARY_EXPRESSION = 26;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_EXPRESSION__ANNOTATIONS = EXPRESSION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_EXPRESSION__ID = EXPRESSION__ID;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_EXPRESSION__TYPE = EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_EXPRESSION__CONTEXT = EXPRESSION__CONTEXT;

	/**
	 * The feature id for the '<em><b>Operand</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_EXPRESSION__OPERAND = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_EXPRESSION__OPERATOR = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Unary Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.ExpressionCallImpl <em>Expression Call</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.ExpressionCallImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getExpressionCall()
	 * @generated
	 */
	int EXPRESSION_CALL = 27;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_CALL__ANNOTATIONS = EXPRESSION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_CALL__ID = EXPRESSION__ID;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_CALL__TYPE = EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_CALL__CONTEXT = EXPRESSION__CONTEXT;

	/**
	 * The number of structural features of the '<em>Expression Call</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_CALL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.FunctionCallImpl <em>Function Call</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.FunctionCallImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getFunctionCall()
	 * @generated
	 */
	int FUNCTION_CALL = 28;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_CALL__ANNOTATIONS = EXPRESSION_CALL__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_CALL__ID = EXPRESSION_CALL__ID;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_CALL__TYPE = EXPRESSION_CALL__TYPE;

	/**
	 * The feature id for the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_CALL__CONTEXT = EXPRESSION_CALL__CONTEXT;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_CALL__PARAMETERS = EXPRESSION_CALL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Function</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_CALL__FUNCTION = EXPRESSION_CALL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Function Call</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_CALL_FEATURE_COUNT = EXPRESSION_CALL_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.TypeConstructorCallImpl <em>Type Constructor Call</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.TypeConstructorCallImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeConstructorCall()
	 * @generated
	 */
	int TYPE_CONSTRUCTOR_CALL = 29;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_CONSTRUCTOR_CALL__ANNOTATIONS = EXPRESSION_CALL__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_CONSTRUCTOR_CALL__ID = EXPRESSION_CALL__ID;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_CONSTRUCTOR_CALL__TYPE = EXPRESSION_CALL__TYPE;

	/**
	 * The feature id for the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_CONSTRUCTOR_CALL__CONTEXT = EXPRESSION_CALL__CONTEXT;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_CONSTRUCTOR_CALL__PARAMETERS = EXPRESSION_CALL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Typedef</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_CONSTRUCTOR_CALL__TYPEDEF = EXPRESSION_CALL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_CONSTRUCTOR_CALL__NAME = EXPRESSION_CALL_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Type Constructor Call</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_CONSTRUCTOR_CALL_FEATURE_COUNT = EXPRESSION_CALL_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.ConnectionImpl <em>Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.ConnectionImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getConnection()
	 * @generated
	 */
	int CONNECTION = 30;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__ANNOTATIONS = NODE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__ID = NODE__ID;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__ATTRIBUTES = NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_FEATURE_COUNT = NODE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.Point2PointConnectionImpl <em>Point2 Point Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.Point2PointConnectionImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getPoint2PointConnection()
	 * @generated
	 */
	int POINT2_POINT_CONNECTION = 31;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT2_POINT_CONNECTION__ANNOTATIONS = CONNECTION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT2_POINT_CONNECTION__ID = CONNECTION__ID;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT2_POINT_CONNECTION__ATTRIBUTES = CONNECTION__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT2_POINT_CONNECTION__SOURCE = CONNECTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT2_POINT_CONNECTION__TARGET = CONNECTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Point2 Point Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT2_POINT_CONNECTION_FEATURE_COUNT = CONNECTION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.FromSourceImpl <em>From Source</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.FromSourceImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getFromSource()
	 * @generated
	 */
	int FROM_SOURCE = 32;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FROM_SOURCE__ANNOTATIONS = CONNECTION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FROM_SOURCE__ID = CONNECTION__ID;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FROM_SOURCE__ATTRIBUTES = CONNECTION__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FROM_SOURCE__TARGET = CONNECTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FROM_SOURCE__SOURCE = CONNECTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>From Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FROM_SOURCE_FEATURE_COUNT = CONNECTION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.ToSinkImpl <em>To Sink</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.ToSinkImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getToSink()
	 * @generated
	 */
	int TO_SINK = 33;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TO_SINK__ANNOTATIONS = CONNECTION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TO_SINK__ID = CONNECTION__ID;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TO_SINK__ATTRIBUTES = CONNECTION__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TO_SINK__SOURCE = CONNECTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Sink</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TO_SINK__SINK = CONNECTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>To Sink</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TO_SINK_FEATURE_COUNT = CONNECTION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.StatementImpl <em>Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.StatementImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getStatement()
	 * @generated
	 */
	int STATEMENT = 34;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT__ANNOTATIONS = NODE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT__ID = NODE__ID;

	/**
	 * The number of structural features of the '<em>Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_FEATURE_COUNT = NODE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.VariableReferenceImpl <em>Variable Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.VariableReferenceImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getVariableReference()
	 * @generated
	 */
	int VARIABLE_REFERENCE = 35;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_REFERENCE__ANNOTATIONS = NODE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_REFERENCE__ID = NODE__ID;

	/**
	 * The feature id for the '<em><b>Declaration</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_REFERENCE__DECLARATION = NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_REFERENCE__INDEX = NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Member</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_REFERENCE__MEMBER = NODE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_REFERENCE__TYPE = NODE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Variable Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_REFERENCE_FEATURE_COUNT = NODE_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.AssignImpl <em>Assign</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.AssignImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getAssign()
	 * @generated
	 */
	int ASSIGN = 36;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGN__ANNOTATIONS = STATEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGN__ID = STATEMENT__ID;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGN__TARGET = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGN__EXPRESSION = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Assign</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGN_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.ReturnValueImpl <em>Return Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.ReturnValueImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getReturnValue()
	 * @generated
	 */
	int RETURN_VALUE = 37;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETURN_VALUE__ANNOTATIONS = STATEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETURN_VALUE__ID = STATEMENT__ID;

	/**
	 * The feature id for the '<em><b>Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETURN_VALUE__VALUE = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Return Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETURN_VALUE_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.ProcCallImpl <em>Proc Call</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.ProcCallImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getProcCall()
	 * @generated
	 */
	int PROC_CALL = 38;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROC_CALL__ANNOTATIONS = STATEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROC_CALL__ID = STATEMENT__ID;

	/**
	 * The feature id for the '<em><b>In Parameters</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROC_CALL__IN_PARAMETERS = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Out Parameters</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROC_CALL__OUT_PARAMETERS = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Procedure</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROC_CALL__PROCEDURE = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Proc Call</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROC_CALL_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.WhileLoopImpl <em>While Loop</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.WhileLoopImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getWhileLoop()
	 * @generated
	 */
	int WHILE_LOOP = 39;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHILE_LOOP__ANNOTATIONS = STATEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHILE_LOOP__ID = STATEMENT__ID;

	/**
	 * The feature id for the '<em><b>Body</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHILE_LOOP__BODY = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHILE_LOOP__CONDITION = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>While Loop</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHILE_LOOP_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.ForEachImpl <em>For Each</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.ForEachImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getForEach()
	 * @generated
	 */
	int FOR_EACH = 40;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH__ANNOTATIONS = STATEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH__ID = STATEMENT__ID;

	/**
	 * The feature id for the '<em><b>Body</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH__BODY = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Generators</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH__GENERATORS = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>For Each</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.IfStatementImpl <em>If Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.IfStatementImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getIfStatement()
	 * @generated
	 */
	int IF_STATEMENT = 41;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_STATEMENT__ANNOTATIONS = STATEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_STATEMENT__ID = STATEMENT__ID;

	/**
	 * The feature id for the '<em><b>Then Block</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_STATEMENT__THEN_BLOCK = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Else Block</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_STATEMENT__ELSE_BLOCK = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_STATEMENT__CONDITION = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>If Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.BlockImpl <em>Block</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.BlockImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getBlock()
	 * @generated
	 */
	int BLOCK = 42;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK__ANNOTATIONS = SCOPE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK__ID = SCOPE__ID;

	/**
	 * The feature id for the '<em><b>Declarations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK__DECLARATIONS = SCOPE__DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Outer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK__OUTER = SCOPE__OUTER;

	/**
	 * The feature id for the '<em><b>Statements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK__STATEMENTS = SCOPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Block</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_FEATURE_COUNT = SCOPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.CaseStatementImpl <em>Case Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.CaseStatementImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getCaseStatement()
	 * @generated
	 */
	int CASE_STATEMENT = 43;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE_STATEMENT__ANNOTATIONS = STATEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE_STATEMENT__ID = STATEMENT__ID;

	/**
	 * The feature id for the '<em><b>Alternatives</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE_STATEMENT__ALTERNATIVES = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE_STATEMENT__EXPRESSION = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Case Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.StmtAlternativeImpl <em>Stmt Alternative</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.StmtAlternativeImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getStmtAlternative()
	 * @generated
	 */
	int STMT_ALTERNATIVE = 44;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STMT_ALTERNATIVE__ANNOTATIONS = BLOCK__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STMT_ALTERNATIVE__ID = BLOCK__ID;

	/**
	 * The feature id for the '<em><b>Declarations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STMT_ALTERNATIVE__DECLARATIONS = BLOCK__DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Outer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STMT_ALTERNATIVE__OUTER = BLOCK__OUTER;

	/**
	 * The feature id for the '<em><b>Statements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STMT_ALTERNATIVE__STATEMENTS = BLOCK__STATEMENTS;

	/**
	 * The feature id for the '<em><b>Guards</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STMT_ALTERNATIVE__GUARDS = BLOCK_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Stmt Alternative</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STMT_ALTERNATIVE_FEATURE_COUNT = BLOCK_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.PortAccessImpl <em>Port Access</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.PortAccessImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getPortAccess()
	 * @generated
	 */
	int PORT_ACCESS = 45;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_ACCESS__ANNOTATIONS = NODE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_ACCESS__ID = NODE__ID;

	/**
	 * The feature id for the '<em><b>Port</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_ACCESS__PORT = NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Repeat</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_ACCESS__REPEAT = NODE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Port Access</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_ACCESS_FEATURE_COUNT = NODE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.PortWriteImpl <em>Port Write</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.PortWriteImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getPortWrite()
	 * @generated
	 */
	int PORT_WRITE = 46;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_WRITE__ANNOTATIONS = BLOCK__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_WRITE__ID = BLOCK__ID;

	/**
	 * The feature id for the '<em><b>Declarations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_WRITE__DECLARATIONS = BLOCK__DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Outer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_WRITE__OUTER = BLOCK__OUTER;

	/**
	 * The feature id for the '<em><b>Statements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_WRITE__STATEMENTS = BLOCK__STATEMENTS;

	/**
	 * The feature id for the '<em><b>Port</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_WRITE__PORT = BLOCK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Repeat</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_WRITE__REPEAT = BLOCK_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Expressions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_WRITE__EXPRESSIONS = BLOCK_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Port Write</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_WRITE_FEATURE_COUNT = BLOCK_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.PortReadImpl <em>Port Read</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.PortReadImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getPortRead()
	 * @generated
	 */
	int PORT_READ = 47;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_READ__ANNOTATIONS = PORT_ACCESS__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_READ__ID = PORT_ACCESS__ID;

	/**
	 * The feature id for the '<em><b>Port</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_READ__PORT = PORT_ACCESS__PORT;

	/**
	 * The feature id for the '<em><b>Repeat</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_READ__REPEAT = PORT_ACCESS__REPEAT;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_READ__VARIABLES = PORT_ACCESS_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Port Read</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_READ_FEATURE_COUNT = PORT_ACCESS_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.PortPeekImpl <em>Port Peek</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.PortPeekImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getPortPeek()
	 * @generated
	 */
	int PORT_PEEK = 48;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_PEEK__ANNOTATIONS = PORT_ACCESS__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_PEEK__ID = PORT_ACCESS__ID;

	/**
	 * The feature id for the '<em><b>Port</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_PEEK__PORT = PORT_ACCESS__PORT;

	/**
	 * The feature id for the '<em><b>Repeat</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_PEEK__REPEAT = PORT_ACCESS__REPEAT;

	/**
	 * The feature id for the '<em><b>Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_PEEK__VARIABLE = PORT_ACCESS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_PEEK__POSITION = PORT_ACCESS_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Port Peek</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_PEEK_FEATURE_COUNT = PORT_ACCESS_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.ForwardDeclarationImpl <em>Forward Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.ForwardDeclarationImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getForwardDeclaration()
	 * @generated
	 */
	int FORWARD_DECLARATION = 50;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_DECLARATION__ANNOTATIONS = DECLARATION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_DECLARATION__ID = DECLARATION__ID;

	/**
	 * The feature id for the '<em><b>Declarations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_DECLARATION__DECLARATIONS = DECLARATION__DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Outer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_DECLARATION__OUTER = DECLARATION__OUTER;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_DECLARATION__NAME = DECLARATION__NAME;

	/**
	 * The feature id for the '<em><b>Scope</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_DECLARATION__SCOPE = DECLARATION__SCOPE;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_DECLARATION__ATTRIBUTES = DECLARATION__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Declaration</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_DECLARATION__DECLARATION = DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_DECLARATION__TYPE = DECLARATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Forward Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_DECLARATION_FEATURE_COUNT = DECLARATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.LambdaExpressionImpl <em>Lambda Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.LambdaExpressionImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getLambdaExpression()
	 * @generated
	 */
	int LAMBDA_EXPRESSION = 52;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAMBDA_EXPRESSION__ANNOTATIONS = SCOPE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAMBDA_EXPRESSION__ID = SCOPE__ID;

	/**
	 * The feature id for the '<em><b>Declarations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAMBDA_EXPRESSION__DECLARATIONS = SCOPE__DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Outer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAMBDA_EXPRESSION__OUTER = SCOPE__OUTER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAMBDA_EXPRESSION__TYPE = SCOPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAMBDA_EXPRESSION__CONTEXT = SCOPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAMBDA_EXPRESSION__PARAMETERS = SCOPE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Body</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAMBDA_EXPRESSION__BODY = SCOPE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Lambda Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAMBDA_EXPRESSION_FEATURE_COUNT = SCOPE_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.ProcExpressionImpl <em>Proc Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.ProcExpressionImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getProcExpression()
	 * @generated
	 */
	int PROC_EXPRESSION = 53;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROC_EXPRESSION__ANNOTATIONS = SCOPE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROC_EXPRESSION__ID = SCOPE__ID;

	/**
	 * The feature id for the '<em><b>Declarations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROC_EXPRESSION__DECLARATIONS = SCOPE__DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Outer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROC_EXPRESSION__OUTER = SCOPE__OUTER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROC_EXPRESSION__TYPE = SCOPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROC_EXPRESSION__CONTEXT = SCOPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROC_EXPRESSION__PARAMETERS = SCOPE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROC_EXPRESSION__OUTPUTS = SCOPE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Body</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROC_EXPRESSION__BODY = SCOPE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Proc Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROC_EXPRESSION_FEATURE_COUNT = SCOPE_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.IfExpressionImpl <em>If Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.IfExpressionImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getIfExpression()
	 * @generated
	 */
	int IF_EXPRESSION = 54;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXPRESSION__ANNOTATIONS = EXPRESSION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXPRESSION__ID = EXPRESSION__ID;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXPRESSION__TYPE = EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXPRESSION__CONTEXT = EXPRESSION__CONTEXT;

	/**
	 * The feature id for the '<em><b>Then Expression</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXPRESSION__THEN_EXPRESSION = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Else Expression</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXPRESSION__ELSE_EXPRESSION = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXPRESSION__CONDITION = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>If Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.CaseExpressionImpl <em>Case Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.CaseExpressionImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getCaseExpression()
	 * @generated
	 */
	int CASE_EXPRESSION = 55;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE_EXPRESSION__ANNOTATIONS = EXPRESSION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE_EXPRESSION__ID = EXPRESSION__ID;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE_EXPRESSION__TYPE = EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE_EXPRESSION__CONTEXT = EXPRESSION__CONTEXT;

	/**
	 * The feature id for the '<em><b>Alternatives</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE_EXPRESSION__ALTERNATIVES = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Default</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE_EXPRESSION__DEFAULT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE_EXPRESSION__EXPRESSION = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Case Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.ExprAlternativeImpl <em>Expr Alternative</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.ExprAlternativeImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getExprAlternative()
	 * @generated
	 */
	int EXPR_ALTERNATIVE = 56;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPR_ALTERNATIVE__ANNOTATIONS = SCOPE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPR_ALTERNATIVE__ID = SCOPE__ID;

	/**
	 * The feature id for the '<em><b>Declarations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPR_ALTERNATIVE__DECLARATIONS = SCOPE__DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Outer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPR_ALTERNATIVE__OUTER = SCOPE__OUTER;

	/**
	 * The feature id for the '<em><b>Guards</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPR_ALTERNATIVE__GUARDS = SCOPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPR_ALTERNATIVE__EXPRESSION = SCOPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Expr Alternative</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPR_ALTERNATIVE_FEATURE_COUNT = SCOPE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.GuardImpl <em>Guard</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.GuardImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getGuard()
	 * @generated
	 */
	int GUARD = 57;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUARD__ANNOTATIONS = SCOPE__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUARD__ID = SCOPE__ID;

	/**
	 * The feature id for the '<em><b>Declarations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUARD__DECLARATIONS = SCOPE__DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Outer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUARD__OUTER = SCOPE__OUTER;

	/**
	 * The feature id for the '<em><b>Peeks</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUARD__PEEKS = SCOPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUARD__EXPRESSION = SCOPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Guard</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUARD_FEATURE_COUNT = SCOPE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.TypeImpl <em>Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.TypeImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getType()
	 * @generated
	 */
	int TYPE = 58;

	/**
	 * The number of structural features of the '<em>Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.TypeBoolImpl <em>Type Bool</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.TypeBoolImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeBool()
	 * @generated
	 */
	int TYPE_BOOL = 59;

	/**
	 * The number of structural features of the '<em>Type Bool</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_BOOL_FEATURE_COUNT = TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.TypeExternalImpl <em>Type External</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.TypeExternalImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeExternal()
	 * @generated
	 */
	int TYPE_EXTERNAL = 60;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_EXTERNAL__NAME = TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Scope Name</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_EXTERNAL__SCOPE_NAME = TYPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_EXTERNAL__ATTRIBUTES = TYPE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Type External</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_EXTERNAL_FEATURE_COUNT = TYPE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.TypeIntImpl <em>Type Int</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.TypeIntImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeInt()
	 * @generated
	 */
	int TYPE_INT = 61;

	/**
	 * The feature id for the '<em><b>Size</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_INT__SIZE = TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Type Int</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_INT_FEATURE_COUNT = TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.TypeListImpl <em>Type List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.TypeListImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeList()
	 * @generated
	 */
	int TYPE_LIST = 62;

	/**
	 * The feature id for the '<em><b>Size</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_LIST__SIZE = TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_LIST__TYPE = TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Type List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_LIST_FEATURE_COUNT = TYPE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.TypeFloatImpl <em>Type Float</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.TypeFloatImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeFloat()
	 * @generated
	 */
	int TYPE_FLOAT = 63;

	/**
	 * The number of structural features of the '<em>Type Float</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_FLOAT_FEATURE_COUNT = TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.TypeUintImpl <em>Type Uint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.TypeUintImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeUint()
	 * @generated
	 */
	int TYPE_UINT = 64;

	/**
	 * The feature id for the '<em><b>Size</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_UINT__SIZE = TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Type Uint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_UINT_FEATURE_COUNT = TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.TypeStringImpl <em>Type String</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.TypeStringImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeString()
	 * @generated
	 */
	int TYPE_STRING = 65;

	/**
	 * The number of structural features of the '<em>Type String</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_STRING_FEATURE_COUNT = TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.TypeTupleImpl <em>Type Tuple</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.TypeTupleImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeTuple()
	 * @generated
	 */
	int TYPE_TUPLE = 66;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_TUPLE__ANNOTATIONS = TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_TUPLE__ID = TYPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Tagged Tuples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_TUPLE__TAGGED_TUPLES = TYPE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Type Tuple</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_TUPLE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.TaggedTupleImpl <em>Tagged Tuple</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.TaggedTupleImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getTaggedTuple()
	 * @generated
	 */
	int TAGGED_TUPLE = 67;

	/**
	 * The feature id for the '<em><b>Fields</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAGGED_TUPLE__FIELDS = 0;

	/**
	 * The feature id for the '<em><b>Tag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAGGED_TUPLE__TAG = 1;

	/**
	 * The number of structural features of the '<em>Tagged Tuple</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAGGED_TUPLE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.TaggedTupleFieldReadImpl <em>Tagged Tuple Field Read</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.TaggedTupleFieldReadImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getTaggedTupleFieldRead()
	 * @generated
	 */
	int TAGGED_TUPLE_FIELD_READ = 68;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAGGED_TUPLE_FIELD_READ__ANNOTATIONS = EXPRESSION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAGGED_TUPLE_FIELD_READ__ID = EXPRESSION__ID;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAGGED_TUPLE_FIELD_READ__TYPE = EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAGGED_TUPLE_FIELD_READ__CONTEXT = EXPRESSION__CONTEXT;

	/**
	 * The feature id for the '<em><b>Tag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAGGED_TUPLE_FIELD_READ__TAG = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAGGED_TUPLE_FIELD_READ__LABEL = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAGGED_TUPLE_FIELD_READ__VALUE = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Tagged Tuple Field Read</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAGGED_TUPLE_FIELD_READ_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.TagOfImpl <em>Tag Of</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.TagOfImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getTagOf()
	 * @generated
	 */
	int TAG_OF = 69;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_OF__ANNOTATIONS = EXPRESSION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_OF__ID = EXPRESSION__ID;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_OF__TYPE = EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_OF__CONTEXT = EXPRESSION__CONTEXT;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_OF__EXPRESSION = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Tag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_OF__TAG = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Tag Of</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_OF_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.TypeUndefImpl <em>Type Undef</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.TypeUndefImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeUndef()
	 * @generated
	 */
	int TYPE_UNDEF = 70;

	/**
	 * The number of structural features of the '<em>Type Undef</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_UNDEF_FEATURE_COUNT = TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.TypeActorImpl <em>Type Actor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.TypeActorImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeActor()
	 * @generated
	 */
	int TYPE_ACTOR = 71;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_ACTOR__NAMESPACE = TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_ACTOR__NAME = TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Type Actor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_ACTOR_FEATURE_COUNT = TYPE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.TypeUserImpl <em>Type User</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.TypeUserImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeUser()
	 * @generated
	 */
	int TYPE_USER = 72;

	/**
	 * The feature id for the '<em><b>Declaration</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_USER__DECLARATION = TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Type User</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_USER_FEATURE_COUNT = TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.TypeLambdaImpl <em>Type Lambda</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.TypeLambdaImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeLambda()
	 * @generated
	 */
	int TYPE_LAMBDA = 73;

	/**
	 * The feature id for the '<em><b>Input Types</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_LAMBDA__INPUT_TYPES = TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Output Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_LAMBDA__OUTPUT_TYPE = TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Type Lambda</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_LAMBDA_FEATURE_COUNT = TYPE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.TypeProcImpl <em>Type Proc</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.TypeProcImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeProc()
	 * @generated
	 */
	int TYPE_PROC = 74;

	/**
	 * The feature id for the '<em><b>Input Types</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_PROC__INPUT_TYPES = TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Output Types</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_PROC__OUTPUT_TYPES = TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Type Proc</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_PROC_FEATURE_COUNT = TYPE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.TypeVariableImpl <em>Type Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.TypeVariableImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeVariable()
	 * @generated
	 */
	int TYPE_VARIABLE = 75;

	/**
	 * The feature id for the '<em><b>Declaration</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_VARIABLE__DECLARATION = TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Type Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_VARIABLE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.TypeDeclarationImpl <em>Type Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.TypeDeclarationImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeDeclaration()
	 * @generated
	 */
	int TYPE_DECLARATION = 76;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION__ANNOTATIONS = DECLARATION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION__ID = DECLARATION__ID;

	/**
	 * The feature id for the '<em><b>Declarations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION__DECLARATIONS = DECLARATION__DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Outer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION__OUTER = DECLARATION__OUTER;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION__NAME = DECLARATION__NAME;

	/**
	 * The feature id for the '<em><b>Scope</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION__SCOPE = DECLARATION__SCOPE;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION__ATTRIBUTES = DECLARATION__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION__TYPE = DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Type Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION_FEATURE_COUNT = DECLARATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.ForwardTypeDeclarationImpl <em>Forward Type Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.ForwardTypeDeclarationImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getForwardTypeDeclaration()
	 * @generated
	 */
	int FORWARD_TYPE_DECLARATION = 77;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_TYPE_DECLARATION__ANNOTATIONS = DECLARATION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_TYPE_DECLARATION__ID = DECLARATION__ID;

	/**
	 * The feature id for the '<em><b>Declarations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_TYPE_DECLARATION__DECLARATIONS = DECLARATION__DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Outer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_TYPE_DECLARATION__OUTER = DECLARATION__OUTER;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_TYPE_DECLARATION__NAME = DECLARATION__NAME;

	/**
	 * The feature id for the '<em><b>Scope</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_TYPE_DECLARATION__SCOPE = DECLARATION__SCOPE;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_TYPE_DECLARATION__ATTRIBUTES = DECLARATION__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Declaration</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_TYPE_DECLARATION__DECLARATION = DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Forward Type Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_TYPE_DECLARATION_FEATURE_COUNT = DECLARATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.TypeVariableDeclarationImpl <em>Type Variable Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.TypeVariableDeclarationImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeVariableDeclaration()
	 * @generated
	 */
	int TYPE_VARIABLE_DECLARATION = 78;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_VARIABLE_DECLARATION__ANNOTATIONS = DECLARATION__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_VARIABLE_DECLARATION__ID = DECLARATION__ID;

	/**
	 * The feature id for the '<em><b>Declarations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_VARIABLE_DECLARATION__DECLARATIONS = DECLARATION__DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Outer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_VARIABLE_DECLARATION__OUTER = DECLARATION__OUTER;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_VARIABLE_DECLARATION__NAME = DECLARATION__NAME;

	/**
	 * The feature id for the '<em><b>Scope</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_VARIABLE_DECLARATION__SCOPE = DECLARATION__SCOPE;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_VARIABLE_DECLARATION__ATTRIBUTES = DECLARATION__ATTRIBUTES;

	/**
	 * The number of structural features of the '<em>Type Variable Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_VARIABLE_DECLARATION_FEATURE_COUNT = DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.ScheduleImpl <em>Schedule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.ScheduleImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getSchedule()
	 * @generated
	 */
	int SCHEDULE = 79;

	/**
	 * The feature id for the '<em><b>States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE__STATES = 0;

	/**
	 * The feature id for the '<em><b>Free Runners</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE__FREE_RUNNERS = 1;

	/**
	 * The feature id for the '<em><b>Priority Graph</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE__PRIORITY_GRAPH = 2;

	/**
	 * The feature id for the '<em><b>Initial State</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE__INITIAL_STATE = 3;

	/**
	 * The number of structural features of the '<em>Schedule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.StateImpl <em>State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.StateImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getState()
	 * @generated
	 */
	int STATE = 80;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Priority Graph</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__PRIORITY_GRAPH = 1;

	/**
	 * The feature id for the '<em><b>Action2 Target Map</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__ACTION2_TARGET_MAP = 2;

	/**
	 * The number of structural features of the '<em>State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.AnnotationImpl <em>Annotation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.AnnotationImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getAnnotation()
	 * @generated
	 */
	int ANNOTATION = 81;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION__NAME = 0;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION__ARGUMENTS = 1;

	/**
	 * The number of structural features of the '<em>Annotation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.caltoopia.ir.impl.AnnotationArgumentImpl <em>Annotation Argument</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.caltoopia.ir.impl.AnnotationArgumentImpl
	 * @see org.caltoopia.ir.impl.IrPackageImpl#getAnnotationArgument()
	 * @generated
	 */
	int ANNOTATION_ARGUMENT = 82;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_ARGUMENT__ID = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_ARGUMENT__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Annotation Argument</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_ARGUMENT_FEATURE_COUNT = 2;


	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.Scope <em>Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Scope</em>'.
	 * @see org.caltoopia.ir.Scope
	 * @generated
	 */
	EClass getScope();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.Scope#getDeclarations <em>Declarations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Declarations</em>'.
	 * @see org.caltoopia.ir.Scope#getDeclarations()
	 * @see #getScope()
	 * @generated
	 */
	EReference getScope_Declarations();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.Scope#getOuter <em>Outer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Outer</em>'.
	 * @see org.caltoopia.ir.Scope#getOuter()
	 * @see #getScope()
	 * @generated
	 */
	EReference getScope_Outer();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node</em>'.
	 * @see org.caltoopia.ir.Node
	 * @generated
	 */
	EClass getNode();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.Node#getAnnotations <em>Annotations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Annotations</em>'.
	 * @see org.caltoopia.ir.Node#getAnnotations()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_Annotations();

	/**
	 * Returns the meta object for the attribute '{@link org.caltoopia.ir.Node#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.caltoopia.ir.Node#getId()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_Id();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.VariableImport <em>Variable Import</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Import</em>'.
	 * @see org.caltoopia.ir.VariableImport
	 * @generated
	 */
	EClass getVariableImport();

	/**
	 * Returns the meta object for the attribute list '{@link org.caltoopia.ir.VariableImport#getNamespace <em>Namespace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Namespace</em>'.
	 * @see org.caltoopia.ir.VariableImport#getNamespace()
	 * @see #getVariableImport()
	 * @generated
	 */
	EAttribute getVariableImport_Namespace();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.VariableExternal <em>Variable External</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable External</em>'.
	 * @see org.caltoopia.ir.VariableExternal
	 * @generated
	 */
	EClass getVariableExternal();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.VariableExternal#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.caltoopia.ir.VariableExternal#getType()
	 * @see #getVariableExternal()
	 * @generated
	 */
	EReference getVariableExternal_Type();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.TypeDeclarationImport <em>Type Declaration Import</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Declaration Import</em>'.
	 * @see org.caltoopia.ir.TypeDeclarationImport
	 * @generated
	 */
	EClass getTypeDeclarationImport();

	/**
	 * Returns the meta object for the attribute list '{@link org.caltoopia.ir.TypeDeclarationImport#getNamespace <em>Namespace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Namespace</em>'.
	 * @see org.caltoopia.ir.TypeDeclarationImport#getNamespace()
	 * @see #getTypeDeclarationImport()
	 * @generated
	 */
	EAttribute getTypeDeclarationImport_Namespace();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.Namespace <em>Namespace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Namespace</em>'.
	 * @see org.caltoopia.ir.Namespace
	 * @generated
	 */
	EClass getNamespace();

	/**
	 * Returns the meta object for the attribute list '{@link org.caltoopia.ir.Namespace#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Name</em>'.
	 * @see org.caltoopia.ir.Namespace#getName()
	 * @see #getNamespace()
	 * @generated
	 */
	EAttribute getNamespace_Name();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.Namespace#getActors <em>Actors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Actors</em>'.
	 * @see org.caltoopia.ir.Namespace#getActors()
	 * @see #getNamespace()
	 * @generated
	 */
	EReference getNamespace_Actors();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.AbstractActor <em>Abstract Actor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Actor</em>'.
	 * @see org.caltoopia.ir.AbstractActor
	 * @generated
	 */
	EClass getAbstractActor();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.AbstractActor#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.caltoopia.ir.AbstractActor#getType()
	 * @see #getAbstractActor()
	 * @generated
	 */
	EReference getAbstractActor_Type();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.AbstractActor#getInputPorts <em>Input Ports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Input Ports</em>'.
	 * @see org.caltoopia.ir.AbstractActor#getInputPorts()
	 * @see #getAbstractActor()
	 * @generated
	 */
	EReference getAbstractActor_InputPorts();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.AbstractActor#getOutputPorts <em>Output Ports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Output Ports</em>'.
	 * @see org.caltoopia.ir.AbstractActor#getOutputPorts()
	 * @see #getAbstractActor()
	 * @generated
	 */
	EReference getAbstractActor_OutputPorts();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.AbstractActor#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Parameters</em>'.
	 * @see org.caltoopia.ir.AbstractActor#getParameters()
	 * @see #getAbstractActor()
	 * @generated
	 */
	EReference getAbstractActor_Parameters();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.ExternalActor <em>External Actor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>External Actor</em>'.
	 * @see org.caltoopia.ir.ExternalActor
	 * @generated
	 */
	EClass getExternalActor();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.Actor <em>Actor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Actor</em>'.
	 * @see org.caltoopia.ir.Actor
	 * @generated
	 */
	EClass getActor();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.Actor#getActions <em>Actions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Actions</em>'.
	 * @see org.caltoopia.ir.Actor#getActions()
	 * @see #getActor()
	 * @generated
	 */
	EReference getActor_Actions();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.Actor#getInitializers <em>Initializers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Initializers</em>'.
	 * @see org.caltoopia.ir.Actor#getInitializers()
	 * @see #getActor()
	 * @generated
	 */
	EReference getActor_Initializers();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.Actor#getSchedule <em>Schedule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Schedule</em>'.
	 * @see org.caltoopia.ir.Actor#getSchedule()
	 * @see #getActor()
	 * @generated
	 */
	EReference getActor_Schedule();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.Network <em>Network</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Network</em>'.
	 * @see org.caltoopia.ir.Network
	 * @generated
	 */
	EClass getNetwork();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.Network#getConnections <em>Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Connections</em>'.
	 * @see org.caltoopia.ir.Network#getConnections()
	 * @see #getNetwork()
	 * @generated
	 */
	EReference getNetwork_Connections();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.Network#getActors <em>Actors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Actors</em>'.
	 * @see org.caltoopia.ir.Network#getActors()
	 * @see #getNetwork()
	 * @generated
	 */
	EReference getNetwork_Actors();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.Action <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action</em>'.
	 * @see org.caltoopia.ir.Action
	 * @generated
	 */
	EClass getAction();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.Action#getGuards <em>Guards</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Guards</em>'.
	 * @see org.caltoopia.ir.Action#getGuards()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Guards();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.Action#getOutputs <em>Outputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Outputs</em>'.
	 * @see org.caltoopia.ir.Action#getOutputs()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Outputs();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.Action#getInputs <em>Inputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Inputs</em>'.
	 * @see org.caltoopia.ir.Action#getInputs()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Inputs();

	/**
	 * Returns the meta object for the attribute list '{@link org.caltoopia.ir.Action#getTag <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Tag</em>'.
	 * @see org.caltoopia.ir.Action#getTag()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_Tag();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.Action#getStatements <em>Statements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Statements</em>'.
	 * @see org.caltoopia.ir.Action#getStatements()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Statements();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.Port <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port</em>'.
	 * @see org.caltoopia.ir.Port
	 * @generated
	 */
	EClass getPort();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.Port#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.caltoopia.ir.Port#getType()
	 * @see #getPort()
	 * @generated
	 */
	EReference getPort_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.caltoopia.ir.Port#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.caltoopia.ir.Port#getName()
	 * @see #getPort()
	 * @generated
	 */
	EAttribute getPort_Name();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.ActorInstance <em>Actor Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Actor Instance</em>'.
	 * @see org.caltoopia.ir.ActorInstance
	 * @generated
	 */
	EClass getActorInstance();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.ActorInstance#getInputs <em>Inputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Inputs</em>'.
	 * @see org.caltoopia.ir.ActorInstance#getInputs()
	 * @see #getActorInstance()
	 * @generated
	 */
	EReference getActorInstance_Inputs();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.ActorInstance#getOutputs <em>Outputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Outputs</em>'.
	 * @see org.caltoopia.ir.ActorInstance#getOutputs()
	 * @see #getActorInstance()
	 * @generated
	 */
	EReference getActorInstance_Outputs();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.ActorInstance#getActualParameters <em>Actual Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Actual Parameters</em>'.
	 * @see org.caltoopia.ir.ActorInstance#getActualParameters()
	 * @see #getActorInstance()
	 * @generated
	 */
	EReference getActorInstance_ActualParameters();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.PortInstance <em>Port Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port Instance</em>'.
	 * @see org.caltoopia.ir.PortInstance
	 * @generated
	 */
	EClass getPortInstance();

	/**
	 * Returns the meta object for the attribute '{@link org.caltoopia.ir.PortInstance#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.caltoopia.ir.PortInstance#getName()
	 * @see #getPortInstance()
	 * @generated
	 */
	EAttribute getPortInstance_Name();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.PortInstance#getConnections <em>Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Connections</em>'.
	 * @see org.caltoopia.ir.PortInstance#getConnections()
	 * @see #getPortInstance()
	 * @generated
	 */
	EReference getPortInstance_Connections();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.PortInstance#getActor <em>Actor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Actor</em>'.
	 * @see org.caltoopia.ir.PortInstance#getActor()
	 * @see #getPortInstance()
	 * @generated
	 */
	EReference getPortInstance_Actor();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expression</em>'.
	 * @see org.caltoopia.ir.Expression
	 * @generated
	 */
	EClass getExpression();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.Expression#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.caltoopia.ir.Expression#getType()
	 * @see #getExpression()
	 * @generated
	 */
	EReference getExpression_Type();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.Expression#getContext <em>Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Context</em>'.
	 * @see org.caltoopia.ir.Expression#getContext()
	 * @see #getExpression()
	 * @generated
	 */
	EReference getExpression_Context();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.TaggedExpression <em>Tagged Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tagged Expression</em>'.
	 * @see org.caltoopia.ir.TaggedExpression
	 * @generated
	 */
	EClass getTaggedExpression();

	/**
	 * Returns the meta object for the attribute '{@link org.caltoopia.ir.TaggedExpression#getTag <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tag</em>'.
	 * @see org.caltoopia.ir.TaggedExpression#getTag()
	 * @see #getTaggedExpression()
	 * @generated
	 */
	EAttribute getTaggedExpression_Tag();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.TaggedExpression#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Expression</em>'.
	 * @see org.caltoopia.ir.TaggedExpression#getExpression()
	 * @see #getTaggedExpression()
	 * @generated
	 */
	EReference getTaggedExpression_Expression();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.LiteralExpression <em>Literal Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Literal Expression</em>'.
	 * @see org.caltoopia.ir.LiteralExpression
	 * @generated
	 */
	EClass getLiteralExpression();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.IntegerLiteral <em>Integer Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Literal</em>'.
	 * @see org.caltoopia.ir.IntegerLiteral
	 * @generated
	 */
	EClass getIntegerLiteral();

	/**
	 * Returns the meta object for the attribute '{@link org.caltoopia.ir.IntegerLiteral#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.caltoopia.ir.IntegerLiteral#getValue()
	 * @see #getIntegerLiteral()
	 * @generated
	 */
	EAttribute getIntegerLiteral_Value();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.FloatLiteral <em>Float Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Float Literal</em>'.
	 * @see org.caltoopia.ir.FloatLiteral
	 * @generated
	 */
	EClass getFloatLiteral();

	/**
	 * Returns the meta object for the attribute '{@link org.caltoopia.ir.FloatLiteral#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.caltoopia.ir.FloatLiteral#getValue()
	 * @see #getFloatLiteral()
	 * @generated
	 */
	EAttribute getFloatLiteral_Value();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.BooleanLiteral <em>Boolean Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Literal</em>'.
	 * @see org.caltoopia.ir.BooleanLiteral
	 * @generated
	 */
	EClass getBooleanLiteral();

	/**
	 * Returns the meta object for the attribute '{@link org.caltoopia.ir.BooleanLiteral#isValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.caltoopia.ir.BooleanLiteral#isValue()
	 * @see #getBooleanLiteral()
	 * @generated
	 */
	EAttribute getBooleanLiteral_Value();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.StringLiteral <em>String Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Literal</em>'.
	 * @see org.caltoopia.ir.StringLiteral
	 * @generated
	 */
	EClass getStringLiteral();

	/**
	 * Returns the meta object for the attribute '{@link org.caltoopia.ir.StringLiteral#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.caltoopia.ir.StringLiteral#getValue()
	 * @see #getStringLiteral()
	 * @generated
	 */
	EAttribute getStringLiteral_Value();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.VariableExpression <em>Variable Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Expression</em>'.
	 * @see org.caltoopia.ir.VariableExpression
	 * @generated
	 */
	EClass getVariableExpression();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.VariableExpression#getVariable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Variable</em>'.
	 * @see org.caltoopia.ir.VariableExpression#getVariable()
	 * @see #getVariableExpression()
	 * @generated
	 */
	EReference getVariableExpression_Variable();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.VariableExpression#getIndex <em>Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Index</em>'.
	 * @see org.caltoopia.ir.VariableExpression#getIndex()
	 * @see #getVariableExpression()
	 * @generated
	 */
	EReference getVariableExpression_Index();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.VariableExpression#getMember <em>Member</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Member</em>'.
	 * @see org.caltoopia.ir.VariableExpression#getMember()
	 * @see #getVariableExpression()
	 * @generated
	 */
	EReference getVariableExpression_Member();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.ListExpression <em>List Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>List Expression</em>'.
	 * @see org.caltoopia.ir.ListExpression
	 * @generated
	 */
	EClass getListExpression();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.ListExpression#getGenerators <em>Generators</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Generators</em>'.
	 * @see org.caltoopia.ir.ListExpression#getGenerators()
	 * @see #getListExpression()
	 * @generated
	 */
	EReference getListExpression_Generators();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.ListExpression#getExpressions <em>Expressions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Expressions</em>'.
	 * @see org.caltoopia.ir.ListExpression#getExpressions()
	 * @see #getListExpression()
	 * @generated
	 */
	EReference getListExpression_Expressions();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.Generator <em>Generator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generator</em>'.
	 * @see org.caltoopia.ir.Generator
	 * @generated
	 */
	EClass getGenerator();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.Generator#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see org.caltoopia.ir.Generator#getSource()
	 * @see #getGenerator()
	 * @generated
	 */
	EReference getGenerator_Source();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.Member <em>Member</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Member</em>'.
	 * @see org.caltoopia.ir.Member
	 * @generated
	 */
	EClass getMember();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.Member#getIndex <em>Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Index</em>'.
	 * @see org.caltoopia.ir.Member#getIndex()
	 * @see #getMember()
	 * @generated
	 */
	EReference getMember_Index();

	/**
	 * Returns the meta object for the attribute '{@link org.caltoopia.ir.Member#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.caltoopia.ir.Member#getName()
	 * @see #getMember()
	 * @generated
	 */
	EAttribute getMember_Name();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.Member#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.caltoopia.ir.Member#getType()
	 * @see #getMember()
	 * @generated
	 */
	EReference getMember_Type();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.BinaryExpression <em>Binary Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Binary Expression</em>'.
	 * @see org.caltoopia.ir.BinaryExpression
	 * @generated
	 */
	EClass getBinaryExpression();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.BinaryExpression#getOperand1 <em>Operand1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operand1</em>'.
	 * @see org.caltoopia.ir.BinaryExpression#getOperand1()
	 * @see #getBinaryExpression()
	 * @generated
	 */
	EReference getBinaryExpression_Operand1();

	/**
	 * Returns the meta object for the attribute '{@link org.caltoopia.ir.BinaryExpression#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see org.caltoopia.ir.BinaryExpression#getOperator()
	 * @see #getBinaryExpression()
	 * @generated
	 */
	EAttribute getBinaryExpression_Operator();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.BinaryExpression#getOperand2 <em>Operand2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operand2</em>'.
	 * @see org.caltoopia.ir.BinaryExpression#getOperand2()
	 * @see #getBinaryExpression()
	 * @generated
	 */
	EReference getBinaryExpression_Operand2();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.UnaryExpression <em>Unary Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unary Expression</em>'.
	 * @see org.caltoopia.ir.UnaryExpression
	 * @generated
	 */
	EClass getUnaryExpression();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.UnaryExpression#getOperand <em>Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operand</em>'.
	 * @see org.caltoopia.ir.UnaryExpression#getOperand()
	 * @see #getUnaryExpression()
	 * @generated
	 */
	EReference getUnaryExpression_Operand();

	/**
	 * Returns the meta object for the attribute '{@link org.caltoopia.ir.UnaryExpression#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see org.caltoopia.ir.UnaryExpression#getOperator()
	 * @see #getUnaryExpression()
	 * @generated
	 */
	EAttribute getUnaryExpression_Operator();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.ExpressionCall <em>Expression Call</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expression Call</em>'.
	 * @see org.caltoopia.ir.ExpressionCall
	 * @generated
	 */
	EClass getExpressionCall();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.FunctionCall <em>Function Call</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Function Call</em>'.
	 * @see org.caltoopia.ir.FunctionCall
	 * @generated
	 */
	EClass getFunctionCall();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.FunctionCall#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Parameters</em>'.
	 * @see org.caltoopia.ir.FunctionCall#getParameters()
	 * @see #getFunctionCall()
	 * @generated
	 */
	EReference getFunctionCall_Parameters();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.FunctionCall#getFunction <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Function</em>'.
	 * @see org.caltoopia.ir.FunctionCall#getFunction()
	 * @see #getFunctionCall()
	 * @generated
	 */
	EReference getFunctionCall_Function();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.TypeConstructorCall <em>Type Constructor Call</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Constructor Call</em>'.
	 * @see org.caltoopia.ir.TypeConstructorCall
	 * @generated
	 */
	EClass getTypeConstructorCall();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.TypeConstructorCall#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Parameters</em>'.
	 * @see org.caltoopia.ir.TypeConstructorCall#getParameters()
	 * @see #getTypeConstructorCall()
	 * @generated
	 */
	EReference getTypeConstructorCall_Parameters();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.TypeConstructorCall#getTypedef <em>Typedef</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Typedef</em>'.
	 * @see org.caltoopia.ir.TypeConstructorCall#getTypedef()
	 * @see #getTypeConstructorCall()
	 * @generated
	 */
	EReference getTypeConstructorCall_Typedef();

	/**
	 * Returns the meta object for the attribute '{@link org.caltoopia.ir.TypeConstructorCall#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.caltoopia.ir.TypeConstructorCall#getName()
	 * @see #getTypeConstructorCall()
	 * @generated
	 */
	EAttribute getTypeConstructorCall_Name();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.Connection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connection</em>'.
	 * @see org.caltoopia.ir.Connection
	 * @generated
	 */
	EClass getConnection();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.Connection#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Attributes</em>'.
	 * @see org.caltoopia.ir.Connection#getAttributes()
	 * @see #getConnection()
	 * @generated
	 */
	EReference getConnection_Attributes();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.Point2PointConnection <em>Point2 Point Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Point2 Point Connection</em>'.
	 * @see org.caltoopia.ir.Point2PointConnection
	 * @generated
	 */
	EClass getPoint2PointConnection();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.Point2PointConnection#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see org.caltoopia.ir.Point2PointConnection#getSource()
	 * @see #getPoint2PointConnection()
	 * @generated
	 */
	EReference getPoint2PointConnection_Source();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.Point2PointConnection#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see org.caltoopia.ir.Point2PointConnection#getTarget()
	 * @see #getPoint2PointConnection()
	 * @generated
	 */
	EReference getPoint2PointConnection_Target();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.FromSource <em>From Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>From Source</em>'.
	 * @see org.caltoopia.ir.FromSource
	 * @generated
	 */
	EClass getFromSource();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.FromSource#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see org.caltoopia.ir.FromSource#getTarget()
	 * @see #getFromSource()
	 * @generated
	 */
	EReference getFromSource_Target();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.FromSource#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see org.caltoopia.ir.FromSource#getSource()
	 * @see #getFromSource()
	 * @generated
	 */
	EReference getFromSource_Source();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.ToSink <em>To Sink</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>To Sink</em>'.
	 * @see org.caltoopia.ir.ToSink
	 * @generated
	 */
	EClass getToSink();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.ToSink#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see org.caltoopia.ir.ToSink#getSource()
	 * @see #getToSink()
	 * @generated
	 */
	EReference getToSink_Source();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.ToSink#getSink <em>Sink</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Sink</em>'.
	 * @see org.caltoopia.ir.ToSink#getSink()
	 * @see #getToSink()
	 * @generated
	 */
	EReference getToSink_Sink();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.Statement <em>Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Statement</em>'.
	 * @see org.caltoopia.ir.Statement
	 * @generated
	 */
	EClass getStatement();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.VariableReference <em>Variable Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Reference</em>'.
	 * @see org.caltoopia.ir.VariableReference
	 * @generated
	 */
	EClass getVariableReference();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.VariableReference#getDeclaration <em>Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Declaration</em>'.
	 * @see org.caltoopia.ir.VariableReference#getDeclaration()
	 * @see #getVariableReference()
	 * @generated
	 */
	EReference getVariableReference_Declaration();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.VariableReference#getIndex <em>Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Index</em>'.
	 * @see org.caltoopia.ir.VariableReference#getIndex()
	 * @see #getVariableReference()
	 * @generated
	 */
	EReference getVariableReference_Index();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.VariableReference#getMember <em>Member</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Member</em>'.
	 * @see org.caltoopia.ir.VariableReference#getMember()
	 * @see #getVariableReference()
	 * @generated
	 */
	EReference getVariableReference_Member();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.VariableReference#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.caltoopia.ir.VariableReference#getType()
	 * @see #getVariableReference()
	 * @generated
	 */
	EReference getVariableReference_Type();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.Assign <em>Assign</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Assign</em>'.
	 * @see org.caltoopia.ir.Assign
	 * @generated
	 */
	EClass getAssign();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.Assign#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see org.caltoopia.ir.Assign#getTarget()
	 * @see #getAssign()
	 * @generated
	 */
	EReference getAssign_Target();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.Assign#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Expression</em>'.
	 * @see org.caltoopia.ir.Assign#getExpression()
	 * @see #getAssign()
	 * @generated
	 */
	EReference getAssign_Expression();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.ReturnValue <em>Return Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Return Value</em>'.
	 * @see org.caltoopia.ir.ReturnValue
	 * @generated
	 */
	EClass getReturnValue();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.ReturnValue#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Value</em>'.
	 * @see org.caltoopia.ir.ReturnValue#getValue()
	 * @see #getReturnValue()
	 * @generated
	 */
	EReference getReturnValue_Value();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.ProcCall <em>Proc Call</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Proc Call</em>'.
	 * @see org.caltoopia.ir.ProcCall
	 * @generated
	 */
	EClass getProcCall();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.ProcCall#getInParameters <em>In Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>In Parameters</em>'.
	 * @see org.caltoopia.ir.ProcCall#getInParameters()
	 * @see #getProcCall()
	 * @generated
	 */
	EReference getProcCall_InParameters();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.ProcCall#getOutParameters <em>Out Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Out Parameters</em>'.
	 * @see org.caltoopia.ir.ProcCall#getOutParameters()
	 * @see #getProcCall()
	 * @generated
	 */
	EReference getProcCall_OutParameters();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.ProcCall#getProcedure <em>Procedure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Procedure</em>'.
	 * @see org.caltoopia.ir.ProcCall#getProcedure()
	 * @see #getProcCall()
	 * @generated
	 */
	EReference getProcCall_Procedure();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.WhileLoop <em>While Loop</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>While Loop</em>'.
	 * @see org.caltoopia.ir.WhileLoop
	 * @generated
	 */
	EClass getWhileLoop();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.WhileLoop#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Body</em>'.
	 * @see org.caltoopia.ir.WhileLoop#getBody()
	 * @see #getWhileLoop()
	 * @generated
	 */
	EReference getWhileLoop_Body();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.WhileLoop#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Condition</em>'.
	 * @see org.caltoopia.ir.WhileLoop#getCondition()
	 * @see #getWhileLoop()
	 * @generated
	 */
	EReference getWhileLoop_Condition();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.ForEach <em>For Each</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>For Each</em>'.
	 * @see org.caltoopia.ir.ForEach
	 * @generated
	 */
	EClass getForEach();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.ForEach#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Body</em>'.
	 * @see org.caltoopia.ir.ForEach#getBody()
	 * @see #getForEach()
	 * @generated
	 */
	EReference getForEach_Body();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.ForEach#getGenerators <em>Generators</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Generators</em>'.
	 * @see org.caltoopia.ir.ForEach#getGenerators()
	 * @see #getForEach()
	 * @generated
	 */
	EReference getForEach_Generators();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.IfStatement <em>If Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>If Statement</em>'.
	 * @see org.caltoopia.ir.IfStatement
	 * @generated
	 */
	EClass getIfStatement();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.IfStatement#getThenBlock <em>Then Block</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Then Block</em>'.
	 * @see org.caltoopia.ir.IfStatement#getThenBlock()
	 * @see #getIfStatement()
	 * @generated
	 */
	EReference getIfStatement_ThenBlock();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.IfStatement#getElseBlock <em>Else Block</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Else Block</em>'.
	 * @see org.caltoopia.ir.IfStatement#getElseBlock()
	 * @see #getIfStatement()
	 * @generated
	 */
	EReference getIfStatement_ElseBlock();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.IfStatement#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Condition</em>'.
	 * @see org.caltoopia.ir.IfStatement#getCondition()
	 * @see #getIfStatement()
	 * @generated
	 */
	EReference getIfStatement_Condition();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.Block <em>Block</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Block</em>'.
	 * @see org.caltoopia.ir.Block
	 * @generated
	 */
	EClass getBlock();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.Block#getStatements <em>Statements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Statements</em>'.
	 * @see org.caltoopia.ir.Block#getStatements()
	 * @see #getBlock()
	 * @generated
	 */
	EReference getBlock_Statements();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.CaseStatement <em>Case Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Case Statement</em>'.
	 * @see org.caltoopia.ir.CaseStatement
	 * @generated
	 */
	EClass getCaseStatement();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.CaseStatement#getAlternatives <em>Alternatives</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Alternatives</em>'.
	 * @see org.caltoopia.ir.CaseStatement#getAlternatives()
	 * @see #getCaseStatement()
	 * @generated
	 */
	EReference getCaseStatement_Alternatives();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.CaseStatement#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Expression</em>'.
	 * @see org.caltoopia.ir.CaseStatement#getExpression()
	 * @see #getCaseStatement()
	 * @generated
	 */
	EReference getCaseStatement_Expression();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.StmtAlternative <em>Stmt Alternative</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stmt Alternative</em>'.
	 * @see org.caltoopia.ir.StmtAlternative
	 * @generated
	 */
	EClass getStmtAlternative();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.StmtAlternative#getGuards <em>Guards</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Guards</em>'.
	 * @see org.caltoopia.ir.StmtAlternative#getGuards()
	 * @see #getStmtAlternative()
	 * @generated
	 */
	EReference getStmtAlternative_Guards();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.PortAccess <em>Port Access</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port Access</em>'.
	 * @see org.caltoopia.ir.PortAccess
	 * @generated
	 */
	EClass getPortAccess();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.PortAccess#getPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Port</em>'.
	 * @see org.caltoopia.ir.PortAccess#getPort()
	 * @see #getPortAccess()
	 * @generated
	 */
	EReference getPortAccess_Port();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.PortAccess#getRepeat <em>Repeat</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Repeat</em>'.
	 * @see org.caltoopia.ir.PortAccess#getRepeat()
	 * @see #getPortAccess()
	 * @generated
	 */
	EReference getPortAccess_Repeat();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.PortWrite <em>Port Write</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port Write</em>'.
	 * @see org.caltoopia.ir.PortWrite
	 * @generated
	 */
	EClass getPortWrite();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.PortWrite#getExpressions <em>Expressions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Expressions</em>'.
	 * @see org.caltoopia.ir.PortWrite#getExpressions()
	 * @see #getPortWrite()
	 * @generated
	 */
	EReference getPortWrite_Expressions();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.PortRead <em>Port Read</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port Read</em>'.
	 * @see org.caltoopia.ir.PortRead
	 * @generated
	 */
	EClass getPortRead();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.PortRead#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Variables</em>'.
	 * @see org.caltoopia.ir.PortRead#getVariables()
	 * @see #getPortRead()
	 * @generated
	 */
	EReference getPortRead_Variables();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.PortPeek <em>Port Peek</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port Peek</em>'.
	 * @see org.caltoopia.ir.PortPeek
	 * @generated
	 */
	EClass getPortPeek();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.PortPeek#getVariable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Variable</em>'.
	 * @see org.caltoopia.ir.PortPeek#getVariable()
	 * @see #getPortPeek()
	 * @generated
	 */
	EReference getPortPeek_Variable();

	/**
	 * Returns the meta object for the attribute '{@link org.caltoopia.ir.PortPeek#getPosition <em>Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Position</em>'.
	 * @see org.caltoopia.ir.PortPeek#getPosition()
	 * @see #getPortPeek()
	 * @generated
	 */
	EAttribute getPortPeek_Position();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.Declaration <em>Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Declaration</em>'.
	 * @see org.caltoopia.ir.Declaration
	 * @generated
	 */
	EClass getDeclaration();

	/**
	 * Returns the meta object for the attribute '{@link org.caltoopia.ir.Declaration#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.caltoopia.ir.Declaration#getName()
	 * @see #getDeclaration()
	 * @generated
	 */
	EAttribute getDeclaration_Name();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.Declaration#getScope <em>Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Scope</em>'.
	 * @see org.caltoopia.ir.Declaration#getScope()
	 * @see #getDeclaration()
	 * @generated
	 */
	EReference getDeclaration_Scope();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.Declaration#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Attributes</em>'.
	 * @see org.caltoopia.ir.Declaration#getAttributes()
	 * @see #getDeclaration()
	 * @generated
	 */
	EReference getDeclaration_Attributes();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.ForwardDeclaration <em>Forward Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Forward Declaration</em>'.
	 * @see org.caltoopia.ir.ForwardDeclaration
	 * @generated
	 */
	EClass getForwardDeclaration();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.ForwardDeclaration#getDeclaration <em>Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Declaration</em>'.
	 * @see org.caltoopia.ir.ForwardDeclaration#getDeclaration()
	 * @see #getForwardDeclaration()
	 * @generated
	 */
	EReference getForwardDeclaration_Declaration();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.ForwardDeclaration#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.caltoopia.ir.ForwardDeclaration#getType()
	 * @see #getForwardDeclaration()
	 * @generated
	 */
	EReference getForwardDeclaration_Type();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.Variable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable</em>'.
	 * @see org.caltoopia.ir.Variable
	 * @generated
	 */
	EClass getVariable();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.Variable#getInitValue <em>Init Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Init Value</em>'.
	 * @see org.caltoopia.ir.Variable#getInitValue()
	 * @see #getVariable()
	 * @generated
	 */
	EReference getVariable_InitValue();

	/**
	 * Returns the meta object for the attribute '{@link org.caltoopia.ir.Variable#isConstant <em>Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Constant</em>'.
	 * @see org.caltoopia.ir.Variable#isConstant()
	 * @see #getVariable()
	 * @generated
	 */
	EAttribute getVariable_Constant();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.Variable#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.caltoopia.ir.Variable#getType()
	 * @see #getVariable()
	 * @generated
	 */
	EReference getVariable_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.caltoopia.ir.Variable#isParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parameter</em>'.
	 * @see org.caltoopia.ir.Variable#isParameter()
	 * @see #getVariable()
	 * @generated
	 */
	EAttribute getVariable_Parameter();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.LambdaExpression <em>Lambda Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Lambda Expression</em>'.
	 * @see org.caltoopia.ir.LambdaExpression
	 * @generated
	 */
	EClass getLambdaExpression();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.LambdaExpression#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Parameters</em>'.
	 * @see org.caltoopia.ir.LambdaExpression#getParameters()
	 * @see #getLambdaExpression()
	 * @generated
	 */
	EReference getLambdaExpression_Parameters();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.LambdaExpression#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Body</em>'.
	 * @see org.caltoopia.ir.LambdaExpression#getBody()
	 * @see #getLambdaExpression()
	 * @generated
	 */
	EReference getLambdaExpression_Body();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.ProcExpression <em>Proc Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Proc Expression</em>'.
	 * @see org.caltoopia.ir.ProcExpression
	 * @generated
	 */
	EClass getProcExpression();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.ProcExpression#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Parameters</em>'.
	 * @see org.caltoopia.ir.ProcExpression#getParameters()
	 * @see #getProcExpression()
	 * @generated
	 */
	EReference getProcExpression_Parameters();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.ProcExpression#getOutputs <em>Outputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Outputs</em>'.
	 * @see org.caltoopia.ir.ProcExpression#getOutputs()
	 * @see #getProcExpression()
	 * @generated
	 */
	EReference getProcExpression_Outputs();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.ProcExpression#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Body</em>'.
	 * @see org.caltoopia.ir.ProcExpression#getBody()
	 * @see #getProcExpression()
	 * @generated
	 */
	EReference getProcExpression_Body();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.IfExpression <em>If Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>If Expression</em>'.
	 * @see org.caltoopia.ir.IfExpression
	 * @generated
	 */
	EClass getIfExpression();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.IfExpression#getThenExpression <em>Then Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Then Expression</em>'.
	 * @see org.caltoopia.ir.IfExpression#getThenExpression()
	 * @see #getIfExpression()
	 * @generated
	 */
	EReference getIfExpression_ThenExpression();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.IfExpression#getElseExpression <em>Else Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Else Expression</em>'.
	 * @see org.caltoopia.ir.IfExpression#getElseExpression()
	 * @see #getIfExpression()
	 * @generated
	 */
	EReference getIfExpression_ElseExpression();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.IfExpression#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Condition</em>'.
	 * @see org.caltoopia.ir.IfExpression#getCondition()
	 * @see #getIfExpression()
	 * @generated
	 */
	EReference getIfExpression_Condition();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.CaseExpression <em>Case Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Case Expression</em>'.
	 * @see org.caltoopia.ir.CaseExpression
	 * @generated
	 */
	EClass getCaseExpression();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.CaseExpression#getAlternatives <em>Alternatives</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Alternatives</em>'.
	 * @see org.caltoopia.ir.CaseExpression#getAlternatives()
	 * @see #getCaseExpression()
	 * @generated
	 */
	EReference getCaseExpression_Alternatives();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.CaseExpression#getDefault <em>Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Default</em>'.
	 * @see org.caltoopia.ir.CaseExpression#getDefault()
	 * @see #getCaseExpression()
	 * @generated
	 */
	EReference getCaseExpression_Default();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.CaseExpression#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Expression</em>'.
	 * @see org.caltoopia.ir.CaseExpression#getExpression()
	 * @see #getCaseExpression()
	 * @generated
	 */
	EReference getCaseExpression_Expression();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.ExprAlternative <em>Expr Alternative</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expr Alternative</em>'.
	 * @see org.caltoopia.ir.ExprAlternative
	 * @generated
	 */
	EClass getExprAlternative();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.ExprAlternative#getGuards <em>Guards</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Guards</em>'.
	 * @see org.caltoopia.ir.ExprAlternative#getGuards()
	 * @see #getExprAlternative()
	 * @generated
	 */
	EReference getExprAlternative_Guards();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.ExprAlternative#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Expression</em>'.
	 * @see org.caltoopia.ir.ExprAlternative#getExpression()
	 * @see #getExprAlternative()
	 * @generated
	 */
	EReference getExprAlternative_Expression();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.Guard <em>Guard</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Guard</em>'.
	 * @see org.caltoopia.ir.Guard
	 * @generated
	 */
	EClass getGuard();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.Guard#getPeeks <em>Peeks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Peeks</em>'.
	 * @see org.caltoopia.ir.Guard#getPeeks()
	 * @see #getGuard()
	 * @generated
	 */
	EReference getGuard_Peeks();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.Guard#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Expression</em>'.
	 * @see org.caltoopia.ir.Guard#getExpression()
	 * @see #getGuard()
	 * @generated
	 */
	EReference getGuard_Expression();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.Type <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type</em>'.
	 * @see org.caltoopia.ir.Type
	 * @generated
	 */
	EClass getType();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.TypeBool <em>Type Bool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Bool</em>'.
	 * @see org.caltoopia.ir.TypeBool
	 * @generated
	 */
	EClass getTypeBool();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.TypeExternal <em>Type External</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type External</em>'.
	 * @see org.caltoopia.ir.TypeExternal
	 * @generated
	 */
	EClass getTypeExternal();

	/**
	 * Returns the meta object for the attribute '{@link org.caltoopia.ir.TypeExternal#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.caltoopia.ir.TypeExternal#getName()
	 * @see #getTypeExternal()
	 * @generated
	 */
	EAttribute getTypeExternal_Name();

	/**
	 * Returns the meta object for the attribute list '{@link org.caltoopia.ir.TypeExternal#getScopeName <em>Scope Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Scope Name</em>'.
	 * @see org.caltoopia.ir.TypeExternal#getScopeName()
	 * @see #getTypeExternal()
	 * @generated
	 */
	EAttribute getTypeExternal_ScopeName();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.TypeExternal#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Attributes</em>'.
	 * @see org.caltoopia.ir.TypeExternal#getAttributes()
	 * @see #getTypeExternal()
	 * @generated
	 */
	EReference getTypeExternal_Attributes();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.TypeInt <em>Type Int</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Int</em>'.
	 * @see org.caltoopia.ir.TypeInt
	 * @generated
	 */
	EClass getTypeInt();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.TypeInt#getSize <em>Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Size</em>'.
	 * @see org.caltoopia.ir.TypeInt#getSize()
	 * @see #getTypeInt()
	 * @generated
	 */
	EReference getTypeInt_Size();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.TypeList <em>Type List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type List</em>'.
	 * @see org.caltoopia.ir.TypeList
	 * @generated
	 */
	EClass getTypeList();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.TypeList#getSize <em>Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Size</em>'.
	 * @see org.caltoopia.ir.TypeList#getSize()
	 * @see #getTypeList()
	 * @generated
	 */
	EReference getTypeList_Size();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.TypeList#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.caltoopia.ir.TypeList#getType()
	 * @see #getTypeList()
	 * @generated
	 */
	EReference getTypeList_Type();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.TypeFloat <em>Type Float</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Float</em>'.
	 * @see org.caltoopia.ir.TypeFloat
	 * @generated
	 */
	EClass getTypeFloat();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.TypeUint <em>Type Uint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Uint</em>'.
	 * @see org.caltoopia.ir.TypeUint
	 * @generated
	 */
	EClass getTypeUint();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.TypeUint#getSize <em>Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Size</em>'.
	 * @see org.caltoopia.ir.TypeUint#getSize()
	 * @see #getTypeUint()
	 * @generated
	 */
	EReference getTypeUint_Size();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.TypeString <em>Type String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type String</em>'.
	 * @see org.caltoopia.ir.TypeString
	 * @generated
	 */
	EClass getTypeString();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.TypeTuple <em>Type Tuple</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Tuple</em>'.
	 * @see org.caltoopia.ir.TypeTuple
	 * @generated
	 */
	EClass getTypeTuple();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.TypeTuple#getTaggedTuples <em>Tagged Tuples</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Tagged Tuples</em>'.
	 * @see org.caltoopia.ir.TypeTuple#getTaggedTuples()
	 * @see #getTypeTuple()
	 * @generated
	 */
	EReference getTypeTuple_TaggedTuples();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.TaggedTuple <em>Tagged Tuple</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tagged Tuple</em>'.
	 * @see org.caltoopia.ir.TaggedTuple
	 * @generated
	 */
	EClass getTaggedTuple();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.TaggedTuple#getFields <em>Fields</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Fields</em>'.
	 * @see org.caltoopia.ir.TaggedTuple#getFields()
	 * @see #getTaggedTuple()
	 * @generated
	 */
	EReference getTaggedTuple_Fields();

	/**
	 * Returns the meta object for the attribute '{@link org.caltoopia.ir.TaggedTuple#getTag <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tag</em>'.
	 * @see org.caltoopia.ir.TaggedTuple#getTag()
	 * @see #getTaggedTuple()
	 * @generated
	 */
	EAttribute getTaggedTuple_Tag();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.TaggedTupleFieldRead <em>Tagged Tuple Field Read</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tagged Tuple Field Read</em>'.
	 * @see org.caltoopia.ir.TaggedTupleFieldRead
	 * @generated
	 */
	EClass getTaggedTupleFieldRead();

	/**
	 * Returns the meta object for the attribute '{@link org.caltoopia.ir.TaggedTupleFieldRead#getTag <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tag</em>'.
	 * @see org.caltoopia.ir.TaggedTupleFieldRead#getTag()
	 * @see #getTaggedTupleFieldRead()
	 * @generated
	 */
	EAttribute getTaggedTupleFieldRead_Tag();

	/**
	 * Returns the meta object for the attribute '{@link org.caltoopia.ir.TaggedTupleFieldRead#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see org.caltoopia.ir.TaggedTupleFieldRead#getLabel()
	 * @see #getTaggedTupleFieldRead()
	 * @generated
	 */
	EAttribute getTaggedTupleFieldRead_Label();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.TaggedTupleFieldRead#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Value</em>'.
	 * @see org.caltoopia.ir.TaggedTupleFieldRead#getValue()
	 * @see #getTaggedTupleFieldRead()
	 * @generated
	 */
	EReference getTaggedTupleFieldRead_Value();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.TagOf <em>Tag Of</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag Of</em>'.
	 * @see org.caltoopia.ir.TagOf
	 * @generated
	 */
	EClass getTagOf();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.TagOf#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Expression</em>'.
	 * @see org.caltoopia.ir.TagOf#getExpression()
	 * @see #getTagOf()
	 * @generated
	 */
	EReference getTagOf_Expression();

	/**
	 * Returns the meta object for the attribute '{@link org.caltoopia.ir.TagOf#getTag <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tag</em>'.
	 * @see org.caltoopia.ir.TagOf#getTag()
	 * @see #getTagOf()
	 * @generated
	 */
	EAttribute getTagOf_Tag();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.TypeUndef <em>Type Undef</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Undef</em>'.
	 * @see org.caltoopia.ir.TypeUndef
	 * @generated
	 */
	EClass getTypeUndef();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.TypeActor <em>Type Actor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Actor</em>'.
	 * @see org.caltoopia.ir.TypeActor
	 * @generated
	 */
	EClass getTypeActor();

	/**
	 * Returns the meta object for the attribute list '{@link org.caltoopia.ir.TypeActor#getNamespace <em>Namespace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Namespace</em>'.
	 * @see org.caltoopia.ir.TypeActor#getNamespace()
	 * @see #getTypeActor()
	 * @generated
	 */
	EAttribute getTypeActor_Namespace();

	/**
	 * Returns the meta object for the attribute '{@link org.caltoopia.ir.TypeActor#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.caltoopia.ir.TypeActor#getName()
	 * @see #getTypeActor()
	 * @generated
	 */
	EAttribute getTypeActor_Name();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.TypeUser <em>Type User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type User</em>'.
	 * @see org.caltoopia.ir.TypeUser
	 * @generated
	 */
	EClass getTypeUser();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.TypeUser#getDeclaration <em>Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Declaration</em>'.
	 * @see org.caltoopia.ir.TypeUser#getDeclaration()
	 * @see #getTypeUser()
	 * @generated
	 */
	EReference getTypeUser_Declaration();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.TypeLambda <em>Type Lambda</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Lambda</em>'.
	 * @see org.caltoopia.ir.TypeLambda
	 * @generated
	 */
	EClass getTypeLambda();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.TypeLambda#getInputTypes <em>Input Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Input Types</em>'.
	 * @see org.caltoopia.ir.TypeLambda#getInputTypes()
	 * @see #getTypeLambda()
	 * @generated
	 */
	EReference getTypeLambda_InputTypes();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.TypeLambda#getOutputType <em>Output Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Output Type</em>'.
	 * @see org.caltoopia.ir.TypeLambda#getOutputType()
	 * @see #getTypeLambda()
	 * @generated
	 */
	EReference getTypeLambda_OutputType();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.TypeProc <em>Type Proc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Proc</em>'.
	 * @see org.caltoopia.ir.TypeProc
	 * @generated
	 */
	EClass getTypeProc();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.TypeProc#getInputTypes <em>Input Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Input Types</em>'.
	 * @see org.caltoopia.ir.TypeProc#getInputTypes()
	 * @see #getTypeProc()
	 * @generated
	 */
	EReference getTypeProc_InputTypes();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.TypeProc#getOutputTypes <em>Output Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Output Types</em>'.
	 * @see org.caltoopia.ir.TypeProc#getOutputTypes()
	 * @see #getTypeProc()
	 * @generated
	 */
	EReference getTypeProc_OutputTypes();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.TypeVariable <em>Type Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Variable</em>'.
	 * @see org.caltoopia.ir.TypeVariable
	 * @generated
	 */
	EClass getTypeVariable();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.TypeVariable#getDeclaration <em>Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Declaration</em>'.
	 * @see org.caltoopia.ir.TypeVariable#getDeclaration()
	 * @see #getTypeVariable()
	 * @generated
	 */
	EReference getTypeVariable_Declaration();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.TypeDeclaration <em>Type Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Declaration</em>'.
	 * @see org.caltoopia.ir.TypeDeclaration
	 * @generated
	 */
	EClass getTypeDeclaration();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.TypeDeclaration#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.caltoopia.ir.TypeDeclaration#getType()
	 * @see #getTypeDeclaration()
	 * @generated
	 */
	EReference getTypeDeclaration_Type();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.ForwardTypeDeclaration <em>Forward Type Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Forward Type Declaration</em>'.
	 * @see org.caltoopia.ir.ForwardTypeDeclaration
	 * @generated
	 */
	EClass getForwardTypeDeclaration();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.ForwardTypeDeclaration#getDeclaration <em>Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Declaration</em>'.
	 * @see org.caltoopia.ir.ForwardTypeDeclaration#getDeclaration()
	 * @see #getForwardTypeDeclaration()
	 * @generated
	 */
	EReference getForwardTypeDeclaration_Declaration();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.TypeVariableDeclaration <em>Type Variable Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Variable Declaration</em>'.
	 * @see org.caltoopia.ir.TypeVariableDeclaration
	 * @generated
	 */
	EClass getTypeVariableDeclaration();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.Schedule <em>Schedule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Schedule</em>'.
	 * @see org.caltoopia.ir.Schedule
	 * @generated
	 */
	EClass getSchedule();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.Schedule#getStates <em>States</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>States</em>'.
	 * @see org.caltoopia.ir.Schedule#getStates()
	 * @see #getSchedule()
	 * @generated
	 */
	EReference getSchedule_States();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.Schedule#getFreeRunners <em>Free Runners</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Free Runners</em>'.
	 * @see org.caltoopia.ir.Schedule#getFreeRunners()
	 * @see #getSchedule()
	 * @generated
	 */
	EReference getSchedule_FreeRunners();

	/**
	 * Returns the meta object for the attribute '{@link org.caltoopia.ir.Schedule#getPriorityGraph <em>Priority Graph</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Priority Graph</em>'.
	 * @see org.caltoopia.ir.Schedule#getPriorityGraph()
	 * @see #getSchedule()
	 * @generated
	 */
	EAttribute getSchedule_PriorityGraph();

	/**
	 * Returns the meta object for the reference '{@link org.caltoopia.ir.Schedule#getInitialState <em>Initial State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Initial State</em>'.
	 * @see org.caltoopia.ir.Schedule#getInitialState()
	 * @see #getSchedule()
	 * @generated
	 */
	EReference getSchedule_InitialState();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.State <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State</em>'.
	 * @see org.caltoopia.ir.State
	 * @generated
	 */
	EClass getState();

	/**
	 * Returns the meta object for the attribute '{@link org.caltoopia.ir.State#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.caltoopia.ir.State#getName()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.caltoopia.ir.State#getPriorityGraph <em>Priority Graph</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Priority Graph</em>'.
	 * @see org.caltoopia.ir.State#getPriorityGraph()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_PriorityGraph();

	/**
	 * Returns the meta object for the attribute '{@link org.caltoopia.ir.State#getAction2TargetMap <em>Action2 Target Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Action2 Target Map</em>'.
	 * @see org.caltoopia.ir.State#getAction2TargetMap()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_Action2TargetMap();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.Annotation <em>Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Annotation</em>'.
	 * @see org.caltoopia.ir.Annotation
	 * @generated
	 */
	EClass getAnnotation();

	/**
	 * Returns the meta object for the attribute '{@link org.caltoopia.ir.Annotation#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.caltoopia.ir.Annotation#getName()
	 * @see #getAnnotation()
	 * @generated
	 */
	EAttribute getAnnotation_Name();

	/**
	 * Returns the meta object for the reference list '{@link org.caltoopia.ir.Annotation#getArguments <em>Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Arguments</em>'.
	 * @see org.caltoopia.ir.Annotation#getArguments()
	 * @see #getAnnotation()
	 * @generated
	 */
	EReference getAnnotation_Arguments();

	/**
	 * Returns the meta object for class '{@link org.caltoopia.ir.AnnotationArgument <em>Annotation Argument</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Annotation Argument</em>'.
	 * @see org.caltoopia.ir.AnnotationArgument
	 * @generated
	 */
	EClass getAnnotationArgument();

	/**
	 * Returns the meta object for the attribute '{@link org.caltoopia.ir.AnnotationArgument#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.caltoopia.ir.AnnotationArgument#getId()
	 * @see #getAnnotationArgument()
	 * @generated
	 */
	EAttribute getAnnotationArgument_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.caltoopia.ir.AnnotationArgument#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.caltoopia.ir.AnnotationArgument#getValue()
	 * @see #getAnnotationArgument()
	 * @generated
	 */
	EAttribute getAnnotationArgument_Value();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	IrFactory getIrFactory();

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
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.ScopeImpl <em>Scope</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.ScopeImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getScope()
		 * @generated
		 */
		EClass SCOPE = eINSTANCE.getScope();

		/**
		 * The meta object literal for the '<em><b>Declarations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCOPE__DECLARATIONS = eINSTANCE.getScope_Declarations();

		/**
		 * The meta object literal for the '<em><b>Outer</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCOPE__OUTER = eINSTANCE.getScope_Outer();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.NodeImpl <em>Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.NodeImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getNode()
		 * @generated
		 */
		EClass NODE = eINSTANCE.getNode();

		/**
		 * The meta object literal for the '<em><b>Annotations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE__ANNOTATIONS = eINSTANCE.getNode_Annotations();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__ID = eINSTANCE.getNode_Id();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.VariableImportImpl <em>Variable Import</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.VariableImportImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getVariableImport()
		 * @generated
		 */
		EClass VARIABLE_IMPORT = eINSTANCE.getVariableImport();

		/**
		 * The meta object literal for the '<em><b>Namespace</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE_IMPORT__NAMESPACE = eINSTANCE.getVariableImport_Namespace();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.VariableExternalImpl <em>Variable External</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.VariableExternalImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getVariableExternal()
		 * @generated
		 */
		EClass VARIABLE_EXTERNAL = eINSTANCE.getVariableExternal();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_EXTERNAL__TYPE = eINSTANCE.getVariableExternal_Type();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.TypeDeclarationImportImpl <em>Type Declaration Import</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.TypeDeclarationImportImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeDeclarationImport()
		 * @generated
		 */
		EClass TYPE_DECLARATION_IMPORT = eINSTANCE.getTypeDeclarationImport();

		/**
		 * The meta object literal for the '<em><b>Namespace</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TYPE_DECLARATION_IMPORT__NAMESPACE = eINSTANCE.getTypeDeclarationImport_Namespace();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.NamespaceImpl <em>Namespace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.NamespaceImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getNamespace()
		 * @generated
		 */
		EClass NAMESPACE = eINSTANCE.getNamespace();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMESPACE__NAME = eINSTANCE.getNamespace_Name();

		/**
		 * The meta object literal for the '<em><b>Actors</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NAMESPACE__ACTORS = eINSTANCE.getNamespace_Actors();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.AbstractActorImpl <em>Abstract Actor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.AbstractActorImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getAbstractActor()
		 * @generated
		 */
		EClass ABSTRACT_ACTOR = eINSTANCE.getAbstractActor();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_ACTOR__TYPE = eINSTANCE.getAbstractActor_Type();

		/**
		 * The meta object literal for the '<em><b>Input Ports</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_ACTOR__INPUT_PORTS = eINSTANCE.getAbstractActor_InputPorts();

		/**
		 * The meta object literal for the '<em><b>Output Ports</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_ACTOR__OUTPUT_PORTS = eINSTANCE.getAbstractActor_OutputPorts();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_ACTOR__PARAMETERS = eINSTANCE.getAbstractActor_Parameters();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.ExternalActorImpl <em>External Actor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.ExternalActorImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getExternalActor()
		 * @generated
		 */
		EClass EXTERNAL_ACTOR = eINSTANCE.getExternalActor();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.ActorImpl <em>Actor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.ActorImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getActor()
		 * @generated
		 */
		EClass ACTOR = eINSTANCE.getActor();

		/**
		 * The meta object literal for the '<em><b>Actions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR__ACTIONS = eINSTANCE.getActor_Actions();

		/**
		 * The meta object literal for the '<em><b>Initializers</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR__INITIALIZERS = eINSTANCE.getActor_Initializers();

		/**
		 * The meta object literal for the '<em><b>Schedule</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR__SCHEDULE = eINSTANCE.getActor_Schedule();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.NetworkImpl <em>Network</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.NetworkImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getNetwork()
		 * @generated
		 */
		EClass NETWORK = eINSTANCE.getNetwork();

		/**
		 * The meta object literal for the '<em><b>Connections</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NETWORK__CONNECTIONS = eINSTANCE.getNetwork_Connections();

		/**
		 * The meta object literal for the '<em><b>Actors</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NETWORK__ACTORS = eINSTANCE.getNetwork_Actors();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.ActionImpl <em>Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.ActionImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getAction()
		 * @generated
		 */
		EClass ACTION = eINSTANCE.getAction();

		/**
		 * The meta object literal for the '<em><b>Guards</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__GUARDS = eINSTANCE.getAction_Guards();

		/**
		 * The meta object literal for the '<em><b>Outputs</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__OUTPUTS = eINSTANCE.getAction_Outputs();

		/**
		 * The meta object literal for the '<em><b>Inputs</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__INPUTS = eINSTANCE.getAction_Inputs();

		/**
		 * The meta object literal for the '<em><b>Tag</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__TAG = eINSTANCE.getAction_Tag();

		/**
		 * The meta object literal for the '<em><b>Statements</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__STATEMENTS = eINSTANCE.getAction_Statements();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.PortImpl <em>Port</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.PortImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getPort()
		 * @generated
		 */
		EClass PORT = eINSTANCE.getPort();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT__TYPE = eINSTANCE.getPort_Type();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORT__NAME = eINSTANCE.getPort_Name();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.ActorInstanceImpl <em>Actor Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.ActorInstanceImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getActorInstance()
		 * @generated
		 */
		EClass ACTOR_INSTANCE = eINSTANCE.getActorInstance();

		/**
		 * The meta object literal for the '<em><b>Inputs</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR_INSTANCE__INPUTS = eINSTANCE.getActorInstance_Inputs();

		/**
		 * The meta object literal for the '<em><b>Outputs</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR_INSTANCE__OUTPUTS = eINSTANCE.getActorInstance_Outputs();

		/**
		 * The meta object literal for the '<em><b>Actual Parameters</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR_INSTANCE__ACTUAL_PARAMETERS = eINSTANCE.getActorInstance_ActualParameters();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.PortInstanceImpl <em>Port Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.PortInstanceImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getPortInstance()
		 * @generated
		 */
		EClass PORT_INSTANCE = eINSTANCE.getPortInstance();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORT_INSTANCE__NAME = eINSTANCE.getPortInstance_Name();

		/**
		 * The meta object literal for the '<em><b>Connections</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_INSTANCE__CONNECTIONS = eINSTANCE.getPortInstance_Connections();

		/**
		 * The meta object literal for the '<em><b>Actor</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_INSTANCE__ACTOR = eINSTANCE.getPortInstance_Actor();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.ExpressionImpl <em>Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.ExpressionImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getExpression()
		 * @generated
		 */
		EClass EXPRESSION = eINSTANCE.getExpression();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPRESSION__TYPE = eINSTANCE.getExpression_Type();

		/**
		 * The meta object literal for the '<em><b>Context</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPRESSION__CONTEXT = eINSTANCE.getExpression_Context();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.TaggedExpressionImpl <em>Tagged Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.TaggedExpressionImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getTaggedExpression()
		 * @generated
		 */
		EClass TAGGED_EXPRESSION = eINSTANCE.getTaggedExpression();

		/**
		 * The meta object literal for the '<em><b>Tag</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAGGED_EXPRESSION__TAG = eINSTANCE.getTaggedExpression_Tag();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAGGED_EXPRESSION__EXPRESSION = eINSTANCE.getTaggedExpression_Expression();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.LiteralExpressionImpl <em>Literal Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.LiteralExpressionImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getLiteralExpression()
		 * @generated
		 */
		EClass LITERAL_EXPRESSION = eINSTANCE.getLiteralExpression();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.IntegerLiteralImpl <em>Integer Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.IntegerLiteralImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getIntegerLiteral()
		 * @generated
		 */
		EClass INTEGER_LITERAL = eINSTANCE.getIntegerLiteral();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTEGER_LITERAL__VALUE = eINSTANCE.getIntegerLiteral_Value();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.FloatLiteralImpl <em>Float Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.FloatLiteralImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getFloatLiteral()
		 * @generated
		 */
		EClass FLOAT_LITERAL = eINSTANCE.getFloatLiteral();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FLOAT_LITERAL__VALUE = eINSTANCE.getFloatLiteral_Value();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.BooleanLiteralImpl <em>Boolean Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.BooleanLiteralImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getBooleanLiteral()
		 * @generated
		 */
		EClass BOOLEAN_LITERAL = eINSTANCE.getBooleanLiteral();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOLEAN_LITERAL__VALUE = eINSTANCE.getBooleanLiteral_Value();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.StringLiteralImpl <em>String Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.StringLiteralImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getStringLiteral()
		 * @generated
		 */
		EClass STRING_LITERAL = eINSTANCE.getStringLiteral();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_LITERAL__VALUE = eINSTANCE.getStringLiteral_Value();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.VariableExpressionImpl <em>Variable Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.VariableExpressionImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getVariableExpression()
		 * @generated
		 */
		EClass VARIABLE_EXPRESSION = eINSTANCE.getVariableExpression();

		/**
		 * The meta object literal for the '<em><b>Variable</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_EXPRESSION__VARIABLE = eINSTANCE.getVariableExpression_Variable();

		/**
		 * The meta object literal for the '<em><b>Index</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_EXPRESSION__INDEX = eINSTANCE.getVariableExpression_Index();

		/**
		 * The meta object literal for the '<em><b>Member</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_EXPRESSION__MEMBER = eINSTANCE.getVariableExpression_Member();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.ListExpressionImpl <em>List Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.ListExpressionImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getListExpression()
		 * @generated
		 */
		EClass LIST_EXPRESSION = eINSTANCE.getListExpression();

		/**
		 * The meta object literal for the '<em><b>Generators</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIST_EXPRESSION__GENERATORS = eINSTANCE.getListExpression_Generators();

		/**
		 * The meta object literal for the '<em><b>Expressions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIST_EXPRESSION__EXPRESSIONS = eINSTANCE.getListExpression_Expressions();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.GeneratorImpl <em>Generator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.GeneratorImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getGenerator()
		 * @generated
		 */
		EClass GENERATOR = eINSTANCE.getGenerator();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GENERATOR__SOURCE = eINSTANCE.getGenerator_Source();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.MemberImpl <em>Member</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.MemberImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getMember()
		 * @generated
		 */
		EClass MEMBER = eINSTANCE.getMember();

		/**
		 * The meta object literal for the '<em><b>Index</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEMBER__INDEX = eINSTANCE.getMember_Index();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEMBER__NAME = eINSTANCE.getMember_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEMBER__TYPE = eINSTANCE.getMember_Type();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.BinaryExpressionImpl <em>Binary Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.BinaryExpressionImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getBinaryExpression()
		 * @generated
		 */
		EClass BINARY_EXPRESSION = eINSTANCE.getBinaryExpression();

		/**
		 * The meta object literal for the '<em><b>Operand1</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINARY_EXPRESSION__OPERAND1 = eINSTANCE.getBinaryExpression_Operand1();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BINARY_EXPRESSION__OPERATOR = eINSTANCE.getBinaryExpression_Operator();

		/**
		 * The meta object literal for the '<em><b>Operand2</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BINARY_EXPRESSION__OPERAND2 = eINSTANCE.getBinaryExpression_Operand2();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.UnaryExpressionImpl <em>Unary Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.UnaryExpressionImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getUnaryExpression()
		 * @generated
		 */
		EClass UNARY_EXPRESSION = eINSTANCE.getUnaryExpression();

		/**
		 * The meta object literal for the '<em><b>Operand</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNARY_EXPRESSION__OPERAND = eINSTANCE.getUnaryExpression_Operand();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNARY_EXPRESSION__OPERATOR = eINSTANCE.getUnaryExpression_Operator();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.ExpressionCallImpl <em>Expression Call</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.ExpressionCallImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getExpressionCall()
		 * @generated
		 */
		EClass EXPRESSION_CALL = eINSTANCE.getExpressionCall();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.FunctionCallImpl <em>Function Call</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.FunctionCallImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getFunctionCall()
		 * @generated
		 */
		EClass FUNCTION_CALL = eINSTANCE.getFunctionCall();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FUNCTION_CALL__PARAMETERS = eINSTANCE.getFunctionCall_Parameters();

		/**
		 * The meta object literal for the '<em><b>Function</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FUNCTION_CALL__FUNCTION = eINSTANCE.getFunctionCall_Function();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.TypeConstructorCallImpl <em>Type Constructor Call</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.TypeConstructorCallImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeConstructorCall()
		 * @generated
		 */
		EClass TYPE_CONSTRUCTOR_CALL = eINSTANCE.getTypeConstructorCall();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_CONSTRUCTOR_CALL__PARAMETERS = eINSTANCE.getTypeConstructorCall_Parameters();

		/**
		 * The meta object literal for the '<em><b>Typedef</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_CONSTRUCTOR_CALL__TYPEDEF = eINSTANCE.getTypeConstructorCall_Typedef();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TYPE_CONSTRUCTOR_CALL__NAME = eINSTANCE.getTypeConstructorCall_Name();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.ConnectionImpl <em>Connection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.ConnectionImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getConnection()
		 * @generated
		 */
		EClass CONNECTION = eINSTANCE.getConnection();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION__ATTRIBUTES = eINSTANCE.getConnection_Attributes();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.Point2PointConnectionImpl <em>Point2 Point Connection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.Point2PointConnectionImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getPoint2PointConnection()
		 * @generated
		 */
		EClass POINT2_POINT_CONNECTION = eINSTANCE.getPoint2PointConnection();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POINT2_POINT_CONNECTION__SOURCE = eINSTANCE.getPoint2PointConnection_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POINT2_POINT_CONNECTION__TARGET = eINSTANCE.getPoint2PointConnection_Target();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.FromSourceImpl <em>From Source</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.FromSourceImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getFromSource()
		 * @generated
		 */
		EClass FROM_SOURCE = eINSTANCE.getFromSource();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FROM_SOURCE__TARGET = eINSTANCE.getFromSource_Target();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FROM_SOURCE__SOURCE = eINSTANCE.getFromSource_Source();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.ToSinkImpl <em>To Sink</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.ToSinkImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getToSink()
		 * @generated
		 */
		EClass TO_SINK = eINSTANCE.getToSink();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TO_SINK__SOURCE = eINSTANCE.getToSink_Source();

		/**
		 * The meta object literal for the '<em><b>Sink</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TO_SINK__SINK = eINSTANCE.getToSink_Sink();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.StatementImpl <em>Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.StatementImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getStatement()
		 * @generated
		 */
		EClass STATEMENT = eINSTANCE.getStatement();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.VariableReferenceImpl <em>Variable Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.VariableReferenceImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getVariableReference()
		 * @generated
		 */
		EClass VARIABLE_REFERENCE = eINSTANCE.getVariableReference();

		/**
		 * The meta object literal for the '<em><b>Declaration</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_REFERENCE__DECLARATION = eINSTANCE.getVariableReference_Declaration();

		/**
		 * The meta object literal for the '<em><b>Index</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_REFERENCE__INDEX = eINSTANCE.getVariableReference_Index();

		/**
		 * The meta object literal for the '<em><b>Member</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_REFERENCE__MEMBER = eINSTANCE.getVariableReference_Member();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_REFERENCE__TYPE = eINSTANCE.getVariableReference_Type();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.AssignImpl <em>Assign</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.AssignImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getAssign()
		 * @generated
		 */
		EClass ASSIGN = eINSTANCE.getAssign();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSIGN__TARGET = eINSTANCE.getAssign_Target();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSIGN__EXPRESSION = eINSTANCE.getAssign_Expression();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.ReturnValueImpl <em>Return Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.ReturnValueImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getReturnValue()
		 * @generated
		 */
		EClass RETURN_VALUE = eINSTANCE.getReturnValue();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RETURN_VALUE__VALUE = eINSTANCE.getReturnValue_Value();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.ProcCallImpl <em>Proc Call</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.ProcCallImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getProcCall()
		 * @generated
		 */
		EClass PROC_CALL = eINSTANCE.getProcCall();

		/**
		 * The meta object literal for the '<em><b>In Parameters</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROC_CALL__IN_PARAMETERS = eINSTANCE.getProcCall_InParameters();

		/**
		 * The meta object literal for the '<em><b>Out Parameters</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROC_CALL__OUT_PARAMETERS = eINSTANCE.getProcCall_OutParameters();

		/**
		 * The meta object literal for the '<em><b>Procedure</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROC_CALL__PROCEDURE = eINSTANCE.getProcCall_Procedure();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.WhileLoopImpl <em>While Loop</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.WhileLoopImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getWhileLoop()
		 * @generated
		 */
		EClass WHILE_LOOP = eINSTANCE.getWhileLoop();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WHILE_LOOP__BODY = eINSTANCE.getWhileLoop_Body();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WHILE_LOOP__CONDITION = eINSTANCE.getWhileLoop_Condition();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.ForEachImpl <em>For Each</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.ForEachImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getForEach()
		 * @generated
		 */
		EClass FOR_EACH = eINSTANCE.getForEach();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FOR_EACH__BODY = eINSTANCE.getForEach_Body();

		/**
		 * The meta object literal for the '<em><b>Generators</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FOR_EACH__GENERATORS = eINSTANCE.getForEach_Generators();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.IfStatementImpl <em>If Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.IfStatementImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getIfStatement()
		 * @generated
		 */
		EClass IF_STATEMENT = eINSTANCE.getIfStatement();

		/**
		 * The meta object literal for the '<em><b>Then Block</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IF_STATEMENT__THEN_BLOCK = eINSTANCE.getIfStatement_ThenBlock();

		/**
		 * The meta object literal for the '<em><b>Else Block</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IF_STATEMENT__ELSE_BLOCK = eINSTANCE.getIfStatement_ElseBlock();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IF_STATEMENT__CONDITION = eINSTANCE.getIfStatement_Condition();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.BlockImpl <em>Block</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.BlockImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getBlock()
		 * @generated
		 */
		EClass BLOCK = eINSTANCE.getBlock();

		/**
		 * The meta object literal for the '<em><b>Statements</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BLOCK__STATEMENTS = eINSTANCE.getBlock_Statements();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.CaseStatementImpl <em>Case Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.CaseStatementImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getCaseStatement()
		 * @generated
		 */
		EClass CASE_STATEMENT = eINSTANCE.getCaseStatement();

		/**
		 * The meta object literal for the '<em><b>Alternatives</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CASE_STATEMENT__ALTERNATIVES = eINSTANCE.getCaseStatement_Alternatives();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CASE_STATEMENT__EXPRESSION = eINSTANCE.getCaseStatement_Expression();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.StmtAlternativeImpl <em>Stmt Alternative</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.StmtAlternativeImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getStmtAlternative()
		 * @generated
		 */
		EClass STMT_ALTERNATIVE = eINSTANCE.getStmtAlternative();

		/**
		 * The meta object literal for the '<em><b>Guards</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STMT_ALTERNATIVE__GUARDS = eINSTANCE.getStmtAlternative_Guards();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.PortAccessImpl <em>Port Access</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.PortAccessImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getPortAccess()
		 * @generated
		 */
		EClass PORT_ACCESS = eINSTANCE.getPortAccess();

		/**
		 * The meta object literal for the '<em><b>Port</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_ACCESS__PORT = eINSTANCE.getPortAccess_Port();

		/**
		 * The meta object literal for the '<em><b>Repeat</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_ACCESS__REPEAT = eINSTANCE.getPortAccess_Repeat();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.PortWriteImpl <em>Port Write</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.PortWriteImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getPortWrite()
		 * @generated
		 */
		EClass PORT_WRITE = eINSTANCE.getPortWrite();

		/**
		 * The meta object literal for the '<em><b>Expressions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_WRITE__EXPRESSIONS = eINSTANCE.getPortWrite_Expressions();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.PortReadImpl <em>Port Read</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.PortReadImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getPortRead()
		 * @generated
		 */
		EClass PORT_READ = eINSTANCE.getPortRead();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_READ__VARIABLES = eINSTANCE.getPortRead_Variables();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.PortPeekImpl <em>Port Peek</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.PortPeekImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getPortPeek()
		 * @generated
		 */
		EClass PORT_PEEK = eINSTANCE.getPortPeek();

		/**
		 * The meta object literal for the '<em><b>Variable</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_PEEK__VARIABLE = eINSTANCE.getPortPeek_Variable();

		/**
		 * The meta object literal for the '<em><b>Position</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORT_PEEK__POSITION = eINSTANCE.getPortPeek_Position();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.DeclarationImpl <em>Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.DeclarationImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getDeclaration()
		 * @generated
		 */
		EClass DECLARATION = eINSTANCE.getDeclaration();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DECLARATION__NAME = eINSTANCE.getDeclaration_Name();

		/**
		 * The meta object literal for the '<em><b>Scope</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DECLARATION__SCOPE = eINSTANCE.getDeclaration_Scope();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DECLARATION__ATTRIBUTES = eINSTANCE.getDeclaration_Attributes();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.ForwardDeclarationImpl <em>Forward Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.ForwardDeclarationImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getForwardDeclaration()
		 * @generated
		 */
		EClass FORWARD_DECLARATION = eINSTANCE.getForwardDeclaration();

		/**
		 * The meta object literal for the '<em><b>Declaration</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORWARD_DECLARATION__DECLARATION = eINSTANCE.getForwardDeclaration_Declaration();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORWARD_DECLARATION__TYPE = eINSTANCE.getForwardDeclaration_Type();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.VariableImpl <em>Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.VariableImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getVariable()
		 * @generated
		 */
		EClass VARIABLE = eINSTANCE.getVariable();

		/**
		 * The meta object literal for the '<em><b>Init Value</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE__INIT_VALUE = eINSTANCE.getVariable_InitValue();

		/**
		 * The meta object literal for the '<em><b>Constant</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE__CONSTANT = eINSTANCE.getVariable_Constant();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE__TYPE = eINSTANCE.getVariable_Type();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE__PARAMETER = eINSTANCE.getVariable_Parameter();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.LambdaExpressionImpl <em>Lambda Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.LambdaExpressionImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getLambdaExpression()
		 * @generated
		 */
		EClass LAMBDA_EXPRESSION = eINSTANCE.getLambdaExpression();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LAMBDA_EXPRESSION__PARAMETERS = eINSTANCE.getLambdaExpression_Parameters();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LAMBDA_EXPRESSION__BODY = eINSTANCE.getLambdaExpression_Body();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.ProcExpressionImpl <em>Proc Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.ProcExpressionImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getProcExpression()
		 * @generated
		 */
		EClass PROC_EXPRESSION = eINSTANCE.getProcExpression();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROC_EXPRESSION__PARAMETERS = eINSTANCE.getProcExpression_Parameters();

		/**
		 * The meta object literal for the '<em><b>Outputs</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROC_EXPRESSION__OUTPUTS = eINSTANCE.getProcExpression_Outputs();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROC_EXPRESSION__BODY = eINSTANCE.getProcExpression_Body();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.IfExpressionImpl <em>If Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.IfExpressionImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getIfExpression()
		 * @generated
		 */
		EClass IF_EXPRESSION = eINSTANCE.getIfExpression();

		/**
		 * The meta object literal for the '<em><b>Then Expression</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IF_EXPRESSION__THEN_EXPRESSION = eINSTANCE.getIfExpression_ThenExpression();

		/**
		 * The meta object literal for the '<em><b>Else Expression</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IF_EXPRESSION__ELSE_EXPRESSION = eINSTANCE.getIfExpression_ElseExpression();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IF_EXPRESSION__CONDITION = eINSTANCE.getIfExpression_Condition();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.CaseExpressionImpl <em>Case Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.CaseExpressionImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getCaseExpression()
		 * @generated
		 */
		EClass CASE_EXPRESSION = eINSTANCE.getCaseExpression();

		/**
		 * The meta object literal for the '<em><b>Alternatives</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CASE_EXPRESSION__ALTERNATIVES = eINSTANCE.getCaseExpression_Alternatives();

		/**
		 * The meta object literal for the '<em><b>Default</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CASE_EXPRESSION__DEFAULT = eINSTANCE.getCaseExpression_Default();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CASE_EXPRESSION__EXPRESSION = eINSTANCE.getCaseExpression_Expression();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.ExprAlternativeImpl <em>Expr Alternative</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.ExprAlternativeImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getExprAlternative()
		 * @generated
		 */
		EClass EXPR_ALTERNATIVE = eINSTANCE.getExprAlternative();

		/**
		 * The meta object literal for the '<em><b>Guards</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPR_ALTERNATIVE__GUARDS = eINSTANCE.getExprAlternative_Guards();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPR_ALTERNATIVE__EXPRESSION = eINSTANCE.getExprAlternative_Expression();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.GuardImpl <em>Guard</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.GuardImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getGuard()
		 * @generated
		 */
		EClass GUARD = eINSTANCE.getGuard();

		/**
		 * The meta object literal for the '<em><b>Peeks</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GUARD__PEEKS = eINSTANCE.getGuard_Peeks();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GUARD__EXPRESSION = eINSTANCE.getGuard_Expression();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.TypeImpl <em>Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.TypeImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getType()
		 * @generated
		 */
		EClass TYPE = eINSTANCE.getType();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.TypeBoolImpl <em>Type Bool</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.TypeBoolImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeBool()
		 * @generated
		 */
		EClass TYPE_BOOL = eINSTANCE.getTypeBool();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.TypeExternalImpl <em>Type External</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.TypeExternalImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeExternal()
		 * @generated
		 */
		EClass TYPE_EXTERNAL = eINSTANCE.getTypeExternal();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TYPE_EXTERNAL__NAME = eINSTANCE.getTypeExternal_Name();

		/**
		 * The meta object literal for the '<em><b>Scope Name</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TYPE_EXTERNAL__SCOPE_NAME = eINSTANCE.getTypeExternal_ScopeName();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_EXTERNAL__ATTRIBUTES = eINSTANCE.getTypeExternal_Attributes();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.TypeIntImpl <em>Type Int</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.TypeIntImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeInt()
		 * @generated
		 */
		EClass TYPE_INT = eINSTANCE.getTypeInt();

		/**
		 * The meta object literal for the '<em><b>Size</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_INT__SIZE = eINSTANCE.getTypeInt_Size();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.TypeListImpl <em>Type List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.TypeListImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeList()
		 * @generated
		 */
		EClass TYPE_LIST = eINSTANCE.getTypeList();

		/**
		 * The meta object literal for the '<em><b>Size</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_LIST__SIZE = eINSTANCE.getTypeList_Size();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_LIST__TYPE = eINSTANCE.getTypeList_Type();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.TypeFloatImpl <em>Type Float</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.TypeFloatImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeFloat()
		 * @generated
		 */
		EClass TYPE_FLOAT = eINSTANCE.getTypeFloat();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.TypeUintImpl <em>Type Uint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.TypeUintImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeUint()
		 * @generated
		 */
		EClass TYPE_UINT = eINSTANCE.getTypeUint();

		/**
		 * The meta object literal for the '<em><b>Size</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_UINT__SIZE = eINSTANCE.getTypeUint_Size();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.TypeStringImpl <em>Type String</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.TypeStringImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeString()
		 * @generated
		 */
		EClass TYPE_STRING = eINSTANCE.getTypeString();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.TypeTupleImpl <em>Type Tuple</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.TypeTupleImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeTuple()
		 * @generated
		 */
		EClass TYPE_TUPLE = eINSTANCE.getTypeTuple();

		/**
		 * The meta object literal for the '<em><b>Tagged Tuples</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_TUPLE__TAGGED_TUPLES = eINSTANCE.getTypeTuple_TaggedTuples();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.TaggedTupleImpl <em>Tagged Tuple</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.TaggedTupleImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getTaggedTuple()
		 * @generated
		 */
		EClass TAGGED_TUPLE = eINSTANCE.getTaggedTuple();

		/**
		 * The meta object literal for the '<em><b>Fields</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAGGED_TUPLE__FIELDS = eINSTANCE.getTaggedTuple_Fields();

		/**
		 * The meta object literal for the '<em><b>Tag</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAGGED_TUPLE__TAG = eINSTANCE.getTaggedTuple_Tag();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.TaggedTupleFieldReadImpl <em>Tagged Tuple Field Read</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.TaggedTupleFieldReadImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getTaggedTupleFieldRead()
		 * @generated
		 */
		EClass TAGGED_TUPLE_FIELD_READ = eINSTANCE.getTaggedTupleFieldRead();

		/**
		 * The meta object literal for the '<em><b>Tag</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAGGED_TUPLE_FIELD_READ__TAG = eINSTANCE.getTaggedTupleFieldRead_Tag();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAGGED_TUPLE_FIELD_READ__LABEL = eINSTANCE.getTaggedTupleFieldRead_Label();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAGGED_TUPLE_FIELD_READ__VALUE = eINSTANCE.getTaggedTupleFieldRead_Value();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.TagOfImpl <em>Tag Of</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.TagOfImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getTagOf()
		 * @generated
		 */
		EClass TAG_OF = eINSTANCE.getTagOf();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG_OF__EXPRESSION = eINSTANCE.getTagOf_Expression();

		/**
		 * The meta object literal for the '<em><b>Tag</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_OF__TAG = eINSTANCE.getTagOf_Tag();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.TypeUndefImpl <em>Type Undef</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.TypeUndefImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeUndef()
		 * @generated
		 */
		EClass TYPE_UNDEF = eINSTANCE.getTypeUndef();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.TypeActorImpl <em>Type Actor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.TypeActorImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeActor()
		 * @generated
		 */
		EClass TYPE_ACTOR = eINSTANCE.getTypeActor();

		/**
		 * The meta object literal for the '<em><b>Namespace</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TYPE_ACTOR__NAMESPACE = eINSTANCE.getTypeActor_Namespace();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TYPE_ACTOR__NAME = eINSTANCE.getTypeActor_Name();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.TypeUserImpl <em>Type User</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.TypeUserImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeUser()
		 * @generated
		 */
		EClass TYPE_USER = eINSTANCE.getTypeUser();

		/**
		 * The meta object literal for the '<em><b>Declaration</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_USER__DECLARATION = eINSTANCE.getTypeUser_Declaration();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.TypeLambdaImpl <em>Type Lambda</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.TypeLambdaImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeLambda()
		 * @generated
		 */
		EClass TYPE_LAMBDA = eINSTANCE.getTypeLambda();

		/**
		 * The meta object literal for the '<em><b>Input Types</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_LAMBDA__INPUT_TYPES = eINSTANCE.getTypeLambda_InputTypes();

		/**
		 * The meta object literal for the '<em><b>Output Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_LAMBDA__OUTPUT_TYPE = eINSTANCE.getTypeLambda_OutputType();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.TypeProcImpl <em>Type Proc</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.TypeProcImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeProc()
		 * @generated
		 */
		EClass TYPE_PROC = eINSTANCE.getTypeProc();

		/**
		 * The meta object literal for the '<em><b>Input Types</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_PROC__INPUT_TYPES = eINSTANCE.getTypeProc_InputTypes();

		/**
		 * The meta object literal for the '<em><b>Output Types</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_PROC__OUTPUT_TYPES = eINSTANCE.getTypeProc_OutputTypes();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.TypeVariableImpl <em>Type Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.TypeVariableImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeVariable()
		 * @generated
		 */
		EClass TYPE_VARIABLE = eINSTANCE.getTypeVariable();

		/**
		 * The meta object literal for the '<em><b>Declaration</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_VARIABLE__DECLARATION = eINSTANCE.getTypeVariable_Declaration();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.TypeDeclarationImpl <em>Type Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.TypeDeclarationImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeDeclaration()
		 * @generated
		 */
		EClass TYPE_DECLARATION = eINSTANCE.getTypeDeclaration();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_DECLARATION__TYPE = eINSTANCE.getTypeDeclaration_Type();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.ForwardTypeDeclarationImpl <em>Forward Type Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.ForwardTypeDeclarationImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getForwardTypeDeclaration()
		 * @generated
		 */
		EClass FORWARD_TYPE_DECLARATION = eINSTANCE.getForwardTypeDeclaration();

		/**
		 * The meta object literal for the '<em><b>Declaration</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORWARD_TYPE_DECLARATION__DECLARATION = eINSTANCE.getForwardTypeDeclaration_Declaration();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.TypeVariableDeclarationImpl <em>Type Variable Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.TypeVariableDeclarationImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getTypeVariableDeclaration()
		 * @generated
		 */
		EClass TYPE_VARIABLE_DECLARATION = eINSTANCE.getTypeVariableDeclaration();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.ScheduleImpl <em>Schedule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.ScheduleImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getSchedule()
		 * @generated
		 */
		EClass SCHEDULE = eINSTANCE.getSchedule();

		/**
		 * The meta object literal for the '<em><b>States</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCHEDULE__STATES = eINSTANCE.getSchedule_States();

		/**
		 * The meta object literal for the '<em><b>Free Runners</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCHEDULE__FREE_RUNNERS = eINSTANCE.getSchedule_FreeRunners();

		/**
		 * The meta object literal for the '<em><b>Priority Graph</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCHEDULE__PRIORITY_GRAPH = eINSTANCE.getSchedule_PriorityGraph();

		/**
		 * The meta object literal for the '<em><b>Initial State</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCHEDULE__INITIAL_STATE = eINSTANCE.getSchedule_InitialState();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.StateImpl <em>State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.StateImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getState()
		 * @generated
		 */
		EClass STATE = eINSTANCE.getState();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__NAME = eINSTANCE.getState_Name();

		/**
		 * The meta object literal for the '<em><b>Priority Graph</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__PRIORITY_GRAPH = eINSTANCE.getState_PriorityGraph();

		/**
		 * The meta object literal for the '<em><b>Action2 Target Map</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__ACTION2_TARGET_MAP = eINSTANCE.getState_Action2TargetMap();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.AnnotationImpl <em>Annotation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.AnnotationImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getAnnotation()
		 * @generated
		 */
		EClass ANNOTATION = eINSTANCE.getAnnotation();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANNOTATION__NAME = eINSTANCE.getAnnotation_Name();

		/**
		 * The meta object literal for the '<em><b>Arguments</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANNOTATION__ARGUMENTS = eINSTANCE.getAnnotation_Arguments();

		/**
		 * The meta object literal for the '{@link org.caltoopia.ir.impl.AnnotationArgumentImpl <em>Annotation Argument</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.caltoopia.ir.impl.AnnotationArgumentImpl
		 * @see org.caltoopia.ir.impl.IrPackageImpl#getAnnotationArgument()
		 * @generated
		 */
		EClass ANNOTATION_ARGUMENT = eINSTANCE.getAnnotationArgument();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANNOTATION_ARGUMENT__ID = eINSTANCE.getAnnotationArgument_Id();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANNOTATION_ARGUMENT__VALUE = eINSTANCE.getAnnotationArgument_Value();

	}

} //IrPackage
