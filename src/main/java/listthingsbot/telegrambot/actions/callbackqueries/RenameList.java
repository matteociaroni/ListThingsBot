package listthingsbot.telegrambot.actions.callbackqueries;

import listthingsbot.telegrambot.Chat;
import listthingsbot.telegrambot.ChatStatus;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

/**
 * An instance of this class is executed when the user press the "rename list" button.
 *
 * @author Matteo Ciaroni
 */
public class RenameList extends CallbackQuery
{
	public RenameList(Chat c, org.telegram.telegrambots.meta.api.objects.CallbackQuery telegramCallbackQuery)
	{
		super(c, telegramCallbackQuery);
		super.adminRequired = true;
	}

	/**
	 * Send a message asking the user to write the name of the new list.
	 */
	@Override
	public void execute()
	{
		chat.lastListTitle = telegramCallbackQuery.getData().replaceFirst("rename_", "");
		chat.status = ChatStatus.RENAME_LIST;
		chat.sendMessage("✏️ Enter a new name for <b>" + chat.lastListTitle + "</b>");
	}
}
