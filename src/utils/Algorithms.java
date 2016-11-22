/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Represents a class with useful algorithms.
 *
 * @author Daniel Gonçalves 1151452@isep.ipp.pt
 * @author Eric Jorge Schipper Ferreira do Amaral 1141570@isep.ipp.pt
 */
public class Algorithms {

    // MERGE SORT
    /**
     * Merge Sort algorithm that sorts an array
     *
     * @param <T> Generic element.
     * @param list list to be sorted.
     * @param comparator comparator of T elements.
     *
     * @return a sorted list.
     */
    public static <T> List mergeSort(List<T> list, Comparator<? super T> comparator) {

        int n = list.size();

        T[] array = (T[]) list.toArray();

        mergeSort(array, n, comparator);

        return Arrays.asList(array);
    }

    /**
     * Merge Sort algorithm that sorts an array
     *
     * @param <T> Generic element.
     * @param set set to be sorted.
     * @param comparator comparator of T elements.
     *
     * @return a sorted list.
     */
    public static <T> List mergeSort(Set<T> set, Comparator<? super T> comparator) {

        int n = set.size();

        T[] array = (T[]) set.toArray();

        mergeSort(array, n, comparator);

        return Arrays.asList(array);
    }

    /**
     * Merge Sort algorithm that sorts an array
     *
     * @param <T> Generic element.
     * @param array array to be sorted.
     * @param n array size.
     * @param comparator comparator of T elements.
     */
    private static <T> void mergeSort(T[] array, int n, Comparator<? super T> comparator) {

        if (n > 1) { // If the list has 1 or 0 elements no need to split anymore or even sort.

            int mid = n / 2; // index of midpoint
            T array1[] = Arrays.copyOfRange(array, 0, mid);
            T array2[] = Arrays.copyOfRange(array, mid, n);

            // Recursively split arrays until of singled element arrays.
            mergeSort(array1, array1.length, comparator);
            mergeSort(array2, array2.length, comparator);

            // Merge two sorted sequences
            merge(array1, array2, array, comparator);
        }
    }

    /**
     * Merges to arrays in one by a specific order
     *
     * @param <T> Generic e.lement
     * @param first first array
     * @param second second array
     * @param result merged array (first + second)
     * @param comparator comparator of T elements.
     */
    private static <T> void merge(T[] first, T[] second, T[] result, Comparator<? super T> comparator) {

        // Index Position of first array.
        int index1 = 0;

        // Index Position of second array.
        int index2 = 0;

        // Index Position of merged array.
        int indexR = 0;

        // Compare elements and move smaller element to indexR.
        while (index1 < first.length && index2 < second.length) {

            if (comparator.compare(first[index1], second[index2]) < 0) {

                result[indexR] = first[index1];
                index1++;

            } else {

                result[indexR] = second[index2];
                index2++;
            }
            indexR++;
        }

        // Copy remaining elements of the array that didn't get to the end.
        if (index1 < first.length) {

            System.arraycopy(first, index1, result, indexR, first.length - index1);
        } else {

            System.arraycopy(second, index2, result, indexR, second.length - index2);
        }

    }
}
