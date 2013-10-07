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
package com.theminequest.api.targeted;


public interface TargetManager {
	
	/**
	 * Register a target with MineQuest. It needs to have a name, such as
	 * NEVERDONE, that the quest file can use. <br>
	 * 
	 * @param reqname
	 *            Event name
	 * @param requirement
	 *            Class of the event (.class)
	 */
	void register(String targetname, Class<? extends QuestTarget> target);
	
	/**
	 * Construct a QuestTarget from the following details
	 * 
	 * @param targetName Target Name
	 * @param ID Target ID
	 * @return newly created QuestTarget
	 */
	QuestTarget construct(String targetName, int ID, String[] properties);
	
}