package com.theminequest.api.quest.event;

import java.util.Collection;
import java.util.Map;

import com.theminequest.api.CompleteStatus;
import com.theminequest.api.platform.entity.MQPlayer;
import com.theminequest.api.quest.QuestDetails;
import com.theminequest.api.target.QuestTarget;

public abstract class TargetedQuestEvent extends DelayedQuestEvent {
	
	private int targetID;
	private long delayMS;
	
	public void setupTarget(int targetID, long delayMS) {
		this.targetID = targetID;
		this.delayMS = delayMS;
	}
	
	@Override
	public long getDelay() {
		return delayMS;
	}

	@Override
	public boolean delayedConditions() {
		return true;
	}
	
	@Override
	public CompleteStatus action() {
		Map<Integer, QuestTarget> targetMap = getQuest().getDetails().getProperty(QuestDetails.QUEST_TARGET);
		if (!targetMap.containsKey(targetID))
			throw new RuntimeException("No such target ID!");
		QuestTarget t = targetMap.get(targetID);
		return targetAction(t.getPlayers(getQuest()));
	}
	
	public abstract CompleteStatus targetAction(Collection<MQPlayer> entities);
	
}
