package lapr.project.model;


import java.util.Objects;

public class SeaDistance implements Comparable<SeaDistance> {

    private final String fromCountry;
    private final String fromPortID;
    private final String fromPort;
    private final String toCountry;
    private final String toPortID;
    private final String toPort;
    private final String distance;

    public SeaDistance(String fromCountry, String fromPortId, String fromPort, String toCountry, String toPortId, String toPort, String seaDistance) {
        this.fromCountry = fromCountry;
        this.fromPortID = fromPortId;
        this.fromPort = fromPort;
        this.toCountry = toCountry;
        this.toPortID = toPortId;
        this.toPort = toPort;
        this.distance = seaDistance;
    }

    public String getFromPortID() {
        return fromPortID;
    }

    public String getToPortID() {
        return toPortID;
    }

    public String getFromCountry() {
        return fromCountry;
    }

    public String getFromPort() {
        return fromPort;
    }

    public String getSeaDistance() {
        return distance;
    }

    public String getToCountry() {
        return toCountry;
    }

    public String getToPort() {
        return toPort;
    }

    @Override
    public int compareTo(SeaDistance o) {
        int i=o.getSeaDistance().compareTo(this.getSeaDistance());
        if(i<0){
            return -1;
        }else if(i>0){
            return 1;
        }else{
            return 0;
        }
    }

}
