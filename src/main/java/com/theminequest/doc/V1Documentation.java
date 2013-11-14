package com.theminequest.doc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface V1Documentation {
	
	String type();
	String name();
	boolean id();
	String description();
	String[] arguments();
	DocArgType[] typeArguments();
	
}
