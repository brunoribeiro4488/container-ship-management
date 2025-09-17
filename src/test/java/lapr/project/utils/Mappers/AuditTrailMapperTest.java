package lapr.project.utils.Mappers;

import lapr.project.utils.dto.AuditTrailDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class AuditTrailMapperTest {

    @Test
    void toDTO() {
        AuditTrailDTO expected = new AuditTrailDTO(null,null,null,null,null,null,null);
        Assert.assertEquals(expected,AuditTrailMapper.toDTO(null));
    }

    @Test
    void toDTOempty() {
        AuditTrailDTO expected = new AuditTrailDTO(null,null,null,null,null,null,null);
        List<String> la = new ArrayList<>();
        Assert.assertEquals(expected,AuditTrailMapper.toDTO(la));
    }
}