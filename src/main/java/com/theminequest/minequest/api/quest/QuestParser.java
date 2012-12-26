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
package com.theminequest.minequest.api.quest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;

import org.mozilla.universalchardet.UniversalDetector;

import com.theminequest.minequest.api.Managers;

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
	
	private Map<String,Class<? extends QHandler>> methods;
	private UniversalDetector encodingDetector;
	
	public QuestParser() {
		methods = Collections.synchronizedMap(new LinkedHashMap<String,Class<? extends QHandler>>());
		encodingDetector = new UniversalDetector(null);
	}
	
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
	
	public void parseDefinition(QuestDetails questDetails) throws IOException {
		File questFile = (File)questDetails.getProperty(QuestDetails.QUEST_FILE);
		
		// detect encoding
		FileInputStream encodingStreamDetect = null;
		String encoding = null;
		try {
			encodingStreamDetect = new FileInputStream(questFile);
			encodingDetector.reset();
			byte[] detectorBuffer = new byte[4096];
			int nread;
			while ((nread = encodingStreamDetect.read(detectorBuffer)) > 0 && !encodingDetector.isDone()) {
				encodingDetector.handleData(detectorBuffer, 0, nread);
			}
			encodingDetector.dataEnd();
			encoding = encodingDetector.getDetectedCharset();
		} finally {
			if (encodingStreamDetect != null)
				encodingStreamDetect.close();
		}
		
		Scanner fileReader = null;
		try {
			if (encoding == null)
				fileReader = new Scanner(questFile);
			else
				fileReader = new Scanner(questFile,encoding);
			while (fileReader.hasNextLine()) {
				String nextline = new String(fileReader.nextLine().getBytes("UTF-8"),"UTF-8");
				if (nextline.startsWith("#")) // ignore and continue
					continue;
				LinkedList<String> ar = new LinkedList<String>();
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
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} finally {
			if (fileReader != null)
				fileReader.close();
		}
	}
	
}
