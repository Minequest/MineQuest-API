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

public class MultiVector {
	
	public static final MultiVector FORWARD = new MultiVector(0, 0, 1);
	public static final MultiVector ONE = new MultiVector(1, 1, 1);
	public static final MultiVector RIGHT = new MultiVector(1, 0, 0);
	public static final MultiVector UP = new MultiVector(0, 1, 0);
	
	private double x, y, z;
	
	public MultiVector(double x, double y, double z) {
		this.x = x;
		this.y = y; 
		this.z = z;
	}
	
	public MultiVector(MultiVector other) {
		this.x = other.x;
		this.y = other.y;
		this.z = other.z;
	}
	
	public MultiVector add(double x, double y, double z) {
		return new MultiVector(this.x + x, this.y + y, this.z + z);
	}
	
	public MultiVector add(MultiVector other) {
		return new MultiVector(x + other.x, y + other.y, z + other.z);
	}
	
	public MultiVector multiply(double x, double y, double z) {
		return new MultiVector(this.x * x, this.y * y, this.z * z);
	}
	
	public MultiVector multiply(MultiVector other) {
		return new MultiVector(x * other.x, y * other.y, z * other.z);
	}
	
	public MultiVector floor() {
		return new MultiVector(Math.floor(x), Math.floor(y), Math.floor(z));
	}
	
	public MultiVector round() {
		return new MultiVector(Math.round(x), Math.round(y), Math.round(z));
	}
	
	public double distance(MultiVector vector) {
		return Math.sqrt(Math.pow(x - vector.x, 2) + Math.pow(y - vector.y, 2) + Math.pow(z - vector.z, 2));
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;
	}

	@Override
	public boolean equals(Object obj) {
		MultiVector other = (MultiVector) obj;
		return x == other.x && y == other.y && z == other.z;
	}

	@Override
	public int hashCode() {
		int baseHash = 814;
		baseHash = (int) (31 * baseHash + x);
		baseHash = (int) (31 * baseHash + y);
		baseHash = (int) (31 * baseHash + z);
		return baseHash;
	}

	
}
