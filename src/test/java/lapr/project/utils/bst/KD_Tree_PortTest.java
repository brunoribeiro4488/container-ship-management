package lapr.project.utils.bst;

import lapr.project.model.Port;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class KD_Tree_PortTest {

    @Test
    void partition() {
        KD_Tree_Port kd_tree_port = new KD_Tree_Port();
        Port port1 = new Port("123", "wtgtrh","wrthteh","trghter","90", "30");
        Port port2 = new Port("122", "wtggtrehrh","wrterhhteh","we","20", "35");
        Port port3 = new Port("120", "whytjhrh","wikuhteh","weytrjuik","10", "45");
        List<Port> list = new ArrayList<>();
        list.add(port2);
        list.add(port3);
        list.add(port1);

        kd_tree_port.insertAllPorts(list);
        int expected = 2;
        int result = kd_tree_port.partition(list,0,2,true);

        Assert.assertEquals(expected,result);
    }

    @Test
    void partition2() {
        KD_Tree_Port kd_tree_port = new KD_Tree_Port();
        Port port1 = new Port("123", "wtgtrh","wrthteh","trghter","90", "30");
        Port port2 = new Port("122", "wtggtrehrh","wrterhhteh","we","20", "35");
        Port port3 = new Port("120", "whytjhrh","wikuhteh","weytrjuik","10", "45");
        List<Port> list = new ArrayList<>();
        list.add(port2);
        list.add(port3);
        list.add(port3);

        kd_tree_port.insertAllPorts(list);
        int expected = 1;
        int result = kd_tree_port.partition(list,0,2,true);

        Assert.assertEquals(expected,result);
    }

    @Test
    void partition3() {
        KD_Tree_Port kd_tree_port = new KD_Tree_Port();
        Port port1 = new Port("123", "wtgtrh","wrthteh","trghter","90", "30");
        Port port2 = new Port("122", "wtggtrehrh","wrterhhteh","we","20", "35");
        Port port3 = new Port("120", "whytjhrh","wikuhteh","weytrjuik","10", "45");
        List<Port> list = new ArrayList<>();
        list.add(port2);
        list.add(port3);
        list.add(port3);
        list.add(port1);

        kd_tree_port.insertAllPorts(list);
        int expected = 2;
        int result = kd_tree_port.partition(list,0,2,true);

        Assert.assertEquals(expected,result);
    }

    @Test
    void quickSort() {
        KD_Tree_Port kd_tree_port = new KD_Tree_Port();
        Port port1 = new Port("123", "wtgtrh","wrthteh","trghter","90", "30");
        Port port2 = new Port("122", "wtggtrehrh","wrterhhteh","we","20", "35");
        Port port3 = new Port("120", "whytjhrh","wikuhteh","weytrjuik","10", "45");
        List<Port> list = new ArrayList<>();
        list.add(port2);
        list.add(port3);
        list.add(port1);

        kd_tree_port.insertAllPorts(list);
        kd_tree_port.quickSort(list,0,2,true);

        List<Port> expected = new ArrayList<>();
        expected.add(port2);
        expected.add(port1);
        expected.add(port3);


        Assert.assertEquals(expected,list);

    }

    @Test
    void quickSort2() {
        KD_Tree_Port kd_tree_port = new KD_Tree_Port();
        Port port1 = new Port("123", "wtgtrh","wrthteh","trghter","90", "30");
        Port port2 = new Port("122", "wtggtrehrh","wrterhhteh","we","20", "35");
        Port port3 = new Port("120", "whytjhrh","wikuhteh","weytrjuik","10", "45");
        List<Port> list = new ArrayList<>();
        list.add(port2);
        list.add(port3);
        list.add(port1);

        kd_tree_port.insertAllPorts(list);
        kd_tree_port.quickSort(list,2,2,true);

        List<Port> expected = new ArrayList<>();
        expected.add(port2);
        expected.add(port1);
        expected.add(port3);


        Assert.assertEquals(expected,list);

    }

    @Test
    void quickSort3() {
        KD_Tree_Port kd_tree_port = new KD_Tree_Port();
        Port port1 = new Port("123", "wtgtrh","wrthteh","trghter","90", "30");
        Port port2 = new Port("122", "wtggtrehrh","wrterhhteh","we","20", "35");
        Port port3 = new Port("120", "whytjhrh","wikuhteh","weytrjuik","10", "45");
        List<Port> list = new ArrayList<>();
        list.add(port2);
        list.add(port3);
        list.add(port1);

        kd_tree_port.insertAllPorts(list);
        kd_tree_port.quickSort(list,5,2,true);

        List<Port> expected = new ArrayList<>();
        expected.add(port2);
        expected.add(port1);
        expected.add(port3);


        Assert.assertEquals(expected,list);

    }
}