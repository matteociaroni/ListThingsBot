package listthingsbot.telegrambot.actions.callbackqueries;

import listthingsbot.telegrambot.Chat;
import listthingsbot.telegrambot.buttons.Markup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

/**
 * An instance of this class is executed when the user press the button to show a list.
 *
 * @author Matteo Ciaroni
 */
public class ShowList extends CallbackQuery
{
	public ShowList(Chat c, org.telegram.telegrambots.meta.api.objects.CallbackQuery telegramCallbackQuery)
	{
		super(c, telegramCallbackQuery);
	}

	/**
	 * Edit the message showing list details and some buttons to manage it.
	 */
	@Override
	public void execute()
	{
		EditMessageText message = new EditMessageText();
		message.setMessageId(super.telegramCallbackQuery.getMessage().getMessageId());
		chat.lastListTitle = super.telegramCallbackQuery.getData().replace("show_", "");
		message.setText("\uD83D\uDDD2  <b>" + chat.lastListTitle + "</b>\n\n" + chat.listUser.getList(chat.lastListTitle).toString());
		message.setReplyMarkup(Markup.listOptions(chat.lastListTitle, chat.listUser.getList(chat.lastListTitle).size()>0));
		chat.editMessage(message);
	}
}
