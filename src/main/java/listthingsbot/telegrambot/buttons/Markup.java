package listthingsbot.telegrambot.buttons;
import listthingsbot.listmodel.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is used to create InlineKeyboardMarkup objects (with buttons) to apply to messages.
 * This class contains only static methods.
 *
 * @author Matteo Ciaroni
 */
public class Markup
{
	/**
	 * @param listTitle
	 * @return a markup with the button "show list"
	 */
	public static InlineKeyboardMarkup showList(String listTitle)
	{
		InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> rows = new ArrayList<>();
		List<InlineKeyboardButton> row1 = new ArrayList<>();
		row1.add(Button.showList(listTitle));
		rows.add(row1);
		markup.setKeyboard(rows);
		return markup;
	}

	/**
	 * This markup is used when the user added a new item.
	 *
	 * @param listTitle
	 * @return a markup with two buttons ("show list" and "add items")
	 */
	public static InlineKeyboardMarkup addItemsOrShow(String listTitle)
	{
		InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> rows = new ArrayList<>();
		List<InlineKeyboardButton> row1 = new ArrayList<>();
		row1.add(Button.addItems(listTitle));
		row1.add(Button.showList(listTitle));
		rows.add(row1);
		markup.setKeyboard(rows);
		return markup;
	}

	/**
	 * This markup is used when a new list is created or when the user press the button to show a list.
	 *
	 * @param listTitle
	 * @param removeItem if true, the button "remove item" will be shown
	 * @return the markup with all the list option buttons
	 */
	public static InlineKeyboardMarkup listOptions(String listTitle, boolean removeItem)
	{
		InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> rows = new ArrayList<>();
		List<InlineKeyboardButton> row1 = new ArrayList<>();
		List<InlineKeyboardButton> row2 = new ArrayList<>();
		List<InlineKeyboardButton> row3 = new ArrayList<>();
		row1.add(Button.addItems(listTitle));

		if(removeItem)
			row1.add(Button.removeItem(listTitle));

		row2.add(Button.renameList(listTitle));
		row2.add(Button.deleteList(listTitle));
		row3.add(Button.allLists());
		rows.add(row1);
		rows.add(row2);
		rows.add(row3);
		markup.setKeyboard(rows);

		return markup;
	}

	/**
	 * This markup is used when the user sends the /lists command or press the "all lists" button.
	 *
	 * @param listUser
	 * @return the markup with all the list titles as buttons
	 */
	public static InlineKeyboardMarkup selectList(User listUser)
	{
		InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> rows = new ArrayList<>();

		String[] listTitles = listUser.getListsTitles().split("\n");
		Arrays.sort(listTitles);

		for(String list : listTitles)
		{
			List<InlineKeyboardButton> row = new ArrayList<>();
			row.add(Button.selectList(list));
			rows.add(row);
		}
		markup.setKeyboard(rows);

		return markup;
	}
}
