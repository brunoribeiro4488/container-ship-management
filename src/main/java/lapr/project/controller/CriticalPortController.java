package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.utils.Printer;
import lapr.project.utils.stores.NetworkStore;

import java.io.IOException;

public class CriticalPortController {

    private final NetworkStore ns;

    public CriticalPortController(Company c){
        ns = c.getNetworkStore();
    }

    public boolean getCriticalPort(int n) throws IOException {
        String criticalPort = ns.getCriticalPort(n);
        if(criticalPort.length()==0){
            return false;
        }
        return print("Critical Port", criticalPort);
    }

    /**
     * Calls the method from class printer which prints the n critical ports.
     *
     * @param fileName name of the new file
     *
     * @return true, if the information was saved in a file
     * @return false, if not
     *
     * @throws IOException
     */
    public boolean print(String fileName, String stringToPrint) throws IOException {
        Printer printer = new Printer();
        return printer.print(fileName, stringToPrint,"US401");
    }
}
