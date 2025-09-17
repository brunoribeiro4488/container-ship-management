package lapr.project.controller;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ArqcpControllerTest {

    @Test
    void arqcp() throws SQLException, IOException {
        ArqcpController ac = new ArqcpController();
        boolean result  = ac.arqcp("32132");
        assertTrue(result);
    }
}