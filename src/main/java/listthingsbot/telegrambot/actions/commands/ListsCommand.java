package listthingsbot.telegrambot.actions.commands;

import listthingsbot.telegrambot.Chat;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class ListsCommand extends Command
{
    public ListsCommand(Chat c)
    {
        super(c);
    }

    @Override
    public void execute()
    {
        if(chat.listUser.countLists()>0)
        {
            SendMessage sendMessage=new SendMessage();
            sendMessage.setReplyMarkup(chat.getButtonsWithListTitles("show_"));
            sendMessage.setText("Select the list to show ⬇️");
            chat.sendMessage(sendMessage);
        }
        else
            chat.sendMessage("Nothing to show \uD83D\uDE41\nCreate a new list with /newlist");
    }
}
