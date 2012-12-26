
package com.theminequest.minequest.api.edit;

import com.theminequest.minequest.api.platform.MQBlock;
import com.theminequest.minequest.api.platform.MQItemStack;
import com.theminequest.minequest.api.platform.MQPlayer;

public class CertainBlockEdit extends Edit {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2586999166842874789L;
	private String[] blockIDs;
	
	public CertainBlockEdit(int eid, int tid, String d) {
		super(eid, tid, d);
	}

	@Override
	public boolean allowEdit(MQBlock b, MQItemStack i, MQPlayer p) {
		for (String s : blockIDs){
			if (Integer.parseInt(s)==b.getMaterialID())
				return true;
		}
		return false;
	}

	@Override
	public void parseDetails(String d) {
		blockIDs = d.split(",");
	}

}
