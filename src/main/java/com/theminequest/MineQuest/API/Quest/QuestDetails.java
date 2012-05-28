package com.theminequest.MineQuest.API.Quest;

import java.io.Serializable;

/**
 * Defines the structure of the quest.
 * @author The MineQuest Team
 * @since 2.0.0
 * @version 2.0.0
 * @see com.theminequest.MineQuest.API.Quest.Quest
 */
public interface QuestDetails extends Serializable, Comparable<QuestDetails> {
	
	/**
	 * The property that contains the Quest Name
	 * (typically the filename without the ".quest")<br>
	 * Returns a {@link java.lang.String}.
	 */
	static final String QUEST_NAME = "mq.name";
	/**
	 * The property that contains the Display Name,
	 * a human readable version of the Quest Name.<br>
	 * Returns a {@link java.lang.String}.
	 */
	static final String QUEST_DISPLAYNAME = "mq.displayname";
	/**
	 * The property that contains the Quest Description.<br>
	 * If none is explicitly specified it uses the default
	 * specified by localization and user-defined configuration.<br>
	 * Returns a {@link java.lang.String}.
	 */
	static final String QUEST_DESCRIPTION = "mq.description";
	/**
	 * The property that contains the acceptance message.<br>
	 * If none is explicitly specified it uses the default
	 * specified by localization and user-defined configuration.<br>
	 * Returns a {@link java.lang.String}.
	 */
	static final String QUEST_ACCEPT = "mq.accept";
	/**
	 * The property that contains the aborted message.<br>
	 * If none is explicitly specified it uses the default
	 * specified by localization and user-defined configuration.<br>
	 * Returns a {@link java.lang.String}.
	 */
	static final String QUEST_ABORT = "mq.abort";
	/**
	 * The property that contains the completed message.<br>
	 * If none is explicitly specified it uses the default
	 * specified by localization and user-defined configuration.<br>
	 * Returns a {@link java.lang.String}.
	 */
	static final String QUEST_COMPLETE = "mq.complete";
	/**
	 * The property that contains the tasks this quest has.<br>
	 * Returns a {@link java.util.LinkedHashMap} containing
	 * keys of {@link java.lang.Integer} and values of
	 * {@link java.lang.Integer} arrays.
	 */
	static final String QUEST_TASKS = "mq.tasks";
	/**
	 * The property that contains the event specifications this
	 * quest has.<br> Returns a {@link java.util.LinkedHashMap}
	 * containing keys of {@link java.lang.Integer} and values of
	 * {@link java.lang.String}.
	 */
	static final String QUEST_EVENTS = "mq.events";
	/**
	 * The property that contains the event specifications this
	 * quest has.<br> Returns a {@link java.util.LinkedHashMap}
	 * containing keys of {@link java.lang.Integer} and values of
	 * {@link com.theminequest.MineQuest.API.Target.TargetDetails}.
	 */
	static final String QUEST_TARGETS = "mq.targets";
	/**
	 * The property that contains the edit specifications this
	 * quest has.<br> Returns a {@link java.util.LinkedHashMap}
	 * containing keys of {@link java.lang.Integer} and values of
	 * {@link com.theminequest.MineQuest.API.Edit.Edit}.
	 */
	static final String QUEST_EDITS = "mq.edits";
	/**
	 * The property that determines whether a quest is repeatable.<br>
	 * Returns a {@link java.lang.Boolean}.
	 */
	static final String QUEST_REPEATABLE = "mq.repeatable";
	/**
	 * The property that determines whether players participating
	 * in the quest are sent back to their original locations
	 * after the quest has been completed or aborted.<br>
	 * Returns a {@link java.lang.Boolean}.
	 */
	static final String QUEST_SPAWNRESET = "mq.spawnreset";
	/**
	 * The property that determines the quest spawn point in
	 * the world.<br>
	 * Returns a {@link java.util.List} of {@link java.util.Double}s.
	 */
	static final String QUEST_SPAWNPOINT = "mq.spawnpoint";
	// FIXME
	//static final String QUEST_AREAPRESERVED = "mq.areapreserved";
	/**
	 * The property that contains the message sent to players
	 * when they attempt to edit a part of the quest that is
	 * not editable.<br>
	 * Returns a {@link java.lang.String}.
	 */
	static final String QUEST_EDITMESSAGE = "mq.editmessage";
	/**
	 * The property that contains the world name the quest
	 * is on. If the quest is instanced, it contains the world
	 * name that an instance will be created off of.<br>
	 * Returns a {@link java.lang.String}.
	 */
	static final String QUEST_WORLD = "mq.world";
	/**
	 * The property that determines whether the quest should
	 * be isolated in an instanced world or not.<br>
	 * Returns a {@link java.lang.Boolean}.
	 */
	static final String QUEST_LOADWORLD = "mq.loadworld";
	/**
	 * The property that determines whether the world the quest
	 * takes place in is a nether world.<br>
	 * Returns a {@link java.lang.Boolean}.
	 */
	static final String QUEST_NETHERWORLD = "mq.netherworld";
	/**
	 * The property that limits the size of the group participating
	 * in this quest, in order to ensure no cheating and no
	 * advantages.<br>
	 * Returns a {@link java.lang.Integer}.
	 */
	static final String QUEST_QUESTGROUPLIMIT = "mq.questgrouplimit";
	
	/**
	 * Retrieve the property associated with the key.
	 * @param <E> returned type
	 * @param key Key
	 * @return Associated Property, or <code>null</code> if there is none.
	 * Note that <code>null</code> does not necessarily mean that there is
	 * no such property, and that the property may actually be <code>null</code>.
	 */
	public <E> E getProperty(String key);
	/**
	 * Set the property associated with the key.
	 * @param key Key to set
	 * @param property Property to set
	 */
	public void setProperty(String key, Serializable property);
	/**
	 * Check if this contains a property.
	 * @param key Key to check
	 * @return true if there is an associated property
	 */
	public boolean containsProperty(String key);
	/**
	 * Remove a property
	 * @param <E> returned type
	 * @param key Key to remove
	 * @return Property removed
	 */
	public <E> E removeProperty(String key);

}
