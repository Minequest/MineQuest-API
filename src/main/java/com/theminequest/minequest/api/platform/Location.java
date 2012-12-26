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
package com.theminequest.minequest.api.platform;

public class Location extends MultiVector {

	private String world;
	
	public Location(String world, double x, double y, double z) {
		super(x,y,z);
		this.world = world;
	}
	
	public Location(String world, MultiVector vector) {
		super(vector);
		this.world = world;
	}
	
	@Override
	public Location add(double x, double y, double z) {
		return new Location(world, super.add(x,y,z));
	}

	@Override
	public Location add(MultiVector other) {
		return new Location(world, super.add(other));
	}

	@Override
	public Location multiply(double x, double y, double z) {
		return new Location(world, super.multiply(x, y, z));
	}

	@Override
	public Location multiply(MultiVector other) {
		return new Location(world, super.multiply(other));
	}

	@Override
	public Location floor() {
		return new Location(world, super.floor());
	}

	@Override
	public Location round() {
		return new Location(world, super.round());
	}
	
	public MultiVector getVector() {
		return this;
	}

	public String getWorld() {
		return world;
	}
	
	public Location setWorld(String world) {
		return new Location(world, this);
	}
	
	
}
