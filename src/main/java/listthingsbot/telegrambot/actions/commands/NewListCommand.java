package listthingsbot.telegrambot.actions.commands;

import listthingsbot.telegrambot.Chat;
import listthingsbot.telegrambot.ChatStatus;

public class NewListCommand extends Command
{
    public NewListCommand(Chat c)
    {
        super(c);
        super.adminRequired=true;
    }

    @Override
    public void execute()
    {
        chat.status=ChatStatus.ADD_LIST;
        chat.sendMessage("Type the <b>name</b> of the new list");
    }
}
