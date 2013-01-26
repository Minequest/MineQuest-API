package com.theminequest.minequest.api.quest.parser;

import java.util.List;

import com.theminequest.minequest.api.quest.QuestDetails;

/**
 * Indicate that this class can handle Quest file details.
 * @author Robert Xu <robxu9@gmail.com>
 *
 */
public interface PropertyHandler {
	
	/**
	 * Parse the details from this line of the quest file.<br>
	 * If a line contains:<br>
	 * <code>ID:tas1,tas2,tas3:444</code><br>
	 * Then the line is split by <code>:</code>. This line
	 * does NOT contain detail type. (In example above, <code>ID</code>
	 * will be omitted.)
	 * @param quest QuestDetails to manipulate
	 * @param details Details
	 */
	void parseDetails(QuestDetails quest, List<String> details);
	
}