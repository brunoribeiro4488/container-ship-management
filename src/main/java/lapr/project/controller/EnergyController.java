package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.utils.Printer;
import lapr.project.utils.stores.FsiapStore;

import java.io.IOException;

/**
 * Represents the controller that serves as an intermediary between the UI and the domain layer.
 *
 * @author Carlos Rodrigues
 */
public class EnergyController {

    /**
     * The controller's Printer.
     */
    private final Printer printer;

    /**
     * The controller's FsiapStore.
     */
    private final FsiapStore store;

    private static final String SEARCH ="US412 E 413";

    /**
     * Builds a EnergyController without receiving parameters.
     */
    public EnergyController(){
        App app = App.getInstance();
        Company company=app.getCompany();
        this.printer=new Printer();
        this.store = company.getFsiapStore();
    }

    /**
     * This method gets the energy needed to maintain the temperature of the containers that operate at temperatures of 7ºC and -5ºC
     * @return the energy needed to maintain the temperature of the containers that operate at temperatures of 7ºC and -5ºC
     * @throws IOException
     */
    public String getEnergy(int segundos , int temperature) throws IOException {
        int hours = segundos /3600;
        int minutes = (segundos % 3600)/60;
        String energy;
        double negativeenergy =store.getEnergyFromATripToNegativeContainer(segundos,temperature);
        double positiveenergy =store.getEnergyFromATripToPositiveContainer(segundos,temperature);

        energy = String.format("The container that operates at temperatures of 7ºC in a trip of %d hours and %d minutes with an exterior temperature of %d ºC" +
                " needs %.2f J to maintain it's temperature\n" +
                "The container that operates at temperatures of -5ºC in a trip of %d hours and %d minutes with an exterior temperature of %d ºC" +
                " needs %.2f J to maintain it's temperature",hours,minutes,temperature,positiveenergy,hours,minutes,temperature,negativeenergy);
        printer.print("Energy to maintain",energy, SEARCH);
        return energy;
    }
}
