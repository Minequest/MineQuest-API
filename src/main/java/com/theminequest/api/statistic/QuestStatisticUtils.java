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
package com.theminequest.api.statistic;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.theminequest.api.CompleteStatus;
import com.theminequest.api.Managers;
import com.theminequest.api.platform.MQPlayer;
import com.theminequest.api.quest.Quest;
import com.theminequest.api.quest.QuestDetails;

public class QuestStatisticUtils {

	public static class QSException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 3521289258825794054L;

		public QSException() {
			super();
		}

		public QSException(String arg0, Throwable arg1) {
			super(arg0, arg1);
		}

		public QSException(String arg0) {
			super(arg0);
		}

		public QSException(Throwable arg0) {
			super(arg0);
		}


	}

	public synchronized static Map<String,Date> getQuests(String playerName, LogStatus status){
		if (status==LogStatus.UNKNOWN)
			throw new IllegalArgumentException("UNKNOWN is not a legal status!");
		List<LogStatistic> s = Managers.getQuestStatisticManager().getAllStatistics(playerName, LogStatistic.class);
		Map<String,Date> toreturn = new HashMap<String,Date>();
		for (LogStatistic l : s){
			if (l.getStatus()==status)
				toreturn.put(l.getQuestName(), new Date(l.getTimestamp()));
		}
		return Collections.unmodifiableMap(toreturn);
	}

	public synchronized static LogStatus hasQuest(String playerName, String questName){
		LogStatistic s = Managers.getQuestStatisticManager().getQuestStatistic(playerName, questName, LogStatistic.class);
		if (s == null)
			return LogStatus.UNKNOWN;
		return s.getStatus();
	}

	public synchronized static void giveQuest(String playerName, String questName) throws QSException{
		LogStatistic stat = Managers.getQuestStatisticManager().getQuestStatistic(playerName, questName, LogStatistic.class);
		if (stat != null) {
			LogStatus qS = stat.getStatus();
			if (qS==LogStatus.GIVEN || qS==LogStatus.ACTIVE)
				throw new QSException("Player already has this quest!");
		}
		QuestDetails d = Managers.getQuestManager().getDetails(questName);
		if (d==null)
			throw new QSException("No such quest available on system!");
		if (d.getProperty(QuestDetails.QUEST_LOADWORLD)){
			if (stat == null) {
				stat = Managers.getQuestStatisticManager().createStatistic(playerName, questName, LogStatistic.class);
			}
			stat.setStatus(LogStatus.GIVEN);
			stat.setTimestamp(System.currentTimeMillis());
			Managers.getQuestStatisticManager().saveStatistic(stat, LogStatistic.class);
		} else {
			Quest q = Managers.getQuestManager().startQuest(d, playerName);
			// FIXME - if q.startQuest() is not invoked, make sure to set last active task
			// to the starting task (-1 might get confusing)
			if (Managers.getPlatform().getPlayer(playerName)!=null)
				q.startQuest();
			if (stat == null) {
				stat = Managers.getQuestStatisticManager().createStatistic(playerName, questName, LogStatistic.class);
			}
			stat.setStatus(LogStatus.ACTIVE);
			stat.setTimestamp(System.currentTimeMillis());
			SnapshotStatistic newsnapshot = Managers.getQuestStatisticManager().createStatistic(playerName, questName, SnapshotStatistic.class);
			newsnapshot.setSnapshot(q.createSnapshot());
			Managers.getQuestStatisticManager().saveStatistic(stat, LogStatistic.class);
			Managers.getQuestStatisticManager().saveStatistic(newsnapshot, SnapshotStatistic.class);
		}
	}

	public synchronized static void dropQuest(String playerName, String questName) throws QSException{
		LogStatistic stat = Managers.getQuestStatisticManager().getQuestStatistic(playerName, questName, LogStatistic.class);
		if (stat == null) {
			throw new QSException("Player doesn't have quest!");
		} else if (stat.getStatus()==LogStatus.GIVEN){
			Managers.getQuestStatisticManager().removeStatistic(stat, LogStatistic.class);
		} else if (stat.getStatus()==LogStatus.ACTIVE) {
			Quest q = Managers.getQuestManager().getMainWorldQuest(playerName,questName);
			q.finishQuest(CompleteStatus.CANCELED);
			q.cleanupQuest();
			Managers.getQuestManager().removeMainWorldQuest(playerName, questName);
			Managers.getQuestStatisticManager().removeStatistic(stat, LogStatistic.class);
			SnapshotStatistic snapshot = Managers.getQuestStatisticManager().getQuestStatistic(playerName, questName, SnapshotStatistic.class);
			if (snapshot == null)
				return;
			Managers.getQuestStatisticManager().removeStatistic(snapshot, SnapshotStatistic.class);
		} else {
			throw new QSException("Player doesn't have quest!");
		}
	}
	
	public synchronized static void failQuest(String playerName, String questName) throws QSException {
		LogStatistic stat = Managers.getQuestStatisticManager().getQuestStatistic(playerName, questName, LogStatistic.class);
		if (stat == null)
			return;
		MQPlayer player = Managers.getPlatform().getPlayer(playerName);
		if (stat.getStatus() == LogStatus.COMPLETED){
			throw new QSException("Player already completed this quest!");
		} else if (stat.getStatus() == LogStatus.GIVEN){
			if (player!=null){
				if (!Managers.getGroupManager().get(player).getQuest().getQuestOwner().equalsIgnoreCase(playerName))
					player.sendMessage(Managers.getPlatform().chatColor().GRAY() + "Since this quest was failed, the system will ignore your participation.");
				else {
					stat.setStatus(LogStatus.FAILED);
					stat.setTimestamp(System.currentTimeMillis());
					Managers.getQuestStatisticManager().saveStatistic(stat, LogStatistic.class);
				}
			}
		} else if (stat.getStatus() == LogStatus.ACTIVE){
			Quest q = Managers.getQuestManager().getMainWorldQuest(playerName, questName);
			if (q.isFinished()==null)
				throw new QSException("Quest not finished!");
			q.cleanupQuest();
			Managers.getQuestManager().removeMainWorldQuest(playerName, questName);
			stat.setStatus(LogStatus.FAILED);
			stat.setTimestamp(System.currentTimeMillis());
			Managers.getQuestStatisticManager().saveStatistic(stat, LogStatistic.class);
			SnapshotStatistic snapshot = Managers.getQuestStatisticManager().getQuestStatistic(playerName, questName, SnapshotStatistic.class);
			if (snapshot == null)
				return;
			Managers.getQuestStatisticManager().removeStatistic(snapshot, SnapshotStatistic.class);
		}
	}

	public synchronized static void completeQuest(String playerName, String questName) throws QSException{
		LogStatistic stat = Managers.getQuestStatisticManager().getQuestStatistic(playerName, questName, LogStatistic.class);
		if (stat == null)
			return;
		MQPlayer player = Managers.getPlatform().getPlayer(playerName);
		if (stat.getStatus() == LogStatus.COMPLETED){
			throw new QSException("Player already completed this quest!");
		} else if (stat.getStatus() == LogStatus.GIVEN){
			if (player!=null){
				if (!Managers.getGroupManager().get(player).getQuest().getQuestOwner().equalsIgnoreCase(playerName))
					player.sendMessage(Managers.getPlatform().chatColor().GRAY() + "Since you were given this quest, you will get credit for this as well.");
			}
			stat.setStatus(LogStatus.COMPLETED);
			stat.setTimestamp(System.currentTimeMillis());
			Managers.getQuestStatisticManager().saveStatistic(stat, LogStatistic.class);
		} else if (stat.getStatus() == LogStatus.ACTIVE){
			Quest q = Managers.getQuestManager().getMainWorldQuest(playerName, questName);
			if (q.isFinished()==null)
				throw new QSException("Quest not finished!");
			q.cleanupQuest();
			Managers.getQuestManager().removeMainWorldQuest(playerName, questName);
			stat.setStatus(LogStatus.COMPLETED);
			stat.setTimestamp(System.currentTimeMillis());
			Managers.getQuestStatisticManager().saveStatistic(stat, LogStatistic.class);
			SnapshotStatistic snapshot = Managers.getQuestStatisticManager().getQuestStatistic(playerName, questName, SnapshotStatistic.class);
			if (snapshot == null)
				return;
			Managers.getQuestStatisticManager().removeStatistic(snapshot, SnapshotStatistic.class);
		}
	}
	
	public synchronized static void checkpointQuest(Quest quest) {
		String playerName = quest.getQuestOwner();
		String questName = quest.getDetails().getProperty(QuestDetails.QUEST_NAME);
		SnapshotStatistic snapshot = Managers.getQuestStatisticManager().getQuestStatistic(playerName, questName, SnapshotStatistic.class);
		if (snapshot == null)
			return;
		snapshot.setSnapshot(quest.createSnapshot());
		Managers.getQuestStatisticManager().saveStatistic(snapshot, SnapshotStatistic.class);
	}

}
