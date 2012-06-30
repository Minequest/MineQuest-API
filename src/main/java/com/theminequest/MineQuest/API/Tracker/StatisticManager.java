package com.theminequest.MineQuest.API.Tracker;

import java.util.List;

import com.alta189.simplesave.Database;
import com.alta189.simplesave.Field;
import com.alta189.simplesave.Id;
import com.alta189.simplesave.exceptions.ConnectionException;

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
	 * Retrieve the storage backend for statistics
	 * @return storage backend
	 */
	Database getStorageBackend();
	
	/**
	 * Connect to the server (or disconnect)
	 * @param connect true for connecting
	 * @throws ConnectionException if connection could not be made or discarded
	 */
	void connect(boolean connect) throws ConnectionException;
	
	/**
	 * Retrieve the specified statistic
	 * @param <T> Type to return as (Must extend {@link Statistic}).
	 * @param playerName Player Name to search for
	 * @param tableClazz table in which to search for (represented by class)
	 * @return Player statistic in database (or if not found, a new one)
	 */
	<T extends Statistic> T getStatistic(String playerName, Class<? extends Statistic> tableClazz);
	
	/**
	 * Save the statistic into the database
	 * @param statistic Statistic to save
	 * @param tableClazz Class that represents this statistic
	 */
	<T extends Statistic> void setStatistic(T statistic, Class<? extends Statistic> tableClazz);
	
	/**
	 * Get a list of statistics in the database
	 * @param tableClazz Table to look in
	 * @return array of statistics
	 */
	<T extends Statistic> List<T> getStatisticList(Class<? extends Statistic> tableClazz);
	
	/**
	 * Register a statistic for use with the manager
	 * @param tableClazz class to register
	 */
	void registerStatistic(Class<? extends Statistic> tableClazz);
	
	/**
	 * Represents a statistic of a player.
	 */
	public static abstract class Statistic {
		
		@Field
		private String playerName;
		
		public String getPlayerName(){
			return playerName;
		}
		
		public void setPlayerName(String playerName){
			this.playerName = playerName;
		}
		
		public abstract void setup();
		
	}
	
}
