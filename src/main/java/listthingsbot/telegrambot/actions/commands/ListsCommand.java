package listthingsbot.telegrambot.actions.commands;

import listthingsbot.telegrambot.Chat;
import listthingsbot.telegrambot.buttons.Markup;

/**
 * This class represents the /lists command.
 * This command sends a message with all the user's lists.
 * The user can select a list pushing a button.
 *
 * @author Matteo Ciaroni
 */
public class ListsCommand extends Command
{
	public ListsCommand(Chat c)
	{
		super(c);
	}

	@Override
	public void execute()
	{
		if(chat.listUser.countLists() > 0)
			chat.sendMessage("Select the list to show ⬇️", Markup.selectList(chat.listUser));
		else
			chat.sendMessage("Nothing to show \uD83D\uDE41\nCreate a new list with /newlist");
	}
}
