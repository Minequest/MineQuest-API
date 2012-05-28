package com.theminequest.MineQuest.API.Quest;

import com.theminequest.MineQuest.API.CompleteStatus;
import com.theminequest.MineQuest.API.BukkitEvents.TaskCompleteEvent;
import com.theminequest.MineQuest.API.Task.QuestTask;

public interface Quest extends Comparable<Quest> {
	
	long getQuestID();
	QuestDetails getDetails();
	QuestTask getActiveTask();
	CompleteStatus isFinished();
	void startQuest();
	void finishQuest(CompleteStatus finishState);
	boolean startTask(int taskID);
	void onTaskCompletion(TaskCompleteEvent e);

}
