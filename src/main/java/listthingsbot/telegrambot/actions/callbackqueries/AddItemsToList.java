package listthingsbot.telegrambot.actions.callbackqueries;

import listthingsbot.telegrambot.Chat;
import listthingsbot.telegrambot.ChatStatus;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

/**
 * An instance of this class is executed when the user press the "add items" button.
 *
 * @author Matteo Ciaroni
 */
public class AddItemsToList extends CallbackQuery
{
	public AddItemsToList(Chat c, org.telegram.telegrambots.meta.api.objects.CallbackQuery telegramCallbackQuery)
	{
		super(c, telegramCallbackQuery);
	}

	/**
	 * Send a message asking the user to write the item to add to the list.
	 */
	@Override
	public void execute()
	{
		chat.lastListTitle = telegramCallbackQuery.getData().replaceFirst("additems_", "");
		chat.status = ChatStatus.ADD_ITEM;
		chat.sendMessage("Type the items you want to add to <b>" + chat.lastListTitle + "</b>");
	}
}
