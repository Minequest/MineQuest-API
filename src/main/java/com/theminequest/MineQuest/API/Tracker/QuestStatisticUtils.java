package com.theminequest.MineQuest.API.Tracker;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.theminequest.MineQuest.API.Managers;

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
		GIVEN, COMPLETED, UNKNOWN;
	}
	
	public static String[] getQuests(Player player, Status status){
		QuestStatistic s = Managers.getStatisticManager().getStatistic(player.getName(), QuestStatistic.class);
		switch(status){
		case GIVEN:
			return s.getGivenQuests();
		case COMPLETED:
			return s.getCompletedQuests();
		default:
			throw new IllegalArgumentException("Cannot be unknown!");
		}
	}
	
	public static Status hasQuest(Player player, String questName){
		QuestStatistic s = Managers.getStatisticManager().getStatistic(player.getName(), QuestStatistic.class);
		for (String g : s.getGivenQuests()){
			if (questName.equals(g))
				return Status.GIVEN;
		}
		for (String c : s.getCompletedQuests()){
			if (questName.equals(c))
				return Status.COMPLETED;
		}
		return Status.UNKNOWN;
	}
	
	public static void giveQuest(Player player, String questName) throws QSException{
		QuestStatistic s = Managers.getStatisticManager().getStatistic(player.getName(), QuestStatistic.class);
		Status qS = hasQuest(player,questName);
		if (qS==Status.GIVEN)
			throw new QSException("Player already has this quest!");
		s.addGivenQuest(questName);
	}
	
	public static void degiveQuest(Player player, String questName) throws QSException{
		QuestStatistic s = Managers.getStatisticManager().getStatistic(player.getName(), QuestStatistic.class);
		Status qS = hasQuest(player,questName);
		if (qS!=Status.GIVEN)
			throw new QSException("Player doesn't have this quest!");
		s.removeGivenQuest(questName);
	}
	
	public static void completeQuest(Player player, String questName) throws QSException{
		QuestStatistic s = Managers.getStatisticManager().getStatistic(player.getName(), QuestStatistic.class);
		Status qS = hasQuest(player,questName);
		if (qS==Status.UNKNOWN){ // people who don't have this quest don't get it completed
			player.sendMessage(ChatColor.GRAY + "Since you were not given this quest, you do not get credit. :|");
			return;
		} else if (qS==Status.COMPLETED)
			throw new QSException("Player already completed this quest!");
		if (!Managers.getQuestGroupManager().get(player).getQuest().getQuestOwner().equalsIgnoreCase(player.getName()))
			player.sendMessage(ChatColor.GRAY + "Since you were given this quest, you will get credit for this as well.");
		s.removeGivenQuest(questName);	
		s.addCompletedQuest(questName);
	}

}
