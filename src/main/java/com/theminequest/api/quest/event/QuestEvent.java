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
package com.theminequest.api.quest.event;

import com.theminequest.api.CompleteStatus;
import com.theminequest.api.Managers;
import com.theminequest.api.quest.Quest;
import com.theminequest.api.quest.QuestTask;

public abstract class QuestEvent {
	
	public static final int TASK_NULL = -2;
	
	private QuestTask task;
	private int id;
	
	private volatile CompleteStatus complete;
	private volatile boolean completeOrPending;
	private final Object completeLock = new Object();
	
	public QuestEvent() {}
	
	/**
	 * Setup the properties of this event
	 * @param quest Task connected with the event
	 * @param id Unique ID of this event
	 * @param details Details for the event to parse
	 */
	public final void setupProperties(QuestTask task, int id, String[] arguments) {
		this.task = task;
		this.id = id;
		this.complete = null;
		this.completeOrPending = false;
		setupArguments(arguments);
	}
	
	/**
	 * Tasks call fireEvent(). Then they wait for all events to
	 * complete, then fire off more stuff.
	 */
	public void fireEvent() {
		setUpEvent();
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
	 * Setup the event with the arguments given
	 * @param arguments Arguments given
	 */
	public abstract void setupArguments(String[] arguments);
	
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
		return task.getQuest();
	}
	
	public final int getEventId() {
		return id;
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
			cleanUpEvent();
			
			Integer nextTask = switchTask();
			if (nextTask != null)
				task.completeEvent(this, c, nextTask);
			else
				task.completeEvent(this, c);
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
	
}
