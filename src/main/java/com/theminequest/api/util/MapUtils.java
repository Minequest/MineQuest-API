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
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MapUtils {
	
	@SuppressWarnings({ "unchecked" })
	public static <E> List<E> convert(Set<E> s){
		List<E> toreturn = new ArrayList<E>();
		Iterator<?> i = s.iterator();
		while (i.hasNext()){
			toreturn.add((E) i.next());
		}
		return toreturn;
	}
	
	public static List<Comparable> sort(Set<? extends Comparable> s){
		List<Comparable> l = new ArrayList<Comparable>();
		for (Comparable c : s){
			l.add(c);
		}
		MergeSort m = new MergeSort(l);
		m.sort();
		return m.getSorted();
	}
	
	@SuppressWarnings("rawtypes")
	private static class MergeSort {
		
		private Comparable[] temp;
		private List<Comparable> tosort;
		
		public MergeSort(List<Comparable> array){
			tosort = array;
			temp = new Comparable[array.size()];
		}
		
		public void sort(){
			sort(tosort,0,tosort.size()-1);
		}
		
		
		private void sort(List<Comparable> array, int from, int to){
			
			if (to == from)
				return;
			
			int middle = (from+to)/2;
			
			sort(array, middle+1,to);
			sort(array, from, middle);
			
			int i = from, j = middle+1, k = from;
			
			while (i<=middle && j<=to){
				if (array.get(i).compareTo(array.get(j))<0)
					temp[k++] = array.get(i++);
				else
					temp[k++] = array.get(j++);
			}
			
			while (i<=middle)
				temp[k++] = array.get(i++);
			while (j<=to)
				temp[k++] = array.get(j++);
			
			for (k = from; k<= to; k++)
				array.set(k, temp[k]);
		}
		
		public List<Comparable> getSorted(){
			return tosort;
		}
	}

}
