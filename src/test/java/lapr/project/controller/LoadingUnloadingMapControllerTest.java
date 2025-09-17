package lapr.project.controller;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class LoadingUnloadingMapControllerTest {

    @Test
    void loadingUnloading() throws SQLException, IOException {
        LoadingUnloadingMapController loadingUnloadingMapController = new LoadingUnloadingMapController();
        boolean result = loadingUnloadingMapController.loadingUnloading("42042");
        assertTrue(result);
    }

    @Test
    void print() {
    }
}