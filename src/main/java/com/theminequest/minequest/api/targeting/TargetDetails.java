/*
 * This file is part of MineQuest-API, version 2, Specifications for the MineQuest system.
 * MineQuest-API, version 2 is licensed under GNU Lesser General Public License v3.
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
package com.theminequest.minequest.api.targeting;

import java.io.Serializable;

/**
 * 
 * @deprecated Since 3.0.0 : Represented old and inflexible
 * targeting. Should not be used, and instead replaced with
 * abstract class Target. This class, and all corresponding
 * methods are slated for removal by 3.5.0.
 *
 */
@Deprecated
public class TargetDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7523749925764167755L;

	public enum TargetType implements Serializable {
		AREATARGET,AREATARGETQUESTER,TEAMTARGET,TARGETTER,TARGETTEREDIT,RANDOMTARGET,LEADER;
	}
	
	private TargetType type;
	private String details;
	
	public TargetDetails(TargetType type, String details){
		this.type = type;
		this.details = details;
	}
	
	public TargetDetails(String details){
		String[] info = details.split(":");
		String type = info[0].toLowerCase();
		if (type.equals("areatarget"))
			this.type = TargetType.AREATARGET;
		else if (type.equals("areatargetquester"))
			this.type = TargetType.AREATARGETQUESTER;
		else if (type.equals("partytarget")||type.equals("teamtarget"))
			this.type = TargetType.TEAMTARGET;
		else if (type.equals("targetter"))
			this.type = TargetType.TARGETTER;
		else if (type.equals("targetteredit"))
			this.type = TargetType.TARGETTEREDIT;
		else if (type.equals("randomtarget"))
			this.type = TargetType.RANDOMTARGET;
		else if (type.equals("leader"))
			this.type = TargetType.LEADER;
		if (info.length > 1)
			this.details = details.substring(details.indexOf(':') + 1);
	}
	
	public TargetType getType(){
		return type;
	}
	
	public String getDetails(){
		return details;
	}
	
}
