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

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface MQInventory extends Serializable, List<MQItemStack> {
	
	void setData(int slot, int data);
	boolean addData(int slot, int amount);
	void setAmount(int slot, int amount);
	boolean addAmount(int slot, int amount);
	int getAmount(MQMaterial material);
	Map<Integer,MQItemStack> getAll(MQMaterial material);
	boolean contains(MQMaterial material, int amount);
	boolean containsExactly(MQMaterial material, int amount);
	void add(int firstSlot, int lastSlot, MQItemStack item);
	
}
