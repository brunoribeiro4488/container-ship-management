package lapr.project.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class ImportPortControllerTest {

    @Test
    void read() {
        ImportPortController controller = new ImportPortController(App.getInstance().getCompany());
        try {
            controller.read(null);
            Assertions.fail("There should have been an exception");
        } catch (NullPointerException | IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}