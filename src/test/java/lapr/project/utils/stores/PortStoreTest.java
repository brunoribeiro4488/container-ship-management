package lapr.project.utils.stores;

import lapr.project.model.Port;
import lapr.project.utils.bst.KD_Tree;
import lapr.project.utils.bst.KD_Tree_Port;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PortStoreTest {

    @Test
    void createPort(){
        PortStore ps = new PortStore();
        Port port1 = new Port("29002","Liverpool","Europe","United Kingdom","5","-3.033333333");
        Port port2 = ps.createPort("29002","Liverpool","Europe","United Kingdom","5","-3.033333333");
        assertEquals(port1,port2);
    }

    @Test
    void getPortPosition(){
        PortStore ps = new PortStore();
        Port port1 = new Port("29002","Liverpool","Europe","United Kingdom","5","-3.033333333");
        ps.savePortinKdt(port1);
        assertEquals(ps.getPortByPosition(port1.getLatitude(), port1.getLongitude()),port1);
    }

    @Test
    void getKdt(){
        PortStore ps = new PortStore();
        KD_Tree_Port result = new KD_Tree_Port();
        ps.setKdt(result);
        KD_Tree expected = ps.getKdt();
        Assertions.assertEquals(expected, result);
    }

}