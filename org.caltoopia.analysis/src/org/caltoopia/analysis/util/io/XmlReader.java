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

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XmlReader {

	private ErrorConsole mErrorConsole;
	
	public XmlReader() {
		mErrorConsole=new StdErrConsole();
	}
	
	public XmlReader(ErrorConsole errorConsole) {
		mErrorConsole=errorConsole;
	}
	
	public Document readDocument(File file) {
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			return builder.parse(file);
			
		} catch (ParserConfigurationException parserException) {
			fatalError(parserException);	
		} catch (Exception exception) {
			fatalError(exception);
		} 

		return null;
	}
	
	protected ErrorConsole getErrorConsole() {
		return mErrorConsole;
	}
	
	protected boolean isTag(Node node, String tagName) {
		return node!=null && node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals(tagName);
	}
	
	protected String getRequiredAttribute(Element element, String attributeName) {
		String attribute=element.getAttribute(attributeName);
		
		if (attribute==null)
			error("Element <"+element.getLocalName()+"> requires attribute \""+attributeName+"\"");
		return attribute;
	}
	
	protected Long getLongAttribute(Element element, String attributeName) {
		return longAttribute(element.getAttribute(attributeName), attributeName);
	}

	protected Long getLongAttribute(Element element, String attributeName, long min, long max) {
		return longAttribute(element.getAttribute(attributeName), attributeName, min, max);
	}
	
	protected Long getRequiredLongAttribute(Element element, String attributeName, long min, long max) {
		return longAttribute(getRequiredAttribute(element,attributeName), attributeName, min, max);
	}
	
	protected Long getRequiredLongAttribute(Element element, String attributeName) {
		return longAttribute(getRequiredAttribute(element,attributeName), attributeName);
	}
	
	protected Integer getIntegerAttribute(Element element, String attributeName) {
		return intAttribute(element.getAttribute(attributeName), attributeName);
	}
	
	protected Integer getIntegerAttribute(Element element, String attributeName, int min, int max) {
		return intAttribute(element.getAttribute(attributeName), attributeName, min, max);
	}
	
	protected Integer getRequiredIntegerAttribute(Element element, String attributeName) {
		return intAttribute(getRequiredAttribute(element,attributeName), attributeName);
	}
	
	protected Integer getRequiredIntegerAttribute(Element element, String attributeName, int min, int max) {
		return intAttribute(getRequiredAttribute(element,attributeName), attributeName, min, max);
	}
	
	private Long longAttribute(String value, String attributeName) {
		if (value!=null && !value.isEmpty()) {
			Long result=null;
			try {
				result=Long.valueOf(value);
			} catch (NumberFormatException ex) {
				error("Illegal number format: "+attributeName+"=\""+value+"\"");
			}
			return result;
		}
		else
			return null;
	}

	private Long longAttribute(String s, String attributeName, long min, long max) {
		Long l=longAttribute(s,attributeName);
		if (l!=null) {
			if (l>=min && l<=max)
				return l;
			else {
				error("Number out of range: "+attributeName+"=\""+attributeName+"\"");
			}	
		}
		return null;
	}
	
	private Integer intAttribute(String s, String attributeName, int min, int max) {
		Long l=longAttribute(s,attributeName,min,max);
		return (l!=null)? l.intValue() : null;
	}

	private Integer intAttribute(String s, String attributeName) {
		return intAttribute(s,attributeName,Integer.MIN_VALUE,Integer.MAX_VALUE);
	}
	
	protected void note(String message) {
		if (mErrorConsole!=null) {
			mErrorConsole.note(message);
		}
	}
	
	protected void warning(String message) {
		if (mErrorConsole!=null) {
			mErrorConsole.warning(message);
		}
	}
	
	protected void warning(Exception ex) {
		if (mErrorConsole!=null) {
			mErrorConsole.warning(ex);
		}
	}
	
	protected void error(String message) {
		if (mErrorConsole!=null) {
			mErrorConsole.error(message);
		}
	}
	
	protected void error(Exception ex) {
		if (mErrorConsole!=null) {
			mErrorConsole.error(ex);
		}
	}
	
	protected void fatalError(String message) {
		if (mErrorConsole!=null) {
			mErrorConsole.fatalError(message);
		}
	}

	protected void fatalError(Exception ex)  {
		if (mErrorConsole!=null) {
			mErrorConsole.fatalError(ex);
		}
	}	
}