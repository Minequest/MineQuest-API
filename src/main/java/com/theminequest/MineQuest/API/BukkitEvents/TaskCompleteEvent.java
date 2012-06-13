/**
 * This file, TaskCompleteEvent.java, is part of MineQuest:
 * A full featured and customizable quest/mission system.
 * Copyright (C) 2012 The MineQuest Party
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 **/
package com.theminequest.MineQuest.API.BukkitEvents;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.theminequest.MineQuest.API.CompleteStatus;
import com.theminequest.MineQuest.API.Quest.Quest;

public class TaskCompleteEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	
	public static HandlerList getHandlerList() {
	    return handlers;
	}
	
	private Quest quest;
	private int id;
	private CompleteStatus result;
	
	public TaskCompleteEvent(Quest quest, int id, CompleteStatus t) {
		this.quest = quest;
		this.id = id;
		this.result = t;
	}
	
	public Quest getQuest(){
		return quest;
	}
	
	public int getID(){
		return id;
	}
	
	public CompleteStatus getResult(){
		return result;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

}
