package com.theminequest.MineQuest.API.Tracker;

import com.alta189.simplesave.Field;

/**
 * Represents a quest statistic of a player.
 */


public abstract class QuestStatistic extends Statistic {
	
	@Field
	private String questName;
	
	public String getQuestName(){
		return questName;
	}
	
	public void setQuestName(String questName){
		this.questName = questName;
	}

}
