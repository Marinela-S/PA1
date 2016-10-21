/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa1;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class PA1 {

    public static void main (String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        int amount = 1000;
        int key = 0;
        populateList(list, amount);
        sort(list);
        //printList(list);

        long startTime = System.nanoTime();
        for (int i = 0; i < 30; i++) {
            int index = linearSearch(list, key);
            if (index != -1) {
                System.out.println("Linear Search Found " + key + " at " + index);
            } else {
                System.out.println("Linear Search: Not Found");
            }
        }
        long estimatedTime = System.nanoTime() - startTime;
        double seconds = (double) estimatedTime / 1000000000.0;
        System.out.println("Time Elapsed: " + seconds + " seconds");
            
        int index = binarySearch(list, key);
        if (index != -1) {
            System.out.println("Binary Search Found " + key + " at " + index);
        } else {
            System.out.println("Binary Seach: Not Found");
        }
    }

    public static void sort (ArrayList<Integer> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        quickSort(list, 0, list.size() - 1);
    }

    public static void quickSort (ArrayList<Integer> list, int lower, int upper) {
        int i = lower;
        int j = upper;
        int pivot = list.get(i + (j - i) / 2);

        while (i <= j) {
            while (list.get(i) < pivot) {
                i++;
            }
            while (list.get(j) > pivot) {
                j--;
            }
            if (i <= j) {
                swap(list, i, j);
                i++;
                j--;
            }
        }
        if (lower < j) {
            quickSort(list, lower, j);
        }
        if (i < upper) {
            quickSort(list, i, upper);
        }
    }

    public static void swap (ArrayList<Integer> list, int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    public static void populateList (ArrayList<Integer> list, int amount) {
        for (int i = 0; i < amount; i++) {
            int random = (int) Math.floor(Math.random() * 101);
            int sign = (int) (Math.random() * 2);
            if (sign == 1 && random != 0) /* Set to negative */ {
                random *= -1;
            }
            list.add(random);
        }
    }

    public static void printList (ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Random Number: " + list.get(i));
        }
    }

    public static int linearSearch (ArrayList<Integer> list, int key) {
        for (int i = 0; i < list.size(); i++) {
            if (key == list.get(i)) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch (ArrayList<Integer> list, int key) {
        if (!list.isEmpty()) {
            return binarySearch(list, key, 0, list.size() - 1);
        } else {
            return -1;
        }
    }

    private static int binarySearch (ArrayList<Integer> list, int key, int left, int right) {
        if (list.get((int) (right / 2)) != 1) {
            if (key == list.get((int) (right / 2))) {
                return (int) (right / 2);
            } else if (key < list.get((int) (right / 2))) {
                right = (int) (right / 2);
                return binarySearch(list, key, left, right);
            } else if (key > list.get((int) (right / 2))) {
                left = (int) (right / 2);
                return binarySearch(list, key, left, right);
            } else {
                return -1;
            }
        } else {
            return -1;
        }

    }
}
