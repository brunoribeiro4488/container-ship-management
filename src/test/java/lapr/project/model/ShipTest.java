package lapr.project.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ShipTest {

    @Test
    public void ensureNullIsNotAllowed(){
        try {
        new Ship(null,null,null,null,null,null,null,null);
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void nameLengthValidation(){
        try {
            new Ship("123456789","","IMO9395044","C4SQ2","70","166","25","9.5");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void mmsiLengthValidation(){
        try {
            new Ship("12345678","VALOR","IMO9395044","C4SQ2","70","166","25","9.5");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void mmsiIsNumericValidation(){
        try {
            new Ship("abcdefghijk","VALOR","IMO9395044","C4SQ2","70","166","25","9.5");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void imoLengthValidation(){
        try {
            new Ship("123456789","VALOR","IMO93950","C4SQ2","70","166","25","9.5");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void imoIsNumericValidation(){
        try {
            new Ship("123456789","VALOR","IMO93abc44","C4SQ2","70","166","25","9.5");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void callSignLengthValidation(){
        try {
            new Ship("123456789","VALOR","IMO9395044","","70","166","25","9.5");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void typeLengthValidation(){
        try {
            new Ship("123456789","VALOR","IMO9395044","C4SQ2","","166","25","9.5");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void typeIsNumericValidation(){
        try {
            new Ship("123456789","VALOR","IMO9395044","C4SQ2","ab","166","25","9.5");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void lengthLengthValidation(){
        try {
            new Ship("123456789","VALOR","IMO9395044","C4SQ2","70","","25","9.5");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void lengthIsNumericValidation(){
        try {
            new Ship("123456789","VALOR","IMO9395044","C4SQ2","70","abc","25","9.5");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void widthLengthValidation(){
        try {
            new Ship("123456789","VALOR","IMO9395044","C4SQ2","70","166","","9.5");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void widthIsNumericValidation(){
        try {
            new Ship("123456789","VALOR","IMO9395044","C4SQ2","70","166","ab","9.5");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void draftLengthValidation(){
        try {
            new Ship("123456789","VALOR","IMO9395044","C4SQ2","70","166","25","");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void draftIsNumericValidation(){
        try {
            new Ship("123456789","VALOR","IMO9395044","C4SQ2","70","166","25","ab");
            Assertions.fail("There should have been an exception");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void equals(){
        Ship ship1 = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        Ship ship2 = new Ship("210950000","VARA","IMO9395022","C4SQ2","708","16","256","9.52");
        Assertions.assertEquals(ship1, ship2);
    }

    @Test
    public void equalsNullOtherClass(){
        Ship ship =new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        String str = null;
        boolean expected = false;
        boolean result = ship.equals(str);
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void equalsNullSameClass(){
        Ship ship =new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        Ship ship1 = null;
        boolean expected = false;
        boolean result = ship.equals(ship1);
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void equalsOtherClass(){
        Ship ship =new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        String str = "ola";
        boolean expected = false;
        boolean result = ship.equals(str);
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void equalsSameClass(){
        Ship ship =new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        Ship ship1 = new Ship("210950001","VARAMO","IMO9395094","C5SQ2","70","166","25","9.5");;
        boolean expected = false;
        boolean result = ship.equals(ship1);
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void equalsSameMMSIIMOCallSign(){
        Ship ship =new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        Ship ship1 =new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        boolean expected = true;
        boolean result = ship.equals(ship1);
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void equalsSameIMOCallSign(){
        Ship ship =new Ship("210950001","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        Ship ship1 =new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        boolean expected = true;
        boolean result = ship.equals(ship1);
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void equalsSameCallSign(){
        Ship ship =new Ship("210950004","VARAMO","IMO9399044","C4SQ2","70","166","25","9.5");
        Ship ship1 =new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        boolean expected = true;
        boolean result = ship.equals(ship1);
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void equalsDifMMSIIMOCallSign(){
        Ship ship =new Ship("210950070","VARAMO","IMO9355044","C7SQ2","70","166","25","9.5");
        Ship ship1 =new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        boolean expected = false;
        boolean result = ship.equals(ship1);
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void compareToSameIMO(){
        Ship ship =new Ship("210950000","VARAMO","IMO9355044","C7SQ2","70","166","25","9.5");
        int expected = 0;
        int result = ship.compareToIMO("IMO9355044");
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void compareToOtherBiggerIMO(){
        Ship ship =new Ship("210950000","VARAMO","IMO9355044","C7SQ2","70","166","25","9.5");
        int expected = 1;
        int result = ship.compareToIMO("IMO9355045");
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void compareToOtherSmallerIMO(){
        Ship ship =new Ship("210950000","VARAMO","IMO9355044","C7SQ2","70","166","25","9.5");
        int expected = -1;
        int result = ship.compareToIMO("IMO9355043");
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void compareToSameCallSign(){
        Ship ship =new Ship("210950000","VARAMO","IMO9355044","C7SQ2","70","166","25","9.5");
        int expected = 0;
        int result = ship.compareToCallSign("C7SQ2");
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void compareToOtherBiggerCallSign(){
        Ship ship =new Ship("210950000","VARAMO","IMO9355044","C7SQ2","70","166","25","9.5");
        int expected = 1;
        int result = ship.compareToCallSign("C7SQ3");
        Assertions.assertEquals(expected,result);
    }

    @Test
    public void compareToOtherSmallerCallSign(){
        Ship ship =new Ship("210950000","VARAMO","IMO9355044","C7SQ2","70","166","25","9.5");
        int expected = -1;
        int result = ship.compareToCallSign("C7SQ1");
        Assertions.assertEquals(expected,result);
    }


    @Test
    void compareTo() {
        Ship ship1 =new Ship("210950000","VARAMO","IMO9355044","C7SQ2","70","166","25","9.5");
        Ship ship2 =new Ship("210950001","VARAMO","IMO9355044","C7SQ2","70","166","25","9.5");
        Ship ship3 =new Ship("210950000","VARAMO","IMO9355044","C7SQ2","70","166","25","9.5");
        int expected = 1;
        Assertions.assertEquals(expected,ship1.compareTo(ship2));

        int expected2 = 0;
        Assertions.assertEquals(expected2,ship1.compareTo(ship3));

        int expected3 = -1;
        Assertions.assertEquals(expected3,ship2.compareTo(ship1));
    }

    @Test
    void hashCodeTest(){
        Ship ship1 =new Ship("210950000","VARAMO","IMO9355044","C7SQ2","70","166","25","9.5");
        int expected = -911644095;
        int result = ship1.hashCode();
        Assertions.assertEquals(expected,result);
    }
}