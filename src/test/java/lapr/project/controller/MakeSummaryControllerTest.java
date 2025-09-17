//package lapr.project.controller;
//
//import lapr.project.model.Ship;
//import lapr.project.utils.stores.ShipStore;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.text.ParseException;
//
//class MakeSummaryControllerTest {
//
//    @Test
//    void createSummary() {
//        ShipStore store = App.getInstance().getCompany().getshipStore();
//        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
//        store.saveShipInBST(ship);
//        try {
//            store.addShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
//        } catch (ParseException e) {
//        }
//
//        MakeSummaryController controller = new MakeSummaryController(App.getInstance().getCompany());
//        try {
//            boolean actual = controller.createSummary("210950000");
//            boolean creationError = controller.createSummary("123456789");
//            Assertions.assertEquals(true, actual);
//            Assertions.assertEquals(false, creationError);
//            Assertions.assertNotEquals(false, actual);
//            Assertions.assertNotEquals(true, creationError);
//        } catch (IOException e) {
//        }
//    }
//
//    @Test
//    void print() {
//    }
//}