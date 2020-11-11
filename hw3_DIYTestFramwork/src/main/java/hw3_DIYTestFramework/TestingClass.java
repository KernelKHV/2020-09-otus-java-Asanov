package hw3_DIYTestFramework;

import hw3_DIYTestFramework.annotations.*;

import java.util.ArrayList;
import java.util.List;

public class TestingClass {
    @Before
    public void start() {
        System.out.println("Start Test run Before");
    }

    @Test
    public void test1() {
        System.out.println("Start Test 1");
    }

    @Test
    public void test2() {
        System.out.println("Start Test 2");
        List<String> test2= new ArrayList();
        test2.add("first");
        test2.add("second");
        test2.get(3);
    }


    @After
    public void theEnd() {
        System.out.println("End Test run After");
    }
}
