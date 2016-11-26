package graphs.map;

import java.lang.reflect.Array;

/**
 * Represents a edge of a map graph.
 *
 * @author Daniel Gonçalves 1151452@isep.ipp.pt
 * @author Eric Jorge Schipper Ferreira do Amaral 1141570@isep.ipp.pt
 *
 * @param <V> the vertex
 * @param <E> the edge
 */
public class Edge<V, E> implements Comparable {

    /**
     * Edge information
     */
    private E element;

    /**
     * Edge weight
     */
    private double weight;

    /**
     * Vertex origin
     */
    private Vertex<V, E> vOrig;

    /**
     * Vertex destination
     */
    private Vertex<V, E> vDest;

    /**
     * Builds up an instance of Edge with parameters by default
     */
    public Edge() {
        this.element = null;
        this.weight = 0.0;
        this.vOrig = null;
        this.vDest = null;
    }

    /**
     * Builds up an instance of Vertex with parameters edge information, weight,
     * vertex origin and vertex destination
     *
     * @param eInf the edge information
     * @param weight the weight
     * @param vOrig the vertex origin
     * @param vDest the vertex destination
     */
    public Edge(E eInf, double weight, Vertex<V, E> vOrig, Vertex<V, E> vDest) {
        this.element = eInf;
        this.weight = weight;
        this.vOrig = vOrig;
        this.vDest = vDest;
    }

    /**
     * Obtain the Edge element
     *
     * @return the Edge element
     */
    public E getElement() {
        return element;
    }

    /**
     * Modifies the Edge element
     * 
     * @param eInf the new element to set
     */
    public void setElement(E eInf) {
        element = eInf;
    }

    /**
     * Obtain the Edge weight
     * 
     * @return the Edge weight 
     */
    public double getWeight() {
        return this.weight;
    }

    /**
     * Modifies the Edge weight
     * 
     * @param weight the new weight to set
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Obtain the element of the origin vertex
     * 
     * @return the element of the origin vertex
     */
    public V getVOrig() {
        return vOrig.getElement();
    }

    /**
     * Modifies the origin vertex
     * @param vOrig the new origin vertex to set
     */
    public void setVOrig(Vertex<V, E> vOrig) {
        this.vOrig = vOrig;
    }

    /**
     * Obtain the element of the destiny vertex
     * 
     * @return the element of the destiny vertex
     */
    public V getVDest() {
        return vDest.getElement();
    }

    /**
     * Modifies the destiny vertex
     * 
     * @param vDest the new destiny vertex to set
     */
    public void setVDest(Vertex<V, E> vDest) {
        this.vDest = vDest;
    }

    /**
     * Obtain the end points
     * 
     * @return the end points
     */
    public V[] getEndpoints() {

        V oElem = this.vOrig.getElement();
        V dElem = this.vDest.getElement(); // To get type

        V[] endverts = (V[]) Array.newInstance(oElem.getClass(), 2);

        endverts[0] = oElem;
        endverts[1] = dElem;

        return endverts;
    }

    @Override
    public boolean equals(Object otherObj) {

        if (this == otherObj) {
            return true;
        }

        if (otherObj == null || this.getClass() != otherObj.getClass()) {
            return false;
        }

        Edge<V, E> otherEdge = (Edge<V, E>) otherObj;

        // if endpoints vertices are not equal
        if (!this.vOrig.equals(otherEdge.vOrig) || !this.vDest.equals(otherEdge.vDest)) {
            return false;
        }

        if (this.weight != otherEdge.weight) {
            return false;
        }

        if (this.element != null && otherEdge.element != null) {
            return this.element.equals(otherEdge.element);
        }

        return true;
    }

    @Override
    public int compareTo(Object otherObject) {

        Edge<V, E> otherEdge = (Edge<V, E>) otherObject;
        if (this.weight < otherEdge.weight) {
            return -1;
        }
        if (this.weight == otherEdge.weight) {
            return 0;
        }
        return 1;
    }

    @Override
    public Edge<V, E> clone() {

        Edge<V, E> newEdge = new Edge<>();

        newEdge.element = this.element;
        newEdge.weight = this.weight;
        newEdge.vOrig = this.vOrig;
        newEdge.vDest = this.vDest;

        return newEdge;
    }

    @Override
    public String toString() {
        String st = "";
        if (this.element != null) {
            st = "      (" + this.element + ") - ";
        } else {
            st = "\t ";
        }

        if (this.weight != 0) {
            st += this.weight + " - " + this.vDest.getElement() + "\n";
        } else {
            st += this.vDest.getElement() + "\n";
        }

        return st;
    }

}
