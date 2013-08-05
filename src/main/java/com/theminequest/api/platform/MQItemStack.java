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

import com.theminequest.api.Managers;

public class MQItemStack {
	
	private MQMaterial material;
	private int amount;
	private int data; // flags or other data, impl. specific
	
	public MQItemStack(MQMaterial material, int amount) {
		this(material, amount, 0);
	}
	
	public MQItemStack(MQMaterial material, int amount, int data) {
		this.material = material;
		this.amount = amount;
		this.data = data;
	}

	public MQItemStack(String item, int amount, int data) {
		this.material = Managers.getPlatform().findMaterial(item);
		this.amount = amount;
		this.data = data;
	}

	public int getAmount() {
		return amount;
	}
	
	public int getData() {
		return data;
	}
	
	public MQMaterial getMaterial() {
		return material;
	}
	
	public boolean isEmpty() {
		return amount == 0;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public void setData(int data) {
		this.data = data;
	}
	
	public void setMaterial(MQMaterial material) {
		this.material = material;
	}
	
}
