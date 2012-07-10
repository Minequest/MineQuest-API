/*
 * This file is part of MineQuest-API, version 2, Specifications for the MineQuest system.
 * MineQuest-API, version 2 is licensed under GNU Lesser General Public License v3.
 * Copyright (C) 2012 The MineQuest Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.theminequest.MineQuest.API.Utils;

import java.util.regex.Pattern;

public class NumberUtils {

	/**
	 * Check if a string can be parsed correctly. This is taken
	 * straight from the Java API.
	 * @param myString String to parse and retrieve a Double
	 * @return Double value, or null if the string is invalid.
	 */
	public static Double parseDouble(String myString){
		final String Digits     = "(\\p{Digit}+)";
		final String HexDigits  = "(\\p{XDigit}+)";
		// an exponent is 'e' or 'E' followed by an optionally 
		// signed decimal integer.
		final String Exp        = "[eE][+-]?"+Digits;
		final String fpRegex    =
				("[\\x00-\\x20]*"+  // Optional leading "whitespace"
						"[+-]?(" + // Optional sign character
						"NaN|" +           // "NaN" string
						"Infinity|" +      // "Infinity" string

	                 // A decimal floating-point string representing a finite positive
	                 // number without a leading sign has at most five basic pieces:
	                 // Digits . Digits ExponentPart FloatTypeSuffix
	                 // 
	                 // Since this method allows integer-only strings as input
	                 // in addition to strings of floating-point literals, the
	                 // two sub-patterns below are simplifications of the grammar
	                 // productions from the Java Language Specification, 2nd 
	                 // edition, section 3.10.2.

	                 // Digits ._opt Digits_opt ExponentPart_opt FloatTypeSuffix_opt
	                 "((("+Digits+"(\\.)?("+Digits+"?)("+Exp+")?)|"+

	                 // . Digits ExponentPart_opt FloatTypeSuffix_opt
	                 "(\\.("+Digits+")("+Exp+")?)|"+

	           // Hexadecimal strings
	           "((" +
	           // 0[xX] HexDigits ._opt BinaryExponent FloatTypeSuffix_opt
	           "(0[xX]" + HexDigits + "(\\.)?)|" +

	            // 0[xX] HexDigits_opt . HexDigits BinaryExponent FloatTypeSuffix_opt
	            "(0[xX]" + HexDigits + "?(\\.)" + HexDigits + ")" +

	            ")[pP][+-]?" + Digits + "))" +
	            "[fFdD]?))" +
						"[\\x00-\\x20]*");// Optional trailing "whitespace"

		if (Pattern.matches(fpRegex, myString))
			return Double.valueOf(myString); // Will not throw NumberFormatException
		else {
			return null;
		}

	}
	
	/**
	 * Check if a string can be parsed correctly. This is taken
	 * straight from the Java API.
	 * @param myString String to parse and retrieve a Float
	 * @return Float value, or null if the string is invalid.
	 */
	public static Float parseFloat(String myString){
		final String Digits     = "(\\p{Digit}+)";
		final String HexDigits  = "(\\p{XDigit}+)";
		// an exponent is 'e' or 'E' followed by an optionally 
		// signed decimal integer.
		final String Exp        = "[eE][+-]?"+Digits;
		final String fpRegex    =
				("[\\x00-\\x20]*"+  // Optional leading "whitespace"
						"[+-]?(" + // Optional sign character
						"NaN|" +           // "NaN" string
						"Infinity|" +      // "Infinity" string

	                 // A decimal floating-point string representing a finite positive
	                 // number without a leading sign has at most five basic pieces:
	                 // Digits . Digits ExponentPart FloatTypeSuffix
	                 // 
	                 // Since this method allows integer-only strings as input
	                 // in addition to strings of floating-point literals, the
	                 // two sub-patterns below are simplifications of the grammar
	                 // productions from the Java Language Specification, 2nd 
	                 // edition, section 3.10.2.

	                 // Digits ._opt Digits_opt ExponentPart_opt FloatTypeSuffix_opt
	                 "((("+Digits+"(\\.)?("+Digits+"?)("+Exp+")?)|"+

	                 // . Digits ExponentPart_opt FloatTypeSuffix_opt
	                 "(\\.("+Digits+")("+Exp+")?)|"+

	           // Hexadecimal strings
	           "((" +
	           // 0[xX] HexDigits ._opt BinaryExponent FloatTypeSuffix_opt
	           "(0[xX]" + HexDigits + "(\\.)?)|" +

	            // 0[xX] HexDigits_opt . HexDigits BinaryExponent FloatTypeSuffix_opt
	            "(0[xX]" + HexDigits + "?(\\.)" + HexDigits + ")" +

	            ")[pP][+-]?" + Digits + "))" +
	            "[fFdD]?))" +
						"[\\x00-\\x20]*");// Optional trailing "whitespace"

		if (Pattern.matches(fpRegex, myString))
			return Float.valueOf(myString); // Will not throw NumberFormatException
		else {
			return null;
		}

	}

}
