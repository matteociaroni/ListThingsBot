package listthingsbot.telegrambot.actions.commands;

import listthingsbot.telegrambot.Chat;
import listthingsbot.telegrambot.ChatStatus;

/**
 * This class represents the /start command.
 * This command sends a message to the user with some basic information about the bot.
 *
 * @author Matteo Baldaccioni
 */
public class StartCommand extends Command
{
    public StartCommand(Chat c)
    {
        super(c);
        super.adminRequired = true;
    }

    @Override
    public void execute()
    {
        chat.status = ChatStatus.DEFAULT;
        String helpMessage = "<b>\uD83D\uDDD2 ListThingsBot</b> v1.01\n\n" +
                "Hi!\n" +
                "Type /help for a quick guide on operating commands.";
        chat.sendMessage(helpMessage);
    }
}
