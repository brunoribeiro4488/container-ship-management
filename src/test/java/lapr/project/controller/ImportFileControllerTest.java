package lapr.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;

class ImportFileControllerTest {
    @Test
    void readFileNull() throws IOException, ParseException {
        ImportFileController controller = new ImportFileController(App.getInstance().getCompany());
        try {
            controller.read(null);
            Assertions.fail("There should have been an exception");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

//    @Test
//    void readFile() throws IOException, ParseException {
//        ImportFileController controller = new ImportFileController(App.getInstance().getCompany());
//        ShipStore store = App.getInstance().getCompany().getshipStore();
//        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
//        try {
//            File f = new File("sships.csv");
//            controller.read(f);
//            Assertions.assertEquals(store.getShipByMMSI("210950000"),ship);
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//    }

}