/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import utils.FileManager;

/**
 * Tests a AVL Binary Search Tree for Mayors (Orderem by total points).
 *
 * @author Daniel Gonçalves 1151452@isep.ipp.pt
 * @author Eric Jorge Schipper Ferreira do Amaral 1141570@isep.ipp.pt
 */
public class MayorAVLTest {

    /**
     * Social Network object to test with 10 cities & 10 users.
     */
    SocialNetwork sn10;

    public MayorAVLTest() {

        sn10 = FileManager.loadSocialNetwork(
                FileManager.defaultCitiesFile(FileManager.DEFAULT_TEN),
                FileManager.defaultUsersFile(FileManager.DEFAULT_TEN));
    }

    /**
     * Test of createTree method, of class MayorAVL.
     */
    @Test
    public void testCreateTree01() {

        System.out.println("createTree");
        List<User> expResult = new LinkedList<>(sn10.getUsersList());
        MayorAVL avl = new MayorAVL();
        avl.createTree(expResult);
        List<User> result = (List<User>) avl.inOrder();

        // Test if AVL Tree is ordered & has the same size
        Collections.sort(expResult); // Order expected result

        System.out.println("List:");
        for (User user : expResult) {
            System.out.printf("U:%s S:%d%n", user.getNickname(), user.totalScore());
        }
        System.out.println("\n\n");

        assertEquals(expResult, result);

    }

    /**
     * Test of createTree method, of class MayorAVL.
     */
    @Test
    public void testCreateTree02() {

        System.out.println("createTree");
        MayorAVL expResult = new MayorAVL();

        MayorAVL result = new MayorAVL();
        result.createTree(sn10.getUsersList());

        // Verify if tree is equal to empty list.
        assertFalse(expResult.equals(result));

    }
}
