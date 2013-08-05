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
package com.theminequest.api.requirement;

import java.io.Serializable;

import com.theminequest.api.platform.MQPlayer;
import com.theminequest.api.quest.QuestDetails;

public abstract class QuestRequirement implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4622785636175059480L;
	private int reqID;
	private QuestDetails details;
	
	public final void setupProperties(int reqID, QuestDetails details, String properties) {
		this.reqID = reqID;
		this.details = details;
		parseDetails(properties.split(":"));
	}
	
	public final QuestDetails getDetails() {
		return details;
	}
	
	public final int getID() {
		return reqID;
	}
	
	/**
	 * Initialize the QuestRequirement with the following details
	 * @param details Details
	 */
	public abstract void parseDetails(String[] details);
	
	/**
	 * Check if the requirement is satisfied for the player.
	 * @param player Player to check
	 * @return true if requirement is satisfied
	 */
	public abstract boolean isSatisfied(MQPlayer player);
	
}
