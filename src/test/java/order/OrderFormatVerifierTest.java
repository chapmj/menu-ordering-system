package order;

import menu.IVerify;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderFormatVerifierTest {
    @Test
    public void testVerify() {
        IVerify verifier = new OrderFormatVerifier();
        assertTrue(verifier.verify("Breakfast 1,2,3"));
        assertTrue(verifier.verify("Breakfast 2,3,1"));
        assertTrue(verifier.verify("Breakfast 1,2,3,3,3"));
        assertTrue(verifier.verify("Breakfast 1"));
        assertTrue(verifier.verify("Lunch 1,2,3"));
        assertTrue(verifier.verify("Lunch 1,2"));
        assertTrue(verifier.verify("Lunch 1,1,2, 3"));
        assertTrue(verifier.verify("Lunch 1,2,2"));
        assertTrue(verifier.verify("Lunch"));
        assertTrue(verifier.verify("Dinner 1,2,3,4"));
        assertTrue(verifier.verify("Dinner 1,2,3"));
        assertTrue(verifier.verify("Dinner 987654321"));
        assertTrue(verifier.verify("Dinner "));
        assertFalse(verifier.verify(""));
        assertFalse(verifier.verify("Lunch 1 1 1"));
        assertFalse(verifier.verify("Lunch 1, 2 2"));
        assertFalse(verifier.verify("1, 2, 3"));



    }

}