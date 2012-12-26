/*
 * This file is part of MineQuest-API, version 3, Specifications for the MineQuest system.
 * MineQuest-API, version 3 is licensed under GNU Lesser General Public License v3.
 * Copyright (C) 2012 The MineQuest Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.theminequest.minequest.api.quest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

import com.theminequest.minequest.api.platform.MQPlayer;
import com.theminequest.minequest.api.requirement.QuestRequirement;
import com.theminequest.minequest.api.util.ChatUtils;
import com.theminequest.minequest.api.util.FastByteArrayOutputStream;

public class QuestDetailsUtils {
	
	public static final String DESC_NEWLINE_SEQ = "<br>";
	public static final String CODE_NEWLINE_SEQ = "<br>";
	
	/**
	 * Get a deep copy of this QuestDetails object. This allows
	 * for the object to be modified without any adverse side-effects
	 * and does not affect the original <b>master</b> object.<br>
	 * <h5>WARNING:</h5> This method uses serialization and a
	 * byte-based stream to accomplish deep copying. As such, it is an
	 * intensive operation and should not be used lightly by
	 * methods. In particular, this method is used by classes implementing
	 * {@link com.theminequest.minequest.api.quest.QuestManager}
	 * to retrieve a mutable object for every quest started.
	 * <br>
	 * <b>Note that this implementation is not synchronized.</b>
	 * As such, you must be aware of threads that may modify this object
	 * while copying it.
	 * @param d Details to copy
	 * @return completely independent copy of the object
	 */
	public static QuestDetails getCopy(QuestDetails d) {
		FastByteArrayOutputStream bos = new FastByteArrayOutputStream();
		ObjectOutputStream os = null;
		try {
			os = new ObjectOutputStream(bos);
			os.writeObject(d);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					// memory leak!
					throw new RuntimeException(e);
				}
			}
		}
		
		ObjectInputStream is = null;
		QuestDetails q = null;
		try {
			is = new ObjectInputStream(bos.getInputStream());
			q = (QuestDetails) is.readObject();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// memory leak!
					throw new RuntimeException(e);
				}
			}
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
		tr+=ChatUtils.formatHeader((String) d.getProperty(QuestDetails.QUEST_DISPLAYNAME))+CODE_NEWLINE_SEQ;
		String description = d.getProperty(QuestDetails.QUEST_DESCRIPTION);
		String[] split = description.split(DESC_NEWLINE_SEQ);
		for (String s : split)
			tr += ChatUtils.chatify(s) + CODE_NEWLINE_SEQ;
		return tr;
	}
	
	/**
	 * Examines if the initial requirements are met for this player to get this quest.
	 * @param details Quest to check on
	 * @param player Player to check
	 * @return true if requirements are met
	 */
	public static boolean getRequirementsMet(QuestDetails details, MQPlayer player) {
		Map<Integer,QuestRequirement> reqs = details.getProperty(QuestDetails.QUEST_REQUIREMENTDETAILS);
		List<Integer> getreqs = details.getProperty(QuestDetails.QUEST_GETREQUIREMENTS);
		
		for (Integer i : getreqs) {
			if (reqs.containsKey(i)) {
				if (!reqs.get(i).isSatisfied(player))
					return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Examines if the starting requirements are met for this player to start this quest.<br>
	 * This only works for instanced quests; e.g. non-instanced quests will return true.
	 * @param details Quest to check on
	 * @param player Player to check
	 * @return true if requirements are met
	 */
	public static boolean startRequirementsMet(QuestDetails details, MQPlayer player) {
		Boolean isInstanced = details.getProperty(QuestDetails.QUEST_LOADWORLD);
		if (!isInstanced)
			return true;
		Map<Integer,QuestRequirement> reqs = details.getProperty(QuestDetails.QUEST_REQUIREMENTDETAILS);
		List<Integer> getreqs = details.getProperty(QuestDetails.QUEST_STARTREQUIREMENTS);
		
		for (Integer i : getreqs) {
			if (reqs.containsKey(i)) {
				if (!reqs.get(i).isSatisfied(player))
					return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Examines if the specified requirement is met for this player.
	 * @param details Quest to check on
	 * @param player Player to check
	 * @return true if requirements are met
	 */
	public static boolean requirementMet(QuestDetails details, int id, MQPlayer player) {
		Map<Integer,QuestRequirement> reqs = details.getProperty(QuestDetails.QUEST_REQUIREMENTDETAILS);
		if (!reqs.containsKey(id))
			return false;
		return reqs.get(id).isSatisfied(player);
	}
	
}
