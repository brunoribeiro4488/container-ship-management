package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.utils.Pair;
import lapr.project.utils.Printer;
import lapr.project.utils.stores.NetworkStore;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

public class MostEfficientCircuitController {
    private final NetworkStore networkStore;
    private final Printer printer;

    private static final String SEARCH ="US403";

    public MostEfficientCircuitController(){
        App app = App.getInstance();
        Company company = app.getCompany();
        this.networkStore=company.getNetworkStore();
        this.printer=new Printer();
    }

    public Pair<LinkedList<Object>, Double> getMostEfficientCircuit(String sourceLocation) throws IOException, SQLException {
        networkStore.createGraph(20);
        Pair<LinkedList<Object>, Double> mostEfficientCircuit = networkStore.getMostEfficientCircuit(sourceLocation);
        printer.print("Most Efficient Circuit", String.format("The most efficient circuit starting at %s is: %s with a total distance of %.2fkm",sourceLocation, mostEfficientCircuit.first.toString(),mostEfficientCircuit.second),SEARCH);
        return mostEfficientCircuit;
    }
}
