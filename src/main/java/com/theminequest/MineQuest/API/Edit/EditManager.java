/*
 * This file is part of MineQuest-API, version 2, Specifications for the MineQuest system.
 * MineQuest-API, version 2 is licensed under GNU Lesser General Public License v3.
 * Copyright (C) 2012 The MineQuest Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
