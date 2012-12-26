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
package com.theminequest.minequest.api.platform.events;

import com.theminequest.minequest.api.CompleteStatus;
import com.theminequest.minequest.api.group.QuestGroup;
import com.theminequest.minequest.api.platform.MQEvent;
import com.theminequest.minequest.api.quest.Quest;

public class QuestCompleteEvent extends MQEvent {
	
	public static final String NAME = "QuestComplete";
	
	private Quest quest;
	private CompleteStatus status;
	private QuestGroup questgroup;

	public QuestCompleteEvent(Quest quest, CompleteStatus c, QuestGroup g) {
		super(NAME);
		this.quest = quest;
		status = c;
		questgroup = g;
	}
	
	public Quest getQuest(){
		return quest;
	}
	
	public CompleteStatus getResult(){
		return status;
	}
	
	public QuestGroup getGroup(){
		return questgroup;
	}

}
