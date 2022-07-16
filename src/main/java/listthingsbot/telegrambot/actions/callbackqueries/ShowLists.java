package listthingsbot.telegrambot.actions.callbackqueries;

import listthingsbot.telegrambot.Chat;
import listthingsbot.telegrambot.buttons.Markup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

/**
 * An instance of this class is executed when the user press the "all lists" button.
 *
 * @author Matteo Ciaroni
 */
public class ShowLists extends CallbackQuery
{
	public ShowLists(Chat c, org.telegram.telegrambots.meta.api.objects.CallbackQuery telegramCallbackQuery)
	{
		super(c, telegramCallbackQuery);
	}

	/**
	 * Edit the message showing all user's lists.
	 */
	@Override
	public void execute()
	{
		EditMessageText message = new EditMessageText();
		message.setMessageId(telegramCallbackQuery.getMessage().getMessageId());
		message.setText("Select the list to show ⬇️");
		message.setReplyMarkup(Markup.selectList(chat.listUser));
		chat.editMessage(message);
	}
}
