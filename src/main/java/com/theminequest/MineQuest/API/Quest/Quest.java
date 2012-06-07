package com.theminequest.MineQuest.API.Quest;

import java.io.Serializable;

import com.theminequest.MineQuest.API.CompleteStatus;
import com.theminequest.MineQuest.API.BukkitEvents.TaskCompleteEvent;
import com.theminequest.MineQuest.API.Task.QuestTask;

public interface Quest extends Comparable<Quest>, Serializable {
	
	long getQuestID();
	QuestDetails getDetails();
	QuestTask getActiveTask();
	boolean isInstanced();
	CompleteStatus isFinished();
	void startQuest();
	void finishQuest(CompleteStatus finishState);
	void cleanupQuest();
	boolean startTask(int taskID);
	void onTaskCompletion(TaskCompleteEvent e);

}
