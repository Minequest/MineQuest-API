package com.theminequest.minequest.api.platform;

public interface MQBlock {
	
	Location getLocation();
	Object getOriginalBlock();
	int getMaterialID();
	MQMaterial getMaterial();
	void setMaterial(MQMaterial material);
	
}
