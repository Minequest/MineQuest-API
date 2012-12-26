package com.theminequest.minequest.api.edit;

public class OutsideAreaEdit extends AreaEdit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2698391472980234738L;

	public OutsideAreaEdit(int eid, int tid, String d) {
		super(eid, tid, d);
	}

	@Override
	public boolean isInside() {
		return false;
	}

}
