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
/**
 * This file, Edit.java, is part of MineQuest:
 * A full featured and customizable quest/mission system.
 * Copyright (C) 2012 The MineQuest Party
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 **/
package com.theminequest.MineQuest.API.Edit;

import java.io.Serializable;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import com.theminequest.MineQuest.API.Managers;
import com.theminequest.MineQuest.API.Quest.Quest;

public abstract class Edit implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5510249479621079410L;
	private transient Quest quest;
	private int editid;
	private int taskid;
	private Player lastEditor = null;
	
	public Edit(int eid, int tid, String d){
		quest = null;
		editid = eid;
		taskid = tid;
		parseDetails(d);
	}
	
	public void startEdit(Quest quest){
		this.quest = quest;
		Managers.getEditManager().addEditTracking(this);
	}
	
	public void dismantle() {
		Managers.getEditManager().rmEditTracking(this);
	}
	
	/**
	 * Retrieve details about this edit.
	 * @param d Edit details
	 */
	public abstract void parseDetails(String d);
	
	/**
	 * Check to see if editing is allowed with the given block
	 * @param b Block that is being edited
	 * @return true if allowed
	 */
	public abstract boolean allowEdit(Block b, ItemStack i, Player p);
	
	public Quest getQuest(){
		return quest;
	}
	
	public int getEditId(){
		return editid;
	}
	
	public void onBlockPlace(BlockPlaceEvent e){
		if (allowEdit(e.getBlock(), e.getItemInHand(), e.getPlayer())){
			lastEditor = e.getPlayer();
			e.setCancelled(false);
			
			// I'm pretty sure this goes here - jmonk
			getQuest().startTask(taskid);
		}
	}
	
	public void onBlockDamage(BlockDamageEvent e){
		if (allowEdit(e.getBlock(), e.getItemInHand(), e.getPlayer())){
			lastEditor = e.getPlayer();
			e.setCancelled(false);

			// I'm pretty sure this goes here - jmonk
			getQuest().startTask(taskid);
		}
	}
	
	public Player getLastEditor() {
		return lastEditor;
	}

}
