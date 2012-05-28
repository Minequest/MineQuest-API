package com.theminequest.MineQuest.API.Quest;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;

import com.theminequest.MineQuest.API.Managers;
import com.theminequest.MineQuest.API.Edit.Edit;
import com.theminequest.MineQuest.API.Target.TargetDetails;

public class QuestParser {
	
	/**
	 * Indicate that this class can handle Quest file details.
	 * @author Robert Xu <robxu9@gmail.com>
	 *
	 */
	public static interface QHandler {
		
		/**
		 * Parse the details from this line of the quest file.<br>
		 * If a line contains:<br>
		 * <code>ID:tas1,tas2,tas3:444</code><br>
		 * Then the line is split by <code>:</code>. This line
		 * does NOT contain detail type. (In example above, <code>ID</code>
		 * will be omitted.)
		 * @param q QuestDetails to manipulate
		 * @param line Details
		 */
		void parseDetails(QuestDetails q, List<String> line);
		
	}

	private Map<String,Class<? extends QHandler>> methods = Collections.synchronizedMap(new LinkedHashMap<String,Class<? extends QHandler>>());
	
	/**
	 * Register a QHandler for use in parsing Quests.
	 * @param name Name to associate with
	 * @param c Class that implements QHandler
	 */
	public void addClassHandler(String name, Class<? extends QHandler> c){
		methods.put(name.toLowerCase(), c);
	}
	
	/**
	 * Deregisters a QHandler for use in parsing Quests.
	 * @param name Name to remove.
	 */
	public void rmClassHandler(String name){
		methods.remove(name.toLowerCase());
	}
	
	protected void parseDefinition(QuestDetails questDetails) throws FileNotFoundException{
		questDetails.setProperty(QuestDetails.QUEST_TASKS,new LinkedHashMap<Integer,Integer[]>(0));
		questDetails.setProperty(QuestDetails.QUEST_EVENTS,new LinkedHashMap<Integer, String>(0));
		questDetails.setProperty(QuestDetails.QUEST_TARGETS,new LinkedHashMap<Integer, TargetDetails>(0));
		questDetails.setProperty(QuestDetails.QUEST_EDITS,new LinkedHashMap<Integer,Edit>(0));
		File f = questDetails.getProperty(QuestDetails.QUEST_FILE);
		Scanner filereader = new Scanner(f);
		while (filereader.hasNextLine()) {
			String nextline = filereader.nextLine();
			ArrayList<String> ar = new ArrayList<String>();
			for (String s : nextline.split(":"))
				ar.add(s);
			String type = ar.get(0).toLowerCase();
			Class<? extends QHandler> c = methods.get(type);
			if (c==null)
				continue;
			Method m;
			try {
				m = c.getMethod("parseDetails", QuestDetails.class, List.class);
			} catch (SecurityException e1) {
				e1.printStackTrace();
				continue;
			} catch (NoSuchMethodException e1) {
				e1.printStackTrace();
				continue;
			}
			/*
			 * We don't need the type in the array we pass,
			 * so we remove it.
			 */
			ar.remove(0);
			try {
				m.invoke(c.newInstance(), questDetails, ar);
			} catch (Exception e1){
				e1.printStackTrace();
				Managers.log(Level.WARNING, "[Parser] Unable to launch parser " + c.getName() +"...");
			}
		}
	}
	
}
