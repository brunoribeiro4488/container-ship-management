
package lapr.project.utils.bst;



import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 *
 * @author DEI-ESINF
 */
public class BSTTest {
    Integer[] arr = {20,15,10,13,8,17,40,50,30,7};
    int[] height={0,1,2,3,3,3,3,3,3,4};
    Integer[] inorderT= {7,8,10,13,15,17,20,30,40,50};
    Integer[] preorderT= {20, 15, 10, 8, 7, 13, 17, 40, 30, 50};
    Integer[] posorderT = {7, 8, 13, 10, 17, 15, 30, 50, 40, 20};

    BST<Integer> instance;

    public BSTTest() {
    }
    
    @BeforeEach
    public void setUp(){
        instance = new BST();
        for(int i :arr)
            instance.insert(i);        
    }    
/**
     * Test of size method, of class BST.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        Assertions.assertEquals(instance.size(), arr.length);
        
        BST<String> sInstance = new BST();
        Assertions.assertEquals(sInstance.size(), 0);
        sInstance.insert("A");
        Assertions.assertEquals(sInstance.size(), 1);
        sInstance.insert("B");
        Assertions.assertEquals(sInstance.size(), 2);
        sInstance.insert("A");
        Assertions.assertEquals(sInstance.size(), 2);
    }

    /**
     * Test of insert method, of class BST.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        int arr[] = {20,15,10,13,8,17,40,50,30,20,15,10};
        BST<Integer> instance = new BST();
        for (int i=0; i<9; i++){            //new elements
            instance.insert(arr[i]);
            Assertions.assertEquals(instance.size(), i+1);
        }
        for(int i=9; i<arr.length; i++){    //duplicated elements => same size
            instance.insert(arr[i]);
            Assertions.assertEquals(instance.size(), 9);
        }
    }
    /**
     * Test of remove method, of class BST.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");

        int qtd=arr.length;
        instance.remove(999);

        Assertions.assertEquals(instance.size(), qtd);
        for (int i=0; i<arr.length; i++){
            instance.remove(arr[i]);
            qtd--;
            Assertions.assertEquals(qtd,instance.size());
        }
        
        instance.remove(999);
        Assertions.assertEquals(0,instance.size());
    }
/**
     * Test of isEmpty method, of class BST.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isempty");

        Assertions.assertFalse(instance.isEmpty());
        instance = new BST();
        Assertions.assertTrue(instance.isEmpty());

        instance.insert(11);
        Assertions.assertFalse(instance.isEmpty());
        
        instance.remove(11);
        Assertions.assertTrue(instance.isEmpty());
    }
/**
     * Test of height method, of class BST.
     */
    @Test
    public void testHeight() {
        System.out.println("height");

        instance = new BST();
        Assertions.assertEquals(instance.height(), -1);
        for(int idx=0; idx<arr.length; idx++){
            instance.insert(arr[idx]);
            Assertions.assertEquals(instance.height(), height[idx]);
        }
        instance = new BST();
        Assertions.assertEquals(instance.height(), -1);
    }
    /**
     * Test of smallestelement method, of class TREE.
     */
    @Test
    public void testSmallestElement() {
        System.out.println("smallestElement");
        Assertions.assertEquals(new Integer(7), instance.smallestElement());
        instance.remove(7);
        Assertions.assertEquals(new Integer(8), instance.smallestElement());
        instance.remove(8);
        Assertions.assertEquals(new Integer(10), instance.smallestElement());
        instance = new BST<>();
        Assertions.assertEquals(null,instance.smallestElement());
    }    
/**
     * Test of processBstByLevel method, of class TREE.
     */
    @Test
    public void testProcessBstByLevel() {
        System.out.println("processbstbylevel");
        Map<Integer,List<Integer>> expResult = new HashMap();
        expResult.put(0, Arrays.asList(new Integer[]{20}));
        expResult.put(1, Arrays.asList(new Integer[]{15,40}));
        expResult.put(2, Arrays.asList(new Integer[]{10,17,30,50}));
        expResult.put(3, Arrays.asList(new Integer[]{8,13}));
        expResult.put(4, Arrays.asList(new Integer[]{7}));
        
        Map<Integer,List<Integer>> result = instance.nodesByLevel();
        
        for(Map.Entry<Integer,List<Integer>> e : result.entrySet())
            Assertions.assertEquals(expResult.get(e.getKey()), e.getValue());
    }    
   

/**
     * Test of inOrder method, of class BST.
     */
    @Test
    public void testInOrder() {
        System.out.println("inOrder");
        List<Integer> lExpected = Arrays.asList(inorderT);
        Assertions.assertEquals(lExpected, instance.inOrder());
    }
/**
     * Test of preOrder method, of class BST.
     */
    @Test
    public void testpreOrder() {
        System.out.println("preOrder");
        List<Integer> lExpected = Arrays.asList(preorderT);
        Assertions.assertEquals(lExpected, instance.preOrder());
        instance = new BST<>();
        Assertions.assertEquals(null,instance.preOrder());
    }
/**
     * Test of posOrder method, of class BST.
     */
    @Test
    public void testposOrder() {
        System.out.println("posOrder");
        List<Integer> lExpected = Arrays.asList(posorderT);
        Assertions.assertEquals(lExpected, instance.posOrder());
        instance = new BST<>();
        Assertions.assertEquals(null,instance.posOrder());
    }


    @Test
    public void find() {
        BST<Integer> instance = new BST<>();
        System.out.println("find");
        int expected = 20;
        instance.insert(20);
        int result = instance.find(20);
        Assertions.assertEquals(expected,result);

        Assertions.assertNull( instance.find(1003));
        
        BST<Integer> instance2 = new BST<>();
        Assertions.assertNull( instance2.find(50));
    }

    @Test
    public void findListInAnInterval() {
        System.out.println("findListInAnInterval");
        int arr[] = {50};
        List<Integer> lExpected = new ArrayList<>();
        lExpected.add(30);
        lExpected.add(40);
        lExpected.add(50);
        Iterable<Integer> actual = instance.findListInAnInterval(30,60);
        Assertions.assertEquals(lExpected,actual);
        List<Integer> lExpected2 = new ArrayList<>();
        instance = new BST<>();
        Assertions.assertEquals(lExpected2,instance.findListInAnInterval(30,60));
    }

    @Test
    public void testBiggestElement() {
        instance = new BST<>();
        Assertions.assertEquals(null,instance.biggestElement());
    }


    @Test
    void testToString() {
        String expected = "|\t|-------50\n" +
                "|-------40\n" +
                "|\t|-------30\n" +
                "20\n" +
                "|\t|-------17\n" +
                "|-------15\n" +
                "|\t|\t|-------13\n" +
                "|\t|-------10\n" +
                "|\t|\t|-------8\n" +
                "|\t|\t|\t|-------7\n";
        Assert.assertEquals(expected,instance.toString());
    }
}
