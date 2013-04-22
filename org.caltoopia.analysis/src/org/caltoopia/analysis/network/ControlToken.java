package org.caltoopia.analysis.network;

import java.util.ArrayList;
import java.util.List;

import org.caltoopia.analysis.air.ActorInstance;
import org.caltoopia.analysis.air.PortInstance;
import org.caltoopia.analysis.util.collections.UnionOfDisjointIntervals;
import org.caltoopia.ast2ir.Stream;

/**
 * represents a control token of produced by a detector actor.
 */
public class ControlToken{
	//the detector actor
	private ActorInstance detectorActor;
	
	//the token name as given by annotation in the CAL actor
	private String tokenName;
	
	//the list of control ports that produce this control token
	private List<PortInstance> controlPorts = new ArrayList<PortInstance>();
	
	//constructor
	public ControlToken(ActorInstance a,  String t){
		detectorActor = a;
		tokenName = t;
	}
	
	public void setDetectorActor(ActorInstance a){
		detectorActor = a;
	}
	
	public void setName(String name){
		tokenName = name;
	}
	
	public void setControlPorts( List<PortInstance> p){
		controlPorts = p;
	}
	
	public ActorInstance getDetectorActor(){
		return detectorActor;
	}
	
	public String getName(){
		return tokenName;
	}
	
	public List<PortInstance> getControlPorts(){
		return controlPorts;
	}
	
	public void addControlPort( PortInstance p){
		controlPorts.add(p);
	}
	
	//print to a stream
	void print(Stream stream){
		stream.println("DetectorActor: "+detectorActor.getInstanceName());
		stream.println("\tTokenName: "+tokenName);
		stream.print("\tContorlPorts: ");
		for(PortInstance p: controlPorts){
			stream.print(p.getName()+" ");
		}
		stream.println();
	}
	
	/**
	 * parses a 'string' and generates a union of disjoint intervals.
	 * 'string' must consist of sub-strings separated by a slash(/): 
	 * string = sub-string1/sub-string2/sub-string3
	 * A sub-string can only be either a numeric letter (e.g. 2) or 
	 * hyphen separated two numeric letters (e.g. 4-7 ). 
	 * Example: The string 2/4-7/13/8-9 produces the union of intervals
	 * given by {[2,2],[4-9],[13-13]}
	 * @param string
	 * @return UnionOfDisjointIntervals if the input string is valid.
	 * 	It returns null otherwise.
	 */
	public static UnionOfDisjointIntervals parseIntervals(String string){
		UnionOfDisjointIntervals intervals = new UnionOfDisjointIntervals();
		for(String interval: string.split("/")){
			String s[] = interval.split("-");
			
			if(s.length==1 && Long.parseLong(s[0].trim()) >= 0){
				intervals.add(Long.parseLong(s[0].trim()), Long.parseLong(s[0].trim()));
			}
			else if(s.length==2 && Long.parseLong(s[0].trim()) >= 0 
					&& Long.parseLong(s[1].trim()) >= 0){
				intervals.add(Long.parseLong(s[0].trim()), Long.parseLong(s[1].trim()));
			}
			else
				return null;									
		}
		return intervals;
	}
};