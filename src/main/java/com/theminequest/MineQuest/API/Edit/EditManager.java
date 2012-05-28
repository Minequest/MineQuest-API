package com.theminequest.MineQuest.API.Edit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import com.theminequest.MineQuest.API.Managers;
import com.theminequest.MineQuest.API.Group.QuestGroup;
import com.theminequest.MineQuest.API.Group.QuestGroup.QuestStatus;

public class EditManager implements Listener {
	
	private final List<Edit> edits;
	
	public EditManager(){
		Managers.log("[Edit] Starting Manager...");
		edits = Collections.synchronizedList(new ArrayList<Edit>());
	}
	
	public void addEditTracking(Edit e){
		if (!edits.contains(e))
			edits.add(e);
	}
	
	public void rmEditTracking(Edit e){
		if (edits.contains(e))
			edits.remove(e);
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e){
		long id = Managers.getGroupManager().indexOf(e.getPlayer());
		if (id==-1)
			return;
		if (((QuestGroup) Managers.getGroupManager().get(id)).getQuestStatus()!=QuestStatus.INQUEST)
			return;
		e.setCancelled(true);
		for (Edit ed : edits){
			ed.onBlockPlace(e);
			if (!e.isCancelled())
				return;
		}
	}
	
	@EventHandler
	public void onBlockDamage(BlockDamageEvent e){
		long id = Managers.getGroupManager().indexOf(e.getPlayer());
		if (id==-1)
			return;
		if (((QuestGroup) Managers.getGroupManager().get(id)).getQuestStatus()!=QuestStatus.INQUEST)
			return;
		e.setCancelled(true);
		for (Edit ed : edits){
			ed.onBlockDamage(e);
			if (!e.isCancelled())
				return;
		}
	}
}
