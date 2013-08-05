package com.theminequest.api.platform;

import com.theminequest.api.Managers;

public class ChatStyle {
	
	public static String RANDOM() {
		return Managers.getPlatform().chatStyle().RANDOM();
	}
	
	public static String BOLD() {
		return Managers.getPlatform().chatStyle().BOLD();
	}
	
	public static String STRIKETHROUGH() {
		return Managers.getPlatform().chatStyle().STRIKETHROUGH();
	}
	
	public static String UNDERLINED() {
		return Managers.getPlatform().chatStyle().UNDERLINED();
	}
	
	public static String ITALIC() {
		return Managers.getPlatform().chatStyle().ITALIC();
	}
	
	public static String PLAIN_WHITE() {
		return Managers.getPlatform().chatStyle().PLAIN_WHITE();
	}
	
}
