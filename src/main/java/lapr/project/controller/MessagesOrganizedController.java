package lapr.project.controller;

import lapr.project.utils.Mappers.ShipPositionMapper;
import lapr.project.utils.Printer;
import lapr.project.utils.dto.ShipPositionDTO;
import lapr.project.model.Company;
import lapr.project.utils.stores.ShipStore;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Represents the controller that serves as an intermediary between the UI and the domain layer.
 *
 * @author Carlos Rodrigues
 */
public class MessagesOrganizedController {

    private static final String FILENAME = "MessagesOrganized";
    /**
     * The controller's Ship Store.
     */
    private final ShipStore shipStore;

    /**
     * Builds a MessagesOrganizedController receiving the company as a parameter.
     */
    public MessagesOrganizedController(Company company) {
        this.shipStore = company.getshipStore();
    }

    /**
     * Calls the method from ship store that receives a list of all messages organized in a date and then calls the method from ship position mapper where transforms the objects in data transfer objects, printing everything in a file.
     *
     * @param date date of the messages to obtain
     * @return list of ship position in data transfer objects
     * @throws ParseException
     */
    public List<ShipPositionDTO> getMessagesOrganized(Date date) throws ParseException {
        List<ShipPositionDTO> dtoList = ShipPositionMapper.toDTOlist(shipStore.getMessagesOrganized(date));
        StringBuilder str = new StringBuilder();
        for (ShipPositionDTO shipPositionDTO: dtoList) {
            str.append(String.format("%s%n",shipPositionDTO.toString()));
        }
        try {
            Printer printer = new Printer();
            printer.print(FILENAME, str.toString(),FILENAME);
        }catch (Exception e){
            // it catches the exception
        }
        return dtoList;
    }

    /**
     * Calls the method from ship store that receives a list of all messages organized in an interval of time and then calls the method from ship position mapper where transforms the objects in data transfer objects, printing everything in a file.
     *
     * @param firstdate beginning of the interval
     * @param lastdate ending of the interval
     *
     * @return list of ship position in data transfer objects
     * @throws ParseException
     */
    public List<ShipPositionDTO> getMessagesOrganized(Date firstdate, Date lastdate) throws ParseException {
        List<ShipPositionDTO> dtoList = ShipPositionMapper.toDTOlist(shipStore.getMessagesOrganized(firstdate,lastdate));
        StringBuilder str = new StringBuilder();
        for (ShipPositionDTO shipPositionDTO: dtoList) {
            str.append(String.format("%s%n",shipPositionDTO.toString()));
        }
        try {
            Printer printer = new Printer();
            printer.print(FILENAME, str.toString(),FILENAME);
        }catch (Exception e){
            // it catches the exception
        }
        return dtoList;
    }

}

