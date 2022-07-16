package listthingsbot.telegrambot.actions.callbackqueries;

import listthingsbot.telegrambot.Chat;
import listthingsbot.telegrambot.ChatStatus;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public class RenameList extends CallbackQuery
{
	public RenameList(Chat c, org.telegram.telegrambots.meta.api.objects.CallbackQuery telegramCallbackQuery)
	{
		super(c, telegramCallbackQuery);
		super.adminRequired = true;
	}

	@Override
	public void execute()
	{
		chat.lastListTitle = telegramCallbackQuery.getData().replaceFirst("rename_", "");
		chat.status = ChatStatus.RENAME_LIST;
		chat.sendMessage("✏️ Enter a new name for <b>" + chat.lastListTitle + "</b>");
	}
}
