package com.theminequest.MineQuest.API.Quest;

import org.bukkit.entity.Player;

public interface QuestRequirement {
	
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
		 * Details: comma-seperated list of items<br>
		 * Example: 64_1,64_5.3 would require 64 of STONE and
		 * 64 of WOODEN_PLANKS with data id 3.
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
		 * Must be certain level<br>
		 * Details: level
		 */
		LEVEL,
		/**
		 * Must fulfill (or fail) certain quests (x) number of times<br>
		 * Details: Success or failure followed by quest name and number of times,
		 * comma-seperated.<br>
		 * Example: S,successfulquest,1:F,failedquest,0 would require that quest
		 * 'successfulquest' be completed at least one time and that 'failedquest'
		 * never be failed.
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
