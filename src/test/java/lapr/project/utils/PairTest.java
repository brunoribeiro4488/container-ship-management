package lapr.project.utils;

import lapr.project.model.Ship;
import lapr.project.utils.dto.ShipPositionDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;


class PairTest {

    @Test
    void pairTest(){
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        Ship ship1 = new Ship("210950001","VARAN","IMO9395043","C4SQ1","70","166","25","9.5");
        Pair s = new Pair<>(ship1, ship);
        Assertions.assertEquals(s.getFirst(), ship1);
        Assertions.assertEquals(s.getSecond(), ship);
    }


    @Test
    void testToString() {
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        Ship ship1 = new Ship("210950001","VARAN","IMO9395043","C4SQ1","70","166","25","9.5");
        Pair<Object, Object> s = new Pair<>(ship1, ship);
        String string1 =  s.toString();
        String string2 = "Pair: \n"+ship1+ship+"\n";
        Assertions.assertEquals(string1, string2);
    }

    @Test
    void equalsSameFirstSecond() {
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        Ship ship1 = new Ship("210950001","VARAN","IMO9395043","C4SQ1","70","166","25","9.5");
        Ship ship2 = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        Ship ship3 = new Ship("210950001","VARAN","IMO9395043","C4SQ1","70","166","25","9.5");
        Pair<Object, Object> s = new Pair<>(ship1, ship);
        Pair<Object, Object> s1 = new Pair<>(ship3, ship2);
        boolean expected = true;
        boolean result = s.equals(s1);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void equalsSameFirstDifSecond(){
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        Ship ship1 = new Ship("210950001","VARAN","IMO9395043","C4SQ1","70","166","25","9.5");
        Ship ship2 = new Ship("210950003","VARAMO","IMO9395144","C4SQ3","70","166","25","9.5");
        Ship ship3 = new Ship("210950001","VARAN","IMO9395043","C4SQ1","70","166","25","9.5");
        Pair<Object, Object> s = new Pair<>(ship1, ship);
        Pair<Object, Object> s1 = new Pair<>(ship3, ship2);
        boolean expected = false;
        boolean result = s.equals(s1);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void equalsDifFirstSameSecond(){
        Ship ship1 = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        Ship ship = new Ship("210950001","VARAN","IMO9395043","C4SQ1","70","166","25","9.5");
        Ship ship3 = new Ship("210950003","VARAMO","IMO9395144","C4SQ3","70","166","25","9.5");
        Ship ship2 = new Ship("210950001","VARAN","IMO9395043","C4SQ1","70","166","25","9.5");
        Pair<Object, Object> s = new Pair<>(ship1, ship);
        Pair<Object, Object> s1 = new Pair<>(ship3, ship2);
        boolean expected = false;
        boolean result = s.equals(s1);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void equalsDifFirstSecond(){
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        Ship ship1 = new Ship("210950002","VARAN","IMO9395046","C4SQ6","70","166","25","9.5");
        Ship ship2 = new Ship("210950003","VARAMO","IMO9395144","C4SQ3","70","166","25","9.5");
        Ship ship3 = new Ship("210950001","VARAN","IMO9395043","C4SQ1","70","166","25","9.5");
        Pair<Object, Object> s = new Pair<>(ship1, ship);
        Pair<Object, Object> s1 = new Pair<>(ship3, ship2);
        boolean expected = false;
        boolean result = s.equals(s1);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void equalsSameObject(){
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        Pair<Object, Object> s = new Pair<>(ship, ship);
        boolean expected=true;
        boolean result = s.equals(s);
        Assertions.assertEquals(expected,result);
    }

    @Test
    void equalsNullSameClass(){
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        Pair<Object, Object> s = new Pair<>(ship, ship);
        Pair<Object, Object> s1 = null;
        boolean expected = false;
        boolean result = s.equals(s1);
        Assertions.assertEquals(expected,result);
    }

    @Test
    void equalsNullOtherClass(){
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        Pair<Object, Object> s = new Pair<>(ship, ship);
        Ship ship1= null;
        boolean expected = false;
        boolean result = s.equals(ship1);
        Assertions.assertEquals(expected,result);
    }

    @Test
    void equalsOtherClass() {
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        Pair<Object, Object> s = new Pair<>(ship, ship);
        boolean expected = false;
        boolean result = s.equals(ship);
        Assertions.assertEquals(expected,result);
    }

}