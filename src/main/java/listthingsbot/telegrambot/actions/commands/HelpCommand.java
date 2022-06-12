package listthingsbot.telegrambot.actions.commands;

import listthingsbot.telegrambot.Chat;
import listthingsbot.telegrambot.ChatStatus;

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
		StringBuilder helpMessage = new StringBuilder();
		helpMessage.append("This bot can help you manage multiple lists of things.\n");
		helpMessage.append("Use the /lists command to show all lists and to manage them.\n");
		helpMessage.append("Use the /newlist command to create a new list.\n");
		helpMessage.append("Use the /cancel command to ignore previous actions.\n");
		helpMessage.append("Use the /help command to show this message.\n");
		chat.sendMessage(helpMessage.toString());
	}
}
