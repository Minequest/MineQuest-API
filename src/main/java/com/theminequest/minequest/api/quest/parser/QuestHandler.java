package com.theminequest.minequest.api.quest.parser;

import java.io.File;

import com.theminequest.minequest.api.quest.QuestDetails;

public interface QuestHandler {

	QuestDetails parseQuest(File questFile);
	
}
