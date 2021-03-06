package graphs.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents an undirected Matrix Graph.
 *
 * @author Daniel Gonçalves 1151452@isep.ipp.pt
 * @author Eric Jorge Schipper Ferreira do Amaral 1141570@isep.ipp.pt
 * @param <V> Represents a Vertex
 * @param <E> Represents an Edge
 */
public class MatrixGraph<V, E> implements MatrixGraphInterface<V, E>, Cloneable {

    /**
     * Initial capacity of the graph.
     */
    public static final int INITIAL_CAPACITY = 10;

    /**
     * Factor in which the matrix is resized.
     */
    public static final float RESIZE_FACTOR = 1.5F;

    /**
     * Number of vertices
     */
    int numVertices;

    /**
     * Number of Edges.
     */
    int numEdges;

    /**
     * Collection of vertices.
     */
    ArrayList<V> vertices;

    /**
     * Edge Matrix.
     */
    E[][] edgeMatrix;

    /**
     * Returns the edge reference associated with edgeMatrix[x][y] position used
     * as workaround to work with edgeMatrix from the
     * WeightedMatrixGraphAlgorithms Class, as Java generic types are not
     * available at runtime.
     *
     * @param x selected line
     * @param y selected column
     *
     * @return edge at position (x,y)
     */
    E privateGet(int x, int y) {
        return edgeMatrix[x][y];
    }

    /*     0 1 2 3 
           A B C D
       0 A|_|_|_|_|
       1 B|_|_|_|_|
       2 C|a|_|_|_|
       3 D|_|_|_|_|  
     */
    /**
     * Set the edge reference associated with edgeMatrix[x][y] position used as
     * workaround to work with edgeMatrix from the WeightedMatrixGraphAlgorithms
     * Class, as Java generic types are not available at runtime.
     *
     * @param x selected line
     * @param y selected column
     * @param e the new reference
     */
    void privateSet(int x, int y, E e) {
        edgeMatrix[x][y] = e;
    }

    /**
     * Returns the index associated with a vertex.
     *
     * @param vertex selected vertex
     * @return vertex index or -1 if vertex does not exist in the graph
     */
    int toIndex(V vertex) {
        return vertices.indexOf(vertex);
    }

    /**
     * Resizes the matrix when a new vertex increases the length of the
     * ArrayList (Vertices)
     */
    private void resizeMatrix() {

        if (edgeMatrix.length < numVertices) {
            int newSize = (int) (edgeMatrix.length * RESIZE_FACTOR);

            @SuppressWarnings("unchecked")
            E[][] temp = (E[][]) new Object[newSize][newSize];

            for (int i = 0; i < edgeMatrix.length; i++) {

                temp[i] = Arrays.copyOf(edgeMatrix[i], newSize);
            }

            edgeMatrix = temp;
        }
    }

    /**
     * Constructs an empty matrix graph.
     */
    public MatrixGraph() {
        this(INITIAL_CAPACITY);
    }
    
    /**
     * Constructs a matrix graph with an initial capacity.
     *
     * @param initialSize initial size of the matrix
     */
    @SuppressWarnings("unchecked")
    public MatrixGraph(int initialSize) {

        vertices = new ArrayList<>(initialSize);
        edgeMatrix = (E[][]) new Object[initialSize][initialSize];
    }

    @Override
    public int numVertices() {
        return numVertices;
    }

    @Override
    public int numEdges() {
        return numEdges;
    }

    /**
     * Checks if a vertex exists.
     *
     * @param vertex selected vertex
     * @return true if exists
     */
    public boolean checkVertex(V vertex) {
        return (vertices.indexOf(vertex) != -1);
    }

    /**
     * Returns a vertex based on an index.
     *
     * @param i the selected index.
     * @return
     */
    public V getVertex(int i) {
        return vertices.get(i);
    }

    @Override
    public Iterable<V> vertices() {
        return (Iterable<V>) vertices.clone();
    }

    @Override
    public Iterable<E> edges() {

        ArrayList<E> edges = new ArrayList<>();

        // graph is undirected, so only return a single copy of edge
        // graph could actually only keep one copy of the edge but algorithms
        // would then need to consider that case.
        for (int i = 0; i < numVertices - 1; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (edgeMatrix[i][j] != null) {
                    edges.add(edgeMatrix[i][j]);
                }
            }
        }

