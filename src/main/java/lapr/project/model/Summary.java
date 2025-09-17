package lapr.project.model;

import java.util.Date;
import java.util.Objects;

/**
 * Class Summary.
 *
 * @author Bruno Ribeiro
 */
public class Summary {

    /**
     * The summary's ship code.
     */
    private final String shipCode;

    /**
     * The summary's vessel name.
     */
    private final String vesselName;

    /**
     * The summary's start base date time.
     */
    private final Date startBaseDateTime;

    /**
     * The summary's end base date time.
     */
    private final Date endBaseDateTime;

    /**
     * The summary's total movement time.
     */
    private final String totalMovementTime;

    /**
     * The summary's total movements.
     */
    private final int totalMovements;

    /**
     * The summary's max sog.
     */
    private final double maxSOG;

    /**
     * The summary's mean sog.
     */
    private final double meanSOG;

    /**
     * The summary's max cog.
     */
    private final double maxCOG;

    /**
     * The summary's mean cog.
     */
    private final double meanCOG;

    /**
     * The summary's departure lat.
     */
    private final String depLat;

    /**
     * The summary's departure lon.
     */
    private final String depLong;

    /**
     * The summary's arrival lat.
     */
    private final String arrivalLat;

    /**
     * The summary's arrival lon.
     */
    private final String arrivalLong;

    /**
     * The summary's travel distance.
     */
    private final double travelDistance;

    /**
     * The summary's delta distance.
     */
    private final double deltaDistance;

    /**
     * Builds a Summary receiving all its parameters.
     *
     * @param shipCode summary's ship code
     * @param vesselName summary's vessel name
     * @param startBaseDateTime summary's start base date time
     * @param endBaseDateTime summary's end base date time
     * @param totalMovementTime summary's total movement time
     * @param totalMovements summary's total movements
     * @param maxSOG summary's max sog
     * @param meanSOG summary's mean sog
     * @param maxCOG summary's max cog
     * @param meanCOG summary's mean cog
     * @param depLat summary's departure lat
     * @param depLong summary's departure lon
     * @param arrivalLat summary's arrival lat
     * @param arrivalLong summary's arrival lon
     * @param travelDistance summary's travel distance
     * @param deltaDistance summary's delta distance
     */
    public Summary(String shipCode, String vesselName, Date startBaseDateTime, Date endBaseDateTime, String totalMovementTime, int totalMovements, double maxSOG, double meanSOG, double maxCOG, double meanCOG, String depLat, String depLong, String arrivalLat, String arrivalLong, double travelDistance, double deltaDistance) {
        this.shipCode = shipCode;
        this.vesselName = vesselName;
        this.startBaseDateTime = startBaseDateTime;
        this.endBaseDateTime = endBaseDateTime;
        this.totalMovementTime = totalMovementTime;
        this.totalMovements = totalMovements;
        this.maxSOG = maxSOG;
        this.meanSOG = meanSOG;
        this.maxCOG = maxCOG;
        this.meanCOG = meanCOG;
        this.depLat = depLat;
        this.depLong = depLong;
        this.arrivalLat = arrivalLat;
        this.arrivalLong = arrivalLong;
        this.travelDistance = travelDistance;
        this.deltaDistance = deltaDistance;
    }

    /**
     * Returns the textual description of the summary.
     *
     * @return summary characteristics.
     */
    @Override
    public String toString() {
        return String.format("Summary of Ship %s%n%nShip Code = %s%nVessel Name = %s%nStart Base Date Time = %s%nEnd Base Date Time = %s%nTotal Movement Time = %s%nTotal Movements = %d%nMax SOG = %f%nMean SOG = %.2f%nMax COG = %f%nMean COG = %.2f%nDeparture Latitude = %s%nDeparture Longitude = %s%nArrival Latitude = %s%nArrival Longitude = %s%nTravelled Distance = %f Km%nDelta Distance = %f Km", vesselName, shipCode, vesselName, startBaseDateTime, endBaseDateTime, totalMovementTime, totalMovements, maxSOG, meanSOG, maxCOG, meanCOG,depLat, depLong, arrivalLat, arrivalLong, travelDistance, deltaDistance);
    }

    /**
     * Get's the Summary's vessel name.
     *
     * @return Summary's vessel name
     */
    public String getVesselName(){
        return vesselName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Summary summary = (Summary) o;
        return Objects.equals(shipCode, summary.shipCode);
    }

}
