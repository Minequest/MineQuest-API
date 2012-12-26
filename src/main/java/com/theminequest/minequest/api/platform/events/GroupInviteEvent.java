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
package com.theminequest.minequest.api.platform.events;

import com.theminequest.minequest.api.platform.MQEvent;
import com.theminequest.minequest.api.platform.MQPlayer;

public class GroupInviteEvent extends MQEvent {

	public static final String NAME = "GroupInvite";
	
	private String invitername;
	private MQPlayer invited;
	private long teamid;
	
	public GroupInviteEvent(String inviter, MQPlayer invited, long teamid){
		super(NAME);
		invitername = inviter;
		this.invited = invited;
		this.teamid = teamid;
	}
	
	public String getInviterName(){
		return invitername;
	}
	
	public MQPlayer getInvited(){
		return invited;
	}
	
	public long getTeamId(){
		return teamid;
	}

}
