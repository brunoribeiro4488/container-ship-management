package lapr.project.controller;

import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.utils.Printer;
import lapr.project.utils.stores.LaprStore;
import lapr.project.utils.stores.ShipStore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VesselSinkController {
    private final LaprStore laprStore;
    private ShipStore shipStore;
    private final Printer printer;

    private static final String SEARCH = "US420";

    public VesselSinkController(){
        App app = App.getInstance();
        Company company=app.getCompany();
        this.laprStore=company.getLaprStore();
        this.shipStore= company.getshipStore();
        this.printer=new Printer();
    }

    public List<Double> getVesselSink(String mmsi, int nContainers) throws IOException {
        List<Double> vesselSink = new ArrayList<>();
        Double totalMass = laprStore.getTotalMass(nContainers);
        vesselSink.add(totalMass);
        Ship ship = shipStore.getShipByMMSI(mmsi);
        Double width = Double.parseDouble(ship.getWidth());
        Double length = Double.parseDouble(ship.getLength());
        Double height = laprStore.getHeight(width, length, totalMass);
        vesselSink.add(height);
        Double pressure = laprStore.getPressure(height);
        vesselSink.add(pressure);
        printer.print("Vessel Sink", String.format("Total mass placed on the vessel: %.2fkg %nDifference in height that the vessel has suffered: %.2fm %nPressure exerted: %.2fPa", vesselSink.get(0),vesselSink.get(1),vesselSink.get(2)), SEARCH);
        return vesselSink;
    }

    public void setShipStore(ShipStore shipStore) {
        this.shipStore = shipStore;
    }
}
