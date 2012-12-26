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
