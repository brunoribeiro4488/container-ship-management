package lapr.project.controller;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AddContainersControllerTest {

    @Test
    void addContaienrs() throws IOException {
        AddContainersController addContainersController = new AddContainersController();
        assertTrue(addContainersController.addContainers(50));
    }
}