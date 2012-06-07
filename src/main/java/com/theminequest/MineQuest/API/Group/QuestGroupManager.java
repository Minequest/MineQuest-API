package com.theminequest.MineQuest.API.Group;

import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.theminequest.MineQuest.API.BukkitEvents.QuestCompleteEvent;
import com.theminequest.MineQuest.API.Quest.Quest;

public interface QuestGroupManager extends GroupManager {

	QuestGroup get(Quest activeQuest);
	long indexOf(Quest activeQuest);
	void onPlayerQuit(PlayerQuitEvent e);
	void onPlayerKick(PlayerKickEvent e);
	void onQuestCompleteEvent(QuestCompleteEvent e);
	
}
