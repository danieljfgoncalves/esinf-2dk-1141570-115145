package esinf_2dk_1141570_1151452.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests Algorithms
 *
 * @author Daniel Gonçalves 1151452@isep.ipp.pt
 * @author Eric Jorge Schipper Ferreira do Amaral 1141570@isep.ipp.pt
 */
public class AlgorithmsTest {

    /**
     * Ordered List of integers.
     */
    private List<Integer> orderedList;
    
    /**
     * Unordered List of integers.
     */
    private List<Integer> unorderedList;
    
    /**
     * Comparator for integers.
     */
    private Comparator<Integer> comparator;

    public AlgorithmsTest() {

        orderedList = new ArrayList();
        for (int i = 0; i < 1000000; i++) {
            orderedList.add(i);
        }

        unorderedList = new ArrayList(orderedList);
        Collections.shuffle(unorderedList);

        comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {

                return i1 == i2 ? 0 : i1 > i2 ? 1 : -1;
            }
        };
    }

    /**
     * Test of mergeSort method, of class Algorithms.
     */
    @Test
    public void testMergeSort_List_Comparator01() {
        System.out.println("mergeSort");

        List unordered = new ArrayList();
        unordered.add("Eric");
        unordered.add("Daniel");
        unordered.add("Ana");
        unordered.add("Joana");
        unordered.add("Bob");
        unordered.add("Jonas");

        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        };

        List expResult = new ArrayList();
        expResult.add("Ana");
        expResult.add("Bob");
        expResult.add("Daniel");
        expResult.add("Eric");
        expResult.add("Joana");
        expResult.add("Jonas");

        List result = Algorithms.mergeSort(unordered, comparator);
        assertEquals(expResult, result);
    }

    /**
     * Test of mergeSort method, of class Algorithms.
     */
    @Test
    public void testMergeSort_List_Comparator02() {
        System.out.println("mergeSort");

        List result = Algorithms.mergeSort(unorderedList, comparator);
        assertEquals(orderedList, result);
    }

    /**
     * Test of mergeSort method, of class Algorithms.
     */
    @Test
    public void testMergeSort_Set_Comparator01() {
        System.out.println("mergeSort");
        Set<Integer> set = new HashSet<Integer>(unorderedList);

        List result = Algorithms.mergeSort(set, comparator);
        assertEquals(orderedList, result);
    }

    /**
     * Test of mergeSort method, of class Algorithms.
     */
    @Test
    public void testMergeSort_Set_Comparator02() {
        System.out.println("mergeSort");

        Set unorderedSet = new HashSet();
        unorderedSet.add("Ana");
        unorderedSet.add("Bob");
        unorderedSet.add("Daniel");
        unorderedSet.add("Eric");
        unorderedSet.add("Joana");
        unorderedSet.add("Jonas");

        Comparator<String> stringComparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        };

        List expResult = new ArrayList();
        expResult.add("Ana");
        expResult.add("Bob");
        expResult.add("Daniel");
        expResult.add("Eric");
        expResult.add("Joana");
        expResult.add("Jonas");

        List result = Algorithms.mergeSort(unorderedSet, stringComparator);
        assertEquals(expResult, result);
    }
}
