package listthingsbot.telegrambot.actions.commands;

import listthingsbot.telegrambot.Chat;
import listthingsbot.telegrambot.actions.Action;

import java.util.HashMap;
import java.util.Map;

public abstract class Command extends Action
{
	public Command(Chat c)
	{
		super(c);
	}

	public static Command getCommand(String commandName, Chat chat)
	{
		Map<String, Command> commands = new HashMap<>();

		commands.put("/lists", new ListsCommand(chat));
		commands.put("/help", new HelpCommand(chat));
		commands.put("/cancel", new CancelCommand(chat));
		commands.put("/newlist", new NewListCommand(chat));

		return commands.get(commandName);
	}
}