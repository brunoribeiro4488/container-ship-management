package lapr.project.utils.dto;

import lapr.project.model.Port;
import lapr.project.model.Ship;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuditTrailDTOTest {

    @Test
    void testToString() {
        AuditTrailDTO at = new AuditTrailDTO("2021-12-27 17:58:11", "INSERT", "78546321458", "4785", "11111", "1", "Load");
        String expected = "Cargo Manifest ID = 11111 || Cargo Manifest Partial ID = 1 || Cargo Manifest Type = Load || Container Number = 78546321458 || Container ISO Code = 4785 || Date = 2021-12-27 17:58:11 || Operation = INSERT";
        String notExpected = "asdas";
        String actual = at.toString();
        assertEquals(expected, actual);
        assertNotEquals(notExpected, actual);
    }

    @Test
    public void equals(){
        AuditTrailDTO at1 = new AuditTrailDTO("2021-12-27 17:58:11", "INSERT", "78546321458", "4785", "11111", "1", "Load");
        AuditTrailDTO at2 = new AuditTrailDTO("2021-12-27 17:58:11", "INSERT", "78546321458", "4785", "11111", "1", "Load");
        Assertions.assertEquals(at1,at2);
    }

    @Test
    public void equals2(){
        AuditTrailDTO at1 = new AuditTrailDTO("2021-12-27 17:58:11", "INSERT", "78546321458", "4785", "11111", "1", "Load");
        AuditTrailDTO at2 = at1;
        Assertions.assertEquals(at1,at2);
    }

    @Test
    public void notequals(){
        AuditTrailDTO at1 = new AuditTrailDTO("2021-12-27 17:58:11", "INSERT", "78546321458", "4785", "11111", "1", "Load");
        AuditTrailDTO at2 = new AuditTrailDTO("2021-12-22 17:58:11", "INSERT", "78546321458", "4785", "11111", "1", "Load");
        Assertions.assertNotEquals(at1,at2);
    }

    @Test
    public void equalsNullDifClass(){
        AuditTrailDTO at = new AuditTrailDTO("2021-12-27 17:58:11", "INSERT", "78546321458", "4785", "11111", "1", "Load");
        Ship ship = null;
        Assertions.assertNotEquals(at,ship);
    }

    @Test
    public void equalsNotNullDifClass(){
        AuditTrailDTO at = new AuditTrailDTO("2021-12-27 17:58:11", "INSERT", "78546321458", "4785", "11111", "1", "Load");
        Ship ship = new Ship("123456789","name","1234567","wert5","145","14","23","15");
        Assertions.assertNotEquals(at,ship);
    }

    @Test
    public void equalsNull(){
        AuditTrailDTO at1 = new AuditTrailDTO("2021-12-27 17:58:11", "INSERT", "78546321458", "4785", "11111", "1", "Load");
        AuditTrailDTO at2 = null;
        Assertions.assertNotEquals(at1,at2);
    }
}