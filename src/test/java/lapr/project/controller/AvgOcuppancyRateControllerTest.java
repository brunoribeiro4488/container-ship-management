package lapr.project.controller;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AvgOcuppancyRateControllerTest {

    @Test
    void avgOcuppancyRate() throws SQLException, IOException {
        AvgOcuppancyRateController avgOcuppancyRateController = new AvgOcuppancyRateController();
        boolean result=avgOcuppancyRateController.avgOcuppancyRate("789789789","2021-03-18 00:16:01","2021-03-28 00:16:01");
        assertTrue(result);
    }

    @Test
    void print() {
    }
}