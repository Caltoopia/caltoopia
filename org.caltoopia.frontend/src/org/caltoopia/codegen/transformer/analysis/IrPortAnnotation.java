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

package org.caltoopia.codegen.transformer.analysis;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.cli.CompilationSession;
import org.caltoopia.cli.DirectoryException;
import org.caltoopia.codegen.UtilIR;
import org.caltoopia.codegen.transformer.IrTransformer;
import org.caltoopia.codegen.transformer.IrTransformer.IrPassTypes;
import org.caltoopia.codegen.transformer.TransUtil;
import org.caltoopia.codegen.transformer.TransUtil.AnnotationsFilter;
import org.caltoopia.ir.AbstractActor;
import org.caltoopia.ir.Action;
import org.caltoopia.ir.Actor;
import org.caltoopia.ir.ActorInstance;
import org.caltoopia.ir.Annotation;
import org.caltoopia.ir.AnnotationArgument;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Expression;
import org.caltoopia.ir.ExternalActor;
import org.caltoopia.ir.Member;
import org.caltoopia.ir.Network;
import org.caltoopia.ir.Node;
import org.caltoopia.ir.Port;
import org.caltoopia.ir.PortPeek;
import org.caltoopia.ir.PortRead;
import org.caltoopia.ir.PortWrite;
import org.caltoopia.ir.Type;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.TypeDeclaration;
import org.caltoopia.ir.TypeDeclarationImport;
import org.caltoopia.ir.TypeList;
import org.caltoopia.ir.TypeUser;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.VariableExpression;
import org.caltoopia.ir.VariableReference;
import org.caltoopia.ir.util.IrReplaceSwitch;
import org.eclipse.emf.ecore.EObject;

public class IrPortAnnotation extends IrReplaceSwitch {

	private PrintStream serr = null; 
	AbstractActor currentActor = null;
	Action currentAction = null;
	PortRead currentRead = null;
	PortWrite currentWrite = null;
	PortPeek currentPeek = null;
	
	/*
	 * Annotate all ports with their index (order) in list of in- or out-ports
	 * 
	 * Quality: 5, does what the label say
	 * 
	 * node: top network
	 * session: contains metadata about the build like directory paths etc
	 * errPrint: if error printout should be printed
	 */
	public IrPortAnnotation(Node node, CompilationSession session, boolean errPrint) {
		if(!errPrint) {
			serr = new PrintStream(new OutputStream(){
			    public void write(int b) {
			        //NO-OP
			    }
			});
		} else {
			serr = System.err;
		}
		//start at caseNetwork() below
		this.doSwitch(node);
	}
	
    @Override
    public AbstractActor caseActor(Actor obj) {
        currentActor = obj;
        for(int i=0; i<obj.getInputPorts().size();i++) {
            Port p = obj.getInputPorts().get(i);
            TransUtil.setAnnotation(p, "Port", "index", String.valueOf(i));
        }
        for(int i=0; i<obj.getOutputPorts().size();i++) {
            Port p = obj.getOutputPorts().get(i);
            TransUtil.setAnnotation(p, "Port", "index", String.valueOf(i));
        }
        AbstractActor ret = super.caseActor(obj);
        currentActor = null;
        return ret;
    }
    
    @Override
    public Action caseAction(Action obj) {
        currentAction = obj;
        Action ret = super.caseAction(obj);
        currentAction = null;
        return ret;
    }

    @Override
    public PortRead casePortRead(PortRead obj) {
        currentRead = obj;
        Port p = obj.getPort();
        TransUtil.copySelectedAnnotations(obj, p, new AnnotationsFilter("Port", new String[]{"index"}));
        TransUtil.setAnnotation(obj, "Port", "name", p.getName());
        PortRead ret = super.casePortRead(obj);
        currentRead = null;
        return ret;
    }


    @Override
    public PortPeek casePortPeek(PortPeek obj) {
        currentPeek = obj;
        Port p = obj.getPort();
        TransUtil.copySelectedAnnotations(obj, p, new AnnotationsFilter("Port", new String[]{"index"}));
        TransUtil.setAnnotation(obj, "Port", "name", p.getName());
        PortPeek ret = super.casePortPeek(obj);
        currentPeek = null;
        return ret;
    }

    @Override
    public PortWrite casePortWrite(PortWrite obj) {
        currentWrite = obj;
        Port p = obj.getPort();
        TransUtil.copySelectedAnnotations(obj, p, new AnnotationsFilter("Port", new String[]{"index"}));
        TransUtil.setAnnotation(obj, "Port", "name", p.getName());
        PortWrite ret = super.casePortWrite(obj);
        currentWrite = null;
        return ret;
    }

	@Override
	public AbstractActor caseNetwork(Network network) {
        for(ActorInstance a : network.getActors()) {
            AbstractActor actor=null;
            try {
                System.out.println("[IrAnnotatePort] Read in actor instance '" + a.getName() + "' of class " + ((TypeActor) a.getType()).getName());
                actor = (AbstractActor) ActorDirectory.findTransformedActor(a);
            } catch (DirectoryException x) {
                //serr.println("[IrAnnotateVariable] Internal error could not get actor of type " + a.getType().toString());
            }
            if(actor!=null && !(actor instanceof ExternalActor)) {
                actor = (AbstractActor) doSwitch(actor);
                String path = TransUtil.getPath(actor);
                TransUtil.AnnotatePass(actor, IrPassTypes.Port, "0");
                ActorDirectory.addTransformedActor(actor, a, path);
            }
        }
        /* TODO Nothing to do for network?
        AbstractActor ret = super.caseNetwork(network);
        String path = TransUtil.getPath(ret);
        TransUtil.AnnotatePass(ret, IrPassTypes.Port, "0");
        ActorDirectory.addTransformedActor(ret, null, path);
        */
		return network;
	}

}
