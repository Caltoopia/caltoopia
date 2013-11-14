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

package org.caltoopia.types;

import java.util.HashMap;
import java.util.Map;

import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.cli.DirectoryException;
import org.caltoopia.ir.AbstractActor;
import org.caltoopia.ir.Actor;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.ForwardDeclaration;
import org.caltoopia.ir.Member;
import org.caltoopia.ir.Network;
import org.caltoopia.ir.ProcExpression;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeDeclaration;
import org.caltoopia.ir.TypeDeclarationImport;
import org.caltoopia.ir.TypeList;
import org.caltoopia.ir.TypeRecord;
import org.caltoopia.ir.TypeUser;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.ir.VariableExternal;
import org.caltoopia.ir.VariableImport;
import org.caltoopia.ir.VariableReference;
import org.caltoopia.ir.util.IrReplaceSwitch;
import org.eclipse.emf.ecore.EObject;

/*
 * This class has one purpose but two ways to achieve this.
 * The purpose is to match type declarations (imports) with
 * in any users of these types inside the node.
 * It is intended to be used on elaborated actors and top network.
 * For actors it make sure that all type declaration users refer 
 * to type declaration IMPORTS since the real ones does not
 * exist inside the actor instance.
 * For top network it make sure that all type declaration users refer
 * to real type declarations since they have all been folded in to the 
 * top network.
 * This enables us to XML print & read the elaborated/instantiated network/actors.
 */

public class TypeMatchDeclaration extends IrReplaceSwitch {
	
	final boolean debugPrint =false;
	private Map<TypeDeclaration,TypeDeclarationImport> imports = new HashMap<TypeDeclaration,TypeDeclarationImport>();
	private Map<String,String> found = new HashMap<String,String>();
	private boolean topnetwork;
	@Override
	public AbstractActor caseAbstractActor(AbstractActor actor) {
		/*
		 * Build up map between TypeDeclaration => TypeDeclarationImport
		 * for this actor. So this can be used as a ref when pushing in
		 * types into expressions and references.
		 */
		if(actor instanceof Network) {
			topnetwork=true;
		} else if(actor instanceof Actor) {
			topnetwork=false;
		} else {
			topnetwork=false;			
		}

		found.clear();
		imports.clear();
		for(Declaration d:actor.getDeclarations()) {
			if(d instanceof TypeDeclarationImport) {
				try {
					TypeDeclaration real = ActorDirectory.findTypeDeclaration((TypeDeclarationImport) d, false);
					imports.put(real, (TypeDeclarationImport) d);
				} catch (DirectoryException e) {
					System.err.println("[TypeMatchDeclaration] Internal Error #1 - could not find type declaration import " + d.getName());
				}
			}
		}
		return super.caseAbstractActor(actor);
	}
	
	@Override
	public AbstractActor caseActor(Actor actor) {
		topnetwork = false;
		super.caseActor(actor);
		if(debugPrint) {
			for(String f:found.keySet()) {
				System.out.println("[TypeMatchDeclaration] Found type declaration " + f + " " + found.get(f));
			}
		}
		return actor;
	}
	
	@Override
	public AbstractActor caseNetwork(Network network) {
		topnetwork = true;
		return super.caseNetwork(network);
	}
	
    private Type replaceDirectTypeWithLocalImport(Type type) {
        if(type instanceof TypeUser) {
            found.put(((TypeUser) type).getDeclaration().getId(),(((TypeUser) type).getDeclaration() instanceof TypeDeclarationImport?"I_":"R_") +((TypeUser) type).getDeclaration().getName());
        }
        //Replace any direct declarations with import declarations
        if(type instanceof TypeUser && ((TypeUser) type).getDeclaration() instanceof TypeDeclaration) {
            String id = ((TypeUser) type).getDeclaration().getId();
            Declaration typeImport = null;
            for(Declaration d: imports.keySet()) {
                if(d.getId().equals(id)) {
                    typeImport = imports.get(d);
                    break;
                }
            }
            if(typeImport!=null) {
                ((TypeUser) type).setDeclaration(typeImport);
                if(debugPrint) {
                    System.out.println("[TypeMatchDeclaration] Replace direct " +
                            ((TypeUser) type).getDeclaration().getId()+ " " +
                            ((TypeUser) type).getDeclaration().getName());
                }
            }
        }
        return type;
    }
	
	private Type replaceNonLocalImport(Type type) {
		//Replace any import declaration that does not refer to imports in this file
		if(type instanceof TypeUser && ((TypeUser) type).getDeclaration() instanceof TypeDeclarationImport
				&& !imports.containsValue(((TypeUser) type).getDeclaration())) {
			for(TypeDeclarationImport d: imports.values()) {
				if(((TypeUser) type).getDeclaration().getName().equals(d.getName())) {
					((TypeUser) type).setDeclaration(d);
					if(debugPrint) {
						System.out.println("[TypeMatchDeclaration] Replace import " + 
								((TypeUser) type).getDeclaration().getId()+ " " +
								((TypeUser) type).getDeclaration().getName());
					}
					break;
				}
			}
		}
		return type;
	}

	@Override
	public Type caseTypeUser(TypeUser type) {
		if(topnetwork) {
			if(type.getDeclaration() instanceof TypeDeclarationImport) {
				try {
					TypeDeclaration real = ActorDirectory.findTypeDeclaration((TypeDeclarationImport) type.getDeclaration(),false);
					type.setDeclaration(real);
				} catch (DirectoryException e) {
					System.err.println("[TypeMatchDeclaration] Internal Error #2 - could not find type declaration import " + type.getDeclaration().getName());
				}
			}
		} else {
			replaceDirectTypeWithLocalImport(type);
			replaceNonLocalImport(type);
		}
		return super.caseTypeUser(type);
	}
}
