package lapr.project.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositiveContainerTest {

    @Test
    void getThermicalResistance() {
        PositiveContainer positiveContainer = new PositiveContainer();
        double expected=4.590000000000001;
        double result = positiveContainer.getThermicalResistance();
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getOuterWalls() {
        PositiveContainer positiveContainer=new PositiveContainer();
        String expected = "Stainless steel";
        String result = positiveContainer.getOuterWalls();
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getInteriorWalls() {
        PositiveContainer positiveContainer=new PositiveContainer();
        String expected = "Soft-board wood";
        String result = positiveContainer.getInteriorWalls();
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getMiddleLayers() {
        PositiveContainer positiveContainer=new PositiveContainer();
        String expected = "Polyurethane foam";
        String result = positiveContainer.getMiddleLayers();
        Assertions.assertEquals(expected,result);
    }
}