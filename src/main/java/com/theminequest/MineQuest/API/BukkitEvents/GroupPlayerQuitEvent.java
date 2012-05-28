package com.theminequest.MineQuest.API.BukkitEvents;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.theminequest.MineQuest.API.Group.Group;

public class GroupPlayerQuitEvent extends Event implements Cancellable {
	
	private static final HandlerList handlers = new HandlerList();
	
	public static HandlerList getHandlerList() {
	    return handlers;
	}
	
	private boolean cancel;
	private Group group;
	private Player player;
	
	public GroupPlayerQuitEvent(Group g, Player p) {
		cancel = false;
		group = g;
		player = p;
	}
	
	public Group getGroup(){
		return group;
	}
	
	public Player getPlayer(){
		return player;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public boolean isCancelled() {
		return cancel;
	}

	public void setCancelled(boolean arg0) {
		cancel = arg0;
	}

}
