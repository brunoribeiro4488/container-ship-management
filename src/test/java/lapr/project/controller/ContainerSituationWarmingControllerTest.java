package lapr.project.controller;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ContainerSituationWarmingControllerTest {

    @Test
    void getContainerSituation() throws SQLException, IOException {
        ContainerSituationWarmingController c = new ContainerSituationWarmingController();
        Assert.assertTrue(c.getContainerSituation("78546321458", "4785", "12345"));
        Assert.assertFalse(c.getContainerSituation("78546321458", "4285", "14345"));
        Assert.assertNotEquals(true, c.getContainerSituation("78546321458", "4285", "14345"));
        Assert.assertNotEquals(false, c.getContainerSituation("78546321458", "4785", "12345"));
    }

    @Test
    void print() {
    }
}