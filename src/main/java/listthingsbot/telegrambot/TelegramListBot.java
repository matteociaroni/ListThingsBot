package listthingsbot.telegrambot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * This class controls the Telegram Bot.
 * It can send messages and receive update from Telegram.
 *
 * @author Matteo Ciaroni
 */
public class TelegramListBot extends TelegramLongPollingBot implements Serializable
{
    /**
     * The HashMap object to manage the conversation between bot and user
     */
    private final HashMap<String, Chat> chats;

    /**
     * The Telegram bot token provided by BotFather
     */
    private final String token;

    /**
     * The username of the Telegram bot
     */
    private final String username;

    public TelegramListBot(String token, String username)
    {
        chats=loadChats();
        this.token=token;
        this.username=username;
    }

    /**
     * This method manages updates from Telegram like new messages from users, buttons pressed...
     * Updates with messages and callback queries are passed to the onUpdate method of a Chat object
     *
     * @param update the object which contains all the information about the update
     */
    @Override
    public void onUpdateReceived(Update update)
    {
        if(update.hasMessage() || update.hasCallbackQuery())
        {
            String chatId;


            if(update.hasMessage())
                chatId=update.getMessage().getChatId().toString();
            else
                chatId=update.getCallbackQuery().getMessage().getChatId().toString();

            if(!chats.containsKey(chatId))
            {
                chats.put(chatId, new Chat(chatId, this));
                writeLog("New chat: "+chatId+"\tusername: "+update.getMessage().getChat().getUserName());
            }
            chats.get(chatId).onUpdate(update);
            saveChats(chats);
        }
    }

    /**
     * This method appends a line to the log file
     *
     * @param log the string to append to the file
     */
    private void writeLog(String log)
    {
        String filename=Secrets.BOT_USERNAME+"-log.txt";
        try
        {
            BufferedWriter b=new BufferedWriter(new FileWriter(filename, true));
            b.write(LocalDateTime.now()+"\t"+log+"\n");
            b.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdatesReceived(List<Update> updates)
    {
        super.onUpdatesReceived(updates);
    }

    /**
     * @return the Telegram bot username
     */
    public String getBotUsername()
    {
        return this.username;
    }

    /**
     * @return the Telegram bot token, provided by BotFather
     */
    @Override
    public String getBotToken()
    {
        return this.token;
    }

    public void saveChats(HashMap<String, Chat> chats)
    {
        try
        {
            ObjectOutputStream stream=new ObjectOutputStream(new FileOutputStream("Chats.bin"));
            stream.writeObject(chats);
            stream.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public HashMap<String, Chat> loadChats()
    {
        HashMap<String, Chat> result;
        try
        {
            ObjectInputStream stream=new ObjectInputStream(new FileInputStream("Chats.bin"));
            result=(HashMap<String, Chat>) stream.readObject();
            stream.close();
        }
        catch(IOException | ClassNotFoundException e)
        {
            result=new HashMap<>();
        }
        return result;
    }
}
