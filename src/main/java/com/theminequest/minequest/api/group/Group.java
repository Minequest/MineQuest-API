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
package com.theminequest.minequest.api.group;

import java.util.List;

import com.theminequest.minequest.api.platform.Location;
import com.theminequest.minequest.api.platform.MQPlayer;

public interface Group extends Comparable<Group> {
	
	/**
	 * Retrieve the Group ID.
	 * @return Group ID
	 */
	long getID();
	
	/**
	 * Retrieve the leader of the group.
	 * @return Player that represents Leader
	 */
	MQPlayer getLeader();
	
	/**
	 * Set the leader of the group
	 * @param p Player that is to be made Leader
	 * @throws GroupException if player is not in the group
	 */
	void setLeader(MQPlayer p) throws GroupException;
	
	/**
	 * Return the list of players
	 * @return Player list
	 */
	List<MQPlayer> getMembers();
	
	/**
	 * Add a player to the group
	 * @param p Player to add
	 * @throws GroupException if player already in a group,
	 * the group is currently inside an instanced quest, or
	 * the group is overcapacity.
	 */
	void add(MQPlayer p) throws GroupException;
	
	/**
	 * Remove a player from the group
	 * @param p Player to remove
	 * @throws GroupException if the player is not
	 * in the group
	 */
	void remove(MQPlayer p) throws GroupException;
	
	/**
	 * Check to see if a player is currently in this group
	 * @param p Player to check
	 * @return true if player is in the group
	 */
	boolean contains(MQPlayer p);

	/**
	 * Get the current capacity of this group
	 * @return capacity capacity of the group
	 */
	int getCapacity();
	
	/**
	 * Set the capacity of the group
	 * @param capacity capacity to set to, >0
	 * @throws GroupException if the capacity is less than
	 * the current number of players that are in the group
	 */
	void setCapacity(int capacity) throws GroupException;

	/**
	 * Check to see if PvP between group members is enabled
	 * @return true if PvP between group members is allowed
	 */
	boolean isPVP();
	
	/**
	 * Set the ability to attack players within the group
	 * @param on true if PvP should be allowed
	 */
	void setPVP(boolean on);
	
	/**
	 * Teleport all members to a certain location.
	 * @param l Location to teleport group to
	 */
	void teleportPlayers(Location l);
}
