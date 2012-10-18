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
