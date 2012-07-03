package com.theminequest.MineQuest.API.Tracker;

import com.alta189.simplesave.Field;
import com.alta189.simplesave.Id;
import com.alta189.simplesave.Table;
import com.theminequest.MineQuest.API.Quest.QuestSnapshot;

@Table("mq_snapshots")
public class SnapshotStatistic extends QuestStatistic {
	
	@Id
	private long snapshotid;
	
	@Field
	private QuestSnapshot snapshot;

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
			if (getQuestName().equals(s.getQuestName()) && snapshot.equals(s.snapshot))
				return true;
		}
		return false;
	}

}
