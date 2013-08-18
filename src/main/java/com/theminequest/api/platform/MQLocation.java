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
package com.theminequest.api.platform;

public class MQLocation extends MultiVector {

	private String world;
	
	public MQLocation(String world, double x, double y, double z) {
		super(x,y,z);
		this.world = world;
	}
	
	public MQLocation(String world, MultiVector vector) {
		super(vector);
		this.world = world;
	}
	
	@Override
	public MQLocation add(double x, double y, double z) {
		return new MQLocation(world, super.add(x,y,z));
	}

	@Override
	public MQLocation add(MultiVector other) {
		return new MQLocation(world, super.add(other));
	}

	@Override
	public MQLocation multiply(double x, double y, double z) {
		return new MQLocation(world, super.multiply(x, y, z));
	}

	@Override
	public MQLocation multiply(MultiVector other) {
		return new MQLocation(world, super.multiply(other));
	}

	@Override
	public MQLocation floor() {
		return new MQLocation(world, super.floor());
	}

	@Override
	public MQLocation round() {
		return new MQLocation(world, super.round());
	}
	
	public MQLocation set(double x, double y, double z) {
		return new MQLocation(world, x, y, z);
	}
	
	public MultiVector getVector() {
		return this;
	}

	public String getWorld() {
		return world;
	}
	
	public MQLocation setWorld(String world) {
		return new MQLocation(world, this);
	}

	@Override
	public boolean equals(Object obj) {
		MQLocation loc = (MQLocation) obj;
		return world.equals(loc.world) && super.equals(obj);
	}

	@Override
	public int hashCode() {
		return 31 * world.hashCode() * super.hashCode();
	}
	
	
}
