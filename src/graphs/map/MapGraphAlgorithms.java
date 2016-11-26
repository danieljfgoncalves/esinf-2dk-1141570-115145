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
     * Obtains the ArrayList with all paths from origin vertex to destination
     * vertex
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
     * Computes shortest path distance from origin vertex (source vertex) to all
     * reachable vertices of a graph with nonnegative edge weights. This
     * implementation uses Dijkstra's algorithm
     *
     * @param graph Graph instance
     * @param vOrig origin vertex that will be the source of the path
     * @param visited set of visited vertices
     * @param pathkeys minimum path vertices keys
     * @param dist array of doubles of minimum distances
     */
    private static <V, E> void shortestPathLength(MapGraph<V, E> graph, V vOrig, V[] vertices,
            boolean[] visited, int[] pathKeys, double[] dist) {

        for (V v : vertices) {
            dist[graph.getKey(v)] = Double.POSITIVE_INFINITY;
            pathKeys[graph.getKey(v)] = -1;
            visited[graph.getKey(v)] = false;
        }

        dist[graph.getKey(vOrig)] = 0;

        while (vOrig != null) {
            int vOrigValue = graph.getKey(vOrig);
            visited[vOrigValue] = true;

            for (Edge<V, E> edge : graph.outgoingEdges(vOrig)) {
                V vAdj = graph.opposite(vOrig, edge);
                if (!visited[graph.getKey(vAdj)] && dist[graph.getKey(vAdj)] > dist[vOrigValue] + edge.getWeight()) {
                    dist[graph.getKey(vAdj)] = dist[vOrigValue] + edge.getWeight();
                    pathKeys[graph.getKey(vAdj)] = vOrigValue;
                }
            }

            vOrig = null;
            double minimumDistance = Double.POSITIVE_INFINITY;

            for (V ver : vertices) {
                int vId = graph.getKey(ver);
                if (visited[vId] == false && dist[vId] < minimumDistance) {
                    vOrig = ver;
                    minimumDistance = dist[vId];
                }
            }
        }
    }

    /**
     * Extracts from pathKeys the minimum path between vOrig and vDest. The path
     * is constructed from the end to the beginning
     *
     * @param graph Graph instance
     * @param vOrig origin vertex
     * @param vDest destination vertex
     * @param pathkeys array of integers of minimum path vertices keys
     * @param path stack with the minimum path (correct order)
     */
    private static <V, E> void getPath(MapGraph<V, E> graph, V vOrig, V vDest, V[] verts, int[] pathKeys, LinkedList<V> path) {

        int vDestID = graph.getKey(vDest);

        int prevID = pathKeys[vDestID];
        V prevV = null;

        for (V v : verts) {
            if (graph.getKey(v) == prevID) {
                prevV = v;
            }
        }
        path.add(vDest);

        if (!vOrig.equals(vDest)) {
            getPath(graph, vOrig, prevV, verts, pathKeys, path);
        }
    }

    /**
     * Reverses the path
     *
     * @param <V> vertex
     * @param <E> edge
     * @param path the linked list with vertices that represent a path that is
     * passed as parameter to be reversed
     *
     * @return the reversed path
     */
    private static <V, E> LinkedList<V> revPath(LinkedList<V> path) {

        LinkedList<V> pathCopy = new LinkedList<>(path);
        LinkedList<V> pathRev = new LinkedList<>();

        while (!pathCopy.isEmpty()) {
            pathRev.push(pathCopy.pop());
        }

        return pathRev;
    }

    /**
     * Obtains shortest path distance from origin vertex (source vertex) to
     * destination vertex.
     *
     * @param <V> vertex
     * @param <E> edge
     * @param graph the graph
     * @param vOrig the origin vertex
     * @param vDest the destination vertex
     * @param shortPath the linked list with shortest path
     *
     * @return the shortest path distance from origin vertex (source vertex) to
     * destination vertex.
     */
    public static <V, E> double shortestPath(MapGraph<V, E> graph, V vOrig, V vDest, LinkedList<V> shortPath) {

        if (!graph.validVertex(vOrig) || !graph.validVertex(vDest)) {
            return -1d;
        }

        int numVert = graph.numVertices();

        V[] vertices = (V[]) graph.allkeyVerts().clone();
        boolean visited[] = new boolean[numVert];
        int[] pathKeys = new int[numVert];
        double[] dist = new double[numVert];

        shortestPathLength(graph, vOrig, vertices, visited, pathKeys, dist);
        shortPath.clear();
        if (!visited[graph.getKey(vDest)]) {
            return -1d;
        }
        getPath(graph, vOrig, vDest, vertices, pathKeys, shortPath);

        LinkedList<V> pathInOrder = revPath(shortPath);
        shortPath.clear();
        while (!pathInOrder.isEmpty()) {
            shortPath.add(pathInOrder.removeFirst());
        }

        int vDestId = graph.getKey(vDest);
        if (!visited[vDestId]) {
            return -1d;
        }

        return dist[vDestId];
    }

}
