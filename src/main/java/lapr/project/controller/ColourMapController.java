package lapr.project.controller;

import lapr.project.utils.Printer;
import lapr.project.utils.stores.NetworkStore;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Represents the controller that serves as an intermediary between the UI and the domain layer.
 *
 * @author Carlos Rodrigues
 */
public class ColourMapController {

    /**
     * The controller's Printer.
     */
    private final Printer printer;

    private final NetworkStore ns;

    /**
     * Builds a ColourMapController without receiving parameters.
     */
    public ColourMapController() {
        this.ns = new NetworkStore();
        this.printer = new Printer();
    }


    public boolean colourMap() throws IOException, SQLException {
        String result = ns.colourMap();
        return printer.print("ColoredMap",result,"302");
    }

}
