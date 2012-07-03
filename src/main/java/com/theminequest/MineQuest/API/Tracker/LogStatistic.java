package com.theminequest.MineQuest.API.Tracker;

import com.alta189.simplesave.Field;
import com.alta189.simplesave.Id;
import com.alta189.simplesave.Table;
import com.theminequest.MineQuest.API.Tracker.StatisticManager.Statistic;

@Table("mq_logs")
public class LogStatistic extends Statistic {
	
	@Id
	private long logid;
	
	/**
	 * Status refers to the state of the statistic.<br>
	 * <ul>
	 * <li>0 - GIVEN</li>
	 * <li>1 - ACTIVE</li>
	 * <li>2 - COMPLETED</li>
	 * </ul>
	 * Which corresponds directly to its equivalent
	 * {@link LogStatus}, excluding the constant UNKNOWN.
	 */
	@Field
	private int status;
	
	@Field
	private String questName;
	
	@Field
	private long timestamp;
	
	public String getQuestName(){
		return questName;
	}
	
	public void setQuestName(String questName){
		this.questName = questName;
	}
	
	public LogStatus getStatus(){
		if (status==0)
			return LogStatus.GIVEN;
		else if (status==1)
			return LogStatus.ACTIVE;
		else if (status==2)
			return LogStatus.COMPLETED;
		else
			return LogStatus.UNKNOWN;
	}
	
	public void setStatus(LogStatus status){
		switch(status){
		case GIVEN:
			this.status = 0;
			return;
		case ACTIVE:
			this.status = 1;
			return;
		case COMPLETED:
			this.status = 2;
			return;
		case UNKNOWN:
			throw new IllegalArgumentException("Status cannot be unknown!");
		}
	}
	
	public long getTimestamp(){
		return timestamp;
	}
	
	public void setTimestamp(long timestamp){
		this.timestamp = timestamp;
	}

	@Override
	public void setup() {}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LogStatistic) {
			LogStatistic s = (LogStatistic) obj;
			if (questName.equals(s.questName) && status==s.status && timestamp==s.timestamp)
				return true;
		}
		return false;
	}

}
