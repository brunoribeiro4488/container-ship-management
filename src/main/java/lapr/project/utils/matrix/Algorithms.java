package lapr.project.utils.matrix;

import java.util.*;

/**
 *
 * @author DEI-ISEP
 *
 */
public class Algorithms {

    private Algorithms(){}

    /**
     * Computes shortest-path distance from a source vertex to all reachable
     * vertices of a graph g with non-negative edge weights
     * This implementation uses Dijkstra's algorithm
     *
     * @param g        Graph instance
     * @param vOrig    Vertex that will be the source of the path
     * @param visited set of discovered vertices
     * @param pathKeys minimum path vertices keys
     * @param dist minimum distances
     */
    protected static <V, E> void shortestPathLength(Graph<V, E> g, V vOrig, boolean[] visited, int[] pathKeys, double[] dist) {

        int vOrigKey = g.key(vOrig);
        dist[vOrigKey] = 0;
        while (vOrigKey != -1) {
            visited[vOrigKey] = true;
            for (V vAdj : g.adjVertices(g.vertex(vOrigKey))) {
                Edge<V, E> edge = g.edge(g.vertex(vOrigKey), vAdj);
                int vAdjKey = g.key(vAdj);
                if (!visited[vAdjKey] && dist[vAdjKey] > dist[vOrigKey] + edge.getWeight()) {
                    dist[vAdjKey] = dist[vOrigKey] + edge.getWeight();
                    pathKeys[vAdjKey] = vOrigKey;
                }
            }
            vOrigKey = getVertMinDist(dist, visited);
        }
    }

    /**
     *
     * @param dist list of distances
     * @param visited list of visited vertices
     * @return Index of the vertex with minimum distance.
     */
    public static int getVertMinDist(double[] dist, boolean[] visited) {
        double min = Double.MAX_VALUE;
        int minVertIdx = -1;
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i] && min > dist[i]) {
                min = dist[i];
                minVertIdx = i;
            }
        }
        return minVertIdx;
    }

    /** Shortest-path between a vertex and all other vertices
     *
     * @param g graph
     * @param vOrig start vertex
     * @param paths returns all the minimum paths
     * @param dists returns the corresponding minimum distances
     * @return if vOrig exists in the graph true, false otherwise
     */
    public static <V, E> boolean shortestPaths(Graph<V, E> g, V vOrig, ArrayList<LinkedList<V>> paths, ArrayList<Double> dists) {
        dists.clear();
        paths.clear();
        if (!g.validVertex(vOrig)) {
            return false;
        }

        int nverts = g.numVertices();
        boolean[] visited = new boolean[nverts]; //default value: false
        int[] pathKeys = new int[nverts];
        double[] dist = new double[nverts];
        ArrayList<V> vertices = g.vertices();

        for (int i = 0; i < nverts; i++) {
            dist[i] = Double.MAX_VALUE;
            pathKeys[i] = -1;
        }

        shortestPathLength(g, vOrig, visited, pathKeys, dist);

        for (int i = 0; i < nverts; i++) {
            paths.add(null);
            dists.add(null);
        }
        for (int i = 0; i < nverts; i++) {
            LinkedList<V> shortPath = new LinkedList<>();

            if (Double.compare(dist[i], Double.MAX_VALUE) != 0) {
                getPath(g, vOrig, vertices.get(i), vertices, pathKeys, shortPath);
            }
            paths.set(i, shortPath);
            dists.set(i, dist[i]);
        }
        return true;
    }

    /**
     * Extracts from pathKeys the minimum path between voInf and vdInf
     * The path is constructed from the end to the beginning
     *
     * @param g        Graph instance
     * @param vOrig    information of the Vertex origin
     * @param vDest    information of the Vertex destination
     * @param pathKeys minimum path vertices keys
     * @param path     stack with the minimum path (correct order)
     */
    protected static <V, E> void getPath(Graph<V, E> g, V vOrig, V vDest, ArrayList<V> verts, int[] pathKeys, LinkedList<V> path) {
        path.push(vDest);
        int vKey = pathKeys[g.key(vDest)];
        if (vKey != -1) {
            vDest = verts.get(vKey);
            getPath(g, vOrig, vDest, verts, pathKeys, path);
        }

    }

    /** Shortest-path between two vertices
     *
     * @param g graph
     * @param vOrig origin vertex
     * @param vDest destination vertex
     * @param shortPath returns the vertices which make the shortest path
     * @return
     */
    public static <V, E> double shortestPath(Graph<V, E> g, V vOrig, V vDest, LinkedList<V> shortPath) {
        shortPath.clear();
        if (!g.validVertex(vOrig) || !g.validVertex(vDest)) {
            return 0;
        }

        if (vOrig.equals(vDest)) {
            shortPath.add(vDest);
            return 0;
        }

        int nverts = g.numVertices();
        boolean[] visited = new boolean[nverts]; //default value: false
        int[] pathKeys = new int[nverts];
        double[] dist = new double[nverts];
        ArrayList<V> verts = g.vertices();

        for (int i = 0; i < nverts; i++) {
            dist[i] = Double.MAX_VALUE;
            pathKeys[i] = -1;
        }
        shortestPathLength(g, vOrig, visited, pathKeys, dist);
        if (pathKeys[g.key(vDest)] == -1) {
            return 0;
        }
        getPath(g, vOrig, vDest, verts, pathKeys, shortPath);
        return dist[g.key(vDest)];
    }

    /**
     * Depth First Search Algorithm to make a circuit
     * @param graph graph
     * @param sourceLocation first place of the circuit
     * @param circuit circuit
     */
    public static <V,E> void depthFirstSearch(Graph<V, E> graph, V sourceLocation, LinkedList<V> circuit) {
        int vertices = graph.numVertices();
        boolean [] visited = new boolean[vertices];
        dfsRecursive(graph, sourceLocation, visited, graph.adjVertices(sourceLocation).iterator(), circuit);
    }

    /**
     * Depth First Search recursive algorithm
     * @param graph graph
     * @param current vertex
     * @param visited list of visited vertices
     * @param iterator iterator
     * @param circuit circuit
     */
    private static <V,E> void dfsRecursive(Graph<V, E> graph, V current, boolean [] visited, Iterator<V> iterator, LinkedList<V> circuit) {
        visited[graph.key(current)] = true;
        while (iterator.hasNext()) {
            V nextVertex = iterator.next();
            if (!visited[graph.key(nextVertex)] && graph.edge(circuit.getFirst(),nextVertex)!=null){
                dfsRecursive(graph, nextVertex, visited, graph.adjVertices(nextVertex).iterator(), circuit);
                circuit.addFirst(nextVertex);
            }
        }
    }

}
