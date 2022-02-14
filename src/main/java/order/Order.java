package order;

import menu.Course;
import menu.CourseType;
import menu.MenuItem;
import menu.MenuType;

import java.util.Collection;
import java.util.EnumMap;

public class Order {
    MenuType menuName;
    EnumMap<CourseType, Collection<MenuItem>> courseMap;

    public Order(MenuType menuName, Course... courses) {
        this.menuName = menuName;
        this.courseMap = new EnumMap<>(CourseType.class);

        for(Course course : courses) {
            this.courseMap.put(course.getName(), course.getItems());
        }
    }

    public MenuType getName() {
        return menuName;
    }
    public String getMenuName() {
        return menuName.toString();
    }

    public Collection<MenuItem> getMenuItems(CourseType courseType) {
        Collection<MenuItem> menuItems = this.courseMap.get(courseType);
        return menuItems;
    }

}
