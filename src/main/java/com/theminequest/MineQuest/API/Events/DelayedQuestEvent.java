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
package com.theminequest.MineQuest.API.Events;

import com.theminequest.MineQuest.API.Quest.Quest;


public abstract class DelayedQuestEvent extends QuestEvent {
	
	private long starttime;

	public DelayedQuestEvent() {}
	
	@Override
	public void setupProperties(Quest quest, int eventid, String details) {
		super.setupProperties(quest, eventid, details);
	}

	@Override
	public void setUpEvent() {
		starttime = System.currentTimeMillis();
		additionalSetup();
	}
	
	/**
	 * Additional method to allow setup for delayed events.
	 */
	public void additionalSetup(){
		
	}

	/**
	 * Get the delay in milliseconds.
	 * @return delay time in MS, parsed from details.
	 */
	public abstract long getDelay();
	
	/**
	 * Specify additional conditions
	 * @return true if additional conditions are met.
	 */
	public abstract boolean delayedConditions();
	
	@Override
	public final boolean conditions() {
		if (starttime+getDelay()>System.currentTimeMillis())
			return false;
		return delayedConditions();
	}

}
