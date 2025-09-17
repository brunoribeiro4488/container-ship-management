package lapr.project.controller;

import lapr.project.utils.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class MostEfficientCircuitControllerTest {

    @Test
    void getMostEfficientCircuit() throws IOException, SQLException {
        MostEfficientCircuitController mostEfficientCircuitController = new MostEfficientCircuitController();
        Pair<LinkedList<Object>, Double> result = mostEfficientCircuitController.getMostEfficientCircuit("18012");
        String expected = "Circuit: [18012, 18137, 18682, 18326, 29002, 29239, 224858, 29749, 23247, 20512, 14277, 26147, 21451, 17941, 14635, 10860, 30045, 13390, 216592, 216593, 18476, 18433, 213737, 21852, 10136, 21863, 22522, 246265, 18200, 11174, 13176, 22770, 14470, 18937, 17386, 24795, 12543, 21381, 11771, 18454, 20072, 25350, 24951, 28313, 16485, 10358, 10563, 18867, 16737, 27792, 22226, 28082, 21556, 21457, 21206, 25007, 27728, 23428, 13012, 14459, 14113, 26670, 27248, 20301, 20351, 20847, 28781, 15107, 14226, 29876, 28261, 20826, 29973, 18012] with distance=208935.0km";
        Assertions.assertEquals(expected, result.toString2());
    }
}