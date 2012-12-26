package com.theminequest.minequest.api.platform;

import java.net.InetAddress;

public interface MQPlayer {
	
	void chat(String message);
	String getDisplayName();
	String getName();
	void kick(String message);
	InetAddress getAddress();
	void sendMessage(String message);
	void setDisplayName(String name);
	Location getLocation();
	void teleport(Location location);
}
