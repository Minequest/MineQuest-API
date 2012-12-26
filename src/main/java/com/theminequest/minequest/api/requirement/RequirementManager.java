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
package com.theminequest.minequest.api.requirement;

import com.theminequest.minequest.api.event.QuestEvent;
import com.theminequest.minequest.api.quest.QuestDetails;


public interface RequirementManager {
	
	/**
	 * Register a requirement with MineQuest. It needs to have a name, such as
	 * NEVERDONE, that the quest file can use. <br>
	 * <b>WARNING: QEvents and classes based off of it must NOT tamper the
	 * constructor. Instead, use {@link QuestEvent#parseDetails(String)} to set
	 * instance variables and conditions.</b> This method explicitly
	 * requests the original constructor and if the event does not have
	 * this constructor, classes will fail to be hooked in entirely.
	 * 
	 * @param reqname
	 *            Event name
	 * @param requirement
	 *            Class of the event (.class)
	 */
	void register(String reqname, Class<? extends QuestRequirement> requirement);

	/**
	 * Construct a QuestRequirement from the following details
	 * @param requirementName Requirement Name
	 * @param ID Requirement ID
	 * @param details Details to setup the object
	 * @return newly created QuestRequirement
	 */
	QuestRequirement construct(String requirementName, int ID, QuestDetails details, String properties);
	
}