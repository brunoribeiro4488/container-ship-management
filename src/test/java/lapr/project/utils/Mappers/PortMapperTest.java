package lapr.project.utils.Mappers;

import lapr.project.model.Port;
import lapr.project.utils.dto.PortDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PortMapperTest {

    @Test
    void modelToDTO() {
        PortDTO expected = new PortDTO(null,null,null,null,null,null);
        Assert.assertEquals(expected,PortMapper.modelToDTO(null));
    }
}