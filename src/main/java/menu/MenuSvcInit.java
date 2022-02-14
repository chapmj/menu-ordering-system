package menu;

import static menu.CourseType.*;
import static menu.MenuType.*;

public class MenuSvcInit {
    public static void initialize() {
        MenuSvc.Clear();

        //Initialize menus
        MenuItem waterDrink = new MenuItem("0", "Water");

        //Breakfast
        Menu breakfast = new Menu(Breakfast,
                new Course(Main, new MenuItem("1", "Eggs")),
                new Course(Side, new MenuItem("2", "Toast")),
                new Course(Drink, new MenuItem("3", "Coffee"), waterDrink) );


        MenuSvc.Add(breakfast.getName(), breakfast);

        //Lunch
        Menu lunch = new Menu(Lunch,
                new Course(Main, new MenuItem("1", "Sandwich")),
                new Course(Side, new MenuItem("2", "Chips")),
                new Course(Drink, new MenuItem("3", "Soda"), waterDrink));
        MenuSvc.Add(lunch.getName(), lunch);

        //Dinner
        Menu dinner = new Menu(Dinner,
                new Course(Main, new MenuItem("1", "Steak")),
                new Course(Side, new MenuItem("2", "Potatoes")),
                new Course(Drink, new MenuItem("3", "Wine"), waterDrink),
                new Course(Dessert, new MenuItem("4", "Cake")));
        MenuSvc.Add(dinner.getName(), dinner);

        //Other
        Menu other = new Menu(Other,
                new Course(Drink, waterDrink));
        MenuSvc.Add(other.getName(), other);

    }
}
