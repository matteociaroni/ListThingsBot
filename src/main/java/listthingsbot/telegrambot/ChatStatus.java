package listthingsbot.telegrambot;

import java.io.Serializable;

/**
 * This class describes all the possible chat statuses.
 * Statuses are used to manage conversations and record previous actions.
 *
 * @author Matteo Ciaroni
 */
public enum ChatStatus implements Serializable
{
    /**
     * The default status
     */
    DEFAULT,

    /**
     * The user sent the /newlist command.
     * The bot is waiting for the list title
     */
    ADD_LIST,

    /**
     * The bot is waiting for the new list title
     */
    RENAME_LIST,

    /**
     * The bot is waiting for the item to add to the list
     */
    ADD_ITEM,

    /**
     * The bot is waiting for the item to remove from the list
     */
    DELETE_ITEM
}