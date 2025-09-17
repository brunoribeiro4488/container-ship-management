package lapr.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class GetRouteOfAContainerControllerTest {

    @Test
    void getRouteOfAContainer() throws SQLException, IOException {
        GetRouteOfAContainerController c = new GetRouteOfAContainerController();
        Assertions.assertFalse(c.getRouteOfAContainer("6654","6876874"));
        Assertions.assertTrue(c.getRouteOfAContainer("12345", "78546321458"));
        Assertions.assertNotEquals(false, c.getRouteOfAContainer("12345", "78546321458"));
        Assertions.assertNotEquals(true, c.getRouteOfAContainer("6654","6876874"));
    }

}