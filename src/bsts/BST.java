package bsts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a Binary Search Tree.
 *
 * @author Daniel Gonçalves 1151452@isep.ipp.pt
 * @author Eric Jorge Schipper Ferreira do Amaral 1141570@isep.ipp.pt
 *
 */
public class BST<E extends Comparable<E>> {

    /**
     * The root of the binary search tree
     */
    protected Node<E> root = null;     // root of the tree

    /**
     * Constructs an empty binary search tree.
     */
    public BST() {    // constructs an empty binary search tree
        root = null;
    }
//****************************************************************

    /**
     * Returns the root Node of the tree (or null if tree is empty).
     *
     * @return root Node of the tree (or null if tree is empty)
     */
    protected Node<E> root() {
        return root;
    }
//****************************************************************

    /**
     * Verifies if the tree is empty.
     *
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }
//****************************************************************

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree
     */
    public int size() {
        return size(root);
    }

    private int size(Node<E> node) {
        return (node == null) ? 0 : (1 + size(node.getLeft()) + size(node.getRight()));
    }
//****************************************************************

    /**
     * Inserts an element in the tree.
     */
    public void insert(E element) {
        root = insert(element, root);
    }

    private Node<E> insert(E element, Node<E> node) {

        if (node == null) {
            return new Node(element, null, null);
        }

        if (node.getElement().compareTo(element) == 0) {      // replace existing element
            node.setElement(element);
        } else if (element.compareTo(node.getElement()) < 0) // add element to the left subtree
        {
            node.setLeft(insert(element, node.getLeft()));
        } else // add element to the right subtree
        {
            node.setRight(insert(element, node.getRight()));
        }
        return node;
    }
//****************************************************************

    /**
     * Removes an element from the tree maintaining its consistency as a Binary
     * Search Tree.
     */
    public void remove(E element) {
        root = remove(element, root());
    }

    private Node<E> remove(E element, Node<E> node) {
        if (node == null) {
            return null;
        }
        if (element.compareTo(node.getElement()) == 0) {
            // node is the Node to be removed
            if (node.getLeft() == null && node.getRight() == null) { //node is a leaf (has no childs)
                return null;
            }
            if (node.getLeft() == null) {   //has only right child
                return node.getRight();
            }
            if (node.getRight() == null) {  //has only left child
                return node.getLeft();
            }
            Node<E> q = node.getLeft();
            Node<E> p = q.getRight();
            if (p == null) {
                node.setElement(q.getElement());
                node.setLeft(q.getLeft());
            } else {
                while (p.getRight() != null) {
                    q = p;
                    p = p.getRight();
                }//while
                node.setElement(p.getElement());
                q.setRight(p.getLeft());
            }
            return node;
        } else if (element.compareTo(node.getElement()) < 0) {
            node.setLeft(remove(element, node.getLeft()));
            return node;
        } else {
            node.setRight(remove(element, node.getRight()));
            return node;
        }
    }
//****************************************************************

    /**
     * Returns the smallest element within the tree.
     *
     * @return the smallest element within the tree
     */
    public E smallestElement() {
        return smallestElement(root);
    }

    protected E smallestElement(Node<E> node) {
        if (node == null) {
            return null;
        }
        Node<E> nodeAux;
        for (nodeAux = node; nodeAux.getLeft() != null; nodeAux = nodeAux.getLeft());
        return nodeAux.getElement();
    }
    //****************************************************************

    /**
     * Returns a map with a list of nodes by each tree level.
     *
     * @return a map with a list of nodes by each tree level
     */
    public Map<Integer, List<E>> nodesByLevel() {
        Map<Integer, List<E>> result = new HashMap();
        processBstByLevel(root, result, 0);
        return result;
    }

    private void processBstByLevel(Node<E> node, Map<Integer, List<E>> result, int level) {
        if (node == null) {
            return;
        }
        List<E> list = result.get(level);
        if (list == null) {
            list = new ArrayList();
        }

        list.add(node.getElement());
        result.put(level, list);
        processBstByLevel(node.getLeft(), result, level + 1);
        processBstByLevel(node.getRight(), result, level + 1);
    }
    //---------------------------------------------------------------   

    /**
     * Returns the height of the tree
     *
     * @return
     */
    public int height() {
        return (root == null) ? -1 : height(root);
    }

