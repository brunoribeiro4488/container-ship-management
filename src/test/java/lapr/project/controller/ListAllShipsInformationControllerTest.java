package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.utils.stores.ShipStore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

class ListAllShipsInformationControllerTest {

    @Test
    void getInformationAllShips() {
        ShipStore store = App.getInstance().getCompany().getshipStore();
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        store.saveShipInBST(ship);
        try {
            store.addShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        } catch (ParseException e) {
        }

        ListAllShipsInformationController controller = new ListAllShipsInformationController(App.getInstance().getCompany());

        boolean actual = controller.getInformationAllShips();

        Assertions.assertEquals(true, actual);
    }

    @Test
    void getInformationAllShipsFalse() {

        ListAllShipsInformationController controller = new ListAllShipsInformationController(new Company());

        boolean actual = controller.getInformationAllShips();

        Assertions.assertEquals(false, actual);
    }

    @Test
    void print() {
    }
}