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

import com.theminequest.minequest.api.platform.Location;
import com.theminequest.minequest.api.platform.MQBlock;
import com.theminequest.minequest.api.platform.MQItemStack;
import com.theminequest.minequest.api.platform.MQPlayer;

public abstract class AreaEdit extends Edit {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1333653703301827692L;
	private int oneX;
	private int oneY;
	private int oneZ;

	private int twoX;
	private int twoY;
	private int twoZ;

	public AreaEdit(int eid, int tid, String d) {
		super(eid, tid, d);
	}

	public boolean isInArea(Location l){
		int minx = Math.min(oneX, twoX),
				miny = Math.min(oneY, twoY),
				minz = Math.min(oneZ, twoZ),
				maxx = Math.max(oneX, twoX),
				maxy = Math.max(oneY, twoY),
				maxz = Math.max(oneZ, twoZ);
		int locX = (int) Math.round(l.getX());
		int locY = (int) Math.round(l.getY());
		int locZ = (int) Math.round(l.getZ());
		for(int x = minx; x<=maxx;x++){
			for(int y = miny; y<=maxy;y++){
				for(int z = minz; z<=maxz;z++){
					if (locX==x && locY==y && locZ==z)
						return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean allowEdit(MQBlock b, MQItemStack i, MQPlayer p) {
		return (isInArea(b.getLocation())==isInside());
	}
	
	@Override
	public void parseDetails(String d){
		String[] s = d.split(":");
		oneX = Integer.parseInt(s[0]);
		oneY = Integer.parseInt(s[1]);
		oneZ = Integer.parseInt(s[2]);
		twoX = Integer.parseInt(s[3]);
		twoY = Integer.parseInt(s[4]);
		twoZ = Integer.parseInt(s[5]);
	}

	/**
	 * For lack of better use, return true you want to allow
	 * edits inside the area.
	 * @return true if allow edits inside the area
	 */
	public abstract boolean isInside();

}
