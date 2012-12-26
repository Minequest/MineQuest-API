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
package com.theminequest.minequest.api.group;

public class GroupException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1073787842559540982L;
	
	private GroupReason groupReason;

	public GroupException(GroupReason c){
		super(c.name());
		groupReason = c;
	}

	public GroupException(Throwable arg0) {
		super(GroupReason.EXTERNALEXCEPTION.name(), arg0);
		groupReason = GroupReason.EXTERNALEXCEPTION;
		// TODO Auto-generated constructor stub
	}

	public GroupException(GroupReason c, Throwable arg1) {
		super(c.name(), arg1);
		groupReason = c;
		// TODO Auto-generated constructor stub
	}
	
	public GroupReason getReason(){
		return groupReason;
	}
	
	public enum GroupReason {
		BADCAPACITY, ALREADYONQUEST, INSIDEQUEST, NOTINSIDEQUEST, NOQUEST,
		ALREADYINTEAM, NOTONTEAM, NOLOCATIONS, UNFINISHEDQUEST, EXTERNALEXCEPTION, MAINWORLDQUEST, NOTMAINWORLDQUEST,
		REQUIREMENTSNOTFULFILLED;
	}

}
