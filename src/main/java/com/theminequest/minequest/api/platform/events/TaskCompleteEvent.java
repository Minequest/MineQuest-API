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
package com.theminequest.minequest.api.platform.events;

import com.theminequest.minequest.api.CompleteStatus;
import com.theminequest.minequest.api.platform.MQEvent;
import com.theminequest.minequest.api.quest.Quest;

public class TaskCompleteEvent extends MQEvent {
	
	public static final String NAME = "TaskComplete";
	
	private Quest quest;
	private int id;
	private CompleteStatus result;
	
	public TaskCompleteEvent(Quest quest, int id, CompleteStatus t) {
		super(NAME);
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

}
