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
package com.theminequest.minequest.api.event;

import java.util.Collection;

import com.theminequest.minequest.api.platform.MQBlock;
import com.theminequest.minequest.api.platform.MQPlayer;
import com.theminequest.minequest.api.quest.Quest;

public interface EventManager {

	void dismantleRunnable();

	/**
	 * Register an event with MineQuest. It needs to have a name, such as
	 * QuestFinishEvent, that the quest file can use. <br>
	 * <b>WARNING: QEvents and classes based off of it must NOT tamper the
	 * constructor. Instead, use {@link QuestEvent#parseDetails(String)} to set
	 * instance variables and conditions.</b> This method explicitly
	 * requests the original constructor and if the event does not have
	 * this constructor, classes will fail to be hooked in entirely.
	 * 
	 * @param eventname
	 *            Event name
	 * @param event
	 *            Class of the event (.class)
	 */
	void addEvent(String eventname,
			Class<? extends QuestEvent> event);

	/**
	 * Retrieve a new instance of an event for use with a quest and task.
	 * 
	 * @param eventname
	 *            Event to use
	 * @param quest
	 *            Quest to attribute to
	 * @param eventid
	 *            EventID for this new event
	 * @param d
	 *            Details for use with {@link QuestEvent#parseDetails(String)}
	 * @return new instance of the event requested
	 */
	QuestEvent constructEvent(String eventname, Quest quest,
			int eventid, String details);
	
	void registerEventListeners(Collection<QuestEvent> events);

	void deregisterEventListener(QuestEvent e);

	void checkAllEvents();

	void onPlayerInteract(MQPlayer player, MQBlock block);

	void onBlockBreak(MQPlayer player, MQBlock block);

}