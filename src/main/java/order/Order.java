package order;

import menu.MenuItem;

import java.util.List;

public class Order {
    String menu;
    List<MenuItem> mainCourses;
    List<MenuItem> sideCourses;
    List<MenuItem> drinkCourses;
    List<MenuItem> dessertCourses;

    public Order(String menu, List<MenuItem> mainCourses, List<MenuItem> sideCourses, List<MenuItem> drinkCourses, List<MenuItem> dessertCourses) {
        this.menu = menu;
        this.mainCourses = mainCourses;
        this.sideCourses = sideCourses;
        this.drinkCourses = drinkCourses;
        this.dessertCourses = dessertCourses;
    }

    public String getMenu() {
        return menu;
    }

    public List<MenuItem> getMainCourses() {
        return mainCourses;

    }
    public List<MenuItem> getSideCourses() {
        return sideCourses;

    }
    public List<MenuItem> getDrinkCourses() {
        return drinkCourses;

    }
    public List<MenuItem> getDessertCourses() {
        return dessertCourses;
    }

}
