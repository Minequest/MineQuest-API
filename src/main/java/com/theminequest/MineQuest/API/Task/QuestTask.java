package com.theminequest.MineQuest.API.Task;

import java.util.Collection;

import com.theminequest.MineQuest.API.CompleteStatus;
import com.theminequest.MineQuest.API.Events.QuestEvent;

public interface QuestTask {

	void start();
	void cancelTask();
	void finishEvent(int eventid, CompleteStatus completeStatus);
	CompleteStatus isComplete();
	long getQuestID();
	int getTaskID();
	Collection<QuestEvent> getEvents();

}