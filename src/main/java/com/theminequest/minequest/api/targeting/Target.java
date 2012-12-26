package com.theminequest.minequest.api.targeting;

import java.util.Set;

import com.theminequest.minequest.api.quest.Quest;

public abstract class Target {
	
	private Quest quest;
	
	public Target() {}
	
	public final void setupTargetDetail(Quest quest) {
		this.quest = quest;
	}
	
	public final Quest getQuest() {
		return quest;
	}
	
	public abstract Set<Object> getTargets();
	
}
