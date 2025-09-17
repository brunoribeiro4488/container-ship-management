package lapr.project.controller;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ShortestPathBetween2PlacesControllerTest {

    @Test
    void shortestPathBetween2Places() throws SQLException, IOException {
        FreightNetworkController fnc = new FreightNetworkController();
        fnc.freightNetwork(0);
        ShortestPathBetween2PlacesController spbp = new ShortestPathBetween2PlacesController();
        boolean result1=spbp.shortestPathBetween2Places123("14635","19473",2);
        boolean expected1=true;
        assertEquals(result1,expected1);

        ArrayList<String> locais= new ArrayList<>();
        locais.add("Athens");
        locais.add("Valetta");
        locais.add("10136");
        boolean result2=spbp.shortestPathBetween2Places4("Nicosia","Ankara",locais);
        boolean expected2=true;
        assertEquals(result2,expected2);
    }
}