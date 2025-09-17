package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.utils.Printer;
import lapr.project.utils.stores.LaprStore;

import java.io.IOException;

public class VesselTypeController {

    Company company;
    LaprStore store;

    public VesselTypeController(){
        company = App.getInstance().getCompany();
        store = company.getLaprStore();
    }

    public String getVesselType(int numOfContainers) throws IOException {
        String result = store.getTypeOfVessel(numOfContainers);
        print("VesselType", result);
        return result;
    }

    public boolean print(String fileName, String stringToPrint) throws IOException {
        Printer printer = new Printer();
        return printer.print(fileName, stringToPrint,"US417" );
    }
}
