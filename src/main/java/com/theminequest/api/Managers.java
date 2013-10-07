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
package com.theminequest.api;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.theminequest.api.group.GroupManager;
import com.theminequest.api.quest.QuestManager;
import com.theminequest.api.quest.handler.QuestHandlerManager;
import com.theminequest.api.requirement.RequirementManager;
import com.theminequest.api.statistic.QuestStatisticManager;
import com.theminequest.api.statistic.StatisticManager;
import com.theminequest.api.targeted.TargetManager;

public class Managers {
	
	private static String version = null;
	private static Platform platform = null;
	
	private static GroupManager groupManager = null;
	private static QuestManager questManager = null;
	private static QuestHandlerManager questHandlerManager = null;
	private static StatisticManager statisticManager = null;
	private static QuestStatisticManager questStatisticManager = null;
	private static RequirementManager requirementManager = null;
	private static TargetManager targetManager = null;
	
	public static String getVersion() {
		return version;
	}
	
	public static void setVersion(String version) {
		Managers.version = version;
	}
	
	public static Platform getPlatform() {
		return platform;
	}
	
	public static void setPlatform(Platform platform) {
		Managers.platform = platform;
	}
	
	public static GroupManager getGroupManager() {
		return groupManager;
	}
	
	public static void setGroupManager(GroupManager groupManager) {
		Managers.groupManager = groupManager;
	}
	
	public static QuestManager getQuestManager() {
		return questManager;
	}
	
	public static void setQuestManager(QuestManager questManager) {
		Managers.questManager = questManager;
	}
	
	public static QuestHandlerManager getQuestHandlerManager() {
		return questHandlerManager;
	}

	public static void setQuestHandlerManager(QuestHandlerManager questHandlerManager) {
		Managers.questHandlerManager = questHandlerManager;
	}

	public static StatisticManager getStatisticManager() {
		return statisticManager;
	}
	
	public static void setStatisticManager(StatisticManager statisticManager) {
		Managers.statisticManager = statisticManager;
	}
	
	public static QuestStatisticManager getQuestStatisticManager() {
		return questStatisticManager;
	}
	
	public static void setQuestStatisticManager(QuestStatisticManager questStatisticManager) {
		Managers.questStatisticManager = questStatisticManager;
	}
	
	public static RequirementManager getRequirementManager() {
		return requirementManager;
	}
	
	public static void setRequirementManager(RequirementManager requirementManager) {
		Managers.requirementManager = requirementManager;
	}
	
	public static TargetManager getTargetManager() {
		return targetManager;
	}

	public static void setTargetManager(TargetManager targetManager) {
		Managers.targetManager = targetManager;
	}

	/**
	 * Log using the central MineQuest logger.
	 * (Prefixed with <code>[MineQuest]</code>; to add on component, add prefix
	 * to message.)
	 * 
	 * @param msg
	 *            Message to log with level <code>INFO</code>.
	 */
	public static void log(String msg) {
		log(Level.INFO, msg);
	}
	
	public static void logf(String msg, Object... args) {
		logf(Level.INFO, msg, args);
	}
	
	/**
	 * Log using the central MineQuest logger.
	 * (Prefixed with <code>[MineQuest]</code>; to add on component, add prefix
	 * to message.)
	 * 
	 * @param level
	 *            Level to log with.
	 * @param msg
	 *            Message to log.
	 */
	public static void log(Level level, String msg) {
		Logger.getLogger("MineQuest").log(level, "[MineQuest] " + msg);
	}
	
	public static void logf(Level level, String msg, Object... args) {
		String info = String.format(msg, args);
		Logger.getLogger("MineQuest").log(level, "[MineQuest] " + info);
	}
	
}
