/*
 * This file is part of MineQuest-API, version 2, Specifications for the
 * MineQuest system.
 * MineQuest-API, version 2 is licensed under GNU Lesser General Public License
 * v3.
 * Copyright (C) 2012 The MineQuest Team
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.theminequest.minequest.api;

import java.io.Serializable;

public enum CompleteStatus implements Serializable {
	
	/**
	 * Indicates the respective action completed successfully.
	 */
	SUCCESS,
	/**
	 * Indicates the respective action completed with some warnings.
	 */
	WARNING,
	/**
	 * Indicates the respective action completed with problems.
	 */
	FAILURE,
	/**
	 * Indicates the respective action was canceled with minor cleanup. 
	 */
	IGNORE,
	/**
	 * Indicates the respective action was canceled with major cleanup.
	 */
	CANCELED,
	/**
	 * Indicates the respective action failed to complete.
	 */
	ERROR;
	
}