    /**
     * Returns the height of the subtree rooted at Node node.
     *
     * @param node A valid Node within the tree
     * @return
     */
    protected int height(Node<E> node) {
        if (node == null) {
            return -1;
        }

        int hl = height(node.getLeft());
        int hr = height(node.getRight());

        return 1 + ((hl < hr) ? hr : hl);
    }

//****************************************************************
    /**
     * Returns the Node containing a specific Element, or null otherwise.
     *
     * @param element the element to find
     * @return the Node that contains the Element, or null otherwise
     *
     * This method despite not being essential is very useful. It is written
     * here in order to be used by this class and its subclasses avoiding
     * recoding. So its access level is protected
     */
    protected Node<E> find(E element, Node<E> node) {
        if (node == null) {
            return null;
        }
        if (element.compareTo(node.getElement()) == 0) {
            return node;
        }
        if (element.compareTo(node.getElement()) < 0) {
            return find(element, node.getLeft());
        }
        return find(element, node.getRight());
    }
//****************************************************************

    /**
     * Returns an iterable collection of elements of the tree, reported in
     * in-order.
     *
     * @return iterable collection of the tree's elements reported in in-order
     */
    public Iterable<E> inOrder() {
        List<E> snapshot = new ArrayList<>();
        if (root != null) {
            inOrderSubtree(root, snapshot);   // fill the snapshot recursively
        }
        return snapshot;
    }

    /**
     * Adds elements of the subtree rooted at Node node to the given snapshot
     * using an in-order traversal
     *
     * @param node Node serving as the root of a subtree
     * @param snapshot a list to which results are appended
     */
    private void inOrderSubtree(Node<E> node, List<E> snapshot) {
        if (node == null) {
            return;
        }
        inOrderSubtree(node.getLeft(), snapshot);
        snapshot.add(node.getElement());
        inOrderSubtree(node.getRight(), snapshot);
    }

    /**
     * Returns an iterable collection of elements of the tree, reported in
     * pre-order.
     *
     * @return iterable collection of the tree's elements reported in pre-order
     */
    public Iterable<E> preOrder() {
        List<E> snapshot = new ArrayList<>();
        if (root != null) {
            preOrderSubtree(root, snapshot);   // fill the snapshot recursively
        }
        return snapshot;
    }

    /**
     * Adds elements of the subtree rooted at Node node to the given snapshot
     * using an pre-order traversal
     *
     * @param node Node serving as the root of a subtree
     * @param snapshot a list to which results are appended
     */
    private void preOrderSubtree(Node<E> node, List<E> snapshot) {
        if (node == null) {
            return;
        }
        snapshot.add(node.getElement());
        preOrderSubtree(node.getLeft(), snapshot);
        preOrderSubtree(node.getRight(), snapshot);
    }

    /**
     * Returns an iterable collection of elements of the tree, reported in
     * post-order.
     *
     * @return iterable collection of the tree's elements reported in post-order
     */
    public Iterable<E> postOrder() {
        List<E> snapshot = new ArrayList<>();
        if (root != null) {
            postOrderSubtree(root, snapshot);   // fill the snapshot recursively
        }
        return snapshot;
    }

    /**
     * Adds positions of the subtree rooted at Node node to the given snapshot
     * using an post-order traversal
     *
     * @param node Node serving as the root of a subtree
     * @param snapshot a list to which results are appended
     */
    private void postOrderSubtree(Node<E> node, List<E> snapshot) {
        if (node == null) {
            return;
        }
        postOrderSubtree(node.getLeft(), snapshot);
        postOrderSubtree(node.getRight(), snapshot);
        snapshot.add(node.getElement());
    }

//#########################################################################
    //---------------- nested Node class ----------------
    /**
     * Nested static class for a binary search tree node.
     */
    protected static class Node<E> {

        private E element;          // an element stored at this node
        private Node<E> left;       // a reference to the left child (if any)
        private Node<E> right;      // a reference to the right child (if any)

        /**
         * Constructs a node with the given element and neighbors.
         *
         * @param e the element to be stored
         * @param leftChild reference to a left child node
         * @param rightChild reference to a right child node
         */
        public Node(E e, Node<E> leftChild, Node<E> rightChild) {
            element = e;
            left = leftChild;
            right = rightChild;
        }

        // accessor methods
        public E getElement() {
            return element;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }

        // update methods
        public void setElement(E e) {
            element = e;
        }

        public void setLeft(Node<E> leftChild) {
            left = leftChild;
        }

        public void setRight(Node<E> rightChild) {
            right = rightChild;
        }
    } //----------- end of nested Node class -----------

//#########################################################################
//#########################################################################
    /**
     * Returns a string representation of the tree. Draw the tree horizontally
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toStringRec(root, 0, sb);
        return sb.toString();
    }

    private void toStringRec(Node<E> root, int level, StringBuilder sb) {
        if (root == null) {
            return;
        }
        toStringRec(root.getRight(), level + 1, sb);
        if (level != 0) {
            for (int i = 0; i < level - 1; i++) {
                sb.append("|\t");
            }
            sb.append("|-------" + root.getElement() + "\n");
        } else {
            sb.append(root.getElement() + "\n");
        }
        toStringRec(root.getLeft(), level + 1, sb);
    }

} //----------- end of BST class -----------

