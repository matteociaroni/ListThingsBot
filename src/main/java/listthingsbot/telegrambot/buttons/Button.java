package listthingsbot.telegrambot.buttons;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

/**
 * This class is used to create InlineKeyboardButtons objects.
 * This class contains only static methods.
 *
 * @author Matteo Ciaroni
 */
public class Button
{
	/**
	 * @param name the name showed on the button
	 * @param callbackQuery the callback query executed when the button is pressed
	 * @return a generic button
	 */
	public static InlineKeyboardButton of(String name, String callbackQuery)
	{
		InlineKeyboardButton button = new InlineKeyboardButton();
		button.setText(name);
		if(callbackQuery != null)
			button.setCallbackData(callbackQuery);
		return button;
	}

	/**
	 * @return the button to show all lists
	 */
	public static InlineKeyboardButton allLists()
	{
		return Button.of("\uD83D\uDD19 All lists", "lists");
	}

	/**
	 * @param listTitle
	 * @return the button to select the list
	 */
	public static InlineKeyboardButton selectList(String listTitle)
	{
		return Button.of(listTitle, "show_" + listTitle);
	}

	/**
	 * @param listTitle
	 * @return the button to show the list
	 */
	public static InlineKeyboardButton showList(String listTitle)
	{
		return Button.of("\uD83D\uDDD2 Show list", "show_" + listTitle);
	}

	/**
	 * @param listTitle
	 * @return the button to add items to the list
	 */
	public static InlineKeyboardButton addItems(String listTitle)
	{
		return Button.of("➕ Add items", "additems_" + listTitle);
	}

	/**
	 * @param listTitle
	 * @return the button to remove an item from the list
	 */
	public static InlineKeyboardButton removeItem(String listTitle)
	{
		return Button.of("➖ Remove item", "removeitem_" + listTitle);
	}

	/**
	 * @param listTitle
	 * @return the button to delete the list
	 */
	public static InlineKeyboardButton deleteList(String listTitle)
	{
		return Button.of("\uD83D\uDDD1 Delete list", "delete_" + listTitle);
	}

	/**
	 * @param listTitle
	 * @return the button to rename the list
	 */
	public static InlineKeyboardButton renameList(String listTitle)
	{
		return Button.of("✏️ Rename list", "rename_" + listTitle);
	}
}
