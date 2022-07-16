package listthingsbot.telegrambot.actions.callbackqueries;

import listthingsbot.telegrambot.Chat;
import listthingsbot.telegrambot.ChatStatus;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public class ShowList extends CallbackQuery
{
	public ShowList(Chat c, org.telegram.telegrambots.meta.api.objects.CallbackQuery telegramCallbackQuery)
	{
		super(c, telegramCallbackQuery);
	}

	@Override
	public void execute()
	{
		EditMessageText message = new EditMessageText();
		message.setMessageId(super.telegramCallbackQuery.getMessage().getMessageId());
		chat.lastListTitle = super.telegramCallbackQuery.getData().replace("show_", "");
		message.setText("\uD83D\uDDD2  <b>" + chat.lastListTitle + "</b>\n\n" + chat.listUser.getList(chat.lastListTitle).toString());
		message.setReplyMarkup(chat.getButtonsWithListOptions(chat.lastListTitle));
		chat.editMessage(message);
	}
}
