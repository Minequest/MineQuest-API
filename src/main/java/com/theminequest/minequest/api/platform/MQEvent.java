package com.theminequest.minequest.api.platform;

public abstract class MQEvent {
	
	private final String eventName;
	
	public MQEvent(String eventName) {
		this.eventName = eventName;
	}
	
	public final String getName() {
		if (eventName == null)
			return getClass().getSimpleName();
		
		return eventName;
	}
	
}
