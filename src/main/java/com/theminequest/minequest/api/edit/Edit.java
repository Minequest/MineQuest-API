
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
