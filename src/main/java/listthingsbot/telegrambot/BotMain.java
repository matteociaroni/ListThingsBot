package listthingsbot.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * This class has only the main method.
 * The main method it's the default method to execute to start the Telegram bot
 *
 * @author Matteo Ciaroni
 */
public class BotMain
{
    /**
     * Create an instance of a TelegramListBot object and executes it
     */
    public static void main(String[] args) throws TelegramApiException
    {
        TelegramBotsApi api=new TelegramBotsApi(DefaultBotSession.class);
        try
        {
            api.registerBot(new TelegramListBot(System.getenv("BOT_TOKEN"),System.getenv("BOT_USERNAME")));
        }
        catch(TelegramApiRequestException e)
        {
            System.out.println("Registration error!");
            e.printStackTrace();
        }
    }
}