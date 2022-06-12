package listthingsbot.telegrambot.actions;

import listthingsbot.telegrambot.Chat;
import listthingsbot.telegrambot.ChatStatus;
import listthingsbot.telegrambot.actions.commands.Command;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
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
	 * @param callbackQuery the callback query which generated the update event
	 */
	public static void elaborate(Chat chat, CallbackQuery callbackQuery)
	{
		/* When a user select a list to show */
		if(callbackQuery.getData().startsWith("show_"))
		{
			EditMessageText message = new EditMessageText();
			message.setMessageId(callbackQuery.getMessage().getMessageId());
			String listTitle = callbackQuery.getData().replace("show_", "");
			message.setText("\uD83D\uDDD2  <b>" + listTitle + "</b>\n\n" + chat.listUser.getList(listTitle).toString());
			message.setReplyMarkup(chat.getButtonsWithListOptions(listTitle));
			chat.editMessage(message);
		}

		/* When user pressed "all lists" button */
		if(callbackQuery.getData().equals("lists"))
		{
			EditMessageText message = new EditMessageText();
			message.setMessageId(callbackQuery.getMessage().getMessageId());
			message.setText("Select the list to show ⬇️");
			message.setReplyMarkup(chat.getButtonsWithListTitles("show_"));
			chat.editMessage(message);
		}

		/* Check if user is admin in a group or if it's a private chat */
		if(!callbackQuery.getMessage().isGroupMessage() || chat.isUserAdmin(callbackQuery.getFrom().getId()))
		{
			/* When user pressed "delete" button */
			if(callbackQuery.getData().startsWith("delete_"))
			{
				chat.lastListTitle = callbackQuery.getData().replaceFirst("delete_", "");

				if(chat.listUser.getList(chat.lastListTitle) != null)
				{
					chat.listUser.removeList(chat.lastListTitle);
					EditMessageText message = new EditMessageText();
					message.setMessageId(callbackQuery.getMessage().getMessageId());
					message.setText("\uD83D\uDDD1️ List <b>" + chat.lastListTitle + "</b> deleted");
					chat.editMessage(message);
				}
			}

			/* When user pressed "rename" button */
			if(callbackQuery.getData().startsWith("rename_"))
			{
				chat.lastListTitle = callbackQuery.getData().replaceFirst("rename_", "");

				if(chat.listUser.getList(chat.lastListTitle) != null)
				{
					chat.status = ChatStatus.RENAME_LIST;
					chat.sendMessage("✏️ Enter a new name for <b>" + chat.lastListTitle + "</b>");
				}
			}

			/* When user presses "Add item" button */
			if(callbackQuery.getData().startsWith("additems_"))
			{
				chat.lastListTitle = callbackQuery.getData().replaceFirst("additems_", "");

				if(chat.listUser.getList(chat.lastListTitle) != null)
				{
					chat.status = ChatStatus.ADD_ITEM;
					chat.sendMessage("Type the items you want to add to <b>" + chat.lastListTitle + "</b>");
				}
			}

			/* When user presses "Remove item" button */
			if(callbackQuery.getData().startsWith("removeitem_"))
			{
				chat.lastListTitle = callbackQuery.getData().replaceFirst("removeitem_", "");

				if(chat.listUser.getList(chat.lastListTitle) != null)
				{
					chat.status = ChatStatus.DELETE_ITEM;
					chat.sendMessage("Type the item you want to remove from <b>" + chat.lastListTitle + "</b>");
				}
			}
		}
	}

	/**
	 * The main instructions of this Action
	 */
	public abstract void execute();
}
