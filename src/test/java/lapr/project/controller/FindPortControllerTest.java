package lapr.project.controller;

import lapr.project.model.Port;
import lapr.project.model.Ship;
import lapr.project.model.ShipPosition;
import lapr.project.utils.dto.PortDTO;
import lapr.project.utils.stores.PortStore;
import lapr.project.utils.stores.ShipStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class FindPortControllerTest {

    @Test
    void getClosestPort() throws ParseException, IOException, SQLException {
        FindPortController fpc = new FindPortController();
        ShipStore shipStore = new ShipStore();
        PortStore portStore = new PortStore();
        fpc.setShipStore(shipStore);
        fpc.setPortStore(portStore);
        Ship ship = shipStore.createShip("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        shipStore.saveShipInBST(ship);
        shipStore.addShipPosition("210950000","31/12/2020 17:03","20.92236","-20.97243","12.5","2.4","358","NA","B");
        shipStore.addShipPosition("210950000","31/12/2020 17:04","42.92236","-66.97243","12.5","2.4","358","NA","B");
        Port port = portStore.createPort("29002","Liverpool","Europe","United Kingdom","20","-20");
        Port port1 = portStore.createPort("29001","Manchester","Europe","United Kingdom","25","-20");
        portStore.savePortinKdt(port);
        portStore.savePortinKdt(port1);
        PortDTO result = fpc.getClosestPort("C4SQ2","31/12/2020 17:03");
        PortDTO expected = new PortDTO("29002","Liverpool","Europe","United Kingdom","20","-20");
        Assertions.assertEquals(expected.toString(),result.toString());
    }
}