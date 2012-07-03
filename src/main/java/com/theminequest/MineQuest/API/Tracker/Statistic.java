package com.theminequest.MineQuest.API.Tracker;

import com.alta189.simplesave.Field;

/**
 * Represents a statistic of a player.
 */


public abstract class Statistic {
	
	@Field
	private String playerName;
	
	public String getPlayerName(){
		return playerName;
	}
	
	public void setPlayerName(String playerName){
		this.playerName = playerName;
	}
	
	public abstract void setup();
	
}
