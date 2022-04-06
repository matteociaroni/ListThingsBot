package listthingsbot.telegrambot;

/**
 * This class describes all the possible chat statuses.
 * Statuses are used to manage conversations and record previous actions.
 *
 * @author Matteo Ciaroni
 */
public enum ChatStatus
{
    DEFAULT, ADD_LIST, RENAME_LIST, ADD_ITEM, DELETE_ITEM
}