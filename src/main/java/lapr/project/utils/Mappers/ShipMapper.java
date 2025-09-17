package lapr.project.utils.Mappers;

import lapr.project.utils.dto.ShipDTO;
import lapr.project.model.Ship;

/**
 * ShipMapper class, which is responsible for converting a Ship in a ShipDTO.
 *
 * @author Rita Lello
 */
public class ShipMapper {

    private ShipMapper(){}

    /**
     * Responsible for converting a Ship to a ShipDTO.
     *
     * @param s a ship
     * @return a shipDTO
     */
    public static ShipDTO modelToDTO(Ship s){
        if(s==null)
            return new ShipDTO(null,null,null,null,null,null,null,null);
        return new ShipDTO(s.getMmsi(),s.getName(), s.getImo(), s.getCallsign(),s.getType(), s.getLength(),s.getWidth(), s.getDraft());
    }
}
