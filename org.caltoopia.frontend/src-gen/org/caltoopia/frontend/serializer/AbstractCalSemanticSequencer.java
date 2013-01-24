package org.caltoopia.frontend.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
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
import org.caltoopia.frontend.cal.AstExpressionBinary;
import org.caltoopia.frontend.cal.AstExpressionBoolean;
import org.caltoopia.frontend.cal.AstExpressionCall;
import org.caltoopia.frontend.cal.AstExpressionFloat;
import org.caltoopia.frontend.cal.AstExpressionIf;
import org.caltoopia.frontend.cal.AstExpressionInteger;
import org.caltoopia.frontend.cal.AstExpressionList;
import org.caltoopia.frontend.cal.AstExpressionString;
import org.caltoopia.frontend.cal.AstExpressionUnary;
import org.caltoopia.frontend.cal.AstExpressionVariable;
import org.caltoopia.frontend.cal.AstExternalActor;
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
import org.caltoopia.frontend.cal.AstPort;
import org.caltoopia.frontend.cal.AstPriority;
import org.caltoopia.frontend.cal.AstProcedure;
import org.caltoopia.frontend.cal.AstSchedule;
import org.caltoopia.frontend.cal.AstState;
import org.caltoopia.frontend.cal.AstStatementAssign;
import org.caltoopia.frontend.cal.AstStatementBlock;
import org.caltoopia.frontend.cal.AstStatementCall;
import org.caltoopia.frontend.cal.AstStatementForeach;
import org.caltoopia.frontend.cal.AstStatementIf;
import org.caltoopia.frontend.cal.AstStatementWhile;
import org.caltoopia.frontend.cal.AstStructure;
import org.caltoopia.frontend.cal.AstTag;
import org.caltoopia.frontend.cal.AstTransition;
import org.caltoopia.frontend.cal.AstType;
import org.caltoopia.frontend.cal.AstTypeDefinitionParameter;
import org.caltoopia.frontend.cal.AstTypeName;
import org.caltoopia.frontend.cal.AstTypeParam;
import org.caltoopia.frontend.cal.AstTypeParameterList;
import org.caltoopia.frontend.cal.AstVariable;
import org.caltoopia.frontend.cal.AstVariableReference;
import org.caltoopia.frontend.cal.CalPackage;
import org.caltoopia.frontend.cal.Import;
import org.caltoopia.frontend.services.CalGrammarAccess;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticNodeProvider.INodesForEObjectProvider;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("restriction")
public class AbstractCalSemanticSequencer extends AbstractSemanticSequencer {

	@Inject
	protected CalGrammarAccess grammarAccess;
	
	@Inject
	protected ISemanticSequencerDiagnosticProvider diagnosticProvider;
	
	@Inject
	protected ITransientValueService transientValues;
	
	@Inject
	@GenericSequencer
	protected Provider<ISemanticSequencer> genericSequencerProvider;
	
