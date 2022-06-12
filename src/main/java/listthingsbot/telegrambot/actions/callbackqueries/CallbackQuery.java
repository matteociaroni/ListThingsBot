package listthingsbot.telegrambot.actions.callbackqueries;

import listthingsbot.telegrambot.Chat;
import listthingsbot.telegrambot.actions.Action;

import java.util.HashMap;
import java.util.Map;

/**
 * Every time the user send a valid Callback query from Telegram an instance of this class is executed
 */
public abstract class CallbackQuery extends Action
{
	public CallbackQuery(Chat c)
	{
		super(c);
	}

	/**
	 * This method is used to create the right CallbackQuery object based on a String
	 *
	 * @param value the value of the callback query provided by the user
	 * @param chat  the chat where the callback query is invoked
	 * @return the right CallbackQuery object based on the commandName string
	 */
	public static CallbackQuery getCallbackQuery(String value, Chat chat)
	{
		Map<String, CallbackQuery> commands = new HashMap<>();

		//commands.put("/lists", new ListsCommand(chat));

		return commands.get(value);
	}
}
