package listthingsbot.telegrambot.actions.callbackqueries;

import listthingsbot.telegrambot.Chat;
import listthingsbot.telegrambot.ChatStatus;

public class RemoveItemFromList extends CallbackQuery
{
	public RemoveItemFromList(Chat c, org.telegram.telegrambots.meta.api.objects.CallbackQuery telegramCallbackQuery)
	{
		super(c, telegramCallbackQuery);
	}

	@Override
	public void execute()
	{
		chat.lastListTitle = telegramCallbackQuery.getData().replaceFirst("removeitem_", "");
		chat.status = ChatStatus.DELETE_ITEM;
		chat.sendMessage("Type the item you want to remove from <b>" + chat.lastListTitle + "</b>");
	}
}
