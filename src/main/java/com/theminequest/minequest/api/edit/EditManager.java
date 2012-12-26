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
package com.theminequest.minequest.api.edit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.theminequest.minequest.api.Managers;
import com.theminequest.minequest.api.group.QuestGroup;
import com.theminequest.minequest.api.group.QuestGroup.QuestStatus;
import com.theminequest.minequest.api.platform.MQBlock;
import com.theminequest.minequest.api.platform.MQItemStack;
import com.theminequest.minequest.api.platform.MQPlayer;

public class EditManager {
	
	private final List<Edit> edits;
	
	public EditManager(){
		Managers.log("[Edit] Starting Manager...");
		edits = Collections.synchronizedList(new ArrayList<Edit>());
	}
	
	public void addEditTracking(Edit e){
		if (!edits.contains(e))
			edits.add(e);
	}
	
	public void rmEditTracking(Edit e){
		if (edits.contains(e))
			edits.remove(e);
	}
	
	public boolean onBlockPlace(MQBlock block, MQItemStack inHand, MQPlayer player){
		long id = Managers.getGroupManager().indexOf(player);
		if (id==-1)
			return true;
		if (((QuestGroup) Managers.getGroupManager().get(id)).getQuestStatus()!=QuestStatus.INQUEST)
			return true;

		boolean returnVal = true;
		
		for (Edit ed : edits){
			if (!ed.onBlockPlace(block, inHand, player))
				returnVal = false;
		}
		return returnVal;
	}
	
	public boolean onBlockDamage(MQBlock block, MQItemStack inHand, MQPlayer player){
		long id = Managers.getGroupManager().indexOf(player);
		if (id==-1)
			return true;
		if (((QuestGroup) Managers.getGroupManager().get(id)).getQuestStatus()!=QuestStatus.INQUEST)
			return true;

		boolean returnVal = true;
		
		for (Edit ed : edits){
			if (!ed.onBlockDamage(block, inHand, player))
				returnVal = false;
		}
		return returnVal;
	}
}
