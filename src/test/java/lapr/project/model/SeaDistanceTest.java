package lapr.project.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SeaDistanceTest {

    @Test
    void getFrom_port_id() {
        SeaDistance sd = new SeaDistance("Denmark","10358","Aarhus","Turkey","246265","Ambarli","3673");
        String result= "10358";
        assertEquals(sd.getFromPortID(),result);
    }

    @Test
    void getTo_port_id() {
        SeaDistance sd = new SeaDistance("Denmark","10358","Aarhus","Turkey","246265","Ambarli","3673");
        String result= "246265";
        assertEquals(sd.getToPortID(),result);
    }

    @Test
    void getFrom_country() {
        SeaDistance sd = new SeaDistance("Denmark","10358","Aarhus","Turkey","246265","Ambarli","3673");
        String result= "Denmark";
        assertEquals(sd.getFromCountry(),result);
    }

    @Test
    void getFrom_port() {
        SeaDistance sd = new SeaDistance("Denmark","10358","Aarhus","Turkey","246265","Ambarli","3673");
        String result= "Aarhus";
        assertEquals(sd.getFromPort(),result);
    }

    @Test
    void getSea_distance() {
        SeaDistance sd = new SeaDistance("Denmark","10358","Aarhus","Turkey","246265","Ambarli","3673");
        String result= "3673";
        assertEquals(sd.getSeaDistance(),result);
    }

    @Test
    void getTo_country() {
        SeaDistance sd = new SeaDistance("Denmark","10358","Aarhus","Turkey","246265","Ambarli","3673");
        String result= "Turkey";
        assertEquals(sd.getToCountry(),result);
    }

    @Test
    void getTo_port() {
        SeaDistance sd = new SeaDistance("Denmark","10358","Aarhus","Turkey","246265","Ambarli","3673");
        String result= "Ambarli";
        assertEquals(sd.getToPort(),result);
    }

    @Test
    void compareTo() {
        SeaDistance sd1 = new SeaDistance("Denmark","10358","Aarhus","Turkey","246265","Ambarli","3673");
        SeaDistance sd2 = new SeaDistance("Algeria","13523","Algiers","Russia","11771","Kaliningrad","2625");
        int i=-1;
        assertEquals(sd1.compareTo(sd2),i);


        SeaDistance sd3 = new SeaDistance("Denmark","10358","Aarhus","Turkey","246265","Ambarli","3673");
        SeaDistance sd4 = new SeaDistance("Algeria","13523","Algiers","Russia","11771","Kaliningrad","3673");
        int expected = 0;
        assertEquals(sd3.compareTo(sd4),expected);

        int expected2 = 1;
        assertEquals(sd2.compareTo(sd1),expected2);


    }
}