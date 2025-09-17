package lapr.project.controller;

import lapr.project.model.Ship;
import lapr.project.utils.stores.ShipStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VesselSinkControllerTest {

    @Test
    void getVesselSink() throws IOException {
        VesselSinkController vesselSinkController=new VesselSinkController();
        Ship ship = new Ship("123456789","shipo","IMO4545678","DR565","12345","90","40","10");
        ShipStore shipStore = new ShipStore();
        shipStore.saveShipInBST(ship);
        vesselSinkController.setShipStore(shipStore);
        List<Double> aux = vesselSinkController.getVesselSink("123456789", 3000);
        String result = String.format("Total mass placed on the vessel: %.2fkg %nDifference in height that the vessel has suffered: %.2fm %nPressure exerted: %.2fPa", aux.get(0),aux.get(1),aux.get(2));
        String expected = "Total mass placed on the vessel: 1500000.00kg \nDifference in height that the vessel has suffered: 0.40m \nPressure exerted: 4083.33Pa";
        Assertions.assertEquals(expected,result);
    }
}