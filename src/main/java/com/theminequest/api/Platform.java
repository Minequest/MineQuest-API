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

import java.io.File;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import com.theminequest.api.platform.IChatColor;
import com.theminequest.api.platform.IChatStyle;
import com.theminequest.api.platform.MQBlock;
import com.theminequest.api.platform.MQEvent;
import com.theminequest.api.platform.MQInventory;
import com.theminequest.api.platform.MQItemStack;
import com.theminequest.api.platform.MQLocation;
import com.theminequest.api.platform.MQMaterial;
import com.theminequest.api.platform.entity.MQEntity;
import com.theminequest.api.platform.entity.MQPlayer;
import com.theminequest.api.util.PropertiesFile;

public interface Platform {
	
	File getResourceDirectory();
	PropertiesFile getConfigurationFile();
	File getJarFile();
	Object getPlatformObject();
	
	IChatColor chatColor();
	IChatStyle chatStyle();
	
	MQMaterial findMaterial(String material);
	MQMaterial toMaterial(Object platformMaterial);
	
	MQItemStack toItemStack(Object platformItemStack);
	<T> T fromItemStack(MQItemStack stack);
	
	MQInventory toInventory(Object platformInventory);
	
	MQLocation toLocation(Object platformLocation);
	<T> T fromLocation(MQLocation location);
	
	MQEntity getEntity(long entityID);
	MQEntity toEntity(Object platformEntity);
	<T> T fromEntity(MQEntity entity);
	
	MQPlayer getPlayer(String name);
	MQPlayer toPlayer(Object platformPlayer);
	Set<MQPlayer> getPlayers();
	
	Set<String> getWorlds();
	boolean hasWorld(String world);
	void loadWorld(String world, int flags); // bitwise flags ( SOMETHING | OTHER SOMETHING )
	void deloadWorld(String world, boolean save);
	void destroyWorld(String world, boolean areyousure); // WARNING WARNING
	String copyWorld(String originalWorld); // for Dungeons
	
	MQBlock getBlock(MQLocation location);
	<T extends MQEvent> void callEvent(T event);
	
	<T> Future<T> callSyncTask(Callable<T> c);
	int scheduleSyncTask(Runnable task);
	int scheduleSyncTask(Runnable task, long tickDelay);
	int scheduleSyncRepeatingTask(Runnable task, long tickDelay, long tickRepeat);
	int scheduleAsynchronousTask(Runnable task);
	int scheduleAsynchronousTask(Runnable task, long tickDelay);
	int scheduleAsynchronousRepeatingTask(Runnable task, long tickDelay, long tickRepeat);
	boolean hasFinished(int taskID);
	void cancelTask(int taskID);
	
	void callCommand(String command);
	
}
