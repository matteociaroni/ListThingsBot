package listthingsbot.listmodel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class describes a list as a collection of strings
 *
 * @author Matteo Ciaroni
 */
public class List implements Serializable
{
	/**
	 * The collection of strings
	 */
	private final java.util.List<String> content;

	/**
	 * The title of the list
	 */
	private final String title;

	public List(String title)
	{
		this.title = title;
		this.content = new ArrayList<>();
	}

	public List(String title, List that)
	{
		this.title = title;
		this.content = new ArrayList<>(that.content);
	}

	/**
	 * @return the list title
	 */
	public String getTitle()
	{
		return this.title;
	}

	/**
	 * Adds a new item to the list
	 *
	 * @param item the item to add to the list
	 */
	public void addItem(String item)
	{
		content.add(item);
	}

	/**
	 * Removes an item from the list
	 *
	 * @param item the item to remove from the list
	 */
	public void removeItem(String item)
	{
		content.remove(item);
	}

	/**
	 * Removes an item from the list
	 *
	 * @param item the position of item to remove from the list
	 */
	public void removeItem(int item)
	{
		if(item > 0 && item <= size())
			content.remove(item - 1);
	}

	/**
	 * @return the number of element in the list
	 */
	public int size()
	{
		return content.size();
	}

	/**
	 * @return the list in a pretty way
	 */
	@Override
	public String toString()
	{
		StringBuilder result = new StringBuilder();
		int count = 0;

		if(content.size() == 0)
			result.append("(empty list)");
		else
			for(String item : content)
				result.append(++count).append(". ").append(item).append("\n");

		return result.toString();
	}
}
