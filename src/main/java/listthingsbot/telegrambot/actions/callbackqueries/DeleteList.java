package listthingsbot.telegrambot.actions.callbackqueries;

import listthingsbot.telegrambot.Chat;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

/**
 * An instance of this class is executed when the user press the "delete list" button.
 *
 * @author Matteo Ciaroni
 */
public class DeleteList extends CallbackQuery
{
	public DeleteList(Chat c, org.telegram.telegrambots.meta.api.objects.CallbackQuery telegramCallbackQuery)
	{
		super(c, telegramCallbackQuery);
		super.adminRequired = true;
	}

	/**
	 * Delete the list and edit the message.
	 */
	@Override
	public void execute()
	{
		chat.lastListTitle = telegramCallbackQuery.getData().replaceFirst("delete_", "");
		chat.listUser.removeList(chat.lastListTitle);
		EditMessageText message = new EditMessageText();
		message.setMessageId(telegramCallbackQuery.getMessage().getMessageId());
		message.setText("\uD83D\uDDD1️ List <b>" + chat.lastListTitle + "</b> deleted");
		chat.editMessage(message);
	}
}
