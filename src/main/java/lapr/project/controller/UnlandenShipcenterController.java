package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.utils.Printer;
import lapr.project.utils.stores.LaprStore;

import java.io.IOException;

public class UnlandenShipcenterController {

    private final LaprStore laprStore;
    private final Printer printer;

    private static final String SEARCH = "US418";

    public UnlandenShipcenterController(){
        App app = App.getInstance();
        Company company=app.getCompany();
        this.laprStore=company.getLaprStore();
        this.printer=new Printer();
    }

    public boolean getCenterOfMassOfShips() throws IOException {
        String centerodmasses = String.format("%s%n%n%s%n%n%s%n", laprStore.getCenterOfMassPanamax(),laprStore.getCenterOfMassNewPanamax(),laprStore.getCenterOfMassUltraLargeVessel());
        return printer.print("CenterOfMass",centerodmasses,SEARCH);
    }
}
