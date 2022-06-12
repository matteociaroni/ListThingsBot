package listthingsbot.telegrambot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.Serializable;
import java.util.function.BiConsumer;

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
	DEFAULT((chat, title) -> chat.sendMessage("No command provided.")),

	/**
	 * The user sent the /newlist command.
	 * The bot is waiting for the list title
	 */
	ADD_LIST((chat, title) -> chat.addList(title)),

	/**
	 * The bot is waiting for the new list title
	 */
	RENAME_LIST((chat, title) -> chat.renameList(chat.lastListTitle, title)),

	/**
	 * The bot is waiting for the item to add to the list
	 */
	ADD_ITEM((chat, items) -> chat.addItems(chat.lastListTitle, items)),

	/**
	 * The bot is waiting for the item to remove from the list
	 */
	DELETE_ITEM((chat, item) ->
	{
		try
		{
			chat.removeItem(chat.lastListTitle, Integer.parseInt(item));
		}
		catch(NumberFormatException e)
		{
			SendMessage sendMessage = new SendMessage();
			sendMessage.setText("⚠️ Not a valid number");
			chat.sendMessage(sendMessage);
		}
	});

	/**
	 * The action of the status.
	 * When the user sends a new message with no command, the action of the current chat status is executed
	 */
	private final BiConsumer<Chat, String> action;

	private ChatStatus(BiConsumer<Chat, String> m)
	{
		this.action = m;
	}

	/**
	 * Execute the action.
	 *
	 * @param chat    the chat
	 * @param message the text of the message
	 */
	public void execute(Chat chat, String message)
	{
		this.action.accept(chat, message);
	}
}