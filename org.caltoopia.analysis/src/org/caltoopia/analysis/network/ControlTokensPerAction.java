package org.caltoopia.analysis.network;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.caltoopia.analysis.air.Action;
import org.caltoopia.analysis.air.ActorInstance;
import org.caltoopia.analysis.air.Transition;
import org.caltoopia.analysis.network.ControlTokensPerAction;
import org.caltoopia.analysis.util.collections.CartesianProduct;
import org.caltoopia.analysis.util.collections.Pair;
import org.caltoopia.analysis.util.collections.UnionOfDisjointIntervals;

/**
 * represents the control tokens produced by an action of a detector actor.
 * A control token is identified by a name given by annotation in the CAL action
 */
public class ControlTokensPerAction{
	//the detector actor
	private ActorInstance detectorActor = null;
	
	//the action
	private Action detectorAction;
	
	//the list of control tokens and their corresponding values
	private Map<String, Long> controlTokens= new HashMap<String, Long>();
				
	public ControlTokensPerAction(ActorInstance actor, Action action){
		detectorActor = actor;
		detectorAction = action;
	};
	
	public void setDetectorActor(ActorInstance a){
		detectorActor = a;
	}
	
	public void setDetectorAction(Action a){
		detectorAction = a;
	}
	
	public void setControlTokens(Map<String, Long> c){
		controlTokens = c;
	}
	
	public void addControlToken(String key, Long value){
		controlTokens.put(key, value);
	}
	
	public ActorInstance getDetectorActor(){
		return detectorActor;
	}
	
	public Action getDetectorAction(){
		return detectorAction;
	}
	
	public Map<String, Long> getControlTokens(){
		return controlTokens;
	}
	
	public String toString(){
		String cta = "";
		for(Map.Entry<String, Long> e: controlTokens.entrySet()){
			cta += e.getKey();
			cta += "_";
			cta += e.getValue();
			cta += "_";
		}
		return cta;
	}
	
	/**
	 * tests if a given control token is the same as 
	 * this control token. 
	 * @param c
	 * @return
	 */
	public boolean equals(ControlTokensPerAction c){
		if(this.detectorActor!=c.detectorActor)
			return false;
		
		if(this.detectorAction!=c.detectorAction)
			return false;
		
		if(this.controlTokens.size()!= c.controlTokens.size())
			return false;
		
		for(Map.Entry<String, Long> e: controlTokens.entrySet()){
			if(c.getControlTokens().get(e.getKey())!=e.getValue())
				return false;
		}
		return true;
	}
	
	public void print(PrintStream out){
		out.println("\t"+detectorActor.getInstanceName()+"\t"+detectorAction.getName());
		for(Map.Entry<String, Long> e: controlTokens.entrySet()){
			out.println("\t\t"+e.getKey()+"\t"+e.getValue().longValue());
		}
	}
	
