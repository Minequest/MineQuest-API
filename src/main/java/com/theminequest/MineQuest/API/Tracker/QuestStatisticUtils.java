package com.theminequest.MineQuest.API.Tracker;

import java.util.NoSuchElementException;

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
	
	public synchronized static String[] getQuests(Player player, Status status){
		QuestStatistic s = Managers.getStatisticManager().getStatistic(player.getName(), QuestStatistic.class);
		switch(status){
		case GIVEN:
			return s.getGivenQuests();
		case COMPLETED:
			return s.getCompletedQuests();
		case INPROGRESS:
			Quest[] q = s.getMainWorldQuests();
			String[] a = new String[q.length];
			for (int i=0; i<q.length; i++)
				a[i] = q[i].getDetails().getProperty(QuestDetails.QUEST_NAME);
			return a;
		default:
			throw new IllegalArgumentException("Cannot be unknown!");
		}
	}
	
	public synchronized static Quest getMainWorldQuest(Player player, String questName){
		QuestStatistic s = Managers.getStatisticManager().getStatistic(player.getName(), QuestStatistic.class);
		for (Quest q : s.getMainWorldQuests()){
			if (q.getDetails().getProperty(QuestDetails.QUEST_NAME).equals(questName))
				return q;
		}
		throw new NoSuchElementException("No such quest!");
	}
	
	public synchronized static Status hasQuest(Player player, String questName){
		QuestStatistic s = Managers.getStatisticManager().getStatistic(player.getName(), QuestStatistic.class);
		for (String g : s.getGivenQuests()){
			if (questName.equals(g))
				return Status.GIVEN;
		}
		for (String c : s.getCompletedQuests()){
			if (questName.equals(c))
				return Status.COMPLETED;
		}
		for (Quest q : s.getMainWorldQuests()){
			if (questName.equals(q.getDetails().getProperty(QuestDetails.QUEST_NAME)))
				return Status.INPROGRESS;
		}
		return Status.UNKNOWN;
	}
	
	public synchronized static void giveQuest(Player player, String questName) throws QSException{
		QuestStatistic s = Managers.getStatisticManager().getStatistic(player.getName(), QuestStatistic.class);
		Status qS = hasQuest(player,questName);
		if (qS==Status.GIVEN || qS==Status.INPROGRESS)
			throw new QSException("Player already has this quest!");
		QuestDetails d = Managers.getQuestManager().getDetails(questName);
		if (d==null)
			throw new QSException("No such quest available on system!");
		if (d.getProperty(QuestDetails.QUEST_LOADWORLD))
			s.addGivenQuest(questName);
		else {
			Quest q = Managers.getQuestManager().startQuest(d, player.getName());
			q.startQuest();
			s.saveMainWorldQuest(q);
		}
	}
	
	public synchronized static void degiveQuest(Player player, String questName) throws QSException{
		QuestStatistic s = Managers.getStatisticManager().getStatistic(player.getName(), QuestStatistic.class);
		Status qS = hasQuest(player,questName);
		switch(qS){
		case GIVEN:
			s.removeGivenQuest(questName);
			break;
		case INPROGRESS:
			Quest q = getMainWorldQuest(player,questName);
			q.finishQuest(CompleteStatus.CANCELED);
			q.cleanupQuest();
			s.removeMainWorldQuest(questName);
			break;
		default:
			throw new QSException("Player doesn't have this quest!");
		}
	}
	
	public synchronized static void completeQuest(Player player, String questName) throws QSException{
		QuestStatistic s = Managers.getStatisticManager().getStatistic(player.getName(), QuestStatistic.class);
		Status qS = hasQuest(player,questName);
		switch(qS){
		case COMPLETED:
			throw new QSException("Player already completed this quest!");
		case GIVEN:
			if (!Managers.getQuestGroupManager().get(player).getQuest().getQuestOwner().equalsIgnoreCase(player.getName()))
				player.sendMessage(ChatColor.GRAY + "Since you were given this quest, you will get credit for this as well.");
			s.removeGivenQuest(questName);
			s.addCompletedQuest(questName);
			return;
		case INPROGRESS:
			Quest[] mwq = s.getMainWorldQuests();
			for (int i=0; i<mwq.length; i++){
				Quest q = mwq[i];
				if (q.getDetails().getProperty(QuestDetails.QUEST_NAME).equals(questName)){
					if (q.isFinished()==null)
						throw new QSException("Quest not finished!");
					q.cleanupQuest();
					s.removeMainWorldQuest(questName);
					i=mwq.length;
				}
			}
			return;
		default:
			break;
		}
	}

}
