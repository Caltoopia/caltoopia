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
import org.caltoopia.frontend.cal.AstExpressionAlternative;
import org.caltoopia.frontend.cal.AstExpressionBinary;
import org.caltoopia.frontend.cal.AstExpressionBoolean;
import org.caltoopia.frontend.cal.AstExpressionCase;
import org.caltoopia.frontend.cal.AstExpressionFloat;
import org.caltoopia.frontend.cal.AstExpressionIf;
import org.caltoopia.frontend.cal.AstExpressionInteger;
import org.caltoopia.frontend.cal.AstExpressionList;
import org.caltoopia.frontend.cal.AstExpressionString;
import org.caltoopia.frontend.cal.AstExpressionSymbolReference;
import org.caltoopia.frontend.cal.AstExpressionUnary;
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
import org.caltoopia.frontend.cal.AstPattern;
import org.caltoopia.frontend.cal.AstPatternExpressionBinary;
import org.caltoopia.frontend.cal.AstPatternExpressionIf;
import org.caltoopia.frontend.cal.AstPatternExpressionSymbolReference;
import org.caltoopia.frontend.cal.AstPatternExpressionUnary;
import org.caltoopia.frontend.cal.AstPort;
import org.caltoopia.frontend.cal.AstPriority;
import org.caltoopia.frontend.cal.AstProcedure;
import org.caltoopia.frontend.cal.AstSchedule;
import org.caltoopia.frontend.cal.AstState;
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
import org.caltoopia.frontend.cal.AstTransition;
import org.caltoopia.frontend.cal.AstTuple;
import org.caltoopia.frontend.cal.AstType;
import org.caltoopia.frontend.cal.AstTypeDefinitionParameter;
import org.caltoopia.frontend.cal.AstTypeParam;
import org.caltoopia.frontend.cal.AstTypeParameterList;
import org.caltoopia.frontend.cal.AstTypeUser;
import org.caltoopia.frontend.cal.AstVariable;
import org.caltoopia.frontend.cal.CalPackage;
import org.caltoopia.frontend.cal.Import;
import org.caltoopia.frontend.services.CalGrammarAccess;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticNodeProvider.INodesForEObjectProvider;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("all")
public abstract class AbstractCalSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private CalGrammarAccess grammarAccess;
	
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == CalPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case CalPackage.AST_ACTION:
				if(context == grammarAccess.getAstActionRule()) {
					sequence_AstAction(context, (AstAction) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_ACTOR:
				if(context == grammarAccess.getAstAbstractActorRule() ||
				   context == grammarAccess.getAstActorRule()) {
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
			case CalPackage.AST_EXPRESSION_ALTERNATIVE:
				if(context == grammarAccess.getAstExpressionAlternativeRule()) {
					sequence_AstExpressionAlternative(context, (AstExpressionAlternative) semanticObject); 
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
					sequence_AstExpression_AstExpressionAdditive_AstExpressionAnd_AstExpressionBitand_AstExpressionBitor_AstExpressionBitxor_AstExpressionEq_AstExpressionExp_AstExpressionMultiplicative_AstExpressionRelational_AstExpressionShift(context, (AstExpressionBinary) semanticObject); 
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
				   context == grammarAccess.getAstExpressionAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionRule() ||
				   context == grammarAccess.getAstPatternExpressionAdditiveRule() ||
				   context == grammarAccess.getAstPatternExpressionAdditiveAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionAndRule() ||
				   context == grammarAccess.getAstPatternExpressionAndAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionBitandRule() ||
				   context == grammarAccess.getAstPatternExpressionBitandAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionBitorRule() ||
				   context == grammarAccess.getAstPatternExpressionBitorAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionBitxorRule() ||
				   context == grammarAccess.getAstPatternExpressionBitxorAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionEqRule() ||
				   context == grammarAccess.getAstPatternExpressionEqAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionExpRule() ||
				   context == grammarAccess.getAstPatternExpressionExpAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionLiteralRule() ||
				   context == grammarAccess.getAstPatternExpressionMultiplicativeRule() ||
				   context == grammarAccess.getAstPatternExpressionMultiplicativeAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionPostfixRule() ||
				   context == grammarAccess.getAstPatternExpressionRelationalRule() ||
				   context == grammarAccess.getAstPatternExpressionRelationalAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionShiftRule() ||
				   context == grammarAccess.getAstPatternExpressionShiftAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionUnaryRule() ||
				   context == grammarAccess.getAstPatternExpressionAccess().getAstPatternExpressionBinaryLeftAction_1_0()) {
					sequence_AstExpressionBoolean(context, (AstExpressionBoolean) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_EXPRESSION_CASE:
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
				   context == grammarAccess.getAstExpressionCaseRule() ||
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
				   context == grammarAccess.getAstExpressionAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionRule() ||
				   context == grammarAccess.getAstPatternExpressionAdditiveRule() ||
				   context == grammarAccess.getAstPatternExpressionAdditiveAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionAndRule() ||
				   context == grammarAccess.getAstPatternExpressionAndAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionBitandRule() ||
				   context == grammarAccess.getAstPatternExpressionBitandAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionBitorRule() ||
				   context == grammarAccess.getAstPatternExpressionBitorAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionBitxorRule() ||
				   context == grammarAccess.getAstPatternExpressionBitxorAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionEqRule() ||
				   context == grammarAccess.getAstPatternExpressionEqAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionExpRule() ||
				   context == grammarAccess.getAstPatternExpressionExpAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionMultiplicativeRule() ||
				   context == grammarAccess.getAstPatternExpressionMultiplicativeAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionPostfixRule() ||
				   context == grammarAccess.getAstPatternExpressionRelationalRule() ||
				   context == grammarAccess.getAstPatternExpressionRelationalAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionShiftRule() ||
				   context == grammarAccess.getAstPatternExpressionShiftAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionUnaryRule() ||
				   context == grammarAccess.getAstPatternExpressionAccess().getAstPatternExpressionBinaryLeftAction_1_0()) {
					sequence_AstExpressionCase(context, (AstExpressionCase) semanticObject); 
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
				   context == grammarAccess.getAstExpressionAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionRule() ||
				   context == grammarAccess.getAstPatternExpressionAdditiveRule() ||
				   context == grammarAccess.getAstPatternExpressionAdditiveAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionAndRule() ||
				   context == grammarAccess.getAstPatternExpressionAndAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionBitandRule() ||
				   context == grammarAccess.getAstPatternExpressionBitandAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionBitorRule() ||
				   context == grammarAccess.getAstPatternExpressionBitorAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionBitxorRule() ||
				   context == grammarAccess.getAstPatternExpressionBitxorAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionEqRule() ||
				   context == grammarAccess.getAstPatternExpressionEqAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionExpRule() ||
				   context == grammarAccess.getAstPatternExpressionExpAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionLiteralRule() ||
				   context == grammarAccess.getAstPatternExpressionMultiplicativeRule() ||
				   context == grammarAccess.getAstPatternExpressionMultiplicativeAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionPostfixRule() ||
				   context == grammarAccess.getAstPatternExpressionRelationalRule() ||
				   context == grammarAccess.getAstPatternExpressionRelationalAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionShiftRule() ||
				   context == grammarAccess.getAstPatternExpressionShiftAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionUnaryRule() ||
				   context == grammarAccess.getAstPatternExpressionAccess().getAstPatternExpressionBinaryLeftAction_1_0()) {
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
				   context == grammarAccess.getAstExpressionAccess().getAstExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionRule() ||
				   context == grammarAccess.getAstPatternExpressionAdditiveRule() ||
				   context == grammarAccess.getAstPatternExpressionAdditiveAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionAndRule() ||
				   context == grammarAccess.getAstPatternExpressionAndAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionBitandRule() ||
				   context == grammarAccess.getAstPatternExpressionBitandAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionBitorRule() ||
				   context == grammarAccess.getAstPatternExpressionBitorAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionBitxorRule() ||
				   context == grammarAccess.getAstPatternExpressionBitxorAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionEqRule() ||
				   context == grammarAccess.getAstPatternExpressionEqAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionExpRule() ||
				   context == grammarAccess.getAstPatternExpressionExpAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionLiteralRule() ||
				   context == grammarAccess.getAstPatternExpressionMultiplicativeRule() ||
				   context == grammarAccess.getAstPatternExpressionMultiplicativeAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionPostfixRule() ||
				   context == grammarAccess.getAstPatternExpressionRelationalRule() ||
				   context == grammarAccess.getAstPatternExpressionRelationalAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionShiftRule() ||
				   context == grammarAccess.getAstPatternExpressionShiftAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionUnaryRule() ||
				   context == grammarAccess.getAstPatternExpressionAccess().getAstPatternExpressionBinaryLeftAction_1_0()) {
					sequence_AstExpressionString(context, (AstExpressionString) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_EXPRESSION_SYMBOL_REFERENCE:
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
				   context == grammarAccess.getAstExpressionSymbolReferenceRule() ||
				   context == grammarAccess.getAstExpressionUnaryRule() ||
				   context == grammarAccess.getAstExpressionAccess().getAstExpressionBinaryLeftAction_1_0()) {
					sequence_AstExpressionSymbolReference(context, (AstExpressionSymbolReference) semanticObject); 
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
			case CalPackage.AST_EXTERNAL_ACTOR:
				if(context == grammarAccess.getAstAbstractActorRule() ||
				   context == grammarAccess.getAstExternalActorRule()) {
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
				if(context == grammarAccess.getAstExternalFunctionRule()) {
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
				else if(context == grammarAccess.getAstTopRule()) {
					sequence_AstNamespace_AstPackage_AstTop(context, (AstNamespace) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getAstPackageRule()) {
					sequence_AstPackage(context, (AstNamespace) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getAstUnitRule()) {
					sequence_AstUnit(context, (AstNamespace) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_NETWORK:
				if(context == grammarAccess.getAstAbstractActorRule() ||
				   context == grammarAccess.getAstNetworkRule()) {
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
			case CalPackage.AST_PATTERN:
				if(context == grammarAccess.getAstPatternRule()) {
					sequence_AstPattern(context, (AstPattern) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_PATTERN_EXPRESSION_BINARY:
				if(context == grammarAccess.getAstPatternExpressionAndRule() ||
				   context == grammarAccess.getAstPatternExpressionAndAccess().getAstPatternExpressionBinaryLeftAction_1_0()) {
					sequence_AstPatternExpressionAdditive_AstPatternExpressionAnd_AstPatternExpressionBitand_AstPatternExpressionBitor_AstPatternExpressionBitxor_AstPatternExpressionEq_AstPatternExpressionExp_AstPatternExpressionMultiplicative_AstPatternExpressionRelational_AstPatternExpressionShift(context, (AstPatternExpressionBinary) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getAstPatternExpressionBitorRule() ||
				   context == grammarAccess.getAstPatternExpressionBitorAccess().getAstPatternExpressionBinaryLeftAction_1_0()) {
					sequence_AstPatternExpressionAdditive_AstPatternExpressionBitand_AstPatternExpressionBitor_AstPatternExpressionBitxor_AstPatternExpressionEq_AstPatternExpressionExp_AstPatternExpressionMultiplicative_AstPatternExpressionRelational_AstPatternExpressionShift(context, (AstPatternExpressionBinary) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getAstPatternExpressionBitxorRule() ||
				   context == grammarAccess.getAstPatternExpressionBitxorAccess().getAstPatternExpressionBinaryLeftAction_1_0()) {
					sequence_AstPatternExpressionAdditive_AstPatternExpressionBitand_AstPatternExpressionBitxor_AstPatternExpressionEq_AstPatternExpressionExp_AstPatternExpressionMultiplicative_AstPatternExpressionRelational_AstPatternExpressionShift(context, (AstPatternExpressionBinary) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getAstPatternExpressionBitandRule() ||
				   context == grammarAccess.getAstPatternExpressionBitandAccess().getAstPatternExpressionBinaryLeftAction_1_0()) {
					sequence_AstPatternExpressionAdditive_AstPatternExpressionBitand_AstPatternExpressionEq_AstPatternExpressionExp_AstPatternExpressionMultiplicative_AstPatternExpressionRelational_AstPatternExpressionShift(context, (AstPatternExpressionBinary) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getAstPatternExpressionEqRule() ||
				   context == grammarAccess.getAstPatternExpressionEqAccess().getAstPatternExpressionBinaryLeftAction_1_0()) {
					sequence_AstPatternExpressionAdditive_AstPatternExpressionEq_AstPatternExpressionExp_AstPatternExpressionMultiplicative_AstPatternExpressionRelational_AstPatternExpressionShift(context, (AstPatternExpressionBinary) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getAstPatternExpressionAdditiveRule() ||
				   context == grammarAccess.getAstPatternExpressionAdditiveAccess().getAstPatternExpressionBinaryLeftAction_1_0()) {
					sequence_AstPatternExpressionAdditive_AstPatternExpressionExp_AstPatternExpressionMultiplicative(context, (AstPatternExpressionBinary) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getAstPatternExpressionRelationalRule() ||
				   context == grammarAccess.getAstPatternExpressionRelationalAccess().getAstPatternExpressionBinaryLeftAction_1_0()) {
					sequence_AstPatternExpressionAdditive_AstPatternExpressionExp_AstPatternExpressionMultiplicative_AstPatternExpressionRelational_AstPatternExpressionShift(context, (AstPatternExpressionBinary) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getAstPatternExpressionShiftRule() ||
				   context == grammarAccess.getAstPatternExpressionShiftAccess().getAstPatternExpressionBinaryLeftAction_1_0()) {
					sequence_AstPatternExpressionAdditive_AstPatternExpressionExp_AstPatternExpressionMultiplicative_AstPatternExpressionShift(context, (AstPatternExpressionBinary) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getAstPatternExpressionExpRule() ||
				   context == grammarAccess.getAstPatternExpressionExpAccess().getAstPatternExpressionBinaryLeftAction_1_0()) {
					sequence_AstPatternExpressionExp(context, (AstPatternExpressionBinary) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getAstPatternExpressionMultiplicativeRule() ||
				   context == grammarAccess.getAstPatternExpressionMultiplicativeAccess().getAstPatternExpressionBinaryLeftAction_1_0()) {
					sequence_AstPatternExpressionExp_AstPatternExpressionMultiplicative(context, (AstPatternExpressionBinary) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getAstPatternExpressionRule() ||
				   context == grammarAccess.getAstPatternExpressionAccess().getAstPatternExpressionBinaryLeftAction_1_0()) {
					sequence_AstPatternExpression_AstPatternExpressionAdditive_AstPatternExpressionAnd_AstPatternExpressionBitand_AstPatternExpressionBitor_AstPatternExpressionBitxor_AstPatternExpressionEq_AstPatternExpressionExp_AstPatternExpressionMultiplicative_AstPatternExpressionRelational_AstPatternExpressionShift(context, (AstPatternExpressionBinary) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_PATTERN_EXPRESSION_IF:
				if(context == grammarAccess.getAstPatternExpressionRule() ||
				   context == grammarAccess.getAstPatternExpressionAdditiveRule() ||
				   context == grammarAccess.getAstPatternExpressionAdditiveAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionAndRule() ||
				   context == grammarAccess.getAstPatternExpressionAndAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionBitandRule() ||
				   context == grammarAccess.getAstPatternExpressionBitandAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionBitorRule() ||
				   context == grammarAccess.getAstPatternExpressionBitorAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionBitxorRule() ||
				   context == grammarAccess.getAstPatternExpressionBitxorAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionEqRule() ||
				   context == grammarAccess.getAstPatternExpressionEqAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionExpRule() ||
				   context == grammarAccess.getAstPatternExpressionExpAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionIfRule() ||
				   context == grammarAccess.getAstPatternExpressionMultiplicativeRule() ||
				   context == grammarAccess.getAstPatternExpressionMultiplicativeAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionPostfixRule() ||
				   context == grammarAccess.getAstPatternExpressionRelationalRule() ||
				   context == grammarAccess.getAstPatternExpressionRelationalAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionShiftRule() ||
				   context == grammarAccess.getAstPatternExpressionShiftAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionUnaryRule() ||
				   context == grammarAccess.getAstPatternExpressionAccess().getAstPatternExpressionBinaryLeftAction_1_0()) {
					sequence_AstPatternExpressionIf(context, (AstPatternExpressionIf) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_PATTERN_EXPRESSION_SYMBOL_REFERENCE:
				if(context == grammarAccess.getAstPatternExpressionRule() ||
				   context == grammarAccess.getAstPatternExpressionAdditiveRule() ||
				   context == grammarAccess.getAstPatternExpressionAdditiveAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionAndRule() ||
				   context == grammarAccess.getAstPatternExpressionAndAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionBitandRule() ||
				   context == grammarAccess.getAstPatternExpressionBitandAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionBitorRule() ||
				   context == grammarAccess.getAstPatternExpressionBitorAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionBitxorRule() ||
				   context == grammarAccess.getAstPatternExpressionBitxorAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionEqRule() ||
				   context == grammarAccess.getAstPatternExpressionEqAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionExpRule() ||
				   context == grammarAccess.getAstPatternExpressionExpAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionMultiplicativeRule() ||
				   context == grammarAccess.getAstPatternExpressionMultiplicativeAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionPostfixRule() ||
				   context == grammarAccess.getAstPatternExpressionRelationalRule() ||
				   context == grammarAccess.getAstPatternExpressionRelationalAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionShiftRule() ||
				   context == grammarAccess.getAstPatternExpressionShiftAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionSymbolReferenceRule() ||
				   context == grammarAccess.getAstPatternExpressionUnaryRule() ||
				   context == grammarAccess.getAstPatternExpressionAccess().getAstPatternExpressionBinaryLeftAction_1_0()) {
					sequence_AstPatternExpressionSymbolReference(context, (AstPatternExpressionSymbolReference) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_PATTERN_EXPRESSION_UNARY:
				if(context == grammarAccess.getAstPatternExpressionRule() ||
				   context == grammarAccess.getAstPatternExpressionAdditiveRule() ||
				   context == grammarAccess.getAstPatternExpressionAdditiveAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionAndRule() ||
				   context == grammarAccess.getAstPatternExpressionAndAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionBitandRule() ||
				   context == grammarAccess.getAstPatternExpressionBitandAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionBitorRule() ||
				   context == grammarAccess.getAstPatternExpressionBitorAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionBitxorRule() ||
				   context == grammarAccess.getAstPatternExpressionBitxorAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionEqRule() ||
				   context == grammarAccess.getAstPatternExpressionEqAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionExpRule() ||
				   context == grammarAccess.getAstPatternExpressionExpAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionMultiplicativeRule() ||
				   context == grammarAccess.getAstPatternExpressionMultiplicativeAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionRelationalRule() ||
				   context == grammarAccess.getAstPatternExpressionRelationalAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionShiftRule() ||
				   context == grammarAccess.getAstPatternExpressionShiftAccess().getAstPatternExpressionBinaryLeftAction_1_0() ||
				   context == grammarAccess.getAstPatternExpressionUnaryRule() ||
				   context == grammarAccess.getAstPatternExpressionAccess().getAstPatternExpressionBinaryLeftAction_1_0()) {
					sequence_AstPatternExpressionUnary(context, (AstPatternExpressionUnary) semanticObject); 
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
			case CalPackage.AST_STATEMENT_ALTERNATIVE:
				if(context == grammarAccess.getAstStatementAlternativeRule()) {
					sequence_AstStatementAlternative(context, (AstStatementAlternative) semanticObject); 
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
			case CalPackage.AST_STATEMENT_CASE:
				if(context == grammarAccess.getAstStatementRule() ||
				   context == grammarAccess.getAstStatementCaseRule()) {
					sequence_AstStatementCase(context, (AstStatementCase) semanticObject); 
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
			case CalPackage.AST_SUB_PATTERN:
				if(context == grammarAccess.getAstSubPatternRule()) {
					sequence_AstSubPattern(context, (AstSubPattern) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_TAG:
				if(context == grammarAccess.getAstTagRule()) {
					sequence_AstTag(context, (AstTag) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_TAGGED_TUPLE:
				if(context == grammarAccess.getAstTaggedTupleRule()) {
					sequence_AstTaggedTuple(context, (AstTaggedTuple) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_TRANSITION:
				if(context == grammarAccess.getAstTransitionRule()) {
					sequence_AstTransition(context, (AstTransition) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_TUPLE:
				if(context == grammarAccess.getAstTupleRule()) {
					sequence_AstTuple(context, (AstTuple) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_TYPE:
				if(context == grammarAccess.getAstTypeRule()) {
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
			case CalPackage.AST_TYPE_USER:
				if(context == grammarAccess.getAstTypeDefinitionTypeParameterRule()) {
					sequence_AstTypeDefinitionTypeParameter(context, (AstTypeUser) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getAstTypeDefinitionRule()) {
					sequence_AstTypeDefinition(context, (AstTypeUser) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getAstTypeUserRule()) {
					sequence_AstTypeUser(context, (AstTypeUser) semanticObject); 
					return; 
				}
				else break;
			case CalPackage.AST_VARIABLE:
				if(context == grammarAccess.getAstConstantVariableRule()) {
					sequence_AstConstantVariable_AstVariableDeclaration(context, (AstVariable) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getAstParameterRule()) {
					sequence_AstParameter_AstVariableDeclaration(context, (AstVariable) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getAstTokenRule()) {
					sequence_AstToken(context, (AstVariable) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getAstStateVariableRule() ||
				   context == grammarAccess.getAstValuedVariableDeclarationRule()) {
					sequence_AstValuedVariableDeclaration_AstVariableDeclaration(context, (AstVariable) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getAstExternalVariableRule() ||
				   context == grammarAccess.getAstVariableDeclarationRule()) {
					sequence_AstVariableDeclaration(context, (AstVariable) semanticObject); 
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
	 */
	protected void sequence_AstAction(EObject context, AstAction semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID type=[AstEntity|QualifiedName] (parameters+=AstAssignParameter parameters+=AstAssignParameter*)?)
	 */
	protected void sequence_AstActorDeclaration(EObject context, AstActorVariable semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     variable=[AstActorVariable|ID]
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
	 *         name=ID 
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
	 */
	protected void sequence_AstActor(EObject context, AstActor semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID value=STRING)
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
	 */
	protected void sequence_AstAnnotation(EObject context, AstAnnotation semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID value=AstExpression)
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
	 */
	protected void sequence_AstConstantVariable_AstVariableDeclaration(EObject context, AstVariable semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (annotations+=AstAnnotation* actor=AstAbstractActor)
	 */
	protected void sequence_AstEntity(EObject context, AstEntity semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (pattern=AstPattern (guards+=AstExpression guards+=AstExpression*)? expression=AstExpression)
	 */
	protected void sequence_AstExpressionAlternative(EObject context, AstExpressionAlternative semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     value=BOOL
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
	 *     (variable=AstExpressionSymbolReference cases+=AstExpressionAlternative+ default=AstExpression)
	 */
	protected void sequence_AstExpressionCase(EObject context, AstExpressionCase semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     value=REAL
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
	 */
	protected void sequence_AstExpressionInteger(EObject context, AstExpressionInteger semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (expressions+=AstExpression expressions+=AstExpression* (generators+=AstGenerator generators+=AstGenerator*)?)
	 */
	protected void sequence_AstExpressionList(EObject context, AstExpressionList semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     value=STRING
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
	 *     (
	 *         symbol=[AstVariable|ID] 
	 *         indexes+=AstExpression* 
	 *         member+=AstMemberAccess* 
	 *         ctor=ID? 
	 *         (call?='(' (parameters+=AstExpression parameters+=AstExpression*)?)?
	 *     )
	 */
	protected void sequence_AstExpressionSymbolReference(EObject context, AstExpressionSymbolReference semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((unaryOperator='~' | unaryOperator='-' | unaryOperator='not' | unaryOperator='#' | unaryOperator='old') expression=AstExpressionPostfix)
	 */
	protected void sequence_AstExpressionUnary(EObject context, AstExpressionUnary semanticObject) {
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
	 */
	protected void sequence_AstExpression_AstExpressionAdditive_AstExpressionAnd_AstExpressionBitand_AstExpressionBitor_AstExpressionBitxor_AstExpressionEq_AstExpressionExp_AstExpressionMultiplicative_AstExpressionRelational_AstExpressionShift(EObject context, AstExpressionBinary semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID (parameters+=AstParameter parameters+=AstParameter*)? (inputs+=AstPort inputs+=AstPort*)? (outputs+=AstPort outputs+=AstPort*)?)
	 */
	protected void sequence_AstExternalActor(EObject context, AstExternalActor semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (annotations+=AstAnnotation* name=ID (parameters+=AstVariableDeclaration parameters+=AstVariableDeclaration*)? type=AstType)
	 */
	protected void sequence_AstExternalFunction(EObject context, AstFunction semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (annotations+=AstAnnotation* name=ID (parameters+=AstVariableDeclaration parameters+=AstVariableDeclaration*)?)
	 */
	protected void sequence_AstExternalProcedure(EObject context, AstProcedure semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (variable=AstVariableDeclaration expression=AstExpression)
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
	 */
	protected void sequence_AstFunction(EObject context, AstFunction semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (variable=AstVariableDeclaration expression=AstExpression)
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
	 */
	protected void sequence_AstInitialize(EObject context, AstInitialize semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (port=[AstPort|ID]? tokens+=AstToken tokens+=AstToken* repeat=AstExpression?)
	 */
	protected void sequence_AstInputPattern(EObject context, AstInputPattern semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID memberIndex+=AstExpression*)
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
	 */
	protected void sequence_AstNamespace(EObject context, AstNamespace semanticObject) {
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
	 */
	protected void sequence_AstNamespace_AstPackage_AstTop(EObject context, AstNamespace semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         name=ID 
	 *         (parameters+=AstParameter parameters+=AstParameter*)? 
	 *         (inputs+=AstPort inputs+=AstPort*)? 
	 *         (outputs+=AstPort outputs+=AstPort*)? 
	 *         (variables+=AstValuedVariableDeclaration variables+=AstValuedVariableDeclaration*)? 
	 *         instances+=AstActorDeclaration+ 
	 *         structure=AstStructure
	 *     )
	 */
	protected void sequence_AstNetwork(EObject context, AstNetwork semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (port=[AstPort|ID]? values+=AstExpression values+=AstExpression* repeat=AstExpression?)
	 */
	protected void sequence_AstOutputPattern(EObject context, AstOutputPattern semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=QualifiedName (entities+=AstEntity | imports+=Import | units+=AstUnit)*)
	 */
	protected void sequence_AstPackage(EObject context, AstNamespace semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (annotations+=AstAnnotation* type=AstType name=ID dimensions+=AstExpression* value=AstExpression?)
	 */
	protected void sequence_AstParameter_AstVariableDeclaration(EObject context, AstVariable semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (
	 *             left=AstPatternExpressionMultiplicative_AstPatternExpressionBinary_1_0 
	 *             (operator='*' | operator='/' | operator='div' | operator='mod') 
	 *             right=AstPatternExpressionExp
	 *         ) | 
	 *         (left=AstPatternExpressionExp_AstPatternExpressionBinary_1_0 operator='**' right=AstPatternExpressionUnary) | 
	 *         (left=AstPatternExpressionAdditive_AstPatternExpressionBinary_1_0 (operator='+' | operator='-') right=AstPatternExpressionMultiplicative) | 
	 *         (
	 *             left=AstPatternExpressionShift_AstPatternExpressionBinary_1_0 
	 *             (operator='<<' | operator='>>' | operator='>>>') 
	 *             right=AstPatternExpressionAdditive
	 *         ) | 
	 *         (
	 *             left=AstPatternExpressionRelational_AstPatternExpressionBinary_1_0 
	 *             (operator='<' | operator='<=' | operator='>' | operator='>=') 
	 *             right=AstPatternExpressionShift
	 *         ) | 
	 *         (left=AstPatternExpressionEq_AstPatternExpressionBinary_1_0 (operator='=' | operator='!=') right=AstPatternExpressionRelational) | 
	 *         (left=AstPatternExpressionBitand_AstPatternExpressionBinary_1_0 operator='&' right=AstPatternExpressionEq) | 
	 *         (left=AstPatternExpressionBitxor_AstPatternExpressionBinary_1_0 operator='^' right=AstPatternExpressionBitand) | 
	 *         (left=AstPatternExpressionBitor_AstPatternExpressionBinary_1_0 operator='|' right=AstPatternExpressionBitxor) | 
	 *         (left=AstPatternExpressionAnd_AstPatternExpressionBinary_1_0 (operator='&&' | operator='and') right=AstPatternExpressionBitor)
	 *     )
	 */
	protected void sequence_AstPatternExpressionAdditive_AstPatternExpressionAnd_AstPatternExpressionBitand_AstPatternExpressionBitor_AstPatternExpressionBitxor_AstPatternExpressionEq_AstPatternExpressionExp_AstPatternExpressionMultiplicative_AstPatternExpressionRelational_AstPatternExpressionShift(EObject context, AstPatternExpressionBinary semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (
	 *             left=AstPatternExpressionMultiplicative_AstPatternExpressionBinary_1_0 
	 *             (operator='*' | operator='/' | operator='div' | operator='mod') 
	 *             right=AstPatternExpressionExp
	 *         ) | 
	 *         (left=AstPatternExpressionExp_AstPatternExpressionBinary_1_0 operator='**' right=AstPatternExpressionUnary) | 
	 *         (left=AstPatternExpressionAdditive_AstPatternExpressionBinary_1_0 (operator='+' | operator='-') right=AstPatternExpressionMultiplicative) | 
	 *         (
	 *             left=AstPatternExpressionShift_AstPatternExpressionBinary_1_0 
	 *             (operator='<<' | operator='>>' | operator='>>>') 
	 *             right=AstPatternExpressionAdditive
	 *         ) | 
	 *         (
	 *             left=AstPatternExpressionRelational_AstPatternExpressionBinary_1_0 
	 *             (operator='<' | operator='<=' | operator='>' | operator='>=') 
	 *             right=AstPatternExpressionShift
	 *         ) | 
	 *         (left=AstPatternExpressionEq_AstPatternExpressionBinary_1_0 (operator='=' | operator='!=') right=AstPatternExpressionRelational) | 
	 *         (left=AstPatternExpressionBitand_AstPatternExpressionBinary_1_0 operator='&' right=AstPatternExpressionEq) | 
	 *         (left=AstPatternExpressionBitxor_AstPatternExpressionBinary_1_0 operator='^' right=AstPatternExpressionBitand) | 
	 *         (left=AstPatternExpressionBitor_AstPatternExpressionBinary_1_0 operator='|' right=AstPatternExpressionBitxor)
	 *     )
	 */
	protected void sequence_AstPatternExpressionAdditive_AstPatternExpressionBitand_AstPatternExpressionBitor_AstPatternExpressionBitxor_AstPatternExpressionEq_AstPatternExpressionExp_AstPatternExpressionMultiplicative_AstPatternExpressionRelational_AstPatternExpressionShift(EObject context, AstPatternExpressionBinary semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (
	 *             left=AstPatternExpressionMultiplicative_AstPatternExpressionBinary_1_0 
	 *             (operator='*' | operator='/' | operator='div' | operator='mod') 
	 *             right=AstPatternExpressionExp
	 *         ) | 
	 *         (left=AstPatternExpressionExp_AstPatternExpressionBinary_1_0 operator='**' right=AstPatternExpressionUnary) | 
	 *         (left=AstPatternExpressionAdditive_AstPatternExpressionBinary_1_0 (operator='+' | operator='-') right=AstPatternExpressionMultiplicative) | 
	 *         (
	 *             left=AstPatternExpressionShift_AstPatternExpressionBinary_1_0 
	 *             (operator='<<' | operator='>>' | operator='>>>') 
	 *             right=AstPatternExpressionAdditive
	 *         ) | 
	 *         (
	 *             left=AstPatternExpressionRelational_AstPatternExpressionBinary_1_0 
	 *             (operator='<' | operator='<=' | operator='>' | operator='>=') 
	 *             right=AstPatternExpressionShift
	 *         ) | 
	 *         (left=AstPatternExpressionEq_AstPatternExpressionBinary_1_0 (operator='=' | operator='!=') right=AstPatternExpressionRelational) | 
	 *         (left=AstPatternExpressionBitand_AstPatternExpressionBinary_1_0 operator='&' right=AstPatternExpressionEq) | 
	 *         (left=AstPatternExpressionBitxor_AstPatternExpressionBinary_1_0 operator='^' right=AstPatternExpressionBitand)
	 *     )
	 */
	protected void sequence_AstPatternExpressionAdditive_AstPatternExpressionBitand_AstPatternExpressionBitxor_AstPatternExpressionEq_AstPatternExpressionExp_AstPatternExpressionMultiplicative_AstPatternExpressionRelational_AstPatternExpressionShift(EObject context, AstPatternExpressionBinary semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (
	 *             left=AstPatternExpressionMultiplicative_AstPatternExpressionBinary_1_0 
	 *             (operator='*' | operator='/' | operator='div' | operator='mod') 
	 *             right=AstPatternExpressionExp
	 *         ) | 
	 *         (left=AstPatternExpressionExp_AstPatternExpressionBinary_1_0 operator='**' right=AstPatternExpressionUnary) | 
	 *         (left=AstPatternExpressionAdditive_AstPatternExpressionBinary_1_0 (operator='+' | operator='-') right=AstPatternExpressionMultiplicative) | 
	 *         (
	 *             left=AstPatternExpressionShift_AstPatternExpressionBinary_1_0 
	 *             (operator='<<' | operator='>>' | operator='>>>') 
	 *             right=AstPatternExpressionAdditive
	 *         ) | 
	 *         (
	 *             left=AstPatternExpressionRelational_AstPatternExpressionBinary_1_0 
	 *             (operator='<' | operator='<=' | operator='>' | operator='>=') 
	 *             right=AstPatternExpressionShift
	 *         ) | 
	 *         (left=AstPatternExpressionEq_AstPatternExpressionBinary_1_0 (operator='=' | operator='!=') right=AstPatternExpressionRelational) | 
	 *         (left=AstPatternExpressionBitand_AstPatternExpressionBinary_1_0 operator='&' right=AstPatternExpressionEq)
	 *     )
	 */
	protected void sequence_AstPatternExpressionAdditive_AstPatternExpressionBitand_AstPatternExpressionEq_AstPatternExpressionExp_AstPatternExpressionMultiplicative_AstPatternExpressionRelational_AstPatternExpressionShift(EObject context, AstPatternExpressionBinary semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (
	 *             left=AstPatternExpressionMultiplicative_AstPatternExpressionBinary_1_0 
	 *             (operator='*' | operator='/' | operator='div' | operator='mod') 
	 *             right=AstPatternExpressionExp
	 *         ) | 
	 *         (left=AstPatternExpressionExp_AstPatternExpressionBinary_1_0 operator='**' right=AstPatternExpressionUnary) | 
	 *         (left=AstPatternExpressionAdditive_AstPatternExpressionBinary_1_0 (operator='+' | operator='-') right=AstPatternExpressionMultiplicative) | 
	 *         (
	 *             left=AstPatternExpressionShift_AstPatternExpressionBinary_1_0 
	 *             (operator='<<' | operator='>>' | operator='>>>') 
	 *             right=AstPatternExpressionAdditive
	 *         ) | 
	 *         (
	 *             left=AstPatternExpressionRelational_AstPatternExpressionBinary_1_0 
	 *             (operator='<' | operator='<=' | operator='>' | operator='>=') 
	 *             right=AstPatternExpressionShift
	 *         ) | 
	 *         (left=AstPatternExpressionEq_AstPatternExpressionBinary_1_0 (operator='=' | operator='!=') right=AstPatternExpressionRelational)
	 *     )
	 */
	protected void sequence_AstPatternExpressionAdditive_AstPatternExpressionEq_AstPatternExpressionExp_AstPatternExpressionMultiplicative_AstPatternExpressionRelational_AstPatternExpressionShift(EObject context, AstPatternExpressionBinary semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (
	 *             left=AstPatternExpressionMultiplicative_AstPatternExpressionBinary_1_0 
	 *             (operator='*' | operator='/' | operator='div' | operator='mod') 
	 *             right=AstPatternExpressionExp
	 *         ) | 
	 *         (left=AstPatternExpressionExp_AstPatternExpressionBinary_1_0 operator='**' right=AstPatternExpressionUnary) | 
	 *         (left=AstPatternExpressionAdditive_AstPatternExpressionBinary_1_0 (operator='+' | operator='-') right=AstPatternExpressionMultiplicative)
	 *     )
	 */
	protected void sequence_AstPatternExpressionAdditive_AstPatternExpressionExp_AstPatternExpressionMultiplicative(EObject context, AstPatternExpressionBinary semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (
	 *             left=AstPatternExpressionMultiplicative_AstPatternExpressionBinary_1_0 
	 *             (operator='*' | operator='/' | operator='div' | operator='mod') 
	 *             right=AstPatternExpressionExp
	 *         ) | 
	 *         (left=AstPatternExpressionExp_AstPatternExpressionBinary_1_0 operator='**' right=AstPatternExpressionUnary) | 
	 *         (left=AstPatternExpressionAdditive_AstPatternExpressionBinary_1_0 (operator='+' | operator='-') right=AstPatternExpressionMultiplicative) | 
	 *         (
	 *             left=AstPatternExpressionShift_AstPatternExpressionBinary_1_0 
	 *             (operator='<<' | operator='>>' | operator='>>>') 
	 *             right=AstPatternExpressionAdditive
	 *         ) | 
	 *         (
	 *             left=AstPatternExpressionRelational_AstPatternExpressionBinary_1_0 
	 *             (operator='<' | operator='<=' | operator='>' | operator='>=') 
	 *             right=AstPatternExpressionShift
	 *         )
	 *     )
	 */
	protected void sequence_AstPatternExpressionAdditive_AstPatternExpressionExp_AstPatternExpressionMultiplicative_AstPatternExpressionRelational_AstPatternExpressionShift(EObject context, AstPatternExpressionBinary semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (
	 *             left=AstPatternExpressionMultiplicative_AstPatternExpressionBinary_1_0 
	 *             (operator='*' | operator='/' | operator='div' | operator='mod') 
	 *             right=AstPatternExpressionExp
	 *         ) | 
	 *         (left=AstPatternExpressionExp_AstPatternExpressionBinary_1_0 operator='**' right=AstPatternExpressionUnary) | 
	 *         (left=AstPatternExpressionAdditive_AstPatternExpressionBinary_1_0 (operator='+' | operator='-') right=AstPatternExpressionMultiplicative) | 
	 *         (
	 *             left=AstPatternExpressionShift_AstPatternExpressionBinary_1_0 
	 *             (operator='<<' | operator='>>' | operator='>>>') 
	 *             right=AstPatternExpressionAdditive
	 *         )
	 *     )
	 */
	protected void sequence_AstPatternExpressionAdditive_AstPatternExpressionExp_AstPatternExpressionMultiplicative_AstPatternExpressionShift(EObject context, AstPatternExpressionBinary semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (left=AstPatternExpressionExp_AstPatternExpressionBinary_1_0 operator='**' right=AstPatternExpressionUnary)
	 */
	protected void sequence_AstPatternExpressionExp(EObject context, AstPatternExpressionBinary semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, CalPackage.Literals.AST_PATTERN_EXPRESSION_BINARY__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalPackage.Literals.AST_PATTERN_EXPRESSION_BINARY__LEFT));
			if(transientValues.isValueTransient(semanticObject, CalPackage.Literals.AST_PATTERN_EXPRESSION_BINARY__OPERATOR) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalPackage.Literals.AST_PATTERN_EXPRESSION_BINARY__OPERATOR));
			if(transientValues.isValueTransient(semanticObject, CalPackage.Literals.AST_PATTERN_EXPRESSION_BINARY__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalPackage.Literals.AST_PATTERN_EXPRESSION_BINARY__RIGHT));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getAstPatternExpressionExpAccess().getAstPatternExpressionBinaryLeftAction_1_0(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getAstPatternExpressionExpAccess().getOperatorAsteriskAsteriskKeyword_1_1_0(), semanticObject.getOperator());
		feeder.accept(grammarAccess.getAstPatternExpressionExpAccess().getRightAstPatternExpressionUnaryParserRuleCall_1_2_0(), semanticObject.getRight());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (
	 *             left=AstPatternExpressionMultiplicative_AstPatternExpressionBinary_1_0 
	 *             (operator='*' | operator='/' | operator='div' | operator='mod') 
	 *             right=AstPatternExpressionExp
	 *         ) | 
	 *         (left=AstPatternExpressionExp_AstPatternExpressionBinary_1_0 operator='**' right=AstPatternExpressionUnary)
	 *     )
	 */
	protected void sequence_AstPatternExpressionExp_AstPatternExpressionMultiplicative(EObject context, AstPatternExpressionBinary semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (condition=AstExpression then=AstExpression else=AstExpression)
	 */
	protected void sequence_AstPatternExpressionIf(EObject context, AstPatternExpressionIf semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, CalPackage.Literals.AST_PATTERN_EXPRESSION_IF__CONDITION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalPackage.Literals.AST_PATTERN_EXPRESSION_IF__CONDITION));
			if(transientValues.isValueTransient(semanticObject, CalPackage.Literals.AST_PATTERN_EXPRESSION_IF__THEN) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalPackage.Literals.AST_PATTERN_EXPRESSION_IF__THEN));
			if(transientValues.isValueTransient(semanticObject, CalPackage.Literals.AST_PATTERN_EXPRESSION_IF__ELSE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CalPackage.Literals.AST_PATTERN_EXPRESSION_IF__ELSE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getAstPatternExpressionIfAccess().getConditionAstExpressionParserRuleCall_1_0(), semanticObject.getCondition());
		feeder.accept(grammarAccess.getAstPatternExpressionIfAccess().getThenAstExpressionParserRuleCall_3_0(), semanticObject.getThen());
		feeder.accept(grammarAccess.getAstPatternExpressionIfAccess().getElseAstExpressionParserRuleCall_5_0(), semanticObject.getElse());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         symbol=[AstVariable|ID] 
	 *         indexes+=AstExpression* 
	 *         member+=AstMemberAccess* 
	 *         ctor=ID? 
	 *         (call?='(' (parameters+=AstExpression parameters+=AstExpression*)?)?
	 *     )
	 */
	protected void sequence_AstPatternExpressionSymbolReference(EObject context, AstPatternExpressionSymbolReference semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (unaryOperator='~' | unaryOperator='-' | unaryOperator='not' | unaryOperator='#' | unaryOperator='old') 
	 *         expression=AstPatternExpressionPostfix
	 *     )
	 */
	protected void sequence_AstPatternExpressionUnary(EObject context, AstPatternExpressionUnary semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (
	 *             left=AstPatternExpressionMultiplicative_AstPatternExpressionBinary_1_0 
	 *             (operator='*' | operator='/' | operator='div' | operator='mod') 
	 *             right=AstPatternExpressionExp
	 *         ) | 
	 *         (left=AstPatternExpressionExp_AstPatternExpressionBinary_1_0 operator='**' right=AstPatternExpressionUnary) | 
	 *         (left=AstPatternExpressionAdditive_AstPatternExpressionBinary_1_0 (operator='+' | operator='-') right=AstPatternExpressionMultiplicative) | 
	 *         (
	 *             left=AstPatternExpressionShift_AstPatternExpressionBinary_1_0 
	 *             (operator='<<' | operator='>>' | operator='>>>') 
	 *             right=AstPatternExpressionAdditive
	 *         ) | 
	 *         (
	 *             left=AstPatternExpressionRelational_AstPatternExpressionBinary_1_0 
	 *             (operator='<' | operator='<=' | operator='>' | operator='>=') 
	 *             right=AstPatternExpressionShift
	 *         ) | 
	 *         (left=AstPatternExpressionEq_AstPatternExpressionBinary_1_0 (operator='=' | operator='!=') right=AstPatternExpressionRelational) | 
	 *         (left=AstPatternExpressionBitand_AstPatternExpressionBinary_1_0 operator='&' right=AstPatternExpressionEq) | 
	 *         (left=AstPatternExpressionBitxor_AstPatternExpressionBinary_1_0 operator='^' right=AstPatternExpressionBitand) | 
	 *         (left=AstPatternExpressionBitor_AstPatternExpressionBinary_1_0 operator='|' right=AstPatternExpressionBitxor) | 
	 *         (left=AstPatternExpressionAnd_AstPatternExpressionBinary_1_0 (operator='&&' | operator='and') right=AstPatternExpressionBitor) | 
	 *         (left=AstPatternExpression_AstPatternExpressionBinary_1_0 (operator='||' | operator='or' | operator='..') right=AstExpressionAnd)
	 *     )
	 */
	protected void sequence_AstPatternExpression_AstPatternExpressionAdditive_AstPatternExpressionAnd_AstPatternExpressionBitand_AstPatternExpressionBitor_AstPatternExpressionBitxor_AstPatternExpressionEq_AstPatternExpressionExp_AstPatternExpressionMultiplicative_AstPatternExpressionRelational_AstPatternExpressionShift(EObject context, AstPatternExpressionBinary semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (tag=ID subpatterns+=AstSubPattern subpatterns+=AstSubPattern?)
	 */
	protected void sequence_AstPattern(EObject context, AstPattern semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (annotations+=AstAnnotation* type=AstType name=ID)
	 */
	protected void sequence_AstPort(EObject context, AstPort semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (inequalities+=AstInequality*)
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
	 */
	protected void sequence_AstProcedure(EObject context, AstProcedure semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (initialState=[AstState|ID] transitions+=AstTransition*)
	 */
	protected void sequence_AstSchedule(EObject context, AstSchedule semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     name=ID
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
	 *     (pattern=AstPattern (guards+=AstExpression guards+=AstExpression*)? statements+=AstStatement*)
	 */
	protected void sequence_AstStatementAlternative(EObject context, AstStatementAlternative semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (target=[AstVariable|ID] indexes+=AstExpression* member+=AstMemberAccess* value=AstExpression)
	 */
	protected void sequence_AstStatementAssign(EObject context, AstStatementAssign semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((variables+=AstValuedVariableDeclaration variables+=AstValuedVariableDeclaration*)? statements+=AstStatement*)
	 */
	protected void sequence_AstStatementBlock(EObject context, AstStatementBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (procedure=[AstProcedure|ID] (parameters+=AstExpression parameters+=AstExpression*)?)
	 */
	protected void sequence_AstStatementCall(EObject context, AstStatementCall semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (expression=AstExpressionSymbolReference cases+=AstStatementAlternative+)
	 */
	protected void sequence_AstStatementCase(EObject context, AstStatementCase semanticObject) {
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
	 */
	protected void sequence_AstStatementForeach(EObject context, AstStatementForeach semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (condition=AstExpression then+=AstStatement* else+=AstStatement*)
	 */
	protected void sequence_AstStatementIf(EObject context, AstStatementIf semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (condition=AstExpression statements+=AstStatement*)
	 */
	protected void sequence_AstStatementWhile(EObject context, AstStatementWhile semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     connections+=AstConnection+
	 */
	protected void sequence_AstStructure(EObject context, AstStructure semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (label=ID? (dontcare?='_' | condition=AstPatternExpression | variable=AstToken | pattern=AstPattern))
	 */
	protected void sequence_AstSubPattern(EObject context, AstSubPattern semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifiers+=ID identifiers+=ID*)
	 */
	protected void sequence_AstTag(EObject context, AstTag semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID (fields+=AstValuedVariableDeclaration fields+=AstValuedVariableDeclaration*)?)
	 */
	protected void sequence_AstTaggedTuple(EObject context, AstTaggedTuple semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     name=ID
	 */
	protected void sequence_AstToken(EObject context, AstVariable semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (source=[AstState|ID] tags+=AstTag tags+=AstTag* target=[AstState|ID])
	 */
	protected void sequence_AstTransition(EObject context, AstTransition semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((fields+=AstValuedVariableDeclaration fields+=AstValuedVariableDeclaration*)?)
	 */
	protected void sequence_AstTuple(EObject context, AstTuple semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (value=AstVariableDeclaration | type=AstTypeDefinitionTypeParameter)
	 */
	protected void sequence_AstTypeDefinitionParameter(EObject context, AstTypeDefinitionParameter semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (variable?='type' name=ID)
	 */
	protected void sequence_AstTypeDefinitionTypeParameter(EObject context, AstTypeUser semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         definition?='type' 
	 *         name=ID 
	 *         (parameters+=AstTypeDefinitionParameter parameters+=AstTypeDefinitionParameter*)? 
	 *         (tuples+=AstTuple | (tuples+=AstTaggedTuple tuples+=AstTaggedTuple*))
	 *     )
	 */
	protected void sequence_AstTypeDefinition(EObject context, AstTypeUser semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((name=ID value=AstExpression) | ((name=ID | name='type') type=AstType))
	 */
	protected void sequence_AstTypeParam(EObject context, AstTypeParam semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (params+=AstTypeParam params+=AstTypeParam*)
	 */
	protected void sequence_AstTypeParameterList(EObject context, AstTypeParameterList semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     {AstTypeUser}
	 */
	protected void sequence_AstTypeUser(EObject context, AstTypeUser semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (builtin=AstTypeBuiltIn typeParams=AstTypeParameterList? dimensions+=AstExpression*) | 
	 *         (name=[AstTypeUser|ID] typeParams=AstTypeParameterList? dimensions+=AstExpression*) | 
	 *         ((domain+=AstType domain+=AstType*)? (codomain+=AstType domain+=AstType*)?)
	 *     )
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
	 */
	protected void sequence_AstUnit(EObject context, AstNamespace semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (annotations+=AstAnnotation* type=AstType name=ID dimensions+=AstExpression* (constant?='='? value=AstExpression)?)
	 */
	protected void sequence_AstValuedVariableDeclaration_AstVariableDeclaration(EObject context, AstVariable semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (annotations+=AstAnnotation* type=AstType name=ID dimensions+=AstExpression*)
	 */
	protected void sequence_AstVariableDeclaration(EObject context, AstVariable semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     importedNamespace=QualifiedNameWithWildCard
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
