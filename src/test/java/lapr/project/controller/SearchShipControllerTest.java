package lapr.project.controller;

import lapr.project.utils.dto.ShipDTO;
import lapr.project.model.Ship;
import lapr.project.utils.stores.ShipStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class SearchShipControllerTest {

    @Test
    void getShipByMMSI() throws IOException {
        SearchShipController ssc = new SearchShipController();
        ShipStore shipStore = new ShipStore();
        ssc.setShipStore(shipStore);
        Ship ship = shipStore.createShip("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        String mmsi = ship.getMmsi();
        shipStore.saveShipInBST(ship);
        ShipDTO shipDTOResult = ssc.getShipByMMSI(mmsi);
        ShipDTO shipDTOExpected = new ShipDTO("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        Assertions.assertEquals(shipDTOExpected.toString(),shipDTOResult.toString());
    }

    @Test
    void getShipByMMSINull() throws IOException {
        SearchShipController ssc = new SearchShipController();
        ShipStore shipStore = new ShipStore();
        ssc.setShipStore(shipStore);
        String mmsi= "123456789";
        ShipDTO shipDTOResult = ssc.getShipByMMSI(mmsi);
        ShipDTO shipDTOExpected = new ShipDTO(null, null, null, null, null, null, null, null);
        Assertions.assertEquals(shipDTOExpected.toString(),shipDTOResult.toString());
    }

    @Test
    void getShipByIMO() throws IOException {
        SearchShipController ssc = new SearchShipController();
        ShipStore shipStore = new ShipStore();
        ssc.setShipStore(shipStore);
        Ship ship = shipStore.createShip("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        String imo = ship.getImo();
        shipStore.saveShipInBST(ship);
        ShipDTO shipDTOResult = ssc.getShipByIMO(imo);
        ShipDTO shipDTOExpected = new ShipDTO("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        Assertions.assertEquals(shipDTOExpected.toString(),shipDTOResult.toString());
    }

    @Test
    void getShipByIMONull() throws IOException {
        SearchShipController ssc = new SearchShipController();
        ShipStore shipStore = new ShipStore();
        ssc.setShipStore(shipStore);
        String imo= "IMO9395044";
        ShipDTO shipDTOResult = ssc.getShipByIMO(imo);
        ShipDTO shipDTOExpected = new ShipDTO(null, null, null, null, null, null, null, null);
        Assertions.assertEquals(shipDTOExpected.toString(),shipDTOResult.toString());
    }

    @Test
    void getShipByCallSign() throws IOException {
        SearchShipController ssc = new SearchShipController();
        ShipStore shipStore = new ShipStore();
        ssc.setShipStore(shipStore);
        Ship ship = shipStore.createShip("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        String callSign = ship.getCallsign();
        shipStore.saveShipInBST(ship);
        ShipDTO shipDTOResult = ssc.getShipByCallSign(callSign);
        ShipDTO shipDTOExpected = new ShipDTO("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        Assertions.assertEquals(shipDTOExpected.toString(),shipDTOResult.toString());
    }

    @Test
    void getShipByCallSignNull() throws IOException {
        SearchShipController ssc = new SearchShipController();
        ShipStore shipStore = new ShipStore();
        ssc.setShipStore(shipStore);
        String callSign= "C4SQ2";
        ShipDTO shipDTOResult = ssc.getShipByCallSign(callSign);
        ShipDTO shipDTOExpected = new ShipDTO(null, null, null, null, null, null, null, null);
        Assertions.assertEquals(shipDTOExpected.toString(),shipDTOResult.toString());
    }
    
}