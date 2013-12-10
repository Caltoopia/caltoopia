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

package org.caltoopia.analysis.util.cline;

public class BasicCommandLineParser {

	static final int NO_ERRORS=0;
	static final int ERROR=1;
	static final int FATAL_ERROR=2;
	
	private int mErrorCode;
	
	public int parseCommandLine(String[] args) {
		int i=0;
		
		while (i<args.length) {
			i=parseCommandLine(args,i);
		}
		return mErrorCode;
	}

	protected boolean parseShortOption(char option, String arg) {
		error("Unknown command-line option: -"+option);
		return false;
	}

	protected boolean parseLongOption(String option, String arg) {
		error("Unknown command-line option: "+option);
		return false;
	}

	protected void parseFileName(String fileName) {
		error("Unexpected argument on command-line: "+fileName);
	}


	protected void setErrorCode(int errorCode) {
		mErrorCode=Math.max(errorCode, mErrorCode);
	}
	
	protected void fatalError(String message) {
		setErrorCode(FATAL_ERROR);
		System.err.println("fatal error: "+message);
	}
	
	protected void error(String message) {
		setErrorCode(ERROR);
		System.err.println("error: "+message);
	}
	
	protected void warning(String message) {
		System.err.println("warning: "+message);
	}
	
	private int parseCommandLine(String[] args, int i) {
		if (args[i].charAt(0)=='-') {
			if (args[i].charAt(1)=='-')
				i += parseLongOption(args,i);
			else
				i += parseShortOption(args,i);
		}
		else {
			parseFileName(args[i]);
			i++;
		}
		return i;
	}
	
	private int parseLongOption(String[] args, int i) {
		String option=args[i];
		int pos=option.indexOf('=');
		if (pos>2) {
			String arg=option.substring(pos+1);
			option=option.substring(2,pos);
			if (!parseLongOption(option,arg)) {
				error("Unexpected argument on command-line: "+arg);
			}
			return i+1;
		}
		else {
			option=option.substring(2);
			String arg=(i+1<args.length)? args[i+1] : null;
			return (parseLongOption(option,arg))? i+2 : i+1;
		}
	}
	
	
	private int parseShortOption(String[] args, int i) {
		String options=args[i];
		int pos=1;
		
		// Process multiple options: -abcd or option plus argument -n3
		while (pos+1<options.length()) {
			char option=options.charAt(pos);
			String rest=options.substring(pos+1);
			if (parseShortOption(option,rest)) {
				// used rest as well
				return i+1;
			}
			else {
				pos++;
			}
		}
		
		char option=(pos<options.length())? options.charAt(pos) : ' ';
		String arg=(i+1<args.length)? args[i+1] : null;
		return (parseShortOption(option,arg))? i+2 : i+1;
	}	
}
