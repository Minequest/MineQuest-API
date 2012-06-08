package com.theminequest.MineQuest.API.Quest;

import org.bukkit.entity.Player;

public interface QuestRequirement {
	
	/**
	 * Type of requirement.
	 */
	static enum Type {
		/**
		 * Must have never performed this quest before
		 */
		NEVERDONE,
		/**
		 * Specific player must be in group
		 */
		PLAYER,
		/**
		 * Must have specific item
		 */
		ITEM,
		/**
		 * Must be holding specific item
		 */
		GIVE,
		/**
		 * Must be certain time of day
		 */
		TIME,
		/**
		 * Must be certain weather (sunny, stormy, lightning)
		 */
		WEATHER,
		/**
		 * Must be in certain world
		 */
		WORLD,
		/**
		 * Must have certain permission
		 */
		PERMISSION,
		/**
		 * Must be certain level
		 */
		LEVEL,
		/**
		 * Must fulfill (or fail) certain quests (x) number of times
		 */
		PREREQ,
		/**
		 * Must have certain monies
		 */
		MONEY,
		/**
		 * Group must not be more than a certain size
		 */
		GROUPSIZE;
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