	/**
	 * Each detector action produces a set of control tokens. This information
	 * is obtained from the action annotation. 
	 * @param detector
	 * @param action
	 * @return a set of control tokens produced by the detector action. It returns
	 * null if the detector action is not annotated.
	 */
	public static Set<ControlTokensPerAction> getControlTokensOfAction(ActorInstance detector, Action action){
		String msg = "";
		try{
			//if it has ControlToken annotated types, parse
			if(action.hasAnnotation("ActionProperty")){
				if (action.getAnnotationArgumentValue("ActionProperty", "ScenarioTokens")!=null){
					Set<ControlTokensPerAction> acToknes = new HashSet<ControlTokensPerAction>();
					String s = action.getAnnotationArgumentValue("ActionProperty", "ScenarioTokens");
					List<Set<Pair<String, Long>>> tokenRanges = 
							new ArrayList<Set<Pair<String, Long>>>();
					for(String t: s.split(";")){
						String st[] = t.split(":");
						UnionOfDisjointIntervals intervals = new UnionOfDisjointIntervals();
						for(String interval: st[1].split("/")){
							String str[] = interval.split("-");
							
							if(str.length==1 && Long.parseLong(str[0].trim()) >= 0){
								intervals.add(Long.parseLong(str[0].trim()), Long.parseLong(str[0].trim()));
							}
							else if(str.length==2 && Long.parseLong(str[0].trim()) >= 0 
									&& Long.parseLong(str[1].trim()) >= 0){
								intervals.add(Long.parseLong(str[0].trim()), Long.parseLong(str[1].trim()));
							}
							else{
								msg = "Scenario tag error. ";
								msg += "Syntax error for scenario interval '";
								msg += interval + "'.";
								throw new NullPointerException( msg );
							}
						}							
						Set<Pair<String, Long>> tokenRange = new 
								HashSet<Pair<String, Long>>();
						for(Long scenario: intervals.asSet()){
							tokenRange.add(new Pair<String, Long>(st[0].trim(), scenario));
						}
						tokenRanges.add(tokenRange);
					}
					
				
					for(Set<Pair<String, Long>> str: CartesianProduct.cartesianProduct(tokenRanges)){
						ControlTokensPerAction sta = new ControlTokensPerAction(detector, action);
						for(Pair<String, Long> e: str){
							if(sta.getControlTokens().containsKey(e.getFirst())){
								msg = "getScenarioOfDetectorAction: ";
								msg += "duplicate entry for a scenario token detected.";
								throw new Exception( msg );
							}
							sta.getControlTokens().put(e.getFirst(), e.getSecond());
						}
						acToknes.add(sta);
					}					
					return acToknes;
				}
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		return null;
	}
	
	/**
	 * computes the set all possible tuples of ControlTokenPerAction that are possible at a 
	 * given transitionTuple. A tuple of ControlTokenPerAction has exactly one control token 
	 * per detector actor. 
	 * @param transitionTuple
	 * @return the set of all possible control token combinations 
	 */
	public static Set<Set<ControlTokensPerAction>> getControlTokensPerActions(
			Set<Pair<ActorInstance, Transition>> transitionTuple){
		List<Set<ControlTokensPerAction>> sTokens = new ArrayList<Set<ControlTokensPerAction>>();
		for(Pair<ActorInstance, Transition> tPair: transitionTuple){
			ActorInstance detector = tPair.getFirst();
			Action detectorAction = tPair.getSecond().getAction();
			Set<ControlTokensPerAction> sta = null;
			sta = getControlTokensOfAction(detector, detectorAction);
			if(sta!=null)
				sTokens.add(sta);
		}	
		
		return  CartesianProduct.cartesianProduct(sTokens);
	}
	
	/**
	 * removes control tokens produced by actors that do not
	 * exist in a given scenario graph.
	 * @param acTokens
	 * @param sg
	 * @return a set of control tokens 
	 */
	public static Set<ControlTokensPerAction> filterControlTokens(
			Set<ControlTokensPerAction> acTokens, ScenarioGraph sg){	
		Set<ControlTokensPerAction> filteredSet = null;
		filteredSet = new HashSet<ControlTokensPerAction>(acTokens);
		for(ControlTokensPerAction ast: acTokens){	
			ActorInstance actor = ast.getDetectorActor();
			if(!sg.getActors().containsKey(actor))
				filteredSet.add(ast);			
		}		
		return filteredSet;
	}	
	
	
	/**
	 * checks if two sets of control token are the same.
	 * @param f : first set of control tokens
	 * @param s : second set of control tokens
	 * @return true if the two sets are the same
	 */
	public static boolean AreControlTokensEqual(Set<ControlTokensPerAction> f, 
										Set<ControlTokensPerAction> s){
		if(f.size() != s.size())
			return false;

		for(ControlTokensPerAction cf: f){
			boolean found = false;
			for(ControlTokensPerAction cs: s){
				if(cf.equals(cs)){
					found = true;
					break;
				}
			}
			if(!found)	
				return false;
		}
		
		return true;
	}
};	
