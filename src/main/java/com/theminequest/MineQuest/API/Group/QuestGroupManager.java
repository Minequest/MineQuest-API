/*
 * This file is part of MineQuest-API, version 2, Specifications for the MineQuest system.
 * MineQuest-API, version 2 is licensed under GNU Lesser General Public License v3.
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
