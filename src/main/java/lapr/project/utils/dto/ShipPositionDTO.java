package lapr.project.utils.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * ShipPositionDTO class, which is responsible for converting a ShipPosition in a ShipPositionDTO.
 *
 * @author Carlos Rodrigues
 */
public class ShipPositionDTO {

    /**
     * The shipPositionDTO's mmsi.
     */
    private final String mmsi;

    /**
     * The shipPositionDTO's date.
     */
    private final Date date;

    /**
     * The shipPositionDTO's lat.
     */
    private final String lat;

    /**
     * The shipPositionDTO's lon.
     */
    private final String lon;

    /**
     * The shipPositionDTO's sog.
     */
    private final String sog;

    /**
     * The shipPositionDTO's cog.
     */
    private final String cog;

    /**
     * The shipPositionDTO's heading.
     */
    private final String heading;

    /**
     * The shipPositionDTO's cargo.
     */
    private final String cargo;

    /**
     * The shipPositionDTO's transceiver.
     */
    private final String transciever;

    /**
     * Builds the ShipDTO object.
     *
     * @param mmsi the shipPositionDTO mmsi
     * @param date the shipPositionDTO date
     * @param lat the shipPositionDTO lat
     * @param lon the shipPositionDTO lon
     * @param sog the shipPositionDTO sog
     * @param cog the shipPositionDTO cog
     * @param heading the shipPositionDTO heading
     * @param cargo the shipPositionDTO cargo
     * @param transciever the shipPositionDTO transceiver
     *
     * @throws ParseException
     */
    public ShipPositionDTO(String mmsi, String date, String lat, String lon, String sog, String cog, String heading, String cargo, String transciever) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        this.mmsi = mmsi;
        this.date = format.parse(date);
        this.lat = lat;
        this.lon = lon;
        this.sog = sog;
        this.cog = cog;
        this.heading = heading;
        this.cargo = cargo;
        this.transciever = transciever;
    }

    /**
     * Builds the ShipDTO object.
     *
     * @param mmsi the shipPositionDTO mmsi
     * @param date the shipPositionDTO date
     * @param lat the shipPositionDTO lat
     * @param lon the shipPositionDTO lon
     * @param sog the shipPositionDTO sog
     * @param cog the shipPositionDTO cog
     * @param heading the shipPositionDTO heading
     * @param cargo the shipPositionDTO cargo
     * @param transciever the shipPositionDTO transceiver
     */
    public ShipPositionDTO(String mmsi, Date date, String lat, String lon, String sog, String cog, String heading, String cargo, String transciever) {
        this.mmsi = mmsi;
        this.date = date;
        this.lat = lat;
        this.lon = lon;
        this.sog = sog;
        this.cog = cog;
        this.heading = heading;
        this.cargo = cargo;
        this.transciever = transciever;
    }

    /**
     * Get's the Ship Position DTO's mmsi.
     *
     * @return Ship Position DTO's mmsi
     */
    public String getMMSI() {
        return mmsi;
    }

    /**
     * Get's the Ship Position DTO's date.
     *
     * @return Ship Position DTO's date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Get's the Ship Position DTO's lat.
     *
     * @return Ship Position DTO's lat
     */
    public String getLAT() {
        return lat;
    }

    /**
     * Get's the Ship Position DTO's lon.
     *
     * @return Ship Position DTO's lon
     */
    public String getLON() {
        return lon;
    }

    /**
     * Get's the Ship Position DTO's sog.
     *
     * @return Ship Position DTO's sog
     */
    public String getSOG() {
        return sog;
    }

    /**
     * Get's the Ship Position DTO's cog.
     *
     * @return Ship Position DTO's cog
     */
    public String getCOG() {
        return cog;
    }

    /**
     * Get's the Ship Position DTO's heading.
     *
     * @return Ship Position DTO's heading
     */
    public String getHeading() {
        return heading;
    }

    /**
     * Get's the Ship Position DTO's cargo.
     *
     * @return Ship Position DTO's cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Get's the Ship Position DTO's transceiver.
     *
     * @return Ship Position DTO's transceiver
     */
    public String getTransciever() {
        return transciever;
    }

    /**
     * Return a textual representation of the object, which contains all of its attributes.
     *
     * @return shipPositionDTO characteristics
     */
    @Override
    public String toString() {
        return "ShipPositionDTO{" +
                "MMSI='" + mmsi + '\'' +
                ", date=" + date +
                '}';
    }

    /**
     * Verifies if there is an object equals to the @param object.
     *
     * @param o
     *
     * @return true if it founds an object equals to the @param.
     * @return false if there is not any object equals to the @param.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShipPositionDTO that = (ShipPositionDTO) o;
        return Objects.equals(mmsi, that.mmsi) && Objects.equals(date, that.date) ;
    }

}
