package com.theminequest.MineQuest.API.Task;

import java.util.Collection;

import com.theminequest.MineQuest.API.CompleteStatus;
import com.theminequest.MineQuest.API.Events.QuestEvent;
import com.theminequest.MineQuest.API.Quest.Quest;

public interface QuestTask {

	void start();
	void cancelTask();
	void completeTask();
	CompleteStatus isComplete();
	Quest getQuest();
	int getTaskID();
	Collection<QuestEvent> getEvents();

}