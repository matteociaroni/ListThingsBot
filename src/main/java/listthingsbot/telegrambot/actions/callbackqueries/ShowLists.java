package listthingsbot.telegrambot.actions.callbackqueries;

import listthingsbot.telegrambot.Chat;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

public class ShowLists extends CallbackQuery
{
	public ShowLists(Chat c, org.telegram.telegrambots.meta.api.objects.CallbackQuery telegramCallbackQuery)
	{
		super(c, telegramCallbackQuery);
	}

	@Override
	public void execute()
	{
		EditMessageText message = new EditMessageText();
		message.setMessageId(telegramCallbackQuery.getMessage().getMessageId());
		message.setText("Select the list to show ⬇️");
		message.setReplyMarkup(chat.getButtonsWithListTitles("show_"));
		chat.editMessage(message);
	}
}
