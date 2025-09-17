package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.utils.Printer;
import lapr.project.utils.stores.NetworkStore;

import java.io.IOException;
import java.sql.SQLException;

public class FreightNetworkController {

    /**
     * Builds a FreightNetworkController without receiving parameters.
     */
    public FreightNetworkController() {
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

    public boolean freightNetwork(int n) throws IOException, SQLException {
        String result = ns.createGraph(n);
        return printer.print("FreightNetwork",result,"US301");
    }
}
