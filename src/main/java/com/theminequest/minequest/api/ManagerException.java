/*
 * This file is part of MineQuest-API, version 3, Specifications for the MineQuest system.
 * MineQuest-API, version 3 is licensed under GNU Lesser General Public License v3.
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
package com.theminequest.minequest.api;

public class ManagerException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4839674539656984977L;
	private ManagerReason managerReason;

	public ManagerException(ManagerReason arg0) {
		super(arg0.name());
		managerReason = arg0;
	}
	
	public ManagerException(ManagerReason arg0, String addlinfo){
		super(addlinfo);
		managerReason = arg0;
	}

	public ManagerException(Throwable arg0) {
		super(arg0);
		managerReason = ManagerReason.EXTERNALEXCEPTION;
	}

	public ManagerException(ManagerReason arg0, Throwable arg1) {
		super(arg0.name(), arg1);
		managerReason = arg0;
	}
	
	public ManagerReason getReason(){
		return managerReason;
	}
	
	public enum ManagerReason {
		INTERNAL, NOTIMPLEMENTED, FAILED, INVALIDARGS, EXTERNALEXCEPTION;
	}

}
