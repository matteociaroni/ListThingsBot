package listthingsbot.telegrambot.actions.callbackqueries;

import listthingsbot.telegrambot.Chat;
import listthingsbot.telegrambot.ChatStatus;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public class AddItemsToList extends CallbackQuery
{
	public AddItemsToList(Chat c, org.telegram.telegrambots.meta.api.objects.CallbackQuery telegramCallbackQuery)
	{
		super(c, telegramCallbackQuery);
	}

	@Override
	public void execute()
	{
		chat.lastListTitle = telegramCallbackQuery.getData().replaceFirst("additems_", "");
		chat.status = ChatStatus.ADD_ITEM;
		chat.sendMessage("Type the items you want to add to <b>" + chat.lastListTitle + "</b>");
	}
}
