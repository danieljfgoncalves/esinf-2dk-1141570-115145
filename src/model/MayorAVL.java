package model;

import bsts.AVL;
import java.util.Iterator;

/**
 * Represents a AVL Binary Search Tree for Mayors (Orderem by total points).
 *
 * @author Daniel Gonçalves 1151452@isep.ipp.pt
 * @author Eric Jorge Schipper Ferreira do Amaral 1141570@isep.ipp.pt
 */
public class MayorAVL extends AVL<User> {

    /**
     * Constructs a MayorBST class.
     */
    public MayorAVL() {
        super();
    }

    /**
     * Creates the tree from a list of mayors.
     *
     * @param list List with mayors to add to tree.
     */
    public void createTree(Iterable<User> list) {

        Iterator<User> mayorsIt = list.iterator();
        while (mayorsIt.hasNext()) {

            insert(mayorsIt.next());
        }
    }
}
