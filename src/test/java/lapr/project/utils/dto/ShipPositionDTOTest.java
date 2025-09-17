package lapr.project.utils.dto;

import lapr.project.model.Ship;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class ShipPositionDTOTest {

    @Test
    void getMMSI() throws ParseException {
        ShipPositionDTO shipPositionDTO = new ShipPositionDTO("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        String mmsiResult= shipPositionDTO.getMMSI();
        String mmsiExpected= "210950000";
        Assertions.assertEquals(mmsiExpected,mmsiResult);
    }

    @Test
    void getDate() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        ShipPositionDTO shipPositionDTO = new ShipPositionDTO("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        Date dateResult= shipPositionDTO.getDate();
        Date dateExpected= format.parse("31/12/2020 17:03");
        Assertions.assertEquals(dateExpected,dateResult);
    }

    @Test
    void getLAT() throws ParseException {
        ShipPositionDTO shipPositionDTO = new ShipPositionDTO("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        String latResult= shipPositionDTO.getLAT();
        String latExpected= "42.92236";
        Assertions.assertEquals(latExpected,latResult);
    }

    @Test
    void getLON() throws ParseException {
        ShipPositionDTO shipPositionDTO = new ShipPositionDTO("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        String lonResult= shipPositionDTO.getLON();
        String lonExpected= "-66.97243";
        Assertions.assertEquals(lonExpected,lonResult);
    }

    @Test
    void getSOG() throws ParseException {
        ShipPositionDTO shipPositionDTO = new ShipPositionDTO("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        String sogResult= shipPositionDTO.getSOG();
        String sogExpected= "12.5";
        Assertions.assertEquals(sogExpected,sogResult);
    }

    @Test
    void getCOG() throws ParseException {
        ShipPositionDTO shipPositionDTO = new ShipPositionDTO("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        String cogResult= shipPositionDTO.getCOG();
        String cogExpected= "2.4";
        Assertions.assertEquals(cogExpected,cogResult);
    }

    @Test
    void getHeading() throws ParseException {
        ShipPositionDTO shipPositionDTO = new ShipPositionDTO("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        String headingResult= shipPositionDTO.getHeading();
        String headingExpected= "358";
        Assertions.assertEquals(headingExpected,headingResult);
    }

    @Test
    void getCargo() throws ParseException {
        ShipPositionDTO shipPositionDTO = new ShipPositionDTO("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        String cargoResult= shipPositionDTO.getCargo();
        String cargoExpected= "NA";
        Assertions.assertEquals(cargoExpected,cargoResult);
    }

    @Test
    void getTransciever() throws ParseException {
        ShipPositionDTO shipPositionDTO = new ShipPositionDTO("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        String transcieverResult= shipPositionDTO.getTransciever();
        String transcieverExpected= "B";
        Assertions.assertEquals(transcieverExpected,transcieverResult);
    }

    @Test
    void equalsSameObject() throws ParseException {
        ShipPositionDTO shipPositionDTO = new ShipPositionDTO("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        boolean expected = true;
        boolean result = shipPositionDTO.equals(shipPositionDTO);
        Assertions.assertEquals(expected,result);
    }

    @Test
    void equalsNullSameClass() throws ParseException {
        ShipPositionDTO shipPositionDTO = new ShipPositionDTO("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        ShipPositionDTO shipPositionDTO1 = null;
        boolean expected = false;
        boolean result = shipPositionDTO.equals(shipPositionDTO1);
        Assertions.assertEquals(expected,result);
    }

    @Test
    void equalsNullOtherClass() throws ParseException {
        ShipPositionDTO shipPositionDTO = new ShipPositionDTO("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        Ship ship = null;
        boolean expected = false;
        boolean result = shipPositionDTO.equals(ship);
        Assertions.assertEquals(expected,result);
    }

    @Test
    void equalsOtherClass() throws ParseException {
        ShipPositionDTO shipPositionDTO = new ShipPositionDTO("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        boolean expected= false;
        boolean result = shipPositionDTO.equals(ship);
        Assertions.assertEquals(expected,result);
    }

    @Test
    void equalsSameMMSIDate() throws ParseException {
        ShipPositionDTO shipPositionDTO = new ShipPositionDTO("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        ShipPositionDTO shipPositionDTO1 = new ShipPositionDTO("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        boolean expected = true;
        boolean result = shipPositionDTO.equals(shipPositionDTO1);
        Assertions.assertEquals(expected,result);
    }

    @Test
    void equalsSameMMSIDifDate() throws ParseException {
        ShipPositionDTO shipPositionDTO = new ShipPositionDTO("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        ShipPositionDTO shipPositionDTO1 = new ShipPositionDTO("210950000","30/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        boolean expected = false;
        boolean result = shipPositionDTO.equals(shipPositionDTO1);
        Assertions.assertEquals(expected,result);
    }

    @Test
    void equalsDifMMSISameDate() throws ParseException {
        ShipPositionDTO shipPositionDTO = new ShipPositionDTO("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        ShipPositionDTO shipPositionDTO1 = new ShipPositionDTO("210950001","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        boolean expected = false;
        boolean result = shipPositionDTO.equals(shipPositionDTO1);
        Assertions.assertEquals(expected,result);
    }

    @Test
    void equalsDifMMSIDate() throws ParseException {
        ShipPositionDTO shipPositionDTO = new ShipPositionDTO("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        ShipPositionDTO shipPositionDTO1 = new ShipPositionDTO("210950001","30/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        boolean expected = false;
        boolean result = shipPositionDTO.equals(shipPositionDTO1);
        Assertions.assertEquals(expected,result);
    }
    
}