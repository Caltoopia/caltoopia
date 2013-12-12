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

package org.caltoopia.codegen.transformer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.caltoopia.ast2ir.Instantiator;
import org.caltoopia.ast2ir.Util;
import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.cli.CompilationSession;
import org.caltoopia.cli.DirectoryException;
import org.caltoopia.codegen.transformer.analysis.IrPortAnnotation;
import org.caltoopia.codegen.transformer.analysis.IrTypeAnnotation;
import org.caltoopia.codegen.transformer.analysis.IrTypeStructureAnnotation;
import org.caltoopia.codegen.transformer.analysis.IrUsedDeclarationAnnotation;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation;
import org.caltoopia.codegen.transformer.analysis.IrVariablePlacementAnnotation;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarAccess;
import org.caltoopia.codegen.transformer.analysis.IrVariableAnnotation.VarType;
import org.caltoopia.codegen.transformer.transforms.CreateForLoop;
import org.caltoopia.codegen.transformer.transforms.ExprToTempVar;
import org.caltoopia.codegen.transformer.transforms.MoveInitValueExpr;
import org.caltoopia.codegen.transformer.transforms.PortTransformations;
import org.caltoopia.codegen.transformer.transforms.StmtAlternativeTrans;
import org.caltoopia.ir.AbstractActor;
import org.caltoopia.ir.Actor;
import org.caltoopia.ir.ActorInstance;
import org.caltoopia.ir.Annotation;
import org.caltoopia.ir.AnnotationArgument;
import org.caltoopia.ir.ExternalActor;
import org.caltoopia.ir.IrFactory;
import org.caltoopia.ir.Network;
import org.caltoopia.ir.Node;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.TypeDeclarationImport;
import org.caltoopia.ir.TypeUser;
import org.caltoopia.ir.util.IrReplaceSwitch;
import org.caltoopia.types.TypeAnnotater;
import org.caltoopia.types.TypeMatchDeclaration;
import org.eclipse.emf.ecore.EObject;

public class IrTransformer {
	/*
	 * IR annotations
	 */
	static public final String VARIABLE_ANNOTATION = "Variable";
    static public final String TYPE_ANNOTATION = "Type";
    static public final String C_ANNOTATION = "CNode";
    public enum CNodes {
        cNode,
        inlineBlock
    };

	/*
	 * The different types of annotation passes that can be executed
	 */
	public enum IrPassTypes {
		//Annotations
		Init,
        Variable,
        UsedDeclaration,
		TypeUsage,
		TypeStructure,
		VariablePlacement,
		Port,
		
		//Transformations
		PortTransformations,
		MoveInitValueExpr,
		CreateForLoop,
		ExprToTempVar,
		StmtAlternative
	}
	
	/*
	 * Constructor that runs the list of annotation & transform passes 
	 * on the top network and actor instances.
	 * The passes takes a top network type.
	 * When a annotator need access to other nodes in the tree it needs 
	 * to retrieve them from the $Transformed part of ActorDirectory
	 * 
	 * The annotations are placed on nodes (as e.g. variables and types)
	 * to have the analysis of the IR readily available when transforming
	 * or printing the node. The intention is that later passes or printing
	 * should avoid doing their own analysis.
	 * 
	 * The transformations are intended to transform all CAL specific imperative code
	 * into statements that have an easy equivalent in c-code. It does not introduce
	 * memory or port handling (as the previous IR2CIR codegen did), since that is handled
	 * in the c-printer.
	 */
	public IrTransformer(TypeActor nodeType, CompilationSession session, List<IrPassTypes> passes) {
		//Apply the list of annotation passes in order
		Node node=null;
		for(IrPassTypes p : passes) {
		    //Get elaborated transformed top network
			node=getNode(nodeType,p);
			switch (p) {
            case UsedDeclaration:
                //Annotate all variable declarations (incl functions and procedures) if they are still used
                printHeader("Used Declaration");
                new IrUsedDeclarationAnnotation(node, session, true);
                break;
            case Variable:
                //Annotate all variable declarations (incl functions and procedures) how and where they are used, also all var ref/expr classified based on lists, members, indexes, etc
                printHeader("Variable");
                new IrVariableAnnotation(node, session, true);
                break;
			case TypeUsage:
			    //Annotates how user types and their members are used by variables, the intention is that it should be used to optimize placement of variables
				printHeader("Type Usage");
				new IrTypeAnnotation(node, session, true);
				break;
			case TypeStructure:
                //Annotates the user types members, is currently not of much use more than debugging but might be needed when introducing tuple types
				printHeader("Type Structure");
				new IrTypeStructureAnnotation(node, session, true);
				break;
			case VariablePlacement:
			    //Annotates variable placement, i.e. heap, stack, fifo etc, this is not followed in the printer now when we have a runtime handling of arrays and user types (i.e. all print the same no change at static level)
				printHeader("Variable Placement");
				new IrVariablePlacementAnnotation(node, session, true);
				break;
            case Port:
                //Annotates the ports with their index since needed to identify in an array of input or output ports in the c-code
                printHeader("Port ");
                new IrPortAnnotation(node, session, true);
                break;
            case MoveInitValueExpr:
                //Transformation to move any variable initialization to later assignment that is non-scalar or dependent on such variable initialization or port reading
                printHeader("Move initvalue expr");
                new MoveInitValueExpr(node, session, true);
                break;
            case PortTransformations:
                //Transformation to move any port write statement last after the action statements, port write statements are usually assignment of temp output var (U_x) from output expression 
                printHeader("Move port statements");
                new PortTransformations(node, session, true);
                break;
            case CreateForLoop:
                //Transformation to convert foreach statements into while statements etc
                printHeader("Create c-style for loops");
                new CreateForLoop(node, session, true);
                break;
            case ExprToTempVar:
                //Transforms the IR so that non-scalar expressions and assignments (including strings) are broken down to scalar assignments
                printHeader("Create temp var for expressions");
                new ExprToTempVar(node, session, true);
                break;
            case StmtAlternative:
                //Transforms the IR so that stmt alternative guards becomes if statements
                printHeader("Stmt alternative transformation");
                new StmtAlternativeTrans(node, session, true);
                break;
			default:
				System.err.println("[IrTransformer] Trying to run an annotation or transformation pass that does not exist!");
			}
			ActorDirectory.resetTransformedNetwork();
		}
	}
	
