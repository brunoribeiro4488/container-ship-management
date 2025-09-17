package lapr.project.utils.stores;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LaprStoreTest {

    @Test
    void getTypeOfVessel() {
        LaprStore store = new LaprStore();
        String expected1 = "The best ship for the task is a Panamax.\nThis ship can transport up to 5100 containers.\nThe dimensions of this ship are 294x32x12 (m).\nWith tower in the stern\n";
        String expected2 = "The best ship for the task is a New-Panamax.\nThis ship can transport up to 10000 containers.\nThe dimensions of this ship are 366x49x15 (m).\nWith tower in the middle\n";
        String expected3 = "The best ship for the task is a Ultra Large Container Vessel.\nThis ship has capacity for more than 14500 containers.\nThis ship is bigger than 366x49x15 (m).\nWith tower in the bow\n";

        Assert.assertEquals(expected1,store.getTypeOfVessel(500));
        Assert.assertEquals(expected1,store.getTypeOfVessel(5100));
        Assert.assertEquals(expected2,store.getTypeOfVessel(6000));
        Assert.assertEquals(expected2,store.getTypeOfVessel(14500));
        Assert.assertEquals(expected3,store.getTypeOfVessel(20000));
    }

    @Test
    void getCenterOfMass() {
        LaprStore store = new LaprStore();

        String expected1 = "(39.506173,40.000000)";
        String expected2 = "(50.000000,50.000000)";
        String expected3 = "(100.492537,100.000000)";

        Assert.assertEquals(expected1,store.getCenterOfMass(20000000000.0,80));
        Assert.assertEquals(expected2,store.getCenterOfMass(50000000000.0,100));
        Assert.assertEquals(expected3,store.getCenterOfMass(100000000000.0,200));
    }

    @Test
    void printMatrix() {
        LaprStore store = new LaprStore();

        String arr[][] = new String[5][5];
        String expected = "|\t0\t0\t0\t0\t0\t|\n" +
                "|\t0\t0\t0\t0\t0\t|\n" +
                "|\t0\t0\tx\t0\t0\t|\n" +
                "|\t0\t0\t0\t0\t0\t|\n" +
                "|\t0\t0\t0\t0\t0\t|\n";
        Assert.assertEquals(expected,store.printMatrix(arr,"(2,0,2,0)"));


    }

    @Test
    void addContainersMatrix() {
        LaprStore store = new LaprStore();

        String arr[][] = new String[5][5];
        String expected = "|\t0\t0\t0\t0\t0\t|\n" +
                "|\t0\t1\t1\t1\t0\t|\n" +
                "|\t0\t1\tx-1\t1\t0\t|\n" +
                "|\t0\t1\t1\t1\t0\t|\n" +
                "|\t0\t0\t0\t0\t0\t|\n";
        Assert.assertEquals(expected,store.addContainersMatrix(arr,"(2,0,2,0)",9));


    }


}