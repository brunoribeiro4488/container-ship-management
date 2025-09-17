package lapr.project.model;

import java.util.Objects;

/**
 * Class Port.
 */
public class Port {

    /**
     * The port's Identification.
     */
    private final  String id;

    /**
     * The port's Name.
     */
    private final String name;

    /**
     * The port's Continent.
     */
    private final String continent;

    /**
     * The port's Country.
     */
    private final String country;

    /**
     * The port's Latitude.
     */
    private final String latitude;

    /**
     * The port's Longitude.
     */
    private final String longitude;

    /**
     * Builds a Port receiving all its parameters.
     * @param id The port's Identification.
     * @param name The port's Name.
     * @param continent The port's Continent.
     * @param country The port's Country.
     * @param latitude The port's Latitude.
     * @param longitude The port's Longitude.
     */
    public Port(String id, String name, String continent, String country, String latitude, String longitude) {
        idValidation(id);
        nameValidation(name);
        continetValidation(continent);
        countryValidation(country);
        latValidation(latitude);
        lonValidation(longitude);
        this.id = id;
        this.name = name;
        this.continent = continent;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Gets the port's identification
     * @return port's identification
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the port's Name
     * @return port's Name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the port's Continent
     * @return port's Continent
     */
    public String getContinent() {
        return continent;
    }

    /**
     * Gets the port's Country
     * @return port's Country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Gets the port's latitude
     * @return port's latitude
     */
    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    /**
     * Gets the port's longitude
     * @return port's longitude
     */


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Port port = (Port) o;
        return Objects.equals(id, port.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * verifies if the port's Identification valid
     * @param id
     */
    private void idValidation(String id){
        if (Objects.equals(id, ""))throw new IllegalArgumentException("Identification can't be empty.");
    }

    /**
     * verifies if the port's name valid
     * @param name
     */
    private void nameValidation(String name){
        if (Objects.equals(name, ""))throw new IllegalArgumentException("Name can't be empty.");
    }

    /**
     * verifies if the port's continent valid
     * @param continent
     */
    private void continetValidation(String continent){
        if (Objects.equals(continent, ""))throw new IllegalArgumentException("Continet can't be empty.");
    }

    /**
     * verifies if the port's country is valid
     * @param country
     */
    private void countryValidation(String country){
        if (Objects.equals(country, ""))throw new IllegalArgumentException("Country can't be empty.");
    }

    /**
     * verifies if the port's latitude is valid
     *
     * @param lat
     */
    private void latValidation(String lat){
        if (Objects.equals(lat, ""))throw new IllegalArgumentException("Latitude can't be empty.");
        try {
            Double.parseDouble(lat);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The Latitude must be numeric.");
        }
        double i =Double.parseDouble(lat);
        if(i<-90 || i>91){
            throw new IllegalArgumentException("The value of Latitude isnt valid.");
        }
    }

    /**
     * verifies if the port's longitude is valid
     *
     * @param lon
     */
    private void lonValidation(String lon){
        if (Objects.equals(lon, ""))throw new IllegalArgumentException("Longitude can't be empty.");
        try {
            Double.parseDouble(lon);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The Longitude must be numeric.");
        }
        double i =Double.parseDouble(lon);
        if(i<-180 || i>181){
            throw new IllegalArgumentException("The value of Longitude isnt valid.");
        }
    }

    public String toString(){
        return String.format("Port with id: %s",this.id);
    }


}
