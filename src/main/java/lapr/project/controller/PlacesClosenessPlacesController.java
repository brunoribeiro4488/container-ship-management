package lapr.project.controller;

import lapr.project.model.Company;

import lapr.project.utils.Pair;
import lapr.project.utils.Printer;
import lapr.project.utils.stores.NetworkStore;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PlacesClosenessPlacesController {
    private NetworkStore networkStore;
    private final Printer printer;

    private static final String SEARCH ="US303";

    public PlacesClosenessPlacesController(){
        App app = App.getInstance();
        Company company = app.getCompany();
        this.networkStore=company.getNetworkStore();
        this.printer=new Printer();
    }

    public List<Pair<String,Pair<Object, Double>>> getNClosestPlaces(int n) throws SQLException, IOException {
        List<Pair<String,Pair<Object, Double>>> closestPlaces = networkStore.getNClosestPlaces(n);
        StringBuilder fim= new StringBuilder();
        for(int i=0; i<closestPlaces.size();i++){
            fim.append(String.format("Continent: %s %n %s %.2fkm %n", closestPlaces.get(i).first, closestPlaces.get(i).second.first, closestPlaces.get(i).second.second));
        }

        printer.print("Closest Places", fim.toString(),SEARCH);
        return closestPlaces;
    }

    public void setNetworkStore(NetworkStore networkStore){
        this.networkStore=networkStore;
    }
}
