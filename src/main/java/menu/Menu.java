package menu;

import java.util.Collection;
import java.util.EnumMap;

public class Menu {

    private final EnumMap<CourseType, Course> courses;
    private final MenuType menuName;

    public Menu(MenuType menuName, Course... courses) {
        this.menuName = menuName;
        this.courses = new EnumMap<>(CourseType.class);

        for (Course course : courses) {
            if (course != null) {
                this.courses.put(course.getName(), course);
            }
        }
    }

    public Course getCourse(CourseType courseName) {
        return courses.get(courseName);
    }

    public Collection<Course> getAllCourses(){

        return courses.values();
    }

    public String toString() {
        return this.menuName.toString();
    }

    public MenuType getName() {
        return this.menuName;
    }
}
