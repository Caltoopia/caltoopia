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

package org.caltoopia.codegen.printer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.caltoopia.ast2ir.Stream;
import org.caltoopia.ast2ir.Util;
import org.caltoopia.cli.ActorDirectory;
import org.caltoopia.cli.Cal2C;
import org.caltoopia.cli.CompilationSession;
import org.caltoopia.cli.DirectoryException;
import org.caltoopia.codegen.CEnvironment;
import org.caltoopia.codegen.CodegenError;
import org.caltoopia.ir.AbstractActor;
import org.caltoopia.ir.ActorInstance;
import org.caltoopia.ir.Connection;
import org.caltoopia.ir.ExternalActor;
import org.caltoopia.ir.IntegerLiteral;
import org.caltoopia.ir.Network;
import org.caltoopia.ir.Point2PointConnection;
import org.caltoopia.ir.Port;
import org.caltoopia.ir.PortInstance;
import org.caltoopia.ir.StringLiteral;
import org.caltoopia.ir.TaggedExpression;
import org.caltoopia.ir.TypeActor;
import org.caltoopia.ir.TypeString;

/*
 * This class prints example Calvin script and
 * Python files to setup the top network with the
 * actors.
 * 
 * Quality: 4, should work
 */
public class CalvinPrintTopFiles {

    CompilationSession session = null;
    PrintStream out = null;
    CEnvironment cenv = null;
    Network network = null;

    /*
     * Constructor for handling the script files.
     * 
     * session: session info
     * cenv: input variable having collected information that is 
     *       needed in makefiles etc from all the CBuilders
     */
    public CalvinPrintTopFiles(CompilationSession session, CEnvironment cenv, Network network) {
        this.session = session;
        this.out = session.getOutputStream();
        this.cenv = cenv;
        this.network = network;
    }
    
    /*
     * Print the example calvin runtime script corresponding to
     * the top network.
     */
    public void CalvinScriptFile() throws Exception {
        TypeActor elaboratedNetworkType = session.getElaboratedNetwork().getType();
        String nsName = Util.marshallQualifiedName(elaboratedNetworkType.getNamespace());
        String baseName = nsName + "__" + elaboratedNetworkType .getName();

        String file = session.getOutputFolder() + File.separator + baseName + ".cs";
        out.println("Writing '" + file + "'");
        Stream s = new Stream(file);

        s.println("# " + baseName);

        /*
         *  LOAD the actors
         */
        Set<String> artClasses = new HashSet<String>();
        artClasses.clear();
        for (ActorInstance instance : network.getActors()) {
            TypeActor type = ((TypeActor) instance.getType());
            String actorInstanceName = Util.marshallQualifiedName(type.getNamespace()) + "__" + instance.getName();
            String actorClassName = null;
            //The ART actors are implemented in the runtime and no need to load them 
            if(!type.getName().startsWith("art_")) {
                actorClassName = actorInstanceName; 
                //Only new ones are defined
                if(!artClasses.contains(actorClassName)) {
                    s.println("LOAD ./" + actorClassName);
                    artClasses.add(actorClassName);
                }
            }
        }
        
        /*
         *  Instantiate (NEW) the actors with
         *  parameters for ART-actors or external
         *  actors.
         */
        String actorInstanceStr = "";
        int actorId = 0;
        for (ActorInstance actor : network.getActors()) {
            TypeActor type = ((TypeActor) actor.getType());
            String actorInstanceName =  Util.marshallQualifiedName(type.getNamespace()) + "__" + actor.getName();
            String actorClassName = null;
            //The ART actors are implemented in the runtime and we use the class name directly instead of instance name 
            if(type.getName().startsWith("art_")) {
                actorClassName = type.getName(); 
            } else {
                actorClassName = actorInstanceName; 
            }
            s.print("NEW " + actorClassName + " " + actorInstanceName);
            actorInstanceStr += " " + actorInstanceName;
            AbstractActor aactor = null;
            try {
                aactor = ActorDirectory.findActor((TypeActor) actor.getType());
            } catch (DirectoryException x) {
            }
            //For any ART actors set the parameters, all other actors have their parameters as part of the actor instance
            if(type.getName().startsWith("art_") || aactor instanceof ExternalActor) {
                for(TaggedExpression param : actor.getActualParameters()) {
                    s.print(" " + param.getTag() + "=");
                    //FIXME now we only support strings as parameter input and hence must be literal, so this goes wrong if it is a function call or variable
                    //Usually the constant expression evaluator have reduced it to a literal, but it is not possible for external declared functions etc.
                    s.print(new CBuildExpression(param.getExpression(), cenv).toStr());
                }
            }
            s.println();
        }

        //CONNECT all the ports
        for (Connection c : network.getConnections()) {
            if(c instanceof Point2PointConnection) {
                Point2PointConnection p2p = (Point2PointConnection) c;
                s.print("CONNECT " + Util.marshallQualifiedName(((TypeActor) p2p.getSource().getActor().getType()).getNamespace()) + 
                        "__" + p2p.getSource().getActor().getName() + 
                        "." + p2p.getSource().getName());
                s.println(" " + Util.marshallQualifiedName(((TypeActor) p2p.getTarget().getActor().getType()).getNamespace()) + 
                        "__" + p2p.getTarget().getActor().getName() + 
                        "." + p2p.getTarget().getName() );
            } else {
                System.err.println("CONNECTION not P2P:" + c.toString());
            }
        }
        
        s.println("ENABLE"+actorInstanceStr);
        s.println("JOIN");
        s.close();

    }

