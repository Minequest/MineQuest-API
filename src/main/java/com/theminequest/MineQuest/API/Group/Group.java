package com.theminequest.MineQuest.API.Group;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

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
	Player getLeader();
	
	/**
	 * Set the leader of the group
	 * @param p Player that is to be made Leader
	 * @throws GroupException if player is not in the group
	 */
	void setLeader(Player p) throws GroupException;
	
	/**
	 * Return the list of players
	 * @return Player list
	 */
	List<Player> getMembers();
	
	/**
	 * Add a player to the group
	 * @param p Player to add
	 * @throws GroupException if player already in a group,
	 * the group is currently inside an instanced quest, or
	 * the group is overcapacity.
	 */
	void add(Player p) throws GroupException;
	
	/**
	 * Remove a player from the group
	 * @param p Player to remove
	 * @throws GroupException if the player is not
	 * in the group
	 */
	void remove(Player p) throws GroupException;
	
	/**
	 * Check to see if a player is currently in this group
	 * @param p Player to check
	 * @return true if player is in the group
	 */
	boolean contains(Player p);

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
