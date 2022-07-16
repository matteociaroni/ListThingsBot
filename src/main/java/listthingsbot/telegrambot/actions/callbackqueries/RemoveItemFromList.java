package listthingsbot.telegrambot.actions.callbackqueries;

import listthingsbot.telegrambot.Chat;
import listthingsbot.telegrambot.ChatStatus;

/**
 * An instance of this class is executed when the user press the "remove item" button.
 *
 * @author Matteo Ciaroni
 */
public class RemoveItemFromList extends CallbackQuery
{
	public RemoveItemFromList(Chat c, org.telegram.telegrambots.meta.api.objects.CallbackQuery telegramCallbackQuery)
	{
		super(c, telegramCallbackQuery);
	}

	/**
	 * Send a message asking the user to write the item to remove from the list.
	 */
	@Override
	public void execute()
	{
		chat.lastListTitle = telegramCallbackQuery.getData().replaceFirst("removeitem_", "");
		chat.status = ChatStatus.DELETE_ITEM;
		chat.sendMessage("Type the item you want to remove from <b>" + chat.lastListTitle + "</b>");
	}
}
