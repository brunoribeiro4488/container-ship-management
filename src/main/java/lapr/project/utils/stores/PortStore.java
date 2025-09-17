package lapr.project.utils.stores;

import lapr.project.data.ConnectionFactory;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.PortStoreDB;
import lapr.project.data.ShipStoreDB;
import lapr.project.model.Port;
import lapr.project.utils.bst.KD_Tree;
import lapr.project.utils.bst.KD_Tree_Port;

import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class PortStore
 */
public class PortStore {

    /**
     * The port store KD Tree
     */
    private KD_Tree_Port kdt;
    /**
     * PortStore constructor
     */
    public PortStore(){
        kdt = new KD_Tree_Port();
    }

    /**
     * Creates a Port
     * @param id The port's Identification.
     * @param name The port's Name.
     * @param continent The port's Continent.
     * @param country The port's Country.
     * @param latitude The port's Latitude.
     * @param longitude The port's Longitude.
     * @return The created Port
     */
    public Port createPort(String id, String name, String continent, String country, String latitude, String longitude){
        return new Port(id,name,continent,country,latitude,longitude);
    }

    /**
     * Gets a Port by position
     * @param latitude Port's latitude
     * @param longitude Port's longitude
     * @return
     */
    public Port getPortByPosition(String latitude,String longitude){
        return (Port) kdt.find(Double.parseDouble(latitude),Double.parseDouble(longitude));
    }

    /**
     * Saves a port in the KD Tree
     * @param port Receives a port
     * @return
     */
    public void savePortinKdt(Port port){
        kdt.insert(Double.parseDouble(port.getLatitude()),Double.parseDouble(port.getLongitude()),port);
    }

    /**
     * Saves all ports in a list in the KD Tree
     * @param portList Receives a list of ports
     * @return
     */
    public void saveListPortinKdt(List<Port> portList){
        kdt.insertAllPorts(portList);
    }


    public KD_Tree<Port> getKdt() {
        return kdt;
    }

    /**
     * reads all the information of a file and save it in kdt's.
     *
     * @param file
     * @throws IOException
     */
    public void read(File file) throws IOException {
        BufferedReader br = null;
        PortStoreDB db = new PortStoreDB();
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
            Port port;
            String[] tempArr;
            line = br.readLine();
            tempArr = line.split(delimiter);

            while((line = br.readLine()) != null) {
                try {
                    i++;
                    tempArr = line.split(delimiter);
                    if (getPortByPosition(tempArr[4],tempArr[5]) == null) {
                        port = createPort(tempArr[2], tempArr[3], tempArr[0], tempArr[1], tempArr[4], tempArr[5]);
                        savePortinKdt(port);
                        db.save(databaseConnection,port);
                    }
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
     * Gets the closest port to a ship in a certain date
     * @param shipPositionLat
     * @param shipPositionLon
     * @return
     */
    public Port getClosestPort(String shipPositionLat, String shipPositionLon){
        return (Port) kdt.findNearestNeighbour(Double.parseDouble(shipPositionLat), Double.parseDouble(shipPositionLon));
    }

    public void setKdt(KD_Tree_Port kdtp){
        this.kdt = kdtp;
    }
}
