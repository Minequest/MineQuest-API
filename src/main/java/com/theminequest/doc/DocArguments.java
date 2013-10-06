package com.theminequest.doc;

public @interface DocArguments {
	
	/**
	 * Returns a list of arguments, in the format:
	 * <pre>TYPE:Description</pre>
	 * Where <code>TYPE</code> is one of the following:
	 * <ul>
	 * <li>INT</li>
	 * <li>FLOAT</li>
	 * <li>STRING</li>
	 * <li>INTLOC</li>
	 * <li>FLOATLOC</li>
	 * <li>TASK</li>
	 * <li>EVENT</li>
	 * <li>REQUIREMENT</li>
	 * <li>TARGET</li>
	 * </ul>
	 * @return a list of arguments that this takes
	 */
	String[] arguments();
	
}
