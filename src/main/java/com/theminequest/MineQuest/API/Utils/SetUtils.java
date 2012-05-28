package com.theminequest.MineQuest.API.Utils;

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
