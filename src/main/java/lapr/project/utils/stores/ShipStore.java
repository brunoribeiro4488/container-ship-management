package lapr.project.utils.stores;

import lapr.project.data.ConnectionFactory;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.ShipStoreDB;
import lapr.project.utils.bst.BSTCallSign;
import lapr.project.utils.bst.BSTIMO;
import lapr.project.utils.bst.BSTMMSI;
import lapr.project.model.Ship;
import lapr.project.model.ShipPosition;
import lapr.project.model.Summary;
import lapr.project.utils.Pair;


import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class Ship Store.
 */
public class ShipStore {

    private static final String MMSI = "123456789";
    private static final String LON = "-24.434";
    private static final String LAT = "1.345";
    private static final String FORMAT = "%s/%s/%s %s:%s";

    /**
     * The ship store's BSTIMO.
     */
    private final BSTIMO bstimo;

    /**
     * The ship store's BSTMMSI.
     */
    private final BSTMMSI bstmmsi;

    /**
     * The ship store's BSTCallSign.
     */
    private final BSTCallSign bstcallsign;


    /**
     * ShipStore constructor
     */
    public ShipStore(){
        bstcallsign = new BSTCallSign();
        bstimo=new BSTIMO();
        bstmmsi=new BSTMMSI();
    }

    /**
     * It saves the ship in all 3 BST's
     * @param s An object of the class Ship
     */
    public void saveShipInBST(Ship s){
        bstmmsi.insert(s);
        bstcallsign.insert(s);
        bstimo.insert(s);
    }

    /**
     * It saves a list of ship in all 3 BST's
     * @param s An object of the class Ship
     */
    public void saveListShipInBST(List<Ship> s){
        for(Ship ship:s) {
            bstmmsi.insert(ship);
            bstcallsign.insert(ship);
            bstimo.insert(ship);
        }
    }

    /**
     * Creates a ship
     *
     * @param mmsi Ship's MMSI
     * @param name Ship's name
     * @param imo Ship's IMO
     * @param callsign Ship's CallSign
     * @param type Ship's Vessel Type
     * @param length Ship's length
     * @param width Ship's width
     * @param draft Ship's draft
     * @return A Ship
     */
    public Ship createShip(String mmsi, String name, String imo, String callsign, String type, String length, String width, String draft){
        return new Ship(mmsi,name,imo,callsign,type,length,width,draft);
    }

    /**
     * It gets the Ship's MMSI
     * @param mmsi Ship's MMSI
     * @return Ship's MMSI
     */
    public Ship getShipByMMSI(String mmsi){
        return bstmmsi.find(mmsi);
    }

    /**
     * It gets the Ship's IMO
     * @param imo Ship's IMO
     * @return Ship's IMO
     */
    public Ship getShipByIMO(String imo){
        return bstimo.find(imo);
    }

    /**
     * IT gets Ship's CallSign
     * @param callSign Ship's CallSign
     * @return Ship's CallSign
     */
    public Ship getShipByCallSign(String callSign){
        return bstcallsign.find(callSign);
    }


    /**
     * It saves a ShipPositon
     * @param mmsi Ship's MMSI
     * @param date ShipPosition's Date
     * @param lat ShipPosition's Latitude
     * @param lon ShipPosition's longitude
     * @param sog Ship's Speed
     * @param cog Ship's Course over Ground
     * @param heading Ship's Heading
     * @param cargo Ship's Catgo
     * @param transciever Ship's Transciever
     * @throws ParseException
     */
    public void addShipPosition(String mmsi, String date, String lat, String lon, String sog, String cog, String heading, String cargo, String transciever) throws ParseException {
        ShipPosition sp = new ShipPosition(mmsi,date,lat,lon,sog,cog,heading,cargo,transciever);
        getShipByMMSI(mmsi).getSp().insert(sp);
    }

    /**
     * Get's the initial date in the ship positions.
     * @param mmsi
     * @return
     */
    public Date getStarBaseDateTime(String mmsi){
        Ship ship = getShipByMMSI(mmsi);
        return ship.getSp().smallestElement().getDate();
    }

    /**
     * Get's the final date in the ship positions.
     * @param mmsi
     * @return
     */
    public Date getEndBaseDateTime(String mmsi){
        Ship ship = getShipByMMSI(mmsi);
        return ship.getSp().biggestElement().getDate();
    }

