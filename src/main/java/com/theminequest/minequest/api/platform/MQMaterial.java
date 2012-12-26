package com.theminequest.minequest.api.platform;

// IMMUTABLE
public interface MQMaterial {
	
	short getData();
	String getDisplayName();
	short getId();
	int getMaxStackSize();
	boolean isMaterial(MQMaterial... materials);
	String getName();
	void setDisplayName(String displayName);
	void setMaxStackSize(int newValue);
	<E> E getUnderlyingObject();
	
}
