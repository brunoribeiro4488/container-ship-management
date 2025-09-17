package lapr.project.controller;

import lapr.project.model.Country;
import lapr.project.model.Port;
import lapr.project.utils.Pair;
import lapr.project.utils.matrix.Graph;
import lapr.project.utils.matrix.MatrixGraph;
import lapr.project.utils.stores.NetworkStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlacesClosenessPlacesControllerTest {

    final MatrixGraph<String,String> completeMap = new MatrixGraph<>(false);
    final ArrayList<Port> ports = new ArrayList<>();
    final ArrayList<Country> countries = new ArrayList<>();

    @BeforeEach
    public void setUp() {

        completeMap.addVertex("Lisboa");
        completeMap.addVertex("Madrid");
        completeMap.addVertex("Roma");
        completeMap.addVertex("12345");
        completeMap.addVertex("77777");
        completeMap.addVertex("66677");
        completeMap.addVertex("aaaaa");

        completeMap.addEdge("Lisboa", "12345", 5.5);
        completeMap.addEdge("Lisboa", "77777", 6);
        completeMap.addEdge("12345", "77777", 3.3);
        completeMap.addEdge("Madrid", "77777", 2);
        completeMap.addEdge("77777", "66677", 4);
        completeMap.addEdge("Madrid", "Roma", 11);
        completeMap.addEdge("Roma", "66677", 5);

        ports.add(new Port("12345","port1","Europe","Portugal","0","0"));
        ports.add(new Port("66677","port2","Europe","Espanha","0","0"));
        ports.add(new Port("77777","port3","Europe","Italia","0","0"));
        countries.add(new Country("Europe","code","code","Portugal","pop","Lisboa","0","0"));
        countries.add(new Country("Europe","code1","code1","Espanha","pop1","Madrid","0","0"));
        countries.add(new Country("Europe","code2","code2","Italia","pop2","Roma","0","0"));

    }

    @Test
    void getNClosestPlaces() throws SQLException, IOException {
        PlacesClosenessPlacesController placesClosenessPlacesController = new PlacesClosenessPlacesController();
        NetworkStore networkStore = new NetworkStore();
        networkStore.setGrafo(completeMap);
        networkStore.setCountryList(countries);
        networkStore.setPortList(ports);
        placesClosenessPlacesController.setNetworkStore(networkStore);
        List<Pair<String, Pair<Object, Double>>> expected = placesClosenessPlacesController.getNClosestPlaces(2);
        List<Pair<String, Pair<Object,Double>>> result=new ArrayList<>();
        Pair<Object,Double> pair = new Pair<>(new Port("77777","port3","Europe","Italia","0","0"),4.86);
        Pair<Object,Double> pair1 = new Pair<>(new Port("66677","port2","Europe","Espanha","0","0"),6.459999999999999);
        Pair<String,Pair<Object,Double>> pairPair= new Pair<>("Europe",pair);
        Pair<String,Pair<Object,Double>> pairPair1= new Pair<>("Europe",pair1);
        result.add(pairPair);
        result.add(pairPair1);
        Assertions.assertEquals(expected,result);
    }
}