    /**
     * Get's the ship store's BSTMMSI
     *
     * @return BSTMMSI
     */
    public BSTMMSI getBstmmsi() {
        return bstmmsi;
    }

    /**
     * Get's the total movement time of a ship.
     * @param mmsi
     * @return
     */
    public String getTotalMovementTime(String mmsi){
        double movement = getEndBaseDateTime(mmsi).getTime() - getStarBaseDateTime(mmsi).getTime();
        return milisecondsToString(movement);
    }

    /**
     * Get's the number of movements of a ship
     * @param mmsi
     * @return
     */
    public int getNumberOfMovements(String mmsi){
        return getShipByMMSI(mmsi).getSp().size();
    }

    /**
     * Creates the summary of a ship
     * @param shipCode
     * @return
     */
    public Summary createSummary(String shipCode) {
        Ship ship;
        if (shipCode.length() == 9) {
            ship = getShipByMMSI(shipCode);
        } else if (shipCode.contains("imo") || shipCode.contains("IMO")) {
            ship = getShipByIMO(shipCode);
        } else {
            ship = getShipByCallSign(shipCode);
        }
        if (ship == null) {
            return null;
        }
        try {
            Date startBaseDateTime = getStarBaseDateTime(ship.getMmsi());
            Date endBaseDateTime = getEndBaseDateTime(ship.getMmsi());
            String totalMovementTime = getTotalMovementTime(ship.getMmsi());
            int totalMovements = getNumberOfMovements(ship.getMmsi());
            double maxSog = maxSOG(ship);
            double meanSog = meanSOG((List<ShipPosition>) ship.getSp().inOrder());
            double maxCog = maxCOG(ship);
            double meanCog = meanCOG(ship);
            String depLat = ship.getSp().smallestElement().getLat();
            String depLog = ship.getSp().smallestElement().getLon();
            String arrivalLat = ship.getSp().biggestElement().getLat();
            String arrivalLog = ship.getSp().biggestElement().getLon();
            double travelDistance = travelDistance((List<ShipPosition>) ship.getSp().inOrder());
            double deltaDistance = getDistanceBetweenTwoPoints(depLat, depLog, arrivalLat, arrivalLog);

            return new Summary(shipCode, ship.getName(), startBaseDateTime, endBaseDateTime, totalMovementTime, totalMovements, maxSog, meanSog, maxCog, meanCog, depLat, depLog, arrivalLat, arrivalLog, travelDistance, deltaDistance);

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Get's the max SOG in all ship positions of a ship
     * @param ship
     * @return
     */
    public double maxSOG(Ship ship){
        double max = -1;
        for(ShipPosition sp : ship.getSp().inOrder()){
            if(Double.parseDouble(sp.getSog())>max){
                max = Double.parseDouble(sp.getSog());
            }
        }
        return max;
    }

    /**
     * Get's the mean SOG in a list of ship positions
     * @param shipPositionList
     * @return
     */
    public double meanSOG(List<ShipPosition> shipPositionList){
        double total = 0;
        int i = 0;
        for(ShipPosition sp : shipPositionList){
            total += Double.parseDouble(sp.getSog());
            i++;
        }
        if(i>0)
            return (total/i);
        else
            return 0;
    }

    /**
     * Get's the max COG in all ship positions of a ship
     * @param ship
     * @return
     */
    public double maxCOG(Ship ship){
        double max = -1;
        for(ShipPosition sp : ship.getSp().inOrder()){
            if(Double.parseDouble(sp.getCog())>max){
                max = Double.parseDouble(sp.getCog());
            }
        }
        return max;
    }

    /**
     * Get's the mean COG in all ship positions of a ship
     * @param ship
     * @return
     */
    public double meanCOG(Ship ship){
        double total = 0;
        int i = 0;
        for(ShipPosition sp : ship.getSp().inOrder()){
            total += Double.parseDouble(sp.getCog());
            i++;
        }
        if(i>0)
            return (total/i);
        else
            return 0;
    }

    /**
     * Converts miliseconds to a string
     * @param time
     * @return
     */
    public String milisecondsToString(double time){
        long segundos =(long) time / 1000;
        long minutos = segundos / 60;
        segundos = segundos % 60;
        long horas = minutos / 60;
        minutos = minutos % 60;
        return String.format ("%02dh:%02dm:%02ds", horas, minutos, segundos);
    }

    /**
     * Get's the distance between two points
     *
     * @param lat1
     * @param log1
     * @param lat2
     * @param log2
     * @return
     */
    public double getDistanceBetweenTwoPoints(String lat1, String log1, String lat2, String log2){
        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(Double.parseDouble(lat2) - Double.parseDouble(lat1));
        double lonDistance = Math.toRadians(Double.parseDouble(log2) - Double.parseDouble(log1));
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(Double.parseDouble(lat1))) * Math.cos(Math.toRadians(Double.parseDouble(lat2)))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;


        distance = Math.pow(distance, 2);

        return (Math.sqrt(distance));
    }

    /**
     * Get's the travel distance of a ship
     * @param shipPosition
     * @return
     */
    public double travelDistance(List<ShipPosition> shipPosition){
        double distance = 0;
        int i=1;
        for(ShipPosition sp : shipPosition){
            if(i<shipPosition.size()){
                distance += getDistanceBetweenTwoPoints(sp.getLat(), sp.getLon(), shipPosition.get(i).getLat(), shipPosition.get(i).getLon());
            }
        }
        return distance;
    }

    /**
     * Get's the messages organized of a ship in a certain date
     * @param date
     * @return
     * @throws ParseException
     */
    public List<ShipPosition> getMessagesOrganized(Date date) throws ParseException {
        List<ShipPosition> shipPositionList = new ArrayList<>();
        ShipPosition firstposition = new ShipPosition(MMSI,String.format(FORMAT,date.getDate(),date.getMonth()+1,date.getYear()+1900,date.getHours(),date.getMinutes()),LAT,LON,"23","4","34","NA","B");
        date.setHours(date.getHours()+24);
        ShipPosition lastposition = new ShipPosition(MMSI,String.format(FORMAT,date.getDate(),date.getMonth()+1,date.getYear()+1900,date.getHours(),date.getMinutes()),LAT,LON,"23","4","34","NA","B");
        List<Ship> shipList = (List<Ship>) getBstmmsi().inOrder();
        for (Ship ship: shipList) {
            shipPositionList.addAll((Collection<? extends ShipPosition>) ship.getSp().findListInAnInterval(firstposition,lastposition));
        }
        return shipPositionList;
    }

    /**
     * Get's the messages organized of a ship in a certain interval
     * @param firstdate
     * @param lastdate
     * @return
     * @throws ParseException
     */
    public List<ShipPosition> getMessagesOrganized(Date firstdate, Date lastdate) throws ParseException {
        List<ShipPosition> shipPositionList = new ArrayList<>();
        ShipPosition firstposition = new ShipPosition(MMSI,String.format(FORMAT,firstdate.getDate(),firstdate.getMonth()+1,firstdate.getYear()+1900,firstdate.getHours(),firstdate.getMinutes()),LAT,LON,"23","4","34","NA","B");
        ShipPosition lastposition = new ShipPosition(MMSI,String.format(FORMAT,lastdate.getDate(),lastdate.getMonth()+1,lastdate.getYear()+1900,lastdate.getHours(),lastdate.getMinutes()),LAT,LON,"23","4","34","NA","B");
        List<Ship> shipList = (List<Ship>) getBstmmsi().inOrder();
        for (Ship ship: shipList) {
            shipPositionList.addAll((Collection<? extends ShipPosition>) ship.getSp().findListInAnInterval(firstposition,lastposition));
        }
        return shipPositionList;
    }


    /**
     * Get's the top n of ships with bigger travel distance
     * @param firstDateAndTime
     * @param lastDateAndTime
     * @param numberofships
     * @return
     * @throws ParseException
     */
    public String getTopShipsWithTravelledDistance(Date firstDateAndTime, Date lastDateAndTime,int numberofships) throws ParseException {

        Map<String,List<Ship>> map = populateMap();

        ShipPosition firstposition = new ShipPosition(MMSI,String.format(FORMAT,firstDateAndTime.getDate(),firstDateAndTime.getMonth()+1,firstDateAndTime.getYear()+1900,firstDateAndTime.getHours(),firstDateAndTime.getMinutes()),LAT,LON,"23","4","34","NA","B");
        ShipPosition lastposition = new ShipPosition(MMSI,String.format(FORMAT,lastDateAndTime.getDate(),lastDateAndTime.getMonth()+1,lastDateAndTime.getYear()+1900,lastDateAndTime.getHours(),lastDateAndTime.getMinutes()),LAT,LON,"23","4","34","NA","B");
        for (String key:map.keySet()) {
            List<Ship>list = map.get(key);
            list.sort(new Comparator<Ship>() {
                @Override
                public int compare(Ship o1, Ship o2) {
                    double num = travelDistance((List<ShipPosition>) o1.getSp().findListInAnInterval(firstposition, lastposition)) - travelDistance((List<ShipPosition>) o2.getSp().findListInAnInterval(firstposition, lastposition));
                    if (num < 0) {
                        return 1;
                    } else if (num > 0) {
                        return -1;
                    } else return 0;
                }

            });
        }


        StringBuilder out = new StringBuilder();
        for (String key:map.keySet()) {
            out.append(String.format("%s: %n", key));
            List<Ship> list = map.get(key);
            for (int i = 0 ;i<numberofships && i< list.size();i++) {
                Ship ship = list.get(i);
                out.append(String.format(" %s ; %s  ; %s  ; %s%n", ship.getMmsi(),ship.getType(), String.format("%f km",travelDistance((List<ShipPosition>) ship.getSp().findListInAnInterval(firstposition,lastposition))) ,String.format("%f", meanSOG((List<ShipPosition>) ship.getSp().findListInAnInterval(firstposition,lastposition)))));
            }
            if (list.size() <numberofships){
                out.append("Não existem navios suficientes para satisfazer sua preferência%n");
            }
            out.append("\n----------------------------------------------------------------\n\n");
        }
        return out.toString();
    }

    /**
     * Populates a map by the vessel types
     * @return
     */
    private Map<String,List<Ship>> populateMap(){
        Map<String,List<Ship>> map = new HashMap<>();
        List<Ship> shipList = (List<Ship>) getBstmmsi().inOrder();
        for (Ship ship : shipList) {
            String type = ship.getType();
            if (map.get(type) ==null){
                map.put(type,new ArrayList<>());
                map.get(type).add(ship);
            }else {
                List<Ship> list2 =  map.get(type);
                list2.add(ship);
            }
        }
        return map;
    }

    /**
     * reads all the information of a file and save it in bst's.
     *
     * @param file
     * @throws IOException
     * @throws ParseException
     */
    public void read(File file) throws IOException, ParseException {
        BufferedReader br = null;
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = ConnectionFactory.getInstance()
                    .getDatabaseConnection();
        } catch (IOException exception) {
            Logger.getLogger(ShipStoreDB.class.getName())
                    .log(Level.SEVERE, null, exception);
        }
        try{
            br = new BufferedReader(new FileReader(file));
            int i=0;
            String line = "";
            String delimiter = ",";
            Ship ship=null;
            String[] tempArr;
            line = br.readLine();
            tempArr = line.split(delimiter);

            while((line = br.readLine()) != null) {
                try {
                    i++;
                    tempArr = line.split(delimiter);
                    if (getShipByMMSI(tempArr[0]) == null) {
                        ship = createShip(tempArr[0], tempArr[7], tempArr[8], tempArr[9], tempArr[10], tempArr[11], tempArr[12], tempArr[13]);
                        saveShipInBST(ship);
                    }
                    addShipPosition(tempArr[0], tempArr[1], tempArr[2], tempArr[3], tempArr[4], tempArr[5], tempArr[6], tempArr[14], tempArr[15]);
                }catch(IllegalArgumentException ignored){
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("There is no file with that name");
        }finally{
            br.close();
        }

    }

    /**
     * Creates the pair of ships.
     * @return
     */
    public List<Pair<Ship,Ship>> pairOfShips(){
        double distance,distance2,traveledDistance,dif,dif1;
        List<Pair<Ship,Ship>> pair = new ArrayList<>();
        List<Double> travel = new ArrayList<>();
        BSTMMSI bst = getBstmmsi();
        List<Ship> ships = (List<Ship>) bst.inOrder(); //O(n)
        for(int i=0;i<ships.size();i++){
            distance = travelDistance((List<ShipPosition>) ships.get(i).getSp().inOrder());
            for(int x=1+i;x<ships.size();x++){
                try {
                    distance2 = travelDistance((List<ShipPosition>) ships.get(x).getSp().inOrder());
                    dif = getDistanceBetweenTwoPoints(ships.get(i).getSp().biggestElement().getLat(), ships.get(i).getSp().biggestElement().getLon(), ships.get(x).getSp().biggestElement().getLat(), ships.get(x).getSp().biggestElement().getLon());
                    dif1 = getDistanceBetweenTwoPoints(ships.get(i).getSp().smallestElement().getLat(), ships.get(i).getSp().smallestElement().getLon(), ships.get(x).getSp().smallestElement().getLat(), ships.get(x).getSp().smallestElement().getLon());
                    traveledDistance = distance - distance2;
                    if (distance > 10 && distance2 > 10 && traveledDistance != 0) {
                        if (dif < 5 || dif1 < 5) {
                            travel.add(traveledDistance);
                            pair.add(new Pair<>(ships.get(i), ships.get(x)));
                        }
                    }
                }catch (NullPointerException ignored){
                }
            }
        }
        pair=orderList(pair,travel);
        return pair;
    }

    /**
     * Orders the list of pairs by its travel distance
     * @param list
     * @param travel
     * @return
     */
    private List<Pair<Ship,Ship>> orderList(List<Pair<Ship,Ship>> list, List<Double> travel) {
        double var;
        Pair<Ship,Ship> par;
        for(int i=0;i<travel.size();i++){
            for(int x=i+1;x<travel.size();x++) {
                if (travel.get(i) < travel.get(x)) {
                    var=travel.get(i);
                    travel.set(i,travel.get(x));
                    travel.set(x,var);
                    par=list.get(i);
                    list.set(i,list.get(x));
                    list.set(x,par);
                }
            }
        }
        return list;
    }

    /**
     * Get's all ships information of travelled distance
     * @return
     */
    public String getAllShipsInformationTravelledDistance(){
        String string= "";
        List<Ship> ships = (List<Ship>) bstmmsi.inOrder();
        ships.sort(new Comparator<Ship>() {
            @Override
            public int compare(Ship o1, Ship o2) {
                double num = travelDistance((List<ShipPosition>) o1.getSp().inOrder()) - travelDistance((List<ShipPosition>)o2.getSp().inOrder());
                if (num < 0) {
                    return 1;
                } else if (num > 0) {
                    return -1;
                } else return 0;
            }
        });
        string += getAllShipsInformation(ships, string);

        return string;
    }

    /**
     * Get's all ships information of number of movements
     * @return
     */
    public String getAllShipsInformationNumberMovements(){
        String string ="";
        List<Ship> ships2 = (List<Ship>) bstmmsi.inOrder();
        ships2.sort(new Comparator<Ship>() {
            @Override
            public int compare(Ship o1, Ship o2) {
                int num = getNumberOfMovements(o1.getMmsi()) - getNumberOfMovements(o2.getMmsi());
                if (num < 0) {
                    return -1;
                } else if (num > 0) {
                    return 1;
                } else return 0;
            }
        });
        string += getAllShipsInformation(ships2, string);
        return string;
    }


    /**
     * Get's all ship information
     * @param ships
     * @param string
     * @return
     */
    public String getAllShipsInformation(List<Ship> ships, String string){
        if(bstmmsi.size() == 0){
            throw new NullPointerException("There are no ships");
        }
        for (Object ship: ships){
            Ship s = (Ship) ship;
            if(s.getSp().size()!=0) {
                String mmsi = s.getMmsi();
                int totalMovements = getNumberOfMovements(mmsi);
                double travelDistance = travelDistance((List<ShipPosition>) s.getSp().inOrder());
                double deltaDistance = getDistanceBetweenTwoPoints(s.getSp().smallestElement().getLat(), s.getSp().smallestElement().getLon(), s.getSp().biggestElement().getLat(), s.getSp().biggestElement().getLon());
                string += String.format("MMSI = %s\nTotal Movements = %d\nTravelled Distance = %.2f\nDelta Distance = %.2f\n\n----------------------------------------------------------------\n\n", mmsi, totalMovements, travelDistance, deltaDistance);
            }
        }
        return string;
    }

    /**
     * Get's a ship position in a specific date
     * @param s
     * @param dateTime
     * @return
     * @throws ParseException
     */
    public ShipPosition getShipPositionByDate(Ship s, String dateTime) throws ParseException {
        return s.getSp().find(new ShipPosition(MMSI,dateTime,LAT,LON,"23","4","34","NA","B"));
    }



}
