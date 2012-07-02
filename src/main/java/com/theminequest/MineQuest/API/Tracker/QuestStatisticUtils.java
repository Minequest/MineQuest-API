package com.theminequest.MineQuest.API.Tracker;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.theminequest.MineQuest.API.CompleteStatus;
import com.theminequest.MineQuest.API.Managers;
import com.theminequest.MineQuest.API.Quest.Quest;
import com.theminequest.MineQuest.API.Quest.QuestDetails;
import com.theminequest.MineQuest.API.Quest.QuestSnapshot;

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
		List<LogStatistic> s = Managers.getStatisticManager().getStatistics(playerName, LogStatistic.class);
		Map<String,Date> toreturn = new HashMap<String,Date>();
		for (LogStatistic l : s){
			if (l.getStatus()==status)
				toreturn.put(l.getQuestName(), new Date(l.getTimestamp()));
		}
		return Collections.unmodifiableMap(toreturn);
	}

	public synchronized static LogStatus hasQuest(String playerName, String questName){
		List<LogStatistic> s = Managers.getStatisticManager().getStatistics(playerName, LogStatistic.class);
		int indexof = s.indexOf(questName);
		if (indexof==-1)
			return LogStatus.UNKNOWN;
		LogStatistic ls = s.get(indexof);
		return ls.getStatus();
	}

	public synchronized static void giveQuest(String playerName, String questName) throws QSException{
		List<LogStatistic> s = Managers.getStatisticManager().getStatistics(playerName, LogStatistic.class);
		LogStatus qS = hasQuest(playerName,questName);
		if (qS==LogStatus.GIVEN || qS==LogStatus.ACTIVE)
			throw new QSException("Player already has this quest!");
		else if (qS==LogStatus.COMPLETED) {
			LogStatistic log = s.get(s.indexOf(questName));
			Managers.getStatisticManager().removeStatistic(log, LogStatistic.class);
		}
		QuestDetails d = Managers.getQuestManager().getDetails(questName);
		if (d==null)
			throw new QSException("No such quest available on system!");
		if (d.getProperty(QuestDetails.QUEST_LOADWORLD)){
			LogStatistic newlog = Managers.getStatisticManager().createStatistic(playerName, LogStatistic.class);
			newlog.setStatus(LogStatus.GIVEN);
			newlog.setQuestName(questName);
			newlog.setTimestamp(System.currentTimeMillis());
			Managers.getStatisticManager().saveStatistic(newlog, LogStatistic.class);
		} else {
			Quest q = Managers.getQuestManager().startQuest(d,playerName);
			// FIXME - if q.startQuest() is not invoked, make sure to set last active task
			// to the starting task (-1 might get confusing)
			if (Bukkit.getPlayer(playerName)!=null)
				q.startQuest();
			LogStatistic newlog = Managers.getStatisticManager().createStatistic(playerName, LogStatistic.class);
			newlog.setStatus(LogStatus.ACTIVE);
			newlog.setQuestName(questName);
			newlog.setTimestamp(System.currentTimeMillis());
			SnapshotStatistic newsnapshot = Managers.getStatisticManager().createStatistic(playerName, SnapshotStatistic.class);
			newsnapshot.setQuestName(questName);
			newsnapshot.setSnapshot(q.createSnapshot());
			Managers.getStatisticManager().saveStatistic(newlog, LogStatistic.class);
			Managers.getStatisticManager().saveStatistic(newsnapshot, SnapshotStatistic.class);
		}
	}

	public synchronized static void dropQuest(String playerName, String questName) throws QSException{
		List<LogStatistic> s = Managers.getStatisticManager().getStatistics(playerName,LogStatistic.class);
		LogStatus qS = hasQuest(playerName,questName);
		if (qS==LogStatus.GIVEN){
			LogStatistic log = s.get(s.indexOf(questName));
			Managers.getStatisticManager().removeStatistic(log, LogStatistic.class);
		} else if (qS==LogStatus.ACTIVE) {
			Quest q = Managers.getQuestManager().getMainWorldQuest(playerName,questName);
			q.finishQuest(CompleteStatus.CANCELED);
			q.cleanupQuest();
			LogStatistic log = s.get(s.indexOf(questName));
			Managers.getStatisticManager().removeStatistic(log, LogStatistic.class);
			List<SnapshotStatistic> snapshots = Managers.getStatisticManager().getStatistics(playerName, SnapshotStatistic.class);
			int index = snapshots.indexOf(questName);
			if (index==-1)
				return;
			SnapshotStatistic snapshot = snapshots.get(index);
			Managers.getStatisticManager().removeStatistic(snapshot, SnapshotStatistic.class);
		} else {
			throw new QSException("Player doesn't have quest!");
		}
	}

	public synchronized static void completeQuest(String playerName, String questName) throws QSException{
		List<LogStatistic> s = Managers.getStatisticManager().getStatistics(playerName, LogStatistic.class);
		LogStatus qS = hasQuest(playerName,questName);
		Player player = Bukkit.getPlayer(playerName);
		if (qS == LogStatus.COMPLETED){
			throw new QSException("Player already completed this quest!");
		} else if (qS == LogStatus.GIVEN){
			if (player!=null){
				if (!Managers.getQuestGroupManager().get(player).getQuest().getQuestOwner().equalsIgnoreCase(playerName))
					player.sendMessage(ChatColor.GRAY + "Since you were given this quest, you will get credit for this as well.");
			}
			LogStatistic log = s.get(s.indexOf(questName));
			log.setStatus(LogStatus.COMPLETED);
			log.setTimestamp(System.currentTimeMillis());
			Managers.getStatisticManager().saveStatistic(log, LogStatistic.class);
		} else if (qS == LogStatus.ACTIVE){
			Quest q = Managers.getQuestManager().getMainWorldQuest(playerName, questName);
			if (q.isFinished()==null)
				throw new QSException("Quest not finished!");
			q.cleanupQuest();
			Managers.getQuestManager().removeMainWorldQuest(playerName, questName);
			LogStatistic log = s.get(s.indexOf(questName));
			log.setStatus(LogStatus.COMPLETED);
			log.setTimestamp(System.currentTimeMillis());
			Managers.getStatisticManager().saveStatistic(log, LogStatistic.class);
			List<SnapshotStatistic> snapshots = Managers.getStatisticManager().getStatistics(playerName, SnapshotStatistic.class);
			int index = snapshots.indexOf(questName);
			if (index==-1)
				return;
			SnapshotStatistic snapshot = snapshots.get(index);
			Managers.getStatisticManager().removeStatistic(snapshot, SnapshotStatistic.class);
		}
	}
	
	public synchronized static void checkpointQuest(Quest quest) {
		String playerName = quest.getQuestOwner();
		String questName = quest.getDetails().getProperty(QuestDetails.QUEST_NAME);
		List<SnapshotStatistic> snapshots = Managers.getStatisticManager().getStatistics(playerName, SnapshotStatistic.class);
		int index = snapshots.indexOf(questName);
		if (index==-1)
			return;
		SnapshotStatistic snapshot = snapshots.get(index);
		snapshot.setSnapshot(quest.createSnapshot());
		Managers.getStatisticManager().saveStatistic(snapshot, SnapshotStatistic.class);
	}

}
