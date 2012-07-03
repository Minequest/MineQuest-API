package com.theminequest.MineQuest.API.Tracker;

import com.alta189.simplesave.Field;
import com.alta189.simplesave.Id;
import com.alta189.simplesave.Table;
import com.theminequest.MineQuest.API.Quest.QuestSnapshot;
import com.theminequest.MineQuest.API.Tracker.StatisticManager.Statistic;

@Table("mq_snapshots")
public class SnapshotStatistic extends Statistic {
	
	@Id
	private long snapshotid;
	
	@Field
	private String questName;
	
	@Field
	private QuestSnapshot snapshot;
	
	public String getQuestName() {
		return questName;
	}

	public void setQuestName(String questName) {
		this.questName = questName;
	}

	public QuestSnapshot getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(QuestSnapshot snapshot) {
		this.snapshot = snapshot;
	}

	@Override
	public void setup() {}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SnapshotStatistic) {
			SnapshotStatistic s = (SnapshotStatistic) obj;
			if (questName.equals(s.questName) && snapshot.equals(s.snapshot))
				return true;
		}
		return false;
	}

}
