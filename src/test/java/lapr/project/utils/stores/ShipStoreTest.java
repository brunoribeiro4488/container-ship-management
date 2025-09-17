package lapr.project.utils.stores;

import lapr.project.model.Company;
import lapr.project.model.Ship;
import lapr.project.model.ShipPosition;
import lapr.project.model.Summary;
import lapr.project.utils.Pair;
import lapr.project.utils.bst.BSTMMSI;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ShipStoreTest {

    @Test
    void getShipByMMSI() {
        ShipStore shipStore = new ShipStore();
        Ship ship = shipStore.createShip("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        String mmsi = ship.getMmsi();
        shipStore.saveShipInBST(ship);
        Ship shipResult = shipStore.getShipByMMSI(mmsi);
        Assertions.assertEquals(ship, shipResult);
    }

    @Test
    void getShipByIMO() {
        ShipStore shipStore = new ShipStore();
        Ship ship = shipStore.createShip("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        String imo = ship.getImo();
        shipStore.saveShipInBST(ship);
        Ship shipResult = shipStore.getShipByIMO(imo);
        Assertions.assertEquals(ship, shipResult);
    }

    @Test
    void getShipByCallSign() {
        ShipStore shipStore = new ShipStore();
        Ship ship = shipStore.createShip("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        String callSign = ship.getCallsign();
        shipStore.saveShipInBST(ship);
        Ship shipResult = shipStore.getShipByCallSign(callSign);
        Assertions.assertEquals(ship, shipResult);
    }

    @Test
    void milisecondsToStringRight(){
        ShipStore shipStore = new ShipStore();
        String expected = "00h:02m:03s";
        String result = shipStore.milisecondsToString(123456);
        Assertions.assertEquals(expected,result);
    }

    @Test
    void milisecondsToStringWrong(){
        ShipStore shipStore = new ShipStore();
        String notExpected = "444441600000h:444441600000m:7407360000s";
        String result = shipStore.milisecondsToString(123456);
        Assertions.assertNotEquals(notExpected,result);
    }


    @Test
    void maxSOGRight() throws ParseException {
        ShipStore shipStore=new ShipStore();
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        shipStore.saveShipInBST(ship);
        shipStore.addShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        shipStore.addShipPosition("210950000","31/12/2020 16:20","42.7698","-66.9759","13.3","3.7","356","NA","B");
        shipStore.addShipPosition("210950000","31/12/2020 16:32","42.81133","-66.97587","13.4","10","356","NA","B");
        double expected = 13.4;
        double result = shipStore.maxSOG(ship);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void maxSOGEquals() throws ParseException {
        ShipStore shipStore=new ShipStore();
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        shipStore.saveShipInBST(ship);
        shipStore.addShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","-1","2.4","358","NA","B");
        shipStore.addShipPosition("210950000","31/12/2020 16:20","42.7698","-66.9759","-1","3.7","356","NA","B");
        shipStore.addShipPosition("210950000","31/12/2020 16:32","42.81133","-66.97587","-1","10","356","NA","B");
        double expected = -1;
        double result = shipStore.maxSOG(ship);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void maxSOGWrong() throws ParseException {
        ShipStore shipStore=new ShipStore();
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        shipStore.saveShipInBST(ship);
        shipStore.addShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        shipStore.addShipPosition("210950000","31/12/2020 16:20","42.7698","-66.9759","13.3","3.7","356","NA","B");
        shipStore.addShipPosition("210950000","31/12/2020 16:32","42.81133","-66.97587","13.4","10","356","NA","B");
        double notExpected = -1;
        double result = shipStore.maxSOG(ship);
        Assertions.assertNotEquals(notExpected, result);
    }

    @Test
    void maxCOGRight() throws ParseException {
        ShipStore shipStore=new ShipStore();
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        shipStore.saveShipInBST(ship);
        shipStore.addShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        shipStore.addShipPosition("210950000","31/12/2020 16:20","42.7698","-66.9759","13.3","3.7","356","NA","B");
        shipStore.addShipPosition("210950000","31/12/2020 16:32","42.81133","-66.97587","13.4","10","356","NA","B");
        double expected = 10;
        double result = shipStore.maxCOG(ship);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void maxCOGEquals() throws ParseException {
        ShipStore shipStore=new ShipStore();
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        shipStore.saveShipInBST(ship);
        shipStore.addShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","0","358","NA","B");
        shipStore.addShipPosition("210950000","31/12/2020 16:20","42.7698","-66.9759","13.3","0","356","NA","B");
        shipStore.addShipPosition("210950000","31/12/2020 16:32","42.81133","-66.97587","13.4","0","356","NA","B");
        double expected = 0;
        double result = shipStore.maxCOG(ship);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void maxCOGWrong() throws ParseException {
        ShipStore shipStore=new ShipStore();
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        shipStore.saveShipInBST(ship);
        shipStore.addShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        shipStore.addShipPosition("210950000","31/12/2020 16:20","42.7698","-66.9759","13.3","3.7","356","NA","B");
        shipStore.addShipPosition("210950000","31/12/2020 16:32","42.81133","-66.97587","13.4","10","356","NA","B");
        double notExpected = -1;
        double result = shipStore.maxCOG(ship);
        Assertions.assertNotEquals(notExpected, result);
    }

    @Test
    void travelDistanceRight() throws ParseException {
        ShipStore shipStore = new ShipStore();
        List<ShipPosition> shipPositionList= new ArrayList<>();
        shipPositionList.add(new ShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B"));
        shipPositionList.add(new ShipPosition("210950000","31/12/2020 16:20","42.7698","-66.9759","13.3","3.7","356","NA","B"));
        shipPositionList.add(new ShipPosition("210950000","31/12/2020 16:32","42.81133","-66.97587","13.4","10","356","NA","B"));
        double expected = 21.58418263101136;
        double result= shipStore.travelDistance(shipPositionList);
        Assertions.assertEquals(expected,result);
    }

    @Test
    void travelDistanceWrong() throws ParseException {
        ShipStore shipStore = new ShipStore();
        List<ShipPosition> shipPositionList= new ArrayList<>();
        shipPositionList.add(new ShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B"));
        shipPositionList.add(new ShipPosition("210950000","31/12/2020 16:20","42.7698","-66.9759","13.3","3.7","356","NA","B"));
        shipPositionList.add(new ShipPosition("210950000","31/12/2020 16:32","42.81133","-66.97587","13.4","10","356","NA","B"));
        double expected = -21.58418263101136;
        double result= shipStore.travelDistance(shipPositionList);
        Assertions.assertNotEquals(expected,result);
    }

    @Test
    void getTotalMovementTimeRight() throws ParseException {
        ShipStore shipStore=new ShipStore();
        Ship ship=new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        shipStore.saveShipInBST(ship);
        shipStore.addShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        shipStore.addShipPosition("210950000","31/12/2020 16:20","42.7698","-66.9759","13.3","3.7","356","NA","B");
        shipStore.addShipPosition("210950000","31/12/2020 16:32","42.81133","-66.97587","13.4","10","356","NA","B");
        String mmsi=ship.getMmsi();
        String expected = "00h:43m:00s";
        String result = shipStore.getTotalMovementTime(mmsi);
        Assertions.assertEquals(expected,result);
    }

    @Test
    void getTotalMovementTimeWrong() throws ParseException {
        ShipStore shipStore=new ShipStore();
        Ship ship=new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        shipStore.saveShipInBST(ship);
        shipStore.addShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        shipStore.addShipPosition("210950000","31/12/2020 16:20","42.7698","-66.9759","13.3","3.7","356","NA","B");
        shipStore.addShipPosition("210950000","31/12/2020 16:32","42.81133","-66.97587","13.4","10","356","NA","B");
        String mmsi=ship.getMmsi();
        String expected = "894129h:23m:00s";
        String result = shipStore.getTotalMovementTime(mmsi);
        Assertions.assertNotEquals(expected,result);
    }

    @Test
    void pairOfShips() throws ParseException{
        Company company = new Company();
        ShipStore store = company.getshipStore();
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        store.saveShipInBST(ship);
        Ship ship1 = new Ship("228339600", "ALMAVIVA","IMO9450648","FLSU","70","334","42","15");
        store.saveShipInBST(ship1);
        store.addShipPosition("210950000","31/12/2020 17:03","25.92236","-25.97243","12.5","2.4","358","NA","B");
        store.addShipPosition("228339600","31/12/2020 17:03","20.90000","-21.0","12.5","2.4","358","NA","B");
        store.addShipPosition("210950000","31/12/2020 17:04","70.92236","-75.97243","12.5","2.4","358","NA","B");
        store.addShipPosition("228339600","31/12/2020 17:04","49.90000","-67.0","12.5","2.4","358","NA","B");
        List<Pair<Ship, Ship>> expected= new ArrayList<Pair<Ship, Ship>>();
        List<Pair<Ship, Ship>> pair= new ArrayList<Pair<Ship, Ship>>();
        pair=store.pairOfShips();
        assertEquals(pair, expected);
    }

    @Test
    void pairOfShips2() throws ParseException{
        Company company = new Company();
        ShipStore store = company.getshipStore();
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        store.saveShipInBST(ship);
        Ship ship1 = new Ship("228339600", "ALMAVIVA","IMO9450648","FLSU","70","334","42","15");
        store.saveShipInBST(ship1);
        store.addShipPosition("210950000","31/12/2020 17:03","20.92236","-20.97243","12.5","2.4","358","NA","B");
        store.addShipPosition("228339600","31/12/2020 17:03","25.90000","-29.97000","12.5","2.4","358","NA","B");
        store.addShipPosition("210950000","31/12/2020 17:04","42.92236","-69.97243","12.5","2.4","358","NA","B");
        store.addShipPosition("228339600","31/12/2020 17:04","48.90000","-79.00000","12.5","2.4","358","NA","B");
        List<Pair<Ship, Ship>> expected= new ArrayList<Pair<Ship, Ship>>();
        List<Pair<Ship, Ship>> pair= new ArrayList<Pair<Ship, Ship>>();
        pair=store.pairOfShips();
        assertEquals(pair, expected);
    }

    @Test
    void pairOfShips3() throws ParseException{
        Company company = new Company();
        ShipStore store = company.getshipStore();
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        store.saveShipInBST(ship);
        Ship ship1 = new Ship("228339600", "ALMAVIVA","IMO9450648","FLSU","70","334","42","15");
        store.saveShipInBST(ship1);
        store.addShipPosition("210950000","31/12/2020 17:03","20.92236","-20.97243","12.5","2.4","358","NA","B");
        store.addShipPosition("228339600","31/12/2020 17:03","20.92236","-20.97243","12.5","2.4","358","NA","B");
        store.addShipPosition("210950000","31/12/2020 17:04","42.92236","-69.97243","12.5","2.4","358","NA","B");
        store.addShipPosition("228339600","31/12/2020 17:04","42.92236","-69.97243","12.5","2.4","358","NA","B");
        List<Pair<Ship, Ship>> expected= new ArrayList<Pair<Ship, Ship>>();
        List<Pair<Ship, Ship>> pair= new ArrayList<Pair<Ship, Ship>>();
        double distance = store.travelDistance((List<ShipPosition>) ship.getSp().inOrder());
        double distance1 = store.travelDistance((List<ShipPosition>) ship1.getSp().inOrder());
        pair=store.pairOfShips();
        assertEquals(pair, expected);
    }

    @Test
    void pairOfShips4() throws ParseException{
        Company company = new Company();
        ShipStore store = company.getshipStore();
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        store.saveShipInBST(ship);
        Ship ship1 = new Ship("228339600", "ALMAVIVA","IMO9450648","FLSU","70","334","42","15");
        store.saveShipInBST(ship1);
        store.addShipPosition("210950000","31/12/2020 17:03","20.92236","-20.97243","12.5","2.4","358","NA","B");
        store.addShipPosition("228339600","31/12/2020 17:03","20.92236","-20.97243","12.5","2.4","358","NA","B");
        store.addShipPosition("210950000","31/12/2020 17:04","20.93236","-20.98243","12.5","2.4","358","NA","B");
        store.addShipPosition("228339600","31/12/2020 17:04","42.92236","-69.97243","12.5","2.4","358","NA","B");
        List<Pair<Ship, Ship>> expected= new ArrayList<Pair<Ship, Ship>>();
        List<Pair<Ship, Ship>> pair= new ArrayList<Pair<Ship, Ship>>();
        pair=store.pairOfShips();
        assertEquals(pair, expected);
    }

    @Test
    void pairOfShips5() throws ParseException{
        Company company = new Company();
        ShipStore store = company.getshipStore();
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        store.saveShipInBST(ship);
        Ship ship1 = new Ship("228339600", "ALMAVIVA","IMO9450648","FLSU","70","334","42","15");
        store.saveShipInBST(ship1);
        store.addShipPosition("210950000","31/12/2020 17:03","20.92236","-20.97243","12.5","2.4","358","NA","B");
        store.addShipPosition("228339600","31/12/2020 17:03","20.92236","-20.97243","12.5","2.4","358","NA","B");
        store.addShipPosition("210950000","31/12/2020 17:04","42.92236","-69.97243","12.5","2.4","358","NA","B");
        store.addShipPosition("228339600","31/12/2020 17:04","20.93236","-20.98243","12.5","2.4","358","NA","B");
        List<Pair<Ship, Ship>> expected= new ArrayList<Pair<Ship, Ship>>();
        List<Pair<Ship, Ship>> pair= new ArrayList<Pair<Ship, Ship>>();
        pair=store.pairOfShips();
        assertEquals(pair, expected);
    }

    @Test
    void meanCOGi0(){
        ShipStore shipStore=new ShipStore();
        Ship ship = new Ship("228339600", "ALMAVIVA","IMO9450648","FLSU","70","334","42","15");
        double expected=0;
        double result= shipStore.meanCOG(ship);
        Assertions.assertEquals(expected,result);
    }

    @Test
    void meanCOGmultiplication() throws ParseException {
        ShipStore shipStore=new ShipStore();
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        shipStore.saveShipInBST(ship);
        shipStore.addShipPosition("210950000","31/12/2020 17:03","20.92236","-20.97243","12.5","2.4","358","NA","B");
        shipStore.addShipPosition("210950000","31/12/2020 17:04","42.92236","-69.97243","12.5","2.4","358","NA","B");
        double notExpected = 9.6;
        double result = shipStore.meanCOG(ship);
        Assertions.assertNotEquals(notExpected,result);
    }

    @Test
    void meanCOGSubtraction() throws ParseException {
        ShipStore shipStore=new ShipStore();
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        shipStore.saveShipInBST(ship);
        shipStore.addShipPosition("210950000","31/12/2020 17:03","20.92236","-20.97243","12.5","2.4","358","NA","B");
        shipStore.addShipPosition("210950000","31/12/2020 17:04","42.92236","-69.97243","12.5","2.4","358","NA","B");
        double notExpected = 0;
        double result = shipStore.meanCOG(ship);
        Assertions.assertNotEquals(notExpected,result);
    }

    @Test
    void meanCOGSubtractionTotal() throws ParseException {
        ShipStore shipStore=new ShipStore();
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        shipStore.saveShipInBST(ship);
        shipStore.addShipPosition("210950000","31/12/2020 17:03","20.92236","-20.97243","12.5","2.4","358","NA","B");
        shipStore.addShipPosition("210950000","31/12/2020 17:04","42.92236","-69.97243","12.5","2.4","358","NA","B");
        double notExpected = -2.4;
        double result = shipStore.meanCOG(ship);
        Assertions.assertNotEquals(notExpected,result);
    }

    @Test
    void meanSOGi0(){
        ShipStore shipStore=new ShipStore();
        List<ShipPosition> shipPositionList = new ArrayList<>();
        double expected=0;
        double result= shipStore.meanSOG(shipPositionList);
        Assertions.assertEquals(expected,result);
    }

    @Test
    void meanSOGmultiplication() throws ParseException {
        ShipStore shipStore=new ShipStore();
        List<ShipPosition> shipPositionList = new ArrayList<>();
        shipPositionList.add(new ShipPosition("210950000","31/12/2020 17:03","20.92236","-20.97243","12.5","2.4","358","NA","B"));
        shipPositionList.add(new ShipPosition("210950000","31/12/2020 17:04","42.92236","-69.97243","12.5","2.4","358","NA","B"));
        double notExpected = 50.0;
        double result = shipStore.meanSOG(shipPositionList);
        Assertions.assertNotEquals(notExpected,result);
    }

    @Test
    void meanSOGSubtraction() throws ParseException {
        ShipStore shipStore=new ShipStore();
        List<ShipPosition> shipPositionList = new ArrayList<>();
        shipPositionList.add(new ShipPosition("210950000","31/12/2020 17:03","20.92236","-20.97243","12.5","2.4","358","NA","B"));
        shipPositionList.add(new ShipPosition("210950000","31/12/2020 17:04","42.92236","-69.97243","12.5","2.4","358","NA","B"));
        double notExpected = 0;
        double result = shipStore.meanSOG(shipPositionList);
        Assertions.assertNotEquals(notExpected,result);
    }

    @Test
    void meanSOGSubtractionTotal() throws ParseException {
        ShipStore shipStore=new ShipStore();
        List<ShipPosition> shipPositionList = new ArrayList<>();
        shipPositionList.add(new ShipPosition("210950000","31/12/2020 17:03","20.92236","-20.97243","12.5","2.4","358","NA","B"));
        shipPositionList.add(new ShipPosition("210950000","31/12/2020 17:04","42.92236","-69.97243","12.5","2.4","358","NA","B"));
        double notExpected = -12.5;
        double result = shipStore.meanSOG(shipPositionList);
        Assertions.assertNotEquals(notExpected,result);
    }

    @Test
    void maxSOGWrong2() throws ParseException {
        ShipStore shipStore=new ShipStore();
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        shipStore.saveShipInBST(ship);
        shipStore.addShipPosition("210950000","31/12/2020 17:03","20.92236","-20.97243","-1","2.4","358","NA","B");
        double expected = -1;
        double result = shipStore.maxSOG(ship);
        Assertions.assertEquals(expected,result);
    }

    @Test
    void maxCOGWrong2() throws ParseException{
        ShipStore shipStore=new ShipStore();
        Ship ship = new Ship("210950000","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        shipStore.saveShipInBST(ship);
        shipStore.addShipPosition("210950000","31/12/2020 17:03","20.92236","-20.97243","12.5","2.4","358","NA","B");
        shipStore.addShipPosition("210950000","31/12/2020 17:04","42.92236","-69.97243","12.5","2.4","358","NA","B");
        double notExpected = 2.4;
        double result = shipStore.maxCOG(ship);
        Assertions.assertEquals(notExpected,result);
    }

    @Test
    void read() throws IOException, ParseException {
        ShipStore shipStore = new ShipStore();
        File file = new File("sships.csv");
        shipStore.read(file);
        BSTMMSI bst = shipStore.getBstmmsi();
        Assertions.assertNotNull(bst);
    }

    @Test
    void createSummary() throws ParseException {
        ShipStore store = new ShipStore();
        Ship ship = new Ship("210950000","VARAMO","IMO9355044","C7SQ2","70","166","25","9.5");
        store.saveShipInBST(ship);
        ShipPosition shipPosition = new ShipPosition("210950000","31/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        ShipPosition shipPosition1 = new ShipPosition("210950000","30/12/2020 17:03","42.92236","-66.97243","12.5","2.4","358","NA","B");
        ship.getSp().insert(shipPosition);
        ship.getSp().insert(shipPosition1);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        Date date1 = format.parse("30/12/2020 17:03");
        Date date2 = format.parse("31/12/2020 17:03");
        String aux = store.milisecondsToString(date2.getTime()-date1.getTime());

        Summary expected = new Summary("210950000","VARAMO",date1,date2,aux,2,12.5,12.5,2.4,2.4,"42.92236",
                "-66.97243","42.92236","-66.97243",0.0,0.0);
        Summary result = store.createSummary(ship.getMmsi());
        Assert.assertEquals(expected,result);

        result = store.createSummary(ship.getImo());
        expected = new Summary("IMO9355044","VARAMO",date1,date2,aux,2,12.5,12.5,2.4,2.4,"42.92236",
                "-66.97243","42.92236","-66.97243",0.0,0.0);
        Assert.assertEquals(expected,result);

        expected = new Summary("C7SQ2","VARAMO",date1,date2,aux,2,12.5,12.5,2.4,2.4,"42.92236",
                "-66.97243","42.92236","-66.97243",0.0,0.0);
        result = store.createSummary(ship.getCallsign());
        Assert.assertEquals(expected,result);
    }

    @Test
    void createSummaryNull(){
        ShipStore shipStore = new ShipStore();
        assertNull(shipStore.createSummary("123456789"));
    }

}