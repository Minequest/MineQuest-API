package com.theminequest.MineQuest.API.Group;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.theminequest.MineQuest.API.ManagerException;
import com.theminequest.MineQuest.API.BukkitEvents.QuestCompleteEvent;
import com.theminequest.MineQuest.API.Quest.Quest;

public interface QuestGroupManager extends GroupManager {

	QuestGroup createNewGroup(Player leader);
	QuestGroup createNewGroup(List<Player> members) throws ManagerException;
	QuestGroup get(long id);
	QuestGroup get(Quest activeQuest);
	QuestGroup get(Player player);
	long indexOf(Quest activeQuest);
	void onPlayerQuit(PlayerQuitEvent e);
	void onPlayerKick(PlayerKickEvent e);
	void onQuestCompleteEvent(QuestCompleteEvent e);
	
}
