package com.theminequest.MineQuest.API.Events;

public interface UserQuestEvent {
	
	/**
	 * Describe the event to the player; e.g.,
	 * where the player has to go (AreaEvent)
	 * @return one-line String description.
	 */
	String getDescription();

}
