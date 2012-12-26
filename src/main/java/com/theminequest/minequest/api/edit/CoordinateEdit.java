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
package com.theminequest.minequest.api.edit;

import com.theminequest.minequest.api.platform.Location;
import com.theminequest.minequest.api.platform.MQBlock;
import com.theminequest.minequest.api.platform.MQItemStack;
import com.theminequest.minequest.api.platform.MQPlayer;

public class CoordinateEdit extends Edit {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4153261092499281453L;
	private int coordX;
	private int coordY;
	private int coordZ;

	public CoordinateEdit(int eid, int tid, String d) {
		super(eid, tid, d);
	}

	@Override
	public boolean allowEdit(MQBlock b, MQItemStack i, MQPlayer p) {
		Location loc = b.getLocation().floor();
		if (loc.getX()==coordX && loc.getY()==coordY && loc.getZ()==coordZ)
			return true;
		return false;
	}

	@Override
	public void parseDetails(String d) {
		String[] coords = d.split(":");
		coordX = Integer.parseInt(coords[0]);
		coordY = Integer.parseInt(coords[1]);
		coordZ = Integer.parseInt(coords[2]);
	}

}
