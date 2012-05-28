package com.theminequest.MineQuest.API.Target;

import java.util.List;

import org.bukkit.entity.LivingEntity;

import com.theminequest.MineQuest.API.Quest.Quest;

public interface TargetManager {
	
	List<LivingEntity> processTargetDetails(Quest quest, TargetDetails details);

}
