package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.utils.Pair;
import lapr.project.utils.Printer;
import lapr.project.utils.stores.ShipStore;

import java.io.IOException;
import java.util.List;

/**
 * Represents the controller that serves as an intermediary between the UI and the domain layer.
 *
 * @author José Pessoa
 */
public class ShipPairController {

    /**
     * The controller's Ship Store.
     */
    private final ShipStore shipStore;

    /**
     * The controller's list of pairs ship,ship.
     */
    private List<Pair<Ship, Ship>> pair;

    /**
     * Builds a ShipPairController receiving the company as a parameter.
     */
    public ShipPairController(Company company) {
        this.shipStore = company.getshipStore();
    }

    /**
     * Calls the method from the store that returns all the ship pairs
     *
     * @return list of pairs of ships
     *
     * @throws IOException
     */
    public List<Pair<Ship, Ship>> pairOfShips() throws IOException {
        pair = shipStore.pairOfShips();
        List<Pair<Ship, Ship>> list = shipStore.pairOfShips();
        print(pair.size()+" Pairs");
        return list;
    }

    /**
     * Calls the method from class printer which prints the information of the pair in a file.
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
        return printer.print(fileName, pair.toString(),"Pairs" );
    }
}
