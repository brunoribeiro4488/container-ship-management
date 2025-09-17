package lapr.project.model;

import lapr.project.utils.dto.ShipPositionDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

class ShipPositionTest {

    @Test
    public void latIsEmptyValidation(){
        try {
            new ShipPosition("210950000","31/12/2020 17:03","","-66.97243","12.5","2.4","358","NA","B");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException | ParseException ignored) {
        }
    }

    @Test
    public void latIsNumericValidation(){
        try {
            new ShipPosition("210950000","31/12/2020 17:03","abcd","-66.97243","12.5","2.4","358","NA","B");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException | ParseException ignored) {
        }
    }

    @Test
    public void latIsValidValidation(){
        try {
            new ShipPosition("210950000","31/12/2020 17:03","5542.92236","-66.97243","12.5","2.4","358","NA","B");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException | ParseException ignored) {
        }
    }

    @Test
    public void latIsValidValidation2(){
        try {
            new ShipPosition("210950000","31/12/2020 17:03","-5542.92236","-66.97243","12.5","2.4","358","NA","B");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException | ParseException ignored) {
        }
    }

    @Test
    public void lonIsEmptyValidation(){
        try {
            new ShipPosition("210950000","31/12/2020 17:03","42.92236","","12.5","2.4","358","NA","B");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException | ParseException ignored) {
        }
    }

    @Test
    public void lonIsNumericValidation(){
        try {
            new ShipPosition("210950000","31/12/2020 17:03","42.92236","abcd","12.5","2.4","358","NA","B");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException | ParseException ignored) {
        }
    }

    @Test
    public void lonIsValidValidation(){
        try {
            new ShipPosition("210950000","31/12/2020 17:03","42.92236","-666.97243","12.5","2.4","358","NA","B");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException | ParseException ignored) {
        }
    }

    @Test
    public void lonIsValidValidation2(){
        try {
            new ShipPosition("210950000","31/12/2020 17:03","42.92236","666.97243","12.5","2.4","358","NA","B");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException | ParseException ignored) {
        }
    }

    @Test
    public void sogIsEmptyValidation(){
        try {
            new ShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","","2.4","358","NA","B");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException | ParseException ignored) {
        }
    }

    @Test
    public void sogIsNumericValidation(){
        try {
            new ShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","abc","2.4","358","NA","B");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException | ParseException ignored) {
        }
    }

    @Test
    public void cogIsEmptyValidation(){
        try {
            new ShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","","358","NA","B");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException | ParseException ignored) {
        }
    }

    @Test
    public void cogIsNumericValidation(){
        try {
            new ShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","abc","358","NA","B");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException | ParseException ignored) {
        }
    }

    @Test
    public void cogIsValidValidation(){
        try {
            new ShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","500","358","NA","B");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException | ParseException ignored) {
        }
    }

    @Test
    public void headingIsEmptyValidation(){
        try {
            new ShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","","NA","B");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException | ParseException ignored) {
        }
    }

    @Test
    public void headingIsNumericValidation(){
        try {
            new ShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","abc","NA","B");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException | ParseException ignored) {
        }
    }

    @Test
    public void headingIsValidValidation(){
        try {
            new ShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","512","NA","B");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException | ParseException ignored) {
        }
    }

    @Test
    public void headingIsValidValidation2(){
        try {
            new ShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","-23","NA","B");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException | ParseException ignored) {
        }
    }

    @Test
    public void headingIsValidValidation3(){
        try {
            new ShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","397","NA","B");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException | ParseException ignored) {
        }
    }

    @Test
    public void cargoIsEmptyValidation(){
        try {
            new ShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","","B");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException | ParseException ignored) {
        }
    }

    @Test
    public void transcieverIsEmptyValidation(){
        try {
            new ShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException | ParseException ignored) {
        }
    }

    @Test
    void equalsSameObject() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        boolean expected = true;
        boolean result = shipPosition.equals(shipPosition);
        Assertions.assertEquals(expected,result);
    }

    @Test
    void equalsNullSameClass() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        ShipPosition shipPosition1 = null;
        boolean expected = false;
        boolean result = shipPosition.equals(shipPosition1);
        Assertions.assertEquals(expected,result);
    }

    @Test
    void equalsNullOtherClass() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        Ship ship = null;
        boolean expected = false;
        boolean result = shipPosition.equals(ship);
        Assertions.assertEquals(expected,result);
    }

    @Test
    void equalsOtherClass() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        boolean expected= false;
        boolean result = shipPosition.equals(ship);
        Assertions.assertEquals(expected,result);
    }

    @Test
    void equalsSameMMSIDate() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        ShipPosition shipPosition1 = new ShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        boolean expected = true;
        boolean result = shipPosition.equals(shipPosition1);
        Assertions.assertEquals(expected,result);
    }

    @Test
    void equalsSameMMSIDifDate() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        ShipPosition shipPosition1 = new ShipPosition("210950000","30/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        boolean expected = false;
        boolean result = shipPosition.equals(shipPosition1);
        Assertions.assertEquals(expected,result);
    }

    @Test
    void equalsDifMMSISameDate() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        ShipPosition shipPosition1 = new ShipPosition("210950001","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        boolean expected = false;
        boolean result = shipPosition.equals(shipPosition1);
        Assertions.assertEquals(expected,result);
    }

    @Test
    void equalsDifMMSIDate() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        ShipPosition shipPosition1 = new ShipPosition("210950001","30/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        boolean expected = false;
        boolean result = shipPosition.equals(shipPosition1);
        Assertions.assertEquals(expected,result);
    }
    
    @Test
    void hashCodeTest() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        int expected = shipPosition.hashCode();
        int result = 867793861 ;
        Assertions.assertEquals(expected,result);
    }

    @Test
    void toStringTest() throws ParseException {
        ShipPosition shipPosition = new ShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        String expected = "ShipPosition{mmsi='210950000', date=31/12/2020 17:03}";
        String result = shipPosition.toString();
        Assertions.assertEquals(expected,result);
    }
}