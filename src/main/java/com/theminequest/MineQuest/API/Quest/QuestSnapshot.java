package com.theminequest.MineQuest.API.Quest;

import java.io.Serializable;

/**
 * A snapshot of the main world quest state for
 * storage in an SQL database or other serializable supported
 * solution.
 */
public interface QuestSnapshot extends Comparable<QuestSnapshot>, Serializable {

	QuestDetails getDetails();
	int getLastTaskID();
	String getQuestOwner();
	Quest recreateQuest();
	
}
