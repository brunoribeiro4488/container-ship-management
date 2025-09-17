package lapr.project.utils.bst;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class KD_TreeTest {

    KD_Tree<String> instance;

    @BeforeEach
    public void setUp(){
        instance = new KD_Tree();
    }

    @Test
    void insert() {
        instance.insert(2,3,"A");
        instance.insert(1,1,"B");
        instance.insert(4,3,"C");
        instance.insert(6,9,"D");
        instance.insert(0,0,"E");

        int expected = 5;
        int result = instance.size();
        Assert.assertEquals(expected,result);
    }

    @Test
    void find() {
        Assert.assertEquals(null,instance.find(2,3));

        instance.insert(2,3,"A");
        instance.insert(1,1,"B");
        instance.insert(3,5,"C");
        instance.insert(4,3,"D");
        instance.insert(0,1,"E");

        String expected = "D";
        String result = instance.find(4,3);

        Assert.assertEquals(expected,result);
        Assert.assertEquals(null, instance.find(9,6));
    }

    @Test
    void findNearestNeighbour() {
        Assert.assertEquals(null,instance.find(2,3));

        instance.insert(3,9,"A");
        instance.insert(7,9,"B");
        instance.insert(1,7,"C");
        instance.insert(2,1,"D");
        instance.insert(1,4,"E");
        instance.insert(9,6,"F");
        instance.insert(5,9,"G");
        instance.insert(7,3,"H");
        instance.insert(11,6,"I");
        instance.insert(6,1,"J");
        instance.insert(8,2,"K");


        String expected = "E";
        String result = instance.findNearestNeighbour(4,5);

        Assert.assertEquals(expected,result);
    }

    @Test
    void size() {

        Assert.assertEquals(0,instance.size());
    }
}