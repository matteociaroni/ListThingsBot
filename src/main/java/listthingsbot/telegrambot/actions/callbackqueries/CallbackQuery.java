package listthingsbot.telegrambot.actions.callbackqueries;

import listthingsbot.telegrambot.actions.Action;
import listthingsbot.telegrambot.Chat;

import java.util.HashMap;
import java.util.Map;

public abstract class CallbackQuery extends Action
{
    protected Chat chat;
    protected boolean adminRequired=false;

    public CallbackQuery(Chat c)
    {
        super(c);
    }

    public abstract void execute();

    public static CallbackQuery getCallbackQuery(String commandName, Chat chat)
    {
        Map<String, CallbackQuery>commands = new HashMap<>();

        //commands.put("/lists", new ListsCommand(chat));

        return commands.get(commandName);
    }
}
