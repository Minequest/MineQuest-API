/*
 * This file is part of MineQuest-API, version 2, Specifications for the MineQuest system.
 * MineQuest-API, version 2 is licensed under GNU Lesser General Public License v3.
 * Copyright (C) 2012 The MineQuest Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.theminequest.minequest.api.statistic;

import com.alta189.simplesave.Field;
import com.alta189.simplesave.Id;
import com.alta189.simplesave.Table;

@Table("mq_logs")
public class LogStatistic extends QuestStatistic {
	
	@Id
	private long logid;
	
	/**
	 * Status refers to the state of the statistic.<br>
	 * <ul>
	 * <li>0 - GIVEN</li>
	 * <li>1 - ACTIVE</li>
	 * <li>2 - COMPLETED</li>
	 * <li>3 - FAILED</li>
	 * </ul>
	 * Which corresponds directly to its equivalent
	 * {@link LogStatus}, excluding the constant UNKNOWN.
	 */
	@Field
	private int status;
	
	@Field
	private long timestamp;
	
	public LogStatus getStatus(){
		if (status==0)
			return LogStatus.GIVEN;
		else if (status==1)
			return LogStatus.ACTIVE;
		else if (status==2)
			return LogStatus.COMPLETED;
		else if (status==3)
			return LogStatus.FAILED;
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
		case FAILED:
			this.status = 3;
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
			if (getQuestName().equals(s.getQuestName()) && status==s.status && timestamp==s.timestamp)
				return true;
		}
		return false;
	}

}
