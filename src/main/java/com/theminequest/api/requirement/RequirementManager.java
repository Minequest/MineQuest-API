/*
 * This file is part of MineQuest-API, version 3, Specifications for the
 * MineQuest system.
 * MineQuest-API, version 3 is licensed under GNU Lesser General Public License
 * v3.
 * Copyright (C) 2012 The MineQuest Team
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.theminequest.api.requirement;


public interface RequirementManager {
	
	/**
	 * Register a requirement with MineQuest. It needs to have a name, such as
	 * NEVERDONE, that the quest file can use. <br>
	 * 
	 * @param reqname
	 *            Event name
	 * @param requirement
	 *            Class of the event (.class)
	 */
	void register(String reqname, Class<? extends QuestRequirement> requirement);
	
	/**
	 * Construct a QuestRequirement from the following details
	 * 
	 * @param requirementName Requirement Name
	 * @param ID Requirement ID
	 * @return newly created QuestRequirement
	 */
	QuestRequirement construct(String requirementName, int ID, String[] properties);
	
}