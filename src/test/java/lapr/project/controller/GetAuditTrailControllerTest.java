package lapr.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class GetAuditTrailControllerTest {

    @Test
    void getAuditTrail() throws SQLException, IOException {
        GetAuditTrailController c = new GetAuditTrailController();
        Assertions.assertFalse(c.getAuditTrail("67645", "6465", "78754545"));
        Assertions.assertTrue(c.getAuditTrail("78546321458", "4785", "11111"));
        Assertions.assertNotEquals(false, c.getAuditTrail("78546321458", "4785", "11111"));
        Assertions.assertNotEquals(true, c.getAuditTrail("67645", "6465", "78754545"));

    }

    @Test
    void print() {
    }
}