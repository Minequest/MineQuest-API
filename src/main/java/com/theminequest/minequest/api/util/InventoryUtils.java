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
package com.theminequest.minequest.api.util;

import java.util.Collection;

import com.theminequest.minequest.api.platform.MQInventory;
import com.theminequest.minequest.api.platform.MQItemStack;
import com.theminequest.minequest.api.platform.MQMaterial;

public class InventoryUtils {
	
	public static boolean inventoryContains(MQInventory inv, MQMaterial material, int data, int amount) {
		Collection<? extends MQItemStack> stacks = inv.getAll(material).values();
		if (stacks.size() == 0)
			return false;
		int count = 0;
		for (MQItemStack stack : stacks) {
//			if (material.equals(Material.POTION)) {
//				if (stack.getDurability() == data) {
//					count += stack.getAmount();
//				}
//			} else if (stack.getData() == data) {
//				count += stack.getAmount();
//			}
			if (stack.getData() == data)
				count += stack.getAmount();
		}
		return count >= amount;
	}
}
