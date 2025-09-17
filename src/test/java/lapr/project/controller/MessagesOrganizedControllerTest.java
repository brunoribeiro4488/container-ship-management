package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.utils.dto.ShipPositionDTO;
import lapr.project.model.Ship;
import lapr.project.utils.stores.ShipStore;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessagesOrganizedControllerTest {

    @Test
    void getMessagesOrganizedWithinADate() throws ParseException {
        Company company = new Company();
        ShipStore store = company.getshipStore();
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        store.saveShipInBST(ship);
        Ship ship2 = new Ship("228339600", "ALMAVIVA","IMO9450648","FLSU","70","334","42","15");
        store.saveShipInBST(ship2);
        store.addShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        store.addShipPosition("210950000","31/12/2020 16:20","42.7698","-66.9759","13.3","3.7","356","NA","B");
        store.addShipPosition("210950000","31/12/2020 16:32","42.81133","-66.97587","13.4","10","356","NA","B");
        store.addShipPosition("228339600","31/12/2020 01:22","28.19854","-88.65058","11.7","130.3","131","79","B");
        store.addShipPosition("228339600","31/12/2020 03:47","27.89788","-88.25001","11.7","128.4","129","79","B");
        store.addShipPosition("228339600","30/12/2020 00:15","28.34412","-88.84108","12","128.3","129","79","B");

        MessagesOrganizedController controller = new MessagesOrganizedController(company);
        try {
            List<lapr.project.utils.dto.ShipPositionDTO> expected = new ArrayList<>();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date date = format.parse("31/12/2020");
            List<lapr.project.utils.dto.ShipPositionDTO> result = controller.getMessagesOrganized(date);
            ShipPositionDTO sp1 = new ShipPositionDTO("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
            ShipPositionDTO sp2 = new ShipPositionDTO("210950000","31/12/2020 16:20","42.7698","-66.9759","13.3","3.7","356","NA","B");
            ShipPositionDTO sp3 = new ShipPositionDTO("210950000","31/12/2020 16:32","42.81133","-66.97587","13.4","10","356","NA","B");
            ShipPositionDTO sp4 = new ShipPositionDTO("228339600","31/12/2020 01:22","28.19854","-88.65058","11.7","130.3","131","79","B");
            ShipPositionDTO sp5 = new ShipPositionDTO("228339600","31/12/2020 03:47","27.89788","-88.25001","11.7","128.4","129","79","B");
            expected.add(sp4);
            expected.add(sp5);
            expected.add(sp2);
            expected.add(sp3);
            expected.add(sp1);
            assertEquals(expected,result);

        } catch (ParseException e) {
        }
    }


    @Test
    void getMessagesOrganizedWithinAInterval() throws ParseException {
        Company company = new Company();
        ShipStore store = company.getshipStore();
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        store.saveShipInBST(ship);
        Ship ship2 = new Ship("228339600", "ALMAVIVA","IMO9450648","FLSU","70","334","42","15");
        store.saveShipInBST(ship2);
        store.addShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        store.addShipPosition("210950000","31/12/2020 16:20","42.7698","-66.9759","13.3","3.7","356","NA","B");
        store.addShipPosition("210950000","31/12/2020 16:32","42.81133","-66.97587","13.4","10","356","NA","B");
        store.addShipPosition("228339600","31/12/2020 01:22","28.19854","-88.65058","11.7","130.3","131","79","B");
        store.addShipPosition("228339600","31/12/2020 03:47","27.89788","-88.25001","11.7","128.4","129","79","B");
        store.addShipPosition("228339600","30/12/2020 00:15","28.34412","-88.84108","12","128.3","129","79","B");

        MessagesOrganizedController controller = new MessagesOrganizedController(company);
        try {
            List<lapr.project.utils.dto.ShipPositionDTO> expected = new ArrayList<>();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date date = format.parse("31/12/2020");
            Date final_date = format.parse("01/01/2021");
            List<lapr.project.utils.dto.ShipPositionDTO> result = controller.getMessagesOrganized(date,final_date);
            ShipPositionDTO sp1 = new ShipPositionDTO("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
            ShipPositionDTO sp2 = new ShipPositionDTO("210950000","31/12/2020 16:20","42.7698","-66.9759","13.3","3.7","356","NA","B");
            ShipPositionDTO sp3 = new ShipPositionDTO("210950000","31/12/2020 16:32","42.81133","-66.97587","13.4","10","356","NA","B");
            ShipPositionDTO sp4 = new ShipPositionDTO("228339600","31/12/2020 01:22","28.19854","-88.65058","11.7","130.3","131","79","B");
            ShipPositionDTO sp5 = new ShipPositionDTO("228339600","31/12/2020 03:47","27.89788","-88.25001","11.7","128.4","129","79","B");
            expected.add(sp4);
            expected.add(sp5);
            expected.add(sp2);
            expected.add(sp3);
            expected.add(sp1);
            assertEquals(expected,result);

        } catch (ParseException e) {
        }
    }
}