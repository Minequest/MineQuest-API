package com.theminequest.MineQuest.API.Tracker;

public interface PlayerStatistic {
	
	String[] getAvailableQuests();
	String[] getAcceptedQuests();
	String[] getCompletedQuests();
	void addAvailableQuest(String questName);
	void removeAvailableQuest(String questName);
	void addAcceptedQuest(String questName);
	void removeAcceptedQuest(String questName);
	void addCompletedQuest(String questName);
	void removeCompletedQuest(String questName);

}
