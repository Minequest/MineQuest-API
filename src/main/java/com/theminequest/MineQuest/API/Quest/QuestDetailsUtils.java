package com.theminequest.MineQuest.API.Quest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.bukkit.entity.Player;

import com.theminequest.MineQuest.API.Utils.ChatUtils;
import com.theminequest.MineQuest.API.Utils.FastByteArrayOutputStream;

public class QuestDetailsUtils {
	
	/**
	 * Get a deep copy of this QuestDetails object. This allows
	 * for the object to be modified without any adverse side-effects
	 * and does not affect the original <b>master</b> object.<br>
	 * <h5>WARNING:</h5> This method uses serialization and a
	 * byte-based stream to accomplish deep copying. As such, it is an
	 * intensive operation and should not be used lightly by
	 * methods. In particular, this method is used by classes implementing
	 * {@link com.theminequest.MineQuest.API.Quest.QuestManager}
	 * to retrieve a mutable object for every quest started.
	 * <br>
	 * <b>Note that this implementation is not synchronized.</b>
	 * As such, you must be aware of threads that may modify this object
	 * while copying it.
	 * @param d Details to copy
	 * @return completely independent copy of the object
	 * @throws IOException if object could not be copied
	 */
	public static QuestDetails getCopy(QuestDetails d) throws IOException{
		FastByteArrayOutputStream bos = new FastByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(bos);
		os.writeObject(d);
		os.flush();
		os.close();
		
		ObjectInputStream is = new ObjectInputStream(bos.getInputStream());
		QuestDetails q;
		try {
			q = (QuestDetails) is.readObject();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} finally {
			is.close();
		}
		return q;
	}
	
	/**
	 * Retrieve the overview of the quest. (Human-Readable)
	 * @param d Details to parse
	 * @return Quest Overview
	 */
	public static String getOverviewString(QuestDetails d) {
		String tr = "";
		tr+=ChatUtils.formatHeader((String) d.getProperty(QuestDetails.QUEST_DISPLAYNAME))+"\n";
		tr+=ChatUtils.chatify((String) d.getProperty(QuestDetails.QUEST_DESCRIPTION))+"\n";
		return tr;
	}
	
	/**
	 * Check to see if a player meets the requirements for this
	 * quest to be made available to the player.
	 * @param d Quest to retrieve requirements from
	 * @param p Player to check (Leader usually)
	 * @return true if requirements are met
	 */
	public static boolean requirementsMet(QuestDetails d, Player p){
		List<QuestRequirement> requirements = d.getProperty(QuestDetails.QUEST_REQUIREMENTS);
		for (QuestRequirement q : requirements){
			if (!q.isSatisfied(p))
				return false;
		}
		return true;
	}

}
