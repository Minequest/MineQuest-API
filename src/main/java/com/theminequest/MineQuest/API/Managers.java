package com.theminequest.MineQuest.API;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.theminequest.MineQuest.API.Edit.EditManager;
import com.theminequest.MineQuest.API.Events.EventManager;
import com.theminequest.MineQuest.API.Group.GroupManager;
import com.theminequest.MineQuest.API.Target.TargetManager;
import com.theminequest.MineQuest.API.Task.TaskManager;

public class Managers {
	
	private static JavaPlugin activePlugin = null;
	
	private static EventManager eventManager = null;
	private static GroupManager groupManager = null;
	private static EditManager editManager = null;
	private static TargetManager targetManager = null;
	private static TaskManager taskManager = null;
	
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
	public static EditManager getEditManager() {
		return editManager;
	}
	public static void setEditManager(EditManager editManager) {
		Managers.editManager = editManager;
	}
	public static TargetManager getTargetManager() {
		return targetManager;
	}
	public static void setTargetManager(TargetManager targetManager) {
		Managers.targetManager = targetManager;
	}
	public static TaskManager getTaskManager() {
		return taskManager;
	}
	public static void setTaskManager(TaskManager taskManager) {
		Managers.taskManager = taskManager;
	}
	
	public static JavaPlugin getActivePlugin() {
		return activePlugin;
	}
	public static void setActivePlugin(JavaPlugin activePlugin) {
		Managers.activePlugin = activePlugin;
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
