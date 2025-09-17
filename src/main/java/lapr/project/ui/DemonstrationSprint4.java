package lapr.project.ui;

import lapr.project.controller.*;
import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.utils.stores.ShipStore;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DemonstrationSprint4 {
    public static void main(String[] args) throws SQLException, IOException {

        System.out.println("Loading...");

        //create graph
        FreightNetworkController fnc = new FreightNetworkController();
        fnc.freightNetwork(5);

        //US401
        CriticalPortController cpc = new CriticalPortController(App.getInstance().getCompany());
        cpc.getCriticalPort(4);

        //US402
        ShortestPathBetween2PlacesController spbp = new ShortestPathBetween2PlacesController();
        spbp.shortestPathBetween2Places123("14635","19473",2);
        ArrayList<String> locais= new ArrayList<>();
        locais.add("Athens");
        locais.add("Valetta");
        locais.add("10136");
        spbp.shortestPathBetween2Places4("Nicosia","Ankara",locais);

        //US403
        MostEfficientCircuitController mostEfficientCircuitController = new MostEfficientCircuitController();
        mostEfficientCircuitController.getMostEfficientCircuit("18012");

        //404
        IdleShipsController isc = new IdleShipsController();
        isc.idleships();

        //US405
        AvgOcuppancyRateController aor = new AvgOcuppancyRateController();
        aor.avgOcuppancyRate("234234234","2022-03-19 00:13:01","2022-07-19 00:13:01");

        //US406
        ShipVoyagesController svc = new ShipVoyagesController();
        svc.shipVoyages("234234234");

        //US407
        LoadingUnloadingMapController lumc = new LoadingUnloadingMapController();
        lumc.loadingUnloading("42042");

        //US412 e 413
        EnergyController ec = new EnergyController();
        ec.getEnergy(3600,20);

        //US414
        EnergyWithSidesController ewsc = new EnergyWithSidesController();
        ewsc.getEnergy(6,6,3600,20);

        //US415
        ewsc.getgenerators(25,15,50,15,6,30,20);

        //US417
        VesselTypeController vtc = new VesselTypeController();
        vtc.getVesselType(5000);
        vtc.getVesselType(10000);
        vtc.getVesselType(15000);

        //US418
        UnlandenShipcenterController unlsc = new UnlandenShipcenterController();
        unlsc.getCenterOfMassOfShips();

        //US419
        AddContainersController acc = new AddContainersController();
        acc.addContainers(101);

        //US420
        VesselSinkController vsc = new VesselSinkController();
        Ship ship = new Ship("123456789","shipo","IMO4545678","DR565","12345","90","40","10");
        ShipStore shipStore = new ShipStore();
        shipStore.saveShipInBST(ship);
        vsc.setShipStore(shipStore);
        vsc.getVesselSink("123456789",3000);

    }
}
