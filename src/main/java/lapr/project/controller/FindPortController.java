package lapr.project.controller;

import lapr.project.data.ConnectionFactory;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.PortStoreDB;
import lapr.project.data.ShipStoreDB;
import lapr.project.model.Company;
import lapr.project.model.Port;
import lapr.project.model.Ship;
import lapr.project.model.ShipPosition;
import lapr.project.utils.Mappers.PortMapper;
import lapr.project.utils.Printer;
import lapr.project.utils.dto.PortDTO;
import lapr.project.utils.stores.PortStore;
import lapr.project.utils.stores.ShipStore;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents the controller that serves as an intermediary between the UI and the domain layer.
 *
 * @author Rita Lello
 */
public class FindPortController {
    /**
     * The controller's Ship Store.
     */
    private ShipStore shipStore;

    /**
     * The controller's Port Store.
     */
    private PortStore portStore;

    /**
     * The controller's Printer.
     */
    private final Printer printer;

    private static final String SEARCH ="Find closest port";

    /**
     * Builds a FindPortController without receiving parameters.
     */
    public FindPortController() throws SQLException, ParseException {
        App app = App.getInstance();
        Company company = app.getCompany();
        this.shipStore= company.getshipStore();
        this.portStore= company.getPortStore();
        this.printer=new Printer();
        PortStoreDB pdb = new PortStoreDB();
        ShipStoreDB sdb = new ShipStoreDB();
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = ConnectionFactory.getInstance()
                    .getDatabaseConnection();
        } catch (IOException exception) {
            Logger.getLogger(ShipStoreDB.class.getName())
                    .log(Level.SEVERE, null, exception);
        }
        portStore.saveListPortinKdt(pdb.readPortsfromDB(databaseConnection));
        shipStore.saveListShipInBST(sdb.readShipsFromDB(databaseConnection));
    }

    /**
     * Calls the method from ship store that gets the ship with the same call sign as wished, the method that gets the ship position on a specific date, from port store calls the method that gets the closest port to that ship and then calls the mapper method that converts the port in data transfer object.
     *
     * @param callSign wished call sign to search a ship
     * @param dateTime wished date to search the closest port
     * @return the port as a data transfer object
     */
    public PortDTO getClosestPort(String callSign, String dateTime) throws ParseException, IOException {
        Ship s = shipStore.getShipByCallSign(callSign);
        ShipPosition shipPosition = shipStore.getShipPositionByDate(s,dateTime);
        String shipPositionLat= shipPosition.getLat();
        String shipPositionLon = shipPosition.getLon();
        Port port = portStore.getClosestPort(shipPositionLat,shipPositionLon);
        PortDTO portDTO= PortMapper.modelToDTO(port);
        printer.print("Port",portDTO.toString(), SEARCH);
        return portDTO;
    }

    /**
     * Changes the Controller's ship store.
     *
     * @param shipStore new Controller's ship store
     */
    public void setShipStore(ShipStore shipStore) {
        this.shipStore = shipStore;
    }

    /**
     * Changes the Controller's port store.
     *
     * @param portStore new Controller's port store
     */
    public void setPortStore(PortStore portStore) {
        this.portStore = portStore;
    }
}
