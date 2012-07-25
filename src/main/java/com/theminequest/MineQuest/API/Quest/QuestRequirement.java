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
package com.theminequest.MineQuest.API.Quest;

import java.io.Serializable;

import org.bukkit.entity.Player;

public interface QuestRequirement extends Serializable {
	
	/**
	 * Type of requirement.
	 */
	static enum Type {
		/**
		 * Must have never performed this quest before.<br>
		 * No details accompany this type.
		 */
		NEVERDONE,
		/**
		 * Specific player must be in group.<br>
		 * Details: comma-seperated list of player names
		 */
		PLAYER,
		/**
		 * Must have specific item.<br>
		 * Details: item, quantity, and data value<br>
		 * Example: 64:5:3 would require 64 of WOODEN_PLANKS
		 * with data id 3, while 64:1:0 would require 64 of STONE.
		 */
		ITEM,
		/**
		 * Must be holding specific item<br>
		 * Details: itemid that should be holding<br>
		 * Example: 1 to hold STONE
		 */
		GIVE,
		/**
		 * Must be certain time of day.<br>
		 * Details: A time that can be easily recognized
		 * by the parser. Need to test? Try using CommandBook's
		 * time command with the text you want to put in.
		 */
		TIME,
		/**
		 * Must be certain weather (sunny, stormy, lightning)<br>
		 * Details: state of weather.
		 */
		WEATHER,
		/**
		 * Must be in certain world.<br>
		 * Details: world name
		 */
		WORLD,
		/**
		 * Must have certain permissions<br>
		 * Details: permission node
		 */
		PERMISSION,
		/**
		 * Must be below certain level<br>
		 * Details: level
		 */
		BELOWLEVEL,
		/**
		 * Must be above ceratin level<br>
		 * Details: level
		 */
		ABOVELEVEL,
		/**
		 * Must fulfill (or fail) certain quests (x) number of times<br>
		 * Details: Success or failure followed by quest name and number of times,
		 * comma-seperated.<br>
		 * Example: S,successfulquest would require that quest
		 * 'successfulquest' be completed successfully, while F,failedquest
		 * requires that 'failedquest' be failed.
		 */
		PREREQ,
		/**
		 * Must have at least a certain amount of money<br>
		 * Details: amount of money
		 */
		MONEY,
		/**
		 * Group must not be more than a certain size<br>
		 * Details: number of members
		 */
		GROUPSIZE,
		/**
		 * Player may only get/start quest after certain period of time
		 * after last completing the quest.<br>
		 * Details: time in milliseconds after the last quest attempt was finished.
		 */
		DATE;
	}
	
	/**
	 * Retrieve the type of requirement this is.
	 * @return Requirement Type
	 */
	Type getType();
	
	/**
	 * Retrieve the details associated with this type.
	 * @return details
	 */
	String getDetails();
	
	/**
	 * Retrieve the quest associated with this requirement
	 * @return quest
	 */
	QuestDetails getQuest();
	
	/**
	 * Check to see if this requirement is satisfied by the player.
	 * @param player Player to check
	 * @return
	 */
	boolean isSatisfied(Player player);
	
}
