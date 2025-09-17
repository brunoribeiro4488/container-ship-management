package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.utils.Pair;
import lapr.project.utils.stores.ShipStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShipPairControllerTest {



    @Test
    void pairOfShips() throws ParseException, IOException {
        Company company = new Company();
        ShipStore store = company.getshipStore();
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        store.saveShipInBST(ship);
        Ship ship1 = new Ship("228339600", "ALMAVIVA","IMO9450648","FLSU","70","334","42","15");
        store.saveShipInBST(ship1);
        store.addShipPosition("210950000","31/12/2020 17:03","20.92236","-20.97243","12.5","2.4","358","NA","B");
        store.addShipPosition("228339600","31/12/2020 17:03","20.90000","-21.0","12.5","2.4","358","NA","B");
        store.addShipPosition("210950000","31/12/2020 17:04","42.92236","-66.97243","12.5","2.4","358","NA","B");
        store.addShipPosition("228339600","31/12/2020 17:04","42.90000","-67.0","12.5","2.4","358","NA","B");
        List<Pair<Ship, Ship>> pair= new ArrayList<Pair<Ship, Ship>>();
        pair.add(new Pair<>(ship1, ship));
        ShipPairController controller = new ShipPairController(company);
        Assertions.assertEquals(pair, controller.pairOfShips());
    }
}