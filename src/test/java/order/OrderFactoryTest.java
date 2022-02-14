package order;

import menu.CourseType;
import menu.MenuItem;
import menu.MenuSvcInit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collection;

import static menu.CourseType.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderFactoryTest {

    private static OrderFactory factory;

    @BeforeEach
    void setUpTest() {
        MenuSvcInit.initialize();
        factory = new OrderFactory(new OrderFormatVerifier());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Breakfast 1, 2, 3", "Breakfast 1,2,3", "Breakfast 1, 2,3"})
    void createTest(String input) {
        Order order = factory.create(input);
        assertNotNull(order);

        Collection<MenuItem> desserts = order.getMenuItems(Dessert);
        assertTrue(desserts.isEmpty());
        assertEquals(0, desserts.size());

        Collection<MenuItem> sides = order.getMenuItems(Side);
        assertFalse(sides.isEmpty());
        assertEquals(1, sides.size());
        String id = ((MenuItem) sides.toArray()[0]).getID();
        assertEquals(id, "2");

        Collection<MenuItem> mains = order.getMenuItems(CourseType.Main);
        assertFalse(mains.isEmpty());
        assertEquals(1, mains.size());
        String idMain = ((MenuItem) mains.toArray()[0]).getID();
        assertEquals(idMain, "1");

        Collection<MenuItem> drinks = order.getMenuItems(CourseType.Drink);
        assertFalse(drinks.isEmpty());
        assertEquals(1, drinks.size());
        String idDrink = ((MenuItem) drinks.toArray()[0]).getID();
        assertEquals(idDrink, "3");
    }

    @Test
    void createNullOrderTest() {
        Order order = factory.create("Breakfast");
        assertNotNull(order);

        Collection<MenuItem> desserts = order.getMenuItems(Dessert);
        assertTrue(desserts.isEmpty());

        Collection<MenuItem> sides = order.getMenuItems(Side);
        assertTrue(sides.isEmpty());

        Collection<MenuItem> mains = order.getMenuItems(Main);
        assertTrue(mains.isEmpty());

        Collection<MenuItem> drinks = order.getMenuItems(Drink);
        assertFalse(drinks.isEmpty()); //Rule: All order get served water
    }

    @Test
    void createDinnerTest() {
        Order order = factory.create("Dinner 1, 2, 3, 4");
        assertNotNull(order);

        Collection<MenuItem> desserts = order.getMenuItems(Dessert);
        assertFalse(desserts.isEmpty());

        Collection<MenuItem> sides = order.getMenuItems(Side);
        assertFalse(sides.isEmpty());

        Collection<MenuItem> mains = order.getMenuItems(Main);
        assertFalse(mains.isEmpty());

        Collection<MenuItem> drinks = order.getMenuItems(Drink);
        assertFalse(drinks.isEmpty()); //Rule: All order get served water
        assertEquals(2, drinks.size());
    }

    @Test
    void createEmptyInputTest() {
        Order order = factory.create("");
        assertNull(order);
    }

    @Test
    void createGarbageInputTest() {
        Order order = factory.create("jsdlkfjlasdjf");
        assertNull(order);
    }

    @Test
    void createWrongArgumentsTest() {
        Order order = factory.create("1, 2, 3, Breakfast");
        assertNull(order);
    }

    @Test
    void createWrongArgumentsTest2() {
        Order order = factory.create("Breakfast, 1, 2, 3, Breakfast");
        assertNull(order);
    }

    @Test
    void createWrongArgumentsTest3() {
        Order order = factory.create("Breakfast 1 2, 3, Breakfast");
        assertNull(order);
    }
}