	/*
	 * Local utility functions
	 */
	//Read the top network from transformed (elaborated) part of actor directory
	private Node getNode(TypeActor nodeType, IrPassTypes p) {
		Node node=null;
		try {
			System.out.println("[IrTransformer] Get top network from storage.");
			node=ActorDirectory.findTransformedActor(nodeType);
		} catch (DirectoryException e) {
			System.err.println("[IrTransformer] Internal error could not get actor of type " + nodeType.toString() + " at pass " + p.toString());
		}
		return node;
	}
	
	private static void printHeader(String name) {
		System.out.println("[IrTransformer] ********************************************************************");
		System.out.println("[IrTransformer] ********************************************************************");
		int preSpaces = (45-name.length())/2;
		int postSpaces = 45-name.length()-preSpaces;
		String spaces = "                                                                                           ";
		System.out.println("[IrTransformer] ************ " + spaces.substring(0, preSpaces) + name + spaces.substring(0, postSpaces)  + " *********");
		System.out.println("[IrTransformer] ********************************************************************");
		System.out.println("[IrTransformer] ********************************************************************");
	}

	/*
	 * Function to store the initial elaborated network 
	 * and the instantiated actors into
	 * the $Transformed part of the ActorDirectory.
	 * Must be called before any pass.
	 * The elaborated top network is taken as input the actor 
	 * classes are obtained from ActorDirectory.
	 */
	public static void IrTransformNetworkInit(Network network) {
		printHeader("Init");
		String path = TransUtil.getPath(network);

		new TypeAnnotater().doSwitch(network);
		new TypeMatchDeclaration().doSwitch(network);
		
		TransUtil.AnnotatePass(network, IrPassTypes.Init, "0");
		System.out.println("[IrTransformer] Write network  " + network.getType().getName());
		ActorDirectory.addTransformedActor(network, null, path);

		for(ActorInstance a : network.getActors()) {
			AbstractActor actor=null;
			try {
				System.out.println("[IrTransformer] Read in actor class " + ((TypeActor) a.getType()).getName());
				actor = (AbstractActor) ActorDirectory.findActor((TypeActor) a.getType());
			} catch (DirectoryException x) {
				System.err.println("[IrTransformer] Internal error could not get actor of type " + a.getType().toString() + " at init.");
			}
			if(actor!=null && !(actor instanceof ExternalActor)) {
				path = TransUtil.getPath(actor);
				AbstractActor actorInstantiated = Instantiator.instantiate(a, network);
				TransUtil.AnnotatePass(actorInstantiated, IrPassTypes.Init, "0");
				String nsName = Util.marshallQualifiedName(((TypeActor) a.getType()).getNamespace());
				Annotation annotation = TransUtil.createAnnotation(actorInstantiated, "Instance");
				TransUtil.setAnnotation(annotation, "name", a.getName());
				TransUtil.setAnnotation(annotation, "id", a.getId());
				System.out.println("[IrTransformer] Write actor instance named " + 
									nsName + "__" + a.getName() + 
									" of class " + ((TypeActor) a.getType()).getName());
				ActorDirectory.addTransformedActor(actorInstantiated, a, path);
			}
		}
	}
	
}
