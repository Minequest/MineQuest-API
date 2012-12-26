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
 * This file, QuestEvent.java, is part of MineQuest:
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
package com.theminequest.minequest.api.event;

import static com.theminequest.minequest.api.quest.QuestDetails.QUEST_NAME;

import java.util.logging.Level;

import com.theminequest.minequest.api.CompleteStatus;
import com.theminequest.minequest.api.Managers;
import com.theminequest.minequest.api.platform.MQBlock;
import com.theminequest.minequest.api.platform.MQPlayer;
import com.theminequest.minequest.api.platform.events.EventCompleteEvent;
import com.theminequest.minequest.api.platform.events.EventStartEvent;
import com.theminequest.minequest.api.quest.Quest;

public abstract class QuestEvent {
	
	private Quest quest;
	private int eventid;
	private volatile CompleteStatus complete;
	private volatile boolean completeOrPending;
	private final Object completeLock = new Object();
	
	public QuestEvent() {}
	
	/**
	 * Setup the properties of this event
	 * @param quest Quest to associate with, or <code>null</code>
	 * for a custom event.
	 * @param eventid Event ID
	 * @param details Details for the event to parse
	 */
	public void setupProperties(Quest quest, int eventid, String details) {
		this.quest = quest;
		this.eventid = eventid;
		this.complete = null;
		this.completeOrPending = false;
		parseDetails(details.split(":"));
	}
	
	/**
	 * Tasks call fireEvent(). Then they wait for all events to
	 * complete, then fire off more stuff.
	 */
	public final void fireEvent() {
		setUpEvent();
		EventStartEvent event = new EventStartEvent(this);
		Managers.getPlatform().callEvent(event);
	}
	
	public final void check() {
		if (completeOrPending)
			return;
		
		if (complete != null)
			return;
		
		synchronized (completeLock) {
			if (completeOrPending)
				return;
			
			if (complete == null) {
				if (conditions()) {
					completeOrPending = true;
				}
			}
		}
		
		if (completeOrPending) {
			Managers.getPlatform().scheduleSyncTask(new Runnable() {
				public void run() {
					complete(action());
				}
			});
		}
	}
	
	/**
	 * Returns the status of this event.
	 * @return Respective status, or <code>null</code> if it has
	 * not been declared yet.
	 */
	public final synchronized CompleteStatus isComplete(){
		return complete;
	}
	
	/**
	 * Parse the details given (: separated)
	 * @param details Parameters given
	 */
	public abstract void parseDetails(String[] details);
	
	/**
	 * Conditions for this event to be performed (and therefore complete)
	 * @return true if all conditions are met for this event to complete
	 */
	public abstract boolean conditions();
	
	/**
	 * Perform the event (and complete it, returning true if successful,
	 * false if not, and null to ignore it completely. Remember that failing
	 * an event fails the whole task, and possibly the whole mission.)
	 * @return the event action result
	 */
	public abstract CompleteStatus action();
	
	public final Quest getQuest(){
		return quest;
	}
	
	public final int getEventId(){
		return eventid;
	}
	
	/**
	 * Optional event implementation: After the event is fired,
	 * do anything else to set it up?
	 */
	public void setUpEvent(){
		
	}
	
	/**
	 * Optional event implementation: After the event has executed successfully,
	 * you have the option of cleaning up your event. (For example,
	 * kill entities that you are tracking, stop a process, etc...)
	 */
	public void cleanUpEvent(){
		
	}
	
	/**
	 * Notify that the event has been completed with the status given.
	 * @param actionresult Status to pass in.
	 */
	public final void complete(CompleteStatus c) {
		if (complete != null)
			return;
		
		boolean completed = false;
		synchronized (completeLock) {
			if (complete == null) {
				completed = true;
				
				if (c != null) {
					complete = c;
				} else {
					complete = CompleteStatus.IGNORE;
				}
			}
		}
		
		if (completed) {
			Managers.getEventManager().deregisterEventListener(this);
			cleanUpEvent();
			EventCompleteEvent event = new EventCompleteEvent(this,c);
			Managers.getPlatform().callEvent(event);
			
			if (c == CompleteStatus.FAILURE) {
				quest.getActiveTask().completeTask(CompleteStatus.FAILURE);
			} else if (c == CompleteStatus.SUCCESS || c == CompleteStatus.WARNING){
				if (switchTask()!=null) {
					if (switchTask()!=-2) {
						quest.getActiveTask().cancelTask();
						if (!quest.startTask(switchTask())) {
							Managers.log(Level.SEVERE, "Starting task " + switchTask() + " for " + getQuest().getDetails().getProperty(QUEST_NAME) + "/" + getQuest().getQuestOwner() + " failed!");
							quest.finishQuest(CompleteStatus.ERROR);
						}
					}
				} else
					quest.getActiveTask().checkTasks();
			}
		}
	}
	
	/**
	 * Some events want to switch to a new task when it completes.
	 * For instance, {@link com.theminequest.MQCoreEvents.TaskEvent}
	 * switches to a new task after a preset delay. This method is here
	 * such that events that complete execution can specify a different
	 * task to switch to. In the case that events do not want to switch
	 * tasks, they may return <code>null</code> as we are asking for an
	 * {@link java.lang.Integer} object to be returned.<br>
	 * <h4>Why did we decide to abstract this method instead of returning
	 * <code>null</code> by default?</h4>
	 * We believed that most developers would ignore the fact that this
	 * method is available and manually attempt to switch tasks by calling
	 * the Quest directly. This would trigger a deadlock and should not
	 * be attempted.
	 * @return Task Number to switch Quest to, or NULL to not switch.
	 */
	public abstract Integer switchTask();
	
	/**
	 * Optional method that QEvents can override if they want;
	 * by default, doesn't do anything.
	 * @param e
	 * @return true if breaking block meets condition for event
	 */
	public boolean blockBreakCondition(MQPlayer player, MQBlock block) {
		return false;
	}
	
	public final void onBlockBreak(MQPlayer player, MQBlock block) {
		if (complete == null) {
			if (blockBreakCondition(player, block)) {
				complete(action());
			}
		}
	}
		
	/**
	 * Optional method that QEvents can override if they want;
	 * by default, doesn't do anything.
	 * @param e
	 * @return true if player interact meets condition for an event
	 */
	public boolean playerInteractCondition(MQPlayer player, MQBlock block) {
		return false;
	}
	
	public final void onPlayerInteract(MQPlayer player, MQBlock block) {
		if (complete == null) {
			if (playerInteractCondition(player, block)) {
				complete(action());
			}
		}
	}
	
}
