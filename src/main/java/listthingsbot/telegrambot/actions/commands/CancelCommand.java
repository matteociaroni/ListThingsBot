package listthingsbot.telegrambot.actions.commands;

import listthingsbot.telegrambot.Chat;
import listthingsbot.telegrambot.ChatStatus;

/**
 * This class represents the /cancel command.
 * This command ignores previous actions and restore the chat status to DEFAULT.
 *
 * @author Matteo Ciaroni
 */
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
