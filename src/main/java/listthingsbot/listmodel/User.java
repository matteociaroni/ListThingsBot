package listthingsbot.listmodel;

import java.io.Serializable;
import java.util.HashMap;

/**
 * This class describes a user
 * It provides different methods to manage lists
 *
 * @author Matteo Ciaroni
 */
public class User implements Serializable
{
    /**
     * Unique id of the user
     */
    private final String id;

    /**
     * The collection of lists
     */
    private final HashMap<String, List> lists;

    public User(String id)
    {
        this.id=id;
        this.lists=new HashMap<>();
    }

    /**
     * @return the unique id of the user
     */
    public String getId()
    {
        return this.id;
    }

    /**
     * Creates a new list
     * @param title the title of the new list created
     */
    public void newList(String title)
    {
        if(getList(title)==null)
            lists.put(title, new List(title));
    }

    /**
     * Creates a new list copying all items from another list
     *
     * @param title         the title of the new list created
     * @param listToCopy    the existent list to copy in the new one
     */
    public void newList(String title, List listToCopy)
    {
        if(getList(title)==null)
            lists.put(title, new List(title, listToCopy));
    }

    /**
     * Deletes a list
     * @param title the title of the list to remove
     */
    public void removeList(String title)
    {
        if(getList(title)!=null)
            lists.remove(title);
    }

    /**
     * @return a string listing all the lists
     */
    public String getListsTitles()
    {
        StringBuilder result=new StringBuilder();

        for(String title : lists.keySet())
            result.append(title+"\n");

        return result.toString();
    }

    /**
     * Renames a list
     * @param oldTitle  is the old title of the list
     * @param newTitle  is the new title of the list
     */
    public void renameList(String oldTitle, String newTitle)
    {
        newList(newTitle, getList(oldTitle));
        removeList(oldTitle);
    }

    /**
     * @param title the title of the list to search
     * @return a list based the title
     */
    public List getList(String title)
    {
        return lists.get(title);
    }


    /**
     * @return the number of lists of the user
     */
    public int countLists()
    {
        return lists.size();
    }
}
