package menu;

public class MenuItem {

    private final String id;
    private final String menuItemName;

    public MenuItem(String id, String menuItemName) {
        this.id = id;
        this.menuItemName = menuItemName;
    }

    public String toString() {
        return menuItemName;
    }

    public String getID() {
        return id;
    }
}
