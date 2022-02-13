package order;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidOrderMessageQueueSvcTest {
    @AfterEach
    void destroyInstances() {
        InvalidOrderMessageQueueSvc.destroyInstance();
    }

    @Test
    void getInstance() {
        Object instance0 = InvalidOrderMessageQueueSvc.getInstance();
        assertNotNull(instance0);

        Object instance1 = InvalidOrderMessageQueueSvc.getInstance();
        assertNotNull(instance1);

        assertEquals(instance0, instance1);

        InvalidOrderMessageQueueSvc.destroyInstance();
        assertNotEquals(InvalidOrderMessageQueueSvc.getInstance(), instance0);


    }

    @Test
    void add() {
        InvalidOrderMessageQueueSvc svc = InvalidOrderMessageQueueSvc.getInstance();
        assertTrue(svc.isEmpty());
        assertEquals(svc.size(), 0);
        svc.add("Zeroth");
        assertEquals(svc.size(), 1);
        svc.add("First");
        assertEquals(svc.size(), 2);
        svc.add("Second");
        assertEquals(svc.size(), 3);
        svc.add("Third");
        assertEquals(svc.size(), 4);
        svc.add("Fourth");
        assertEquals(svc.size(), 5);
        svc.add("Fifth");
        assertEquals(svc.size(), 6);
        svc.add("Last");
        assertEquals(svc.size(), 7);
        assertFalse(svc.isEmpty());

    }

    @Test
    void remove() {
        InvalidOrderMessageQueueSvc svc = InvalidOrderMessageQueueSvc.getInstance();

        assertEquals(svc.size(), 0);
        svc.add("Zeroth");
        assertEquals(svc.size(), 1);
        svc.add("First");
        assertEquals(svc.size(), 2);
        svc.add("Second");
        assertEquals(svc.size(), 3);
        svc.add("Third");
        assertEquals(svc.size(), 4);
        svc.add("Fourth");
        assertEquals(svc.size(), 5);
        svc.add("Fifth");
        assertEquals(svc.size(), 6);
        svc.add("Last");
        assertEquals(svc.size(), 7);

        assertFalse(svc.isEmpty());
        assertEquals(svc.remove(), "Zeroth");
        assertEquals(svc.size(), 6);
        assertEquals(svc.remove(), "First");
        assertEquals(svc.size(), 5);
        assertEquals(svc.remove(), "Second");
        assertEquals(svc.size(), 4);
        assertEquals(svc.remove(), "Third");
        assertEquals(svc.size(), 3);
        assertEquals(svc.remove(), "Fourth");
        assertEquals(svc.size(), 2);
        assertEquals(svc.remove(), "Fifth");
        assertEquals(svc.size(), 1);
        assertEquals(svc.remove(), "Last");
        assertEquals(svc.size(), 0);
        assertTrue(svc.isEmpty());
    }

    @Test
    void size() {
    }

    @Test
    void isEmpty() {
    }
}