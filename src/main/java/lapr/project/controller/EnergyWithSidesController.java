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
public class EnergyWithSidesController {


    /**
     * The controller's Printer.
     */
    private final Printer printer;

    /**
     * The controller's FsiapStore.
     */
    private final FsiapStore store;

    private static final String SEARCH ="US414";

    /**
     * Builds a EnergyController without receiving parameters.
     */
    public EnergyWithSidesController(){
        App app = App.getInstance();
        Company company=app.getCompany();
        this.printer=new Printer();
        this.store = company.getFsiapStore();
    }

    public String getEnergy(int lateralsides ,int frontalsides ,int segundos , int temperature) throws IOException {
        int hours = segundos /3600;
        int minutes = (segundos%3600) / 60;
        String energy;
        double negativeenergy =store.getEnergyFromATripToNegativeContainerWithSides(lateralsides,frontalsides,segundos,temperature);
        double positiveenergy =store.getEnergyFromATripToPositiveContainerWithSides(lateralsides,frontalsides,segundos,temperature);

        energy = String.format("The container that operates at temperatures of 7ºC in a trip of %d hours and %d minutes with an exterior temperature of %d ºC" +
                " needs %.2f J to maintain it's temperature\n" +
                "The container that operates at temperatures of -5ºC in a trip of %d hours and %d minutes with an exterior temperature of %d ºC" +
                " needs %.2f J to maintain it's temperature",hours,minutes,temperature,positiveenergy,hours,minutes,temperature,negativeenergy);
        printer.print("Energy to maintain",energy, SEARCH);
        return energy;
    }

    /**
     *
     * @param positivecontainers number of containers of 7ºC
     * @param lateralsidespositive number of exposed side sides of the containers of 7ºC
     * @param frontalsidespositive number of exposed frontal sides of the containers de 7ºC
     * @param negativecontainers number of containers of -5ºC
     * @param lateralsidesnegative number of exposed side sides of the containers of -5ºC
     * @param frontalsidesnegative number of exposed frontal sides of the containers de -5ºC
     * @param temperatura outside temperature
     * @return
     * @throws IOException
     */
    public String getgenerators(int positivecontainers,int lateralsidespositive , int frontalsidespositive , int negativecontainers, int lateralsidesnegative , int frontalsidesnegative, int temperatura) throws IOException {
        double potencia = 75000.0;
        String energy;
        double fluxo = store.getFluxoWithSides(positivecontainers,lateralsidespositive,frontalsidespositive,negativecontainers,lateralsidesnegative,frontalsidesnegative,temperatura);
        int geradores = (int) Math.ceil(fluxo / potencia);
        energy = String.format("To maintain the temperature of %d containers of -5ºC and %d containers of 7ºC arranged in a specific way, %d generators of 75KW are needed",negativecontainers,positivecontainers,geradores);
        printer.print("Needed generators",energy,"US415");
        return energy;
    }

}
