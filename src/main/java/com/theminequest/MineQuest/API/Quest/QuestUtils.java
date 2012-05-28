package com.theminequest.MineQuest.API.Quest;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import com.theminequest.MineQuest.API.Events.QuestEvent;
import com.theminequest.MineQuest.API.Events.UserQuestEvent;
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
	
	public static Location getSpawnLocation(Quest q){
		@SuppressWarnings("unchecked")
		List<Double> xyzcoords = (List<Double>) q.getDetails().getProperty(QuestDetails.QUEST_SPAWNPOINT);
		return new Location(Bukkit.getWorld((String) q.getDetails().getProperty(QuestDetails.QUEST_WORLD)),
				xyzcoords.get(0), xyzcoords.get(1), xyzcoords.get(2));
	}

}
