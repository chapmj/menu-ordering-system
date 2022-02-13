package menu;

import java.util.HashMap;
import java.util.Objects;

public class Course {
    private final HashMap<String, MenuItem> menuItems;
    private final String courseName;

    public Course(String courseName, MenuItem... menuItems) {
        Objects.requireNonNull(courseName);
        Objects.requireNonNull(menuItems);

        this.courseName = courseName;
        this.menuItems = new HashMap<>();

        for(int i = 0; i < menuItems.length; i++) {
            if(menuItems[i] != null && menuItems[i].toString() != null) {
                this.menuItems.put(menuItems[i].toString(), menuItems[i]);
            }
        }
    }

    public boolean contains(String menuItemName) {
        return menuItems.containsKey(menuItemName);
    }

    public void add(MenuItem menuItem) {
        this.menuItems.put(menuItem.toString(), menuItem);
    }

    public String toString() {
        return this.courseName;
    }

    public MenuItem getMenuItem(String menuItemID) {
        for(MenuItem menuItem : menuItems.values()) {
            if (menuItem.getID().equals(menuItemID)) {
                return menuItem;
            }
        }
        return null;
    }
}
