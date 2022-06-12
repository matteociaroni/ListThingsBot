package listthingsbot.telegrambot.actions.commands;

import listthingsbot.telegrambot.Chat;
import listthingsbot.telegrambot.ChatStatus;

public class CancelCommand extends Command
{
	public CancelCommand(Chat c)
	{
		super(c);
		super.adminRequired = true;
	}

	@Override
	public void execute()
	{
		chat.status = ChatStatus.DEFAULT;
		chat.sendMessage("Previous actions ignored");
	}
}
