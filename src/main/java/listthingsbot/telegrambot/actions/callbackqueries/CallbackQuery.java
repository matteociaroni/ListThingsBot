package listthingsbot.telegrambot.actions.callbackqueries;

import listthingsbot.telegrambot.Chat;
import listthingsbot.telegrambot.actions.Action;

import java.util.HashMap;
import java.util.Map;

public abstract class CallbackQuery extends Action
{
	protected Chat chat;
	protected boolean adminRequired = false;

	public CallbackQuery(Chat c)
	{
		super(c);
	}

	public static CallbackQuery getCallbackQuery(String commandName, Chat chat)
	{
		Map<String, CallbackQuery> commands = new HashMap<>();

		//commands.put("/lists", new ListsCommand(chat));

		return commands.get(commandName);
	}

	public abstract void execute();
}
