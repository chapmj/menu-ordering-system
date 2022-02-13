package order.ordervalidator;

import order.InvalidOrderMessageQueueSvc;
import order.OrderValidatorSvc;
import order.ordervalidator.AlwaysTrueValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderValidatorSvcTest {
    @AfterEach
    void destroyInstanceTeardown() {
        OrderValidatorSvc.destroyInstance();
        InvalidOrderMessageQueueSvc.getInstance().clear();
        InvalidOrderMessageQueueSvc.destroyInstance();
    }

    @Test
    void getInstanceTest() {
        Object instance0 = OrderValidatorSvc.getInstance();
        assertNotNull(instance0);

        Object instance1 = OrderValidatorSvc.getInstance();
        assertNotNull(instance1);

        assertEquals(instance0, instance1);

        OrderValidatorSvc.destroyInstance();
        assertNotEquals(OrderValidatorSvc.getInstance(), instance0);
    }

    @Test
    void addRuleTest() {
        try {
            OrderValidatorSvc.AddRule("Brunch", new AlwaysTrueValidator());
            OrderValidatorSvc.AddRule("Linner", new AlwaysTrueValidator());
            OrderValidatorSvc svc = OrderValidatorSvc.getInstance();
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    void missingOrderTest() {
        OrderValidatorSvc.AddRule("Brunch", new AlwaysTrueValidator());
        OrderValidatorSvc.AddRule("Linner", new AlwaysTrueValidator());
        OrderValidatorSvc svc = OrderValidatorSvc.getInstance();
        svc.validate(null);
        assertEquals(InvalidOrderMessageQueueSvc.getInstance().remove(), "Unable to validate order");
    }


}