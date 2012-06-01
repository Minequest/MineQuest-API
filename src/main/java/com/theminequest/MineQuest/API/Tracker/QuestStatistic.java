package com.theminequest.MineQuest.API.Tracker;

import java.util.Arrays;
import java.util.List;

import com.alta189.simplesave.Field;
import com.alta189.simplesave.Id;
import com.alta189.simplesave.Table;
import com.theminequest.MineQuest.API.Managers;
import com.theminequest.MineQuest.API.Tracker.StatisticManager.Statistic;

@Table("minequest_Quests")
public class QuestStatistic implements Comparable<QuestStatistic>, Statistic {
	
	@Id
	private int id;
	
	@Field
	private String playerName;
	
	@Field
	private String questsAvailable;
	
	@Field
	private String questsAccepted;
	
	@Field
	private String questsCompleted;
	
	// NON-PERSISTENT DATA
	private List<String> availableQuests;
	private List<String> acceptedQuests;
	private List<String> completedQuests;
	
	public String getPlayerName(){
		return playerName;
	}
	
	public void setPlayerName(String playerName){
		this.playerName = playerName;
	}
	
	public String[] getAvailableQuests(){
		setup();
		return availableQuests.toArray(new String[availableQuests.size()]);
	}
	
	public String[] getAcceptedQuests(){
		setup();
		return acceptedQuests.toArray(new String[acceptedQuests.size()]);
	}
	
	public String[] getCompletedQuests(){
		setup();
		return completedQuests.toArray(new String[completedQuests.size()]);
	}
	
	public void addAvailableQuest(String questName){
		setup();
		availableQuests.add(questName);
		save();
	}
	
	public void removeAvailableQuest(String questName){
		setup();
		availableQuests.remove(questName);
		save();
	}
	
	public void addAcceptedQuest(String questName){
		setup();
		acceptedQuests.add(questName);
		save();
	}
	
	public void removeAcceptedQuest(String questName){
		setup();
		acceptedQuests.remove(questName);
		save();
	}
	
	public void addCompletedQuest(String questName){
		setup();
		completedQuests.add(questName);
		save();
	}
	
	public void removeCompletedQuest(String questName){
		setup();
		completedQuests.remove(questName);
		save();
	}
	
	private void setup(){
		if (availableQuests==null)
			availableQuests = Arrays.asList(questsAvailable.split("/"));
		if (acceptedQuests==null)
			acceptedQuests = Arrays.asList(questsAccepted.split("/"));
		if (completedQuests==null)
			completedQuests = Arrays.asList(questsCompleted.split("/"));
	}
	
	private void save(){
		questsAvailable = "";
		for (String s : availableQuests){
			questsAvailable += s + "/";
		}
		questsAvailable = questsAvailable.substring(0,questsAvailable.length()-1);
		
		questsAccepted = "";
		for (String s : acceptedQuests){
			questsAccepted += s + "/";
		}
		questsAccepted = questsAccepted.substring(0,questsAccepted.length()-1);

		questsCompleted = "";
		for (String s : completedQuests){
			questsCompleted += s + "/";
		}
		questsCompleted = questsCompleted.substring(0,questsCompleted.length()-1);

		Managers.getStatisticManager().setStatistic(this, getClass());
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof QuestStatistic))
			return false;
		return playerName.equals(((QuestStatistic)obj).playerName);
	}

	@Override
	public int compareTo(QuestStatistic other) {
		return playerName.compareTo(other.playerName);
	}

}
