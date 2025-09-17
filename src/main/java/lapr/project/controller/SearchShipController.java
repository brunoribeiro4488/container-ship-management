package lapr.project.controller;

import lapr.project.utils.dto.ShipDTO;
import lapr.project.utils.Mappers.ShipMapper;
import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.utils.Printer;
import lapr.project.utils.stores.ShipStore;

import java.io.IOException;

/**
 * Represents the controller that serves as an intermediary between the UI and the domain layer.
 *
 * @author Rita Lello
 */
public class SearchShipController {

    /**
     * The controller's Ship Store.
     */
    private ShipStore shipStore;

    /**
     * The controller's Printer.
     */
    private final Printer printer;

    private static final String SEARCH ="Search Ship";

    /**
     * Builds a SearchShipController without receiving parameters.
     */
    public SearchShipController(){
        App app = App.getInstance();
        Company company = app.getCompany();
        this.shipStore= company.getshipStore();
        this.printer=new Printer();
    }

    /**
     * Calls the method from ship store that gets the ship with the same mmsi as wished and then calls the mapper method that converts the ship in data transfer object.
     *
     * @param mmsi wished mmsi to search a ship
     * @return the ship as a data transfer object
     */
    public ShipDTO getShipByMMSI(String mmsi) throws IOException {
        Ship ship = shipStore.getShipByMMSI(mmsi);
        ShipDTO shipDTO=ShipMapper.modelToDTO(ship);
        printer.print("Ship",shipDTO.toString(), SEARCH);
        return shipDTO;
    }

    /**
     * Calls the method from ship store that gets the ship with the same imo as wished and then calls the mapper method that converts the ship in data transfer object.
     *
     * @param imo wished imo to search a ship
     * @return the ship as a data transfer object
     */
    public ShipDTO getShipByIMO(String imo) throws IOException {
        Ship ship =shipStore.getShipByIMO(imo);
        ShipDTO shipDTO=ShipMapper.modelToDTO(ship);
        printer.print("Ship",shipDTO.toString(), SEARCH);
        return shipDTO;
    }

    /**
     * Calls the method from ship store that gets the ship with the same call sign as wished and then calls the mapper method that converts the ship in data transfer object.
     *
     * @param callSign wished call sign to search a ship
     * @return the ship as a data transfer object
     */
    public ShipDTO getShipByCallSign(String callSign) throws IOException {
        Ship ship = shipStore.getShipByCallSign(callSign);
        ShipDTO shipDTO=ShipMapper.modelToDTO(ship);
        printer.print("Ship",shipDTO.toString(), SEARCH);
        return shipDTO;
    }

    /**
     * Changes the Controller's ship store.
     *
     * @param shipStore new Controller's ship store
     */
    public void setShipStore(ShipStore shipStore) {
        this.shipStore = shipStore;
    }
}
