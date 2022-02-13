package order;

import menu.MenuItem;
import menu.MenuSvcInit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderFactoryTest {

    private static OrderFactory factory;

    @BeforeEach
    void setUpTest() {
        MenuSvcInit.initialize();
        factory = new OrderFactory(new OrderFormatVerifier());
    }

    @Test
    void createTest() {
        Order order = factory.create("Breakfast 1, 2, 3");
        assertNotNull(order);

        List<MenuItem> desserts = order.getDessertCourses();
        assertTrue(desserts.isEmpty());
        assertTrue(desserts.size() == 0);

        List<MenuItem> sides = order.getSideCourses();
        assertTrue(!sides.isEmpty());
        assertTrue(sides.size() == 1);
        assertEquals(sides.get(0).getID(), "2");

        List<MenuItem> mains = order.getMainCourses();
        assertTrue(!mains.isEmpty());
        assertTrue(mains.size() == 1);
        assertEquals(mains.get(0).getID(), "1");

        List<MenuItem> drinks = order.getDrinkCourses();
        assertTrue(!drinks.isEmpty());
        assertTrue(drinks.size() == 1);
        assertEquals(drinks.get(0).getID(), "3");
    }

    @Test
    void createTestValidVariation0() {
        Order order = factory.create("Breakfast 1,2,3");
        assertNotNull(order);

        List<MenuItem> desserts = order.getDessertCourses();
        assertTrue(desserts.isEmpty());
        assertTrue(desserts.size() == 0);

        List<MenuItem> sides = order.getSideCourses();
        assertTrue(!sides.isEmpty());
        assertTrue(sides.size() == 1);
        assertEquals(sides.get(0).getID(), "2");

        List<MenuItem> mains = order.getMainCourses();
        assertTrue(!mains.isEmpty());
        assertTrue(mains.size() == 1);
        assertEquals(mains.get(0).getID(), "1");

        List<MenuItem> drinks = order.getDrinkCourses();
        assertTrue(!drinks.isEmpty());
        assertTrue(drinks.size() == 1);
        assertEquals(drinks.get(0).getID(), "3");
    }

    @Test
    void createTestValidVariation1() {
        Order order = factory.create("Breakfast 1,2, 3");
        assertNotNull(order);

        List<MenuItem> desserts = order.getDessertCourses();
        assertTrue(desserts.isEmpty());
        assertTrue(desserts.size() == 0);

        List<MenuItem> sides = order.getSideCourses();
        assertTrue(!sides.isEmpty());
        assertTrue(sides.size() == 1);
        assertEquals(sides.get(0).getID(), "2");

        List<MenuItem> mains = order.getMainCourses();
        assertTrue(!mains.isEmpty());
        assertTrue(mains.size() == 1);
        assertEquals(mains.get(0).getID(), "1");

        List<MenuItem> drinks = order.getDrinkCourses();
        assertTrue(!drinks.isEmpty());
        assertTrue(drinks.size() == 1);
        assertEquals(drinks.get(0).getID(), "3");
    }

    @Test
    void createNullOrderTest() {
        Order order = factory.create("Breakfast");
        assertNotNull(order);

        List<MenuItem> desserts = order.getDessertCourses();
        assertTrue(desserts.isEmpty());

        List<MenuItem> sides = order.getSideCourses();
        assertTrue(sides.isEmpty());

        List<MenuItem> mains = order.getMainCourses();
        assertTrue(mains.isEmpty());

        List<MenuItem> drinks = order.getDrinkCourses();
        assertTrue(!drinks.isEmpty()); //Rule: All order get served water
    }

    @Test
    void createDinnerTest() {
        Order order = factory.create("Dinner 1, 2, 3, 4");
        assertNotNull(order);

        List<MenuItem> desserts = order.getDessertCourses();
        assertTrue(!desserts.isEmpty());

        List<MenuItem> sides = order.getSideCourses();
        assertTrue(!sides.isEmpty());

        List<MenuItem> mains = order.getMainCourses();
        assertTrue(!mains.isEmpty());

        List<MenuItem> drinks = order.getDrinkCourses();
        assertTrue(!drinks.isEmpty()); //Rule: All order get served water
        assertTrue(drinks.size() == 2);
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