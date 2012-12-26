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
package com.theminequest.minequest.api;

import java.util.Set;

import com.theminequest.minequest.api.platform.ChatColor;
import com.theminequest.minequest.api.platform.ChatStyle;
import com.theminequest.minequest.api.platform.Location;
import com.theminequest.minequest.api.platform.MQBlock;
import com.theminequest.minequest.api.platform.MQEvent;
import com.theminequest.minequest.api.platform.MQInventory;
import com.theminequest.minequest.api.platform.MQItemStack;
import com.theminequest.minequest.api.platform.MQMaterial;
import com.theminequest.minequest.api.platform.MQPlayer;

public interface Platform {
	
	ChatColor chatColor();
	ChatStyle chatStyle();
	MQMaterial findMaterial(String material);
	MQMaterial findMaterial(int id);
	MQMaterial toMaterial(Object platformMaterial);
	MQItemStack toItemStack(MQMaterial material);
	MQItemStack toItemStack(Object platformItemStack);
	MQInventory toInventory(Object platformInventory);
	MQPlayer getPlayer(String name);
	Set<MQPlayer> getPlayers();
	Set<String> getWorlds();
	MQBlock getBlock(Location location);
	void callEvent(MQEvent event);
	
	int scheduleSyncTask(Runnable task);
	int scheduleSyncTask(Runnable task, long tickDelay);
	int scheduleSyncTask(Runnable task, long tickDelay, long tickRepeat);
	int scheduleASyncTask(Runnable task);
	int scheduleASyncTask(Runnable task, long tickDelay);
	int scheduleASyncTask(Runnable task, long tickDelay, long tickRepeat);
	boolean hasFinished(int taskID);
	boolean cancelTask(int taskID);
	
}
