package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.utils.Printer;
import lapr.project.utils.stores.NetworkStore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShortestPathBetween2PlacesController {
    /**
     * Builds a FreightNetworkController without receiving parameters.
     */
    public ShortestPathBetween2PlacesController() {
        App app = App.getInstance();
        Company company = app.getCompany();
        this.ns = company.getNetworkStore();
        this.printer = new Printer();
    }

    /**
     * The controller's Printer.
     */
    private final Printer printer;

    /**
     * The Network Store.
     */
    private final NetworkStore ns;

    public boolean shortestPathBetween2Places123(String local1, String local2, int n) throws IOException{
        String result = ns.shortestPathBetweenTwoLocals(local1,local2,n);
        return printer.print("shortestPathBetweenTwoLocals",result,"US402");
    }

    public boolean shortestPathBetween2Places4(String local1, String local2, List<String> locais) throws IOException {
        String result = ns.shortestPathBetweenTwoLocals4(local1,local2, (ArrayList<String>) locais);
        return printer.print("shortestPathBetweenTwoLocals",result,"US402");
    }
}
