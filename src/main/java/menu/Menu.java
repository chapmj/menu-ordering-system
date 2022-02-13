package menu;

import java.util.Collection;
import java.util.HashMap;

public class Menu {

    private final HashMap<String, Course> courses;
    private final String menuName;

    public Menu(String menuName, Course... courses) {
        this.menuName = menuName;
        this.courses = new HashMap<>();

        if(courses != null) {
            for (Course course : courses) {
                if (course != null) {
                    this.courses.put(course.toString(), course);
                }
            }
        }
    }

    public Course getCourse(String courseName) {
        return courses.get(courseName);
    }

    public Collection<Course> getAllCourses(){

        return courses.values();
    }

    public String toString() {
        return this.menuName;
    }



}
