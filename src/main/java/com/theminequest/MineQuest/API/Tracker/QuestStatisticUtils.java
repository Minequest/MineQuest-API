package com.theminequest.MineQuest.API.Tracker;

import java.util.Date;
import java.util.LinkedHashMap;
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

	public static enum Status {
		/**
		 * Given Quests (Instanced)
		 */
		GIVEN,
		/**
		 * Completed Quests
		 */
		COMPLETED,
		/**
		 * Active Main World Quests
		 */
		INPROGRESS,
		/**
		 * Unknown
		 */
		UNKNOWN;
	}

	public synchronized static String[] getQuests(String playerName, Status status){
		QuestStatistic s = Managers.getStatisticManager().getStatistic(playerName, QuestStatistic.class);
		switch(status){
		case GIVEN:
			return s.getGivenQuests();
		case COMPLETED:
			return s.getCompletedQuests().keySet().toArray(new String[s.getCompletedQuests().keySet().size()]);
		case INPROGRESS:
			QuestSnapshot[] q = s.getMainWorldQuests();
			String[] a = new String[q.length];
			for (int i=0; i<q.length; i++)
				a[i] = q[i].getDetails().getProperty(QuestDetails.QUEST_NAME);
			return a;
		default:
			throw new IllegalArgumentException("Cannot be unknown!");
		}
	}
	
	public synchronized static Map<String,Date> getCompletedDetails(String playerName){
		QuestStatistic s = Managers.getStatisticManager().getStatistic(playerName, QuestStatistic.class);
		Map<String,Long> details = s.getCompletedQuests();
		Map<String,Date> toreturn = new LinkedHashMap<String,Date>();
		for (String key : details.keySet()){
			toreturn.put(key, new Date(details.get(key)));
		}
		return toreturn;
	}

	public synchronized static Status hasQuest(String playerName, String questName){
		QuestStatistic s = Managers.getStatisticManager().getStatistic(playerName, QuestStatistic.class);
		for (String g : s.getGivenQuests()){
			if (questName.equals(g))
				return Status.GIVEN;
		}
		for (String c : s.getCompletedQuests().keySet()){
			if (questName.equals(c))
				return Status.COMPLETED;
		}
		for (QuestSnapshot q : s.getMainWorldQuests()){
			if (questName.equals(q.getDetails().getProperty(QuestDetails.QUEST_NAME)))
				return Status.INPROGRESS;
		}
		return Status.UNKNOWN;
	}

	public synchronized static void giveQuest(String playerName, String questName) throws QSException{
		QuestStatistic s = Managers.getStatisticManager().getStatistic(playerName, QuestStatistic.class);
		Status qS = hasQuest(playerName,questName);
		if (qS==Status.GIVEN || qS==Status.INPROGRESS)
			throw new QSException("Player already has this quest!");
		QuestDetails d = Managers.getQuestManager().getDetails(questName);
		if (d==null)
			throw new QSException("No such quest available on system!");
		if (d.getProperty(QuestDetails.QUEST_LOADWORLD))
			s.addGivenQuest(questName);
		else {
			Quest q = Managers.getQuestManager().startQuest(d,playerName);
			q.startQuest();
			s.saveMainWorldQuest(q);
		}
	}

	public synchronized static void degiveQuest(String playerName, String questName) throws QSException{
		QuestStatistic s = Managers.getStatisticManager().getStatistic(playerName, QuestStatistic.class);
		Status qS = hasQuest(playerName,questName);
		switch(qS){
		case GIVEN:
			s.removeGivenQuest(questName);
			break;
		case INPROGRESS:
			Quest q = Managers.getQuestManager().getMainWorldQuest(playerName,questName);
			q.finishQuest(CompleteStatus.CANCELED);
			q.cleanupQuest();
			s.removeMainWorldQuest(questName);
			Managers.getQuestManager().removeMainWorldQuest(playerName, questName);
			break;
		default:
			throw new QSException("Player doesn't have this quest!");
		}
	}

	public synchronized static void completeQuest(String playerName, String questName) throws QSException{
		QuestStatistic s = Managers.getStatisticManager().getStatistic(playerName, QuestStatistic.class);
		Status qS = hasQuest(playerName,questName);
		Player player = Bukkit.getPlayer(playerName);
		switch(qS){
		case COMPLETED:
			throw new QSException("Player already completed this quest!");
		case GIVEN:
			if (player!=null)
				if (!Managers.getQuestGroupManager().get(player).getQuest().getQuestOwner().equalsIgnoreCase(playerName))
					player.sendMessage(ChatColor.GRAY + "Since you were given this quest, you will get credit for this as well.");
			s.removeGivenQuest(questName);
			s.addCompletedQuest(questName);
			return;
		case INPROGRESS:
			Quest q = Managers.getQuestManager().getMainWorldQuest(playerName, questName);
			if (q.isFinished()==null)
				throw new QSException("Quest not finished!");
			q.cleanupQuest();
			s.removeMainWorldQuest(questName);
			Managers.getQuestManager().removeMainWorldQuest(playerName, questName);
			return;
		default:
			break;
		}
	}

}
