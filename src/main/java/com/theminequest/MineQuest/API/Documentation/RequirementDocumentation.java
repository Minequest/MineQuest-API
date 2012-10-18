package com.theminequest.MineQuest.API.Documentation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class RequirementDocumentation {
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public static @interface ReqFields {
		
		String[] value();
		
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public static @interface ReqInfo {
		
		String name();
		
		String description();
		
	}
	
}
