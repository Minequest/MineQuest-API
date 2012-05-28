package com.theminequest.MineQuest.API.Utils;

import org.bukkit.entity.EntityType;

public class MobUtils {
	
	public static EntityType getEntityType(String s){
		return EntityType.fromName(s.toUpperCase());
	}

}
