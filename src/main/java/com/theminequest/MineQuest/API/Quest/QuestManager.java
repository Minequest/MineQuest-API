package com.theminequest.MineQuest.API.Quest;

import org.bukkit.entity.Player;
import java.util.List;

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
	List<String> getListOfDetails();
	Quest getQuest(long currentquest);
	Quest startQuest(QuestDetails d, String ownerName);
	Quest[] getMainWorldQuests(Player player);
	Quest getMainWorldQuest(String playerName, String questName);
	void removeMainWorldQuest(String playerName, String questName);
	
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
