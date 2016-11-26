package graphs.map;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Represents the algorithms to manipulate a map graph.
 *
 * @author Daniel Gonçalves 1151452@isep.ipp.pt
 * @author Eric Jorge Schipper Ferreira do Amaral 1141570@isep.ipp.pt
 */
public class MapGraphAlgorithms {

    /**
     * Performs breadth-first search of a Graph starting in a Vertex, obtaining
     * a queue of vertices of BFS
     *
     * @param <V> vertex
     * @param <E> edge
     * @param graph Graph instance
     * @param vertex the vertex
     *
     * @return qbfs a queue with the vertices of breadth first search (BFS)
     */
    public static <V, E> LinkedList<V> BreadthFirstSearch(MapGraph<V, E> graph, V vertex) {

        if (!graph.validVertex(vertex)) {
            return null;
        }

        LinkedList<V> qbfs = new LinkedList<>();
        LinkedList<V> qaux = new LinkedList<>();
        boolean[] visited = new boolean[graph.numVertices()];  //default initialized as false

        qbfs.add(vertex);
        qaux.add(vertex);
        int vKey = graph.getKey(vertex);
        visited[vKey] = true;

        while (!qaux.isEmpty()) {
            vertex = qaux.remove();
            for (Edge<V, E> edge : graph.outgoingEdges(vertex)) {
                V vAdj = graph.opposite(vertex, edge);
                vKey = graph.getKey(vAdj);
                if (!visited[vKey]) {
                    qbfs.add(vAdj);
                    qaux.add(vAdj);
                    visited[vKey] = true;
                }
            }
        }
        return qbfs;
    }

    /**
     * Performs depth-first search starting in a Vertex
     *
     * @param graph the graph where the DFS will occur
     * @param vOrig the origin vertex in the graph where the DFS will start (the
     * source of the search)
     * @param visited Set of vertices
     * @param qdfs queue with vertices of depth first search(DFS) (queue with
     * the output vertices)
     */
    private static <V, E> void DepthFirstSearch(MapGraph<V, E> graph, V vOrig, boolean[] visited, LinkedList<V> qdfs) {

        visited[graph.getKey(vOrig)] = true;

        for (Edge<V, E> edge : graph.outgoingEdges(vOrig)) {
            V vAdj = graph.opposite(vOrig, edge);
            int vKey = graph.getKey(vAdj);
            if (!visited[vKey]) {
                qdfs.add(vAdj);
                DepthFirstSearch(graph, vAdj, visited, qdfs);
            }
        }
    }

    /**
     * @param <V> vertex
     * @param <E> edge
     * @param graph the graph where the DFS will occur
     * @param vert the origin vertex in the graph where the DFS will start (the
     * source of the search)
     *
     * @return qdfs a queue with the vertices of depth-first search
     */
    public static <V, E> LinkedList<V> DepthFirstSearch(MapGraph<V, E> graph, V vert) {

        if (!graph.validVertex(vert)) {
            return null;
        }

        LinkedList<V> qdfs = new LinkedList<V>();
        qdfs.add(vert);
        boolean[] knownVertices = new boolean[graph.numVertices()];
        DepthFirstSearch(graph, vert, knownVertices, qdfs);
        return qdfs;
    }

    /**
     *
     * @param graph Graph instance
     * @param vOrig the origin vertex that will be the source of the path
     * @param vDest the destination vertex that will be the end of the path
     * @param visited Set of vertices
     * @param path stack with vertices of the current path (the path is in
     * reverse order)
     * @param paths ArrayList with all the paths (in correct order)
     */
    private static <V, E> void allPaths(MapGraph<V, E> graph, V vOrig, V vDest, boolean[] visited,
            LinkedList<V> path, ArrayList<LinkedList<V>> paths) {

        visited[graph.getKey(vOrig)] = true;
        path.add(vOrig);

        for (Edge<V, E> edge : graph.outgoingEdges(vOrig)) {
            V vAdj = graph.opposite(vOrig, edge);
            if (vAdj.equals(vDest)) {
                path.add(vDest);
                paths.add((LinkedList<V>) path.clone());
                path.removeLast();
            } else {
                if (!visited[graph.getKey(vAdj)]) {
                    allPaths(graph, vAdj, vDest, visited, path, paths);
                }
            }
        }
        visited[graph.getKey(vOrig)] = false;
        path.removeLast();
    }

    /**
     * Obtains the ArrayList with all paths from origin vertex to destination vertex
     * 
     * @param <V> vertex
     * @param <E> edge
     * @param graph the graph
     * @param vOrig the origin vertex
     * @param vDest the destination vertex
     * 
     * @return paths the ArrayList with all paths from vOrig to vDest
     */
    public static <V, E> ArrayList<LinkedList<V>> allPaths(MapGraph<V, E> graph, V vOrig, V vDest) {

        ArrayList<LinkedList<V>> paths = new ArrayList();

        if (!graph.validVertex(vOrig)) {
            return paths;
        }

        if (!graph.validVertex(vDest)) {
            return paths;
        }

        boolean[] knownVertices = new boolean[graph.numVertices()];
        LinkedList<V> path = new LinkedList<>();

        allPaths(graph, vOrig, vDest, knownVertices, path, paths);

        return paths;
    }

    /**
     * Computes shortest-path distance from a source vertex to all reachable
     * vertices of a graph g with nonnegative edge weights This implementation
     * uses Dijkstra's algorithm
     *
     * @param g Graph instance
     * @param vOrig Vertex that will be the source of the path
     * @param visited set of discovered vertices
     * @param pathkeys minimum path vertices keys
     * @param dist minimum distances
     */
    private static <V, E> void shortestPathLength(MapGraph<V, E> g, V vOrig, V[] vertices,
            boolean[] visited, int[] pathKeys, double[] dist) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Extracts from pathKeys the minimum path between voInf and vdInf The path
     * is constructed from the end to the beginning
     *
     * @param g Graph instance
     * @param voInf information of the Vertex origin
     * @param vdInf information of the Vertex destination
     * @param pathkeys minimum path vertices keys
     * @param path stack with the minimum path (correct order)
     */
    private static <V, E> void getPath(MapGraph<V, E> g, V vOrig, V vDest, V[] verts, int[] pathKeys, LinkedList<V> path) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    //shortest-path between voInf and vdInf
    public static <V, E> double shortestPath(MapGraph<V, E> g, V vOrig, V vDest, LinkedList<V> shortPath) {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Reverses the path
     *
     * @param path stack with path
     */
    private static <V, E> LinkedList<V> revPath(LinkedList<V> path) {

        LinkedList<V> pathcopy = new LinkedList<>(path);
        LinkedList<V> pathrev = new LinkedList<>();

        while (!pathcopy.isEmpty()) {
            pathrev.push(pathcopy.pop());
        }

        return pathrev;
    }
}
