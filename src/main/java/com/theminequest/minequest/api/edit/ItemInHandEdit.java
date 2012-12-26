package com.theminequest.minequest.api.edit;

import com.theminequest.minequest.api.platform.MQBlock;
import com.theminequest.minequest.api.platform.MQItemStack;
import com.theminequest.minequest.api.platform.MQPlayer;

public class ItemInHandEdit extends Edit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8325582887415696504L;
	private String[] materialsID;

	public ItemInHandEdit(int eid, int tid, String d) {
		super(eid, tid, d);
	}

	@Override
	public boolean allowEdit(MQBlock b, MQItemStack i, MQPlayer p) {
		for (String m : materialsID){
			if (Integer.parseInt(m)==i.getMaterial().getId())
				return true;
		}
		return false;
	}

	@Override
	public void parseDetails(String d) {
		materialsID = d.split(",");
	}

	

}
