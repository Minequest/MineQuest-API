package com.theminequest.MineQuest.API.Task;

import java.io.Serializable;
import java.util.Collection;

import com.theminequest.MineQuest.API.CompleteStatus;
import com.theminequest.MineQuest.API.Events.QuestEvent;

public interface QuestTask extends Serializable {

	void start();
	void cancelTask();
	void finishEvent(QuestEvent questEvent, CompleteStatus completeStatus);
	CompleteStatus isComplete();
	long getQuestID();
	int getTaskID();
	Collection<QuestEvent> getEvents();

}