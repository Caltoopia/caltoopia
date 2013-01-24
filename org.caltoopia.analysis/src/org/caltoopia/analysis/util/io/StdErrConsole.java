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

package org.caltoopia.analysis.util.io;

public class StdErrConsole implements ErrorConsole {
	protected int mNumErrors;
	
	@Override
	public boolean hasErrors() {
		return mNumErrors!=0;
	}
	
	@Override
	public void note(String message) {
		printMessage("Note: "+message);
	}
	
	@Override
	public void warning(String message) {
		printMessage("Warning: "+message);
	}
	
	@Override
	public void warning(Exception ex) {
		warning(exceptionMessage(ex));
	}
	
	@Override
	public void error(String message) {
		printMessage("Error: "+message);
		++mNumErrors;
	}
	
	@Override
	public void error(Exception ex) {
		error(exceptionMessage(ex));
	}
	
	@Override
	public void fatalError(String message) {
		printMessage("Fatal error: "+message);
		++mNumErrors;
		abortAfterFatalError();
	}
	
	@Override
	public void fatalError(Exception ex) {
		fatalError(exceptionMessage(ex));
	}
	
	protected void printMessage(String message) {
		System.err.println(message);
	}
	
	protected void abortAfterFatalError() {
		System.err.flush();
		System.exit(1);
	}
	
	protected String exceptionMessage(Exception exception) {
		String message=exception.getMessage();
		if (message==null)
			message=exception.toString();
		return message;
	}
}