package lapr.project.utils.stores;


import lapr.project.data.*;
import lapr.project.model.*;

import lapr.project.utils.Pair;
import lapr.project.utils.matrix.Algorithms;
import lapr.project.utils.matrix.MatrixGraph;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NetworkStore {


    private MatrixGraph grafo;
    private ArrayList<Port> portList;
    private ArrayList<Country> countryList;

    public NetworkStore() {
        // It is not needed to inicialize anything
    }

    public String createGraph(int n) throws SQLException {
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = ConnectionFactory.getInstance()
                    .getDatabaseConnection();
        } catch (IOException exception) {
            Logger.getLogger(ShipStoreDB.class.getName())
                    .log(Level.SEVERE, null, exception);
        }

        PortStoreDB pdb = new PortStoreDB();
        portList=pdb.readPortsfromDB(databaseConnection);

        PortStore ps = new PortStore();
        ps.saveListPortinKdt(portList);


        SeaDistanceStoreDB sdb = new SeaDistanceStoreDB();
        ArrayList<SeaDistance> seaDistanceList = sdb.readSeaDistancefromDB(databaseConnection);


        CountryStoreDB cdb = new CountryStoreDB();
        countryList=cdb.readCountryfromDB(databaseConnection);



        BorderStoreDB bdb = new BorderStoreDB();
        ArrayList<Border> borderList = bdb.readBorderfromDB(databaseConnection);


        ArrayList<String> vertices = new ArrayList<>();

        //Get all ports and capitals into the vertices array

        fillVertices(vertices,portList,countryList);

        //Fill matrix arestas

        double[][] arestas= new double[vertices.size()][vertices.size()];
        fillArestas(arestas,vertices,portList,countryList,seaDistanceList,borderList,n,ps);

        grafo=new MatrixGraph(false,vertices,arestas);
        return grafo.toString();
    }

    private void fillVertices(ArrayList<String> vertices,ArrayList<Port> portList,ArrayList<Country> countryList){
        for(Country c : countryList){
            for(Port p: portList){
                if(Objects.equals(p.getCountry(), c.getCountry())){
                    vertices.add(p.getId());
                }
            }
            vertices.add(c.getCapital());
        }
    }

    private void fillArestas(double[][] arestas,ArrayList<String> vertices,ArrayList<Port> portList,ArrayList<Country> countryList,ArrayList<SeaDistance> seaDistanceList,ArrayList<Border> borderList,int n,PortStore ps){
        //Connect the captials of the respective borders
        Port port=null;
        String lat1=null;
        String lon1=null;
        String lat2=null;
        String lon2=null;
        String capital1=null;
        String capital2=null;
        for (Border b:borderList){
            for(Country c : countryList) {
                if(Objects.equals(c.getCountry(), b.getCountry1())){
                    capital1=c.getCapital();
                    lat1=c.getLatitude();
                    lon1=c.getLongitude();
                }else if(Objects.equals(c.getCountry(), b.getCountry2())){
                    capital2=c.getCapital();
                    lat2=c.getLatitude();
                    lon2=c.getLongitude();
                }
            }
            if(vertices.contains(capital1) && vertices.contains(capital2)) {
                double result = getDistanceBetweenTwoPoints(lat1, lon1, lat2, lon2);
                arestas[vertices.indexOf(capital1)][vertices.indexOf(capital2)] = result;
                arestas[vertices.indexOf(capital2)][vertices.indexOf(capital1)] = result;
            }
        }

        //Connect the capital of a country to its closest port
        for(Country c:countryList) {
            port = ps.getClosestPort(c.getLatitude(), c.getLongitude());
            double result = getDistanceBetweenTwoPoints(c.getLatitude(),c.getLongitude(),port.getLatitude(),port.getLongitude());
            arestas[vertices.indexOf(port.getId())][vertices.indexOf(c.getCapital())]=result;
            arestas[vertices.indexOf(c.getCapital())][vertices.indexOf(port.getId())]=result;
        }
        //Connect all the ports of a country
        for(SeaDistance sd:seaDistanceList){
            if (vertices.contains(sd.getFromPortID()) && vertices.contains(sd.getToPortID()) && Objects.equals(sd.getFromCountry(), sd.getToCountry())) {
                double result = Double.parseDouble(sd.getSeaDistance());
                arestas[vertices.indexOf(sd.getFromPortID())][vertices.indexOf(sd.getToPortID())] = result;
                arestas[vertices.indexOf(sd.getToPortID())][vertices.indexOf(sd.getToPortID())] = result;
            }
        }

        //Connect each port of a country with the n closest ports of any other country
        Collections.sort(seaDistanceList);
        for(Port p:portList){
            int x=0;
            for(SeaDistance sd:seaDistanceList){
                if (x < n && vertices.contains(sd.getFromPortID()) && vertices.contains(sd.getToPortID()) && Objects.equals(sd.getFromPortID(), p.getId()) && !Objects.equals(sd.getFromCountry(), sd.getToCountry())) {
                    x++;
                    double result = Double.parseDouble(sd.getSeaDistance());
                    arestas[vertices.indexOf(sd.getFromPortID())][vertices.indexOf(sd.getToPortID())] = result;
                    arestas[vertices.indexOf(sd.getToPortID())][vertices.indexOf(sd.getFromPortID())] = result;
                }
            }
        }
    }



    public String colourMap() throws SQLException {
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = ConnectionFactory.getInstance()
                    .getDatabaseConnection();
        } catch (IOException exception) {
            Logger.getLogger(ShipStoreDB.class.getName())
                    .log(Level.SEVERE, null, exception);
        }
        CountryStoreDB cdb = new CountryStoreDB();
        countryList = cdb.readCountryfromDB(databaseConnection);
        BorderStoreDB bdb = new BorderStoreDB();
        ArrayList<Border> borderList =bdb.readBorderfromDB(databaseConnection);

        ArrayList<String> vertices = new ArrayList<>();
        for(Country c : countryList){
            vertices.add(c.getCountry());
        }
        double[][] arestas= new double[vertices.size()][vertices.size()];
        String lat1=null;
        String lon1=null;
        String lat2=null;
        String lon2=null;
        for (Border b:borderList){
            for(Country c : countryList) {
                if(Objects.equals(c.getCountry(), b.getCountry1())){
                    lat1=c.getLatitude();
                    lon1=c.getLongitude();
                }else if(Objects.equals(c.getCountry(), b.getCountry2())){
                    lat2=c.getLatitude();
                    lon2=c.getLongitude();
                }
            }
            if(vertices.contains(b.getCountry1()) && vertices.contains(b.getCountry2())) {
                double result = getDistanceBetweenTwoPoints(lat1, lon1, lat2, lon2);
                arestas[vertices.indexOf(b.getCountry1())][vertices.indexOf(b.getCountry2())] = result;
                arestas[vertices.indexOf(b.getCountry2())][vertices.indexOf(b.getCountry1())] = result;
            }
        }

        MatrixGraph la = new MatrixGraph(false,vertices,arestas);

        return la.colourMap();
    }

    public double getDistanceBetweenTwoPoints(String lat1, String log1, String lat2, String log2){
        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(Double.parseDouble(lat2) - Double.parseDouble(lat1));
        double lonDistance = Math.toRadians(Double.parseDouble(log2) - Double.parseDouble(log1));
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(Double.parseDouble(lat1))) * Math.cos(Math.toRadians(Double.parseDouble(lat2)))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;


        distance = Math.pow(distance, 2);

        return (Math.sqrt(distance));
    }

    /**
     * This method uses the algorithm of dijkstra.
     * @param n number of places per continent
     * @return list of pairs, as first the continent and second another pair, as first the port or country and second the average of the shortest path length.
     * @throws SQLException
     */
    public List<Pair<String,Pair<Object, Double>>> getNClosestPlaces(int n) throws SQLException {
        if(n>0){
            List<Pair<String,Pair<Object, Double>>> listPlaceDistance = new ArrayList<>();
            Map<String, List<String>> map = new HashMap<>();
            List<String> continents = new ArrayList<>();
            List<String> ids = new ArrayList<>();
            for(Port port: portList){
                String continent= port.getContinent();
                if(!continents.contains(continent))
                    continents.add(continent);
                ids.add(port.getId());
            }
            for(Country country: countryList){
                String continent= country.getContinent();
                if(!continents.contains(continent))
                    continents.add(continent);
            }
            for(String continent: continents){
                for(Port port: portList){
                    if(port.getContinent().equals(continent)){
                        if(!map.containsKey(continent))
                            map.put(continent, new ArrayList<>());
                        map.get(continent).add(port.getId());
                    }
                }
                for(Country country: countryList){
                    if(country.getContinent().equals(continent)){
                        if(!map.containsKey(continent))
                            map.put(continent, new ArrayList<>());
                        map.get(continent).add(country.getCapital());
                    }
                }
            }
            int numTemp;
            double dist;
            for(String continent: continents){
                numTemp=n;
                List<Pair<String, Double>> temp = new ArrayList<>();
                for(String obj: map.get(continent)){
                    dist=0;
                    for(String obj2: map.get(continent)){
                        LinkedList<String> shortPath=new LinkedList<>();
                        dist+=Algorithms.shortestPath(grafo,obj,obj2,shortPath);
                    }
                    dist/=map.get(continent).size()-1;
                    if(dist!=0){
                        Pair<String, Double> temp2=new Pair<>(obj,dist);
                        temp.add(temp2);
                    }
                }
                temp.sort(new Comparator<Pair<String,Double>>() {
                    @Override
                    public int compare(Pair<String, Double> o1, Pair<String, Double> o2) {
                        double num= o1.second-o2.second;
                        if (num < 0) {
                            return -1;
                        } else if (num > 0) {
                            return 1;
                        } else return 0;
                    }
                });
                while(numTemp!=0){
                    if(ids.contains(temp.get(0).first)){
                        Port porto=null;
                        for(Port port:portList){
                            if(port.getId().equals(temp.get(0).first)){
                                porto = port;
                                break;
                            }
                        }
                        Pair<String,Pair<Object, Double>> arr = new Pair<>(porto.getContinent(),new Pair<>(porto,temp.get(0).second));
                        listPlaceDistance.add(arr);
                    }else{
                        Country pais = null;
                        for(Country country:countryList){
                            if(country.getCapital().equals(temp.get(0).first)){
                                pais = country;
                                break;
                            }
                        }
                        Pair<String,Pair<Object, Double>> arr = new Pair<>(pais.getContinent(),new Pair<>(pais,temp.get(0).second));
                        listPlaceDistance.add(arr);
                    }
                    temp.remove(0);
                    numTemp--;
                }
            }
            return listPlaceDistance;
        }
        return Collections.emptyList();
    }

    public String shortestPathBetweenTwoLocals(String local1,String local2,int n){
        String result;
        LinkedList<String> shortPath=new LinkedList<>();
        switch(n) {
            case 1:
                if(!isNumeric(local1) && isNumeric(local2) ||isNumeric(local1) && !isNumeric(local2) || !isNumeric(local1) && !isNumeric(local2)){
                  double distance =Algorithms.shortestPath(grafo,local1,local2,shortPath);
                  result="The shortest path between "+local1+" and "+local2+" by land is "+distance+" and its path is \n"+shortPath;
                  if (Double.MAX_VALUE==distance||distance==0.0){
                      result="There is no connection by land between "+local1 +" and "+local2+".";
                  }
                }else
                result="There is no connection by land between "+local1 +" and "+local2+".";
                break;
            case 2:
                if(isNumeric(local1) && isNumeric(local2)){
                    double distance =Algorithms.shortestPath(grafo,local1,local2,shortPath);
                    result="The shortest path between "+local1+" and "+local2+" by sea is "+distance+" and its path is \n"+shortPath;
                    if (Double.MAX_VALUE==distance||distance==0.0){
                        result="There is no connection by sea between "+local1 +" and "+local2+".";
                    }
                }else{
                    result="There is no connection by sea between "+local1 +" and "+local2+".";
                }
                break;
            case 3:
                double distance =Algorithms.shortestPath(grafo,local1,local2,shortPath);
                result="The shortest path between "+local1+" and "+local2+" is "+distance+" and its path is \n"+shortPath;
                if (Double.MAX_VALUE==distance||distance==0.0){
                    result="There is no connection between "+local1 +" and "+local2+".";
                }
                break;
            default:
                result="That option isn't available";
        }
        return result;
    }

    public String shortestPathBetweenTwoLocals4(String local1,String local2,ArrayList<String> locais){
        String result;
        ArrayList<String> caminho=new ArrayList<>();
        ArrayList<String> locaistemp = new ArrayList<>(locais);
        LinkedList<String> shortPath=new LinkedList<>();
        double distance = Double.MAX_VALUE;
        double answer= Double.MAX_VALUE;
        //verifies if the given list is null
        if (locais.isEmpty()){
            distance =Algorithms.shortestPath(grafo,local1,local2,shortPath);
            result="The shortest path between "+local1+" and "+local2+" is "+distance+" and its path is \n"+shortPath;
            if (Double.MAX_VALUE==distance || distance==0.0){
                result="There is no connection between "+local1 +" and "+local2+".";
            }
            return result;
        }

        double[][] shortpaths= new double[locais.size()][locais.size()];
        ArrayList<Double> valores = new ArrayList<>();

        //fills the array shortpaths with all the possible pairs
        for (int i=0;i<locais.size();i++){
            for (int x=0;x<locais.size();x++){
                distance =Algorithms.shortestPath(grafo,locais.get(i),locais.get(x),shortPath);
                shortpaths[i][x]=distance;
            }
        }
        int c=0;
        int x=0;
        permute(locais,locaistemp,caminho,shortpaths,valores,0, locais.size()-1,local1,local2,shortPath);

        for (double d:valores){
            if (d<answer){
                x=c;
                answer=d;
            }
            c++;
        }
        if (Double.MAX_VALUE==answer){
            result="There is no connection between "+local1 +" and "+local2+".";
        }
        result="The shortest path between "+local1+" and "+local2+" is "+answer+" and its path is \n"+caminho.get(x);
        return result;
    }

    private void permute(ArrayList<String> locais,ArrayList<String> locaistemp,ArrayList<String> caminho,double[][] shortpaths,ArrayList<Double> result, int l, int r,String local1,String local2,LinkedList<String> shortPath)
    {
        double distance=0;
        StringBuilder path= new StringBuilder();
        if (l == r) {
            path.append(local1);
            for (int x = 0; x < locais.size() - 1; x++) {
                path.append(",").append(locais.get(x));
                distance=distance+shortpaths[locaistemp.indexOf(locais.get(x))][locaistemp.indexOf(locais.get(x + 1))];
            }
            path.append(",").append(locais.get(locais.size() - 1)).append(",").append(local2);
            distance=distance+Algorithms.shortestPath(grafo,local1,locais.get(0),shortPath);
            distance=distance+Algorithms.shortestPath(grafo,local1,locais.get(locais.size()-1),shortPath);
            result.add(distance);
            caminho.add(path.toString());
        }
        else{
            for (int i = l; i <= r; i++)
            {
                swap(locais,shortpaths,l,i);
                permute(locais,locaistemp,caminho,shortpaths,result,l+1, r,local1,local2,shortPath);
                swap(locais,shortpaths,l,i);
            }
        }
    }

    public static void swap(ArrayList<String> locais,double[][] shortpaths, int i, int j)
    {
        String temp;
        temp = locais.get(i) ;
        locais.set(i,locais.get(j));
        locais.set(j,temp);
    }

    public void setPortList(List<Port> list){
        portList= (ArrayList<Port>) list;
    }

    public void setCountryList(List<Country> list){
        countryList= (ArrayList<Country>) list;
    }

    public void setGrafo(MatrixGraph graph){
        grafo=graph;
    }
    
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public Pair<LinkedList<Object>, Double> getMostEfficientCircuit(String sourceLocation){
        LinkedList<Object> circuit = new LinkedList<>();
        circuit.add(sourceLocation);
        Algorithms.depthFirstSearch(grafo, sourceLocation, circuit);
        for(int i=0; i< circuit.size();i++){
            if(grafo.edge(circuit.get(i), sourceLocation)!=null){
                circuit.addFirst(sourceLocation);
                i= circuit.size();
            }else{
                circuit.removeFirst();
                i--;
            }
        }
        Double distance=0.0;
        for(int i=1; i<circuit.size();i++){
            distance += grafo.edge(circuit.get(i-1),circuit.get(i)).getWeight();
        }
        return new Pair<>(circuit, distance);
    }

    public String getCriticalPort(int n){
        if(grafo == null){
            return "";
        }
        ArrayList<String> vert = grafo.vertices();
        String ports = "";
        int[] count = new int[grafo.numVertices()];
        for(String v : vert){
            ArrayList<LinkedList<String>> paths = new ArrayList<>(new LinkedList<>());
            ArrayList<Double> dists = new ArrayList<>();
            Algorithms.shortestPaths(grafo, v, paths, dists);
            for(LinkedList<String> list: paths){
                for(String v1: list){
                    if(isNumeric(v1)) {
                        count[vert.indexOf(v1)]++;
                    }
                }
            }
        }
        for(int i=1; i<=n;i++){
            ports += String.format("%dº Port -> %s\n", i, vert.get(getMaxValue(count)));
            count[getMaxValue(count)] = 0;
        }
        return ports;
    }


    public int getMaxValue(int[] list) {
        int max = -1;
        int maxIndex = -1;
        for (int i = 0; i < list.length; i++) {
            if (list[i] > max) {
                max = list[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

}
