package com.theminequest.minequest.api.quest.parser;

import java.util.Set;

public interface ParserManager {
	
	void addQuestHandler(String endsWith, QuestHandler handler);
	
	QuestHandler getQuestHandler(String endsWith);
	
	Set<String> getQuestHandlers();
	
	QuestHandler removeQuestHandler(String endsWith);
	
	void addPropertyHandler(String propertyName, PropertyHandler handler);
	
	PropertyHandler getPropertyHandler(String propertyName);
	
	Set<String> getPropertyHandlers();
	
	PropertyHandler removePropertyHandler(String propertyName);
	
}
