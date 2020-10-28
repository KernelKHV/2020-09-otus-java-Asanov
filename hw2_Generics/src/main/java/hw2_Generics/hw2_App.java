package hw2_Generics;

import hw2_Generics.generics.DIYArrayList;
import java.util.Collections;

import java.util.ArrayList;
import java.util.List;

public class hw2_App {

    public static void main(String[] args) {
        System.out.println("addAll");
        //ArrayList<String> first = new ArrayList<>();
        DIYArrayList<String> first = new DIYArrayList<>();
        for (int i=0; i<20; i++){
            first.add("number "+String.valueOf(i));
        }

        DIYArrayList<String> strAddAll = new DIYArrayList<>();

        strAddAll.addAll(first);

        for(String testAdd:strAddAll){
            System.out.println(testAdd);
        }

        System.out.println("\ncopy");

        //DIYArrayList<String> slave = new DIYArrayList<>();
        DIYArrayList<String> slave = new DIYArrayList<>();
        for (int i=20; i>0; i--){
            slave.add("slave "+String.valueOf(i));
        }
        System.out.println("Value of slave list: ");
        for (String testCopy:slave){
            System.out.println(testCopy);
        }

        System.out.println("Value of first list: ");
        for (String testCopy:first){
            System.out.println(testCopy);
        }

        System.out.println("\nAfter copying:\n");

        Collections.copy(slave, first);

        System.out.println("Value of slave list: ");
        for (String testCopy:slave){
            System.out.println(testCopy);
        }

        System.out.println("\nsort");
        Collections.sort(slave);
        for (String testSort:slave){
            System.out.println(testSort);
        }
    }
}

