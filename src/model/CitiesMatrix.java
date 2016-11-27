package model;

import graphs.matrix.MatrixGraph;

/**
 * Represents a matrix graph of cities.
 *
 * @author Daniel Gonçalves 1151452@isep.ipp.pt
 * @author Eric Jorge Schipper Ferreira do Amaral 1141570@isep.ipp.pt
 */
public class CitiesMatrix {

    /**
     * Graph that contains all cities.
     */
    private MatrixGraph<City, Double> graph;

    /**
     * Constructs a empty cities matrix graph.
     */
    public CitiesMatrix() {
        this.graph = new MatrixGraph();
    }

    /**
     * Constructs a cities matrix graph receiving the graph
     *
     * @param graph graph with cities
     */
    public CitiesMatrix(MatrixGraph graph) {
        this.graph = graph;
    }

    /**
     * Copy constructor of a cities matrix graph
     *
     * @param citiesMatrix cities matrix to copy
     */
    public CitiesMatrix(CitiesMatrix citiesMatrix) {
        this.graph = citiesMatrix.graph;
    }

    /**
     * Obtains the Graph that contains all cities.
     *
     * @return the graph
     */
    public MatrixGraph getGraph() {
        return graph;
    }

    /**
     * Sets the Graph that contains all cities.
     *
     * @param graph the graph to set
     */
    public void setGraph(MatrixGraph graph) {
        this.graph = graph;
    }

    /**
     * Obtains all cities of graph.
     *
     * @return the graph
     */
    public Iterable<City> getCities() {
        return this.graph.vertices();
    }
}
