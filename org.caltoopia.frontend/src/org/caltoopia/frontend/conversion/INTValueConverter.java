/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.caltoopia.frontend.conversion;

import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.conversion.impl.AbstractLexerBasedConverter;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.util.Strings;

/**
 * This class defines a value converter for Long rule.
 * 
 * @author Matthieu Wipliez, Johan Eker
 */
public class INTValueConverter extends AbstractLexerBasedConverter<Long> {

	public INTValueConverter() {
		super();
	}

	@Override
	protected void assertValidValue(Long value) {
		super.assertValidValue(value);
		if (value < 0)
			throw new ValueConverterException(getRuleName()
					+ "-value may not be negative (value:" + value + ").",
					null, null);
	}

	@Override
	protected String toEscapedString(Long value) {
		return value.toString();
	}

	public Long toValue(String string, INode node) {
		if (Strings.isEmpty(string))
			throw new ValueConverterException(
					"Couldn't convert empty string to int.", node, null);
		try {
			if (string.startsWith("0")) {
				return Long.valueOf(string, 8);
			} else {
				return Long.valueOf(string, 10);
			}
		} catch (NumberFormatException e) {
			throw new ValueConverterException("Couldn't convert '" + string
					+ "' to int.", node, e);
		}
	}

}
