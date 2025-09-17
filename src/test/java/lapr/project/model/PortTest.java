package lapr.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class PortTest {

    @Test
    public void equals(){
        Port port1 = new Port("29002","Liverpool","Europe","United Kingdom","5","-3.033333333");
        Port port2 = new Port("29002","Liverol","Eupe","United gdom","6","-3.033333333");
        Assertions.assertEquals(port1,port2);
    }

    @Test
    public void notequals(){
        Port port1 = new Port("29002","Liverpool","Europe","United Kingdom","5","-3.033333333");
        Port port2 = new Port("29012","Liverol","Eupe","United gdom","6","-3.033333333");
        Assertions.assertNotEquals(port1,port2);
    }

    @Test
    public void equalsNullDifClass(){
        Port port1 = new Port("29002","Liverpool","Europe","United Kingdom","5","-3.033333333");
        Ship ship = null;
        Assertions.assertNotEquals(port1,ship);
    }

    @Test
    public void equalsNotNullDifClass(){
        Port port1 = new Port("29002","Liverpool","Europe","United Kingdom","5","-3.033333333");
        Ship ship = new Ship("123456789","name","1234567","wert5","145","14","23","15");
        Assertions.assertNotEquals(port1,ship);
    }

    @Test
    public void equalsNull(){
        Port port1 = new Port("29002","Liverpool","Europe","United Kingdom","5","-3.033333333");
        Port port = null;
        Assertions.assertNotEquals(port1,port);
    }

    @Test
    public void latIsEmptyValidation(){
        try {
            new Port("29002","Liverpool","Europe","United Kingdom","","-3.033333333");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void latIsNumericValidation(){
        try {
            new Port("29002","Liverpool","Europe","United Kingdom","a53.46666667","-3.033333333");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void latIsValidValidation(){
        try {
            new Port("29002","Liverpool","Europe","United Kingdom","100","-3.033333333");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void latIsValidValidation2(){
        try {
            new Port("29002","Liverpool","Europe","United Kingdom","-100","-3.033333333");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void lonIsEmptyValidation(){
        try {
            new Port("29002","Liverpool","Europe","United Kingdom","53.46666667","");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void lonIsNumericValidation(){
        try {
            new Port("29002","Liverpool","Europe","United Kingdom","53.46666667","-3a.033333333");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void lonIsValidValidation(){
        try {
            new Port("29002","Liverpool","Europe","United Kingdom","53.46666667","300");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void lonIsValidValidation2(){
        try {
            new Port("29002","Liverpool","Europe","United Kingdom","53.46666667","-300");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void idIsEmptyValidation(){
        try {
            new Port("","Liverpool","Europe","United Kingdom","53.46666667","5");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void nameIsEmptyValidation(){
        try {
            new Port("29002","","Europe","United Kingdom","53.46666667","5");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void continentIsEmptyValidation(){
        try {
            new Port("29002","Liverpool","","United Kingdom","53.46666667","5");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void countryIsEmptyValidation(){
        try {
            new Port("29002","Liverpool","Europe","","53.46666667","5");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void toStringTest(){
        Port port=new Port("29002","Liverpool","Europe","United Kingdom","5","-3.033333333");
        String result = "Port with id: 29002";
        assertEquals(port.toString(),result);
    }

    @Test
    public void hashCodeTest(){
        Port port=new Port("29002","Liverpool","Europe","United Kingdom","5","-3.033333333");
        int expected = port.hashCode();
        int result=47921834;
        Assertions.assertEquals(expected,result);
    }

}