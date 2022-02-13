package menu;

import menu.Course;
import menu.Menu;
import menu.MenuItem;
import menu.MenuSvc;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuSvcTest {

    @Test
    void addTest() {
        MenuSvc.Clear();

        try {
            //Initialize menus
            MenuItem waterDrink = new MenuItem("0", "Water");

            //Breakfast
            Menu breakfast = new Menu("Breakfast",
                    new Course("Main", new MenuItem("1", "Eggs")),
                    new Course("Side", new MenuItem("2", "Toast")),
                    new Course("Drink", new MenuItem("3", "Coffee"), waterDrink));


            MenuSvc.Add(breakfast.toString(), breakfast);

            //Lunch
            Menu lunch = new Menu("Lunch",
                    new Course("Main", new MenuItem("1", "Sandwich")),
                    new Course("Side", new MenuItem("2", "Chips")),
                    new Course("Drink", new MenuItem("3", "Soda"), waterDrink));
            MenuSvc.Add(lunch.toString(), lunch);

            //Dinner
            Menu dinner = new Menu("Dinner",
                    new Course("Main", new MenuItem("1", "Steak")),
                    new Course("Side", new MenuItem("2", "Potatoes")),
                    new Course("Drink", new MenuItem("3", "Wine"), waterDrink),
                    new Course("Dessert", new MenuItem("4", "Cake")));
            MenuSvc.Add(dinner.toString(), dinner);

            //Other
            Menu other = new Menu("Other",
                    new Course("Drink", waterDrink));
            MenuSvc.Add(other.toString(), other);
        } catch (Exception e) {
            assertFalse(true);
        }

    }

    @Test
    void getTest() {

        //Breakfast

        Menu breakfast = MenuSvc.Get("Breakfast");
        Course bMain = breakfast.getCourse("Main");
        assertEquals(bMain.toString(), "Main");
        bMain.contains("Eggs");

        Course bSide = breakfast.getCourse("Side");
        assertEquals(bSide.toString(), "Side");
        bSide.contains("Toast");

        Course bDrink = breakfast.getCourse("Drink");
        assertEquals(bDrink.toString(), "Drink");
        bDrink.contains("Coffee");
        bDrink.contains("Water");

        Course bDessert = breakfast.getCourse("Dessert");
        assertNull(bDessert);


        //Lunch

        Menu lunch = MenuSvc.Get("Lunch");
        assertEquals(lunch.toString(), "Lunch");

        Course lMain = lunch.getCourse("Main");
        assertEquals(lMain.toString(), "Main");
        assertTrue(lMain.contains("Sandwich"));

        Course lSide = lunch.getCourse("Side");
        assertEquals(lSide.toString(), "Side");
        assertTrue(lSide.contains("Chips"));

        Course lDrink = lunch.getCourse("Drink");
        assertEquals(lDrink.toString(), "Drink");
        assertTrue(lDrink.contains("Soda"));
        assertTrue(lDrink.contains("Water"));

        Course lDessert = lunch.getCourse("Dessert");
        assertNull(lDessert);

        //Dinner

        Menu dinner = MenuSvc.Get("Dinner");
        assertEquals(dinner.toString(), "Dinner");

        Course dMain = dinner.getCourse("Main");
        assertEquals(dMain.toString(), "Main");
        assertTrue(dMain.contains("Steak"));

        Course dSide = dinner.getCourse("Side");
        assertEquals(dSide.toString(), "Side");
        assertTrue(dSide.contains("Potatoes"));

        Course dDrink = dinner.getCourse("Drink");
        assertEquals(dDrink.toString(), "Drink");
        assertTrue(dDrink.contains("Wine"));
        assertTrue(dDrink.contains("Water"));

        Course dDessert = dinner.getCourse("Dessert");
        assertEquals(dDessert.toString(), "Dessert");
        assertTrue(dDessert.contains("Cake"));


    }

    @Test
    public void MenuSvcDestroyTest() {
        MenuSvc svc0 = MenuSvc.getInstance();
        MenuSvc.Destroy();
        MenuSvc svc1 = MenuSvc.getInstance();
        assertNotSame(svc0, svc1);

    }

}