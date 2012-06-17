package com.theminequest.MineQuest.API.Quest;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import com.theminequest.MineQuest.API.BukkitEvents.QuestGivenEvent;
import com.theminequest.MineQuest.API.BukkitEvents.QuestCompleteEvent;
import com.theminequest.MineQuest.API.BukkitEvents.TaskCompleteEvent;

public interface QuestManager extends Listener {
	
	QuestParser getParser();

	void reloadQuests();
	void reloadQuest(String name);
	QuestDetails getDetails(String name);
	Quest getQuest(long currentquest);
	long startQuest(QuestDetails d, String ownerName);
	
	@EventHandler
	void taskCompletion(TaskCompleteEvent e);

	@EventHandler
	void onQuestCompletion(QuestCompleteEvent e);

	@EventHandler
	void onBlockPlaceEvent(BlockPlaceEvent e);

	@EventHandler
	void onBlockDamageEvent(BlockDamageEvent e);

	@EventHandler
	void onPlayerRespawnEvent(PlayerRespawnEvent e);
	
}
