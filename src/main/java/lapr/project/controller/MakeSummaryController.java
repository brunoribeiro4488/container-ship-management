package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Summary;
import lapr.project.utils.Printer;
import lapr.project.utils.stores.ShipStore;

import java.io.IOException;

/**
 * Represents the controller that serves as an intermediary between the UI and the domain layer.
 *
 * @author Bruno Ribeiro
 */
public class MakeSummaryController {

    /**
     * The controller's Ship Store.
     */
    private final ShipStore shipStore;

    /**
     * The controller's Summary.
     */
    private Summary summary;

    /**
     * Builds a MakeSummaryController receiving the company as a parameter.
     */
    public MakeSummaryController(Company company){
        shipStore = company.getshipStore();
    }

    /**
     * Calls the method from ship store that creates a summary for a specific ship and then prints the information in a file (if the summary exists)
     *
     * @param shipCode code of the ship which summary will be created
     *
     * @return true, if the information was saved in a file
     * @return false, if not
     *
     * @throws IOException
     */
    public boolean createSummary(String shipCode) throws IOException {
        summary = shipStore.createSummary(shipCode);
        if(summary == null){
            return false;
        }else {
            return print(summary.getVesselName());
        }
    }

    /**
     * Calls the method from class printer which prints the information of the summary in a file.
     *
     * @param fileName name of the new file
     *
     * @return true, if the information was saved in a file
     * @return false, if not
     *
     * @throws IOException
     */
    public boolean print(String fileName) throws IOException {
        Printer printer = new Printer();
        return printer.print(fileName, summary.toString(),"Summaries" );
    }
}
