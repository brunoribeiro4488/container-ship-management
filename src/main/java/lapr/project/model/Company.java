package lapr.project.model;

import lapr.project.utils.stores.*;

/**
 * Class Company.
 */
public class Company {

    /**
     * The company's ship store.
     */
    private final ShipStore shipStore;

    /**
     * The company's port store.
     */
    private final PortStore portStore;

    /**
     * The company's network store.
     */
    private final NetworkStore networkStore;

    /**
     * The company's lapr store.
     */
    private final LaprStore laprStore;

    /**
     * The company's fsiap store.
     */
    private final FsiapStore fsiapStore;


    /**
     * Builds a Company without receiving parameters.
     */
    public Company(){
        shipStore = new ShipStore();
        portStore = new PortStore();
        networkStore = new NetworkStore();
        laprStore = new LaprStore();
        fsiapStore = new FsiapStore();
    }

    /**
     * Get's the Company's ship store.
     *
     * @return Company's ship store
     */
    public ShipStore getshipStore() {
        return shipStore;
    }

    /**
     * Get's the Company's port store.
     *
     * @return Company's port store
     */
    public PortStore getPortStore() {
        return portStore;
    }

    /**
     * Get's the Company's network store.
     *
     * @return Company's network store
     */
    public NetworkStore getNetworkStore(){
        return networkStore;
    }

    /**
     * Get's the Company's lapr store.
     *
     * @return Company's lapr store
     */
    public LaprStore getLaprStore(){
        return laprStore;
    }

    /**
     * Get's the Company's fsiap store.
     *
     * @return Company's fsiap store
     */
    public FsiapStore getFsiapStore(){
        return fsiapStore;
    }
}
