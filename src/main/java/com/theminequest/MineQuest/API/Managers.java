package com.theminequest.MineQuest.API;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.theminequest.MineQuest.API.Edit.EditManager;
import com.theminequest.MineQuest.API.Events.EventManager;
import com.theminequest.MineQuest.API.Group.GroupManager;
import com.theminequest.MineQuest.API.Group.QuestGroupManager;
import com.theminequest.MineQuest.API.Quest.QuestManager;
import com.theminequest.MineQuest.API.Target.TargetManager;
import com.theminequest.MineQuest.API.Tracker.QuestStatisticManager;
import com.theminequest.MineQuest.API.Tracker.StatisticManager;
import com.theminequest.MineQuest.API.Utils.UtilManager;

public class Managers {
	
	private static JavaPlugin activePlugin = null;
	
	private static EditManager editManager = null;
	private static EventManager eventManager = null;
	private static GroupManager groupManager = null;
	private static QuestGroupManager qGroupManager = null;
	private static QuestManager questManager = null;
	private static StatisticManager statisticManager = null;
	private static QuestStatisticManager qStatisticManager = null;
	private static TargetManager targetManager = null;
	private static UtilManager utilManager = null;
	
	public static JavaPlugin getActivePlugin() {
		return activePlugin;
	}
	public static void setActivePlugin(JavaPlugin activePlugin) {
		Managers.activePlugin = activePlugin;
	}
	
	public static EditManager getEditManager() {
		return editManager;
	}
	public static void setEditManager(EditManager editManager) {
		Managers.editManager = editManager;
	}
	public static EventManager getEventManager() {
		return eventManager;
	}
	public static void setEventManager(EventManager eventManager) {
		Managers.eventManager = eventManager;
	}
	public static GroupManager getGroupManager() {
		return groupManager;
	}
	public static void setGroupManager(GroupManager groupManager) {
		Managers.groupManager = groupManager;
	}
	public static QuestGroupManager getQuestGroupManager(){
		return qGroupManager;
	}
	public static void setQuestGroupManager(QuestGroupManager qGroupManager){
		Managers.qGroupManager = qGroupManager;
	}
	public static QuestManager getQuestManager() {
		return questManager;
	}
	public static void setQuestManager(QuestManager questManager) {
		Managers.questManager = questManager;
	}
	public static StatisticManager getStatisticManager() {
		return statisticManager;
	}
	public static void setStatisticManager(StatisticManager statisticManager) {
		Managers.statisticManager = statisticManager;
	}
	public static QuestStatisticManager getQuestStatisticManager() {
		return qStatisticManager;
	}
	public static void setQuestStatisticManager(QuestStatisticManager qStatisticManager) {
		Managers.qStatisticManager = qStatisticManager;
	}
	public static TargetManager getTargetManager() {
		return targetManager;
	}
	public static void setTargetManager(TargetManager targetManager) {
		Managers.targetManager = targetManager;
	}
	public static UtilManager getUtilManager() {
		return utilManager;
	}
	public static void setUtilManager(UtilManager utilManager) {
		Managers.utilManager = utilManager;
	}
	
	/**
	 * Log using the central MineQuest logger.
	 * (Prefixed with <code>[MineQuest]</code>; to add on component, add prefix to message.)
	 * @param msg Message to log with level <code>INFO</code>.
	 */
	public static void log(String msg) {
		log(Level.INFO, msg);
	}
	
	/**
	 * Log using the central MineQuest logger.
	 * (Prefixed with <code>[MineQuest]</code>; to add on component, add prefix to message.)
	 * @param level Level to log with.
	 * @param msg Message to log.
	 */
	public static void log(Level level, String msg) {
		Logger.getLogger("Minecraft").log(level, "[MineQuest] " + msg);
	}

}
