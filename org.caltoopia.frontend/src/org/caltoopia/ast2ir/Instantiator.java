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

import java.util.List;
import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.cli.DirectoryException;
import org.caltoopia.ir.AbstractActor;
import org.caltoopia.ir.ActorInstance;
import org.caltoopia.ir.Declaration;
import org.caltoopia.ir.Scope;
import org.caltoopia.ir.TaggedExpression;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.Variable;
import org.caltoopia.ir.util.IrReplaceSwitch;
import org.caltoopia.types.TypeAnnotater;

public class Instantiator {

	static List<TaggedExpression> parameters;  
	
	static public AbstractActor instantiate(ActorInstance actorInstance, Scope environment) {

		AbstractActor actor;
		try {
			actor = (AbstractActor) ActorDirectory.findActor((TypeActor) actorInstance.getType());
		} catch (DirectoryException x) {
			System.err.println("[InstantiateActor] Internal error #1");
			return null;
		}
		
		parameters = actorInstance.getActualParameters();

		new IrReplaceSwitch() {
			@Override 
			public Declaration caseVariable(Variable variable) { 
				if (variable.isParameter() && (variable.getScope() instanceof AbstractActor) ) {
					for (TaggedExpression param : parameters) {
						if (param.getTag().equals(variable.getName())) {
							variable.setInitValue(param.getExpression());
							return variable;
						}
					}
					System.err.println(parameters.toString());
					throw new RuntimeException("[InstantiateActor] Internal error!");
				} else {		
					return super.caseVariable(variable);
				}
			}		
			
		}.doSwitch(actor);
		
		ConstantExpressionEvaluator.evaluate(actor, environment);

		new TypeAnnotater().doSwitch(actor);
		
		return actor;
	}		
}
