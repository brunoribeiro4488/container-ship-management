package lapr.project.utils.stores;

import lapr.project.utils.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NetworkStoreTest {

    NetworkStore instance = new NetworkStore();

    @Test
    void getNClosestePlacesEmpty() throws SQLException {
        List<Pair<String, Pair<Object, Double>>> expected = instance.getNClosestPlaces(0);
        List<Pair<String,Pair<Object, Double>>> result = Collections.emptyList();
        Assertions.assertEquals(expected, result);
    }

    @Test
    void getDistanceBetweenTwoPointsRight(){
        double expected = instance.getDistanceBetweenTwoPoints("14","15","20","25");
        double result = 1254.704874184855;
        Assertions.assertEquals(expected, result);
    }

    @Test
    void shortestpathbetweentwolocaldirect() throws SQLException {
        instance.createGraph(3);
        String local1="Nicosia";
        String local2="10136";
        ArrayList<String> locais= new ArrayList<>();
        String result=instance.shortestPathBetweenTwoLocals4(local1,local2,locais);
        System.out.println(result);
        String expected="The shortest path between "+local1+" and "+local2+" is 37.922515793079384 and its path is \n[Nicosia, 10136]";

        assertEquals(result,expected);

        String local51="Nicosia";
        String local52="Ankara";
        ArrayList<String> locais2= new ArrayList<>();
        locais2.add("Athens");
        locais2.add("Valetta");
        locais2.add("10136");

        String result5=instance.shortestPathBetweenTwoLocals4(local51,local52,locais2);
        String expected5="The shortest path between Nicosia and Ankara is 987.5582352053079 and its path is \n" +
                "Nicosia,Valetta,Athens,10136,Ankara";
        assertEquals(result5,expected5);

        String local41="Nicosia";
        String local42="32133";
        ArrayList<String> locaiis= new ArrayList<>();
        String result4=instance.shortestPathBetweenTwoLocals4(local41,local42,locaiis);
        String expected4="There is no connection between "+local41 +" and "+local42+".";

        assertEquals(result4,expected4);

        String result1=instance.shortestPathBetweenTwoLocals("Mexico City","18682",1);
        String expected1="The shortest path between Mexico City and 18682 by land is 15737.782219830551 and its path is \n" +
                "[Mexico City, 22522, 246265, 21556, 24951, 21852, 18682]";
        assertEquals(result1,expected1);

        String result1n=instance.shortestPathBetweenTwoLocals("Mexico City","18787",1);
        String expected1n="There is no connection by land between Mexico City and 18787.";
        assertEquals(result1n,expected1n);


        //2
        String result2=instance.shortestPathBetweenTwoLocals("14635","19473",2);
        String expected2="The shortest path between 14635 and 19473 by sea is 8169.0 and its path is \n" +
                "[14635, 18476, 13012, 19057, 19473]";
        assertEquals(result2,expected2);

        String result2n=instance.shortestPathBetweenTwoLocals("Mexico City","18787",2);
        String expected2n="There is no connection by sea between Mexico City and 18787.";
        assertEquals(result2n,expected2n);

        //3
        String result3=instance.shortestPathBetweenTwoLocals("Mexico City","18682",3);
        String expected3="The shortest path between Mexico City and 18682 is 15737.782219830551 and its path is \n" +
                "[Mexico City, 22522, 246265, 21556, 24951, 21852, 18682]";
        assertEquals(result3,expected3);

        String result3n=instance.shortestPathBetweenTwoLocals("Mexico City","18787",3);
        String expected3n="There is no connection between Mexico City and 18787.";
        assertEquals(result3n,expected3n);
    }

    @Test
    void isNumeric() {
        String numeric = "12345";
        String notNumeric = "123esad3";
        Assertions.assertTrue(instance.isNumeric(numeric));
        Assertions.assertFalse(instance.isNumeric(notNumeric));
        Assertions.assertNotEquals(true, notNumeric);
        Assertions.assertNotEquals(false, numeric);
    }

    @Test
    void getMaxValue() {
        int[] list = {5,8,1,6,5,4};
        int[] list2 = {1,6,9,9,7,4};
        Assertions.assertEquals(1, instance.getMaxValue(list));
        Assertions.assertEquals(2, instance.getMaxValue(list2));
        Assertions.assertNotEquals(0, instance.getMaxValue(list));
        Assertions.assertNotEquals(3, instance.getMaxValue(list2));
    }

//    @Test
//    void getCriticalPort() throws SQLException {
//        NetworkStore ns = new NetworkStore();
//        String notExpected = String.format("1º Port -> 18421\n2º Port -> 48621\n");
//        Assertions.assertNotEquals(notExpected, ns.getCriticalPort(2));
//        Assertions.assertEquals("", ns.getCriticalPort(2));
//        ns.createGraph(5);
//        String expected = String.format("1º Port -> 18137\n2º Port -> 22522\n3º Port -> 18682\n");
//        Assertions.assertEquals(expected, ns.getCriticalPort(3));
//        Assertions.assertNotEquals(notExpected, ns.getCriticalPort(2));
//        ns.createGraph(3);
//        String expected2 = String.format("1º Port -> 14459\n2º Port -> 22522\n3º Port -> 18137\n");
//        String notExpected2 = String.format("1º Port -> 18421\n2º Port -> 48621\n");
//
//
//    }
}