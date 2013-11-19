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

import java.util.ArrayList;
import java.util.List;
import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.cli.DirectoryException;
import org.caltoopia.ir.AbstractActor;
import org.caltoopia.ir.ActorInstance;
import org.caltoopia.ir.Connection;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.FromSource;
import org.caltoopia.ir.IrFactory;
import org.caltoopia.ir.Network;
import org.caltoopia.ir.Point2PointConnection;
import org.caltoopia.ir.Port;
import org.caltoopia.ir.PortInstance;
import org.caltoopia.ir.ToSink;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.Variable;

public class Elaborator {
		
	public static Network elaborate(Network topNetwork) {
		// System.out.print("Elaborating network '" + topNetwork.getType().getName() + "' .");
		
		boolean done = false;
		
		while (!done) {				
			// IrCompactPrinter.print(null, topNetwork);
			
			done = true;
			
			// Start by finding a subnetwork.
			ActorInstance subNetwork = null;
			for (ActorInstance actorVariable : topNetwork.getActors()) {
				// System.out.print(".");
				AbstractActor actorClass;
				try {
					actorClass = ActorDirectory.findActor((TypeActor) actorVariable.getType());
				} catch (DirectoryException x) {
					System.err.println("[Elaborator] Internal error #1: " + x.getMessage());
					return null;
				}
				
				if (actorClass instanceof Network) {
					subNetwork = actorVariable;
				}
			}	
			
			if (subNetwork != null) {
				done = false;
				// Remove the actor instance from the network.	
				topNetwork.getActors().remove(subNetwork);
				
				for (PortInstance networkInport : subNetwork.getInputs()) {
					for (Connection c : networkInport.getConnections()) {
						topNetwork.getConnections().remove(c);
					}
				}

				for (PortInstance networkOutport : subNetwork.getOutputs()) {
					for (Connection c : networkOutport.getConnections()) {
						topNetwork.getConnections().remove(c);
					}					
				}
			
				// Create instances of all the entities in the subnetwork by cloning it
				Network clone = (Network) Instantiator.instantiate(subNetwork, topNetwork);				
				String prefix = subNetwork.getName() + "__";

				// Copy all variable declarations and do some name mangling
				for (Declaration decl : clone.getDeclarations()) {
					if (decl instanceof Variable) {
						((Variable) decl).setName(prefix + ((Variable) decl).getName());
						topNetwork.getDeclarations().add(decl);
					}
				}				
				
				// Add all the inner actors to the top level network.
				for (ActorInstance instance : clone.getActors()) {										
					instance.setName(prefix + instance.getName());
                    //Make sure to give the actor instance a unique ID due to that the subnetwork might be instantiated several times
                    instance.setId(Util.getDefinitionId());
					topNetwork.getActors().add(instance);	
					// Take care of actor parameters by making them into 
					// actor state variables 
						
					//Reconnect input ports of all entities in the subnetwork
					for (PortInstance actorInport : instance.getInputs()) {
						// Test if the input port is connected

						if (!actorInport.getConnections().isEmpty()) {
							// Can only have one input connection
							Connection actorInputConnection = actorInport.getConnections().get(0);								
							
							// Test if the input comes from an internal connection. If so just add is. 
							// Otherwise it is from a network port and then we a new connection 
							// that goes straight from the source to the actor port 
							if (actorInputConnection instanceof Point2PointConnection) {
								topNetwork.getConnections().add(actorInputConnection);								
							} else {
								FromSource sourceConnection = (FromSource) actorInputConnection;
								String sourcePortName = sourceConnection.getSource().getName();
								//Now disconnect the connection between the actor and the network port
								actorInport.getConnections().remove(0);									
 									
								// Find the incoming network-port
								PortInstance networkInport = null;
								for (PortInstance p : subNetwork.getInputs()) {
									if (p.getName().equals(sourcePortName)) {
										networkInport = p;
									}
								}
									
								assert(networkInport != null);

								if (!networkInport.getConnections().isEmpty()) {
									// Can only have one input connection
									Connection networkInputConnection = networkInport.getConnections().get(0);	
									if (networkInputConnection instanceof Point2PointConnection) {
										PortInstance source = ((Point2PointConnection) networkInputConnection).getSource();																				
										topNetwork.getConnections().remove(networkInputConnection);
										source.getConnections().remove(networkInputConnection);
										
										//Finally, create a new connection from
										Point2PointConnection rewired = IrFactory.eINSTANCE.createPoint2PointConnection();
										
										rewired.setSource(source);
										source.getConnections().add(rewired);
										rewired.setTarget(actorInport);
										
										rewired.getAttributes().addAll(networkInputConnection.getAttributes());
										rewired.getAttributes().addAll(actorInputConnection.getAttributes());
										rewired.getAnnotations().addAll(networkInputConnection.getAnnotations());
										rewired.getAnnotations().addAll(actorInputConnection.getAnnotations());

										actorInport.getConnections().add(rewired);	
										topNetwork.getConnections().add(rewired);
									} else {
										Port source = ((FromSource) networkInputConnection).getSource();										
										topNetwork.getConnections().remove(networkInputConnection);
										
										//Finally, create a new connection from
										FromSource rewired = IrFactory.eINSTANCE.createFromSource();
										
										rewired.setSource(source);
										rewired.setTarget(actorInport);
										
										rewired.getAttributes().addAll(networkInputConnection.getAttributes());
										rewired.getAttributes().addAll(actorInputConnection.getAttributes());
										rewired.getAnnotations().addAll(networkInputConnection.getAnnotations());
										rewired.getAnnotations().addAll(actorInputConnection.getAnnotations());

										actorInport.getConnections().add(rewired);											
										topNetwork.getConnections().add(rewired);
									}
								}	
							}
						}
					}							
					
					//Reconnect output port
					for (PortInstance actorOutport : instance.getOutputs()) {	
						// Test if the input port is connected
						List<Connection> deletedOutputConnections = new ArrayList<Connection>();
						List<Connection> addedOutputConnections = new ArrayList<Connection>();
							
						for (Connection actorOutputConnection : actorOutport.getConnections()) {								
							// Test if the output goes to a network output port.
							// If true, create a new connection straight from the 
							// actor to the target port 
							if (actorOutputConnection instanceof ToSink) {
								ToSink targetConnection = (ToSink) actorOutputConnection;
								String targetPortName = targetConnection.getSink().getName();
								
								//Now disconnect the connection between the actor and the network port
								deletedOutputConnections.add(actorOutputConnection);

								topNetwork.getConnections().remove(actorOutputConnection);
								
								// Find the outgoing network-port
								PortInstance networkOutport = null;
								for (PortInstance p : subNetwork.getOutputs()) {
									if (p.getName().equals(targetPortName)) {
										networkOutport = p;
									}
								}
									
								assert(networkOutport != null);
								
								for (Connection networkOutputConnection : networkOutport.getConnections()) {	
									if (networkOutputConnection instanceof Point2PointConnection) {
										PortInstance target = ((Point2PointConnection) networkOutputConnection).getTarget();
										
										//Finally, create a new connection from
										Point2PointConnection rewired = IrFactory.eINSTANCE.createPoint2PointConnection();
										rewired.setSource(actorOutport);
										addedOutputConnections.add(rewired);
										rewired.setTarget(target);

										rewired.getAttributes().addAll(networkOutputConnection.getAttributes());
										rewired.getAttributes().addAll(networkOutputConnection.getAttributes());
										rewired.getAnnotations().addAll(networkOutputConnection.getAnnotations());
										rewired.getAnnotations().addAll(networkOutputConnection.getAnnotations());
										
										target.getConnections().add(rewired);
										target.getConnections().remove(networkOutputConnection);

										topNetwork.getConnections().remove(networkOutputConnection);
										topNetwork.getConnections().add(rewired);
									} else {
										Port target = ((ToSink) networkOutputConnection).getSink();
										
										//Finally, create a new connection from
										ToSink rewired = IrFactory.eINSTANCE.createToSink();
										rewired.setSource(actorOutport);
										addedOutputConnections.add(rewired);
										rewired.setSink(target);
										
										rewired.getAttributes().addAll(networkOutputConnection.getAttributes());
										rewired.getAttributes().addAll(networkOutputConnection.getAttributes());
										rewired.getAnnotations().addAll(networkOutputConnection.getAnnotations());
										rewired.getAnnotations().addAll(networkOutputConnection.getAnnotations());
										
										topNetwork.getConnections().remove(networkOutputConnection);
										topNetwork.getConnections().add(rewired);
									}
								}
								
							}
						}
						actorOutport.getConnections().addAll(addedOutputConnections);														
						actorOutport.getConnections().removeAll(deletedOutputConnections);														
					}
				}
			}
		}
		System.out.println("done.");

		return topNetwork;
	}
}
