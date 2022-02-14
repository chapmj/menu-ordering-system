package menu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Course {
    private final Collection<MenuItem> menuItems;
    private final CourseType courseName;

    public Course(CourseType courseName) {
        Objects.requireNonNull(courseName);
        this.courseName = courseName;
        this.menuItems = new ArrayList<>();
    }

    public Course(CourseType courseName, MenuItem... menuItems) {
        Objects.requireNonNull(courseName);
        Objects.requireNonNull(menuItems);

        this.courseName = courseName;
        this.menuItems = new ArrayList<>();

        for (MenuItem menuItem : menuItems) {
            if (menuItem != null && menuItem.toString() != null) {
                this.menuItems.add(menuItem);
            }
        }
    }

    public boolean contains(String menuItemName) {
        for(MenuItem menuItem : menuItems) {
            if(menuItem.toString().equals(menuItemName)) {
                return true;
            }
        }
        return false;
    }

    public void add(MenuItem menuItem) {
        this.menuItems.add(menuItem);
    }

    public CourseType getName() {
        return this.courseName;
    }
    public String toString() {
        return this.courseName.toString();
    }

    public Collection<MenuItem> getItems() {
        return menuItems;
    }

    public MenuItem getMenuItem(String menuItemID) {
        for(MenuItem menuItem : menuItems) {
            if (menuItem.getID().equals(menuItemID)) {
                return menuItem;
            }
        }
        return null;
    }
}
