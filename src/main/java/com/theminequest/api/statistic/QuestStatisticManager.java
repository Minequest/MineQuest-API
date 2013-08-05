/*
 * This file is part of MineQuest-API, version 3, Specifications for the MineQuest system.
 * MineQuest-API, version 3 is licensed under GNU Lesser General Public License v3.
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
package com.theminequest.api.statistic;

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
