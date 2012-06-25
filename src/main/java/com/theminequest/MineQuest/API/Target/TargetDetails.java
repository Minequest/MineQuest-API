package com.theminequest.MineQuest.API.Target;

import java.io.Serializable;

public class TargetDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7523749925764167755L;

	public enum TargetType implements Serializable {
		AREATARGET,AREATARGETQUESTER,TEAMTARGET,TARGETTER,TARGETTEREDIT,RANDOMTARGET;
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
