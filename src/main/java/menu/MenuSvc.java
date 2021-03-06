package menu;

import java.util.EnumMap;

public class MenuSvc {
    //Singleton to provide global access to all menus
    static MenuSvc instance = null;

    //MenuTree MenuType, MenuCourse, MenuItems
    private final EnumMap<MenuType, Menu> menus;

    private MenuSvc() {
        menus = new EnumMap<>(MenuType.class);
    }

    public static MenuSvc getInstance() {
        if(instance == null) instance = new MenuSvc();
        return instance;
    }

    public static boolean Contains(String menuName) {
        return getInstance().menus.containsKey(menuName);
    }

    public static Menu Get(MenuType menuName) {
        return getInstance().menus.get(menuName);
    }

    public static void Add(MenuType menuName, Menu menu) {
        getInstance().menus.put(menuName, menu);
    }

    public static void Destroy() {
        instance = null;
    }

    public static void Clear() {
        getInstance().menus.clear();
    }
}
