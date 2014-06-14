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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Stream {

	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	
	//PrintStream printStream = new PrintStream(outputStream);
	
    PrintStream printStream = System.out;
    PrintStream so = System.out;
    boolean debug = false;
	
	private Indent indent = new Indent();

	boolean newline = false;
	
	public class Indent {
		int tabs = 0;
		String tab = "  ";

		public void increase() {
			tabs++;
		}

		public void decrease() {
			tabs--;
			if (tabs < 0 ) tabs = 0;
		}

		public String blanks() {
			String result = "";
			for (int i = 0; i < tabs; i++) {
				result += tab;
			}
			return result;
		}
		
		public void reset() {
			tabs = 0;
		}
	}
	
	public Stream(String outputFile) {
		if(outputFile != null) {
			try {
				Util.checkFolder(outputFile.substring(0, outputFile.lastIndexOf(File.separator)));				
				
				printStream = new PrintStream(outputFile);
			} catch (FileNotFoundException e) {
				System.err.print("Could not create output file " + outputFile);
				e.printStackTrace();
			}
		} else {
			printStream = System.out;
		}
		
	}
    public Stream(String outputFile,boolean debug) {
        this(outputFile);
        if(debug) {
            this.debug = true;
            so.println(">>>||| " + outputFile + " |||<<<");
            so.print(">>>");
        }
    }
	public Stream() {
		printStream = System.out;
	}
	
	public void inc() {
		indent.increase();
	}

	public void inc(int i) {
		for (int j = 0; j<i; j++)
			indent.increase();
	}
	
	public void dec() {
		indent.decrease();
	}

	public void dec(int i) {
		for (int j = 0; j<i; j++)
			indent.decrease();
	}
	
	public void print(String s) {
		if (newline) {
            printStream.println();
            if(debug) so.println();
			printIndent();
			newline = false;
		}		
		printStream.print(s);
        if(debug) so.print(s);
	}

	public void println(String s) {
		if (newline) {
			printStream.println();
            if(debug) so.println();
			printIndent();
		}		
		printStream.print(s);
        if(debug) so.print(s);
		newline = true;
	}
	
	public void println() {
		if (newline) {
			printStream.println();
            if(debug) so.println();
			printIndent();
		}		
		newline = true;
	}

	
	public void printlnLast(String s) {
		printStream.print(s + "\n");
        if(debug) so.print(s + "\n");
		inc();
		printIndent();
	}
	
	public void printlnInc(String s) {		
		println(s);
		inc();
	}
	
	public void printlnDec(String s) {
		dec();
		println(s);
	}
	
	public void printLeft() {
        printStream.print("(");
        if(debug) so.print("(");
	}

	public void printRight() {
		printStream.print(")");
        if(debug) so.print(")");
	}
	
	public void printSpace() {
		printStream.print(" ");
        if(debug) so.print(" ");
	}

	public void printIndent() {
		printStream.print(indent.blanks());
        if(debug) so.print(">>>"+indent.blanks());
	}

	public void printComma() {
		printStream.print(", ");
        if(debug) so.print(", ");
	}

	public void printSemiColonEnd() {
		printStream.print(";\n");
        if(debug) so.print(";\n");
	}
	
	public void printColon() {
		printStream.print(" : ");
        if(debug) so.print(" : ");
	}
	
	public void close() {
		printStream.close();
        if(debug) so.println();
	}

}
