/*
 * This file is part of MineQuest-API, version 3, Specifications for the MineQuest system.
 * MineQuest-API, version 3 is licensed under GNU Lesser General Public License v3.
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
package com.theminequest.minequest.api.edit;

import java.io.Serializable;

import com.theminequest.minequest.api.Managers;
import com.theminequest.minequest.api.platform.MQBlock;
import com.theminequest.minequest.api.platform.MQItemStack;
import com.theminequest.minequest.api.platform.MQPlayer;
import com.theminequest.minequest.api.quest.Quest;

public abstract class Edit implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5510249479621079410L;
	private transient Quest quest;
	private int editid;
	private int taskid;
	private MQPlayer lastEditor = null;
	
	public Edit(int eid, int tid, String d) {
		quest = null;
		editid = eid;
		taskid = tid;
		parseDetails(d);
	}
	
	public void startEdit(Quest quest) {
		this.quest = quest;
		Managers.getEditManager().addEditTracking(this);
	}
	
	public void dismantle() {
		Managers.getEditManager().rmEditTracking(this);
	}
	
	/**
	 * Retrieve details about this edit.
	 * 
	 * @param d
	 *            Edit details
	 */
	public abstract void parseDetails(String d);
	
	/**
	 * Check to see if editing is allowed with the given block
	 * 
	 * @param b
	 *            Block that is being edited
	 * @return true if allowed
	 */
	public abstract boolean allowEdit(MQBlock b, MQItemStack i, MQPlayer p);
	
	public Quest getQuest() {
		return quest;
	}
	
	public int getEditId() {
		return editid;
	}
	
	public boolean onBlockPlace(MQBlock block, MQItemStack inHand, MQPlayer player) {
		if (allowEdit(block, inHand, player)) {
			lastEditor = player;
			getQuest().startTask(taskid);
			return true;
		}
		return false;
	}
	
	public boolean onBlockDamage(MQBlock block, MQItemStack inHand, MQPlayer player) {
		if (allowEdit(block, inHand, player)) {
			lastEditor = player;
			getQuest().startTask(taskid);
			return true;
		}
		return false;
	}
	
	public MQPlayer getLastEditor() {
		return lastEditor;
	}
	
}