        return edges;
    }

    @Override
    public int outDegree(V vertex) {

        int index = toIndex(vertex);
        if (index == -1) {
            return -1;
        }

        int edgeCount = 0;
        for (int i = 0; i < numVertices; i++) {
            if (edgeMatrix[index][i] != null) {
                edgeCount++;
            }
        }

        return edgeCount;
    }

    @Override
    public int inDegree(V vertex) {
        return outDegree(vertex);
    }

    /**
     * Returns an iterable collection of vertices directly connected to vertex
     *
     * @param vertex selected vertex
     *
     * @return collection of vertices connected to vertex, null if vertex does
     * not exist in the graph
     */
    public Iterable<V> directConnections(V vertex) {

        int index = toIndex(vertex);
        if (index == -1) {
            return null;
        }

        ArrayList<V> directConnections = new ArrayList<>();

        for (int col = 0; col < numVertices; col++) {
            if (edgeMatrix[index][col] != null) {
                directConnections.add(vertices.get(col));
            }
        }

        return directConnections;
    }

    @Override
    public Iterable<E> outgoingEdges(V vertex) {

        int index = toIndex(vertex);
        if (index == -1) {
            return null;
        }

        ArrayList<E> outgoingEdges = new ArrayList<>();

        for (int col = 0; col < numVertices; col++) {
            if (edgeMatrix[index][col] != null) {
                outgoingEdges.add(edgeMatrix[index][col]);
            }
        }

        return outgoingEdges;
    }

    @Override
    public Iterable<E> incomingEdges(V vertex) {
        return outgoingEdges(vertex);
    }

    @Override
    public E getEdge(V vertexA, V vertexB) {
        int indexA = toIndex(vertexA);
        if (indexA == -1) {
            return null;
        }

        int indexB = toIndex(vertexB);
        if (indexB == -1) {
            return null;
        }

        return edgeMatrix[indexA][indexB];
    }

    @Override
    public V[] endVertices(E edge) {

        for (int i = 0; i < numVertices - 1; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (edgeMatrix[i][j] != null) {
                    if (edgeMatrix[i][j].equals(edge)) {
                        @SuppressWarnings("unchecked")
                        V[] result = (V[]) new Object[2];
                        result[0] = vertices.get(i);
                        result[1] = vertices.get(j);
                        return result;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public boolean insertVertex(V newVertex) {
        int index = toIndex(newVertex);
        if (index != -1) {
            return false;
        }

        vertices.add(newVertex);
        numVertices++;
        resizeMatrix();
        return true;
    }

    /**
     * Inserts a new edge between two vertices. Package level method is for use
     * of algorithms class
     *
     * @param indexA first index
     * @param indexB second index
     * @param newEdge new Edge to insert
     *
     * @return false if vertices are not in the graph or are the same vertex or
     * an edge already exists between the two.
     */
    private void insertEdge(int indexA, int indexB, E newEdge) {
        edgeMatrix[indexA][indexB] = edgeMatrix[indexB][indexA] = newEdge; // undirected graph
        numEdges++;
    }

    @Override
    public boolean insertEdge(V vertexA, V vertexB, E newEdge) {

        if (vertexA.equals(vertexB)) {
            return false;
        }

        int indexA = toIndex(vertexA);
        if (indexA == -1) {
            return false;
        }

        int indexB = toIndex(vertexB);
        if (indexB == -1) {
            return false;
        }

        if (edgeMatrix[indexA][indexB] != null) {
            return false;
        }

        insertEdge(indexA, indexB, newEdge);

        return true;
    }

    /**
     * Removes a vertex and all its incoming/outgoing edges from the graph.
     *
     * @param vertex vertex
     * @return false if vertex does not exist in the graph
     */
    public boolean removeVertex(V vertex) {
        int index = toIndex(vertex);
        if (index == -1) {
            return false;
        }

        // first let's remove edges to/from the vertex
        for (int i = 0; i < numVertices; i++) {
            if (edgeMatrix[index][i] != null) {
                removeEdge(index, i);
            }
        }

        vertices.remove(index);
        numVertices--;

        // remove shifts left all vertices after the one removed
        // It is necessary to collapse the edge matrix
        // first the lines after line vertex removed
        for (int i = index; i < numVertices; i++) {
            for (int j = 0; j < edgeMatrix.length; j++) {
                edgeMatrix[i][j] = edgeMatrix[i + 1][j];
            }
        }

        for (int j = 0; j < edgeMatrix.length; j++) {
            edgeMatrix[numVertices][j] = null;
        }

        //second the columns after column vertex removed
        for (int i = index; i < numVertices; i++) {
            for (int j = 0; j < edgeMatrix.length; j++) {
                edgeMatrix[j][i] = edgeMatrix[j][i + 1];
            }
        }

        for (int j = 0; j < edgeMatrix.length; j++) {
            edgeMatrix[j][numVertices] = null;
        }

        return true;
    }

    /**
     * Removes the edge between two vertices Package level method is for use of
     * algorithms class
     *
     * @param indexA first index
     * @param indexB second index
     *
     * @return the edge or null if vertices are not in the graph or not
     * connected
     */
    private E removeEdge(int indexA, int indexB) {
        E edge = edgeMatrix[indexA][indexB];
        edgeMatrix[indexA][indexB] = edgeMatrix[indexB][indexA] = null; // undirected graph
        numEdges--;
        return edge;
    }

    @Override
    public E removeEdge(V vertexA, V vertexB) {
        int indexA = toIndex(vertexA);
        if (indexA == -1) {
            return null;
        }

        int indexB = toIndex(vertexB);
        if (indexB == -1) {
            return null;
        }

        return removeEdge(indexA, indexB);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Vertices:\n");
        for (int i = 0; i < numVertices; i++) {
            sb.append(vertices.get(i) + "\n");
        }

        sb.append("\nMatrix:\n");

        sb.append("  ");
        for (int i = 0; i < numVertices; i++) {
            sb.append(" |  " + i + " ");
        }
        sb.append("\n");

        // aligned only when vertices < 10
        for (int i = 0; i < numVertices; i++) {
            sb.append(" " + i + " ");
            for (int j = 0; j < numVertices; j++) {
                if (edgeMatrix[i][j] != null) {
                    sb.append("|  X  ");
                } else {
                    sb.append("|     ");
                }
            }
            sb.append("\n");
        }

        sb.append("\nEdges:\n");

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (edgeMatrix[i][j] != null) {
                    sb.append("From " + i + " to " + j + "-> " + edgeMatrix[i][j] + "\n");
                }
            }
        }

        sb.append("\n");

        return sb.toString();
    }

    /**
     * Returns a clone of the graph (a shallow copy).
     *
     * @return the new cloned graph
     * @throws java.lang.CloneNotSupportedException
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object clone() throws CloneNotSupportedException {
        MatrixGraph<V, E> newObject = new MatrixGraph<>();

        newObject.vertices = (ArrayList<V>) vertices.clone();

        newObject.numVertices = numVertices;

        newObject.edgeMatrix = (E[][]) new Object[edgeMatrix.length][edgeMatrix.length];

        for (int i = 0; i < edgeMatrix.length; i++) {
            newObject.edgeMatrix[i] = Arrays.copyOf(edgeMatrix[i], edgeMatrix.length);
        }

        newObject.numEdges = numEdges;

        return newObject;
    }


    @Override
    public boolean equals(Object other) {

        if (other == null) {
            return false;
        }

        if (this == other) {
            return true;
        }

        if (!(other instanceof MatrixGraph<?, ?>)) {
            return false;
        }

        MatrixGraph<?, ?> otherGraph = (MatrixGraph<?, ?>) other;

        if (numVertices != otherGraph.numVertices || numEdges != otherGraph.numEdges) {
            return false;
        }

        if (!vertices.equals(otherGraph.vertices)) {
            return false;
        }

        if (!Arrays.deepEquals(edgeMatrix, otherGraph.edgeMatrix)) {
            return false;
        }

        // fails to recognise difference between objects with different <E> type
        // when vertices are the same and both graphs have no edges
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.numVertices;
        hash = 53 * hash + this.numEdges;
        hash = 53 * hash + Objects.hashCode(this.vertices);
        hash = 53 * hash + Arrays.deepHashCode(this.edgeMatrix);
        return hash;
    }

}
