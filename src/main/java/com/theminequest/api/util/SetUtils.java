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
package com.theminequest.api.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

public class SetUtils {
	
	public static Integer getFirstKey(Set<Integer> s) {
		int first = Integer.MAX_VALUE;
		Iterator<Integer> it = s.iterator();
		while (it.hasNext()) {
			int i = it.next();
			if (i < first)
				first = i;
		}
		return first;

	}

	public static ArrayList<Integer> getSortedKeys(Set<Integer> s) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		Iterator<Integer> i = s.iterator();
		while (i.hasNext())
			a.add(i.next());
		Collections.sort(a);
		return a;
	}

}
