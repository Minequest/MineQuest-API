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
package com.theminequest.MineQuest.API.Documentation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.theminequest.MineQuest.API.Quest.QuestParser;

public class ParserDocumentation {

	/**
	 * Indicates the fields that this {@link QuestParser.QHandler} can parse.
	 * @author Robert Xu <robxu9@gmail.com>
	 *
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public static @interface QHFields {
		
		/**
		 * Get the fields that this handler contains.<br>
		 * For convenience, putting a colon in the string implies that it
		 * will be split between the field name and the field description.
		 * @return Fields
		 */
		String[] value();
		
	}

	/**
	 * Describes the function of this {@link QuestParser.QHandler}.
	 * @author Robert Xu <robxu9@gmail.com>
	 *
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public static @interface QHInfo {
		
		/**
		 * Get the name of this handler that will be used with the parser.<br>
		 * For user-friendliness.
		 * @return Name of the handler, e.g. "Acceptance Text"
		 */
		String name();
		
		/**
		 * Returns the descriptive function of this handler.<br>
		 * Used as a summary of the handler.
		 * @return Description of the Handler, e.g. "Sets the acceptance text"
		 */
		String description();
		
	}
	
}
