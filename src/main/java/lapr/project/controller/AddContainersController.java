package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.utils.Printer;
import lapr.project.utils.stores.LaprStore;

import java.io.IOException;

public class AddContainersController {
    private final LaprStore laprStore;
    private final Printer printer;

    private static final String SEARCH = "US419";

    public AddContainersController(){
        App app = App.getInstance();
        Company company=app.getCompany();
        this.laprStore=company.getLaprStore();
        this.printer=new Printer();
    }

    public boolean addContainers(int containers) throws IOException {
        String addcontainers = String.format("%s%n%n%s%n%n%s%n", laprStore.keepCenterOfMassPanamax(containers),laprStore.keepCenterOfMassNewPanamax(containers),laprStore.keepCenterOfMassUltraLargeVessel(containers));
        return printer.print("AddContainers",addcontainers,SEARCH);
    }
}
