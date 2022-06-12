package listthingsbot.telegrambot.actions.commands;

import listthingsbot.telegrambot.Chat;
import listthingsbot.telegrambot.actions.Action;

import java.util.HashMap;
import java.util.Map;

/**
 * Every time the user submit a valid Telegram command (/lists, newlist, ...) an instance of this class is executed
 */
public abstract class Command extends Action
{
	public Command(Chat c)
	{
		super(c);
	}

	/**
	 * This method is used to create the right Command object based on a String
	 *
	 * @param name the name of the command ("/lists", "/newlist", ...)
	 * @param chat        the chat where the command is executed
	 * @return the right command object based on the commandName string
	 */
	public static Command getCommand(String name, Chat chat)
	{
		Map<String, Command> commands = new HashMap<>();

		commands.put("/lists", new ListsCommand(chat));
		commands.put("/help", new HelpCommand(chat));
		commands.put("/cancel", new CancelCommand(chat));
		commands.put("/newlist", new NewListCommand(chat));

		return commands.get(name);
	}
}
