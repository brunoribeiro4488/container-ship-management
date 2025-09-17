package lapr.project.utils.stores;

import lapr.project.model.NegativeContainer;
import lapr.project.model.PositiveContainer;

public class FsiapStore {

    /**
     * The controller's Positive Container.
     */
    private final PositiveContainer positiveContainer;

    /**
     * The controller's Negative Container.
     */
    private final NegativeContainer negativeContainer;

    public FsiapStore() {
        this.negativeContainer = new NegativeContainer();
        this.positiveContainer = new PositiveContainer();
    }

    public double getEnergyFromATripToNegativeContainer(int segundos , double temperatura ){
        double arealateral = 6 * 2.438;
        double areafrontal = 2.438 *2.436;
        double areatotal = 4* arealateral +2*areafrontal;
        double fluxo = (temperatura - negativeContainer.getTemperature()) / negativeContainer.getThermicalResistance();
        return fluxo*segundos*areatotal;
    }

    public double getEnergyFromATripToPositiveContainer(int segundos , double temperatura ){
        double arealateral = 6 * 2.438;
        double areafrontal = 2.438 *2.436;
        double areatotal = 4* arealateral +2*areafrontal;
        double fluxo = (temperatura - positiveContainer.getTemperature()) / positiveContainer.getThermicalResistance();
        return fluxo*segundos*areatotal;
    }

    public double getEnergyFromATripToNegativeContainerWithSides(int lateralsides ,int frontalsides ,int segundos , double temperatura ){
        double arealateral = 6 * 2.438;
        double areafrontal = 2.438 *2.436;
        double areatotal = lateralsides * arealateral + frontalsides *areafrontal;
        double fluxo = (temperatura - negativeContainer.getTemperature()) / negativeContainer.getThermicalResistance();
        return fluxo*segundos*areatotal;
    }

    public double getEnergyFromATripToPositiveContainerWithSides(int lateralsides ,int frontalsides  ,int segundos , double temperatura ){
        double arealateral = 6 * 2.438;
        double areafrontal = 2.438 *2.436;
        double areatotal = lateralsides * arealateral + frontalsides *areafrontal;
        double fluxo = (temperatura - positiveContainer.getTemperature()) / positiveContainer.getThermicalResistance();
        return fluxo*segundos*areatotal;
    }

    public double getFluxoWithSides(int positivecontainers,int lateralsidespositive , int frontalsidespositive , int negativecontainers, int lateralsidesnegative , int frontalsidesnegative, int temperatura){
        double arealateral = 6 * 2.438;
        double areafrontal = 2.438 *2.436;
        double areatotalnegative = lateralsidesnegative * arealateral + frontalsidesnegative * areafrontal;
        double areatotalpositive = lateralsidespositive * arealateral + frontalsidespositive * areafrontal;
        double fluxopositive = (temperatura - positiveContainer.getTemperature()) / positiveContainer.getThermicalResistance();
        double fluxonegative = (temperatura - negativeContainer.getTemperature()) / negativeContainer.getThermicalResistance();

        return ((fluxonegative*areatotalnegative)*negativecontainers + (fluxopositive*areatotalpositive)*positivecontainers);
    }
}
