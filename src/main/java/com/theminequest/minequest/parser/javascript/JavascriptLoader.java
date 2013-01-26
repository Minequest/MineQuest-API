package com.theminequest.minequest.parser.javascript;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.universalchardet.UniversalDetector;

import com.theminequest.minequest.api.quest.QuestDetails;
import com.theminequest.minequest.api.util.PropertiesFile;

public class JavascriptLoader {
	
	public static final String LOADER_LOC = "plugins/MineQuest/scripts";
	
	private UniversalDetector encodingDetector;
	
	public JavascriptLoader() {
		encodingDetector = new UniversalDetector(null);
	}
	
	// @Override
	public QuestDetails loadQuest(File file) {
		// FIXME
		return null;
	}
	
	public QuestDetails loadJavaScriptQuest(File propertiesFile, File javascriptFile) throws IOException {
		
		if (!(propertiesFile.exists() && propertiesFile.isFile()))
			throw new IllegalArgumentException("Require existing Quest Properties file!");
		
		if (!(javascriptFile.exists() && javascriptFile.isFile()))
			throw new IllegalArgumentException("Require existing Quest Javascript file!");
		
		/*
		 * Load Properties
		 */
		PropertiesFile properties = new PropertiesFile(propertiesFile);
		Set<String> keys = properties.keySet();
		
		/*
		 * Get JavaScript encoding
		 */
		FileInputStream javaScriptEncodingStream = null;
		String javaScriptEncoding = null;
		try {
			javaScriptEncodingStream = new FileInputStream(propertiesFile);
			encodingDetector.reset();
			byte[] detectorBuffer = new byte[4096];
			int nread;
			while ((nread = javaScriptEncodingStream.read(detectorBuffer)) > 0 && !encodingDetector.isDone()) {
				encodingDetector.handleData(detectorBuffer, 0, nread);
			}
			encodingDetector.dataEnd();
			javaScriptEncoding = encodingDetector.getDetectedCharset();
		} finally {
			if (javaScriptEncodingStream != null)
				javaScriptEncodingStream.close();
		}
		
		/*
		 * Load JavaScript file
		 */
		InputStreamReader javaScriptReader = null;
		String javaScriptFile = null;
		try {
			if (javaScriptEncoding == null)
				javaScriptReader = new InputStreamReader(new FileInputStream(propertiesFile));
			else
				javaScriptReader = new InputStreamReader(new FileInputStream(propertiesFile), javaScriptEncoding);
			
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = javaScriptReader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			javaScriptFile = builder.toString();
		} finally {
			if (javaScriptReader != null)
				javaScriptReader.close();
		}
		
		/*
		 * Process Properties File
		 */
		
	}
	
}
