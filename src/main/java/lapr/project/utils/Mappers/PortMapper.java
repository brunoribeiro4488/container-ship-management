package lapr.project.utils.Mappers;

import lapr.project.model.Port;
import lapr.project.utils.dto.PortDTO;

/**
 * PortMapper class, which is responsible for converting a Port in a PortDTO.
 *
 * @author Rita Lello
 */
public class PortMapper {

    private PortMapper() {
        // It is not needed to inicialize anything
    }

    /**
     * Responsible for converting a Port to a PortDTO.
     *
     * @param port a port
     * @return a portDTO
     */
    public static PortDTO modelToDTO(Port port){
        if(port==null)
            return new PortDTO(null,null,null,null,null,null);
        return new PortDTO(port.getId(),port.getName(),port.getContinent(), port.getCountry(),port.getLatitude(),port.getLongitude());
    }
}
