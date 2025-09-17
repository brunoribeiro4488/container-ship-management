package lapr.project.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Class Ship Position.
 */
public class ShipPosition implements Comparable<ShipPosition> {

    /**
     * The ship position's mmsi.
     */
    private final String mmsi;

    /**
     * The ship position's date.
     */
    private final Date date;

    /**
     * The ship position's lat.
     */
    private final String lat;

    /**
     * The ship position's lon.
     */
    private final String lon;

    /**
     * The ship position's sog.
     */
    private final String sog;

    /**
     * The ship position's cog.
     */
    private final String cog;

    /**
     * The ship position's heading.
     */
    private final String heading;

    /**
     * The ship position's cargo.
     */
    private final String cargo;

    /**
     * The ship position's transceiver.
     */
    private final String transciever;

    /**
     * Builds a Ship Position receiving all its parameters.
     *
     * @param mmsi ship position's mmsi
     * @param date ship position's date
     * @param lat ship position's lat
     * @param lon ship position's lon
     * @param sog ship position's sog
     * @param cog ship position's cog
     * @param heading ship position's heading
     * @param cargo ship position's cargo
     * @param transciever ship position's transceiver
     *
     * @throws ParseException
     */
    public ShipPosition(String mmsi, String date, String lat, String lon, String sog, String cog, String heading, String cargo, String transciever) throws ParseException {
        Date date1;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        latSignValidation(lat);
        lonValidation(lon);
        sogValidation(sog);
        cogValidation(cog);
        headingValidation(heading);
        cargoValidation(cargo);
        transcieverValidation(transciever);
        this.mmsi = mmsi;
        try {
            date1 = format.parse(date);
        }catch (ParseException ignored){
            format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            date1=format.parse(date);
        }
        this.date = date1;
        this.lat = lat;
        this.lon = lon;
        this.sog = sog;
        this.cog = cog;
        this.heading = heading;
        this.cargo = cargo;
        this.transciever = transciever;
    }

    /**
     * Get's the Ship Position's date.
     *
     * @return Ship's date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Get's the Ship Position's mmsi.
     *
     * @return Ship's mmsi
     */
    public String getMmsi() {
        return mmsi;
    }

    /**
     * Get's the Ship Position's lat.
     *
     * @return Ship's lat
     */
    public String getLat() {
        return lat;
    }

    /**
     * Get's the Ship Position's lon.
     *
     * @return Ship's lon
     */
    public String getLon() {
        return lon;
    }

    /**
     * Get's the Ship Position's sog.
     *
     * @return Ship's sog
     */
    public String getSog() {
        return sog;
    }

    /**
     * Get's the Ship Position's cog.
     *
     * @return Ship's cog
     */
    public String getCog() {
        return cog;
    }

    /**
     * Get's the Ship Position's heading.
     *
     * @return Ship's heading
     */
    public String getHeading() {
        return heading;
    }

    /**
     * Get's the Ship Position's cargo.
     *
     * @return Ship's cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Get's the Ship Position's transceiver.
     *
     * @return Ship's transceiver
     */
    public String getTransciever() {
        return transciever;
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
        ShipPosition that = (ShipPosition) o;
        return Objects.equals(mmsi, that.mmsi) && Objects.equals(date, that.date);
    }

    /**
     * returns the hash code of the ship position.
     *
     * @return ship hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(mmsi, date, lat, lon, sog, cog, heading, cargo, transciever);
    }

    /**
     * Compares the ship position date with the @param date.
     *
     * @param o
     *
     * @return if its lower, equal or bigger
     */
    @Override
    public int compareTo(ShipPosition o) {
        return this.getDate().compareTo(o.getDate());
    }

    /**
     * Returns the textual description of the ship position.
     *
     * @return ship position characteristics.
     */
    @Override
    public String toString() {
        return String.format("ShipPosition{mmsi='%s', date=%d/%d/%d %02d:%02d}",mmsi, date.getDate(), date.getMonth()+1, date.getYear()+1900, date.getHours(), date.getMinutes());
    }

    /**
     * verifies if the ship position lat is valid
     *
     * @param lat
     */
    private void latSignValidation(String lat){
        if (Objects.equals(lat, ""))throw new IllegalArgumentException("LAT can't be empty.");
        try {
            Double.parseDouble(lat);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The LAT must be numeric.");
        }
        double i =Double.parseDouble(lat);
        if(i<-90 || i>91){
            throw new IllegalArgumentException("The value of LAT isnt valid.");
        }
    }

    /**
     * verifies if the ship position lon is valid
     *
     * @param lon
     */
    private void lonValidation(String lon){
        if (Objects.equals(lon, ""))throw new IllegalArgumentException("LON can't be empty.");
        try {
            Double.parseDouble(lon);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The LON must be numeric.");
        }
        double i =Double.parseDouble(lon);
        if(i<-180 || i>181){
            throw new IllegalArgumentException("The value of LON isnt valid.");
        }
    }

    /**
     * verifies if the ship position sog is valid
     *
     * @param sog
     */
    private void sogValidation(String sog){
        if (Objects.equals(sog, ""))throw new IllegalArgumentException("SOG can't be empty.");
        try {
            Double.parseDouble(sog);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The SOG must be numeric.");
        }
    }

    /**
     * verifies if the ship position cog is valid
     *
     * @param cog
     */
    private void cogValidation(String cog){
        if (Objects.equals(cog, ""))throw new IllegalArgumentException("COG can't be empty.");
        try {
            Double.parseDouble(cog);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The COG must be numeric.");
        }
        double i =Double.parseDouble(cog);
        if(i<0 || i>359){
            throw new IllegalArgumentException("The value of COG isnt valid.");
        }
    }

    /**
     * verifies if the ship position heading is valid
     *
     * @param heading
     */
    private void headingValidation(String heading) {
        if (Objects.equals(heading, "")) throw new IllegalArgumentException("Heading can't be empty.");
        try {
            Integer.parseInt(heading);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The Heading must be numeric.");
        }
        int i = Integer.parseInt(heading);
            if (i!=511 &&(i < 0 || i > 359)) {
                throw new IllegalArgumentException("The value of Heading isnt valid.");
            }
    }

    /**
     * verifies if the ship position cargo is valid
     *
     * @param cargo
     */
    private void cargoValidation(String cargo){
        if (Objects.equals(cargo, ""))throw new IllegalArgumentException("Cargo can't be empty.");
    }

    /**
     * verifies if the ship position transceiver is valid
     *
     * @param transciever
     */
    private void transcieverValidation(String transciever){
        if (Objects.equals(transciever, ""))throw new IllegalArgumentException("Cargo can't be empty.");
    }

}
