package com.theminequest.MineQuest.API.Task;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public interface TaskManager extends Listener {
	
	@EventHandler
	void onEventComplete(com.theminequest.MineQuest.API.BukkitEvents.EventCompleteEvent e);

}
