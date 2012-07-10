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

import com.theminequest.MineQuest.API.Quest.Quest;
import com.theminequest.MineQuest.API.Quest.QuestDetails;

public interface QuestGroup extends Group {

	/**
	 * Retrieve the active quest
	 * @return Active quest, or <code>null</code> if there is none.
	 */
	Quest getQuest();
	
	/**
	 * Retrieve the quest status with regards to the group.
	 * @return
	 */
	QuestStatus getQuestStatus();
	
	/**
	 * <b>Global Entry Point</b><br>
	 * Start a Quest for this Group
	 * @param d Quest to start
	 * @throws GroupException If the group is already on
	 * a quest or if this quest could not be started by
	 * this group
	 */
	void startQuest(QuestDetails d) throws GroupException;
	
	/**
	 * <b>Global Abandonment Point</b><br>
	 * Drop (and if instanced, leave) the unfinished quest.
	 * @throws GroupException if the group does not have a quest, has a quest
	 * but it is main-world, the group is not inside the quest yet, or the quest
	 * is already finished.
	 */
	void abandonQuest() throws GroupException;
	
	/**
	 * <b>Instanced Start Point</b><br>
	 * If this quest is instanced, enter the instanced quest.
	 * @throws GroupException If the group does not have a quest,
	 * if the group has a main-world quest, or if the group is already
	 * inside the quest.
	 */
	void enterQuest() throws GroupException;
	
	/**
	 * <b>Instanced Exit Point</b><br>
	 * Leave the <b>finished</b> instanced quest.
	 * @throws GroupException if the group does not have a quest, has a quest
	 * but it is main-world, the group is not inside the quest yet, or the quest
	 * is unfinished.
	 */
	void exitQuest() throws GroupException;
	
	/**
	 * <b>Main-World Exit Point</b><br>
	 * Finish the main-world quest.
	 * @throws GroupException if the group does not have a quest, has a quest
	 * but it is instanced, or the quest is unfinished.
	 */
	void finishQuest() throws GroupException;
		
	/**
	 * Define the QuestStatus types
	 * @version 2.0.0
	 * @since 2.0.0
	 */
	public static enum QuestStatus {
		/**
		 * There is no quest that this group is on.
		 */
		NOQUEST,
		/**
		 * The group has an instanced quest, but has not entered it.
		 */
		NOTINQUEST,
		/**
		 * The group has a main world quest that it is participating in.
		 */
		MAINWORLDQUEST,
		/**
		 * The group has an instanced quest that it has entered.
		 */
		INQUEST;
	}

}
