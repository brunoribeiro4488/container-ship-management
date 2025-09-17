package lapr.project.controller;

import lapr.project.data.ContainerSituationWarming;
import lapr.project.utils.Printer;

import java.io.IOException;
import java.sql.SQLException;

public class ContainerSituationWarmingController {

    /**
     * Get the Container Situation
     * @param number The container number
     * @param isoCode The container ISO code
     * @param clientReg The client registration code
     * @return True if the information is correctly write in a file
     * @throws SQLException
     * @throws IOException
     */
    public boolean getContainerSituation(String number, String isoCode, String clientReg) throws SQLException, IOException {
        ContainerSituationWarming cs = new ContainerSituationWarming();
        String result = cs.getContainerSituationWarming(number, isoCode, clientReg);
        if(result.contains("Error")){
            print("Current Situation", result);
            return false;
        }
        return print("Current Situation", result);
    }

    /**
     * Calls the method from class printer which prints the container situation.
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
        return printer.print(fileName, stringToPrint,"US312" );
    }
}
