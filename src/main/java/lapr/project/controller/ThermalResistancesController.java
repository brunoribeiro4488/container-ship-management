package lapr.project.controller;

import lapr.project.model.NegativeContainer;
import lapr.project.model.PositiveContainer;
import lapr.project.utils.Printer;

import java.io.IOException;

/**
 * Represents the controller that serves as an intermediary between the UI and the domain layer.
 *
 * @author Rita Lello
 */
public class ThermalResistancesController {

    /**
     * The controller's Printer.
     */
    private final Printer printer;

    /**
     * The controller's Positive Container.
     */
    private final PositiveContainer positiveContainer;

    /**
     * The controller's Negative Container.
     */
    private final NegativeContainer negativeContainer;

    private static final String SEARCH ="US319";

    /**
     * Builds a ThermalResistancesController without receiving parameters.
     */
    public ThermalResistancesController(){
        this.printer=new Printer();
        this.positiveContainer=new PositiveContainer();
        this.negativeContainer=new NegativeContainer();
    }

    /**
     * This method gets the thermal resistances of the containers that operate at temperatures of 7ºC and -5ºC
     * @return the thermal resistances of the containers
     * @throws IOException
     */
    public String getThermalResistances() throws IOException {
        String stringThermalResistances;
        double posContThermalResistance = positiveContainer.getThermicalResistance();
        double negContThermalResistance = negativeContainer.getThermicalResistance();
        stringThermalResistances = String.format("The container that operates at temperatures of 7ºC has as thermal resistance = %.2fK/W by square meter. This container is made of: Outer walls are %s, middle layers are %s and interior walls are %s. %n The container that operates at temperatures of -5ºC has as thermal resistance = %.2fK/W by square meter. This container is made of: Outer walls are %s, middle layers are %s and interior walls are %s",posContThermalResistance,positiveContainer.getOuterWalls(),positiveContainer.getMiddleLayers(),positiveContainer.getInteriorWalls(),negContThermalResistance,negativeContainer.getOuterWalls(),negativeContainer.getMiddleLayers(),negativeContainer.getInteriorWalls());
        printer.print("Thermal Resistances",stringThermalResistances, SEARCH);
        return stringThermalResistances;
    }
}
