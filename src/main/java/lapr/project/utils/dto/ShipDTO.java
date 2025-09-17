package lapr.project.utils.dto;

/**
 * ShipDTO class, which is responsible for converting a Ship in a ShipDTO.
 *
 * @author Rita Lello
 */
public class ShipDTO {

    /**
     * The shipDTO's mmsi.
     */
    private final String mmsi;

    /**
     * The shipDTO's name.
     */
    private final String name;

    /**
     * The shipDTO's imo.
     */
    private final String imo;

    /**
     * The shipDTO's call sign.
     */
    private final String callsign;

    /**
     * The shipDTO's type.
     */
    private final String type;

    /**
     * The shipDTO's length.
     */
    private final String length;

    /**
     * The shipDTO's width.
     */
    private final String width;

    /**
     * The shipDTO's draft.
     */
    private final String draft;

    /**
     * Builds the ShipDTO object.
     *
     * @param mmsi the shipDTO mmsi
     * @param name the shipDTO name
     * @param imo the shipDTO imo
     * @param callsign the shipDTO call sign
     * @param type the shipDTO type
     * @param length the shipDTO length
     * @param width the shipDTO width
     * @param draft the shipDTO draft
     */
    public ShipDTO(String mmsi, String name, String imo, String callsign, String type, String length, String width, String draft) {
        this.mmsi = mmsi;
        this.name = name;
        this.imo = imo;
        this.callsign = callsign;
        this.type = type;
        this.length = length;
        this.width = width;
        this.draft = draft;
    }

    /**
     * Return a textual representation of the object, which contains all of its attributes.
     *
     * @return shipDTO characteristics
     */
    @Override
    public String toString(){
        return String.format("The Ship name is %s, with mmsi %s, imo %s, call sign %s, type %s, length %s, width %s, draft %s.", this.name, this.mmsi, this.imo, this.callsign, this.type, this.length, this.width, this.draft);
    }
}
