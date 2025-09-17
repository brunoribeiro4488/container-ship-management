package lapr.project.utils.dto;

import java.util.Objects;

/**
 * PortDTO class, which is responsible for converting a Port in a PortDTO.
 *
 * @author Rita Lello
 */
public class PortDTO {

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
    public PortDTO(String id, String name, String continent, String country, String latitude, String longitude) {
        this.id = id;
        this.name = name;
        this.continent = continent;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Return a textual representation of the object, which contains all of its attributes.
     *
     * @return portDTO characteristics
     */
    @Override
    public String toString(){
        return String.format("The Port id is %s, with name %s, it's in continent %s, country %s, with latitude %s and longitude %s.", this.id, this.name, this.continent, this.country, this.latitude, this.longitude);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PortDTO portDTO = (PortDTO) o;
        return Objects.equals(id, portDTO.id) ;
    }


}
