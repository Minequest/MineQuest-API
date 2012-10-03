package com.theminequest.MineQuest.API.Requirements;

import java.io.Serializable;

import org.bukkit.entity.Player;

import com.theminequest.MineQuest.API.Quest.QuestDetails;

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
	public abstract boolean isSatisfied(Player player);
	
}