	protected ISemanticSequencer genericSequencer;
	
	
	@Override
	public void init(ISemanticSequencer sequencer, ISemanticSequenceAcceptor sequenceAcceptor, Acceptor errorAcceptor) {
		super.init(sequencer, sequenceAcceptor, errorAcceptor);
		this.genericSequencer = genericSequencerProvider.get();
		this.genericSequencer.init(sequencer, sequenceAcceptor, errorAcceptor);
	}
	
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == CalPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case CalPackage.AST_ACTION:
				if(context == grammarAccess.getAstActionRule()) {
					sequence_AstAction(context, (AstAction) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_ACTOR:
				if(context == grammarAccess.getAstActorRule()) {
					sequence_AstActor(context, (AstActor) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_ACTOR_VARIABLE:
				if(context == grammarAccess.getAstActorDeclarationRule()) {
					sequence_AstActorDeclaration(context, (AstActorVariable) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_ACTOR_VARIABLE_REFERENCE:
				if(context == grammarAccess.getAstActorVariableReferenceRule()) {
					sequence_AstActorVariableReference(context, (AstActorVariableReference) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_ANNOTATION:
				if(context == grammarAccess.getAstAnnotationRule()) {
					sequence_AstAnnotation(context, (AstAnnotation) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_ANNOTATION_ARGUMENT:
				if(context == grammarAccess.getAstAnnotationArgumentRule()) {
					sequence_AstAnnotationArgument(context, (AstAnnotationArgument) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_ASSIGN_PARAMETER:
				if(context == grammarAccess.getAstAssignParameterRule()) {
					sequence_AstAssignParameter(context, (AstAssignParameter) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_CONNECTION:
				if(context == grammarAccess.getAstConnectionRule()) {
					sequence_AstConnection(context, (AstConnection) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_CONNECTION_ATTRIBUTE:
				if(context == grammarAccess.getAstConnectionAttributeRule()) {
					sequence_AstConnectionAttribute(context, (AstConnectionAttribute) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_ENTITY:
				if(context == grammarAccess.getAstEntityRule()) {
					sequence_AstEntity(context, (AstEntity) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_EXPRESSION_BINARY:
				if(context == grammarAccess.getAstExpressionRule() ||
				   context == grammarAccess.getAstExpressionAdditiveRule() ||
				   context == grammarAccess.getAstExpressionAdditiveAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionAndRule() ||
				   context == grammarAccess.getAstExpressionAndAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionBitandRule() ||
				   context == grammarAccess.getAstExpressionBitandAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionBitorRule() ||
				   context == grammarAccess.getAstExpressionBitorAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionBitxorRule() ||
				   context == grammarAccess.getAstExpressionBitxorAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionEqRule() ||
				   context == grammarAccess.getAstExpressionEqAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionExpRule() ||
				   context == grammarAccess.getAstExpressionExpAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionMultiplicativeRule() ||
				   context == grammarAccess.getAstExpressionMultiplicativeAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionPostfixRule() ||
				   context == grammarAccess.getAstExpressionRelationalRule() ||
				   context == grammarAccess.getAstExpressionRelationalAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionShiftRule() ||
				   context == grammarAccess.getAstExpressionShiftAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionUnaryRule() ||
				   context == grammarAccess.getAstExpressionAccess().getAstExpressionBinaryLeftAction_1_0()) {
					sequence_AstExpressionMultiplicative(context, (AstExpressionBinary) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_EXPRESSION_BOOLEAN:
				if(context == grammarAccess.getAstExpressionRule() ||
				   context == grammarAccess.getAstExpressionAdditiveRule() ||
				   context == grammarAccess.getAstExpressionAdditiveAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionAndRule() ||
				   context == grammarAccess.getAstExpressionAndAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionBitandRule() ||
				   context == grammarAccess.getAstExpressionBitandAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionBitorRule() ||
				   context == grammarAccess.getAstExpressionBitorAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionBitxorRule() ||
				   context == grammarAccess.getAstExpressionBitxorAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionBooleanRule() ||
				   context == grammarAccess.getAstExpressionEqRule() ||
				   context == grammarAccess.getAstExpressionEqAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionExpRule() ||
				   context == grammarAccess.getAstExpressionExpAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionLiteralRule() ||
				   context == grammarAccess.getAstExpressionMultiplicativeRule() ||
				   context == grammarAccess.getAstExpressionMultiplicativeAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionPostfixRule() ||
				   context == grammarAccess.getAstExpressionRelationalRule() ||
				   context == grammarAccess.getAstExpressionRelationalAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionShiftRule() ||
				   context == grammarAccess.getAstExpressionShiftAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionUnaryRule() ||
				   context == grammarAccess.getAstExpressionAccess().getAstExpressionBinaryLeftAction_1_0()) {
					sequence_AstExpressionBoolean(context, (AstExpressionBoolean) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_EXPRESSION_CALL:
				if(context == grammarAccess.getAstExpressionRule() ||
				   context == grammarAccess.getAstExpressionAdditiveRule() ||
				   context == grammarAccess.getAstExpressionAdditiveAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionAndRule() ||
				   context == grammarAccess.getAstExpressionAndAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionBitandRule() ||
				   context == grammarAccess.getAstExpressionBitandAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionBitorRule() ||
				   context == grammarAccess.getAstExpressionBitorAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionBitxorRule() ||
				   context == grammarAccess.getAstExpressionBitxorAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionCallRule() ||
				   context == grammarAccess.getAstExpressionEqRule() ||
				   context == grammarAccess.getAstExpressionEqAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionExpRule() ||
				   context == grammarAccess.getAstExpressionExpAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionMultiplicativeRule() ||
				   context == grammarAccess.getAstExpressionMultiplicativeAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionPostfixRule() ||
				   context == grammarAccess.getAstExpressionRelationalRule() ||
				   context == grammarAccess.getAstExpressionRelationalAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionShiftRule() ||
				   context == grammarAccess.getAstExpressionShiftAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionUnaryRule() ||
				   context == grammarAccess.getAstExpressionAccess().getAstExpressionBinaryLeftAction_1_0()) {
					sequence_AstExpressionCall(context, (AstExpressionCall) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_EXPRESSION_FLOAT:
				if(context == grammarAccess.getAstExpressionRule() ||
				   context == grammarAccess.getAstExpressionAdditiveRule() ||
				   context == grammarAccess.getAstExpressionAdditiveAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionAndRule() ||
				   context == grammarAccess.getAstExpressionAndAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionBitandRule() ||
				   context == grammarAccess.getAstExpressionBitandAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionBitorRule() ||
				   context == grammarAccess.getAstExpressionBitorAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionBitxorRule() ||
				   context == grammarAccess.getAstExpressionBitxorAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionEqRule() ||
				   context == grammarAccess.getAstExpressionEqAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionExpRule() ||
				   context == grammarAccess.getAstExpressionExpAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionFloatRule() ||
				   context == grammarAccess.getAstExpressionLiteralRule() ||
				   context == grammarAccess.getAstExpressionMultiplicativeRule() ||
				   context == grammarAccess.getAstExpressionMultiplicativeAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionPostfixRule() ||
				   context == grammarAccess.getAstExpressionRelationalRule() ||
				   context == grammarAccess.getAstExpressionRelationalAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionShiftRule() ||
				   context == grammarAccess.getAstExpressionShiftAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionUnaryRule() ||
				   context == grammarAccess.getAstExpressionAccess().getAstExpressionBinaryLeftAction_1_0()) {
					sequence_AstExpressionFloat(context, (AstExpressionFloat) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_EXPRESSION_IF:
				if(context == grammarAccess.getAstExpressionRule() ||
				   context == grammarAccess.getAstExpressionAdditiveRule() ||
				   context == grammarAccess.getAstExpressionAdditiveAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionAndRule() ||
				   context == grammarAccess.getAstExpressionAndAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionBitandRule() ||
				   context == grammarAccess.getAstExpressionBitandAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionBitorRule() ||
				   context == grammarAccess.getAstExpressionBitorAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionBitxorRule() ||
				   context == grammarAccess.getAstExpressionBitxorAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionEqRule() ||
				   context == grammarAccess.getAstExpressionEqAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionExpRule() ||
				   context == grammarAccess.getAstExpressionExpAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionIfRule() ||
				   context == grammarAccess.getAstExpressionMultiplicativeRule() ||
				   context == grammarAccess.getAstExpressionMultiplicativeAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionPostfixRule() ||
				   context == grammarAccess.getAstExpressionRelationalRule() ||
				   context == grammarAccess.getAstExpressionRelationalAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionShiftRule() ||
				   context == grammarAccess.getAstExpressionShiftAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionUnaryRule() ||
				   context == grammarAccess.getAstExpressionAccess().getAstExpressionBinaryLeftAction_1_0()) {
					sequence_AstExpressionIf(context, (AstExpressionIf) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_EXPRESSION_INTEGER:
				if(context == grammarAccess.getAstExpressionRule() ||
				   context == grammarAccess.getAstExpressionAdditiveRule() ||
				   context == grammarAccess.getAstExpressionAdditiveAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionAndRule() ||
				   context == grammarAccess.getAstExpressionAndAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionBitandRule() ||
				   context == grammarAccess.getAstExpressionBitandAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionBitorRule() ||
				   context == grammarAccess.getAstExpressionBitorAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionBitxorRule() ||
				   context == grammarAccess.getAstExpressionBitxorAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionEqRule() ||
				   context == grammarAccess.getAstExpressionEqAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionExpRule() ||
				   context == grammarAccess.getAstExpressionExpAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionIntegerRule() ||
				   context == grammarAccess.getAstExpressionLiteralRule() ||
				   context == grammarAccess.getAstExpressionMultiplicativeRule() ||
				   context == grammarAccess.getAstExpressionMultiplicativeAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionPostfixRule() ||
				   context == grammarAccess.getAstExpressionRelationalRule() ||
				   context == grammarAccess.getAstExpressionRelationalAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionShiftRule() ||
				   context == grammarAccess.getAstExpressionShiftAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionUnaryRule() ||
				   context == grammarAccess.getAstExpressionAccess().getAstExpressionBinaryLeftAction_1_0()) {
					sequence_AstExpressionInteger(context, (AstExpressionInteger) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_EXPRESSION_LIST:
				if(context == grammarAccess.getAstExpressionRule() ||
				   context == grammarAccess.getAstExpressionAdditiveRule() ||
				   context == grammarAccess.getAstExpressionAdditiveAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionAndRule() ||
				   context == grammarAccess.getAstExpressionAndAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionBitandRule() ||
				   context == grammarAccess.getAstExpressionBitandAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionBitorRule() ||
				   context == grammarAccess.getAstExpressionBitorAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionBitxorRule() ||
				   context == grammarAccess.getAstExpressionBitxorAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionEqRule() ||
				   context == grammarAccess.getAstExpressionEqAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionExpRule() ||
				   context == grammarAccess.getAstExpressionExpAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionListRule() ||
				   context == grammarAccess.getAstExpressionMultiplicativeRule() ||
				   context == grammarAccess.getAstExpressionMultiplicativeAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionPostfixRule() ||
				   context == grammarAccess.getAstExpressionRelationalRule() ||
				   context == grammarAccess.getAstExpressionRelationalAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionShiftRule() ||
				   context == grammarAccess.getAstExpressionShiftAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionUnaryRule() ||
				   context == grammarAccess.getAstExpressionAccess().getAstExpressionBinaryLeftAction_1_0()) {
					sequence_AstExpressionList(context, (AstExpressionList) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_EXPRESSION_STRING:
				if(context == grammarAccess.getAstExpressionRule() ||
				   context == grammarAccess.getAstExpressionAdditiveRule() ||
				   context == grammarAccess.getAstExpressionAdditiveAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionAndRule() ||
				   context == grammarAccess.getAstExpressionAndAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionBitandRule() ||
				   context == grammarAccess.getAstExpressionBitandAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionBitorRule() ||
				   context == grammarAccess.getAstExpressionBitorAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionBitxorRule() ||
				   context == grammarAccess.getAstExpressionBitxorAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionEqRule() ||
				   context == grammarAccess.getAstExpressionEqAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionExpRule() ||
				   context == grammarAccess.getAstExpressionExpAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionLiteralRule() ||
				   context == grammarAccess.getAstExpressionMultiplicativeRule() ||
				   context == grammarAccess.getAstExpressionMultiplicativeAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionPostfixRule() ||
				   context == grammarAccess.getAstExpressionRelationalRule() ||
				   context == grammarAccess.getAstExpressionRelationalAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionShiftRule() ||
				   context == grammarAccess.getAstExpressionShiftAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionStringRule() ||
				   context == grammarAccess.getAstExpressionUnaryRule() ||
				   context == grammarAccess.getAstExpressionAccess().getAstExpressionBinaryLeftAction_1_0()) {
					sequence_AstExpressionString(context, (AstExpressionString) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_EXPRESSION_UNARY:
				if(context == grammarAccess.getAstExpressionRule() ||
				   context == grammarAccess.getAstExpressionAdditiveRule() ||
				   context == grammarAccess.getAstExpressionAdditiveAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionAndRule() ||
				   context == grammarAccess.getAstExpressionAndAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionBitandRule() ||
				   context == grammarAccess.getAstExpressionBitandAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionBitorRule() ||
				   context == grammarAccess.getAstExpressionBitorAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionBitxorRule() ||
				   context == grammarAccess.getAstExpressionBitxorAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionEqRule() ||
				   context == grammarAccess.getAstExpressionEqAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionExpRule() ||
				   context == grammarAccess.getAstExpressionExpAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionMultiplicativeRule() ||
				   context == grammarAccess.getAstExpressionMultiplicativeAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionPostfixRule() ||
				   context == grammarAccess.getAstExpressionRelationalRule() ||
				   context == grammarAccess.getAstExpressionRelationalAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionShiftRule() ||
				   context == grammarAccess.getAstExpressionShiftAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionUnaryRule() ||
				   context == grammarAccess.getAstExpressionAccess().getAstExpressionBinaryLeftAction_1_0()) {
					sequence_AstExpressionUnary(context, (AstExpressionUnary) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_EXPRESSION_VARIABLE:
				if(context == grammarAccess.getAstExpressionRule() ||
				   context == grammarAccess.getAstExpressionAdditiveRule() ||
				   context == grammarAccess.getAstExpressionAdditiveAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionAndRule() ||
				   context == grammarAccess.getAstExpressionAndAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionBitandRule() ||
				   context == grammarAccess.getAstExpressionBitandAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionBitorRule() ||
				   context == grammarAccess.getAstExpressionBitorAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionBitxorRule() ||
				   context == grammarAccess.getAstExpressionBitxorAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionEqRule() ||
				   context == grammarAccess.getAstExpressionEqAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionExpRule() ||
				   context == grammarAccess.getAstExpressionExpAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionMultiplicativeRule() ||
				   context == grammarAccess.getAstExpressionMultiplicativeAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionPostfixRule() ||
				   context == grammarAccess.getAstExpressionRelationalRule() ||
				   context == grammarAccess.getAstExpressionRelationalAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionShiftRule() ||
				   context == grammarAccess.getAstExpressionShiftAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstExpressionUnaryRule() ||
				   context == grammarAccess.getAstExpressionVariableRule() ||
				   context == grammarAccess.getAstExpressionAccess().getAstExpressionBinaryLeftAction_1_0()) {
					sequence_AstExpressionVariable(context, (AstExpressionVariable) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_EXTERNAL_ACTOR:
				if(context == grammarAccess.getAstExternalActorRule()) {
					sequence_AstExternalActor(context, (AstExternalActor) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_FOREACH_GENERATOR:
				if(context == grammarAccess.getAstForeachGeneratorRule()) {
					sequence_AstForeachGenerator(context, (AstForeachGenerator) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_FUNCTION:
				if(context == grammarAccess.getAstConstructorRule()) {
					sequence_AstConstructor(context, (AstFunction) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getAstExternalFunctionRule()) {
					sequence_AstExternalFunction(context, (AstFunction) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getAstFunctionRule()) {
					sequence_AstFunction(context, (AstFunction) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_GENERATOR:
				if(context == grammarAccess.getAstGeneratorRule()) {
					sequence_AstGenerator(context, (AstGenerator) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_INEQUALITY:
				if(context == grammarAccess.getAstInequalityRule()) {
					sequence_AstInequality(context, (AstInequality) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_INITIALIZE:
				if(context == grammarAccess.getAstInitializeRule()) {
					sequence_AstInitialize(context, (AstInitialize) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_INPUT_PATTERN:
				if(context == grammarAccess.getAstInputPatternRule()) {
					sequence_AstInputPattern(context, (AstInputPattern) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_MEMBER_ACCESS:
				if(context == grammarAccess.getAstMemberAccessRule()) {
					sequence_AstMemberAccess(context, (AstMemberAccess) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_NAMESPACE:
				if(context == grammarAccess.getAstNamespaceRule()) {
					sequence_AstNamespace(context, (AstNamespace) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getAstPackageRule()) {
					sequence_AstPackage(context, (AstNamespace) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getAstTopRule()) {
					sequence_AstTop(context, (AstNamespace) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getAstUnitRule()) {
					sequence_AstUnit(context, (AstNamespace) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_NETWORK:
				if(context == grammarAccess.getAstNetworkRule()) {
					sequence_AstNetwork(context, (AstNetwork) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_OUTPUT_PATTERN:
				if(context == grammarAccess.getAstOutputPatternRule()) {
					sequence_AstOutputPattern(context, (AstOutputPattern) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_PORT:
				if(context == grammarAccess.getAstPortRule()) {
					sequence_AstPort(context, (AstPort) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_PRIORITY:
				if(context == grammarAccess.getAstPriorityRule()) {
					sequence_AstPriority(context, (AstPriority) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_PROCEDURE:
				if(context == grammarAccess.getAstExternalProcedureRule()) {
					sequence_AstExternalProcedure(context, (AstProcedure) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getAstProcedureRule()) {
					sequence_AstProcedure(context, (AstProcedure) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_SCHEDULE:
				if(context == grammarAccess.getAstScheduleRule()) {
					sequence_AstSchedule(context, (AstSchedule) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_STATE:
				if(context == grammarAccess.getAstStateRule()) {
					sequence_AstState(context, (AstState) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_STATEMENT_ASSIGN:
				if(context == grammarAccess.getAstStatementRule() ||
				   context == grammarAccess.getAstStatementAssignRule()) {
					sequence_AstStatementAssign(context, (AstStatementAssign) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_STATEMENT_BLOCK:
				if(context == grammarAccess.getAstStatementRule() ||
				   context == grammarAccess.getAstStatementBlockRule()) {
					sequence_AstStatementBlock(context, (AstStatementBlock) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_STATEMENT_CALL:
				if(context == grammarAccess.getAstStatementRule() ||
				   context == grammarAccess.getAstStatementCallRule()) {
					sequence_AstStatementCall(context, (AstStatementCall) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_STATEMENT_FOREACH:
				if(context == grammarAccess.getAstStatementRule() ||
				   context == grammarAccess.getAstStatementForeachRule()) {
					sequence_AstStatementForeach(context, (AstStatementForeach) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_STATEMENT_IF:
				if(context == grammarAccess.getAstStatementRule() ||
				   context == grammarAccess.getAstStatementIfRule()) {
					sequence_AstStatementIf(context, (AstStatementIf) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_STATEMENT_WHILE:
				if(context == grammarAccess.getAstStatementRule() ||
				   context == grammarAccess.getAstStatementWhileRule()) {
					sequence_AstStatementWhile(context, (AstStatementWhile) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_STRUCTURE:
				if(context == grammarAccess.getAstStructureRule()) {
					sequence_AstStructure(context, (AstStructure) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_TAG:
				if(context == grammarAccess.getAstTagRule()) {
					sequence_AstTag(context, (AstTag) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_TRANSITION:
				if(context == grammarAccess.getAstTransitionRule()) {
					sequence_AstTransition(context, (AstTransition) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_TYPE:
				if(context == grammarAccess.getAstTypeTupleRule()) {
					sequence_AstTypeTuple(context, (AstType) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getAstTypeRule()) {
					sequence_AstType(context, (AstType) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_TYPE_DEFINITION_PARAMETER:
				if(context == grammarAccess.getAstTypeDefinitionParameterRule()) {
					sequence_AstTypeDefinitionParameter(context, (AstTypeDefinitionParameter) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_TYPE_NAME:
				if(context == grammarAccess.getAstTypeDefinitionTypeParameterRule()) {
					sequence_AstTypeDefinitionTypeParameter(context, (AstTypeName) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getAstTypeDefinitionRule()) {
					sequence_AstTypeDefinition(context, (AstTypeName) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_TYPE_PARAM:
				if(context == grammarAccess.getAstTypeParamRule()) {
					sequence_AstTypeParam(context, (AstTypeParam) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_TYPE_PARAMETER_LIST:
				if(context == grammarAccess.getAstTypeParameterListRule()) {
					sequence_AstTypeParameterList(context, (AstTypeParameterList) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_VARIABLE:
				if(context == grammarAccess.getAstConstantVariableRule()) {
					sequence_AstConstantVariable(context, (AstVariable) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getAstParameterRule()) {
					sequence_AstParameter(context, (AstVariable) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getAstTokenRule()) {
					sequence_AstToken(context, (AstVariable) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getAstStateVariableRule() ||
				   context == grammarAccess.getAstValuedVariableDeclarationRule()) {
					sequence_AstValuedVariableDeclaration(context, (AstVariable) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getAstExternalVariableRule() ||
				   context == grammarAccess.getAstVariableDeclarationRule()) {
					sequence_AstVariableDeclaration(context, (AstVariable) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_VARIABLE_REFERENCE:
				if(context == grammarAccess.getAstVariableReferenceRule()) {
					sequence_AstVariableReference(context, (AstVariableReference) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.IMPORT:
				if(context == grammarAccess.getImportRule()) {
					sequence_Import(context, (Import) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     (
	 *         annotations+=AstAnnotation* 
	 *         tag=AstTag? 
	 *         (inputs+=AstInputPattern inputs+=AstInputPattern*)? 
	 *         (outputs+=AstOutputPattern outputs+=AstOutputPattern*)? 
	 *         (guards+=AstExpression guards+=AstExpression*)? 
	 *         (variables+=AstValuedVariableDeclaration variables+=AstValuedVariableDeclaration*)? 
	 *         statements+=AstStatement*
	 *     )
	 *
	 * Features:
	 *    annotations[0, *]
	 *    tag[0, 1]
	 *    inputs[0, *]
	 *    outputs[0, *]
	 *    guards[0, *]
	 *    variables[0, *]
	 *    statements[0, *]
	 */
	protected void sequence_AstAction(EObject context, AstAction semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID type=[AstEntity|QualifiedName] (parameters+=AstAssignParameter parameters+=AstAssignParameter*)?)
	 *
	 * Features:
	 *    name[1, 1]
	 *    type[1, 1]
	 *    parameters[0, *]
	 */
	protected void sequence_AstActorDeclaration(EObject context, AstActorVariable semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     variable=[AstActorVariable|ID]
	 *
	 * Features:
	 *    variable[1, 1]
	 */
	protected void sequence_AstActorVariableReference(EObject context, AstActorVariableReference semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, CalPackage.Literals.AST_ACTOR_VARIABLE_REFERENCE__VARIABLE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalPackage.Literals.AST_ACTOR_VARIABLE_REFERENCE__VARIABLE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getAstActorVariableReferenceAccess().getVariableAstActorVariableIDTerminalRuleCall_0_1(), semanticObject.getVariable());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (parameters+=AstParameter parameters+=AstParameter*)? 
	 *         (inputs+=AstPort inputs+=AstPort*)? 
	 *         (outputs+=AstPort outputs+=AstPort*)? 
	 *         (
	 *             functions+=AstFunction | 
	 *             procedures+=AstProcedure | 
	 *             actions+=AstAction | 
	 *             initializes+=AstInitialize | 
	 *             stateVariables+=AstStateVariable | 
	 *             schedules+=AstSchedule | 
	 *             priorities+=AstPriority
	 *         )*
	 *     )
	 *
	 * Features:
	 *    parameters[0, *]
	 *    inputs[0, *]
	 *    outputs[0, *]
	 *    functions[0, *]
	 *    procedures[0, *]
	 *    actions[0, *]
	 *    initializes[0, *]
	 *    stateVariables[0, *]
	 *    schedules[0, *]
	 *    priorities[0, *]
	 */
	protected void sequence_AstActor(EObject context, AstActor semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID value=STRING)
	 *
	 * Features:
	 *    name[1, 1]
	 *    value[1, 1]
	 */
	protected void sequence_AstAnnotationArgument(EObject context, AstAnnotationArgument semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, CalPackage.Literals.AST_ANNOTATION_ARGUMENT__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalPackage.Literals.AST_ANNOTATION_ARGUMENT__NAME));
			if(transientValues.isValueTransient(semanticObject, CalPackage.Literals.AST_ANNOTATION_ARGUMENT__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalPackage.Literals.AST_ANNOTATION_ARGUMENT__VALUE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getAstAnnotationArgumentAccess().getNameIDTerminalRuleCall_0_0(), semanticObject.getName());
		feeder.accept(grammarAccess.getAstAnnotationArgumentAccess().getValueSTRINGTerminalRuleCall_2_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID (arguments+=AstAnnotationArgument arguments+=AstAnnotationArgument*)?)
	 *
	 * Features:
	 *    name[1, 1]
	 *    arguments[0, *]
	 */
	protected void sequence_AstAnnotation(EObject context, AstAnnotation semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID value=AstExpression)
	 *
	 * Features:
	 *    name[1, 1]
	 *    value[1, 1]
	 */
	protected void sequence_AstAssignParameter(EObject context, AstAssignParameter semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, CalPackage.Literals.AST_ASSIGN_PARAMETER__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalPackage.Literals.AST_ASSIGN_PARAMETER__NAME));
			if(transientValues.isValueTransient(semanticObject, CalPackage.Literals.AST_ASSIGN_PARAMETER__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalPackage.Literals.AST_ASSIGN_PARAMETER__VALUE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getAstAssignParameterAccess().getNameIDTerminalRuleCall_0_0(), semanticObject.getName());
		feeder.accept(grammarAccess.getAstAssignParameterAccess().getValueAstExpressionParserRuleCall_2_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID value=AstExpression)
	 *
	 * Features:
	 *    name[1, 1]
	 *    value[1, 1]
	 */
	protected void sequence_AstConnectionAttribute(EObject context, AstConnectionAttribute semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, CalPackage.Literals.AST_CONNECTION_ATTRIBUTE__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalPackage.Literals.AST_CONNECTION_ATTRIBUTE__NAME));
			if(transientValues.isValueTransient(semanticObject, CalPackage.Literals.AST_CONNECTION_ATTRIBUTE__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalPackage.Literals.AST_CONNECTION_ATTRIBUTE__VALUE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getAstConnectionAttributeAccess().getNameIDTerminalRuleCall_0_0(), semanticObject.getName());
		feeder.accept(grammarAccess.getAstConnectionAttributeAccess().getValueAstExpressionParserRuleCall_2_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (from=AstActorVariableReference? outPort=ID to=AstActorVariableReference? inPort=ID attribute+=AstConnectionAttribute*)
	 *
	 * Features:
	 *    from[0, 1]
	 *    outPort[1, 1]
	 *    to[0, 1]
	 *    inPort[1, 1]
	 *    attribute[0, *]
	 */
	protected void sequence_AstConnection(EObject context, AstConnection semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         annotations+=AstAnnotation* 
	 *         type=AstType 
	 *         name=ID 
	 *         dimensions+=AstExpression* 
	 *         constant?='=' 
	 *         value=AstExpression
	 *     )
	 *
	 * Features:
	 *    constant[1, 1]
	 *    value[1, 1]
	 *    name[1, 1]
	 *    annotations[0, *]
	 *    type[1, 1]
	 *    dimensions[0, *]
	 */
	protected void sequence_AstConstantVariable(EObject context, AstVariable semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID (members+=AstValuedVariableDeclaration members+=AstValuedVariableDeclaration*)?)
	 *
	 * Features:
	 *    name[1, 1]
	 *    members[0, *]
	 */
	protected void sequence_AstConstructor(EObject context, AstFunction semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (annotations+=AstAnnotation* ((name=ID actor=AstActor) | (name=ID network=AstNetwork) | (name=ID external=AstExternalActor)))
	 *
	 * Features:
	 *    annotations[0, *]
	 *    name[0, 3]
	 *    actor[0, 1]
	 *         EXCLUDE_IF_UNSET name
	 *         MANDATORY_IF_SET name
	 *         EXCLUDE_IF_SET name
	 *         EXCLUDE_IF_SET network
	 *         EXCLUDE_IF_SET name
	 *         EXCLUDE_IF_SET external
	 *    network[0, 1]
	 *         EXCLUDE_IF_UNSET name
	 *         MANDATORY_IF_SET name
	 *         EXCLUDE_IF_SET name
	 *         EXCLUDE_IF_SET actor
	 *         EXCLUDE_IF_SET name
	 *         EXCLUDE_IF_SET external
	 *    external[0, 1]
	 *         EXCLUDE_IF_UNSET name
	 *         MANDATORY_IF_SET name
	 *         EXCLUDE_IF_SET name
	 *         EXCLUDE_IF_SET actor
	 *         EXCLUDE_IF_SET name
	 *         EXCLUDE_IF_SET network
	 */
	protected void sequence_AstEntity(EObject context, AstEntity semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     value=BOOL
	 *
	 * Features:
	 *    value[1, 1]
	 */
	protected void sequence_AstExpressionBoolean(EObject context, AstExpressionBoolean semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, CalPackage.Literals.AST_EXPRESSION_BOOLEAN__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalPackage.Literals.AST_EXPRESSION_BOOLEAN__VALUE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getAstExpressionBooleanAccess().getValueBOOLTerminalRuleCall_0(), semanticObject.isValue());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (function=[AstFunction|ID] (parameters+=AstExpression parameters+=AstExpression*)?)
	 *
	 * Features:
	 *    function[1, 1]
	 *    parameters[0, *]
	 */
	protected void sequence_AstExpressionCall(EObject context, AstExpressionCall semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     value=REAL
	 *
	 * Features:
	 *    value[1, 1]
	 */
	protected void sequence_AstExpressionFloat(EObject context, AstExpressionFloat semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, CalPackage.Literals.AST_EXPRESSION_FLOAT__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalPackage.Literals.AST_EXPRESSION_FLOAT__VALUE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getAstExpressionFloatAccess().getValueREALParserRuleCall_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (condition=AstExpression then=AstExpression else=AstExpression)
	 *
	 * Features:
	 *    condition[1, 1]
	 *    then[1, 1]
	 *    else[1, 1]
	 */
	protected void sequence_AstExpressionIf(EObject context, AstExpressionIf semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, CalPackage.Literals.AST_EXPRESSION_IF__CONDITION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalPackage.Literals.AST_EXPRESSION_IF__CONDITION));
			if(transientValues.isValueTransient(semanticObject, CalPackage.Literals.AST_EXPRESSION_IF__THEN) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalPackage.Literals.AST_EXPRESSION_IF__THEN));
			if(transientValues.isValueTransient(semanticObject, CalPackage.Literals.AST_EXPRESSION_IF__ELSE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalPackage.Literals.AST_EXPRESSION_IF__ELSE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getAstExpressionIfAccess().getConditionAstExpressionParserRuleCall_1_0(), semanticObject.getCondition());
		feeder.accept(grammarAccess.getAstExpressionIfAccess().getThenAstExpressionParserRuleCall_3_0(), semanticObject.getThen());
		feeder.accept(grammarAccess.getAstExpressionIfAccess().getElseAstExpressionParserRuleCall_5_0(), semanticObject.getElse());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (value=INT | value=EXP_INT | value=HEX)
	 *
	 * Features:
	 *    value[0, 3]
	 */
	protected void sequence_AstExpressionInteger(EObject context, AstExpressionInteger semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (expressions+=AstExpression expressions+=AstExpression* (generators+=AstGenerator generators+=AstGenerator*)?)
	 *
	 * Features:
	 *    expressions[1, *]
	 *    generators[0, *]
	 */
	protected void sequence_AstExpressionList(EObject context, AstExpressionList semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (
	 *             left=AstExpressionMultiplicative_AstExpressionBinary_1_0 
	 *             (operator='*' | operator='/' | operator='div' | operator='mod') 
	 *             right=AstExpressionExp
	 *         ) | 
	 *         (left=AstExpressionExp_AstExpressionBinary_1_0 operator='**' right=AstExpressionUnary) | 
	 *         (left=AstExpressionAdditive_AstExpressionBinary_1_0 (operator='+' | operator='-') right=AstExpressionMultiplicative) | 
	 *         (left=AstExpressionShift_AstExpressionBinary_1_0 (operator='<<' | operator='>>' | operator='>>>') right=AstExpressionAdditive) | 
	 *         (left=AstExpressionRelational_AstExpressionBinary_1_0 (operator='<' | operator='<=' | operator='>' | operator='>=') right=AstExpressionShift) | 
	 *         (left=AstExpressionEq_AstExpressionBinary_1_0 (operator='=' | operator='!=') right=AstExpressionRelational) | 
	 *         (left=AstExpressionBitand_AstExpressionBinary_1_0 operator='&' right=AstExpressionEq) | 
	 *         (left=AstExpressionBitxor_AstExpressionBinary_1_0 operator='^' right=AstExpressionBitand) | 
	 *         (left=AstExpressionBitor_AstExpressionBinary_1_0 operator='|' right=AstExpressionBitxor) | 
	 *         (left=AstExpressionAnd_AstExpressionBinary_1_0 (operator='&&' | operator='and') right=AstExpressionBitor) | 
	 *         (left=AstExpression_AstExpressionBinary_1_0 (operator='||' | operator='or' | operator='..') right=AstExpressionAnd)
	 *     )
	 *
	 * Features:
	 *    left[0, 11]
	 *    operator[0, 24]
	 *    right[0, 11]
	 */
	protected void sequence_AstExpressionMultiplicative(EObject context, AstExpressionBinary semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     value=STRING
	 *
	 * Features:
	 *    value[1, 1]
	 */
	protected void sequence_AstExpressionString(EObject context, AstExpressionString semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, CalPackage.Literals.AST_EXPRESSION_STRING__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalPackage.Literals.AST_EXPRESSION_STRING__VALUE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getAstExpressionStringAccess().getValueSTRINGTerminalRuleCall_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     ((unaryOperator='~' | unaryOperator='-' | unaryOperator='not' | unaryOperator='#' | unaryOperator='old') expression=AstExpressionPostfix)
	 *
	 * Features:
	 *    unaryOperator[0, 5]
	 *    expression[1, 1]
	 */
	protected void sequence_AstExpressionUnary(EObject context, AstExpressionUnary semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (value=AstVariableReference indexes+=AstExpression* member+=AstMemberAccess*)
	 *
	 * Features:
	 *    value[1, 1]
	 *    indexes[0, *]
	 *    member[0, *]
	 */
	protected void sequence_AstExpressionVariable(EObject context, AstExpressionVariable semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((parameters+=AstParameter parameters+=AstParameter*)? (inputs+=AstPort inputs+=AstPort*)? (outputs+=AstPort outputs+=AstPort*)?)
	 *
	 * Features:
	 *    parameters[0, *]
	 *    inputs[0, *]
	 *    outputs[0, *]
	 */
	protected void sequence_AstExternalActor(EObject context, AstExternalActor semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (annotations+=AstAnnotation* name=ID (parameters+=AstVariableDeclaration parameters+=AstVariableDeclaration*)? type=AstType)
	 *
	 * Features:
	 *    name[1, 1]
	 *    annotations[0, *]
	 *    parameters[0, *]
	 *    type[1, 1]
	 */
	protected void sequence_AstExternalFunction(EObject context, AstFunction semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (annotations+=AstAnnotation* name=ID (parameters+=AstVariableDeclaration parameters+=AstVariableDeclaration*)?)
	 *
	 * Features:
	 *    annotations[0, *]
	 *    name[1, 1]
	 *    parameters[0, *]
	 */
	protected void sequence_AstExternalProcedure(EObject context, AstProcedure semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (variable=AstVariableDeclaration expression=AstExpression)
	 *
	 * Features:
	 *    variable[1, 1]
	 *    expression[1, 1]
	 */
	protected void sequence_AstForeachGenerator(EObject context, AstForeachGenerator semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, CalPackage.Literals.AST_FOREACH_GENERATOR__VARIABLE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalPackage.Literals.AST_FOREACH_GENERATOR__VARIABLE));
			if(transientValues.isValueTransient(semanticObject, CalPackage.Literals.AST_FOREACH_GENERATOR__EXPRESSION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalPackage.Literals.AST_FOREACH_GENERATOR__EXPRESSION));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getAstForeachGeneratorAccess().getVariableAstVariableDeclarationParserRuleCall_0_0(), semanticObject.getVariable());
		feeder.accept(grammarAccess.getAstForeachGeneratorAccess().getExpressionAstExpressionParserRuleCall_2_0(), semanticObject.getExpression());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         annotations+=AstAnnotation* 
	 *         name=ID 
	 *         (parameters+=AstVariableDeclaration parameters+=AstVariableDeclaration*)? 
	 *         type=AstType 
	 *         (variables+=AstValuedVariableDeclaration variables+=AstValuedVariableDeclaration*)? 
	 *         expression=AstExpression
	 *     )
	 *
	 * Features:
	 *    name[1, 1]
	 *    annotations[0, *]
	 *    parameters[0, *]
	 *    type[1, 1]
	 *    variables[0, *]
	 *    expression[1, 1]
	 */
	protected void sequence_AstFunction(EObject context, AstFunction semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (variable=AstVariableDeclaration expression=AstExpression)
	 *
	 * Features:
	 *    variable[1, 1]
	 *    expression[1, 1]
	 */
	protected void sequence_AstGenerator(EObject context, AstGenerator semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, CalPackage.Literals.AST_GENERATOR__VARIABLE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalPackage.Literals.AST_GENERATOR__VARIABLE));
			if(transientValues.isValueTransient(semanticObject, CalPackage.Literals.AST_GENERATOR__EXPRESSION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalPackage.Literals.AST_GENERATOR__EXPRESSION));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getAstGeneratorAccess().getVariableAstVariableDeclarationParserRuleCall_1_0(), semanticObject.getVariable());
		feeder.accept(grammarAccess.getAstGeneratorAccess().getExpressionAstExpressionParserRuleCall_3_0(), semanticObject.getExpression());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (tags+=AstTag tags+=AstTag+)
	 *
	 * Features:
	 *    tags[2, *]
	 */
	protected void sequence_AstInequality(EObject context, AstInequality semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         annotations+=AstAnnotation* 
	 *         tag=AstTag? 
	 *         (outputs+=AstOutputPattern outputs+=AstOutputPattern*)? 
	 *         (guards+=AstExpression guards+=AstExpression*)? 
	 *         (variables+=AstValuedVariableDeclaration variables+=AstValuedVariableDeclaration*)? 
	 *         statements+=AstStatement*
	 *     )
	 *
	 * Features:
	 *    annotations[0, *]
	 *    tag[0, 1]
	 *    outputs[0, *]
	 *    guards[0, *]
	 *    variables[0, *]
	 *    statements[0, *]
	 */
	protected void sequence_AstInitialize(EObject context, AstInitialize semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (port=[AstPort|ID]? tokens+=AstToken tokens+=AstToken* repeat=AstExpression?)
	 *
	 * Features:
	 *    port[0, 1]
	 *    tokens[1, *]
	 *    repeat[0, 1]
	 */
	protected void sequence_AstInputPattern(EObject context, AstInputPattern semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID memberIndex+=AstExpression*)
	 *
	 * Features:
	 *    name[1, 1]
	 *    memberIndex[0, *]
	 */
	protected void sequence_AstMemberAccess(EObject context, AstMemberAccess semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         annotations+=AstAnnotation* 
	 *         name=QualifiedName 
	 *         (
	 *             functions+=AstFunction | 
	 *             variables+=AstConstantVariable | 
	 *             externals+=AstExternalVariable | 
	 *             externals+=AstExternalFunction | 
	 *             externals+=AstExternalProcedure | 
	 *             typedefs+=AstTypeDefinition | 
	 *             imports+=Import | 
	 *             entities+=AstEntity | 
	 *             namespaces+=AstNamespace
	 *         )*
	 *     )
	 *
	 * Features:
	 *    name[1, 1]
	 *    entities[0, *]
	 *    imports[0, *]
	 *    functions[0, *]
	 *    variables[0, *]
	 *    externals[0, *]
	 *    annotations[0, *]
	 *    typedefs[0, *]
	 *    namespaces[0, *]
	 */
	protected void sequence_AstNamespace(EObject context, AstNamespace semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (parameters+=AstParameter parameters+=AstParameter*)? 
	 *         (inputs+=AstPort inputs+=AstPort*)? 
	 *         (outputs+=AstPort outputs+=AstPort*)? 
	 *         (variables+=AstValuedVariableDeclaration variables+=AstValuedVariableDeclaration*)? 
	 *         instances+=AstActorDeclaration+ 
	 *         structure=AstStructure
	 *     )
	 *
	 * Features:
	 *    parameters[0, *]
	 *    inputs[0, *]
	 *    outputs[0, *]
	 *    variables[0, *]
	 *    instances[1, *]
	 *    structure[1, 1]
	 */
	protected void sequence_AstNetwork(EObject context, AstNetwork semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (port=[AstPort|ID]? values+=AstExpression values+=AstExpression* repeat=AstExpression?)
	 *
	 * Features:
	 *    port[0, 1]
	 *    values[1, *]
	 *    repeat[0, 1]
	 */
	protected void sequence_AstOutputPattern(EObject context, AstOutputPattern semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=QualifiedName (entities+=AstEntity | imports+=Import | units+=AstUnit)*)
	 *
	 * Features:
	 *    name[1, 1]
	 *    entities[0, *]
	 *    imports[0, *]
	 *    units[0, *]
	 */
	protected void sequence_AstPackage(EObject context, AstNamespace semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (annotations+=AstAnnotation* type=AstType name=ID dimensions+=AstExpression* value=AstExpression?)
	 *
	 * Features:
	 *    value[0, 1]
	 *    name[1, 1]
	 *    annotations[0, *]
	 *    type[1, 1]
	 *    dimensions[0, *]
	 */
	protected void sequence_AstParameter(EObject context, AstVariable semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (annotations+=AstAnnotation* type=AstType name=ID)
	 *
	 * Features:
	 *    annotations[0, *]
	 *    type[1, 1]
	 *    name[1, 1]
	 */
	protected void sequence_AstPort(EObject context, AstPort semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (inequalities+=AstInequality*)
	 *
	 * Features:
	 *    inequalities[0, *]
	 */
	protected void sequence_AstPriority(EObject context, AstPriority semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         annotations+=AstAnnotation* 
	 *         name=ID 
	 *         (parameters+=AstVariableDeclaration parameters+=AstVariableDeclaration*)? 
	 *         (variables+=AstValuedVariableDeclaration variables+=AstValuedVariableDeclaration*)? 
	 *         statements+=AstStatement*
	 *     )
	 *
	 * Features:
	 *    annotations[0, *]
	 *    name[1, 1]
	 *    parameters[0, *]
	 *    variables[0, *]
	 *    statements[0, *]
	 */
	protected void sequence_AstProcedure(EObject context, AstProcedure semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (initialState=[AstState|ID] transitions+=AstTransition*)
	 *
	 * Features:
	 *    initialState[1, 1]
	 *    transitions[0, *]
	 */
	protected void sequence_AstSchedule(EObject context, AstSchedule semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     name=ID
	 *
	 * Features:
	 *    name[1, 1]
	 */
	protected void sequence_AstState(EObject context, AstState semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, CalPackage.Literals.AST_STATE__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalPackage.Literals.AST_STATE__NAME));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getAstStateAccess().getNameIDTerminalRuleCall_0(), semanticObject.getName());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (target=AstVariableReference indexes+=AstExpression* member+=AstMemberAccess* value=AstExpression)
	 *
	 * Features:
	 *    target[1, 1]
	 *    indexes[0, *]
	 *    member[0, *]
	 *    value[1, 1]
	 */
	protected void sequence_AstStatementAssign(EObject context, AstStatementAssign semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((variables+=AstValuedVariableDeclaration variables+=AstValuedVariableDeclaration*)? statements+=AstStatement*)
	 *
	 * Features:
	 *    variables[0, *]
	 *    statements[0, *]
	 */
	protected void sequence_AstStatementBlock(EObject context, AstStatementBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (procedure=[AstProcedure|ID] (parameters+=AstExpression parameters+=AstExpression*)?)
	 *
	 * Features:
	 *    procedure[1, 1]
	 *    parameters[0, *]
	 */
	protected void sequence_AstStatementCall(EObject context, AstStatementCall semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         generators+=AstForeachGenerator 
	 *         generators+=AstForeachGenerator* 
	 *         (variables+=AstValuedVariableDeclaration variables+=AstValuedVariableDeclaration*)? 
	 *         statements+=AstStatement*
	 *     )
	 *
	 * Features:
	 *    generators[1, *]
	 *    variables[0, *]
	 *    statements[0, *]
	 */
	protected void sequence_AstStatementForeach(EObject context, AstStatementForeach semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (condition=AstExpression then+=AstStatement* else+=AstStatement*)
	 *
	 * Features:
	 *    condition[1, 1]
	 *    then[0, *]
	 *    else[0, *]
	 */
	protected void sequence_AstStatementIf(EObject context, AstStatementIf semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (condition=AstExpression statements+=AstStatement*)
	 *
	 * Features:
	 *    condition[1, 1]
	 *    statements[0, *]
	 */
	protected void sequence_AstStatementWhile(EObject context, AstStatementWhile semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     connections+=AstConnection+
	 *
	 * Features:
	 *    connections[1, *]
	 */
	protected void sequence_AstStructure(EObject context, AstStructure semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifiers+=ID identifiers+=ID*)
	 *
	 * Features:
	 *    identifiers[1, *]
	 */
	protected void sequence_AstTag(EObject context, AstTag semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     name=ID
	 *
	 * Features:
	 *    name[1, 1]
	 */
	protected void sequence_AstToken(EObject context, AstVariable semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (name=QualifiedName (entities+=AstEntity | imports+=Import | units+=AstUnit)*) | 
	 *         (
	 *             annotations+=AstAnnotation* 
	 *             name=QualifiedName 
	 *             (
	 *                 functions+=AstFunction | 
	 *                 variables+=AstConstantVariable | 
	 *                 externals+=AstExternalVariable | 
	 *                 externals+=AstExternalFunction | 
	 *                 externals+=AstExternalProcedure | 
	 *                 typedefs+=AstTypeDefinition | 
	 *                 imports+=Import | 
	 *                 entities+=AstEntity | 
	 *                 namespaces+=AstNamespace
	 *             )*
	 *         )
	 *     )
	 *
	 * Features:
	 *    name[0, 2]
	 *    entities[0, *]
	 *    imports[0, *]
	 *    units[0, *]
	 *         EXCLUDE_IF_UNSET name
	 *         EXCLUDE_IF_SET annotations
	 *         EXCLUDE_IF_SET name
	 *         EXCLUDE_IF_SET functions
	 *         EXCLUDE_IF_SET variables
	 *         EXCLUDE_IF_SET externals
	 *         EXCLUDE_IF_SET externals
	 *         EXCLUDE_IF_SET externals
	 *         EXCLUDE_IF_SET typedefs
	 *         EXCLUDE_IF_SET imports
	 *         EXCLUDE_IF_SET entities
	 *         EXCLUDE_IF_SET namespaces
	 *    functions[0, *]
	 *         EXCLUDE_IF_UNSET name
	 *         EXCLUDE_IF_SET name
	 *         EXCLUDE_IF_SET entities
	 *         EXCLUDE_IF_SET imports
	 *         EXCLUDE_IF_SET units
	 *    variables[0, *]
	 *         EXCLUDE_IF_UNSET name
	 *         EXCLUDE_IF_SET name
	 *         EXCLUDE_IF_SET entities
	 *         EXCLUDE_IF_SET imports
	 *         EXCLUDE_IF_SET units
	 *    externals[0, *]
	 *         EXCLUDE_IF_UNSET name
	 *         EXCLUDE_IF_SET name
	 *         EXCLUDE_IF_SET entities
	 *         EXCLUDE_IF_SET imports
	 *         EXCLUDE_IF_SET units
	 *    annotations[0, *]
	 *         EXCLUDE_IF_UNSET name
	 *         EXCLUDE_IF_SET name
	 *         EXCLUDE_IF_SET entities
	 *         EXCLUDE_IF_SET imports
	 *         EXCLUDE_IF_SET units
	 *    typedefs[0, *]
	 *         EXCLUDE_IF_UNSET name
	 *         EXCLUDE_IF_SET name
	 *         EXCLUDE_IF_SET entities
	 *         EXCLUDE_IF_SET imports
	 *         EXCLUDE_IF_SET units
	 *    namespaces[0, *]
	 *         EXCLUDE_IF_UNSET name
	 *         EXCLUDE_IF_SET name
	 *         EXCLUDE_IF_SET entities
	 *         EXCLUDE_IF_SET imports
	 *         EXCLUDE_IF_SET units
	 */
	protected void sequence_AstTop(EObject context, AstNamespace semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (source=[AstState|ID] tags+=AstTag tags+=AstTag* target=[AstState|ID])
	 *
	 * Features:
	 *    source[1, 1]
	 *    tags[1, *]
	 *    target[1, 1]
	 */
	protected void sequence_AstTransition(EObject context, AstTransition semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (value=AstVariableDeclaration | type=AstTypeDefinitionTypeParameter)
	 *
	 * Features:
	 *    value[0, 1]
	 *         EXCLUDE_IF_SET type
	 *    type[0, 1]
	 *         EXCLUDE_IF_SET value
	 */
	protected void sequence_AstTypeDefinitionParameter(EObject context, AstTypeDefinitionParameter semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     name=ID
	 *
	 * Features:
	 *    name[1, 1]
	 */
	protected void sequence_AstTypeDefinitionTypeParameter(EObject context, AstTypeName semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         name=ID 
	 *         (parameters+=AstTypeDefinitionParameter parameters+=AstTypeDefinitionParameter*)? 
	 *         ((constructor+=AstConstructor constructor+=AstConstructor?) | type=AstType)
	 *     )
	 *
	 * Features:
	 *    name[1, 1]
	 *    parameters[0, *]
	 *    constructor[0, 2]
	 *         EXCLUDE_IF_SET type
	 *    type[0, 1]
	 *         EXCLUDE_IF_SET constructor
	 *         EXCLUDE_IF_SET constructor
	 */
	protected void sequence_AstTypeDefinition(EObject context, AstTypeName semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((name=ID value=AstExpression) | ((name=ID | name='type') type=AstType))
	 *
	 * Features:
	 *    name[0, 3]
	 *    value[0, 1]
	 *         EXCLUDE_IF_UNSET name
	 *         MANDATORY_IF_SET name
	 *         EXCLUDE_IF_SET name
	 *         EXCLUDE_IF_SET name
	 *         EXCLUDE_IF_SET type
	 *    type[0, 1]
	 *         MANDATORY_IF_SET name
	 *         MANDATORY_IF_SET name
	 *         EXCLUDE_IF_SET name
	 *         EXCLUDE_IF_SET value
	 */
	protected void sequence_AstTypeParam(EObject context, AstTypeParam semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (params+=AstTypeParam params+=AstTypeParam*)
	 *
	 * Features:
	 *    params[1, *]
	 */
	protected void sequence_AstTypeParameterList(EObject context, AstTypeParameterList semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((members+=AstValuedVariableDeclaration members+=AstValuedVariableDeclaration*)?)
	 *
	 * Features:
	 *    members[0, *]
	 */
	protected void sequence_AstTypeTuple(EObject context, AstType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (builtin=AstBuiltInType typeParams=AstTypeParameterList? dimensions+=AstExpression*) | 
	 *         (name=[AstTypeName|ID] typeParams=AstTypeParameterList? dimensions+=AstExpression*) | 
	 *         ((domain+=AstType domain+=AstType*)? (codomain+=AstType domain+=AstType*)?) | 
	 *         ((members+=AstValuedVariableDeclaration members+=AstValuedVariableDeclaration*)?)
	 *     )
	 *
	 * Features:
	 *    builtin[0, 1]
	 *         MANDATORY_IF_SET typeParams
	 *         MANDATORY_IF_SET dimensions
	 *         EXCLUDE_IF_SET name
	 *         EXCLUDE_IF_SET typeParams
	 *         EXCLUDE_IF_SET dimensions
	 *         EXCLUDE_IF_SET domain
	 *         EXCLUDE_IF_SET domain
	 *         EXCLUDE_IF_SET codomain
	 *         EXCLUDE_IF_SET domain
	 *         EXCLUDE_IF_SET members
	 *         EXCLUDE_IF_SET members
	 *    typeParams[0, 2]
	 *         EXCLUDE_IF_SET domain
	 *         EXCLUDE_IF_SET domain
	 *         EXCLUDE_IF_SET codomain
	 *         EXCLUDE_IF_SET domain
	 *         EXCLUDE_IF_SET members
	 *         EXCLUDE_IF_SET members
	 *    dimensions[0, *]
	 *         EXCLUDE_IF_SET domain
	 *         EXCLUDE_IF_SET domain
	 *         EXCLUDE_IF_SET codomain
	 *         EXCLUDE_IF_SET domain
	 *         EXCLUDE_IF_SET members
	 *         EXCLUDE_IF_SET members
	 *    name[0, 1]
	 *         MANDATORY_IF_SET typeParams
	 *         MANDATORY_IF_SET dimensions
	 *         EXCLUDE_IF_SET builtin
	 *         EXCLUDE_IF_SET typeParams
	 *         EXCLUDE_IF_SET dimensions
	 *         EXCLUDE_IF_SET domain
	 *         EXCLUDE_IF_SET domain
	 *         EXCLUDE_IF_SET codomain
	 *         EXCLUDE_IF_SET domain
	 *         EXCLUDE_IF_SET members
	 *         EXCLUDE_IF_SET members
	 *    domain[0, *]
	 *         EXCLUDE_IF_SET builtin
	 *         EXCLUDE_IF_SET typeParams
	 *         EXCLUDE_IF_SET dimensions
	 *         EXCLUDE_IF_SET name
	 *         EXCLUDE_IF_SET typeParams
	 *         EXCLUDE_IF_SET dimensions
	 *         EXCLUDE_IF_SET members
	 *         EXCLUDE_IF_SET members
	 *    codomain[0, 1]
	 *         MANDATORY_IF_SET domain
	 *         EXCLUDE_IF_SET builtin
	 *         EXCLUDE_IF_SET typeParams
	 *         EXCLUDE_IF_SET dimensions
	 *         EXCLUDE_IF_SET name
	 *         EXCLUDE_IF_SET typeParams
	 *         EXCLUDE_IF_SET dimensions
	 *         EXCLUDE_IF_SET members
	 *         EXCLUDE_IF_SET members
	 *    members[0, *]
	 *         EXCLUDE_IF_SET builtin
	 *         EXCLUDE_IF_SET typeParams
	 *         EXCLUDE_IF_SET dimensions
	 *         EXCLUDE_IF_SET name
	 *         EXCLUDE_IF_SET typeParams
	 *         EXCLUDE_IF_SET dimensions
	 *         EXCLUDE_IF_SET domain
	 *         EXCLUDE_IF_SET domain
	 *         EXCLUDE_IF_SET codomain
	 *         EXCLUDE_IF_SET domain
	 */
	protected void sequence_AstType(EObject context, AstType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         name=QualifiedName 
	 *         (
	 *             functions+=AstFunction | 
	 *             variables+=AstConstantVariable | 
	 *             externals+=AstExternalVariable | 
	 *             externals+=AstExternalFunction | 
	 *             externals+=AstExternalProcedure
	 *         )*
	 *     )
	 *
	 * Features:
	 *    name[1, 1]
	 *    functions[0, *]
	 *    variables[0, *]
	 *    externals[0, *]
	 */
	protected void sequence_AstUnit(EObject context, AstNamespace semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (annotations+=AstAnnotation* type=AstType name=ID dimensions+=AstExpression* (constant?='='? value=AstExpression)?)
	 *
	 * Features:
	 *    constant[0, 1]
	 *         EXCLUDE_IF_UNSET value
	 *    value[0, 1]
	 *         MANDATORY_IF_SET constant
	 *    name[1, 1]
	 *    annotations[0, *]
	 *    type[1, 1]
	 *    dimensions[0, *]
	 */
	protected void sequence_AstValuedVariableDeclaration(EObject context, AstVariable semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (annotations+=AstAnnotation* type=AstType name=ID dimensions+=AstExpression*)
	 *
	 * Features:
	 *    name[1, 1]
	 *    annotations[0, *]
	 *    type[1, 1]
	 *    dimensions[0, *]
	 */
	protected void sequence_AstVariableDeclaration(EObject context, AstVariable semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     variable=[AstVariable|ID]
	 *
	 * Features:
	 *    variable[1, 1]
	 */
	protected void sequence_AstVariableReference(EObject context, AstVariableReference semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, CalPackage.Literals.AST_VARIABLE_REFERENCE__VARIABLE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalPackage.Literals.AST_VARIABLE_REFERENCE__VARIABLE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getAstVariableReferenceAccess().getVariableAstVariableIDTerminalRuleCall_0_1(), semanticObject.getVariable());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     importedNamespace=QualifiedNameWithWildCard
	 *
	 * Features:
	 *    importedNamespace[1, 1]
	 */
	protected void sequence_Import(EObject context, Import semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, CalPackage.Literals.IMPORT__IMPORTED_NAMESPACE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalPackage.Literals.IMPORT__IMPORTED_NAMESPACE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getImportAccess().getImportedNamespaceQualifiedNameWithWildCardParserRuleCall_1_0(), semanticObject.getImportedNamespace());
		feeder.finish();
	}
}
