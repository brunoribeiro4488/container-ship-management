package lapr.project.utils.matrix;

import lapr.project.model.Country;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class AlgorithmsTest {

    final Graph<String,String> completeMap = new MatrixGraph<>(false);

    public AlgorithmsTest() {
    }

    @BeforeEach
    public void setUp() {

        completeMap.addVertex("Lisboa");
        completeMap.addVertex("Madrid");
        completeMap.addVertex("Roma");
        completeMap.addVertex("12345");
        completeMap.addVertex("77777");
        completeMap.addVertex("66677");
        completeMap.addVertex("aaaaa");
        completeMap.addVertex("Londres");
        completeMap.addVertex("Lima");
        completeMap.addVertex("01010");

        completeMap.addEdge("Lisboa", "12345", 5.5);
        completeMap.addEdge("Lisboa", "77777", 6);
        completeMap.addEdge("12345", "77777", 3.3);
        completeMap.addEdge("Madrid", "77777", 2);
        completeMap.addEdge("77777", "66677", 4);
        completeMap.addEdge("Madrid", "Roma", 11);
        completeMap.addEdge("Roma", "66677", 5);
        completeMap.addEdge("Londres","Lima",6);
        completeMap.addEdge("Lima","01010",4);
    }



/**
     * Test of shortestPath method, of class GraphAlgorithms.
     */

    @Test
    public void testShortestPath() {
        System.out.println("Test of shortest path");

        LinkedList<String> shortPath = new LinkedList<String>();
        double lenpath = Algorithms.shortestPath(completeMap, "Lisboa", "12346", shortPath);
        assertTrue(lenpath == 0, "Length path should be 0 if vertex does not exist");

        lenpath = Algorithms.shortestPath(completeMap, "Lisboa", "aaaaa", shortPath);
        assertTrue(lenpath == 0, "Length path should be 0 if there is no path");

        lenpath = Algorithms.shortestPath(completeMap, "Lisboa", "Lisboa", shortPath);
        assertTrue(shortPath.size() == 1, "Number of nodes should be 1 if source and vertex are the same");
        lenpath = Algorithms.shortestPath(completeMap, "Lisboa", "Roma", shortPath);

        assertTrue(lenpath == 15, "Path between Lisboa and Roma should be 15 Km");

        Iterator<String> it = shortPath.iterator();
        assertTrue(it.next().compareTo("Lisboa") == 0, "First in path should be Lisboa");
        assertTrue(it.next().compareTo("77777") == 0, "then 77777");
        assertTrue(it.next().compareTo("66677") == 0, "then 66677");
        assertTrue(it.next().compareTo("Roma") == 0, "then Roma");

        lenpath = Algorithms.shortestPath(completeMap, "Madrid", "12345", shortPath);
        assertTrue(lenpath == 5.3, "Path between Madrid and 12345 should be 5.3 Km");

        it = shortPath.iterator();

        assertTrue(it.next().compareTo("Madrid") == 0, "First in path should be Madrid");
        assertTrue(it.next().compareTo("77777") == 0, "then 77777");
        assertTrue(it.next().compareTo("12345") == 0, "then 12345");

        shortPath.clear();
        lenpath = Algorithms.shortestPath(completeMap, "Roma", "12345", shortPath);
        assertTrue(lenpath == 12.3, "Path between Roma and 12345 should be 12.3 Km");
        assertTrue(shortPath.size() == 4, "N. cities between Roma and 12345 should be 4 ");

        it = shortPath.iterator();

        assertTrue(it.next().compareTo("Roma") == 0, "First in path should be Roma");
        assertTrue(it.next().compareTo("66677") == 0, "then 66677");
        assertTrue(it.next().compareTo("77777") == 0, "then 77777");
        assertTrue(it.next().compareTo("12345") == 0, "then 12345");

        //Changing Edge: 77777-66677 with Edge: Madrid-66677
        //should change shortest path between Roma and 12345
        completeMap.removeEdge("77777", "66677");
        completeMap.addEdge("Madrid", "66677", 4.2);
        shortPath.clear();
        lenpath = Algorithms.shortestPath(completeMap, "Roma", "12345", shortPath);
        assertTrue(lenpath == 14.5, "Path between Roma and 12345 should now be 14.5 Km");
        assertTrue(shortPath.size() == 5, "Path between Roma and 12345 should be 5 ");

        it = shortPath.iterator();

        assertTrue(it.next().compareTo("Roma") == 0, "First in path should be Roma");
        assertTrue(it.next().compareTo("66677") == 0, "then 6667");
        assertTrue(it.next().compareTo("Madrid") == 0, "then Madrid");
        assertTrue(it.next().compareTo("77777") == 0, "then 77777");
        assertTrue(it.next().compareTo("12345") == 0, "then 12345");

        Assertions.assertEquals(0, Algorithms.shortestPath(completeMap,"Luanda","Marte",shortPath));
    }


/**
     * Test of shortestPaths method, of class Algorithms.
     */

    @Test
    public void testShortestPaths() {
        System.out.println("Test of shortest path");

        ArrayList<LinkedList<String>> paths = new ArrayList<>();
        ArrayList<Double> dists = new ArrayList<>();

        Algorithms.shortestPaths(completeMap, "Lisboa", paths, dists);

        assertEquals(paths.size(), dists.size());
        assertEquals(completeMap.numVertices(), paths.size());
        assertEquals(1, paths.get(completeMap.key("Lisboa")).size());
        assertEquals(Arrays.asList("Lisboa", "77777", "66677", "Roma"), paths.get(completeMap.key("Roma")));
        assertEquals(Arrays.asList("Lisboa", "77777", "Madrid"), paths.get(completeMap.key("Madrid")));
        assertEquals(15, dists.get(completeMap.key("Roma")), 0.01);

        //Changing Edge: 77777-66677 with Edge: Madrid-66677
        //should change shortest path between Roma and 12345
        completeMap.removeEdge("77777", "66677");
        completeMap.addEdge("Madrid", "66677", 4.2);
        Algorithms.shortestPaths(completeMap, "Lisboa", paths, dists);
        assertEquals(17.2, dists.get(completeMap.key("Roma")), 0.01);
        assertEquals(Arrays.asList("Lisboa", "77777", "Madrid","66677", "Roma"), paths.get(completeMap.key("Roma")));

        Algorithms.shortestPaths(completeMap, "12345", paths, dists);
        assertEquals(Double.MAX_VALUE, dists.get(completeMap.key("aaaaa")), 0.01);
        assertEquals(5.5, dists.get(completeMap.key("Lisboa")), 0.01);
        assertEquals(Arrays.asList("12345", "Lisboa"), paths.get(completeMap.key("Lisboa")));
        assertEquals(9.5, dists.get(completeMap.key("66677")), 0.01);

        assertFalse(Algorithms.shortestPaths(completeMap,"Luanda",paths,dists));
        Algorithms.shortestPaths(completeMap, "66677", paths, dists);
        assertEquals(12.2, dists.get(completeMap.key("Lisboa")), 0.01, "Path between Braga and Leiria should be 255 Km");
        assertEquals(Arrays.asList("66677", "Madrid", "77777", "Lisboa"), paths.get(completeMap.key("Lisboa")));
    }
}
