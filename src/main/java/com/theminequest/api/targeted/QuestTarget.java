package com.theminequest.api.targeted;

import java.io.Serializable;
import java.util.Collection;

import com.theminequest.api.platform.entity.MQPlayer;
import com.theminequest.api.quest.Quest;

public abstract class QuestTarget implements Serializable {
	
	private static final long serialVersionUID = -4933287084272124807L;
	private int targetID;
	
	public final void setupProperties(int targetID, String[] properties) {
		this.targetID = targetID;
		parseDetails(properties);
	}
	
	public final int getID() {
		return targetID;
	}
	
	/**
	 * Initialize the target with the following details
	 * @param details Details
	 */
	public abstract void parseDetails(String[] details);
	
	public abstract Collection<MQPlayer> getPlayers(Quest quest);
	
}
