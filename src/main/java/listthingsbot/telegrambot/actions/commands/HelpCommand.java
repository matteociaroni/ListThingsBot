package listthingsbot.telegrambot.actions.commands;

import listthingsbot.telegrambot.Chat;
import listthingsbot.telegrambot.ChatStatus;

/**
 * This class represents the /help command.
 * This command sends a message to the user with some information about the bot and its commands.
 *
 * @author Matteo Ciaroni
 */
public class HelpCommand extends Command
{
	public HelpCommand(Chat c)
	{
		super(c);
		super.adminRequired = true;
	}

	@Override
	public void execute()
	{
		chat.status = ChatStatus.DEFAULT;
		String helpMessage = "This bot can help you manage multiple lists of things.\n"
				+ "Use the /lists command to show all lists and to manage them.\n"
				+ "Use the /newlist command to create a new list.\n"
				+ "Use the /cancel command to ignore previous actions.\n"
				+ "Use the /help command to show this message.\n";
		chat.sendMessage(helpMessage);
	}
}
