package listthingsbot.telegrambot.actions;

import listthingsbot.telegrambot.Chat;
import listthingsbot.telegrambot.ChatStatus;
import listthingsbot.telegrambot.actions.callbackqueries.CallbackQuery;
import listthingsbot.telegrambot.actions.commands.Command;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;

public abstract class Action
{
	/**
	 * The chat used to execute the action
	 */
	protected Chat chat;

	/**
	 * If true, in group chats only admin che execute this Action
	 */
	protected boolean adminRequired = false;

	public Action(Chat c)
	{
		this.chat = c;
	}

	/**
	 * This method is called when the update received from Telegram has a message.
	 * It can send and edit messages.
	 *
	 * @param message the message which generated the update event
	 */
	public static void elaborate(Chat chat, Message message)
	{
		/* When the user sends a Telegram command */
		if(message.isCommand())
		{
			Command command = Command.getCommand(message.getText().trim(), chat);

			if(!command.adminRequired || !message.isGroupMessage() || chat.isUserAdmin(message.getFrom().getId()))
				command.execute();
		}

		/* Messages with no Telegram commands: instructions are based on the chat status */
		else if(chat.status != ChatStatus.DEFAULT && (!message.isGroupMessage() || chat.isUserAdmin(message.getFrom().getId())))
		{
			chat.status.execute(chat, message.getText().trim());
			chat.status = ChatStatus.DEFAULT;
		}
	}

	/**
	 * This method is called when the update received from Telegram has a callback query.
	 * This method can send and edit messages.
	 *
	 * @param telegramCallbackQuery the callback query which generated the update event
	 */
	public static void elaborate(Chat chat, org.telegram.telegrambots.meta.api.objects.CallbackQuery telegramCallbackQuery)
	{
		CallbackQuery callbackQuery = CallbackQuery.getCallbackQuery(telegramCallbackQuery, chat);
		if(!callbackQuery.adminRequired || !telegramCallbackQuery.getMessage().isGroupMessage() || chat.isUserAdmin(telegramCallbackQuery.getFrom().getId()))
		{
			String[] callbackQueryData = telegramCallbackQuery.getData().split("_");
			if(callbackQueryData.length > 1 && chat.listUser.getList(callbackQueryData[1])==null)
				chat.sendMessage("⚠️This list is no longer available \uD83D\uDE41");
			else
				callbackQuery.execute();
		}
	}

	/**
	 * The main instructions of this Action
	 */
	public abstract void execute();
}
