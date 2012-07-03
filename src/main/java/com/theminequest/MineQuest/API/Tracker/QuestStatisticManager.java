package com.theminequest.MineQuest.API.Tracker;

public interface QuestStatisticManager extends StatisticManager {

	/**
	 * Retrieve statistics associated with the player
	 * @param <T> Type to return as (Must extend {@link QuestStatistic}).
	 * @param playerName Player Name to search for
	 * @param questName Quest name to search for
	 * @param tableClazz table in which to search for (represented by class)
	 * @return Player statistic in database (if not found, returns <code>null</code>)
	 */
	<T extends QuestStatistic> T getQuestStatistic(String playerName, String questName, Class<? extends QuestStatistic> tableClazz);

	/**
	 * Create a new QuestStatistic for the specified player for the specified table
	 * @param playerName Player to associate with
	 * @param questName Quest Name to associate with
	 * @param tableClazz Table to insert into
	 * @return New Player Statistic (<i>WARNING: make sure to initialize instance variables</i>)
	 */
	<T extends QuestStatistic> T createStatistic(String playerName, String questName, Class<? extends QuestStatistic> tableClazz);
	
}
