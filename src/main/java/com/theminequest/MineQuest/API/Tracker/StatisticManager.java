package com.theminequest.MineQuest.API.Tracker;

/**
 * StatisticManager keeps track of
 * player statistics, such as a player's
 * available, pending, completed quests,
 * and other such information.
 * 
 * @since 2.0.0
 * @version 2.0.0
 */
public interface StatisticManager {
	
	/**
	 * Retrieve statistics on a player.
	 * @param name Player name (case-insensitive)
	 * @return statistics
	 */
	PlayerStatistic getPlayerQuests(String name);
	
}
