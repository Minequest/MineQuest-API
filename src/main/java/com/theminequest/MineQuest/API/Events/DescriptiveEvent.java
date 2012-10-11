package com.theminequest.MineQuest.API.Events;

public @interface DescriptiveEvent {
	
	/**
	 * User-friendly name for this event
	 * @return User-friendly name
	 */
	String name();
	
	/**
	 * User-friendly description for this event
	 * @return User-friendly description
	 */
	String description();
	
	/**
	 * Fields/Event parameters that this takes
	 * @return Array of parameters
	 */
	String[] fields();
}
