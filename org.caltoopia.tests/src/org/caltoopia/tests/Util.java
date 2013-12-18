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

package org.caltoopia.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;

import org.caltoopia.cli.CompilationSession;

public class Util {
	
	public static String getRootDir() {
		URL pathToClassFiles = Util.class.getProtectionDomain().getCodeSource().getLocation();
		// System.out.println("Path to class files dir = " + pathToClassFiles);
		int i = pathToClassFiles.getPath().indexOf("org.caltoopia.tests");
		String pathToRootDir = pathToClassFiles.getPath().substring(0, i);
		// System.out.println("Root dir = " + pathToRootDir);
		return pathToRootDir;
	}

	public static String getCalAppsDir() {
		return getRootDir() + "org.caltoopia.tests/cal-src/";
	}
	
	public static String getOutputDir() {
		return getRootDir() + "../caltoopia-output/";
	}	
	
    public static String getRuntimeDir() {
        Map<String, String> env = System.getenv();
        if(env.containsKey("CALTOOPIA_RUNTIME_HOME")) {
            return env.get("CALTOOPIA_RUNTIME_HOME");
        } else {
            return getRootDir() + "snow_actors_rts";
        }
    }

    public static String getCalvinRuntimeDir() {
        Map<String, String> env = System.getenv();
        if(env.containsKey("CALTOOPIA_CALVIN_RUNTIME_HOME")) {
            return env.get("CALTOOPIA_CALVIN_RUNTIME_HOME");
        } else {
            return null;
        }
    }

	public static void removeFileDir(File item) {
		if(item!=null) {
			if(item.isDirectory()) {
				File files[] = item.listFiles();
				if(files!=null) {
					for(File f:files) {
						removeFileDir(f);
					}
				}
				item.delete();
			} else {
				item.delete();
			}
		}
	}
	
	public static void clear(CompilationSession session) {
		File files[] = new File(session.getOutputFolder()).listFiles();
		if(files!=null) {
			for(File f:files){
				removeFileDir(f);				
			}
		}
	}

	public static void build(CompilationSession session) {
		try {				
		    List<String> command = new ArrayList<String>();
		    String executable = "make";
		    command.add(executable);
		    ProcessBuilder builder = new ProcessBuilder(command);		
		    builder.directory(new File(session.getOutputFolder()));
		    
		    final Process process = builder.start();
		    
		    InputStream is = process.getInputStream();
		    InputStreamReader isr = new InputStreamReader(is);
		    BufferedReader br = new BufferedReader(isr);
		    String line;
		    while ((line = br.readLine()) != null) {
		      session.getOutputStream().println(line);
		    }
		    session.getOutputStream().println("Build completed!");								
		} catch(Exception x){
			session.getOutputStream().print("Failed to build binary! (" + x.getMessage() + ")");
			fail("Failed to build binary! (" + x.getMessage() + ")");
		}
	}
	
	public static void run(CompilationSession session) {
		try {				
		    List<String> command = new ArrayList<String>();
		    String executable = session.getOutputFolder() + File.separator + session.getElaboratedNetwork().getType().getName();
		    command.add(executable);
		    ProcessBuilder builder = new ProcessBuilder(command);		
		    if (!session.getWorkingDirectory().isEmpty()) { 
		    	builder.directory(new File(session.getWorkingDirectory()));
		    } else {
		    	builder.directory(new File(session.getOutputFolder()));
		    }
		    if (!session.getRunOptions().isEmpty()) {
		    	command.add(session.getRunOptions());
		    }
		    
		    final Process process = builder.start();
		    
		    InputStream is = process.getInputStream();
		    InputStreamReader isr = new InputStreamReader(is);
		    BufferedReader br = new BufferedReader(isr);
		    String line;
		    while ((line = br.readLine()) != null) {
		      session.getOutputStream().println(line);
		    }
		    //Any error outputs?
		    InputStream es = process.getErrorStream();
		    InputStreamReader esr = new InputStreamReader(es);
		    BufferedReader ebr = new BufferedReader(esr);
		    while ((line = ebr.readLine()) != null) {
		      session.getOutputStream().println(line);
		    }
		    process.waitFor();
		    session.getOutputStream().println("Program terminated with " + process.exitValue() + " as exit value!");
		    if(process.exitValue()>0) {
		    	throw new Exception("exit code " + process.exitValue());
		    }
		} catch(Exception x){
			session.getOutputStream().print("Failed to execute binary! (" + x.getMessage() + ")");
			fail("Failed to execute binary! (" + x.getMessage() + ")");
		}
	}
	
	public static boolean goldCheck(CompilationSession session, String goldFile, String outFile) {
		byte[] dataGold = new byte[8192];
		byte[] dataOut = new byte[8192];
		FileInputStream isGold=null;
		FileInputStream isOut=null;
		boolean match=false;
		try {
			isGold = new FileInputStream(goldFile);
			isOut = new FileInputStream(outFile);
			int bytesGold;
			int bytesOut;
			do {
				bytesGold = isGold.read(dataGold);
				bytesOut = isOut.read(dataOut);
				match = ((bytesGold == bytesOut) && Arrays.equals(dataGold,dataOut));
			} while (match && (bytesGold > -1));
		} catch (Exception e) {
			match = false;
		}
		try {isGold.close();} catch (Exception ee) {}
		try {isOut.close();} catch (Exception ee) {}
		if(session!=null) {
			if(match) {
				session.getOutputStream().println("Output matched gold vector!");
			} else {
				session.getOutputStream().println("Failed to match gold vector:\n" + goldFile +"\n with output vector:\n" + outFile);
			}
		}
		return match;
	}
}
