package com.theminequest.MineQuest.API.Quest;

import static com.theminequest.MineQuest.API.Quest.QuestDetails.*;

import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import com.theminequest.MineQuest.API.Events.QuestEvent;
import com.theminequest.MineQuest.API.Events.UserQuestEvent;
import com.theminequest.MineQuest.API.Target.TargetDetails;
import com.theminequest.MineQuest.API.Utils.ChatUtils;

public class QuestUtils {
	
	public static String getStatusString(Quest q){
		String tr = QuestDetailsUtils.getOverviewString(q.getDetails()) + "\n";
		if (q.getActiveTask()!=null){
			tr += ChatUtils.formatHeader("Current Tasks") + "\n";
			for (QuestEvent e : q.getActiveTask().getEvents()){
				if (e instanceof UserQuestEvent){
					String description = ((UserQuestEvent)e).getDescription();
					if (e.isComplete()==null)
						tr += ChatColor.GREEN + "- " + description + "\n";
					else
						tr += ChatColor.GRAY + "- " + description + "\n";
				}
			}
		}
		return tr;
	}
	
	/**
	 * Get all possible events
	 * 
	 * @return all possible events (# association)
	 */
	public Set<Integer> getEventNums(Quest q) {
		Map<Integer,String> events = q.getDetails().getProperty(QUEST_EVENTS);
		return events.keySet();
	}
	
	public static Location getSpawnLocation(Quest q){
		double[] xyzcoords = q.getDetails().getProperty(QUEST_SPAWNPOINT);
		return new Location(Bukkit.getWorld((String) q.getDetails().getProperty(QUEST_WORLD)),
				xyzcoords[0], xyzcoords[1], xyzcoords[2]);
	}
	
	public static TargetDetails getTargetDetails(Quest q, int targetID){
		Map<Integer,TargetDetails> targets = q.getDetails().getProperty(QUEST_TARGETS);
		return targets.get(targetID);
	}

}
