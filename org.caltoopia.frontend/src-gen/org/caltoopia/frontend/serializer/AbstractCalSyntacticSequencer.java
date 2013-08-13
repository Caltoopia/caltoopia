package org.caltoopia.frontend.serializer;

import com.google.inject.Inject;
import java.util.List;
import org.caltoopia.frontend.services.CalGrammarAccess;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AbstractElementAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.GroupAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.TokenAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynNavigable;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynTransition;
import org.eclipse.xtext.serializer.sequencer.AbstractSyntacticSequencer;

@SuppressWarnings("all")
public abstract class AbstractCalSyntacticSequencer extends AbstractSyntacticSequencer {

	protected CalGrammarAccess grammarAccess;
	protected AbstractElementAlias match_AstAction_DoKeyword_9_0_q;
	protected AbstractElementAlias match_AstConnection___LeftCurlyBracketKeyword_5_0_RightCurlyBracketKeyword_5_2__q;
	protected AbstractElementAlias match_AstExpressionPostfix_LeftParenthesisKeyword_4_0_a;
	protected AbstractElementAlias match_AstExpressionPostfix_LeftParenthesisKeyword_4_0_p;
	protected AbstractElementAlias match_AstInitialize_DoKeyword_8_0_q;
	protected AbstractElementAlias match_AstStatementIf_ElseKeyword_4_0_q;
	protected AbstractElementAlias match_AstTaggedTuple___LeftParenthesisKeyword_1_0_RightParenthesisKeyword_1_2__q;
	protected AbstractElementAlias match_AstTypeDefinition___LeftParenthesisKeyword_2_0_RightParenthesisKeyword_2_2__q;
	
	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (CalGrammarAccess) access;
		match_AstAction_DoKeyword_9_0_q = new TokenAlias(false, true, grammarAccess.getAstActionAccess().getDoKeyword_9_0());
		match_AstConnection___LeftCurlyBracketKeyword_5_0_RightCurlyBracketKeyword_5_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getAstConnectionAccess().getLeftCurlyBracketKeyword_5_0()), new TokenAlias(false, false, grammarAccess.getAstConnectionAccess().getRightCurlyBracketKeyword_5_2()));
		match_AstExpressionPostfix_LeftParenthesisKeyword_4_0_a = new TokenAlias(true, true, grammarAccess.getAstExpressionPostfixAccess().getLeftParenthesisKeyword_4_0());
		match_AstExpressionPostfix_LeftParenthesisKeyword_4_0_p = new TokenAlias(true, false, grammarAccess.getAstExpressionPostfixAccess().getLeftParenthesisKeyword_4_0());
		match_AstInitialize_DoKeyword_8_0_q = new TokenAlias(false, true, grammarAccess.getAstInitializeAccess().getDoKeyword_8_0());
		match_AstStatementIf_ElseKeyword_4_0_q = new TokenAlias(false, true, grammarAccess.getAstStatementIfAccess().getElseKeyword_4_0());
		match_AstTaggedTuple___LeftParenthesisKeyword_1_0_RightParenthesisKeyword_1_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getAstTaggedTupleAccess().getLeftParenthesisKeyword_1_0()), new TokenAlias(false, false, grammarAccess.getAstTaggedTupleAccess().getRightParenthesisKeyword_1_2()));
		match_AstTypeDefinition___LeftParenthesisKeyword_2_0_RightParenthesisKeyword_2_2__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getAstTypeDefinitionAccess().getLeftParenthesisKeyword_2_0()), new TokenAlias(false, false, grammarAccess.getAstTypeDefinitionAccess().getRightParenthesisKeyword_2_2()));
	}
	
	@Override
	protected String getUnassignedRuleCallToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if(ruleCall.getRule() == grammarAccess.getIDRule())
			return getIDToken(semanticObject, ruleCall, node);
		return "";
	}
	
	/**
	 * terminal ID returns ecore::EString : ('a'..'z'|'A'..'Z'|'_'|'$')('a'..'z'|'A'..'Z'|'0'..'9'|'_'|'$')*;
	 */
	protected String getIDToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "";
	}
	
	@Override
	protected void emitUnassignedTokens(EObject semanticObject, ISynTransition transition, INode fromNode, INode toNode) {
		if (transition.getAmbiguousSyntaxes().isEmpty()) return;
		List<INode> transitionNodes = collectNodes(fromNode, toNode);
		for (AbstractElementAlias syntax : transition.getAmbiguousSyntaxes()) {
			List<INode> syntaxNodes = getNodesFor(transitionNodes, syntax);
			if(match_AstAction_DoKeyword_9_0_q.equals(syntax))
				emit_AstAction_DoKeyword_9_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_AstConnection___LeftCurlyBracketKeyword_5_0_RightCurlyBracketKeyword_5_2__q.equals(syntax))
				emit_AstConnection___LeftCurlyBracketKeyword_5_0_RightCurlyBracketKeyword_5_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_AstExpressionPostfix_LeftParenthesisKeyword_4_0_a.equals(syntax))
				emit_AstExpressionPostfix_LeftParenthesisKeyword_4_0_a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_AstExpressionPostfix_LeftParenthesisKeyword_4_0_p.equals(syntax))
				emit_AstExpressionPostfix_LeftParenthesisKeyword_4_0_p(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_AstInitialize_DoKeyword_8_0_q.equals(syntax))
				emit_AstInitialize_DoKeyword_8_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_AstStatementIf_ElseKeyword_4_0_q.equals(syntax))
				emit_AstStatementIf_ElseKeyword_4_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_AstTaggedTuple___LeftParenthesisKeyword_1_0_RightParenthesisKeyword_1_2__q.equals(syntax))
				emit_AstTaggedTuple___LeftParenthesisKeyword_1_0_RightParenthesisKeyword_1_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_AstTypeDefinition___LeftParenthesisKeyword_2_0_RightParenthesisKeyword_2_2__q.equals(syntax))
				emit_AstTypeDefinition___LeftParenthesisKeyword_2_0_RightParenthesisKeyword_2_2__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else acceptNodes(getLastNavigableState(), syntaxNodes);
		}
	}

	/**
	 * Syntax:
	 *     'do'?
	 */
	protected void emit_AstAction_DoKeyword_9_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('{' '}')?
	 */
	protected void emit_AstConnection___LeftCurlyBracketKeyword_5_0_RightCurlyBracketKeyword_5_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     '('*
	 */
	protected void emit_AstExpressionPostfix_LeftParenthesisKeyword_4_0_a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     '('+
	 */
	protected void emit_AstExpressionPostfix_LeftParenthesisKeyword_4_0_p(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     'do'?
	 */
	protected void emit_AstInitialize_DoKeyword_8_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     'else'?
	 */
	protected void emit_AstStatementIf_ElseKeyword_4_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('(' ')')?
	 */
	protected void emit_AstTaggedTuple___LeftParenthesisKeyword_1_0_RightParenthesisKeyword_1_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     ('(' ')')?
	 */
	protected void emit_AstTypeDefinition___LeftParenthesisKeyword_2_0_RightParenthesisKeyword_2_2__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
}