    /*
     * Print the example calvin runtime Python setup file
     * corresponding to the top network.
     */
    public void CalvinPythonFile() throws Exception {
        TypeActor elaboratedNetworkType = session.getElaboratedNetwork().getType();
        String nsName = Util.marshallQualifiedName(elaboratedNetworkType.getNamespace());
        String baseName = nsName + "__" + elaboratedNetworkType .getName();

        String file = session.getOutputFolder() + File.separator + baseName + ".py";
        out.println("Writing '" + file + "'");
        Stream s = new Stream(file);

        s.println("# " + baseName);
        s.println("import calvin");
        s.println("import sys");
        s.println("run_test = 0");
        s.println("if len(sys.argv) > 1:");
        s.println("  try:");
        s.println("    run_test = int(sys.argv[1])");
        s.println("  except ValueError:");
        s.println("    run_test = 0");
        s.println("l = calvin.Node(\"localhost\", 9000, True)");
        //s.println("if run_test == 2:");
        //s.println("  k = calvin.Node(\"localhost\", 9001, True)");
        /*
         *  LOAD the actors
         */
        Set<String> artClasses = new HashSet<String>();
        artClasses.clear();
        for (ActorInstance instance : network.getActors()) {
            TypeActor type = ((TypeActor) instance.getType());
            String actorInstanceName = Util.marshallQualifiedName(type.getNamespace()) + "__" + instance.getName();
            String actorClassName = null;
            //The ART actors are implemented in the runtime and no need to load them 
            if(!type.getName().startsWith("art_")) {
                actorClassName = actorInstanceName; 
                //Only new ones are defined
                if(!artClasses.contains(actorClassName)) {
                    s.println("l.load(\"./" + actorClassName+"\")");
                    //s.println("if run_test == 2:");
                    //s.println("  k.load(\"./" + actorClassName+"\")");
                    artClasses.add(actorClassName);
                }
            }
        }
        
        /*
         *  Instantiate (NEW) the actors with
         *  parameters for ART-actors or external
         *  actors.
         */
        String actorInstanceStr = "";
        int actorId = 0;
        for (ActorInstance actor : network.getActors()) {
            TypeActor type = ((TypeActor) actor.getType());
            String actorInstanceName =  Util.marshallQualifiedName(type.getNamespace()) + "__" + actor.getName();
            String actorClassName = null;
            //The ART actors are implemented in the runtime and we use the class name directly instead of instance name 
            if(type.getName().startsWith("art_")) {
                actorClassName = type.getName(); 
            } else {
                actorClassName = actorInstanceName; 
            }
            s.print(actorInstanceName + " = l.new(\"" + actorClassName + "\", \"" + actorInstanceName + "\"");
            String test2Str=("  " +actorInstanceName + "_k = k.new(\"" + actorClassName + "\", \"" + actorInstanceName + "_k\"");
            actorInstanceStr += actorInstanceName +",";
            AbstractActor aactor = null;
            try {
                aactor = ActorDirectory.findActor((TypeActor) actor.getType());
            } catch (DirectoryException x) {
            }
            //For any ART actors set the parameters, all other actors have their parameters as part of the actor instance
            if(type.getName().startsWith("art_") || aactor instanceof ExternalActor) {
                for(TaggedExpression param : actor.getActualParameters()) {
                    s.print(", " + param.getTag() + "=");
                    test2Str += (", " + param.getTag() + "=");
                    //FIXME now we only support strings as parameter input and hence must be literal, so this goes wrong if it is a function call or variable
                    //Usually the constant expression evaluator have reduced it to a literal, but it is not possible for external declared functions etc.
                    s.print(new CBuildExpression(param.getExpression(), cenv).toStr());
                    test2Str += (new CBuildExpression(param.getExpression(), cenv).toStr());
                }
            }
            s.println(")");
            test2Str += ")";
            //s.println("if run_test == 2:");
            //s.println(test2Str);
        }

        //CONNECT all the ports
        for (Connection c : network.getConnections()) {
            if(c instanceof Point2PointConnection) {
                Point2PointConnection p2p = (Point2PointConnection) c;
                s.print(Util.marshallQualifiedName(((TypeActor) p2p.getSource().getActor().getType()).getNamespace()) + 
                        "__" + p2p.getSource().getActor().getName() + 
                        "." + p2p.getSource().getName());
                s.println(" >> " + Util.marshallQualifiedName(((TypeActor) p2p.getTarget().getActor().getType()).getNamespace()) + 
                        "__" + p2p.getTarget().getActor().getName() + 
                        "." + p2p.getTarget().getName() );
            } else {
                System.err.println("CONNECTION not P2P:" + c.toString());
            }
        }
        
        //Trailing comma is python code for do a tuple even if only one element and is otherwise ignored 
        s.println("actors = ("+actorInstanceStr + ")");
        s.println("for actor in actors:");
        s.println("  actor.enable()");
        s.println("if run_test in (1,2):");
        s.println("  for actor in actors:");
        s.println("    actor.disable()");
        s.println("    state = actor.serialize()");
        //s.println("    if run_test == 2:");
        //s.println("      actor.destroy()");
        s.println("    actor.deserialize(state)");
        s.println("    actor.enable()");
        s.close();

    }
}
