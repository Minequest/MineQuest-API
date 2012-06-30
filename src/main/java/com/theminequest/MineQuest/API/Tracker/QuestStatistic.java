package com.theminequest.MineQuest.API.Tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;

import com.alta189.simplesave.Field;
import com.alta189.simplesave.Id;
import com.alta189.simplesave.Table;
import com.theminequest.MineQuest.API.Managers;
import com.theminequest.MineQuest.API.BukkitEvents.QuestGivenEvent;
import com.theminequest.MineQuest.API.Quest.Quest;
import com.theminequest.MineQuest.API.Quest.QuestDetails;
import com.theminequest.MineQuest.API.Quest.QuestSnapshot;
import com.theminequest.MineQuest.API.Tracker.StatisticManager.Statistic;

@Table("minequest_Quests")
public class QuestStatistic extends Statistic implements Comparable<QuestStatistic> {
	
	@Id
	private long uuid;
		
	@Field
	private String questsGiven;
	
	@Field
	private String questsCompleted;
	
	@Field
	private ArrayList<QuestSnapshot> questsMWSaved;
	
	// NON-PERSISTENT DATA
	private transient List<String> givenQuests;
	private transient Map<String, Long> completedQuests;
	private transient Map<String, QuestSnapshot> mwQuestsRegenerated;
	
	public String[] getGivenQuests(){
		return givenQuests.toArray(new String[givenQuests.size()]);
	}
	
	public Map<String,Long> getCompletedQuests(){
		return Collections.unmodifiableMap(completedQuests);
	}
	
	public QuestSnapshot[] getMainWorldQuests(){
		return mwQuestsRegenerated.values().toArray(new QuestSnapshot[mwQuestsRegenerated.size()]);
	}
	
	public void addGivenQuest(String questName){
		givenQuests.add(questName);
		save();
		QuestGivenEvent e = new QuestGivenEvent(questName,Bukkit.getPlayer(getPlayerName()));
		Bukkit.getPluginManager().callEvent(e);
	}
	
	public void removeGivenQuest(String questName){
		givenQuests.remove(questName);
		save();
	}
	
	public void addCompletedQuest(String questName){
		if (!completedQuests.containsKey(questName))
			completedQuests.put(questName, System.currentTimeMillis());
		save();
	}
	
	public void removeCompletedQuest(String questName){
		completedQuests.remove(questName);
		save();
	}
	
	public void saveMainWorldQuest(Quest quest){
		mwQuestsRegenerated.put((String) quest.getDetails().getProperty(QuestDetails.QUEST_NAME), quest.createSnapshot());
		save();
	}
	
	public void removeMainWorldQuest(String questName){
		mwQuestsRegenerated.remove(questName);
		save();
	}
	
	@Override
	public void setup(){
		if (questsGiven==null)
			questsGiven = "";
		if (questsCompleted==null)
			questsCompleted = "";
		if (questsMWSaved==null)
			questsMWSaved = new ArrayList<QuestSnapshot>();
		if (givenQuests==null)
			givenQuests = new ArrayList<String>(Arrays.asList(questsGiven.split("/")));
		if (completedQuests==null){
			completedQuests = new LinkedHashMap<String,Long>();
			for (String s : questsCompleted.split("/")){
				String[] details = s.split("/:#:/");
				if (details.length > 1) {
					completedQuests.put(details[0], Long.parseLong(details[1]));
				} else if (details.length == 1) {
					completedQuests.put(details[0], System.currentTimeMillis());
				}
			}
		}
		if (mwQuestsRegenerated==null){
			mwQuestsRegenerated = new LinkedHashMap<String, QuestSnapshot>();
			for (QuestSnapshot s : questsMWSaved){
				mwQuestsRegenerated.put((String) s.getDetails().getProperty(QuestDetails.QUEST_NAME), s);
			}
		}
	}
	
	public void save(){		
		questsGiven = "";
		for (String s : givenQuests){
			questsGiven += s + "/";
		}
		if (questsGiven.length()!=0)
			questsGiven = questsGiven.substring(0,questsGiven.length()-1);

		questsCompleted = "";
		for (Map.Entry<String, Long> e : completedQuests.entrySet()){
			questsCompleted += e.getKey() + ":#:" + e.getValue() + "/";
		}
		if (questsCompleted.length()!=0)
			questsCompleted = questsCompleted.substring(0,questsCompleted.length()-1);
		
		questsMWSaved.clear();
		questsMWSaved.addAll(mwQuestsRegenerated.values());

		Managers.getStatisticManager().setStatistic(this, getClass());
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof QuestStatistic))
			return false;
		return getPlayerName().equals(((QuestStatistic)obj).getPlayerName());
	}

	@Override
	public int compareTo(QuestStatistic other) {
		return getPlayerName().compareTo(other.getPlayerName());
	}

}
