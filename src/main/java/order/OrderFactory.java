package order;

import menu.*;

import java.util.Collection;
import java.util.StringTokenizer;

import static menu.CourseType.*;
import static menu.MenuType.Dinner;
import static menu.MenuType.Other;

public class OrderFactory {

    IVerify verifier;

    public static void main(String[] args) {
        OrderFactory factory = new OrderFactory(new OrderFormatVerifier());
        MenuSvcInit.initialize();
        factory.create("Breakfast 1, 2, 3");

    }

    public OrderFactory(IVerify orderFormatVerifier) {
        this.verifier = orderFormatVerifier;
    }

    public Order create(String rawOrder) {

        MenuType menuName;
        Menu menu;
        Collection<Course> menuCourses;

        // Begin setup
        boolean isOrderFormatVerified = verifier.verify(rawOrder);
        if (!isOrderFormatVerified) {
            return null;
        }

        StringTokenizer tokenizer = new StringTokenizer(rawOrder);

        if (tokenizer.hasMoreElements()) {

            String possibleMenuName = tokenizer.nextToken(" ");
            try {
                menuName = MenuType.valueOf(possibleMenuName);
            }
            catch (IllegalArgumentException e) {
                return null;
            }
        }
        else {
            return null;
        }

        menu = MenuSvc.Get(menuName);

        if (menu == null) {
            return null;
        }

        menuCourses = menu.getAllCourses();

        if (menuCourses == null || menuCourses.isEmpty()) {
            return null;
        }
        //End setup

        Course mainCourse = new Course(Main);
        Course sideCourse = new Course(Side);
        Course drinkCourse = new Course(Drink);
        Course dessertCourse = new Course(Dessert);

        //Search courses for each menuID and fill corresponding list with MenuItem
        while (tokenizer.hasMoreTokens()) {

            String order = tokenizer.nextToken(", \n");
            for (Course course : menuCourses) {
                MenuItem menuItem = course.getMenuItem(order);

                if (menuItem != null) {
                    switch (course.getName()) {
                        case Main:
                            mainCourse.add(menuItem);
                            break;
                        case Side:
                            sideCourse.add(menuItem);
                            break;
                        case Drink:
                            drinkCourse.add(menuItem);
                            break;
                        case Dessert:
                            dessertCourse.add(menuItem);
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + course.getName());
                    }
                }
            }
        }

        if (drinkCourse.getItems().isEmpty()) {
            Menu otherMenu = MenuSvc.Get(Other);
            Course course = otherMenu.getCourse(Drink);
            MenuItem defaultWater = course.getMenuItem("0");
            drinkCourse.add(defaultWater);
        }

        if (menuName.equals(Dinner)) {

            boolean hasWater = false;
            for (MenuItem item : drinkCourse.getItems()) {
                if (item.toString().equals("Water")) {
                    hasWater = true;
                    break;
                }
            }

            if (!hasWater) {
                Menu dinnerMenu = MenuSvc.Get(Dinner);
                Course course = dinnerMenu.getCourse(Drink);
                MenuItem defaultWater = course.getMenuItem("0");
                drinkCourse.add(defaultWater);
            }
        }

        return new Order(menuName, mainCourse, sideCourse, drinkCourse, dessertCourse);
    }
}
