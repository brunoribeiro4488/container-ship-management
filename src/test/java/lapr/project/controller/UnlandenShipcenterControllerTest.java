package lapr.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class UnlandenShipcenterControllerTest {

    @Test
    void getCenterOfMassOfShips() throws IOException {
        UnlandenShipcenterController unlsc = new UnlandenShipcenterController();
        Assertions.assertTrue(unlsc.getCenterOfMassOfShips());
        Assertions.assertNotEquals(false, unlsc.getCenterOfMassOfShips());
    }
}