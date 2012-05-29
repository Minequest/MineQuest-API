package com.theminequest.MineQuest.API.Events;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.LivingEntity;

import com.theminequest.MineQuest.API.Managers;
import com.theminequest.MineQuest.API.Quest.Quest;
import com.theminequest.MineQuest.API.Quest.QuestDetails;
import com.theminequest.MineQuest.API.Target.TargetDetails;

public abstract class TargetQuestEvent extends DelayedQuestEvent {

	public TargetQuestEvent() {}

	@Override
	public long getDelay() {
		return 0;
	}
	
	/**
	 * Some events are dual normal/targeted events.
	 * Figure out if getTargets() should check.
	 * @return true if event is targeted
	 */
	public abstract boolean enableTargets();
	
	/**
	 * Retrieve the target ID.
	 * @return target ID
	 */
	public abstract int getTargetId();
	
	/**
	 * Retrieve the targets with this event.
	 * @return list of LivingEntites (empty if disabled, or null if no associated quest)
	 */
	public List<LivingEntity> getTargets(){
		if (!enableTargets())
			return new ArrayList<LivingEntity>();
		Quest quest = getQuest();
		if (quest==null)
			return null;
		@SuppressWarnings("unchecked")
		TargetDetails details = ((Map<Integer,TargetDetails>) getQuest().getDetails().getProperty(QuestDetails.QUEST_TARGETS)).get(getTargetId());
		return Managers.getTargetManager().processTargetDetails(quest,details);
	}

}
