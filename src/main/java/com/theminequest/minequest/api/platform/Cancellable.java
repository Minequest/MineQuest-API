package com.theminequest.minequest.api.platform;

public interface Cancellable {
	
	boolean isCancelled();
	void setCancelled(boolean cancelled);
	
}
