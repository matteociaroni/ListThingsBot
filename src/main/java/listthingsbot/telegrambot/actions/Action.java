package listthingsbot.telegrambot.actions;

import listthingsbot.telegrambot.Chat;
import listthingsbot.telegrambot.ChatStatus;
import listthingsbot.telegrambot.actions.commands.Command;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

public abstract class Action
{
    protected Chat chat;
    protected boolean adminRequired=false;

    public Action(Chat c)
    {
        this.chat=c;
    }

    public abstract void execute();

    /**
     * This method is called when the update received from Telegram has a message.
     * It can send and edit messages.
     *
     * @param message the message which generated the update event
     */
    public static void elaborate(Chat chat, Message message)
    {
        /* When the user sends the "lists" command */
        if(message.isCommand() && message.getText().startsWith("/lists"))
            Command.getCommand("/lists", chat).execute();

            /* Check if user is admin in a group or if it's a private chat */
        else if(!message.isGroupMessage() || chat.isUserAdmin(message.getFrom().getId()))
        {
            /* Messages containing Telegram Commands */
            if(message.isCommand())
                Command.getCommand(message.getText().trim(), chat).execute();

                /* Messages with no Telegram commands: instructions are based on the chat status */
            else
            {
                /* When the user sends a message with the title of a new list */
                if(chat.status==ChatStatus.ADD_LIST)
                    chat.addList(message.getText().trim());

                /* When the user sends a message with the new title of a list to rename */
                else if(chat.status==ChatStatus.RENAME_LIST)
                    chat.renameList(chat.lastListTitle, message.getText().trim());

                /* When the user sends a message with a new item to add to a list */
                else if(chat.status==ChatStatus.ADD_ITEM)
                    chat.addItems(chat.lastListTitle, message.getText().trim().split("\n"));

                /* When the user sends a message with a new item to remove from a list */
                else if(chat.status==ChatStatus.DELETE_ITEM)
                {
                    try
                    {
                        chat.removeItem(chat.lastListTitle, Integer.parseInt(message.getText()));
                    }
                    catch(NumberFormatException e)
                    {
                        SendMessage sendMessage=new SendMessage();
                        sendMessage.setText("⚠️ Not a valid number");
                        chat.sendMessage(sendMessage);
                    }
                }
                chat.status=ChatStatus.DEFAULT;
            }
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
            EditMessageText message=new EditMessageText();
            message.setMessageId(callbackQuery.getMessage().getMessageId());
            String listTitle=callbackQuery.getData().replace("show_", "");
            message.setText("\uD83D\uDDD2  <b>"+listTitle+"</b>\n\n"+chat.listUser.getList(listTitle).toString());
            message.setReplyMarkup(chat.getButtonsWithListOptions(listTitle));
            chat.editMessage(message);
        }

        /* When user pressed "all lists" button */
        if(callbackQuery.getData().equals("lists"))
        {
            EditMessageText message=new EditMessageText();
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
                chat.lastListTitle=callbackQuery.getData().replaceFirst("delete_", "");

                if(chat.listUser.getList(chat.lastListTitle)!=null)
                {
                    chat.listUser.removeList(chat.lastListTitle);
                    EditMessageText message=new EditMessageText();
                    message.setMessageId(callbackQuery.getMessage().getMessageId());
                    message.setText("\uD83D\uDDD1️ List <b>"+chat.lastListTitle+"</b> deleted");
                    chat.editMessage(message);
                }
            }

            /* When user pressed "rename" button */
            if(callbackQuery.getData().startsWith("rename_"))
            {
                chat.lastListTitle=callbackQuery.getData().replaceFirst("rename_", "");

                if(chat.listUser.getList(chat.lastListTitle)!=null)
                {
                    chat.status=ChatStatus.RENAME_LIST;
                    chat.sendMessage("✏️ Enter a new name for <b>"+chat.lastListTitle+"</b>");
                }
            }

            /* When user presses "Add item" button */
            if(callbackQuery.getData().startsWith("additems_"))
            {
                chat.lastListTitle=callbackQuery.getData().replaceFirst("additems_", "");

                if(chat.listUser.getList(chat.lastListTitle)!=null)
                {
                    chat.status=ChatStatus.ADD_ITEM;
                    chat.sendMessage("Type the items you want to add to <b>"+chat.lastListTitle+"</b>");
                }
            }

            /* When user presses "Remove item" button */
            if(callbackQuery.getData().startsWith("removeitem_"))
            {
                chat.lastListTitle=callbackQuery.getData().replaceFirst("removeitem_", "");

                if(chat.listUser.getList(chat.lastListTitle)!=null)
                {
                    chat.status=ChatStatus.DELETE_ITEM;
                    chat.sendMessage("Type the item you want to remove from <b>"+chat.lastListTitle+"</b>");
                }
            }
        }
    }
}
