package lapr.project.utils.Mappers;

import lapr.project.utils.dto.ShipPositionDTO;
import lapr.project.model.ShipPosition;

import java.util.ArrayList;
import java.util.List;

/**
 * ShipPositionMapper class, which is responsible for converting a Ship Position in a ShipPositionDTO.
 *
 * @author Carlos Rodrigues
 */
public class ShipPositionMapper {


    /**
     * Responsible for converting a ShipPosition to a ShipPositionDTO.
     *
     * @param shipPosition a ship position
     * @return a shipPositionDTO
     */
    public static ShipPositionDTO toDTO(ShipPosition shipPosition){
        return new ShipPositionDTO(shipPosition.getMmsi(), shipPosition.getDate(),shipPosition.getLat(),shipPosition.getLon(),shipPosition.getSog(),shipPosition.getCog(),shipPosition.getHeading(),shipPosition.getCargo(),shipPosition.getTransciever());
    }

    /**
     * Responsible for converting a Ship Position list to a ShipPositionDTO list.
     *
     * @param list a ship position list
     * @return a shipPositionDTO list
     */
    public static List<ShipPositionDTO> toDTOlist(List<ShipPosition> list){
        List<ShipPositionDTO> listDTO = new ArrayList<>();
        for (ShipPosition shipPosition:list) {
            listDTO.add(toDTO(shipPosition));
        }
        return listDTO;
    }

}
