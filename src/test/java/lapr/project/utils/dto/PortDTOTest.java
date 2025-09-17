package lapr.project.utils.dto;

import lapr.project.model.Port;
import lapr.project.model.Ship;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PortDTOTest {

    @Test
    public void equals(){
        PortDTO port1 = new PortDTO("29002","Liverpool","Europe","United Kingdom","5","-3.033333333");
        PortDTO port2 = new PortDTO("29002","Liverol","Eupe","United gdom","6","-3.033333333");
        Assertions.assertEquals(port1,port2);
    }

    @Test
    public void equals2(){
        PortDTO port1 = new PortDTO("29002","Liverpool","Europe","United Kingdom","5","-3.033333333");
        PortDTO port2 = port1;
        Assertions.assertEquals(port1,port2);
    }

    @Test
    public void notequals(){
        PortDTO port1 = new PortDTO("29002","Liverpool","Europe","United Kingdom","5","-3.033333333");
        PortDTO port2 = new PortDTO("29012","Liverol","Eupe","United gdom","6","-3.033333333");
        Assertions.assertNotEquals(port1,port2);
    }

    @Test
    public void equalsNullDifClass(){
        PortDTO port1 = new PortDTO("29002","Liverpool","Europe","United Kingdom","5","-3.033333333");
        Ship ship = null;
        Assertions.assertNotEquals(port1,ship);
    }

    @Test
    public void equalsNotNullDifClass(){
        PortDTO port1 = new PortDTO("29002","Liverpool","Europe","United Kingdom","5","-3.033333333");
        Ship ship = new Ship("123456789","name","1234567","wert5","145","14","23","15");
        Assertions.assertNotEquals(port1,ship);
    }

    @Test
    public void equalsNull(){
        PortDTO port1 = new PortDTO("29002","Liverpool","Europe","United Kingdom","5","-3.033333333");
        PortDTO port = null;
        Assertions.assertNotEquals(port1,port);
    }
}