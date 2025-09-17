//package lapr.project.controller;
//
//import lapr.project.model.Company;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.sql.SQLException;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class CriticalPortControllerTest {
//
//    @Test
//    void getCriticalPort() throws IOException, SQLException {
//        CriticalPortController cpc = new CriticalPortController(new Company());
//        Assertions.assertFalse(cpc.getCriticalPort(2));
//        Assertions.assertNotEquals(true, cpc.getCriticalPort(2));
//        cpc = new CriticalPortController(App.getInstance().getCompany());
//        App.getInstance().getCompany().getNetworkStore().createGraph(3);
//        Assertions.assertTrue(cpc.getCriticalPort(2));
//        Assertions.assertNotEquals(false, cpc.getCriticalPort(2));
//    }
//
//    @Test
//    void print() {
//    }
//}