package com.theminequest.api.platform.entity;

import java.util.UUID;

import com.theminequest.api.platform.MQLocation;

public interface MQEntity {
	
	MQLocation getLocation();
	void teleport(MQLocation location);
	
	void remove();
	long getEntityId();
	UUID getUUID();
	
}
