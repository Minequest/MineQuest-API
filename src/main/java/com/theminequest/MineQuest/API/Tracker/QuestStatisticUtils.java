package com.theminequest.MineQuest.API.Tracker;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.theminequest.MineQuest.API.CompleteStatus;
import com.theminequest.MineQuest.API.Managers;
import com.theminequest.MineQuest.API.Quest.Quest;
import com.theminequest.MineQuest.API.Quest.QuestDetails;

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
		List<LogStatistic> s = Managers.getStatisticManager().getAllStatistics(playerName, LogStatistic.class);
		Map<String,Date> toreturn = new HashMap<String,Date>();
		for (LogStatistic l : s){
			if (l.getStatus()==status)
				toreturn.put(l.getQuestName(), new Date(l.getTimestamp()));
		}
		return Collections.unmodifiableMap(toreturn);
	}

	public synchronized static LogStatus hasQuest(String playerName, String questName){
		LogStatistic s = Managers.getStatisticManager().getQuestStatistic(playerName, questName, LogStatistic.class);
		if (s == null)
			return LogStatus.UNKNOWN;
		return s.getStatus();
	}

	public synchronized static void giveQuest(String playerName, String questName) throws QSException{
		LogStatistic stat = Managers.getStatisticManager().getQuestStatistic(playerName, questName, LogStatistic.class);
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
				stat = Managers.getStatisticManager().createStatistic(playerName, LogStatistic.class);
				stat.setQuestName(questName);
				stat.setStatus(LogStatus.GIVEN);
			}
			stat.setTimestamp(System.currentTimeMillis());
			Managers.getStatisticManager().saveStatistic(stat, LogStatistic.class);
		} else {
			Quest q = Managers.getQuestManager().startQuest(d,playerName);
			// FIXME - if q.startQuest() is not invoked, make sure to set last active task
			// to the starting task (-1 might get confusing)
			if (Bukkit.getPlayer(playerName)!=null)
				q.startQuest();
			if (stat == null) {
				stat = Managers.getStatisticManager().createStatistic(playerName, LogStatistic.class);
				stat.setQuestName(questName);
				stat.setStatus(LogStatus.ACTIVE);
			}
			stat.setTimestamp(System.currentTimeMillis());
			SnapshotStatistic newsnapshot = Managers.getStatisticManager().createStatistic(playerName, SnapshotStatistic.class);
			stat.setQuestName(questName);
			newsnapshot.setSnapshot(q.createSnapshot());
			Managers.getStatisticManager().saveStatistic(stat, LogStatistic.class);
			Managers.getStatisticManager().saveStatistic(newsnapshot, SnapshotStatistic.class);
		}
	}

	public synchronized static void dropQuest(String playerName, String questName) throws QSException{
		LogStatistic stat = Managers.getStatisticManager().getQuestStatistic(playerName, questName, LogStatistic.class);
		if (stat == null) {
			throw new QSException("Player doesn't have quest!");
		} else if (stat.getStatus()==LogStatus.GIVEN){
			Managers.getStatisticManager().removeStatistic(stat, LogStatistic.class);
		} else if (stat.getStatus()==LogStatus.ACTIVE) {
			Quest q = Managers.getQuestManager().getMainWorldQuest(playerName,questName);
			q.finishQuest(CompleteStatus.CANCELED);
			q.cleanupQuest();
			Managers.getStatisticManager().removeStatistic(stat, LogStatistic.class);
			SnapshotStatistic snapshot = Managers.getStatisticManager().getQuestStatistic(playerName, questName, SnapshotStatistic.class);
			if (snapshot == null)
				return;
			Managers.getStatisticManager().removeStatistic(snapshot, SnapshotStatistic.class);
		} else {
			throw new QSException("Player doesn't have quest!");
		}
	}

	public synchronized static void completeQuest(String playerName, String questName) throws QSException{
		LogStatistic stat = Managers.getStatisticManager().getQuestStatistic(playerName, questName, LogStatistic.class);
		if (stat == null)
			return;
		Player player = Bukkit.getPlayer(playerName);
		if (stat.getStatus() == LogStatus.COMPLETED){
			throw new QSException("Player already completed this quest!");
		} else if (stat.getStatus() == LogStatus.GIVEN){
			if (player!=null){
				if (!Managers.getQuestGroupManager().get(player).getQuest().getQuestOwner().equalsIgnoreCase(playerName))
					player.sendMessage(ChatColor.GRAY + "Since you were given this quest, you will get credit for this as well.");
			}
			stat.setStatus(LogStatus.COMPLETED);
			stat.setTimestamp(System.currentTimeMillis());
			Managers.getStatisticManager().saveStatistic(stat, LogStatistic.class);
		} else if (stat.getStatus() == LogStatus.ACTIVE){
			Quest q = Managers.getQuestManager().getMainWorldQuest(playerName, questName);
			if (q.isFinished()==null)
				throw new QSException("Quest not finished!");
			q.cleanupQuest();
			Managers.getQuestManager().removeMainWorldQuest(playerName, questName);
			stat.setStatus(LogStatus.COMPLETED);
			stat.setTimestamp(System.currentTimeMillis());
			Managers.getStatisticManager().saveStatistic(stat, LogStatistic.class);
			SnapshotStatistic snapshot = Managers.getStatisticManager().getQuestStatistic(playerName, questName, SnapshotStatistic.class);
			if (snapshot == null)
				return;
			Managers.getStatisticManager().removeStatistic(snapshot, SnapshotStatistic.class);
		}
	}
	
	public synchronized static void checkpointQuest(Quest quest) {
		String playerName = quest.getQuestOwner();
		String questName = quest.getDetails().getProperty(QuestDetails.QUEST_NAME);
		SnapshotStatistic snapshot = Managers.getStatisticManager().getQuestStatistic(playerName, questName, SnapshotStatistic.class);
		if (snapshot == null)
			return;
		snapshot.setSnapshot(quest.createSnapshot());
		Managers.getStatisticManager().saveStatistic(snapshot, SnapshotStatistic.class);
	}

}
