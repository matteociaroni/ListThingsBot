package listthingsbot.telegrambot.actions.commands;

import listthingsbot.telegrambot.Chat;
import listthingsbot.telegrambot.ChatStatus;

/**
 * This class represents the /newlist command.
 * This command sends a message to the user asking for the title of the new list.
 *
 * @author Matteo Ciaroni
 */
public class NewListCommand extends Command
{
	public NewListCommand(Chat c)
	{
		super(c);
		super.adminRequired = true;
	}

	@Override
	public void execute()
	{
		chat.status = ChatStatus.ADD_LIST;
		chat.sendMessage("Type the <b>name</b> of the new list");
	}
}
