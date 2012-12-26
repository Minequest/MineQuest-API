package com.theminequest.minequest.api.edit;

public class InsideAreaEdit extends AreaEdit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2170504313888455702L;

	public InsideAreaEdit(int eid, int tid, String d) {
		super(eid, tid, d);
	}

	@Override
	public boolean isInside() {
		return true;
	}

}
