package lapr.project.utils.Mappers;

import lapr.project.utils.dto.AuditTrailDTO;

import java.util.List;

public class AuditTrailMapper {

    private AuditTrailMapper(){}

    /**
     * Responsible for create a auditTrailDTO
     *
     * @param at the information of a audit trail
     * @return a auditTrailDTO
     */
    public static AuditTrailDTO toDTO(List<String> at){
        if(at == null || at.isEmpty()){
            return new AuditTrailDTO(null,null,null,null,null,null,null);
        }
        return new AuditTrailDTO(at.get(0), at.get(1), at.get(2), at.get(3), at.get(4), at.get(5), at.get(6));
    }

}
