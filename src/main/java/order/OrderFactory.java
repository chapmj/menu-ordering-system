package order;

import menu.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class OrderFactory {

    IVerify verifier;

    public OrderFactory(IVerify orderFormatVerifier) {
        this.verifier = orderFormatVerifier;
    }

    public Order create(String rawOrder) {

        //<!--Begin setup nullchecks
        boolean isOrderFormatVerified = verifier.verify(rawOrder);
        if(!isOrderFormatVerified) return null;

        String[] ordersArr = rawOrderToArray(rawOrder);
        if(ordersArr == null) return null;

        String menuName = ordersArr[0];
        Menu menu = MenuSvc.Get(menuName);
        if(menu == null) return null;

        Collection<Course> courses;
        courses = menu.getAllCourses();
        if(courses == null || courses.isEmpty()) return null;
        //End setup nullchecks -->

        //Set up parameter vars for Order object
        List<MenuItem> mainCourses = new ArrayList<>();
        List<MenuItem> sideCourses = new ArrayList<>();
        List<MenuItem> drinkCourses = new ArrayList<>();
        List<MenuItem> dessertCourses = new ArrayList<>();

        Arrays.stream(ordersArr).forEach((order) -> {
            //Search courses for each menuID and fill corresponding list with MenuItem
            for (Course course : courses) {

                MenuItem menuItem = course.getMenuItem(order);

                if (menuItem != null) {
                    switch (course.toString()) {
                        case "Main":
                            mainCourses.add(menuItem);
                            break;
                        case "Side":
                            sideCourses.add(menuItem);
                            break;
                        case "Drink":
                            drinkCourses.add(menuItem);
                            break;
                        case "Dessert":
                            dessertCourses.add(menuItem);
                            break;

                        default:
                            //Skip id
                            break;
                    }
                }
            }
        });

        if(drinkCourses.isEmpty()) {
            Menu otherMenu = MenuSvc.Get("Other");
            Course course = otherMenu.getCourse("Drink");
            MenuItem defaultWater = course.getMenuItem("0");
            drinkCourses.add(defaultWater);
        }

        if(menuName.equals("Dinner")) {

            boolean hasWater = false;
            for(MenuItem item : drinkCourses) {
                if(item.toString().equals("Water")) {
                    hasWater = true;
                    break;
                }
            }

            if(!hasWater) {
                Menu dinnerMenu = MenuSvc.Get("Dinner");
                Course course = dinnerMenu.getCourse("Drink");
                MenuItem defaultWater = course.getMenuItem("0");
                drinkCourses.add(defaultWater);
            }

        }

        return new Order(menuName, mainCourses, sideCourses, drinkCourses, dessertCourses);
    }

    private String[] rawOrderToArray(String rawOrder) {

        String[] halves = rawOrder.split(" ", 2);

        if(halves.length == 2) {
            String head = halves[0];
            String tail = halves[1];
            String[] orders = tail.split(",");

            String[] ordersArr = new String[1 + orders.length];

            //join head + orders
            ordersArr[0] = head;
            for(int i = 0; i < orders.length; i++) {
                ordersArr[i + 1] = orders[i].trim();
            }

            return ordersArr;

        } else if(halves.length == 1) {
            String[] ordersArr = new String[1];
            ordersArr[0] = halves[0];
            return ordersArr;

        } else {
            return null;
        }
    }
}
