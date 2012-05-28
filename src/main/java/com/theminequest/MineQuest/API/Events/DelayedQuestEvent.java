package com.theminequest.MineQuest.API.Events;

import com.theminequest.MineQuest.API.Quest.Quest;


public abstract class DelayedQuestEvent extends QuestEvent {
	
	private long starttime;

	public DelayedQuestEvent() {}
	
	@Override
	public void setupProperties(Quest quest, int eventid, String details) {
		super.setupProperties(quest, eventid, details);
	}

	@Override
	public void setUpEvent() {
		starttime = System.currentTimeMillis();
		additionalSetup();
	}
	
	/**
	 * Additional method to allow setup for delayed events.
	 */
	public void additionalSetup(){
		
	}

	/**
	 * Get the delay in milliseconds.
	 * @return delay time in MS, parsed from details.
	 */
	public abstract long getDelay();
	
	/**
	 * Specify additional conditions
	 * @return true if additional conditions are met.
	 */
	public abstract boolean delayedConditions();
	
	@Override
	public final boolean conditions() {
		if (starttime+getDelay()>System.currentTimeMillis())
			return false;
		return delayedConditions();
	}

}
