package lapr.project.utils.bst;

import lapr.project.model.Ship;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class BSTMMSITest {
    Ship[] arr = {new Ship("123456789","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5"),new Ship("123456780","VARAMD","IMO9395054","C4SQ3","70","166","25","9.5"),new Ship("123457789","VAREMO","IMO9396044","C6SQ2","70","166","25","9.5"),new Ship("129456789","VAREMD","IMO7395044","C4DQ2","70","166","25","9.5"),new Ship("123896789","VAFAMO","IMO9885044","C1SQ2","70","166","25","9.5"),new Ship("125656789","VADAMD","IMO9399944","C6FQ2","70","166","25","9.5")};

    BSTMMSI instance;

    public BSTMMSITest() {
    }

    @BeforeEach
    public void setUp(){
        instance = new BSTMMSI();
        for(Ship i :arr)
            instance.insert(i);
    }

    /**
     * Test of insert method, of class BSTMMSI.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        Ship[] arr = {new Ship("123456789","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5"),new Ship("123456780","VARAMD","IMO9395054","C4SQ3","70","166","25","9.5"),new Ship("123457789","VAREMO","IMO9396044","C6SQ2","70","166","25","9.5"),new Ship("129456789","VAREMD","IMO7395044","C4DQ2","70","166","25","9.5"),new Ship("123896789","VAFAMO","IMO9885044","C1SQ2","70","166","25","9.5"),new Ship("125656789","VADAMD","IMO9399944","C6FQ2","70","166","25","9.5"),};
        BSTMMSI instance = new BSTMMSI();
        for (int i=0; i<6; i++){            //new elements
            instance.insert(arr[i]);
            Assertions.assertEquals(instance.size(), i+1);
        }
        for(int i=9; i<arr.length; i++){    //duplicated elements => same size
            instance.insert(arr[i]);
            Assertions.assertEquals(instance.size(), 9);
        }
    }

    /**
     * Test of find method, of class BSTMMSI.
     */
    @Test
    public void find() {
        System.out.println("find");
        Ship expected = new Ship("123456789","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5");
        Ship result = instance.find("123456789");
        Assertions.assertEquals(expected,result);

        Assertions.assertNull(instance.find("123499999"));

        BSTMMSI instance2 = new BSTMMSI();
        Assertions.assertNull( instance2.find("123488888"));
    }

    /**
     * Test of isEmpty method, of class BSTMMSI.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isempty");

        Assertions.assertFalse(instance.isEmpty());
        instance = new BSTMMSI();
        Assertions.assertTrue(instance.isEmpty());

        instance.insert(new Ship("123456789","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5"));
        Assertions.assertFalse(instance.isEmpty());

        instance.remove(new Ship("123456789","VARAMO","IMO9395044","C4SQ2","70","166","25","9.5"));
        Assertions.assertTrue(instance.isEmpty());
    }

}