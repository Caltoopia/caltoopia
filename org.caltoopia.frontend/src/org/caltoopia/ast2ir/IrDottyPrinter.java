/* 
 * Copyright (c) Ericsson AB, 2013
 * All rights reserved.
 *
 * License terms:
 *
 * Redistribution and use in source and binary forms, 
 * with or without modification, are permitted provided 
 * that the following conditions are met:
 *     * Redistributions of source code must retain the above 
 *       copyright notice, this list of conditions and the 
 *       following disclaimer.
 *     * Redistributions in binary form must reproduce the 
 *       above copyright notice, this list of conditions and 
 *       the following disclaimer in the documentation and/or 
 *       other materials provided with the distribution.
 *     * Neither the name of the copyright holder nor the names 
 *       of its contributors may be used to endorse or promote 
 *       products derived from this software without specific 
 *       prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND 
 * CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, 
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; 
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) 
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR 
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS 
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.caltoopia.ast2ir;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.caltoopia.codegen.UtilIR;
import org.caltoopia.ir.ActorInstance;
import org.caltoopia.ir.Connection;
import org.caltoopia.ir.FromSource;
import org.caltoopia.ir.IntegerLiteral;
import org.caltoopia.ir.Network;
import org.caltoopia.ir.Point2PointConnection;
import org.caltoopia.ir.Port;
import org.caltoopia.ir.PortInstance;
import org.caltoopia.ir.TaggedExpression;
import org.caltoopia.ir.ToSink;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.util.IrSwitch;
import java.io.File;
import java.sql.Timestamp;

public class IrDottyPrinter extends IrSwitch<Stream> {

	int revhue = 0;
	
	double hue;
	
	private  Stream s;

	private String folder; 
	
	Set<String> subNets = new HashSet<String>();	
	
	public IrDottyPrinter(String folder) {
		this.folder = folder;				
	}
				
	@Override
	public Stream caseActorInstance(ActorInstance actor) {
		List<PortInstance> inputPorts  = actor.getInputs();
		List<PortInstance> outputPorts = actor.getOutputs();
		int rows = inputPorts.size() > outputPorts.size() ? inputPorts.size() : outputPorts.size();
		
		s.println(actor.getName() + " [label=<");
		s.inc();
		s.println("<TABLE BORDER=\"1\" CELLBORDER=\"0\" CELLSPACING=\"0\">");
		s.println("<TR><TD COLSPAN=\"2\">" + actor.getName() + "</TD></TR>");
		s.println("<TR><TD COLSPAN=\"2\"><FONT POINT-SIZE=\"8\">[" + ((TypeActor) actor.getType()).getName() + "]</FONT></TD></TR>");
		
		for (int i = 0; i < rows; i++) {
			String in = i < inputPorts.size() ? inputPorts.get(i).getName() : "";
			String out = i < outputPorts.size() ? outputPorts.get(i).getName() : "";
			s.println("<TR><TD ALIGN=\"LEFT\" PORT=\"" + in + "\">" + in + 
					  "</TD><TD ALIGN=\"RIGHT\" PORT=\"" + out + "\">" + out + "</TD></TR>");
		}
		s.printlnDec("</TABLE>>];");	

		return s;		
	}
		
	@Override
	public Stream caseNetwork(Network network) {		
		s = new Stream(folder + File.separator + Util.namespace2Path(network.getType().getNamespace()) + File.separator + network.getType().getName() + ".dot");		

		java.util.Date date= new java.util.Date();
		s.println();
		s.println("/* --- CAL Dotty Printer " + new Timestamp(date.getTime()) + " --- */");

		s.println("digraph " + network.getType().getName() + "{");
		s.inc();
		s.println("node [shape=none];");
		s.println("rankdir=LR;");

		for (ActorInstance instance : network.getActors()) {
			caseActorInstance(instance);
		}
		
		for (Connection c : network.getConnections()) {
			doSwitch(c);
		}				
		
		/* Network input ports */
		List<Port> inputPorts  = network.getInputPorts();
		if(inputPorts.size()>0) {
			s.println( "__INs" + " [label=<");
			s.println("<TABLE BORDER=\"1\" CELLBORDER=\"0\" CELLSPACING=\"0\">");
			s.println("<TR><TD COLSPAN=\"2\">INs</TD></TR>");
			s.inc();
			for (int i = 0; i < inputPorts.size(); i++) {
				String in = inputPorts.get(i).getName();
				s.println("<TR><TD></TD><TD ALIGN=\"RIGHT\" PORT=\"" + in + "\">" + in + "</TD></TR>");
			}
			s.printlnDec("</TABLE>>];");				
		}
		
		/* Network output ports */
		List<Port> outputPorts  = network.getOutputPorts();
		if(outputPorts.size()>0) {
			s.println("__OUTs" + " [label=<");
			s.println("<TABLE BORDER=\"1\" CELLBORDER=\"0\" CELLSPACING=\"0\">");
			s.printlnInc("<TR><TD COLSPAN=\"2\">OUTs</TD></TR>");
			for (int i = 0; i < outputPorts.size(); i++) {
				String out = outputPorts.get(i).getName();
				s.println("<TR><TD ALIGN=\"LEFT\" PORT=\"" + out + "\">" + out + "</TD><TD></TD></TR>");
			}
			s.printlnDec("</TABLE>>];");				
		}

		s.println();
		s.println();
		
		s.println("}");	
		
		return s;
		
	}
	
	@Override
	public Stream casePoint2PointConnection(Point2PointConnection c) {
		String source, target;
		
		source = c.getSource().getActor().getName() + ":" + c.getSource().getName();
		target = c.getTarget().getActor().getName() + ":" + c.getTarget().getName();
		//reverse the bits, to get a hue that looks "random" but is the same each time, close by lines have different colors
		hue = ((((revhue * 0x0802 & 0x22110) | (revhue * 0x8020 & 0x88440)) * 0x10101 >> 16) & 255)/255.0;
		revhue++;
		revhue %= 128;
		s.print(source + ":e -> " + target + ":w [color=\"" + hue + " 1.000 " + ((revhue & 1)*0.5+0.5) + "\"");
		long bufferSize = 0;
		for (TaggedExpression attribute : c.getAttributes()) {
			if (attribute.getTag().compareToIgnoreCase("BufferSize") == 0) {
				assert(attribute.getExpression() instanceof IntegerLiteral);
				long tmp = ((IntegerLiteral) attribute.getExpression()).getValue();
				bufferSize = tmp > bufferSize ? tmp : bufferSize;
			}
		}	
		s.print(",label=\"sz=" + bufferSize + "\"");
		s.println ("];");
	
		return s;
	}

	@Override
	public Stream caseToSink(ToSink c) {
		String source, target;
		
		source = c.getSource().getActor().getName() + ":" + c.getSource().getName();
		target = c.getSink().getName();
		
		hue=((((revhue * 0x0802 & 0x22110) | (revhue * 0x8020 & 0x88440)) * 0x10101 >> 16) & 255)/255.0;
		revhue++;revhue %=128;
		s.println(source + ":e -> " + target + ":w [color=\"" + hue + " 1.000 " + ((revhue & 1)*0.5+0.5) + "\"];");

		return s;
	}
	
	@Override
	public Stream caseFromSource(FromSource c) {
		String source, target;
		
		source = c.getSource().getName();
		target = c.getTarget().getActor().getName() + ":" + c.getTarget().getName();
		
		
		hue=((((revhue * 0x0802 & 0x22110) | (revhue * 0x8020 & 0x88440)) * 0x10101 >> 16) & 255)/255.0;
		revhue++;revhue %=128;
		s.println(source + ":e -> " + target + ":w [color=\"" + hue + " 1.000 " + ((revhue & 1)*0.5+0.5) + "\"];");

		
		return s;
	}
	
}
