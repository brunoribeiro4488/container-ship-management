//package lapr.project.ui;
//
//import lapr.project.controller.*;
//import lapr.project.model.Company;
//
//import java.io.IOException;
//import java.sql.SQLException;
//
//public class DemonstrationSprint3 {
//    public static void main(String[] args) throws SQLException, IOException {
//        Company company = App.getInstance().getCompany();
//
//        System.out.println("Loading...");
//
//        //US301
//        FreightNetworkController fnc = new FreightNetworkController();
//        fnc.freightNetwork(5);
//
//        //US302
//        ColourMapController cmc = new ColourMapController();
//        cmc.colourMap();
//
//        //US303
//        PlacesClosenessPlacesController pcpc = new PlacesClosenessPlacesController();
//        pcpc.getNClosestPlaces(5);
//
//        //US304
//        GetAuditTrailController atc = new GetAuditTrailController();
//        atc.getAuditTrail("78546321458", "4785", "11111");
//
//        //US305
//        GetRouteOfAContainerController rcc = new GetRouteOfAContainerController();
//        rcc.getRouteOfAContainer("12345", "78546321458");
//
//        //US306
//        WarehouseRateController wrc = new WarehouseRateController();
//        wrc.warehouseRate();
//
//        //US307
//        WarehouseCapacityController wcc = new WarehouseCapacityController();
//        wcc.warehouseCapacity("12333");
//
//        //US308
//        ContainerCapacityController ccc = new ContainerCapacityController();
//        ccc.containerCapacity();
//
//        //US309
//        ShipOccupiedController soc = new ShipOccupiedController();
//        soc.insertCargoManifestForShip("11113", "1", "2021-02-21 00:07:01", "0", "12345");
//
//        //US310
//        OccupationmapPortController opc = new OccupationmapPortController();
//        opc.occupationmapPort("78978","2021-03-01 00:00:00");
//
//        //US312
//        ContainerSituationWarmingController cswc = new ContainerSituationWarmingController();
//        cswc.getContainerSituation("78546321458", "4285", "14345");
//        cswc.getContainerSituation("77546321458", "7785", "14342");
//
//        //US313
//        ArqcpController ac = new ArqcpController();
//        ac.arqcp("32132");
//
//        //US319
//        ThermalResistancesController trc = new ThermalResistancesController();
//        trc.getThermalResistances();
//    }
//}
