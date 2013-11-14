package com.theminequest.doc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface V1Documentation {
	
	/**
	 * Return the Type. This is usually the handler name,
	 * e.g. "Name", "Event", "Target", "Description", etc.
	 * @return type
	 */
	String type();
	/**
	 * Return the ident. Most types do not have this property,
	 * but some do, like "Event" and "Target". By specifying
	 * an ident, they are automatically requesting an ID field.
	 * @return Ident
	 */
	String ident() default "";
	/**
	 * Return a description.
	 * @return Description
	 */
	String description();
	/**
	 * Specify the arguments that this type[&ident] takes.
	 * @return Arguments that this type[&ident] takes.
	 */
	String[] arguments();
	/**
	 * Attach types to arguments.
	 * @return Type for each argument above.
	 */
	DocArgType[] typeArguments();
	/**
	 * Hide this type[/ident] by default.
	 * @return Hidden type[/ident]
	 */
	boolean hide() default false;
}
