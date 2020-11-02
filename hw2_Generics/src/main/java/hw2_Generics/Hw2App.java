package hw2_Generics;

import hw2_Generics.generics.DIYArrayList;

import java.util.Collections;

import java.util.Comparator;
import java.util.List;

public class Hw2App {

    public static void main(String[] args) {
        System.out.println("Test addAll");
        List<String> first = new DIYArrayList<>();
        for (int i = 0; i < 20; i++) {
            first.add("number " + i);
        }

        Collections.addAll(first, "1", "2", "3", "4");
        for (String testAdd : first) {
            System.out.println(testAdd);
        }

        System.out.println("\nTest copy");
        List<String> slave = new DIYArrayList<>();
        for (int i = 20; i > 0; i--) {
            slave.add("slave " + i);
        }
        System.out.println("Value of slave list: ");
        for (String testCopy : slave) {
            System.out.println(testCopy);
        }

        System.out.println("Value of first list: ");
        for (String testCopy : first) {
            System.out.println(testCopy);
        }

        System.out.println("\nAfter copying:\n");

        Collections.copy(first, slave);
        System.out.println("Value of first list: ");
        for (String testCopy : first) {
            System.out.println(testCopy);
        }

        System.out.println("\nTest sort");
        List<Integer> sortTest = new DIYArrayList<>();
        for (int i = 20; i > 0; i--) {
            sortTest.add((int) (Math.random() * (100)));
        }

        System.out.println("Unsorted");
        for (Integer unSort : sortTest) {
            System.out.println(unSort);
        }

        Collections.sort(sortTest, Comparator.naturalOrder());
        System.out.println("Sorted");
        for (Integer Sort : sortTest) {
            System.out.println(Sort);
        }
    }
}

