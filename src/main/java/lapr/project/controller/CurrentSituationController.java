package lapr.project.controller;

import lapr.project.data.CurrentSituation;
import lapr.project.data.DatabaseConnection;
import lapr.project.utils.Printer;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;

/**
 * Represents the controller that serves as an intermediary between the UI and the domain layer.
 */
public class CurrentSituationController {

    /**
     * The controller's current situation.
     */
    private final CurrentSituation currentSituation;

    /**
     * Builds a CurrentSituationController without receiving parameters.
     */
    public CurrentSituationController() {
        currentSituation= new CurrentSituation();
    }

    /**
     * calls methods that returns the current situation of a container.
     *
     * @param databaseConnection
     * @param ncontainer
     * @param ccontainer
     * @return
     * @throws IOException
     * @throws ParseException
     * @throws SQLException
     */
    public boolean currentSituation(DatabaseConnection databaseConnection,String ncontainer,String ccontainer) throws SQLException, IOException {
            String result = currentSituation.currentSituation(databaseConnection,ncontainer,ccontainer);
            return print("Current Situation",result);
    }

    /**
     * Calls the method from class printer which prints the information of the current situation in a file.
     *
     * @param fileName name of the new file
     *
     * @return true, if the information was saved in a file
     * @return false, if not
     *
     * @throws IOException
     */
    public boolean print(String fileName, String str) throws IOException {
        Printer printer = new Printer();
        return printer.print(fileName, str,"CurrentSituation" );
    }

}
